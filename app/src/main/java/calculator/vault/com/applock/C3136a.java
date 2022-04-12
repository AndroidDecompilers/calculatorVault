package calculator.vault.com.applock;

import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

public class C3136a implements Comparable<C3136a> {
    public String f9925a;
    public String f9926b;
    public boolean f9927c;
    public String f9928d;
    public String f9929e;
    public String f9930f;
    public int f9931g;
    public boolean f9932h = true;
    private PackageItemInfo f9933i;
    private Drawable f9934j;

    public C3136a(String str, int i) {
        this.f9925a = str;
        this.f9933i = null;
        this.f9926b = "";
        this.f9931g = i;
    }

    public C3136a(String str, PackageItemInfo packageItemInfo, int i) {
        this.f9925a = str;
        this.f9933i = packageItemInfo;
        this.f9926b = packageItemInfo.packageName;
        this.f9931g = i;
    }

    public C3136a(boolean z, String str, String str2, String str3, String str4) {
        this.f9925a = str;
        this.f9927c = z;
        this.f9928d = str3;
        this.f9929e = str4;
        this.f9930f = str2;
    }

    public int m14820a(C3136a c3136a) {
        return this.f9931g != c3136a.f9931g ? c3136a.f9931g - this.f9931g : this.f9932h != c3136a.f9932h ? this.f9932h ? -1 : 1 : (this.f9925a == null || c3136a.f9925a == null) ? 0 : this.f9925a.compareTo(c3136a.f9925a);
    }

    public Drawable m14821a(PackageManager packageManager) {
        if (this.f9934j == null) {
            if (this.f9933i == null) {
                return null;
            }
            this.f9934j = this.f9933i.loadIcon(packageManager);
        }
        return this.f9934j;
    }

    public boolean m14822a() {
        return this.f9926b != null && this.f9926b.length() > 0;
    }

    public String m14823b(PackageManager packageManager) {
        if (this.f9925a == null) {
            this.f9925a = (String) this.f9933i.loadLabel(packageManager);
        }
        return this.f9925a;
    }

    public /* synthetic */ int compareTo(C3136a obj) {
        return m14820a((obj));
    }

    public final boolean equals(Object obj) {
        boolean z = true;
        if (obj == null || !(obj instanceof C3136a)) {
            return false;
        }
        C3136a c3136a = (C3136a) obj;
        if (m14822a() != c3136a.m14822a()) {
            return false;
        }
        if (m14822a()) {
            if (this.f9926b == null || !this.f9926b.equals(c3136a.f9926b)) {
                z = false;
            }
            return z;
        }
        if (this.f9925a == null || !this.f9925a.equals(c3136a.f9925a)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return m14822a() ? ("bypkgname" + this.f9926b).hashCode() : ("bytitle" + this.f9925a).hashCode();
    }
}
