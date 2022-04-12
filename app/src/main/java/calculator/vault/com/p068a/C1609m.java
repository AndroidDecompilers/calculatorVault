package calculator.vault.com.p068a;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import calculator.vault.com.R;

public class C1609m extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context f4578a;
    C1602a f4579b;
    C1608d f4580c;
    C1603b f4581d;
    private ArrayList<C1610n> f4582e;

    public interface C1602a {
        void mo2102a(C1610n c1610n);
    }

    public interface C1603b {
        void mo2103a(C1610n c1610n);
    }

    public class C1607c extends RecyclerView.ViewHolder {
        ImageView f4574a;
        final /* synthetic */ C1609m f4575b;
        private TextView f4576c;
        private TextView f4577d;

        public C1607c(final C1609m c1609m, View view) {
            super(view);

            this.f4575b = c1609m;
            this.f4574a = (ImageView) view.findViewById(R.id.ivAction);
            if (c1609m.f4581d == null) {
                this.f4574a.setImageResource(R.drawable.browser_history_go);
            }
            this.f4576c = (TextView) view.findViewById(R.id.tvName);
            this.f4577d = (TextView) view.findViewById(R.id.tvUrl);
            view.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    f4575b.f4579b.mo2102a((C1610n) f4575b.f4582e.get(getAdapterPosition()));
                }
            });
            this.f4574a.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    f4575b.f4580c.mo2104a((C1610n) f4575b.f4582e.get(getAdapterPosition()));
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {

                public boolean onLongClick(View view) {
                    if (f4575b.f4581d == null) {
                        return false;
                    }
                    f4575b.f4581d.mo2103a((C1610n) f4575b.f4582e.get(getAdapterPosition()));
                    return true;
                }
            });
        }
    }

    public interface C1608d {
        void mo2104a(C1610n c1610n);
    }

    public C1609m(Context context, ArrayList<C1610n> arrayList, C1602a c1602a, C1603b c1603b, C1608d c1608d) {
        this.f4582e = arrayList;
        this.f4578a = context;
        this.f4579b = c1602a;
        this.f4581d = c1603b;
        this.f4580c = c1608d;
    }

    private void m7247b(List<C1610n> list) {
        for (int size = this.f4582e.size() - 1; size >= 0; size--) {
            if (!list.contains((C1610n) this.f4582e.get(size))) {
                m7250a(size);
            }
        }
    }

    private void m7248c(List<C1610n> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            C1610n c1610n = (C1610n) list.get(i);
            if (!this.f4582e.contains(c1610n)) {
                m7252a(i, c1610n);
            }
        }
    }

    private void m7249d(List<C1610n> list) {
        int size = list.size() - 1;
        while (size >= 0) {
            int indexOf = this.f4582e.indexOf((C1610n) list.get(size));
            if (indexOf >= 0 && indexOf != size) {
                m7251a(indexOf, size);
            }
            size--;
        }
    }

    public C1610n m7250a(int i) {
        C1610n c1610n = (C1610n) this.f4582e.remove(i);
        notifyItemRemoved(i);
        return c1610n;
    }

    public void m7251a(int i, int i2) {
        this.f4582e.add(i2, (C1610n) this.f4582e.remove(i));
        notifyItemMoved(i, i2);
    }

    public void m7252a(int i, C1610n c1610n) {
        this.f4582e.add(i, c1610n);
        notifyItemInserted(i);
    }

    public void m7253a(List<C1610n> list) {
        m7247b(list);
        m7248c(list);
        m7249d(list);
    }

    public int getItemCount() {
        return this.f4582e.size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder c0823u, int i) {
        C1607c c1607c = (C1607c) c0823u;
        C1610n c1610n = (C1610n) this.f4582e.get(i);
        c1607c.f4576c.setText("" + c1610n.f4583a);
        c1607c.f4577d.setText(c1610n.f4584b);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new C1607c(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_history_item, viewGroup, false));
    }
}
