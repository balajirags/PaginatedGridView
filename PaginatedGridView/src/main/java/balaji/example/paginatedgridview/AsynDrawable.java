package balaji.example.paginatedgridview;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import balaji.example.asynTask.BitmapWorkerTask;

import java.lang.ref.WeakReference;

/**
 * Created by balajig on 6/1/13.
 */
public class AsynDrawable extends BitmapDrawable {
    private final WeakReference<BitmapWorkerTask> bitmapWorkerTaskReference;

    public AsynDrawable(Resources res, Bitmap bitmap,
                        BitmapWorkerTask bitmapWorkerTask) {
        super(res, bitmap);
        bitmapWorkerTaskReference =
                new WeakReference<BitmapWorkerTask>(bitmapWorkerTask);
    }

    public BitmapWorkerTask getBitmapWorkerTask() {
        return bitmapWorkerTaskReference.get();
    }
}