package calculator.vault.com.p048j;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class C1299e<T, Y> {
    private final LinkedHashMap<T, Y> f3882a = new LinkedHashMap(100, 0.75f, true);
    private int f3883b;
    private final int f3884c;
    private int f3885d = 0;

    public C1299e(int i) {
        this.f3884c = i;
        this.f3883b = i;
    }

    private void m6408c() {
        m6415b(this.f3883b);
    }

    protected int mo1055a(Y y) {
        return 1;
    }

    public void m6410a() {
        m6415b(0);
    }

    protected void mo1059a(Object t, Object y) {
    }

    public int m6412b() {
        return this.f3885d;
    }

    public Y m6413b(T t) {
        return this.f3882a.get(t);
    }

    public Y m6414b(T t, Y y) {
        if (mo1055a(y) >= this.f3883b) {
            mo1059a(t, y);
            return null;
        }
        Y put = this.f3882a.put(t, y);
        if (y != null) {
            this.f3885d += mo1055a(y);
        }
        if (put != null) {
            this.f3885d -= mo1055a(put);
        }
        m6408c();
        return put;
    }

    protected void m6415b(int i) {
        while (this.f3885d > i) {
            Entry entry = (Entry) this.f3882a.entrySet().iterator().next();
            Object value = entry.getValue();
            this.f3885d -= mo1055a((Y) value);
            Object key = entry.getKey();
            this.f3882a.remove(key);
            mo1059a(key, value);
        }
    }

    public Y m6416c(T t) {
        Y remove = this.f3882a.remove(t);
        if (remove != null) {
            this.f3885d -= mo1055a(remove);
        }
        return remove;
    }
}
