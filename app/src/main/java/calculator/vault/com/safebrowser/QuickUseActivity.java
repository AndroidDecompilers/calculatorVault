package calculator.vault.com.safebrowser;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.lock.MainActivity;
import calculator.vault.com.lock.ManageSpaceActivity;
import calculator.vault.com.lock.ViewAlbumActivity;

public class QuickUseActivity extends AppCompatActivity {
    TelephonyManager f9400a;
    PowerManager f9401b;
    SensorManager f9402c;
    Sensor f9403d;
    public int f9404e;
    boolean f9405f;
    String f9406g;
    SharedPreferences f9407h;
    private SensorEventListener f9408i = new C30012(this);

    class C30001 implements OnClickListener {
        final /* synthetic */ QuickUseActivity f9397a;

        C30001(QuickUseActivity quickUseActivity) {
            this.f9397a = quickUseActivity;
        }

        public void onClick(View view) {
            this.f9397a.finish();
        }
    }

    class C30012 implements SensorEventListener {
        final /* synthetic */ QuickUseActivity f9398a;

        C30012(QuickUseActivity quickUseActivity) {
            this.f9398a = quickUseActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f9398a.f9405f) {
                    this.f9398a.f9405f = true;
                    if (this.f9398a.f9404e == 1) {
                        C1131f.m5806a(this.f9398a.getApplicationContext(), this.f9398a.getPackageManager(), this.f9398a.f9407h.getString("Package_Name", null));
                    }
                    if (this.f9398a.f9404e == 2) {
                        this.f9398a.f9406g = this.f9398a.f9407h.getString("URL_Name", null);
                        this.f9398a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f9398a.f9406g)));
                    }
                    if (this.f9398a.f9404e == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.f9398a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    class C30023 extends TimerTask {
        final /* synthetic */ QuickUseActivity f9399a;

        C30023(QuickUseActivity quickUseActivity) {
            this.f9399a = quickUseActivity;
        }

        public void run() {
            try {
                if (SecurityHelpers.isCallRinging(this.f9399a.f9400a) || !SecurityHelpers.m14854b(this.f9399a.getApplicationContext()).equals(this.f9399a.getPackageName())) {
                    this.f9399a.finish();
                    MainBrowserActivity.f9367a.finish();
                    MainActivity.mainActivity.finish();
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                }
                if (!SecurityHelpers.isScreenON(this.f9399a.f9401b)) {
                    this.f9399a.finish();
                    MainBrowserActivity.f9367a.finish();
                    MainActivity.mainActivity.finish();
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                    Intent intent = new Intent(this.f9399a.getApplicationContext(), ManageSpaceActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    this.f9399a.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_quickguide);
        this.f9401b = (PowerManager) getSystemService("power");
        this.f9400a = (TelephonyManager) getSystemService("phone");
        this.f9407h = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ((TextView) findViewById(R.id.tvOne)).setText(Html.fromHtml(getResources().getString(R.string.step_one)));
        ((TextView) findViewById(R.id.tvTwo)).setText(Html.fromHtml(getResources().getString(R.string.step_two)));
        findViewById(R.id.btnClose).setOnClickListener(new C30001(this));
        ((TextView) findViewById(R.id.tvOne)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.tvTwo)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.tvDetails)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.tvGuide)).setTypeface(C1131f.f3322h);
        ((TextView) findViewById(R.id.tvTitle)).setTypeface(C1131f.f3322h);
        if (VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(-16777216);
        }
        try {
            if (this.f9407h.getBoolean("faceDown", false)) {
                this.f9404e = this.f9407h.getInt("selectedPos", 0);
                this.f9402c = (SensorManager) getSystemService("sensor");
                this.f9403d = (Sensor) this.f9402c.getSensorList(1).get(0);
                this.f9402c.registerListener(this.f9408i, this.f9403d, 3);
            }
        } catch (Exception e) {
        }
    }

    protected void onPause() {
        overridePendingTransition(0, R.anim.slide_right);
        super.onPause();
    }

    protected void onResume() {
        overridePendingTransition(R.anim.slide_left, 0);
        super.onResume();
    }

    protected void onStop() {
        try {
            if (this.f9402c != null) {
                this.f9402c.unregisterListener(this.f9408i);
            }
        } catch (Exception e) {
        }
        if (this.f9400a != null) {
            new Timer().schedule(new C30023(this), 1000);
        }
        super.onStop();
    }
}
