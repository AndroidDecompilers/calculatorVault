package calculator.vault.com.lock;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;

import calculator.vault.com.MainActivity11;

public class ManageSpaceActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String string = PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).getString("cmpName", MainActivity11.class.getName());
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(getPackageName(), string));
        intent.putExtra("fromBackGalleryLock", getIntent().getBooleanExtra("fromBackGalleryLock", false));
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}
