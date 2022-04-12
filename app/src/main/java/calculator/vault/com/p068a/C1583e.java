package calculator.vault.com.p068a;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

import calculator.vault.com.R;
import calculator.vault.com.applock.SecurityHelpers;

public class C1583e extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context f4475a;
    int f4476b;
    C1578a f4477c;
    C1579b f4478d;
    private ArrayList<C1577d> f4479e;

    public interface C1578a {
        void mo2107a(C1577d c1577d);
    }

    public interface C1579b {
        void mo2108a(C1577d c1577d);
    }

    public class C1582c extends RecyclerView.ViewHolder {
        final /* synthetic */ C1583e f4471a;
        private ImageView f4472b;
        private TextView f4473c;
        private FrameLayout f4474d;

        public C1582c(final C1583e c1583e, View view) {
            super(view);
            this.f4471a = c1583e;
            this.f4472b = (ImageView) view.findViewById(R.id.ivStoryImage);
            this.f4474d = (FrameLayout) view.findViewById(R.id.flBg);
            this.f4473c = (TextView) view.findViewById(R.id.tvName);
            view.setOnClickListener(new OnClickListener() {

                public void onClick(View view) {
                    f4471a.f4477c.mo2107a((C1577d) f4471a.f4479e.get(getAdapterPosition()));
                }
            });
            view.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View view) {
                    C1577d c1577d = (C1577d) f4471a.f4479e.get(getAdapterPosition());
                    if (c1577d.f4465d) {
                        return false;
                    }
                    f4471a.f4478d.mo2108a(c1577d);
                    return true;
                }
            });
        }
    }

    public C1583e(Context context, ArrayList<C1577d> arrayList, C1578a c1578a, C1579b c1579b) {
        this.f4479e = arrayList;
        this.f4475a = context;
        this.f4477c = c1578a;
        this.f4478d = c1579b;
        f4476b = ((int) SecurityHelpers.m14847a(60.0f, this.f4475a));
    }

    private boolean m7209a(int i) {
        return i == this.f4479e.size() + -1;
    }

    public void m7210a(ArrayList<C1577d> arrayList) {
        this.f4479e = arrayList;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.f4479e.size();
    }

    public int getItemViewType(int i) {
        return m7209a(i) ? 1 : 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onBindViewHolder(RecyclerView.ViewHolder c0823u, int i) {
        if (c0823u instanceof C1582c) {
            C1582c c1582c = (C1582c) c0823u;
            C1577d c1577d = (C1577d) this.f4479e.get(i);
            if (c1577d.f4465d) {
                Glide.with(this.f4475a).load((int) R.drawable.add_more).into(c1582c.f4472b);
            } else if (c1577d.f4464c.length() > 1) {
                File file = new File(c1577d.f4464c);
                Glide.with(this.f4475a).load(file).override(this.f4476b, this.f4476b).into(c1582c.f4472b);
            } else {
                Glide.with(this.f4475a).load("").override(this.f4476b, this.f4476b).error((int) R.drawable.browser_globe).into(c1582c.f4472b);
            }
            c1582c.f4473c.setText("" + c1577d.f4462a);
            if (!c1577d.f4465d) {
                GradientDrawable gradientDrawable = new GradientDrawable();
                gradientDrawable.setColor(c1577d.f4466e);
                gradientDrawable.setShape(GradientDrawable.RECTANGLE);
                gradientDrawable.setCornerRadius(SecurityHelpers.m14847a(5.0f, this.f4475a));
                c1582c.f4474d.setBackground(gradientDrawable);
            }
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new C1582c(this, LayoutInflater.from(viewGroup.getContext()).inflate(i == 1 ? R.layout.add_item_card : R.layout.raw_bookmark_item, viewGroup, false));
    }
}
