package calculator.vault.com.p068a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import calculator.vault.com.R;
import calculator.vault.com.customgallery.pictures.PictureModel;
import calculator.vault.com.customgallery.pictures.C3044b;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.p029h.C1099i;

public class C1591h extends RecyclerView.Adapter<C1591h.C1590b> {
    LayoutInflater f4507a;
    List<PictureModel> f4508b;
    Context f4509c;
    int f4510d = C1131f.f3316b;
    int f4511e = C1131f.f3317c;
    boolean f4512f;
    public int f4513g;
    boolean f4514h;
    int f4515i;
    C1099i f4516j;
    LayoutParams f4517k;
    int f4518l = 8;

//    public class C1588a implements RequestListener<String, Drwa> {
//        C1590b f4497a;
//        Context f4498b;
//        final /* synthetic */ C1591h f4499c;
//
//        public C1588a(C1591h c1591h, Context context, C1590b c1590b) {
//            this.f4499c = c1591h;
//            this.f4497a = c1590b;
//            this.f4498b = context;
//        }
//
//        @Override
//        public boolean onException(Exception e, String model, Target<String> target, boolean isFirstResource) {
//            return false;
//        }
//
//        @Override
//        public boolean onResourceReady(String resource, String model, Target<String> target, boolean isFromMemoryCache, boolean isFirstResource) {
//            this.f4497a.f4504c.setVisibility(View.VISIBLE);
////            this.f4497a.f4504c.startAnimation(AnimationUtils.loadAnimation(this.f4498b, 17432576));
//            return false;
//        }
//    }

    public class C1590b extends RecyclerView.ViewHolder {
        ImageView f4502a;
        CheckBox f4503b;
        FrameLayout f4504c;
        TextView f4505d;
        final /* synthetic */ C1591h f4506e;

        public C1590b(final C1591h c1591h, View view) {

            super(view);
            this.f4506e = c1591h;
            this.f4502a = (ImageView) view.findViewById(R.id.ivAlbumThumb);
            this.f4502a.setLayoutParams(c1591h.f4517k);
            this.f4502a.setScaleType(ScaleType.CENTER_CROP);
            this.f4503b = (CheckBox) view.findViewById(R.id.checkBox1);
            this.f4503b.setLayoutParams(c1591h.f4517k);
            this.f4503b.setOnClickListener(new OnClickListener() {

                public void onClick(View view) {
                    C1591h c1591h;
                    boolean z = true;
                    ((FrameLayout) view.getParent()).startAnimation(AnimationUtils.loadAnimation(f4506e.f4509c, R.anim.scale_btn));
                    PictureModel pictureModel = (PictureModel) f4506e.f4508b.get(((Integer) view.getTag()).intValue());
                    boolean z2 = !pictureModel.f9592d;
                    pictureModel.m14504a(z2);
                    if (z2) {
                        c1591h = f4506e;
                        c1591h.f4513g++;
                    } else {
                        c1591h = f4506e;
                        c1591h.f4513g--;
                    }
                    c1591h = f4506e;
                    if (f4506e.f4513g != f4506e.f4515i) {
                        z = false;
                    }
                    c1591h.f4514h = z;
                    if (f4506e.f4516j != null) {
                        f4506e.f4516j.mo975c(f4506e.f4513g);
                    }
                }
            });
            this.f4505d = (TextView) view.findViewById(R.id.tvFileName);
            this.f4505d.setTypeface(C1131f.f3315a);
            this.f4504c = (FrameLayout) view.findViewById(R.id.flFileName);
            this.f4505d.getLayoutParams().width = c1591h.f4517k.width;
        }
    }

    public C1591h(Context context, List<PictureModel> list, boolean z) {
        this.f4508b = list;
        this.f4512f = z;
        this.f4507a = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.f4509c = context;
        this.f4510d = this.f4510d < 1 ? 720 : this.f4510d;
        this.f4511e = this.f4511e < 1 ? 1280 : this.f4511e;
        int a = (this.f4510d / 3) - C3044b.m14508a(this.f4509c, 10.0f);
        this.f4517k = new LayoutParams(a, a);
        this.f4517k.gravity = 17;
    }

    public C1590b m7219a(ViewGroup viewGroup, int i) {
        return new C1590b(this, this.f4507a.inflate(R.layout.raw_item_images, viewGroup, false));
    }

    public void m7220a() {
        int i = 0;
        boolean z = !this.f4514h;
        this.f4514h = z;
        for (PictureModel pictureModel : this.f4508b) {
            pictureModel.f9592d = z;
        }
        if (z) {
            i = this.f4515i;
        }
        this.f4513g = i;
        notifyDataSetChanged();
        if (this.f4516j != null) {
            this.f4516j.mo975c(this.f4513g);
        }
    }

    public void m7221a(int i) {
        this.f4518l = i;
    }

    @SuppressLint("WrongConstant")
    public void m7222a(C1590b c1590b, int i) {
        c1590b.f4503b.setVisibility(this.f4518l);
        PictureModel pictureModel = (PictureModel) this.f4508b.get(i);
        Glide.with(this.f4509c).load(pictureModel.f9589a).error((int) R.drawable.error_image).placeholder((int) R.drawable.error_image).diskCacheStrategy(DiskCacheStrategy.NONE).crossFade().into(c1590b.f4502a);
//        Glide.with(this.f4509c).load(pictureModel.f9589a).error((int) R.drawable.error_image).placeholder((int) R.drawable.error_image).listener(new C1588a(this, this.f4509c, c1590b)).diskCacheStrategy(DiskCacheStrategy.NONE).crossFade().into(c1590b.f4502a);
        c1590b.f4505d.setText("" + pictureModel.f9591c);
        c1590b.f4503b.setTag(Integer.valueOf(i));
        c1590b.f4503b.setChecked(((PictureModel) this.f4508b.get(i)).f9592d);
    }

    public void m7223a(C1099i c1099i) {
        this.f4516j = c1099i;
    }

    public ArrayList<String> m7224b() {
        ArrayList<String> arrayList = new ArrayList();
        for (PictureModel pictureModel : this.f4508b) {
            if (pictureModel.m14506b()) {
                arrayList.add(pictureModel.f9589a);
            }
        }
        return arrayList;
    }

    public void m7225c() {
        for (PictureModel pictureModel : this.f4508b) {
            pictureModel.f9592d = false;
        }
        this.f4514h = false;
        this.f4513g = 0;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        this.f4515i = this.f4508b.size();
        return this.f4515i;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public /* synthetic */ void onBindViewHolder(C1590b c0823u, int i) {
        m7222a((C1590b) c0823u, i);
    }

    public /* synthetic */ C1590b onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m7219a(viewGroup, i);
    }
}
