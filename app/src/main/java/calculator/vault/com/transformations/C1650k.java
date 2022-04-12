package calculator.vault.com.transformations;

import android.view.View;

public class C1650k extends C1640c {
    protected void mo1150b(View view, float f) {
        float height = (float) view.getHeight();
        float f2 = (-15.0f * f) * -1.25f;
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(height);
        view.setRotation(f2);
    }

    protected boolean mo1151b() {
        return true;
    }
}
