package calculator.vault.com.lock;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import calculator.vault.com.R;
import calculator.vault.com.p068a.C1584f;


public class ColorThemeActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    Editor editor;
    public int f2773c;
    int[] f2774d = new int[]{R.style.AppThemeOne, R.style.AppThemeTwo, R.style.AppThemeThree, R.style.AppThemeFour, R.style.AppThemeFive,
            R.style.AppThemeSix, R.style.AppThemeSeven, R.style.AppThemeEight, R.style.AppThemeNine, R.style.AppThemeTen, R.style.AppThemeEleven,
            R.style.AppThemeTwelve, R.style.AppThemeThirteen, R.style.AppThemeFourteen, R.style.AppThemeFifteen, R.style.AppThemeSixteen
    };
    private int[] f2775e = new int[]{-14043402, -7380005, -13166199, -12434878,
            -1023342, -1092784, -5552196, -24576,
            -10044566, -11684180, -10453621, -12546099,
            -14575885, -8825528, -16728876, -10011977};
    private int f2776f;


    class C09892 implements OnItemClickListener {
        final /* synthetic */ ColorThemeActivity f2770a;

        C09892(ColorThemeActivity colorThemeActivity) {
            this.f2770a = colorThemeActivity;
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.f2770a.f2775e[i] != this.f2770a.f2776f) {
                this.f2770a.f2776f = this.f2770a.f2775e[i];
                this.f2770a.editor.putInt("currentStyle", this.f2770a.f2774d[i]);
                this.f2770a.editor.putInt("colorPrimary", this.f2770a.f2776f);
                this.f2770a.editor.commit();
                setTheme(this.f2770a.f2776f);
                Intent intent = this.f2770a.getIntent();
                this.f2770a.overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                this.f2770a.finish();
                this.f2770a.overridePendingTransition(0, 0);
                this.f2770a.startActivity(intent);
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.editor = this.sharedPreferences.edit();
        this.f2776f = this.sharedPreferences.getInt("colorPrimary", getResources().getColor(R.color.toolbar_color));
        this.f2773c = this.sharedPreferences.getInt("currentStyle", R.style.AppTheme);
        if (this.f2773c != R.style.AppTheme) {
            setTheme(this.f2773c);
        }
        setContentView((int) R.layout.layout_themes);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        GridView gridView = (GridView) findViewById(R.id.gvColorList);
        gridView.setAdapter(new C1584f(this, this.f2775e, this.f2776f));
        gridView.setOnItemClickListener(new C09892(this));
        ((TextView) findViewById(R.id.textView1)).setTypeface(C1131f.f3315a);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    protected void onPause() {
        overridePendingTransition(0, R.anim.slide_right);
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
    }
}
