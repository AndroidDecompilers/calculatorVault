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
import android.app.KeyguardManager;
import android.content.Context;
import android.graphics.Rect;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.KeyProperties;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroupOverlay;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class CalculatorL23 extends Calculator {

    FingerprintHandler helper;
    private Animator mCurrentAnimator;
    private KeyguardManager keyguardManager;
    private FingerprintManager fingerprintManager;
    private FingerprintManager.CryptoObject cryptoObject;
    private KeyStore keyStore;
    private Cipher cipher;

    class C32453 implements ValueAnimator.AnimatorUpdateListener {
        C32453() {
        }

        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            CalculatorL23.this.mResultEditText.setTextColor(((Integer) valueAnimator.getAnimatedValue()).intValue());
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

        animatorSet.playTogether(ObjectAnimator.ofFloat(this.mResultEditText, "scaleX", new float[]{variableTextSize}),
                ObjectAnimator.ofFloat(this.mResultEditText, "scaleY", new float[]{variableTextSize}),
                ObjectAnimator.ofFloat(this.mResultEditText, "translationX", new float[]{width}),
                ObjectAnimator.ofFloat(this.mResultEditText, "translationY", new float[]{height}),
                ObjectAnimator.ofFloat(this.mFormulaEditText, "translationY", new float[]{f}));

        animatorSet.setDuration(500);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                CalculatorL23.this.mResultEditText.setTextColor(currentTextColor);
                CalculatorL23.this.mResultEditText.setScaleX(1.0f);
                CalculatorL23.this.mResultEditText.setScaleY(1.0f);
                CalculatorL23.this.mResultEditText.setTranslationX(0.0f);
                CalculatorL23.this.mResultEditText.setTranslationY(0.0f);
                CalculatorL23.this.mFormulaEditText.setTranslationY(0.0f);
                CalculatorL23.this.mFormulaEditText.setText(str);
                CalculatorL23.this.setState(CalculatorState.RESULT);
                CalculatorL23.this.mCurrentAnimator = null;
            }

            public void onAnimationStart(Animator animator) {
                CalculatorL23.this.mResultEditText.setText(str);
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
                CalculatorL23.this.mCurrentAnimator = null;
            }
        });
        this.mCurrentAnimator = animatorSet;
        animatorSet.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.prefs.getBoolean("isFinger", false)) {
            this.keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
            this.fingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
            if (this.fingerprintManager.isHardwareDetected()) {
                if (this.keyguardManager.isKeyguardSecure() || this.fingerprintManager.hasEnrolledFingerprints()) {
                    generateKey();
                    if (cipherInit()) {
                        this.cryptoObject = new FingerprintManager.CryptoObject(this.cipher);
                        this.helper = new FingerprintHandler(this);
                        this.helper.startAuth(this.fingerprintManager, this.cryptoObject, new C32314());
                        if (this.prefs.getBoolean("isFirstTimeFinger", true)) {
                            Toast.makeText(getApplicationContext(), "you can use your fingerprint", Toast.LENGTH_LONG).show();
                            this.edit.putBoolean("isFirstTimeFinger", false);
                            this.edit.commit();
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }


    class C32379 implements Runnable {

        class C32361 implements FingerprintHandler.onFingerScanListener {
            C32361() {
            }

            public void onAuthFailed() {
            }

            public void onAuthSucceed() {
                CalculatorL23.this.openLocker();
            }
        }

        C32379() {
        }

        public void run() {
            helper.resumeListening();
            helper.startAuth(fingerprintManager, cryptoObject, new C32361());
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (!(this.helper == null || this.helper.mListener == null)) {
            new Handler().postDelayed(new C32379(), 500);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!(this.helper == null || this.helper.mListener == null)) {
            this.helper.stopListening();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void generateKey() {
        try {
            keyStore = KeyStore.getInstance("AndroidKeyStore");
        } catch (Exception e) {
            e.printStackTrace();
        }


        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new RuntimeException("Failed to get KeyGenerator instance", e);
        }


        try {
            keyStore.load(null);
            keyGenerator.init(new
                    KeyGenParameterSpec.Builder(KEY_NAME,
                    KeyProperties.PURPOSE_ENCRYPT |
                            KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
                    .setUserAuthenticationRequired(true)
                    .setEncryptionPaddings(
                            KeyProperties.ENCRYPTION_PADDING_PKCS7)
                    .build());
            keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException |
                InvalidAlgorithmParameterException
                | CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public boolean cipherInit() {
        try {
            cipher = Cipher.getInstance(KeyProperties.KEY_ALGORITHM_AES + "/" + KeyProperties.BLOCK_MODE_CBC + "/" + KeyProperties.ENCRYPTION_PADDING_PKCS7);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            throw new RuntimeException("Failed to get Cipher", e);
        }


        try {
            keyStore.load(null);
            SecretKey key = (SecretKey) keyStore.getKey(KEY_NAME,
                    null);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return true;
        } catch (KeyPermanentlyInvalidatedException e) {
            return false;
        } catch (KeyStoreException | CertificateException | UnrecoverableKeyException | IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException("Failed to init Cipher", e);
        }
    }

    private String KEY_NAME = "kkinfosis";
}
