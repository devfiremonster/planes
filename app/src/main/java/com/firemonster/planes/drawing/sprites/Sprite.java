package com.firemonster.planes.drawing.sprites;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Sprite {
    public int x;
    public int y;
    public int width;
    public int height;
    public boolean active = true;

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void draw(Canvas canvas, Paint paint);

    public abstract boolean isFatal();

    public String toString() {
        return "x:" + x + ", y:" + y + ", width: " + width + ", height: " + height;
    }
}