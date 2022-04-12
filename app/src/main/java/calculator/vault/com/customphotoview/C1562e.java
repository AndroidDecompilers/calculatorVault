package calculator.vault.com.customphotoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

public class C1562e extends BitmapTransformation {
    private float f4411a = 0.0f;

    public C1562e(Context context, float f) {
        super(context);
        this.f4411a = f;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Matrix matrix = new Matrix();
        matrix.postRotate(this.f4411a);
        return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);

    }

    @Override
    public String getId() {
        return "rotate" + this.f4411a;
    }
}
