package calculator.vault.com.p073g;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.provider.DocumentFile;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import calculator.vault.com.R;
import calculator.vault.com.applock.C3150b;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.customgallery.pictures.C3044b;
import calculator.vault.com.customgallery.videos.C1127d;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.lock.C1132g;
import calculator.vault.com.lock.MainActivity;
import calculator.vault.com.lock.MyApplication;
import calculator.vault.com.lock.ViewAlbumActivity;
import calculator.vault.com.p029h.ItemClickInterface;
import calculator.vault.com.p029h.C1098e;
import calculator.vault.com.p029h.C2927f;
import calculator.vault.com.p068a.FolderAdapterPhotos;
import calculator.vault.com.p068a.C1600k;
import calculator.vault.com.p068a.FileModel;
import calculator.vault.com.p068a.C1625q;
import calculator.vault.com.p084i.C2929b;
import calculator.vault.com.p084i.C2930c;
import calculator.vault.com.safebrowser.MainBrowserActivity;

public class C1735d extends Fragment implements OnClickListener, ItemClickInterface, C1098e {
    public static C1735d f4874a;
    boolean ai;
    FileModel aj;
    String ak;
    SharedPreferences al;
    Editor am;
    C2929b an;
    ArrayList<C1625q> ao = new ArrayList();
    int ap = -1;
    private Context aq;
    private boolean ar;
    private boolean as;
    RecyclerView recyclerView;
    FolderAdapterPhotos folderAdapterPhotos;
    TextView f4877d;
    View rootView;
    C2930c f4879f;
    String f4880g;
    ImageButton f4881h;
    public ArrayList<FileModel> f4882i = new ArrayList();

    class C17131 implements OnClickListener {
        C1735d f4820a;

        C17131(C1735d c1735d) {
            this.f4820a = c1735d;
        }

        public void onClick(View view) {
            C3150b.m14829a(this.f4820a.getActivity(), this.f4820a.am);
        }
    }

    class C17175 implements C3150b.C1055c {
        final /* synthetic */ C1735d f4830a;

        C17175(C1735d c1735d) {
            this.f4830a = c1735d;
        }

