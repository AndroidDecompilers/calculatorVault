package calculator.vault.com.p068a;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import calculator.vault.com.R;
import calculator.vault.com.customgallery.pictures.C3044b;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.p029h.ItemClickInterface;
import calculator.vault.com.p029h.C1098e;

public class FolderAdapterPhotos extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    LayoutInflater layoutInflater;
    List<FileModel> fileModels;
    Context context;
    LayoutParams f4446d;
    LayoutParams f4447e;
    ViewGroup.LayoutParams f4448f;
    int f4449g = C1131f.f3316b;
    int f4450h = C1131f.f3317c;
    boolean f4451i;
    ItemClickInterface f4452j;
    C1098e f4453k;
    boolean f4454l;
    String f4455m;

    public class AdViewHolder extends RecyclerView.ViewHolder {
        ImageView f4431a;
        ImageView f4432b;
        TextView f4433c;
        Button f4434d;

        public AdViewHolder(final FolderAdapterPhotos folderAdapterPhotos, View view) {
            super(view);
            this.f4431a = (ImageView) view.findViewById(R.id.ivAppIcon);
            this.f4432b = (ImageView) view.findViewById(R.id.ivAlbumThumb);
            this.f4434d = (Button) view.findViewById(R.id.btnInstall);
            view.setLayoutParams(folderAdapterPhotos.f4448f);
            this.f4433c = (TextView) view.findViewById(R.id.tvAppName);
            this.f4433c.setTypeface(C1131f.f3315a);
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    redirectToMarket(getAdapterPosition());
                }
            });
            this.f4434d.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    redirectToMarket(getAdapterPosition());
                }
            });
        }
    }

    public class FolderThumNailHolder extends RecyclerView.ViewHolder {
        ImageView f4440a;
        TextView f4441b;

        public FolderThumNailHolder(final FolderAdapterPhotos folderAdapterPhotos, View view) {
            super(view);
            this.f4440a = (ImageView) view.findViewById(R.id.ivAlbumThumb);
            this.f4440a.setLayoutParams(folderAdapterPhotos.f4446d);
            this.f4441b = (TextView) view.findViewById(R.id.tvAlbumName);
            this.f4441b.setTypeface(C1131f.f3315a);
            this.f4440a.setScaleType(ScaleType.CENTER_CROP);
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    f4452j.onItemClicked(view, getAdapterPosition());
                }
            });
            view.setOnLongClickListener(new OnLongClickListener() {
                public boolean onLongClick(View view) {
                    f4453k.mo974b(getAdapterPosition());
                    return true;
                }
            });
        }
    }

    public FolderAdapterPhotos(Context context, List<FileModel> list, boolean z, boolean z2) {
        this.context = context;
        this.f4451i = z;
        this.fileModels = list;
        this.f4454l = z2;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.f4449g = this.f4449g < 1 ? 720 : this.f4449g;
        this.f4450h = this.f4450h < 1 ? 1280 : this.f4450h;
        int a = (this.f4449g / 2) - C3044b.m14508a(context, 24.0f);
        int a2 = (this.f4449g / 2) - C3044b.m14508a(context, 14.0f);
        this.f4446d = new LayoutParams(a, a);
        this.f4448f = new ViewGroup.LayoutParams(a2, a2);
        this.f4446d.gravity = 17;
        this.f4447e = new LayoutParams(a, C3044b.m14508a(context, 40.0f));
    }

    private int m7197a(String str) {
        try {
            File[] listFiles = new File(str).listFiles();
            int length = listFiles.length;
            if (listFiles != null) {
                if (length > 1) {
                    Arrays.sort(listFiles, new Comparator<File>() {
                        @Override
                        public int compare(File file, File file2) {
                            return f4451i ? file.lastModified() < file2.lastModified() ? -1 : 1 : file.lastModified() <= file2.lastModified() ? 1 : -1;
                        }
                    });
                } else {
                    this.f4455m = listFiles[0].getAbsolutePath();
                }
            }
            this.f4455m = listFiles[0].getAbsolutePath();
            return length;
        } catch (Exception e) {
            this.f4455m = "";
            return 0;
        }
    }

    private void redirectToMarket(int i) {
        String str = this.fileModels.get(i).f4565d.f4625b;
        str = str.substring(str.lastIndexOf("?id=") + 4, str.length());
        Intent intent;
        try {
            intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + str));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.context.startActivity(intent);
        }
    }

    public void m7200a(ItemClickInterface itemClickInterface) {
        this.f4452j = itemClickInterface;
    }

    public void m7201a(C1098e c1098e) {
        this.f4453k = c1098e;
    }

    public void m7202a(boolean z) {
        this.f4451i = z;
        notifyDataSetChanged();
    }

    public int getItemCount() {
        return this.fileModels.size();
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public int getItemViewType(int i) {
        return ((FileModel) this.fileModels.get(i)).f4564c ? 1 : 0;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder c0823u, int i) {
        int i2 = R.drawable.error_video;
        switch (getItemViewType(i)) {
            case 0:
                FolderThumNailHolder folderThumNailHolder = (FolderThumNailHolder) c0823u;
                FileModel fileModel = this.fileModels.get(i);
                String str = fileModel.fileName;
                if (str.length() > 12) {
                    str = str.substring(0, 13) + "..";
                }
                this.f4455m = "";
                int a = m7197a(fileModel.fileAddress);
                fileModel.f4567f = a;
                folderThumNailHolder.f4441b.setText(str + " (" + a + ")");
                DrawableRequestBuilder a2;
                if (a > 0) {
                    a2 = Glide.with(this.context).load(this.f4455m).error(this.f4454l ? R.drawable.error_video : R.drawable.error_image);
                    if (!this.f4454l) {
                        i2 = R.drawable.error_image;
                    }
                    a2.error(i2).diskCacheStrategy(DiskCacheStrategy.NONE).into(folderThumNailHolder.f4440a);
                    return;
                }
//                folderThumNailHolder.f4440a.setImageResource(R.drawable.transparent);
                a2 = Glide.with(this.context).load(this.f4454l ? R.drawable.error_video : R.drawable.error_image);
                a2.error(i2);
                a2.into(folderThumNailHolder.f4440a);
                return;
            case 1:
                AdViewHolder adViewHolder = (AdViewHolder) c0823u;
                C1625q c1625q = ((FileModel) this.fileModels.get(i)).f4565d;
                adViewHolder.f4433c.setText(c1625q.f4624a);
                Glide.with(this.context).load(c1625q.f4627d).placeholder((int) R.drawable.transparent).error((int) R.drawable.image_empty_folder).into(adViewHolder.f4432b);
                Glide.with(this.context).load(c1625q.f4628e).placeholder((int) R.drawable.transparent).error((int) R.drawable.transparent).into(adViewHolder.f4431a);
                return;
            default:
                return;
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return new FolderThumNailHolder(this, this.layoutInflater.inflate(R.layout.raw_item_folders, viewGroup, false));
            case 1:
                return new AdViewHolder(this, this.layoutInflater.inflate(R.layout.dirs_native_ad, viewGroup, false));
            default:
                return new FolderThumNailHolder(this, this.layoutInflater.inflate(R.layout.raw_item_folders, viewGroup, false));
        }
    }
}
