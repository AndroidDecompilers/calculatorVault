package calculator.vault.com.p068a;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import calculator.vault.com.R;


@SuppressLint({"NewApi"})
public class C1584f extends BaseAdapter {
    int f4480a;
    private Activity f4481b;
    private int[] f4482c;

    public C1584f(Activity activity, int[] iArr, int i) {
        this.f4481b = activity;
        this.f4482c = iArr;
        this.f4480a = i;
    }

    public int getCount() {
        return this.f4482c.length;
    }

    public Object getItem(int i) {
        return Integer.valueOf(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.f4481b.getLayoutInflater().inflate(R.layout.raw_item_color, null);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.iv1);
        if (this.f4482c[i] == this.f4480a) {
            view.findViewById(R.id.ivInside).setVisibility(View.VISIBLE);
        } else {
            view.findViewById(R.id.ivInside).setVisibility(View.GONE);
        }
        GradientDrawable gradientDrawable = (GradientDrawable) imageView.getBackground();
        gradientDrawable.setColor(this.f4482c[i]);
        if (VERSION.SDK_INT >= 16) {
            imageView.setBackground(gradientDrawable);
        } else {
            imageView.setBackgroundDrawable(gradientDrawable);
        }
        return view;
    }
}
