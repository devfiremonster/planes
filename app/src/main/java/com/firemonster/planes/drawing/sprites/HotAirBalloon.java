package com.firemonster.planes.drawing.sprites;

import android.content.res.Resources;

import com.firemonster.planes.R;

public class HotAirBalloon extends ImageSprite {

    public static int MAX_SPEED_Y = 5;

    public int speedX = 0;
    public int speedY = 3;


    public HotAirBalloon(int x, int y, Resources resources) {
        super(x, y, resources, R.drawable.hotairballoontrans);
    }

    public boolean isFatal() {
        return true;
    }

    public String toString() {
        return "HotAirBalloon: " + super.toString();
    }
}