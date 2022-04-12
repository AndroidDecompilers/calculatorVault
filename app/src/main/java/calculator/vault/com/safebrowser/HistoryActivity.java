package calculator.vault.com.safebrowser;

import android.app.Dialog;
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
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper.SimpleCallback;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.lock.MainActivity;
import calculator.vault.com.lock.ManageSpaceActivity;
import calculator.vault.com.lock.ViewAlbumActivity;
import calculator.vault.com.p068a.C1609m;
import calculator.vault.com.p068a.C1610n;

public class HistoryActivity extends AppCompatActivity {
    LinearLayoutManager f9315a;
    ArrayList<C1610n> f9316b = new ArrayList();
    C1609m f9317c;
    SaveBrowserDatabaseHelper f9318d;
    TextView f9319e;
    SensorManager f9320f;
    Sensor f9321g;
    public int f9322h;
    boolean f9323i;
    String f9324j;
    SharedPreferences f9325k;
    TelephonyManager f9326l;
    PowerManager f9327m;
    private RecyclerView f9328n;
    private int f9329o;
    private SensorEventListener f9331q = new C29889(this);

    class C29823 implements C1609m.C1603b {
        final /* synthetic */ HistoryActivity f9306a;

        C29823(HistoryActivity historyActivity) {
            this.f9306a = historyActivity;
        }

        public void mo2103a(C1610n c1610n) {
            Snackbar.make(this.f9306a.f9328n, "Swipe history item to remove", 0).show();
        }
    }

    class C29834 implements C1609m.C1608d {
        final /* synthetic */ HistoryActivity f9307a;

        C29834(HistoryActivity historyActivity) {
            this.f9307a = historyActivity;
        }

        public void mo2104a(C1610n c1610n) {
            int indexOf = this.f9307a.f9316b.indexOf(c1610n);
            this.f9307a.f9316b.remove(indexOf);
            this.f9307a.f9317c.notifyItemRemoved(indexOf);
            this.f9307a.f9318d.m14460b(c1610n.f4584b);
            this.f9307a.m14407b();
        }
    }

    class C29878 extends TimerTask {
        final /* synthetic */ HistoryActivity f9313a;

        C29878(HistoryActivity historyActivity) {
            this.f9313a = historyActivity;
        }

        public void run() {
            try {
                if (SecurityHelpers.isCallRinging(this.f9313a.f9326l) || !SecurityHelpers.m14854b(this.f9313a.getApplicationContext()).equals(this.f9313a.getPackageName())) {
                    this.f9313a.finish();
                    if (SafeBrowseActivity.f9448z != null) {
                        SafeBrowseActivity.f9448z.finish();
                    }
                    MainActivity.mainActivity.finish();
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                    MainBrowserActivity.f9367a.finish();
                }
                if (!SecurityHelpers.isScreenON(this.f9313a.f9327m)) {
                    this.f9313a.finish();
                    if (SafeBrowseActivity.f9448z != null) {
                        SafeBrowseActivity.f9448z.finish();
                    }
                    MainActivity.mainActivity.finish();
                    MainBrowserActivity.f9367a.finish();
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                    Intent intent = new Intent(this.f9313a.getApplicationContext(), ManageSpaceActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    this.f9313a.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C29889 implements SensorEventListener {
        final /* synthetic */ HistoryActivity f9314a;

        C29889(HistoryActivity historyActivity) {
            this.f9314a = historyActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f9314a.f9323i) {
                    this.f9314a.f9323i = true;
                    if (this.f9314a.f9322h == 1) {
                        C1131f.m5806a(this.f9314a.getApplicationContext(), this.f9314a.getPackageManager(), this.f9314a.f9325k.getString("Package_Name", null));
                    }
                    if (this.f9314a.f9322h == 2) {
                        this.f9314a.f9324j = this.f9314a.f9325k.getString("URL_Name", null);
                        this.f9314a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f9314a.f9324j)));
                    }
                    if (this.f9314a.f9322h == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.f9314a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    private void m14405a() {
        final Dialog dialog = new Dialog(this);
        View inflate = getLayoutInflater().inflate(R.layout.exit_dialog, null);
        dialog.setContentView(inflate);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_dialogText);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tvOk);
        textView2.setText("CLEAR");
        textView.setText("Clear history?");
        textView2.setTypeface(C1131f.f3322h);
        ((TextView) inflate.findViewById(R.id.tvCancel)).setTypeface(C1131f.f3322h);
        textView.setTypeface(C1131f.f3322h);
        textView.setText("Attention");
        inflate.findViewById(R.id.rlCancel).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        inflate.findViewById(R.id.rlExit).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                if (f9318d.m14461c()) {
                    int size = f9316b.size();
                    f9316b.clear();
                    f9317c.notifyItemRangeRemoved(0, size);
                    f9319e.setVisibility(View.INVISIBLE);
                }
            }
        });
        dialog.show();
    }

