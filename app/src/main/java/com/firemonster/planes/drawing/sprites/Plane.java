package com.firemonster.planes.drawing.sprites;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;

import com.firemonster.planes.R;

public class Plane extends ImageSprite {
    public static int MAX_SPEED_Y = 5;
    public static final int FUEL_MAX = 500;

    private MediaPlayer mp;
    private Context context;

    public int speedX = 5;
    public int speedY = 0;

    public int fuel = FUEL_MAX;

    public Plane(int x, int y, Resources resources) {
        super(x, y, resources, R.drawable.planetrans);
    }

    public void setApplicationContext(Context context) {
        mp = MediaPlayer.create(context, R.raw.explosion);
        this.context = context;
    }

    public void explode() {
        mp.start();
    }

    public void refuel() {
        fuel = FUEL_MAX;
    }

    public boolean hasFuel() {
        return fuel > 0;
    }

    public void useFuel() {
        fuel--;
        if (fuel < 0) {
            fuel = 0;
        }
    }

    public boolean isFatal() {
        return false;
    }

    public String toString() {
        return "Plane: " + super.toString();
    }
}