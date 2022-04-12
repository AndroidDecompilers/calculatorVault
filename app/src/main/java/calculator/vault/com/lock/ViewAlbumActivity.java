package calculator.vault.com.lock;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.provider.DocumentFile;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
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
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import calculator.vault.com.R;
import calculator.vault.com.applock.C3150b;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.customgallery.pictures.C3044b;
import calculator.vault.com.customgallery.pictures.NewImageAlbumActivity;
import calculator.vault.com.customgallery.videos.NewVideoAlbumActivity;
import calculator.vault.com.p029h.C1044b;
import calculator.vault.com.p029h.ItemClickInterface;
import calculator.vault.com.p029h.C1098e;
import calculator.vault.com.p029h.C1099i;
import calculator.vault.com.p029h.C2927f;
import calculator.vault.com.p034a.C1201b;
import calculator.vault.com.p034a.C1213d;
import calculator.vault.com.p034a.C1223e;
import calculator.vault.com.p034a.C1230i;
import calculator.vault.com.p068a.C1600k;
import calculator.vault.com.p068a.C1611o;
import calculator.vault.com.p068a.C1625q;
import calculator.vault.com.p068a.C1639t;
import calculator.vault.com.p084i.C2930c;
import calculator.vault.com.safebrowser.MainBrowserActivity;

public class ViewAlbumActivity extends Activity implements OnClickListener, C1213d.C1096a, ItemClickInterface, C1098e, C1099i, C3150b.C1055c {

