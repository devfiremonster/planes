package com.firemonster.planes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.firemonster.planes.drawing.GameBoard;
import com.firemonster.planes.drawing.Utils;
import com.firemonster.planes.drawing.sprites.Plane;
import com.firemonster.planes.drawing.sprites.Building;
import com.firemonster.planes.drawing.sprites.Cloud;
import com.firemonster.planes.drawing.sprites.Block;
import com.firemonster.planes.drawing.sprites.HotAirBalloon;
import com.firemonster.planes.drawing.sprites.FuelIndicator;
import com.firemonster.planes.drawing.sprites.FuelBalloon;
import com.firemonster.planes.drawing.sprites.Explosion;
import com.firemonster.planes.drawing.sprites.Score;
import com.firemonster.planes.drawing.sprites.Sprite;
import com.firemonster.planes.drawing.sprites.Tower;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import android.graphics.Point;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Handler frame = new Handler();
    //private Point sprite2Velocity;
    private boolean isAscending = false;
    private static final int FRAME_RATE = 20; //20 milliseconds delay per update
        // 1 second = 1000 milliseconds, 1000 / 20 = 50 frames per second
    private boolean mPaused = false;
    private GameBoard gameBoard;

    private boolean first = true;
    private String sInit;

    //Method for getting touch state--requires android 2.1 or greater
    @Override
    synchronized public boolean onTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                isAscending = true;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                isAscending = false;
                break;
        }
        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler h = new Handler();
        ((Button)findViewById(R.id.the_button)).setOnClickListener(this);

        // Create an anonymous implementation of OnClickListener
        Button pause_button = ((Button)findViewById(R.id.pause_button));
        pause_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPaused = !mPaused;
            }
        });

        //initGfx();

        //h.postDelayed(new Runnable() {
        //    @Override
        //    public void run() {
        //        initGfx();
        //    }
        //}, 1000);

        // https://stackoverflow.com/questions/3591784/views-getwidth-and-getheight-returns-0
        View canvas = findViewById(R.id.the_canvas);
        canvas.post(new Runnable() {
            @Override
            public void run() {
                initGfx();
            }
        });
    }

    /*
    private Point getRandomVelocity() {
        Random r = new Random();
        int min = 1;
        int max = 5;
        int x = r.nextInt(max-min+1)+min;
        int y = r.nextInt(max-min+1)+min;
        return new Point (x,y);
    }
    */

    private Point getRandomPoint() {
        Random r = new Random();
        int minX = 0;
        int maxX = gameBoard.getWidth(); // - gameBoard.building.width;
        int minY = 0;
        int maxY = gameBoard.getHeight(); // - gameBoard.building.height;
        int x = r.nextInt(maxX-minX+1)+minX;
        int y = r.nextInt(maxY-minY+1)+minY;
        return new Point (x,y);
    }

    synchronized public void initGfx() {
        View canvas = findViewById(R.id.the_canvas);

        gameBoard = ((GameBoard)findViewById(R.id.the_canvas));

        gameBoard.sprites.clear();

        // Always there sprites
        Plane plane = gameBoard.plane;
        plane.active = true;
        gameBoard.sprites.add(plane);
        gameBoard.sprites.add(gameBoard.fuelIndicator);
        gameBoard.sprites.add(gameBoard.score);

        // Dynamic sprites
        Building building = new Building(-1, -1, getResources());
        initBuildingPosition(building);
        Cloud cloud = new Cloud(-1, -1, getResources());
        cloud.x = getRandomPoint().x;
        cloud.y = getRandomPoint().y;
        Block block = new Block(-1, -1);
        initBlockPosition(block);
        FuelIndicator fuelIndicator = gameBoard.fuelIndicator;
        Score score = gameBoard.score;
        Tower tower = new Tower(-1, -1, 5);
        initTowerPosition(tower);

        // Dynamic sprites
        HotAirBalloon hotAirBalloon = new HotAirBalloon(-1, -1, getResources());
        initHotAirBalloonPosition(hotAirBalloon);
        FuelBalloon fuelBalloon = new FuelBalloon(gameBoard.getWidth(), getRandomPoint().y, getResources(), getApplicationContext());

        // Initialize sprite positions
        plane.x = 100;
        plane.y = getRandomPoint().y;
        plane.setApplicationContext(getApplicationContext());

        score.x = 10;
        score.y = 100;

        //gameBoard.sprites.add(building);
        gameBoard.sprites.add(cloud);
        gameBoard.sprites.add(block);
        gameBoard.sprites.add(hotAirBalloon);
        gameBoard.sprites.add(fuelBalloon);
        gameBoard.sprites.add(tower);

        // Initialize properties
        plane.refuel();
        fuelIndicator.max = Plane.FUEL_MAX;
        fuelIndicator.setFuel(plane.fuel);

        //planeVelocity = getRandomVelocity();
        //sprite2Velocity = new Point(1,1);
        ((Button)findViewById(R.id.the_button)).setEnabled(true);
        ((Button)findViewById(R.id.pause_button)).setEnabled(true);
        frame.removeCallbacks(frameUpdate);
        gameBoard.invalidate();
        frame.postDelayed(frameUpdate, FRAME_RATE);
    }

    private void initHotAirBalloonPosition(HotAirBalloon hotAirBalloon) {
        Random r = new Random();
        int minX = 0;
        int maxX = gameBoard.getWidth() + gameBoard.getHeight();
        int ranNum = r.nextInt(maxX-minX+1)+minX;
        if (ranNum <= gameBoard.getWidth()) {
            hotAirBalloon.x = ranNum;
            hotAirBalloon.y = gameBoard.getHeight();
        }
        else {
            hotAirBalloon.x = gameBoard.getWidth();
            hotAirBalloon.y = ranNum - gameBoard.getWidth();//gameBoard.getHeight() - ranNum;
        }
    }

    private void initFuelBalloonPosition(FuelBalloon fuelBalloon) {
        fuelBalloon.x = gameBoard.getWidth();
        fuelBalloon.y = getRandomPoint().y;
    }

    private void initBuildingPosition(Building building) {
        building.x = gameBoard.getWidth();
        building.y = gameBoard.getHeight()-building.height;
    }

    private void initCloudPosition(Cloud cloud) {
        cloud.x = gameBoard.getWidth();
        cloud.y = getRandomPoint().y;
    }

    private void initBlockPosition(Block block) {
        block.x = gameBoard.getWidth();
        block.y = gameBoard.getHeight() - block.height;
    }

    private void initTowerPosition(Tower tower) {
        tower.x = gameBoard.getWidth();
        tower.y = gameBoard.getHeight() - tower.height;
    }

    @Override
    synchronized public void onClick(View v) {
        initGfx();
    }

    private void explode(Sprite s) {
        s.active = false;

        Explosion explosion = new Explosion(-1, -1, getResources());
        explosion.x = s.x + (gameBoard.plane.width / 2) - (explosion.width / 2);
        explosion.y = s.y + (gameBoard.plane.height / 2) - (explosion.height / 2);
        gameBoard.sprites.add(explosion);
    }

    private Runnable frameUpdate = new Runnable() {

        @Override
        synchronized public void run() {
            if (!mPaused) {
                frame.removeCallbacks(frameUpdate);

                ArrayList<Sprite> newSprites = new ArrayList<Sprite>();

                // Check for collisions
                boolean fatalCollision = false;
                List<Sprite> ss = Utils.collisions(gameBoard.plane, gameBoard.sprites);
                for (Sprite s : ss) {
                    if (s.isFatal()) {
                        //explode(s);
                        s.active = false;
                        fatalCollision = true;
                    }

                    if (s instanceof FuelBalloon) {
                        gameBoard.plane.refuel();
                        FuelBalloon fuelBalloon = (FuelBalloon)s;
                        fuelBalloon.collect();
                        fuelBalloon.active = false;
                        gameBoard.fuelIndicator.setFuel(gameBoard.plane.fuel);

                        //FuelBalloon newFuelBalloon = new FuelBalloon(-1, -1, getResources());
                        //initFuelBalloonPosition(newFuelBalloon);
                        FuelBalloon newFuelBalloon = new FuelBalloon(gameBoard.getWidth(), getRandomPoint().y, getResources(), getApplicationContext());
                        //newFuelBalloon.setApplicationContext(getApplicationContext());
                        newSprites.add(newFuelBalloon);
                    }
                }
                if (fatalCollision) {
                    explode(gameBoard.plane);
                    gameBoard.plane.explode();
                }

                Plane plane = gameBoard.plane;
                //if (plane.active) {
                //if (plane != null) {
                FuelIndicator fuelIndicator = gameBoard.fuelIndicator;

                // Update speeds
                if ( (isAscending) && (plane.hasFuel())){
                    plane.speedY = -Plane.MAX_SPEED_Y;
                    plane.useFuel();
                    fuelIndicator.setFuel(plane.fuel);
                } else {
                    plane.speedY = Plane.MAX_SPEED_Y;
                }

                // Update score
                gameBoard.score.score++;

                // Update locations
                plane.y += plane.speedY;
                for (Sprite sprite : gameBoard.sprites) {
                //for (Iterator<Sprite> iter = gameBoard.sprites.iterator(); iter.hasNext(); ) {
                    //Sprite sprite = iter.next();
                    if (sprite instanceof Building) {
                        Building building = (Building)sprite;
                        building.x -= plane.speedX;
                    }
                    else if (sprite instanceof Cloud) {
                        Cloud cloud = ((Cloud)sprite);
                        cloud.x -= plane.speedX;
                    }
                    else if (sprite instanceof Block) {
                        Block block = (Block)sprite;
                        block.x -= plane.speedX;
                        block.y = gameBoard.getHeight() - block.height;
                    }
                    else if (sprite instanceof HotAirBalloon) {
                        HotAirBalloon hotAirBalloon = (HotAirBalloon)sprite;
                        hotAirBalloon.x -= plane.speedX;
                        hotAirBalloon.y -= hotAirBalloon.speedY;
                    }
                    else if (sprite instanceof FuelBalloon) {
                        FuelBalloon fuelBalloon = (FuelBalloon) sprite;
                        fuelBalloon.x -= plane.speedX;
                    }
                    else if (sprite instanceof Tower) {
                        Tower tower = (Tower)sprite;
                        tower.x -= plane.speedX;
                        tower.y = gameBoard.getHeight() - tower.height;
                    }
                }

                // Check boundaries
                if (plane.y < 0) {
                    plane.y = 0;
                }
                if ((plane.y + plane.height) > gameBoard.getHeight()) {
                    plane.y = gameBoard.getHeight() - plane.height;
                }

                for (Sprite sprite : gameBoard.sprites) {
                //for (Iterator<Sprite> iter = gameBoard.sprites.iterator(); iter.hasNext(); ) {
                    //Sprite sprite = iter.next();
                    if (sprite instanceof Building) {
                        Building building = (Building)sprite;
                        if (building.x < -building.width) {
                            building.active = false;

                            Building newBuilding = new Building(-1, -1, getResources());
                            initBuildingPosition(newBuilding);
                            newSprites.add(newBuilding);
                        }
                    } else if (sprite instanceof Cloud) {
                        Cloud cloud = ((Cloud)sprite);
                        if (cloud.x < -cloud.width) {
                            cloud.active = false;

                            Cloud newCloud = new Cloud(-1, -1, getResources());
                            initCloudPosition(newCloud);
                            newSprites.add(newCloud);
                        }
                    } else if (sprite instanceof Block) {
                        Block block = (Block)sprite;
                        if (block.x < -block.width) {
                            block.active = false;

                            Block newBlock = new Block(-1, -1);
                            initBlockPosition(newBlock);
                            newSprites.add(newBlock);
                        }
                    } else if (sprite instanceof HotAirBalloon) {
                        HotAirBalloon hotAirBalloon = (HotAirBalloon)sprite;
                        if (hotAirBalloon.x < -hotAirBalloon.width || hotAirBalloon.y < -hotAirBalloon.height) {
                            //initHotAirBalloonPosition(hotAirBalloon);
                            hotAirBalloon.active = false;

                            HotAirBalloon newHotAirBalloon = new HotAirBalloon(-1, -1, getResources());
                            initHotAirBalloonPosition(newHotAirBalloon);
                            newSprites.add(newHotAirBalloon);
                        }
                    } else if (sprite instanceof FuelBalloon) {
                        FuelBalloon fuelBalloon = (FuelBalloon)sprite;
                        if (fuelBalloon.x < -fuelBalloon.width || fuelBalloon.y < -fuelBalloon.height) {
                            fuelBalloon.active = false;

                            //FuelBalloon newFuelBalloon = new FuelBalloon(-1, -1, getResources());
                            //initFuelBalloonPosition(newFuelBalloon);
                            //newFuelBalloon.setApplicationContext(getApplicationContext());
                            FuelBalloon newFuelBalloon = new FuelBalloon(gameBoard.getWidth(), getRandomPoint().y, getResources(), getApplicationContext());
                            newSprites.add(newFuelBalloon);
                        }
                    }
                }
                //}

                // Remove inactive sprites
                for (Iterator<Sprite> iter = gameBoard.sprites.iterator(); iter.hasNext(); ) {
                    Sprite sprite = iter.next();
                    if (!sprite.active) {
                        iter.remove();
                    }
                }

                // Add new sprites
                gameBoard.sprites.addAll(newSprites);
            }

            gameBoard.invalidate();
            frame.postDelayed(frameUpdate, FRAME_RATE);
        }
    };
}