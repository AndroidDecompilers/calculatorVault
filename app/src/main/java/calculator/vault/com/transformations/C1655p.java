package calculator.vault.com.transformations;

import android.view.View;

public class C1655p extends C1640c {
    protected void mo1150b(View view, float f) {
        if (f >= -1.0f || f <= 1.0f) {
            float height = (float) view.getHeight();
            float max = Math.max(0.85f, 1.0f - Math.abs(f));
            float f2 = ((1.0f - max) * height) / 2.0f;
            float width = (((float) view.getWidth()) * (1.0f - max)) / 2.0f;
            view.setPivotY(height * 0.5f);
            if (f < 0.0f) {
                view.setTranslationX(width - (f2 / 2.0f));
            } else {
                view.setTranslationX((-width) + (f2 / 2.0f));
            }
            view.setScaleX(max);
            view.setScaleY(max);
            view.setAlpha((((max - 0.85f) / 0.14999998f) * 0.5f) + 0.5f);
        }
    }
}
