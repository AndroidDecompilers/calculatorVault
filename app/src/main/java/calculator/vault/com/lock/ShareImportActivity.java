package calculator.vault.com.lock;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import calculator.vault.com.R;
import calculator.vault.com.p029h.C1044b;
import calculator.vault.com.p034a.C1201b;
import calculator.vault.com.p034a.C1223e;
import calculator.vault.com.p084i.C2930c;

public class ShareImportActivity extends Activity {
    C2930c f3002a;
    String f3003b;

    class C10401 implements Runnable {
        final /* synthetic */ ShareImportActivity f2996a;

        C10401(ShareImportActivity shareImportActivity) {
            this.f2996a = shareImportActivity;
        }

        public void run() {
            this.f2996a.finish();
            Toast.makeText(this.f2996a, "Please use other gallery app to sharing", Toast.LENGTH_SHORT).show();
        }
    }

    class C10412 implements Runnable {
        final /* synthetic */ ShareImportActivity f2997a;

        C10412(ShareImportActivity shareImportActivity) {
            this.f2997a = shareImportActivity;
        }

        public void run() {
            this.f2997a.finish();
            Toast.makeText(this.f2997a, "Please use other gallery app to sharing", Toast.LENGTH_SHORT).show();
        }
    }

    class C10423 implements Runnable {
        final /* synthetic */ ShareImportActivity f2998a;

        C10423(ShareImportActivity shareImportActivity) {
            this.f2998a = shareImportActivity;
        }

        public void run() {
            this.f2998a.finish();
            Toast.makeText(this.f2998a, "Please use other gallery app to sharing", Toast.LENGTH_SHORT).show();
        }
    }

    class C10434 implements Runnable {
        final /* synthetic */ ShareImportActivity f2999a;

        C10434(ShareImportActivity shareImportActivity) {
            this.f2999a = shareImportActivity;
        }

        public void run() {
            this.f2999a.finish();
            Toast.makeText(this.f2999a, "Please use other gallery app to sharing", Toast.LENGTH_SHORT).show();
        }
    }

    class C10455 implements C1044b {
        final /* synthetic */ ShareImportActivity f3000a;

        C10455(ShareImportActivity shareImportActivity) {
            this.f3000a = shareImportActivity;
        }

        public void mo964a(String str) {
            Toast.makeText(this.f3000a.getApplicationContext(), "Error hiding videos,try again", Toast.LENGTH_SHORT).show();
            this.f3000a.finish();
        }

        public void mo965a(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
            C1131f.m5804a(this.f3000a, "Video(s) are locked to Gallery Videos folder inside locker");
            this.f3000a.finish();
        }
    }

    class C10466 implements C1044b {
        final /* synthetic */ ShareImportActivity f3001a;

        C10466(ShareImportActivity shareImportActivity) {
            this.f3001a = shareImportActivity;
        }

        public void mo964a(String str) {
            Toast.makeText(this.f3001a.getApplicationContext(), "Error hiding files", Toast.LENGTH_SHORT).show();
            this.f3001a.finish();
        }

        public void mo965a(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
            C1131f.m5804a(this.f3001a, "Image(s) are locked to Gallery Pictures folder inside locker");
            this.f3001a.finish();
        }
    }

    private void m5684a() {
        this.f3003b = getFilesDir() + "/lockerVault/Images1769/GalleryPictures";
        if (!new File(this.f3003b).exists()) {
            new File(this.f3003b).mkdirs();
        }
    }

    private void m5685a(ArrayList<String> arrayList) {
        new C1223e(this, arrayList, this.f3003b, this.f3002a, new C10455(this), false, null).execute(new Void[0]);
    }

    private void m5686b() {
        this.f3003b = getFilesDir() + "/lockerVault/Videos1769/GalleryVideos";
        if (!new File(this.f3003b).exists()) {
            new File(this.f3003b).mkdirs();
        }
    }

    private void m5687b(ArrayList<String> arrayList) {
        new C1201b(this, arrayList, this.f3003b, this.f3002a, new C10466(this), false, null).execute(new Void[0]);
    }

