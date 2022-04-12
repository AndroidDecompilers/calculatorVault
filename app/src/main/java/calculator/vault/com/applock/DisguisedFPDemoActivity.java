package calculator.vault.com.applock;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.R;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.lock.MainActivity;
import calculator.vault.com.lock.ManageSpaceActivity;


public class DisguisedFPDemoActivity extends AppCompatActivity {
    PowerManager f9840a;
    TelephonyManager f9841b;
    SensorManager f9842c;
    Sensor f9843d;
    public int f9844e;
    boolean f9845f;
    String f9846g;
    SharedPreferences f9847h;
    boolean f9848i;
    boolean f9849j = false;
    long f9850k = 0;
    long f9851l;
    long f9852m;
    private ImageView f9853n;
    private ImageView f9854o;
    private ImageView f9855p;
    private ImageView f9856q;
    private AnimationSet f9857r;
    private AnimationSet f9858s;
    private int f9859t;
    private SensorEventListener f9860u = new C31165(this);

    class C31111 implements AnimationListener {
        final /* synthetic */ DisguisedFPDemoActivity f9826a;

        class C31101 implements Runnable {
            final /* synthetic */ C31111 f9825a;

            C31101(C31111 c31111) {
                this.f9825a = c31111;
            }

            public void run() {
                this.f9825a.f9826a.f9855p.animate().alpha(0.0f).setDuration(1000).setStartDelay(500).start();
            }
        }

