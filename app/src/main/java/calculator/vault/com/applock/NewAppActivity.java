package calculator.vault.com.applock;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;;import calculator.vault.com.R;
import calculator.vault.com.p084i.C2928a;

@TargetApi(21)
public class NewAppActivity extends AppCompatActivity {

    class C31311 implements OnClickListener {
        final /* synthetic */ NewAppActivity f9905a;

        C31311(NewAppActivity newAppActivity) {
            this.f9905a = newAppActivity;
        }

        public void onClick(View view) {
            this.f9905a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        ApplicationInfo applicationInfo;
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_new_app);
        if (VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
        }
        final String stringExtra = getIntent().getStringExtra("packName");
        PackageManager packageManager = getApplicationContext().getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(stringExtra, 0);
        } catch (NameNotFoundException e) {
            applicationInfo = null;
        }
        final SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ((TextView) findViewById(R.id.tvAppName)).setText("Lock " + ((String) (applicationInfo != null ? packageManager.getApplicationLabel(applicationInfo) : "(unknown)")) + "?");
        findViewById(R.id.rlCancel).setOnClickListener(new C31311(this));
        findViewById(R.id.rlLock).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                C2928a.m14118a(getApplicationContext()).m14121a(stringExtra, 1);
                if (!defaultSharedPreferences.getBoolean("isAccess", false)) {
                    sendBroadcast(new Intent(SecurityHelpers.f9999a));
                } else if (WindowChangeDetectingService.f9919a != null) {
                    WindowChangeDetectingService.f9919a.add(stringExtra);
                }
            }
        });
    }

    protected void onDestroy() {
        overridePendingTransition(0, 0);
        super.onDestroy();
    }

    protected void onPause() {
        overridePendingTransition(0, 0);
        super.onPause();
    }

    protected void onResume() {
        overridePendingTransition(0, 0);
        super.onResume();
    }
}
