package calculator.vault.com.customgallery.pictures;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.provider.MediaStore.Images.Media;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.calculator.Calculator;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.lock.MainActivity;
import calculator.vault.com.lock.RecyclerDecoration;
import calculator.vault.com.lock.ViewAlbumActivity;
import calculator.vault.com.p029h.ItemClickInterface;
import calculator.vault.com.p068a.RecyclerAdapter;

public class NewImageAlbumActivity extends AppCompatActivity implements ItemClickInterface {
    public static NewImageAlbumActivity f9539d;
    PowerManager f9540a;
    TelephonyManager f9541b;
    TextView f9542c;
    SensorManager f9543e;
    Sensor f9544f;
    public int f9545g;
    boolean f9546h;
    String f9547i;
    SharedPreferences f9548j;
    String f9549k;
    String currentPath;
    boolean fromAlbum;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    int f9554p;
    int f9555q = 900;
    private ArrayList<PictureModel> f9556r;
    private int f9557s;
    private SensorEventListener f9558t = new C30353(this);

    class C30331 extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ NewImageAlbumActivity f9534a;

        class C30311 implements Runnable {
            final /* synthetic */ C30331 f9532a;

            C30311(C30331 c30331) {
                this.f9532a = c30331;
            }

            public void run() {
                if (this.f9532a.f9534a.f9556r.size() > 0) {
                    if (this.f9532a.f9534a.f9542c.getVisibility() == View.VISIBLE) {
                        this.f9532a.f9534a.f9542c.startAnimation(AnimationUtils.loadAnimation(NewImageAlbumActivity.f9539d, R.anim.fade_in));
                    }
                    this.f9532a.f9534a.f9542c.setText("just few moments more..");
                }
            }
        }

        class C30322 implements Runnable {
            final /* synthetic */ C30331 f9533a;

            C30322(C30331 c30331) {
                this.f9533a = c30331;
            }

            public void run() {
                Toast.makeText(this.f9533a.f9534a.getApplicationContext(), "Error getting albums list, try again", Toast.LENGTH_LONG).show();
            }
        }

        C30331(NewImageAlbumActivity newImageAlbumActivity) {
            this.f9534a = newImageAlbumActivity;
        }

        protected Void doInBackground(Void... voidArr) {
            try {
                this.f9534a.m14489a();
            } catch (Exception e) {
                this.f9534a.runOnUiThread(new C30322(this));
            }
            return null;
        }

        protected void onPostExecute(Void voidR) {
            if (this.f9534a.f9556r.size() < 1) {
                this.f9534a.f9542c.setText("No Images");
                return;
            }
            this.f9534a.f9542c.setVisibility(View.GONE);
            this.f9534a.recyclerAdapter = new RecyclerAdapter(this.f9534a, this.f9534a.f9556r);
            this.f9534a.recyclerAdapter.m7214a(this.f9534a);
            this.f9534a.recyclerView.setAdapter(recyclerAdapter);
        }

