package calculator.vault.com.transformations;

import android.view.View;

public class C1651l extends C1640c {
    protected void mo1150b(View view, float f) {
        float f2 = -15.0f * f;
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(0.0f);
        view.setTranslationX(0.0f);
        view.setRotation(f2);
    }

    protected boolean mo1151b() {
        return true;
    }
}
