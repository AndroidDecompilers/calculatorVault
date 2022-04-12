package calculator.vault.com.applock;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

import calculator.vault.com.R;

public class Temporary_view_Service extends Service {
    public WindowManager f9911a;
    public View f9912b;
    public int f9913c;
    DisplayMetrics f9914d;
    LayoutParams f9915e;
    int f9916f;
    int f9917g;

    class C31341 extends Thread {
        final /* synthetic */ Temporary_view_Service f9910a;

        class C31331 implements Runnable {
            final /* synthetic */ C31341 f9909a;

            C31331(C31341 c31341) {
                this.f9909a = c31341;
            }

            public void run() {
                this.f9909a.f9910a.stopService(new Intent(this.f9909a.f9910a.getApplicationContext(), Temporary_view_Service.class));
            }
        }

        C31341(Temporary_view_Service temporary_view_Service) {
            this.f9910a = temporary_view_Service;
        }

        public void run() {
            try {
                C31341.sleep(3500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                this.f9910a.f9912b.post(new C31331(this));
            }
        }
    }

    private void m14816b() {
        this.f9914d = new DisplayMetrics();
        this.f9911a.getDefaultDisplay().getMetrics(this.f9914d);
        this.f9916f = this.f9914d.widthPixels;
        this.f9917g = this.f9914d.heightPixels;
        this.f9913c = getResources().getDimensionPixelSize(R.dimen.bar_size);
        this.f9915e = new LayoutParams();
        this.f9912b = LayoutInflater.from(this).inflate(R.layout.temporary_nav_bar, null);
        ((TextView) this.f9912b.findViewById(R.id.textView1)).setText(C3150b.m14827a(getResources().getString(R.string.tempvalue)));
        this.f9915e.width = -1;
        this.f9915e.height = -2;
        this.f9915e.x = 0;
        this.f9915e.y = 0;
        this.f9915e.type = 2006;
        this.f9915e.flags = 512;
        this.f9915e.format = -2;
        this.f9915e.gravity = 80;
        this.f9911a.addView(this.f9912b, this.f9915e);
        new C31341(this).start();
    }

    private void m14817c() {
        this.f9911a.removeView(this.f9912b);
    }

    public void m14818a() {
        this.f9911a = (WindowManager) getSystemService(WINDOW_SERVICE);
        try {
            m14816b();
        } catch (SecurityException e) {
        }
    }

    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void onCreate() {
        super.onCreate();
        m14818a();
    }

    public void onDestroy() {
        super.onDestroy();
        try {
            m14817c();
        } catch (SecurityException e) {
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return START_NOT_STICKY;
    }
}
