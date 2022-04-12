package calculator.vault.com.lock;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.provider.DocumentFile;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.applock.C3150b;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.customphotoview.C1562e;
import calculator.vault.com.customphotoview.PhotoView;
import calculator.vault.com.customphotoview.TrickyViewPager;
import calculator.vault.com.p029h.C2927f;
import calculator.vault.com.p068a.C1600k;
import calculator.vault.com.p068a.C1611o;
import calculator.vault.com.p084i.C2930c;
import calculator.vault.com.transformations.C1641a;
import calculator.vault.com.transformations.C1642b;
import calculator.vault.com.transformations.C1643d;
import calculator.vault.com.transformations.C1644e;
import calculator.vault.com.transformations.C1645f;
import calculator.vault.com.transformations.C1646g;
import calculator.vault.com.transformations.C1647h;
import calculator.vault.com.transformations.C1648i;
import calculator.vault.com.transformations.C1649j;
import calculator.vault.com.transformations.C1650k;
import calculator.vault.com.transformations.C1651l;
import calculator.vault.com.transformations.C1652m;
import calculator.vault.com.transformations.C1653n;
import calculator.vault.com.transformations.C1654o;
import calculator.vault.com.transformations.C1655p;
import calculator.vault.com.transformations.C1656q;

@TargetApi(21)
public class ViewImageActivity extends Activity implements ViewPager.OnPageChangeListener, OnClickListener {

    public static ViewImageActivity f3235s;
    public static ArrayList<C1611o> f3236t;
    Sensor sensor;
    boolean f3238B;
    boolean f3239C;
    String f3240D;
    SharedPreferences sharedPreferences;
    C1611o f3242F;
    TrickyViewPager trickyViewPager;
    PagerAdaperImages f3244H;
    int f3245I;
    private boolean f3246J;
    private SensorEventListener f3247K = new C11033(this);
    int f3248a;
    int f3249b;
    TelephonyManager telephonyManager;
    PowerManager powerManager;
    TextView textView;
    RelativeLayout relativeLayout;
    LinearLayout linearLayout;
    Timer timer = new Timer();
    ImageButton f3256i;
    ImageButton f3257j;
    TimerTask timerTask;
    int f3259l;
    CheckBox checkBox;
    Random random;
    int f3262o;
    int f3263p;
    String currentPath;
    FrameLayout frameLayout;
    boolean f3266u;
    ArrayList<Float> f3267v = new ArrayList();
    ViewPager.PageTransformer f3268w;
    ViewPager.PageTransformer[] f3269x = new ViewPager.PageTransformer[]{new C1644e(), null, new C1641a(), new C1642b(), new C1643d(), new C1645f(), new C1646g(), new C1647h(), new C1648i(), new C1649j(), new C1650k(), new C1651l(), new C1652m(), new C1653n(), new C1654o(), new C1655p(), new C1656q()};
    public int f3270y;
    SensorManager sensorManager;

    class C11011 extends TimerTask {
        final /* synthetic */ ViewImageActivity f3201a;

        C11011(ViewImageActivity viewImageActivity) {
            this.f3201a = viewImageActivity;
        }

