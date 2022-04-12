package calculator.vault.com.safebrowser;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebView.HitTestResult;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.lock.MainActivity;
import calculator.vault.com.lock.ManageSpaceActivity;
import calculator.vault.com.lock.ViewAlbumActivity;
import calculator.vault.com.p068a.C1577d;
import calculator.vault.com.p073g.PhotoFolderViewFragment;
import calculator.vault.com.p073g.C1735d;

import static calculator.vault.com.p073g.C1735d.f4874a;

public class SafeBrowseActivity extends AppCompatActivity implements OnClickListener {
    public static HashMap<Integer, C3023b> f9447d = new HashMap();
    public static SafeBrowseActivity f9448z;
    C3016a webViewClient;
    TextWatcher f9450B = new C30064(this);
    private VideoEnabledWebView videoEnabledWebView;
    private SafeBrowserChromeClient safeBrowserChromeClient;
    private boolean f9453E;
    private String f9454F = "main";
    private View nonVideoLayout;
    private View bottomBar;
    private boolean f9457I;
    private SensorEventListener f9458J = new C30053(this);
    ProgressBar f9459a;
    EditText editText;
    View llTop;
    PowerManager f9462e;
    TelephonyManager f9463f;
    public int f9464g;
    SensorManager f9465h;
    Sensor f9466i;
    boolean f9467j;
    String f9468k;
    SharedPreferences f9469l;
    View btnHome;
    View btnStop;
    View btnGo;
    View btnRefresh;
    View appbarLayout;
    ImageButton btnBookmarks;
    ImageButton btnBack;
    ImageButton btnNext;
    SaveBrowserDatabaseHelper f9478u;
    String f9479v = null;
    Bitmap f9480w;
    ImageView f9481x;
    String f9482y = null;

    class C30042 extends TimerTask {
        final /* synthetic */ SafeBrowseActivity f9419a;

        C30042(SafeBrowseActivity safeBrowseActivity) {
            this.f9419a = safeBrowseActivity;
        }

        public void run() {
            try {
                if (SecurityHelpers.isCallRinging(this.f9419a.f9463f) || !SecurityHelpers.m14854b(this.f9419a.getApplicationContext()).equals(this.f9419a.getPackageName())) {
                    if (MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                    }
                    if (MainBrowserActivity.f9367a != null) {
                        MainBrowserActivity.f9367a.finish();
                    }
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                    this.f9419a.finish();
                }
                if (!SecurityHelpers.isScreenON(this.f9419a.f9462e)) {
                    if (MainActivity.mainActivity != null) {
                        MainActivity.mainActivity.finish();
                    }
                    if (MainBrowserActivity.f9367a != null) {
                        MainBrowserActivity.f9367a.finish();
                    }
                    if (ViewAlbumActivity.f3158g != null) {
                        ViewAlbumActivity.f3158g.finish();
                    }
                    this.f9419a.finish();
                    Intent intent = new Intent(this.f9419a.getApplicationContext(), ManageSpaceActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    this.f9419a.startActivity(intent);
                }
            } catch (Exception e) {
            }
        }
    }

    class ColorInterFace {
        @JavascriptInterface
        public void getColor(String color) {
            try {
                int col = Color.parseColor(color);
                llTop.setBackgroundColor(col);
                bottomBar.setBackgroundColor(col);
                editText.setTextColor(getContrastColor(col));
            } catch (Exception e) {

            }
        }

        public int getContrastColor(int color) {
            @SuppressLint("Range") double y = (299 * Color.red(color) + 587 * Color.green(color) + 114 * Color.blue(color)) / 1000;
            return y >= 128 ? Color.BLACK : Color.WHITE;
        }
    }

    class C30053 implements SensorEventListener {
        final /* synthetic */ SafeBrowseActivity f9420a;

