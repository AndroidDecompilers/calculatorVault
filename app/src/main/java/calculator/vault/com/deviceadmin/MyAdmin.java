package calculator.vault.com.deviceadmin;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyAdmin extends DeviceAdminReceiver {
    void m7297a(Context context, CharSequence charSequence) {
        Toast.makeText(context, charSequence, 0).show();
    }

    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "Disabling uninstall protection cause less secure Gallery Lock.";
    }

    public void onDisabled(Context context, Intent intent) {
        m7297a(context, "Uninstall Protection : disabled");
    }

    public void onEnabled(Context context, Intent intent) {
        m7297a(context, "Uninstall Protection : enabled");
    }

    public void onPasswordChanged(Context context, Intent intent) {
    }

    public void onPasswordFailed(Context context, Intent intent) {
    }

    public void onPasswordSucceeded(Context context, Intent intent) {
    }
}
