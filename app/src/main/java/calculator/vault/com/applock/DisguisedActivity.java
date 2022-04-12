package calculator.vault.com.applock;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.lock.MainActivity;
import calculator.vault.com.lock.ManageSpaceActivity;

public class DisguisedActivity extends AppCompatActivity implements OnClickListener {
    public static DisguisedActivity f9806k;
    FrameLayout f9807a;
    FrameLayout f9808b;
    FrameLayout f9809c;
    ImageView f9810d;
    ImageView f9811e;
    ImageView f9812f;
    SharedPreferences f9813g;
    Editor f9814h;
    PowerManager f9815i;
    TelephonyManager f9816j;
    SensorManager f9817l;
    Sensor f9818m;
    public int f9819n;
    boolean f9820o;
    String f9821p;
    private int f9822q;
    private SensorEventListener f9824s = new C31093(this);

    class C31082 extends TimerTask {
        final /* synthetic */ DisguisedActivity f9804a;

        C31082(DisguisedActivity disguisedActivity) {
            this.f9804a = disguisedActivity;
        }

        public void run() {
            try {
                if (SecurityHelpers.isCallRinging(this.f9804a.f9816j) || !SecurityHelpers.m14854b(this.f9804a.getApplicationContext()).equals(this.f9804a.getPackageName())) {
                    this.f9804a.finish();
                    if (MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                    }
                }
                if (!SecurityHelpers.isScreenON(this.f9804a.f9815i)) {
                    this.f9804a.finish();
                    MainActivity.mainActivity.finish();
                    Intent intent = new Intent(this.f9804a.getApplicationContext(), ManageSpaceActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    this.f9804a.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C31093 implements SensorEventListener {
        final /* synthetic */ DisguisedActivity f9805a;

        C31093(DisguisedActivity disguisedActivity) {
            this.f9805a = disguisedActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f9805a.f9820o) {
                    this.f9805a.f9820o = true;
                    if (this.f9805a.f9819n == 1) {
                        C1131f.m5806a(this.f9805a.getApplicationContext(), this.f9805a.getPackageManager(), this.f9805a.f9813g.getString("Package_Name", null));
                    }
                    if (this.f9805a.f9819n == 2) {
                        this.f9805a.f9821p = this.f9805a.f9813g.getString("URL_Name", null);
                        this.f9805a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f9805a.f9821p)));
                    }
                    if (this.f9805a.f9819n == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.f9805a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if ((i == 131 || i == 141) && i2 == -1) {
            Toast.makeText(getApplicationContext(), "Fake Cover has been set", Toast.LENGTH_SHORT).show();
            this.f9814h.putBoolean("isFakeCover", true);
            this.f9814h.commit();
            this.f9810d.setVisibility(View.GONE);
            if (i == 131) {
                this.f9811e.setVisibility(View.VISIBLE);
                this.f9812f.setVisibility(View.GONE);
                this.f9814h.putBoolean("isFakeCoverFinger", false);
            } else {
                this.f9812f.setVisibility(View.VISIBLE);
                this.f9811e.setVisibility(View.GONE);
                this.f9814h.putBoolean("isFakeCoverFinger", true);
            }
            this.f9814h.commit();
        } else if ((i == 131 || i == 141) && i2 != -1) {
            Toast.makeText(getApplicationContext(), "Please complete action to apply cover.", Toast.LENGTH_SHORT).show();
        }
        super.onActivityResult(i, i2, intent);
    }

    @SuppressLint("WrongConstant")
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.flIcon0:
                this.f9810d.setVisibility(0);
                this.f9811e.setVisibility(8);
                this.f9812f.setVisibility(8);
                this.f9814h.putBoolean("isFakeCover", false);
                this.f9814h.commit();
                Toast.makeText(getApplicationContext(), "Fake Cover Disabled", Toast.LENGTH_SHORT).show();
                return;
            case R.id.flIcon1:
                startActivityForResult(new Intent(getApplicationContext(), DisguisedForceStopDemoActivity.class), 131);
                return;
            case R.id.flIcon2:
                startActivityForResult(new Intent(getApplicationContext(), DisguisedFPDemoActivity.class), 141);
                return;
            default:
                return;
        }
    }

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f9813g = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f9822q = C1131f.getCurrentStyle(this.f9813g);
        if (this.f9822q != R.style.AppTheme) {
            setTheme(this.f9822q);
        }
        setContentView((int) R.layout.applock_disguise);
        f9806k = this;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.f9814h = this.f9813g.edit();
        this.f9815i = (PowerManager) getSystemService(POWER_SERVICE);
        this.f9816j = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        this.f9807a = (FrameLayout) findViewById(R.id.flIcon0);
        this.f9808b = (FrameLayout) findViewById(R.id.flIcon1);
        this.f9809c = (FrameLayout) findViewById(R.id.flIcon2);
        this.f9807a.setOnClickListener(this);
        this.f9809c.setOnClickListener(this);
        this.f9808b.setOnClickListener(this);
        this.f9810d = (ImageView) findViewById(R.id.ivTick0);
        this.f9811e = (ImageView) findViewById(R.id.ivTick1);
        this.f9812f = (ImageView) findViewById(R.id.ivTick2);
        if (this.f9813g.getBoolean("isFakeCover", false)) {
            this.f9810d.setVisibility(8);
            if (this.f9813g.getBoolean("isFakeCoverFinger", false)) {
                this.f9812f.setVisibility(0);
                this.f9811e.setVisibility(8);
            } else {
                this.f9811e.setVisibility(0);
                this.f9812f.setVisibility(8);
            }
        }
        try {
            if (this.f9813g.getBoolean("faceDown", false)) {
                this.f9819n = this.f9813g.getInt("selectedPos", 0);
                this.f9817l = (SensorManager) getSystemService("sensor");
                this.f9818m = (Sensor) this.f9817l.getSensorList(1).get(0);
            }
        } catch (Exception e) {
        }
        ((TextView) findViewById(R.id.textView3)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.textView4)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.textView6)).setTypeface(C1131f.f3315a);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    protected void onPause() {
        overridePendingTransition(0, R.anim.slide_right);
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStart() {
        try {
            if (this.f9817l != null) {
                this.f9817l.registerListener(this.f9824s, this.f9818m, 3);
            }
        } catch (Exception e) {
        }
        super.onStart();
    }

    protected void onStop() {
        try {
            if (this.f9817l != null) {
                this.f9817l.unregisterListener(this.f9824s);
            }
        } catch (Exception e) {
        }
        if (this.f9816j != null) {
            new Timer().schedule(new C31082(this), 1000);
        }
        super.onStop();
    }
}