        C30053(SafeBrowseActivity safeBrowseActivity) {
            this.f9420a = safeBrowseActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f9420a.f9467j) {
                    this.f9420a.f9467j = true;
                    if (this.f9420a.f9464g == 1) {
                        C1131f.m5806a(this.f9420a.getApplicationContext(), this.f9420a.getPackageManager(), this.f9420a.f9469l.getString("Package_Name", null));
                    }
                    if (this.f9420a.f9464g == 2) {
                        this.f9420a.f9468k = this.f9420a.f9469l.getString("URL_Name", null);
                        this.f9420a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f9420a.f9468k)));
                    }
                    if (this.f9420a.f9464g == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.f9420a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    class C30064 implements TextWatcher {
        final /* synthetic */ SafeBrowseActivity f9421a;

        C30064(SafeBrowseActivity safeBrowseActivity) {
            this.f9421a = safeBrowseActivity;
        }

        public void afterTextChanged(Editable editable) {
            Log.d(this.f9421a.f9454F, "afterTextChanged: " + editable);
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            Log.d(this.f9421a.f9454F, "beforeTextChanged: s: " + charSequence);
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (this.f9421a.f9457I) {
                this.f9421a.f9457I = false;
                if (charSequence.length() > 1) {
                    this.f9421a.editText.setText("");
                    return;
                }
                try {
                    if (charSequence.length() > 0) {
                        this.f9421a.editText.setText("" + charSequence);
                        this.f9421a.editText.setSelection(1);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    class C30075 implements OnEditorActionListener {
        final /* synthetic */ SafeBrowseActivity f9422a;

        C30075(SafeBrowseActivity safeBrowseActivity) {
            this.f9422a = safeBrowseActivity;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 6) {
                return false;
            }
            this.f9422a.m14445b("" + this.f9422a.editText.getText());
            return true;
        }
    }

    class C30086 implements OnFocusChangeListener {
        final /* synthetic */ SafeBrowseActivity f9423a;

        C30086(SafeBrowseActivity safeBrowseActivity) {
            this.f9423a = safeBrowseActivity;
        }

        public void onFocusChange(View view, boolean z) {
            if (z) {
                this.f9423a.btnGo.setVisibility(View.VISIBLE);
                this.f9423a.btnRefresh.setVisibility(View.GONE);
                this.f9423a.btnStop.setVisibility(View.GONE);
                if (this.f9423a.videoEnabledWebView.getUrl() != null) {
                    this.f9423a.editText.setText(this.f9423a.videoEnabledWebView.getUrl());
                    this.f9423a.editText.selectAll();
                    this.f9423a.f9457I = true;
                    this.f9423a.f9481x.setImageResource(R.drawable.browser_globe);
                    this.f9423a.editText.addTextChangedListener(this.f9423a.f9450B);
                    return;
                }
                return;
            }
            this.f9423a.btnGo.setVisibility(View.GONE);
            this.f9423a.btnRefresh.setVisibility(View.VISIBLE);
            this.f9423a.btnStop.setVisibility(View.GONE);
            this.f9423a.editText.removeTextChangedListener(this.f9423a.f9450B);
            if (this.f9423a.f9479v != null) {
                this.f9423a.editText.setText(this.f9423a.f9479v);
            }
        }
    }

    class C30128 implements SafeBrowserChromeClient.C3011a {
        final /* synthetic */ SafeBrowseActivity f9435a;

        C30128(SafeBrowseActivity safeBrowseActivity) {
            this.f9435a = safeBrowseActivity;
        }

        public void mo2110a(boolean z) {
            if (z) {
                LayoutParams attributes = this.f9435a.getWindow().getAttributes();
                attributes.flags |= 1024;
                attributes.flags |= 128;
                this.f9435a.getWindow().setAttributes(attributes);
                if (VERSION.SDK_INT >= 14) {
                    this.f9435a.getWindow().getDecorView().setSystemUiVisibility(1);
                }
                this.f9435a.llTop.setVisibility(View.GONE);
                this.f9435a.f9459a.setVisibility(View.GONE);
                return;
            }
            LayoutParams attributes = this.f9435a.getWindow().getAttributes();
            attributes.flags &= -1025;
            attributes.flags &= -129;
            this.f9435a.getWindow().setAttributes(attributes);
            if (VERSION.SDK_INT >= 14) {
                this.f9435a.getWindow().getDecorView().setSystemUiVisibility(View.GONE);
            }
            this.f9435a.llTop.setVisibility(View.VISIBLE);
        }
    }

    private class C3016a extends WebViewClient {
        final /* synthetic */ SafeBrowseActivity f9440a;
        private boolean f9441b;

        class C30151 implements Runnable {
            final /* synthetic */ C3016a f9439a;

            class C30141 implements AnimationListener {
                final /* synthetic */ C30151 f9438a;

                C30141(C30151 c30151) {
                    this.f9438a = c30151;
                }

                public void onAnimationEnd(Animation animation) {
                    this.f9438a.f9439a.f9440a.f9459a.setVisibility(View.GONE);
                    this.f9438a.f9439a.f9440a.f9459a.setProgress(100);
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            }

            C30151(C3016a c3016a) {
                this.f9439a = c3016a;
            }

            public void run() {
                Animation loadAnimation = AnimationUtils.loadAnimation(this.f9439a.f9440a.getApplicationContext(), R.anim.d_fade_out);
                loadAnimation.setAnimationListener(new C30141(this));
                this.f9439a.f9440a.f9459a.startAnimation(loadAnimation);
            }
        }

        private C3016a(SafeBrowseActivity safeBrowseActivity) {
            this.f9440a = safeBrowseActivity;
            this.f9441b = false;
        }

        public void onLoadResource(WebView webView, String str) {
            boolean z = true;
            this.f9440a.btnNext.setEnabled(webView.canGoForward());
            ImageButton imageButton = this.f9440a.btnBack;
            if (!webView.canGoBack()) {
                z = false;
            }
            imageButton.setEnabled(z);
            this.f9440a.btnBack.setImageResource(webView.canGoBack() ? R.drawable.back_browser : R.drawable.back_browser_disabled);
            this.f9440a.btnNext.setImageResource(webView.canGoForward() ? R.drawable.next_browser : R.drawable.next_browser_disabled);
            super.onLoadResource(webView, str);
        }

        public void onPageFinished(WebView webView, String str) {
            this.f9440a.btnGo.setVisibility(View.GONE);
            this.f9440a.btnRefresh.setVisibility(View.VISIBLE);
            this.f9440a.btnStop.setVisibility(View.GONE);
            new Handler().postDelayed(new C30151(this), 500);
            if (str.contains("www.instagram.com")) {
                webView.loadUrl("javascript:document.getElementsByClassName('_ovg3g')[0].remove();");
                webView.loadUrl("javascript:document.getElementsByClassName('_9sso8')[0].remove();");
                webView.loadUrl("javascript:document.getElementsByClassName('_jj6py')[0].remove();");
            }
            webView.loadUrl("javascript:a=window.document.getElementsByTagName('meta');m=null;for(var b=0;b<a.length;b++){ c=a[b].getAttribute(\"content\");if(!(c===null)&&c.match(/^#[0-9a-f]{3,6}$/i)){android.getColor(c);m=c;break;}}if(m===null){android.getColor('#ffffff');}");

            if (this.f9441b) {
                this.f9441b = false;
                this.f9440a.videoEnabledWebView.clearHistory();
            }
            super.onPageFinished(webView, str);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            this.f9440a.f9482y = str;
            this.f9440a.btnGo.setVisibility(View.GONE);
            this.f9440a.btnRefresh.setVisibility(View.GONE);
            this.f9440a.btnStop.setVisibility(View.VISIBLE);
            this.f9440a.f9481x.setImageBitmap(BitmapFactory.decodeResource(this.f9440a.getResources(), R.drawable.browser_globe));
            Log.d(this.f9440a.f9454F, "onPageStarted: url: " + str);
            if (bitmap != null) {
                this.f9440a.f9481x.setImageBitmap(bitmap);
                Log.d(this.f9440a.f9454F, "onPageStarted: favicon" + bitmap.getWidth() + " " + bitmap.getHeight());
            }
            this.f9440a.f9453E = this.f9440a.f9478u.m14462c(str);
            Log.d(this.f9440a.f9454F, "onPageStarted: is bookmarked: " + this.f9440a.f9453E);
            if (this.f9440a.f9453E) {
                this.f9440a.btnBookmarks.setImageResource(R.drawable.bookmark_press);
            } else {
                this.f9440a.btnBookmarks.setImageResource(R.drawable.bookmark_unpress);
            }
            super.onPageStarted(webView, str, bitmap);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            File file = new File(str);
            if (SafeBrowseActivity.m14446b(this.f9440a.getApplicationContext(), file.getName())) {
                this.f9440a.m14441a(str, 693);
            } else if (SafeBrowseActivity.m14442a(this.f9440a.getApplicationContext(), file.getName())) {
                this.f9440a.m14441a(str, 891);
            } else if (SafeBrowseActivity.m14444a(str)) {
                this.f9440a.m14441a(str, 693);
            } else {
                webView.loadUrl(str);
            }
            return true;
        }
    }

    private class C3018b extends BaseAdapter {
        ArrayList<String> f9444a;
        LayoutInflater f9445b;
        final /* synthetic */ SafeBrowseActivity f9446c;

        class C3017a {
            TextView f9442a;
            final /* synthetic */ C3018b f9443b;

            C3017a(C3018b c3018b) {
                this.f9443b = c3018b;
            }
        }

        public C3018b(SafeBrowseActivity safeBrowseActivity, ArrayList<String> arrayList) {
            this.f9446c = safeBrowseActivity;
            this.f9444a = arrayList;
            this.f9445b = LayoutInflater.from(safeBrowseActivity.getApplicationContext());
        }

        public int getCount() {
            return this.f9444a.size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            C3017a c3017a;
            if (view == null) {
                C3017a c3017a2 = new C3017a(this);
                view = this.f9445b.inflate(R.layout.list_item_layout, null);
                c3017a2.f9442a = (TextView) view.findViewById(R.id.textView1);
                view.setTag(c3017a2);
                c3017a = c3017a2;
            } else {
                c3017a = (C3017a) view.getTag();
            }
            c3017a.f9442a.setText((CharSequence) this.f9444a.get(i));
            return view;
        }
    }

    private void m14436a() {
        final Dialog dialog = new Dialog(this);
        View inflate = getLayoutInflater().inflate(R.layout.exit_dialog, null);
        dialog.setContentView(inflate);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_dialogText);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tvOk);
        textView2.setText("DELETE");
        textView.setText("Delete the bookmark?");
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
                File file = new File(f9478u.m14453a(videoEnabledWebView.getUrl()));
                if (file.exists()) {
                    boolean delete = file.delete();
                    Log.d(f9454F, "file deleted?: " + delete);
                    if (delete) {
                        btnBookmarks.setImageResource(R.drawable.bookmark_unpress);
                        f9453E = false;
                        if (MainBrowserActivity.f9368v != null) {
                            MainBrowserActivity.f9368v.add(videoEnabledWebView.getUrl());
                        }
                    }
                }
            }
        });
        dialog.show();
    }

    private void m14437a(ProgressBar progressBar, int i) {
        ObjectAnimator ofInt = ObjectAnimator.ofInt(progressBar, "progress", new int[]{progressBar.getProgress(), i});
        ofInt.setDuration(500);
        ofInt.setInterpolator(new AccelerateDecelerateInterpolator());
        ofInt.start();
    }

    private void m14441a(final String str, final int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(i == 891 ? "View image" : "Play video");
        arrayList.add("Save " + (i == 891 ? "image" : "video"));
        final Dialog dialog = new Dialog(this);
        View inflate = getLayoutInflater().inflate(R.layout.dialog_restore_chooser, null);
        TextView textView = (TextView) inflate.findViewById(R.id.textView1);
        textView.setText("Select Action");
        textView.setTypeface(C1131f.f3322h);
        ListView listView = (ListView) inflate.findViewById(R.id.lvDirs);
        listView.setAdapter(new C3018b(this, arrayList));
        listView.setOnItemClickListener(new OnItemClickListener() {

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                dialog.dismiss();
                switch (i) {
                    case 0:
                        videoEnabledWebView.loadUrl(str);
                        return;
                    case 1:
                        int currentTimeMillis = (int) System.currentTimeMillis();
                        String str = i == 891 ? getFilesDir().getAbsolutePath() + "/lockerVault/Images1769/Downloads" : getFilesDir().getAbsolutePath() + "/lockerVault/Videos1769/Downloads";
                        File file = new File(str);
                        if (!file.exists()) {
                            file.mkdir();
                            if (i == 891) {
                                try {
                                    if (PhotoFolderViewFragment.f4765e != null) {
                                        PhotoFolderViewFragment.f4765e.m7383b(str);
                                    }
                                } catch (Exception e) {
                                }
                            } else {
                                try {
                                    if (C1735d.f4874a != null) {
                                        C1735d.f4874a.m7451b(str);
                                    }
                                } catch (Exception e2) {
                                }
                            }
                        }
                        C3023b c3023b = new C3023b(getApplicationContext(), str, i, currentTimeMillis, str);
                        c3023b.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
                        SafeBrowseActivity.f9447d.put(Integer.valueOf(currentTimeMillis), c3023b);
                        return;
                    default:
                        return;
                }
            }
        });
        dialog.setContentView(inflate);
        dialog.show();
    }

