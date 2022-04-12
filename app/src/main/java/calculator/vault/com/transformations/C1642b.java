package calculator.vault.com.transformations;

import android.view.View;

public class C1642b extends C1640c {
    private static final float m7305a(float f, float f2) {
        return f < f2 ? f2 : f;
    }

    protected void mo1150b(View view, float f) {
        float f2 = 1.0f;
        float height = (float) view.getHeight();
        float width = (float) view.getWidth();
        if (f >= 0.0f) {
            f2 = Math.abs(1.0f - f);
        }
        f2 = C1642b.m7305a(f2, 0.5f);
        view.setScaleX(f2);
        view.setScaleY(f2);
        view.setPivotX(width * 0.5f);
        view.setPivotY(height * 0.5f);
        view.setTranslationX(f < 0.0f ? width * f : ((-width) * f) * 0.25f);
    }
}
