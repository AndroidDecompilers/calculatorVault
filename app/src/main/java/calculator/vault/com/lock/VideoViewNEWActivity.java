package calculator.vault.com.lock;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.provider.DocumentFile;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.applock.C3150b;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.p029h.C2927f;
import calculator.vault.com.p068a.C1600k;
import calculator.vault.com.p068a.C1625q;
import calculator.vault.com.p084i.C2929b;
import calculator.vault.com.p084i.C2930c;

public class VideoViewNEWActivity extends Activity implements OnClickListener {
    VideoView f3067a;
    String f3068b;
    PowerManager f3069c;
    TelephonyManager f3070d;
    public int f3071e;
    SensorManager f3072f;
    Sensor f3073g;
    boolean f3074h;
    String f3075i;
    SharedPreferences f3076j;
    View f3077k;
    int f3078l;
    String f3079m;
    boolean f3080n;
    ArrayList<C1625q> f3081o = new ArrayList();
    C2929b f3082p;
    int f3083q = -1;
    Editor f3084r;
    private MediaController f3085s;
    private Animation f3086t;
    private Animation f3087u;
    private boolean f3088v;
    private SensorEventListener f3089w = new C10521(this);

    class C10521 implements SensorEventListener {
        final /* synthetic */ VideoViewNEWActivity f3030a;

