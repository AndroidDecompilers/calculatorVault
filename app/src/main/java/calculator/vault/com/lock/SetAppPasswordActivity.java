package calculator.vault.com.lock;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.Builder;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import calculator.vault.com.R;

public class SetAppPasswordActivity extends AppCompatActivity {
    String f2949a = "";
    SharedPreferences f2950b;
    Editor f2951c;
    String f2952d;
    String f2953e;
    LinearLayout f2954f;
    SoundPool f2955g;
    boolean f2956h;
    boolean f2957i;
    int f2958j;
    float f2959k;
    boolean f2960l;
    boolean f2961m;
    boolean f2962n;
    boolean f2963o;
    Vibrator f2964p;
    TextView f2965q;
    int f2966r;
    Animation f2967s;
    OnClickListener f2968t = new C10304(this);
    OnClickListener f2969u = new C10315(this);
    OnClickListener f2970v = new C10326(this);
    boolean f2971w;
    String f2972x;
    OnClickListener f2973y = new C10337(this);
    private int f2974z;

    class C10271 implements OnLoadCompleteListener {
        final /* synthetic */ SetAppPasswordActivity f2942a;

        C10271(SetAppPasswordActivity setAppPasswordActivity) {
            this.f2942a = setAppPasswordActivity;
        }

        public void onLoadComplete(SoundPool soundPool, int i, int i2) {
            this.f2942a.f2956h = true;
        }
    }

    class C10282 implements OnLongClickListener {
        final /* synthetic */ SetAppPasswordActivity f2943a;

        C10282(SetAppPasswordActivity setAppPasswordActivity) {
            this.f2943a = setAppPasswordActivity;
        }

        public boolean onLongClick(View view) {
            this.f2943a.f2949a = "";
            this.f2943a.f2954f.removeAllViews();
            return false;
        }
    }

    class C10293 implements AnimationListener {
        final /* synthetic */ SetAppPasswordActivity f2944a;

        C10293(SetAppPasswordActivity setAppPasswordActivity) {
            this.f2944a = setAppPasswordActivity;
        }