    public static ViewAlbumActivity f3158g;
    public int f3159A;
    SensorManager f3160B;
    Sensor f3161C;
    boolean f3162D;
    String f3163E;
    SharedPreferences f3164F;
    boolean f3165G;
    private String f3166H;
    private int f3168J;
    private boolean f3169K;
    private SensorEventListener f3170L = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !f3162D) {
                    f3162D = true;
                    if (f3159A == 1) {
                        C1131f.m5806a(getApplicationContext(), getPackageManager(), f3164F.getString("Package_Name", null));
                    }
                    if (f3159A == 2) {
                        f3163E = f3164F.getString("URL_Name", null);
                        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(f3163E)));
                    }
                    if (f3159A == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    };
    private final int f3171M = 280;
    TextView tvLoading;
    ArrayList<C1611o> f3173b = new ArrayList();
    int f3174c = 111;
    int f3175d = 222;
    int f3176e = 333;
    int f3177f = 444;
    FrameLayout rlHeadEdit;
    LinearLayout f3179i;
    TextView f3180j;
    boolean inOverMenu;
    boolean f3182l;
    C2930c f3183m;
    ImageButton btnAddFolder1;
    TelephonyManager f3185o;
    PowerManager f3186p;
    boolean f3187q;
    Editor f3188r;
    Window f3189s;
    boolean f3190t;
    RecyclerView f3191u;
    C1639t f3192v;
    ArrayList<C1611o> f3193w = new ArrayList();
    ArrayList<String> f3194x = new ArrayList();
    ArrayList<C1625q> f3195y = new ArrayList();
    int f3196z = -1;

    class C10731 extends TimerTask {
        final /* synthetic */ ViewAlbumActivity f3110a;

        C10731(ViewAlbumActivity viewAlbumActivity) {
            this.f3110a = viewAlbumActivity;
        }

        public void run() {
            try {
                if ((SecurityHelpers.isCallRinging(this.f3110a.f3185o) || !SecurityHelpers.m14854b(this.f3110a.getApplicationContext()).equals(this.f3110a.getPackageName())) && !this.f3110a.f3187q) {
                    MainActivity.mainActivity.finish();
                    this.f3110a.finish();
                }
                if (!SecurityHelpers.isScreenON(this.f3110a.f3186p)) {
                    MainActivity.mainActivity.finish();
                    this.f3110a.finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C10805 implements C1230i.C1075a {
        final /* synthetic */ ViewAlbumActivity f3121a;

        C10805(ViewAlbumActivity viewAlbumActivity) {
            this.f3121a = viewAlbumActivity;
        }

        public void mo969a(boolean z) {
            this.f3121a.f3194x.clear();
            if (z) {
                Toast.makeText(this.f3121a.getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this.f3121a.getApplicationContext(), this.f3121a.getResources().getString(R.string.unsusuccess_text_sdcard), Toast.LENGTH_SHORT).show();
            }
        }
    }

    class C10838 implements Runnable {
        final /* synthetic */ ViewAlbumActivity f3125a;

        C10838(ViewAlbumActivity viewAlbumActivity) {
            this.f3125a = viewAlbumActivity;
        }

        public void run() {
            this.f3125a.f3192v.notifyItemInserted(this.f3125a.f3173b.size() - 1);
            this.f3125a.f3191u.smoothScrollToPosition(this.f3125a.f3173b.size() - 1);
        }
    }

    class C10849 implements Runnable {
        final /* synthetic */ ViewAlbumActivity f3126a;

        C10849(ViewAlbumActivity viewAlbumActivity) {
            this.f3126a = viewAlbumActivity;
        }

        public void run() {
            this.f3126a.f3192v.notifyItemInserted(0);
            this.f3126a.f3191u.smoothScrollToPosition(0);
        }
    }

    class C1094a extends AsyncTask<Void, Integer, C2927f> {
        Dialog f3138a;
        ProgressBar f3139b;
        TextView f3140c;
        TextView f3141d;
        int f3142e = 1;
        int f3143f;
        ArrayList<C1611o> f3144g;
        boolean f3145h;
        boolean f3146i;
        DocumentFile f3147j;
        C1625q f3148k;
        View f3149l;
        Button f3150m;
        TextView f3151n;
        int f3152o = -1;
        OnClickListener f3153p = new C10935(this);
        final /* synthetic */ ViewAlbumActivity f3154q;

        class C10851 implements OnClickListener {
            final /* synthetic */ C1094a f3127a;

            C10851(C1094a c1094a) {
                this.f3127a = c1094a;
            }

            public void onClick(View view) {
                this.f3127a.f3146i = true;
            }
        }

        class C10913 implements Runnable {
            final /* synthetic */ C1094a f3135a;

            C10913(C1094a c1094a) {
                this.f3135a = c1094a;
            }

            public void run() {
                this.f3135a.f3140c.setText(this.f3135a.f3142e + "/" + this.f3135a.f3143f);
                C1094a c1094a = this.f3135a;
                c1094a.f3142e++;
            }
        }

        class C10924 implements OnClickListener {
            final /* synthetic */ C1094a f3136a;

            C10924(C1094a c1094a) {
                this.f3136a = c1094a;
            }

            public void onClick(View view) {
                if (this.f3136a.f3138a != null && this.f3136a.f3138a.isShowing()) {
                    this.f3136a.f3138a.dismiss();
                }
                this.f3136a.f3154q.m5766d();
                this.f3136a.f3154q.f3192v.m7295c();
                this.f3136a.f3154q.onBackPressed();
            }
        }

        class C10935 implements OnClickListener {
            final /* synthetic */ C1094a f3137a;

            C10935(C1094a c1094a) {
                this.f3137a = c1094a;
            }

            public void onClick(View view) {
                C1131f.m5809b(this.f3137a.f3154q, this.f3137a.f3148k.f4625b);
            }
        }

        public C1094a(ViewAlbumActivity viewAlbumActivity, ArrayList<C1611o> arrayList, boolean z, C1625q c1625q) {
            this.f3154q = viewAlbumActivity;
            this.f3144g = arrayList;
            this.f3145h = z;
            this.f3148k = c1625q;
        }

        private C2927f m5739a(File file) {
            String replace = file.getAbsolutePath().replace(this.f3154q.f3166H, C1131f.f3320f);
            if (replace.contains("null")) {
                replace = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + file.getName();
            }
            File file2 = new File(replace);
            try {
                OutputStream openOutputStream;
                InputStream fileInputStream = new FileInputStream(file);
                if (this.f3145h) {
                    this.f3147j = C1132g.m5812a(file2, false, this.f3154q.getApplicationContext());
                    openOutputStream = this.f3154q.getContentResolver().openOutputStream(this.f3147j.getUri());
                } else {
                    openOutputStream = new FileOutputStream(file2);
                }
                byte[] bArr = new byte[1024];
                long j = 0;
                if (fileInputStream.available() < 1) {
                    openOutputStream.close();
                    fileInputStream.close();
                    return C2927f.FAILED;
                }
                do {
                    int read = fileInputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    j += (long) read;
//                    publishProgress(new Integer[]{Integer.valueOf((int) ((100 * j) / ((long) r6)))});
                    openOutputStream.write(bArr, 0, read);
                } while (!this.f3146i);
                openOutputStream.flush();
                openOutputStream.close();
                fileInputStream.close();
                if (this.f3145h) {
                    this.f3147j.delete();
                } else {
                    file2.delete();
                }
                if (this.f3146i) {
                    return C2927f.CANCELLED;
                }
                openOutputStream.flush();
                openOutputStream.close();
                fileInputStream.close();
                if (file.delete()) {
                    this.f3154q.f3183m.m14129a(file.getName());
                    if (this.f3154q.f3182l) {
                        C1131f.m5805a(this.f3154q.getApplicationContext(), file2, "video/*");
                    } else {
                        C1131f.m5805a(this.f3154q.getApplicationContext(), file2, "image/*");
                    }
                } else if (FileUtils.deleteQuietly(file)) {
                    this.f3154q.f3183m.m14129a(file.getName());
                    if (this.f3154q.f3182l) {
                        C1131f.m5805a(this.f3154q.getApplicationContext(), file2, "video/*");
                    } else {
                        C1131f.m5805a(this.f3154q.getApplicationContext(), file2, "image/*");
                    }
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
            Iterator it = this.f3144g.iterator();
            while (it.hasNext()) {
                C1611o c1611o = (C1611o) it.next();
                File file = new File(c1611o.f4585a);
                this.f3154q.runOnUiThread(new C10913(this));
                if (this.f3146i) {
                    return C2927f.CANCELLED;
                }
                C2927f a = m5739a(file);
                if (a == C2927f.FAILED || a == C2927f.CANCELLED) {
                    return a;
                }
                this.f3154q.f3173b.remove(c1611o);
                this.f3152o = -1;
            }
            return C2927f.SUCCESS;
        }

        protected void onPostExecute(C2927f c2927f) {
            C1131f.f3320f = Environment.getExternalStorageDirectory() + "/GalleryLock";
            if (c2927f == C2927f.SUCCESS) {
                this.f3151n.setText(this.f3143f > 1 ? this.f3143f + " files were restored to public gallery." : "One file was restored to public gallery.");
                this.f3139b.setProgress(100);
                this.f3150m.setText("DONE");
                this.f3150m.setOnClickListener(new C10924(this));
            } else if (c2927f == C2927f.CANCELLED) {
                this.f3154q.f3192v.m7295c();
                this.f3154q.onBackPressed();
                Toast.makeText(this.f3154q, "Operation Cancelled", Toast.LENGTH_SHORT).show();
                if (this.f3138a != null && this.f3138a.isShowing()) {
                    this.f3138a.dismiss();
                }
            } else {
                this.f3154q.f3192v.m7295c();
                this.f3154q.onBackPressed();
                C1131f.m5804a(this.f3154q, "Exception restoring");
                if (this.f3138a != null && this.f3138a.isShowing()) {
                    this.f3138a.dismiss();
                }
            }
            super.onPostExecute(c2927f);
        }

        protected void onProgressUpdate(Integer... numArr) {
            int intValue = numArr[0].intValue();
            if (intValue - this.f3152o > 0) {
                this.f3139b.setProgress(intValue);
                this.f3141d.setText(intValue + "%");
                this.f3152o = intValue;
            }
            super.onProgressUpdate(numArr);
        }

        protected void onPreExecute() {
            this.f3138a = new ProgressDialog(this.f3154q);
            final View inflate = this.f3154q.getLayoutInflater().inflate(R.layout.progress_dialog, null);
            this.f3138a.show();
            this.f3138a.setContentView(inflate);
            this.f3138a.setCancelable(false);
            this.f3139b = (ProgressBar) inflate.findViewById(R.id.progressBar1);
            this.f3151n = (TextView) inflate.findViewById(R.id.tvTitle);
            this.f3141d = (TextView) inflate.findViewById(R.id.tvProgress);
            this.f3151n.setTypeface(C1131f.f3315a);
            this.f3151n.setText("Please wait..Unhiding file(s)");
            this.f3140c = (TextView) inflate.findViewById(R.id.tvCount);
            this.f3143f = this.f3144g.size();
            this.f3140c.setText("1/" + this.f3143f);
            this.f3140c.setTypeface(C1131f.f3315a);
            this.f3150m = (Button) inflate.findViewById(R.id.btnCancel);
            this.f3150m.setOnClickListener(new C10851(this));
            if (this.f3148k != null) {
                this.f3149l = inflate.findViewById(R.id.llAd);
                new Handler().postDelayed(new Runnable() {
                    class C10892 implements OnClickListener {
                        class C10881 implements AnimationListener {
                            final /* synthetic */ C10892 f3131a;

                            C10881(C10892 c10892) {
                                this.f3131a = c10892;
                            }

                            public void onAnimationEnd(Animation animation) {
                                f3149l.setVisibility(View.VISIBLE);
                            }

                            public void onAnimationRepeat(Animation animation) {
                            }

                            public void onAnimationStart(Animation animation) {
                            }
                        }

                        public void onClick(View view) {
                            Animation loadAnimation = AnimationUtils.loadAnimation(f3154q.getApplicationContext(), R.anim.adview_shrink);
                            loadAnimation.setAnimationListener(new C10881(this));
                            f3149l.startAnimation(loadAnimation);
                        }
                    }

                    public void run() {
                        f3149l.setVisibility(View.VISIBLE);
                        f3149l.startAnimation(AnimationUtils.loadAnimation(f3154q.getApplicationContext(), R.anim.adview_grow));
                        final ImageView imageView = (ImageView) inflate.findViewById(R.id.ivAd);
                        Glide.with(f3154q.getApplicationContext()).load(f3148k.f4627d).listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                imageView.post(new C10861());
                                return false;
                            }

                            class C10861 implements Runnable {
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
                        Glide.with(f3154q.getApplicationContext()).load(f3148k.f4628e).into((ImageView) inflate.findViewById(R.id.ivAdIcon));
                        ((TextView) inflate.findViewById(R.id.tvAppName)).setText(f3148k.f4624a);
                        ((TextView) inflate.findViewById(R.id.tvDesc)).setText(f3148k.f4626c);
                        imageView.setOnClickListener(f3153p);
                        inflate.findViewById(R.id.btnInstall).setOnClickListener(f3153p);
                        inflate.findViewById(R.id.btnCloseAd).setOnClickListener(new C10892());
                    }
                }, 1000);
            }
            super.onPreExecute();
        }
    }

    class C1095b extends AsyncTask<Void, Integer, Void> {

        ProgressDialog f3155a;
        ArrayList<C1611o> f3156b;
        final /* synthetic */ ViewAlbumActivity f3157c;

        public C1095b(ViewAlbumActivity viewAlbumActivity, ArrayList<C1611o> arrayList) {
            this.f3157c = viewAlbumActivity;
            this.f3156b = arrayList;
        }

        protected Void doInBackground(Void... voidArr) {
            Iterator it = this.f3156b.iterator();
            int i = 0;
            while (it.hasNext()) {
                C1611o c1611o = (C1611o) it.next();
                File file = new File(c1611o.f4585a);
                if (file.delete()) {
                    this.f3157c.f3173b.remove(c1611o);
                    this.f3157c.f3183m.m14129a(file.getName());
                }
                publishProgress(new Integer[]{Integer.valueOf(i + 1)});
            }
            return null;
        }

        protected void onPostExecute(Void voidR) {
            try {
                if (this.f3155a != null && this.f3155a.isShowing()) {
                    this.f3155a.dismiss();
                }
                this.f3157c.inOverMenu = false;
                this.f3157c.m5766d();
                this.f3157c.f3192v.m7295c();
                this.f3157c.rlHeadEdit.setVisibility(View.GONE);
                this.f3157c.f3179i.setVisibility(View.GONE);
            } catch (Exception e) {
            }
            super.onPostExecute(voidR);
        }

        protected void onProgressUpdate(Integer... numArr) {
            this.f3155a.setProgress(numArr[0].intValue());
            super.onProgressUpdate(numArr);
        }

        protected void onPreExecute() {
            this.f3155a = new ProgressDialog(this.f3157c);
            this.f3155a.setTitle("just a moment...");
            this.f3155a.setMessage("Sit back and relax. this may take a while, depending on file size");
            this.f3155a.setCancelable(false);
            this.f3155a.setProgressStyle(1);
            this.f3155a.show();
            this.f3155a.setMax(this.f3156b.size());
            super.onPreExecute();
        }
    }

    private void m5755a(ArrayList<C1611o> arrayList, boolean z) {
        this.f3196z++;
        if (this.f3196z >= this.f3195y.size()) {
            this.f3196z = 0;
        }
        this.f3188r.putInt("adCount", this.f3196z);
        this.f3188r.commit();
        new C1094a(this, arrayList, z, this.f3195y.size() > 0 ? (C1625q) this.f3195y.get(this.f3196z) : null).execute(new Void[0]);
    }

    @TargetApi(21)
    private void m5757b() {
        final ArrayList a = this.f3192v.m7287a();
        if (a.size() < 1) {
            C1131f.m5804a((Activity) this, "No items selected");
            return;
        }
        String str = "";
        HashSet hashSet = new HashSet();
        Iterator it = a.iterator();
        while (it.hasNext()) {
            hashSet.add(this.f3183m.m14131b(new File(((C1611o) it.next()).f4585a).getName()));
        }
        String str2 = hashSet.size() == 1 ? (String) hashSet.iterator().next() : str;
        ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        arrayList.add("GalleryLock folder (Phone memory)");
        arrayList2.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GalleryLock");
        File[] a2 = ContextCompat.getExternalFilesDirs(getApplicationContext(), "GalleryLock");
        if (a2 != null && a2.length > 1) {
            String absolutePath;
            if (C1131f.f3325k) {
                absolutePath = new File(a2[1], "").getAbsolutePath();
                if (absolutePath.length() > 2) {
                    arrayList.add("GalleryLock folder (external sdcard)");
                    arrayList2.add(absolutePath);
                }
            } else {
                absolutePath = new File(a2[1], "").getAbsolutePath().replace("/Android/data/" + getPackageName() + "/files", "");
                if (absolutePath.length() > 2 && !absolutePath.contains(getPackageName())) {
                    arrayList.add("GalleryLock folder (external sdcard)");
                    arrayList2.add(absolutePath);
                }
            }
        }
        if (C1131f.f3325k) {
            if (str2.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath()) && str2.length() > 5) {
                arrayList.add(0, "Original Path");
                arrayList2.add(0, str2);
            }
        } else if (str2.length() > 5) {
            arrayList.add(0, "Original Path");
            arrayList2.add(0, str2);
        }
        final Dialog dialog = new Dialog(f3158g);
        View inflate = f3158g.getLayoutInflater().inflate(R.layout.dialog_restore_chooser, null);
        ListView listView = (ListView) inflate.findViewById(R.id.lvDirs);
        listView.setAdapter(new C1600k(arrayList, arrayList2, getApplicationContext()));
        listView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                C1131f.f3320f = (String) arrayList2.get(i);
                if (C1131f.f3320f == null) {
                    C1131f.f3320f = Environment.getExternalStorageDirectory() + "/GalleryLock";
                }
                File file = new File(C1131f.f3320f);
                boolean startsWith = C1131f.f3320f.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath());
                boolean z = !startsWith && C1131f.f3323i;
                if (C1131f.f3325k) {
                    if (startsWith) {
                        dialog.dismiss();
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        m5755a(a, z);
                    } else if (f3169K) {
                        dialog.dismiss();
                        f3169K = false;
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        m5755a(a, z);
                    } else {
                        f3169K = true;
                        C3150b.m14836b(ViewAlbumActivity.this);
                    }
                } else if (z) {
                    dialog.dismiss();
                    if (f3164F.getString("treeUri", null) != null) {
                        m5755a(a, z);
                        return;
                    }
                    f3193w = a;
                    m5761b(true);
                } else {
                    dialog.dismiss();
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    m5755a(a, z);
                }
            }
        });
        dialog.setContentView(inflate);
        dialog.show();
    }

    private void m5760b(final ArrayList<C1611o> arrayList) {
        final Dialog dialog = new Dialog(this);
        View inflate = getLayoutInflater().inflate(R.layout.delete_dialog, null);
        ((TextView) inflate.findViewById(R.id.textView1)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tv_dialogText)).setTypeface(C1131f.f3315a);
        dialog.setContentView(inflate);
        inflate.findViewById(R.id.rlDelete).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                new C1095b(ViewAlbumActivity.this, arrayList).execute(new Void[0]);
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

    @TargetApi(21)
    private void m5761b(final boolean z) {
        C3150b.m14832a((Activity) this, new C3150b.C1055c() {

            class C10721 implements C3150b.C1055c {
                public void mo966a(boolean z) {
                    if (z) {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.delete_files_descr_manual_toast), Toast.LENGTH_LONG).show();
                    } else {
                        C3150b.m14839c(ViewAlbumActivity.this);
                    }
                }
            }

            public void mo966a(boolean z) {
                if (z) {
                    f3187q = true;
                    startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), !z ? 42 : 142);
                } else if (z) {
                    Toast.makeText(getApplicationContext(), "Choose Internal Storage to restore", Toast.LENGTH_LONG).show();
                    m5757b();
                } else {
                    C3150b.m14831a(ViewAlbumActivity.this, new C10721());
                }
            }
        }, z);
    }

    private void m5762c() {
        this.f3187q = true;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File file = new File(Environment.getExternalStorageDirectory() + "/Android/data/" + getPackageName() + "/tmp_image.jpg");
        File file2 = new File(Environment.getExternalStorageDirectory() + "/Android/data/" + getPackageName());
        if (!file2.exists()) {
            file2.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        intent.putExtra("output", Uri.fromFile(file));
        startActivityForResult(intent, this.f3174c);
    }

    private void m5763c(ArrayList<String> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(FileProvider.getUriForFile(this, "calculator.vault.com.lock.VideoViewNEWActivity", new File((String) it.next())));
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND_MULTIPLE");
        intent.putParcelableArrayListExtra("android.intent.extra.STREAM", arrayList2);
        if (this.f3182l) {
            intent.setType("video/*");
        } else {
            intent.setType("image/*");
        }
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(intent, "Share selected items"));
    }

    private void m5766d() {
        if (this.f3173b.size() > 0) {
            this.tvLoading.setVisibility(View.GONE);
            this.btnAddFolder1.setVisibility(View.GONE);
            return;
        }
        this.tvLoading.setVisibility(View.VISIBLE);
        if (this.f3182l) {
            this.tvLoading.setText(getResources().getString(R.string.add_videos_string));
            this.btnAddFolder1.setImageResource(R.drawable.add_video_icon_xml);
        } else {
            this.btnAddFolder1.setImageResource(R.drawable.add_image_icon_xml);
            this.tvLoading.setText(getResources().getString(R.string.add_pictures_string));
        }
        this.btnAddFolder1.setVisibility(View.VISIBLE);
        this.btnAddFolder1.setOnClickListener(this);
    }

    private void m5767e() {
        this.f3187q = true;
        Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
        File file = new File(Environment.getExternalStorageDirectory() + "/Android/data/" + getPackageName());
        if (!file.exists()) {
            file.mkdirs();
        }
        startActivityForResult(intent, this.f3175d);
    }

    private void m5769f() {
        final PopupWindow popupWindow = new PopupWindow(this);
        View inflate = getLayoutInflater().inflate(R.layout.sort_popup, null);
        AppCompatRadioButton appCompatRadioButton = (AppCompatRadioButton) inflate.findViewById(R.id.radioNew);
        AppCompatRadioButton appCompatRadioButton2 = (AppCompatRadioButton) inflate.findViewById(R.id.radioOld);
        if (C1131f.f3324j) {
            appCompatRadioButton2.setChecked(true);
        } else {
            appCompatRadioButton.setChecked(true);
        }
        ((RadioGroup) inflate.findViewById(R.id.radioGroup1)).setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioNew) {
                    C1131f.f3324j = false;
                } else {
                    C1131f.f3324j = true;
                }
                Collections.sort(f3173b);
