package calculator.vault.com.lock;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.applock.MyAppLockService;
import calculator.vault.com.p068a.C1625q;
import calculator.vault.com.p073g.ApplockerFragment;
import calculator.vault.com.p073g.PhotoFolderViewFragment;
import calculator.vault.com.p073g.C1712c;
import calculator.vault.com.p073g.C1735d;
import calculator.vault.com.p084i.C2928a;
import calculator.vault.com.p084i.C2929b;

public class MainActivity extends AppCompatActivity {

    public static MainActivity mainActivity;
    private SensorEventListener f2884C = new C10146(this);
    SharedPreferences sharedPreferences;
    Editor editor;
    PowerManager powerManager;
    TelephonyManager telephonyManager;
    boolean fromFake;
    boolean fromView;
    public boolean f2891h;
    public boolean f2892i;
    SensorManager sensorManager;
    Sensor sensor;
    public boolean f2895l;
    public int selectedPos;
    boolean f2897n = false;
    boolean f2898o;
    String f2899p;
    boolean f2900q;
    int currentStyle;
    boolean f2902s;
    C2929b f2903t;
    boolean f2904u;
    C2928a f2905v;
    C1712c.C1700a f2906w;
    private FragmentTabHost f2907x;

    class C10102 implements Runnable {
        final /* synthetic */ MainActivity f2874a;

        C10102(MainActivity mainActivity) {
            this.f2874a = mainActivity;
        }

        public void run() {
            this.f2874a.startActivity(new Intent(this.f2874a.getApplicationContext(), SetEmailActivity.class));
        }
    }

    class C10113 implements Runnable {
        final /* synthetic */ MainActivity f2875a;

        C10113(MainActivity mainActivity) {
            this.f2875a = mainActivity;
        }

        public void run() {
            this.f2875a.f2904u = false;
        }
    }


    class C10146 implements SensorEventListener {
        final /* synthetic */ MainActivity f2878a;

