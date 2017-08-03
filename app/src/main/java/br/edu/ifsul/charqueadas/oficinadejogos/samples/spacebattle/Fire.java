package br.edu.ifsul.charqueadas.oficinadejogos.samples.spacebattle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import br.edu.ifsul.charqueadas.oficinadejogos.R;
import br.edu.ifsul.charqueadas.oficinadejogos.core.Metrics;

public class Fire {

    private int x, y;
    static int w, h;
    private Paint p = new Paint();
    private long lastUpdate;
    static private Bitmap[] frames;
    private int framePosition = 0;
    private long lastFrameTime;

    public Fire(int x, int y, Context context) {
        this.x = x;
        this.y = y;
        this.p.setColor(Color.BLUE);
        this.lastUpdate = System.currentTimeMillis();
        this.lastFrameTime = lastUpdate;

        w = (int) ((260.0f/5.0f) * Metrics.SCALED_DENSITY);
        h = (int) (121f * Metrics.SCALED_DENSITY);

        Bitmap tiles =
                BitmapFactory.decodeResource(context.getResources(), R.drawable.fires);

        frames = new Bitmap[5];

        frames[0] = Bitmap.createBitmap(tiles, 0,   0, w, h);
        frames[1] = Bitmap.createBitmap(tiles, w,   0, w, h);
        frames[2] = Bitmap.createBitmap(tiles, w*2, 0, w, h);
        frames[3] = Bitmap.createBitmap(tiles, w*3, 0, w, h);
        frames[4] = Bitmap.createBitmap(tiles, w*4, 0, w, h);
    }

    public void update() {
        long current = System.currentTimeMillis();

        if ( (current - lastUpdate ) > (10) ) {
            y -= 10;
            lastUpdate = System.currentTimeMillis();
        }

        if ((System.currentTimeMillis() - lastFrameTime) > 250) {

            framePosition++;

            if (framePosition > 4) {
                framePosition = 0;
            }

            lastFrameTime = System.currentTimeMillis();
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(frames[framePosition], x, y, p);
    }
}