package calculator.vault.com.lock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

import calculator.vault.com.R;
import calculator.vault.com.p029h.C1037g;
import calculator.vault.com.p034a.C1204c;
import calculator.vault.com.p034a.C1226f;

public class ShareFromOutsideActivity extends Activity {
    String f2994a;
    private boolean f2995b;

    class C10381 implements C1037g {
        final /* synthetic */ ShareFromOutsideActivity f2992a;

        C10381(ShareFromOutsideActivity shareFromOutsideActivity) {
            this.f2992a = shareFromOutsideActivity;
        }

        public void mo962a() {
            C1131f.m5804a(this.f2992a, "Video(s) are locked to InstaSave folder inside locker");
            this.f2992a.setResult(-1);
            this.f2992a.finish();
        }

        public void mo963a(String str) {
            Toast.makeText(this.f2992a.getApplicationContext(), "Error hiding videos,try again", Toast.LENGTH_LONG).show();
            this.f2992a.finish();
        }
    }

    class C10392 implements C1037g {
        final /* synthetic */ ShareFromOutsideActivity f2993a;

        C10392(ShareFromOutsideActivity shareFromOutsideActivity) {
            this.f2993a = shareFromOutsideActivity;
        }

        public void mo962a() {
            C1131f.m5804a(this.f2993a, "Photo(s) are locked to InstaSave folder inside locker");
            Intent intent = new Intent();
            intent.putExtra("moved", true);
            this.f2993a.setResult(-1, intent);
            this.f2993a.finish();
        }

        public void mo963a(String str) {
            Toast.makeText(this.f2993a.getApplicationContext(), "Error hiding photos", Toast.LENGTH_LONG).show();
            this.f2993a.finish();
        }
    }

    private void m5672a() {
        this.f2994a = getFilesDir() + "/lockerVault/Images1769/InstaSave";
        if (!new File(this.f2994a).exists()) {
            new File(this.f2994a).mkdirs();
        }
    }

    private void m5673a(Intent intent) {
        ArrayList stringArrayListExtra = intent.getStringArrayListExtra("android.intent.extra.STREAM");
        if (stringArrayListExtra != null && !stringArrayListExtra.isEmpty()) {
            m5674a(stringArrayListExtra);
        }
    }

    private void m5674a(ArrayList<String> arrayList) {
        new C1226f(this, arrayList, new C10381(this), this.f2995b).execute(new Void[0]);
    }

    private void m5675b() {
        this.f2994a = getFilesDir() + "/lockerVault/Videos1769/InstaSave";
        if (!new File(this.f2994a).exists()) {
            new File(this.f2994a).mkdirs();
        }
    }

    private void m5676b(Intent intent) {
        ArrayList stringArrayListExtra = intent.getStringArrayListExtra("android.intent.extra.STREAM");
        if (stringArrayListExtra != null) {
            m5677b(stringArrayListExtra);
        }
    }

    private void m5677b(ArrayList<String> arrayList) {
        new C1204c(this, arrayList, new C10392(this), this.f2995b).execute(new Void[0]);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_share_import);
        Intent intent = getIntent();
        this.f2995b = intent.getBooleanExtra("doCut", true);
        String action = intent.getAction();
        String stringExtra = intent.getStringExtra("type");
        if ("ACTION_INSTA_SHARE_ACTIVITY".equals(action) && stringExtra != null) {
            if (stringExtra.equals("insta_pics")) {
                m5672a();
                m5676b(intent);
            } else if (stringExtra.equals("insta_vids")) {
                m5675b();
                m5673a(intent);
            }
        }
    }
}
