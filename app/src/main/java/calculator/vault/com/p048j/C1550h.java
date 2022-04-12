package calculator.vault.com.p048j;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Build.VERSION;
import android.os.Looper;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

public final class C1550h {
    private static final char[] f4365a = "0123456789abcdef".toCharArray();
    private static final char[] f4366b = new char[64];
    private static final char[] f4367c = new char[40];

    static /* synthetic */ class C15491 {
        static final /* synthetic */ int[] f4364a = new int[Config.values().length];

        static {
            try {
                f4364a[Config.ALPHA_8.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f4364a[Config.RGB_565.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f4364a[Config.ARGB_4444.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f4364a[Config.ARGB_8888.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public static int m7117a(int i, int i2, Config config) {
        return (i * i2) * C1550h.m7118a(config);
    }

    private static int m7118a(Config config) {
        if (config == null) {
            config = Config.ARGB_8888;
        }
        switch (C15491.f4364a[config.ordinal()]) {
            case 1:
                return 1;
            case 2:
            case 3:
                return 2;
            default:
                return 4;
        }
    }

    @TargetApi(19)
    public static int m7119a(Bitmap bitmap) {
        if (VERSION.SDK_INT >= 19) {
            try {
                return bitmap.getAllocationByteCount();
            } catch (NullPointerException e) {
            }
        }
        return bitmap.getHeight() * bitmap.getRowBytes();
    }

    public static String m7120a(byte[] bArr) {
        String a;
        synchronized (f4366b) {
            a = C1550h.m7121a(bArr, f4366b);
        }
        return a;
    }

    private static String m7121a(byte[] bArr, char[] cArr) {
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            cArr[i * 2] = f4365a[i2 >>> 4];
            cArr[(i * 2) + 1] = f4365a[i2 & 15];
        }
        return new String(cArr);
    }

    public static <T> List<T> m7122a(Collection<T> collection) {
        List<T> arrayList = new ArrayList(collection.size());
        for (T add : collection) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public static <T> Queue<T> m7123a(int i) {
        return new ArrayDeque(i);
    }

    public static void m7124a() {
        if (!C1550h.m7126b()) {
            throw new IllegalArgumentException("You must call this method on the main thread");
        }
    }

    public static boolean m7125a(int i, int i2) {
        return C1550h.m7127b(i) && C1550h.m7127b(i2);
    }

    public static boolean m7126b() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private static boolean m7127b(int i) {
        return i > 0 || i == Integer.MIN_VALUE;
    }

    public static boolean m7128c() {
        return !C1550h.m7126b();
    }
}