    private void m14407b() {
        if (this.f9316b.isEmpty()) {
            this.f9319e.setVisibility(View.VISIBLE);
        } else {
            this.f9319e.setVisibility(View.INVISIBLE);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f9325k = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f9329o = C1131f.getCurrentStyle(this.f9325k);
        if (this.f9329o != R.style.AppTheme) {
            setTheme(this.f9329o);
        }
        setContentView((int) R.layout.activity_history);
        this.f9327m = (PowerManager) getSystemService(POWER_SERVICE);
        this.f9326l = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final boolean booleanExtra = getIntent().getBooleanExtra("fromBrowser", false);
        this.f9319e = (TextView) findViewById(R.id.tvEmpty);
        this.f9318d = new SaveBrowserDatabaseHelper(this);
        this.f9328n = (RecyclerView) findViewById(R.id.recyclerView);
        this.f9315a = new LinearLayoutManager(this, 1, false);
        this.f9328n.setLayoutManager(this.f9315a);
        this.f9316b = this.f9318d.m14459b();
        Collections.reverse(this.f9316b);
        if (this.f9316b.isEmpty()) {
            this.f9319e.setVisibility(View.VISIBLE);
            return;
        }
        Editor edit = this.f9325k.edit();
        if (!this.f9325k.getBoolean("isHistoryVisited", false)) {
            Snackbar.make(this.f9328n, "Swipe history card to remove history item", 0).show();
            edit.putBoolean("isHistoryVisited", true);
            edit.commit();
        }
        this.f9317c = new C1609m(getApplicationContext(), this.f9316b, new C1609m.C1602a() {

            public void mo2102a(C1610n c1610n) {
                if (booleanExtra) {
                    Intent intent = new Intent();
                    intent.putExtra("url", c1610n.f4584b);
                    setResult(-1, intent);
                    finish();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), SafeBrowseActivity.class);
                intent.putExtra("url", c1610n.f4584b);
                finish();
                startActivity(intent);
            }
        }, new C29823(this), new C29834(this));
        this.f9328n.setAdapter(this.f9317c);
        new android.support.v7.widget.helper.ItemTouchHelper(new SimpleCallback(0, 12) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int adapterPosition = viewHolder.getAdapterPosition();
                C1610n c1610n = (C1610n) f9316b.get(adapterPosition);
                f9316b.remove(adapterPosition);
                f9317c.notifyItemRemoved(adapterPosition);
                f9318d.m14460b(c1610n.f4584b);
                m14407b();
            }
        }).attachToRecyclerView(this.f9328n);

        try {
            if (this.f9325k.getBoolean("faceDown", false)) {
                this.f9322h = this.f9325k.getInt("selectedPos", 0);
                this.f9320f = (SensorManager) getSystemService(SENSOR_SERVICE);
                this.f9321g = (Sensor) this.f9320f.getSensorList(1).get(0);
                this.f9320f.registerListener(this.f9331q, this.f9321g, 3);
            }
        } catch (Exception e) {
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_history, menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.item_clear) {
            if (this.f9316b.isEmpty()) {
                Toast.makeText(getApplicationContext(), "No History to clear", Toast.LENGTH_SHORT).show();
                return false;
            }
            m14405a();
        } else if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStop() {
        if (this.f9326l != null) {
            new Timer().schedule(new C29878(this), 1000);
        }
        super.onStop();
    }
}
