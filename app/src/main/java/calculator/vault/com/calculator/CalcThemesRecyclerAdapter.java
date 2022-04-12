package calculator.vault.com.calculator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import calculator.vault.com.R;

public class CalcThemesRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    ThemeClickListener mListener;
    int[] resIds = new int[]{R.drawable.theme0, R.drawable.theme1, R.drawable.theme2, R.drawable.theme3, R.drawable.theme4, R.drawable.theme5, R.drawable.theme6, R.drawable.theme7};

    class C32251 implements OnClickListener {
        C32251() {
        }

        public void onClick(View view) {
            CalcThemesRecyclerAdapter.this.mListener.onThemeClicked(((Integer) view.getTag()).intValue());
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumb;

        public MyViewHolder(View view) {
            super(view);
            this.ivThumb = (ImageView) view.findViewById(R.id.ivThumb);
        }
    }

    public interface ThemeClickListener {
        void onThemeClicked(int i);
    }

    public int getItemCount() {
        return this.resIds.length;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder c0823u, int i) {
        MyViewHolder myViewHolder = (MyViewHolder) c0823u;
        myViewHolder.ivThumb.setImageResource(this.resIds[i]);
        myViewHolder.ivThumb.setTag(Integer.valueOf(i));
        myViewHolder.ivThumb.setOnClickListener(new C32251());
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.raw_item_calculator_theme, viewGroup, false));
    }

    public void setOnItemCLickListener(ThemeClickListener themeClickListener) {
        this.mListener = themeClickListener;
    }
}
