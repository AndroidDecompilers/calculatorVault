package calculator.vault.com.p068a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import calculator.vault.com.R;
import calculator.vault.com.customgallery.pictures.C3044b;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.p029h.ItemClickInterface;
import calculator.vault.com.p029h.C1098e;
import calculator.vault.com.p029h.C1099i;

public class C1639t extends RecyclerView.Adapter<C1639t.C1638b> {
    LayoutInflater f4665a;
    List<C1611o> f4666b;
    Context f4667c;
    LayoutParams f4668d;
    int f4669e;
    boolean f4670f;
    int f4671g;
    int f4672h = 8;
    C1099i f4673i;
    ItemClickInterface f4674j;
    C1098e f4675k;
    boolean f4676l;
//
//    public class C1634a implements C1064d<String, C1410b> {
//        C1638b f4650a;
//        Context f4651b;
//        final /* synthetic */ C1639t f4652c;
//
//        public C1634a(C1639t c1639t, Context context, C1638b c1638b) {
//            this.f4652c = c1639t;
//            this.f4650a = c1638b;
//            this.f4651b = context;
//        }
//
//        public boolean m7282a(C1410b c1410b, String str, C1446j<C1410b> c1446j, boolean z, boolean z2) {
//            return false;
//        }
//
//        public boolean m7284a(Exception exception, String str, C1446j<C1410b> c1446j, boolean z) {
//            this.f4650a.f4662d.setVisibility(0);
//            this.f4650a.f4662d.startAnimation(AnimationUtils.loadAnimation(this.f4651b, 17432576));
//            return false;
//        }
//    }

    public class C1638b extends RecyclerView.ViewHolder {
        public ImageView f4659a;
        public ImageView f4660b;
        public CheckBox f4661c;
        public FrameLayout f4662d;
        public TextView f4663e;
        final /* synthetic */ C1639t f4664f;

