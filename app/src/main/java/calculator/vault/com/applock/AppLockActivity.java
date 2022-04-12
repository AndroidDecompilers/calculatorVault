package calculator.vault.com.applock;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.ErrorCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.fingerprint.FingerprintManager;
import android.hardware.fingerprint.FingerprintManager.CryptoObject;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.media.SoundPool.Builder;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


import calculator.vault.com.R;
import calculator.vault.com.lock.C1121a;
import calculator.vault.com.lock.C1123b;
import calculator.vault.com.p068a.C1584f;
import haibison.android.lockpattern.LockPatternActivity;

@SuppressLint({"InlinedApi"})
public class AppLockActivity extends Activity {
    long f9751A;
    protected int f9752B;
    protected int f9753C;
    public HashMap<String, Integer> f9754D = new C30941(this);
    String f9755E;
    int f9756F;
    int f9757G;
    C1123b f9758H;
    Camera f9759I;
    boolean f9760J;
    AnimationSet f9761K;
    AnimationSet f9762L;
    ImageView f9763M;
    ImageView f9764N;
    ImageView f9765O;
    BroadcastReceiver f9766P = new C31005(this);
    Animation f9767Q;
    OnClickListener f9768R = new C31016(this);
    OnClickListener f9769S = new C31027(this);
    OnClickListener f9770T = new C31038(this);
    boolean f9771U;
    OnClickListener f9772V = new C31049(this);
    int f9773W;
    AutoFocusCallback f9774X = new AutoFocusCallback() {
        public void onAutoFocus(boolean z, Camera camera) {
            try {
//                camera.takePicture(f9775Y, null, aa);
            } catch (Exception e) {
            }
        }
    };

    ShutterCallback f9775Y = new ShutterCallback() {
        public void onShutter() {
        }
    };

    boolean f9776Z;
    String f9777a = "";
    private int ah;
    private int ai;
    SharedPreferences f9778b;
    Editor f9779c;
    String f9780d;
    LinearLayout f9781e;
    SoundPool f9782f;
    boolean f9783g;
    boolean f9784h;
    int f9785i;
    float f9786j;
    boolean f9787k;
    boolean f9788l;
    Vibrator f9789m;
    int f9790n;
    FrameLayout f9792p;
    int f9793q;
    int f9794r;
    Window f9795s;
    int f9796t;
    int f9797u;
    String f9798v = "App Lock";
    boolean f9799w;
    boolean f9800x = false;
    long f9801y = 0;
    long f9802z;

    class C30941 extends HashMap<String, Integer> {
        final /* synthetic */ AppLockActivity f9721a;

        C30941(AppLockActivity appLockActivity) {
            this.f9721a = appLockActivity;
            put("com.whatsapp", Integer.valueOf(-13584316));
            put("com.facebook.katana", Integer.valueOf(-12887656));
            put("com.facebook.orca", Integer.valueOf(-16743169));
            put("com.viber.voip", Integer.valueOf(-7381577));
            put("com.tencent.mm", Integer.valueOf(-16723443));
            put("com.bsb.hike", Integer.valueOf(-12733957));
            put("jp.naver.line.android", Integer.valueOf(-16727296));
            put("com.imo.android.imoim", Integer.valueOf(-14722642));
            put("com.google.android.apps.messaging", Integer.valueOf(-16733193));
            put("com.instagram.android", Integer.valueOf(-2019220));
            put("com.snapchat.android", Integer.valueOf(-1024));
            put("com.twitter.android", Integer.valueOf(-11162386));
            put("org.telegram.messenger", Integer.valueOf(-16742196));
            put("com.skype.raider", Integer.valueOf(-16732691));
            put("com.nimbuzz", Integer.valueOf(-25572));
            put("com.bbm", Integer.valueOf(-12500671));
            put("com.google.android.gm", Integer.valueOf(-2406855));
            put("com.android.chrome", Integer.valueOf(-2339781));
            put("com.android.settings", Integer.valueOf(-11903128));
            put("com.estrongs.android.pop", Integer.valueOf(-13721875));
            put("com.asus.filemanager", Integer.valueOf(-9659));
            put("com.google.android.apps.photos", Integer.valueOf(-283116));
            put("com.android.vending", Integer.valueOf(-10634517));
            put("com.google.android.apps.plus", Integer.valueOf(-2273212));
            put("com.google.android.talk", Integer.valueOf(-15228834));
            put("com.tinder", Integer.valueOf(-957340));
        }
    }

