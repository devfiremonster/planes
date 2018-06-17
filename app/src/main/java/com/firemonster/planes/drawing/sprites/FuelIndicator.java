package com.firemonster.planes.drawing.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class FuelIndicator extends PolygonSprite {

    public int max;
    public int fuel;

    private static final String FUEL_TEXT = "FUEL";
    private static final int GAP = 10;
    private static final int FUEL_INDICATOR_HEIGHT = 20;

    public FuelIndicator(int x, int y) {
        super(x, y);
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }

    public boolean isFatal() {
        return false;
    }

    public void draw(Canvas canvas, Paint paint) {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        Rect bounds = new Rect();
        paint.getTextBounds(FUEL_TEXT, 0, FUEL_TEXT.length(), bounds);


        // Fuel text
        paint.setTextSize(40);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        //canvas.drawText(FUEL_TEXT, 10, 40, paint);
        //canvas.drawText(bounds.width() + "," + bounds.height(), 0, 40, paint);
        canvas.drawText(FUEL_TEXT, GAP, bounds.height()+GAP, paint);
        //canvas.drawText(":" + fuel, GAP, bounds.height()+GAP, paint);

        // Fuel indicator
        paint.setColor(Color.RED);
        paint.setStrokeWidth(5);
        //y = 15;
        //canvas.drawRect(120, y, canvas.getWidth() - 5, y+20, paint);
        int height = bounds.height();
        int width = bounds.width();
        //canvas.drawRect(width+10, height, canvas.getWidth() - 5, height+20, paint);
        int fuelIndicatorLeft = bounds.width()+ GAP * 2;
        int fuelIndicatorTop = GAP + bounds.height() / 2 - FUEL_INDICATOR_HEIGHT  / 2;
        //int fuelIndicatorRight = canvas.getWidth() - GAP;
        int fuelIndicatorWidth = (canvas.getWidth() - GAP - fuelIndicatorLeft) * fuel / max;
        //if (fuel == 0) {
        //    fuel
        //}   else {
        //
        //}
        //int fuelIndicatorRight = (int)(canvas.getWidth() * max / fuel - GAP);
        int fuelIndicatorRight = fuelIndicatorLeft + fuelIndicatorWidth;
        int fuelIndiciatorBottom = fuelIndicatorTop+FUEL_INDICATOR_HEIGHT;
        //canvas.drawRect(bounds.width()+GAP * 2, fuelIndicatorTop, canvas.getWidth() - GAP, fuelIndicatorTop+FUEL_INDICATOR_HEIGHT, paint);
        canvas.drawRect(fuelIndicatorLeft, fuelIndicatorTop, fuelIndicatorRight, fuelIndiciatorBottom, paint);
    }
}
