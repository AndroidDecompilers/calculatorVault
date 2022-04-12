package calculator.vault.com.p048j;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.os.SystemClock;

public final class C1546d {
    private static final double f4360a;

    static {
        double d = 1.0d;
        if (17 <= VERSION.SDK_INT) {
            d = 1.0d / Math.pow(10.0d, 6.0d);
        }
        f4360a = d;
    }

    public static double m7112a(long j) {
        return ((double) (C1546d.m7113a() - j)) * f4360a;
    }

    @TargetApi(17)
    public static long m7113a() {
        return 17 <= VERSION.SDK_INT ? SystemClock.elapsedRealtimeNanos() : System.currentTimeMillis();
    }
}
