package calculator.vault.com.p068a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.util.List;

import calculator.vault.com.R;
import calculator.vault.com.customgallery.pictures.C3044b;
import calculator.vault.com.lock.C1131f;

public class C1565a extends BaseAdapter {
    LayoutInflater f4416a;
    List<FileModel> f4417b;
    Context f4418c;
    LayoutParams f4419d;
    LayoutParams f4420e;
    int f4421f = C1131f.f3316b;
    int f4422g = C1131f.f3317c;
    boolean f4423h;
    public boolean f4424i;
    String f4425j;

    private class C1564a {
        ImageView f4412a;
        TextView f4413b;
        ImageView f4414c;
        final /* synthetic */ C1565a f4415d;

        private C1564a(C1565a c1565a) {
            this.f4415d = c1565a;
        }
    }

    public C1565a(Context context, List<FileModel> list, boolean z, boolean z2) {
        this.f4418c = context;
        this.f4423h = z;
        this.f4417b = list;
        this.f4424i = z2;
        this.f4416a = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.f4421f = this.f4421f < 1 ? 720 : this.f4421f;
        this.f4422g = this.f4422g < 1 ? 1280 : this.f4422g;
        this.f4419d = new LayoutParams((this.f4421f / 2) - C3044b.m14508a(context, 25.0f), (this.f4421f / 2) - C3044b.m14508a(context, 30.0f));
        this.f4419d.gravity = 17;
        this.f4420e = new LayoutParams((this.f4421f / 2) - C3044b.m14508a(context, 25.0f), C3044b.m14508a(context, 33.0f));
    }

    private int m7195a(String str) {
        try {
            File file = new File(str);
            int length = file.list().length;
            if (this.f4424i) {
                this.f4425j = file.listFiles()[0].getAbsolutePath();
                return length;
            }
            this.f4425j = file.listFiles()[length - 1].getAbsolutePath();
            return length;
        } catch (Exception e) {
            this.f4425j = "";
            return 0;
        }
    }

    public int getCount() {
        return this.f4417b.size();
    }

    public Object getItem(int i) {
        return this.f4417b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1564a c1564a;
        int i2 = R.drawable.error_image;
        if (view == null) {
            c1564a = new C1564a(this);
            view = this.f4416a.inflate(R.layout.raw_item_folders, null);
            c1564a.f4412a = (ImageView) view.findViewById(R.id.ivAlbumThumb);
            c1564a.f4414c = (ImageView) view.findViewById(R.id.ivPlaybtn);
            c1564a.f4412a.setLayoutParams(this.f4419d);
            c1564a.f4413b = (TextView) view.findViewById(R.id.tvAlbumName);
            c1564a.f4412a.setScaleType(ScaleType.CENTER_CROP);
            c1564a.f4413b.setLayoutParams(this.f4420e);
            view.setTag(c1564a);
        } else {
            c1564a = (C1564a) view.getTag();
        }
        FileModel fileModel = (FileModel) this.f4417b.get(i);
        String str = fileModel.fileName;
        if (str.length() > 12) {
            str = str.substring(0, 13) + "..";
        }
        this.f4425j = "";
        int a = m7195a(fileModel.fileAddress);
        c1564a.f4413b.setText(str + " (" + a + ")");
        if (a > 0) {
            if (this.f4423h) {
                c1564a.f4414c.setVisibility(View.VISIBLE);
            }
            Glide.with(this.f4418c).load(this.f4425j).error(this.f4423h ? R.drawable.error_video : R.drawable.error_image).diskCacheStrategy(DiskCacheStrategy.NONE).into(c1564a.f4412a);
//            Glide.with(this.f4418c).load(this.f4425j).error(this.f4423h ? R.drawable.error_video : R.drawable.error_image).diskCacheStrategy(DiskCacheStrategy.NONE).m6168c().mo1009a(c1564a.f4412a);
        } else {
            if (this.f4423h) {
                c1564a.f4414c.setVisibility(View.GONE);
            }
            c1564a.f4412a.setImageResource(0);
            ImageView imageView = c1564a.f4412a;
            if (this.f4423h) {
                i2 = R.drawable.error_video;
            }
            imageView.setBackgroundResource(i2);
        }
        return view;
    }
}
