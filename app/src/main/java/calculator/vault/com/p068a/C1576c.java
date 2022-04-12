package calculator.vault.com.p068a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import calculator.vault.com.R;

public class C1576c extends BaseAdapter {
    private Context f4459a;
    private List<ApplicationInfo> f4460b;
    private PackageManager f4461c;

    private class C1575a {
        ImageView f4456a;
        TextView f4457b;
        final /* synthetic */ C1576c f4458c;

        private C1575a(C1576c c1576c) {
            this.f4458c = c1576c;
        }
    }

    public C1576c(Context context, List<ApplicationInfo> list, PackageManager packageManager) {
        this.f4459a = context;
        this.f4460b = list;
        this.f4461c = packageManager;
    }

    public int getCount() {
        return this.f4460b.size();
    }

    public Object getItem(int i) {
        return this.f4460b.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1575a c1575a;
        if (view == null) {
            view = LayoutInflater.from(this.f4459a).inflate(R.layout.layout_appinfo, null);
            c1575a = new C1575a(this);
            c1575a.f4456a = (ImageView) view.findViewById(R.id.ivIcon);
            c1575a.f4457b = (TextView) view.findViewById(R.id.tvName);
            view.setTag(c1575a);
        } else {
            c1575a = (C1575a) view.getTag();
        }
        ApplicationInfo applicationInfo = (ApplicationInfo) this.f4460b.get(i);
        c1575a.f4456a.setImageDrawable(applicationInfo.loadIcon(this.f4461c));
        c1575a.f4457b.setText(applicationInfo.loadLabel(this.f4461c));
        return view;
    }
}
