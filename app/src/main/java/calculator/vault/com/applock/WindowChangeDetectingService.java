package calculator.vault.com.applock;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.view.accessibility.AccessibilityEvent;

import java.util.HashSet;

import calculator.vault.com.p084i.C2928a;


public class WindowChangeDetectingService extends AccessibilityService {
    public static HashSet<String> f9919a = new HashSet();
    public static String f9920b;
    SharedPreferences f9921c;
    String f9922d = "";
    C3135a f9923e;
    private String f9924f = "";

    class C3135a extends BroadcastReceiver {
        final /* synthetic */ WindowChangeDetectingService f9918a;

        C3135a(WindowChangeDetectingService windowChangeDetectingService) {
            this.f9918a = windowChangeDetectingService;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {
                this.f9918a.m14819a();
            }
        }
    }

    public void m14819a() {
        f9919a = C2928a.m14118a(getApplicationContext()).m14119a();
        this.f9922d = "";
    }

    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        if (accessibilityEvent.getEventType() == 32) {
            String str = "" + accessibilityEvent.getPackageName();
            if (!str.equals(getPackageName()) && !this.f9921c.getBoolean("isFrozen", false)) {
                Intent intent = new Intent("android.intent.action.MAIN");
                intent.addCategory("android.intent.category.HOME");
                this.f9924f = getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY).activityInfo.packageName;
                if (str.equals(this.f9924f)) {
                    this.f9922d = "";
                    if (this.f9921c.getBoolean("immediately", true)) {
                        m14819a();
                        return;
                    }
                    return;
                }
                if (str.contains("systemui")) {
                    this.f9922d = "";
                }
                if (f9919a.contains(str) && !this.f9922d.equals(str)) {
                    f9920b = str;
                    this.f9922d = str;
                    Intent intent2 = null;
                    if (VERSION.SDK_INT >= 23) {
                        new Intent(getApplicationContext(), AppLockActivity23.class);
                    } else {
                        new Intent(getApplicationContext(), AppLockActivity.class);
                    }
                    intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent2.putExtra("fromAccess", true);
                    getApplicationContext().startActivity(intent2);
                }
            }
        }
    }

    public void onDestroy() {
        if (!SecurityHelpers.m14856d(getApplicationContext())) {
            Editor edit = this.f9921c.edit();
            edit.putBoolean("isAccess", false);
            edit.commit();
            startService(new Intent(getApplicationContext(), MyAppLockService.class));
        }
        try {
            if (this.f9923e.isOrderedBroadcast()) {
                unregisterReceiver(this.f9923e);
            }
        } catch (Exception e) {
        }
        super.onDestroy();
    }

    public void onInterrupt() {
    }

    @TargetApi(16)
    protected void onServiceConnected() {
        super.onServiceConnected();
        this.f9921c = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        m14819a();
        AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo();
        accessibilityServiceInfo.eventTypes = 32;
        accessibilityServiceInfo.feedbackType = 16;
        if (VERSION.SDK_INT >= 16) {
            accessibilityServiceInfo.flags = 2;
        }
        setServiceInfo(accessibilityServiceInfo);
        IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
        this.f9923e = new C3135a(this);
        registerReceiver(this.f9923e, intentFilter);
    }

    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
