package calculator.vault.com.p034a;

import android.app.Activity;
import android.app.Dialog;
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

public class C1201b extends AsyncTask<Void, Integer, C2927f> {

    Dialog f3484a;
    ArrayList<String> f3485b;
    Activity f3486c;
    String f3487d;
    C2930c f3488e;
    C1044b f3489f;
    String f3490g;
    TextView f3491h;
    TextView f3492i;
    int f3493j;
    int f3494k;
    ProgressBar f3495l;
    int f3496m = 1;
    ArrayList<String> f3497n = new ArrayList();
    ArrayList<String> f3498o = new ArrayList();
    boolean f3499p;
    boolean f3500q;
    File f3501r;
    Button f3502s;
    C1625q f3503t;
    View f3504u;
    TextView f3505v;
    int f3506w = -1;
    OnClickListener f3507x = new C12005(this);

    class C11921 implements OnClickListener {
        final /* synthetic */ C1201b f3473a;

        C11921(C1201b c1201b) {
            this.f3473a = c1201b;
        }

        public void onClick(View view) {
            this.f3473a.f3500q = true;
        }
    }

    class C11983 implements Runnable {
        final /* synthetic */ C1201b f3481a;

        C11983(C1201b c1201b) {
            this.f3481a = c1201b;
        }

        public void run() {
            this.f3481a.f3491h.setText(this.f3481a.f3496m + "/" + this.f3481a.f3493j);
            C1201b c1201b = this.f3481a;
            c1201b.f3496m++;
        }
    }

    class C11994 implements OnClickListener {
        final /* synthetic */ C1201b f3482a;

        C11994(C1201b c1201b) {
            this.f3482a = c1201b;
        }

        public void onClick(View view) {
            if (this.f3482a.f3484a != null && this.f3482a.f3484a.isShowing()) {
                this.f3482a.f3484a.dismiss();
            }
            this.f3482a.f3500q = false;
            C3150b.m14840d(this.f3482a.f3486c);
        }
    }

    class C12005 implements OnClickListener {
        final /* synthetic */ C1201b f3483a;

        C12005(C1201b c1201b) {
            this.f3483a = c1201b;
        }

        public void onClick(View view) {
            C1131f.m5809b(this.f3483a.f3486c, this.f3483a.f3503t.f4625b);
        }
    }

    public C1201b(Activity activity, ArrayList<String> arrayList, String str, C2930c c2930c, C1044b c1044b, boolean z, C1625q c1625q) {
        this.f3485b = arrayList;
        this.f3486c = activity;
        this.f3488e = c2930c;
        this.f3487d = str;
        this.f3489f = c1044b;
        this.f3499p = z;
        this.f3503t = c1625q;
    }

