package calculator.vault.com.lock;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.deviceadmin.MyAdmin;

public class UninstallProtectionActivity extends AppCompatActivity implements OnCheckedChangeListener {
    PowerManager f3009a;
    TelephonyManager f3010b;
    SwitchCompat f3011c;
    public int f3012d;
    SensorManager f3013e;
    Sensor f3014f;
    boolean f3015g;
    String f3016h;
    SharedPreferences f3017i;
    DevicePolicyManager f3018j;
    boolean f3019k;
    ComponentName f3020l;
    private SensorEventListener f3021m = new C10471(this);
    private int f3022n;

    class C10471 implements SensorEventListener {
        final /* synthetic */ UninstallProtectionActivity f3004a;

        C10471(UninstallProtectionActivity uninstallProtectionActivity) {
            this.f3004a = uninstallProtectionActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        @SuppressLint("WrongConstant")
        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f3004a.f3015g) {
                    this.f3004a.f3015g = true;
                    if (this.f3004a.f3012d == 1) {
                        C1131f.m5806a(this.f3004a.getApplicationContext(), this.f3004a.getPackageManager(), this.f3004a.f3017i.getString("Package_Name", null));
                    }
                    if (this.f3004a.f3012d == 2) {
                        this.f3004a.f3016h = this.f3004a.f3017i.getString("URL_Name", null);
                        this.f3004a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f3004a.f3016h)));
                    }
                    if (this.f3004a.f3012d == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(268435456);
                        this.f3004a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    class C10482 extends TimerTask {
        final /* synthetic */ UninstallProtectionActivity f3005a;

        C10482(UninstallProtectionActivity uninstallProtectionActivity) {
            this.f3005a = uninstallProtectionActivity;
        }

        public void run() {
            try {
                if ((SecurityHelpers.isCallRinging(this.f3005a.f3010b) || !SecurityHelpers.m14854b(this.f3005a.getApplicationContext()).equals(this.f3005a.getPackageName())) && !this.f3005a.f3019k) {
                    MainActivity.mainActivity.finish();
                    this.f3005a.finish();
                }
                if (!SecurityHelpers.isScreenON(this.f3005a.f3009a)) {
                    MainActivity.mainActivity.finish();
                    this.f3005a.finish();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    class C10504 implements OnClickListener {
        final /* synthetic */ UninstallProtectionActivity f3007a;

        C10504(UninstallProtectionActivity uninstallProtectionActivity) {
            this.f3007a = uninstallProtectionActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            this.f3007a.m5699a();
        }
    }

    class C10515 implements OnCancelListener {
        final /* synthetic */ UninstallProtectionActivity f3008a;

        C10515(UninstallProtectionActivity uninstallProtectionActivity) {
            this.f3008a = uninstallProtectionActivity;
        }

        public void onCancel(DialogInterface dialogInterface) {
            this.f3008a.f3011c.setChecked(false);
        }
    }

    private void m5699a() {
        this.f3019k = true;
        Intent intent = new Intent("android.app.action.ADD_DEVICE_ADMIN");
        intent.putExtra("android.app.extra.DEVICE_ADMIN", this.f3020l);
        intent.putExtra("android.app.extra.ADD_EXPLANATION", getResources().getString(R.string.device_policy_text));
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        this.f3019k = false;
        switch (i) {
            case 1:
                if (i2 != -1) {
                    Toast.makeText(this, "without permission uninstall protection not gonna work", Toast.LENGTH_LONG).show();
                    this.f3011c.setChecked(false);
                    return;
                }
                return;
            default:
                super.onActivityResult(i, i2, intent);
                return;
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.uninstall_toggle:
                if (z) {
                    Builder builder = new Builder(this);
                    builder.setTitle("Disclosure");
                    builder.setMessage("To prevent Calculator App being uninstalled, Calculator needs Device Administrator Permission and we never use any other Device Administrator permission except uninstallation prevention.\n\nYou can disable Device Administrator Permission for this app anytime. You can go to Settings->Security->Device administrators Or Turn off Switch from Uninstall Protection.");
                    builder.setPositiveButton("OK", new C10504(this));
                    builder.setOnCancelListener(new C10515(this));
                    builder.create().show();
                    return;
                }
                this.f3018j.removeActiveAdmin(this.f3020l);
                return;
            default:
                return;
        }
    }

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3017i = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f3022n = C1131f.getCurrentStyle(this.f3017i);
        if (this.f3022n != R.style.AppTheme) {
            setTheme(this.f3022n);
        }
        getWindow().addFlags(128);
        setContentView((int) R.layout.activity_uninstall_protection);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.f3018j = (DevicePolicyManager) getSystemService("device_policy");
        this.f3020l = new ComponentName(this, MyAdmin.class);
        this.f3009a = (PowerManager) getSystemService("power");
        this.f3010b = (TelephonyManager) getSystemService("phone");
        this.f3011c = (SwitchCompat) findViewById(R.id.uninstall_toggle);
        boolean z = this.f3018j != null && this.f3018j.isAdminActive(this.f3020l);
        this.f3011c.setChecked(z);
        this.f3011c.setOnCheckedChangeListener(this);
        try {
            if (this.f3017i.getBoolean("faceDown", false)) {
                this.f3012d = this.f3017i.getInt("selectedPos", 0);
                this.f3013e = (SensorManager) getSystemService("sensor");
                this.f3014f = (Sensor) this.f3013e.getSensorList(1).get(0);
            }
        } catch (Exception e) {
        }
    }

    protected void onDestroy() {
        getWindow().clearFlags(128);
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
            if (this.f3013e != null) {
                this.f3013e.registerListener(this.f3021m, this.f3014f, 3);
            }
        } catch (Exception e) {
        }
        super.onStart();
    }

    protected void onStop() {
        if (this.f3010b != null) {
            new Timer().schedule(new C10482(this), 1000);
        }
        super.onStop();
    }
}
