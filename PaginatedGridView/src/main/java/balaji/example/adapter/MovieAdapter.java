package balaji.example.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import balaji.example.asynTask.BitmapWorkerTask;
import balaji.example.model.Movie;
import balaji.example.paginatedgridview.AsynDrawable;
import balaji.example.paginatedgridview.R;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by balajig on 5/31/13.
 */
public class MovieAdapter extends BaseAdapter {

    private Context context;

    List<Movie> movieList = new ArrayList<Movie>();

    public MovieAdapter(List<Movie> movies, Context context) {
        this.context = context;
        movieList = movies;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ImageView imageView;
        View row = null;
        if (convertView == null) { // if it's not recycled, initialize some attributes
            row = LayoutInflater.from(context).inflate(R.layout.movie_view, null, false);
        }else{
            row = convertView;
        }
        imageView = (ImageView) row.findViewById(R.id.imageView);
        final Movie movie = movieList.get(position);
        if (cancelPotentialWork(movie.getImageUrl(), imageView)) {
            BitmapWorkerTask task = new BitmapWorkerTask(new WeakReference<ImageView>(imageView));
            AsynDrawable asyncDrawable = new AsynDrawable(null, null, task);
            imageView.setImageDrawable(asyncDrawable);
            task.execute(movie.getImageUrl());
        }
        return row;
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

    public static Bitmap decodeSampledBitmapFromResource(String url,
                                                         int reqWidth, int reqHeight) {

        InputStream is = null;
        Bitmap bitmap = null;
        try {
            is = (InputStream) new URL(url).getContent();
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            bitmap = BitmapFactory.decodeStream(is, null, options);
            options.inSampleSize = MovieAdapter.calculateInSampleSize(options, 150, 200);
            options.inJustDecodeBounds = false;
            is = (InputStream) new URL(url).getContent();
            bitmap = BitmapFactory.decodeStream(is, null, options);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static boolean cancelPotentialWork(String data, ImageView imageView) {
        final BitmapWorkerTask bitmapWorkerTask = getBitmapWorkerTask(imageView);

        if (bitmapWorkerTask != null) {
            final String bitmapData = bitmapWorkerTask.data;
            if (bitmapData != data) {
                // Cancel previous task
                bitmapWorkerTask.cancel(true);
            } else {
                // The same work is already in progress
                return false;
            }
        }
        // No task associated with the ImageView, or an existing task was cancelled
        return true;
    }

    public static BitmapWorkerTask getBitmapWorkerTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsynDrawable) {
                final AsynDrawable asyncDrawable = (AsynDrawable) drawable;
                return asyncDrawable.getBitmapWorkerTask();
            }
        }
        return null;
    }

}

