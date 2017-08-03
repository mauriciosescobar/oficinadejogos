package br.edu.ifsul.charqueadas.oficinadejogos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import br.edu.ifsul.charqueadas.oficinadejogos.samples.movemente_basics.MovementBasicsActivity;
import br.edu.ifsul.charqueadas.oficinadejogos.samples.spacebattle.SpaceBattleActivity;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onMovementBasics(View source) {
        Intent i = new Intent(getApplicationContext(), MovementBasicsActivity.class);
        startActivity(i);
    }

    public void onSpaceBatte(View source) {
        Intent i = new Intent(getApplicationContext(), SpaceBattleActivity.class);
        startActivity(i);
    }
}
