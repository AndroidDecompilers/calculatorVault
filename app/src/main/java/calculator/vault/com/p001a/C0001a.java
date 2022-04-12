package calculator.vault.com.p001a;

import android.annotation.TargetApi;
import android.content.Context;
import android.widget.OverScroller;

@TargetApi(9)
public class C0001a extends C0000d {
    protected final OverScroller f0a;
    private boolean f1b = false;
    public OverScroller a;

    public C0001a(Context context) {
        this.f0a = new OverScroller(context);
    }

    public void mo1a(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        this.f0a.fling(i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
    }

    public void mo2a(boolean z) {
        this.f0a.forceFinished(z);
    }

    public boolean mo3a() {
        if (this.f1b) {
            this.f0a.computeScrollOffset();
            this.f1b = false;
        }
        return this.f0a.computeScrollOffset();
    }

    public boolean mo4b() {
        return this.f0a.isFinished();
    }

    public int mo5c() {
        return this.f0a.getCurrX();
    }

    public int mo6d() {
        return this.f0a.getCurrY();
    }
}
