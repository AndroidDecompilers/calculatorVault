package calculator.vault.com.p068a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import calculator.vault.com.R;

public class C1600k extends BaseAdapter {
    ArrayList<String> f4558a;
    ArrayList<String> f4559b;
    Context f4560c;
    LayoutInflater f4561d;

    class C1599a {
        TextView f4555a;
        TextView f4556b;
        final /* synthetic */ C1600k f4557c;

        C1599a(C1600k c1600k) {
            this.f4557c = c1600k;
        }
    }

    public C1600k(ArrayList<String> arrayList, ArrayList<String> arrayList2, Context context) {
        this.f4558a = arrayList;
        this.f4559b = arrayList2;
        this.f4560c = context;
        this.f4561d = LayoutInflater.from(context);
    }

    public int getCount() {
        return this.f4558a.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        C1599a c1599a;
        if (view == null) {
            c1599a = new C1599a(this);
            view = this.f4561d.inflate(R.layout.list_item_with_path_layout, null);
            c1599a.f4555a = (TextView) view.findViewById(R.id.textView1);
            c1599a.f4556b = (TextView) view.findViewById(R.id.textView2);
            view.setTag(c1599a);
        } else {
            c1599a = (C1599a) view.getTag();
        }
        c1599a.f4555a.setText((CharSequence) this.f4558a.get(i));
        c1599a.f4556b.setText((CharSequence) this.f4559b.get(i));
        return view;
    }
}
