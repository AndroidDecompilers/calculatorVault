package calculator.vault.com.customphotoview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector.OnDoubleTapListener;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class PhotoView extends ImageView implements C1551c {
    private final C1561d f4368a;
    private ScaleType f4369b;

    public PhotoView(Context context) {
        this(context, null);
    }

    public PhotoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setScaleType(ScaleType.MATRIX);
        this.f4368a = new C1561d(this);
        if (this.f4369b != null) {
            setScaleType(this.f4369b);
            this.f4369b = null;
        }
    }

    public Matrix getDisplayMatrix() {
        return this.f4368a.m7191l();
    }

    public RectF getDisplayRect() {
        return this.f4368a.m7175b();
    }

    public C1551c getIPhotoViewImplementation() {
        return this.f4368a;
    }

    @Deprecated
    public float getMaxScale() {
        return getMaximumScale();
    }

    public float getMaximumScale() {
        return this.f4368a.m7184f();
    }

    public float getMediumScale() {
        return this.f4368a.m7182e();
    }

    @Deprecated
    public float getMidScale() {
        return getMediumScale();
    }

    @Deprecated
    public float getMinScale() {
        return getMinimumScale();
    }

    public float getMinimumScale() {
        return this.f4368a.m7180d();
    }

    public C1561d.C1559d getOnPhotoTapListener() {
        return this.f4368a.m7188i();
    }

    public C1561d.C1560e getOnViewTapListener() {
        return this.f4368a.m7189j();
    }

    public float getScale() {
        return this.f4368a.m7186g();
    }

    public ScaleType getScaleType() {
        return this.f4368a.m7187h();
    }

    public Bitmap getVisibleRectangleBitmap() {
        return this.f4368a.m7192m();
    }

    protected void onDetachedFromWindow() {
        this.f4368a.m7160a();
        super.onDetachedFromWindow();
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.f4368a.m7174a(z);
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (this.f4368a != null) {
            this.f4368a.m7190k();
        }
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        if (this.f4368a != null) {
            this.f4368a.m7190k();
        }
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if (this.f4368a != null) {
            this.f4368a.m7190k();
        }
    }

    @Deprecated
    public void setMaxScale(float f) {
        setMaximumScale(f);
    }

    public void setMaximumScale(float f) {
        this.f4368a.m7183e(f);
    }

    public void setMediumScale(float f) {
        this.f4368a.m7181d(f);
    }

    @Deprecated
    public void setMidScale(float f) {
        setMediumScale(f);
    }

    @Deprecated
    public void setMinScale(float f) {
        setMinimumScale(f);
    }

    public void setMinimumScale(float f) {
        this.f4368a.m7179c(f);
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        this.f4368a.m7168a(onDoubleTapListener);
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.f4368a.m7169a(onLongClickListener);
    }

    public void setOnMatrixChangeListener(C1561d.C1558c c1558c) {
        this.f4368a.m7171a(c1558c);
    }

    public void setOnPhotoTapListener(C1561d.C1559d c1559d) {
        this.f4368a.m7172a(c1559d);
    }

    public void setOnViewTapListener(C1561d.C1560e c1560e) {
        this.f4368a.m7173a(c1560e);
    }

    public void setPhotoViewRotation(float f) {
        this.f4368a.m7161a(f);
    }

    public void setRotationBy(float f) {
        this.f4368a.m7176b(f);
    }

    public void setRotationTo(float f) {
        this.f4368a.m7161a(f);
    }

    public void setScale(float f) {
        this.f4368a.m7185f(f);
    }

    public void setScaleType(ScaleType scaleType) {
        if (this.f4368a != null) {
            this.f4368a.m7170a(scaleType);
        } else {
            this.f4369b = scaleType;
        }
    }

    public void setZoomTransitionDuration(int i) {
        this.f4368a.m7167a(i);
    }

    public void setZoomable(boolean z) {
        this.f4368a.m7177b(z);
    }
}
