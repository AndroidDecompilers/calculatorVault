package calculator.vault.com.lock;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class C1123b extends ViewGroup implements Callback {
    SurfaceView f3296a;
    SurfaceHolder f3297b;
    Size f3298c;
    List<Size> f3299d;
    Camera f3300e;
    AutoFocusCallback f3301f = new C11221(this);
    private final String f3302g = "Preview";

    class C11221 implements AutoFocusCallback {
        final /* synthetic */ C1123b f3295a;

        C11221(C1123b c1123b) {
            this.f3295a = c1123b;
        }

        public void onAutoFocus(boolean z, Camera camera) {
        }
    }

    public C1123b(Context context, SurfaceView surfaceView) {
        super(context);
        this.f3296a = surfaceView;
        this.f3297b = this.f3296a.getHolder();
        this.f3297b.addCallback(this);
        this.f3297b.setType(3);
    }

    public Size m5799a() {
        Size size = (Size) this.f3299d.get(0);
        Size size2 = size;
        for (Size size3 : this.f3299d) {
            if (size3.height * size3.width <= size2.width * size2.height) {
                size3 = size2;
            }
            size2 = size3;
        }
        return size2;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            try {
                if (getChildCount() > 0) {
                    int i5;
                    int i6;
                    View childAt = getChildAt(0);
                    int i7 = i3 - i;
                    int i8 = i4 - i2;
                    if (this.f3298c != null) {
                        i5 = this.f3298c.width;
                        i6 = this.f3298c.height;
                    } else {
                        i6 = i8;
                        i5 = i7;
                    }
                    if (i7 * i6 > i8 * i5) {
                        i6 = (i5 * i8) / i6;
                        childAt.layout((i7 - i6) / 2, 0, (i6 + i7) / 2, i8);
                        return;
                    }
                    i6 = (i6 * i7) / i5;
                    childAt.layout(0, (i8 - i6) / 2, i7, (i6 + i8) / 2);
                }
            } catch (Exception e) {
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(C1123b.resolveSize(getSuggestedMinimumWidth(), i), C1123b.resolveSize(getSuggestedMinimumHeight(), i2));
        if (this.f3299d != null) {
            this.f3298c = m5799a();
        }
    }

    public void setCamera(Camera camera) {
        this.f3300e = camera;
        if (this.f3300e != null) {
            try {
                this.f3299d = this.f3300e.getParameters().getSupportedPictureSizes();
                requestLayout();
                Parameters parameters = this.f3300e.getParameters();
                List supportedFocusModes = parameters.getSupportedFocusModes();
                Log.e("main", "focus size: " + supportedFocusModes.size());
                if (supportedFocusModes.contains("auto")) {
                    parameters.setFocusMode("auto");
                    this.f3300e.setParameters(parameters);
                }
            } catch (Exception e) {
            }
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (this.f3300e != null) {
            try {
                Parameters parameters = this.f3300e.getParameters();
                parameters.setPictureSize(this.f3298c.width, this.f3298c.height);
                requestLayout();
                this.f3300e.setParameters(parameters);
                this.f3300e.startPreview();
            } catch (Exception e) {
            }
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            if (this.f3300e != null) {
                this.f3300e.setPreviewDisplay(surfaceHolder);
            }
        } catch (Throwable e) {
            Log.e("Preview", "IOException caused by setPreviewDisplay()", e);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (this.f3300e != null) {
            try {
                this.f3300e.stopPreview();
            } catch (Exception e) {
            }
        }
    }
}
