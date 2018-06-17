package com.firemonster.planes.drawing;

import android.graphics.Color;
import android.graphics.Rect;

import com.firemonster.planes.drawing.sprites.ImageSprite;
import com.firemonster.planes.drawing.sprites.PolygonSprite;
import com.firemonster.planes.drawing.sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    /*
    public static boolean collision(Sprite sprite1, Sprite sprite2) {
        //if ( (sprite1 != null) && (sprite2 != null) && sprite1.active && sprite2.active && (sprite1 != sprite2) && (sprite1 instanceof ImageSprite) && (sprite2 instanceof ImageSprite) ) {
        if ( sprite1.active && sprite2.active && (sprite1 != sprite2) && (sprite1 instanceof ImageSprite) && (sprite2 instanceof ImageSprite) ) {
            if (sprite1.x < 0 && sprite2.x < 0 && sprite1.y < 0 && sprite2.y < 0) return false;
            Rect r1 = new Rect(sprite1.x, sprite1.y, sprite1.x + sprite1.width, sprite1.y + sprite1.height);
            Rect r2 = new Rect(sprite2.x, sprite2.y, sprite2.x + sprite2.width, sprite2.y + sprite2.height);
            Rect r3 = new Rect(r1);
            if (r1.intersect(r2)) {
                for (int i = r1.left; i < r1.right; i++) {
                    for (int j = r1.top; j < r1.bottom; j++) {
                        if (((ImageSprite)sprite1).bitmap.getPixel(i - r3.left, j - r3.top) != Color.TRANSPARENT) {
                            if (((ImageSprite)sprite2).bitmap.getPixel(i - r2.left, j - r2.top) != Color.TRANSPARENT) {
                                //lastCollision = new Point(sprite2.x + i - r2.left, sprite2.y + j - r2.top);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    */

    public static boolean collision(Sprite sprite1, Sprite sprite2) {
        //if ( (sprite1 != null) && (sprite2 != null) && sprite1.active && sprite2.active && (sprite1 != sprite2) && (sprite1 instanceof ImageSprite) && (sprite2 instanceof ImageSprite) ) {
        if ( sprite1.active && sprite2.active && (sprite1 != sprite2) ) {
            if (sprite1.x < 0 && sprite2.x < 0 && sprite1.y < 0 && sprite2.y < 0) return false;
            Rect r1 = new Rect(sprite1.x, sprite1.y, sprite1.x + sprite1.width, sprite1.y + sprite1.height);
            Rect r2 = new Rect(sprite2.x, sprite2.y, sprite2.x + sprite2.width, sprite2.y + sprite2.height);
            Rect r3 = new Rect(r1);
            if (r1.intersect(r2)) {
                if ( (sprite1 instanceof PolygonSprite) || (sprite2 instanceof PolygonSprite)) {
                    return true; // todo: handle bitmaps - convert PolygonSprite into ImageSprite?
                }
                for (int i = r1.left; i < r1.right; i++) {
                    for (int j = r1.top; j < r1.bottom; j++) {
                        if (((ImageSprite)sprite1).bitmap.getPixel(i - r3.left, j - r3.top) != Color.TRANSPARENT) {
                            if (((ImageSprite)sprite2).bitmap.getPixel(i - r2.left, j - r2.top) != Color.TRANSPARENT) {
                                //lastCollision = new Point(sprite2.x + i - r2.left, sprite2.y + j - r2.top);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public static List<Sprite> collisions(Sprite sprite, List<Sprite> sprites) {
        List<Sprite> ss = new ArrayList<Sprite>();
        for (Sprite s : sprites) {
            if (collision(sprite, s)) {
                ss.add(s);
            }
        }
        return ss;
    }

    /*
    public static boolean fatalCollisions(Sprite sprite, List<Sprite> sprites) {
        for (Sprite s : sprites) {
            if (collision(sprite, s)) {
                return true;
            }
        }
        return false;
    }
    */

    /*
    public static List<Sprite> fatalCollisions(Sprite sprite, List<Sprite> sprites) {
        List<Sprite> ss = new ArrayList<Sprite>();
        for (Sprite s : sprites) {
            if ( (s.isFatal() && (collision(sprite, s)) ) {
                ss.add(s);
            }
        }
        return ss;
    }
    */

    public static boolean fatalCollisions(List<Sprite> sprites) {
        for (Sprite sprite : sprites) {
            if (sprite.isFatal()) {
                return true;
            }
        }
        return false;
    }
}