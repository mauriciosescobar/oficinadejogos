package br.edu.ifsul.charqueadas.oficinadejogos;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        Metrics.SCREEN_WIDTH = dm.widthPixels;
        Metrics.SCREEN_HEIGHT = dm.heightPixels;
        Metrics.SCALED_DENSITY = dm.scaledDensity;

        setContentView(new GamePanel(this));
   }
}