        C10521(VideoViewNEWActivity videoViewNEWActivity) {
            this.f3030a = videoViewNEWActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f3030a.f3074h) {
                    this.f3030a.f3074h = true;
                    if (this.f3030a.f3071e == 1) {
                        C1131f.m5806a(this.f3030a.getApplicationContext(), this.f3030a.getPackageManager(), this.f3030a.f3076j.getString("Package_Name", null));
                    }
                    if (this.f3030a.f3071e == 2) {
                        this.f3030a.f3075i = this.f3030a.f3076j.getString("URL_Name", null);
                        this.f3030a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f3030a.f3075i)));
                    }
                    if (this.f3030a.f3071e == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.f3030a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    class C10564 implements C3150b.C1055c {
        final /* synthetic */ VideoViewNEWActivity f3036a;

        C10564(VideoViewNEWActivity videoViewNEWActivity) {
            this.f3036a = videoViewNEWActivity;
        }

        public void mo966a(boolean z) {
            if (z) {
                this.f3036a.f3080n = true;
                this.f3036a.startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), 142);
                return;
            }
            C1131f.m5804a(this.f3036a, "Choose Internal Storage to restore");
            this.f3036a.m5714a();
        }
    }

    class C10575 extends TimerTask {
        final /* synthetic */ VideoViewNEWActivity f3037a;

        C10575(VideoViewNEWActivity videoViewNEWActivity) {
            this.f3037a = videoViewNEWActivity;
        }

        public void run() {
            try {
                if (SecurityHelpers.isCallRinging(this.f3037a.f3070d) || !(SecurityHelpers.m14854b(this.f3037a.getApplicationContext()).equals(this.f3037a.getPackageName()) || this.f3037a.f3080n)) {
                    MainActivity.mainActivity.finish();
                    ViewAlbumActivity.f3158g.finish();
                    this.f3037a.finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!SecurityHelpers.isScreenON(this.f3037a.f3069c)) {
                MainActivity.mainActivity.finish();
                ViewAlbumActivity.f3158g.finish();
                this.f3037a.finish();
            }
        }
    }

    class C10597 implements OnClickListener {
        final /* synthetic */ VideoViewNEWActivity f3039a;

        C10597(VideoViewNEWActivity videoViewNEWActivity) {
            this.f3039a = videoViewNEWActivity;
        }

        public void onClick(View view) {
            this.f3039a.onBackPressed();
        }
    }

    class C10608 implements AnimationListener {
        final /* synthetic */ VideoViewNEWActivity f3040a;

        C10608(VideoViewNEWActivity videoViewNEWActivity) {
            this.f3040a = videoViewNEWActivity;
        }

        public void onAnimationEnd(Animation animation) {
            this.f3040a.f3077k.setVisibility(View.VISIBLE);
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    class C10619 implements AnimationListener {
        final /* synthetic */ VideoViewNEWActivity f3041a;

        C10619(VideoViewNEWActivity videoViewNEWActivity) {
            this.f3041a = videoViewNEWActivity;
        }

        public void onAnimationEnd(Animation animation) {
            this.f3041a.f3077k.setVisibility(View.GONE);
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    class C1071a extends AsyncTask<Void, String, C2927f> {
        Dialog f3052a;
        ProgressBar f3053b;
        TextView f3054c;
        String f3055d;
        boolean f3056e;
        boolean f3057f;
        DocumentFile f3058g;
        DocumentFile f3059h;
        Button f3060i;
        C1625q f3061j;
        View f3062k;
        TextView f3063l;
        int f3064m = -1;
        OnClickListener f3065n = new C10704(this);
        final /* synthetic */ VideoViewNEWActivity f3066o;

        class C10621 implements OnClickListener {
            final /* synthetic */ C1071a f3042a;

            C10621(C1071a c1071a) {
                this.f3042a = c1071a;
            }

            public void onClick(View view) {
                this.f3042a.f3057f = true;
            }
        }

        class C10693 implements OnClickListener {
            final /* synthetic */ C1071a f3050a;

            C10693(C1071a c1071a) {
                this.f3050a = c1071a;
            }

            public void onClick(View view) {
                this.f3050a.f3052a.dismiss();
                ViewAlbumActivity.f3158g.m5771a(this.f3050a.f3066o.f3078l);
                C1131f.f3320f = Environment.getExternalStorageDirectory() + "/GalleryLock";
                this.f3050a.f3066o.finish();
                Toast.makeText(this.f3050a.f3066o.getApplicationContext(), "Video Restored", Toast.LENGTH_SHORT).show();
            }
        }

        class C10704 implements OnClickListener {
            final /* synthetic */ C1071a f3051a;

            C10704(C1071a c1071a) {
                this.f3051a = c1071a;
            }

            public void onClick(View view) {
                C1131f.m5809b(this.f3051a.f3066o, this.f3051a.f3061j.f4625b);
            }
        }

        public C1071a(VideoViewNEWActivity videoViewNEWActivity, String str, boolean z, DocumentFile DocumentFile, C1625q c1625q) {
            this.f3066o = videoViewNEWActivity;
            this.f3055d = str;
            this.f3056e = z;
            this.f3058g = DocumentFile;
            this.f3061j = c1625q;
        }

        private C2927f m5709a(File file) {
            C2930c c2930c = new C2930c(this.f3066o.getApplicationContext());
            String replace = file.getAbsolutePath().replace(this.f3066o.f3079m, C1131f.f3320f);
            if (replace.contains("null")) {
                replace = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + file.getName();
            }
            File file2 = new File(replace);
            try {
                OutputStream openOutputStream;
                if (this.f3056e) {
                    this.f3059h = C1132g.m5812a(file2, false, this.f3066o.getApplicationContext());
                    openOutputStream = this.f3066o.getContentResolver().openOutputStream(this.f3059h.getUri());
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
                    publishProgress(new String[]{"" + ((int) ((100 * j) / ((long) available)))});
                    openOutputStream.write(bArr, 0, read);
                } while (!this.f3057f);
                openOutputStream.flush();
                openOutputStream.close();
                fileInputStream.close();
                if (this.f3056e) {
                    this.f3059h.delete();
                } else {
                    file2.delete();
                }
                if (this.f3057f) {
                    return C2927f.CANCELLED;
                }
                openOutputStream.flush();
                openOutputStream.close();
                fileInputStream.close();
                if (file.delete()) {
                    c2930c.m14129a(file.getName());
                    C1131f.m5805a(this.f3066o.getApplicationContext(), file2, "video/*");
                } else {
                    c2930c.m14129a(file.getName());
                    C1131f.m5805a(this.f3066o.getApplicationContext(), file2, "video/*");
                }
                return C2927f.SUCCESS;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return C2927f.FAILED;
            } catch (IOException e2) {
                e2.printStackTrace();
                return C2927f.FAILED;
            } catch (NullPointerException e3) {
                e3.printStackTrace();
                return C2927f.FAILED;
            }
        }

        protected C2927f m5710a(Void... voidArr) {
            return m5709a(new File(this.f3055d));
        }

        protected void m5711a(C2927f c2927f) {
            if (c2927f == C2927f.SUCCESS) {
                this.f3063l.setText("One video was restored to public gallery.");
                this.f3060i.setText("DONE");
                this.f3060i.setOnClickListener(new C10693(this));
            } else if (c2927f == C2927f.CANCELLED) {
                this.f3052a.dismiss();
                Toast.makeText(this.f3066o.getApplicationContext(), "Operation Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                this.f3052a.dismiss();
                Toast.makeText(this.f3066o.getApplicationContext(), "Error Unhiding.. Check SD Card or unhide to Phone memory", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(c2927f);
        }

        protected void m5712a(String... strArr) {
            int parseInt = Integer.parseInt(strArr[0]);
            if (parseInt - this.f3064m > 0) {
                this.f3053b.setProgress(parseInt);
                this.f3054c.setText(parseInt + "%");
                this.f3064m = parseInt;
            }
            super.onProgressUpdate(strArr);
        }

        protected /* synthetic */ C2927f doInBackground(Void[] objArr) {
            return m5710a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(C2927f obj) {
            m5711a((C2927f) obj);
        }

        protected void onPreExecute() {
            this.f3052a = new ProgressDialog(this.f3066o);
            final View inflate = this.f3066o.getLayoutInflater().inflate(R.layout.progress_dialog, null);
            this.f3052a.show();
            this.f3052a.setContentView(inflate);
            this.f3052a.setCancelable(false);
            this.f3053b = (ProgressBar) inflate.findViewById(R.id.progressBar1);
            this.f3063l = (TextView) inflate.findViewById(R.id.tvTitle);
            this.f3054c = (TextView) inflate.findViewById(R.id.tvProgress);
            this.f3063l.setTypeface(C1131f.f3315a);
            this.f3063l.setText("Please wait..Unhiding video");
            this.f3060i = (Button) inflate.findViewById(R.id.btnCancel);
            this.f3060i.setOnClickListener(new C10621(this));
            if (this.f3061j != null) {
                this.f3062k = inflate.findViewById(R.id.llAd);
                new Handler().postDelayed(new Runnable() {

                    class C10672 implements OnClickListener {
                        class C10661 implements AnimationListener {

                            public void onAnimationEnd(Animation animation) {
                                f3062k.setVisibility(View.GONE);
                            }

                            public void onAnimationRepeat(Animation animation) {
                            }

                            public void onAnimationStart(Animation animation) {
                            }
                        }

                        public void onClick(View view) {
                            Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.adview_shrink);
                            loadAnimation.setAnimationListener(new C10661());
                            f3062k.startAnimation(loadAnimation);
                        }
                    }

                    public void run() {
                        f3062k.setVisibility(View.VISIBLE);
//                        f3062k.startAnimation(AnimationUtils.loadAnimation(f3066o.getApplicationContext(), R.anim.adview_grow));
                        final ImageView imageView = (ImageView) inflate.findViewById(R.id.ivAd);
//                        C1497e.m6918b(f3066o.getApplicationContext()).m7089a(f3061j.f4627d).m6157b().m6151a(new C1064d<String, C1410b>() {
//                            class C10631 implements Runnable {
//
//                                public void run() {
//                                    int width = imageView.getWidth();
//                                    int height = imageView.getHeight();
//                                    LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
//                                    if (C1131f.f3317c > C1131f.f3316b) {
//                                        layoutParams.height = (width * 2) / 4;
//                                    } else {
//                                        layoutParams.height = (height * 2) / 4;
//                                    }
//                                    layoutParams.width = width;
//                                    imageView.setLayoutParams(layoutParams);
//                                }
//                            }
//
//                        }).mo1009a(imageView);
//                        C1497e.m6914a(f3066o).m7089a(f3061j.f4628e).mo1009a((ImageView) inflate.findViewById(R.id.ivAdIcon));
                        ((TextView) inflate.findViewById(R.id.tvAppName)).setText(f3061j.f4624a);
                        ((TextView) inflate.findViewById(R.id.tvDesc)).setText(f3061j.f4626c);
                        imageView.setOnClickListener(f3065n);
                        inflate.findViewById(R.id.btnInstall).setOnClickListener(f3065n);
                        inflate.findViewById(R.id.btnCloseAd).setOnClickListener(new C10672());
                    }
                }, 1000);
            }
            super.onPreExecute();
        }

        protected /* synthetic */ void onProgressUpdate(String[] objArr) {
            m5712a((String[]) objArr);
        }
    }

    private void m5714a() {
        C2930c c2930c = new C2930c(getApplicationContext());
        ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        String b = c2930c.m14131b(new File(this.f3068b).getName());
        arrayList.add("GalleryLock folder (Phone memory)");
        arrayList2.add(Environment.getExternalStorageDirectory().getAbsolutePath() + "/GalleryLock");
        File[] a = ContextCompat.getExternalFilesDirs(getApplicationContext(), "GalleryLock");
        if (a != null && a.length > 1) {
            String absolutePath;
            if (C1131f.f3325k) {
                absolutePath = new File(a[1], "").getAbsolutePath();
                if (absolutePath.length() > 2) {
                    arrayList.add("Calculator folder (external sdcard)");
                    arrayList2.add(absolutePath);
                }
            } else {
                absolutePath = new File(a[1], "").getAbsolutePath().replace("/Android/data/" + getPackageName() + "/files", "");
                if (absolutePath.length() > 2 && !absolutePath.contains(getPackageName())) {
                    arrayList.add("GalleryLock folder (external sdcard)");
                    arrayList2.add(absolutePath);
                }
            }
        }
        if (C1131f.f3325k) {
            if (b.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath()) && b.length() > 5) {
                arrayList.add(0, "Original Path");
                arrayList2.add(0, b);
            }
        } else if (b.length() > 5) {
            arrayList.add(0, "Original Path");
            arrayList2.add(0, b);
        }
        final Dialog dialog = new Dialog(this);
        View inflate = getLayoutInflater().inflate(R.layout.dialog_restore_chooser, null);
        ListView listView = (ListView) inflate.findViewById(R.id.lvDirs);
        listView.setAdapter(new C1600k(arrayList, arrayList2, getApplicationContext()));
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                dialog.dismiss();
                C1131f.f3320f = (String) arrayList2.get(i);
                if (C1131f.f3320f == null) {
                    C1131f.f3320f = Environment.getExternalStorageDirectory() + "/GalleryLock";
                }
                File file = new File(C1131f.f3320f);
                boolean startsWith = C1131f.f3320f.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath());
                boolean z = !startsWith && C1131f.f3323i;
                VideoViewNEWActivity r0;
                if (C1131f.f3325k) {
                    if (startsWith) {
                        dialog.dismiss();
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        new C1071a(VideoViewNEWActivity.this, f3068b, z, null, f3081o.size() > 0 ? (C1625q) f3081o.get(f3083q) : null).execute(new Void[0]);
                    } else if (f3088v) {
                        dialog.dismiss();
                        f3088v = false;
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        new C1071a(VideoViewNEWActivity.this, f3068b, z, null, f3081o.size() > 0 ? (C1625q) f3081o.get(f3083q) : null).execute(new Void[0]);
                    } else {
                        f3088v = true;
                        C3150b.m14836b(VideoViewNEWActivity.this);
                    }
                } else if (z) {
                    String string = f3076j.getString("treeUri", null);
                    if (string != null) {
                        DocumentFile b = C1132g.m5816b(getApplicationContext(), file, Uri.parse(string));
                        f3083q++;
                        if (f3083q >= f3081o.size()) {
                            f3083q = 0;
                        }
                        f3084r.putInt("adCount", f3083q);
                        f3084r.commit();
                        new C1071a(VideoViewNEWActivity.this, f3068b, z, b, f3081o.size() > 0 ? (C1625q) f3081o.get(f3083q) : null).execute(new Void[0]);
                        return;
                    }
                    m5717b();
                } else {
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    f3083q++;
                    if (f3083q >= f3081o.size()) {
                        f3083q = 0;
                    }
                    f3084r.putInt("adCount", f3083q);
                    f3084r.commit();
                    new C1071a(VideoViewNEWActivity.this, f3068b, z, null, f3081o.size() > 0 ? (C1625q) f3081o.get(f3083q) : null).execute();
                }
            }
        });
        dialog.setContentView(inflate);
        dialog.show();
    }


    private void m5717b() {
        C3150b.m14832a((Activity) this, new C10564(this), true);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onActivityResult(int i, int i2, Intent intent) {
        this.f3080n = false;
        if (i == 142 && i2 == -1) {
            Uri data = intent.getData();
            if (C1132g.m5814a(data)) {
                getContentResolver().takePersistableUriPermission(data, intent.getFlags() & Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                this.f3084r.putString("treeUri", "" + data);
                this.f3084r.putString("extSdCardPath", C1131f.f3326l);
                this.f3083q++;
                if (this.f3083q >= this.f3081o.size()) {
                    this.f3083q = 0;
                }
                this.f3084r.putInt("adCount", this.f3083q);
                this.f3084r.commit();
                new C1071a(this, this.f3068b, true, C1132g.m5816b(getApplicationContext(), new File(C1131f.f3320f), data), this.f3081o.size() > 0 ? (C1625q) this.f3081o.get(this.f3083q) : null).execute(new Void[0]);
            } else {
                Toast.makeText(getApplicationContext(), "Grant Failed. Please choose the root directory of SD card", Toast.LENGTH_SHORT).show();
                m5717b();
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onClick(View view) {
        View inflate;
        TextView textView;
        switch (view.getId()) {
            case R.id.btnShare:
                Parcelable a = FileProvider.getUriForFile(this, "calculator.vault.com.lock.VideoViewNEWActivity", new File(this.f3068b));
                Intent intent = new Intent();
                intent.setAction("android.intent.action.SEND");
                intent.putExtra("android.intent.extra.STREAM", a);
                intent.setType("video/*");
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(intent, "Share video via"));
                return;
            case R.id.btnUnhide:
                final Dialog dialog = new Dialog(this);
                inflate = getLayoutInflater().inflate(R.layout.delete_dialog, null);
                dialog.setContentView(inflate);
                textView = (TextView) inflate.findViewById(R.id.textView1);
                textView.setText("Unhide");
                textView.setTypeface(C1131f.f3315a);
                textView = (TextView) inflate.findViewById(R.id.tv_dialogText);
                textView.setText("Unhide and restore this video back to gallery?");
                textView.setTypeface(C1131f.f3315a);
                inflate.findViewById(R.id.rlDelete).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        m5714a();
                        dialog.dismiss();
                    }
                });
                inflate.findViewById(R.id.rlCancel).setOnClickListener(new OnClickListener() {

                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return;
            case R.id.btnOpenWith:
                inflate = getLayoutInflater().inflate(R.layout.oops_video_dialog, null);
                textView = (TextView) inflate.findViewById(R.id.tv_dialogText);
                ((TextView) inflate.findViewById(R.id.textView1)).setTypeface(C1131f.f3315a);
                ((TextView) inflate.findViewById(R.id.textView1)).setText("Play video with..");
                ((TextView) inflate.findViewById(R.id.tv_dialogText)).setText("Play video with other video player installed in phone?\n\n*For security reasons, this app will be close when you play video outside this app.");
                textView.setTypeface(C1131f.f3315a);
                final Dialog dialog2 = new Dialog(this);
                dialog2.setContentView(inflate);
                inflate.findViewById(R.id.rlIntent).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        dialog2.dismiss();
                        Uri a = FileProvider.getUriForFile(VideoViewNEWActivity.this, "calculator.vault.com.lock.VideoViewNEWActivity", new File(f3068b));
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        intent.setDataAndType(a, "video/*");
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        startActivity(intent);
                    }
                });
                inflate.findViewById(R.id.rlCancel).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        dialog2.dismiss();
                    }
                });
                dialog2.show();
                return;
            default:
                return;
        }
    }

    protected void onCreate(Bundle bundle) {
        boolean z;
        int i = 0;
        super.onCreate(bundle);
        getWindow().addFlags(128);
        setContentView(R.layout.activity_video_view_new);
        this.f3076j = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f3084r = this.f3076j.edit();
        if (!this.f3076j.getBoolean("hideAd", false)) {
            this.f3082p = new C2929b(this);
            this.f3081o = (ArrayList<C1625q>) this.f3082p.m14124a(getPackageManager(), false);
        }
        this.f3069c = (PowerManager) getSystemService(POWER_SERVICE);
        this.f3070d = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        this.f3077k = findViewById(R.id.toolbar);
        this.f3067a = (VideoView) findViewById(R.id.videoView1);
        this.f3068b = getIntent().getStringExtra("videoPath");
        this.f3078l = getIntent().getIntExtra("index", 0);
        this.f3079m = getIntent().getStringExtra("currentPath");
        this.f3085s = new MediaController(this) {
            public boolean dispatchKeyEvent(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 4) {
                    finish();
                }
                return super.dispatchKeyEvent(keyEvent);
            }

            public void hide() {
                f3077k.startAnimation(f3087u);
                super.hide();
            }

            public void show() {
                f3077k.startAnimation(f3086t);
                f3085s.startAnimation(f3086t);
                super.show();
            }
        };
        this.f3067a.setVideoURI(Uri.parse(this.f3068b));
        this.f3067a.setMediaController(this.f3085s);
        if (bundle != null) {
            i = bundle.getInt("videoPosition");
            z = bundle.getBoolean("isPlaying");
        } else {
            this.f3067a.start();
            z = false;
        }
        this.f3067a.seekTo(i);
        if (z) {
            this.f3067a.start();
        }
        findViewById(R.id.btnBack).setOnClickListener(new C10597(this));
        this.f3086t = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.d_fade_in);
        this.f3086t.setAnimationListener(new C10608(this));
        this.f3087u = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.d_fade_out);
        this.f3087u.setAnimationListener(new C10619(this));
        this.f3077k.startAnimation(this.f3087u);
        findViewById(R.id.btnShare).setOnClickListener(this);
        findViewById(R.id.btnUnhide).setOnClickListener(this);
        findViewById(R.id.btnOpenWith).setOnClickListener(this);
        try {
            if (this.f3076j.getBoolean("faceDown", false)) {
                this.f3071e = this.f3076j.getInt("selectedPos", 0);
                this.f3072f = (SensorManager) getSystemService(SENSOR_SERVICE);
                this.f3073g = (Sensor) this.f3072f.getSensorList(1).get(0);
                this.f3072f.registerListener(this.f3089w, this.f3073g, 3);
            }
        } catch (Exception e) {
        }
        TextView textView = (TextView) findViewById(R.id.tvTitle);
        textView.setText("" + new File(this.f3068b).getName());
        textView.setTypeface(C1131f.f3315a);
    }

    protected void onDestroy() {
        getWindow().clearFlags(128);
        super.onDestroy();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("videoPosition", this.f3067a.getCurrentPosition());
        bundle.putBoolean("isPlaying", this.f3067a.isPlaying());
    }

    protected void onStart() {
        this.f3080n = false;
        try {
            if (this.f3072f != null) {
                this.f3072f.registerListener(this.f3089w, this.f3073g, 3);
            }
        } catch (Exception e) {
        }
        super.onStart();
    }

    protected void onStop() {
        if (this.f3070d != null) {
            new Timer().schedule(new C10575(this), 500);
        }
        super.onStop();
    }
}
