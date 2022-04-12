package calculator.vault.com.safebrowser;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class AutoHideBottom extends CoordinatorLayout.Behavior<LinearLayout> {
    private int f9302a = -1;

    public AutoHideBottom(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean m14394a(CoordinatorLayout coordinatorLayout, LinearLayout linearLayout, View view) {
        return view instanceof AppBarLayout;
    }

    public boolean layoutDependsOn(CoordinatorLayout coordinatorLayout, LinearLayout view, View view2) {
        return m14394a(coordinatorLayout, (LinearLayout) view, view2);
    }

    public boolean m14396b(CoordinatorLayout coordinatorLayout, LinearLayout linearLayout, View view) {
        if (this.f9302a == -1) {
            this.f9302a = view.getTop();
        }
        linearLayout.setTranslationY((float) ((-view.getTop()) + this.f9302a));
        return true;
    }

    public /* synthetic */ boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, LinearLayout view, View view2) {
        return m14396b(coordinatorLayout, (LinearLayout) view, view2);
    }
}
