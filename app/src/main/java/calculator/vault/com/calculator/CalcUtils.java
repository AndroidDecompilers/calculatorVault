package calculator.vault.com.calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

@SuppressLint({"NewApi"})
public class CalcUtils {

    static class C32272 implements OnClickListener {
        C32272() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    private static class RequestTask extends AsyncTask<String, String, String> {
        Activity act;

        public RequestTask(Activity activity) {
            this.act = activity;
        }

        protected String doInBackground(String... strArr) {
            try {
                URL url = new URL("https://api.sparkpost.com/api/v1/transmissions");
                HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
                httpsURLConnection.setRequestProperty("Content-Type", "application/json");
                httpsURLConnection.setRequestProperty("Authorization", "a5286bc1db1d299d8a4333297546ae0248130108");
                httpsURLConnection.setDoOutput(true);
                httpsURLConnection.setRequestMethod("POST");
                DataOutputStream wr = new DataOutputStream(httpsURLConnection.getOutputStream());
                wr.write(("{\"options\":{\"open_tracking\":true,\"click_tracking\":true},\"metadata\":{\"user_pass\":\"pass\"},\"substitution_data\"" +
                        ":{\"signature\":\"calculator vault\"},\"recipients\":[{\"address\":{\"email\":\"" + strArr[0] + "\"},\"tags\"" +
                        ":[\"learning\"],\"substitution_data\":{\"customer_type\":\"Platinum\",\"first_name\":\"unknown\"}}]," +
                        "\"content\":{\"from\":{\"name\":\"Calculator Vault Password Recovery\",\"email\":\"recovery@calculator.appsapionline.com\"},\"subject\":\"Calculator Vault Password Recovery\"," +
                        "\"text\":\"Hi user,\\r\\nAs per your request your vault password is  " + strArr[1] + ". \\r\\nUse this password to open your vault\\r\\nThanks and Regards,\\r\\nCalculator Vault\",\"html\":\"<strong>Hi user,</strong><p>" +
                        "As per your request your Vault password is " + strArr[1] + ". \\r\\nUse this password to open your vault</p><p>Thanks and Regards</p>Calculator vault\"}}").getBytes());
                wr.flush();
                wr.close();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpsURLConnection.getInputStream()));
                StringBuilder readData = new StringBuilder();
                String read = "";
                while ((read = bufferedReader.readLine()) != null) {
                    readData.append(read);
                }
//            System.out.print("Responce :" + readData.toString());

                if (readData.toString() != null && !readData.toString().equals("")) {
                    JSONObject jsonObject = new JSONObject(readData.toString());
                    JSONObject result = jsonObject.getJSONObject("results");
                    if (result.getInt("total_accepted_recipients") == 1) {
                        return "Password send Sucessfully";
                    } else {
                        return null;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            if (str == null) {
                Toast.makeText(this.act, "Failed. check your internet connection.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this.act, "" + str, Toast.LENGTH_SHORT).show();
            }
        }

        protected void onPreExecute() {
            Toast.makeText(this.act, "Sending mail..", Toast.LENGTH_SHORT).show();
            super.onPreExecute();
        }
    }

    public static void createInitialFolders(Activity activity) {
        String absolutePath = activity.getFilesDir().getAbsolutePath();
        if (absolutePath.contains(activity.getPackageName())) {
            File file = new File(absolutePath + "/lockerVault/Images1769/Pictures");
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(absolutePath + "/lockerVault/Images1769/Downloads");
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(absolutePath + "/lockerVault/Videos1769/Videos");
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(absolutePath + "/lockerVault/Videos1769/Downloads");
            if (!file.exists()) {
                file.mkdirs();
                return;
            }
            return;
        }
        activity.finish();
        System.exit(0);
    }

    public static boolean hasHoneycomb() {
        return VERSION.SDK_INT >= 11;
    }

    public static boolean hasJellyBean() {
        return VERSION.SDK_INT >= 16;
    }

    public static boolean hasLollipop() {
        return VERSION.SDK_INT >= 21;
    }

    public static void removeOnGlobalLayoutListenerCompat(View view, OnGlobalLayoutListener onGlobalLayoutListener) {
        if (hasJellyBean()) {
            view.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        } else {
            view.getViewTreeObserver().removeGlobalOnLayoutListener(onGlobalLayoutListener);
        }
    }

    public static void setBackgroundCompat(View view, Drawable drawable) {
        if (hasJellyBean()) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void setLayerTypeCompat(View view, int i) {
        if (hasHoneycomb()) {
            view.setLayerType(i, null);
        }
    }

    public static void setStatusBarColorCompat(Window window, int i) {
        if (hasLollipop()) {
            window.setStatusBarColor(i);
        }
    }

    public static void showSendMailDialog(final Activity activity, final String str, final String str2) {
        Builder builder = VERSION.SDK_INT >= 21 ? new Builder(activity, 16974394) : new Builder(activity);
        builder.setTitle("Confirmation");
        builder.setMessage("We will send your password to your registered email address.\nThis may take 5-10 minutes to arrive in your mail.");
        builder.setPositiveButton("OK", new OnClickListener() {
            private void sendMailToAddress(String str, String str2) {
                new RequestTask(activity).execute(new String[]{str, str2});
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                sendMailToAddress(str, str2);
            }
        });
        builder.setNegativeButton("Oops,No!", new C32272());
        builder.create().show();
    }
}