    public static boolean m14442a(Context context, String str) {
        return new ArrayList(Arrays.asList(context.getResources().getStringArray(R.array.image_types))).contains(str.substring(str.lastIndexOf(".") + 1, str.length()).toLowerCase());
    }

    public static boolean m14444a(String str) {
        return str.contains(".mp4");
    }

    private void m14445b(String str) {
        String str2 = (str.startsWith("http://") || str.startsWith("https://") || str.startsWith("ftp://")) ? str : "http://" + str;
        if (C1131f.m5807a(str2)) {
            this.videoEnabledWebView.loadUrl(str2);
        } else {
            this.videoEnabledWebView.loadUrl("https://www.google.com/search?q=" + str);
        }
        this.editText.clearFocus();
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.editText.getWindowToken(), 0);
        this.btnGo.setVisibility(View.GONE);
        this.btnRefresh.setVisibility(View.GONE);
        this.btnStop.setVisibility(View.VISIBLE);
    }

    public static boolean m14446b(Context context, String str) {
        return new ArrayList(Arrays.asList(context.getResources().getStringArray(R.array.video_types))).contains(str.substring(str.lastIndexOf(".") + 1, str.length()).toLowerCase());
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 474 && i2 == -1) {
            this.videoEnabledWebView.loadUrl(intent.getStringExtra("url"));
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        if (this.editText.hasFocus()) {
            this.editText.clearFocus();
            if (this.f9480w != null) {
                this.f9481x.setImageBitmap(this.f9480w);
            }
        } else if (this.videoEnabledWebView.canGoBack()) {
            this.videoEnabledWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStop:
                this.videoEnabledWebView.stopLoading();
                this.btnGo.setVisibility(View.GONE);
                this.btnRefresh.setVisibility(View.VISIBLE);
                this.btnStop.setVisibility(View.GONE);
                return;
            case R.id.btnRefresh:
                this.videoEnabledWebView.reload();
                this.btnGo.setVisibility(View.GONE);
                this.btnRefresh.setVisibility(View.GONE);
                this.btnStop.setVisibility(View.VISIBLE);
                return;
            case R.id.btnGo:
                String str = "" + this.editText.getText();
                if (str.length() < 1) {
                    return;
                }
                if (str.equals(this.f9482y)) {
                    m14445b("" + this.f9482y);
                    return;
                } else {
                    m14445b(str);
                    return;
                }
            case R.id.btnBookmarks:
                if (this.f9479v == null) {
                    return;
                }
                if (this.f9453E) {
                    m14436a();
                    return;
                }
                String str2 = this.f9479v;
                final String url = this.videoEnabledWebView.getUrl();
                if (this.f9480w == null) {
                    this.f9480w = BitmapFactory.decodeResource(getResources(), R.drawable.browser_globe);
                }
                File file = new File(getFilesDir() + "/bookmarks");
                if (!file.exists()) {
                    file.mkdir();
                }
                File file2 = new File(file, "" + (str2.contains(" ") ? str2.substring(0, str2.indexOf(" ")) : str2) + ".png");
                try {
                    this.f9480w.compress(CompressFormat.PNG, 100, new FileOutputStream(file2));
                } catch (FileNotFoundException e) {
                }

                C1577d c1577d = new C1577d(str2, url, file2.getAbsolutePath(), getResources().getColor(R.color.browser_prim));
                if (this.f9478u.m14458a(str2, url, file2.getAbsolutePath(), getResources().getColor(R.color.browser_prim))) {
                    if (MainBrowserActivity.f9369w != null) {
                        MainBrowserActivity.f9369w.add(c1577d);
                    }
                    this.btnBookmarks.setImageResource(R.drawable.bookmark_press);
                    this.f9453E = true;
                    Palette.from(this.f9480w).generate(new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            try {
                                f9478u.m14456a(url, palette.getDominantColor(getResources().getColor(R.color.browser_prim)));
                            } catch (Exception e) {
                                Log.d(f9454F, "onGenerated: exception adding color to db");
                            }
                        }
                    });
                    return;
                }
                return;
            case R.id.btnBack:
                if (this.videoEnabledWebView.canGoBack()) {
                    this.videoEnabledWebView.goBack();
                    return;
                } else {
                    super.onBackPressed();
                    return;
                }
            case R.id.btnNext:
                if (this.videoEnabledWebView.canGoForward()) {
                    this.videoEnabledWebView.goForward();
                    return;
                }
                return;
            case R.id.btnHome:
                finish();
                return;
            case R.id.btnHistory:
                Intent intent = new Intent(getApplicationContext(), HistoryActivity.class);
                intent.putExtra("fromBrowser", true);
                startActivityForResult(intent, 474);
                return;
            default:
                return;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f9469l = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        getWindow().addFlags(128);
        setContentView((int) R.layout.activity_safebrowser);
        this.f9478u = new SaveBrowserDatabaseHelper(this);
        f9448z = this;
        this.f9462e = (PowerManager) getSystemService(POWER_SERVICE);
        this.f9463f = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        this.nonVideoLayout = findViewById(R.id.nonVideoLayout);
        this.f9481x = (ImageView) findViewById(R.id.ivWebIcon);
        findViewById(R.id.btnHistory).setOnClickListener(this);
        this.bottomBar = findViewById(R.id.bottomBar);

        this.appbarLayout = findViewById(R.id.appbar);
        this.videoEnabledWebView = (VideoEnabledWebView) findViewById(R.id.webView);
        this.videoEnabledWebView.getSettings().setDisplayZoomControls(false);
        this.videoEnabledWebView.getSettings().setBuiltInZoomControls(true);
        this.videoEnabledWebView.getSettings().setJavaScriptEnabled(true);
        this.videoEnabledWebView.getSettings().setDomStorageEnabled(true);
        this.videoEnabledWebView.getSettings().setPluginState(PluginState.ON);
        this.videoEnabledWebView.getSettings().setUseWideViewPort(true);
        this.videoEnabledWebView.getSettings().setAllowFileAccess(true);
        this.videoEnabledWebView.getSettings().setDomStorageEnabled(true);
        this.videoEnabledWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        WebSettings settings = this.videoEnabledWebView.getSettings();
        settings.setLoadWithOverviewMode(true);
        settings.setLightTouchEnabled(true);
        registerForContextMenu(this.videoEnabledWebView);
        videoEnabledWebView.addJavascriptInterface(new ColorInterFace(), "android");
        this.llTop = findViewById(R.id.llTop);
        this.editText = (EditText) findViewById(R.id.editText1);
        this.editText.setOnEditorActionListener(new C30075(this));
        this.editText.setOnFocusChangeListener(new C30086(this));
        this.f9459a = (ProgressBar) findViewById(R.id.progressBar1);
        this.safeBrowserChromeClient = new SafeBrowserChromeClient(this.nonVideoLayout, this.appbarLayout, this.bottomBar, (ViewGroup) findViewById(R.id.videoLayout), getLayoutInflater().inflate(R.layout.video_loader_progress, null), this.videoEnabledWebView) {

            public void onProgressChanged(WebView webView, int i) {
                int i2 = i * 100;
                if (i2 >= 10000) {
                    m14437a(f9459a, i2);
                    return;
                }
                f9459a.setVisibility(View.VISIBLE);
                m14437a(f9459a, i2);
            }

            public void onReceivedIcon(WebView webView, Bitmap bitmap) {
                f9480w = bitmap;
                if (bitmap != null) {
                    f9481x.setImageBitmap(bitmap);
                }
                super.onReceivedIcon(webView, bitmap);
            }

            public void onReceivedTitle(WebView webView, String str) {
                f9479v = str;
                if (!f9457I) {
                    editText.setText(f9479v);
                }
                f9478u.m14457a(f9479v, webView.getUrl());
                if (str != null && str.length() > 20) {
                    str = str.substring(0, 20) + "...";
                }
                super.onReceivedTitle(webView, str);
            }

            public void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
                super.onReceivedTouchIconUrl(webView, str, z);
            }
        };
        this.safeBrowserChromeClient.m14431a(new C30128(this));
        this.videoEnabledWebView.setWebChromeClient(this.safeBrowserChromeClient);
        this.webViewClient = new C3016a(this);
        this.videoEnabledWebView.setWebViewClient(this.webViewClient);
        this.btnHome = findViewById(R.id.btnHome);
        this.btnHome.setOnClickListener(this);
        this.btnStop = findViewById(R.id.btnStop);
        this.btnStop.setOnClickListener(this);
        this.btnRefresh = findViewById(R.id.btnRefresh);
        this.btnRefresh.setOnClickListener(this);
        this.btnGo = findViewById(R.id.btnGo);
        this.btnGo.setOnClickListener(this);
        this.btnBack = (ImageButton) findViewById(R.id.btnBack);
        this.btnBookmarks = (ImageButton) findViewById(R.id.btnBookmarks);
        this.btnNext = (ImageButton) findViewById(R.id.btnNext);
        this.btnBack.setOnClickListener(this);
        this.btnNext.setOnClickListener(this);
        this.btnBookmarks.setOnClickListener(this);
        try {
            if (this.f9469l.getBoolean("faceDown", false)) {
                this.f9464g = this.f9469l.getInt("selectedPos", 0);
                this.f9465h = (SensorManager) getSystemService(SENSOR_SERVICE);
                this.f9466i = (Sensor) this.f9465h.getSensorList(1).get(0);
                this.f9465h.registerListener(this.f9458J, this.f9466i, 3);
            }
        } catch (Exception e) {
        }
        m14445b(getIntent().getStringExtra("url"));
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        final HitTestResult hitTestResult = this.videoEnabledWebView.getHitTestResult();
        OnMenuItemClickListener c30031 = new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (menuItem.getItemId() == 891 || menuItem.getItemId() == 693) {
                    int currentTimeMillis = (int) System.currentTimeMillis();
                    String str = menuItem.getItemId() == 891 ? getFilesDir().getAbsolutePath() + "/lockerVault/Images1769/Downloads" : getFilesDir().getAbsolutePath() + "/lockerVault/Videos1769/Downloads";
                    File file = new File(str);
                    if (!file.exists()) {
                        file.mkdir();
                        if (menuItem.getItemId() == 891) {
                            PhotoFolderViewFragment.f4765e.m7383b(str);
                        } else {
                            f4874a.m7451b(str);
                        }
                    }
                    C3023b c3023b = new C3023b(getApplicationContext(), hitTestResult.getExtra(), menuItem.getItemId(), currentTimeMillis, str);
                    c3023b.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
                    SafeBrowseActivity.f9447d.put(Integer.valueOf(currentTimeMillis), c3023b);
                } else if (menuItem.getItemId() == 874) {
                    videoEnabledWebView.loadUrl(hitTestResult.getExtra());
                }
                return true;
            }
        };
        String name;
        String substring;
        if (hitTestResult.getType() == 5 || hitTestResult.getType() == 8) {
            name = new File(hitTestResult.getExtra()).getName();
            substring = name.substring(name.lastIndexOf(".") + 1, name.length());
            if (!m14442a(getApplicationContext(), (String) name)) {
                name = System.currentTimeMillis() + ".jpg";
            }
            if (substring != null && substring.length() > 1) {
                contextMenu.setHeaderTitle(name);
                contextMenu.add(0, 874, 0, "Open Link").setOnMenuItemClickListener(c30031);
                contextMenu.add(0, 891, 1, "Save image").setOnMenuItemClickListener(c30031);
            }
        } else if (hitTestResult.getType() == 1 || hitTestResult.getType() == 7) {
            String name2 = new File(hitTestResult.getExtra()).getName();
            substring = name2.substring(name2.lastIndexOf(".") + 1, name2.length());
            if (substring != null && substring.length() > 1) {
                if (m14442a(getApplicationContext(), name2)) {
                    name = name2.replace("%20", " ");
                    if (name.length() > 30) {
                        name = name.substring(0, 30) + ".." + substring;
                    }
                    contextMenu.setHeaderTitle(name);
                    contextMenu.add(0, 874, 0, "Open Link").setOnMenuItemClickListener(c30031);
                    contextMenu.add(0, 891, 1, "Save image").setOnMenuItemClickListener(c30031);
                } else if (m14446b(getApplicationContext(), name2)) {
                    name = name2.replace("%20", " ");
                    if (name.length() > 30) {
                        name = name.toString().substring(0, 30) + ".." + substring;
                    }
                    contextMenu.setHeaderTitle(name);
                    contextMenu.add(0, 874, 0, "Open Link").setOnMenuItemClickListener(c30031);
                    contextMenu.add(0, 693, 1, "Save video").setOnMenuItemClickListener(c30031);
                }
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_browser, menu);
        return super.onCreateOptionsMenu(menu);
    }

    protected void onDestroy() {
        getWindow().clearFlags(128);
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        menuItem.getItemId();
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
        if (this.videoEnabledWebView != null) {
            this.videoEnabledWebView.onPause();
        }
        super.onPause();
    }

    protected void onResume() {
        if (this.videoEnabledWebView != null) {
            this.videoEnabledWebView.onResume();
        }
        try {
            if (this.f9465h != null) {
                this.f9465h.registerListener(this.f9458J, this.f9466i, 3);
            }
        } catch (Exception e) {
        }
        super.onResume();
    }

    protected void onStop() {
        try {
            if (this.f9465h != null) {
                this.f9465h.unregisterListener(this.f9458J);
            }
        } catch (Exception e) {
        }
        if (this.f9463f != null) {
            new Timer().schedule(new C30042(this), 1000);
        }
        super.onStop();
    }
}
