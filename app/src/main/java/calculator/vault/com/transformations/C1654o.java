package calculator.vault.com.transformations;

import android.view.View;

public class C1654o extends C1640c {
    protected void mo1150b(View view, float f) {
        float abs = f < 0.0f ? f + 1.0f : Math.abs(1.0f - f);
        view.setScaleX(abs);
        view.setScaleY(abs);
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(((float) view.getHeight()) * 0.5f);
        abs = (f < -1.0f || f > 1.0f) ? 0.0f : 1.0f - (abs - 1.0f);
        view.setAlpha(abs);
    }
}
