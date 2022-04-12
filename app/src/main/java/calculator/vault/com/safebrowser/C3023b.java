package calculator.vault.com.safebrowser;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Base64;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import calculator.vault.com.R;

public class C3023b extends AsyncTask<String, Integer, Boolean> {
    String f9500a;
    String f9501b;
    int f9502c;
    int f9503d;
    Notification f9504e;
    Context f9505f;
    String f9506g;
    BroadcastReceiver f9507h = new C30221(this);
    NotificationCompat.Builder f9508i;
    NotificationManager f9509j;
    float f9510k = 0.0f;
    private boolean f9511l;

    class C30221 extends BroadcastReceiver {
        final /* synthetic */ C3023b f9499a;

        C30221(C3023b c3023b) {
            this.f9499a = c3023b;
        }

        public void onReceive(Context context, Intent intent) {
            this.f9499a.f9509j.cancel(this.f9499a.f9502c);
            this.f9499a.m14464a().cancel(true);
            this.f9499a.f9505f.unregisterReceiver(this.f9499a.f9507h);
        }
    }

    public C3023b(Context context, String str, int i, int i2, String str2) {
        this.f9503d = i;
        this.f9505f = context;
        this.f9502c = i2;
        this.f9506g = str2;
        this.f9501b = str;
        this.f9511l = str.startsWith("data:image");
        this.f9500a = new File(str).getName();
        if (i == 891 || i == 693) {
            String toLowerCase = this.f9500a.substring(this.f9500a.lastIndexOf(".") + 1, this.f9500a.length()).toLowerCase();
            if (toLowerCase == null || toLowerCase.length() > 6 || this.f9500a.startsWith("?")) {
                this.f9500a = System.currentTimeMillis() + (i == 891 ? ".jpg" : ".mp4");
            }
        }
        this.f9509j = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        this.f9508i = new NotificationCompat.Builder(context);
        this.f9508i.setAutoCancel(true);
        this.f9508i.setContentText(i == 891 ? "Picture Download" : "Video Download").setTicker((CharSequence) "Starting Download").setSmallIcon((int) R.drawable.ic_download);
        this.f9508i.setProgress(0, 0, true);
        this.f9508i.setShowWhen(false);
        Intent intent = new Intent(getClass().getName() + "" + i2);
        intent.putExtra("nId", i2);
        context.registerReceiver(this.f9507h, new IntentFilter(getClass().getName() + "" + i2));
        this.f9508i.addAction((int) R.drawable.abc_ic_clear_mtrl_alpha, (CharSequence) "Cancel", PendingIntent.getBroadcast(context, 0, intent, 0));
        this.f9504e = this.f9508i.build();
        this.f9509j.notify(i2, this.f9504e);
    }

    private boolean m14463b() {
        try {
            File file;
            URL url = new URL(this.f9501b);
            File file2 = new File(this.f9506g, this.f9500a);
            if (file2.exists()) {
                String[] split = this.f9500a.split("\\.(?=[^\\.]+$)");
                String str = split[0];
                file = new File(file2.getParent() + "/" + str + "_" + new Random().nextInt(123) + "." + split[1]);
            } else {
                file = file2;
            }
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            int contentLength = httpURLConnection.getContentLength();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            long j = 0;
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    inputStream.close();
                    fileOutputStream.close();
                    return true;
                } else if (isCancelled()) {
                    return false;
                } else {
                    j += (long) read;
                    fileOutputStream.write(bArr, 0, read);
                    float f = (float) ((int) ((100 * j) / ((long) contentLength)));
                    if (f - this.f9510k > 0.0f) {
                        publishProgress(new Integer[]{Integer.valueOf((int) f)});
                        this.f9510k = f;
                    }
                }
            }
        } catch (MalformedURLException e) {
            if (this.f9511l) {
                Bitmap decodeStream = BitmapFactory.decodeStream(new ByteArrayInputStream(Base64.decode(this.f9501b.substring(this.f9501b.indexOf(",") + 1).getBytes(), 0)));
                try {
                    decodeStream.compress(CompressFormat.JPEG, 100, new FileOutputStream(new File(this.f9506g, System.currentTimeMillis() + ".jpg")));
                    return true;
                } catch (FileNotFoundException e2) {
                    return false;
                }
            }
            e.printStackTrace();
            return false;
        } catch (IOException e3) {
            e3.printStackTrace();
            return false;
        } catch (Exception e4) {
            e4.printStackTrace();
            return false;
        }
    }

    public C3023b m14464a() {
        return this;
    }

    protected Boolean m14465a(String... strArr) {
        return Boolean.valueOf(m14463b());
    }

    protected void m14466a(Boolean bool) {
        if (bool.booleanValue()) {
            this.f9508i.setContentText((CharSequence) "Download completed");
        } else {
            this.f9508i.setContentText((CharSequence) "Download Failed");
        }
        this.f9508i.setShowWhen(false);
        this.f9504e = this.f9508i.build();
        this.f9509j.cancel(this.f9502c);
        Toast.makeText(this.f9505f, (this.f9503d == 891 ? "Photo" : "Video") + " download " + (bool.booleanValue() ? "completed" : "failed"), Toast.LENGTH_SHORT).show();
    }

    protected void m14467a(Integer... numArr) {
        this.f9508i.setProgress(100, numArr[0].intValue(), false);
        this.f9508i.setContentText("Downloading... " + numArr[0] + "%");
        this.f9504e = this.f9508i.build();
        this.f9509j.notify(this.f9502c, this.f9504e);
    }

    protected Boolean doInBackground(String[] objArr) {
        return m14465a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Boolean obj) {
        m14466a((Boolean) obj);
    }

    protected void onPreExecute() {
        this.f9508i.setContentText((CharSequence) "Download starting");
        this.f9504e = this.f9508i.build();
        this.f9509j.notify(this.f9502c, this.f9504e);
    }

    protected /* synthetic */ void onProgressUpdate(Integer[] objArr) {
        m14467a((Integer[]) objArr);
    }
}
