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

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.View.OnLongClickListener;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

import java.io.File;

import calculator.vault.com.R;

public abstract class Calculator extends AppCompatActivity
        implements CalculatorEditText.OnTextSizeChangeListener, CalcThemesRecyclerAdapter.ThemeClickListener, CalculatorExpressionEvaluator.EvaluateCallback, OnLongClickListener {

    private static final String NAME = Calculator.class.getName();
    public static final int INVALID_RES_ID = -1;
    private static final String KEY_CURRENT_EXPRESSION = (NAME + "_currentExpression");
    private static final String KEY_CURRENT_STATE = (NAME + "_currentState");
    private static final String KEY_NAME = "example_key";
    int[] advancedPadColors = new int[]{-1114303, -2012404, -14816842, -547036, -1552832, -958371, -282787, -16596793};
    int[] advancedTextColors = new int[]{-14342875, -1, -14342875, -14342875, -1, -14342875, -14342875, -14342875};
    Button btnClr;
    Button btnDel;
    Button btnDiv;
    Button btnMul;
    Button btnPlus;
    Button btnSub;
    private ImageButton btnTheme;
    boolean coverOpened;
    int currentAdvancedPadColor;
    int currentAdvancedTextColor;
    int currentDisplayTextColor;
    int currentDisplayViewColor;
    int currentNumPadColor;
    int currentNumPadTextColor;
    int currentOperatorPadColor;
    int currentOperatorpadTextColor;
    int currentPosition;
    int currentStatusBarColor;
    int currentThemeIcon;
    Button digit0;
    Button digit1;
    Button digit2;
    Button digit3;
    Button digit4;
    Button digit5;
    Button digit6;
    Button digit7;
    Button digit8;
    Button digit9;
    Button digitEQ;
    Button digitPoint;
    int[] displayTextColors = new int[]{-1, -1, -14342875, -14342875, -1, -14342875, -1, -1};
    int[] displayViewColors = new int[]{-16732433, -5650738, -1, -5385345, -12171706, -1252733, -1552832, -1553821};
    SharedPreferences.Editor edit;
    boolean fakeEnabled;
    String fakePass;
    boolean fromBackGalleryLock;
    Button funCos;
    Button funE;
    Button funFact;
    Button funLog;
    Button funLpren;
    Button funPi;
    Button funPow;
    Button funRoot;
    Button funRpren;
    Button funSine;
    Button funTan;
    Button funln;
    boolean isFirstTime;
    private View mClearButton;
    private CalculatorState mCurrentState;
    private View mDeleteButton;
    protected RelativeLayout mDisplayView;
    private DrawerLayout mDrawerLayout;
    private View mEqualButton;
    private CalculatorExpressionEvaluator mEvaluator;
    protected CalculatorEditText mFormulaEditText;
    private final Editable.Factory mFormulaEditableFactory = new C32303();
    private final OnKeyListener mFormulaOnKeyListener = new C32292();
    private final TextWatcher mFormulaTextWatcher = new C32281();
    private NineOldViewPager mPadViewPager;
    String mPass;
    protected CalculatorEditText mResultEditText;
    String mTempPwd = "";
    private CalculatorExpressionTokenizer mTokenizer;
    boolean needEqual;
    int[] numberPadTextColors = new int[]{-1, -14342875, -1, -1, -14342875, -1, -1, -1};
    int[] numbericPadColors = new int[]{-11053225, -2962763, -12369085, -13483190, -1, -13815719, -11053225, -14670785};
    int[] operatorPadColors = new int[]{-9408400, -4872566, -10263709, -13483190, -1973791, -12302206, -9408400, -13024922};
    int[] operatorPadTextColors = new int[]{-1, -1, -1, -1, -14342875, -1, -1, -1};
    View padAdvanced;
    View padNumeric;
    View padOperator;
    SharedPreferences prefs;
    int[] statusBarColors = new int[]{-16083254, -7754056, -16728876, -7425693, -13224394, -4147096, -4245460, -3199159};

    class C32281 implements TextWatcher {
        C32281() {
        }

        public void afterTextChanged(Editable editable) {
            Calculator.this.setState(CalculatorState.INPUT);
            Calculator.this.mEvaluator.evaluate((CharSequence) editable, Calculator.this);
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (("" + charSequence).equals(Calculator.this.mPass) && !Calculator.this.needEqual) {
                Calculator.this.openLocker();
            }
        }
    }

    class C32292 implements OnKeyListener {
        C32292() {
        }

        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            switch (i) {
                case 66:
                case 160:
                    if (keyEvent.getAction() != 1) {
                        return true;
                    }
                    Calculator.this.onEquals();
                    return true;
                default:
                    return false;
            }
        }
    }

    class C32303 extends Editable.Factory {
        C32303() {
        }

        public Editable newEditable(CharSequence charSequence) {
            boolean z = Calculator.this.mCurrentState == CalculatorState.INPUT || Calculator.this.mCurrentState == CalculatorState.ERROR;
            return new CalculatorExpressionBuilder(charSequence, Calculator.this.mTokenizer, z);
        }
    }

    class C32314 implements FingerprintHandler.onFingerScanListener {
        C32314() {
        }

        public void onAuthFailed() {
        }

        public void onAuthSucceed() {
            Calculator.this.openLocker();
        }
    }

    class C32336 implements View.OnClickListener {
        C32336() {
        }

        public void onClick(View view) {
            Calculator.this.finish();
        }
    }

    class C32347 implements AnimatorListenerWrapper {
        C32347() {
        }

        public void onAnimationStart() {
            Calculator.this.mFormulaEditText.getEditableText().clear();
        }
    }


    protected enum CalculatorState {
        INPUT,
        EVALUATE,
        RESULT,
        ERROR
    }

    private void onClear() {
        if (!TextUtils.isEmpty(this.mFormulaEditText.getText())) {
            reveal(this.mClearButton.getVisibility() == View.VISIBLE ? this.mClearButton : this.mDeleteButton, this.currentStatusBarColor, new C32347());
        }
    }

    private void onDelete() {
        Editable editableText = this.mFormulaEditText.getEditableText();
        int length = editableText.length();
        if (length > 0) {
            editableText.delete(length - 1, length);
        }
    }

    private void onEquals() {
        String str = "" + this.mFormulaEditText.getText();
        if (this.isFirstTime) {
            if (str.length() < 4 || str.length() > 12) {
                Toast.makeText(getApplicationContext(), "Password must have 4-12 characters long.\nTry again", Toast.LENGTH_LONG).show();
            } else if (this.mTempPwd.length() < 1) {
                this.mTempPwd = str;
                onClear();
                showPasswordDialog(true);
            } else if (this.mTempPwd.equals(str)) {
                Toast.makeText(getApplicationContext(), "Welcome!", Toast.LENGTH_LONG).show();
                CalcUtils.createInitialFolders(this);
                this.edit.putString("password", str);
                this.edit.commit();
                this.mPass = this.mTempPwd;
                openLocker();
            } else {
                Toast.makeText(getApplicationContext(), "Password did not matched.\nTry again", Toast.LENGTH_LONG).show();
            }
        } else if (str.equals(this.mPass)) {
            openLocker();
        } else {
            if (str.equals("00001111")) {
                if (this.prefs.getString("regEmail", "").length() > 3) {
                    CalcUtils.showSendMailDialog(this, this.prefs.getString("regEmail", ""), this.mPass);
                } else {
                    Toast.makeText(getApplicationContext(), "Sorry, you did not save any email address to receive password.", Toast.LENGTH_LONG).show();
                }
            } else if (this.fakeEnabled && this.fakePass.equals(str)) {
                finish();
                Intent intent = new Intent("INTENT_ACTION_UNLOCK_LOCKER");
                intent.putExtra("mPass", this.mPass);
                intent.putExtra("fromFake", true);
                startActivity(intent);
                return;
            }
            if (this.mCurrentState == CalculatorState.INPUT) {
                setState(CalculatorState.EVALUATE);
                this.mEvaluator.evaluate(this.mFormulaEditText.getText(), (CalculatorExpressionEvaluator.EvaluateCallback) this);
            }
        }
    }

    private void onError(final int i) {
        if (this.mCurrentState != CalculatorState.EVALUATE) {
            this.mResultEditText.setText(i);
        } else {
            reveal(this.mEqualButton, getResources().getColor(R.color.calculator_error_color), new AnimatorListenerWrapper() {
                public void onAnimationStart() {
                    Calculator.this.setState(CalculatorState.ERROR);
                    Calculator.this.mResultEditText.setText(i);
                }
            });
        }
    }

    protected void openLocker() {
        Intent intent = new Intent("INTENT_ACTION_UNLOCK_LOCKER");
        intent.putExtra("mPass", this.mPass);
        startActivity(intent);
        finish();
    }

    private void setThemeColors() {
        this.mDisplayView.setBackgroundColor(this.currentDisplayViewColor);
        this.padNumeric.setBackgroundColor(this.currentNumPadColor);
        this.padOperator.setBackgroundColor(this.currentOperatorPadColor);
        this.padAdvanced.setBackgroundColor(this.currentAdvancedPadColor);
        this.mFormulaEditText.setTextColor(this.currentDisplayTextColor);
        this.mResultEditText.setTextColor(this.currentDisplayTextColor);
        this.btnTheme.setImageResource(this.currentThemeIcon);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().addFlags(Integer.MIN_VALUE);
            getWindow().setStatusBarColor(this.currentStatusBarColor);
        }
        this.digit0.setTextColor(this.currentNumPadTextColor);
        this.digit1.setTextColor(this.currentNumPadTextColor);
        this.digit2.setTextColor(this.currentNumPadTextColor);
        this.digit3.setTextColor(this.currentNumPadTextColor);
        this.digit4.setTextColor(this.currentNumPadTextColor);
        this.digit5.setTextColor(this.currentNumPadTextColor);
        this.digit6.setTextColor(this.currentNumPadTextColor);
        this.digit7.setTextColor(this.currentNumPadTextColor);
        this.digit8.setTextColor(this.currentNumPadTextColor);
        this.digit9.setTextColor(this.currentNumPadTextColor);
        this.digitEQ.setTextColor(this.currentNumPadTextColor);
        this.digitPoint.setTextColor(this.currentNumPadTextColor);
        this.btnClr.setTextColor(this.currentOperatorpadTextColor);
        this.btnDel.setTextColor(this.currentOperatorpadTextColor);
        this.btnDiv.setTextColor(this.currentOperatorpadTextColor);
        this.btnMul.setTextColor(this.currentOperatorpadTextColor);
        this.btnPlus.setTextColor(this.currentOperatorpadTextColor);
        this.btnSub.setTextColor(this.currentOperatorpadTextColor);
        this.funSine.setTextColor(this.currentAdvancedTextColor);
        this.funCos.setTextColor(this.currentAdvancedTextColor);
        this.funE.setTextColor(this.currentAdvancedTextColor);
        this.funFact.setTextColor(this.currentAdvancedTextColor);
        this.funln.setTextColor(this.currentAdvancedTextColor);
        this.funLog.setTextColor(this.currentAdvancedTextColor);
        this.funLpren.setTextColor(this.currentAdvancedTextColor);
        this.funRpren.setTextColor(this.currentAdvancedTextColor);
        this.funPi.setTextColor(this.currentAdvancedTextColor);
        this.funPow.setTextColor(this.currentAdvancedTextColor);
        this.funRoot.setTextColor(this.currentAdvancedTextColor);
        this.funTan.setTextColor(this.currentAdvancedTextColor);
    }

    private void showPasswordDialog(boolean z) {
        View inflate = getLayoutInflater().inflate(R.layout.set_pwd_dialog, null);
        final Dialog dialog = new Dialog(this);
        TextView textView = (TextView) inflate.findViewById(R.id.tvTitle);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tvmsg);
        if (z) {
            textView.setText("Confirm Password");
            textView2.setText("Enter your password again and press '=' to confirm and Enter into Locker");
        }
        inflate.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(inflate);
        dialog.setCancelable(false);
        dialog.show();
    }

    abstract void cancelAnimation();


    public void onBackPressed() {
        if (this.mPadViewPager == null || this.mPadViewPager.getCurrentItem() == 0) {
            super.onBackPressed();
            return;
        }
        cancelAnimation();
        this.mPadViewPager.setCurrentItem(this.mPadViewPager.getCurrentItem() - 1);
    }

    public void onButtonClick(View view) {
        int id = view.getId();
        if (id == R.id.eq) {
            onEquals();
        } else if (id == R.id.del) {
            onDelete();
        } else if (id == R.id.clr) {
            if (this.isFirstTime) {
                Toast.makeText(getApplicationContext(), "only digits are allowed for password", Toast.LENGTH_LONG).show();
            } else {
                onClear();
            }
        } else if (id == R.id.fun_cos || id == R.id.fun_ln || id == R.id.fun_log || id == R.id.fun_sin || id == R.id.fun_tan) {
            if (this.isFirstTime) {
                Toast.makeText(getApplicationContext(), "only digits are allowed for password", Toast.LENGTH_LONG).show();
            } else {
                this.mFormulaEditText.append(((Button) view).getText() + "(");
            }
        } else if (!this.isFirstTime || Character.isDigit(((Button) view).getText().charAt(0))) {
            this.mFormulaEditText.append(((Button) view).getText());
        } else {
            Toast.makeText(getApplicationContext(), "only digits are allowed for password", Toast.LENGTH_LONG).show();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }


    protected void onCreate(Bundle bundle) {
        String resourceName;
        CalculatorEditText calculatorEditText;
        CalculatorExpressionTokenizer calculatorExpressionTokenizer;
        RecyclerView recyclerView;
        CalcThemesRecyclerAdapter calcThemesRecyclerAdapter;
        String str = null;
        super.onCreate(bundle);
        this.fromBackGalleryLock = getIntent().getBooleanExtra("fromBackGalleryLock", false);
        setContentView(R.layout.activity_calculator);
        this.prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.edit = this.prefs.edit();
        this.currentAdvancedPadColor = this.prefs.getInt("currentAdvancedPadColor", advancedPadColors[0]);
        this.currentAdvancedTextColor = this.prefs.getInt("currentAdvancedTextColor", advancedTextColors[0]);
        this.currentDisplayTextColor = this.prefs.getInt("currentDisplayTextColor", displayTextColors[0]);
        this.currentDisplayViewColor = this.prefs.getInt("currentDisplayViewColor", displayViewColors[0]);
        this.currentNumPadColor = this.prefs.getInt("currentNumPadColor", numbericPadColors[0]);
        this.currentNumPadTextColor = this.prefs.getInt("currentNumPadTextColor", numberPadTextColors[0]);
        this.currentOperatorPadColor = this.prefs.getInt("currentOperatorPadColor", operatorPadColors[0]);
        this.currentOperatorpadTextColor = this.prefs.getInt("currentOperatorpadTextColor", operatorPadTextColors[0]);
        this.currentStatusBarColor = this.prefs.getInt("currentStatusBarColor", statusBarColors[0]);
        this.currentPosition = this.prefs.getInt("currentPosition", 0);
        this.currentThemeIcon = this.prefs.getInt("currentThemeIcon", R.drawable.theme_icon);
        try {
            resourceName = getResources().getResourceName(this.currentThemeIcon);
            try {
                if (new File(resourceName).getName().contains("theme_icon")) {
                    str = resourceName;
                }
            } catch (Resources.NotFoundException e) {
                str = resourceName;
                if (str == null) {
                    if (this.currentPosition != 2) {
                    }
                    this.currentThemeIcon = R.drawable.theme_icon_dark;
                    this.edit.putInt("currentThemeIcon", this.currentThemeIcon);
                    this.edit.commit();
                }
                this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                this.fakeEnabled = this.prefs.getBoolean("fakeEnabled", false);
                this.fakePass = this.prefs.getString("fakePin", "  ");
                this.mPass = this.prefs.getString("password", "   ");
                this.isFirstTime = this.mPass.length() < 4;
                this.needEqual = this.prefs.getBoolean("needEqual", false);
                if (this.isFirstTime) {
                    showPasswordDialog(false);
                }
//                if (this.prefs.getBoolean("isFinger", false)) {
//                    this.keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
//                    this.fingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
//                    if (this.fingerprintManager.isHardwareDetected()) {
//                        if (!this.keyguardManager.isKeyguardSecure()) {
//                        }
//                        try {
//                            generateKey();
//                            if (cipherInit()) {
//                                this.cryptoObject = new FingerprintManager.CryptoObject(this.cipher);
//                                this.helper = new FingerprintHandler(this);
//                                this.helper.startAuth(this.fingerprintManager, this.cryptoObject, new C32314());
//                                if (this.prefs.getBoolean("isFirstTimeFinger", true)) {
//                                    Toast.makeText(getApplicationContext(), "you can use your fingerprint", Toast.LENGTH_LONG).show();
//                                    this.edit.putBoolean("isFirstTimeFinger", false);
//                                    this.edit.commit();
//                                }
//                            }
//                        } catch (RuntimeException e2) {
//                            return;
//                        }
//                    }
//                }
                this.mDisplayView = (RelativeLayout) findViewById(R.id.display);
                this.mFormulaEditText = (CalculatorEditText) findViewById(R.id.formula);
                this.mResultEditText = (CalculatorEditText) findViewById(R.id.result);
                this.btnTheme = (ImageButton) findViewById(R.id.btnTheme);
                this.mPadViewPager = (NineOldViewPager) findViewById(R.id.pad_pager);
                this.mDeleteButton = findViewById(R.id.del);
                this.mClearButton = findViewById(R.id.clr);
                this.padNumeric = findViewById(R.id.pad_numeric);
                this.padOperator = findViewById(R.id.pad_operator);
                this.padAdvanced = findViewById(R.id.pad_advanced);
                this.mEqualButton = this.padNumeric.findViewById(R.id.eq);
                this.mEqualButton = this.padOperator.findViewById(R.id.eq);
                this.mTokenizer = new CalculatorExpressionTokenizer(this);
                this.mEvaluator = new CalculatorExpressionEvaluator(this.mTokenizer);
                if (bundle == null) {
                    bundle = Bundle.EMPTY;
                }
                setState(CalculatorState.values()[bundle.getInt(KEY_CURRENT_STATE, CalculatorState.INPUT.ordinal())]);
                str = bundle.getString(KEY_CURRENT_EXPRESSION);
                this.coverOpened = bundle.getBoolean("coverOpened");
                calculatorEditText = this.mFormulaEditText;
                calculatorExpressionTokenizer = this.mTokenizer;
                if (str == null) {
                    str = "";
                }
                calculatorEditText.setText(calculatorExpressionTokenizer.getLocalizedExpression(str));
                this.mEvaluator.evaluate(this.mFormulaEditText.getText(), (CalculatorExpressionEvaluator.EvaluateCallback) this);
                this.mFormulaEditText.setEditableFactory(this.mFormulaEditableFactory);
                this.mFormulaEditText.addTextChangedListener(this.mFormulaTextWatcher);
                this.mFormulaEditText.setOnKeyListener(this.mFormulaOnKeyListener);
                this.mFormulaEditText.setOnTextSizeChangeListener(this);
                this.mDeleteButton.setOnLongClickListener(this);
                recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                calcThemesRecyclerAdapter = new CalcThemesRecyclerAdapter();
                calcThemesRecyclerAdapter.setOnItemCLickListener(this);
                recyclerView.setAdapter(calcThemesRecyclerAdapter);
                this.digit0 = (Button) findViewById(R.id.digit_0);
                this.digit1 = (Button) findViewById(R.id.digit_1);
                this.digit2 = (Button) findViewById(R.id.digit_2);
                this.digit3 = (Button) findViewById(R.id.digit_3);
                this.digit4 = (Button) findViewById(R.id.digit_4);
                this.digit5 = (Button) findViewById(R.id.digit_5);
                this.digit6 = (Button) findViewById(R.id.digit_6);
                this.digit7 = (Button) findViewById(R.id.digit_7);
                this.digit8 = (Button) findViewById(R.id.digit_8);
                this.digit9 = (Button) findViewById(R.id.digit_9);
                this.digitEQ = (Button) findViewById(R.id.eq);
                this.digitPoint = (Button) findViewById(R.id.dec_point);
                this.btnPlus = (Button) findViewById(R.id.op_add);
                this.btnSub = (Button) findViewById(R.id.op_sub);
                this.btnMul = (Button) findViewById(R.id.op_mul);
                this.btnDiv = (Button) findViewById(R.id.op_div);
                this.btnDel = (Button) findViewById(R.id.del);
                this.btnClr = (Button) findViewById(R.id.clr);
                this.funRoot = (Button) findViewById(R.id.op_sqrt);
                this.funPow = (Button) findViewById(R.id.op_pow);
                this.funPi = (Button) findViewById(R.id.const_pi);
                this.funLpren = (Button) findViewById(R.id.lparen);
                this.funRpren = (Button) findViewById(R.id.rparen);
                this.funCos = (Button) findViewById(R.id.fun_cos);
                this.funE = (Button) findViewById(R.id.const_e);
                this.funFact = (Button) findViewById(R.id.op_fact);
                this.funln = (Button) findViewById(R.id.fun_ln);
                this.funLog = (Button) findViewById(R.id.fun_log);
                this.funSine = (Button) findViewById(R.id.fun_sin);
                this.funTan = (Button) findViewById(R.id.fun_tan);
                setThemeColors();
            }
        } catch (Resources.NotFoundException e3) {
            resourceName = null;
            str = resourceName;
            if (str == null) {
                if (this.currentPosition != 2) {
                }
                this.currentThemeIcon = R.drawable.theme_icon_dark;
                this.edit.putInt("currentThemeIcon", this.currentThemeIcon);
                this.edit.commit();
            }
            this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
            this.fakeEnabled = this.prefs.getBoolean("fakeEnabled", false);
            this.fakePass = this.prefs.getString("fakePin", "  ");
            this.mPass = this.prefs.getString("password", "   ");
            if (this.mPass.length() < 4) {
            }
            this.isFirstTime = this.mPass.length() < 4;
            this.needEqual = this.prefs.getBoolean("needEqual", false);
            if (this.isFirstTime) {
                showPasswordDialog(false);
            }
//            if (this.prefs.getBoolean("isFinger", false)) {
//                this.keyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
//                this.fingerprintManager = (FingerprintManager) getSystemService(Context.FINGERPRINT_SERVICE);
//                if (this.fingerprintManager.isHardwareDetected()) {
//                    if (this.keyguardManager.isKeyguardSecure()) {
//                    }
//                    generateKey();
//                    if (cipherInit()) {
//                        this.cryptoObject = new FingerprintManager.CryptoObject(this.cipher);
//                        this.helper = new FingerprintHandler(this);
//                        this.helper.startAuth(this.fingerprintManager, this.cryptoObject, new C32314());
//                        if (this.prefs.getBoolean("isFirstTimeFinger", true)) {
//                            Toast.makeText(getApplicationContext(), "you can use your fingerprint", Toast.LENGTH_LONG).show();
//                            this.edit.putBoolean("isFirstTimeFinger", false);
//                            this.edit.commit();
//                        }
//                    }
//                }
//            }
            this.mDisplayView = (RelativeLayout) findViewById(R.id.display);
            this.mFormulaEditText = (CalculatorEditText) findViewById(R.id.formula);
            this.mResultEditText = (CalculatorEditText) findViewById(R.id.result);
            this.btnTheme = (ImageButton) findViewById(R.id.btnTheme);
            this.mPadViewPager = (NineOldViewPager) findViewById(R.id.pad_pager);
            this.mDeleteButton = findViewById(R.id.del);
            this.mClearButton = findViewById(R.id.clr);
            this.padNumeric = findViewById(R.id.pad_numeric);
            this.padOperator = findViewById(R.id.pad_operator);
            this.padAdvanced = findViewById(R.id.pad_advanced);
            this.mEqualButton = this.padNumeric.findViewById(R.id.eq);
            this.mEqualButton = this.padOperator.findViewById(R.id.eq);
            this.mTokenizer = new CalculatorExpressionTokenizer(this);
            this.mEvaluator = new CalculatorExpressionEvaluator(this.mTokenizer);
            if (bundle == null) {
                bundle = Bundle.EMPTY;
            }
            setState(CalculatorState.values()[bundle.getInt(KEY_CURRENT_STATE, CalculatorState.INPUT.ordinal())]);
            str = bundle.getString(KEY_CURRENT_EXPRESSION);
            this.coverOpened = bundle.getBoolean("coverOpened");
            calculatorEditText = this.mFormulaEditText;
            calculatorExpressionTokenizer = this.mTokenizer;
            if (str == null) {
                str = "";
            }
            calculatorEditText.setText(calculatorExpressionTokenizer.getLocalizedExpression(str));
            this.mEvaluator.evaluate(this.mFormulaEditText.getText(), (CalculatorExpressionEvaluator.EvaluateCallback) this);
            this.mFormulaEditText.setEditableFactory(this.mFormulaEditableFactory);
            this.mFormulaEditText.addTextChangedListener(this.mFormulaTextWatcher);
            this.mFormulaEditText.setOnKeyListener(this.mFormulaOnKeyListener);
            this.mFormulaEditText.setOnTextSizeChangeListener(this);
            this.mDeleteButton.setOnLongClickListener(this);
            recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            calcThemesRecyclerAdapter = new CalcThemesRecyclerAdapter();
            calcThemesRecyclerAdapter.setOnItemCLickListener(this);
            recyclerView.setAdapter(calcThemesRecyclerAdapter);
            this.digit0 = (Button) findViewById(R.id.digit_0);
            this.digit1 = (Button) findViewById(R.id.digit_1);
            this.digit2 = (Button) findViewById(R.id.digit_2);
            this.digit3 = (Button) findViewById(R.id.digit_3);
            this.digit4 = (Button) findViewById(R.id.digit_4);
            this.digit5 = (Button) findViewById(R.id.digit_5);
            this.digit6 = (Button) findViewById(R.id.digit_6);
            this.digit7 = (Button) findViewById(R.id.digit_7);
            this.digit8 = (Button) findViewById(R.id.digit_8);
            this.digit9 = (Button) findViewById(R.id.digit_9);
            this.digitEQ = (Button) findViewById(R.id.eq);
            this.digitPoint = (Button) findViewById(R.id.dec_point);
            this.btnPlus = (Button) findViewById(R.id.op_add);
            this.btnSub = (Button) findViewById(R.id.op_sub);
            this.btnMul = (Button) findViewById(R.id.op_mul);
            this.btnDiv = (Button) findViewById(R.id.op_div);
            this.btnDel = (Button) findViewById(R.id.del);
            this.btnClr = (Button) findViewById(R.id.clr);
            this.funRoot = (Button) findViewById(R.id.op_sqrt);
            this.funPow = (Button) findViewById(R.id.op_pow);
            this.funPi = (Button) findViewById(R.id.const_pi);
            this.funLpren = (Button) findViewById(R.id.lparen);
            this.funRpren = (Button) findViewById(R.id.rparen);
            this.funCos = (Button) findViewById(R.id.fun_cos);
            this.funE = (Button) findViewById(R.id.const_e);
            this.funFact = (Button) findViewById(R.id.op_fact);
            this.funln = (Button) findViewById(R.id.fun_ln);
            this.funLog = (Button) findViewById(R.id.fun_log);
            this.funSine = (Button) findViewById(R.id.fun_sin);
            this.funTan = (Button) findViewById(R.id.fun_tan);
            setThemeColors();
        }
        if (str != null) {
            if (this.currentPosition != 2 || this.currentPosition == 3 || this.currentPosition == 5) {
                this.currentThemeIcon = R.drawable.theme_icon_dark;
            } else {
                this.currentThemeIcon = R.drawable.theme_icon;
            }
            this.edit.putInt("currentThemeIcon", this.currentThemeIcon);
            this.edit.commit();
        }
        this.mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        this.fakeEnabled = this.prefs.getBoolean("fakeEnabled", false);
        this.fakePass = this.prefs.getString("fakePin", "  ");
        this.mPass = this.prefs.getString("password", "   ");
        if (this.mPass.length() < 4) {
        }
        this.isFirstTime = this.mPass.length() < 4;
        this.needEqual = this.prefs.getBoolean("needEqual", false);
        if (this.isFirstTime) {
            showPasswordDialog(false);
        }
        this.mDisplayView = (RelativeLayout) findViewById(R.id.display);
        this.mFormulaEditText = (CalculatorEditText) findViewById(R.id.formula);
        this.mResultEditText = (CalculatorEditText) findViewById(R.id.result);
        this.btnTheme = (ImageButton) findViewById(R.id.btnTheme);
        this.mPadViewPager = (NineOldViewPager) findViewById(R.id.pad_pager);
        this.mDeleteButton = findViewById(R.id.del);
        this.mClearButton = findViewById(R.id.clr);
        this.padNumeric = findViewById(R.id.pad_numeric);
        this.padOperator = findViewById(R.id.pad_operator);
        this.padAdvanced = findViewById(R.id.pad_advanced);
        this.mEqualButton = this.padNumeric.findViewById(R.id.eq);
        if (this.mEqualButton == null || this.mEqualButton.getVisibility() != View.VISIBLE) {
            this.mEqualButton = this.padOperator.findViewById(R.id.eq);
        }
        this.mTokenizer = new CalculatorExpressionTokenizer(this);
        this.mEvaluator = new CalculatorExpressionEvaluator(this.mTokenizer);
        if (bundle == null) {
            bundle = Bundle.EMPTY;
        }
        setState(CalculatorState.values()[bundle.getInt(KEY_CURRENT_STATE, CalculatorState.INPUT.ordinal())]);
        str = bundle.getString(KEY_CURRENT_EXPRESSION);
        this.coverOpened = bundle.getBoolean("coverOpened");
        calculatorEditText = this.mFormulaEditText;
        calculatorExpressionTokenizer = this.mTokenizer;
        if (str == null) {
            str = "";
        }
        calculatorEditText.setText(calculatorExpressionTokenizer.getLocalizedExpression(str));
        this.mEvaluator.evaluate(this.mFormulaEditText.getText(), (CalculatorExpressionEvaluator.EvaluateCallback) this);
        this.mFormulaEditText.setEditableFactory(this.mFormulaEditableFactory);
        this.mFormulaEditText.addTextChangedListener(this.mFormulaTextWatcher);
        this.mFormulaEditText.setOnKeyListener(this.mFormulaOnKeyListener);
        this.mFormulaEditText.setOnTextSizeChangeListener(this);
        this.mDeleteButton.setOnLongClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        calcThemesRecyclerAdapter = new CalcThemesRecyclerAdapter();
        calcThemesRecyclerAdapter.setOnItemCLickListener(this);
        recyclerView.setAdapter(calcThemesRecyclerAdapter);
        this.digit0 = (Button) findViewById(R.id.digit_0);
        this.digit1 = (Button) findViewById(R.id.digit_1);
        this.digit2 = (Button) findViewById(R.id.digit_2);
        this.digit3 = (Button) findViewById(R.id.digit_3);
        this.digit4 = (Button) findViewById(R.id.digit_4);
        this.digit5 = (Button) findViewById(R.id.digit_5);
        this.digit6 = (Button) findViewById(R.id.digit_6);
        this.digit7 = (Button) findViewById(R.id.digit_7);
        this.digit8 = (Button) findViewById(R.id.digit_8);
        this.digit9 = (Button) findViewById(R.id.digit_9);
        this.digitEQ = (Button) findViewById(R.id.eq);
        this.digitPoint = (Button) findViewById(R.id.dec_point);
        this.btnPlus = (Button) findViewById(R.id.op_add);
        this.btnSub = (Button) findViewById(R.id.op_sub);
        this.btnMul = (Button) findViewById(R.id.op_mul);
        this.btnDiv = (Button) findViewById(R.id.op_div);
        this.btnDel = (Button) findViewById(R.id.del);
        this.btnClr = (Button) findViewById(R.id.clr);
        this.funRoot = (Button) findViewById(R.id.op_sqrt);
        this.funPow = (Button) findViewById(R.id.op_pow);
        this.funPi = (Button) findViewById(R.id.const_pi);
        this.funLpren = (Button) findViewById(R.id.lparen);
        this.funRpren = (Button) findViewById(R.id.rparen);
        this.funCos = (Button) findViewById(R.id.fun_cos);
        this.funE = (Button) findViewById(R.id.const_e);
        this.funFact = (Button) findViewById(R.id.op_fact);
        this.funln = (Button) findViewById(R.id.fun_ln);
        this.funLog = (Button) findViewById(R.id.fun_log);
        this.funSine = (Button) findViewById(R.id.fun_sin);
        this.funTan = (Button) findViewById(R.id.fun_tan);
        setThemeColors();
//        resourceName = this.prefs.getString("cmpName", "overlays.com.calculatorlib.CalcStartActivity_calc");
//        if (!resourceName.equals("overlays.com.calculatorlib.CalcStartActivity_calc") && !this.coverOpened && !this.fromBackGalleryLock) {
//            ViewStub viewStub = (ViewStub) findViewById(R.id.stub1);
//            if (resourceName.equals("overlays.com.calculatorlib.CalcStartActivity_scanner")) {
//                if (Build.VERSION.SDK_INT >= 21) {
//                    Window window = getWindow();
//                    window.addFlags(Integer.MIN_VALUE);
//                    window.setStatusBarColor(getResources().getColor(R.color.scanner_bg_color));
//                }
//                final View inflate = viewStub.inflate();
//                inflate.setClickable(true);
//                inflate.findViewById(R.id.longPressObject).setOnLongClickListener(new OnLongClickListener() {
//                    public boolean onLongClick(View view) {
//                        inflate.setVisibility(8);
//                        Calculator.this.coverOpened = true;
//                        if (Build.VERSION.SDK_INT >= 21) {
//                            Window window = Calculator.this.getWindow();
//                            window.addFlags(Integer.MIN_VALUE);
//                            window.setStatusBarColor(Calculator.this.currentStatusBarColor);
//                        }
//                        return false;
//                    }
//                });
//                inflate.findViewById(R.id.btnDone).setOnClickListener(new C32336());
//            }
//        }
    }

    public void onEvaluate(String str, String str2, int i) {
        if (this.mCurrentState == CalculatorState.INPUT) {
            this.mResultEditText.setText(str2);
        } else if (i != -1) {
            onError(i);
        } else if (!TextUtils.isEmpty(str2)) {
            onResult(str2);
        } else if (this.mCurrentState == CalculatorState.EVALUATE) {
            setState(CalculatorState.INPUT);
        }
        this.mFormulaEditText.requestFocus();
    }

    public boolean onLongClick(View view) {
        if (view.getId() != R.id.del) {
            return false;
        }
        onClear();
        return true;
    }

    abstract void onResult(String str);

    protected void onSaveInstanceState(Bundle bundle) {
        cancelAnimation();
        super.onSaveInstanceState(bundle);
        bundle.putInt(KEY_CURRENT_STATE, this.mCurrentState.ordinal());
        bundle.putBoolean("coverOpened", this.coverOpened);
        bundle.putString(KEY_CURRENT_EXPRESSION, this.mTokenizer.getNormalizedExpression(this.mFormulaEditText.getText().toString()));
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {

        super.onStop();
    }

    @Override
    public void onTextSizeChanged(final TextView textView, float oldSize) {
        if (mCurrentState != CalculatorState.INPUT) {
            // Only animate text changes that occur from user input.
            return;
        }

        // Calculate the values needed to perform the scale and translation animations,
        // maintaining the same apparent baseline for the displayed text.
        final float textScale = oldSize / textView.getTextSize();
        final float translationX = (1.0f - textScale) *
                (textView.getWidth() / 2.0f - ViewCompat.getPaddingEnd(textView));
        final float translationY = (1.0f - textScale) *
                (textView.getHeight() / 2.0f - textView.getPaddingBottom());

        final AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(textView, "scaleX", textScale, 1.0f),
                ObjectAnimator.ofFloat(textView, "scaleY", textScale, 1.0f),
                ObjectAnimator.ofFloat(textView, "translationX", translationX, 0.0f),
                ObjectAnimator.ofFloat(textView, "translationY", translationY, 0.0f));
        animatorSet.setDuration(getResources().getInteger(android.R.integer.config_mediumAnimTime));
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();
    }


    public void onThemeClick(View view) {
        if (this.mDrawerLayout.isDrawerOpen(Gravity.RIGHT)) {
            this.mDrawerLayout.closeDrawer(Gravity.RIGHT);
        } else {
            this.mDrawerLayout.openDrawer(Gravity.RIGHT);
        }
    }

    public void onThemeClicked(int i) {
        this.mDrawerLayout.closeDrawer(Gravity.RIGHT);
        this.currentPosition = i;
        this.currentStatusBarColor = this.statusBarColors[this.currentPosition];
        this.currentOperatorpadTextColor = this.operatorPadTextColors[this.currentPosition];
        this.currentOperatorPadColor = this.operatorPadColors[this.currentPosition];
        this.currentAdvancedPadColor = this.advancedPadColors[this.currentPosition];
        this.currentDisplayTextColor = this.displayTextColors[this.currentPosition];
        this.currentAdvancedTextColor = this.advancedTextColors[this.currentPosition];
        this.currentDisplayViewColor = this.displayViewColors[this.currentPosition];
        this.currentNumPadTextColor = this.numberPadTextColors[this.currentPosition];
        this.currentNumPadColor = this.numbericPadColors[this.currentPosition];
        if (i == 2 || i == 3 || i == 5) {
            this.currentThemeIcon = R.drawable.theme_icon_dark;
        } else {
            this.currentThemeIcon = R.drawable.theme_icon;
        }
        this.edit.putInt("currentPosition", this.currentPosition);
        this.edit.putInt("currentStatusBarColor", this.currentStatusBarColor);
        this.edit.putInt("currentOperatorpadTextColor", this.currentOperatorpadTextColor);
        this.edit.putInt("currentOperatorPadColor", this.currentOperatorPadColor);
        this.edit.putInt("currentAdvancedPadColor", this.currentAdvancedPadColor);
        this.edit.putInt("currentDisplayTextColor", this.currentDisplayTextColor);
        this.edit.putInt("currentAdvancedTextColor", this.currentAdvancedTextColor);
        this.edit.putInt("currentDisplayViewColor", this.currentDisplayViewColor);
        this.edit.putInt("currentNumPadTextColor", this.currentNumPadTextColor);
        this.edit.putInt("currentNumPadColor", this.currentNumPadColor);
        this.edit.putInt("currentThemeIcon", this.currentThemeIcon);
        this.edit.commit();
        setThemeColors();
    }

    public void onUserInteraction() {
        super.onUserInteraction();
        cancelAnimation();
    }

    abstract void reveal(View view, int i, AnimatorListenerWrapper animatorListenerWrapper);

    protected void setState(CalculatorState calculatorState) {
        if (this.mCurrentState != calculatorState) {
            this.mCurrentState = calculatorState;
            if (calculatorState == CalculatorState.RESULT || calculatorState == CalculatorState.ERROR) {
                this.mDeleteButton.setVisibility(View.GONE);
                this.mClearButton.setVisibility(View.VISIBLE);
            } else {
                this.mDeleteButton.setVisibility(View.VISIBLE);
                this.mClearButton.setVisibility(View.GONE);
            }
            if (calculatorState == CalculatorState.ERROR) {
                int color = getResources().getColor(R.color.calculator_error_color);
                this.mFormulaEditText.setTextColor(color);
                this.mResultEditText.setTextColor(color);
                CalcUtils.setStatusBarColorCompat(getWindow(), color);
                return;
            }
            this.mFormulaEditText.setTextColor(this.currentDisplayTextColor);
            this.mResultEditText.setTextColor(this.currentDisplayTextColor);
            CalcUtils.setStatusBarColorCompat(getWindow(), this.currentStatusBarColor);
        }
    }
}
