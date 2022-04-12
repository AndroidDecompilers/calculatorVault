/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package calculator.vault.com.calculator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.TargetApi;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroupOverlay;
import android.view.animation.AccelerateDecelerateInterpolator;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class CalculatorL extends Calculator {

    private Animator mCurrentAnimator;

    class C32453 implements ValueAnimator.AnimatorUpdateListener {
        C32453() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            CalculatorL.this.mResultEditText.setTextColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
        }
    }

    void cancelAnimation() {
        if (this.mCurrentAnimator != null) {
            this.mCurrentAnimator.end();
        }
    }

    public void onResult(final String str) {
        float variableTextSize = this.mFormulaEditText.getVariableTextSize(str) / this.mResultEditText.getTextSize();
        float width = (1.0f - variableTextSize) * ((((float) this.mResultEditText.getWidth()) / 2.0f) - ((float) this.mResultEditText.getPaddingEnd()));
        float height = (((1.0f - variableTextSize) * ((((float) this.mResultEditText.getHeight()) / 2.0f) - ((float) this.mResultEditText.getPaddingBottom()))) + ((float) (this.mFormulaEditText.getBottom() - this.mResultEditText.getBottom()))) + ((float) (this.mResultEditText.getPaddingBottom() - this.mFormulaEditText.getPaddingBottom()));
        float f = (float) (-this.mFormulaEditText.getBottom());
        final int currentTextColor = this.mResultEditText.getCurrentTextColor();
        int currentTextColor2 = this.mFormulaEditText.getCurrentTextColor();
        ValueAnimator.ofObject(new ArgbEvaluator(), new Object[]{Integer.valueOf(currentTextColor), Integer.valueOf(currentTextColor2)}).addUpdateListener(new C32453());
        AnimatorSet animatorSet = new AnimatorSet();
        Animator[] r7 = new Animator[6];
        r7[1] = ObjectAnimator.ofFloat(this.mResultEditText, "scaleX", new float[]{variableTextSize});
        r7[2] = ObjectAnimator.ofFloat(this.mResultEditText, "scaleY", new float[]{variableTextSize});
        r7[3] = ObjectAnimator.ofFloat(this.mResultEditText, "translationX", new float[]{width});
        r7[4] = ObjectAnimator.ofFloat(this.mResultEditText, "translationY", new float[]{height});
        r7[5] = ObjectAnimator.ofFloat(this.mFormulaEditText, "translationY", new float[]{f});
        animatorSet.playTogether(r7);
        animatorSet.setDuration(500);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                CalculatorL.this.mResultEditText.setTextColor(currentTextColor);
                CalculatorL.this.mResultEditText.setScaleX(1.0f);
                CalculatorL.this.mResultEditText.setScaleY(1.0f);
                CalculatorL.this.mResultEditText.setTranslationX(0.0f);
                CalculatorL.this.mResultEditText.setTranslationY(0.0f);
                CalculatorL.this.mFormulaEditText.setTranslationY(0.0f);
                CalculatorL.this.mFormulaEditText.setText(str);
                CalculatorL.this.setState(CalculatorState.RESULT);
                CalculatorL.this.mCurrentAnimator = null;
            }

            public void onAnimationStart(Animator animator) {
                CalculatorL.this.mResultEditText.setText(str);
            }
        });
        this.mCurrentAnimator = animatorSet;
        animatorSet.start();
    }

    public void reveal(View view, int i, final AnimatorListenerWrapper animatorListenerWrapper) {
        final ViewGroupOverlay viewGroupOverlay = (ViewGroupOverlay) getWindow().getDecorView().getOverlay();
        Rect rect = new Rect();
        this.mDisplayView.getGlobalVisibleRect(rect);
        final View view2 = new View(this);
        view2.setBottom(rect.bottom);
        view2.setLeft(rect.left);
        view2.setRight(rect.right);
        view2.setBackgroundColor(i);
        viewGroupOverlay.add(view2);
        int[] r1 = new int[2];
        view.getLocationInWindow(r1);
        r1[0] = r1[0] + (view.getWidth() / 2);
        r1[1] = r1[1] + (view.getHeight() / 2);
        int left = r1[0] - view2.getLeft();
        int top = r1[1] - view2.getTop();
        double pow = Math.pow((double) (view2.getLeft() - left), 2.0d);
        double pow2 = Math.pow((double) (view2.getRight() - left), 2.0d);
        double pow3 = Math.pow((double) (view2.getTop() - top), 2.0d);
        Animator createCircularReveal = ViewAnimationUtils.createCircularReveal(view2, left, top, 0.0f, (float) Math.max(Math.sqrt(pow + pow3), Math.sqrt(pow2 + pow3)));
        createCircularReveal.setDuration(500);
        Animator ofFloat = ObjectAnimator.ofFloat(view2, View.ALPHA, new float[]{0.0f});
        ofFloat.setDuration(200);
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationStart(Animator animator) {
                animatorListenerWrapper.onAnimationStart();
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(createCircularReveal).before(ofFloat);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                viewGroupOverlay.remove(view2);
                CalculatorL.this.mCurrentAnimator = null;
            }
        });
        this.mCurrentAnimator = animatorSet;
        animatorSet.start();
    }
}
