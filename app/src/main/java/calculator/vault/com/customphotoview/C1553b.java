package calculator.vault.com.customphotoview;

import android.graphics.RectF;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.MotionEvent;
import android.view.View;

import calculator.vault.com.lock.ViewImageActivity;

public class C1553b implements OnDoubleTapListener {
    private C1561d f4371a;

    public C1553b(C1561d c1561d) {
        m7134a(c1561d);
    }

    public void m7134a(C1561d c1561d) {
        this.f4371a = c1561d;
    }

    public boolean onDoubleTap(MotionEvent motionEvent) {
        if (this.f4371a == null) {
            return false;
        }
        try {
            float g = this.f4371a.m7186g();
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            if (g < this.f4371a.m7182e()) {
                this.f4371a.m7165a(this.f4371a.m7182e(), x, y, true);
                return true;
            } else if (g < this.f4371a.m7182e() || g >= this.f4371a.m7184f()) {
                this.f4371a.m7165a(this.f4371a.m7180d(), x, y, true);
                return true;
            } else {
                this.f4371a.m7165a(this.f4371a.m7184f(), x, y, true);
                return true;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return true;
        }
    }

    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        if (this.f4371a == null) {
            return false;
        }
        View c = this.f4371a.m7178c();
        if (ViewImageActivity.f3235s != null) {
            ViewImageActivity.f3235s.m5795a();
        }
        if (this.f4371a.m7188i() != null) {
            RectF b = this.f4371a.m7175b();
            if (b != null) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                if (b.contains(x, y)) {
                    this.f4371a.m7188i().m7139a(c, (x - b.left) / b.width(), (y - b.top) / b.height());
                    return true;
                }
            }
        }
        if (this.f4371a.m7189j() == null) {
            return false;
        }
        this.f4371a.m7189j().m7140a(c, motionEvent.getX(), motionEvent.getY());
        return false;
    }
}
