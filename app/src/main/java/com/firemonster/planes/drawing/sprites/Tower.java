package com.firemonster.planes.drawing.sprites;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Tower extends PolygonSprite {

    public Block[] blocks;

    public Tower(int x, int y, int blockCount) {
        super(x, y);
        blocks = new Block[blockCount];
        for (int i=0; i<blockCount; i++) {
            blocks[i] = new Block(x, y - i * Block.HEIGHT);
        }
        height = Block.HEIGHT * blockCount;
    }

    public void draw(Canvas canvas, Paint paint) {
        for (int i=0; i<blocks.length; i++) {
            blocks[i].x = this.x; // todo remove this, maybe have set/get in base class
            blocks[i].draw(canvas, paint);
        }
    }

    public boolean isFatal() {
        return true;
    }

    public String toString() {
        return "Tower: " + super.toString();
    }
}