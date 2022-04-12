package calculator.vault.com.customphotoview;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.view.View;

public class C1552a {
    public static int m7129a(int i) {
        return VERSION.SDK_INT >= 11 ? C1552a.m7133c(i) : C1552a.m7131b(i);
    }

    public static void m7130a(View view, Runnable runnable) {
        if (VERSION.SDK_INT >= 16) {
            C1552a.m7132b(view, runnable);
        } else {
            view.postDelayed(runnable, 16);
        }
    }

    @TargetApi(5)
    private static int m7131b(int i) {
        return (65280 & i) >> 8;
    }

    @TargetApi(16)
    private static void m7132b(View view, Runnable runnable) {
        view.postOnAnimation(runnable);
    }

    @TargetApi(11)
    private static int m7133c(int i) {
        return (65280 & i) >> 8;
    }
}
