package calculator.vault.com.safebrowser;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.lock.MainActivity;
import calculator.vault.com.lock.ManageSpaceActivity;
import calculator.vault.com.lock.ViewAlbumActivity;
import calculator.vault.com.p068a.C1577d;
import calculator.vault.com.p068a.C1583e;
import calculator.vault.com.p068a.C1609m;
import calculator.vault.com.p068a.C1610n;


public class MainBrowserActivity extends AppCompatActivity implements TextWatcher, OnClickListener, OnCheckedChangeListener {
    public static MainBrowserActivity f9367a;
    public static ArrayList<String> f9368v = new ArrayList();
    public static ArrayList<C1577d> f9369w = new ArrayList();
    private int f9371B;
    private ArrayList<Integer> f9372C = new ArrayList();
    private SensorEventListener f9373D = new SensorEventListener() {

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !f9389q) {
                    f9389q = true;
                    if (f9388p == 1) {
                        C1131f.m5806a(getApplicationContext(), getPackageManager(), f9391s.getString("Package_Name", null));
                    }
                    if (f9388p == 2) {
                        f9390r = f9391s.getString("URL_Name", null);
                        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(f9390r)));
                    }
                    if (f9388p == 0) {
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
    ArrayList<C1577d> f9374b = new ArrayList();
    ArrayList<C1610n> f9375c = new ArrayList();
    ArrayList<C1610n> f9376d = new ArrayList(this.f9375c);
    C1583e f9377e;
    C1609m f9378f;
    GridLayoutManager gridLayoutManagerBookmark;
    SaveBrowserDatabaseHelper f9380h;
    View urlEditor;
    View f9382j;
    TelephonyManager f9383k;
    PowerManager f9384l;
    boolean f9385m;
    SensorManager f9386n;
    Sensor f9387o;
    public int f9388p;
    boolean f9389q;
    String f9390r;
    SharedPreferences f9391s;
    Editor f9392t;
    EditText f9393u;
    public RecyclerView bookmarkRecycler;
    public RecyclerView recyclerVisited;
    public final int f9396z = 454;

    public interface C2989a {
        void mo2109a(boolean z);
    }


    class C29989 extends TimerTask {
        final /* synthetic */ MainBrowserActivity mainBrowserActivity;

        C29989(MainBrowserActivity mainBrowserActivity) {
            this.mainBrowserActivity = mainBrowserActivity;
        }

        public void run() {
            try {
                if ((SecurityHelpers.isCallRinging(this.mainBrowserActivity.f9383k) || !SecurityHelpers.m14854b(this.mainBrowserActivity.getApplicationContext()).equals(this.mainBrowserActivity.getPackageName())) && !this.mainBrowserActivity.f9385m) {
                    this.mainBrowserActivity.finish();
                    MainActivity.mainActivity.finish();
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                }
                if (!SecurityHelpers.isScreenON(this.mainBrowserActivity.f9384l)) {
                    this.mainBrowserActivity.finish();
                    MainActivity.mainActivity.finish();
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                    Intent intent = new Intent(this.mainBrowserActivity.getApplicationContext(), ManageSpaceActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    this.mainBrowserActivity.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class C2999b extends AsyncTask<Void, Void, ArrayList<C1577d>> {
        final /* synthetic */ MainBrowserActivity f9366a;

        private C2999b(MainBrowserActivity mainBrowserActivity) {
            this.f9366a = mainBrowserActivity;
        }

        private void m14416a() {
            try {
                String str = this.f9366a.getFilesDir() + "/bookmarks/";
                String str2 = str + "AOL.png";
                BitmapFactory.decodeResource(this.f9366a.getResources(), R.drawable.aol).compress(CompressFormat.PNG, 100, new FileOutputStream(new File(str2)));
                this.f9366a.f9380h.m14458a("AOL", "https://search.aol.com/", str2, -12763843);
                str2 = str + "Ask.com.png";
                BitmapFactory.decodeResource(this.f9366a.getResources(), R.drawable.ask).compress(CompressFormat.PNG, 100, new FileOutputStream(new File(str2)));
                this.f9366a.f9380h.m14458a("ASK.com", "http://www.ask.com/", str2, -3211264);
                str2 = str + "Yahoo.png";
                BitmapFactory.decodeResource(this.f9366a.getResources(), R.drawable.yahoo).compress(CompressFormat.PNG, 100, new FileOutputStream(new File(str2)));
                this.f9366a.f9380h.m14458a("Yahoo", "https://search.yahoo.com/", str2, -12910202);
                str2 = str + "DuckDuckGo.png";
                BitmapFactory.decodeResource(this.f9366a.getResources(), R.drawable.duckgo).compress(CompressFormat.PNG, 100, new FileOutputStream(new File(str2)));
                this.f9366a.f9380h.m14458a("DuckDuckGo", "https://duckduckgo.com/", str2, -2205645);
                str2 = str + "Facebook.png";
                BitmapFactory.decodeResource(this.f9366a.getResources(), R.drawable.facebook).compress(CompressFormat.PNG, 100, new FileOutputStream(new File(str2)));
                this.f9366a.f9380h.m14458a("Facebook", "http://m.facebook.com/", str2, -12887656);
                str2 = str + "Bing.png";
                BitmapFactory.decodeResource(this.f9366a.getResources(), R.drawable.bing).compress(CompressFormat.PNG, 100, new FileOutputStream(new File(str2)));
                this.f9366a.f9380h.m14458a("Bing", "http://www.bing.com/", str2, -15956860);
                str = str + "Google.png";
                BitmapFactory.decodeResource(this.f9366a.getResources(), R.drawable.google).compress(CompressFormat.PNG, 100, new FileOutputStream(new File(str)));
                this.f9366a.f9380h.m14458a("Google", "https://www.google.com/", str, -12417548);
            } catch (Exception e) {

            }
        }

        protected ArrayList<C1577d> m14417a(Void... voidArr) {
            File file = new File(this.f9366a.getFilesDir() + "/bookmarks");
            if (!file.exists()) {
                file.mkdir();
                try {
                    m14416a();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            ArrayList<C1577d> a = this.f9366a.f9380h.m14454a();
            if (!a.isEmpty()) {
                Collections.reverse(a);
            }
            C1577d c1577d = new C1577d("Add More", "", "", -14575885);
            c1577d.f4465d = true;
            a.add(c1577d);
            return a;
        }

        protected void m14418a(ArrayList<C1577d> arrayList) {
            this.f9366a.f9374b = arrayList;
            if (this.f9366a.f9374b.isEmpty()) {
                this.f9366a.bookmarkRecycler.setVisibility(View.GONE);
            } else {
                this.f9366a.bookmarkRecycler.setVisibility(View.VISIBLE);
                this.f9366a.f9377e.m7210a(this.f9366a.f9374b);
            }
            super.onPostExecute(arrayList);
        }

        protected ArrayList<C1577d> doInBackground(Void[] objArr) {
            return m14417a(objArr);
        }

        protected /* synthetic */ void onPostExecute(ArrayList<C1577d> obj) {
            m14418a((ArrayList) obj);
        }

    }

    private List<C1610n> m14420a(List<C1610n> list, String str) {
        CharSequence toLowerCase = str.toLowerCase();
        List<C1610n> arrayList = new ArrayList();
        for (C1610n c1610n : list) {
            if (c1610n.f4584b.toLowerCase().contains(toLowerCase)) {
                arrayList.add(c1610n);
            }
        }
        return arrayList;
    }

    private void m14421a() {
        final Dialog dialog = new Dialog(this);
        View inflate = getLayoutInflater().inflate(R.layout.exit_browser_dialog, null);
        dialog.setContentView(inflate);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_dialogText);
        ((TextView) inflate.findViewById(R.id.tvOk)).setTypeface(C1131f.f3322h);
        textView.setTypeface(C1131f.f3322h);
        ((TextView) inflate.findViewById(R.id.tvCancel)).setTypeface(C1131f.f3322h);
        ((TextView) inflate.findViewById(R.id.tvclrHistory)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvclrReminded)).setTypeface(C1131f.f3315a);
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.clrHistory);
        final CheckBox checkBox2 = (CheckBox) inflate.findViewById(R.id.clrReminded);
        checkBox.setChecked(this.f9391s.getBoolean("cbHistory", false));
        checkBox2.setChecked(this.f9391s.getBoolean("cbReminded", false));
        checkBox.setOnCheckedChangeListener(this);
        checkBox2.setOnCheckedChangeListener(this);
        inflate.findViewById(R.id.flReminded).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                checkBox2.setChecked(!checkBox2.isChecked());
            }
        });

        inflate.findViewById(R.id.flHistory).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                checkBox.setChecked(!checkBox.isChecked());
            }
        });
        inflate.findViewById(R.id.rlCancel).setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        inflate.findViewById(R.id.rlExit).setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                dialog.dismiss();
                if (checkBox.isChecked()) {
                    f9380h.m14461c();
                }
                if (checkBox2.isChecked()) {
                    CookieManager.getInstance().removeAllCookie();
                }
                finish();
            }
        });
        dialog.show();
    }

    private void m14422a(final C1577d c1577d) {
        final Dialog dialog = new Dialog(this);
        View inflate = getLayoutInflater().inflate(R.layout.exit_dialog, null);
        dialog.setContentView(inflate);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_dialogText);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tvOk);
        textView2.setText("DELETE");
        textView.setText("Delete bookmark?");
        textView2.setTypeface(C1131f.f3322h);
        ((TextView) inflate.findViewById(R.id.tvCancel)).setTypeface(C1131f.f3322h);
        textView.setTypeface(C1131f.f3322h);
        inflate.findViewById(R.id.rlCancel).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        inflate.findViewById(R.id.rlExit).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                File file = new File(f9380h.m14453a(c1577d.f4463b));
                if (file.exists()) {
                    file.delete();
                }
                int indexOf = f9374b.indexOf(c1577d);
                f9374b.remove(indexOf);
                f9377e.notifyItemRemoved(indexOf);
            }
        });
        dialog.show();
    }

    private void m14425a(String str) {
        Intent intent = new Intent(getApplicationContext(), SafeBrowseActivity.class);
        intent.putExtra("url", str);
        startActivityForResult(intent, 454);
    }

    private void m14427b() {
        final Dialog dialog = new Dialog(this);
        View inflate = getLayoutInflater().inflate(R.layout.custom_bookmark_dialog, null);
        dialog.setContentView(inflate);
        TextView tv_dialogText = (TextView) inflate.findViewById(R.id.tv_dialogText);
        ((TextView) inflate.findViewById(R.id.tvOk)).setTypeface(C1131f.f3322h);
        tv_dialogText.setTypeface(C1131f.f3322h);
        ((TextView) inflate.findViewById(R.id.tvCancel)).setTypeface(C1131f.f3322h);
        final EditText etTitle = (EditText) inflate.findViewById(R.id.etTitle);
        final EditText etUrl = (EditText) inflate.findViewById(R.id.etUrl);
        etTitle.setTypeface(C1131f.f3315a);
        etUrl.setTypeface(C1131f.f3315a);
        inflate.findViewById(R.id.rlCancel).setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        inflate.findViewById(R.id.rlOk).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                String str = "" + etTitle.getText();
                String str2 = "" + etUrl.getText();
                if (str.length() < 1) {
                    Toast.makeText(getApplicationContext(), "please enter Title", Toast.LENGTH_SHORT).show();
                } else if (str2.length() < 1) {
                    Toast.makeText(getApplicationContext(), "please enter URL", Toast.LENGTH_SHORT).show();
                } else {
                    f9380h.m14458a(str, str2, "", getResources().getColor(R.color.toolbar_color));
                    f9374b.add(f9374b.size() - 1, new C1577d(str, str2, "", getResources().getColor(R.color.toolbar_color)));
                    f9377e.notifyItemInserted(f9374b.size() - 2);
                }
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        int width2 = (int) (getResources().getDisplayMetrics().widthPixels * 0.90);
        dialog.getWindow().setLayout(width2, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    public void m14430a(final C2989a c2989a) {
        this.urlEditor.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
            private int f9335c;

            public void onGlobalLayout() {
                int height = urlEditor.getHeight();
                if (this.f9335c != 0) {
                    if (this.f9335c > height) {
                        c2989a.mo2109a(true);
                    } else if (this.f9335c < height) {
                        c2989a.mo2109a(false);
                    }
                }
                this.f9335c = height;
            }
        });
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 454) {
            if (!f9368v.isEmpty()) {
                Iterator it = this.f9374b.iterator();
                while (it.hasNext()) {
                    C1577d c1577d = (C1577d) it.next();
                    if (f9368v.contains(c1577d.f4463b)) {
                        this.f9372C.add(Integer.valueOf(this.f9374b.indexOf(c1577d)));
                    }
                }
                if (!this.f9372C.isEmpty()) {
                    for (int size = this.f9372C.size() - 2; size >= 0; size--) {
                        int intValue = ((Integer) this.f9372C.get(size)).intValue();
                        this.f9374b.remove(intValue);
                        this.f9377e.notifyItemRemoved(intValue);
                    }
                    this.f9372C.clear();
                }
                f9368v.clear();
            }
            if (!f9369w.isEmpty()) {
                Collections.reverse(f9369w);
                this.f9374b.addAll(0, f9369w);
                this.f9377e.notifyItemRangeInserted(0, f9369w.size());
                f9369w.clear();
                this.bookmarkRecycler.smoothScrollToPosition(0);
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (this.urlEditor.getVisibility() == View.VISIBLE) {
            this.recyclerVisited.setVisibility(View.GONE);
            this.urlEditor.setVisibility(View.GONE);
            return;
        }
        m14421a();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.clrHistory:
                this.f9392t.putBoolean("cbHistory", z);
                break;
            case R.id.clrReminded:
                this.f9392t.putBoolean("cbReminded", z);
                break;
        }
        this.f9392t.commit();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.llSearchBar:
                this.urlEditor.setVisibility(View.VISIBLE);
                this.f9393u.requestFocus();
                ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).toggleSoftInput(1, 1);
                this.f9375c = this.f9380h.m14459b();
                Collections.reverse(this.f9375c);
                this.f9376d = new ArrayList(this.f9375c);
                if (this.f9375c.isEmpty()) {
                    this.recyclerVisited.setVisibility(View.GONE);
                    return;
                }
                this.f9378f = new C1609m(getApplicationContext(), this.f9375c, new C1609m.C1602a() {

                    public void mo2102a(C1610n c1610n) {
                        Intent intent = new Intent(getApplicationContext(), SafeBrowseActivity.class);
                        intent.putExtra("url", c1610n.f4584b);
                        startActivityForResult(intent, 454);
                    }
                }, null, new C1609m.C1608d() {
                    public void mo2104a(C1610n c1610n) {
                        Intent intent = new Intent(getApplicationContext(), SafeBrowseActivity.class);
                        intent.putExtra("url", c1610n.f4584b);
                        startActivityForResult(intent, 454);
                    }
                });
                this.recyclerVisited.setAdapter(this.f9378f);
                return;
            case R.id.tvGo:
                String str = "" + this.f9393u.getText();
                if (str.trim().length() > 0) {
                    m14425a(str);
                    return;
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter keywords or URL", Toast.LENGTH_SHORT).show();
                    return;
                }
            case R.id.tvwww:
                this.f9393u.getText().insert(0, "www.");
                return;
            case R.id.tvslash:
                this.f9393u.getText().insert(this.f9393u.getText().length(), "/");
                return;
            case R.id.tvcom:
                this.f9393u.getText().insert(this.f9393u.getText().length(), ".com");
                return;
            default:
                return;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f9391s = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f9371B = C1131f.getCurrentStyle(this.f9391s);
        if (this.f9371B != R.style.AppTheme) {
            setTheme(this.f9371B);
        }
        getWindow().addFlags(128);
        setContentView((int) R.layout.activity_main_browser);
        f9367a = this;
        this.f9384l = (PowerManager) getSystemService(POWER_SERVICE);
        this.f9383k = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        this.f9392t = this.f9391s.edit();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.f9380h = new SaveBrowserDatabaseHelper(this);
        findViewById(R.id.llSearchBar).setOnClickListener(this);
        this.f9393u = (EditText) findViewById(R.id.etSearch);
        this.f9393u.addTextChangedListener(this);
        this.f9393u.setOnEditorActionListener(new OnEditorActionListener() {

            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 6) {
                    return false;
                }
                String str = "" + f9393u.getText();
                if (str.trim().length() > 0) {
                    m14425a(str);
                    return true;
                }
                Toast.makeText(getApplicationContext(), "Please enter keywords or URL", Toast.LENGTH_LONG).show();
                return true;
            }
        });
        this.f9393u.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                recyclerVisited.setVisibility(z ? View.VISIBLE : View.GONE);
            }
        });
        this.bookmarkRecycler = (RecyclerView) findViewById(R.id.recyclerView);
        this.bookmarkRecycler.setHasFixedSize(true);
        this.gridLayoutManagerBookmark = new GridLayoutManager(this, 4);
        this.bookmarkRecycler.setLayoutManager(this.gridLayoutManagerBookmark);
        this.f9377e = new C1583e(this, this.f9374b, new C1583e.C1578a() {
            public void mo2107a(C1577d c1577d) {
                if (c1577d.f4465d) {
                    m14427b();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), SafeBrowseActivity.class);
                intent.putExtra("url", c1577d.f4463b);
                startActivityForResult(intent, 454);
            }
        }, new C1583e.C1579b() {

            public void mo2108a(C1577d c1577d) {
                m14422a(c1577d);
            }
        });

        this.bookmarkRecycler.setAdapter(this.f9377e);
        new C2999b(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        try {
            if (this.f9391s.getBoolean("faceDown", false)) {
                this.f9388p = this.f9391s.getInt("selectedPos", 0);
                this.f9386n = (SensorManager) getSystemService(SENSOR_SERVICE);
                this.f9387o = (Sensor) this.f9386n.getSensorList(1).get(0);
            }
        } catch (Exception e) {
        }
        this.urlEditor = findViewById(R.id.urlEditor);
        m14430a(new C2989a() {

            public void mo2109a(boolean z) {
                f9382j.setVisibility(z ? View.VISIBLE : View.GONE);
            }
        });
        this.f9382j = findViewById(R.id.urlSuggestions);
        TextView textView = (TextView) findViewById(R.id.tvGo);
        textView.setTypeface(C1131f.f3315a);
        textView.setOnClickListener(this);
        this.recyclerVisited = (RecyclerView) findViewById(R.id.recyclerVisited);
        this.recyclerVisited.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        findViewById(R.id.tvwww).setOnClickListener(this);
        findViewById(R.id.tvslash).setOnClickListener(this);
        findViewById(R.id.tvcom).setOnClickListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_browser, menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected void onDestroy() {
        getWindow().clearFlags(128);
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
            case R.id.action_history:
                startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                break;
            case R.id.action_help:
                startActivity(new Intent(getApplicationContext(), QuickUseActivity.class));
                break;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onStart() {
        try {
            if (this.f9386n != null) {
                this.f9386n.registerListener(this.f9373D, this.f9387o, 3);
            }
        } catch (Exception e) {
        }
        super.onStart();
    }

    protected void onStop() {
        try {
            if (this.f9386n != null) {
                this.f9386n.unregisterListener(this.f9373D);
            }
        } catch (Exception e) {
        }
        this.recyclerVisited.setVisibility(View.GONE);
        this.f9382j.setVisibility(View.GONE);
        this.urlEditor.setVisibility(View.GONE);
        if (this.f9383k != null) {
            new Timer().schedule(new C29989(this), 1000);
        }
        super.onStop();
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!this.f9376d.isEmpty()) {
            this.f9378f.m7253a(m14420a(this.f9376d, "" + charSequence));
            this.recyclerVisited.smoothScrollToPosition(0);
        }
    }
}
