package calculator.vault.com.p048j;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class C1544b extends FilterInputStream {
    private final long f4355a;
    private int f4356b;

    C1544b(InputStream inputStream, long j) {
        super(inputStream);
        this.f4355a = j;
    }

    private int m7106a(int i) throws IOException {
        if (i >= 0) {
            this.f4356b += i;
        } else if (this.f4355a - ((long) this.f4356b) > 0) {
            throw new IOException("Failed to read all expected data, expected: " + this.f4355a + ", but read: " + this.f4356b);
        }
        return i;
    }

    public static InputStream m7107a(InputStream inputStream, long j) {
        return new C1544b(inputStream, j);
    }

    public synchronized int available() throws IOException {
        return (int) Math.max(this.f4355a - ((long) this.f4356b), (long) this.in.available());
    }

    public synchronized int read() throws IOException {
        return m7106a(super.read());
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        return m7106a(super.read(bArr, i, i2));
    }
}
