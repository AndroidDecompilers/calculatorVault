package calculator.vault.com.p048j;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class C1547f extends FilterInputStream {
    private int f4361a = Integer.MIN_VALUE;

    public C1547f(InputStream inputStream) {
        super(inputStream);
    }

    private long m7114a(long j) {
        return this.f4361a == 0 ? -1 : (this.f4361a == Integer.MIN_VALUE || j <= ((long) this.f4361a)) ? j : (long) this.f4361a;
    }

    private void m7115b(long j) {
        if (this.f4361a != Integer.MIN_VALUE && j != -1) {
            this.f4361a = (int) (((long) this.f4361a) - j);
        }
    }

    public int available() {
        try {
            return this.f4361a == Integer.MIN_VALUE ? super.available() : Math.min(this.f4361a, super.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void mark(int i) {
        super.mark(i);
        this.f4361a = i;
    }

    public int read() throws IOException {
        if (m7114a(1) == -1) {
            return -1;
        }
        int read = super.read();
        m7115b(1);
        return read;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int a = (int) m7114a((long) i2);
        if (a == -1) {
            return -1;
        }
        int read = super.read(bArr, i, a);
        m7115b((long) read);
        return read;
    }

    public void reset() throws IOException {
        super.reset();
        this.f4361a = Integer.MIN_VALUE;
    }

    public long skip(long j) throws IOException {
        long a = m7114a(j);
        if (a == -1) {
            return -1;
        }
        long skip = super.skip(a);
        m7115b(skip);
        return skip;
    }
}
