package calculator.vault.com.calculator;

import android.app.Dialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import calculator.vault.com.R;

public class ScannerDemoActivity extends AppCompatActivity {
    View flTry;

    class C32602 implements OnClickListener {
        C32602() {
        }

        public void onClick(View view) {
            Toast.makeText(ScannerDemoActivity.this.getApplicationContext(), "Please Long-press on Icon on top", Toast.LENGTH_SHORT).show();
        }
    }

    class C32613 implements OnClickListener {
        C32613() {
        }

        public void onClick(View view) {
            Toast.makeText(ScannerDemoActivity.this.getApplicationContext(), "Long-press to activate", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.layout_virus_scanner_demo);
        final Typeface createFromAsset = Typeface.createFromAsset(getAssets(), "tf.ttf");
        final Typeface createFromAsset2 = Typeface.createFromAsset(getAssets(), "RoboBold.ttf");
        this.flTry = findViewById(R.id.flTry);
        ((PulsatorLayout) findViewById(R.id.pulsator)).start();
        ((TextView) findViewById(R.id.tv1)).setTypeface(createFromAsset2);
        ((TextView) findViewById(R.id.tv2)).setTypeface(createFromAsset);
        ((Button) findViewById(R.id.btnDone)).setTypeface(createFromAsset);
        findViewById(R.id.longPressObject).setOnLongClickListener(new OnLongClickListener() {
            private void showConfirmDialog() {
                final Dialog dialog = new Dialog(ScannerDemoActivity.this);
                View inflate = ScannerDemoActivity.this.getLayoutInflater().inflate(R.layout.dialog_confirm_scanner, null);
                TextView textView = (TextView) inflate.findViewById(R.id.tvYES);
                TextView textView2 = (TextView) inflate.findViewById(R.id.tvNO);
                textView.setTypeface(createFromAsset2);
                textView2.setTypeface(createFromAsset2);
                ((TextView) inflate.findViewById(R.id.textView1)).setTypeface(createFromAsset);
                ((TextView) inflate.findViewById(R.id.textView2)).setTypeface(createFromAsset);
                ((TextView) inflate.findViewById(R.id.tvDialogText)).setTypeface(createFromAsset);
                ((TextView) inflate.findViewById(R.id.tvDialogTitle)).setTypeface(createFromAsset2);
                textView.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        ScannerDemoActivity.this.setResult(-1);
                        ScannerDemoActivity.this.finish();
                        dialog.dismiss();
                    }
                });
                textView2.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.setContentView(inflate);
                dialog.show();
            }

            public boolean onLongClick(View view) {
                showConfirmDialog();
                return false;
            }
        });
        findViewById(R.id.btnDone).setOnClickListener(new C32602());
        findViewById(R.id.longPressObject).setOnClickListener(new C32613());
    }

    public void onWindowFocusChanged(boolean z) {
        if (!z || this.flTry == null) {
            this.flTry.setVisibility(View.GONE);
        } else {
            this.flTry.setVisibility(View.VISIBLE);
        }
        super.onWindowFocusChanged(z);
    }
}