        C31111(DisguisedFPDemoActivity disguisedFPDemoActivity) {
            this.f9826a = disguisedFPDemoActivity;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public void onAnimationEnd(Animation animation) {
            this.f9826a.f9855p.animate().alpha(1.0f).setDuration(500).withEndAction(new C31101(this)).start();
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    class C31143 implements OnTouchListener {
        final /* synthetic */ DisguisedFPDemoActivity f9831a;

        C31143(DisguisedFPDemoActivity disguisedFPDemoActivity) {
            this.f9831a = disguisedFPDemoActivity;
        }

        public boolean onTouch(View view, final MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                DisguisedFPDemoActivity disguisedFPDemoActivity = this.f9831a;
                DisguisedFPDemoActivity disguisedFPDemoActivity2 = this.f9831a;
                long currentTimeMillis = System.currentTimeMillis();
                disguisedFPDemoActivity2.f9850k = currentTimeMillis;
                disguisedFPDemoActivity.f9852m = currentTimeMillis;
                if (this.f9831a.f9850k - this.f9831a.f9851l > 500) {
                    this.f9831a.f9849j = false;
                }
                if (this.f9831a.f9849j) {
                    new Handler().postDelayed(new Runnable() {

                        public void run() {
                            if (motionEvent.getAction() == 0 || motionEvent.getAction() == 2) {
                                f9831a.m14794a();
                            }
                        }
                    }, 500);
                }
            } else if (motionEvent.getAction() == 1) {
                this.f9831a.f9851l = System.currentTimeMillis();
                if (!this.f9831a.f9849j) {
                    this.f9831a.f9848i = false;
                    this.f9831a.f9849j = true;
                } else if (System.currentTimeMillis() - this.f9831a.f9850k >= 1000) {
                    this.f9831a.f9848i = true;
                    this.f9831a.f9849j = false;
                } else {
                    this.f9831a.f9849j = false;
                }
            }
            return true;
        }
    }

    class C31154 extends TimerTask {
        final /* synthetic */ DisguisedFPDemoActivity f9832a;

        C31154(DisguisedFPDemoActivity disguisedFPDemoActivity) {
            this.f9832a = disguisedFPDemoActivity;
        }

        public void run() {
            try {
                if (SecurityHelpers.isCallRinging(this.f9832a.f9841b) || !SecurityHelpers.m14854b(this.f9832a.getApplicationContext()).equals(this.f9832a.getPackageName())) {
                    this.f9832a.finish();
                    if (DisguisedActivity.f9806k != null) {
                        DisguisedActivity.f9806k.finish();
                    }
                    MainActivity.mainActivity.finish();
                }
                if (!SecurityHelpers.isScreenON(this.f9832a.f9840a)) {
                    this.f9832a.finish();
                    MainActivity.mainActivity.finish();
                    Intent intent = new Intent(this.f9832a.getApplicationContext(), ManageSpaceActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    this.f9832a.startActivity(intent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class C31165 implements SensorEventListener {
        final /* synthetic */ DisguisedFPDemoActivity f9833a;

        C31165(DisguisedFPDemoActivity disguisedFPDemoActivity) {
            this.f9833a = disguisedFPDemoActivity;
        }

        public void onAccuracyChanged(Sensor sensor, int i) {
        }

        public void onSensorChanged(SensorEvent sensorEvent) {
            try {
                float f = sensorEvent.values[2];
                if ((f <= 9.0f || f >= 10.0f) && f > -10.0f && f < -9.0f && !this.f9833a.f9845f) {
                    this.f9833a.f9845f = true;
                    if (this.f9833a.f9844e == 1) {
                        C1131f.m5806a(this.f9833a.getApplicationContext(), this.f9833a.getPackageManager(), this.f9833a.f9847h.getString("Package_Name", null));
                    }
                    if (this.f9833a.f9844e == 2) {
                        this.f9833a.f9846g = this.f9833a.f9847h.getString("URL_Name", null);
                        this.f9833a.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.f9833a.f9846g)));
                    }
                    if (this.f9833a.f9844e == 0) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.HOME");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.f9833a.startActivity(intent);
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    class C31198 implements AnimationListener {
        final /* synthetic */ DisguisedFPDemoActivity f9838a;

        C31198(DisguisedFPDemoActivity disguisedFPDemoActivity) {
            this.f9838a = disguisedFPDemoActivity;
        }

        public void onAnimationEnd(Animation animation) {
            this.f9838a.f9853n.setVisibility(View.GONE);
            this.f9838a.m14798c();
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    class C31209 implements AnimationListener {
        final /* synthetic */ DisguisedFPDemoActivity f9839a;

        C31209(DisguisedFPDemoActivity disguisedFPDemoActivity) {
            this.f9839a = disguisedFPDemoActivity;
        }

        public void onAnimationEnd(Animation animation) {
            this.f9839a.f9854o.setVisibility(View.GONE);
            this.f9839a.m14795b();
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    private void m14794a() {
        final Dialog dialog = new Dialog(this);
        View inflate = getLayoutInflater().inflate(R.layout.demo_success_dialog, null);
        TextView textView = (TextView) inflate.findViewById(R.id.tvOk);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tvCancel);
        textView.setTypeface(C1131f.f3322h);
        textView2.setTypeface(C1131f.f3322h);
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                setResult(-1);
                finish();
            }
        });
        textView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(inflate);
        dialog.show();
    }

    private void m14795b() {
        Animation alphaAnimation = new AlphaAnimation(0.0f, 9.0f);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setDuration(2100);
        this.f9858s = new AnimationSet(false);
        this.f9858s.setFillAfter(true);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        this.f9854o.setVisibility(View.INVISIBLE);
        Animation translateAnimation = new TranslateAnimation(0, 0.0f, 0, 0.0f, 2, 0.0f, 2, 1.0f);
        this.f9858s.addAnimation(alphaAnimation);
        this.f9858s.addAnimation(loadAnimation);
        this.f9858s.addAnimation(translateAnimation);
        translateAnimation.setDuration(1400);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.setFillAfter(true);
        translateAnimation.isFillEnabled();
        translateAnimation.setInterpolator(new LinearInterpolator());
        this.f9853n.setAnimation(this.f9858s);
        translateAnimation.setAnimationListener(new C31198(this));
    }

    private void m14798c() {
        Animation alphaAnimation = new AlphaAnimation(0.0f, 9.0f);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setDuration(2100);
        this.f9857r = new AnimationSet(false);
        this.f9857r.setFillAfter(true);
        Animation translateAnimation = new TranslateAnimation(0, 0.0f, 0, 0.0f, 2, 0.0f, 2, -1.0f);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        this.f9857r.addAnimation(alphaAnimation);
        this.f9857r.addAnimation(translateAnimation);
        this.f9857r.addAnimation(loadAnimation);
        translateAnimation.setDuration(1400);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.setFillAfter(true);
        translateAnimation.isFillEnabled();
        translateAnimation.setInterpolator(new LinearInterpolator());
        this.f9854o.setAnimation(this.f9857r);
        translateAnimation.setAnimationListener(new C31209(this));
    }

    @SuppressLint("WrongConstant")
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f9847h = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f9859t = C1131f.getCurrentStyle(this.f9847h);
        if (this.f9859t != R.style.AppTheme) {
            setTheme(this.f9859t);
        }
        setContentView((int) R.layout.applock_fp_cover_demo);
        if (VERSION.SDK_INT >= 21) {
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().setStatusBarColor(-16777216);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.f9840a = (PowerManager) getSystemService("power");
        this.f9841b = (TelephonyManager) getSystemService("phone");
        this.f9856q = (ImageView) findViewById(R.id.ivFingerTouch);
        this.f9855p = (ImageView) findViewById(R.id.ivFingerDemo);
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(400);
        Animation alphaAnimation2 = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation2.setStartOffset(400);
        alphaAnimation2.setDuration(400);
        final AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(alphaAnimation2);
        animationSet.setAnimationListener(new C31111(this));
        findViewById(R.id.button2).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                f9855p.setVisibility(0);
                f9855p.setAlpha(1.0f);
                f9855p.startAnimation(animationSet);
            }
        });
        try {
            if (this.f9847h.getBoolean("faceDown", false)) {
                this.f9844e = this.f9847h.getInt("selectedPos", 0);
                this.f9842c = (SensorManager) getSystemService("sensor");
                this.f9843d = (Sensor) this.f9842c.getSensorList(1).get(0);
                this.f9842c.registerListener(this.f9860u, this.f9843d, 3);
            }
        } catch (Exception e) {
        }
        this.f9853n = (ImageView) findViewById(R.id.ivLineUp);
        this.f9854o = (ImageView) findViewById(R.id.ivLineDown);
        m14795b();
        this.f9856q.setOnTouchListener(new C31143(this));
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
        overridePendingTransition(0, R.anim.slide_right);
        super.onPause();
    }

    protected void onResume() {
        try {
            if (this.f9842c != null) {
                this.f9842c.registerListener(this.f9860u, this.f9843d, 3);
            }
        } catch (Exception e) {
        }
        super.onResume();
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        try {
            if (this.f9842c != null) {
                this.f9842c.unregisterListener(this.f9860u);
            }
        } catch (Exception e) {
        }
        if (this.f9841b != null) {
            new Timer().schedule(new C31154(this), 1000);
        }
        super.onStop();
    }
}
