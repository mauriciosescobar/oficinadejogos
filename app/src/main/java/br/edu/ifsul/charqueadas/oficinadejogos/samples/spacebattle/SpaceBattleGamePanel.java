package br.edu.ifsul.charqueadas.oficinadejogos.samples.spacebattle;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

import br.edu.ifsul.charqueadas.oficinadejogos.core.GamePanel;

public class SpaceBattleGamePanel extends GamePanel {

    private ArrayList<Fire> fires = new ArrayList<>();

    public SpaceBattleGamePanel(Context context) {
        super(context);

        addGameObject(new SpaceShip(context, this));
    }

    public void addFire(Fire newFire) {
        this.fires.add ( newFire );
    }

    @Override
    public void update() {
        super.update();

        for(Fire f : fires)
            f.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        for(Fire f : fires)
            f.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
