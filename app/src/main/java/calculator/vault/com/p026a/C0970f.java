package calculator.vault.com.p026a;

import android.content.Context;
import android.os.Build.VERSION;

public final class C0970f {
    public static C0964d m5533a(Context context, C0969e c0969e) {
        int i = VERSION.SDK_INT;
        C0964d c0965a = i < 5 ? new C0965a(context) : i < 8 ? new C0966b(context) : new C0968c(context);
        c0965a.mo938a(c0969e);
        return c0965a;
    }
}
