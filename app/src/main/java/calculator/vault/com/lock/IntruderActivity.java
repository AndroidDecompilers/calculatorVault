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
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.p068a.C1632r;
import calculator.vault.com.p068a.C1633s;
import calculator.vault.com.p084i.C2931d;

public class IntruderActivity extends AppCompatActivity {
    public static IntruderActivity f2821a;
    PowerManager f2822b;
    TelephonyManager f2823c;
    public int f2824d;
    SensorManager f2825e;
    Sensor f2826f;
    boolean f2827g;
    String f2828h;
    SharedPreferences f2829i;
    LinearLayout f2830j;
    private C1632r f2831k;
    private RecyclerView.LayoutManager f2832l;
    private RecyclerView f2833m;
    private ArrayList<C1633s> f2834n;
    private int f2835o;
    private SensorEventListener f2837q = new C09993(this);

    class C09971 extends TimerTask {
        final /* synthetic */ IntruderActivity f2818a;

        C09971(IntruderActivity intruderActivity) {
            this.f2818a = intruderActivity;
        }

        public void run() {
            try {
                if (SecurityHelpers.isCallRinging(this.f2818a.f2823c) || !SecurityHelpers.m14854b(this.f2818a.getApplicationContext()).equals(this.f2818a.getPackageName())) {
                    try {
                        if (MainActivity.mainActivity != null) {
                            MainActivity.mainActivity.finish();
                        }
                    } catch (Exception e) {
                    }
                    this.f2818a.finish();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (!SecurityHelpers.isScreenON(this.f2818a.f2822b)) {
                try {
                    if (MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                    }
                } catch (Exception e3) {
                }
                this.f2818a.finish();
            }
        }
    }

    class C09993 implements SensorEventListener {
        final /* synthetic */ IntruderActivity f2820a;

        C09993(IntruderActivity intruderActivity) {
            this.f2820a = intruderActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f2820a.f2827g) {
                    this.f2820a.f2827g = true;
                    if (this.f2820a.f2824d == 1) {
                        C1131f.m5806a(this.f2820a.getApplicationContext(), this.f2820a.getPackageManager(), this.f2820a.f2829i.getString("Package_Name", null));
                    }
                    if (this.f2820a.f2824d == 2) {
                        this.f2820a.f2828h = this.f2820a.f2829i.getString("URL_Name", null);
                        this.f2820a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f2820a.f2828h)));
                    }
                    if (this.f2820a.f2824d == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.f2820a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public void m5598a() {
        if (this.f2834n.size() < 1) {
            this.f2830j.setVisibility(View.VISIBLE);
        } else {
            this.f2830j.setVisibility(View.GONE);
        }
    }

    public void m5599a(int i) {
        this.f2834n.remove(i);
        this.f2831k.notifyItemRemoved(i);
        m5598a();
    }

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle bundle) {
        boolean z = false;
        super.onCreate(bundle);
        this.f2829i = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f2835o = C1131f.getCurrentStyle(this.f2829i);
        if (this.f2835o != R.style.AppTheme) {
            setTheme(this.f2835o);
        }
        setContentView((int) R.layout.activity_intruder);
        f2821a = this;
        Editor edit = this.f2829i.edit();
        if (this.f2829i.getBoolean("isNew", false)) {
            edit.putBoolean("isNew", false);
            edit.commit();
            z = true;
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.f2830j = (LinearLayout) findViewById(R.id.llDemo);
        this.f2822b = (PowerManager) getSystemService("power");
        this.f2823c = (TelephonyManager) getSystemService("phone");
        this.f2833m = (RecyclerView) findViewById(R.id.recyclerview);
        this.f2833m.setHasFixedSize(true);
        this.f2832l = new LinearLayoutManager(this);
        this.f2833m.setLayoutManager(this.f2832l);
        this.f2833m.setItemAnimator(new DefaultItemAnimator());
        this.f2834n = new C2931d(getApplicationContext()).m14132a();
        Collections.reverse(this.f2834n);
        this.f2831k = new C1632r(getApplicationContext(), this.f2834n, z);
        this.f2833m.setAdapter(this.f2831k);
        m5598a();
        try {
            if (this.f2829i.getBoolean("faceDown", false)) {
                this.f2824d = this.f2829i.getInt("selectedPos", 0);
                this.f2825e = (SensorManager) getSystemService("sensor");
                this.f2826f = (Sensor) this.f2825e.getSensorList(1).get(0);
            }
        } catch (Exception e) {
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_intruder, menu);
        return true;
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.action_settings) {
            startActivity(new Intent(getApplicationContext(), IntruderSettingActivity.class));
            return true;
        }
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStart() {
        try {
            if (this.f2825e != null) {
                this.f2825e.registerListener(this.f2837q, this.f2826f, 3);
            }
        } catch (Exception e) {
        }
        super.onStart();
    }

    protected void onStop() {
        try {
            if (this.f2825e != null) {
                this.f2825e.unregisterListener(this.f2837q);
            }
        } catch (Exception e) {
        }
        if (this.f2823c != null) {
            new Timer().schedule(new C09971(this), 500);
        }
        super.onStop();
    }
}
