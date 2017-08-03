package br.edu.ifsul.charqueadas.oficinadejogos.samples.movemente_basics;

import android.content.Context;

import br.edu.ifsul.charqueadas.oficinadejogos.core.GamePanel;

public class MovementGamePanel extends GamePanel {

    public MovementGamePanel(Context context) {
        super(context);

        Player player = new Player(context);

        addGameObject(player);
    }
}