        public C1638b(final C1639t c1639t, View view) {
            super(view);

            this.f4664f = c1639t;
            this.f4659a = (ImageView) view.findViewById(R.id.ivAlbumThumb);
            this.f4660b = (ImageView) view.findViewById(R.id.ivPlaybtn);
            this.f4659a.setScaleType(ScaleType.CENTER_CROP);
            this.f4659a.setLayoutParams(c1639t.f4668d);
            this.f4662d = (FrameLayout) view.findViewById(R.id.flFileName);
            this.f4663e = (TextView) view.findViewById(R.id.tvFileName);
            this.f4663e.getLayoutParams().width = c1639t.f4668d.width;
            this.f4661c = (CheckBox) view.findViewById(R.id.checkBox1);
            this.f4661c.setLayoutParams(c1639t.f4668d);
            this.f4661c.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    C1639t c1639t;
                    boolean z = true;
                    ((FrameLayout) view.getParent()).startAnimation(AnimationUtils.loadAnimation(f4664f.f4667c, R.anim.scale_btn));
                    int intValue = ((Integer) view.getTag()).intValue();
                    boolean z2 = !((C1611o) f4664f.f4666b.get(intValue)).f4586b;
                    ((C1611o) f4664f.f4666b.get(intValue)).m7255a(z2);
                    if (z2) {
                        c1639t = f4664f;
                        c1639t.f4669e++;
                    } else {
                        c1639t = f4664f;
                        c1639t.f4669e--;
                    }
                    c1639t = f4664f;
                    if (f4664f.f4669e != f4664f.f4671g) {
                        z = false;
                    }
                    c1639t.f4670f = z;
                    if (f4664f.f4673i != null) {
                        f4664f.f4673i.mo975c(f4664f.f4669e);
                    }
                }
            });
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    f4664f.f4674j.onItemClicked(view, getAdapterPosition());
                }
            });
            view.setOnLongClickListener(new OnLongClickListener() {

                public boolean onLongClick(View view) {
                    f4664f.f4675k.mo974b(getAdapterPosition());
                    return false;
                }
            });
        }
    }

    public C1639t(Context context, List<C1611o> list, boolean z) {
        this.f4667c = context;
        this.f4666b = list;
        this.f4671g = this.f4666b.size();
        this.f4665a = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        C1131f.f3316b = C1131f.f3316b < 1 ? 720 : C1131f.f3316b;
        C1131f.f3317c = C1131f.f3317c < 1 ? 1280 : C1131f.f3317c;
        this.f4676l = z;
        int a = (C1131f.f3316b / 3) - C3044b.m14508a(context, 10.0f);
        this.f4668d = new LayoutParams(a, a);
        this.f4668d.gravity = 17;
    }

    public C1638b m7286a(ViewGroup viewGroup, int i) {
        return new C1638b(this, this.f4665a.inflate(R.layout.raw_item_images, viewGroup, false));
    }

    public ArrayList<C1611o> m7287a() {
        ArrayList<C1611o> arrayList = new ArrayList();
        if (this.f4669e < 1) {
            return arrayList;
        }
        for (C1611o c1611o : this.f4666b) {
            if (c1611o.f4586b) {
                arrayList.add(c1611o);
            }
        }
        return arrayList;
    }

    public void m7288a(int i) {
        this.f4672h = i;
        this.f4669e = 0;
        this.f4673i.mo975c(this.f4669e);
        notifyDataSetChanged();
    }

    @SuppressLint("WrongConstant")
    public void m7289a(C1638b c1638b, int i) {
        int i2 = R.drawable.error_video;
        C1611o c1611o = (C1611o) this.f4666b.get(i);
        CharSequence name = new File(c1611o.f4585a).getName();
        if (this.f4676l) {
            c1638b.f4660b.setVisibility(0);
        } else {
            c1638b.f4660b.setVisibility(8);
        }
        DrawableRequestBuilder a = Glide.with(this.f4667c).load(c1611o.f4585a).placeholder(this.f4676l ? R.drawable.error_video : R.drawable.error_image);
        if (!this.f4676l) {
            i2 = R.drawable.error_image;
        }
        a.error(i2).listener(new RequestListener() {
            @Override
            public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
//                f4662d.setVisibility(0);
//                f4662d.startAnimation(AnimationUtils.loadAnimation(this.f4651b, 17432576));
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        }).diskCacheStrategy(DiskCacheStrategy.NONE).into(c1638b.f4659a);
        c1638b.f4663e.setText(name);
        c1638b.f4661c.setVisibility(this.f4672h);
        c1638b.f4661c.setTag(Integer.valueOf(i));
        c1638b.f4661c.setChecked(c1611o.f4586b);
    }

    public void m7290a(ItemClickInterface itemClickInterface) {
        this.f4674j = itemClickInterface;
    }

    public void m7291a(C1098e c1098e) {
        this.f4675k = c1098e;
    }

    public void m7292a(C1099i c1099i) {
        this.f4673i = c1099i;
    }

    public ArrayList<String> m7293b() {
        ArrayList<String> arrayList = new ArrayList();
        if (this.f4669e < 1) {
            return arrayList;
        }
        for (C1611o c1611o : this.f4666b) {
            if (c1611o.f4586b) {
                arrayList.add(c1611o.f4585a);
            }
        }
        return arrayList;
    }

    public void m7294b(int i) {
        ((C1611o) this.f4666b.get(i)).m7255a(true);
        this.f4669e++;
        this.f4672h = 0;
        if (this.f4673i != null) {
            this.f4673i.mo975c(this.f4669e);
        }
        notifyDataSetChanged();
    }

    public void m7295c() {
        for (C1611o c1611o : this.f4666b) {
            c1611o.f4586b = false;
        }
        this.f4670f = false;
        this.f4672h = 8;
        this.f4669e = 0;
        notifyDataSetChanged();
    }

    public void m7296d() {
        int i = 0;
        boolean z = !this.f4670f;
        this.f4670f = z;
        for (C1611o c1611o : this.f4666b) {
            c1611o.f4586b = z;
        }
        if (z) {
            i = this.f4671g;
        }
        this.f4669e = i;
        this.f4673i.mo975c(this.f4669e);
        notifyDataSetChanged();
    }

    public int getItemCount() {
        this.f4671g = this.f4666b.size();
        return this.f4671g;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public /* synthetic */ void onBindViewHolder(C1638b c0823u, int i) {
        m7289a((C1638b) c0823u, i);
    }

    public /* synthetic */ C1638b onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m7286a(viewGroup, i);
    }
}
