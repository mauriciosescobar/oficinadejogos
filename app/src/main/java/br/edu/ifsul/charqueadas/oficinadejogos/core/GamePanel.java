package br.edu.ifsul.charqueadas.oficinadejogos.core;

import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;

    private int MAX_FPS;
    private ArrayList<GameObject> objects;

    public GamePanel(Context context) {
        super(context);

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);

        Metrics.SCREEN_WIDTH = dm.widthPixels;
        Metrics.SCREEN_HEIGHT = dm.heightPixels;
        Metrics.SCALED_DENSITY = dm.scaledDensity;

        getHolder().addCallback(this);

        this.objects = new ArrayList<>();

        this.MAX_FPS = 30;

        this.thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }

    public int getMAX_FPS() {
        return MAX_FPS;
    }

    public void addGameObject(GameObject gameObject) {
        this.objects.add(gameObject);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread = new MainThread(getHolder(), this);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        thread.setRunning(false);
    }

    public void update() {
        for (GameObject go : objects)
            go.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        for (GameObject go : objects)
            go.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        for (GameObject go : objects)
            go.onTouchEvent(event);
        return true;
    }
}
