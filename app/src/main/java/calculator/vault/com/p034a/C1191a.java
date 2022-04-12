package calculator.vault.com.p034a;

import android.content.Context;
import android.os.AsyncTask;

import java.io.File;
import java.util.ArrayList;

import calculator.vault.com.lock.C1131f;
import calculator.vault.com.p029h.C1017a;
import calculator.vault.com.p068a.FileModel;

public class C1191a {
    private static ArrayList<FileModel> f3470c = new ArrayList();
    Context f3471a;
    String f3472b;

    public C1191a(Context context, String str) {
        this.f3471a = context;
        this.f3472b = str;
    }

    private ArrayList<FileModel> m6030a(String str) {
        ArrayList<FileModel> arrayList = new ArrayList();
        for (File file : new File(str).listFiles()) {
            arrayList.add(new FileModel(file.getAbsolutePath(), file.getName()));
        }
        return arrayList;
    }

    public void m6032a(final C1017a c1017a) {
        new AsyncTask<Void, Void, Void>() {

            protected Void doInBackground(Void... voidArr) {
                if (C1131f.f3318d == null || C1131f.f3318d.length() < 5) {
                    C1131f.f3318d = f3471a.getFilesDir() + "/lockerVault";
                }
                C1191a.f3470c = m6030a(C1131f.f3318d);
                return null;
            }

            protected void onPostExecute(Void voidR) {
                c1017a.mo959a(C1191a.f3470c);
                super.onPostExecute(voidR);
            }

            protected void onPreExecute() {
                super.onPreExecute();
                c1017a.mo958a();
            }
        }.execute(new Void[0]);
    }
}
