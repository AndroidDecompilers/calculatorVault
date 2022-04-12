package calculator.vault.com.p034a;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore.Video.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import calculator.vault.com.R;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.p068a.C1625q;
import calculator.vault.com.p084i.C2930c;

public class C1213d extends AsyncTask<Void, Integer, Void> {
    Activity f3531a;
    C1096a f3532b;
    ArrayList<String> f3533c;
    File f3534d;
    File f3535e;
    TextView f3536f;
    int f3537g = 1;
    int f3538h;
    String f3539i;
    C2930c f3540j;
    TextView f3541k;
    TextView f3542l;
    boolean f3543m;
    boolean f3544n;
    Button f3545o;
    File f3546p;
    ArrayList<String> f3547q = new ArrayList();
    C1625q f3548r;
    View f3549s;
    boolean f3550t;
    boolean f3551u;
    Dialog f3552v;
    ProgressBar f3553w;
    int f3554x = -1;

    public interface C1096a {
        void mo970a();

        void mo972a(String str);

        void mo973a(ArrayList<String> arrayList);
    }

    class C12051 implements OnClickListener {
        final /* synthetic */ C1213d f3523a;

        C12051(C1213d c1213d) {
            this.f3523a = c1213d;
        }

        public void onClick(View view) {
            this.f3523a.f3544n = true;
        }
    }

    class C12062 implements OnClickListener {
        final /* synthetic */ C1213d f3524a;

        C12062(C1213d c1213d) {
            this.f3524a = c1213d;
        }

        public void onClick(View view) {
        }
    }

    class C12083 implements OnClickListener {
        final /* synthetic */ C1213d f3526a;

        class C12071 implements AnimationListener {
            final /* synthetic */ C12083 f3525a;

            C12071(C12083 c12083) {
                this.f3525a = c12083;
            }

            public void onAnimationEnd(Animation animation) {
                this.f3525a.f3526a.f3549s.setVisibility(View.GONE);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        }

        C12083(C1213d c1213d) {
            this.f3526a = c1213d;
        }

        public void onClick(View view) {
            Animation loadAnimation = AnimationUtils.loadAnimation(this.f3526a.f3531a, R.anim.adview_shrink);
            loadAnimation.setAnimationListener(new C12071(this));
            this.f3526a.f3549s.startAnimation(loadAnimation);
        }
    }

    class C12094 implements Runnable {
        final /* synthetic */ C1213d f3527a;

        C12094(C1213d c1213d) {
            this.f3527a = c1213d;
        }

        public void run() {
            this.f3527a.f3536f.setText(this.f3527a.f3537g + "/" + this.f3527a.f3538h);
            C1213d c1213d = this.f3527a;
            c1213d.f3537g++;
        }
    }

    class C12105 implements Runnable {
        final /* synthetic */ C1213d f3528a;

        C12105(C1213d c1213d) {
            this.f3528a = c1213d;
        }

        public void run() {
            this.f3528a.f3536f.setText("1/1");
        }
    }

    class C12116 implements Runnable {
        final /* synthetic */ C1213d f3529a;

        C12116(C1213d c1213d) {
            this.f3529a = c1213d;
        }

        public void run() {
            C1131f.m5804a(this.f3529a.f3531a, "Error moving file");
            try {
                if (this.f3529a.f3552v != null && this.f3529a.f3552v.isShowing()) {
                    this.f3529a.f3552v.dismiss();
                }
            } catch (Exception e) {
            }
        }
    }

    class C12127 implements OnClickListener {
        final /* synthetic */ C1213d f3530a;

        C12127(C1213d c1213d) {
            this.f3530a = c1213d;
        }

        public void onClick(View view) {
            this.f3530a.f3542l.setText("One video was moved to secret locker");
            if (this.f3530a.f3552v != null && this.f3530a.f3552v.isShowing()) {
                this.f3530a.f3552v.dismiss();
            }
            this.f3530a.f3544n = false;
            this.f3530a.f3532b.mo973a(this.f3530a.f3547q);
        }
    }

    public C1213d(Activity activity, File file, File file2, boolean z, C1096a c1096a, boolean z2) {
        this.f3543m = z2;
        this.f3550t = true;
        this.f3534d = file;
        this.f3535e = file2;
        this.f3551u = z;
        this.f3531a = activity;
        this.f3532b = c1096a;
        this.f3540j = new C2930c(activity);
    }

