package calculator.vault.com.lock;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
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
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.p029h.C1017a;
import calculator.vault.com.p029h.C1019c;
import calculator.vault.com.p034a.C1191a;
import calculator.vault.com.p034a.C1229h;
import calculator.vault.com.p068a.C1565a;
import calculator.vault.com.p068a.FileModel;


public class MoveActivity extends AppCompatActivity implements OnItemClickListener {
    GridView f2915a;
    TextView f2916b;
    String f2917c;
    String f2918d;
    ArrayList<String> f2919e;
    boolean f2920f;
    TelephonyManager f2921g;
    PowerManager f2922h;
    SensorManager f2923i;
    Sensor f2924j;
    public int f2925k;
    boolean f2926l;
    String f2927m;
    SharedPreferences f2928n;
    boolean f2929o;
    private boolean f2930p;
    private int f2931q;
    private SensorEventListener f2932r = new C10245(this);

    class C10181 implements C1017a {
        final /* synthetic */ MoveActivity f2908a;

        C10181(MoveActivity moveActivity) {
            this.f2908a = moveActivity;
        }

        public void mo958a() {
            this.f2908a.f2916b.setVisibility(View.VISIBLE);
        }

        public void mo959a(ArrayList<?> arrayList) {
            this.f2908a.f2915a = (GridView) this.f2908a.findViewById(R.id.gridView1);
            this.f2908a.f2915a.setOnItemClickListener(this.f2908a);
            ListAdapter c1565a = new C1565a(this.f2908a, (List<FileModel>) arrayList, this.f2908a.f2920f, this.f2908a.f2930p);
            this.f2908a.f2915a.setVisibility(View.VISIBLE);
            this.f2908a.f2915a.setAdapter(c1565a);
            if (arrayList.size() > 0) {
                this.f2908a.f2916b.setVisibility(View.GONE);
                return;
            }
            this.f2908a.f2916b.setVisibility(View.VISIBLE);
            this.f2908a.f2916b.setText("No Folders");
        }
    }

    class C10223 implements OnClickListener {
        final /* synthetic */ MoveActivity f2912a;

        C10223(MoveActivity moveActivity) {
            this.f2912a = moveActivity;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    class C10234 extends TimerTask {
        final /* synthetic */ MoveActivity f2913a;

        C10234(MoveActivity moveActivity) {
            this.f2913a = moveActivity;
        }

        public void run() {
            try {
                if (SecurityHelpers.isCallRinging(this.f2913a.f2921g) || !SecurityHelpers.m14854b(this.f2913a.getApplicationContext()).equals(this.f2913a.getPackageName())) {
                    this.f2913a.finish();
                    MainActivity.mainActivity.finish();
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                }
                if (!SecurityHelpers.isScreenON(this.f2913a.f2922h)) {
                    this.f2913a.finish();
                    MainActivity.mainActivity.finish();
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                    Intent intent = new Intent(this.f2913a.getApplicationContext(), ManageSpaceActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    this.f2913a.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C10245 implements SensorEventListener {
        final /* synthetic */ MoveActivity f2914a;

        C10245(MoveActivity moveActivity) {
            this.f2914a = moveActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f2914a.f2926l) {
                    this.f2914a.f2926l = true;
                    if (this.f2914a.f2925k == 1) {
                        C1131f.m5806a(this.f2914a.getApplicationContext(), this.f2914a.getPackageManager(), this.f2914a.f2928n.getString("Package_Name", null));
                    }
                    if (this.f2914a.f2925k == 2) {
                        this.f2914a.f2927m = this.f2914a.f2928n.getString("URL_Name", null);
                        this.f2914a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f2914a.f2927m)));
                    }
                    if (this.f2914a.f2925k == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.f2914a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    public void onBackPressed() {
        setResult(0);
        finish();
        super.onBackPressed();
    }

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f2928n = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f2931q = C1131f.getCurrentStyle(this.f2928n);
        if (this.f2931q != R.style.AppTheme) {
            setTheme(this.f2931q);
        }
        this.f2929o = getIntent().getBooleanExtra("fromFake", false);
        setContentView((int) R.layout.activity_move);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.f2922h = (PowerManager) getSystemService("power");
        this.f2921g = (TelephonyManager) getSystemService("phone");
        this.f2930p = this.f2928n.getBoolean("isOldFirst", false);
        this.f2919e = getIntent().getStringArrayListExtra("listItems");
        this.f2916b = (TextView) findViewById(R.id.tvLoading);
        this.f2917c = getIntent().getStringExtra("srcFolder");
        if (this.f2917c.contains("Videos1769")) {
            this.f2920f = true;
            if (this.f2929o) {
                C1131f.f3318d = getFilesDir() + "/lockerVault/FVault/Videos1769";
            } else {
                C1131f.f3318d = getFilesDir() + "/lockerVault/Videos1769";
            }
        } else if (this.f2929o) {
            C1131f.f3318d = getFilesDir() + "/lockerVault/FVault/Images1769";
        } else {
            C1131f.f3318d = getFilesDir() + "/lockerVault/Images1769";
        }
        this.f2918d = new File(this.f2917c).getName();
        new C1191a(getApplicationContext(), C1131f.f3318d).m6032a(new C10181(this));
        try {
            if (this.f2928n.getBoolean("faceDown", false)) {
                this.f2925k = this.f2928n.getInt("selectedPos", 0);
                this.f2923i = (SensorManager) getSystemService("sensor");
                this.f2924j = (Sensor) this.f2923i.getSensorList(1).get(0);
                this.f2923i.registerListener(this.f2932r, this.f2924j, 3);
            }
        } catch (Exception e) {
        }
    }

    @TargetApi(21)
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        FileModel fileModel = (FileModel) adapterView.getItemAtPosition(i);
        final String str = fileModel.fileAddress;
        if (this.f2918d.equals(fileModel.fileName)) {
            Toast.makeText(getApplicationContext(), "source and destination can not be same", Toast.LENGTH_LONG).show();
            return;
        }
        Builder builder = new Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure you want to move from \"" + this.f2918d + "\" to \"" + fileModel.fileName + "\" Folder?");
        builder.setPositiveButton("Yes", new OnClickListener() {

            class C10201 implements C1019c {
                public void mo960a(String str) {
                    setResult(-1);
                    if (f2929o) {
                        C1131f.f3318d = getFilesDir() + "/lockerVault/FVault";
                    }
                    finish();
                    Toast.makeText(getApplicationContext(), "file(s) Moved", Toast.LENGTH_SHORT).show();
                }
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                new C1229h(MoveActivity.this, f2917c, str, f2919e).m6067a(new C10201());
            }
        });
        builder.setNegativeButton("Oops! No", new C10223(this));
        builder.create().show();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onStop() {
        try {
            if (this.f2923i != null) {
                this.f2923i.unregisterListener(this.f2932r);
            }
        } catch (Exception e) {
        }
        if (this.f2921g != null) {
            new Timer().schedule(new C10234(this), 1000);
        }
        super.onStop();
    }
}
