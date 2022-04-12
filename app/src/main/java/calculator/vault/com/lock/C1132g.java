package calculator.vault.com.lock;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.DocumentsContract;
import android.support.annotation.RequiresApi;
import android.support.v4.provider.DocumentFile;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class C1132g {
    public static DocumentFile m5811a(Context context, File file, Uri uri) {
        DocumentFile a = DocumentFile.fromSingleUri(context, uri);
        List<String> arrayList = new ArrayList(Arrays.asList(file.getParentFile().getAbsolutePath().split("/")));
        if (arrayList.isEmpty() || arrayList.size() <= 1) {
            return a;
        }
        arrayList.remove(0);
        arrayList.remove(0);
        arrayList.remove(0);
        DocumentFile DocumentFile = a;
        for (String b : arrayList) {
            DocumentFile = DocumentFile.findFile(b);
        }
        return DocumentFile;
    }

    public static DocumentFile m5812a(File file, boolean z, Context context) {
        int i = 0;
        String a = C1132g.m5813a(file, context);
        if (a == null) {
            return null;
        }
        String canonicalPath;
        int i2;
        String str;
        try {
            canonicalPath = file.getCanonicalPath();
            if (a.equals(canonicalPath)) {
                i2 = 1;
                str = null;
            } else {
                str = canonicalPath.substring(a.length() + 1);
                i2 = 0;
            }
        } catch (IOException e) {
            return null;
        } catch (Exception e2) {
            i2 = 1;
            str = null;
        }
        canonicalPath = PreferenceManager.getDefaultSharedPreferences(context).getString("treeUri", null);
        Uri parse = canonicalPath != null ? Uri.parse(canonicalPath) : null;
        if (parse == null) {
            return null;
        }
        DocumentFile a2 = DocumentFile.fromSingleUri(context, parse);
        if (i2 != 0) {
            return a2;
        }
        String[] split = str.split("\\/");
        DocumentFile DocumentFile = a2;
        while (i < split.length) {
            a2 = DocumentFile.findFile(split[i]);
            DocumentFile = a2 == null ? (i < split.length + -1 || z) ? DocumentFile.findFile(split[i]) : DocumentFile.createFile("image", split[i]) : a2;
            i++;
        }
        return DocumentFile;
    }

    @TargetApi(19)
    public static String m5813a(File file, Context context) {
        String[] a = C1132g.m5815a(context);
        int i = 0;
        while (i < a.length) {
            try {
                if (file.getCanonicalPath().startsWith(a[i])) {
                    return a[i];
                }
                i++;
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    @TargetApi(21)
    public static boolean m5814a(Uri uri) {
        return C1132g.m5818c(uri) && C1132g.m5819d(uri) && !C1132g.m5817b(uri);
    }

    @TargetApi(19)
    public static String[] m5815a(Context context) {
        List arrayList = new ArrayList();
        for (File file : context.getExternalFilesDirs("external")) {
            if (!(file == null || file.equals(context.getExternalFilesDir("external")))) {
                int lastIndexOf = file.getAbsolutePath().lastIndexOf("/Android/data");
                if (lastIndexOf < 0) {
                    Log.w("AmazeFileUtils", "Unexpected external file dir: " + file.getAbsolutePath());
                } else {
                    String substring = file.getAbsolutePath().substring(0, lastIndexOf);
                    try {
                        substring = new File(substring).getCanonicalPath();
                    } catch (IOException e) {
                    }
                    arrayList.add(substring);
                }
            }
        }
        if (arrayList.isEmpty()) {
            arrayList.add("/storage/sdcard1");
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static DocumentFile m5816b(Context context, File file, Uri uri) {
        DocumentFile a = DocumentFile.fromSingleUri(context, uri);
        List<String> arrayList = new ArrayList(Arrays.asList(file.getAbsolutePath().split("/")));
        if (arrayList.isEmpty() || arrayList.size() <= 1) {
            return a;
        }
        arrayList.remove(0);
        arrayList.remove(0);
        arrayList.remove(0);
        DocumentFile DocumentFile = a;
        for (String str : arrayList) {
            DocumentFile b = DocumentFile.createDirectory(str);
            DocumentFile = b == null ? DocumentFile.findFile(str) : b;
        }
        return DocumentFile;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static boolean m5817b(Uri uri) {
        return C1132g.m5818c(uri) && DocumentsContract.getTreeDocumentId(uri).contains("primary");
    }

    public static boolean m5818c(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private static boolean m5819d(Uri uri) {
        return DocumentsContract.getTreeDocumentId(uri).endsWith(":");
    }
}
