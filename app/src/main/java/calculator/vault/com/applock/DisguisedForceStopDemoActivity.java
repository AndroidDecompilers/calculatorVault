package calculator.vault.com.applock;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.lock.MainActivity;
import calculator.vault.com.lock.ManageSpaceActivity;

public class DisguisedForceStopDemoActivity extends AppCompatActivity {
    ImageView f9873a;
    int f9874b;
    PowerManager f9875c;
    TelephonyManager f9876d;
    SensorManager f9877e;
    Sensor f9878f;
    public int f9879g;
    boolean f9880h;
    String f9881i;
    SharedPreferences f9882j;
    int f9883k;
    int f9884l;
    boolean f9885m = false;
    private int f9886n;
    private SensorEventListener f9887o = new C31266(this);

    class C31233 implements OnClickListener {
        final /* synthetic */ DisguisedForceStopDemoActivity f9865a;

        C31233(DisguisedForceStopDemoActivity disguisedForceStopDemoActivity) {
            this.f9865a = disguisedForceStopDemoActivity;
        }

        public void onClick(View view) {
            Toast.makeText(this.f9865a.getApplicationContext(), "Swipe from right to left on button.", Toast.LENGTH_SHORT).show();
        }
    }

    class C31244 implements OnTouchListener {
        final /* synthetic */ DisguisedForceStopDemoActivity f9866a;

        C31244(DisguisedForceStopDemoActivity disguisedForceStopDemoActivity) {
            this.f9866a = disguisedForceStopDemoActivity;
        }

        @SuppressLint("WrongConstant")
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.f9866a.f9883k = (int) motionEvent.getX();
                    if (this.f9866a.f9874b < 50) {
                        this.f9866a.f9874b = 80;
                        break;
                    }
                    break;
                case 1:
                    this.f9866a.f9884l = (int) motionEvent.getX();
                    if (this.f9866a.f9883k - this.f9866a.f9884l <= this.f9866a.f9874b && !this.f9866a.f9885m) {
                        Toast.makeText(this.f9866a.getApplicationContext(), "Swipe from right to left on button.", 0).show();
                        break;
                    }
                    this.f9866a.f9885m = false;
                    break;
                case 2:
                    this.f9866a.f9884l = (int) motionEvent.getX();
                    if (this.f9866a.f9883k - this.f9866a.f9884l >= this.f9866a.f9874b) {
                        this.f9866a.f9885m = true;
                        this.f9866a.m14802a();
                        DisguisedForceStopDemoActivity disguisedForceStopDemoActivity = this.f9866a;
                        this.f9866a.f9884l = 0;
                        disguisedForceStopDemoActivity.f9883k = 0;
                        break;
                    }
                    break;
            }
            return false;
        }
    }

    class C31255 extends TimerTask {
        final /* synthetic */ DisguisedForceStopDemoActivity f9867a;

        C31255(DisguisedForceStopDemoActivity disguisedForceStopDemoActivity) {
            this.f9867a = disguisedForceStopDemoActivity;
        }

        @SuppressLint("WrongConstant")
        public void run() {
            try {
                if (SecurityHelpers.isCallRinging(this.f9867a.f9876d) || !SecurityHelpers.m14854b(this.f9867a.getApplicationContext()).equals(this.f9867a.getPackageName())) {
                    this.f9867a.finish();
                    MainActivity.mainActivity.finish();
                }
                if (!SecurityHelpers.isScreenON(this.f9867a.f9875c)) {
                    this.f9867a.finish();
                    MainActivity.mainActivity.finish();
                    Intent intent = new Intent(this.f9867a.getApplicationContext(), ManageSpaceActivity.class);
                    intent.addFlags(67108864);
                    this.f9867a.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C31266 implements SensorEventListener {
        final /* synthetic */ DisguisedForceStopDemoActivity f9868a;

        C31266(DisguisedForceStopDemoActivity disguisedForceStopDemoActivity) {
            this.f9868a = disguisedForceStopDemoActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @SuppressLint("WrongConstant")
        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f9868a.f9880h) {
                    this.f9868a.f9880h = true;
                    if (this.f9868a.f9879g == 1) {
                        C1131f.m5806a(this.f9868a.getApplicationContext(), this.f9868a.getPackageManager(), this.f9868a.f9882j.getString("Package_Name", null));
                    }
                    if (this.f9868a.f9879g == 2) {
                        this.f9868a.f9881i = this.f9868a.f9882j.getString("URL_Name", null);
                        this.f9868a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f9868a.f9881i)));
                    }
                    if (this.f9868a.f9879g == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(268435456);
                        this.f9868a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    private void m14802a() {
        final Dialog dialog = new Dialog(this);
        View inflate = getLayoutInflater().inflate(R.layout.demo_success_dialog, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tvOk);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tvCancel);
        textView.setTypeface(C1131f.f3322h);
        textView2.setTypeface(C1131f.f3322h);
        ((TextView) inflate.findViewById(R.id.tv_dialogText)).setTypeface(C1131f.f3315a);
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                setResult(RESULT_OK);
                finish();
            }
        });
        textView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(inflate);
        dialog.show();
    }

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f9882j = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f9886n = C1131f.getCurrentStyle(this.f9882j);
        if (this.f9886n != R.style.AppTheme) {
            setTheme(this.f9886n);
        }
        setContentView((int) R.layout.applock_forcestop_cover_demo);
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().setStatusBarColor(-16777216);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.f9875c = (PowerManager) getSystemService("power");
        this.f9876d = (TelephonyManager) getSystemService("phone");
        this.f9873a = (ImageView) findViewById(R.id.imageView1);
        final Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_right_to_left);
        findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                f9873a.setVisibility(0);
                f9873a.startAnimation(loadAnimation);
            }
        });
        final Button button = (Button) findViewById(R.id.button1);
        button.post(new Runnable() {

            public void run() {
                f9874b = (button.getWidth() * 25) / 100;
            }
        });
        button.setOnClickListener(new C31233(this));
        button.setOnTouchListener(new C31244(this));
        try {
            if (this.f9882j.getBoolean("faceDown", false)) {
                this.f9879g = this.f9882j.getInt("selectedPos", 0);
                this.f9877e = (SensorManager) getSystemService("sensor");
                this.f9878f = (Sensor) this.f9877e.getSensorList(1).get(0);
                this.f9877e.registerListener(this.f9887o, this.f9878f, 3);
            }
        } catch (Exception e) {
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
        overridePendingTransition(0, R.anim.slide_right);
        super.onPause();
    }

    protected void onResume() {
        try {
            if (this.f9877e != null) {
                this.f9877e.registerListener(this.f9887o, this.f9878f, 3);
            }
        } catch (Exception e) {
        }
        super.onResume();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        try {
            if (this.f9877e != null) {
                this.f9877e.unregisterListener(this.f9887o);
            }
        } catch (Exception e) {
        }
        if (this.f9876d != null) {
            new Timer().schedule(new C31255(this), 1000);
        }
        super.onStop();
    }
}
