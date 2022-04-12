package calculator.vault.com.p073g;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

import calculator.vault.com.R;
import calculator.vault.com.applock.C3136a;
import calculator.vault.com.applock.C3150b;
import calculator.vault.com.lock.MainActivity;
import calculator.vault.com.p029h.C1016j;
import calculator.vault.com.p029h.C1672h;
import calculator.vault.com.p068a.ApplockAdapter;
import calculator.vault.com.p084i.C2928a;
import calculator.vault.com.p084i.C2929b;

public class ApplockerFragment extends Fragment implements ApplockAdapter.C1622e, ApplockAdapter.C1623f, C1016j {
    public static ApplockerFragment f4690f;
    Context f4691a;
    boolean ai;
    View aj;
    TextView f4692b;
    SharedPreferences sharedPreferences;
    Editor editor;
    C2928a f4695e;
    RecyclerView recyclerView;
    ApplockAdapter f4697h;
    C2929b f4698i;

    class C16731 implements C1672h {
        final /* synthetic */ ApplockerFragment f4685a;

        C16731(ApplockerFragment applockerFragment) {
            this.f4685a = applockerFragment;
        }

        public void mo1152a(View view, int i, ArrayList<C3136a> arrayList) {
            Iterator it = arrayList.iterator();
            int i2 = 0;
            while (it.hasNext()) {
                i2 = ((C3136a) it.next()).f9932h ? i2 + 1 : i2;
            }
            Iterator it2;
            C3136a c3136a;
            if (i2 == arrayList.size()) {
                it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    c3136a = (C3136a) it2.next();
                    c3136a.f9932h = false;
                    final C3136a finalC3136a1 = c3136a;
                    AsyncTask.execute(new Runnable() {
                        public void run() {
                            f4685a.f4695e.m14122b(finalC3136a1.f9926b, 0);
                        }
                    });
                }
            } else {
                it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    c3136a = (C3136a) it2.next();
                    c3136a.f9932h = true;
                    final C3136a finalC3136a = c3136a;
                    AsyncTask.execute(new Runnable() {

                        public void run() {
                            f4685a.f4695e.m14122b(finalC3136a.f9926b, 1);
                        }
                    });
                }
            }
            this.f4685a.f4697h.notifyDataSetChanged();
        }
    }

    class C16742 implements Runnable {
        final /* synthetic */ ApplockerFragment f4686a;

        C16742(ApplockerFragment applockerFragment) {
            this.f4686a = applockerFragment;
        }

        public void run() {
            if (this.f4686a.m7338b(new Intent("android.settings.USAGE_ACCESS_SETTINGS"))) {
                C3150b.m14828a(this.f4686a.getActivity());
            }
        }
    }

    @TargetApi(21)
    public void m7330N() {
        MainActivity.mainActivity.f2891h = true;
        Intent intent = new Intent("android.settings.USAGE_ACCESS_SETTINGS");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivityForResult(intent, 12345);
    }

    public void m7331O() {
        if (this.f4697h != null) {
            ArrayList arrayList = new ArrayList(this.f4697h.m7278c());
            if (!this.f4697h.m7278c().isEmpty()) {
                Iterator it = arrayList.iterator();
                Object obj = null;
                while (it.hasNext()) {
                    Object obj2;
                    C3136a c3136a = (C3136a) it.next();
                    if (c3136a.f9927c) {
                        this.f4697h.m7278c().remove(c3136a);
                        obj2 = 1;
                    } else {
                        obj2 = obj;
                    }
                    obj = obj2;
                }
                if (obj != null) {
                    this.f4697h.notifyDataSetChanged();
                }
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.aj == null) {
            this.aj = layoutInflater.inflate(R.layout.frag_apps, null);
            this.f4692b = (TextView) this.aj.findViewById(R.id.textView2);
            this.editor = this.sharedPreferences.edit();
            this.recyclerView = (RecyclerView) this.aj.findViewById(R.id.recycler_view);
            this.recyclerView.setLayoutManager(new LinearLayoutManager(this.f4691a));
            this.f4697h = new ApplockAdapter(this.f4691a, this.ai, this.f4698i);
            this.f4697h.m7274a((ApplockAdapter.C1622e) this);
            this.f4697h.m7276a(new C16731(this));
            this.recyclerView.setAdapter(this.f4697h);
            this.f4697h.m7275a((ApplockAdapter.C1623f) this);
            this.f4695e = C2928a.m14118a(this.f4691a);
            if (VERSION.SDK_INT >= 23) {
                this.f4695e.m14121a(getActivity().getPackageName(), 0);
            } else {
                this.f4695e.m14121a(getActivity().getPackageName(), 0);
            }
            if (VERSION.SDK_INT > 20 && !C3150b.m14835a(getActivity())) {
                new Handler().postDelayed(new C16742(this), 1000);
            }
        }
        return this.aj;
    }

    public void mo1154a() {
        this.f4692b.setVisibility(View.GONE);
        if (this.f4698i != null) {
//            new C3152d(MainActivity.mainActivity, this, "calc_gallerylock_applist_ads").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    public void onCreate(Bundle bundle) {
        this.f4691a = getActivity().getApplicationContext();
        f4690f = this;
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.f4691a);
        this.ai = this.sharedPreferences.getBoolean("hideAd", false);
        if (!this.ai) {
            this.f4698i = new C2929b(this.f4691a);
        }
        super.onCreate(bundle);
    }

    public void mo1155a(View view, int i) {
        if (!C3150b.m14835a(getActivity())) {
            C3150b.m14828a(getActivity());
            return;
        }

        int i2 = 1;
        final ImageView imageView = (ImageView) view.findViewById(R.id.applist_item_image);
        final C3136a a = this.f4697h.m7272a(i);
        if (a.m14822a()) {
            a.f9932h = !a.f9932h;
            Animation loadAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_lock_unlock);
            loadAnimation.setAnimationListener(new AnimationListener() {

                public void onAnimationEnd(Animation animation) {
                    imageView.setImageResource(a.f9932h ? R.drawable.app_lock : R.drawable.app_unlock);
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }
            });
            imageView.startAnimation(loadAnimation);
            C2928a c2928a = this.f4695e;
            String str = a.f9926b;
            if (!a.f9932h) {
                i2 = 0;
            }
            c2928a.m14122b(str, i2);
        }
    }

    public void mo954a(ArrayList<?> arrayList) {
        if (this.f4698i != null) {
            this.f4698i.m14125a();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                C3136a c3136a = (C3136a) it.next();
                this.f4698i.m14126a(c3136a.f9925a, c3136a.f9930f, c3136a.f9929e, c3136a.f9928d);
            }
        }
    }

    public void mo1156a(boolean z) {
    }

    public void a_() {
    }

    public boolean m7338b(Intent intent) {
        return this.f4691a.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0;
    }
}
