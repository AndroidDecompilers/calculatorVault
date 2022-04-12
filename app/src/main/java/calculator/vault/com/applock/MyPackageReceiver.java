package calculator.vault.com.applock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;

import calculator.vault.com.p084i.C2928a;

public class MyPackageReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if ("android.intent.action.PACKAGE_REPLACED".equals(intent.getAction()) && intent.getData().getSchemeSpecificPart().equals(context.getPackageName())) {
            Editor edit = defaultSharedPreferences.edit();
            if (defaultSharedPreferences.getBoolean("isFrozen", false)) {
                edit.putBoolean("startApplock", true);
                edit.putBoolean("isFrozen", false);
                edit.commit();
            }
            if (defaultSharedPreferences.getBoolean("startApplock", false) && !defaultSharedPreferences.getBoolean("isAccess", false)) {
                context.startService(new Intent(context, MyAppLockService.class));
                context.sendBroadcast(new Intent(SecurityHelpers.f9999a));
            }
        } else if (!defaultSharedPreferences.getBoolean("instaLock", false)) {
        } else {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                if (defaultSharedPreferences.getBoolean("startApplock", false)) {
                    Bundle extras = intent.getExtras();
                    if (extras == null || !extras.containsKey("android.intent.extra.REPLACING") || !extras.getBoolean("android.intent.extra.REPLACING")) {
                        String encodedSchemeSpecificPart = intent.getData().getEncodedSchemeSpecificPart();
                        if (encodedSchemeSpecificPart != null && encodedSchemeSpecificPart.length() > 1) {
                            Intent intent2 = new Intent(context, NewAppActivity.class);
                            intent2.putExtra("packName", encodedSchemeSpecificPart);
                            intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent2);
                        }
                    }
                }
            } else if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                C2928a.m14118a(context).m14120a(intent.getData().getEncodedSchemeSpecificPart());
                context.sendBroadcast(new Intent(SecurityHelpers.f9999a));
            }
        }
    }
}
