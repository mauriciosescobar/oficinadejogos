package br.edu.ifsul.charqueadas.oficinadejogos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class GameMap {

    private Bitmap tileSet;
    private Bitmap grass;

    private int tileSize = 16;
    private int scaledTileSize;

    int [][] sceneMatrix =
    {
        { 1, 2, 3, 2, 2, 2, 2, 2, 2, 2 },
        { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
        { 1, 2, 3, 2, 2, 2, 2, 2, 2, 2 },
        { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
        { 2, 2, 2, 0, 0, 2, 2, 2, 2, 2 },
        { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
        { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
        { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
        { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
        { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },
        { 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }
    };

    public GameMap(Context context) {

        scaledTileSize = tileSize * (int) Metrics.SCALED_DENSITY;

        tileSet = BitmapFactory.decodeResource(context.getResources(), R.drawable.tiles_packed_1);

        grass = Bitmap.createBitmap(tileSet, scaledTileSize*6, scaledTileSize*7, scaledTileSize, scaledTileSize);

    }

    public void draw(Canvas canvas) {

        for(int y = 0; y < sceneMatrix.length; y++){
            for(int x = 0; x < sceneMatrix[y].length; x++){
                Bitmap b = getBitmap(sceneMatrix[y][x]);
                if ( b != null )
                    canvas.drawBitmap(b, x * scaledTileSize, y * scaledTileSize, new Paint());
            }
        }
    }

    private Bitmap getBitmap(int code) {
        switch (code) {
            case 2: return grass;
        }
        return null;
    }
}
