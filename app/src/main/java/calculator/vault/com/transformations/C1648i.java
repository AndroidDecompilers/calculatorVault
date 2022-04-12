package calculator.vault.com.transformations;

import android.view.View;

public class C1648i extends C1640c {
    protected void mo1150b(View view, float f) {
        float f2 = -180.0f * f;
        float f3 = (f2 > 90.0f || f2 < -90.0f) ? 0.0f : 1.0f;
        view.setAlpha(f3);
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(((float) view.getHeight()) * 0.5f);
        view.setRotationX(f2);
    }
}
