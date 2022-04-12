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

import java.util.ArrayList;
import java.util.List;

import calculator.vault.com.R;
import calculator.vault.com.customgallery.pictures.PictureModel;
import calculator.vault.com.customgallery.pictures.C3044b;
import calculator.vault.com.lock.C1131f;

public class C1598j extends RecyclerView.Adapter<C1598j.C1597b> {
    LayoutInflater f4545a;
    List<PictureModel> f4546b;
    Context f4547c;
    LayoutParams f4548d;
    int f4549e;
    int f4550f;
    public int f4551g;
    boolean f4552h;
    int f4553i;
    int f4554j = 8;

//    public class C1595a implements C1064d<String, C1410b> {
//        C1597b f4534a;
//        Context f4535b;
//        final /* synthetic */ C1598j f4536c;
//
//        public C1595a(C1598j c1598j, Context context, C1597b c1597b) {
//            this.f4536c = c1598j;
//            this.f4534a = c1597b;
//            this.f4535b = context;
//        }
//
//        public boolean m7230a(C1410b c1410b, String str, C1446j<C1410b> c1446j, boolean z, boolean z2) {
//            return false;
//        }
//
//        public boolean m7232a(Exception exception, String str, C1446j<C1410b> c1446j, boolean z) {
//            this.f4534a.f4542d.setVisibility(0);
//            this.f4534a.f4542d.startAnimation(AnimationUtils.loadAnimation(this.f4535b, 17432576));
//            return false;
//        }
//    }

    public class C1597b extends RecyclerView.ViewHolder {
        ImageView f4539a;
        ImageView f4540b;
        CheckBox f4541c;
        FrameLayout f4542d;
        TextView f4543e;
        final /* synthetic */ C1598j f4544f;

        public C1597b(final C1598j c1598j, View view) {

            super(view);
            this.f4544f = c1598j;
            this.f4540b = (ImageView) view.findViewById(R.id.ivPlaybtn);
            this.f4540b.setVisibility(View.VISIBLE);
            this.f4539a = (ImageView) view.findViewById(R.id.ivAlbumThumb);
            this.f4539a.setLayoutParams(c1598j.f4548d);
            this.f4539a.setScaleType(ScaleType.CENTER_CROP);
            this.f4541c = (CheckBox) view.findViewById(R.id.checkBox1);
            this.f4541c.setLayoutParams(c1598j.f4548d);
            this.f4541c.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    C1598j c1598j;
                    boolean z = true;
                    ((FrameLayout) view.getParent()).startAnimation(AnimationUtils.loadAnimation(f4544f.f4547c, R.anim.scale_btn));
                    PictureModel pictureModel = (PictureModel) f4544f.f4546b.get(getAdapterPosition());
                    boolean z2 = !pictureModel.f9592d;
                    pictureModel.m14504a(z2);
                    if (z2) {
                        c1598j = f4544f;
                        c1598j.f4551g++;
                    } else {
                        c1598j = f4544f;
                        c1598j.f4551g--;
                    }
                    c1598j = f4544f;
                    if (f4544f.f4551g != f4544f.f4553i) {
                        z = false;
                    }
                    c1598j.f4552h = z;
                }
            });
            this.f4543e = (TextView) view.findViewById(R.id.tvFileName);
            this.f4543e.setTypeface(C1131f.f3315a);
            this.f4542d = (FrameLayout) view.findViewById(R.id.flFileName);
            this.f4543e.getLayoutParams().width = c1598j.f4548d.width;
        }
    }

    public C1598j(Context context, List<PictureModel> list) {
        this.f4546b = list;
        this.f4545a = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.f4547c = context;
        this.f4549e = C1131f.f3316b;
        this.f4550f = C1131f.f3317c;
        this.f4549e = this.f4549e < 1 ? 720 : this.f4549e;
        this.f4550f = this.f4550f < 1 ? 1280 : this.f4550f;
        int a = (this.f4549e / 3) - C3044b.m14508a(this.f4547c, 10.0f);
        this.f4548d = new LayoutParams(a, a);
        this.f4548d.gravity = 17;
    }

    public C1597b m7234a(ViewGroup viewGroup, int i) {
        return new C1597b(this, this.f4545a.inflate(R.layout.raw_item_images, viewGroup, false));
    }

    public void m7235a() {
        for (PictureModel pictureModel : this.f4546b) {
            pictureModel.f9592d = false;
        }
        this.f4552h = false;
        this.f4551g = 0;
        notifyDataSetChanged();
    }

    public void m7236a(int i) {
        this.f4554j = i;
    }

    @SuppressLint("WrongConstant")
    public void m7237a(C1597b c1597b, int i) {
        PictureModel pictureModel = (PictureModel) this.f4546b.get(i);
        Glide.with(this.f4547c).load(pictureModel.f9589a).asBitmap().placeholder(R.drawable.error_video).error(R.drawable.error_video).into(c1597b.f4539a);
        c1597b.f4543e.setText("" + pictureModel.f9591c);
        c1597b.f4541c.setVisibility(this.f4554j);
        c1597b.f4541c.setChecked(pictureModel.f9592d);
    }

    public void m7238b() {
        int i = 0;
        boolean z = !this.f4552h;
        this.f4552h = z;
        for (PictureModel pictureModel : this.f4546b) {
            pictureModel.f9592d = z;
        }
        if (z) {
            i = this.f4553i;
        }
        this.f4551g = i;
        notifyDataSetChanged();
    }

    public ArrayList<String> m7239c() {
        ArrayList<String> arrayList = new ArrayList();
        for (PictureModel pictureModel : this.f4546b) {
            if (pictureModel.m14506b()) {
                arrayList.add(pictureModel.f9589a);
            }
        }
        return arrayList;
    }

    public int getItemCount() {
        this.f4553i = this.f4546b.size();
        return this.f4553i;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public /* synthetic */ void onBindViewHolder(C1597b c0823u, int i) {
        m7237a((C1597b) c0823u, i);
    }

    public /* synthetic */ C1597b onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m7234a(viewGroup, i);
    }
}
