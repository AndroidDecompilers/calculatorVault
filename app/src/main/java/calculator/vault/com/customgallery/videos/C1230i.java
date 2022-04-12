package calculator.vault.com.customgallery.videos;


import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.provider.DocumentFile;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import calculator.vault.com.R;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.lock.C1132g;


public class C1230i extends AsyncTask<Void, Integer, Boolean> {
    Activity f3615a;
    ProgressDialog f3616b;
    TextView f3617c;
    TextView f3618d;
    int f3619e;
    ProgressBar f3620f;
    int f3621g = 1;
    ArrayList<String> f3622h;
    String f3623i;
    C1075a f3624j;
    Uri f3625k;

    public interface C1075a {
        void mo969a(boolean z);
    }

    public C1230i(Activity activity, ArrayList<String> arrayList, Uri uri, String str, C1075a c1075a) {
        this.f3615a = activity;
        this.f3622h = arrayList;
        this.f3625k = uri;
        this.f3623i = str;
        this.f3624j = c1075a;
    }

    protected Boolean doInBackground(Void... voidArr) {
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            Iterator it = this.f3622h.iterator();
            while (it.hasNext()) {
                File file = new File((String) it.next());
                arrayList2.add(file.getParent());
                arrayList.add(file.getName());
            }
            if (arrayList2.size() > 1) {
                Collection hashSet = new HashSet();
                hashSet.addAll(arrayList2);
                arrayList2.clear();
                arrayList2.addAll(hashSet);
            }
            ArrayList arrayList3 = new ArrayList();
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                for (DocumentFile c0318a : C1132g.m5811a(this.f3615a.getApplicationContext(), new File(((String) it2.next()) + "/dummy.txt"), this.f3625k).listFiles()) {
                    if (arrayList.contains(c0318a.getUri())) {
                        arrayList3.add(c0318a);
                    }
                }
            }
            Iterator it3 = this.f3622h.iterator();
            int i = 0;
            while (it3.hasNext()) {
                String str = (String) it3.next();
                int i2 = i + 1;
                publishProgress(new Integer[]{Integer.valueOf(i2)});
                new File(str).getName();
                if (((DocumentFile) arrayList3.get(i2 - 1)).exists() && this.f3623i != null) {
                    C1131f.m5810b(this.f3615a, new File(str), this.f3623i);
                }
                i = i2;
            }
            return Boolean.valueOf(true);
        } catch (Exception e) {
            return Boolean.valueOf(false);
        }
    }

    protected void m6069a(Boolean bool) {
        try {
            if (this.f3616b != null && this.f3616b.isShowing()) {
                this.f3616b.dismiss();
            }
        } catch (Exception e) {
        }
        this.f3624j.mo969a(bool.booleanValue());
        super.onPostExecute(bool);
    }

    protected void onProgressUpdate(Integer... numArr) {
        int intValue = numArr[0].intValue();
        this.f3620f.setProgress(intValue);
        this.f3618d.setText(((intValue * 100) / this.f3619e) + "%");
        this.f3617c.setText(intValue + "/" + this.f3619e);
        super.onProgressUpdate(numArr);
    }

    protected void onPreExecute() {
        this.f3616b = new ProgressDialog(this.f3615a);
        View inflate = this.f3615a.getLayoutInflater().inflate(R.layout.sdcard_delete_pregress_dialog, null);
        this.f3616b.show();
        this.f3616b.setContentView(inflate);
        this.f3616b.setCancelable(false);
        this.f3620f = (ProgressBar) inflate.findViewById(R.id.progressBar1);
        this.f3617c = (TextView) inflate.findViewById(R.id.tvCount);
        this.f3618d = (TextView) inflate.findViewById(R.id.tvProgress);
        this.f3619e = this.f3622h.size();
        this.f3617c.setText("1/" + this.f3619e);
        this.f3620f.setMax(this.f3619e);
        super.onPreExecute();
    }
}