//                f3191u.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out));
                f3192v.notifyDataSetChanged();
                f3188r.putBoolean("isOldFirst", C1131f.f3324j);
                f3188r.commit();
                MainActivity.mainActivity.m5626a(C1131f.f3324j);
                popupWindow.dismiss();

            }
        });
        popupWindow.setContentView(inflate);
        popupWindow.setHeight(-2);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setWidth((int) SecurityHelpers.m14847a(150.0f, (Context) this));
        popupWindow.showAsDropDown(findViewById(R.id.rlSort), 10, -((int) SecurityHelpers.m14847a(50.0f, (Context) this)));
    }

    public void mo970a() {
        this.f3165G = true;
    }

    public void m5771a(int i) {
        this.f3173b.remove(i);
        this.f3192v.notifyDataSetChanged();
        m5766d();
    }

    public void onItemClicked(View view, final int i) {
        Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_item);
        loadAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                if (f3182l) {
                    Intent intent = new Intent(ViewAlbumActivity.this, VideoViewNEWActivity.class);
                    intent.putExtra("videoPath", ((C1611o) f3173b.get(i)).f4585a);
                    intent.putExtra("currentPath", f3166H);
                    intent.putExtra("position", i);
                    startActivity(intent);
                    return;
                }
                ViewImageActivity.f3236t = new ArrayList();
                ViewImageActivity.f3236t.addAll(f3173b);
                Intent intent2 = new Intent(getApplicationContext(), ViewImageActivity.class);
                intent2.putExtra("position", i);
                intent2.putExtra("currentPath", f3166H);
                startActivity(intent2);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        view.startAnimation(loadAnimation);
    }

    public void mo972a(String str) {
        C1611o c1611o = new C1611o(str);
        c1611o.f4587c = System.currentTimeMillis();
        if (C1131f.f3324j) {
            this.f3173b.add(c1611o);
            runOnUiThread(new C10838(this));
            return;
        }
        this.f3173b.add(0, c1611o);
        runOnUiThread(new C10849(this));
    }

    public void mo973a(ArrayList<String> arrayList) {
        this.f3165G = false;
        this.f3192v.notifyDataSetChanged();
        m5766d();
    }

    public void mo966a(boolean z) {
    }

    public void mo974b(int i) {
        this.inOverMenu = true;
        this.f3192v.m7294b(i);
        this.rlHeadEdit.setVisibility(View.VISIBLE);
        this.rlHeadEdit.setAlpha(0.0f);
        this.rlHeadEdit.animate().alpha(1.0f).setDuration(200).setInterpolator(new DecelerateInterpolator());
        this.f3179i.setVisibility(View.VISIBLE);
        this.f3179i.setAlpha(0.0f);
        this.f3179i.animate().alpha(1.0f).setDuration(200).setInterpolator(new DecelerateInterpolator()).setListener(null);
    }

    public void mo975c(int i) {
        this.f3180j.setText(i + " items selected");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1) {
            File file;
            if (i == this.f3174c) {
                file = new File(Environment.getExternalStorageDirectory() + "/Android/data/" + getPackageName() + "/tmp_image.jpg");
                String str = "Image-" + System.currentTimeMillis() + ".jpg";
                File file2 = new File(this.f3166H, str);
                C1611o c1611o = new C1611o(file2.getAbsolutePath());
                c1611o.f4587c = System.currentTimeMillis();
                if (C1131f.f3324j) {
                    this.f3173b.add(c1611o);
                    this.f3192v.notifyItemInserted(this.f3173b.size() - 1);
                    this.f3191u.smoothScrollToPosition(this.f3173b.size() - 1);
                } else {
                    this.f3173b.add(0, c1611o);
                    this.f3192v.notifyItemInserted(0);
                    this.f3191u.smoothScrollToPosition(0);
                }
                this.f3192v.notifyDataSetChanged();
                if (this.f3166H != null) {
                    try {
                        FileUtils.copyFile(file, file2);
                        this.f3183m.m14130a(str, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath());
                        if (this.f3182l) {
                            C1131f.m5810b(getApplicationContext(), file, "video/*");
                        } else {
                            C1131f.m5810b(getApplicationContext(), file, "image/*");
                        }
                    } catch (IOException e) {
                        Log.d("main", "Error Moving file: " + e);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error, Try again ", Toast.LENGTH_LONG).show();
                }
                setResult(-1);
                Cursor query = getContentResolver().query(Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "bucket_display_name", "datetaken", "mime_type"}, null, null, "datetaken DESC");
                if (query != null) {
                    query.moveToFirst();
                    str = query.getString(query.getColumnIndexOrThrow("_data"));
                    if (((int) (TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()) - TimeUnit.MILLISECONDS.toMinutes(new File(str).lastModified()))) < 3) {
                        new File(str).delete();
                        C1131f.m5810b(getApplicationContext(), new File(str), "image/*");
                    }
                    m5766d();
                }
            } else if (i == this.f3175d) {
                try {
                    FileInputStream createInputStream = getContentResolver().openAssetFileDescriptor(intent.getData(), "r").createInputStream();
                    file = new File(Environment.getExternalStorageDirectory() + "/Android/data/" + getPackageName());
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    FileOutputStream fileOutputStream = new FileOutputStream(new File(file, "tmp_video.mp4"));
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = createInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    createInputStream.close();
                    fileOutputStream.close();
                } catch (FileNotFoundException e2) {
                    e2.printStackTrace();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                new C1213d(this, new File(Environment.getExternalStorageDirectory() + "/Android/data/" + getPackageName() + "/tmp_video.mp4"), new File(this.f3166H, "Video-" + System.currentTimeMillis() + ".mp4"), true, this, false).execute(new Void[0]);
            } else if (i == 280) {
                finish();
            } else if (i == 142) {
                Uri data = intent.getData();
                if (C1132g.m5814a(data)) {
                    getContentResolver().takePersistableUriPermission(data, intent.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    this.f3188r.putString("treeUri", "" + data);
                    this.f3188r.putString("extSdCardPath", C1131f.f3326l);
                    this.f3188r.commit();
                    m5755a(this.f3193w, true);
                } else {
                    Toast.makeText(getApplicationContext(), "Grant Failed. Please choose the root directory of SD card", Toast.LENGTH_SHORT).show();
                    m5761b(true);
                }
            } else if (i == this.f3176e || i == this.f3177f) {
                ArrayList stringArrayListExtra = intent.getStringArrayListExtra("listSelected");
                final boolean booleanExtra = intent.getBooleanExtra("fromSdCard", false);
                final boolean booleanExtra2 = intent.getBooleanExtra("fromSdCardReal", false);
                if (i == this.f3176e) {
                    this.f3196z++;
                    if (this.f3196z >= this.f3195y.size()) {
                        this.f3196z = 0;
                    }
                    this.f3188r.putInt("adCount", this.f3196z);
                    this.f3188r.commit();
                    new C1201b(this, stringArrayListExtra, this.f3166H, this.f3183m, new C1044b() {

                        class C10761 implements C1230i.C1075a {

                            public void mo969a(boolean z) {
                                f3194x.clear();
                                if (z) {
                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.unsusuccess_text_sdcard), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        public void mo964a(String str) {
                            Toast.makeText(getApplicationContext(), "Error hiding videos, try again", Toast.LENGTH_SHORT).show();
                        }

                        public void mo965a(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                C1611o c1611o = new C1611o((String) it.next());
                                c1611o.f4587c = System.currentTimeMillis();
                                if (C1131f.f3324j) {
                                    f3173b.add(c1611o);
                                    f3192v.notifyItemInserted(f3173b.size() - 1);
                                } else {
                                    f3173b.add(0, c1611o);
                                    f3192v.notifyItemInserted(0);
                                }
                            }
                            m5766d();
                            f3191u.smoothScrollToPosition(C1131f.f3324j ? f3173b.size() - 1 : 0);
                            if (!booleanExtra || arrayList2 == null || (arrayList2 != null && arrayList2.isEmpty())) {
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                if (booleanExtra2 && C1131f.f3325k) {
                                    C3150b.m14838b(ViewAlbumActivity.this, ViewAlbumActivity.this);
                                    return;
                                }
                                return;
                            }
                            String string = f3164F.getString("treeUri", null);
                            if (string != null) {
                                new C1230i(ViewAlbumActivity.this, arrayList2, Uri.parse(string), "image/*", new C10761()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                                return;
                            }
                            f3194x = arrayList2;
                            m5761b(false);
                        }
                    }, booleanExtra, this.f3195y.size() > 0 ? (C1625q) this.f3195y.get(this.f3196z) : null).execute(new Void[0]);
                } else {
                    this.f3196z++;
                    if (this.f3196z >= this.f3195y.size()) {
                        this.f3196z = 0;
                    }
                    this.f3188r.putInt("adCount", this.f3196z);
                    this.f3188r.commit();
                    new C1223e(this, stringArrayListExtra, this.f3166H, this.f3183m, new C1044b() {
                        class C10781 implements C1230i.C1075a {

                            public void mo969a(boolean z) {
                                f3194x.clear();
                                if (z) {
                                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.unsusuccess_text_sdcard), Toast.LENGTH_LONG).show();
                                }
                            }
                        }

                        public void mo964a(String str) {
                            Toast.makeText(getApplicationContext(), "Error hiding videos, try again", Toast.LENGTH_SHORT).show();
                        }

                        public void mo965a(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                C1611o c1611o = new C1611o((String) it.next());
                                c1611o.f4587c = System.currentTimeMillis();
                                if (C1131f.f3324j) {
                                    f3173b.add(c1611o);
                                    f3192v.notifyItemInserted(f3173b.size() - 1);
                                    f3191u.smoothScrollToPosition(f3173b.size() - 1);
                                } else {
                                    f3173b.add(0, c1611o);
                                    f3192v.notifyItemInserted(0);
                                    f3191u.smoothScrollToPosition(0);
                                }
                                m5766d();
                            }
                            if (!booleanExtra || arrayList2 == null || (arrayList2 != null && arrayList2.isEmpty())) {
                                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                                if (booleanExtra2 && C1131f.f3325k) {
                                    C3150b.m14838b(ViewAlbumActivity.this, ViewAlbumActivity.this);
                                    return;
                                }
                                return;
                            }
                            String string = f3164F.getString("treeUri", null);
                            if (string != null) {
                                new C1230i(ViewAlbumActivity.this, arrayList2, Uri.parse(string), "video/*", new C10781()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                                return;
                            }
                            f3194x = arrayList2;
                            m5761b(false);
                        }
                    }, booleanExtra, this.f3195y.size() > 0 ? (C1625q) this.f3195y.get(this.f3196z) : null).execute(new Void[0]);
                }
            }
        }
        if (i == 42) {
            this.f3187q = false;
            if (i2 == -1) {
                Uri data2 = intent.getData();
                if (C1132g.m5814a(data2)) {
                    getContentResolver().takePersistableUriPermission(data2, intent.getFlags() & Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    this.f3188r.putString("treeUri", "" + data2);
                    this.f3188r.putString("extSdCardPath", C1131f.f3326l);
                    this.f3188r.commit();
                    if (!(this.f3194x == null || this.f3194x.isEmpty())) {
                        new C1230i(this, this.f3194x, data2, this.f3182l ? "video/*" : "image/*", new C10805(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Grant Failed. Please choose the root directory of SD card", Toast.LENGTH_SHORT).show();
                    m5761b(false);
                }
            } else {
                Toast.makeText(getApplicationContext(), "Grant Failed. Please choose the root directory of SD card", Toast.LENGTH_SHORT).show();
                m5761b(false);
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (this.f3165G) {
            C1131f.m5804a(f3158g, "Operation is running..\nDepending on Files size, this may take a while");
        } else if (this.inOverMenu) {
            this.inOverMenu = false;
            this.rlHeadEdit.animate().alpha(0.0f).setDuration(300).setInterpolator(new DecelerateInterpolator());
            this.f3179i.animate().alpha(0.0f).setDuration(300).setInterpolator(new DecelerateInterpolator()).setListener(new AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    rlHeadEdit.setVisibility(View.GONE);
                    f3179i.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            this.f3192v.m7295c();
        } else {
            super.onBackPressed();
        }
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.rlCancel:
                onBackPressed();
                return;
            case R.id.rlExport:
                m5757b();
                return;
            case R.id.rlDelete:
                ArrayList a = this.f3192v.m7287a();
                if (a.size() < 1) {
                    Toast.makeText(getApplicationContext(), "select atleast one item to delete", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    m5760b(a);
                    return;
                }
            case R.id.rlPlay:
                if (this.f3173b.size() < 1) {
                    C1131f.m5804a((Activity) this, "you have no items to play slideshow");
                    return;
                }
                ViewImageActivity.f3236t = new ArrayList(this.f3173b);
                intent = new Intent(getApplicationContext(), ViewImageActivity.class);
                intent.putExtra("play", true);
                intent.putExtra("currentPath", this.f3166H);
                startActivity(intent);
                return;
            case R.id.rlSort:
                m5769f();
                return;
            case R.id.rlEdit:
                if (this.f3173b.size() < 1) {
                    C1131f.m5804a(f3158g, "Import some items first");
                    return;
                }
                this.inOverMenu = true;
                this.f3192v.m7288a(0);
                this.rlHeadEdit.setVisibility(View.VISIBLE);
                this.rlHeadEdit.setAlpha(0.0f);
                this.rlHeadEdit.animate().alpha(1.0f).setDuration(200).setInterpolator(new DecelerateInterpolator());
                this.f3179i.setVisibility(View.VISIBLE);
                this.f3179i.setAlpha(0.0f);
                this.f3179i.animate().alpha(1.0f).setDuration(200).setInterpolator(new DecelerateInterpolator()).setListener(null);
                return;
            case R.id.rlBackEdit:
                onBackPressed();
                return;
            case R.id.rlSelectAll:
                this.f3192v.m7296d();
                return;
            case R.id.btnAddFolder1:
                onClick(findViewById(R.id.rlImageGallery));
                return;
            case R.id.rlImageGallery:
                if (this.f3182l) {
                    intent = new Intent(getApplicationContext(), NewVideoAlbumActivity.class);
                    intent.putExtra("currentPath", this.f3166H);
                    startActivityForResult(intent, this.f3177f);
                    return;
                }
                intent = new Intent(getApplicationContext(), NewImageAlbumActivity.class);
                intent.putExtra("currentPath", this.f3166H);
                startActivityForResult(intent, this.f3176e);
                return;
            case R.id.rlBrowser:
                startActivity(new Intent(getApplicationContext(), MainBrowserActivity.class));
                return;
            case R.id.rlCaptureImage:
                if (this.f3182l) {
                    m5767e();
                    return;
                } else {
                    m5762c();
                    return;
                }
            case R.id.rlMove:
                ArrayList b = this.f3192v.m7293b();
                if (b.size() < 1) {
                    Toast.makeText(getApplicationContext(), "select some items first", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent2 = new Intent(getApplicationContext(), MoveActivity.class);
                intent2.putExtra("listItems", b);
                intent2.putExtra("srcFolder", this.f3166H);
                intent2.putExtra("fromFake", this.f3190t);
                startActivityForResult(intent2, 280);
                return;
            case R.id.rlShare:
                final ArrayList b2 = this.f3192v.m7293b();
                if (b2.size() < 1) {
                    Toast.makeText(getApplicationContext(), "Select atLeast one item to share", Toast.LENGTH_SHORT).show();
                    return;
                } else if (b2.size() > 1) {
                    Builder builder = VERSION.SDK_INT >= 21 ? new Builder(this, 16974394) : new Builder(this);
                    builder.setTitle("Confirm");
                    builder.setMessage("Share " + b2.size() + (this.f3182l ? " Videos" : " Pictures") + " outside locker?");
                    builder.setPositiveButton("SHARE", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            m5763c(b2);
                        }
                    });
                    builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    builder.create().show();
                    return;
                } else {
                    m5763c(b2);
                    return;
                }
            default:
                return;
        }
    }

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        this.f3190t = getIntent().getBooleanExtra("fromFake", false);
        this.f3164F = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f3168J = C1131f.getCurrentStyle(this.f3164F);
        if (this.f3168J != R.style.AppTheme) {
            setTheme(this.f3168J);
        }
        setContentView(R.layout.activity_view_albums);
        f3158g = this;
        if (VERSION.SDK_INT >= 21) {
            this.f3189s = getWindow();
            this.f3189s.addFlags(Integer.MIN_VALUE);
        }
        this.f3188r = this.f3164F.edit();
        C1131f.f3324j = this.f3164F.getBoolean("isOldFirst", false);
        this.f3186p = (PowerManager) getSystemService("power");
        this.f3185o = (TelephonyManager) getSystemService("phone");
        this.btnAddFolder1 = (ImageButton) findViewById(R.id.btnAddFolder1);
        this.f3166H = getIntent().getStringExtra("path");
        this.f3182l = this.f3166H.contains("Videos1769");
        this.f3183m = new C2930c(getApplicationContext());
        C1131f.f3320f = Environment.getExternalStorageDirectory() + "/GalleryLock";
        final File file = new File(this.f3166H);
        new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... voidArr) {

                if (file != null) {
                    for (File file : file.listFiles()) {
                        C1611o c1611o = new C1611o(file.getAbsolutePath());
                        c1611o.f4587c = file.lastModified();
                        f3173b.add(c1611o);
                    }
                    Collections.sort(f3173b);
                }
                return null;
            }

            protected void onPostExecute(Void voidR) {
                f3191u = (RecyclerView) findViewById(R.id.recyclerView);
                f3191u.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
                int a = C3044b.m14508a(getApplicationContext(), 1.0f);
                int a2 = C3044b.m14508a(getApplicationContext(), 1.0f);
                f3191u.addItemDecoration(new RecyclerDecoration(a, a2, a, a2));
                f3192v = new C1639t(ViewAlbumActivity.this, f3173b, f3182l);
                f3192v.m7292a(ViewAlbumActivity.this);
                f3192v.m7290a(ViewAlbumActivity.this);
                f3192v.m7291a(ViewAlbumActivity.this);
                f3191u.setAdapter(f3192v);
                m5766d();
                super.onPostExecute(voidR);
            }

        }.execute(new Void[0]);
        TextView textView = (TextView) findViewById(R.id.tvFolderName);
        textView.setText(file.getName());
        textView.setTypeface(C1131f.f3315a);
        findViewById(R.id.rlPlay).setOnClickListener(this);
        findViewById(R.id.rlEdit).setOnClickListener(this);
        if (this.f3182l) {
            findViewById(R.id.rlPlay).setVisibility(View.GONE);
            ((ImageView) findViewById(R.id.ivGallery)).setImageResource(R.drawable.add_video_icon);
            ((ImageView) findViewById(R.id.ivRecord)).setImageResource(R.drawable.video_recorder_icon);
        }
        findViewById(R.id.rlImageGallery).setOnClickListener(this);
        findViewById(R.id.rlCaptureImage).setOnClickListener(this);
        this.tvLoading = (TextView) findViewById(R.id.tvLoading);
        this.tvLoading.setVisibility(8);
        this.rlHeadEdit = (FrameLayout) findViewById(R.id.rlHeadEdit);
        findViewById(R.id.rlBackEdit).setOnClickListener(this);
        if (this.f3190t) {
            findViewById(R.id.rlBrowser).setVisibility(View.GONE);
        } else {
            findViewById(R.id.rlBrowser).setOnClickListener(this);
        }
        findViewById(R.id.rlSelectAll).setOnClickListener(this);
        findViewById(R.id.rlCancel).setOnClickListener(this);
        this.f3180j = (TextView) findViewById(R.id.tvSelectedCount);
        this.f3179i = (LinearLayout) findViewById(R.id.bottomBarEdit);
        findViewById(R.id.rlDelete).setOnClickListener(this);
        findViewById(R.id.rlShare).setOnClickListener(this);
        findViewById(R.id.rlExport).setOnClickListener(this);
        findViewById(R.id.rlMove).setOnClickListener(this);
        findViewById(R.id.rlBack).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                onBackPressed();
            }
        });
        findViewById(R.id.rlSort).setOnClickListener(this);
        try {
            if (this.f3164F.getBoolean("faceDown", false)) {
                this.f3159A = this.f3164F.getInt("selectedPos", 0);
                this.f3160B = (SensorManager) getSystemService("sensor");
                this.f3161C = (Sensor) this.f3160B.getSensorList(1).get(0);
            }
        } catch (Exception e) {
        }
    }

    protected void onDestroy() {
        getWindow().clearFlags(128);
        super.onDestroy();
    }

    protected void onPause() {
//        overridePendingTransition(0, R.anim.exit);
        super.onPause();
    }

    protected void onResume() {
        if (this.f3192v != null) {
            this.f3192v.notifyDataSetChanged();
            m5766d();
        }
        super.onResume();
    }

    protected void onStart() {
        this.f3187q = false;
        try {
            if (this.f3160B != null) {
                this.f3160B.registerListener(this.f3170L, this.f3161C, 3);
            }
        } catch (Exception e) {
        }
        super.onStart();
    }

    protected void onStop() {
        try {
            if (this.f3160B != null) {
                this.f3160B.unregisterListener(this.f3170L);
            }
        } catch (Exception e) {
        }
        if (this.f3185o != null) {
            new Timer().schedule(new C10731(this), 500);
        }
        super.onStop();
    }
}
