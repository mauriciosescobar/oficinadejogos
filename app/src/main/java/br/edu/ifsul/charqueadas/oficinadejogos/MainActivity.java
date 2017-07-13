package br.edu.ifsul.charqueadas.oficinadejogos;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Entra em modo tela cheia
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Remove a barra de título da aplicacao
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // obtém as dimensões da janela
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // Armazena em variáveis globais
        Metrics.SCREEN_WIDTH = dm.widthPixels;
        Metrics.SCREEN_HEIGHT = dm.heightPixels;

        setContentView(new GamePanel(this));
   }
}
