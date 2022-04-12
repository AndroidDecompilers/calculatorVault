package calculator.vault.com.customphotoview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import java.lang.ref.WeakReference;

import calculator.vault.com.p001a.C0000d;
import calculator.vault.com.p026a.C0964d;
import calculator.vault.com.p026a.C0969e;
import calculator.vault.com.p026a.C0970f;

@TargetApi(3)
public class C1561d implements OnTouchListener, OnGlobalLayoutListener, C0969e, C1551c {
    static final Interpolator f4384a = new AccelerateDecelerateInterpolator();
    private static final boolean f4385c = Log.isLoggable("PhotoViewAttacher", 3);
    private ScaleType f4386A = ScaleType.FIT_CENTER;
    int f4387b = 200;
    private float f4388d = 1.0f;
    private float f4389e = 1.75f;
    private float f4390f = 3.0f;
    private boolean f4391g = true;
    private WeakReference<ImageView> f4392h;
    private GestureDetector f4393i;
    private C0964d f4394j;
    private final Matrix f4395k = new Matrix();
    private final Matrix f4396l = new Matrix();
    private final Matrix f4397m = new Matrix();
    private final RectF f4398n = new RectF();
    private final float[] f4399o = new float[9];
    private C1558c f4400p;
    private C1559d f4401q;
    private C1560e f4402r;
    private OnLongClickListener f4403s;
    private int f4404t;
    private int f4405u;
    private int f4406v;
    private int f4407w;
    private C1557b f4408x;
    private int f4409y = 2;
    private boolean f4410z;

    class C15541 extends SimpleOnGestureListener {
        final /* synthetic */ C1561d f4372a;

        C15541(C1561d c1561d) {
            this.f4372a = c1561d;
        }

        public void onLongPress(MotionEvent motionEvent) {
            if (this.f4372a.f4403s != null) {
                this.f4372a.f4403s.onLongClick(this.f4372a.m7178c());
            }
        }
    }

