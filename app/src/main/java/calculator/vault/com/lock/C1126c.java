package calculator.vault.com.lock;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build.VERSION;
import android.os.Bundle;

public class C1126c extends DialogFragment {
    String[] f3304a = new String[]{"Close app", "Open another app", "Open website"};
    C1125a f3305b;
    OnClickListener f3306c = new C11241(this);


    public C1126c() {
    }

    class C11241 implements OnClickListener {
        final /* synthetic */ C1126c f3303a;

        C11241(C1126c c1126c) {
            this.f3303a = c1126c;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.f3303a.f3305b.mo1161a(((AlertDialog) dialogInterface).getListView().getCheckedItemPosition());
        }
    }

    public interface C1125a {
        void mo1161a(int i);
    }

    @SuppressLint("ValidFragment")
    public C1126c(C1125a c1125a) {
        this.f3305b = c1125a;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        int i = getArguments().getInt("position");
        Builder builder = VERSION.SDK_INT >= 21 ? new Builder(getActivity(), 16974394) : new Builder(getActivity());
        builder.setTitle("Choose your version");
        builder.setSingleChoiceItems(this.f3304a, i, null);
        builder.setPositiveButton("OK", this.f3306c);
        builder.setNegativeButton("Cancel", null);
        return builder.create();
    }
}
