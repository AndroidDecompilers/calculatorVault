package calculator.vault.com.p026a;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;

@TargetApi(8)
public class C0968c extends C0966b {
    protected final ScaleGestureDetector f2742f;
    private C0969e a;

    class C09671 implements OnScaleGestureListener {
        final /* synthetic */ C0968c f2741a;

        C09671(C0968c c0968c) {
            this.f2741a = c0968c;
        }

        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            float scaleFactor = scaleGestureDetector.getScaleFactor();
            if (Float.isNaN(scaleFactor) || Float.isInfinite(scaleFactor)) {
                return false;
            }
            a.mo1142a(scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            return true;
        }

        public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
            return true;
        }

        public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
        }
    }

    public C0968c(Context context) {
        super(context);
        this.f2742f = new ScaleGestureDetector(context, new C09671(this));
    }

    public boolean mo939a() {
        return this.f2742f.isInProgress();
    }

    public boolean mo940c(MotionEvent motionEvent) {
        this.f2742f.onTouchEvent(motionEvent);
        return super.mo940c(motionEvent);
    }
}