    class C30962 implements OnClickListener {
        final /* synthetic */ AppLockActivity f9737a;

        C30962(AppLockActivity appLockActivity) {
            this.f9737a = appLockActivity;
        }

        public void onClick(View view) {
            if (this.f9737a.f9778b.getBoolean("isFirstFake", true)) {
                Toast.makeText(this.f9737a.getApplicationContext(), "Swipe from right to left on button.", Toast.LENGTH_SHORT).show();
                this.f9737a.f9779c.putBoolean("isFirstFake", false);
                this.f9737a.f9779c.commit();
                return;
            }
            this.f9737a.onBackPressed();
        }
    }

    class C31005 extends BroadcastReceiver {
        final /* synthetic */ AppLockActivity f9744a;

        C31005(AppLockActivity appLockActivity) {
            this.f9744a = appLockActivity;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() == "ACTION_INTRUDER_SELFIE") {
                this.f9744a.m14790h();
            }
        }
    }

    class C31016 implements OnClickListener {
        final /* synthetic */ AppLockActivity f9745a;

        C31016(AppLockActivity appLockActivity) {
            this.f9745a = appLockActivity;
        }

        public void onClick(View view) {
            if (!this.f9745a.f9771U) {
                StringBuilder stringBuilder;
                AppLockActivity appLockActivity;
                switch (view.getId()) {
                    case R.id.rl1:
                        stringBuilder = new StringBuilder();
                        appLockActivity = this.f9745a;
                        appLockActivity.f9777a = stringBuilder.append(appLockActivity.f9777a).append("1").toString();
                        break;
                    case R.id.rl2:
                        stringBuilder = new StringBuilder();
                        appLockActivity = this.f9745a;
                        appLockActivity.f9777a = stringBuilder.append(appLockActivity.f9777a).append("2").toString();
                        break;
                    case R.id.rl3:
                        stringBuilder = new StringBuilder();
                        appLockActivity = this.f9745a;
                        appLockActivity.f9777a = stringBuilder.append(appLockActivity.f9777a).append("3").toString();
                        break;
                    case R.id.rl4:
                        stringBuilder = new StringBuilder();
                        appLockActivity = this.f9745a;
                        appLockActivity.f9777a = stringBuilder.append(appLockActivity.f9777a).append("4").toString();
                        break;
                    case R.id.rl5:
                        stringBuilder = new StringBuilder();
                        appLockActivity = this.f9745a;
                        appLockActivity.f9777a = stringBuilder.append(appLockActivity.f9777a).append("5").toString();
                        break;
                    case R.id.rl6:
                        stringBuilder = new StringBuilder();
                        appLockActivity = this.f9745a;
                        appLockActivity.f9777a = stringBuilder.append(appLockActivity.f9777a).append("6").toString();
                        break;
                    case R.id.rl7:
                        stringBuilder = new StringBuilder();
                        appLockActivity = this.f9745a;
                        appLockActivity.f9777a = stringBuilder.append(appLockActivity.f9777a).append("7").toString();
                        break;
                    case R.id.rl8:
                        stringBuilder = new StringBuilder();
                        appLockActivity = this.f9745a;
                        appLockActivity.f9777a = stringBuilder.append(appLockActivity.f9777a).append("8").toString();
                        break;
                    case R.id.rl9:
                        stringBuilder = new StringBuilder();
                        appLockActivity = this.f9745a;
                        appLockActivity.f9777a = stringBuilder.append(appLockActivity.f9777a).append("9").toString();
                        break;
                    case R.id.rl0:
                        stringBuilder = new StringBuilder();
                        appLockActivity = this.f9745a;
                        appLockActivity.f9777a = stringBuilder.append(appLockActivity.f9777a).append("0").toString();
                        break;
                }
                if (this.f9745a.f9783g && this.f9745a.f9787k) {
                    this.f9745a.ai = this.f9745a.f9782f.play(this.f9745a.f9785i, this.f9745a.f9786j, this.f9745a.f9786j, 1, 0, 1.0f);
                }
                this.f9745a.m14783a();
                this.f9745a.m14778j();
            }
        }
    }

    class C31027 implements OnClickListener {
        final /* synthetic */ AppLockActivity f9746a;

        C31027(AppLockActivity appLockActivity) {
            this.f9746a = appLockActivity;
        }

        public void onClick(View view) {
            if (this.f9746a.f9777a.length() <= 0) {
                Toast.makeText(this.f9746a.getApplicationContext(), "Enter Password first", Toast.LENGTH_LONG).show();
            } else if (!this.f9746a.m14778j()) {
                this.f9746a.f9777a = "";
                this.f9746a.f9781e.startAnimation(this.f9746a.f9767Q);
                this.f9746a.f9789m.vibrate(200);
                this.f9746a.m14780l();
            }
        }
    }

    class C31038 implements OnClickListener {
        final /* synthetic */ AppLockActivity f9747a;

        C31038(AppLockActivity appLockActivity) {
            this.f9747a = appLockActivity;
        }

        public void onClick(View view) {
            this.f9747a.f9777a = this.f9747a.f9777a.replaceFirst(".$", "");
            this.f9747a.m14784b();
        }
    }

    class C31049 implements OnClickListener {
        final /* synthetic */ AppLockActivity f9748a;

        C31049(AppLockActivity appLockActivity) {
            this.f9748a = appLockActivity;
        }

        public void onClick(View view) {
            if (this.f9748a.f9778b.getString("regEmail", "").length() > 3) {
                C3150b.m14833a(this.f9748a, this.f9748a.f9778b.getString("regEmail", ""), this.f9748a.f9780d);
            } else {
                Toast.makeText(this.f9748a.getApplicationContext(), "Sorry, you did not save any email address to receive password.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private class C3106a extends AsyncTask<Void, Void, Drawable> {
        final /* synthetic */ AppLockActivity f9750a;

        class C31051 implements Runnable {
            final /* synthetic */ C3106a f9749a;

            C31051(C3106a c3106a) {
                this.f9749a = c3106a;
            }

            public void run() {
                ((TextView) this.f9749a.f9750a.findViewById(R.id.lock_textView1)).setText(this.f9749a.f9750a.f9798v);
            }
        }

        private C3106a(AppLockActivity appLockActivity) {
            this.f9750a = appLockActivity;
        }

        protected Drawable m14763a(Void... voidArr) {
            try {
                ApplicationInfo applicationInfo = this.f9750a.getPackageManager().getApplicationInfo(this.f9750a.f9784h ? WindowChangeDetectingService.f9920b : MyAppLockService.f9891d, 0);
                this.f9750a.f9798v = "" + applicationInfo.loadLabel(this.f9750a.getPackageManager());
                this.f9750a.runOnUiThread(new C31051(this));
                return applicationInfo.loadIcon(this.f9750a.getPackageManager());
            } catch (NameNotFoundException e) {
                return null;
            }
        }

        protected void m14764a(Drawable drawable) {
            if (drawable != null) {
                ((ImageView) this.f9750a.findViewById(R.id.iv_appicon)).setBackgroundDrawable(drawable);
            }
            super.onPostExecute(drawable);
        }

        protected Drawable doInBackground(Void[] objArr) {
            return m14763a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Drawable obj) {
            m14764a((Drawable) obj);
        }
    }

    public static Bitmap m14766a(Bitmap bitmap, float f) {
        Matrix matrix = new Matrix();
        matrix.postRotate(f);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    private void m14767a(View view, int i, int i2, int i3, int i4) {
        this.f9795s.setStatusBarColor(0);
        Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(this.f9792p, i, i2, (float) Math.max(i3, i4), 0.0f);
        createCircularReveal.setInterpolator(new OvershootInterpolator());
        createCircularReveal.setDuration(700);
        view.setVisibility(View.VISIBLE);
        createCircularReveal.start();
        createCircularReveal.addListener(new AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
                f9792p.setVisibility(View.GONE);
                if (f9784h) {
                    WindowChangeDetectingService.f9919a.remove(WindowChangeDetectingService.f9920b);
                } else if (!(MyAppLockService.class == null || MyAppLockService.f9891d == null || MyAppLockService.f9890c == null)) {
                    MyAppLockService.f9890c.remove(MyAppLockService.f9891d);
                }
                setResult(-1);
                f9760J = true;
                finish();
                overridePendingTransition(0, R.anim.lock_fade_out);
            }

            public void onAnimationEnd(Animator animator) {
                f9792p.setVisibility(View.GONE);
                if (f9784h) {
                    WindowChangeDetectingService.f9919a.remove(WindowChangeDetectingService.f9920b);
                } else if (!(MyAppLockService.class == null || MyAppLockService.f9891d == null || MyAppLockService.f9890c == null)) {
                    MyAppLockService.f9890c.remove(MyAppLockService.f9891d);
                }
                setResult(-1);
                f9760J = true;
                finish();
                overridePendingTransition(0, R.anim.lock_fade_out);
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }
        });
    }

    private void m14777i() {
        Intent intent = new Intent(LockPatternActivity.ACTION_COMPARE_PATTERN, null, getApplicationContext(), LockPatternActivity.class);
        intent.putExtra("isStealthMode", this.f9778b.getBoolean("stealthMode", false));
        intent.putExtra("isFromLock", true);
        intent.putExtra("colorCode", this.f9790n);
        startActivityForResult(intent, 70);
    }

    private boolean m14778j() {
        if (this.f9788l) {
            this.f9789m.vibrate(20);
        }
        if (!this.f9777a.equals(this.f9780d)) {
            return false;
        }
        if (this.f9784h) {
            WindowChangeDetectingService.f9919a.remove(WindowChangeDetectingService.f9920b);
            this.f9760J = true;
            finish();
            overridePendingTransition(0, R.anim.lock_fade_out);
            return true;
        } else if (MyAppLockService.class == null || MyAppLockService.f9891d == null || MyAppLockService.f9890c == null) {
            return true;
        } else {
            MyAppLockService.f9890c.remove(MyAppLockService.f9891d);
            this.f9760J = true;
            finish();
            overridePendingTransition(0, R.anim.lock_fade_out);
            return true;
        }
    }

    private void m14779k() {
        this.f9792p.post(new Runnable() {

            public void run() {
                try {
                    m14767a(f9792p, f9793q / 2, f9794r, f9793q, f9794r);
                } catch (Exception e) {
                    if (f9784h) {
                        WindowChangeDetectingService.f9919a.remove(WindowChangeDetectingService.f9920b);
                    } else if (!(MyAppLockService.class == null || MyAppLockService.f9891d == null || MyAppLockService.f9890c == null)) {
                        MyAppLockService.f9890c.remove(MyAppLockService.f9891d);
                    }
                    setResult(-1);
                    f9760J = true;
                    finish();
                    overridePendingTransition(0, R.anim.lock_fade_out);
                }
            }
        });
    }

    private void m14780l() {
        int i = this.f9790n;
        int color = getResources().getColor(R.color.error);
        ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(i), Integer.valueOf(color)});
        ofObject.setDuration(500);
        ofObject.addUpdateListener(new AnimatorUpdateListener() {

            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                f9792p.setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
                if (f9795s != null) {
                    f9795s.setStatusBarColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
                }
            }
        });
        ofObject.start();
        new Handler().postDelayed(new Runnable() {
            class C30881 implements AnimatorUpdateListener {
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    f9792p.setBackgroundColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
                    if (f9795s != null) {
                        f9795s.setStatusBarColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
                    }
                }
            }

            public void run() {
                try {
                    int i = f9790n;
                    int color = getResources().getColor(R.color.error);
                    ValueAnimator ofObject = ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(color), Integer.valueOf(i)});
                    ofObject.setDuration(500);
                    ofObject.addUpdateListener(new C30881());
                    ofObject.start();
                } catch (Exception e) {
                }
            }
        }, 1200);
        m14790h();
    }

    private void m14781m() {
        Animation alphaAnimation = new AlphaAnimation(0.0f, 9.0f);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setDuration(2100);
        this.f9762L = new AnimationSet(false);
        this.f9762L.setFillAfter(true);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        this.f9764N.setVisibility(View.INVISIBLE);
        Animation translateAnimation = new TranslateAnimation(0, 0.0f, 0, 0.0f, 2, 0.0f, 2, 1.0f);
        this.f9762L.addAnimation(alphaAnimation);
        this.f9762L.addAnimation(loadAnimation);
        this.f9762L.addAnimation(translateAnimation);
        translateAnimation.setDuration(1400);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.setFillAfter(true);
        translateAnimation.isFillEnabled();
        translateAnimation.setInterpolator(new LinearInterpolator());
        this.f9763M.setAnimation(this.f9762L);
        translateAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                f9763M.setVisibility(View.GONE);
                m14782n();
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
    }

    private void m14782n() {
        Animation alphaAnimation = new AlphaAnimation(0.0f, 9.0f);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setDuration(2100);
        this.f9761K = new AnimationSet(false);
        this.f9761K.setFillAfter(true);
        Animation translateAnimation = new TranslateAnimation(0, 0.0f, 0, 0.0f, 2, 0.0f, 2, -1.0f);
        Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        this.f9761K.addAnimation(alphaAnimation);
        this.f9761K.addAnimation(translateAnimation);
        this.f9761K.addAnimation(loadAnimation);
        translateAnimation.setDuration(1400);
        translateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        translateAnimation.setFillAfter(true);
        translateAnimation.isFillEnabled();
        translateAnimation.setInterpolator(new LinearInterpolator());
        this.f9764N.setAnimation(this.f9761K);
        translateAnimation.setAnimationListener(new AnimationListener() {

            public void onAnimationEnd(Animation animation) {
                f9764N.setVisibility(View.GONE);
                m14781m();
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });
    }

    void m14783a() {
        View imageView = new ImageView(getApplicationContext());
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(this.f9796t, this.f9796t);
        layoutParams.setMargins(this.f9797u, 2, this.f9797u, 2);
        ViewGroup frameLayout = new FrameLayout(getApplicationContext());
        frameLayout.setLayoutParams(layoutParams);
        imageView.setBackgroundResource(R.drawable.circle_shape2);
        frameLayout.addView(imageView);
        if (this.f9781e.getChildCount() < 13) {
            this.f9781e.addView(frameLayout);
            imageView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.dot_insert));
            return;
        }
        this.f9777a = "";
        this.f9771U = true;
        this.f9789m.vibrate(200);
        this.f9781e.startAnimation(this.f9767Q);
        m14780l();
    }

    void m14784b() {
        this.f9781e.removeView((FrameLayout) this.f9781e.getChildAt(this.f9781e.getChildCount() - 1));
    }

    @TargetApi(21)
    protected void m14785c() {
        this.f9782f = new Builder().setAudioAttributes(new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build()).setMaxStreams(6).build();
    }

    protected void m14786d() {
        this.f9782f = new SoundPool(10, 3, 0);
    }


    public void m14789g() {
        this.f9759I.autoFocus(this.f9774X);
    }

    public void m14790h() {
        this.f9773W++;
        if (this.f9773W == this.f9756F) {
            try {
                if (this.f9778b.getBoolean("isSelfie", true)) {
                    FrameLayout frameLayout = (FrameLayout) findViewById(R.id.flMain);
                    this.f9758H = new C1123b(this, (SurfaceView) ((ViewStub) findViewById(R.id.viewStub1)).inflate().findViewById(R.id.KutCameraFragment));
                    frameLayout.addView(this.f9758H);
                    this.f9758H.setKeepScreenOn(true);
                    if (this.f9759I == null) {
                        Camera camera = this.f9759I;
                        final int i = Camera.getNumberOfCameras() >= 2 ? 1 : 0;
                        this.f9759I = Camera.open(i);
                        CameraInfo cameraInfo = new CameraInfo();
                        Camera.getCameraInfo(i, cameraInfo);
                        this.f9757G = ((cameraInfo.orientation - getResources().getConfiguration().orientation) + 360) % 360;
                        this.f9757G++;
                        this.f9759I.startPreview();
                        this.f9759I.setErrorCallback(new ErrorCallback() {

                            public void onError(int i, Camera camera) {
                                f9759I.release();
                                f9759I = Camera.open(i);
                            }
                        });
                    }
                    if (this.f9759I != null) {
                        this.f9758H.setCamera(this.f9759I);
                    }
                }
            } catch (Exception e) {
            }
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    try {
                        if (!f9776Z) {
                            ((AudioManager) getSystemService(AUDIO_SERVICE)).setStreamMute(1, true);
                        }
                    } catch (Exception e) {
                    }
                    try {
                        m14789g();
                    } catch (Exception e2) {
                    }
                }
            }, 1000);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 70 && i2 == -1) {
            if (this.f9784h) {
                WindowChangeDetectingService.f9919a.remove(WindowChangeDetectingService.f9920b);
            } else if (!(MyAppLockService.class == null || MyAppLockService.f9891d == null || MyAppLockService.f9890c == null)) {
                MyAppLockService.f9890c.remove(MyAppLockService.f9891d);
            }
            setResult(-1);
            this.f9760J = true;
            finish();
            overridePendingTransition(0, R.anim.lock_fade_out);
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onBackPressed() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        super.onBackPressed();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f9784h = getIntent().getBooleanExtra("fromAccess", false);
        setContentView(R.layout.activity_lock);
        this.f9778b = PreferenceManager.getDefaultSharedPreferences(this);
        this.f9755E = getFilesDir() + "/lockerVault/selfieVault/";
        this.f9796t = (int) SecurityHelpers.m14847a(12.0f, getApplicationContext());
        this.f9797u = (int) SecurityHelpers.m14847a(5.0f, getApplicationContext());
        this.f9756F = this.f9778b.getInt("tryCount", 3);
        findViewById(R.id.btnPattern).setVisibility(this.f9778b.getBoolean("isPatternSet", false) ? View.VISIBLE : View.GONE);
        this.f9792p = (FrameLayout) findViewById(R.id.rll_main);
        if (this.f9754D.get(this.f9784h ? WindowChangeDetectingService.f9920b : MyAppLockService.f9891d) != null) {
            this.f9790n = ((Integer) this.f9754D.get(this.f9784h ? WindowChangeDetectingService.f9920b : MyAppLockService.f9891d)).intValue();
            this.f9792p.setBackgroundColor(this.f9790n);
        } else {
            this.f9790n = this.f9778b.getInt("lockBgColor", getResources().getColor(R.color.lock_bg_color));
            this.f9792p.setBackgroundColor(this.f9790n);
        }
        this.f9776Z = this.f9778b.getBoolean("isMute", true);
        new C3106a(this).execute(new Void[0]);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.f9793q = displayMetrics.widthPixels;
        this.f9794r = displayMetrics.heightPixels;
        if (VERSION.SDK_INT >= 21) {
            this.f9795s = getWindow();
            this.f9795s.addFlags(Integer.MIN_VALUE);
            this.f9795s.setStatusBarColor(this.f9790n);
        }
        Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "tf.ttf");
        ((TextView) findViewById(R.id.textView1)).setTypeface(createFromAsset);
        ((TextView) findViewById(R.id.textView2)).setTypeface(createFromAsset);
        ((TextView) findViewById(R.id.textView3)).setTypeface(createFromAsset);
        ((TextView) findViewById(R.id.textView4)).setTypeface(createFromAsset);
        ((TextView) findViewById(R.id.textView5)).setTypeface(createFromAsset);
        ((TextView) findViewById(R.id.textView6)).setTypeface(createFromAsset);
        ((TextView) findViewById(R.id.textView7)).setTypeface(createFromAsset);
        ((TextView) findViewById(R.id.textView8)).setTypeface(createFromAsset);
        ((TextView) findViewById(R.id.textView9)).setTypeface(createFromAsset);
        ((TextView) findViewById(R.id.textView0)).setTypeface(createFromAsset);
        this.f9781e = (LinearLayout) findViewById(R.id.ll_dots);
        this.f9789m = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (VERSION.SDK_INT >= 21) {
            m14785c();
        } else {
            m14786d();
        }
        this.f9782f.setOnLoadCompleteListener(new OnLoadCompleteListener() {

            public void onLoadComplete(SoundPool soundPool, int i, int i2) {
                f9783g = true;
            }
        });
        this.f9785i = this.f9782f.load(this, R.raw.click, 1);
        AudioManager audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        this.f9786j = ((float) audioManager.getStreamVolume(3)) / ((float) audioManager.getStreamMaxVolume(3));
        this.f9780d = this.f9778b.getString("password", "0000");
        this.f9787k = this.f9778b.getBoolean("isSound", true);
        this.f9788l = this.f9778b.getBoolean("isVib", false);
        this.f9779c = this.f9778b.edit();
        findViewById(R.id.rl0).setOnClickListener(this.f9768R);
        findViewById(R.id.rl1).setOnClickListener(this.f9768R);
        findViewById(R.id.rl2).setOnClickListener(this.f9768R);
        findViewById(R.id.rl3).setOnClickListener(this.f9768R);
        findViewById(R.id.rl4).setOnClickListener(this.f9768R);
        findViewById(R.id.rl5).setOnClickListener(this.f9768R);
        findViewById(R.id.rl6).setOnClickListener(this.f9768R);
        findViewById(R.id.rl7).setOnClickListener(this.f9768R);
        findViewById(R.id.rl8).setOnClickListener(this.f9768R);
        findViewById(R.id.rl9).setOnClickListener(this.f9768R);
        findViewById(R.id.rl0).setOnClickListener(this.f9768R);
        findViewById(R.id.rlOk).setOnClickListener(this.f9769S);
        findViewById(R.id.rlHome).setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                onBackPressed();
            }
        });
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
        findViewById(R.id.rlHome).setOnTouchListener(new C1121a(getApplicationContext()));
        findViewById(R.id.rippleDelete).setOnClickListener(this.f9770T);
        findViewById(R.id.rippleDelete).setOnLongClickListener(new OnLongClickListener() {

            public boolean onLongClick(View view) {
                f9777a = "";
                f9781e.removeAllViews();
                return false;
            }
        });
        findViewById(R.id.tvForget).setOnClickListener(this.f9772V);
        ((TextView) findViewById(R.id.tvForget)).setTypeface(createFromAsset);
        findViewById(R.id.rlThemeIcon).setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                final Dialog dialog = new Dialog(AppLockActivity.this);
                View inflate = getLayoutInflater().inflate(R.layout.grid_dialog_color, null);
                ((TextView) inflate.findViewById(R.id.textView1)).setText("Theme Color");
                GridView gridView = (GridView) inflate.findViewById(R.id.gvColorList);
                final int[] iArr = new int[]{-1499549, -769226, -6543440, -10011977, -26624, -14575885, -16728876, -11751600, -43230, -8825528, -6381922, -10453621, -16121, -16738680, -13286987, -5317};
                final int[] iArr2 = new int[]{-4056997, -2937054, -8708190, -11457112, -689152, -15108398, -16738393, -13070788, -1684967, -10665929, -10395295, -12232092, -24576, -16746133, -13615201, -278483};
                gridView.setAdapter(new C1584f(AppLockActivity.this, iArr, f9790n));
                gridView.setOnItemClickListener(new OnItemClickListener() {

                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        dialog.dismiss();
                        f9790n = iArr[i];
                        int i2 = iArr2[i];
                        f9779c.putInt("lockBgColor", f9790n);
                        f9779c.putInt("lockBgColorDark", i2);
                        f9779c.commit();
                        ObjectAnimator ofObject = ObjectAnimator.ofObject(f9792p, "backgroundColor", new ArgbEvaluator(), new Object[]{Integer.valueOf(f9778b.getInt("lockBgColor", getResources().getColor(R.color.lock_bg_color))), Integer.valueOf(f9790n)});
                        ofObject.setDuration(2000);
                        ofObject.start();
                        if (f9795s != null) {
                            f9795s.setStatusBarColor(f9790n);
                        }
                    }
                });
                dialog.setContentView(inflate);
                dialog.show();
            }
        });
        this.f9767Q = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        this.f9767Q.setAnimationListener(new AnimationListener() {

            public void onAnimationEnd(Animation animation) {
                f9781e.removeAllViews();
                f9771U = false;
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });

        findViewById(R.id.btnPattern).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                m14777i();
            }
        });
        registerReceiver(this.f9766P, new IntentFilter("ACTION_INTRUDER_SELFIE"));
        if (this.f9778b.getBoolean("isFakeCover", false)) {
            ViewStub viewStub = (ViewStub) findViewById(R.id.stub1);
            if (VERSION.SDK_INT >= 21) {
                this.f9795s = getWindow();
                this.f9795s.addFlags(Integer.MIN_VALUE);
                this.f9795s.setStatusBarColor(-16777216);
            }
            if (this.f9778b.getBoolean("isFakeCoverFinger", false)) {
                viewStub.setLayoutResource(R.layout.applock_fp_cover);
                final View inflate = viewStub.inflate();
                inflate.setClickable(true);
                this.f9765O = (ImageView) findViewById(R.id.ivFingerTouch);
                this.f9763M = (ImageView) findViewById(R.id.ivLineUp);
                this.f9764N = (ImageView) findViewById(R.id.ivLineDown);
                m14781m();
                this.f9765O.setOnTouchListener(new OnTouchListener() {

                    public boolean onTouch(View view, final MotionEvent motionEvent) {
                        if (motionEvent.getAction() == 0) {
                            long currentTimeMillis = System.currentTimeMillis();
                            f9801y = currentTimeMillis;
                            f9751A = currentTimeMillis;
                            if (f9801y - f9802z > 500) {
                                f9800x = false;
                            }
                            if (f9800x) {
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        if (motionEvent.getAction() == 0 || motionEvent.getAction() == 2) {
                                            inflate.setVisibility(View.GONE);
                                        }
                                    }
                                }, 500);
                            }
                        } else if (motionEvent.getAction() == 1) {
                            f9802z = System.currentTimeMillis();
                            if (!f9800x) {
                                f9799w = false;
                                f9800x = true;
                            } else if (System.currentTimeMillis() - f9801y >= 1000) {
                                f9799w = true;
                                f9800x = false;
                            } else {
                                f9800x = false;
                            }
                        }
                        return true;
                    }
                });
                return;
            }
            String str;
            final View inflate2 = viewStub.inflate();
            inflate2.setClickable(true);
            try {
                str = "" + getPackageManager().getApplicationInfo(this.f9784h ? WindowChangeDetectingService.f9920b : MyAppLockService.f9891d, 0).loadLabel(getPackageManager());
            } catch (NameNotFoundException e) {
                str = "App";
            }
            ((TextView) inflate2.findViewById(R.id.textView1)).setText("Unfortunately, " + str + " has stopped");
            final Button button = (Button) inflate2.findViewById(R.id.button1);
            button.post(new Runnable() {

                public void run() {
                    ah = (button.getWidth() * 45) / 100;
                }
            });
            button.setOnClickListener(new C30962(this));
            button.setOnTouchListener(new OnTouchListener() {

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case 0:
                            f9752B = (int) motionEvent.getX();
                            if (ah < 50) {
                                ah = 80;
                                break;
                            }
                            break;
                        case 1:
                            f9753C = (int) motionEvent.getX();
                            if (f9752B - f9753C <= ah) {
                                onBackPressed();
                                return false;
                            }
                            break;
                        case 2:
                            f9753C = (int) motionEvent.getX();
                            if (f9752B - f9753C >= ah) {
                                inflate2.setVisibility(View.GONE);
                                break;
                            }
                            break;
                    }
                    return true;
                }
            });
        }
    }

    protected void onDestroy() {
        if (this.f9782f != null) {
            this.f9782f.autoPause();
            this.f9782f.stop(this.ai);
            this.f9782f.release();
        }
        try {
            if (this.f9759I != null) {
                this.f9759I.stopPreview();
                this.f9759I.release();
                this.f9759I = null;
            }
            System.gc();
            unregisterReceiver(this.f9766P);
        } catch (Exception e) {
        }
        super.onDestroy();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        this.f9760J = false;
        overridePendingTransition(0, 0);
        super.onResume();
    }

    protected void onStop() {
        if (!this.f9760J) {
            finish();
        }
        super.onStop();
    }
}
