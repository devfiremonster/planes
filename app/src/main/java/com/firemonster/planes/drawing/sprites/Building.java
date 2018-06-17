package com.firemonster.planes.drawing.sprites;

import android.content.res.Resources;
import com.firemonster.planes.R;

public class Building extends ImageSprite {
    public Building(int x, int y, Resources resources) {
        super(x, y, resources, R.drawable.buildingtrans);
    }

    public boolean isFatal() {
        return true;
    }

    public String toString() {
        return "Building: " + super.toString();
    }
}