package calculator.vault.com;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import calculator.vault.com.R;
import calculator.vault.com.p073g.PhotoFolderViewFragment;

/**
 * Created by Kundan Singh on 01-04-2018.
 */

public class TestingApp extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing_act);
        getSupportFragmentManager().beginTransaction().add(R.id.conten_view, new PhotoFolderViewFragment()).commit();
    }
}
