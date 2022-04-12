package calculator.vault.com.transformations;

import android.view.View;

public class C1656q extends C1640c {
    protected void mo1150b(View view, float f) {
        float abs = Math.abs(f) + 1.0f;
        view.setScaleX(abs);
        view.setScaleY(abs);
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(((float) view.getHeight()) * 0.5f);
        abs = (f < -1.0f || f > 1.0f) ? 0.0f : 1.0f - (abs - 1.0f);
        view.setAlpha(abs);
        if (f == -1.0f) {
            view.setTranslationX((float) (view.getWidth() * -1));
        }
    }
}
