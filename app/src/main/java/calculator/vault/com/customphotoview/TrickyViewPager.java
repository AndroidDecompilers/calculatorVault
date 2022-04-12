package calculator.vault.com.customphotoview;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TrickyViewPager extends ViewPager {
    private boolean f4370a = false;

    public TrickyViewPager(Context context) {
        super(context);
    }

    public TrickyViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        boolean z = false;
        if (!this.f4370a) {
            try {
                z = super.onInterceptTouchEvent(motionEvent);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return z;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return !this.f4370a ? super.onTouchEvent(motionEvent) : false;
    }

    public void setLocked(boolean z) {
        this.f4370a = z;
    }
}
