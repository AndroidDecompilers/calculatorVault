package calculator.vault.com.lock;

import android.annotation.SuppressLint;
import android.app.Dialog;
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
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.customphotoview.PhotoView;
import calculator.vault.com.p068a.FileModel;
import calculator.vault.com.p073g.PhotoFolderViewFragment;
import calculator.vault.com.p084i.C2931d;

public class ViewSelfieActivity extends AppCompatActivity {
    String f3282a;
    int f3283b;
    PowerManager f3284c;
    TelephonyManager f3285d;
    public int f3286e;
    SensorManager f3287f;
    Sensor f3288g;
    boolean f3289h;
    String f3290i;
    SharedPreferences f3291j;
    private int f3292k;
    private SensorEventListener f3293l = new C11206(this);

    class C11195 extends TimerTask {
        final /* synthetic */ ViewSelfieActivity f3280a;

        C11195(ViewSelfieActivity viewSelfieActivity) {
            this.f3280a = viewSelfieActivity;
        }

        public void run() {
            try {
                if (SecurityHelpers.isCallRinging(this.f3280a.f3285d) || !SecurityHelpers.m14854b(this.f3280a.getApplicationContext()).equals(this.f3280a.getPackageName())) {
                    try {
                        if (IntruderActivity.f2821a != null) {
                            IntruderActivity.f2821a.finish();
                        }
                        if (MainActivity.mainActivity != null) {
                            MainActivity.mainActivity.finish();
                        }
                    } catch (Exception e) {
                    }
                    this.f3280a.finish();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (!SecurityHelpers.isScreenON(this.f3280a.f3284c)) {
                try {
                    if (IntruderActivity.f2821a != null) {
                        IntruderActivity.f2821a.finish();
                    }
                    if (MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                    }
                } catch (Exception e3) {
                }
                this.f3280a.finish();
            }
        }
    }

    class C11206 implements SensorEventListener {
        final /* synthetic */ ViewSelfieActivity f3281a;

        C11206(ViewSelfieActivity viewSelfieActivity) {
            this.f3281a = viewSelfieActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f3281a.f3289h) {
                    this.f3281a.f3289h = true;
                    if (this.f3281a.f3286e == 1) {
                        C1131f.m5806a(this.f3281a.getApplicationContext(), this.f3281a.getPackageManager(), this.f3281a.f3291j.getString("Package_Name", null));
                    }
                    if (this.f3281a.f3286e == 2) {
                        this.f3281a.f3290i = this.f3281a.f3291j.getString("URL_Name", null);
                        this.f3281a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f3281a.f3290i)));
                    }
                    if (this.f3281a.f3286e == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.f3281a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3291j = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f3292k = C1131f.getCurrentStyle(this.f3291j);
        if (this.f3292k != R.style.AppTheme) {
            setTheme(this.f3292k);
        }
        setContentView((int) R.layout.activity_view_selfie);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.f3284c = (PowerManager) getSystemService("power");
        this.f3285d = (TelephonyManager) getSystemService("phone");
        CharSequence stringExtra = getIntent().getStringExtra("time");
        String stringExtra2 = getIntent().getStringExtra("appName");
        this.f3282a = getIntent().getStringExtra("path");
        this.f3283b = getIntent().getIntExtra("pos", 0);
        ((TextView) findViewById(R.id.tvTime)).setText(stringExtra);
        ((TextView) findViewById(R.id.tvAppName)).setText("This guy was trying to break in your " + stringExtra2 + ". we caught him.");
        Glide.with(this).load(this.f3282a).into((ImageView) (PhotoView) findViewById(R.id.ivImage));
        try {
            if (this.f3291j.getBoolean("faceDown", false)) {
                this.f3286e = this.f3291j.getInt("selectedPos", 0);
                this.f3287f = (SensorManager) getSystemService("sensor");
                this.f3288g = (Sensor) this.f3287f.getSensorList(1).get(0);
            }
        } catch (Exception e) {
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_viewselfie, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        final Dialog dialog;
        View inflate;
        TextView textView;
        switch (menuItem.getItemId()) {
            case 16908332:
                finish();
                break;
            case R.id.action_delete:
                dialog = new Dialog(this);
                inflate = getLayoutInflater().inflate(R.layout.delete_dialog, null);
                ((TextView) inflate.findViewById(R.id.textView1)).setTypeface(C1131f.f3315a);
                textView = (TextView) inflate.findViewById(R.id.tv_dialogText);
                textView.setText("Delete this menu_intruder selfie picture?");
                textView.setTypeface(C1131f.f3315a);
                dialog.setContentView(inflate);
                inflate.findViewById(R.id.rlDelete).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        new C2931d(getApplicationContext()).m14133a(f3282a);
                        new File(f3282a).delete();
                        IntruderActivity.f2821a.m5599a(f3283b);
                        finish();
                        dialog.dismiss();
                    }
                });
                inflate.findViewById(R.id.rlCancel).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            case R.id.action_save:
                dialog = new Dialog(this);
                inflate = getLayoutInflater().inflate(R.layout.delete_dialog, null);
                textView = (TextView) inflate.findViewById(R.id.textView1);
                textView.setTypeface(C1131f.f3315a);
                textView.setText("Lock Selfie");
                textView = (TextView) inflate.findViewById(R.id.tv_dialogText);
                textView.setText("Save and lock this menu_intruder photo with your locked picture albums?");
                textView.setTypeface(C1131f.f3315a);
                dialog.setContentView(inflate);
                inflate.findViewById(R.id.rlDelete).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        String str = getFilesDir() + "/lockerVault/Images1769/IntruderSelfie";
                        File file = new File(str);
                        if (!file.exists()) {
                            file.mkdir();
                            if (PhotoFolderViewFragment.f4765e != null) {
                                PhotoFolderViewFragment.f4765e.m7379a(new FileModel(str, "IntruderSelfie"));
                            }
                        }
                        try {
                            FileUtils.copyDirectory(new File(f3282a), new File(file.getAbsolutePath() + "/" + new File(f3282a).getName()));
                            Toast.makeText(getApplicationContext(), "Photo saved.", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            Toast.makeText(getApplicationContext(), "Sorry can not copy to locker", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                inflate.findViewById(R.id.rlCancel).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onStart() {
        try {
            if (this.f3287f != null) {
                this.f3287f.registerListener(this.f3293l, this.f3288g, 3);
            }
        } catch (Exception e) {
        }
        super.onStart();
    }

    protected void onStop() {
        try {
            if (this.f3287f != null) {
                this.f3287f.unregisterListener(this.f3293l);
            }
        } catch (Exception e) {
        }
        if (this.f3285d != null) {
            new Timer().schedule(new C11195(this), 500);
        }
        super.onStop();
    }
}
