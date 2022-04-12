package calculator.vault.com.transformations;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.View;

public class C1653n extends C1640c {
    private static final Matrix f4677a = new Matrix();
    private static final Camera f4678b = new Camera();
    private static final float[] f4679c = new float[2];

    protected static final float m7323a(float f, int i, int i2) {
        f4677a.reset();
        f4678b.save();
        f4678b.rotateY(Math.abs(f));
        f4678b.getMatrix(f4677a);
        f4678b.restore();
        f4677a.preTranslate(((float) (-i)) * 0.5f, ((float) (-i2)) * 0.5f);
        f4677a.postTranslate(((float) i) * 0.5f, ((float) i2) * 0.5f);
        f4679c[0] = (float) i;
        f4679c[1] = (float) i2;
        f4677a.mapPoints(f4679c);
        return (f > 0.0f ? 1.0f : -1.0f) * (((float) i) - f4679c[0]);
    }

    protected void mo1150b(View view, float f) {
        float abs = (f < 0.0f ? 30.0f : -30.0f) * Math.abs(f);
        view.setTranslationX(C1653n.m7323a(abs, view.getWidth(), view.getHeight()));
        view.setPivotX(((float) view.getWidth()) * 0.5f);
        view.setPivotY(0.0f);
        view.setRotationY(abs);
    }
}
