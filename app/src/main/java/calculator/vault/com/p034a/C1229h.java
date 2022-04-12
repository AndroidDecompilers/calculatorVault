package calculator.vault.com.p034a;

import android.app.Activity;
import android.os.AsyncTask;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import calculator.vault.com.p029h.C1019c;

public class C1229h {
    Activity f3611a;
    String f3612b;
    String f3613c;
    private ArrayList<String> f3614d = new ArrayList();

    public C1229h(Activity activity, String str, String str2, ArrayList<String> arrayList) {
        this.f3611a = activity;
        this.f3612b = str;
        this.f3613c = str2;
        this.f3614d = arrayList;
    }

    public void m6067a(final C1019c c1019c) {
        new AsyncTask<Void, Void, Boolean>() {

            protected Boolean doInBackground(Void... voidArr) {
                Iterator it = f3614d.iterator();
                while (it.hasNext()) {
                    File file = new File((String) it.next());
                    file.renameTo(new File(f3613c + "/" + file.getName()));
                }
                return Boolean.valueOf(true);
            }

            protected void onPostExecute(Boolean bool) {
                c1019c.mo960a(f3613c);
                super.onPostExecute(bool);
            }

            protected void onPreExecute() {
                super.onPreExecute();
            }
        }.execute(new Void[0]);
    }
}
