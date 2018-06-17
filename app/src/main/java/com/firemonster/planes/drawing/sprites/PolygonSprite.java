package com.firemonster.planes.drawing.sprites;

public abstract class PolygonSprite extends Sprite {

    public PolygonSprite(int x, int y) {
        super(x, y);
    }

    public PolygonSprite(int x, int y, int width, int height) {
        super(x, y);
        this.width = width;
        this.height = height;
    }
}
