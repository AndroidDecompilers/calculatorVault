package calculator.vault.com.p001a;

import android.content.Context;
import android.widget.Scroller;

public class C0003c extends C0000d {
    private final Scroller f2a;

    public C0003c(Context context) {
        this.f2a = new Scroller(context);
    }

    public void mo1a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.f2a.fling(i, i2, i3, i4, i5, i6, i7, i8);
    }

    public void mo2a(boolean z) {
        this.f2a.forceFinished(z);
    }

    public boolean mo3a() {
        return this.f2a.computeScrollOffset();
    }

    public boolean mo4b() {
        return this.f2a.isFinished();
    }

    public int mo5c() {
        return this.f2a.getCurrX();
    }

    public int mo6d() {
        return this.f2a.getCurrY();
    }
}
