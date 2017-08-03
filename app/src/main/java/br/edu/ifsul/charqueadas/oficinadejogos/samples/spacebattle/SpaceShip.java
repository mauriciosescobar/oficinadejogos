package br.edu.ifsul.charqueadas.oficinadejogos.samples.spacebattle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import br.edu.ifsul.charqueadas.oficinadejogos.R;
import br.edu.ifsul.charqueadas.oficinadejogos.core.GameObject;
import br.edu.ifsul.charqueadas.oficinadejogos.core.Metrics;

public class SpaceShip implements GameObject {

    private Bitmap[] frames;
    private Paint paint;
    private Context context;
    private Point destination;
    private Rect rectPlayer;
    private float speed = 8;
    private long lastUpdateTime;
    private long lastFrameTime;
    private int framePosition = 0;
    private boolean isMoving = false;
    private enum Direction { LEFT, RIGHT };
    private Direction currentDirection;

    private long lastFiredTime;

    private SpaceBattleGamePanel canvas;

    private int sts = 0; // scaled tile size

    public SpaceShip(Context context, SpaceBattleGamePanel canvas) {
        this.paint = new Paint();
        this.context = context;

        this.canvas = canvas;

        sts = (int) (72.0f * Metrics.SCALED_DENSITY);

        Bitmap tiles =
            BitmapFactory.decodeResource(context.getResources(), R.drawable.spaceship);

        frames = new Bitmap[4];

        frames[0] = Bitmap.createBitmap(tiles, 0,     0, sts, sts);
        frames[1] = Bitmap.createBitmap(tiles, sts,   0, sts, sts);
        frames[2] = Bitmap.createBitmap(tiles, sts*2, 0, sts, sts);
        frames[3] = Bitmap.createBitmap(tiles, sts*3, 0, sts, sts);

        int x = Metrics.SCREEN_WIDTH/2 - frames[0].getWidth()/2;
        int y = Metrics.SCREEN_HEIGHT/2 - frames[0].getHeight()/2;

        this.rectPlayer = new Rect(x, y, x + frames[0].getWidth(), y + frames[0].getHeight());

        this.speed = 8;

        lastUpdateTime = lastFrameTime = System.currentTimeMillis();

        lastFiredTime = System.currentTimeMillis();
    }

    public void update() {

        float elapsedTime = System.currentTimeMillis() - lastUpdateTime;

        if (destination != null) {

            isMoving = true;

            if (rectPlayer.left < destination.x) {
                currentDirection = Direction.RIGHT;
            } else {
                currentDirection = Direction.LEFT;
            }

            Rect destRect = new Rect(destination.x, destination.y, destination.x + 5, destination.y + 5);

            if (!Rect.intersects(rectPlayer, destRect)) {

                float b = this.destination.x - this.rectPlayer.centerX();
                float a = this.destination.y - this.rectPlayer.centerY();
                float c = (float) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));

                float spdX = (b * speed) / c;
                float spdY = (a * speed) / c;

                this.rectPlayer.offset((int) spdX, (int) spdY);

            } else {
                destination = null;

                isMoving = false;
            }
        }


        if ((System.currentTimeMillis() - lastFrameTime) > 250) {

            framePosition++;

            if (framePosition > 3) {
                framePosition = 0;
            }

            lastFrameTime = System.currentTimeMillis();
        }


        // gera um tiro a cada 2 segundos
        if ((System.currentTimeMillis() - lastFiredTime) > 1000 ) {

            Fire newFire = new Fire(
                    rectPlayer.left + (sts/2) - 15,
                    rectPlayer.top - 30);

            canvas.addFire( newFire );

            lastFiredTime = System.currentTimeMillis();
        }

        lastUpdateTime = System.currentTimeMillis();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(frames[framePosition], null, rectPlayer, paint);
    }

    public void onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                destination = new Point((int)event.getX(), (int)event.getY());
                break;
        }
    }
}
