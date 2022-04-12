package calculator.vault.com.lock;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import calculator.vault.com.R;

public class C1129e {
    C1129e(Activity activity) {
        final Dialog dialog = new Dialog(activity);
        View inflate = activity.getLayoutInflater().inflate(R.layout.updateinfo_dialog, null);
        ((TextView) inflate.findViewById(R.id.textView1)).setTypeface(C1131f.f3322h);
        ((TextView) inflate.findViewById(R.id.textView2)).setTypeface(C1131f.f3315a);
        ((TextView) inflate.findViewById(R.id.textView3)).setTypeface(C1131f.f3322h);
        dialog.setContentView(inflate);
        inflate.findViewById(R.id.rlDone).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
