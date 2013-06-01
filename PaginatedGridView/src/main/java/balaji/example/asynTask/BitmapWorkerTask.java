package balaji.example.asynTask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import balaji.example.adapter.MovieAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by balajig on 6/1/13.
 */
public class BitmapWorkerTask extends AsyncTask<String, Void, Bitmap> {
    private final WeakReference<ImageView> imageViewReference;
    public String data = "";

    public BitmapWorkerTask(WeakReference<ImageView> imageViewReference) {
        this.imageViewReference = imageViewReference;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        data = params[0];
        return MovieAdapter.decodeSampledBitmapFromResource(params[0], 150, 200);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (isCancelled()) {
            bitmap = null;
        }

        if (imageViewReference != null && bitmap != null) {
            final ImageView imageView = imageViewReference.get();
            final BitmapWorkerTask bitmapWorkerTask = MovieAdapter.getBitmapWorkerTask(imageView);
            if (this == bitmapWorkerTask && imageView != null) {
                imageView.setScaleType(ImageView.ScaleType.FIT_START);
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}