        C10146(MainActivity mainActivity) {
            this.f2878a = mainActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            float f = sensorEvent.values[2];
            if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f2878a.f2900q) {
                this.f2878a.f2900q = true;
                if (this.f2878a.selectedPos == 1) {
                    C1131f.m5806a(this.f2878a.getApplicationContext(), this.f2878a.getPackageManager(), this.f2878a.sharedPreferences.getString("Package_Name", null));
                } else if (this.f2878a.selectedPos == 2) {
                    this.f2878a.f2899p = this.f2878a.sharedPreferences.getString("URL_Name", null);
                    this.f2878a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f2878a.f2899p)));
                    this.f2878a.f2897n = true;
                    this.f2878a.selectedPos = 2;
                } else if (this.f2878a.selectedPos == 0) {
                    Intent intent = new Intent("android.intent.action.MAIN");
                    intent.addCategory("android.intent.category.HOME");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    this.f2878a.startActivity(intent);
                }
            }
        }
    }

    private void m5616a(String str) {
        Log.d("donna", str);
    }

    private void m5617b(int i) {
        String str = null;
        String resourceName;
        try {
            resourceName = getResources().getResourceName(i);
            try {
                if (new File(resourceName).getName().contains("AppTheme")) {
                    str = resourceName;
                }
            } catch (NotFoundException e) {
                str = resourceName;
                if (str != null) {
                    this.currentStyle = R.style.AppTheme;
                    this.editor.putInt("currentStyle", R.style.AppTheme);
                    this.editor.commit();
                }
            }
        } catch (NotFoundException e2) {
            resourceName = null;
            str = resourceName;
            if (str != null) {
                this.currentStyle = R.style.AppTheme;
                this.editor.putInt("currentStyle", R.style.AppTheme);
                this.editor.commit();
            }
        }
        if (str != null) {
            this.currentStyle = R.style.AppTheme;
            this.editor.putInt("currentStyle", R.style.AppTheme);
            this.editor.commit();
        }
    }

    private void m5619h() {
        File[] a = ContextCompat.getExternalFilesDirs(getApplicationContext(), "");
        if (a != null && a.length > 1) {
            String replace = new File(a[1], "").getAbsolutePath().replace("/Android/data/" + getPackageName() + "/files", "");
            if (replace.length() > 2 && !replace.contains(getPackageName())) {
                C1131f.f3326l = replace;
            }
            if (this.sharedPreferences.getString("treeUri", null) != null) {
                replace = this.sharedPreferences.getString("extSdCardPath", null);
                if (replace != null && !replace.equals(C1131f.f3326l)) {
                    this.editor.putString("treeUri", null);
                    this.editor.commit();
                }
            }
        }
    }


    public void m5621a(int i) {
        this.selectedPos = i;
    }

    public void mo954a(ArrayList<?> arrayList) {
        if (this.f2903t != null) {
            this.f2903t.m14128b();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                C1625q c1625q = (C1625q) it.next();
                this.f2903t.m14127a(c1625q.f4624a, c1625q.f4625b, c1625q.f4627d, c1625q.f4626c, c1625q.f4628e, c1625q.f4629f);
            }
        }
    }

    public void m5626a(boolean z) {
        if (PhotoFolderViewFragment.f4765e != null) {
            PhotoFolderViewFragment.f4765e.m7380a(z);
        }
        if (C1735d.f4874a != null) {
            C1735d.f4874a.m7448a(z);
        }
    }

    public void a_() {
    }

    public void m5628c() {
        this.sensorManager.registerListener(this.f2884C, this.sensor, 3);
        this.f2898o = true;
    }

    public void m5629d() {
        if (this.f2898o) {
            this.f2898o = false;
            try {
                this.sensorManager.unregisterListener(this.f2884C);
                this.sensorManager = null;
            } catch (Exception e) {
            }
        }
    }

    public boolean m5630e() {
        if (this.sensorManager == null) {
            try {
                this.sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                List sensorList = this.sensorManager.getSensorList(1);
                if (sensorList.size() > 0) {
                    this.f2895l = true;
                    this.sensor = (Sensor) sensorList.get(0);
                } else {
                    this.f2895l = false;
                }
            } catch (Exception e) {
            }
        }
        return this.f2895l;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (this.fromView) {
            C1131f.f3318d = getFilesDir() + "/lockerVault";
            this.f2904u = true;
        }
        if (this.f2904u) {
            super.onBackPressed();
            if (!this.fromFake) {
                Intent intent = new Intent(getApplicationContext(), ManageSpaceActivity.class);
                intent.putExtra("fromBackGalleryLock", true);
                startActivity(intent);
                return;
            }
            return;
        }
        this.f2904u = true;
        Toast.makeText(this, "click back again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new C10113(this), 2000);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.editor = this.sharedPreferences.edit();
        this.fromFake = getIntent().getBooleanExtra("fromFake", false);
        this.fromView = getIntent().getBooleanExtra("fromView", false);
        this.selectedPos = this.sharedPreferences.getInt("selectedPos", 0);
        if (!(this.fromFake || this.fromView)) {
            if (!getIntent().hasExtra("mPass")) {
                finish();
                Toast.makeText(getApplicationContext(), "password required to open vault", Toast.LENGTH_SHORT).show();
            } else if (!getIntent().getStringExtra("mPass").equals(this.sharedPreferences.getString("password", "000"))) {
                finish();
                Toast.makeText(getApplicationContext(), "Wrong password to open vault", Toast.LENGTH_LONG).show();
            }
        }
        this.currentStyle = C1131f.getCurrentStyle(this.sharedPreferences);
        try {
            int i = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode % 10000;
            if (i > this.sharedPreferences.getInt("vCode", 105)) {
                m5617b(this.currentStyle);
                this.editor.putInt("vCode", i);
                this.editor.commit();
//                C1129e c1129e = new C1129e(this);
            }
        } catch (NameNotFoundException e) {
        }
        if (this.currentStyle != R.style.AppTheme) {
            setTheme(this.currentStyle);
        }
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mainActivity = this;
        C1131f.f3318d = getFilesDir() + "/lockerVault";
        C1131f.f3319e = C1131f.f3318d;
        C1131f.f3320f = Environment.getExternalStorageDirectory() + "/GalleryLock";
        this.f2898o = this.sharedPreferences.getBoolean("faceDown", false);
        boolean z = this.sharedPreferences.getBoolean("isOldFirst", false);
        C1131f.f3324j = z;
        this.f2902s = z;
        C1131f.f3321g = true;
        this.powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        this.telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        this.f2905v = C2928a.m14118a((Context) this);
        this.f2892i = this.sharedPreferences.getBoolean("hideAd", false);
        if (!this.f2892i) {
            this.f2903t = new C2929b(this);
        }
        if (this.sharedPreferences.getString("regEmail", "").length() < 2) {
            new Handler().postDelayed(new C10102(this), 1000);
        }
        if (this.fromFake) {
            C1131f.f3318d = getFilesDir() + "/lockerVault/FVault";
        } else {
            C1131f.f3318d = getFilesDir() + "/lockerVault";
        }
        C1131f.f3320f = Environment.getExternalStorageDirectory() + "/GalleryLock";
        File file = new File(C1131f.f3318d);
        if (!file.exists()) {
            file.mkdirs();
            new File(C1131f.f3318d + "/Images1769/Pictures").mkdirs();
            new File(C1131f.f3318d + "/Images1769/Downloads").mkdirs();
            new File(C1131f.f3318d + "/Videos1769/Videos").mkdirs();
            new File(C1131f.f3318d + "/Videos1769/Downloads").mkdirs();
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        C1131f.f3317c = displayMetrics.heightPixels;
        C1131f.f3316b = displayMetrics.widthPixels;
        C1131f.f3315a = Typeface.createFromAsset(getAssets(), "tf.ttf");
        C1131f.f3322h = Typeface.createFromAsset(getAssets(), "RoboBold.ttf");
        this.f2907x = (FragmentTabHost) findViewById(R.id.tabhost);
        this.f2907x.setup((Context) this, getSupportFragmentManager(), android.R.id.tabcontent);
        this.f2907x.getTabWidget().setShowDividers(0);
        LayoutParams layoutParams = C1131f.f3316b == 540 ? new LayoutParams(C1131f.f3316b / 4, 85) : C1131f.f3316b == 1080 ? new LayoutParams(C1131f.f3316b / 4, 169) : C1131f.f3316b == 1440 ? new LayoutParams(C1131f.f3316b / 4, 225) : new LayoutParams(C1131f.f3316b / 4, getActionBarSize());
        Bundle bundle2 = new Bundle();
        bundle2.putString("key", "Images");
        bundle2.putBoolean("fromFake", this.fromFake);
        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.pictures_tab_selector);
        imageView.setScaleType(ScaleType.FIT_CENTER);
        TabSpec newTabSpec = this.f2907x.newTabSpec("images");
        newTabSpec.setIndicator(imageView);
        this.f2907x.addTab(newTabSpec, PhotoFolderViewFragment.class, bundle2);
        bundle2 = new Bundle();
        bundle2.putString("key", "Videos");
        bundle2.putBoolean("fromFake", this.fromFake);
        imageView = new ImageView(getApplicationContext());
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.videos_tab_selector);
        imageView.setScaleType(ScaleType.FIT_CENTER);
        newTabSpec = this.f2907x.newTabSpec("videos");
        newTabSpec.setIndicator(imageView);
        this.f2907x.addTab(newTabSpec, C1735d.class, bundle2);
        bundle2 = new Bundle();
        bundle2.putString("key", "Applock");
        imageView = new ImageView(getApplicationContext());
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.applock_tab_selector);
        imageView.setScaleType(ScaleType.FIT_CENTER);
        newTabSpec = this.f2907x.newTabSpec("applock");
        newTabSpec.setIndicator(imageView);
        this.f2907x.addTab(newTabSpec, ApplockerFragment.class, bundle2);
        bundle2 = new Bundle();
        bundle2.putString("key", "Setting");
        imageView = new ImageView(getApplicationContext());
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.setting_tab_selector);
        imageView.setScaleType(ScaleType.FIT_CENTER);
        TabSpec newTabSpec2 = this.f2907x.newTabSpec("setting");
        newTabSpec2.setIndicator(imageView);
        this.f2907x.addTab(newTabSpec2, C1712c.class, bundle2);
        this.f2907x.getTabWidget().setStripEnabled(false);
        this.f2907x.getTabWidget().getChildAt(0).setBackgroundColor(0);
        this.f2907x.getTabWidget().getChildAt(1).setBackgroundColor(0);
        this.f2907x.getTabWidget().getChildAt(2).setBackgroundColor(0);
        this.f2907x.getTabWidget().getChildAt(3).setBackgroundColor(0);
        if (this.fromFake) {
            this.f2907x.getTabWidget().getChildTabViewAt(2).setEnabled(false);
            this.f2907x.getTabWidget().getChildTabViewAt(3).setEnabled(false);
        }
        if (this.sharedPreferences.getBoolean("isNew", false)) {
            startActivity(new Intent(getApplicationContext(), IntruderActivity.class));
        }
        if (this.f2898o) {
            try {
                this.sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
                List sensorList = this.sensorManager.getSensorList(1);
                if (sensorList.size() > 0) {
                    this.f2895l = true;
                    this.sensor = (Sensor) sensorList.get(0);
                } else {
                    this.f2895l = false;
                }
            } catch (Exception e2) {
            }
            this.sensorManager.registerListener(this.f2884C, this.sensor, 3);
        }
        try {
            m5619h();
        } catch (Exception e3) {
        }
    }

    private int getActionBarSize() {

        final TypedArray styledAttributes = getTheme().obtainStyledAttributes(
                new int[]{android.R.attr.actionBarSize});
        int mActionBarSize = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();
        return mActionBarSize == 0 ? LayoutParams.WRAP_CONTENT : mActionBarSize;
    }

    protected void onDestroy() {
        if (this.fromView) {
            C1131f.f3318d = getFilesDir() + "/lockerVault";
        }
        super.onDestroy();
    }

    protected void onPause() {
        boolean z = this.f2905v != null && this.f2905v.m14119a().size() > 0;
        if (!this.sharedPreferences.getBoolean("isFrozen", false)) {
            if (z) {
                this.editor.putBoolean("startApplock", true);
                this.editor.commit();
                if (!this.sharedPreferences.getBoolean("isAccess", false)) {
                    startService(new Intent(getApplicationContext(), MyAppLockService.class));
                    sendBroadcast(new Intent(SecurityHelpers.f9999a));
                }
            } else {
                this.editor.putBoolean("startApplock", false);
                this.editor.commit();
                sendBroadcast(new Intent(SecurityHelpers.f10000b));
            }
        }
        super.onPause();
    }

    protected void onResume() {
        try {
            if (this.sensorManager != null) {
                this.sensorManager.registerListener(this.f2884C, this.sensor, 3);
            }
        } catch (Exception e) {
        }
        if (this.sharedPreferences == null) {
            this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        }
        if (C1131f.getCurrentStyle(this.sharedPreferences) != this.currentStyle) {
            this.currentStyle = C1131f.getCurrentStyle(this.sharedPreferences);
            Intent intent = getIntent();
            overridePendingTransition(0, 0);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            finish();
            overridePendingTransition(0, 0);
            startActivity(intent);
        }
        super.onResume();
    }

    protected void onStart() {
        this.f2891h = false;
        mainActivity = this;
        super.onStart();
    }

    protected void onStop() {
        if (this.f2898o) {
            this.sensorManager.unregisterListener(this.f2884C);
        }
        if (this.telephonyManager != null) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        if (SecurityHelpers.isCallRinging(telephonyManager) || !(SecurityHelpers.m14854b(getApplicationContext()).equals(getPackageName()) || f2891h)) {
                            finish();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (!SecurityHelpers.isScreenON(powerManager)) {
                        finish();
                    }
                }
            }, 500);
        }
        super.onStop();
    }
}
