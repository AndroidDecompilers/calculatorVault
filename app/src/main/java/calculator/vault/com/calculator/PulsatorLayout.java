package calculator.vault.com.calculator;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import calculator.vault.com.R;

public class PulsatorLayout extends RelativeLayout {
    private static final int DEFAULT_COLOR = Color.rgb(0, 116, 193);
    private static final int DEFAULT_COUNT = 4;
    private static final int DEFAULT_DURATION = 7000;
    private static final int DEFAULT_INTERPOLATOR = 0;
    private static final int DEFAULT_REPEAT = 0;
    private static final boolean DEFAULT_START_FROM_SCRATCH = true;
    public static final int INFINITE = 0;
    public static final int INTERP_ACCELERATE = 1;
    public static final int INTERP_ACCELERATE_DECELERATE = 3;
    public static final int INTERP_DECELERATE = 2;
    public static final int INTERP_LINEAR = 0;
    public static String s1 = "vcvavlvcv.v";
    private final AnimatorListener mAnimatorListener;
    private AnimatorSet mAnimatorSet;
    private float mCenterX;
    private float mCenterY;
    private int mColor;
    private int mCount;
    private int mDuration;
    private int mInterpolator;
    private boolean mIsStarted;
    private Paint mPaint;
    private float mRadius;
    private int mRepeat;
    private boolean mStartFromScratch;
    private final List<View> mViews;

    class C32551 implements AnimatorListener {
        C32551() {
        }

        public void onAnimationCancel(Animator animator) {
            PulsatorLayout.this.mIsStarted = false;
        }

        public void onAnimationEnd(Animator animator) {
            PulsatorLayout.this.mIsStarted = false;
        }

        public void onAnimationRepeat(Animator animator) {
        }

        public void onAnimationStart(Animator animator) {
            PulsatorLayout.this.mIsStarted = PulsatorLayout.DEFAULT_START_FROM_SCRATCH;
        }
    }

    private class PulseView extends View {
        public PulseView(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawCircle(PulsatorLayout.this.mCenterX, PulsatorLayout.this.mCenterY, PulsatorLayout.this.mRadius, PulsatorLayout.this.mPaint);
        }
    }

    public PulsatorLayout(Context context) {
        this(context, null, 0);
    }

