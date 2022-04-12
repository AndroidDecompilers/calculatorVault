package calculator.vault.com.p073g;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.KeyguardManager;
import android.app.PendingIntent;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.GradientDrawable;
import android.hardware.fingerprint.FingerprintManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SwitchCompat;
import android.util.Patterns;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import calculator.vault.com.R;
import calculator.vault.com.applock.AlarmReciever;
import calculator.vault.com.applock.DisguisedActivity;
import calculator.vault.com.applock.MyAppLockService;
import calculator.vault.com.applock.SecurityHelpers;
import calculator.vault.com.applock.Temporary_view_Service;
import calculator.vault.com.deviceadmin.MyAdmin;
import calculator.vault.com.lock.C1126c;
import calculator.vault.com.lock.C1131f;
import calculator.vault.com.lock.ColorThemeActivity;
import calculator.vault.com.lock.FakePinActivity;
import calculator.vault.com.lock.IntruderActivity;
import calculator.vault.com.lock.MainActivity;
import calculator.vault.com.lock.SetAppPasswordActivity;
import calculator.vault.com.lock.SetEmailActivity;
import calculator.vault.com.lock.UninstallProtectionActivity;
import calculator.vault.com.p068a.C1576c;
import calculator.vault.com.p084i.C2928a;
import haibison.android.lockpattern.LockPatternActivity;


@TargetApi(21)
public class C1712c extends android.support.v4.app.Fragment implements OnClickListener, OnCheckedChangeListener, C1126c.C1125a {
    SharedPreferences f4799a;
    boolean aA = false;
    boolean aB;
    String aC;
    RelativeLayout aD;
    boolean aE;
    private String aF;
    private FingerprintManager aG;
    private KeyguardManager aH;
    private C1700a aI = new C1700a() {
        public void mo1159a() {
            aD.setVisibility(View.GONE);
        }

        public void mo1160a(String str) {
            Toast.makeText(getActivity().getApplicationContext(), str, Toast.LENGTH_SHORT).show();
        }
    };
    RelativeLayout ai;
    Context context;
    Editor ak;
    TextView al;
    TextView am;
    TextView an;
    TextView ao;
    TextView ap;
    TextView aq;
    TextView ar;
    TextView as;
    TextView at;
    TextView au;
    boolean av;
    boolean aw;
    CharSequence[] ax = new CharSequence[]{"None", "Random", "Accordion", "Background To Foreground", "Cube Out", "Depth Page", "Draw From Back", "Flip Horizontal", "Flip Vertical", "Foreground To Background", "Rotate Down", "Rotate Up", "Stack", "Tablet Transformer", "Zoom In", "ZoomOut Slide", "Zoom Out"};
    public int ay;
    public int az;
    SwitchCompat f4800b;
    SwitchCompat f4801c;
    SwitchCompat f4802d;
    SwitchCompat f4803e;
    SwitchCompat f4804f;
    SwitchCompat f4805g;
    SwitchCompat f4806h;
    RelativeLayout f4807i;

    public interface C1700a {
        void mo1159a();

        void mo1160a(String str);
    }

    class C17031 implements OnCheckedChangeListener {
        final /* synthetic */ C1712c f4788a;

