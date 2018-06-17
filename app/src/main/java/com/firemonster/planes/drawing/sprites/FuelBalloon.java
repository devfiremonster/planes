package com.firemonster.planes.drawing.sprites;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;

import com.firemonster.planes.R;

public class FuelBalloon extends ImageSprite {

    public static int MAX_SPEED_Y = 5;

    public int speedX = 0;
    public int speedY = 3;

    private MediaPlayer mp;
    private Context context;


    public FuelBalloon(int x, int y, Resources resources, Context context) {
        super(x, y, resources, R.drawable.fuelballoon);
        mp = MediaPlayer.create(context, R.raw.powerupwav);
        this.context = context;
    }

    //public void setApplicationContext(Context context) {
    //    mp = MediaPlayer.create(context, R.raw.powerupwav);
    //    this.context = context;
    //}

    public void collect() {
        mp.start();
    }

    public boolean isFatal() {
        return false;
    }

    public String toString() {
        return "HotAirBalloon: " + super.toString();
    }
}