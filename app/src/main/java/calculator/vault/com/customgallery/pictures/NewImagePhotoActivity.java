package calculator.vault.com.customgallery.pictures;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.provider.MediaStore.Images.Media;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.applock.C3150b;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.calculator.Calculator;
import calculator.vault.com.customgallery.videos.C1230i;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.lock.C1132g;
import calculator.vault.com.lock.MainActivity;
import calculator.vault.com.lock.ViewAlbumActivity;
import calculator.vault.com.p029h.C1099i;
import calculator.vault.com.p068a.C1591h;
import calculator.vault.com.p084i.C2930c;

public class NewImagePhotoActivity extends AppCompatActivity implements C1099i, C3150b.C1055c {
    PowerManager powerManager;
    TelephonyManager telephonyManager;
    String f9568c;
    C2930c f9569d;
    ArrayList<PictureModel> f9570e;
    boolean f9571f;
    Intent f9572g = new Intent();
    String f9573h;
    SensorManager sensorManager;
    Sensor f9575j;
    public int f9576k;
    boolean f9577l;
    boolean f9578m;
    boolean f9579n;
    boolean f9580o;
    String f9581p;
    String f9582q;
    SharedPreferences sharedPreferences;
    RecyclerView recyclerView;
    C1591h f9585t;
    ArrayList<String> f9586u = new ArrayList();
    private int f9587v;
    private SensorEventListener f9588w = new C30393(this);

    class C30382 extends TimerTask {
        final /* synthetic */ NewImagePhotoActivity f9561a;

        C30382(NewImagePhotoActivity newImagePhotoActivity) {
            this.f9561a = newImagePhotoActivity;
        }

