package calculator.vault.com;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mobiburn.Mobiburn;

import calculator.vault.com.calculator.Calculator;
import calculator.vault.com.calculator.CalculatorGB;
import calculator.vault.com.calculator.CalculatorL;
import calculator.vault.com.calculator.CalculatorL23;
import calculator.vault.com.calculator.Utils;

/**
 * Created by Kundan Singh on 25-03-2018.
 */


public class MainActivity11 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mobiburn.init(this, "9aadf116-bd6e-4ece-ac9a-09fa3d91a869");
        if (Utils.hasLollipop()) {
            if (Build.VERSION.SDK_INT >= 23) {
                startActivity(new Intent(this, CalculatorL23.class));
            } else {
                startActivity(new Intent(this, CalculatorL.class));
            }
        } else {
            startActivity(new Intent(this, CalculatorGB.class));
        }
        finish();
    }
}
