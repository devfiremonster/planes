package com.firemonster.planes.drawing.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Block extends PolygonSprite {

    public static int WIDTH = 50;
    public static int HEIGHT = 50;

    public Block(int x, int y) {
        super(x, y, WIDTH, HEIGHT);
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        canvas.drawRect(x, y, x+width, y+height, paint);
    }

    public boolean isFatal() {
        return true;
    }

    public String toString() {
        return "Block: " + super.toString();
    }
}