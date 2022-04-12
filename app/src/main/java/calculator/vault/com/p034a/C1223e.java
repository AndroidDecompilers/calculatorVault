package calculator.vault.com.p034a;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import calculator.vault.com.R;
import calculator.vault.com.applock.C3150b;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.p029h.C1044b;
import calculator.vault.com.p029h.C2927f;
import calculator.vault.com.p068a.C1625q;
import calculator.vault.com.p084i.C2930c;

public class C1223e extends AsyncTask<Void, Integer, C2927f> {
    ProgressDialog f3566a;
    ArrayList<String> f3567b;
    Activity f3568c;
    String f3569d;
    C2930c f3570e;
    C1044b f3571f;
    ArrayList<String> f3572g = new ArrayList();
    ArrayList<String> f3573h = new ArrayList();
    String f3574i;
    ProgressBar f3575j;
    TextView f3576k;
    TextView f3577l;
    int f3578m;
    int f3579n;
    boolean f3580o;
    boolean f3581p;
    File f3582q;
    C1625q f3583r;
    Button f3584s;
    View f3585t;
    TextView f3586u;
    int f3587v = 1;
    int f3588w = -1;
    OnClickListener f3589x = new C12225(this);

    class C12141 implements OnClickListener {
        final /* synthetic */ C1223e f3555a;

        C12141(C1223e c1223e) {
            this.f3555a = c1223e;
        }

        public void onClick(View view) {
            this.f3555a.f3581p = true;
        }
    }

    class C12203 implements Runnable {
        final /* synthetic */ C1223e f3563a;

        C12203(C1223e c1223e) {
            this.f3563a = c1223e;
        }

        public void run() {
            this.f3563a.f3576k.setText(this.f3563a.f3587v + "/" + this.f3563a.f3578m);
            C1223e c1223e = this.f3563a;
            c1223e.f3587v++;
        }
    }

    class C12214 implements OnClickListener {
        final /* synthetic */ C1223e f3564a;

        C12214(C1223e c1223e) {
            this.f3564a = c1223e;
        }

        public void onClick(View view) {
            if (this.f3564a.f3566a != null && this.f3564a.f3566a.isShowing()) {
                this.f3564a.f3566a.dismiss();
            }
            this.f3564a.f3581p = false;
            C3150b.m14840d(this.f3564a.f3568c);
        }
    }

    class C12225 implements OnClickListener {
        final /* synthetic */ C1223e f3565a;

        C12225(C1223e c1223e) {
            this.f3565a = c1223e;
        }

        public void onClick(View view) {
            C1131f.m5809b(this.f3565a.f3568c, this.f3565a.f3583r.f4625b);
        }
    }

    public C1223e(Activity activity, ArrayList<String> arrayList, String str, C2930c c2930c, C1044b c1044b, boolean z, C1625q c1625q) {
        this.f3567b = arrayList;
        this.f3568c = activity;
        this.f3570e = c2930c;
        this.f3569d = str;
        this.f3571f = c1044b;
        this.f3580o = z;
        this.f3583r = c1625q;
    }

    private C2927f m6053a(File file, File file2) throws IOException {
        String name = file2.getName();
        if (file2.exists()) {
            String[] split = name.split("\\.(?=[^\\.]+$)");
            String str = split[0];
            File file3 = new File(file2.getParent() + "/" + str + "_" + new Random().nextInt(123) + "." + split[1]);
            name = file3.getName();
        }
        InputStream fileInputStream = new FileInputStream(file);
        OutputStream fileOutputStream = new FileOutputStream(file2);
        this.f3582q = file;
        byte[] bArr = new byte[1024];
        this.f3588w = -1;
        long j = 0;
        int available = fileInputStream.available();
        publishProgress(new Integer[]{Integer.valueOf(0)});
        do {
            int read = fileInputStream.read(bArr);
            if (read == -1) {
                break;
            }
            j += (long) read;
            if (available > 0) {
                publishProgress(new Integer[]{Integer.valueOf((int) ((100 * j) / ((long) available)))});
            }
            fileOutputStream.write(bArr, 0, read);
        } while (!this.f3581p);
        fileOutputStream.flush();
        fileOutputStream.close();
        fileInputStream.close();
        this.f3582q.delete();
        if (this.f3581p) {
            return C2927f.CANCELLED;
        }
        fileOutputStream.flush();
        fileOutputStream.close();
        fileInputStream.close();
        if (this.f3580o) {
            this.f3573h.add(file.getAbsolutePath());
        } else if (file.delete()) {
            C1131f.m5810b(this.f3568c, file, "video/*");
        } else if (FileUtils.deleteQuietly(file)) {
            C1131f.m5810b(this.f3568c, file, "video/*");
        }
        this.f3570e.m14130a(file2.getName(), file.getParent());
        this.f3572g.add(this.f3569d + "/" + name);
        return C2927f.SUCCESS;
    }

    protected C2927f doInBackground(Void... voidArr) {
        Iterator it = this.f3567b.iterator();
        while (it.hasNext()) {
            File file = new File((String) it.next());
            File file2 = new File(this.f3569d + "/" + file.getName());
            try {
                if (isCancelled()) {
                    return C2927f.CANCELLED;
                }
                C2927f a = m6053a(file, file2);
                if (a == C2927f.CANCELLED || a == C2927f.FAILED) {
                    return a;
                }
                this.f3568c.runOnUiThread(new C12203(this));
            } catch (IOException e) {
                this.f3579n++;
                e.printStackTrace();
            }
        }
        return C2927f.SUCCESS;
    }

