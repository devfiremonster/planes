package com.firemonster.planes.drawing.sprites;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class Score extends PolygonSprite {

    public int score;

    private static final String SCORE_TEXT = "SCORE";
    private static final int GAP = 10;
    private static final int FUEL_INDICATOR_HEIGHT = 20;

    public Score(int x, int y) {
        super(x, y);
    }

    /*public void setFuel(int fuel) {
        this.fuel = fuel;
    }
*/
    public boolean isFatal() {
        return false;
    }

    public void draw(Canvas canvas, Paint paint) {
        Rect bounds = new Rect();
        paint.getTextBounds(SCORE_TEXT, 0, SCORE_TEXT.length(), bounds);


        // Score text
        paint.setTextSize(40);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        //canvas.drawText(FUEL_TEXT, 10, 40, paint);
        //canvas.drawText(bounds.width() + "," + bounds.height(), 0, 40, paint);
        canvas.drawText(SCORE_TEXT, x, y, paint);
        //canvas.drawText(":" + fuel, GAP, bounds.height()+GAP, paint);


    }
}
