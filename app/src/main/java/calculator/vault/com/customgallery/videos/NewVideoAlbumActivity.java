package calculator.vault.com.customgallery.videos;

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
import android.provider.MediaStore.Video.Media;
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
import calculator.vault.com.customgallery.pictures.PictureModel;
import calculator.vault.com.customgallery.pictures.C3044b;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.lock.MainActivity;
import calculator.vault.com.lock.ViewAlbumActivity;
import calculator.vault.com.p029h.ItemClickInterface;
import calculator.vault.com.p068a.C1594i;

public class NewVideoAlbumActivity extends AppCompatActivity implements ItemClickInterface {
    public static NewVideoAlbumActivity f9632o;
    PowerManager f9633a;
    TelephonyManager f9634b;
    TextView f9635c;
    String f9636d;
    boolean f9637e;
    SensorManager f9638f;
    Sensor f9639g;
    public int f9640h;
    boolean f9641i;
    String f9642j;
    SharedPreferences f9643k;
    RecyclerView f9644l;
    C1594i f9645m;
    String f9646n;
    int f9647p = 800;
    int f9648q;
    private ArrayList<PictureModel> f9649r;
    private int f9650s;
    private SensorEventListener f9651t = new C30553(this);

    class C30531 extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ NewVideoAlbumActivity f9627a;

        class C30511 implements Runnable {
            final /* synthetic */ C30531 f9625a;

            C30511(C30531 c30531) {
                this.f9625a = c30531;
            }

            public void run() {
                if (this.f9625a.f9627a.f9649r.size() > 0 && this.f9625a.f9627a.f9635c.getVisibility() == View.VISIBLE) {
                    this.f9625a.f9627a.f9635c.startAnimation(AnimationUtils.loadAnimation(NewVideoAlbumActivity.f9632o, R.anim.fade_in));
                    this.f9625a.f9627a.f9635c.setText("Just few moments more..");
                }
            }
        }

        class C30522 implements Runnable {
            final /* synthetic */ C30531 f9626a;

            C30522(C30531 c30531) {
                this.f9626a = c30531;
            }

            public void run() {
                Toast.makeText(this.f9626a.f9627a.getApplicationContext(), "Error getting video albums,try again later", Toast.LENGTH_SHORT).show();
            }
        }

        C30531(NewVideoAlbumActivity newVideoAlbumActivity) {
            this.f9627a = newVideoAlbumActivity;
        }

        protected Void doInBackground(Void... voidArr) {
            try {
                this.f9627a.m14521a();
            } catch (Exception e) {
                this.f9627a.runOnUiThread(new C30522(this));
            }
            return null;
        }

        protected void onPostExecute(Void voidR) {
            if (this.f9627a.f9649r.size() < 1) {
                this.f9627a.f9635c.setText("No Videos");
                return;
            }
            this.f9627a.f9645m = new C1594i(this.f9627a, this.f9627a.f9649r);
            this.f9627a.f9645m.m7229a(this.f9627a);
            this.f9627a.f9644l.setAdapter(this.f9627a.f9645m);
            this.f9627a.f9635c.setVisibility(View.GONE);
        }


