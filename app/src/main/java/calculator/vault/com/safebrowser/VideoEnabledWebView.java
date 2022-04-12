package calculator.vault.com.safebrowser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.NestedScrollingChild;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import java.util.Map;

public class VideoEnabledWebView extends WebView implements NestedScrollingChild {
    private int f9485a;
    private final int[] f9486b;
    private final int[] f9487c;
    private int f9488d;
    private NestedScrollingChildHelper f9489e;
    private SafeBrowserChromeClient f9490f;
    private boolean addedJavaScrip;

    public class JavascriptInterface {

        @android.webkit.JavascriptInterface
        public void notifyVideoEnd() {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (f9490f != null) {
                        f9490f.onHideCustomView();
                    }
                }
            });
        }
    }

    public VideoEnabledWebView(Context context) {
        super(context);
        this.f9486b = new int[2];
        this.f9487c = new int[2];
        this.addedJavaScrip = false;
        this.f9489e = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    public VideoEnabledWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f9486b = new int[2];
        this.f9487c = new int[2];
        this.addedJavaScrip = false;
        this.f9489e = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    public VideoEnabledWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f9486b = new int[2];
        this.f9487c = new int[2];
        this.addedJavaScrip = false;
        this.f9489e = new NestedScrollingChildHelper(this);
        setNestedScrollingEnabled(true);
    }

    private void m14452a() {
        if (!this.addedJavaScrip) {
            addJavascriptInterface(new JavascriptInterface(), "_VideoEnabledWebView");
            this.addedJavaScrip = true;
        }
    }

    public boolean dispatchNestedFling(float f, float f2, boolean z) {
        return this.f9489e.dispatchNestedFling(f, f2, z);
    }

    public boolean dispatchNestedPreFling(float f, float f2) {
        return this.f9489e.dispatchNestedPreFling(f, f2);
    }

    public boolean dispatchNestedPreScroll(int i, int i2, int[] iArr, int[] iArr2) {
        return this.f9489e.dispatchNestedPreScroll(i, i2, iArr, iArr2);
    }

    public boolean dispatchNestedScroll(int i, int i2, int i3, int i4, int[] iArr) {
        return this.f9489e.dispatchNestedScroll(i, i2, i3, i4, iArr);
    }

    public boolean hasNestedScrollingParent() {
        return this.f9489e.hasNestedScrollingParent();
    }

    public boolean isNestedScrollingEnabled() {
        return this.f9489e.isNestedScrollingEnabled();
    }

    public void loadData(String str, String str2, String str3) {
        m14452a();
        super.loadData(str, str2, str3);
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        m14452a();
        super.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    public void loadUrl(String str) {
        m14452a();
        super.loadUrl(str);
    }

    public void loadUrl(String str, Map<String, String> map) {
        m14452a();
        super.loadUrl(str, map);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        MotionEvent obtain = MotionEvent.obtain(motionEvent);
        int a = MotionEventCompat.getActionMasked(obtain);
        if (a == 0) {
            this.f9488d = 0;
        }
        int y = (int) obtain.getY();
        obtain.offsetLocation(0.0f, (float) this.f9488d);
        boolean onTouchEvent;
        switch (a) {
            case MotionEvent.ACTION_DOWN:
                onTouchEvent = super.onTouchEvent(obtain);
                this.f9485a = y;
                startNestedScroll(2);
                return onTouchEvent;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                onTouchEvent = super.onTouchEvent(obtain);
                stopNestedScroll();
                return onTouchEvent;
            case MotionEvent.ACTION_MOVE:
                int i = this.f9485a - y;
                if (dispatchNestedPreScroll(0, i, this.f9487c, this.f9486b)) {
                    i -= this.f9487c[1];
                    this.f9485a = y - this.f9486b[1];
                    obtain.offsetLocation(0.0f, (float) (-this.f9486b[1]));
                    this.f9488d += this.f9486b[1];
                }
                boolean onTouchEvent2 = super.onTouchEvent(obtain);
                if (!dispatchNestedScroll(0, this.f9486b[1], 0, i, this.f9486b)) {
                    return onTouchEvent2;
                }
                obtain.offsetLocation(0.0f, (float) this.f9486b[1]);
                this.f9488d += this.f9486b[1];
                this.f9485a -= this.f9486b[1];
                return onTouchEvent2;
            default:
                return false;
        }
    }

    public void setNestedScrollingEnabled(boolean z) {
        this.f9489e.setNestedScrollingEnabled(z);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void setWebChromeClient(WebChromeClient webChromeClient) {
        getSettings().setJavaScriptEnabled(true);
        if (webChromeClient instanceof SafeBrowserChromeClient) {
            this.f9490f = (SafeBrowserChromeClient) webChromeClient;
        }
        super.setWebChromeClient(webChromeClient);
    }

    public boolean startNestedScroll(int i) {
        return this.f9489e.startNestedScroll(i);
    }

    public void stopNestedScroll() {
        this.f9489e.stopNestedScroll();
    }
}
