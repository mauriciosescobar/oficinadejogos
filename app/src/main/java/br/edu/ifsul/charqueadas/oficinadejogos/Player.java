package br.edu.ifsul.charqueadas.oficinadejogos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.widget.Toast;

public class Player {

    private Bitmap [] frames;
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

    public Player(Context context) {
        this.paint = new Paint();
        this.context = context;

        this.frames = new Bitmap[] { BitmapFactory.decodeResource(context.getResources(), R.drawable.aliengreen), BitmapFactory.decodeResource(context.getResources(), R.drawable.aliengreen_walk1), BitmapFactory.decodeResource(context.getResources(), R.drawable.aliengreen_walk2), BitmapFactory.decodeResource(context.getResources(), R.drawable.aliengreen_walk1_left), BitmapFactory.decodeResource(context.getResources(), R.drawable.aliengreen_walk2_left) };

        int x = Metrics.SCREEN_WIDTH/2 - frames[0].getWidth()/2;
        int y = Metrics.SCREEN_HEIGHT/2 - frames[0].getHeight()/2;

        this.rectPlayer = new Rect(x, y, x + frames[0].getWidth(), y + frames[0].getHeight());

        this.speed = 8;

        lastUpdateTime = lastFrameTime = System.currentTimeMillis();

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

        if (isMoving) {
            if ((System.currentTimeMillis() - lastFrameTime) > 250) {

                framePosition++;

                if ( currentDirection == Direction.RIGHT) {
                    if (framePosition > 2) // limites do movimento para a direita
                        framePosition = 1; // inicio do movimento para a direita
                }
                else {
                    if ( framePosition < 3 )
                        framePosition = 3;

                    if (framePosition > 4) // limites do movimento para a esquerda
                        framePosition = 3; // inicio do movimento para a esquerda
                }

                lastFrameTime = System.currentTimeMillis();
            }
        } else {
            framePosition = 0;
        }

        lastUpdateTime = System.currentTimeMillis();
    }

    public void draw(Canvas canvas) {

//        Paint p =  new Paint();
//        p.setTextSize(25);
//        p.setColor(Color.WHITE);
//        canvas.drawText("F_Time: " + lastFrameTime, 10, 30, p);
//        canvas.drawText("C_Time: " + System.currentTimeMillis(), 10, 60, p);
//        canvas.drawText("L_Time: " + lastUpdateTime, 10, 90, p);

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
