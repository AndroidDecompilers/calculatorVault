package calculator.vault.com.applock;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.Service;
import android.app.usage.UsageStatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.PowerManager;
import android.preference.PreferenceManager;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

import calculator.vault.com.p084i.C2928a;

public class MyAppLockService extends Service {

    public static HashSet<String> f9890c;
    public static String f9891d;
    public static int f9892k = VERSION.SDK_INT;
    private static boolean f9893n;
    ActivityManager f9894a;
    PowerManager f9895b;
    SharedPreferences f9896e;
    public boolean f9897f = true;
    Timer f9898g;
    TimerTask f9899h;
    BroadcastReceiver f9900i;
    UsageStatsManager f9901j;
    String f9902l;
    boolean f9903m;
    private boolean f9904o;

    class C31291 extends BroadcastReceiver {
        final /* synthetic */ MyAppLockService f9888a;

        C31291(MyAppLockService myAppLockService) {
            this.f9888a = myAppLockService;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(SecurityHelpers.f9999a)) {
                this.f9888a.m14815a();
            } else if (intent.getAction().equals(SecurityHelpers.f10000b)) {
                this.f9888a.m14814e();
            } else if (intent.getAction().equals(SecurityHelpers.f10001c)) {
                this.f9888a.m14807a(intent.getStringExtra("packName"));
            }
        }
    }

    class C31302 extends TimerTask {
        final /* synthetic */ MyAppLockService f9889a;

        C31302(MyAppLockService myAppLockService) {
            this.f9889a = myAppLockService;
        }

        public void run() {
            if (SecurityHelpers.isScreenON(this.f9889a.f9895b)) {
                this.f9889a.f9904o = false;
                String str = "";
                try {
                    str = SecurityHelpers.m14850a(this.f9889a.f9901j, this.f9889a.getApplicationContext());
                } catch (Exception e) {
                    str = "";
                }
                if (str != null && !str.equals(this.f9889a.getPackageName())) {
                    if (MyAppLockService.f9893n && str.equals(this.f9889a.f9902l)) {
                        if (this.f9889a.f9896e.getBoolean("immediately", true)) {
                            MyAppLockService.f9890c = C2928a.m14118a(this.f9889a.getApplicationContext()).m14119a();
                        }
                        MyAppLockService.f9893n = false;
                    }
                    if (!str.equals(this.f9889a.f9902l) && MyAppLockService.f9890c.contains(str)) {
                        MyAppLockService.f9891d = str;
                        Intent intent = null;
                        if (VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            intent = new Intent(this.f9889a.getApplicationContext(), AppLockActivity23.class);
                        } else {
                            intent = new Intent(this.f9889a.getApplicationContext(), AppLockActivity.class);
                        }
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        this.f9889a.getApplicationContext().startActivity(intent);
                        MyAppLockService.f9893n = true;
                    }
                }
            } else if (!this.f9889a.f9904o) {
                this.f9889a.m14815a();
            }
        }
    }

    private void m14807a(String str) {
        f9890c.remove(str);
    }


    private void m14813d() {
        if (f9892k < 25) {
            HelperService.m14804a(this);
        }
    }

    private void m14814e() {
        this.f9903m = true;
        try {
            unregisterReceiver(this.f9900i);
        } catch (Exception e) {
        }
        stopForeground(true);
        stopSelf();
    }

    public void m14815a() {
        f9890c = C2928a.m14118a(getApplicationContext()).m14119a();
        if (f9890c.size() == 0) {
            m14814e();
        }
        this.f9904o = true;
        if (this.f9896e.getBoolean("isAccess", false)) {
            m14814e();
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        this.f9896e = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.f9894a = (ActivityManager) getApplicationContext().getSystemService(ACTIVITY_SERVICE);
        this.f9895b = (PowerManager) getApplicationContext().getSystemService(POWER_SERVICE);
        if (f9892k >= 21) {
            this.f9901j = (UsageStatsManager) getApplicationContext().getSystemService(USAGE_STATS_SERVICE);
        }
        m14813d();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        ResolveInfo resolveActivity = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveActivity == null) {
            this.f9902l = "com.sec.android.app.launcher";
        } else {
            this.f9902l = resolveActivity.activityInfo.packageName;
        }
        this.f9900i = new C31291(this);
        IntentFilter intentFilter = new IntentFilter(SecurityHelpers.f9999a);
        intentFilter.addAction(SecurityHelpers.f10000b);
        intentFilter.addAction(SecurityHelpers.f10001c);
        registerReceiver(this.f9900i, intentFilter);
        m14815a();
        if (this.f9896e.getBoolean("isAccess", false)) {
            m14814e();
        }
        this.f9899h = new C31302(this);
        this.f9898g = new Timer();
        this.f9898g.schedule(this.f9899h, 200, 200);
        super.onCreate();
    }

    public void onDestroy() {
        try {
            this.f9898g.cancel();
            this.f9899h.cancel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!this.f9903m) {
            sendBroadcast(new Intent("sure.unstoppable.service"));
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return START_STICKY;
    }
}