        C17031(C1712c c1712c) {
            this.f4788a = c1712c;
        }

        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            this.f4788a.ak.putBoolean("uninstallProtection", z);
            this.f4788a.ak.commit();
            String str = VERSION.SDK_INT >= 23 ? "com.google.android.packageinstaller" : "com.android.packageinstaller";
            C2928a.m14118a(this.f4788a.context).m14122b(str, z ? 1 : 0);
            if (z) {
                if (MyAppLockService.f9890c != null) {
                    MyAppLockService.f9890c.add(str);
                }
            } else if (MyAppLockService.f9890c != null) {
                MyAppLockService.f9890c.remove(str);
            }
        }
    }

    class C17042 implements DialogInterface.OnClickListener {
        final /* synthetic */ C1712c f4789a;

        C17042(C1712c c1712c) {
            this.f4789a = c1712c;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            switch (i) {
                case 0:
                    this.f4789a.ak.putBoolean("immediately", true);
                    this.f4789a.an.setText("Immediately");
                    break;
                case 1:
                    this.f4789a.ak.putBoolean("immediately", false);
                    this.f4789a.an.setText("After screen turns off");
                    break;
            }
            this.f4789a.ak.commit();
        }
    }

    class C17075 implements DialogInterface.OnClickListener {

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            if (ap.getVisibility() == View.VISIBLE) {
                ap.setVisibility(View.GONE);
                ak.putBoolean("tvNewAccess", false);
                ak.commit();
            }
            C1712c c1712c = C1712c.this;
            MainActivity.mainActivity.f2891h = true;
            c1712c.aE = true;
            startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
            getActivity().startService(new Intent(getActivity(), Temporary_view_Service.class));
        }
    }

    class C17086 implements DialogInterface.OnClickListener {
        final /* synthetic */ C1712c f4795a;

        C17086(C1712c c1712c) {
            this.f4795a = c1712c;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    }

    class C17097 implements OnClickListener {
        final /* synthetic */ C1712c f4796a;

        C17097(C1712c c1712c) {
            this.f4796a = c1712c;
        }

        public void onClick(View view) {
            this.f4796a.m7391R();
        }
    }

    class C17108 implements OnClickListener {
        final /* synthetic */ C1712c f4797a;

        C17108(C1712c c1712c) {
            this.f4797a = c1712c;
        }

        public void onClick(View view) {
            this.f4797a.m7392S();
        }
    }

    class C17119 implements DialogInterface.OnClickListener {
        final /* synthetic */ C1712c f4798a;

        C17119(C1712c c1712c) {
            this.f4798a = c1712c;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
            this.f4798a.as.setText(this.f4798a.ax[i]);
            this.f4798a.ak.putInt("trans", i);
            this.f4798a.ak.commit();
        }
    }

    @TargetApi(21)
    private void m7389P() {
        Builder builder = new Builder(MainActivity.mainActivity);
        builder.setTitle("Select Transition");
        builder.setItems(this.ax, new C17119(this));
        builder.create().show();
    }

    private void m7390Q() {
        final CharSequence[] charSequenceArr = new CharSequence[]{"1 sec", "2 sec", "4 sec", "8 sec", "20 sec", "40 sec", "1 min", "5 min", "10 min"};
        final int[] iArr = new int[]{1, 2, 4, 8, 20, 40, 60, 300, 600};
        Builder builder = new Builder(getActivity());
        builder.setTitle("Select Slideshow Interval");
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                al.setText(charSequenceArr[i]);
                ak.putInt("interval", iArr[i]);
                ak.commit();
            }
        });
        builder.create().show();
    }

    private void m7391R() {
        CharSequence[] charSequenceArr = new CharSequence[]{"Enable App Lock", "Disable for 1 hour", "Disable for 3 hour", "Disable for 6 hour", "Disable for 9 hour", "Disable for 12 hour"};
        final long[] jArr = new long[]{0, 3600000, 10800000, 21600000, 32400000, 43200000};
        Builder builder = new Builder(getActivity());
        builder.setTitle("Set Time");
        builder.setItems(charSequenceArr, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        PendingIntent broadcast = PendingIntent.getBroadcast(context, 1, new Intent(context, AlarmReciever.class), PendingIntent.FLAG_UPDATE_CURRENT);
                        broadcast.cancel();
                        ((AlarmManager) context.getSystemService(Context.ALARM_SERVICE)).cancel(broadcast);
                        am.setText("App Lock Enabled");
                        ak.putBoolean("startApplock", true);
                        ak.putBoolean("isFrozen", false);
                        ak.commit();
                        context.startService(new Intent(context, MyAppLockService.class));
                        context.sendBroadcast(new Intent(SecurityHelpers.f9999a));
                        return;
                    default:
                        m7405a(System.currentTimeMillis() + jArr[i]);
                        ak.putBoolean("startApplock", false);
                        ak.putBoolean("isFrozen", true);
                        ak.commit();
                        context.sendBroadcast(new Intent(SecurityHelpers.f10000b));
                        return;
                }
            }
        });
        builder.create().show();
    }

    private void m7392S() {
        CharSequence[] charSequenceArr = new CharSequence[]{"Immediately", "After screen turns off"};
        Builder builder = new Builder(getActivity());
        builder.setTitle("Re-Lock App");
        builder.setItems(charSequenceArr, new C17042(this));
        builder.create().show();
    }

    private void m7393T() {
        final Dialog dialog = new Dialog(getActivity());
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.exit_dialog, null);
        dialog.setContentView(inflate);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_dialogText);
        ((TextView) inflate.findViewById(R.id.tvOk)).setText("DISABLE");
        textView.setTypeface(C1131f.f3315a);
        textView.setTextSize(SecurityHelpers.m14847a(8.0f, this.context));
        textView.setText("Disable accessibily service for app lock? It may cause less speedy app lock engine and increase battery usage.");
        inflate.findViewById(R.id.rlExit).setOnClickListener(new OnClickListener() {

            public void onClick(View view) {
                C1712c c1712c = C1712c.this;
                MainActivity.mainActivity.f2891h = true;
                c1712c.aE = true;
                startActivity(new Intent("android.settings.ACCESSIBILITY_SETTINGS"));
                dialog.dismiss();
            }
        });
        inflate.findViewById(R.id.rlCancel).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void m7394U() {
        Builder builder = new Builder(getActivity());
        builder.setTitle("Disclosure");
        builder.setMessage("By enable this service app lock will save battery power and help users with disabilities to remind unlock app. Gallery Lock never use any private information from this service.");
        builder.setPositiveButton("ENABLE", new C17075());
        builder.setNegativeButton("CANCEL", new C17086(this));
        builder.create().show();
    }

    public void m7399N() {
        ListAdapter c1576c = new C1576c(MainActivity.mainActivity, m7407b(MainActivity.mainActivity), MainActivity.mainActivity.getPackageManager());
        final Dialog dialog = new Dialog(MainActivity.mainActivity);
        dialog.setContentView(R.layout.app_layout);
        ListView listView = (ListView) dialog.findViewById(R.id.lvApp);
        listView.setAdapter(c1576c);
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ApplicationInfo applicationInfo = (ApplicationInfo) ((C1576c) adapterView.getAdapter()).getItem(i);
                aF = applicationInfo.packageName;
                ak.putString("Package_Name", aF);
                ak.putString("app_name", "" + applicationInfo.loadLabel(MainActivity.mainActivity.getPackageManager()));
                aA = true;
                az = 1;
                ak.putInt("selectedPos", az);
                ak.commit();
                MainActivity.mainActivity.m5621a(az);
                dialog.dismiss();
                at.setText(getActivity().getString(R.string.action_another_app) + f4799a.getString("app_name", ""));
            }
        });
        dialog.show();
    }

    public void m7400O() {
        Intent intent = new Intent(LockPatternActivity.ACTION_CREATE_PATTERN, null, getContext(), LockPatternActivity.class);
        try {
            TypedValue typedValue = new TypedValue();
            getActivity().getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
            int i = typedValue.data;
            TypedValue typedValue2 = new TypedValue();
            getActivity().getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue2, true);
            int i2 = typedValue2.data;
            intent.putExtra("colorCode", i);
            intent.putExtra("colorCodeDark", i2);
        } catch (Exception e) {
        }
        startActivityForResult(intent, 1);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.frag_setting, null);
        this.f4799a = PreferenceManager.getDefaultSharedPreferences(this.context);
        this.ak = this.f4799a.edit();
        this.f4807i = (RelativeLayout) inflate.findViewById(R.id.rl_changePassword);
        this.ai = (RelativeLayout) inflate.findViewById(R.id.rlFingerPrint);
        this.f4807i.setOnClickListener(this);
        inflate.findViewById(R.id.rl_uninstall_protection).setOnClickListener(this);
        inflate.findViewById(R.id.rl_rateApp).setOnClickListener(this);
        inflate.findViewById(R.id.rl_interval).setOnClickListener(this);
        inflate.findViewById(R.id.rl_recovery).setOnClickListener(this);
        inflate.findViewById(R.id.rl_shareApp).setOnClickListener(this);
        inflate.findViewById(R.id.rl_likeUs).setOnClickListener(this);
        inflate.findViewById(R.id.rl_Follow).setOnClickListener(this);
        inflate.findViewById(R.id.rl_Contact).setOnClickListener(this);
        inflate.findViewById(R.id.rl_fake_icon).setOnClickListener(this);
        inflate.findViewById(R.id.rlLockNew).setOnClickListener(this);
        inflate.findViewById(R.id.rl_pattern).setOnClickListener(this);
        inflate.findViewById(R.id.rl_fake).setOnClickListener(this);
        inflate.findViewById(R.id.rl_intruder).setOnClickListener(this);
        inflate.findViewById(R.id.rlFacedown).setOnClickListener(this);
        inflate.findViewById(R.id.rlfdAction).setOnClickListener(this);
        inflate.findViewById(R.id.rlDisguise).setOnClickListener(this);
        inflate.findViewById(R.id.rlSound).setOnClickListener(this);
        inflate.findViewById(R.id.rlVibration).setOnClickListener(this);
        inflate.findViewById(R.id.rlNeedEqual).setOnClickListener(this);
        inflate.findViewById(R.id.rlThemes).setOnClickListener(this);
        this.am = (TextView) inflate.findViewById(R.id.tvFreezeTime);
        this.an = (TextView) inflate.findViewById(R.id.tvImmediately);
        this.ao = (TextView) inflate.findViewById(R.id.tvPattern);
        this.ao.setTypeface(C1131f.f3315a);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.ivThemeColor);
        GradientDrawable gradientDrawable = (GradientDrawable) imageView.getBackground();
        gradientDrawable.setColor(this.f4799a.getInt("colorPrimary", ContextCompat.getColor(getActivity(), R.color.toolbar_color)));
        if (VERSION.SDK_INT >= 16) {
            imageView.setBackground(gradientDrawable);
        } else {
            imageView.setBackgroundDrawable(gradientDrawable);
        }
        if (this.f4799a.getBoolean("isFrozen", false)) {
            this.am.setText("Disabled until " + this.f4799a.getString("frozenTime", ""));
        }
        boolean z = this.f4799a.getBoolean("isPatternSet", false);
        this.av = z;
        if (z) {
            this.ao.setText("Change Backup Pattern");
        }
        this.an.setText(this.f4799a.getBoolean("immediately", true) ? "Immediately" : "After screen turns off");
        this.an.setTypeface(C1131f.f3315a);
        this.am.setTypeface(C1131f.f3315a);
        this.ap = (TextView) inflate.findViewById(R.id.tvNewAccess);
        this.aq = (TextView) inflate.findViewById(R.id.tvNewDisg);
