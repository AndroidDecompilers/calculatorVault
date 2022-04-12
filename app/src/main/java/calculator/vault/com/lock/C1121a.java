package calculator.vault.com.lock;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;

import calculator.vault.com.R;

public class C1121a implements OnTouchListener {
    Context f3294a;

    public C1121a(Context context) {
        this.f3294a = context;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            view.startAnimation(AnimationUtils.loadAnimation(this.f3294a, R.anim.scale_btn));
        }
        return false;
    }
}
