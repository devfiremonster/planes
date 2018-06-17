package com.firemonster.planes.drawing.sprites;

import android.content.res.Resources;
import com.firemonster.planes.R;

public class Cloud extends ImageSprite {
    public Cloud(int x, int y, Resources resources) {
        super(x, y, resources, R.drawable.cloud1);
    }

    public boolean isFatal() {
        return false;
    }

    public String toString() {
        return "Cloud: " + super.toString();
    }
}