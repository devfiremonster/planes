package com.firemonster.planes.drawing.sprites;

import android.content.Context;
import android.content.res.Resources;
import android.media.MediaPlayer;

import com.firemonster.planes.R;

public class Explosion extends MultiImageSprite {

    private static int[] ids = {
            R.drawable.explosion1trans,
            R.drawable.explosion2trans,
            R.drawable.explosion3trans,
            R.drawable.explosion4trans,
            R.drawable.explosion5trans,
            R.drawable.explosion6trans,
            R.drawable.explosion7trans,
            R.drawable.explosion8trans,
            R.drawable.explosion9trans,
            R.drawable.explosion10trans,
            R.drawable.explosion11trans,
            R.drawable.explosion12trans,
            R.drawable.explosion13trans,
            R.drawable.explosion14trans,
            R.drawable.explosion15trans,
            R.drawable.explosion16trans,
            R.drawable.explosion17trans};

    private MediaPlayer mp;
    private Context context;

    public Explosion(int x, int y, Resources resources) {
        super(x, y, resources, ids, 8);
    }

    public void setApplicationContext(Context context) {
        mp = MediaPlayer.create(context, R.raw.explosion);
        this.context = context;
    }

    public boolean isFatal() {
        return false;
    }

    public String toString() {
        return "Explosion: " + super.toString();
    }
}