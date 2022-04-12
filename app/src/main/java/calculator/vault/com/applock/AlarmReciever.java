package calculator.vault.com.applock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class AlarmReciever extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor edit = defaultSharedPreferences.edit();
        edit.putBoolean("startApplock", true);
        edit.putBoolean("isFrozen", false);
        edit.commit();
        if (!defaultSharedPreferences.getBoolean("isAccess", false)) {
            context.startService(new Intent(context, MyAppLockService.class));
            context.sendBroadcast(new Intent(SecurityHelpers.f9999a));
        }
    }
}
