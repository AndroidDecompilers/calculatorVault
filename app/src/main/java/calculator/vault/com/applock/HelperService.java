package calculator.vault.com.applock;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class HelperService extends Service {
    public static void m14804a(Context context) {
        context.startService(new Intent(context, HelperService.class));
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        stopForeground(true);
        stopSelf();
        return START_NOT_STICKY;
    }
}
