package calculator.vault.com.customgallery.videos;

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
import android.provider.MediaStore.Video.Media;
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
import calculator.vault.com.customgallery.pictures.PictureModel;
import calculator.vault.com.customgallery.pictures.C3044b;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.lock.C1132g;
import calculator.vault.com.lock.MainActivity;
import calculator.vault.com.lock.ViewAlbumActivity;
import calculator.vault.com.p068a.C1598j;
import calculator.vault.com.p084i.C2930c;

public class NewVidePhotoActivity extends AppCompatActivity implements C3150b.C1055c {
    PowerManager f9603a;
    TelephonyManager f9604b;
    String f9605c;
    ArrayList<PictureModel> f9606d;
    C2930c f9607e;
    Intent f9608f = new Intent();
    SensorManager f9609g;
    Sensor f9610h;
    public int f9611i;
    boolean f9612j;
    boolean f9613k;
    boolean f9614l;
    boolean f9615m;
    String f9616n;
    SharedPreferences f9617o;
    RecyclerView f9618p;
    C1598j f9619q;
    ArrayList<String> f9620r = new ArrayList();
    String f9621s;
    boolean f9622t;
    private int f9623u;
    private SensorEventListener f9624v = new C30473(this);

    class C30462 extends TimerTask {
        final /* synthetic */ NewVidePhotoActivity f9598a;

        C30462(NewVidePhotoActivity newVidePhotoActivity) {
            this.f9598a = newVidePhotoActivity;
        }

