package com.firemonster.planes.drawing.sprites;

import android.graphics.Bitmap;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class ImageSprite extends Sprite {
    public Bitmap bitmap;

    public ImageSprite(int x, int y, Resources resources, int id) {
        super(x, y);
        Bitmap bitmap = BitmapFactory.decodeResource(resources, id);
        setBitmap(bitmap);
    }

    public void draw(Canvas canvas, Paint paint) {
        canvas.drawBitmap(bitmap, x, y, null);
    }

    private void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
    }
}