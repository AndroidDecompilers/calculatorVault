package calculator.vault.com.p026a;

import android.annotation.TargetApi;
import android.content.Context;
import android.view.MotionEvent;

import calculator.vault.com.customphotoview.C1552a;

@TargetApi(5)
public class C0966b extends C0965a {
    private int f2739f = -1;
    private int f2740g = 0;

    public C0966b(Context context) {
        super(context);
    }

    float mo941a(MotionEvent motionEvent) {
        try {
            return motionEvent.getX(this.f2740g);
        } catch (Exception e) {
            return motionEvent.getX();
        }
    }

    float mo942b(MotionEvent motionEvent) {
        try {
            return motionEvent.getY(this.f2740g);
        } catch (Exception e) {
            return motionEvent.getY();
        }
    }

    public boolean mo940c(MotionEvent motionEvent) {
        int i = 0;
        switch (motionEvent.getAction() & 255) {
            case 0:
                this.f2739f = motionEvent.getPointerId(0);
                break;
            case 1:
            case 3:
                this.f2739f = -1;
                break;
            case 6:
                int a = C1552a.m7129a(motionEvent.getAction());
                if (motionEvent.getPointerId(a) == this.f2739f) {
                    a = a == 0 ? 1 : 0;
                    this.f2739f = motionEvent.getPointerId(a);
                    this.b = motionEvent.getX(a);
                    this.c = motionEvent.getY(a);
                    break;
                }
                break;
        }
        if (this.f2739f != -1) {
            i = this.f2739f;
        }
        this.f2740g = motionEvent.findPointerIndex(i);
        return super.mo940c(motionEvent);
    }
}
