package calculator.vault.com.p026a;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;

public class C0965a implements C0964d {
    protected C0969e f2732a;
    float f2733b;
    float f2734c;
    final float f2735d;
    final float f2736e;
    private VelocityTracker f2737f;
    private boolean f2738g;
    public float b;
    public float c;

    public C0965a(Context context) {
        ViewConfiguration viewConfiguration = ViewConfiguration.get(context);
        this.f2736e = (float) viewConfiguration.getScaledMinimumFlingVelocity();
        this.f2735d = (float) viewConfiguration.getScaledTouchSlop();
    }

    float mo941a(MotionEvent motionEvent) {
        return motionEvent.getX();
    }

    public void mo938a(C0969e c0969e) {
        this.f2732a = c0969e;
    }

    public boolean mo939a() {
        return false;
    }

    float mo942b(MotionEvent motionEvent) {
        return motionEvent.getY();
    }

    public boolean mo940c(MotionEvent motionEvent) {
        boolean z = false;
        float yVelocity;
        switch (motionEvent.getAction()) {
            case 0:
                this.f2737f = VelocityTracker.obtain();
                if (this.f2737f != null) {
                    this.f2737f.addMovement(motionEvent);
                } else {
                    Log.i("CupcakeGestureDetector", "Velocity tracker is null");
                }
                this.f2733b = mo941a(motionEvent);
                this.f2734c = mo942b(motionEvent);
                this.f2738g = false;
                break;
            case 1:
                if (this.f2738g && this.f2737f != null) {
                    this.f2733b = mo941a(motionEvent);
                    this.f2734c = mo942b(motionEvent);
                    this.f2737f.addMovement(motionEvent);
                    this.f2737f.computeCurrentVelocity(1000);
                    float xVelocity = this.f2737f.getXVelocity();
                    yVelocity = this.f2737f.getYVelocity();
                    if (Math.max(Math.abs(xVelocity), Math.abs(yVelocity)) >= this.f2736e) {
                        this.f2732a.mo1143a(this.f2733b, this.f2734c, -xVelocity, -yVelocity);
                    }
                }
                if (this.f2737f != null) {
                    this.f2737f.recycle();
                    this.f2737f = null;
                    break;
                }
                break;
            case 2:
                yVelocity = mo941a(motionEvent);
                float b = mo942b(motionEvent);
                float f = yVelocity - this.f2733b;
                float f2 = b - this.f2734c;
                if (!this.f2738g) {
                    if (Math.sqrt((double) ((f * f) + (f2 * f2))) >= ((double) this.f2735d)) {
                        z = true;
                    }
                    this.f2738g = z;
                }
                if (this.f2738g) {
                    this.f2732a.mo1141a(f, f2);
                    this.f2733b = yVelocity;
                    this.f2734c = b;
                    if (this.f2737f != null) {
                        this.f2737f.addMovement(motionEvent);
                        break;
                    }
                }
                break;
            case 3:
                if (this.f2737f != null) {
                    this.f2737f.recycle();
                    this.f2737f = null;
                    break;
                }
                break;
        }
        return true;
    }
}
