package calculator.vault.com.p001a;

import android.content.Context;
import android.os.Build.VERSION;

public abstract class C0000d {
    public static C0000d m0a(Context context) {
        return VERSION.SDK_INT < 9 ? new C0003c(context) : VERSION.SDK_INT < 14 ? new C0001a(context) : new C0002b(context);
    }

    public abstract void mo1a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10);

    public abstract void mo2a(boolean z);

    public abstract boolean mo3a();

    public abstract boolean mo4b();

    public abstract int mo5c();

    public abstract int mo6d();
}