    private void m6045a(File file, File file2) {
        try {
            InputStream fileInputStream = new FileInputStream(file);
            OutputStream fileOutputStream = new FileOutputStream(file2);
            this.f3546p = file2;
            byte[] bArr = new byte[1024];
            this.f3554x = -1;
            long j = 0;
            int available = fileInputStream.available();
            publishProgress(new Integer[]{Integer.valueOf(0)});
            do {
                int read = fileInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                j += (long) read;
                if (available > 0) {
                    publishProgress(new Integer[]{Integer.valueOf((int) ((100 * j) / ((long) available)))});
                }
                fileOutputStream.write(bArr, 0, read);
            } while (!this.f3544n);
            fileOutputStream.flush();
            fileOutputStream.close();
            fileInputStream.close();
            this.f3546p.delete();
            if (!this.f3544n) {
                fileOutputStream.flush();
                fileOutputStream.close();
                fileInputStream.close();
                if (this.f3543m) {
                    this.f3547q.add(file.getAbsolutePath());
                } else if (file.delete()) {
                    C1131f.m5810b(this.f3531a, file, "video/*");
                } else if (FileUtils.deleteQuietly(file)) {
                    C1131f.m5810b(this.f3531a, file, "video/*");
                }
                this.f3540j.m14130a(file2.getName(), !this.f3551u ? file.getParent() : Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Camera");
                this.f3532b.mo972a(file2.getAbsolutePath());
            }
        } catch (IOException e) {
            this.f3531a.runOnUiThread(new C12116(this));
            e.printStackTrace();
        }
    }

    protected Void doInBackground(Void... voidArr) {
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        if (this.f3550t) {
            this.f3531a.runOnUiThread(new C12105(this));
            m6045a(this.f3534d, this.f3535e);
        } else {
            Iterator it = this.f3533c.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                String substring = str.substring(str.lastIndexOf(47) + 1, str.length());
                File file = new File(str);
                File file2 = new File(this.f3539i + "/" + substring);
                this.f3531a.runOnUiThread(new C12094(this));
                m6045a(file, file2);
            }
        }
        return null;
    }

    protected void onPostExecute(Void voidR) {
        this.f3545o.setText("DONE");
        this.f3545o.setOnClickListener(new C12127(this));
        if (this.f3551u) {
            Cursor query = this.f3531a.getContentResolver().query(Media.EXTERNAL_CONTENT_URI, new String[]{"_id", "_data", "bucket_display_name", "datetaken", "mime_type"}, null, null, "datetaken DESC");
            if (query != null) {
                query.moveToFirst();
                String string = query.getString(query.getColumnIndexOrThrow("_data"));
                if (((int) (TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis()) - TimeUnit.MILLISECONDS.toMinutes(new File(string).lastModified()))) < 5) {
                    new File(string).delete();
                    C1131f.m5810b(this.f3531a, new File(string), "video/*");
                }
            }
        }
        super.onPostExecute(voidR);
    }

    protected void onProgressUpdate(Integer... numArr) {
        int intValue = numArr[0].intValue();
        if (intValue - this.f3554x > 0) {
            this.f3553w.setProgress(intValue);
            this.f3541k.setText(intValue + "%");
            this.f3554x = intValue;
        }
        super.onProgressUpdate(numArr);
    }


    protected void onPreExecute() {
        this.f3552v = new ProgressDialog(this.f3531a);
        View inflate = this.f3531a.getLayoutInflater().inflate(R.layout.progress_dialog, null);
        this.f3552v.show();
        this.f3552v.setContentView(inflate);
        this.f3552v.setCancelable(false);
        this.f3542l = (TextView) inflate.findViewById(R.id.tvTitle);
        this.f3542l.setTypeface(C1131f.f3315a);
        this.f3553w = (ProgressBar) inflate.findViewById(R.id.progressBar1);
        this.f3541k = (TextView) inflate.findViewById(R.id.tvProgress);
        this.f3536f = (TextView) inflate.findViewById(R.id.tvCount);
        if (this.f3550t) {
            this.f3536f.setText("1/1");
        } else {
            this.f3538h = this.f3533c.size();
            this.f3536f.setText("1/" + this.f3538h);
        }
        this.f3532b.mo970a();
        this.f3545o = (Button) inflate.findViewById(R.id.btnCancel);
        this.f3545o.setOnClickListener(new C12051(this));
        if (!(this.f3548r == null || this.f3543m)) {
            this.f3549s = inflate.findViewById(R.id.llAd);
            this.f3549s.setVisibility(View.VISIBLE);
            Glide.with(this.f3531a).load(this.f3548r.f4627d).into((ImageView) inflate.findViewById(R.id.ivAd));
            Glide.with(this.f3531a).load(this.f3548r.f4628e).into((ImageView) inflate.findViewById(R.id.ivAdIcon));
            ((TextView) inflate.findViewById(R.id.tvAppName)).setText(this.f3548r.f4624a);
            ((TextView) inflate.findViewById(R.id.tvDesc)).setText(this.f3548r.f4626c);
            inflate.findViewById(R.id.btnInstall).setOnClickListener(new C12062(this));
            inflate.findViewById(R.id.btnCloseAd).setOnClickListener(new C12083(this));
        }
        super.onPreExecute();
    }

}
