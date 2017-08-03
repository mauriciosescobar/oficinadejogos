package br.edu.ifsul.charqueadas.oficinadejogos.samples.movemente_basics;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import br.edu.ifsul.charqueadas.oficinadejogos.core.GamePanel;
import br.edu.ifsul.charqueadas.oficinadejogos.core.Metrics;

public class MovementBasicsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(new MovementGamePanel(this));
   }
}
