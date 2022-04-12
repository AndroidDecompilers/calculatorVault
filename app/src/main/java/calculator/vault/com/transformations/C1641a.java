package calculator.vault.com.transformations;

import android.view.View;

public class C1641a extends C1640c {
    protected void mo1150b(View view, float f) {
        view.setPivotX(f < 0.0f ? 0.0f : (float) view.getWidth());
        view.setScaleX(f < 0.0f ? 1.0f + f : 1.0f - f);
    }
}
