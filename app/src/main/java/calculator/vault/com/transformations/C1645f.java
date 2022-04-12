package calculator.vault.com.transformations;

import android.view.View;

public class C1645f extends C1640c {
    protected void mo1150b(View view, float f) {
        if (f <= 0.0f) {
            view.setTranslationX(0.0f);
            view.setScaleX(1.0f);
            view.setScaleY(1.0f);
        } else if (f <= 1.0f) {
            float abs = 0.75f + (0.25f * (1.0f - Math.abs(f)));
            view.setAlpha(1.0f - f);
            view.setPivotY(0.5f * ((float) view.getHeight()));
            view.setTranslationX(((float) view.getWidth()) * (-f));
            view.setScaleX(abs);
            view.setScaleY(abs);
        }
    }

    protected boolean mo1151b() {
        return true;
    }
}