        public void onAnimationEnd(Animation animation) {
            this.f2944a.f2954f.removeAllViews();
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    class C10304 implements OnClickListener {
        final /* synthetic */ SetAppPasswordActivity f2945a;

        C10304(SetAppPasswordActivity setAppPasswordActivity) {
            this.f2945a = setAppPasswordActivity;
        }

        public void onClick(View view) {
            StringBuilder stringBuilder;
            SetAppPasswordActivity setAppPasswordActivity;
            switch (view.getId()) {
                case R.id.rl1:
                    stringBuilder = new StringBuilder();
                    setAppPasswordActivity = this.f2945a;
                    setAppPasswordActivity.f2949a = stringBuilder.append(setAppPasswordActivity.f2949a).append("1").toString();
                    break;
                case R.id.rl2:
                    stringBuilder = new StringBuilder();
                    setAppPasswordActivity = this.f2945a;
                    setAppPasswordActivity.f2949a = stringBuilder.append(setAppPasswordActivity.f2949a).append("2").toString();
                    break;
                case R.id.rl3:
                    stringBuilder = new StringBuilder();
                    setAppPasswordActivity = this.f2945a;
                    setAppPasswordActivity.f2949a = stringBuilder.append(setAppPasswordActivity.f2949a).append("3").toString();
                    break;
                case R.id.rl4:
                    stringBuilder = new StringBuilder();
                    setAppPasswordActivity = this.f2945a;
                    setAppPasswordActivity.f2949a = stringBuilder.append(setAppPasswordActivity.f2949a).append("4").toString();
                    break;
                case R.id.rl5:
                    stringBuilder = new StringBuilder();
                    setAppPasswordActivity = this.f2945a;
                    setAppPasswordActivity.f2949a = stringBuilder.append(setAppPasswordActivity.f2949a).append("5").toString();
                    break;
                case R.id.rl6:
                    stringBuilder = new StringBuilder();
                    setAppPasswordActivity = this.f2945a;
                    setAppPasswordActivity.f2949a = stringBuilder.append(setAppPasswordActivity.f2949a).append("6").toString();
                    break;
                case R.id.rl7:
                    stringBuilder = new StringBuilder();
                    setAppPasswordActivity = this.f2945a;
                    setAppPasswordActivity.f2949a = stringBuilder.append(setAppPasswordActivity.f2949a).append("7").toString();
                    break;
                case R.id.rl8:
                    stringBuilder = new StringBuilder();
                    setAppPasswordActivity = this.f2945a;
                    setAppPasswordActivity.f2949a = stringBuilder.append(setAppPasswordActivity.f2949a).append("8").toString();
                    break;
                case R.id.rl9:
                    stringBuilder = new StringBuilder();
                    setAppPasswordActivity = this.f2945a;
                    setAppPasswordActivity.f2949a = stringBuilder.append(setAppPasswordActivity.f2949a).append("9").toString();
                    break;
                case R.id.rl0:
                    stringBuilder = new StringBuilder();
                    setAppPasswordActivity = this.f2945a;
                    setAppPasswordActivity.f2949a = stringBuilder.append(setAppPasswordActivity.f2949a).append("0").toString();
                    break;
            }
            if (this.f2945a.f2956h && this.f2945a.f2960l) {
                this.f2945a.f2966r = this.f2945a.f2955g.play(this.f2945a.f2958j, this.f2945a.f2959k, this.f2945a.f2959k, 1, 0, 1.0f);
            }
            this.f2945a.m5661a();
        }
    }

    class C10315 implements OnClickListener {
        final /* synthetic */ SetAppPasswordActivity f2946a;

        C10315(SetAppPasswordActivity setAppPasswordActivity) {
            this.f2946a = setAppPasswordActivity;
        }

        public void onClick(View view) {
            if (this.f2946a.f2949a.length() < 4) {
                Toast.makeText(this.f2946a.getApplicationContext(), "Password too short!", Toast.LENGTH_LONG).show();
            } else if (this.f2946a.f2949a.length() > 12) {
                Toast.makeText(this.f2946a.getApplicationContext(), "Password too long!", Toast.LENGTH_SHORT).show();
            } else {
                this.f2946a.m5660e();
            }
        }
    }

    class C10326 implements OnClickListener {
        final /* synthetic */ SetAppPasswordActivity f2947a;

        C10326(SetAppPasswordActivity setAppPasswordActivity) {
            this.f2947a = setAppPasswordActivity;
        }

        public void onClick(View view) {
            this.f2947a.f2949a = this.f2947a.f2949a.replaceFirst(".$", "");
            this.f2947a.m5662b();
        }
    }

    class C10337 implements OnClickListener {
        final /* synthetic */ SetAppPasswordActivity f2948a;

        C10337(SetAppPasswordActivity setAppPasswordActivity) {
            this.f2948a = setAppPasswordActivity;
        }

        public void onClick(View view) {
            this.f2948a.onBackPressed();
        }
    }

    private void m5660e() {
        if (this.f2961m) {
            this.f2964p.vibrate(20);
        }
        if (this.f2971w) {
            if (!this.f2949a.equals(this.f2972x)) {
                this.f2949a = "";
                this.f2954f.startAnimation(this.f2967s);
                this.f2964p.vibrate(300);
                Toast.makeText(getApplicationContext(), "Please enter correct repeat password", Toast.LENGTH_LONG).show();
            } else if (this.f2957i) {
                this.f2951c.putString("fakePin", this.f2949a);
                this.f2951c.putBoolean("fakeEnabled", true);
                this.f2951c.commit();
                String str = getFilesDir() + "/lockerVault/FVault";
                File file = new File(str);
                if (!file.exists()) {
                    file.mkdirs();
                    new File(str + "/Images1769/Pictures").mkdirs();
                    new File(str + "/Videos1769/Videos").mkdirs();
                }
                setResult(-1);
                finish();
            } else {
                this.f2951c.putString("password", this.f2949a);
                this.f2951c.commit();
                if (this.f2963o) {
                    finish();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("mPass", this.f2949a);
                startActivity(intent);
            }
        } else if (this.f2962n) {
            if (this.f2949a.equals(this.f2952d)) {
                this.f2962n = false;
                this.f2949a = "";
                this.f2965q.setText("Enter new password");
                this.f2954f.removeAllViews();
                findViewById(R.id.tvNumLimit).setVisibility(View.VISIBLE);
                return;
            }
            this.f2949a = "";
            this.f2954f.startAnimation(this.f2967s);
            this.f2964p.vibrate(300);
            Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
        } else if (this.f2957i && this.f2949a.equals(this.f2952d)) {
            this.f2949a = "";
            this.f2954f.startAnimation(this.f2967s);
            this.f2964p.vibrate(100);
            Toast.makeText(getApplicationContext(), "Fake PIN must be different from real PIN", Toast.LENGTH_LONG).show();
        } else {
            this.f2972x = this.f2949a;
            this.f2949a = "";
            this.f2965q.setText("Repeat password");
            ((TextView) findViewById(R.id.tvNumLimit)).setText("(must be the same as previous)");
            this.f2971w = true;
            this.f2954f.removeAllViews();
        }
    }

    void m5661a() {
        View imageView = new ImageView(getApplicationContext());
        LayoutParams layoutParams = new LinearLayout.LayoutParams((int) convertDpToPixel(10f, this), (int) convertDpToPixel(10f, this));
        imageView.setBackgroundResource(R.drawable.dot_presed);
        if (this.f2954f.getChildCount() < 13) {
            this.f2954f.addView(imageView, layoutParams);
        }
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    void m5662b() {
        this.f2954f.removeView((ImageView) this.f2954f.getChildAt(this.f2954f.getChildCount() - 1));
    }

    @TargetApi(21)
    protected void m5663c() {
        this.f2955g = new Builder().setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()).setMaxStreams(6).build();
    }

    protected void m5664d() {
        this.f2955g = new SoundPool(10, 3, 0);
    }

    public void onBackPressed() {
        if ((this.f2963o | this.f2957i) != false) {
            super.onBackPressed();
            return;
        }
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f2974z = C1131f.getCurrentStyle(PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
        if (this.f2974z != R.style.AppTheme) {
            setTheme(this.f2974z);
        }
        this.f2962n = getIntent().getBooleanExtra("fromReset", false);
        this.f2963o = getIntent().getBooleanExtra("fromReset", false);
        this.f2957i = getIntent().getBooleanExtra("fromFake", false);
        setContentView((int) R.layout.activity_set_pwd);
        getSupportActionBar().hide();
        ((TextView) findViewById(R.id.textView1)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.textView2)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.textView3)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.textView4)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.textView5)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.textView6)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.textView7)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.textView8)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.textView9)).setTypeface(C1131f.f3315a);
        ((TextView) findViewById(R.id.textView0)).setTypeface(C1131f.f3315a);
        this.f2954f = (LinearLayout) findViewById(R.id.ll_dots);
        this.f2965q = (TextView) findViewById(R.id.lock_textView1);
        if (this.f2962n) {
            this.f2965q.setText("Enter Old Password");
        } else if (this.f2957i) {
            this.f2965q.setText("Create Fake PIN");
        }
        findViewById(R.id.tvNumLimit).setVisibility(this.f2962n ? 8 : 0);
        this.f2964p = (Vibrator) getSystemService("vibrator");
        if (VERSION.SDK_INT >= 21) {
            m5663c();
        } else {
            m5664d();
        }
        this.f2955g.setOnLoadCompleteListener(new C10271(this));
        this.f2958j = this.f2955g.load(this, R.raw.click, 1);
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        this.f2959k = ((float) audioManager.getStreamVolume(3)) / ((float) audioManager.getStreamMaxVolume(3));
        this.f2950b = PreferenceManager.getDefaultSharedPreferences(this);
        this.f2952d = this.f2950b.getString("password", "   ");
        this.f2953e = this.f2950b.getString("fakePin", "  ");
        this.f2960l = this.f2950b.getBoolean("isSound", true);
        this.f2961m = this.f2950b.getBoolean("isVib", false);
        this.f2951c = this.f2950b.edit();
        findViewById(R.id.rl0).setOnClickListener(this.f2968t);
        findViewById(R.id.rl1).setOnClickListener(this.f2968t);
        findViewById(R.id.rl2).setOnClickListener(this.f2968t);
        findViewById(R.id.rl3).setOnClickListener(this.f2968t);
        findViewById(R.id.rl4).setOnClickListener(this.f2968t);
        findViewById(R.id.rl5).setOnClickListener(this.f2968t);
        findViewById(R.id.rl6).setOnClickListener(this.f2968t);
        findViewById(R.id.rl7).setOnClickListener(this.f2968t);
        findViewById(R.id.rl8).setOnClickListener(this.f2968t);
        findViewById(R.id.rl9).setOnClickListener(this.f2968t);
        findViewById(R.id.rl0).setOnClickListener(this.f2968t);
        findViewById(R.id.rlOk).setOnClickListener(this.f2969u);
        findViewById(R.id.rlExit).setOnClickListener(this.f2973y);
        findViewById(R.id.rippleDelete).setOnClickListener(this.f2970v);
        findViewById(R.id.rippleDelete).setOnLongClickListener(new C10282(this));
        findViewById(R.id.rl0).setOnTouchListener(new C1121a(getApplicationContext()));
        findViewById(R.id.rl1).setOnTouchListener(new C1121a(getApplicationContext()));
        findViewById(R.id.rl2).setOnTouchListener(new C1121a(getApplicationContext()));
        findViewById(R.id.rl3).setOnTouchListener(new C1121a(getApplicationContext()));
        findViewById(R.id.rl4).setOnTouchListener(new C1121a(getApplicationContext()));
        findViewById(R.id.rl5).setOnTouchListener(new C1121a(getApplicationContext()));
        findViewById(R.id.rl6).setOnTouchListener(new C1121a(getApplicationContext()));
        findViewById(R.id.rl7).setOnTouchListener(new C1121a(getApplicationContext()));
        findViewById(R.id.rl8).setOnTouchListener(new C1121a(getApplicationContext()));
        findViewById(R.id.rl9).setOnTouchListener(new C1121a(getApplicationContext()));
        findViewById(R.id.rlOk).setOnTouchListener(new C1121a(getApplicationContext()));
        findViewById(R.id.rlExit).setOnTouchListener(new C1121a(getApplicationContext()));
        this.f2967s = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        this.f2967s.setAnimationListener(new C10293(this));
    }

    protected void onDestroy() {
        if (this.f2955g != null) {
            this.f2955g.release();
        }
        super.onDestroy();
    }
}