        protected void onPreExecute() {
            this.f9627a.f9635c.setVisibility(View.VISIBLE);
            this.f9627a.f9635c.setText("Loading gallery..");
            new Handler().postDelayed(new C30511(this), 5000);
            super.onPreExecute();
        }
    }

    class C30542 extends TimerTask {
        final /* synthetic */ NewVideoAlbumActivity f9628a;

        C30542(NewVideoAlbumActivity newVideoAlbumActivity) {
            this.f9628a = newVideoAlbumActivity;
        }

        public void run() {
            try {
                if (SecurityHelpers.isCallRinging(this.f9628a.f9634b) || !SecurityHelpers.m14854b(this.f9628a.getApplicationContext()).equals(this.f9628a.getPackageName())) {
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                    if (MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                    }
                    this.f9628a.finish();
                }
                if (!SecurityHelpers.isScreenON(this.f9628a.f9633a)) {
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                    if (MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                    }
                    this.f9628a.finish();
                    Intent intent = new Intent(this.f9628a.getApplicationContext(), Calculator.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    this.f9628a.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C30553 implements SensorEventListener {
        final /* synthetic */ NewVideoAlbumActivity f9629a;

        C30553(NewVideoAlbumActivity newVideoAlbumActivity) {
            this.f9629a = newVideoAlbumActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f9629a.f9641i) {
                    this.f9629a.f9641i = true;
                    if (this.f9629a.f9640h == 1) {
                        C1131f.m5806a(this.f9629a.getApplicationContext(), this.f9629a.getPackageManager(), this.f9629a.f9643k.getString("Package_Name", null));
                    }
                    if (this.f9629a.f9640h == 2) {
                        this.f9629a.f9642j = this.f9629a.f9643k.getString("URL_Name", null);
                        this.f9629a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f9629a.f9642j)));
                    }
                    if (this.f9629a.f9640h == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        this.f9629a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    private void m14521a() {
        String[] strArr = new String[]{"bucket_id", "bucket_display_name", "datetaken", "_data"};
        Uri uri = Media.EXTERNAL_CONTENT_URI;
        Cursor query = getContentResolver().query(uri, strArr, "1) GROUP BY 1,(2", null, "MAX(datetaken) DESC");
        if (query.moveToFirst()) {
            int columnIndex = query.getColumnIndex("bucket_display_name");
            int columnIndex2 = query.getColumnIndex("_data");
            int columnIndex3 = query.getColumnIndex("bucket_id");
            do {
                String string = query.getString(columnIndex);
                String string2 = query.getString(columnIndex2);
                String string3 = query.getString(columnIndex3);
                if (string != null && string.length() > 0) {
                    File file = new File(string2);
                    if (file != null) {
                        PictureModel pictureModel = new PictureModel(file.getParent(), false, file.getParentFile().getName(), false);
                        pictureModel.m14503a(string3);
                        if (!file.getParentFile().getAbsolutePath().startsWith(this.f9646n)) {
                            pictureModel.m14505b(true);
                        }
                        this.f9649r.add(pictureModel);
                    }
                }
            } while (query.moveToNext());
        }
        query.close();
    }

    public void onItemClicked(View view, final int i) {
        Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale_item);
        loadAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                f9648q = i;
                PictureModel pictureModel = (PictureModel) f9649r.get(i);
                Intent intent = new Intent(getApplicationContext(),
                        NewVidePhotoActivity.class);
                intent.putExtra("albumName", pictureModel.f9591c);
                intent.putExtra("bucketId", pictureModel.m14502a());
                String str = "fromSdCard";
                boolean z = pictureModel.m14507c() && C1131f.f3323i;
                intent.putExtra(str, z);
                intent.putExtra("fromSdCard1", pictureModel.m14507c());
                intent.putExtra("currentPath", f9636d);
                intent.putExtra("fromAlbum", f9637e);
                startActivityForResult(intent, f9647p);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
        view.startAnimation(loadAnimation);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && intent != null) {
            setResult(-1, intent);
            finish();
        }
        super.onActivityResult(i, i2, intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f9643k = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f9650s = C1131f.getCurrentStyle(this.f9643k);
        if (this.f9650s != R.style.AppTheme) {
            setTheme(this.f9650s);
        }
        getWindow().addFlags(128);
        this.f9636d = getIntent().getStringExtra("currentPath");
        this.f9637e = getIntent().getBooleanExtra("fromAlbum", false);
        setContentView((int) R.layout.activity_new_video_album);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        f9632o = this;
        this.f9635c = (TextView) findViewById(R.id.textView2);
        this.f9633a = (PowerManager) getSystemService(POWER_SERVICE);
        this.f9634b = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        this.f9646n = Environment.getExternalStorageDirectory().getAbsolutePath();
        this.f9644l = (RecyclerView) findViewById(R.id.recyclerView);
        this.f9644l.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        int a = C3044b.m14508a(this, 5.0f);
        int a2 = C3044b.m14508a(this, 3.0f);
        this.f9644l.addItemDecoration(new C1127d(a, a2, a, a2));
        this.f9649r = new ArrayList();
        new C30531(this).execute(new Void[0]);
        try {
            if (this.f9643k.getBoolean("faceDown", false)) {
                this.f9640h = this.f9643k.getInt("selectedPos", 0);
                this.f9638f = (SensorManager) getSystemService(SENSOR_SERVICE);
                this.f9639g = (Sensor) this.f9638f.getSensorList(1).get(0);
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

    protected void onResume() {
        if (this.f9645m != null) {
            this.f9645m.notifyDataSetChanged();
        }
        super.onResume();
    }

    protected void onStart() {
        try {
            if (this.f9638f != null) {
                this.f9638f.registerListener(this.f9651t, this.f9639g, 3);
            }
        } catch (Exception e) {
        }
        super.onStart();
    }

    protected void onStop() {
        try {
            if (this.f9638f != null) {
                this.f9638f.unregisterListener(this.f9651t);
            }
        } catch (Exception e) {
        }
        if (this.f9634b != null) {
            new Timer().schedule(new C30542(this), 1000);
        }
        super.onStop();
    }
}
