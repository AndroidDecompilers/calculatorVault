package calculator.vault.com.lock;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;

import com.crashlytics.android.Crashlytics;
import com.mobiburn.Mobiburn;

import io.fabric.sdk.android.Fabric;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;

public class MyApplication extends Application {
    private static MyApplication f2936e;
    public SharedPreferences f2937a;
    OkHttpClient f2938b;

    public static synchronized MyApplication m5640a() {
        return f2936e;
    }

    public void onCreate() {
        boolean z = true;
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        f2936e = this;
        this.f2938b = new Builder().build();
        C1131f.f3323i = VERSION.SDK_INT >= 21;
        if (VERSION.SDK_INT != 19) {
            z = false;
        }
        C1131f.f3325k = z;
        this.f2937a = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }
}
