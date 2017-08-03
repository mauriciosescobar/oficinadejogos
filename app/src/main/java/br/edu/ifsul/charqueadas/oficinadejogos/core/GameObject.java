package br.edu.ifsul.charqueadas.oficinadejogos.core;

import android.graphics.Canvas;
import android.view.MotionEvent;

public interface GameObject {

    public abstract void update();

    public abstract void draw(Canvas canvas);

    public abstract void onTouchEvent(MotionEvent event);

}