    public PulsatorLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PulsatorLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mViews = new ArrayList();
        this.mAnimatorListener = new C32551();
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.Pulsator4Droid, 0, 0);
        this.mCount = 4;
        this.mDuration = DEFAULT_DURATION;
        this.mRepeat = 0;
        this.mStartFromScratch = DEFAULT_START_FROM_SCRATCH;
        this.mColor = DEFAULT_COLOR;
        this.mInterpolator = 0;
        try {
            this.mCount = obtainStyledAttributes.getInteger(R.styleable.Pulsator4Droid_pulse_count, 4);
            this.mDuration = obtainStyledAttributes.getInteger(R.styleable.Pulsator4Droid_pulse_duration, DEFAULT_DURATION);
            this.mRepeat = obtainStyledAttributes.getInteger(R.styleable.Pulsator4Droid_pulse_repeat, 0);
            this.mStartFromScratch = obtainStyledAttributes.getBoolean(R.styleable.Pulsator4Droid_pulse_startFromScratch, DEFAULT_START_FROM_SCRATCH);
            this.mColor = obtainStyledAttributes.getColor(R.styleable.Pulsator4Droid_pulse_color, DEFAULT_COLOR);
            this.mInterpolator = obtainStyledAttributes.getInteger(R.styleable.Pulsator4Droid_pulse_interpolator, 0);
            this.mPaint = new Paint();
            this.mPaint.setAntiAlias(DEFAULT_START_FROM_SCRATCH);
            this.mPaint.setStyle(Style.FILL);
            this.mPaint.setColor(this.mColor);
            build();
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    private void build() {
        int i = -1;
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        if (this.mRepeat != 0) {
            i = this.mRepeat;
        }
        Collection arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.mCount; i2++) {
            View pulseView = new PulseView(getContext());
            pulseView.setScaleX(0.0f);
            pulseView.setScaleY(0.0f);
            pulseView.setAlpha(1.0f);
            addView(pulseView, i2, layoutParams);
            this.mViews.add(pulseView);
            long j = (long) ((this.mDuration * i2) / this.mCount);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(pulseView, "ScaleX", new float[]{0.0f, 1.0f});
            ofFloat.setRepeatCount(i);
            ofFloat.setRepeatMode(ValueAnimator.RESTART);
            ofFloat.setStartDelay(j);
            arrayList.add(ofFloat);
            ofFloat = ObjectAnimator.ofFloat(pulseView, "ScaleY", new float[]{0.0f, 1.0f});
            ofFloat.setRepeatCount(i);
            ofFloat.setRepeatMode(ValueAnimator.RESTART);
            ofFloat.setStartDelay(j);
            arrayList.add(ofFloat);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(pulseView, "Alpha", new float[]{1.0f, 0.0f});
            ofFloat2.setRepeatCount(i);
            ofFloat2.setRepeatMode(ValueAnimator.RESTART);
            ofFloat2.setStartDelay(j);
            arrayList.add(ofFloat2);
        }
        this.mAnimatorSet = new AnimatorSet();
        this.mAnimatorSet.playTogether(arrayList);
        this.mAnimatorSet.setInterpolator(createInterpolator(this.mInterpolator));
        this.mAnimatorSet.setDuration((long) this.mDuration);
        this.mAnimatorSet.addListener(this.mAnimatorListener);
    }

    private void clear() {
        stop();
        for (View removeView : this.mViews) {
            removeView(removeView);
        }
        this.mViews.clear();
    }

    private static Interpolator createInterpolator(int i) {
        switch (i) {
            case 1:
                return new AccelerateInterpolator();
            case 2:
                return new DecelerateInterpolator();
            case 3:
                return new AccelerateDecelerateInterpolator();
            default:
                return new LinearInterpolator();
        }
    }

    private void reset() {
        boolean isStarted = isStarted();
        clear();
        build();
        if (isStarted) {
            start();
        }
    }

    public int getColor() {
        return this.mColor;
    }

    public int getCount() {
        return this.mCount;
    }

    public int getDuration() {
        return this.mDuration;
    }

    public int getInterpolator() {
        return this.mInterpolator;
    }

    public synchronized boolean isStarted() {
        boolean z;
        z = (this.mAnimatorSet == null || !this.mIsStarted) ? false : DEFAULT_START_FROM_SCRATCH;
        return z;
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.mAnimatorSet != null) {
            this.mAnimatorSet.cancel();
            this.mAnimatorSet = null;
        }
    }

    public void onMeasure(int i, int i2) {
        int size = (MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        int size2 = (MeasureSpec.getSize(i2) - getPaddingTop()) - getPaddingBottom();
        this.mCenterX = ((float) size) * 0.5f;
        this.mCenterY = ((float) size2) * 0.5f;
        this.mRadius = ((float) Math.min(size, size2)) * 0.5f;
        super.onMeasure(i, i2);
    }

    public void setColor(int i) {
        if (i != this.mColor) {
            this.mColor = i;
            if (this.mPaint != null) {
                this.mPaint.setColor(i);
            }
        }
    }

    public void setCount(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Count cannot be negative");
        } else if (i != this.mCount) {
            this.mCount = i;
            reset();
            invalidate();
        }
    }

    public void setDuration(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        } else if (i != this.mDuration) {
            this.mDuration = i;
            reset();
            invalidate();
        }
    }

    public void setInterpolator(int i) {
        if (i != this.mInterpolator) {
            this.mInterpolator = i;
            reset();
            invalidate();
        }
    }

    public synchronized void start() {
        if (!(this.mAnimatorSet == null || this.mIsStarted)) {
            this.mAnimatorSet.start();
            if (!this.mStartFromScratch) {
                Iterator it = this.mAnimatorSet.getChildAnimations().iterator();
                while (it.hasNext()) {
                    ObjectAnimator objectAnimator = (ObjectAnimator) ((Animator) it.next());
                    long startDelay = objectAnimator.getStartDelay();
                    objectAnimator.setStartDelay(0);
                    objectAnimator.setCurrentPlayTime(((long) this.mDuration) - startDelay);
                }
            }
        }
    }

    public synchronized void stop() {
        if (this.mAnimatorSet != null && this.mIsStarted) {
            this.mAnimatorSet.end();
        }
    }
}