        protected void onPreExecute() {
            this.f9534a.f9542c.setVisibility(View.VISIBLE);
            this.f9534a.f9542c.setText("Loading gallery...");
            new Handler().postDelayed(new C30311(this), 5000);
            super.onPreExecute();
        }
    }

    class C30342 extends TimerTask {
        final /* synthetic */ NewImageAlbumActivity f9535a;

        C30342(NewImageAlbumActivity newImageAlbumActivity) {
            this.f9535a = newImageAlbumActivity;
        }

        public void run() {
            try {
                if (SecurityHelpers.isCallRinging(this.f9535a.f9541b) || !SecurityHelpers.m14854b(this.f9535a.getApplicationContext()).equals(this.f9535a.getPackageName())) {
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                    if (MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                    }
                    this.f9535a.finish();
                }
                if (!SecurityHelpers.isScreenON(this.f9535a.f9540a)) {
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                    if (MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                    }
                    this.f9535a.finish();
                    Intent intent = new Intent(this.f9535a.getApplicationContext(), Calculator.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    this.f9535a.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C30353 implements SensorEventListener {
        final /* synthetic */ NewImageAlbumActivity f9536a;

        C30353(NewImageAlbumActivity newImageAlbumActivity) {
            this.f9536a = newImageAlbumActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f9536a.f9546h) {
                    this.f9536a.f9546h = true;
                    if (this.f9536a.f9545g == 1) {
                        C1131f.m5806a(this.f9536a.getApplicationContext(), this.f9536a.getPackageManager(), this.f9536a.f9548j.getString("Package_Name", null));
                    }
                    if (this.f9536a.f9545g == 2) {
                        this.f9536a.f9547i = this.f9536a.f9548j.getString("URL_Name", null);
                        this.f9536a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f9536a.f9547i)));
                    }
                    if (this.f9536a.f9545g == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.f9536a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    private void m14489a() {
        String[] strArr = new String[]{"bucket_id", "bucket_display_name", "datetaken", "_data"};
        Uri uri = Media.EXTERNAL_CONTENT_URI;
        Cursor query = getContentResolver().query(uri, strArr, "1) GROUP BY 1,(2", null, "MAX(datetaken) DESC");
        if (query == null || !query.moveToFirst()) {
            query.close();
        }
        int columnIndex = query.getColumnIndex("bucket_id");
        int columnIndex2 = query.getColumnIndex("bucket_display_name");
        int columnIndex3 = query.getColumnIndex("_data");
        do {
            String string = query.getString(columnIndex2);
            String string2 = query.getString(columnIndex);
            String string3 = query.getString(columnIndex3);
            if (string != null && string.length() > 0) {
                File file = new File(string3);
                if (file != null) {
                    PictureModel pictureModel = new PictureModel(file.getParent(), false, file.getParentFile().getName(), false);
                    pictureModel.m14503a(string2);
                    if (!file.getParentFile().getAbsolutePath().startsWith(this.f9549k)) {
                        pictureModel.m14505b(true);
                    }
                    this.f9556r.add(pictureModel);
                }
            }
        } while (query.moveToNext());
        query.close();
    }

    public void onItemClicked(View view, final int i) {
        Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_item);
        loadAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                f9554p = i;
                Intent intent = new Intent(getApplicationContext(), NewImagePhotoActivity.class);
                PictureModel pictureModel = (PictureModel) f9556r.get(i);
                intent.putExtra("albumName", pictureModel.f9591c);
                intent.putExtra("bucketId", pictureModel.m14502a());
                intent.putExtra("currentPath", currentPath);
                intent.putExtra("fromAlbum", fromAlbum);
                String str = "fromSdCard";
                boolean z = pictureModel.m14507c() && C1131f.f3323i;
                intent.putExtra(str, z);
                intent.putExtra("fromSdCard1", pictureModel.m14507c());
                startActivityForResult(intent, f9555q);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        view.startAnimation(loadAnimation);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == this.f9555q && i2 == -1 && intent != null) {
            setResult(-1, intent);
            finish();
        }
        super.onActivityResult(i, i2, intent);
    }

    protected void
    onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f9548j = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f9557s = C1131f.getCurrentStyle(this.f9548j);
        if (this.f9557s != R.style.AppTheme) {
            setTheme(this.f9557s);
        }
        this.currentPath = getIntent().getStringExtra("currentPath");
        getWindow().addFlags(128);
        setContentView((int) R.layout.activity_new_video_album);
        f9539d = this;
        this.fromAlbum = getIntent().getBooleanExtra("fromAlbum", false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.f9549k = Environment.getExternalStorageDirectory().getAbsolutePath();
        this.f9540a = (PowerManager) getSystemService(POWER_SERVICE);
        this.f9541b = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        this.recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        int a = C3044b.m14508a(this, 5.0f);
        int a2 = C3044b.m14508a(this, 3.0f);
        this.recyclerView.addItemDecoration(new RecyclerDecoration(a, a2, a, a2));
        this.f9542c = (TextView) findViewById(R.id.textView2);
        this.f9542c.setTypeface(C1131f.f3315a);
        this.f9556r = new ArrayList();
        new C30331(this).execute();
        try {
            if (this.f9548j.getBoolean("faceDown", false)) {
                this.f9545g = this.f9548j.getInt("selectedPos", 0);
                this.f9543e = (SensorManager) getSystemService(SENSOR_SERVICE);
                this.f9544f = (Sensor) this.f9543e.getSensorList(1).get(0);
                this.f9543e.registerListener(this.f9558t, this.f9544f, 3);
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
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
        overridePendingTransition(0, R.anim.exit);
        super.onPause();
    }

    public void onResume() {
        if (this.recyclerAdapter != null) {
            this.recyclerAdapter.notifyDataSetChanged();
        }
        try {
            if (this.f9543e != null) {
                this.f9543e.registerListener(this.f9558t, this.f9544f, 3);
            }
        } catch (Exception e) {
        }
        super.onResume();
    }

    protected void onStop() {
        try {
            if (this.f9543e != null) {
                this.f9543e.unregisterListener(this.f9558t);
            }
        } catch (Exception e) {
        }
        if (this.f9541b != null) {
            new Timer().schedule(new C30342(this), 1000);
        }
        super.onStop();
    }
}
