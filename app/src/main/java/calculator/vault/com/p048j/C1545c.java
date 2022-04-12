package calculator.vault.com.p048j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;

public class C1545c extends InputStream {
    private static final Queue<C1545c> f4357a = C1550h.m7123a(0);
    private InputStream f4358b;
    private IOException f4359c;

    C1545c() {
    }

    public static C1545c m7108a(InputStream inputStream) {
        C1545c c1545c;
        synchronized (f4357a) {
            c1545c = (C1545c) f4357a.poll();
        }
        if (c1545c == null) {
            c1545c = new C1545c();
        }
        c1545c.m7111b(inputStream);
        return c1545c;
    }

    public IOException m7109a() {
        return this.f4359c;
    }

    public int available() {
        try {
            return this.f4358b.available();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void m7110b() {
        this.f4359c = null;
        this.f4358b = null;
        synchronized (f4357a) {
            f4357a.offer(this);
        }
    }

    void m7111b(InputStream inputStream) {
        this.f4358b = inputStream;
    }

    public void close() {
        try {
            this.f4358b.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mark(int i) {
        this.f4358b.mark(i);
    }

    public boolean markSupported() {
        return this.f4358b.markSupported();
    }

    public int read() {
        try {
            return this.f4358b.read();
        } catch (IOException e) {
            this.f4359c = e;
            return -1;
        }
    }

    public int read(byte[] bArr) {
        try {
            return this.f4358b.read(bArr);
        } catch (IOException e) {
            this.f4359c = e;
            return -1;
        }
    }

    public int read(byte[] bArr, int i, int i2) {
        try {
            return this.f4358b.read(bArr, i, i2);
        } catch (IOException e) {
            this.f4359c = e;
            return -1;
        }
    }

    public synchronized void reset() {
        try {
            this.f4358b.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long skip(long j) {
        try {
            return this.f4358b.skip(j);
        } catch (IOException e) {
            this.f4359c = e;
            return 0;
        }
    }
}
