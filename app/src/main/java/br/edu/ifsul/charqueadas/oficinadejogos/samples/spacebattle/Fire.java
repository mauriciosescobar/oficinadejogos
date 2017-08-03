package br.edu.ifsul.charqueadas.oficinadejogos.samples.spacebattle;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Fire {

    int x, y;
    Paint p = new Paint();
    long lastUpdate;

    public Fire(int x, int y) {
        this.x = x;
        this.y = y;
        p.setColor(Color.BLUE);
        this.lastUpdate = System.currentTimeMillis();
    }

    public void update() {
        long current = System.currentTimeMillis();

        if ( (current - lastUpdate ) > (100) ) {
            y -= 10;
            lastUpdate = System.currentTimeMillis();
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawOval(x, y, x + 30, y + 30, p);
    }
}