        public void run() {
            try {
                if (SecurityHelpers.isCallRinging(this.f3201a.telephonyManager) || !(SecurityHelpers.m14854b(this.f3201a.getApplicationContext()).equals(this.f3201a.getPackageName()) || this.f3201a.f3239C)) {
                    MainActivity.mainActivity.finish();
                    ViewAlbumActivity.f3158g.finish();
                    this.f3201a.finish();
                }
                if (!SecurityHelpers.isScreenON(this.f3201a.powerManager)) {
                    MainActivity.mainActivity.finish();
                    ViewAlbumActivity.f3158g.finish();
                    this.f3201a.finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C11033 implements SensorEventListener {
        final /* synthetic */ ViewImageActivity f3206a;

        C11033(ViewImageActivity viewImageActivity) {
            this.f3206a = viewImageActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f3206a.f3238B) {
                    this.f3206a.f3238B = true;
                    if (this.f3206a.f3270y == 1) {
                        C1131f.m5806a(this.f3206a.getApplicationContext(), this.f3206a.getPackageManager(), this.f3206a.sharedPreferences.getString("Package_Name", null));
                    }
                    if (this.f3206a.f3270y == 2) {
                        this.f3206a.f3240D = this.f3206a.sharedPreferences.getString("URL_Name", null);
                        this.f3206a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f3206a.f3240D)));
                    }
                    if (this.f3206a.f3270y == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.f3206a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    class C11044 implements C3150b.C1055c {
        final /* synthetic */ ViewImageActivity f3207a;

        C11044(ViewImageActivity viewImageActivity) {
            this.f3207a = viewImageActivity;
        }

        public void mo966a(boolean z) {
            if (z) {
                this.f3207a.f3239C = true;
                this.f3207a.startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), 142);
                return;
            }
            C1131f.m5804a(this.f3207a, "Choose Internal Storage to restore");
            this.f3207a.m5789b();
        }
    }

    class C11055 implements OnCheckedChangeListener {
        final /* synthetic */ ViewImageActivity f3208a;

        C11055(ViewImageActivity viewImageActivity) {
            this.f3208a = viewImageActivity;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            C1131f.m5804a(this.f3208a, "Shuffle Images " + (z ? "ON" : "OFF"));
            this.f3208a.f3266u = z;
        }
    }

    private class PagerAdaperImages extends PagerAdapter {
        final ViewImageActivity f3217a;

        private PagerAdaperImages(ViewImageActivity viewImageActivity) {
            this.f3217a = viewImageActivity;
        }

        public void destroyItem(View view, int i, Object obj) {
            ((ViewPager) view).removeView((LinearLayout) obj);
        }

        public int getCount() {
            return ViewImageActivity.f3236t.size();
        }

        public Object instantiateItem(View view, int i) {
            String str = ((C1611o) ViewImageActivity.f3236t.get(i)).f4585a;
            if (str.endsWith("gif")) {
                LinearLayout linearLayout = new LinearLayout(this.f3217a);
                ImageView imageView = new ImageView(this.f3217a);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                imageView.setScaleType(ScaleType.FIT_CENTER);
                Glide.with(ViewImageActivity.f3235s).load(str).asGif().diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
                linearLayout.setTag("iv" + i);
                linearLayout.addView(imageView);
                ((ViewPager) view).addView(linearLayout);
                return linearLayout;
            }
            LinearLayout linearLayout = new LinearLayout(this.f3217a);
            SubsamplingScaleImageView photoView = new SubsamplingScaleImageView(this.f3217a);
            photoView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            photoView.setImage(ImageSource.uri(Uri.fromFile(new File(str))));
            ((ViewPager) view).addView(linearLayout);
            linearLayout.setTag("iv" + i);
            linearLayout.addView(photoView);
            return linearLayout;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public Parcelable saveState() {
            return null;
        }
    }

    class C1113b extends AsyncTask<Void, String, C2927f> {
        Dialog f3220a;
        ProgressBar f3221b;
        TextView f3222c;
        C1611o f3223d;
        boolean f3224e;
        boolean f3225f;
        DocumentFile f3226g;
        DocumentFile f3227h;
        Button f3228i;
        TextView f3229j;
        final /* synthetic */ ViewImageActivity f3230k;
        private int f3231l = -1;

        class C11111 implements OnClickListener {
            final /* synthetic */ C1113b f3218a;

            C11111(C1113b c1113b) {
                this.f3218a = c1113b;
            }

            public void onClick(View view) {
                this.f3218a.f3225f = true;
            }
        }

        class C11122 implements OnClickListener {
            final /* synthetic */ C1113b f3219a;

            C11122(C1113b c1113b) {
                this.f3219a = c1113b;
            }

            public void onClick(View view) {
                this.f3219a.f3220a.dismiss();
                ViewAlbumActivity.f3158g.m5771a(this.f3219a.f3230k.f3262o);
                C1131f.f3320f = Environment.getExternalStorageDirectory() + "/Calculator";
                this.f3219a.f3230k.finish();
            }
        }

        public C1113b(ViewImageActivity viewImageActivity, C1611o c1611o, boolean z, DocumentFile c0318a) {
            this.f3230k = viewImageActivity;
            this.f3223d = c1611o;
            this.f3224e = z;
            this.f3226g = c0318a;
        }

        private C2927f m5779a(File file) {
            C2930c c2930c = new C2930c(this.f3230k.getApplicationContext());
            String replace = file.getAbsolutePath().replace(this.f3230k.currentPath, C1131f.f3320f);
            if (replace.contains("null")) {
                replace = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + file.getName();
            }
            File file2 = new File(replace);
            try {
                OutputStream openOutputStream;
                if (this.f3224e) {
                    this.f3227h = C1132g.m5812a(file2, false, this.f3230k.getApplicationContext());
                    openOutputStream = this.f3230k.getContentResolver().openOutputStream(this.f3227h.getUri());
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
                } while (!this.f3225f);
                openOutputStream.flush();
                openOutputStream.close();
                fileInputStream.close();
                if (this.f3224e) {
                    this.f3227h.delete();
                } else {
                    file2.delete();
                }
                if (this.f3225f) {
                    return C2927f.CANCELLED;
                }
                openOutputStream.flush();
                openOutputStream.close();
                fileInputStream.close();
                if (file.delete()) {
                    c2930c.m14129a(file.getName());
                    C1131f.m5805a(this.f3230k.getApplicationContext(), file2, "image/*");
                } else if (FileUtils.deleteQuietly(file)) {
                    c2930c.m14129a(file.getName());
                    C1131f.m5805a(this.f3230k.getApplicationContext(), file2, "image/*");
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

        protected C2927f m5780a(Void... voidArr) {
            return m5779a(new File(this.f3223d.f4585a));
        }

        protected void m5781a(C2927f c2927f) {
            if (c2927f == C2927f.SUCCESS) {
                this.f3229j.setText("One picture was restored to public gallery.");
                this.f3228i.setText("DONE");
                this.f3228i.setOnClickListener(new C11122(this));
            } else if (c2927f == C2927f.CANCELLED) {
                this.f3220a.dismiss();
                Toast.makeText(this.f3230k.getApplicationContext(), "Operation Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                this.f3220a.dismiss();
                Toast.makeText(this.f3230k.getApplicationContext(), "Error Unhiding.. Check SD Card or unhide to Phone memory", Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(c2927f);
        }

        protected void m5782a(String... strArr) {
            int parseInt = Integer.parseInt(strArr[0]);
            if (parseInt - this.f3231l > 0) {
                this.f3221b.setProgress(parseInt);
                this.f3222c.setText(parseInt + "%");
                this.f3231l = parseInt;
            }
            super.onProgressUpdate(strArr);
        }

        protected /* synthetic */ C2927f doInBackground(Void[] objArr) {
            return m5780a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(C2927f obj) {
            m5781a((C2927f) obj);
        }

        protected void onPreExecute() {
            this.f3220a = new ProgressDialog(this.f3230k);
            View inflate = this.f3230k.getLayoutInflater().inflate(R.layout.progress_dialog, null);
            this.f3220a.show();
            this.f3220a.setContentView(inflate);
            this.f3220a.setCancelable(false);
            this.f3221b = (ProgressBar) inflate.findViewById(R.id.progressBar1);
            this.f3229j = (TextView) inflate.findViewById(R.id.tvTitle);
            this.f3222c = (TextView) inflate.findViewById(R.id.tvProgress);
            this.f3229j.setTypeface(C1131f.f3315a);
            this.f3229j.setText("Please wait..Unhiding picture");
            this.f3228i = (Button) inflate.findViewById(R.id.btnCancel);
            this.f3228i.setOnClickListener(new C11111(this));
            super.onPreExecute();
        }

        protected /* synthetic */ void onProgressUpdate(String[] objArr) {
            m5782a((String[]) objArr);
        }
    }

    public class C1114c extends AsyncTask<Void, Void, Void> {
        ProgressDialog f3232a;
        C1611o f3233b;
        final /* synthetic */ ViewImageActivity f3234c;

        public C1114c(ViewImageActivity viewImageActivity, C1611o c1611o) {
            this.f3234c = viewImageActivity;
            this.f3233b = c1611o;
        }

        protected Void doInBackground(Void... voidArr) {
            new File(this.f3233b.f4585a).delete();
            return null;
        }

        protected void onPostExecute(Void voidR) {
            try {
                if (this.f3232a != null && this.f3232a.isShowing()) {
                    this.f3232a.dismiss();
                }
            } catch (Exception e) {
            }
            ViewAlbumActivity.f3158g.m5771a(this.f3234c.f3262o);
            this.f3234c.finish();
            super.onPostExecute(voidR);
        }


        protected void onPreExecute() {
            if (VERSION.SDK_INT >= 21) {
                this.f3232a = new ProgressDialog(this.f3234c, 16974394);
            } else {
                this.f3232a = new ProgressDialog(this.f3234c);
            }
            this.f3232a.setTitle("Please wait...");
            this.f3232a.setMessage("It takes a while, depending on file size");
            this.f3232a.setCancelable(false);
            this.f3232a.setProgressStyle(1);
            this.f3232a.show();
            super.onPreExecute();
        }
    }

    private void m5786a(String str) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.STREAM", FileProvider.getUriForFile(this, VideoViewNEWActivity.class.getName(), new File(str)));
        intent.setType("image/*");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(intent, "Share selected items"));
    }

    private void m5787a(String str, float f, ImageView imageView) {
        Glide.with(getApplicationContext()).load(str).transform(new C1562e(getApplicationContext(), f)).into(imageView);
    }

    private void m5787a(String str, float f, final SubsamplingScaleImageView imageView) {
        Glide.with(getApplicationContext()).load(str).transform(new C1562e(getApplicationContext(), f)).listener(new RequestListener<String, GlideDrawable>() {
            @Override
            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                imageView.setImage(ImageSource.bitmap(drawableToBitmap(resource)));
                return true;
            }
        }).preload();
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888); // Single color bitmap will be created of 1x1 pixel
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private void m5789b() {
        final C1611o c1611o = (C1611o) f3236t.get(this.f3262o);
        C2930c c2930c = new C2930c(getApplicationContext());
        ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        String b = c2930c.m14131b(new File(c1611o.f4585a).getName());
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
        final Dialog dialog = new Dialog(f3235s);
        View inflate = f3235s.getLayoutInflater().inflate(R.layout.dialog_restore_chooser, null);
        ListView listView = (ListView) inflate.findViewById(R.id.lvDirs);
        listView.setAdapter(new C1600k(arrayList, arrayList2, getApplicationContext()));
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                dialog.dismiss();
                C1131f.f3320f = (String) arrayList2.get(i);
                if (C1131f.f3320f == null) {
                    C1131f.f3320f = Environment.getExternalStorageDirectory() + "/Calculator";
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
                        new C1113b(ViewImageActivity.this, c1611o, z, null).execute(new Void[0]);
                    } else if (f3246J) {
                        dialog.dismiss();
                        f3246J = false;
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        new C1113b(ViewImageActivity.this, c1611o, z, null).execute(new Void[0]);
                    } else {
                        f3246J = true;
                        C3150b.m14836b(ViewImageActivity.this);
                    }
                } else if (z) {
                    String string = sharedPreferences.getString("treeUri", null);
                    if (string != null) {
                        new C1113b(ViewImageActivity.this, c1611o, z, C1132g.m5816b(getApplicationContext(), file, Uri.parse(string))).execute(new Void[0]);
                    } else {
                        f3242F = c1611o;
                        m5793d();
                    }
                } else {
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    new C1113b(ViewImageActivity.this, c1611o, z, null).execute(new Void[0]);
                }
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
        });
        dialog.setContentView(inflate);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        dialog.getWindow().setLayout(width, LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    private void m5791c() {
        this.trickyViewPager.setSystemUiVisibility(3846);
    }

    private void m5793d() {
        C3150b.m14832a((Activity) this, new C11044(this), true);
    }

    public void m5795a() {
        Animation loadAnimation = AnimationUtils.loadAnimation(f3235s, android.R.anim.fade_out);
        Animation loadAnimation2 = AnimationUtils.loadAnimation(f3235s, android.R.anim.fade_in);
        loadAnimation.setFillAfter(true);
        loadAnimation2.setFillAfter(true);
        loadAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                relativeLayout.setVisibility(View.GONE);
                linearLayout.setVisibility(View.GONE);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        loadAnimation2.setAnimationListener(new AnimationListener() {

            public void onAnimationEnd(Animation animation) {
                relativeLayout.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        if (this.relativeLayout.getVisibility() == View.VISIBLE) {

            if (VERSION.SDK_INT >= 19) {
                m5791c();
            }
            this.relativeLayout.startAnimation(loadAnimation);
            this.linearLayout.startAnimation(loadAnimation);
            return;
        }
        this.relativeLayout.startAnimation(loadAnimation2);
        this.linearLayout.startAnimation(loadAnimation2);
    }

    public void onPageSelected(int i) {
        this.f3262o = i;
        this.f3245I = 0;
        try {
            String name = new File(((C1611o) f3236t.get(this.f3262o)).f4585a).getName();
            if (name.length() > 15) {
                name = name.substring(0, 15) + "..";
            }
            this.textView.setText(name);
        } catch (Exception e) {
            this.textView.setText("");
        }
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageScrollStateChanged(int i) {
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.f3239C = false;
        if (i == 142 && i2 == -1) {
            Uri data = intent.getData();
            if (C1132g.m5814a(data)) {
                getContentResolver().takePersistableUriPermission(data, intent.getFlags() & Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                Editor edit = this.sharedPreferences.edit();
                edit.putString("treeUri", "" + data);
                edit.putString("extSdCardPath", C1131f.f3326l);
                edit.commit();
                try {
                    new C1113b(this, this.f3242F, true, C1132g.m5816b(getApplicationContext(), new File(C1131f.f3320f), data)).execute(new Void[0]);
                } catch (Exception e) {
                }
            } else {
                Toast.makeText(getApplicationContext(), "Grant Failed. Please choose the root directory of SD card", Toast.LENGTH_SHORT).show();
                m5793d();
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onClick(View view) {
        boolean z = true;
        Object imageView;
        LinearLayout linearLayout;
        String str;
        float floatValue;
        final Dialog dialog;
        View inflate;
        switch (view.getId()) {
            case R.id.rlBack:
                onBackPressed();
                return;
            case R.id.flRLeft:
                linearLayout = (LinearLayout) this.trickyViewPager.findViewWithTag("iv" + this.f3262o);
                str = ((C1611o) f3236t.get(this.f3262o)).f4585a;
                floatValue = ((Float) this.f3267v.get(this.f3262o)).floatValue() - 90.0f;
                this.f3267v.set(this.f3262o, Float.valueOf(floatValue));
                imageView = linearLayout.getChildAt(0);
                if (imageView instanceof SubsamplingScaleImageView) {
                    m5787a(str, floatValue, (SubsamplingScaleImageView) imageView);
                } else if (imageView instanceof ImageView) {
                    m5787a(str, floatValue, (ImageView) imageView);
                }

                return;
            case R.id.flRRight:
                linearLayout = (LinearLayout) this.trickyViewPager.findViewWithTag("iv" + this.f3262o);
                str = ((C1611o) f3236t.get(this.f3262o)).f4585a;
                floatValue = ((Float) this.f3267v.get(this.f3262o)).floatValue() + 90.0f;
                this.f3267v.set(this.f3262o, Float.valueOf(floatValue));
                imageView = linearLayout.getChildAt(0);

                if (imageView instanceof SubsamplingScaleImageView) {
                    m5787a(str, floatValue, (SubsamplingScaleImageView) imageView);
                } else if (imageView instanceof ImageView) {
                    m5787a(str, floatValue, (ImageView) imageView);
                }
                return;
            case R.id.flShare:
                if (this.f3257j.getVisibility() == View.VISIBLE) {
                    this.timerTask.cancel();
                    this.timer.cancel();
                    this.f3256i.setVisibility(View.VISIBLE);
                    this.f3257j.setVisibility(View.GONE);
                }
                m5786a(((C1611o) f3236t.get(this.f3262o)).f4585a);
                return;
            case R.id.flShuffle:
                CheckBox checkBox = this.checkBox;
                if (this.checkBox.isChecked()) {
                    z = false;
                }
                checkBox.setChecked(z);
                return;
            case R.id.rlExport:
                if (this.f3257j.getVisibility() == View.VISIBLE) {
                    this.timerTask.cancel();
                    this.timer.cancel();
                    this.f3256i.setVisibility(View.VISIBLE);
                    this.f3257j.setVisibility(View.GONE);
                }
                dialog = new Dialog(this);
                inflate = getLayoutInflater().inflate(R.layout.delete_dialog, null);
                dialog.setContentView(inflate);
                ((TextView) inflate.findViewById(R.id.textView1)).setText("Unlock");
                TextView textView = (TextView) inflate.findViewById(R.id.tv_dialogText);
                textView.setText("Do you want to unlock this Picture?");
                textView.setTypeface(C1131f.f3315a);
                ((TextView) inflate.findViewById(R.id.tvDelete)).setText("UNLOCK");
                inflate.findViewById(R.id.rlDelete).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        m5789b();
                        dialog.dismiss();
                    }
                });
                inflate.findViewById(R.id.rlCancel).setOnClickListener(new OnClickListener() {

                    public void onClick(View view) {
                        dialog.dismiss();
                        if (VERSION.SDK_INT >= 19) {
                            m5791c();
                        }
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                int width = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                dialog.getWindow().setLayout(width, LayoutParams.WRAP_CONTENT);
                dialog.show();
                return;
            case R.id.rlPrevious:
                if (this.f3266u) {
                    this.f3262o = this.random.nextInt(this.f3259l);
                } else if (this.f3262o > 0) {
                    this.f3262o--;
                } else {
                    this.f3262o = this.f3259l - 1;
                }
                this.trickyViewPager.setCurrentItem(this.f3262o);
                return;
            case R.id.btn_pause:
                this.timerTask.cancel();
                this.timer.cancel();
                this.f3256i.setVisibility(View.VISIBLE);
                this.f3257j.setVisibility(View.GONE);
                return;
            case R.id.btn_slideshow:
                if (this.relativeLayout.getVisibility() == View.VISIBLE) {
                    m5795a();
                }
//                this.trickyViewPager.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in));
                this.timer = new Timer();
                this.timerTask = new TimerTask() {
                    class C11001 implements Runnable {
                        public void run() {
                            if (f3262o >= f3259l) {
                                f3262o--;
                                timerTask.cancel();
                                timer.cancel();
                                if (relativeLayout.getVisibility() != View.VISIBLE) {
                                    m5795a();
                                }
                                f3256i.setVisibility(View.VISIBLE);
                                f3257j.setVisibility(View.GONE);
                            } else if (f3266u) {
                                f3262o = random.nextInt(f3259l);
                                trickyViewPager.setCurrentItem(f3262o, true);
                            } else {
                                TrickyViewPager trickyViewPager = ViewImageActivity.this.trickyViewPager;
                                int i = f3262o;
                                f3262o = i + 1;
                                trickyViewPager.setCurrentItem(i);
                            }
                        }
                    }

                    public void run() {
                        runOnUiThread(new C11001());
                    }
                };
                this.timer.scheduleAtFixedRate(this.timerTask, (long) this.f3263p, (long) this.f3263p);
                this.f3256i.setVisibility(View.GONE);
            case R.id.rlNext:
                if (this.f3266u) {
                    this.f3262o = this.random.nextInt(this.f3259l);
                } else if (this.f3262o < this.f3259l - 1) {
                    this.f3262o++;
                } else {
                    this.f3262o = 0;
                }
                this.trickyViewPager.setCurrentItem(this.f3262o, true);
                return;
            case R.id.rlDelete:
                if (this.f3257j.getVisibility() == View.VISIBLE) {
                    this.timerTask.cancel();
                    this.timer.cancel();
                    this.f3256i.setVisibility(View.VISIBLE);
                    this.f3257j.setVisibility(View.GONE);
                }
                dialog = new Dialog(this);
                inflate = getLayoutInflater().inflate(R.layout.delete_dialog, null);
                ((TextView) inflate.findViewById(R.id.textView1)).setTypeface(C1131f.f3315a);
                ((TextView) inflate.findViewById(R.id.tv_dialogText)).setTypeface(C1131f.f3315a);
                TextView textView1 = (TextView) inflate.findViewById(R.id.tv_dialogText);
                textView1.setText("delete selected file(s) permenently from phone?");
                dialog.setContentView(inflate);
                inflate.findViewById(R.id.rlDelete).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        new C1114c(ViewImageActivity.this, (C1611o) ViewImageActivity.f3236t.get(f3262o)).execute(new Void[0]);
                        dialog.dismiss();
                    }
                });
                inflate.findViewById(R.id.rlCancel).setOnClickListener(new OnClickListener() {

                    public void onClick(View view) {
                        dialog.dismiss();
                        if (VERSION.SDK_INT >= 19) {
                            m5791c();
                        }
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                int width2 = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
                dialog.getWindow().setLayout(width2, LayoutParams.WRAP_CONTENT);
                dialog.show();
                return;
            default:
                return;
        }
    }

    @SuppressLint("WrongConstant")
    @TargetApi(16)
    protected void onCreate(Bundle bundle) {
        int nextInt;
        super.onCreate(bundle);
        getWindow().addFlags(128);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.currentPath = getIntent().getStringExtra("currentPath");
        setContentView(R.layout.activity_pager);
        f3235s = this;
        this.random = new Random();
        this.f3263p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getInt("interval", 2) * 500;
        this.frameLayout = (FrameLayout) findViewById(R.id.flShuffle);
        this.frameLayout.setOnClickListener(this);
        this.checkBox = (CheckBox) findViewById(R.id.btnShuffle);
        this.checkBox.setOnCheckedChangeListener(new C11055(this));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.f3248a = displayMetrics.widthPixels;
        this.f3249b = displayMetrics.heightPixels;
        this.powerManager = (PowerManager) getSystemService("power");
        this.telephonyManager = (TelephonyManager) getSystemService("phone");
        findViewById(R.id.rlBack).setOnClickListener(this);
        findViewById(R.id.rlNext).setOnClickListener(this);
        findViewById(R.id.flRLeft).setOnClickListener(this);
        findViewById(R.id.flRRight).setOnClickListener(this);
        findViewById(R.id.flShare).setOnClickListener(this);
        findViewById(R.id.rlPrevious).setOnClickListener(this);
        findViewById(R.id.rlExport).setOnClickListener(this);
        findViewById(R.id.rlDelete).setOnClickListener(this);
        this.f3256i = (ImageButton) findViewById(R.id.btn_slideshow);
        this.f3256i.setOnClickListener(this);
        this.f3257j = (ImageButton) findViewById(R.id.btn_pause);
        this.f3257j.setOnClickListener(this);
        this.f3259l = f3236t.size();
        this.trickyViewPager = (TrickyViewPager) findViewById(R.id.viewPager);
        if (VERSION.SDK_INT >= 19) {
            m5791c();
        }
        this.trickyViewPager.setOnPageChangeListener(this);
        this.f3268w = this.f3269x[this.sharedPreferences.getInt("trans", 0)];
        if (this.f3268w != null) {
            this.trickyViewPager.setPageTransformer(true, this.f3268w);
        } else {
            nextInt = this.random.nextInt(this.f3269x.length - 1);
            if (nextInt == 1) {
                nextInt++;
            }
            this.trickyViewPager.setPageTransformer(true, this.f3269x[nextInt]);
        }
        this.f3244H = new PagerAdaperImages(this);
        this.trickyViewPager.setAdapter(this.f3244H);
        this.textView = (TextView) findViewById(R.id.tv_imagename);
        if (bundle != null) {
            this.trickyViewPager.setLocked(bundle.getBoolean("isLocked", false));
        }
        this.f3262o = getIntent().getIntExtra("position", 0);
        this.trickyViewPager.setCurrentItem(this.f3262o);
        this.relativeLayout = (RelativeLayout) findViewById(R.id.rlTop);
        this.linearLayout = (LinearLayout) findViewById(R.id.rlBottom);
        try {
            String name = new File(((C1611o) f3236t.get(this.f3262o)).f4585a).getName();
            if (name.length() > 15) {
                name = name.substring(0, 15) + "..";
            }
            this.textView.setText(name);
        } catch (Exception e) {
            this.textView.setText("");
        }
        this.f3267v = new ArrayList();
        for (nextInt = 0; nextInt <= f3236t.size(); nextInt++) {
            this.f3267v.add(Float.valueOf(0.0f));
        }
        if (getIntent().getBooleanExtra("play", false)) {
            onClick(this.f3256i);
        }
        try {
            if (this.sharedPreferences.getBoolean("faceDown", false)) {
                this.f3270y = this.sharedPreferences.getInt("selectedPos", 0);
                this.sensorManager = (SensorManager) getSystemService("sensor");
                this.sensor = (Sensor) this.sensorManager.getSensorList(1).get(0);
            }
        } catch (Exception e2) {
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        getWindow().clearFlags(128);
    }

    protected void onPause() {
        overridePendingTransition(0, R.anim.exit);
        super.onPause();
    }

    protected void onStart() {
        this.f3239C = false;
        try {
            if (this.sensorManager != null) {
                this.sensorManager.registerListener(this.f3247K, this.sensor, 3);
            }
        } catch (Exception e) {
        }
        super.onStart();
    }

    protected void onStop() {
        try {
            if (this.sensorManager != null) {
                this.sensorManager.unregisterListener(this.f3247K);
            }
        } catch (Exception e) {
        }
        if (this.telephonyManager != null) {
            new Timer().schedule(new C11011(this), 1000);
        }
        super.onStop();
    }
}
