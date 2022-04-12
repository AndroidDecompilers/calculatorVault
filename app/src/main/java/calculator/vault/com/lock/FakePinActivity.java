package calculator.vault.com.lock;

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
import android.support.v7.widget.SwitchCompat;
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.applock.SecurityHelpers;

public class FakePinActivity extends AppCompatActivity implements OnClickListener {
    PowerManager f2803a;
    TelephonyManager f2804b;
    SharedPreferences f2805c;
    Editor f2806d;
    LinearLayout f2807e;
    LinearLayout f2808f;
    Button f2809g;
    public int f2810h;
    SensorManager f2811i;
    Sensor f2812j;
    boolean f2813k;
    String f2814l;
    private int f2815m;
    private SensorEventListener f2817o = new C09964(this);

    class C09953 extends TimerTask {
        final /* synthetic */ FakePinActivity f2801a;

        C09953(FakePinActivity fakePinActivity) {
            this.f2801a = fakePinActivity;
        }

        @SuppressLint("WrongConstant")
        public void run() {
            try {
                if (SecurityHelpers.isCallRinging(this.f2801a.f2804b) || !SecurityHelpers.m14854b(this.f2801a.getApplicationContext()).equals(this.f2801a.getPackageName())) {
                    this.f2801a.finish();
                    MainActivity.mainActivity.finish();
                } else if (!SecurityHelpers.isScreenON(this.f2801a.f2803a)) {
                    this.f2801a.finish();
                    MainActivity.mainActivity.finish();
                    Intent intent = new Intent(this.f2801a.getApplicationContext(), ManageSpaceActivity.class);
                    intent.addFlags(67108864);
                    this.f2801a.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C09964 implements SensorEventListener {
        final /* synthetic */ FakePinActivity f2802a;

        C09964(FakePinActivity fakePinActivity) {
            this.f2802a = fakePinActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f2802a.f2813k) {
                    this.f2802a.f2813k = true;
                    if (this.f2802a.f2810h == 1) {
                        C1131f.m5806a(this.f2802a.getApplicationContext(), this.f2802a.getPackageManager(), this.f2802a.f2805c.getString("Package_Name", null));
                    }
                    if (this.f2802a.f2810h == 2) {
                        this.f2802a.f2814l = this.f2802a.f2805c.getString("URL_Name", null);
                        this.f2802a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f2802a.f2814l)));
                    }
                    if (this.f2802a.f2810h == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        this.f2802a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && i == 121) {
            this.f2808f.setVisibility(View.GONE);
            this.f2807e.setVisibility(View.VISIBLE);
            this.f2809g.setEnabled(true);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.btnChangePassword:
                intent = new Intent(getApplicationContext(), SetAppPasswordActivity.class);
                intent.putExtra("fromFake", true);
                startActivityForResult(intent, 121);
                return;
            case R.id.rl_view:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("fromFake", true);
                intent.putExtra("fromView", true);
                startActivityForResult(intent, 444);
                return;
            default:
                return;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f2805c = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f2815m = C1131f.getCurrentStyle(this.f2805c);
        if (this.f2815m != R.style.AppTheme) {
            setTheme(this.f2815m);
        }
        setContentView(R.layout.activity_fake_pin);
        this.f2807e = (LinearLayout) findViewById(R.id.llbtns);
        this.f2808f = (LinearLayout) findViewById(R.id.llHint);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.f2806d = this.f2805c.edit();
        this.f2807e.setVisibility(this.f2805c.getString("fakePin", "").equals("") ? View.GONE : View.VISIBLE);
        if (this.f2807e.getVisibility() == View.GONE) {
            this.f2808f.setVisibility(View.VISIBLE);
        }
        this.f2809g = (Button) findViewById(R.id.btnChangePassword);
        this.f2809g.setEnabled(this.f2805c.getBoolean("fakeEnabled", false));
        findViewById(R.id.btnChangePassword).setOnClickListener(this);
        findViewById(R.id.rl_view).setOnClickListener(this);
        SwitchCompat switchCompat = (SwitchCompat) findViewById(R.id.btnEnable);
        boolean z = this.f2805c.getBoolean("fakeEnabled", false);
        switchCompat.setChecked(z);
        final TextView textView = (TextView) findViewById(R.id.textView3);
        textView.setText(z ? "Enabled" : "Disabled");
        switchCompat.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                textView.setText(z ? "Enabled" : "Disabled");

                if (f2805c.getString("fakePin", "").equals("")) {
                    Intent intent = new Intent(getApplicationContext(), SetAppPasswordActivity.class);
                    intent.putExtra("fromFake", true);
                    startActivityForResult(intent, 121);
                } else if (!z) {
                    f2809g.setEnabled(false);
                    f2806d.putBoolean("fakeEnabled", false);
                    f2806d.commit();
                } else if (f2807e.getVisibility() == View.GONE) {
                    f2809g.setEnabled(true);
                    f2806d.putBoolean("fakeEnabled", true);
                    f2806d.commit();
                }
            }
        });
        this.f2803a = (PowerManager) getSystemService(POWER_SERVICE);
        this.f2804b = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        ((TextView) findViewById(R.id.textView2)).setTypeface(C1131f.f3315a);
        textView.setTypeface(C1131f.f3315a);
        this.f2809g.setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.textView4)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.textView6)).setTypeface(C1131f.f3315a);
        try {
            if (this.f2805c.getBoolean("faceDown", false)) {
                this.f2810h = this.f2805c.getInt("selectedPos", 0);
                this.f2811i = (SensorManager) getSystemService(SENSOR_SERVICE);
                this.f2812j = (Sensor) this.f2811i.getSensorList(1).get(0);
            }
        } catch (Exception e) {
        }
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
        overridePendingTransition(0, R.anim.exit);
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStart() {
        try {
            if (this.f2811i != null) {
                this.f2811i.registerListener(this.f2817o, this.f2812j, 3);
            }
        } catch (Exception e) {
        }
        super.onStart();
    }

    protected void onStop() {
        try {
            if (this.f2811i != null) {
                this.f2811i.unregisterListener(this.f2817o);
            }
        } catch (Exception e) {
        }
        if (this.f2804b != null) {
            new Timer().schedule(new C09953(this), 1000);
        }
        super.onStop();
    }
}
