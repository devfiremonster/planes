package com.firemonster.planes.drawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.firemonster.planes.drawing.sprites.Sprite;
import com.firemonster.planes.drawing.sprites.FuelIndicator;
import com.firemonster.planes.drawing.sprites.Plane;
import com.firemonster.planes.drawing.sprites.Score;

import java.util.ArrayList;
import java.util.List;

public class GameBoard extends View {
    public Plane plane;
    public FuelIndicator fuelIndicator;
    public Score score;
    public List<Sprite> sprites = new ArrayList<Sprite>();

    //private boolean once = false;

    private Paint paint;
    //private boolean collisionDetected = false;
    private Point lastCollision = new Point(-1,-1);
    //private boolean isPaused = false;

    //public void setIsPaused(boolean isPaused) {
    //    this.isPaused = isPaused;
    //}

    //return the point of the last collision
    //synchronized public Point getLastCollision() {
    //    return lastCollision;
    //}

    /*
    cloud 5
    storm cloud 20
    birds 10
    raining cloud 15
    ufo 50
    hot air balloon 30
    balloon 10
    meteor 50
    missile 30
    seeking missile 40
    volcano 50
    building 20
    tornado 40
    raining flaming meteors 60

    todo:
    -release media player on finalize


    https://www.freesoundeffects.com/free-sounds/explosion-10070/
    http://www.mavengang.com/2016/05/02/gif-animation-android/
    https://freesound.org/people/GameAudio/sounds/220173/
     */

    //return the collision flag
    //synchronized public boolean wasCollisionDetected() {
    //    return collisionDetected;
   // }

    public GameBoard(Context context, AttributeSet aSet) {
        super(context, aSet);
        paint = new Paint();
        //load our bitmaps and set the bounds for the controller

        // Always there sprites
        plane = new Plane(-1,-1, getResources());
        fuelIndicator = new FuelIndicator(-1, -1);
        score = new Score(-1, -1);
    }

    @Override
    synchronized public void onDraw(Canvas canvas) {
        // Draw canvas background
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

        // Draw sprites
        for (Sprite sprite : sprites) {
            sprite.draw(canvas, paint);
        }
    }
}