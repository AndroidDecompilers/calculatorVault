package calculator.vault.com.p068a;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import calculator.vault.com.R;
import calculator.vault.com.applock.C3136a;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.p029h.C1672h;
import calculator.vault.com.p084i.C2928a;
import calculator.vault.com.p084i.C2929b;


public class ApplockAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    C1672h f4613a;
    C2929b f4614b;
    C1623f f4615c;
    private final LayoutInflater f4616d;
    private final PackageManager f4617e;
    private final Context f4618f;
    private final Set<C3136a> f4619g = new HashSet();
    private List<C3136a> f4620h = new ArrayList();
    private boolean f4621i;
    private boolean f4622j;
    private C1622e f4623k;

    private class C1614a extends RecyclerView.ViewHolder {
        ImageView f4593a;
        ImageView f4594b;
        TextView f4595c;
        final /* synthetic */ ApplockAdapter f4596d;

        public C1614a(final ApplockAdapter applockAdapter, final View view) {
            super(view);

            this.f4596d = applockAdapter;
            this.f4593a = (ImageView) view.findViewById(R.id.applist_item_image);
            this.f4595c = (TextView) view.findViewById(R.id.listName);
            this.f4595c.setTypeface(C1131f.f3315a);
            this.f4594b = (ImageView) view.findViewById(R.id.listIcon);
            view.setOnClickListener(new OnClickListener() {

                public void onClick(View view) {
                    f4596d.f4615c.mo1155a(view, getAdapterPosition());
                }
            });
        }
    }

    private class C1616b extends RecyclerView.ViewHolder {
        TextView f4599a;
        ImageButton f4600b;
        final /* synthetic */ ApplockAdapter f4601c;

        public C1616b(final ApplockAdapter applockAdapter, View view) {
            super(view);
            this.f4601c = applockAdapter;
            this.f4600b = (ImageButton) view.findViewById(R.id.btnSelectAll);
            this.f4599a = (TextView) view.findViewById(R.id.listName);
            this.f4599a.setTypeface(C1131f.f3315a);
            this.f4600b.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    int i = ((C3136a) f4601c.f4620h.get(getAdapterPosition() + 1)).f9931g;
                    ArrayList arrayList = new ArrayList();
                    for (C3136a c3136a : f4601c.f4620h) {
                        if (c3136a.m14822a() && c3136a.f9931g == i) {
                            arrayList.add(c3136a);
                        }
                    }
                    f4601c.f4613a.mo1152a(view, i, arrayList);
                }
            });
        }
    }

    private class C1618c extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ ApplockAdapter f4603a;

        class C16171 extends AsyncTask<Void, Void, ArrayList<C3136a>> {
            final /* synthetic */ C1618c f4602a;

            C16171(C1618c c1618c) {
                this.f4602a = c1618c;
            }

            protected ArrayList<C3136a> doInBackground(Void... voidArr) {
                return this.f4602a.f4603a.f4614b.m14123a(this.f4602a.f4603a.f4618f.getPackageManager());
            }

            protected void onPostExecute(ArrayList<C3136a> arrayList) {
                if (!arrayList.isEmpty()) {
                    int i = 0;
                    for (int i2 = 10; i2 < this.f4602a.f4603a.f4620h.size() && i < arrayList.size(); i2 += 10) {
                        this.f4602a.f4603a.f4620h.add(i2, arrayList.get(i));
                        i++;
                    }
                }
                if (this.f4602a.f4603a.f4623k != null) {
                    this.f4602a.f4603a.f4623k.mo1154a();
                }
                super.onPostExecute(arrayList);
            }
        }

        private C1618c(ApplockAdapter applockAdapter) {
            this.f4603a = applockAdapter;
        }

        protected Void doInBackground(Void... voidArr) {
            this.f4603a.m7273a();
            return null;
        }

        protected void onPostExecute(Void voidR) {
            this.f4603a.m7277b();
            if (!this.f4603a.f4622j) {
                new C16171(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
            } else if (this.f4603a.f4623k != null) {
                this.f4603a.f4623k.mo1154a();
            }
        }
    }

    private class C1621d extends RecyclerView.ViewHolder {
        ImageView f4608a;
        TextView f4609b;
        TextView f4610c;
        Button f4611d;
        final /* synthetic */ ApplockAdapter f4612e;

        public C1621d(final ApplockAdapter applockAdapter, View view) {
            super(view);
            this.f4612e = applockAdapter;
            this.f4609b = (TextView) view.findViewById(R.id.tvAppName);
            this.f4610c = (TextView) view.findViewById(R.id.tvShortDesc);
            this.f4608a = (ImageView) view.findViewById(R.id.ivAdIcon);
            this.f4611d = (Button) view.findViewById(R.id.btnInstall);
            this.f4611d.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    C3136a c3136a = (C3136a) f4612e.f4620h.get(getAdapterPosition());
                    f4612e.m7266a(c3136a.f9925a, c3136a.f9930f);
                }
            });
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    C3136a c3136a = (C3136a) f4612e.f4620h.get(getAdapterPosition());
                    f4612e.m7266a(c3136a.f9925a, c3136a.f9930f);
                }
            });
        }
    }

    public interface C1622e {
        void mo1154a();

        void mo1156a(boolean z);
    }

    public interface C1623f {
        void mo1155a(View view, int i);
    }

    public ApplockAdapter(Context context, boolean z, C2929b c2929b) {
        this.f4618f = context;
        this.f4622j = z;
        this.f4614b = c2929b;
        this.f4616d = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.f4617e = context.getPackageManager();
        new C1618c(this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, (Void[]) null);
    }

    @SuppressLint({"NewApi"})
    private void m7264a(View view, Drawable drawable) {
        if (VERSION.SDK_INT < 16) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }

    private void m7266a(String str, String str2) {
        Intent intent;
        try {
            intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str2.substring(str2.lastIndexOf("?id=") + 4, str2.length())));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.f4618f.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.f4618f.startActivity(intent);
        }
    }

    private void m7267a(Collection<C3136a> collection, C2928a c2928a) {
        List arrayList = new ArrayList(SecurityHelpers.f10003e.keySet());
        List asList = Arrays.asList(new String[]{"com.android.vending", "com.android.settings"});
        List asList2 = Arrays.asList(new String[]{"com.android.dialer"});
        PackageManager packageManager = this.f4618f.getPackageManager();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (ApplicationInfo applicationInfo : packageManager.getInstalledApplications(0)) {
            int i4;
            if (arrayList.contains(applicationInfo.packageName)) {
                String charSequence = applicationInfo.loadLabel(packageManager).toString();
                if (charSequence.equals("WhatsApp")) {
                    charSequence = " WhatsApp";
                }
                collection.add(new C3136a(charSequence, applicationInfo, 8));
                i = 1;
            }
            if (asList.contains(applicationInfo.packageName)) {
                collection.add(new C3136a(applicationInfo.loadLabel(packageManager).toString(), applicationInfo, 6));
                i2 = 1;
            }
            if (asList2.contains(applicationInfo.packageName)) {
                collection.add(new C3136a(applicationInfo.loadLabel(packageManager).toString(), applicationInfo, 4));
                i4 = 1;
            } else {
                i4 = i3;
            }
            collection.add(new C3136a(this.f4618f.getString(R.string.applist_tit_apps), 3));
            if (i != 0) {
                collection.add(new C3136a(this.f4618f.getString(R.string.applist_tit_social), 9));
            }
            if (i2 != 0) {
                collection.add(new C3136a(this.f4618f.getString(R.string.applist_tit_important), 7));
            }
            if (i4 != 0) {
                collection.add(new C3136a(this.f4618f.getString(R.string.applist_tit_system), 5));
            }
            i3 = i4;
        }
    }

    private void m7268a(boolean z) {
        if (this.f4621i != z) {
            this.f4621i = z;
            if (this.f4623k != null) {
                this.f4623k.mo1156a(z);
            }
        }
    }

    public C3136a m7272a(int i) {
        return (C3136a) this.f4620h.get(i);
    }

    void m7273a() {
        C2928a a = C2928a.m14118a(this.f4618f);
        m7267a(this.f4619g, a);
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        for (ResolveInfo resolveInfo : this.f4617e.queryIntentActivities(intent, 0)) {
            if (!this.f4618f.getPackageName().equals(resolveInfo.activityInfo.packageName)) {
                C3136a c3136a = new C3136a(resolveInfo.loadLabel(this.f4617e).toString(), resolveInfo.activityInfo, 1);
                this.f4619g.add(c3136a);
                a.m14121a(c3136a.f9926b, 0);
            }
        }
        a.m14121a("com.wifi", 0);
        a.m14121a("com.bt", 0);
        HashSet a2 = a.m14119a();
        for (C3136a c3136a2 : this.f4619g) {
            c3136a2.f9932h = a2.contains(c3136a2.f9926b);
        }
        this.f4620h = new ArrayList(this.f4619g);
    }

    public void m7274a(C1622e c1622e) {
        this.f4623k = c1622e;
    }

    public void m7275a(C1623f c1623f) {
        this.f4615c = c1623f;
    }

    public void m7276a(C1672h c1672h) {
        this.f4613a = c1672h;
    }

    public void m7277b() {
        Collections.sort(this.f4620h);
        notifyDataSetChanged();
        m7268a(false);
    }

    public List<C3136a> m7278c() {
        return this.f4620h;
    }

    public int getItemCount() {
        return this.f4620h.size();
    }

    public int getItemViewType(int i) {
        C3136a c3136a = (C3136a) this.f4620h.get(i);
        return c3136a.m14822a() ? 0 : c3136a.f9927c ? 2 : 1;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder c0823u, final int i) {
        C3136a c3136a = (C3136a) this.f4620h.get(i);
        switch (getItemViewType(i)) {
            case 0:
                C1614a c1614a = (C1614a) c0823u;
                c1614a.f4593a.setImageResource(c3136a.f9932h ? R.drawable.app_lock : R.drawable.app_unlock);
                c1614a.f4595c.setText(c3136a.m14823b(this.f4617e));
                Drawable a = c3136a.m14821a(this.f4617e);
                if (a == null) {
                    c1614a.f4594b.setVisibility(View.GONE);
                    return;
                } else {
                    m7264a(c1614a.f4594b, a);
                    return;
                }
            case 1:
                C1616b c1616b = (C1616b) c0823u;
                c1616b.f4599a.setText(c3136a.f9925a);
                c1616b.f4600b.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        int ii = ((C3136a) f4620h.get(i + 1)).f9931g;
                        ArrayList arrayList = new ArrayList();
                        for (C3136a c3136a : f4620h) {
                            if (c3136a.m14822a() && c3136a.f9931g == ii) {
                                arrayList.add(c3136a);
                            }
                        }
                        f4613a.mo1152a(view, ii, arrayList);
                    }
                });
                return;
            case 2:
                C1621d c1621d = (C1621d) c0823u;
                Glide.with(this.f4618f).load(c3136a.f9928d).into(c1621d.f4608a);
                c1621d.f4609b.setText("" + c3136a.f9925a);
                c1621d.f4610c.setText("" + c3136a.f9929e);
                return;
            default:
                return;
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return new C1614a(this, this.f4616d.inflate(R.layout.raw_item_applist, viewGroup, false));
            case 1:
                return new C1616b(this, this.f4616d.inflate(R.layout.raw_category_applist, viewGroup, false));
            case 2:
                return new C1621d(this, this.f4616d.inflate(R.layout.raw_ad_applist, viewGroup, false));
            default:
                return new C1614a(this, this.f4616d.inflate(R.layout.raw_item_applist, viewGroup, false));
        }
    }
}