        public void run() {
            try {
                if ((SecurityHelpers.isCallRinging(this.f9561a.telephonyManager) || !SecurityHelpers.m14854b(this.f9561a.getApplicationContext()).equals(getPackageName())) && !this.f9561a.f9580o) {
                    NewImageAlbumActivity.f9539d.finish();
                    if (MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                    }
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                    this.f9561a.finish();
                }
                if (!SecurityHelpers.isScreenON(this.f9561a.powerManager)) {
                    NewImageAlbumActivity.f9539d.finish();
                    if (MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                    }
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                    this.f9561a.finish();
                    Intent intent = new Intent(this.f9561a.getApplicationContext(), Calculator.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    this.f9561a.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C30393 implements SensorEventListener {
        final /* synthetic */ NewImagePhotoActivity f9562a;

        C30393(NewImagePhotoActivity newImagePhotoActivity) {
            this.f9562a = newImagePhotoActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f9562a.f9577l) {
                    this.f9562a.f9577l = true;
                    if (this.f9562a.f9576k == 1) {
                        C1131f.m5806a(this.f9562a.getApplicationContext(), this.f9562a.getPackageManager(), this.f9562a.sharedPreferences.getString("Package_Name", null));
                    }
                    if (this.f9562a.f9576k == 2) {
                        this.f9562a.f9581p = this.f9562a.sharedPreferences.getString("URL_Name", null);
                        this.f9562a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f9562a.f9581p)));
                    }
                    if (this.f9562a.f9576k == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.f9562a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    class C30404 implements C1230i.C1075a {
        final /* synthetic */ NewImagePhotoActivity f9563a;

        C30404(NewImagePhotoActivity newImagePhotoActivity) {
            this.f9563a = newImagePhotoActivity;
        }

        public void mo969a(boolean z) {
            this.f9563a.f9586u.clear();
            if (z) {
                Toast.makeText(this.f9563a.getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this.f9563a.getApplicationContext(), this.f9563a.getResources().getString(R.string.unsusuccess_text_sdcard), Toast.LENGTH_LONG).show();
            }
            this.f9563a.f9572g.putExtra("doRefresh", true);
            this.f9563a.f9572g.putExtra("selectedAlbumName", this.f9563a.f9582q);
            this.f9563a.setResult(-1, this.f9563a.f9572g);
            this.f9563a.finish();
        }
    }

    class C30425 implements C3150b.C1055c {
        final /* synthetic */ NewImagePhotoActivity f9565a;

        class C30411 implements C3150b.C1055c {
            final /* synthetic */ C30425 f9564a;

            C30411(C30425 c30425) {
                this.f9564a = c30425;
            }

            public void mo966a(boolean z) {
                if (z) {
                    Toast.makeText(this.f9564a.f9565a.getApplicationContext(), this.f9564a.f9565a.getResources().getString(R.string.delete_files_descr_manual_toast), Toast.LENGTH_LONG).show();
                    this.f9564a.f9565a.f9572g.putExtra("doRefresh", true);
                    this.f9564a.f9565a.f9572g.putExtra("selectedAlbumName", this.f9564a.f9565a.f9582q);
                    this.f9564a.f9565a.setResult(-1, this.f9564a.f9565a.f9572g);
                    this.f9564a.f9565a.finish();
                    return;
                }
                C3150b.m14839c(this.f9564a.f9565a);
            }
        }

        C30425(NewImagePhotoActivity newImagePhotoActivity) {
            this.f9565a = newImagePhotoActivity;
        }

        public void mo966a(boolean z) {
            if (z) {
                this.f9565a.f9580o = true;
                this.f9565a.startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), 42);
                return;
            }
            C3150b.m14831a(this.f9565a, new C30411(this));
        }
    }

    private ArrayList<PictureModel> m14498a(String str) {
        ArrayList<PictureModel> arrayList = new ArrayList();
        try {
            String str2 = "datetaken";
            Cursor query = getContentResolver().query(Media.EXTERNAL_CONTENT_URI, null, "bucket_id = \"" + str + "\"", null, "datetaken DESC");
            if (query.getCount() > 0) {
                query.moveToFirst();
                do {
                    String string = query.getString(1);
                    arrayList.add(new PictureModel(string, true, new File(string).getName(), true));
                } while (query.moveToNext());
                return arrayList;
            }
            query.close();
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private void m14499a() {
        C3150b.m14832a((Activity) this, new C30425(this), false);
    }

    public void mo966a(boolean z) {
        if (z) {
            setResult(-1);
            finish();
        }
    }

    public void mo975c(int i) {
        if (i != 0) {
            getSupportActionBar().setTitle(i + " selected");
        } else {
            getSupportActionBar().setTitle(("" + this.f9573h));
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 42) {
            this.f9580o = false;
            if (i2 == -1) {
                Uri data = intent.getData();
                if (C1132g.m5814a(data)) {
                    getContentResolver().takePersistableUriPermission(data, intent.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Editor edit = this.sharedPreferences.edit();
                    edit.putString("treeUri", "" + data);
                    edit.putString("extSdCardPath", C1131f.f3326l);
                    edit.commit();
                    if (!(this.f9586u == null || this.f9586u.isEmpty())) {
                        new C1230i(this, this.f9586u, data, "image/*", new C30404(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Grant Failed. Please choose the root directory of SD card", Toast.LENGTH_SHORT).show();
                    m14499a();
                }
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (this.f9571f) {
            Toast.makeText(getApplicationContext(), "please wait...", Toast.LENGTH_SHORT).show();
        } else if (this.f9585t.f4513g > 0) {
            this.f9585t.m7225c();
        } else {
            super.onBackPressed();
        }
    }

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f9587v = C1131f.getCurrentStyle(this.sharedPreferences);
        if (this.f9587v != R.style.AppTheme) {
            setTheme(this.f9587v);
        }
        this.f9568c = getIntent().getStringExtra("currentPath");
        this.f9578m = getIntent().getBooleanExtra("fromSdCard", false);
        this.f9579n = getIntent().getBooleanExtra("fromSdCard1", false);
        getWindow().addFlags(128);
        setContentView((int) R.layout.activity_new_video_images);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.f9569d = new C2930c(getApplicationContext());
        this.powerManager = (PowerManager) getSystemService("power");
        this.telephonyManager = (TelephonyManager) getSystemService("phone");
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        int a = C3044b.m14508a(this, 1.0f);
        int a2 = C3044b.m14508a(this, 1.0f);
        this.recyclerView.addItemDecoration(new C1127d(a, a2, a, a2));
        this.f9573h = getIntent().getStringExtra("albumName");
        final String stringExtra = getIntent().getStringExtra("bucketId");
        getSupportActionBar().setTitle("" + this.f9573h);
        new AsyncTask<Void, Void, ArrayList<PictureModel>>() {
            protected ArrayList<PictureModel> doInBackground(Void... voidArr) {
                return m14498a(stringExtra);
            }

            protected void onPostExecute(ArrayList<PictureModel> arrayList) {
                f9570e = arrayList;
                if (arrayList.size() < 1) {
                    findViewById(R.id.tvNoImage).setVisibility(0);
                    recyclerView.setVisibility(8);
                    return;
                }
                f9585t = new C1591h(NewImagePhotoActivity.this, f9570e, false);
                f9585t.m7223a(NewImagePhotoActivity.this);
                f9585t.m7221a(0);
                recyclerView.setAdapter(f9585t);
            }
        }.execute(new Void[0]);
        try {
            if (this.sharedPreferences.getBoolean("faceDown", false)) {
                this.f9576k = this.sharedPreferences.getInt("selectedPos", 0);
                this.sensorManager = (SensorManager) getSystemService("sensor");
                this.f9575j = (Sensor) this.sensorManager.getSensorList(1).get(0);
            }
        } catch (Exception e) {
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected void onDestroy() {
        getWindow().clearFlags(128);
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        } else if (menuItem.getItemId() == R.id.item_selectall) {
            this.f9585t.m7220a();
        } else if (menuItem.getItemId() == R.id.item_lock) {
            ArrayList b = this.f9585t.m7224b();
            if (b.isEmpty()) {
                Toast.makeText(this, "Select atLeast one Picture", Toast.LENGTH_SHORT).show();
            } else {
                this.f9572g.putExtra("listSelected", b);
                this.f9572g.putExtra("fromSdCard", this.f9578m);
                this.f9572g.putExtra("fromSdCardReal", this.f9579n);
                setResult(-1, this.f9572g);
                finish();
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
//        overridePendingTransition(0, R.anim.exit);
        super.onPause();
    }

    protected void onStart() {
        this.f9580o = false;
        try {
            if (this.sensorManager != null) {
                this.sensorManager.registerListener(this.f9588w, this.f9575j, 3);
            }
        } catch (Exception e) {
        }
        super.onStart();
    }

    protected void onStop() {
        try {
            if (this.sensorManager != null) {
                this.sensorManager.unregisterListener(this.f9588w);
            }
        } catch (Exception e) {
        }
        if (this.telephonyManager != null) {
            new Timer().schedule(new C30382(this), 1000);
        }
        super.onStop();
    }
}
