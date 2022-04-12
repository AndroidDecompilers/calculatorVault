package calculator.vault.com.applock;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.AppOpsManager;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Process;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import calculator.vault.com.R;
import calculator.vault.com.lock.MyApplication;
import calculator.vault.com.p073g.ApplockerFragment;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

@TargetApi(22)
public class C3150b {
    public static C1055c f9991a;

    public interface C1055c {
        void mo966a(boolean z);
    }

    public static class C3148a implements Callback {
        Activity activity;

        class C31472 implements Runnable {
            final /* synthetic */ C3148a f9988a;

            C31472(C3148a c3148a) {
                this.f9988a = c3148a;
            }

            public void run() {
                C3150b.m14834a(this.f9988a.activity, new String[0]);
            }
        }

        public C3148a(Activity activity) {
            this.activity = activity;
        }

        public void onFailure(Call call, IOException iOException) {
        }

        public void onResponse(Call call, Response response) {

        }
    }

    private static class C3149b extends AsyncTask<String, String, String> {
        Activity f9990a;

        public C3149b(Activity activity) {
            this.f9990a = activity;
        }

        protected String m14825a(String... strArr) {
            HttpURLConnection httpURLConnection;
            Exception exception;
            Throwable th;
            HttpURLConnection httpURLConnection2 = null;
            try {
                HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(strArr[0]).openConnection();
                try {
                    httpURLConnection3.connect();
                    int responseCode = httpURLConnection3.getResponseCode();
                    String obj;
                    if (responseCode < 200 || responseCode > 399) {
                        obj = httpURLConnection3.getErrorStream().toString();
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                        return obj;
                    }
                    obj = "password has been sent. you will receive your mail soon.";
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return obj;
                } catch (Exception e) {
                    Exception exception2 = e;
                    httpURLConnection = httpURLConnection3;
                    exception = exception2;
                    try {
                        exception.printStackTrace();
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        httpURLConnection2 = httpURLConnection;
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    httpURLConnection2 = httpURLConnection3;
                    th = th4;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (Exception e2) {
                exception = e2;
                httpURLConnection = httpURLConnection2;
                exception.printStackTrace();
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            } catch (Throwable th5) {
                th = th5;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
            }
            return null;
        }

        protected void m14826a(String str) {
            super.onPostExecute(str);
            if (str == null) {
                Toast.makeText(this.f9990a, "Failed. check your internet connection.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.f9990a, "" + str, Toast.LENGTH_SHORT).show();
            }
        }

        protected String doInBackground(String[] objArr) {
            return m14825a((String[]) objArr);
        }

        protected void onPostExecute(String obj) {
            m14826a((String) obj);
        }

        protected void onPreExecute() {
            Toast.makeText(this.f9990a, "Sending mail..", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }
    }

    public static Spanned m14827a(String str) {
        return Html.fromHtml(str);
    }

    public static void m14828a(final Activity activity) {
        final Dialog dialog = new Dialog(activity);
        View inflate = activity.getLayoutInflater().inflate(R.layout.permission_applock_dialog, null);
        dialog.setContentView(inflate);
        inflate.findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
                ApplockerFragment.f4690f.m7330N();
                activity.startService(new Intent(activity, Temporary_view_Service.class));
            }
        });
        try {
            dialog.show();
        } catch (Exception e) {
        }
    }

    public static void m14829a(Activity activity, final Editor editor) {
        Builder builder = new Builder(activity);
        builder.setTitle("Information");
        builder.setMessage(activity.getResources().getString(R.string.hint_uninstall));
        builder.setNeutralButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                editor.putInt("addCount", 2);
                editor.commit();
            }
        });
        builder.setCancelable(false);
        try {
            builder.create().show();
        } catch (Exception e) {
        }
    }

    public static void m14830a(Activity activity, final SharedPreferences sharedPreferences) {
        final Editor edit = sharedPreferences.edit();
        Builder builder = VERSION.SDK_INT >= 21 ? new Builder(activity) : new Builder(activity);
        builder.setTitle(activity.getResources().getString(R.string.rate_dialog_title));
        builder.setMessage(activity.getResources().getString(R.string.rate_dialog_message));
        final SharedPreferences sharedPreferences2 = sharedPreferences;
        final Activity activity2 = activity;
        builder.setPositiveButton(activity.getString(R.string.rate_dialog_ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (sharedPreferences2.getInt("yesCount", 0) > 0) {
                    edit.putBoolean("neverRate", true);
                } else {
                    edit.putInt("rateCount", 2);
                    edit.putInt("yesCount", 1);
                }
                edit.commit();
                activity2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + activity2.getPackageName())));
            }
        });
        builder.setNegativeButton(activity.getString(R.string.rate_dialog_no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (sharedPreferences.getInt("noCount", 0) > 0) {
                    edit.putBoolean("neverRate", true);
                } else {
                    edit.putInt("rateCount", 0);
                    edit.putInt("noCount", 1);
                }
                edit.commit();
            }
        });
        builder.setNeutralButton(activity.getString(R.string.rate_dialog_cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        AlertDialog create = builder.create();
        create.show();
        if (VERSION.SDK_INT >= 21) {
            try {
                ((LinearLayout) create.getButton(-1).getParent()).setOrientation(LinearLayout.VERTICAL);
            } catch (Exception e) {
            }
        }
    }

    public static void m14831a(final Activity activity, final C1055c c1055c) {
        final Dialog dialog = new Dialog(activity);
        View inflate = activity.getLayoutInflater().inflate(R.layout.manual_delete_dialog, null);
        ((TextView) inflate.findViewById(R.id.tvMsg)).setText(C3150b.m14827a(activity.getString(R.string.manually_delete_description)));
        dialog.setContentView(inflate);
        dialog.setCancelable(false);
        inflate.findViewById(R.id.tvSelectSdCard).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                C3150b.m14832a(activity, C3150b.f9991a, false);
                dialog.dismiss();
            }
        });
        inflate.findViewById(R.id.tvHowToDelete).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                c1055c.mo966a(false);
            }
        });
        inflate.findViewById(R.id.tvGotIt).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                c1055c.mo966a(true);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void m14832a(Activity activity, final C1055c c1055c, boolean z) {
        final Dialog dialog = new Dialog(activity);
        f9991a = c1055c;
        View inflate = activity.getLayoutInflater().inflate(R.layout.sdcard_select_dialog, null);
        dialog.setContentView(inflate);
        dialog.setCancelable(false);
        TextView textView = (TextView) inflate.findViewById(R.id.textView1);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tvDeleteManually);
        if (z) {
            textView.setText("Write Permission Required");
            textView2.setText("USE INTERNAL INSTEAD");
        }
        inflate.findViewById(R.id.tvDeleteManually).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                c1055c.mo966a(false);
                dialog.dismiss();
            }
        });
        inflate.findViewById(R.id.tvSet).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                c1055c.mo966a(true);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void m14833a(final Activity activity, final String str, final String str2) {
        Builder builder = new Builder(activity);
        builder.setTitle("Confirmation");
        builder.setMessage("We will send your password to your registered email address.\nThis may take 5-10 minutes to arrive in your mail.");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            private void m14824a(String str, String str2) {
                new C3149b(activity).execute(new String[]{"http://www.wondersoftwares.com/clathiya/index.php?email=" + str + "&password=" + str2});
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                m14824a(str, str2);
            }
        });
        builder.setNegativeButton("Oops,No!", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    public static void m14834a(Activity activity, String... strArr) {

    }

    @TargetApi(19)
    public static boolean m14835a(Context context) {
        if (VERSION.SDK_INT < 21) {
            return true;
        }
        return ((AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE)).checkOpNoThrow("android:get_usage_stats", Process.myUid(), context.getPackageName()) == 0;
    }

    public static void m14836b(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        View inflate = activity.getLayoutInflater().inflate(R.layout.manual_unhide_kitkat_dialog, null);
        ((TextView) inflate.findViewById(R.id.tvMsg)).setText(C3150b.m14827a(activity.getString(R.string.manually_unhide_kitkat_descr)));
        dialog.setContentView(inflate);
        dialog.setCancelable(false);
        inflate.findViewById(R.id.tvGotIt).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void m14837b(Activity activity, Editor editor) {
        final Dialog dialog = new Dialog(activity);
        View inflate = activity.getLayoutInflater().inflate(R.layout.rate_dialog, null);
        dialog.setContentView(inflate);
        final Activity activity2 = activity;
        final Editor editor2 = editor;
        inflate.findViewById(R.id.rlRate).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                try {
                    activity2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + activity2.getPackageName())));
                } catch (ActivityNotFoundException e) {
                    activity2.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + activity2.getPackageName())));
                }
                editor2.putBoolean("isRated", true);
                editor2.commit();
                dialog.dismiss();
            }
        });
        inflate.findViewById(R.id.rlCancel).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        dialog.show();
    }

    public static void m14838b(Activity activity, final C1055c c1055c) {
        final Dialog dialog = new Dialog(activity);
        View inflate = activity.getLayoutInflater().inflate(R.layout.manual_delete_kitkat_dialog, null);
        ((TextView) inflate.findViewById(R.id.tvMsg)).setText(C3150b.m14827a(activity.getString(R.string.manually_delete_kitkat_desc)));
        dialog.setContentView(inflate);
        dialog.setCancelable(false);
        inflate.findViewById(R.id.tvGotIt).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (c1055c != null) {
                    c1055c.mo966a(true);
                }
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void m14839c(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        View inflate = activity.getLayoutInflater().inflate(R.layout.manual_howtodelete_dialog, null);
        ((TextView) inflate.findViewById(R.id.tvMsg)).setText(C3150b.m14827a(activity.getString(R.string.howtodelete_desc)));
        dialog.setContentView(inflate);
        dialog.setCancelable(false);
        inflate.findViewById(R.id.tvOk).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void m14840d(Activity activity) {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        Editor edit = defaultSharedPreferences.edit();
        int i = defaultSharedPreferences.getInt("rateCount", 0);
        boolean z = defaultSharedPreferences.getBoolean("neverRate", false);
        if ((i == 3 || defaultSharedPreferences.getInt("rateCount", 0) == 6) && !z) {
            C3150b.m14830a(activity, defaultSharedPreferences);
        } else if (!defaultSharedPreferences.getBoolean("isRated", false)) {
            C3150b.m14837b(activity, edit);
        }
        edit.putInt("rateCount", i + 1);
        edit.commit();
    }
}
