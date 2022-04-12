package calculator.vault.com.applock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class SureBootReciever extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        Editor edit = defaultSharedPreferences.edit();
        if (defaultSharedPreferences.getBoolean("isFrozen", false)) {
            edit.putBoolean("isFrozen", false);
            edit.putBoolean("startApplock", true);
            edit.commit();
        }
        if (defaultSharedPreferences.getBoolean("startApplock", true) && !defaultSharedPreferences.getBoolean("isAccess", false)) {
            context.startService(new Intent(context, MyAppLockService.class));
        }
    }
}
