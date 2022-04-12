package calculator.vault.com.transformations;

import android.support.v4.view.ViewPager;
import android.view.View;

public class C1646g implements ViewPager.PageTransformer {
    public void transformPage(View view, float f) {
        float f2 = 0.25f;
        int width = view.getWidth();
        if (f < -1.0f || f > 1.0f) {
            view.setAlpha(0.0f);
        } else if (f <= 0.0f) {
            view.setAlpha(1.0f + f);
            view.setTranslationX(((float) width) * (-f));
            f2 = (0.25f * (1.0f - Math.abs(f))) + 0.75f;
            view.setScaleX(f2);
            view.setScaleY(f2);
        } else if (((double) f) > 0.5d && f <= 1.0f) {
            view.setAlpha(0.0f);
            view.setTranslationX(((float) width) * (-f));
        } else if (((double) f) > 0.3d && ((double) f) <= 0.5d) {
            view.setAlpha(1.0f);
            view.setTranslationX(((float) width) * f);
            view.setScaleX(0.75f);
            view.setScaleY(0.75f);
        } else if (((double) f) <= 0.3d) {
            view.setAlpha(1.0f);
            view.setTranslationX(((float) width) * f);
            float f3 = (float) (0.3d - ((double) f));
            if (f3 < 0.25f) {
                f2 = f3;
            }
            f2 += 0.75f;
            view.setScaleX(f2);
            view.setScaleY(f2);
        }
    }
}
