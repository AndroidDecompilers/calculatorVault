package calculator.vault.com.transformations;

import android.view.View;

public class C1643d extends C1640c {
    protected void mo1150b(View view, float f) {
        float f2 = 0.0f;
        if (f < 0.0f) {
            f2 = (float) view.getWidth();
        }
        view.setPivotX(f2);
        view.setPivotY(((float) view.getHeight()) * 0.5f);
        view.setRotationY(90.0f * f);
    }

    public boolean mo1151b() {
        return true;
    }
}
