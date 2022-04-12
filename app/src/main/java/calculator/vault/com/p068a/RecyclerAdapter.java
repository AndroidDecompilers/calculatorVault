package calculator.vault.com.p068a;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore.Images.Media;
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
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import calculator.vault.com.R;
import calculator.vault.com.customgallery.pictures.PictureModel;
import calculator.vault.com.customgallery.pictures.C3044b;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.p029h.ItemClickInterface;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.C1586a> {

    LayoutInflater layoutInflater;
    List<PictureModel> pictureModels;
    Context context;
    LayoutParams layoutParams;
    int f4493e = C1131f.f3316b;
    int f4494f = C1131f.f3317c;
    ItemClickInterface f4495g;
    String f4496h;

    public class C1586a extends RecyclerView.ViewHolder {
        ImageView f4485a;
        TextView f4486b;
        View f4487c;


        public C1586a(final RecyclerAdapter recyclerAdapter, View view) {
            super(view);
            this.f4485a = (ImageView) view.findViewById(R.id.ivAlbumThumb);
            this.f4487c = view.findViewById(R.id.ivIsSdCard);
            this.f4485a.setLayoutParams(recyclerAdapter.layoutParams);
            this.f4486b = (TextView) view.findViewById(R.id.tvAlbumName);
            this.f4486b.setTypeface(C1131f.f3315a);
            this.f4485a.setScaleType(ScaleType.CENTER_CROP);
            view.setOnClickListener(new OnClickListener() {

                public void onClick(View view) {
                    f4495g.onItemClicked(view, getAdapterPosition());
                }
            });
        }
    }

    public RecyclerAdapter(Context context, List<PictureModel> list) {
        this.pictureModels = list;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.f4493e = this.f4493e < 1 ? 720 : this.f4493e;
        this.f4494f = this.f4494f < 1 ? 1280 : this.f4494f;
        int a = (this.f4493e / 2) - C3044b.m14508a(this.context, 24.0f);
        this.layoutParams = new LayoutParams(a, a);
        this.layoutParams.gravity = 17;
    }

    private int m7211a(String str) {
        try {
            String str2 = "datetaken";
            Cursor query = this.context.getContentResolver().query(Media.EXTERNAL_CONTENT_URI, null, "bucket_id = \"" + str + "\"", null, "datetaken DESC");
            if (query.getCount() > 0) {
                query.moveToFirst();
                this.f4496h = query.getString(1);
                return query.getCount();
            }
            query.close();
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

    public C1586a m7212a(ViewGroup viewGroup, int i) {
        return new C1586a(this, this.layoutInflater.inflate(R.layout.raw_item_folders, viewGroup, false));
    }

    public void m7213a(C1586a c1586a, int i) {
        PictureModel pictureModel = (PictureModel) this.pictureModels.get(i);
        String str = pictureModel.f9591c;
        if (str.length() > 12) {
            str = str.substring(0, 12) + "..";
        }
        c1586a.f4486b.setText(str + " (" + m7211a(pictureModel.m14502a()) + ")");
        Glide.with(this.context).load(this.f4496h).error(R.drawable.error_image).diskCacheStrategy(DiskCacheStrategy.NONE).into(c1586a.f4485a);
        c1586a.f4487c.setVisibility(pictureModel.m14507c() ? View.VISIBLE : View.GONE);
    }

    public void m7214a(ItemClickInterface itemClickInterface) {
        this.f4495g = itemClickInterface;
    }

    public int getItemCount() {
        return this.pictureModels.size();
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void onBindViewHolder(C1586a c0823u, int i) {
        m7213a(c0823u, i);
    }

    public C1586a onCreateViewHolder(ViewGroup viewGroup, int i) {
        return m7212a(viewGroup, i);
    }
}
