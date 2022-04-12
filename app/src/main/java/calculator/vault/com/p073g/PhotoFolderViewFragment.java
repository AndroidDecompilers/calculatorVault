package calculator.vault.com.p073g;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.preference.PreferenceManager;
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
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.lock.C1132g;
import calculator.vault.com.lock.MainActivity;
import calculator.vault.com.lock.ViewAlbumActivity;
import calculator.vault.com.p029h.ItemClickInterface;
import calculator.vault.com.p029h.C1098e;
import calculator.vault.com.p029h.C2927f;
import calculator.vault.com.p068a.C1600k;
import calculator.vault.com.p068a.C1625q;
import calculator.vault.com.p068a.FileModel;
import calculator.vault.com.p068a.FolderAdapterPhotos;
import calculator.vault.com.p084i.C2929b;
import calculator.vault.com.p084i.C2930c;
import calculator.vault.com.safebrowser.MainBrowserActivity;

@TargetApi(21)
public class PhotoFolderViewFragment extends Fragment implements OnClickListener, ItemClickInterface, C1098e {


    public static PhotoFolderViewFragment f4765e;
    TextView f4766a;
    ArrayList<C1625q> ai = new ArrayList();
    int aj = -1;
    SharedPreferences sharedPreferences;
    Editor editor;
    FileModel fileModel;
    String an;
    String ao;
    private Context context;
    private boolean aq;
    private boolean ar;
    private ArrayList<FileModel> fileModels = new ArrayList();
    View f4767b;
    C2930c f4768c;
    ImageButton f4769d;
    boolean fromFake;
    RecyclerView recyclerView;
    FolderAdapterPhotos folderAdapterPhotos;
    C2929b f4773i;


    class C16815 implements C3150b.C1055c {
        C16815(PhotoFolderViewFragment photoFolderViewFragment) {

        }