    protected void onPostExecute(C2927f c2927f) {
        if (c2927f == C2927f.SUCCESS) {
            if (this.f3579n > 0) {
                this.f3578m -= this.f3579n;
            }
            if (this.f3578m == 0) {
                this.f3586u.setText(this.f3579n + " " + (this.f3579n > 1 ? "videos" : "video") + " failed to hide");
            } else {
                CharSequence charSequence;
                TextView textView = this.f3586u;
                if (this.f3578m > 1) {
                    charSequence = this.f3578m + " videos were moved to secret locker.";
                } else {
                    String str;
                    StringBuilder append = new StringBuilder().append("One video was moved to secret locker");
                    if (this.f3579n > 0) {
                        str = "\n" + this.f3579n + " " + (this.f3579n > 1 ? "videos" : "video") + " failed to hide";
                    } else {
                        str = "";
                    }
                    charSequence = append.append(str).toString();
                }
                textView.setText(charSequence);
            }
            this.f3575j.setProgress(100);
            this.f3577l.setText("100%");
            this.f3584s.setText("DONE");
            this.f3584s.setOnClickListener(new C12214(this));
            this.f3571f.mo965a(this.f3572g, this.f3573h);
        } else if (c2927f == C2927f.CANCELLED) {
            this.f3571f.mo965a(this.f3572g, this.f3573h);
            Toast.makeText(this.f3568c, "Operation Cancelled", Toast.LENGTH_SHORT).show();
            if (this.f3566a != null && this.f3566a.isShowing()) {
                this.f3566a.dismiss();
            }
        } else {
            this.f3571f.mo964a(this.f3574i);
            if (this.f3566a != null && this.f3566a.isShowing()) {
                this.f3566a.dismiss();
            }
        }
        super.onPostExecute(c2927f);
    }

    protected void onProgressUpdate(Integer... numArr) {
        int intValue = numArr[0].intValue();
        if (intValue - this.f3588w > 0) {
            this.f3575j.setProgress(intValue);
            this.f3577l.setText(intValue + "%");
            this.f3588w = intValue;
        }
        super.onProgressUpdate(numArr);
    }


    protected void onPreExecute() {
        this.f3566a = new ProgressDialog(this.f3568c);
        final View inflate = this.f3568c.getLayoutInflater().inflate(R.layout.progress_dialog, null);
        this.f3566a.show();
        this.f3566a.setContentView(inflate);
        this.f3566a.setCancelable(false);
        this.f3575j = (ProgressBar) inflate.findViewById(R.id.progressBar1);
        this.f3586u = (TextView) inflate.findViewById(R.id.tvTitle);
        this.f3586u.setTypeface(C1131f.f3315a);
        this.f3576k = (TextView) inflate.findViewById(R.id.tvCount);
        this.f3577l = (TextView) inflate.findViewById(R.id.tvProgress);
        this.f3578m = this.f3567b.size();
        this.f3576k.setText("1/" + this.f3578m);
        this.f3584s = (Button) inflate.findViewById(R.id.btnCancel);
        this.f3584s.setOnClickListener(new C12141(this));
        if (!(this.f3583r == null || this.f3580o)) {
            this.f3585t = inflate.findViewById(R.id.llAd);
            new Handler().postDelayed(new Runnable() {
                class C12182 implements OnClickListener {
                    class C12171 implements AnimationListener {
                        final /* synthetic */ C12182 f3559a;

                        C12171(C12182 c12182) {
                            this.f3559a = c12182;
                        }

                        public void onAnimationEnd(Animation animation) {
                            f3585t.setVisibility(View.GONE);
                        }

                        public void onAnimationRepeat(Animation animation) {
                        }

                        public void onAnimationStart(Animation animation) {
                        }
                    }

                    public void onClick(View view) {
                        Animation loadAnimation = AnimationUtils.loadAnimation(f3568c, R.anim.adview_shrink);
                        loadAnimation.setAnimationListener(new C12171(this));
                        f3585t.startAnimation(loadAnimation);
                    }
                }

                public void run() {
                    f3585t.setVisibility(View.VISIBLE);
                    f3585t.startAnimation(AnimationUtils.loadAnimation(f3568c, R.anim.adview_grow));
                    final ImageView imageView = (ImageView) inflate.findViewById(R.id.ivAd);
                    Glide.with(f3568c).load(f3583r.f4627d).listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            imageView.post(new C12151());
                            return false;
                        }

                        class C12151 implements Runnable {


                            public void run() {
                                int width = imageView.getWidth();
                                int height = imageView.getHeight();
                                LayoutParams layoutParams = (LayoutParams) imageView.getLayoutParams();
                                if (C1131f.f3317c > C1131f.f3316b) {
                                    layoutParams.height = (width * 2) / 4;
                                } else {
                                    layoutParams.height = (height * 2) / 4;
                                }
                                layoutParams.width = width;
                                imageView.setLayoutParams(layoutParams);
                            }
                        }

                    }).into(imageView);
                    Glide.with(f3568c).load(f3583r.f4628e).into((ImageView) inflate.findViewById(R.id.ivAdIcon));
                    ((TextView) inflate.findViewById(R.id.tvAppName)).setText(f3583r.f4624a);
                    ((TextView) inflate.findViewById(R.id.tvDesc)).setText(f3583r.f4626c);
                    imageView.setOnClickListener(f3589x);
                    inflate.findViewById(R.id.btnInstall).setOnClickListener(f3589x);
                    inflate.findViewById(R.id.btnCloseAd).setOnClickListener(new C12182());
                }
            }, 1000);
        }
        super.onPreExecute();
    }
}
