package calculator.vault.com.p068a;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore.Video.Media;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import calculator.vault.com.R;
import calculator.vault.com.customgallery.pictures.PictureModel;
import calculator.vault.com.customgallery.pictures.C3044b;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.p029h.ItemClickInterface;

public class C1594i extends RecyclerView.Adapter<C1594i.C1593a> {
    LayoutInflater f4526a;
    List<PictureModel> f4527b;
    Context f4528c;
    LayoutParams f4529d;
    int f4530e;
    int f4531f;
    ItemClickInterface f4532g;
    String f4533h;

    public class C1593a extends RecyclerView.ViewHolder {
        ImageView f4521a;
        TextView f4522b;
        ImageView f4523c;
        View f4524d;
        final /* synthetic */ C1594i f4525e;

        public C1593a(final C1594i c1594i, View view) {
            super(view);
            this.f4525e = c1594i;
            this.f4521a = (ImageView) view.findViewById(R.id.ivAlbumThumb);
            this.f4524d = view.findViewById(R.id.ivIsSdCard);
            this.f4521a.setLayoutParams(c1594i.f4529d);
            this.f4522b = (TextView) view.findViewById(R.id.tvAlbumName);
            this.f4522b.setTypeface(C1131f.f3315a);
            this.f4521a.setScaleType(ScaleType.CENTER_CROP);
            this.f4523c = (ImageView) view.findViewById(R.id.ivPlaybtn);
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    f4525e.f4532g.onItemClicked(view, getAdapterPosition());
                }
            });
        }
    }

    public C1594i(Context context, List<PictureModel> list) {
        this.f4527b = list;
        this.f4526a = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.f4528c = context;
        this.f4530e = C1131f.f3316b < 1 ? 720 : C1131f.f3316b;
        this.f4531f = C1131f.f3317c < 1 ? 1280 : C1131f.f3317c;
        int a = (this.f4530e / 2) - C3044b.m14508a(this.f4528c, 24.0f);
        this.f4529d = new LayoutParams(a, a);
        this.f4529d.gravity = 17;
    }

    private int m7226a(String str) {
        try {
            String str2 = "datetaken";
            Cursor query = this.f4528c.getContentResolver().query(Media.EXTERNAL_CONTENT_URI, null, "bucket_id = \"" + str + "\"", null, "datetaken DESC");
            if (query.getCount() > 0) {
                query.moveToFirst();
                this.f4533h = query.getString(1);
                return query.getCount();
            }
            query.close();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public C1593a m7227a(ViewGroup viewGroup, int i) {
        return new C1593a(this, this.f4526a.inflate(R.layout.raw_item_folders, viewGroup, false));
    }

    public void m7228a(C1593a c1593a, int i) {
        PictureModel pictureModel = (PictureModel) this.f4527b.get(i);
        String str = pictureModel.f9591c;
        if (str.length() > 12) {
            str = str.substring(0, 12) + "..";
        }
        int a = m7226a(pictureModel.m14502a());
        c1593a.f4522b.setText(str + " (" + a + ")");
        if (a < 1) {
            c1593a.f4523c.setVisibility(View.GONE);
        } else {
            c1593a.f4523c.setVisibility(View.VISIBLE);
        }
        Glide.with(this.f4528c).load(this.f4533h).asBitmap().error(R.drawable.error_video).into(c1593a.f4521a);
        c1593a.f4524d.setVisibility(pictureModel.m14507c() ? View.VISIBLE : View.GONE);
    }

    public void m7229a(ItemClickInterface itemClickInterface) {
        this.f4532g = itemClickInterface;
    }

    public int getItemCount() {
        return this.f4527b.size();
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public /* synthetic */ void onBindViewHolder(C1593a c0823u, int i) {
        m7228a((C1593a) c0823u, i);
    }

    public /* synthetic */ C1593a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m7227a(viewGroup, i);
    }
}
