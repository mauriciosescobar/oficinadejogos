package br.edu.ifsul.charqueadas.oficinadejogos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Player {

    private Bitmap idle;
    private Paint paint;
    private Context context;

    public Player(Context context) {
        this.paint = new Paint();
        this.context = context;
        this.idle = BitmapFactory.decodeResource(context.getResources(), R.drawable.aliengreen);
    }

    public void update() {

    }

    public void draw(Canvas canvas) {

        canvas.drawBitmap(idle, 100, 100, paint);

    }
}