        public void mo966a(boolean z) {
            if (z) {
                MainActivity.mainActivity.f2891h = true;
                startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), 142);
                return;
            }
            Toast.makeText(context, "Choose Internal Storage to restore", Toast.LENGTH_LONG).show();
            m7368c(fileModels.indexOf(fileModel));
        }
    }

    class C16836 implements OnClickListener {

        public void onClick(View view) {
            final PopupWindow popupWindow = new PopupWindow(getActivity());
            View inflate = getActivity().getLayoutInflater().inflate(R.layout.pop_menu_item, null);
            ((TextView) inflate.findViewById(R.id.textView1)).setTypeface(C1131f.f3315a);
            ((TextView) inflate.findViewById(R.id.textView2)).setTypeface(C1131f.f3315a);
            ((TextView) inflate.findViewById(R.id.textView3)).setTypeface(C1131f.f3315a);
            OnClickListener c16821 = new OnClickListener() {
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
                            C1131f.sendEmail(getActivity());
                            return;
                        case R.id.itemShare:
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.SEND");
                            intent.setType("text/plain");
                            intent.putExtra("android.intent.extra.TEXT", getString(R.string.shareString));
                            getActivity().startActivity(Intent.createChooser(intent, "Share via"));
                            return;
//                        case R.id.itemFollow:
//                            startActivity(m7381b(context));
//                            return;
                        default:
                            return;
                    }
                }
            };
            inflate.findViewById(R.id.item_Rate).setOnClickListener(c16821);
            inflate.findViewById(R.id.itemContact).setOnClickListener(c16821);
            inflate.findViewById(R.id.itemShare).setOnClickListener(c16821);
            popupWindow.setContentView(inflate);
            popupWindow.setHeight(-2);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
            popupWindow.setOutsideTouchable(true);
            popupWindow.setFocusable(true);
            popupWindow.setWidth((int) SecurityHelpers.m14847a(160.0f, getActivity()));
            popupWindow.showAsDropDown(view, 10, -110);
        }
    }

    class C16847 extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ PhotoFolderViewFragment f4725a;

        C16847(PhotoFolderViewFragment photoFolderViewFragment) {
            this.f4725a = photoFolderViewFragment;
        }

        protected Void doInBackground(Void... voidArr) {
            this.f4725a.fileModels = this.f4725a.m7367c(this.f4725a.an);
            if (!this.f4725a.ar) {
                try {
                    ArrayList a = this.f4725a.f4773i.m14124a(this.f4725a.context.getPackageManager(), true);
                    if (this.f4725a.fileModels.size() >= 3 && a.size() > 0) {
                        int i = 0;
                        for (int i2 = 3; i2 <= this.f4725a.fileModels.size(); i2 += 3) {
                            this.f4725a.fileModels.add(i2, new FileModel((C1625q) a.get(i)));
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
            this.f4725a.folderAdapterPhotos = new FolderAdapterPhotos(this.f4725a.context, this.f4725a.fileModels, C1131f.f3324j, false);
            this.f4725a.folderAdapterPhotos.m7200a(this.f4725a);
            this.f4725a.folderAdapterPhotos.m7201a(PhotoFolderViewFragment.this);
            this.f4725a.recyclerView.setVisibility(View.VISIBLE);
            this.f4725a.recyclerView.setAdapter(this.f4725a.folderAdapterPhotos);
            this.f4725a.m7356P();
            super.onPostExecute(voidR);
        }
    }

    class C1697a extends AsyncTask<Void, Integer, C2927f> {
        Dialog f4743a;
        FileModel f4744b;
        ProgressBar f4745c;
        TextView f4746d;
        TextView f4747e;
        int f4748f;
        int f4749g = 0;
        DocumentFile f4750h;
        boolean f4751i;
        boolean f4752j;
        DocumentFile f4753k;
        C1625q f4754l;
        View f4755m;
        Button f4756n;
        TextView f4757o;
        int f4758p;
        int f4759q = -1;
        OnClickListener f4760r = new C16966(this);
        final /* synthetic */ PhotoFolderViewFragment f4761s;

        class C16871 implements OnClickListener {
            final /* synthetic */ C1697a f4731a;

            C16871(C1697a c1697a) {
                this.f4731a = c1697a;
            }

            public void onClick(View view) {
                this.f4731a.f4752j = true;
            }
        }

        class C16933 implements Runnable {
            final /* synthetic */ C1697a f4739a;

            C16933(C1697a c1697a) {
                this.f4739a = c1697a;
            }

            public void run() {
                this.f4739a.f4746d.setText("1/" + this.f4739a.f4748f);
            }
        }

        class C16944 implements Runnable {
            final /* synthetic */ C1697a f4740a;

            C16944(C1697a c1697a) {
                this.f4740a = c1697a;
            }

            public void run() {
                this.f4740a.f4746d.setText(this.f4740a.f4749g + "/" + this.f4740a.f4748f);
            }
        }

        class C16955 implements OnClickListener {
            final /* synthetic */ C1697a f4741a;

            C16955(C1697a c1697a) {
                this.f4741a = c1697a;
            }

            public void onClick(View view) {
                if (this.f4741a.f4743a != null && this.f4741a.f4743a.isShowing()) {
                    this.f4741a.f4743a.dismiss();
                }
                this.f4741a.f4752j = false;
            }
        }

        class C16966 implements OnClickListener {
            final /* synthetic */ C1697a f4742a;

            C16966(C1697a c1697a) {
                this.f4742a = c1697a;
            }

            public void onClick(View view) {
                C1131f.m5809b(MainActivity.mainActivity, this.f4742a.f4754l.f4625b);
            }
        }

        public C1697a(PhotoFolderViewFragment photoFolderViewFragment, FileModel fileModel, boolean z, DocumentFile c0318a, C1625q c1625q) {
            this.f4761s = photoFolderViewFragment;
            this.f4744b = fileModel;
            this.f4751i = z;
            this.f4750h = c0318a;
            this.f4754l = c1625q;
        }

        private C2927f m7346a(File file) {
            for (File file2 : file.listFiles()) {
                if (this.f4752j) {
                    return C2927f.CANCELLED;
                }
                C2927f a;
                if (file2.isDirectory()) {
                    a = m7346a(file2);
                    if (a == C2927f.FAILED || a == C2927f.CANCELLED) {
                        return a;
                    }
                    file2.delete();
                } else {
                    this.f4759q = -1;
                    a = m7347b(file2);
                    if (a == C2927f.FAILED || a == C2927f.CANCELLED) {
                        return a;
                    }
                    FileModel fileModel = this.f4744b;
                    fileModel.f4567f--;
                }
            }
            return C2927f.SUCCESS;
        }

        private C2927f m7347b(File file) {
            String absolutePath = file.getAbsolutePath();
            this.f4749g++;
            absolutePath = absolutePath.replace(this.f4761s.an + "/" + this.f4761s.ao, C1131f.f3320f);
            if (absolutePath.contains("null")) {
                absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + file.getName();
            }
            MainActivity.mainActivity.runOnUiThread(new C16944(this));
            File file2 = new File(absolutePath);
            try {
                OutputStream openOutputStream;
                if (this.f4751i) {
                    this.f4753k = C1132g.m5812a(file2, false, this.f4761s.context);
                    openOutputStream = this.f4761s.context.getContentResolver().openOutputStream(this.f4753k.getUri());
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
                } while (!this.f4752j);
                openOutputStream.flush();
                openOutputStream.close();
                fileInputStream.close();
                if (this.f4751i) {
                    this.f4753k.delete();
                } else {
                    file2.delete();
                }
                if (this.f4752j) {
                    return C2927f.CANCELLED;
                }
                openOutputStream.flush();
                openOutputStream.close();
                fileInputStream.close();
                if (file.delete()) {
                    this.f4761s.f4768c.m14129a(file.getName());
                    C1131f.m5805a(this.f4761s.context, file2, "image/*");
                } else if (FileUtils.deleteQuietly(file)) {
                    this.f4761s.f4768c.m14129a(file.getName());
                    C1131f.m5805a(this.f4761s.context, file2, "image/*");
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
            File file = new File(this.f4744b.fileAddress);
            C2927f a;
            if (file.isDirectory()) {
                this.f4748f = file.list().length;
                if (this.f4748f <= 0) {
                    return C2927f.FAILED;
                }
                this.f4761s.getActivity().runOnUiThread(new C16933(this));
                a = m7346a(file);
                if (a == C2927f.FAILED || a == C2927f.CANCELLED) {
                    return a;
                }
                file.delete();
                this.f4758p = this.f4761s.fileModels.indexOf(this.f4744b);
                this.f4761s.fileModels.remove(this.f4744b);
            } else {
                a = m7347b(file);
                if (a == C2927f.FAILED || a == C2927f.CANCELLED) {
                    return a;
                }
                this.f4761s.f4768c.m14129a(file.getName());
                this.f4758p = this.f4761s.fileModels.indexOf(this.f4744b);
                this.f4761s.fileModels.remove(this.f4744b);
            }
            return C2927f.SUCCESS;
        }

        protected void onPostExecute(C2927f c2927f) {
            C1131f.f3320f = Environment.getExternalStorageDirectory() + "/GalleryLock";
            if (c2927f == C2927f.SUCCESS) {
                this.f4745c.setProgress(100);
                this.f4747e.setText("100%");
                this.f4757o.setText("Pictures were moved and restored to public gallery.");
                this.f4756n.setText("DONE");
                this.f4756n.setOnClickListener(new C16955(this));
                this.f4761s.folderAdapterPhotos.notifyItemRemoved(this.f4758p);
                this.f4761s.m7356P();
            } else if (c2927f == C2927f.CANCELLED) {
                Toast.makeText(this.f4761s.getActivity(), "Operation Cancelled.", Toast.LENGTH_LONG).show();
                this.f4761s.folderAdapterPhotos.notifyDataSetChanged();
                if (this.f4743a != null && this.f4743a.isShowing()) {
                    this.f4743a.dismiss();
                }
            } else {
                Toast.makeText(this.f4761s.getActivity(), "Empty Folder! nothing to restore", Toast.LENGTH_LONG).show();
                if (this.f4743a != null && this.f4743a.isShowing()) {
                    this.f4743a.dismiss();
                }
            }
            super.onPostExecute(c2927f);
        }

        protected void onProgressUpdate(Integer... numArr) {
            int intValue = numArr[0].intValue();
            if (intValue - this.f4759q > 0) {
                this.f4745c.setProgress(intValue);
                this.f4747e.setText(intValue + "%");
                this.f4759q = intValue;
            }
            super.onProgressUpdate(numArr);
        }


        protected void onPreExecute() {
            this.f4743a = new ProgressDialog(this.f4761s.getActivity());
            final View inflate = this.f4761s.getActivity().getLayoutInflater().inflate(R.layout.progress_dialog, null);
            this.f4743a.show();
            this.f4743a.setContentView(inflate);
            this.f4743a.setCancelable(false);
            this.f4745c = (ProgressBar) inflate.findViewById(R.id.progressBar1);
            this.f4757o = (TextView) inflate.findViewById(R.id.tvTitle);
            this.f4757o.setTypeface(C1131f.f3315a);
            this.f4757o.setText("Please wait..Unhiding picture(s)");
            this.f4746d = (TextView) inflate.findViewById(R.id.tvCount);
            this.f4747e = (TextView) inflate.findViewById(R.id.tvProgress);
            this.f4756n = (Button) inflate.findViewById(R.id.btnCancel);
            this.f4756n.setOnClickListener(new C16871(this));
            if (this.f4754l != null) {
                this.f4755m = inflate.findViewById(R.id.llAd);
                new Handler().postDelayed(new Runnable() {

                    class C16912 implements OnClickListener {

                        class C16901 implements AnimationListener {
                            final /* synthetic */ C16912 f4735a;

                            C16901(C16912 c16912) {
                                this.f4735a = c16912;
                            }

                            public void onAnimationEnd(Animation animation) {
                                f4755m.setVisibility(View.GONE);
                            }

                            public void onAnimationRepeat(Animation animation) {
                            }

                            public void onAnimationStart(Animation animation) {
                            }
                        }


                        public void onClick(View view) {
                            Animation loadAnimation = AnimationUtils.loadAnimation(f4761s.context, R.anim.adview_shrink);
                            loadAnimation.setAnimationListener(new C16901(this));
                            f4755m.startAnimation(loadAnimation);
                        }
                    }

                    public void run() {
                        f4755m.setVisibility(View.VISIBLE);
                        f4755m.startAnimation(AnimationUtils.loadAnimation(f4761s.context, R.anim.adview_grow));
                        final ImageView imageView = (ImageView) inflate.findViewById(R.id.ivAd);
                        Glide.with(f4761s.context).load(f4754l.f4627d).listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                imageView.post(new C16881());
                                return false;
                            }


                            class C16881 implements Runnable {

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
                        }).into(imageView);
                        Glide.with(f4761s.context).load(f4754l.f4628e).into((ImageView) inflate.findViewById(R.id.ivAdIcon));
                        ((TextView) inflate.findViewById(R.id.tvAppName)).setText(f4754l.f4624a);
                        ((TextView) inflate.findViewById(R.id.tvDesc)).setText(f4754l.f4626c);
                        imageView.setOnClickListener(f4760r);
                        inflate.findViewById(R.id.btnInstall).setOnClickListener(f4760r);
                        inflate.findViewById(R.id.btnCloseAd).setOnClickListener(new C16912());
                    }
                }, 1000);
            }
            super.onPreExecute();
        }

    }

    public class C1698b extends AsyncTask<Void, Void, Void> {
        ProgressDialog f4762a;
        FileModel f4763b;
        final /* synthetic */ PhotoFolderViewFragment f4764c;

        public C1698b(PhotoFolderViewFragment photoFolderViewFragment, FileModel fileModel) {
            this.f4764c = photoFolderViewFragment;
            this.f4763b = fileModel;
        }

        private void m7351a(File file) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    m7351a(file2);
                    file2.delete();
                } else {
                    file2.delete();
                    this.f4764c.f4768c.m14129a(file2.getName());
                }
            }
        }

        protected Void doInBackground(Void... voidArr) {
            File file = new File(this.f4763b.fileAddress);
            if (file.isDirectory()) {
                m7351a(file);
                file.delete();
                this.f4764c.fileModels.remove(this.f4763b);
            } else {
                file.delete();
                this.f4764c.f4768c.m14129a(file.getName());
                this.f4764c.fileModels.remove(this.f4763b);
            }
            return null;
        }

        protected void onPostExecute(Void voidR) {
            try {
                if (this.f4762a != null && this.f4762a.isShowing()) {
                    this.f4762a.dismiss();
                }
            } catch (Exception e) {
            }
            this.f4764c.m7356P();
            this.f4764c.folderAdapterPhotos.notifyDataSetChanged();
            super.onPostExecute(voidR);
        }

        protected void onPreExecute() {
            this.f4762a = new ProgressDialog(this.f4764c.getActivity());
            this.f4762a.setTitle("Please wait...");
            this.f4762a.setMessage("It takes a while, depending on file size");
            this.f4762a.setCancelable(false);
            this.f4762a.setProgressStyle(1);
            this.f4762a.show();
            super.onPreExecute();
        }
    }

    private void m7354N() {
        new C16847(this).execute(new Void[0]);
    }

    private void m7355O() {
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
                    File file = new File(an + "/" + (Character.toString(obj.charAt(0)).toUpperCase() + obj.substring(1)));
                    if (file.exists()) {
                        C1131f.m5804a(MainActivity.mainActivity, "Error!! Directory already exists.");
                        return;
                    }
                    file.mkdirs();
                    dialog.dismiss();
                    fileModels.add(new FileModel(file.getAbsolutePath(), file.getName()));
                    f4766a.setVisibility(View.GONE);
                    f4769d.setVisibility(View.GONE);
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

    private void m7356P() {
        if (this.fileModels.size() > 0) {
            this.f4766a.setVisibility(View.GONE);
            this.f4769d.setVisibility(View.GONE);
            return;
        }
        this.f4766a.setVisibility(View.VISIBLE);
        this.f4766a.setText(getString(R.string.add_folder_string));
        this.f4769d.setVisibility(View.VISIBLE);
        this.f4769d.setOnClickListener(this);
    }

    private void m7357Q() {
        C3150b.m14832a(MainActivity.mainActivity, new C16815(this), true);
    }

    private void m7361a(int i) {
        final Dialog dialog = new Dialog(getActivity());
        View inflate = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_dialog, null);
        TextView textView = (TextView) inflate.findViewById(R.id.textView1);
        textView.setTypeface(C1131f.f3315a);
        textView.setText("Rename");
        inflate.findViewById(R.id.rlRename).setVisibility(View.VISIBLE);
        inflate.findViewById(R.id.rlCreate).setVisibility(View.GONE);
        View findViewById = inflate.findViewById(R.id.rlCancel);
        final FileModel fileModel = (FileModel) this.fileModels.get(i);
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

    private ArrayList<FileModel> m7367c(String str) {
        ArrayList arrayList = new ArrayList();
        for (File file : new File(str).listFiles()) {
            FileModel fileModel = new FileModel(file.getAbsolutePath(), file.getName());
            fileModel.f4566e = file.lastModified();
            arrayList.add(fileModel);
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private void m7368c(final int i) {
        String str;
        ArrayList arrayList;
        final ArrayList arrayList2;
        ArrayList arrayList1;
        File[] a;
        String replace;
        final Dialog dialog;
        Dialog dialog1;
        View inflate;
        ListView listView;
        String str2 = "";
        File file = new File(((FileModel) this.fileModels.get(i)).fileAddress);
        HashSet hashSet = new HashSet();
        String[] list = file.list();
        if (list != null && list.length > 0) {
            for (String b : list) {
                hashSet.add(this.f4768c.m14131b(b));
            }
            if (hashSet.size() == 1) {
                str = (String) hashSet.iterator().next();
                arrayList = new ArrayList();
                arrayList1 = new ArrayList();
                arrayList.add("GalleryLock folder (Phone memory)");
                arrayList1.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GalleryLock");
                a = ContextCompat.getExternalFilesDirs(getActivity().getApplicationContext(), "GalleryLock");
                if (a != null && a.length > 1) {
                    if (C1131f.f3325k) {
                        replace = new File(a[1], "").getAbsolutePath().replace("/Android/data/" + this.context.getPackageName() + "/files", "");
                        if (replace.length() > 2 && !replace.contains(this.context.getPackageName())) {
                            arrayList.add("GalleryLock folder (external sdcard)");
                            arrayList1.add(replace);
                        }
                    } else {
                        replace = new File(a[1], "").getAbsolutePath();
                        if (replace.length() > 2) {
                            arrayList.add("GalleryLock folder (external sdcard)");
                            arrayList1.add(replace);
                        }
                    }
                }
                if (C1131f.f3325k) {
                    if (str.length() > 5) {
                        arrayList.add(0, "Original Path");
                        arrayList1.add(0, str);
                    }
                } else if (str.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath()) && str.length() > 5) {
                    arrayList.add(0, "Original Path");
                    arrayList1.add(0, str);
                }
                dialog1 = new Dialog(getActivity());
                inflate = getActivity().getLayoutInflater().inflate(R.layout.dialog_restore_chooser, null);
                ((TextView) inflate.findViewById(R.id.textView1)).setTypeface(C1131f.f3315a);
                listView = (ListView) inflate.findViewById(R.id.lvDirs);
                listView.setAdapter(new C1600k(arrayList, arrayList1, getActivity().getApplicationContext()));
                final Dialog finalDialog = dialog1;
                final ArrayList finalArrayList = arrayList1;
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
                        PhotoFolderViewFragment photoFolderViewFragment;
                        if (C1131f.f3325k) {
                            if (startsWith) {
                                finalDialog.dismiss();
                                if (!file.exists()) {
                                    file.mkdirs();
                                }

                                aj++;
                                if (aj >= ai.size()) {
                                    aj = 0;
                                }
                                editor.putInt("adCount", aj);
                                editor.commit();
                                new C1697a(PhotoFolderViewFragment.this, (FileModel) fileModels.get(i), z, null, ai.size() > 0 ? (C1625q) ai.get(aj) : null).execute(new Void[0]);
                            } else if (aq) {
                                finalDialog.dismiss();
                                aq = false;
                                if (!file.exists()) {
                                    file.mkdirs();
                                }
                                aj++;
                                if (aj >= ai.size()) {
                                    aj = 0;
                                }
                                editor.putInt("adCount", aj);
                                editor.commit();
                                new C1697a(PhotoFolderViewFragment.this, (FileModel) fileModels.get(i), z, null, ai.size() > 0 ? (C1625q) ai.get(aj) : null).execute(new Void[0]);
                            } else {
                                aq = true;
                                C3150b.m14836b(MainActivity.mainActivity);
                            }
                        } else if (z) {
                            finalDialog.dismiss();
                            String string = sharedPreferences.getString("treeUri", null);
                            if (string != null) {
                                DocumentFile b = C1132g.m5816b(context, file, Uri.parse(string));
                                aj++;
                                if (aj >= ai.size()) {
                                    aj = 0;
                                }
                                editor.putInt("adCount", aj);
                                editor.commit();
                                new C1697a(PhotoFolderViewFragment.this, (FileModel) fileModels.get(i), z, b, ai.size() > 0 ? (C1625q) ai.get(aj) : null).execute(new Void[0]);
                                return;
                            }
                            fileModel = (FileModel) fileModels.get(i);
                            m7357Q();
                        } else {
                            finalDialog.dismiss();
                            if (!file.exists()) {
                                file.mkdirs();
                            }
                            aj++;
                            if (aj >= ai.size()) {
                                aj = 0;
                            }
                            editor.putInt("adCount", aj);
                            editor.commit();
                            new C1697a(PhotoFolderViewFragment.this, (FileModel) fileModels.get(i), z, null, ai.size() > 0 ? (C1625q) ai.get(aj) : null).execute(new Void[0]);
                        }
                    }
                });
                dialog1.setContentView(inflate);
                dialog1.show();
            }
        }
        str = str2;
        arrayList = new ArrayList();
        arrayList1 = new ArrayList();
        arrayList2 = arrayList1;
        arrayList.add("GalleryLock folder (Phone memory)");
        arrayList2.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GalleryLock");
        a = ContextCompat.getExternalFilesDirs(getActivity().getApplicationContext(), "GalleryLock");
        if (C1131f.f3325k) {
            replace = new File(a[1], "").getAbsolutePath().replace("/Android/data/" + this.context.getPackageName() + "/files", "");
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
        ((TextView) inflate.findViewById(R.id.textView1)).setTypeface(C1131f.f3315a);
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

    private void m7370d(final int i) {
        final Dialog dialog = new Dialog(MainActivity.mainActivity);
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.delete_dialog, null);
        dialog.setContentView(inflate);
        inflate.findViewById(R.id.rlDelete).setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                new C1698b(PhotoFolderViewFragment.this, (FileModel) fileModels.get(i)).execute(new Void[0]);
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


    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.f4767b;
    }

    public void m7375a() {
        if (this.fileModels != null && !this.fileModels.isEmpty()) {
            Iterator it = new ArrayList(this.fileModels).iterator();
            Object obj = null;
            while (it.hasNext()) {
                Object obj2;
                FileModel fileModel = (FileModel) it.next();
                if (fileModel.f4564c) {
                    this.fileModels.remove(fileModel);
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
                Toast.makeText(this.context, "Grant Failed. Please choose the root directory of SD card", Toast.LENGTH_SHORT).show();
                m7357Q();
            } else {
                Uri data = intent.getData();
                if (C1132g.m5814a(data)) {
                    this.context.getContentResolver().takePersistableUriPermission(data, intent.getFlags() & Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    this.editor.putString("treeUri", "" + data);
                    this.editor.putString("extSdCardPath", C1131f.f3326l);
                    this.aj++;
                    if (this.aj >= this.ai.size()) {
                        this.aj = 0;
                    }
                    this.editor.putInt("adCount", this.aj);
                    this.editor.commit();
                    new C1697a(this, this.fileModel, true, null, this.ai.size() > 0 ? (C1625q) this.ai.get(this.aj) : null).execute(new Void[0]);
                } else {
                    Toast.makeText(this.context, "Grant Failed. Please choose the root directory of SD card", Toast.LENGTH_LONG).show();
                    m7357Q();
                }
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f4765e = this;
        this.context = getActivity().getApplicationContext();
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
        this.editor = this.sharedPreferences.edit();
        this.ar = this.sharedPreferences.getBoolean("hideAd", false);
        if (!this.ar) {
            this.f4773i = new C2929b(this.context);
            this.ai = this.f4773i.m14124a(this.context.getPackageManager(), false);
        }
        this.fromFake = getArguments().getBoolean("fromFake", false);
        this.f4768c = new C2930c(this.context);
        if (C1131f.f3318d == null || C1131f.f3318d.length() < 5) {
            if (this.fromFake) {
                C1131f.f3318d = this.context.getFilesDir() + "/lockerVault/FVault";
            } else {
                C1131f.f3318d = this.context.getFilesDir() + "/lockerVault";
            }
        }
        this.an = C1131f.f3318d + "/Images1769";
        File file = new File(this.an);
        if (!file.exists()) {
            file.mkdirs();
        }
        this.f4767b = LayoutInflater.from(getActivity()).inflate(R.layout.layout, null);
        TextView textView = (TextView) this.f4767b.findViewById(R.id.textView1);
        textView.setText("Pictures");
        textView.setTypeface(C1131f.f3315a);
        this.f4769d = (ImageButton) this.f4767b.findViewById(R.id.btnAddFolder1);
        this.f4766a = (TextView) this.f4767b.findViewById(R.id.tvLoading);
        this.recyclerView = (RecyclerView) this.f4767b.findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this.context, 2));
        int a = C3044b.m14508a(this.context, 5.0f);
        int a2 = C3044b.m14508a(this.context, 3.0f);
        this.recyclerView.addItemDecoration(new calculator.vault.com.customgallery.videos.C1127d(a, a2, a, a2));
        this.f4767b.findViewById(R.id.rlAddFolder).setOnClickListener(this);
        if (this.fromFake) {
            this.f4767b.findViewById(R.id.rlBrowser).setVisibility(View.GONE);
        } else {
            this.f4767b.findViewById(R.id.rlBrowser).setOnClickListener(this);
        }
        m7354N();
        this.f4767b.findViewById(R.id.rlInfo).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                C3150b.m14829a(getActivity(), editor);
            }
        });
        this.f4767b.findViewById(R.id.rlMore).setOnClickListener(new C16836());
    }

    public void onItemClicked(View view, final int i) {
        Animation loadAnimation = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.scale_item);
        loadAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                String path = ((FileModel) fileModels.get(i)).fileAddress;
                Intent intent = new Intent(context, ViewAlbumActivity.class);
                intent.putExtra("fromFake", fromFake);
                intent.putExtra("path", path);
                startActivityForResult(intent, 11);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        view.startAnimation(loadAnimation);
    }

    public void m7379a(FileModel fileModel) {
        fileModel.f4566e = System.currentTimeMillis();
        this.fileModels.add(0, fileModel);
    }

    public void m7380a(boolean z) {
        C1131f.f3324j = z;
        if (this.folderAdapterPhotos != null) {
            this.folderAdapterPhotos.m7202a(C1131f.f3324j);
        }
    }

