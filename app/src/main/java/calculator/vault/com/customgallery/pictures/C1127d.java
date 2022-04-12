package calculator.vault.com.customgallery.pictures;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Kundan Singh on 01-04-2018.
 */

class C1127d extends RecyclerView.ItemDecoration {

    private int f3307a;
    private int f3308b;
    private int f3309c;
    private int f3310d;

    public C1127d(int i, int i2, int i3, int i4) {
        this.f3307a = i;
        this.f3308b = i2;
        this.f3309c = i3;
        this.f3310d = i4;
    }

    public void mo827a(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State c0820r) {
        rect.left = this.f3307a;
        rect.right = this.f3308b;
        rect.bottom = this.f3310d;
        rect.top = this.f3309c;
    }
}