        public void run() {
            try {
                if ((SecurityHelpers.isCallRinging(this.f9598a.f9604b) || !SecurityHelpers.m14854b(this.f9598a.getApplicationContext()).equals(this.f9598a.getPackageName())) && !this.f9598a.f9615m) {
                    NewVideoAlbumActivity.f9632o.finish();
                    this.f9598a.finish();
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                    if (MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                    }
                }
                if (!SecurityHelpers.isScreenON(this.f9598a.f9603a)) {
                    NewVideoAlbumActivity.f9632o.finish();
                    this.f9598a.finish();
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                    if (MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                    }
                    Intent intent = new Intent(this.f9598a.getApplicationContext(), Calculator.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    this.f9598a.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C30473 implements SensorEventListener {
        final /* synthetic */ NewVidePhotoActivity f9599a;

        C30473(NewVidePhotoActivity newVidePhotoActivity) {
            this.f9599a = newVidePhotoActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f9599a.f9612j) {
                    this.f9599a.f9612j = true;
                    if (this.f9599a.f9611i == 1) {
                        C1131f.m5806a(this.f9599a.getApplicationContext(), this.f9599a.getPackageManager(), this.f9599a.f9617o.getString("Package_Name", null));
                    }
                    if (this.f9599a.f9611i == 2) {
                        this.f9599a.f9616n = this.f9599a.f9617o.getString("URL_Name", null);
                        this.f9599a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f9599a.f9616n)));
                    }
                    if (this.f9599a.f9611i == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.f9599a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    class C30484 implements C1230i.C1075a {
        final /* synthetic */ NewVidePhotoActivity f9600a;

        C30484(NewVidePhotoActivity newVidePhotoActivity) {
            this.f9600a = newVidePhotoActivity;
        }

        public void mo969a(boolean z) {
            this.f9600a.f9620r.clear();
            if (z) {
                Toast.makeText(this.f9600a.getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this.f9600a.getApplicationContext(), this.f9600a.getResources().getString(R.string.unsusuccess_text_sdcard), Toast.LENGTH_LONG).show();
            }
            this.f9600a.f9608f.putExtra("doRefresh", true);
            this.f9600a.f9608f.putExtra("selectedAlbumName", this.f9600a.f9621s);
            this.f9600a.setResult(-1, this.f9600a.f9608f);
            this.f9600a.finish();
        }
    }

    class C30505 implements C3150b.C1055c {
        final /* synthetic */ NewVidePhotoActivity f9602a;

        class C30491 implements C3150b.C1055c {
            final /* synthetic */ C30505 f9601a;

            C30491(C30505 c30505) {
                this.f9601a = c30505;
            }

            public void mo966a(boolean z) {
                if (z) {
                    Toast.makeText(this.f9601a.f9602a.getApplicationContext(), this.f9601a.f9602a.getResources().getString(R.string.delete_files_descr_manual_toast), Toast.LENGTH_LONG).show();
                    this.f9601a.f9602a.f9608f.putExtra("doRefresh", true);
                    this.f9601a.f9602a.f9608f.putExtra("selectedAlbumName", this.f9601a.f9602a.f9621s);
                    this.f9601a.f9602a.setResult(-1, this.f9601a.f9602a.f9608f);
                    this.f9601a.f9602a.finish();
                    return;
                }
                C3150b.m14839c(this.f9601a.f9602a);
            }
        }

        C30505(NewVidePhotoActivity newVidePhotoActivity) {
            this.f9602a = newVidePhotoActivity;
        }

        public void mo966a(boolean z) {
            if (z) {
                this.f9602a.f9615m = true;
                this.f9602a.startActivityForResult(new Intent("android.intent.action.OPEN_DOCUMENT_TREE"), 42);
                return;
            }
            C3150b.m14831a(this.f9602a, new C30491(this));
        }
    }

    private ArrayList<PictureModel> m14515a(String str) {
        ArrayList<PictureModel> arrayList = new ArrayList();
        try {
            String str2 = "datetaken";
            Cursor query = getContentResolver().query(Media.EXTERNAL_CONTENT_URI, null, "bucket_id= \"" + str + "\"", null, "datetaken DESC");
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

    private void m14516a() {
        C3150b.m14832a((Activity) this, new C30505(this), false);
    }

    public void mo966a(boolean z) {
        if (z) {
            setResult(-1, this.f9608f);
            finish();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 42) {
            this.f9615m = false;
            if (i2 == -1) {
                Uri data = intent.getData();
                if (C1132g.m5814a(data)) {
                    getContentResolver().takePersistableUriPermission(data, intent.getFlags() & Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Editor edit = this.f9617o.edit();
                    edit.putString("treeUri", "" + data);
                    edit.putString("extSdCardPath", C1131f.f3326l);
                    edit.commit();
                    if (!(this.f9620r == null || this.f9620r.isEmpty())) {
                        new C1230i(this, this.f9620r, data, "video/*", new C30484(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Grant Failed. Please choose the root directory of SD card", Toast.LENGTH_SHORT).show();
                    m14516a();
                }
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (this.f9622t) {
            Toast.makeText(getApplicationContext(), "Please wait..\nUnhiding file(s)", Toast.LENGTH_LONG).show();
        } else if (this.f9619q.f4551g > 0) {
            this.f9619q.m7235a();
        } else {
            super.onBackPressed();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f9617o = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f9623u = C1131f.getCurrentStyle(this.f9617o);
        if (this.f9623u != R.style.AppTheme) {
            setTheme(this.f9623u);
        }
        getWindow().addFlags(128);
        this.f9605c = getIntent().getStringExtra("currentPath");
        this.f9614l = getIntent().getBooleanExtra("fromSdCard1", false);
        this.f9613k = getIntent().getBooleanExtra("fromSdCard", false);
        setContentView((int) R.layout.activity_new_video_images);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.f9607e = new C2930c(getApplicationContext());
        this.f9603a = (PowerManager) getSystemService(POWER_SERVICE);
        this.f9604b = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        this.f9618p = (RecyclerView) findViewById(R.id.recyclerView);
        this.f9618p.setLayoutManager(new GridLayoutManager(getApplicationContext(), 3));
        int a = C3044b.m14508a(this, 1.0f);
        int a2 = C3044b.m14508a(this, 1.0f);
        this.f9618p.addItemDecoration(new C1127d(a, a2, a, a2));
        String stringExtra = getIntent().getStringExtra("albumName");
        final String stringExtra2 = getIntent().getStringExtra("bucketId");
        getSupportActionBar().setTitle("" + stringExtra);
        new AsyncTask<Void, Void, ArrayList<PictureModel>>() {

            protected ArrayList<PictureModel> m14509a(Void... voidArr) {
                return m14515a(stringExtra2);
            }

            protected void m14510a(ArrayList<PictureModel> arrayList) {
                f9606d = arrayList;
                if (arrayList.size() > 0) {
                    f9619q = new C1598j(NewVidePhotoActivity.this.getApplicationContext(), arrayList);
                    f9619q.m7236a(0);
                    f9618p.setAdapter(f9619q);
                    return;
                }
                Toast.makeText(getApplicationContext(), "You have no videos in gallery", Toast.LENGTH_LONG).show();
            }

            protected /* synthetic */ ArrayList<PictureModel> doInBackground(Void[] objArr) {
                return m14509a((Void[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(ArrayList<PictureModel> obj) {
                m14510a((ArrayList) obj);
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        try {
            if (this.f9617o.getBoolean("faceDown", false)) {
                this.f9611i = this.f9617o.getInt("selectedPos", 0);
                this.f9609g = (SensorManager) getSystemService(SENSOR_SERVICE);
                this.f9610h = (Sensor) this.f9609g.getSensorList(1).get(0);
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
            this.f9619q.m7238b();
        } else if (menuItem.getItemId() == R.id.item_lock) {
            ArrayList c = this.f9619q.m7239c();
            if (c.isEmpty()) {
                Toast.makeText(this, "Select atLeast one Video", Toast.LENGTH_LONG).show();
            } else {
                this.f9608f.putExtra("listSelected", c);
                this.f9608f.putExtra("fromSdCard", this.f9613k);
                this.f9608f.putExtra("fromSdCardReal", this.f9614l);
                setResult(-1, this.f9608f);
                finish();
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
        overridePendingTransition(0, R.anim.exit);
        super.onPause();
    }

    protected void onStart() {
        this.f9615m = false;
        try {
            if (this.f9609g != null) {
                this.f9609g.registerListener(this.f9624v, this.f9610h, 3);
            }
        } catch (Exception e) {
        }
        super.onStart();
    }

    protected void onStop() {
        try {
            if (this.f9609g != null) {
                this.f9609g.unregisterListener(this.f9624v);
            }
        } catch (Exception e) {
        }
        try {
            if (this.f9604b != null) {
                new Timer().schedule(new C30462(this), 500);
            }
        } catch (Exception e2) {
        }
        super.onStop();
    }
}