    private C2927f m6037a(File file, File file2) throws IOException {
        String name = file2.getName();
        if (file2.exists()) {
            String[] split = name.split("\\.(?=[^\\.]+$)");
            String str = split[0];
            File file3 = new File(file2.getParent() + "/" + str + "_" + new Random().nextInt(123) + "." + split[1]);
            name = file3.getName();
        }
        InputStream fileInputStream = new FileInputStream(file);
        OutputStream fileOutputStream = new FileOutputStream(file2);
        this.f3501r = file;
        byte[] bArr = new byte[1024];
        this.f3506w = -1;
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
        } while (!this.f3500q);
        fileOutputStream.flush();
        fileOutputStream.close();
        fileInputStream.close();
        this.f3501r.delete();
        if (this.f3500q) {
            return C2927f.CANCELLED;
        }
        fileOutputStream.flush();
        fileOutputStream.close();
        fileInputStream.close();
        if (this.f3499p) {
            this.f3498o.add(file.getAbsolutePath());
        } else if (file.delete()) {
            C1131f.m5810b(this.f3486c, file, "image/*");
        } else if (FileUtils.deleteQuietly(file)) {
            C1131f.m5810b(this.f3486c, file, "image/*");
        }
        this.f3488e.m14130a(file2.getName(), file.getParent());
        this.f3497n.add(this.f3487d + "/" + name);
        return C2927f.SUCCESS;
    }

    protected C2927f doInBackground(Void... voidArr) {
        Iterator it = this.f3485b.iterator();
        while (it.hasNext()) {
            File file = new File((String) it.next());
            File file2 = new File(this.f3487d + "/" + file.getName());
            try {
                if (isCancelled()) {
                    return C2927f.CANCELLED;
                }
                C2927f a = m6037a(file, file2);
                if (a == C2927f.CANCELLED || a == C2927f.FAILED) {
                    return a;
                }
                this.f3486c.runOnUiThread(new C11983(this));
            } catch (IOException e) {
                this.f3494k++;
                e.printStackTrace();
            }
        }
        return C2927f.SUCCESS;
    }

    protected void onPostExecute(C2927f c2927f) {
        if (c2927f == C2927f.SUCCESS) {
            if (this.f3494k > 0) {
                this.f3493j -= this.f3494k;
            }
            if (this.f3493j == 0) {
                this.f3505v.setText(this.f3494k + " " + (this.f3494k > 1 ? "pictures" : "picture") + " failed to hide");
            } else {
                CharSequence charSequence;
                TextView textView = this.f3505v;
                if (this.f3493j > 1) {
                    charSequence = this.f3493j + " pictures were moved to secret locker.";
                } else {
                    String str;
                    StringBuilder append = new StringBuilder().append("One picture was moved to secret locker");
                    if (this.f3494k > 0) {
                        str = "\n" + this.f3494k + " " + (this.f3494k > 1 ? "pictures" : "picture") + " failed to hide";
                    } else {
                        str = "";
                    }
                    charSequence = append.append(str).toString();
                }
                textView.setText(charSequence);
            }
            this.f3495l.setProgress(100);
            this.f3492i.setText("100%");
            this.f3502s.setText("DONE");
            this.f3502s.setOnClickListener(new C11994(this));
            this.f3489f.mo965a(this.f3497n, this.f3498o);
        } else if (c2927f == C2927f.CANCELLED) {
            this.f3489f.mo965a(this.f3497n, this.f3498o);
            Toast.makeText(this.f3486c, "Operation Cancelled", Toast.LENGTH_SHORT).show();
            if (this.f3484a != null && this.f3484a.isShowing()) {
                this.f3484a.dismiss();
            }
        } else {
            this.f3489f.mo964a(this.f3490g);
            if (this.f3484a != null && this.f3484a.isShowing()) {
                this.f3484a.dismiss();
            }
        }
        super.onPostExecute(c2927f);
    }

    protected void onProgressUpdate(Integer... numArr) {
        int intValue = numArr[0].intValue();
        if (intValue - this.f3506w > 0) {
            this.f3495l.setProgress(intValue);
            this.f3492i.setText(intValue + "%");
            this.f3506w = intValue;
        }
        super.onProgressUpdate(numArr);
    }

    protected void onPreExecute() {
        this.f3484a = new ProgressDialog(this.f3486c);
        final View inflate = this.f3486c.getLayoutInflater().inflate(R.layout.progress_dialog, null);
        this.f3484a.show();
        this.f3484a.setContentView(inflate);
        this.f3484a.setCancelable(false);
        this.f3495l = (ProgressBar) inflate.findViewById(R.id.progressBar1);
        this.f3505v = (TextView) inflate.findViewById(R.id.tvTitle);
        this.f3505v.setTypeface(C1131f.f3315a);
        this.f3491h = (TextView) inflate.findViewById(R.id.tvCount);
        this.f3492i = (TextView) inflate.findViewById(R.id.tvProgress);
        this.f3493j = this.f3485b.size();
        this.f3491h.setText("1/" + this.f3493j);
        this.f3502s = (Button) inflate.findViewById(R.id.btnCancel);
        this.f3502s.setOnClickListener(new C11921(this));
        if (!(this.f3503t == null || this.f3499p)) {
            this.f3504u = inflate.findViewById(R.id.llAd);
            new Handler().postDelayed(new Runnable() {
                class C11962 implements OnClickListener {
                    class C11951 implements AnimationListener {
                        final /* synthetic */ C11962 f3477a;

                        C11951(C11962 c11962) {
                            this.f3477a = c11962;
                        }

                        public void onAnimationEnd(Animation animation) {
                            f3504u.setVisibility(View.GONE);
                        }

                        public void onAnimationRepeat(Animation animation) {
                        }

                        public void onAnimationStart(Animation animation) {
                        }
                    }

                    public void onClick(View view) {
                        Animation loadAnimation = AnimationUtils.loadAnimation(f3486c, R.anim.adview_shrink);
                        loadAnimation.setAnimationListener(new C11951(this));
                        f3504u.startAnimation(loadAnimation);
                    }
                }

                public void run() {
                    f3504u.setVisibility(View.VISIBLE);
//                    startAnimation(AnimationUtils.loadAnimation(f3486c, R.anim.adview_grow));
                    final ImageView imageView = (ImageView) inflate.findViewById(R.id.ivAd);
                    Glide.with(f3486c).load(f3503t.f4627d).listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            imageView.post(new C11931());
                            return false;
                        }


                        class C11931 implements Runnable {

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
                    Glide.with(f3486c).load(f3503t.f4628e).into((ImageView) inflate.findViewById(R.id.ivAdIcon));
                    ((TextView) inflate.findViewById(R.id.tvAppName)).setText(f3503t.f4624a);
                    ((TextView) inflate.findViewById(R.id.tvDesc)).setText(f3503t.f4626c);
                    imageView.setOnClickListener(f3507x);
                    inflate.findViewById(R.id.btnInstall).setOnClickListener(f3507x);
                    inflate.findViewById(R.id.btnCloseAd).setOnClickListener(new C11962());
                }
            }, 1000);
        }
        super.onPreExecute();
    }
}
