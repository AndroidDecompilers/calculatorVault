package calculator.vault.com.transformations;

import android.view.View;

public class C1652m extends C1640c {
    protected void mo1150b(View view, float f) {
        float f2 = 0.0f;
        if (f >= 0.0f) {
            f2 = ((float) (-view.getWidth())) * f;
        }
        view.setTranslationX(f2);
    }
}
