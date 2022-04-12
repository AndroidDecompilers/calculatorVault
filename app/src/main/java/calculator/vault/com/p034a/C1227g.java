package calculator.vault.com.p034a;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import calculator.vault.com.p029h.C1016j;
import calculator.vault.com.p068a.C1625q;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;

public class C1227g extends AsyncTask<Void, Void, String> {

    JSONArray f3605a = null;
    Activity f3606b;
    C1016j f3607c;
    private String f3608d = "http://www.wondersoftwares.com/ads/ad_native.php?tableName=";

    public C1227g(Activity activity, C1016j c1016j, String str) {
        this.f3606b = activity;
        this.f3607c = c1016j;
        if (m6061a()) {
            this.f3608d += str;
        } else {
            this.f3607c.a_();
        }
    }

    private boolean m6061a() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f3606b.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    protected String doInBackground(Void... voidArr) {
        try {
            return new OkHttpClient().newCall(new Builder().url(this.f3608d).build()).execute().body().string();
        } catch (IOException e) {
            return null;
        }
    }

    protected void onPostExecute(String str) {
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.getInt("success") == 1) {
                    this.f3605a = jSONObject.getJSONArray("apps");
                    for (int i = 0; i < this.f3605a.length(); i++) {
                        jSONObject = this.f3605a.getJSONObject(i);
                        String string = jSONObject.getString("name");
                        String string2 = jSONObject.getString("link");
                        String string3 = jSONObject.getString("icon");
                        String string4 = jSONObject.getString("shortDesc");
                        String string5 = jSONObject.getString("imageurl");
                        String string6 = jSONObject.getString("isAlbumAd");
                        if (!string2.contains(this.f3606b.getPackageName())) {
                            C1625q c1625q = new C1625q(string, string4, string3, string5, string2);
                            c1625q.f4629f = Boolean.parseBoolean(string6);
                            arrayList.add(c1625q);
                        }
                    }
                    if (arrayList.size() > 0) {
                        this.f3607c.mo954a(arrayList);
                    } else {
                        this.f3607c.a_();
                    }
                }
            } catch (JSONException e) {
                this.f3607c.a_();
            } catch (Exception e2) {
                this.f3607c.a_();
            }
        }
        super.onPostExecute(str);
    }

}
