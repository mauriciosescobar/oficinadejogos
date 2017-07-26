package br.edu.ifsul.charqueadas.oficinadejogos;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class GameMap {

    private Bitmap tileSet;
    private Bitmap grass, tree1, tree2, tree3, tree4, tree5, tree6;

    private int tileSize = 16;
    private int scaledTileSize;

    int [][] background =
    {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 2, 2, 2, 2, 2, 2, 0, 0 },
            { 0, 0, 2, 0, 0, 0, 0, 2, 0, 0 },
            { 0, 0, 2, 0, 0, 0, 0, 2, 0, 0 },
            { 0, 0, 2, 0, 0, 0, 0, 2, 0, 0 },
            { 0, 0, 2, 0, 0, 0, 0, 2, 0, 0 },
            { 0, 0, 2, 0, 0, 0, 0, 2, 0, 0 },
            { 0, 0, 2, 2, 2, 2, 2, 2, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    int [][] foreground =
    {
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 4, 5, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 6, 7, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 8, 9, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
    };

    public GameMap(Context context) {

        scaledTileSize = tileSize * (int) Metrics.SCALED_DENSITY;

        tileSet = BitmapFactory.decodeResource(context.getResources(), R.drawable.tiles_packed_1);

        grass = Bitmap.createBitmap(tileSet, scaledTileSize*6, scaledTileSize*7, scaledTileSize, scaledTileSize);

        tree1 = Bitmap.createBitmap(tileSet, scaledTileSize*13, scaledTileSize*1, scaledTileSize, scaledTileSize);
        tree2 = Bitmap.createBitmap(tileSet, scaledTileSize*14, scaledTileSize*1, scaledTileSize, scaledTileSize);
        tree3 = Bitmap.createBitmap(tileSet, scaledTileSize*13, scaledTileSize*2, scaledTileSize, scaledTileSize);
        tree4 = Bitmap.createBitmap(tileSet, scaledTileSize*14, scaledTileSize*2, scaledTileSize, scaledTileSize);
        tree5 = Bitmap.createBitmap(tileSet, scaledTileSize*13, scaledTileSize*3, scaledTileSize, scaledTileSize);
        tree6 = Bitmap.createBitmap(tileSet, scaledTileSize*14, scaledTileSize*3, scaledTileSize, scaledTileSize);
    }

    public void draw(Canvas canvas) {

        for(int y = 0; y < background.length; y++){
            for(int x = 0; x < background[y].length; x++){
                // desenha o fundo
                Bitmap b = getBitmap(background[y][x]);
                if ( b != null )
                    canvas.drawBitmap(b, x * scaledTileSize, y * scaledTileSize, new Paint());

                // desenha a parte da frente
                b = getBitmap(foreground[y][x]);
                if ( b != null )
                    canvas.drawBitmap(b, x * scaledTileSize, y * scaledTileSize, new Paint());
            }
        }
    }

    private Bitmap getBitmap(int code) {
        switch (code) {
            case 2: return grass;
            case 4: return tree1;
            case 5: return tree2;
            case 6: return tree3;
            case 7: return tree4;
            case 8: return tree5;
            case 9: return tree6;
        }
        return null;
    }
}
