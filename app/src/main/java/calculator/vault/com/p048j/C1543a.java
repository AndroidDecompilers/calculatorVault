package calculator.vault.com.p048j;

import android.util.Log;
import java.util.Queue;

public final class C1543a {
    private static final C1543a f4353b = new C1543a();
    private final Queue<byte[]> f4354a = C1550h.m7123a(0);

    private C1543a() {
    }

    public static C1543a m7103a() {
        return f4353b;
    }

    public boolean m7104a(byte[] bArr) {
        boolean z = false;
        if (bArr.length == 65536) {
            synchronized (this.f4354a) {
                if (this.f4354a.size() < 32) {
                    z = true;
                    this.f4354a.offer(bArr);
                }
            }
        }
        return z;
    }

    public byte[] m7105b() {
        byte[] bArr;
        synchronized (this.f4354a) {
            bArr = (byte[]) this.f4354a.poll();
        }
        if (bArr == null) {
            bArr = new byte[65536];
            if (Log.isLoggable("ByteArrayPool", 3)) {
                Log.d("ByteArrayPool", "Created temp bytes");
            }
        }
        return bArr;
    }
}