    static /* synthetic */ class C15552 {
        static final /* synthetic */ int[] f4373a = new int[ScaleType.values().length];

        static {
            try {
                f4373a[ScaleType.MATRIX.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4373a[ScaleType.FIT_START.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4373a[ScaleType.FIT_END.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4373a[ScaleType.FIT_CENTER.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f4373a[ScaleType.FIT_XY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    private class C1556a implements Runnable {
        final /* synthetic */ C1561d f4374a;
        private final float f4375b;
        private final float f4376c;
        private final long f4377d = System.currentTimeMillis();
        private final float f4378e;
        private final float f4379f;

        public C1556a(C1561d c1561d, float f, float f2, float f3, float f4) {
            this.f4374a = c1561d;
            this.f4375b = f3;
            this.f4376c = f4;
            this.f4378e = f;
            this.f4379f = f2;
        }

        private float m7135a() {
            return C1561d.f4384a.getInterpolation(Math.min(1.0f, (((float) (System.currentTimeMillis() - this.f4377d)) * 1.0f) / ((float) this.f4374a.f4387b)));
        }

        public void run() {
            View c = this.f4374a.m7178c();
            if (c != null) {
                float a = m7135a();
                float g = (this.f4378e + ((this.f4379f - this.f4378e) * a)) / this.f4374a.m7186g();
                this.f4374a.f4397m.postScale(g, g, this.f4375b, this.f4376c);
                this.f4374a.m7156o();
                if (a < 1.0f) {
                    C1552a.m7130a(c, this);
                }
            }
        }
    }

    private class C1557b implements Runnable {
        final /* synthetic */ C1561d f4380a;
        private final C0000d f4381b;
        private int f4382c;
        private int f4383d;

        public C1557b(C1561d c1561d, Context context) {
            this.f4380a = c1561d;
            this.f4381b = C0000d.m0a(context);
        }

        public void m7136a() {
            this.f4381b.mo2a(true);
        }

        public void m7137a(int i, int i2, int i3, int i4) {
            RectF b = this.f4380a.m7175b();
            if (b != null) {
                int round;
                int i5;
                int round2;
                int i6;
                int round3 = Math.round(-b.left);
                if (((float) i) < b.width()) {
                    round = Math.round(b.width() - ((float) i));
                    i5 = 0;
                } else {
                    round = round3;
                    i5 = round3;
                }
                int round4 = Math.round(-b.top);
                if (((float) i2) < b.height()) {
                    round2 = Math.round(b.height() - ((float) i2));
                    i6 = 0;
                } else {
                    round2 = round4;
                    i6 = round4;
                }
                this.f4382c = round3;
                this.f4383d = round4;
                if (round3 != round || round4 != round2) {
                    this.f4381b.mo1a(round3, round4, i3, i4, i5, round, i6, round2, 0, 0);
                }
            }
        }

        public void run() {
            if (!this.f4381b.mo4b()) {
                View c = this.f4380a.m7178c();
                if (c != null && this.f4381b.mo3a()) {
                    int c2 = this.f4381b.mo5c();
                    int d = this.f4381b.mo6d();
                    this.f4380a.f4397m.postTranslate((float) (this.f4382c - c2), (float) (this.f4383d - d));
                    this.f4380a.m7149b(this.f4380a.m7191l());
                    this.f4382c = c2;
                    this.f4383d = d;
                    C1552a.m7130a(c, this);
                }
            }
        }
    }

    public interface C1558c {
        void m7138a(RectF rectF);
    }

    public interface C1559d {
        void m7139a(View view, float f, float f2);
    }

    public interface C1560e {
        void m7140a(View view, float f, float f2);
    }

    public C1561d(ImageView imageView) {
        this.f4392h = new WeakReference(imageView);
        imageView.setDrawingCacheEnabled(true);
        imageView.setOnTouchListener(this);
        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
        if (viewTreeObserver != null) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        }
        C1561d.m7150b(imageView);
        if (!imageView.isInEditMode()) {
            this.f4394j = C0970f.m5533a(imageView.getContext(), this);
            this.f4393i = new GestureDetector(imageView.getContext(), new C15541(this));
            this.f4393i.setOnDoubleTapListener(new C1553b(this));
            m7177b(true);
        }
    }

    private float m7141a(Matrix matrix, int i) {
        matrix.getValues(this.f4399o);
        return this.f4399o[i];
    }

    private RectF m7142a(Matrix matrix) {
        ImageView c = m7178c();
        if (c != null) {
            Drawable drawable = c.getDrawable();
            if (drawable != null) {
                this.f4398n.set(0.0f, 0.0f, (float) drawable.getIntrinsicWidth(), (float) drawable.getIntrinsicHeight());
                matrix.mapRect(this.f4398n);
                return this.f4398n;
            }
        }
        return null;
    }

    private void m7144a(Drawable drawable) {
        ImageView c = m7178c();
        if (c != null && drawable != null) {
            float c2 = (float) m7152c(c);
            float d = (float) m7154d(c);
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            this.f4395k.reset();
            float f = c2 / ((float) intrinsicWidth);
            float f2 = d / ((float) intrinsicHeight);
            if (this.f4386A != ScaleType.CENTER) {
                if (this.f4386A != ScaleType.CENTER_CROP) {
                    if (this.f4386A != ScaleType.CENTER_INSIDE) {
                        RectF rectF = new RectF(0.0f, 0.0f, (float) intrinsicWidth, (float) intrinsicHeight);
                        RectF rectF2 = new RectF(0.0f, 0.0f, c2, d);
                        switch (C15552.f4373a[this.f4386A.ordinal()]) {
                            case 2:
                                this.f4395k.setRectToRect(rectF, rectF2, ScaleToFit.START);
                                break;
                            case 3:
                                this.f4395k.setRectToRect(rectF, rectF2, ScaleToFit.END);
                                break;
                            case 4:
                                this.f4395k.setRectToRect(rectF, rectF2, ScaleToFit.CENTER);
                                break;
                            case 5:
                                this.f4395k.setRectToRect(rectF, rectF2, ScaleToFit.FILL);
                                break;
                            default:
                                break;
                        }
                    }
                    f = Math.min(1.0f, Math.min(f, f2));
                    this.f4395k.postScale(f, f);
                    this.f4395k.postTranslate((c2 - (((float) intrinsicWidth) * f)) / 2.0f, (d - (((float) intrinsicHeight) * f)) / 2.0f);
                } else {
                    f = Math.max(f, f2);
                    this.f4395k.postScale(f, f);
                    this.f4395k.postTranslate((c2 - (((float) intrinsicWidth) * f)) / 2.0f, (d - (((float) intrinsicHeight) * f)) / 2.0f);
                }
            } else {
                this.f4395k.postTranslate((c2 - ((float) intrinsicWidth)) / 2.0f, (d - ((float) intrinsicHeight)) / 2.0f);
            }
            m7159r();
        }
    }

    private static boolean m7146a(ImageView imageView) {
        return (imageView == null || imageView.getDrawable() == null) ? false : true;
    }

    private static void m7148b(float f, float f2, float f3) {
        if (f >= f2) {
            throw new IllegalArgumentException("MinZoom has to be less than MidZoom");
        } else if (f2 >= f3) {
            throw new IllegalArgumentException("MidZoom has to be less than MaxZoom");
        }
    }

    private void m7149b(Matrix matrix) {
        ImageView c = m7178c();
        if (c != null) {
            m7157p();
            c.setImageMatrix(matrix);
            if (this.f4400p != null) {
                RectF a = m7142a(matrix);
                if (a != null) {
                    this.f4400p.m7138a(a);
                }
            }
        }
    }

    private static void m7150b(ImageView imageView) {
        if (imageView != null && !(imageView instanceof C1551c) && !ScaleType.MATRIX.equals(imageView.getScaleType())) {
            imageView.setScaleType(ScaleType.MATRIX);
        }
    }

    private static boolean m7151b(ScaleType scaleType) {
        if (scaleType == null) {
            return false;
        }
        switch (C15552.f4373a[scaleType.ordinal()]) {
            case 1:
                throw new IllegalArgumentException(scaleType.name() + " is not supported in PhotoView");
            default:
                return true;
        }
    }

    private int m7152c(ImageView imageView) {
        return imageView == null ? 0 : (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
    }

    private int m7154d(ImageView imageView) {
        return imageView == null ? 0 : (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
    }

    private void m7155n() {
        if (this.f4408x != null) {
            this.f4408x.m7136a();
            this.f4408x = null;
        }
    }

    private void m7156o() {
        if (m7158q()) {
            m7149b(m7191l());
        }
    }

    private void m7157p() {
        ImageView c = m7178c();
        if (c != null && !(c instanceof C1551c) && !ScaleType.MATRIX.equals(c.getScaleType())) {
            throw new IllegalStateException("The ImageView's ScaleType has been changed since attaching a PhotoViewAttacher");
        }
    }

    private boolean m7158q() {
        float f = 0.0f;
        ImageView c = m7178c();
        if (c == null) {
            return false;
        }
        RectF a = m7142a(m7191l());
        if (a == null) {
            return false;
        }
        float height = a.height();
        float width = a.width();
        int d = m7154d(c);
        if (height <= ((float) d)) {
            switch (C15552.f4373a[this.f4386A.ordinal()]) {
                case 2:
                    height = -a.top;
                    break;
                case 3:
                    height = (((float) d) - height) - a.top;
                    break;
                default:
                    height = ((((float) d) - height) / 2.0f) - a.top;
                    break;
            }
        }
        height = a.top > 0.0f ? -a.top : a.bottom < ((float) d) ? ((float) d) - a.bottom : 0.0f;
        int c2 = m7152c(c);
        if (width <= ((float) c2)) {
            switch (C15552.f4373a[this.f4386A.ordinal()]) {
                case 2:
                    f = -a.left;
                    break;
                case 3:
                    f = (((float) c2) - width) - a.left;
                    break;
                default:
                    f = ((((float) c2) - width) / 2.0f) - a.left;
                    break;
            }
            this.f4409y = 2;
        } else if (a.left > 0.0f) {
            this.f4409y = 0;
            f = -a.left;
        } else if (a.right < ((float) c2)) {
            f = ((float) c2) - a.right;
            this.f4409y = 1;
        } else {
            this.f4409y = -1;
        }
        this.f4397m.postTranslate(f, height);
        return true;
    }

    private void m7159r() {
        this.f4397m.reset();
        m7149b(m7191l());
        m7158q();
    }

    public void m7160a() {
        if (this.f4392h != null) {
            ImageView imageView = (ImageView) this.f4392h.get();
            if (imageView != null) {
                ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
                    viewTreeObserver.removeGlobalOnLayoutListener(this);
                }
                imageView.setOnTouchListener(null);
                m7155n();
            }
            if (this.f4393i != null) {
                this.f4393i.setOnDoubleTapListener(null);
            }
            this.f4400p = null;
            this.f4401q = null;
            this.f4402r = null;
            this.f4392h = null;
        }
    }

    public void m7161a(float f) {
        this.f4397m.setRotate(f % 360.0f);
        m7156o();
    }

    public void mo1141a(float f, float f2) {
        if (!this.f4394j.mo939a()) {
            ImageView c = m7178c();
            this.f4397m.postTranslate(f, f2);
            m7156o();
            ViewParent parent = c.getParent();
            if (!this.f4391g || this.f4394j.mo939a()) {
                if (parent != null) {
                    parent.requestDisallowInterceptTouchEvent(true);
                }
            } else if ((this.f4409y == 2 || ((this.f4409y == 0 && f >= 1.0f) || (this.f4409y == 1 && f <= -1.0f))) && parent != null) {
                parent.requestDisallowInterceptTouchEvent(false);
            }
        }
    }

    public void mo1142a(float f, float f2, float f3) {
        if (m7186g() < this.f4390f || f < 1.0f) {
            this.f4397m.postScale(f, f, f2, f3);
            m7156o();
        }
    }

    public void mo1143a(float f, float f2, float f3, float f4) {
        ImageView c = m7178c();
        this.f4408x = new C1557b(this, c.getContext());
        this.f4408x.m7137a(m7152c(c), m7154d(c), (int) f3, (int) f4);
        c.post(this.f4408x);
    }

    public void m7165a(float f, float f2, float f3, boolean z) {
        ImageView c = m7178c();
        if (c != null && f >= this.f4388d && f <= this.f4390f) {
            if (z) {
                c.post(new C1556a(this, m7186g(), f, f2, f3));
                return;
            }
            this.f4397m.setScale(f, f, f2, f3);
            m7156o();
        }
    }

    public void m7166a(float f, boolean z) {
        ImageView c = m7178c();
        if (c != null) {
            m7165a(f, (float) (c.getRight() / 2), (float) (c.getBottom() / 2), z);
        }
    }

    public void m7167a(int i) {
        if (i < 0) {
            i = 200;
        }
        this.f4387b = i;
    }

    public void m7168a(OnDoubleTapListener onDoubleTapListener) {
        if (onDoubleTapListener != null) {
            this.f4393i.setOnDoubleTapListener(onDoubleTapListener);
        } else {
            this.f4393i.setOnDoubleTapListener(new C1553b(this));
        }
    }

    public void m7169a(OnLongClickListener onLongClickListener) {
        this.f4403s = onLongClickListener;
    }

    public void m7170a(ScaleType scaleType) {
        if (C1561d.m7151b(scaleType) && scaleType != this.f4386A) {
            this.f4386A = scaleType;
            m7190k();
        }
    }

    public void m7171a(C1558c c1558c) {
        this.f4400p = c1558c;
    }

    public void m7172a(C1559d c1559d) {
        this.f4401q = c1559d;
    }

    public void m7173a(C1560e c1560e) {
        this.f4402r = c1560e;
    }

    public void m7174a(boolean z) {
        this.f4391g = z;
    }

    public RectF m7175b() {
        m7158q();
        return m7142a(m7191l());
    }

    public void m7176b(float f) {
        this.f4397m.postRotate(f % 360.0f);
        m7156o();
    }

    public void m7177b(boolean z) {
        this.f4410z = z;
        m7190k();
    }

    public ImageView m7178c() {
        ImageView imageView = null;
        if (this.f4392h != null) {
            imageView = (ImageView) this.f4392h.get();
        }
        if (imageView == null) {
            m7160a();
            Log.i("PhotoViewAttacher", "ImageView no longer exists. You should not use this PhotoViewAttacher any more.");
        }
        return imageView;
    }

    public void m7179c(float f) {
        C1561d.m7148b(f, this.f4389e, this.f4390f);
        this.f4388d = f;
    }

    public float m7180d() {
        return this.f4388d;
    }

    public void m7181d(float f) {
        C1561d.m7148b(this.f4388d, f, this.f4390f);
        this.f4389e = f;
    }

    public float m7182e() {
        return this.f4389e;
    }

    public void m7183e(float f) {
        C1561d.m7148b(this.f4388d, this.f4389e, f);
        this.f4390f = f;
    }

    public float m7184f() {
        return this.f4390f;
    }

    public void m7185f(float f) {
        m7166a(f, false);
    }

    public float m7186g() {
        return (float) Math.sqrt((double) (((float) Math.pow((double) m7141a(this.f4397m, 0), 2.0d)) + ((float) Math.pow((double) m7141a(this.f4397m, 3), 2.0d))));
    }

    public ScaleType m7187h() {
        return this.f4386A;
    }

    public C1559d m7188i() {
        return this.f4401q;
    }

    public C1560e m7189j() {
        return this.f4402r;
    }

    public void m7190k() {
        ImageView c = m7178c();
        if (c == null) {
            return;
        }
        if (this.f4410z) {
            C1561d.m7150b(c);
            m7144a(c.getDrawable());
            return;
        }
        m7159r();
    }

    public Matrix m7191l() {
        this.f4396l.set(this.f4395k);
        this.f4396l.postConcat(this.f4397m);
        return this.f4396l;
    }

    public Bitmap m7192m() {
        ImageView c = m7178c();
        return c == null ? null : c.getDrawingCache();
    }

    public void onGlobalLayout() {
        ImageView c = m7178c();
        if (c == null) {
            return;
        }
        if (this.f4410z) {
            int top = c.getTop();
            int right = c.getRight();
            int bottom = c.getBottom();
            int left = c.getLeft();
            if (top != this.f4404t || bottom != this.f4406v || left != this.f4407w || right != this.f4405u) {
                m7144a(c.getDrawable());
                this.f4404t = top;
                this.f4405u = right;
                this.f4406v = bottom;
                this.f4407w = left;
                return;
            }
            return;
        }
        m7144a(c.getDrawable());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouch(View r8, MotionEvent r9) {
        /*
        r7 = this;
        r6 = 1;
        r1 = 0;
        r0 = r7.f4410z;
        if (r0 == 0) goto L_0x0071;
    L_0x0006:
        r0 = r8;
        r0 = (android.widget.ImageView) r0;
        r0 = com.customphotoview.C1561d.m7146a(r0);
        if (r0 == 0) goto L_0x0071;
    L_0x000f:
        r0 = r8.getParent();
        r2 = r9.getAction();
        switch(r2) {
            case 0: goto L_0x0036;
            case 1: goto L_0x0048;
            case 2: goto L_0x001a;
            case 3: goto L_0x0048;
            default: goto L_0x001a;
        };
    L_0x001a:
        r0 = r1;
    L_0x001b:
        r1 = r7.f4394j;
        if (r1 == 0) goto L_0x0028;
    L_0x001f:
        r1 = r7.f4394j;
        r1 = r1.mo940c(r9);
        if (r1 == 0) goto L_0x0028;
    L_0x0027:
        r0 = r6;
    L_0x0028:
        r1 = r7.f4393i;
        if (r1 == 0) goto L_0x0035;
    L_0x002c:
        r1 = r7.f4393i;
        r1 = r1.onTouchEvent(r9);
        if (r1 == 0) goto L_0x0035;
    L_0x0034:
        r0 = r6;
    L_0x0035:
        return r0;
    L_0x0036:
        if (r0 == 0) goto L_0x0040;
    L_0x0038:
        r0.requestDisallowInterceptTouchEvent(r6);
    L_0x003b:
        r7.m7155n();
        r0 = r1;
        goto L_0x001b;
    L_0x0040:
        r0 = "PhotoViewAttacher";
        r2 = "onTouch getParent() returned null";
        android.util.Log.i(r0, r2);
        goto L_0x003b;
    L_0x0048:
        r0 = r7.m7186g();
        r2 = r7.f4388d;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 >= 0) goto L_0x001a;
    L_0x0052:
        r5 = r7.m7175b();
        if (r5 == 0) goto L_0x001a;
    L_0x0058:
        r0 = new com.customphotoview.d$a;
        r2 = r7.m7186g();
        r3 = r7.f4388d;
        r4 = r5.centerX();
        r5 = r5.centerY();
        r1 = r7;
        r0.<init>(r1, r2, r3, r4, r5);
        r8.post(r0);
        r0 = r6;
        goto L_0x001b;
    L_0x0071:
        r0 = r1;
        goto L_0x0035;
        */
//        throw new UnsupportedOperationException("Method not decompiled: com.customphotoview.d.onTouch(android.view.View, android.view.MotionEvent):boolean");
        return true;
    }
}
