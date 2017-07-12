package br.edu.ifsul.charqueadas.oficinadejogos;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private final GamePanel gamePanel;
    private final SurfaceHolder surfaceHolder;

    private boolean running;
    private long MAX_FPS = 30;

    public MainThread(SurfaceHolder surfaceHolder, GamePanel panel ) {
        super();
        this.gamePanel = panel;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {

        running = true;

        long startTime;
        long fpsInterval = 1000 / MAX_FPS;
        long difTime;

        while ( running ) {
            startTime = System.currentTimeMillis();

            Canvas canvas = null;

            try {
                canvas = surfaceHolder.lockCanvas();

                synchronized (canvas) {
                    // atualizacao do estado do jogo - logica
                    this.gamePanel.update();
                    // atualização do parte visual - desenho
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
