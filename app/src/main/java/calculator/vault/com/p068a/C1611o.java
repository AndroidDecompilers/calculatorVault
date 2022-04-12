package calculator.vault.com.p068a;

import calculator.vault.com.lock.C1131f;

public class C1611o implements Comparable<C1611o> {
    public String f4585a;
    public boolean f4586b;
    public long f4587c;

    public C1611o(String str) {
        this.f4585a = str;
    }

    public int m7254a(C1611o c1611o) {
        return c1611o.f4587c != this.f4587c ? C1131f.f3324j ? c1611o.f4587c < this.f4587c ? 1 : -1 : c1611o.f4587c <= this.f4587c ? -1 : 1 : 0;
    }

    public void m7255a(boolean z) {
        this.f4586b = z;
    }

    public /* synthetic */ int compareTo(C1611o obj) {
        return m7254a((C1611o) obj);
    }
}
