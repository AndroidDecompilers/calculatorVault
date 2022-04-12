package calculator.vault.com.lock;

import android.accounts.Account;
import android.accounts.AccountManager;
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
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import calculator.vault.com.R;
import calculator.vault.com.applock.SecurityHelpers;

public class SetEmailActivity extends AppCompatActivity {
    boolean fromReset;
    TelephonyManager telephonyManager;
    PowerManager powerManager;
    SensorManager sensorManager;
    Sensor sensor;
    public int f2984f;
    boolean f2985g;
    String openUrl;
    SharedPreferences sharedPreferences;
    EditText email;
    Editor settingsEditor;
    private int f2990l;
    private SensorEventListener f2991m = new SensorEventListener() {

        public void onAccuracyChanged(Sensor sensor, int i) {

        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !f2985g) {
                    f2985g = true;
                    if (f2984f == 1) {
                        C1131f.m5806a(getApplicationContext(), getPackageManager(), sharedPreferences.getString("Package_Name", null));
                    }
                    if (f2984f == 2) {
                        openUrl = sharedPreferences.getString("URL_Name", null);
                        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(openUrl)));
                    }
                    if (f2984f == 0) {
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

    class C10363 extends TimerTask {
        final /* synthetic */ SetEmailActivity f2978a;

        C10363(SetEmailActivity setEmailActivity) {
            this.f2978a = setEmailActivity;
        }

        public void run() {
            try {
                if (SecurityHelpers.isCallRinging(this.f2978a.telephonyManager) || !SecurityHelpers.m14854b(this.f2978a.getApplicationContext()).equals(this.f2978a.getPackageName())) {
                    this.f2978a.finish();
                    if (this.f2978a.fromReset && MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                    }
                }
                if (!SecurityHelpers.isScreenON(this.f2978a.powerManager)) {
                    this.f2978a.finish();
                    if (this.f2978a.fromReset && MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                        Intent intent = new Intent(this.f2978a.getApplicationContext(), ManageSpaceActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        this.f2978a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean m5665a(String str) {
        return Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(str).matches();
    }

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f2990l = C1131f.getCurrentStyle(this.sharedPreferences);
        if (this.f2990l != R.style.AppTheme) {
            setTheme(this.f2990l);
        }

        getWindow().setSoftInputMode(3);
        this.fromReset = getIntent().getBooleanExtra("fromReset", false);
        setContentView((int) R.layout.activity_set_email);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        this.telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.settingsEditor = this.sharedPreferences.edit();
        ((TextView) findViewById(R.id.textView2)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.textView3)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.textView4)).setTypeface(C1131f.f3315a);
        this.email = (EditText) findViewById(R.id.editText1);
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Account[] accounts = AccountManager.get(getApplicationContext()).getAccounts();
        final ArrayList arrayList = new ArrayList();
        for (Account account : accounts) {
            if (pattern.matcher(account.name).matches()) {
                arrayList.add(account.name);
            }
        }
        ListView listView = (ListView) findViewById(R.id.listView1);
        if (arrayList.isEmpty()) {
            if (this.sharedPreferences.getString("regEmail", "").length() > 2) {
                this.email.setText("" + this.sharedPreferences.getString("regEmail", ""));
            }
            listView.setVisibility(View.GONE);
            findViewById(R.id.textView4).setVisibility(View.GONE);
        } else {
            if (!this.fromReset) {
                this.email.setText((CharSequence) arrayList.get(0));
            } else if (this.sharedPreferences.getString("regEmail", "").length() > 2) {
                this.email.setText("" + this.sharedPreferences.getString("regEmail", ""));
            }
            listView.setAdapter(new ArrayAdapter(getApplicationContext(), R.layout.tv_email_raw_item, arrayList));
            listView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    email.setText((CharSequence) arrayList.get(i));
                }
            });
        }
        try {
            if (this.sharedPreferences.getBoolean("faceDown", false)) {
                this.f2984f = this.sharedPreferences.getInt("selectedPos", 0);
                this.sensorManager = (SensorManager) getSystemService("sensor");
                this.sensor = (Sensor) this.sensorManager.getSensorList(1).get(0);
                this.sensorManager.registerListener(this.f2991m, this.sensor, 3);
            }
        } catch (Exception e) {
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setmail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_set:
                String trim = this.email.getText().toString().trim();
                if (trim.length() >= 1 && m5665a(trim)) {
                    this.settingsEditor.putString("regEmail", trim);
                    this.settingsEditor.commit();
                    finish();
                    break;
                }
                Toast.makeText(getApplicationContext(), "Please enter valid mail address", Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onStop() {
        try {
            if (this.sensorManager != null) {
                this.sensorManager.unregisterListener(this.f2991m);
            }
        } catch (Exception e) {
        }
        if (this.telephonyManager != null) {
            new Timer().schedule(new C10363(this), 1000);
        }
        super.onStop();
    }
}