//    protected Intent m7381b(Context context) {
//        try {
//            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
//            return new Intent("android.intent.action.VIEW", Uri.parse("fb://page/" + getActivity().getResources().getString(R.string.fbPageId)));
//        } catch (Exception e) {
//            return new Intent("android.intent.action.VIEW", Uri.parse("https://www.facebook.com/" + getActivity().getResources().getString(R.string.fbPageId)));
//        }
//    }

    public void mo974b(final int i) {
        this.ao = ((FileModel) this.fileModels.get(i)).fileName;
        String[] strArr = new String[]{"Delete", "Unlock and Restore", "Rename"};
        final Dialog dialog = new Dialog(getActivity());
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.dialog_restore_chooser, null);
        TextView textView = (TextView) inflate.findViewById(R.id.textView1);
        textView.setText("Select Action");
        textView.setTypeface(C1131f.f3315a);
        ListView listView = (ListView) inflate.findViewById(R.id.lvDirs);
        listView.setAdapter(new ArrayAdapter(this.context, R.layout.list_item_layout, R.id.textView1, strArr));
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                dialog.dismiss();
                switch (i) {
                    case 0:
                        m7370d(i);
                        return;
                    case 1:
                        m7368c(i);
                        return;
                    case 2:
                        m7361a(i);
                        return;
                    default:
                        return;
                }
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(inflate);
        dialog.show();
    }

    public void m7383b(String str) {
        this.fileModels.add(new FileModel(str, new File(str).getName()));
        this.folderAdapterPhotos.notifyDataSetChanged();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddFolder1:
                m7355O();
                return;
            case R.id.rlBrowser:
                getActivity().startActivity(new Intent(this.context, MainBrowserActivity.class));
                return;
            case R.id.rlAddFolder:
                m7355O();
                return;
            default:
                return;
        }
    }

    public void onStart() {
        if (this.folderAdapterPhotos != null) {
            this.folderAdapterPhotos.notifyDataSetChanged();
            m7356P();
        }
        super.onStart();
    }
}
