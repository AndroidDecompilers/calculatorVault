package calculator.vault.com.applock;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.usage.UsageEvents;
import android.app.usage.UsageEvents.Event;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.graphics.BitmapFactory.Options;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.support.annotation.RequiresApi;
import android.telephony.TelephonyManager;
import android.text.TextUtils.SimpleStringSplitter;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;

public class SecurityHelpers {
    public static String f9999a = "calc.applock.refreshList";
    public static String f10000b = "calc.applock.stopself";
    public static String f10001c = "calc.applock.removeapp";
    public static LinkedHashMap<String, Integer> f10002d = new C31531();
    public static LinkedHashMap<String, Integer> f10003e = new C31542();

    static class C31531 extends LinkedHashMap<String, Integer> {
        C31531() {
            put("com.google.android.gm", Integer.valueOf(-2406855));
            put("com.android.chrome", Integer.valueOf(-2339781));
            put("com.android.settings", Integer.valueOf(-11903128));
            put("com.estrongs.android.pop", Integer.valueOf(-13721875));
            put("com.asus.filemanager", Integer.valueOf(-9659));
            put("com.google.android.apps.photos", Integer.valueOf(-283116));
            put("com.android.vending", Integer.valueOf(-10634517));
            put("com.google.android.apps.plus", Integer.valueOf(-2273212));
            put("com.google.android.talk", Integer.valueOf(-15228834));
        }
    }

    static class C31542 extends LinkedHashMap<String, Integer> {
        C31542() {
            put("com.whatsapp", Integer.valueOf(-13584316));
            put("com.facebook.katana", Integer.valueOf(-12887656));
            put("com.tencent.mm", Integer.valueOf(-16723443));
            put("com.facebook.orca", Integer.valueOf(-16743169));
            put("com.viber.voip", Integer.valueOf(-7381577));
            put("jp.naver.line.android", Integer.valueOf(-16727296));
            put("com.imo.android.imoim", Integer.valueOf(-14722642));
            put("com.google.android.apps.messaging", Integer.valueOf(-16733193));
            put("com.instagram.android", Integer.valueOf(-2019220));
            put("com.snapchat.android", Integer.valueOf(-1024));
            put("com.twitter.android", Integer.valueOf(-11162386));
            put("org.telegram.messenger", Integer.valueOf(-16742196));
            put("com.skype.raider", Integer.valueOf(-16732691));
            put("com.bsb.hike", Integer.valueOf(-12733957));
            put("com.nimbuzz", Integer.valueOf(-25572));
            put("com.tinder", Integer.valueOf(-957340));
            put("com.bbm", Integer.valueOf(-12500671));
        }
    }

    public static float m14847a(float f, Context context) {
        return (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f) * f;
    }

    public static int m14848a(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            i3 /= 2;
            i4 /= 2;
            while (i3 / i5 > i2 && i4 / i5 > i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static String m14849a(UsageStatsManager usageStatsManager) {
        String str = null;
        long currentTimeMillis = System.currentTimeMillis() + 5000;
        UsageEvents queryEvents = usageStatsManager.queryEvents(currentTimeMillis - 100000, currentTimeMillis);
        Event event = new Event();
        while (queryEvents.hasNextEvent()) {
            queryEvents.getNextEvent(event);
            if (event.getEventType() == 1) {
                str = event.getPackageName();
            }
        }
        return str;
    }

    public static String m14850a(UsageStatsManager usageStatsManager, Context context) {
        return VERSION.SDK_INT >= 21 ? SecurityHelpers.m14849a(usageStatsManager) : SecurityHelpers.getTopOld(context);
    }

    public static String getTopOld(Context context) {
        List runningTasks = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1);
        return runningTasks != null ? ((RunningTaskInfo) runningTasks.get(0)).topActivity.getPackageName() : null;
    }

    @TargetApi(20)
    public static boolean isScreenON(PowerManager powerManager) {
        return (VERSION.SDK_INT >= 20 && powerManager.isInteractive()) || (VERSION.SDK_INT < 20 && powerManager.isScreenOn());
    }

    public static boolean isCallRinging(TelephonyManager telephonyManager) {
        return telephonyManager.getCallState() == TelephonyManager.CALL_STATE_RINGING;
    }

    public static String m14854b(Context context) {
        return VERSION.SDK_INT >= 21 ? SecurityHelpers.getTopNew(context) : SecurityHelpers.getTopOld(context);
    }

    public static String getTopNew(Context context) {
        Field declaredField;
        try {
            declaredField = RunningAppProcessInfo.class.getDeclaredField("processState");
        } catch (Exception e) {
            declaredField = null;
        }
        try {
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.importanceReasonCode == 0) {
                    Integer valueOf;
                    try {
                        valueOf = Integer.valueOf(declaredField.getInt(runningAppProcessInfo));
                    } catch (Exception e2) {
                        valueOf = null;
                    }
                    if (valueOf == null) {
                        continue;
                    } else if (valueOf.intValue() == 2) {
                        return runningAppProcessInfo.processName;
                    }
                }

            }
        } catch (Exception e3) {
            return "";
        }
        return "";
    }

    public static boolean m14856d(Context context) {
        int i;
        String str = context.getPackageName() + "/" + WindowChangeDetectingService.class.getCanonicalName();
        try {
            i = Secure.getInt(context.getApplicationContext().getContentResolver(), "accessibility_enabled");
        } catch (SettingNotFoundException e) {
            i = 0;
        }
        SimpleStringSplitter simpleStringSplitter = new SimpleStringSplitter(':');
        if (i != 1) {
            return false;
        }
        String string = Secure.getString(context.getApplicationContext().getContentResolver(), "enabled_accessibility_services");
        if (string == null) {
            return false;
        }
        simpleStringSplitter.setString(string);
        while (simpleStringSplitter.hasNext()) {
            if (simpleStringSplitter.next().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