        public void mo966a(boolean z) {
            if (z) {
                MainActivity.mainActivity.f2891h = true;
                this.f4830a.startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), 142);
                return;
            }
            Toast.makeText(this.f4830a.aq, "Choose Internal Storage to restore", Toast.LENGTH_LONG).show();
            this.f4830a.m7437c(this.f4830a.f4882i.indexOf(this.f4830a.aj));
        }
    }

    class C17196 implements OnClickListener {
        final /* synthetic */ C1735d f4833a;

        C17196(C1735d c1735d) {
            this.f4833a = c1735d;
        }

        public void onClick(View view) {
            final PopupWindow popupWindow = new PopupWindow(this.f4833a.getActivity());
            View inflate = this.f4833a.getActivity().getLayoutInflater().inflate(R.layout.pop_menu_item, null);
            ((TextView) inflate.findViewById(R.id.textView1)).setTypeface(C1131f.f3315a);
            ((TextView) inflate.findViewById(R.id.textView2)).setTypeface(C1131f.f3315a);
            ((TextView) inflate.findViewById(R.id.textView3)).setTypeface(C1131f.f3315a);
            OnClickListener c17181 = new OnClickListener() {

                public void onClick(View view) {
                    popupWindow.dismiss();
                    switch (view.getId()) {
                        case R.id.item_Rate:
                            Uri uri = Uri.parse("market://details?id=" + getActivity().getPackageName());
                            Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
                            try {
                                startActivity(myAppLinkToMarket);
                            } catch (ActivityNotFoundException e) {
                                Toast.makeText(getActivity(), " unable to find market app", Toast.LENGTH_LONG).show();
                            }
                            return;
                        case R.id.itemContact:
                            C1131f.sendEmail(f4833a.getActivity());
                            return;
                        case R.id.itemShare:
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.SEND");
                            intent.setType("text/plain");
                            intent.putExtra("android.intent.extra.TEXT", f4833a.getActivity().getString(R.string.shareString));
                            f4833a.startActivity(Intent.createChooser(intent, "Share via"));
                            return;
//                        case R.id.itemFollow:
//                            f4833a.startActivity(f4833a.m7449b(f4833a.aq));
//                            return;
                        default:
                            return;
                    }
                }
            };
            inflate.findViewById(R.id.item_Rate).setOnClickListener(c17181);
            inflate.findViewById(R.id.itemContact).setOnClickListener(c17181);
            inflate.findViewById(R.id.itemShare).setOnClickListener(c17181);
            popupWindow.setContentView(inflate);
            popupWindow.setHeight(-2);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);
            popupWindow.setWidth((int) SecurityHelpers.m14847a(160.0f, this.f4833a.getActivity()));
            popupWindow.showAsDropDown(view, 10, -110);
        }
    }

    class C17229 extends AsyncTask<Void, Void, Void> {


        protected Void doInBackground(Void... voidArr) {
            f4882i = m7436c(f4880g);
            if (!as) {
                try {
                    ArrayList a = an.m14124a(aq.getPackageManager(), true);
                    if (f4882i.size() >= 3 && a.size() > 0) {
                        int i = 0;
                        for (int i2 = 3; i2 <= f4882i.size(); i2 += 3) {
                            f4882i.add(i2, new FileModel((C1625q) a.get(i)));
                            i++;
                            if (a.size() <= i) {
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
            return null;
        }

        protected void onPostExecute(Void voidR) {
            folderAdapterPhotos = new FolderAdapterPhotos(aq, f4882i, C1131f.f3324j, true);
            folderAdapterPhotos.m7200a(C1735d.this);
            folderAdapterPhotos.m7201a(C1735d.this);
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(folderAdapterPhotos);
            m7428Q();
            super.onPostExecute(voidR);
        }
    }

    class C1733a extends AsyncTask<Void, Integer, C2927f> {
        Dialog f4852a;
        FileModel f4853b;
        ProgressBar f4854c;
        TextView f4855d;
        TextView f4856e;
        int f4857f;
        int f4858g = 0;
        DocumentFile f4859h;
        boolean f4860i;
        boolean f4861j;
        DocumentFile f4862k;
        C1625q f4863l;
        View f4864m;
        Button f4865n;
        TextView f4866o;
        int f4867p;
        int f4868q = -1;
        OnClickListener f4869r = new C17326(this);
        final /* synthetic */ C1735d f4870s;

        class C17231 implements OnClickListener {
            final /* synthetic */ C1733a f4840a;

            C17231(C1733a c1733a) {
                this.f4840a = c1733a;
            }

            public void onClick(View view) {
                this.f4840a.f4861j = true;
            }
        }

        class C17293 implements Runnable {
            final /* synthetic */ C1733a f4848a;

            C17293(C1733a c1733a) {
                this.f4848a = c1733a;
            }

            public void run() {
                this.f4848a.f4855d.setText("1/" + this.f4848a.f4857f);
            }
        }

        class C17304 implements Runnable {
            final /* synthetic */ C1733a f4849a;

            C17304(C1733a c1733a) {
                this.f4849a = c1733a;
            }

            public void run() {
                this.f4849a.f4855d.setText(this.f4849a.f4858g + "/" + this.f4849a.f4857f);
            }
        }

        class C17315 implements OnClickListener {
            final /* synthetic */ C1733a f4850a;

            C17315(C1733a c1733a) {
                this.f4850a = c1733a;
            }

            public void onClick(View view) {
                try {
                    if (this.f4850a.f4852a != null && this.f4850a.f4852a.isShowing()) {
                        this.f4850a.f4852a.dismiss();
                    }
                } catch (Exception e) {
                }
                this.f4850a.f4861j = false;
            }
        }

        class C17326 implements OnClickListener {
            final /* synthetic */ C1733a f4851a;

            C17326(C1733a c1733a) {
                this.f4851a = c1733a;
            }

            public void onClick(View view) {
                C1131f.m5809b(MainActivity.mainActivity, this.f4851a.f4863l.f4625b);
            }
        }

        public C1733a(C1735d c1735d, FileModel fileModel, boolean z, DocumentFile c0318a, C1625q c1625q) {
            this.f4870s = c1735d;
            this.f4853b = fileModel;
            this.f4860i = z;
            this.f4859h = c0318a;
            this.f4863l = c1625q;
        }

        private C2927f m7417a(File file) {
            for (File file2 : file.listFiles()) {
                if (this.f4861j) {
                    return C2927f.CANCELLED;
                }
                C2927f a;
                if (file2.isDirectory()) {
                    a = m7417a(file2);
                    if (a == C2927f.FAILED || a == C2927f.CANCELLED) {
                        return a;
                    }
                    file2.delete();
                } else {
                    this.f4868q = -1;
                    a = m7418b(file2);
                    if (a == C2927f.FAILED || a == C2927f.CANCELLED) {
                        return a;
                    }
                    FileModel fileModel = this.f4853b;
                    fileModel.f4567f--;
                }
            }
            return C2927f.SUCCESS;
        }

        private C2927f m7418b(File file) {
            String absolutePath = file.getAbsolutePath();
            this.f4858g++;
            absolutePath = absolutePath.replace(this.f4870s.f4880g + "/" + this.f4870s.ak, C1131f.f3320f);
            if (absolutePath.contains("null")) {
                absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + file.getName();
            }
            MainActivity.mainActivity.runOnUiThread(new C17304(this));
            File file2 = new File(absolutePath);
            try {
                OutputStream openOutputStream;
                if (this.f4860i) {
                    this.f4862k = C1132g.m5812a(file2, false, this.f4870s.aq);
                    openOutputStream = this.f4870s.aq.getContentResolver().openOutputStream(this.f4862k.getUri());
                } else {
                    openOutputStream = new FileOutputStream(file2);
                }
                InputStream fileInputStream = new FileInputStream(file);
                byte[] bArr = new byte[1024];
                long j = 0;
                int available = fileInputStream.available();
                do {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    j += (long) read;
                    publishProgress(new Integer[]{Integer.valueOf((int) ((100 * j) / ((long) available)))});
                    openOutputStream.write(bArr, 0, read);
                } while (!this.f4861j);
                openOutputStream.flush();
                openOutputStream.close();
                fileInputStream.close();
                if (this.f4860i) {
                    this.f4862k.delete();
                } else {
                    file2.delete();
                }
                if (this.f4861j) {
                    return C2927f.CANCELLED;
                }
                openOutputStream.flush();
                openOutputStream.close();
                fileInputStream.close();
                if (file.delete()) {
                    this.f4870s.f4879f.m14129a(file.getName());
                    C1131f.m5805a(this.f4870s.aq, file2, "video/*");
                } else if (FileUtils.deleteQuietly(file)) {
                    this.f4870s.f4879f.m14129a(file.getName());
                    C1131f.m5805a(this.f4870s.aq, file2, "video/*");
                }
                return C2927f.SUCCESS;
            } catch (FileNotFoundException e) {
                return C2927f.FAILED;
            } catch (IOException e2) {
                return C2927f.FAILED;
            } catch (NullPointerException e3) {
                return C2927f.FAILED;
            }
        }

        protected C2927f doInBackground(Void... voidArr) {
            File file = new File(this.f4853b.fileAddress);
            C2927f a;
            if (file.isDirectory()) {
                this.f4857f = file.list().length;
                if (this.f4857f <= 0) {
                    return C2927f.FAILED;
                }
                this.f4870s.getActivity().runOnUiThread(new C17293(this));
                a = m7417a(file);
                if (a == C2927f.FAILED || a == C2927f.CANCELLED) {
                    return a;
                }
                file.delete();
                this.f4867p = this.f4870s.f4882i.indexOf(this.f4853b);
                this.f4870s.f4882i.remove(this.f4853b);
            } else {
                a = m7418b(file);
                if (a == C2927f.FAILED || a == C2927f.CANCELLED) {
                    return a;
                }
                this.f4870s.f4879f.m14129a(file.getName());
                this.f4867p = this.f4870s.f4882i.indexOf(this.f4853b);
                this.f4870s.f4882i.remove(this.f4853b);
            }
            return C2927f.SUCCESS;
        }

        protected void onPostExecute(C2927f c2927f) {
            try {
                C1131f.f3320f = Environment.getExternalStorageDirectory() + "/GalleryLock";
                if (c2927f == C2927f.SUCCESS) {
                    this.f4866o.setText(this.f4857f + (this.f4857f > 1 ? " Videos were" : " Video was") + " moved and restored to public gallery.");
                    this.f4854c.setProgress(100);
                    this.f4856e.setText("100%");
                    this.f4865n.setText("DONE");
                    this.f4865n.setOnClickListener(new C17315(this));
                    this.f4870s.folderAdapterPhotos.notifyItemRemoved(this.f4867p);
                    this.f4870s.m7428Q();
                } else if (c2927f == C2927f.CANCELLED) {
                    Toast.makeText(this.f4870s.getActivity(), "Operation Cancelled.", Toast.LENGTH_SHORT).show();
                    this.f4870s.folderAdapterPhotos.notifyDataSetChanged();
                    if (this.f4852a != null && this.f4852a.isShowing()) {
                        this.f4852a.dismiss();
                    }
                } else {
                    if (this.f4852a != null && this.f4852a.isShowing()) {
                        this.f4852a.dismiss();
                    }
                    Toast.makeText(this.f4870s.getActivity(), "Empty Folder! nothing to restore", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
            }
            super.onPostExecute(c2927f);
        }

        protected void onProgressUpdate(Integer... numArr) {
            int intValue = numArr[0].intValue();
            if (intValue - this.f4868q > 0) {
                this.f4854c.setProgress(intValue);
                this.f4856e.setText(intValue + "%");
                this.f4868q = intValue;
            }
            super.onProgressUpdate(numArr);
        }

        protected void onPreExecute() {
            this.f4852a = new ProgressDialog(this.f4870s.getActivity());
            final View inflate = this.f4870s.getActivity().getLayoutInflater().inflate(R.layout.progress_dialog, null);
            this.f4852a.show();
            this.f4852a.setContentView(inflate);
            this.f4852a.setCancelable(false);
            this.f4854c = (ProgressBar) inflate.findViewById(R.id.progressBar1);
            this.f4866o = (TextView) inflate.findViewById(R.id.tvTitle);
            this.f4866o.setText("Please wait..Unhiding video(s)");
            this.f4866o.setTypeface(C1131f.f3315a);
            this.f4855d = (TextView) inflate.findViewById(R.id.tvCount);
            this.f4856e = (TextView) inflate.findViewById(R.id.tvProgress);
            this.f4865n = (Button) inflate.findViewById(R.id.btnCancel);
            this.f4865n.setOnClickListener(new C17231(this));
            if (this.f4863l != null) {
                this.f4864m = inflate.findViewById(R.id.llAd);
                new Handler().postDelayed(new Runnable() {

                    class C17272 implements OnClickListener {
                        class C17261 implements AnimationListener {
                            final /* synthetic */ C17272 f4844a;

                            C17261(C17272 c17272) {
                                this.f4844a = c17272;
                            }

                            public void onAnimationEnd(Animation animation) {
                                f4864m.setVisibility(View.GONE);
                            }

                            public void onAnimationRepeat(Animation animation) {
                            }

                            public void onAnimationStart(Animation animation) {
                            }
                        }

                        public void onClick(View view) {
                            Animation loadAnimation = AnimationUtils.loadAnimation(f4870s.aq, R.anim.adview_shrink);
                            loadAnimation.setAnimationListener(new C17261(this));
                            f4864m.startAnimation(loadAnimation);
                        }
                    }

                    public void run() {
                        f4864m.setVisibility(View.VISIBLE);
                        f4864m.startAnimation(AnimationUtils.loadAnimation(f4870s.aq, R.anim.adview_grow));
                        final ImageView imageView = (ImageView) inflate.findViewById(R.id.ivAd);
                        Glide.with(f4870s.aq).load(f4863l.f4627d).listener(new RequestListener<String, GlideDrawable>() {


                            class C17241 implements Runnable {

                                public void run() {
                                    int width = imageView.getWidth();
                                    int height = imageView.getHeight();
                                    LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
                                    if (C1131f.f3317c > C1131f.f3316b) {
                                        layoutParams.height = (width * 2) / 4;
                                    } else {
                                        layoutParams.height = (height * 2) / 4;
                                    }
                                    layoutParams.width = width;
                                    imageView.setLayoutParams(layoutParams);
                                }

                            }

                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                imageView.post(new C17241());
                                return false;
                            }
                        }).into(imageView);
                        Glide.with(f4870s.aq).load(f4863l.f4628e).into((ImageView) inflate.findViewById(R.id.ivAdIcon));
                        ((TextView) inflate.findViewById(R.id.tvAppName)).setText(f4863l.f4624a);
                        ((TextView) inflate.findViewById(R.id.tvDesc)).setText(f4863l.f4626c);
                        imageView.setOnClickListener(f4869r);
                        inflate.findViewById(R.id.btnInstall).setOnClickListener(f4869r);
                        inflate.findViewById(R.id.btnCloseAd).setOnClickListener(new C17272());
                    }
                }, 1000);
            }
            super.onPreExecute();
        }
    }

    public class C1734b extends AsyncTask<Void, Void, Void> {

        ProgressDialog f4871a;
        FileModel f4872b;
        final /* synthetic */ C1735d f4873c;

        public C1734b(C1735d c1735d, FileModel fileModel) {
            this.f4873c = c1735d;
            this.f4872b = fileModel;
        }

        private void m7422a(File file) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    m7422a(file2);
                    file2.delete();
                } else {
                    file2.delete();
                    this.f4873c.f4879f.m14129a(file2.getName());
                }
            }
        }

        protected Void doInBackground(Void... voidArr) {
            File file = new File(this.f4872b.fileAddress);
            if (file.isDirectory()) {
                m7422a(file);
                file.delete();
                this.f4873c.f4882i.remove(this.f4872b);
            } else {
                file.delete();
                this.f4873c.f4879f.m14129a(file.getName());
                this.f4873c.f4882i.remove(this.f4872b);
            }
            return null;
        }

        protected void onPostExecute(Void voidR) {
            try {
                if (this.f4871a != null && this.f4871a.isShowing()) {
                    this.f4871a.dismiss();
                }
                this.f4873c.folderAdapterPhotos.notifyDataSetChanged();
                this.f4873c.m7428Q();
            } catch (Exception e) {
            }
            super.onPostExecute(voidR);
        }

        protected void onPreExecute() {
            this.f4871a = new ProgressDialog(MainActivity.mainActivity);
            this.f4871a.setTitle("Please wait...");
            this.f4871a.setMessage("It takes a while, depending on file size");
            this.f4871a.setCancelable(false);
            this.f4871a.setProgressStyle(1);
            this.f4871a.show();
            super.onPreExecute();
        }
    }

    private void m7425N() {
        new C17229().execute(new Void[0]);
    }

    private void m7426O() {
        final Dialog dialog = new Dialog(getActivity());
        View inflate = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_dialog, null);
        ((TextView) inflate.findViewById(R.id.textView1)).setTypeface(C1131f.f3315a);
        final EditText editText = (EditText) inflate.findViewById(R.id.editText1);
        editText.setTypeface(C1131f.f3315a);
        dialog.getWindow().setSoftInputMode(4);
        inflate.findViewById(R.id.rlCreate).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String obj = editText.getText().toString();
                if (obj.trim().length() > 0) {
                    File file = new File(f4880g + "/" + (Character.toString(obj.charAt(0)).toUpperCase() + obj.substring(1)));
                    if (file.exists()) {
                        C1131f.m5804a(MainActivity.mainActivity, "Error!! Directory already exists.");
                        return;
                    }
                    file.mkdirs();
                    dialog.dismiss();
                    f4882i.add(new FileModel(file.getAbsolutePath(), file.getName()));
                    f4877d.setVisibility(View.GONE);
                    f4881h.setVisibility(View.GONE);
                    folderAdapterPhotos.notifyDataSetChanged();
                    return;
                }
                C1131f.m5804a(MainActivity.mainActivity, "Please Enter Folder Name");
            }
        });
        inflate.findViewById(R.id.rlCancel).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(inflate);
        dialog.show();
    }

    private void m7427P() {
        C3150b.m14832a(MainActivity.mainActivity, new C17175(this), true);
    }

    private void m7428Q() {
        if (this.f4882i.size() > 0) {
            this.f4877d.setVisibility(View.GONE);
            this.f4881h.setVisibility(View.GONE);
            return;
        }
        this.f4877d.setVisibility(View.VISIBLE);
        this.f4877d.setText(getActivity().getString(R.string.add_folder_string));
        this.f4881h.setVisibility(View.VISIBLE);
        this.f4881h.setOnClickListener(this);
    }

    private void m7431a(int i) {
        final Dialog dialog = new Dialog(getActivity());
        View inflate = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_dialog, null);
        TextView textView = (TextView) inflate.findViewById(R.id.textView1);
        textView.setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvCancel)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvRename)).setTypeface(C1131f.f3315a);
        textView.setText("Rename");
        inflate.findViewById(R.id.rlRename).setVisibility(View.VISIBLE);
        inflate.findViewById(R.id.rlCreate).setVisibility(View.GONE);
        View findViewById = inflate.findViewById(R.id.rlCancel);
        final FileModel fileModel = (FileModel) this.f4882i.get(i);
        final File file = new File(fileModel.fileAddress);
        final EditText editText = (EditText) inflate.findViewById(R.id.editText1);
        editText.setText(file.getName());
        editText.setTypeface(C1131f.f3315a);
        editText.setSelection(editText.getText().length());
        dialog.getWindow().setSoftInputMode(4);
        inflate.findViewById(R.id.rlRename).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String obj = editText.getText().toString();
                if (obj.length() > 0) {
                    File file2 = new File(file.getParentFile().getAbsolutePath() + "/" + obj);
                    if (file2.exists()) {
                        C1131f.m5804a(getActivity(), "same name already exists. try another name");
                        return;
                    }
                    file2.renameTo(file2);
                    file2.delete();
                    fileModel.fileName = obj;
                    fileModel.fileAddress = file2.getAbsolutePath();
                    dialog.dismiss();
                    folderAdapterPhotos.notifyDataSetChanged();
                    return;
                }
                C1131f.m5804a(MainActivity.mainActivity, "Enter Some name first.");
            }
        });
        findViewById.setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(inflate);
        dialog.show();
    }

    private ArrayList<FileModel> m7436c(String str) {
        ArrayList arrayList = new ArrayList();
        for (File file : new File(str).listFiles()) {
            FileModel fileModel = new FileModel(file.getAbsolutePath(), file.getName());
            fileModel.f4566e = file.lastModified();
            arrayList.add(fileModel);
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private void m7437c(final int i) {
        String str;
        ArrayList arrayList;
        ArrayList arrayList2;
        File[] a;
        String replace;
        final Dialog dialog;
        Dialog dialog1;
        View inflate;
        ListView listView;
        String str2 = "";
        File file = new File(((FileModel) this.f4882i.get(i)).fileAddress);
        HashSet hashSet = new HashSet();
        String[] list = file.list();
        if (list != null && list.length > 0) {
            for (String b : list) {
                hashSet.add(this.f4879f.m14131b(b));
            }
            if (hashSet.size() == 1) {
                str = (String) hashSet.iterator().next();
                arrayList = new ArrayList();
                arrayList2 = new ArrayList();
                arrayList.add("GalleryLock folder (Phone memory)");
                arrayList2.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GalleryLock");
                a = ContextCompat.getExternalFilesDirs(getActivity().getApplicationContext(), "GalleryLock");
                if (a != null && a.length > 1) {
                    if (C1131f.f3325k) {
                        replace = new File(a[1], "").getAbsolutePath().replace("/Android/data/" + this.aq.getPackageName() + "/files", "");
                        if (replace.length() > 2 && !replace.contains(this.aq.getPackageName())) {
                            arrayList.add("GalleryLock folder (external sdcard)");
                            arrayList2.add(replace);
                        }
                    } else {
                        replace = new File(a[1], "").getAbsolutePath();
                        if (replace.length() > 2) {
                            arrayList.add("GalleryLock folder (external sdcard)");
                            arrayList2.add(replace);
                        }
                    }
                }
                if (C1131f.f3325k) {
                    if (str.length() > 5) {
                        arrayList.add(0, "Original Path");
                        arrayList2.add(0, str);
                    }
                } else if (str.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath()) && str.length() > 5) {
                    arrayList.add(0, "Original Path");
                    arrayList2.add(0, str);
                }
                dialog1 = new Dialog(getActivity());
                inflate = getActivity().getLayoutInflater().inflate(R.layout.dialog_restore_chooser, null);
                listView = (ListView) inflate.findViewById(R.id.lvDirs);
                listView.setAdapter(new C1600k(arrayList, arrayList2, getActivity().getApplicationContext()));
                final Dialog finalDialog = dialog1;
                final ArrayList finalArrayList = arrayList2;
                listView.setOnItemClickListener(new OnItemClickListener() {

                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        finalDialog.dismiss();
                        C1131f.f3320f = (String) finalArrayList.get(i);
                        if (C1131f.f3320f == null) {
                            C1131f.f3320f = Environment.getExternalStorageDirectory() + "/GalleryLock";
                        }
                        File file = new File(C1131f.f3320f);
                        boolean startsWith = C1131f.f3320f.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath());
                        boolean z = !startsWith && C1131f.f3323i;
                        C1735d c1735d;
                        if (C1131f.f3325k) {
                            if (startsWith) {
                                finalDialog.dismiss();
                                if (!file.exists()) {
                                    file.mkdirs();
                                }
                                c1735d = C1735d.this;
                                c1735d.ap++;
                                if (ap >= ao.size()) {
                                    ap = 0;
                                }
                                am.putInt("adCount", ap);
                                am.commit();
                                new C1733a(C1735d.this, (FileModel) f4882i.get(i), z, null, ao.size() > 0 ? (C1625q) ao.get(ap) : null).execute(new Void[0]);
                            } else if (ar) {
                                finalDialog.dismiss();
                                ar = false;
                                if (!file.exists()) {
                                    file.mkdirs();
                                }

                                ap++;
                                if (ap >= ao.size()) {
                                    ap = 0;
                                }
                                am.putInt("adCount", ap);
                                am.commit();
                                new C1733a(C1735d.this, (FileModel) f4882i.get(i), z, null, ao.size() > 0 ? (C1625q) ao.get(ap) : null).execute(new Void[0]);
                            } else {
                                ar = true;
                                C3150b.m14836b(MainActivity.mainActivity);
                            }
                        } else if (z) {
                            finalDialog.dismiss();
                            String string = al.getString("treeUri", null);
                            if (string != null) {
                                DocumentFile b = C1132g.m5816b(aq, file, Uri.parse(string));
                                ap++;
                                if (ap >= ao.size()) {
                                    ap = 0;
                                }
                                am.putInt("adCount", ap);
                                am.commit();
                                new C1733a(C1735d.this, (FileModel) f4882i.get(i), z, b, ao.size() > 0 ? (C1625q) ao.get(ap) : null).execute(new Void[0]);
                                return;
                            }
                            aj = (FileModel) f4882i.get(i);
                            m7427P();
                        } else {
                            finalDialog.dismiss();
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            ap++;
                            if (ap >= ao.size()) {
                                ap = 0;
                            }
                            am.putInt("adCount", ap);
                            am.commit();
                            new C1733a(C1735d.this, (FileModel) f4882i.get(i), z, null, ao.size() > 0 ? (C1625q) ao.get(ap) : null).execute(new Void[0]);
                        }
                    }
                });
                dialog1.setContentView(inflate);
                dialog1.show();
            }
        }
        str = str2;
        arrayList = new ArrayList();
        arrayList2 = new ArrayList();
        arrayList.add("GalleryLock folder (Phone memory)");
        arrayList2.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GalleryLock");
        a = ContextCompat.getExternalFilesDirs(getActivity().getApplicationContext(), "GalleryLock");
        if (C1131f.f3325k) {
            replace = new File(a[1], "").getAbsolutePath().replace("/Android/data/" + this.aq.getPackageName() + "/files", "");
            arrayList.add("GalleryLock folder (external sdcard)");
            arrayList2.add(replace);
        } else {
            replace = new File(a[1], "").getAbsolutePath();
            if (replace.length() > 2) {
                arrayList.add("GalleryLock folder (external sdcard)");
                arrayList2.add(replace);
            }
        }
        if (C1131f.f3325k) {
            arrayList.add(0, "Original Path");
            arrayList2.add(0, str);
        } else if (str.length() > 5) {
            arrayList.add(0, "Original Path");
            arrayList2.add(0, str);
        }
        dialog1 = new Dialog(getActivity());
        dialog = dialog1;
        inflate = getActivity().getLayoutInflater().inflate(R.layout.dialog_restore_chooser, null);
        listView = (ListView) inflate.findViewById(R.id.lvDirs);
        listView.setAdapter(new C1600k(arrayList, arrayList2, getActivity().getApplicationContext()));
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        dialog.setContentView(inflate);
        dialog.show();
    }

    private void m7440d(final int i) {
        final Dialog dialog = new Dialog(getActivity());
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.delete_dialog, null);
        dialog.setContentView(inflate);
        inflate.findViewById(R.id.rlDelete).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                new C1734b(C1735d.this, (FileModel) f4882i.get(i)).execute(new Void[0]);
                dialog.dismiss();
            }
        });
        inflate.findViewById(R.id.rlCancel).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public View mo1153a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.rootView;
    }

    public void m7444a() {
        if (this.f4882i != null && !this.f4882i.isEmpty()) {
            Iterator it = new ArrayList(this.f4882i).iterator();
            Object obj = null;
            while (it.hasNext()) {
                Object obj2;
                FileModel fileModel = (FileModel) it.next();
                if (fileModel.f4564c) {
                    this.f4882i.remove(fileModel);
                    obj2 = 1;
                } else {
                    obj2 = obj;
                }
                obj = obj2;
            }
            if (obj != null) {
                this.folderAdapterPhotos.notifyDataSetChanged();
            }
        }
    }


    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 142) {
            if (i2 != -1 || intent == null) {
                Toast.makeText(this.aq, "Grant Failed. Please choose the root directory of SD card", Toast.LENGTH_SHORT).show();
                m7427P();
            } else {
                Uri data = intent.getData();
                if (C1132g.m5814a(data)) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        this.aq.getContentResolver().takePersistableUriPermission(data, intent.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    this.am.putString("treeUri", "" + data);
                    this.am.putString("extSdCardPath", C1131f.f3326l);
                    this.ap++;
                    if (this.ap >= this.ao.size()) {
                        this.ap = 0;
                    }
                    this.am.putInt("adCount", this.ap);
                    this.am.commit();
                    new C1733a(this, this.aj, true, null, this.ao.size() > 0 ? (C1625q) this.ao.get(this.ap) : null).execute(new Void[0]);
                } else {
                    Toast.makeText(this.aq, "Grant Failed. Please choose the root directory of SD card", Toast.LENGTH_SHORT).show();
                    m7427P();
                }
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f4874a = this;
        this.aq = getActivity().getApplicationContext();
        this.al = PreferenceManager.getDefaultSharedPreferences(this.aq);
        this.am = this.al.edit();
        this.as = this.al.getBoolean("hideAd", false);
        this.ai = getArguments().getBoolean("fromFake", false);
        this.f4879f = new C2930c(this.aq);
        if (!this.as) {
            this.an = new C2929b(this.aq);
            this.ao = this.an.m14124a(this.aq.getPackageManager(), false);
        }
        if (C1131f.f3318d == null || C1131f.f3318d.length() < 5) {
            if (this.ai) {
                C1131f.f3318d = this.aq.getFilesDir() + "/lockerVault/FVault";
            } else {
                C1131f.f3318d = this.aq.getFilesDir() + "/lockerVault";
            }
        }
        this.f4880g = C1131f.f3318d + "/Videos1769";
        File file = new File(this.f4880g);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.rootView = LayoutInflater.from(getActivity()).inflate(R.layout.layout, null);
        TextView textView = (TextView) this.rootView.findViewById(R.id.textView1);
        textView.setText("Videos");
        textView.setTypeface(C1131f.f3315a);
        this.f4881h = (ImageButton) this.rootView.findViewById(R.id.btnAddFolder1);
        this.f4877d = (TextView) this.rootView.findViewById(R.id.tvLoading);
        this.recyclerView = (RecyclerView) this.rootView.findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this.aq, 2));
        int a = C3044b.m14508a(this.aq, 5.0f);
        int a2 = C3044b.m14508a(this.aq, 3.0f);
        this.recyclerView.addItemDecoration(new C1127d(a, a2, a, a2));
        this.rootView.findViewById(R.id.rlAddFolder).setOnClickListener(this);
        if (this.ai) {
            this.rootView.findViewById(R.id.rlBrowser).setVisibility(View.GONE);
        } else {
            this.rootView.findViewById(R.id.rlBrowser).setOnClickListener(this);
        }
        m7425N();
        this.rootView.findViewById(R.id.rlInfo).setOnClickListener(new C17131(this));
        this.rootView.findViewById(R.id.rlMore).setOnClickListener(new C17196(this));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return rootView;

    }

    public void onItemClicked(View view, final int i) {
        Animation loadAnimation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.scale_item);
        loadAnimation.setAnimationListener(new AnimationListener() {

            public void onAnimationEnd(Animation animation) {
                String str = ((FileModel) f4882i.get(i)).fileAddress;
                Intent intent = new Intent(aq, ViewAlbumActivity.class);
                intent.putExtra("path", str);
                intent.putExtra("fromFake", ai);
                startActivity(intent);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        view.startAnimation(loadAnimation);
    }

    public void m7448a(boolean z) {
        C1131f.f3324j = z;
        if (this.folderAdapterPhotos != null) {
            this.folderAdapterPhotos.m7202a(C1131f.f3324j);
        }
    }

//    protected Intent m7449b(Context context) {
//        try {
//            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
//            return new Intent("android.intent.action.VIEW", Uri.parse("fb://page/" + getActivity().getResources().getString(R.string.fbPageId)));
//        } catch (Exception e) {
//            return new Intent("android.intent.action.VIEW", Uri.parse("https://www.facebook.com/" + getActivity().getResources().getString(R.string.fbPageId)));
//        }
//    }

    public void mo974b(final int i) {
        this.ak = ((FileModel) this.f4882i.get(i)).fileName;
        String[] strArr = new String[]{"Delete", "Unhide and Restore", "Rename"};
        final Dialog dialog = new Dialog(getActivity());
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.dialog_restore_chooser, null);
        ((TextView) inflate.findViewById(R.id.textView1)).setText("Select Action");
        ListView listView = (ListView) inflate.findViewById(R.id.lvDirs);
        listView.setAdapter(new ArrayAdapter(this.aq, R.layout.list_item_layout, R.id.textView1, strArr));
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                dialog.dismiss();
                switch (i) {
                    case 0:
                        m7440d(i);
                        return;
                    case 1:
                        m7437c(i);
                        return;
                    case 2:
                        m7431a(i);
                        return;
                    default:
                        return;
                }
            }
        });
        dialog.setContentView(inflate);
        dialog.show();
    }

    public void m7451b(String str) {
        this.f4882i.add(new FileModel(str, new File(str).getName()));
        this.folderAdapterPhotos.notifyDataSetChanged();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddFolder1:
                m7426O();
                return;
            case R.id.rlBrowser:
                getActivity().startActivity(new Intent(this.aq, MainBrowserActivity.class));
                return;
            case R.id.rlAddFolder:
                m7426O();
                return;
            default:
                return;
        }
    }

    public void onStart() {
        if (this.folderAdapterPhotos != null) {
            this.folderAdapterPhotos.notifyDataSetChanged();
        }
        super.onStart();
    }
}
