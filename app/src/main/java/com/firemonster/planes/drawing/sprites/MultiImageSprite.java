package com.firemonster.planes.drawing.sprites;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class MultiImageSprite extends Sprite {
    public Bitmap[] bitmaps;
    public int index = 0;
    private long millisPerFrame;
    private long lastUpdateTime = -1;
    private long now;

    public MultiImageSprite(int x, int y, Resources resources, int[] ids, int frameRate) {
        super(x, y);
        bitmaps = new Bitmap[ids.length];
        for (int i=0; i<ids.length; i++) {
            bitmaps[i] = BitmapFactory.decodeResource(resources, ids[i]);
        }
        millisPerFrame = 1000 / frameRate;
        this.width = this.bitmaps[0].getWidth();
        this.height = this.bitmaps[0].getHeight();
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(bitmaps[index], x, y, null);

        now = System.currentTimeMillis();
        if ( (lastUpdateTime == -1) || ((now - lastUpdateTime) > millisPerFrame) ) {
            lastUpdateTime = now;
            index++;
        }
        if (index >= bitmaps.length) {
            //index = 0;
            active = false;
        }
    }
}