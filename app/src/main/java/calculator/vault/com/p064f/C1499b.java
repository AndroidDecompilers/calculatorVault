package calculator.vault.com.p064f;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

public final class C1499b {
    private final Context f4236a;

    public C1499b(Context context) {
        this.f4236a = context;
    }

    private static C1498a m6932a(String str) {
        try {
            Object newInstance = null;
            try {
                newInstance = Class.forName(str).newInstance();
                if (newInstance instanceof C1498a) {
                    return (C1498a) newInstance;
                }
                throw new RuntimeException("Expected instanceof GlideModule, but found: " + newInstance);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("Unable to instantiate GlideModule implementation for " + newInstance, e);
            } catch (Throwable e2) {
                throw new RuntimeException("Unable to instantiate GlideModule implementation for " + newInstance, e2);
            }
        } catch (Throwable e3) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e3);
        }
    }

    public List<C1498a> m6933a() {
        List<C1498a> arrayList = new ArrayList();
        try {
            ApplicationInfo applicationInfo = this.f4236a.getPackageManager().getApplicationInfo(this.f4236a.getPackageName(), PackageManager.GET_META_DATA);
            if (applicationInfo.metaData != null) {
                for (String str : applicationInfo.metaData.keySet()) {
                    if ("GlideModule".equals(applicationInfo.metaData.get(str))) {
                        arrayList.add(C1499b.m6932a(str));
                    }
                }
            }
            return arrayList;
        } catch (Throwable e) {
            throw new RuntimeException("Unable to find metadata to parse GlideModules", e);
        }
    }
}
