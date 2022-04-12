package calculator.vault.com.p034a;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import calculator.vault.com.R;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.p029h.C1037g;
import calculator.vault.com.p084i.C2930c;

public class C1204c extends AsyncTask<Void, Integer, Boolean> {
    ProgressDialog f3510a;
    ArrayList<String> f3511b;
    Activity f3512c;
    C1037g f3513d;
    String f3514e;
    TextView f3515f;
    TextView f3516g;
    int f3517h;
    ProgressBar f3518i;
    int f3519j = 1;
    C2930c f3520k;
    boolean f3521l;
    int f3522m = -1;

    class C12021 implements Runnable {
        final /* synthetic */ C1204c f3508a;

        C12021(C1204c c1204c) {
            this.f3508a = c1204c;
        }

        public void run() {
            this.f3508a.f3515f.setText(this.f3508a.f3519j + "/" + this.f3508a.f3517h);
        }
    }

    class C12032 implements Runnable {
        final /* synthetic */ C1204c f3509a;

        C12032(C1204c c1204c) {
            this.f3509a = c1204c;
        }

        public void run() {
            C1131f.m5804a(this.f3509a.f3512c, "Error hiding files");
        }
    }

    public C1204c(Activity activity, ArrayList<String> arrayList, C1037g c1037g, boolean z) {
        this.f3512c = activity;
        this.f3513d = c1037g;
        this.f3520k = new C2930c(activity);
        this.f3521l = z;
        this.f3511b = arrayList;
    }

    private void m6041a(File file, File file2) throws IOException {
        InputStream fileInputStream = new FileInputStream(file);
        OutputStream fileOutputStream = new FileOutputStream(file2);
        byte[] bArr = new byte[1024];
        this.f3522m = -1;
        long j = 0;
        int available = fileInputStream.available();
        publishProgress(new Integer[]{Integer.valueOf(0)});
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read == -1) {
                break;
            }
            j += (long) read;
            if (available > 0) {
                publishProgress(new Integer[]{Integer.valueOf((int) ((100 * j) / ((long) available)))});
            }
            fileOutputStream.write(bArr, 0, read);
        }
        fileOutputStream.flush();
        fileOutputStream.close();
        fileInputStream.close();
        this.f3520k.m14130a(file2.getName(), file.getParent());
        if (this.f3521l) {
            if (!file.delete()) {
                FileUtils.deleteQuietly(file);
            }
            C1131f.m5810b(this.f3512c, file, "image/*");
        }
    }

    protected Boolean doInBackground(Void... voidArr) {
        String str = this.f3512c.getFilesDir() + "/lockerVault/Images1769/InstaSave/";
        Iterator it = this.f3511b.iterator();
        while (it.hasNext()) {
            File file = new File((String) it.next());
            String name = file.getName();
            try {
                m6041a(file, new File(str + name));
                this.f3519j++;
                this.f3512c.runOnUiThread(new C12021(this));
                this.f3520k.m14130a(name, file.getParent());
            } catch (Exception e) {
                try {
                    this.f3512c.runOnUiThread(new C12032(this));
                    if (this.f3510a != null && this.f3510a.isShowing()) {
                        this.f3510a.dismiss();
                    }
                } catch (Exception e2) {
                }
                e.printStackTrace();
                return Boolean.valueOf(false);
            }
        }
        return Boolean.valueOf(true);
    }

    protected void onPostExecute(Boolean bool) {
        try {
            if (this.f3510a != null && this.f3510a.isShowing()) {
                this.f3510a.dismiss();
            }
        } catch (Exception e) {
        }
        if (bool.booleanValue()) {
            this.f3513d.mo962a();
        } else {
            this.f3513d.mo963a(this.f3514e);
        }
        super.onPostExecute(bool);
    }

    protected void onProgressUpdate(Integer... numArr) {
        int intValue = numArr[0].intValue();
        if (intValue - this.f3522m > 0) {
            this.f3518i.setProgress(intValue);
            this.f3516g.setText(intValue + "%");
            this.f3522m = intValue;
        }
        super.onProgressUpdate(numArr);
    }

    protected void onPreExecute() {
        this.f3510a = new ProgressDialog(this.f3512c);
        View inflate = this.f3512c.getLayoutInflater().inflate(R.layout.progress_dialog, null);
        this.f3510a.show();
        this.f3510a.setContentView(inflate);
        this.f3510a.setCancelable(false);
        this.f3518i = (ProgressBar) inflate.findViewById(R.id.progressBar1);
        this.f3515f = (TextView) inflate.findViewById(R.id.tvCount);
        this.f3516g = (TextView) inflate.findViewById(R.id.tvProgress);
        this.f3517h = this.f3511b.size();
        this.f3515f.setText("1/" + this.f3517h);
        super.onPreExecute();
    }
}
