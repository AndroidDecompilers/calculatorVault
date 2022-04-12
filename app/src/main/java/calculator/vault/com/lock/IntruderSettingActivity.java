package calculator.vault.com.lock;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.telephony.TelephonyManager;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import calculator.vault.com.R;
import calculator.vault.com.applock.SecurityHelpers;

public class IntruderSettingActivity extends AppCompatActivity implements OnClickListener, OnCheckedChangeListener {
    TextView f2853a;
    TextView f2854b;
    TextView f2855c;
    Editor f2856d;
    SwitchCompat f2857e;
    SwitchCompat f2858f;
    View f2859g;
    View f2860h;
    PowerManager f2861i;
    TelephonyManager f2862j;
    public int f2863k;
    SensorManager f2864l;
    Sensor f2865m;
    boolean f2866n;
    String f2867o;
    SharedPreferences f2868p;
    ArrayList<String> f2869q = new ArrayList();
    MediaPlayer f2870r;
    private SensorEventListener f2871s = new C10001(this);

    class C10001 implements SensorEventListener {
        final /* synthetic */ IntruderSettingActivity f2839a;

        C10001(IntruderSettingActivity intruderSettingActivity) {
            this.f2839a = intruderSettingActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f2839a.f2866n) {
                    this.f2839a.f2866n = true;
                    if (this.f2839a.f2863k == 1) {
                        C1131f.m5806a(this.f2839a.getApplicationContext(), this.f2839a.getPackageManager(), this.f2839a.f2868p.getString("Package_Name", null));
                    }
                    if (this.f2839a.f2863k == 2) {
                        this.f2839a.f2867o = this.f2839a.f2868p.getString("URL_Name", null);
                        this.f2839a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f2839a.f2867o)));
                    }
                    if (this.f2839a.f2863k == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.f2839a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    class C10023 extends TimerTask {
        final /* synthetic */ IntruderSettingActivity f2843a;

        C10023(IntruderSettingActivity intruderSettingActivity) {
            this.f2843a = intruderSettingActivity;
        }

        public void run() {
            try {
                if (SecurityHelpers.isCallRinging(this.f2843a.f2862j) || !SecurityHelpers.m14854b(this.f2843a.getApplicationContext()).equals(this.f2843a.getPackageName())) {
                    try {
                        if (MainActivity.mainActivity != null) {
                            MainActivity.mainActivity.finish();
                        }
                        if (IntruderActivity.f2821a != null) {
                            IntruderActivity.f2821a.finish();
                        }
                    } catch (Exception e) {
                    }
                    this.f2843a.finish();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (!SecurityHelpers.isScreenON(this.f2843a.f2861i)) {
                try {
                    if (MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                    }
                    if (IntruderActivity.f2821a != null) {
                        IntruderActivity.f2821a.finish();
                    }
                } catch (Exception e3) {
                }
                this.f2843a.finish();
            }
        }
    }


    class C10056 implements DialogInterface.OnClickListener {
        final /* synthetic */ IntruderSettingActivity f2847a;

        C10056(IntruderSettingActivity intruderSettingActivity) {
            this.f2847a = intruderSettingActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            this.f2847a.f2854b.setText((i + 1) + " times");
            this.f2847a.f2856d.putInt("tryCount", i + 1);
            this.f2847a.f2856d.commit();
        }
    }

    class C10089 implements DialogInterface.OnClickListener {
        final /* synthetic */ IntruderSettingActivity f2852a;

        C10089(IntruderSettingActivity intruderSettingActivity) {
            this.f2852a = intruderSettingActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
        }
    }

    private void m5602a() {
        Builder builder = new Builder(this);
        builder.setTitle("Set up your email");
        final EditText editText = new EditText(this);
        editText.setLayoutParams(new LayoutParams(-1, -1));
        editText.setHint("username@example.com");
        editText.setTextColor(-16777216);
        editText.setInputType(32);
        builder.setView(editText);
        builder.setPositiveButton("OK", new C10089(this));
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        final AlertDialog create = builder.create();
        create.show();
        create.getButton(-1).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String trim = editText.getText().toString().trim();
                if (trim.length() < 1 || !m5604a(trim)) {
                    Toast.makeText(getApplicationContext(), "Please enter valid mail address", Toast.LENGTH_SHORT).show();
                    return;
                }
                f2856d.putBoolean("mailIntru", true);
                f2856d.putString("mailIdIntru", trim);
                f2856d.commit();
                f2855c.setText("" + trim);
                create.dismiss();
            }
        });
    }

    private boolean m5604a(String str) {
        String str2 = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$").matcher(str).matches();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.enable_btn:
                this.f2856d.putBoolean("isSelfie", z);
                break;
            case R.id.shutter_btn:
                this.f2856d.putBoolean("isMute", z);
                break;
        }
        this.f2856d.commit();
    }

    public void onClick(View view) {
        boolean z = true;
        SwitchCompat switchCompat;
        switch (view.getId()) {
            case R.id.rlEnable:
                switchCompat = this.f2857e;
                if (this.f2857e.isChecked()) {
                    z = false;
                }
                switchCompat.setChecked(z);
                return;
            case R.id.rl_tryCount:
                CharSequence[] charSequenceArr = new CharSequence[]{"1 times", "2 times", "3 times", "4 times", "5 times", "6 times", "7 times", "8 times", "10 times"};
                Builder builder = new Builder(this);
                builder.setTitle("Wrong Password attempts");
                builder.setItems(charSequenceArr, new C10056(this));
                builder.create().show();
                return;
            case R.id.rlMail:
                Builder builder2 = new Builder(this);
                final ArrayList arrayList = new ArrayList(this.f2869q);
                builder2.setTitle("Select a mailbox");
                arrayList.add("Enter your email");
                arrayList.add("None");
                builder2.setItems((CharSequence[]) arrayList.toArray((CharSequence[]) arrayList.toArray(new CharSequence[arrayList.size()])), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        if (i == arrayList.size() - 1) {
                            f2856d.putBoolean("mailIntru", false);
                            f2856d.commit();
                            f2855c.setText("none");
                        } else if (i == arrayList.size() - 2) {
                            m5602a();
                        } else {
                            f2856d.putBoolean("mailIntru", true);
                            f2856d.putString("mailIdIntru", (String) arrayList.get(i));
                            f2855c.setText("" + ((String) arrayList.get(i)));
                            f2856d.commit();
                        }
                    }
                });
                builder2.create().show();
                return;
            case R.id.rlSound:
                switchCompat = this.f2858f;
                if (this.f2858f.isChecked()) {
                    z = false;
                }
                switchCompat.setChecked(z);
                return;
            case R.id.rl_help:
                this.f2870r = MediaPlayer.create(getApplicationContext(), R.raw.shuttersound);
                this.f2870r.start();
                final Dialog dialog = new Dialog(this);
                final View inflate = getLayoutInflater().inflate(R.layout.selfie_dialog, null);
                inflate.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setContentView(inflate);
                dialog.show();
                Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_fade_out);
                loadAnimation.setAnimationListener(new AnimationListener() {

                    public void onAnimationEnd(Animation animation) {
                        inflate.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.abc_fade_in));
                    }

                    public void onAnimationRepeat(Animation animation) {
                    }

                    public void onAnimationStart(Animation animation) {
                    }
                });
                inflate.startAnimation(loadAnimation);
                return;
            default:
                return;
        }
    }

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle bundle) {
        CharSequence charSequence;
        super.onCreate(bundle);
        this.f2868p = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int a = C1131f.getCurrentStyle(this.f2868p);
        if (a != R.style.AppTheme) {
            setTheme(a);
        }
        setContentView(R.layout.activity_intruder_setting);
        this.f2861i = (PowerManager) getSystemService("power");
        this.f2862j = (TelephonyManager) getSystemService("phone");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.f2856d = this.f2868p.edit();
        ((TextView) findViewById(R.id.tvEnable)).setTypeface(C1131f.f3315a);
        this.f2853a = (TextView) findViewById(R.id.tvIntruder);
        this.f2853a.setTypeface(C1131f.f3315a);
        this.f2853a.setText("Intruder Detector is turned " + (this.f2868p.getBoolean("isSelfie", true) ? "ON" : "OFF"));
        this.f2854b = (TextView) findViewById(R.id.tvCount);
        this.f2855c = (TextView) findViewById(R.id.tvMailId);
        this.f2855c.setTypeface(C1131f.f3315a);
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        try {
            for (Account account : AccountManager.get(getApplicationContext()).getAccounts()) {
                if (pattern.matcher(account.name).matches()) {
                    this.f2869q.add(account.name);
                }
            }
            Collection hashSet = new HashSet(this.f2869q);
            this.f2869q.clear();
            this.f2869q.addAll(hashSet);
        } catch (Exception e) {
        }
        TextView textView = this.f2855c;
        if (!this.f2868p.getBoolean("mailIntru", true)) {
            charSequence = "none";
        } else if (this.f2868p.getString("regEmail", "").length() > 1) {
            charSequence = this.f2868p.getString("regEmail", "");
        } else {
            charSequence = this.f2868p.getString("mailIdIntru", this.f2869q.size() > 0 ? (String) this.f2869q.get(0) : "none");
        }
        textView.setText(charSequence);
        this.f2854b.setTypeface(C1131f.f3315a);
        this.f2854b.setText(this.f2868p.getInt("tryCount", 3) + " times");
        ((TextView) findViewById(R.id.tv2)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.tvSound)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.tvHelp)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.textView50)).setTypeface(C1131f.f3315a);
        this.f2857e = (SwitchCompat) findViewById(R.id.enable_btn);
        this.f2858f = (SwitchCompat) findViewById(R.id.shutter_btn);
        this.f2857e.setChecked(this.f2868p.getBoolean("isSelfie", true));
        this.f2858f.setChecked(this.f2868p.getBoolean("isMute", true));
        this.f2857e.setOnCheckedChangeListener(this);
        this.f2858f.setOnCheckedChangeListener(this);
        this.f2859g = findViewById(R.id.rlEnable);
        this.f2860h = findViewById(R.id.rlSound);
        findViewById(R.id.rl_tryCount).setOnClickListener(this);
        findViewById(R.id.rl_help).setOnClickListener(this);
        findViewById(R.id.rlMail).setOnClickListener(this);
        this.f2859g.setOnClickListener(this);
        this.f2860h.setOnClickListener(this);
        try {
            if (this.f2868p.getBoolean("faceDown", false)) {
                this.f2863k = this.f2868p.getInt("selectedPos", 0);
                this.f2864l = (SensorManager) getSystemService("sensor");
                this.f2865m = (Sensor) this.f2864l.getSensorList(1).get(0);
            }
        } catch (Exception e2) {
        }
    }

    protected void onDestroy() {
        if (this.f2870r != null) {
            this.f2870r.release();
        }
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
            if (this.f2864l != null) {
                this.f2864l.registerListener(this.f2871s, this.f2865m, 3);
            }
        } catch (Exception e) {
        }
        super.onStart();
    }

    protected void onStop() {
        try {
            if (this.f2864l != null) {
                this.f2864l.unregisterListener(this.f2871s);
            }
        } catch (Exception e) {
        }
        if (this.f2862j != null) {
            new Timer().schedule(new C10023(this), 500);
        }
        super.onStop();
    }
}
