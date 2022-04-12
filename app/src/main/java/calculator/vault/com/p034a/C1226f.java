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

public class C1226f extends AsyncTask<Void, Integer, Boolean> {
    ProgressDialog f3592a;
    ArrayList<String> f3593b;
    Activity f3594c;
    C2930c f3595d;
    C1037g f3596e;
    String f3597f;
    ProgressBar f3598g;
    TextView f3599h;
    TextView f3600i;
    int f3601j;
    boolean f3602k;
    int f3603l = 1;
    int f3604m = -1;

    class C12241 implements Runnable {
        final /* synthetic */ C1226f f3590a;

        C12241(C1226f c1226f) {
            this.f3590a = c1226f;
        }

        public void run() {
            this.f3590a.f3599h.setText(this.f3590a.f3603l + "/" + this.f3590a.f3601j);
        }
    }

    class C12252 implements Runnable {
        final /* synthetic */ C1226f f3591a;

        C12252(C1226f c1226f) {
            this.f3591a = c1226f;
        }

        public void run() {
            C1131f.m5804a(this.f3591a.f3594c, "Error moving files");
        }
    }

    public C1226f(Activity activity, ArrayList<String> arrayList, C1037g c1037g, boolean z) {
        this.f3593b = arrayList;
        this.f3594c = activity;
        this.f3596e = c1037g;
        this.f3595d = new C2930c(activity);
        this.f3602k = z;
    }

    private void m6057a(File file, File file2) {
        try {
            InputStream fileInputStream = new FileInputStream(file);
            OutputStream fileOutputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[1024];
            this.f3604m = -1;
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
            this.f3595d.m14130a(file2.getName(), file.getParent());
            if (this.f3602k) {
                if (!file.delete()) {
                    FileUtils.deleteQuietly(file);
                }
                C1131f.m5810b(this.f3594c, file, "video/*");
            }
        } catch (IOException e) {
            this.f3594c.runOnUiThread(new C12252(this));
            try {
                if (this.f3592a != null && this.f3592a.isShowing()) {
                    this.f3592a.dismiss();
                }
            } catch (Exception e2) {
            }
            e.printStackTrace();
        }
    }

    protected Boolean doInBackground(Void... voidArr) {
        String str = this.f3594c.getFilesDir() + "/lockerVault/Videos1769/InstaSave/";
        Iterator it = this.f3593b.iterator();
        while (it.hasNext()) {
            File file = new File((String) it.next());
            String name = file.getName();
            m6057a(file, new File(str + name));
            this.f3603l++;
            this.f3594c.runOnUiThread(new C12241(this));
            this.f3595d.m14130a(name, file.getParent());
        }
        return Boolean.valueOf(true);
    }

    protected void onPostExecute(Boolean bool) {
        try {
            if (this.f3592a != null && this.f3592a.isShowing()) {
                this.f3592a.dismiss();
            }
            if (bool.booleanValue()) {
                this.f3596e.mo962a();
            } else {
                this.f3596e.mo963a(this.f3597f);
            }
        } catch (Exception e) {
        }
        super.onPostExecute(bool);
    }

    protected void onProgressUpdate(Integer... numArr) {
        int intValue = numArr[0].intValue();
        if (intValue - this.f3604m > 0) {
            this.f3598g.setProgress(intValue);
            this.f3600i.setText(intValue + "%");
            this.f3604m = intValue;
        }
        super.onProgressUpdate(numArr);
    }

    protected void onPreExecute() {
        this.f3592a = new ProgressDialog(this.f3594c);
        View inflate = this.f3594c.getLayoutInflater().inflate(R.layout.progress_dialog, null);
        this.f3592a.show();
        this.f3592a.setContentView(inflate);
        this.f3592a.setCancelable(false);
        this.f3598g = (ProgressBar) inflate.findViewById(R.id.progressBar1);
        this.f3599h = (TextView) inflate.findViewById(R.id.tvCount);
        this.f3600i = (TextView) inflate.findViewById(R.id.tvProgress);
        this.f3601j = this.f3593b.size();
        this.f3599h.setText("1/" + this.f3601j);
        super.onPreExecute();
    }
}
