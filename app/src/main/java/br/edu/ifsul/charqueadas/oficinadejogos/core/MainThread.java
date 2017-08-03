package br.edu.ifsul.charqueadas.oficinadejogos.core;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private final GamePanel gamePanel;
    private final SurfaceHolder surfaceHolder;

    private boolean running;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel panel) {
        super();
        this.gamePanel = panel;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {

        running = true;

        long startTime;
        long fpsInterval = 1000 / gamePanel.getMAX_FPS();
        long difTime;

        while ( running ) {
            startTime = System.currentTimeMillis();

            Canvas canvas = null;

            try {
                canvas = surfaceHolder.lockCanvas();

                synchronized (canvas) {
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }

            } catch ( Exception e ) {
                e.printStackTrace();
            } finally {
                if (canvas!=null)
                surfaceHolder.unlockCanvasAndPost(canvas);
            }

            difTime = System.currentTimeMillis() - startTime;

            long waitTime = fpsInterval - difTime;

            if ( waitTime > 0 ) {
                try {
                    this.sleep( waitTime );
                } catch ( Exception e ){
                    e.printStackTrace();
                }
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