//        this.ap.setVisibility(this.f4799a.getBoolean("tvNewAccess", true) ? View.VISIBLE : View.GONE);
//        this.aq.setVisibility(this.f4799a.getBoolean("tvNewDisg", true) ? View.VISIBLE : View.GONE);
        this.ar = (TextView) inflate.findViewById(R.id.tvAccessibility);
        this.aw = SecurityHelpers.m14856d(this.context);
        this.ar.setText(this.aw ? "Enabled" : "Decrease battery usage by 50% and enable improved lock engine");
        this.ak.putBoolean("isAccess", this.aw);
        this.ak.commit();
        this.aD = (RelativeLayout) inflate.findViewById(R.id.rlAdFree);
        this.aD.setOnClickListener(this);
        if (this.f4799a.getBoolean("hideAd", false) || !C1131f.f3321g) {
            this.aD.setVisibility(View.GONE);
        }
        this.f4801c = (SwitchCompat) inflate.findViewById(R.id.uninstall_btn);
        this.f4802d = (SwitchCompat) inflate.findViewById(R.id.lock_new_btn);
        this.f4804f = (SwitchCompat) inflate.findViewById(R.id.sound_btn);
        this.f4805g = (SwitchCompat) inflate.findViewById(R.id.vib_btn);
        this.f4806h = (SwitchCompat) inflate.findViewById(R.id.equal_btn);
        this.f4802d.setChecked(this.f4799a.getBoolean("instaLock", false));
        this.f4805g.setChecked(this.f4799a.getBoolean("isVib", false));
        this.f4806h.setChecked(this.f4799a.getBoolean("needEqual", false));
        this.f4804f.setChecked(this.f4799a.getBoolean("isSound", true));
        this.f4801c.setChecked(this.f4799a.getBoolean("uninstallProtection", false));
        this.f4804f.setOnCheckedChangeListener(this);
        this.f4806h.setOnCheckedChangeListener(this);
        this.f4805g.setOnCheckedChangeListener(this);
        this.au = (TextView) inflate.findViewById(R.id.tvRecommended);
        this.au.setTypeface(C1131f.f3315a);
        try {
            DevicePolicyManager devicePolicyManager = (DevicePolicyManager) this.context.getSystemService(Context.DEVICE_POLICY_SERVICE);
            ComponentName componentName = new ComponentName(this.context, MyAdmin.class);
            if (devicePolicyManager != null && devicePolicyManager.isAdminActive(componentName)) {
                this.au.setVisibility(View.GONE);
            }
        } catch (Exception e) {
        }
        this.f4801c.setOnCheckedChangeListener(new C17031(this));
        inflate.findViewById(R.id.rlFreeze).setOnClickListener(new C17097(this));
        inflate.findViewById(R.id.rlRelock).setOnClickListener(new C17108(this));
        ((TextView) inflate.findViewById(R.id.tvAdFree)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.textView1)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvInterval)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvRecovery)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvRelock)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvFake)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvFakeIcon)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvIntruder)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvChangePasscode)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvUninstall)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvFinger)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.textView3)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvAccess)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvAccessibility)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvUnlockPrevent)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvRequire)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvFreeze)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvRate)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvTellFriends)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvLikeUs)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvLockNew)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvLockNew1)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvFollow)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvTransition)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvFaceDown)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvfd)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvFdAction)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvTransition)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvDisguise)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvSound)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvsound1)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.textView443)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvEqual)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.tvThemes)).setTypeface(C1131f.f3315a);
        this.as = (TextView) inflate.findViewById(R.id.tvTransType);
        this.at = (TextView) inflate.findViewById(R.id.tvDownAction);
        this.as.setTypeface(C1131f.f3315a);
        this.at.setTypeface(C1131f.f3315a);
        this.as.setText(this.ax[this.f4799a.getInt("trans", 0)]);
        ((TextView) inflate.findViewById(R.id.tvContact)).setTypeface(C1131f.f3315a);
        inflate.findViewById(R.id.rlTransition).setOnClickListener(this);
        this.al = (TextView) inflate.findViewById(R.id.tvIntervalTime);
        this.al.setTypeface(C1131f.f3315a);
        this.al.setText(this.f4799a.getInt("interval", 2) + " sec");
        this.f4800b = (SwitchCompat) inflate.findViewById(R.id.finger_btn);
        this.f4803e = (SwitchCompat) inflate.findViewById(R.id.facedown_btn);
        this.f4803e.setOnCheckedChangeListener(this);
        this.aB = this.f4799a.getBoolean("faceDown", false);
        this.f4803e.setChecked(this.aB);
        inflate.findViewById(R.id.rlUninstallProtection).setOnClickListener(this);
        inflate.findViewById(R.id.rlAccess).setOnClickListener(this);
        if (VERSION.SDK_INT >= 23) {
            this.aH = (KeyguardManager) this.context.getSystemService(Context.KEYGUARD_SERVICE);
            this.aG = (FingerprintManager) this.context.getSystemService(Context.FINGERPRINT_SERVICE);
            if (!this.aG.isHardwareDetected()) {
                this.ai.setVisibility(View.GONE);
            } else if (this.aH.isKeyguardSecure() && this.aG.hasEnrolledFingerprints()) {
                this.ai.setOnClickListener(this);
                this.f4800b.setChecked(this.f4799a.getBoolean("isFinger", false));
                this.f4800b.setOnCheckedChangeListener(this);
            } else {
                this.ai.setVisibility(View.GONE);
            }
        } else {
            this.ai.setVisibility(View.GONE);
        }
        this.az = this.f4799a.getInt("selectedPos", 0);
        TextView textView = this.at;
        CharSequence string = this.az == 0 ? getActivity().getString(R.string.action_close_app) : this.az == 1 ? getActivity().getString(R.string.action_another_app) + this.f4799a.getString("app_name", "") : getActivity().getString(R.string.action_website) + this.f4799a.getString("URL_Name", "");
        textView.setText(string);
        return inflate;
    }

    @TargetApi(21)
    public void m7402a() {
        Builder builder = new Builder(MainActivity.mainActivity);
        builder.setTitle("New Website");
        builder.setMessage("Enter your URL here").setPositiveButton("OK", null).setNegativeButton("CANCEL", null);
        builder.setCancelable(false);
        final EditText editText = new EditText(MainActivity.mainActivity);
        editText.setTextColor(-16777216);
        editText.setTypeface(C1131f.f3315a);
        editText.setInputType(33);
        final AlertDialog create = builder.create();
        create.setView(editText);
        create.setOnShowListener(new OnShowListener() {
            public void onShow(final DialogInterface dialogInterface) {
                create.getButton(-1).setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        String obj = editText.getEditableText().toString();
                        if (Patterns.WEB_URL.matcher(obj).matches()) {
                            if (!(obj.startsWith("https://") || obj.startsWith("http://"))) {
                                obj = "http://" + obj;
                                aC = obj;
                            }
                            dialogInterface.dismiss();
                            az = 2;
                            MainActivity.mainActivity.m5621a(az);
                            ak.putString("URL_Name", aC);
                            ak.putInt("selectedPos", az);
                            ak.commit();
                            at.setText(getActivity().getString(R.string.action_website) + obj);
                            return;
                        }
                        Toast.makeText(MainActivity.mainActivity, "INVALID URL", Toast.LENGTH_SHORT).show();
                        aA = false;
                        ay = 2;
                    }
                });
                create.getButton(-2).setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        create.dismiss();
                        aA = false;
                    }
                });
            }
        });
        create.show();
    }

    public void mo1161a(int i) {
        switch (i) {
            case 0:
                this.az = 0;
                this.ak.putString("CloseAppIns", "CLS_APP");
                this.ak.putInt("selectedPos", this.az);
                this.ak.commit();
                this.at.setText(getActivity().getString(R.string.action_close_app));
                MainActivity.mainActivity.m5621a(this.az);
                return;
            case 1:
                m7399N();
                if (this.aA) {
                    this.ay = this.az;
                    return;
                }
                return;
            case 2:
                m7402a();
                if (this.aA) {
                    this.ay = this.az;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1) {
            if (i2 == -1) {
                this.ak.putBoolean("isPatternSet", true);
                this.ak.commit();
                this.av = true;
                this.ao.setText("Change Backup Pattern");
                Toast.makeText(this.context, "Pattern has been Set", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this.context, "Unsuccessful", Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void m7405a(long j) {
        ((AlarmManager) this.context.getSystemService(Context.ALARM_SERVICE)).set(AlarmManager.RTC_WAKEUP, j, PendingIntent.getBroadcast(this.context, 1, new Intent(this.context, AlarmReciever.class), PendingIntent.FLAG_UPDATE_CURRENT));
        String format = new SimpleDateFormat("hh:mm aa").format(new Date(j));
        this.am.setText("Disabled until " + format);
        this.ak.putString("frozenTime", format);
        this.ak.commit();
    }

    public void onCreate(Bundle bundle) {
        this.context = getActivity().getApplicationContext();
        super.onCreate(bundle);
    }

    public List<ApplicationInfo> m7407b(Context context) {
        PackageManager packageManager = context.getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);
        List<ApplicationInfo> arrayList = new ArrayList();
        for (ApplicationInfo applicationInfo : installedApplications) {
            if (packageManager.getLaunchIntentForPackage(applicationInfo.packageName) != null) {
                arrayList.add(applicationInfo);
            }
        }
        return arrayList;
    }

//    protected Intent m7408c(Context context) {
////        try {
////            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
////            return new Intent("android.intent.action.VIEW", Uri.parse("fb://page/" + getActivity().getResources().getString(R.string.fbPageId)));
////        } catch (Exception e) {
////            return new Intent("android.intent.action.VIEW", Uri.parse("https://www.facebook.com/" + getActivity().getResources().getString(R.string.fbPageId)));
////        }
//    }

    public void onResume() {
        if (this.ar != null && this.aE) {
            MainActivity.mainActivity.f2891h = false;
            this.aE = false;
            this.aw = SecurityHelpers.m14856d(this.context);
            this.ar.setText(this.aw ? "Enabled" : "Decrease battery usage by 50% and enable improved lock engine");
            this.ak.putBoolean("isAccess", this.aw);
            this.ak.commit();
            if (this.aw) {
                this.context.sendBroadcast(new Intent(SecurityHelpers.f10000b));
            }
        }
        super.onResume();
    }


    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.finger_btn:
                this.ak.putBoolean("isFinger", z);
                this.ak.commit();
                if (!z) {
                    Toast.makeText(this.context, "Fingerprint Disabled", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    Toast.makeText(this.context, "Fingerprint Enabled", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.equal_btn:
                this.ak.putBoolean("needEqual", z);
                if (z) {
                    Toast.makeText(MainActivity.mainActivity, "Equal to unlock enabled", Toast.LENGTH_SHORT).show();
                    break;
                }
                break;
            case R.id.facedown_btn:
                if (z) {
                    if (!MainActivity.mainActivity.m5630e()) {
                        Toast.makeText(getActivity(), "Accelerometer sensor is not preset to avail this function.", Toast.LENGTH_SHORT).show();
                        this.f4803e.setChecked(false);
                        break;
                    }
                    this.ak.putBoolean("faceDown", true);
                    this.ak.commit();
                    MainActivity.mainActivity.m5628c();
                    break;
                }
                MainActivity.mainActivity.m5629d();
                this.ak.putBoolean("faceDown", false);
                this.ak.commit();
                break;
            case R.id.uninstall_btn:
                this.ak.putBoolean("instaLock", z);
                break;
            case R.id.sound_btn:
                this.ak.putBoolean("isSound", z);
                break;
            case R.id.vib_btn:
                this.ak.putBoolean("isVib", z);
                break;
        }
        this.ak.commit();
    }

    public void onClick(View view) {
        boolean z = true;
        SwitchCompat switchCompat;
        Intent intent;
        switch (view.getId()) {
            case R.id.rlSound:
                switchCompat = this.f4804f;
                if (this.f4804f.isChecked()) {
                    z = false;
                }
                switchCompat.setChecked(z);
                return;
            case R.id.rlAdFree:
//                MainActivity.mainActivity.m5623a(aI);
                return;
            case R.id.rlFingerPrint:
                switchCompat = this.f4800b;
                if (this.f4800b.isChecked()) {
                    z = false;
                }
                switchCompat.setChecked(z);
                return;
            case R.id.rlNeedEqual:
                switchCompat = this.f4806h;
                if (this.f4806h.isChecked()) {
                    z = false;
                }
                switchCompat.setChecked(z);
                return;
            case R.id.rl_changePassword:
                intent = new Intent(this.context, SetAppPasswordActivity.class);
                intent.putExtra("fromReset", true);
                startActivity(intent);
                return;
            case R.id.rl_interval:
                m7390Q();
                return;
            case R.id.rlTransition:
                m7389P();
                return;
            case R.id.rl_recovery:
                intent = new Intent(getActivity(), SetEmailActivity.class);
                intent.putExtra("fromReset", true);
                startActivity(intent);
                return;
            case R.id.rl_fake:
                startActivity(new Intent(this.context, FakePinActivity.class));
                return;
            case R.id.rl_uninstall_protection:
                startActivityForResult(new Intent(this.context, UninstallProtectionActivity.class), 854);
                this.au.setVisibility(View.GONE);
                return;
            case R.id.rlThemes:
                startActivity(new Intent(this.context, ColorThemeActivity.class));
                return;
            case R.id.rlFacedown:
                switchCompat = this.f4803e;
                if (this.f4803e.isChecked()) {
                    z = false;
                }
                switchCompat.setChecked(z);
                return;
            case R.id.rlfdAction:
                FragmentManager fragmentManager = MainActivity.mainActivity.getFragmentManager();
                C1126c c1126c = new C1126c(this);
                Bundle bundle = new Bundle();
                bundle.putInt("position", this.az);
                c1126c.setArguments(bundle);
                c1126c.show(fragmentManager, "alert_dialog_radio");
                return;
            case R.id.rl_pattern:
                m7400O();
                return;
            case R.id.rlAccess:
                if (this.aw) {
                    m7393T();
                    return;
                } else {
                    m7394U();
                    return;
                }
            case R.id.rlDisguise:
                if (this.aq.getVisibility() == View.VISIBLE) {
                    this.aq.setVisibility(View.GONE);
                    this.ak.putBoolean("tvNewDisg", false);
                    this.ak.commit();
                }
                startActivity(new Intent(this.context, DisguisedActivity.class));
                return;
            case R.id.rl_intruder:
                startActivity(new Intent(this.context, IntruderActivity.class));
                return;
            case R.id.rlUninstallProtection:
                switchCompat = this.f4801c;
                if (this.f4801c.isChecked()) {
                    z = false;
                }
                switchCompat.setChecked(z);
                return;
            case R.id.rlLockNew:
                switchCompat = this.f4802d;
                if (this.f4802d.isChecked()) {
                    z = false;
                }
                switchCompat.setChecked(z);
                return;
            case R.id.rlVibration:
                switchCompat = this.f4805g;
                if (this.f4805g.isChecked()) {
                    z = false;
                }
                switchCompat.setChecked(z);
                return;
            case R.id.rl_rateApp:// getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getActivity().getPackageName())));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getActivity().getPackageName())));
                }
                return;
            case R.id.rl_shareApp:
                Intent intent2 = new Intent();
                intent2.setAction("android.intent.action.SEND");
                intent2.setType("text/plain");
                intent2.putExtra("android.intent.extra.TEXT", getActivity().getString(R.string.shareString));
                startActivity(Intent.createChooser(intent2, "Share via"));
                return;
            case R.id.rl_likeUs:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + this.context.getPackageName())));
                return;
            case R.id.rl_Follow:
//                startActivity(m7408c(getActivity()));
                return;
            case R.id.rl_Contact:
                C1131f.sendEmail(getActivity());
                return;
            default:
                return;
        }
    }
}