    public static boolean m5688b(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private void m5689c(Intent intent) {
        Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
        if (uri != null) {
            String a = m5693a(uri);
            ArrayList arrayList = new ArrayList();
            arrayList.add(a);
            if (!arrayList.isEmpty()) {
                m5685a(arrayList);
                return;
            }
            return;
        }
        Toast.makeText(getApplicationContext(), "Please use other gallery app to use sharing", Toast.LENGTH_SHORT).show();
        finish();
    }

    public static boolean m5690c(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private void m5691d(Intent intent) {
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("android.intent.extra.STREAM");
        if (parcelableArrayListExtra != null) {
            ArrayList arrayList = new ArrayList();
            Iterator it = parcelableArrayListExtra.iterator();
            while (it.hasNext()) {
                arrayList.add(m5693a((Uri) it.next()));
            }
            if (!arrayList.isEmpty()) {
                m5685a(arrayList);
                return;
            }
            return;
        }
        finish();
        Toast.makeText(this, "Please use other gallery app to sharing", Toast.LENGTH_SHORT).show();
    }

    public static boolean m5692d(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    @TargetApi(19)
    public String m5693a(Uri uri) {
        Uri uri2 = null;
        if ((VERSION.SDK_INT >= 19 ? 1 : 0) == 0 || !DocumentsContract.isDocumentUri(getApplicationContext(), uri)) {
            if ("content".equalsIgnoreCase(uri.getScheme())) {
                return m5694a(uri, null, null);
            }
            if ("file".equalsIgnoreCase(uri.getScheme())) {
                return uri.getPath();
            }
        } else if (m5688b(uri)) {
            String[] split = DocumentsContract.getDocumentId(uri).split(":");
            if ("primary".equalsIgnoreCase(split[0])) {
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            }
        } else if (m5690c(uri)) {
            return m5694a(ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
        } else if (m5692d(uri)) {
            Object obj = DocumentsContract.getDocumentId(uri).split(":")[0];
            if ("image".equals(obj)) {
                uri2 = Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(obj)) {
                uri2 = Video.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(obj)) {
                uri2 = Audio.Media.EXTERNAL_CONTENT_URI;
            }
            String str = "_id=?";
            return m5694a(uri2, "_id=?", new String[]{});
        }
        return "" + uri;
    }

    public String m5694a(Uri uri, String str, String[] strArr) {
        Throwable th;
        Cursor cursor = null;
        String str2 = "_data";
        try {
            Cursor query = getContentResolver().query(uri, new String[]{"_data"}, str, strArr, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        str2 = query.getString(query.getColumnIndexOrThrow("_data"));
                        if (query == null) {
                            return str2;
                        }
                        query.close();
                        return str2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return null;
        } catch (Throwable th3) {
            th = th3;
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    void m5695a(Intent intent) {
        Uri uri = (Uri) intent.getParcelableExtra("android.intent.extra.STREAM");
        if (uri != null) {
            String a = m5693a(uri);
            ArrayList arrayList = new ArrayList();
            arrayList.add(a);
            if (!arrayList.isEmpty()) {
                m5687b(arrayList);
                return;
            }
            return;
        }
        finish();
        Toast.makeText(getApplicationContext(), "Please use other gallery app to use sharing", Toast.LENGTH_SHORT).show();
    }

    void m5696b(Intent intent) {
        ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra("android.intent.extra.STREAM");
        if (parcelableArrayListExtra != null) {
            ArrayList arrayList = new ArrayList();
            Iterator it = parcelableArrayListExtra.iterator();
            while (it.hasNext()) {
                arrayList.add(m5693a((Uri) it.next()));
            }
            if (!arrayList.isEmpty()) {
                m5687b(arrayList);
                return;
            }
            return;
        }
        finish();
        Toast.makeText(this, "Please use other gallery app to sharing", Toast.LENGTH_SHORT).show();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_share_import);
        this.f3002a = new C2930c(getApplicationContext());
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if (!"android.intent.action.SEND".equals(action) || type == null) {
            if ("android.intent.action.SEND_MULTIPLE".equals(action) && type != null) {
                if (type.startsWith("image/")) {
                    m5684a();
                    try {
                        m5696b(intent);
                    } catch (Exception e) {
                        runOnUiThread(new C10423(this));
                    }
                } else if (type.startsWith("video/")) {
                    m5686b();
                    try {
                        m5691d(intent);
                    } catch (Exception e2) {
                        runOnUiThread(new C10434(this));
                    }
                }
            }
        } else if (type.startsWith("image/")) {
            m5684a();
            try {
                m5695a(intent);
            } catch (Exception e3) {
                runOnUiThread(new C10401(this));
            }
        } else if (type.startsWith("video/")) {
            m5686b();
            try {
                m5689c(intent);
            } catch (Exception e4) {
                runOnUiThread(new C10412(this));
            }
        }
    }
}
