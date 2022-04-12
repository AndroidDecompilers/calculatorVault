package calculator.vault.com.p048j;

public class C1548g {
    private Class<?> f4362a;
    private Class<?> f4363b;

    public C1548g(Class<?> cls, Class<?> cls2) {
        m7116a(cls, cls2);
    }

    public void m7116a(Class<?> cls, Class<?> cls2) {
        this.f4362a = cls;
        this.f4363b = cls2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        C1548g c1548g = (C1548g) obj;
        return !this.f4362a.equals(c1548g.f4362a) ? false : this.f4363b.equals(c1548g.f4363b);
    }

    public int hashCode() {
        return (this.f4362a.hashCode() * 31) + this.f4363b.hashCode();
    }

    public String toString() {
        return "MultiClassKey{first=" + this.f4362a + ", second=" + this.f4363b + '}';
    }
}
