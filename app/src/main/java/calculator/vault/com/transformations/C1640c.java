package calculator.vault.com.transformations;

import android.support.v4.view.ViewPager;
import android.view.View;

public abstract class C1640c implements ViewPager.PageTransformer {

    public void transformPage(View view, float f) {
        m7302c(view, f);
        mo1150b(view, f);
        m7303d(view, f);
    }

    protected boolean m7299a() {
        return true;
    }

    protected abstract void mo1150b(View view, float f);

    protected boolean mo1151b() {
        return false;
    }

    protected void m7302c(View view, float f) {
        float f2 = 0.0f;
        float width = (float) view.getWidth();
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setRotation(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
        view.setPivotX(0.0f);
        view.setPivotY(0.0f);
        view.setTranslationY(0.0f);
        view.setTranslationX(mo1151b() ? 0.0f : (-width) * f);
        if (m7299a()) {
            if (f > -1.0f && f < 1.0f) {
                f2 = 1.0f;
            }
            view.setAlpha(f2);
            return;
        }
        view.setAlpha(1.0f);
    }

    protected void m7303d(View view, float f) {
    }
}
