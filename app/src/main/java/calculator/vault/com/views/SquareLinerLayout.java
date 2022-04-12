package calculator.vault.com.views;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Kundan Singh on 01-04-2018.
 */

public class SquareLinerLayout extends LinearLayout {
    public SquareLinerLayout(Context context) {
        super(context);
    }

    public SquareLinerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareLinerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SquareLinerLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
