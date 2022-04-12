package calculator.vault.com.lock;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Video.Media;
import android.util.Patterns;
import android.widget.Toast;

import java.io.File;

import calculator.vault.com.R;

public class C1131f {
    public static Typeface f3315a = null;
    public static int f3316b;
    public static int f3317c;
    public static String f3318d;
    public static String f3319e;
    public static String f3320f;
    public static boolean f3321g;
    public static Typeface f3322h = null;
    public static boolean f3323i;
    public static boolean f3324j;
    public static boolean f3325k;
    public static String f3326l;

    public static int getCurrentStyle(SharedPreferences sharedPreferences) {
        return sharedPreferences.getInt("currentStyle", R.style.AppTheme);
    }

    public static void sendEmail(Activity activity) {
        String str;
        String strArr = activity.getResources().getString(R.string.developer_mail);
        try {
            str = "v" + activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            str = "vNA";
        }

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + strArr));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, activity.getResources().getString(R.string.app_name));
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Calculator Gallery Lock" + str + " " + Build.MANUFACTURER + " " + Build.MODEL + " running Android " + VERSION.RELEASE + "\n----------------------------------------------------------------\n\n\n");
        try {
            activity.startActivity(Intent.createChooser(emailIntent, "Send mail"));
        } catch (ActivityNotFoundException e2) {
            Toast.makeText(activity, "No email app installed in your phone", Toast.LENGTH_SHORT).show();
        }
    }

    public static void m5804a(final Activity activity, final String str) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, "" + str, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static void m5805a(Context context, File file, String str) {
        if (VERSION.SDK_INT >= 20) {
            ContentValues contentValues;
            if (str.equals("video/*")) {
                contentValues = new ContentValues();
                contentValues.put("_data", file.getAbsolutePath());
                contentValues.put("mime_type", str);
                context.getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, contentValues);
            } else if (str.equals("image/*")) {
                contentValues = new ContentValues();
                contentValues.put("_data", file.getAbsolutePath());
                contentValues.put("mime_type", str);
                context.getContentResolver().insert(Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            }
        } else if (VERSION.SDK_INT >= 19) {
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(file));
            context.sendBroadcast(intent);
        } else {
            context.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
        }
    }

    public static boolean m5806a(Context context, PackageManager packageManager, String str) {
        Intent launchIntentForPackage = packageManager.getLaunchIntentForPackage(str);
        if (launchIntentForPackage != null) {
            try {
                context.startActivity(launchIntentForPackage);
                return true;
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "Applications Not Found", Toast.LENGTH_SHORT).show();
            }
        }
        return false;
    }

    public static boolean m5807a(String str) {
        return Patterns.WEB_URL.matcher(str).matches();
    }

    public static boolean m5808a(String str, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(str, Toast.LENGTH_SHORT);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    public static void m5809b(Activity activity, String str) {
        try {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str.substring(str.lastIndexOf("?id=") + 4, str.length()))));
        } catch (ActivityNotFoundException e) {
            activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        }
    }

    public static void m5810b(Context context, File file, String str) {
        if (VERSION.SDK_INT >= 20) {
            if (str.equals("video/*")) {
                context.getContentResolver().delete(Media.EXTERNAL_CONTENT_URI, "_data= ?", new String[]{file.getAbsolutePath()});
                return;
            }
            context.getContentResolver().delete(Images.Media.EXTERNAL_CONTENT_URI, "_data= ?", new String[]{file.getAbsolutePath()});
        } else if (VERSION.SDK_INT >= 19) {
            Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
            intent.setData(Uri.fromFile(file));
            context.sendBroadcast(intent);
        } else {
            context.sendBroadcast(new Intent("android.intent.action.MEDIA_MOUNTED", Uri.fromFile(file)));
        }
    }
}
