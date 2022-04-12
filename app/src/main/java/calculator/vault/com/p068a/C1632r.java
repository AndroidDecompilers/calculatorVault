package calculator.vault.com.p068a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import calculator.vault.com.R;
import calculator.vault.com.lock.ViewSelfieActivity;


public class C1632r extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context f4644a;
    boolean f4645b;
    private ArrayList<C1633s> f4646c;

    class C16261 implements OnClickListener {
        final /* synthetic */ C1632r f4630a;

        C16261(C1632r c1632r) {
            this.f4630a = c1632r;
        }

        public void onClick(View view) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + this.f4630a.f4644a.getPackageName()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.f4630a.f4644a.startActivity(intent);
        }
    }

    class C16272 implements OnClickListener {
        final /* synthetic */ C1632r f4631a;

        C16272(C1632r c1632r) {
            this.f4631a = c1632r;
        }

        public void onClick(View view) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://play.google.com/store/apps/details?id=" + this.f4631a.f4644a.getPackageName()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.f4631a.f4644a.startActivity(intent);
        }
    }

    class C1628a extends RecyclerView.ViewHolder {
        TextView f4632a;
        Button f4633b;
        View f4634c;
        final /* synthetic */ C1632r f4635d;

        public C1628a(C1632r c1632r, View view) {
            super(view);
            this.f4635d = c1632r;
            this.f4632a = (TextView) view.findViewById(R.id.textView1);
            this.f4633b = (Button) view.findViewById(R.id.button1);
            this.f4634c = view.findViewById(R.id.llRate);
        }
    }

    class C1629b extends RecyclerView.ViewHolder {
        TextView f4636a;
        final /* synthetic */ C1632r f4637b;

        public C1629b(C1632r c1632r, View view) {
            super(view);
            this.f4637b = c1632r;
            this.f4636a = (TextView) view.findViewById(R.id.textView1);
        }
    }

    public class C1631c extends RecyclerView.ViewHolder {
        TextView f4640a;
        TextView f4641b;
        ImageView f4642c;
        final /* synthetic */ C1632r f4643d;

        public C1631c(final C1632r c1632r, View view) {
            super(view);
            this.f4643d = c1632r;
            this.f4640a = (TextView) view.findViewById(R.id.tvTitle);
            this.f4641b = (TextView) view.findViewById(R.id.tvTime);
            this.f4642c = (ImageView) view.findViewById(R.id.imageView);
            view.setOnClickListener(new OnClickListener() {

                public void onClick(View view) {
                    int layoutPosition = f4643d.f4645b ? getLayoutPosition() - 1 : getLayoutPosition();
                    C1633s c1633s = (C1633s) f4643d.f4646c.get(layoutPosition);
                    Intent intent = new Intent(f4643d.f4644a, ViewSelfieActivity.class);
                    intent.putExtra("path", c1633s.f4647a);
                    intent.putExtra("time", c1633s.f4648b);
                    intent.putExtra("appName", c1633s.f4649c);
                    intent.putExtra("pos", layoutPosition);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    f4643d.f4644a.startActivity(intent);
                }
            });
        }
    }

    public C1632r(Context context, ArrayList<C1633s> arrayList, boolean z) {
        this.f4646c = arrayList;
        this.f4644a = context;
        this.f4645b = z;
    }

    private boolean m7280a(int i) {
        return i == 0;
    }

    private boolean m7281b(int i) {
        return i == this.f4646c.size() + 1;
    }

    public int getItemCount() {
        return this.f4645b ? this.f4646c.size() + 2 : this.f4646c.size();
    }

    public int getItemViewType(int i) {
        return !this.f4645b ? 1 : m7280a(i) ? 0 : m7281b(i) ? 2 : 1;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder c0823u, int i) {
        if (!(c0823u instanceof C1629b)) {
            if (c0823u instanceof C1628a) {
                C1628a c1628a = (C1628a) c0823u;
                c1628a.f4633b.setOnClickListener(new C16261(this));
                c1628a.f4634c.setOnClickListener(new C16272(this));
            } else if (c0823u instanceof C1631c) {
                C1631c c1631c = (C1631c) c0823u;
                TextView textView = c1631c.f4640a;
                TextView textView2 = c1631c.f4641b;
                ImageView imageView = c1631c.f4642c;
                ArrayList arrayList = this.f4646c;
                if (this.f4645b) {
                    i--;
                }
                C1633s c1633s = (C1633s) arrayList.get(i);
                textView.setText("This guy was trying to break in your " + c1633s.f4649c + " security. we caught him.");
                textView2.setText(c1633s.f4648b);
                Glide.with(this.f4644a).load(c1633s.f4647a).into(imageView);
            }
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return i == 0 ? new C1629b(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_header, viewGroup, false)) : i == 2 ? new C1628a(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_rate, viewGroup, false)) : new C1631c(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.intruder_raw_item, viewGroup, false));
    }
}
