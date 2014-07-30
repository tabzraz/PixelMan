package uk.co.tabish.game.thing;

import com.badlogic.gdx.math.MathUtils;

public class MovingComponent {
    
    private static final float timeDelta = 1/60f;

    private float maxXSpeed = Float.MAX_VALUE;
    private float maxYSpeed = Float.MAX_VALUE;

    public void moveThing(Thing thing) {

        //Basic Euler integration assuming a time delta of 1/60s
        //Speeds are in pixels/second

        //'Integrate'

        thing.xSpeed+=thing.xAccel*timeDelta;
        thing.ySpeed+=thing.yAccel*timeDelta;

        //Check speeds are within bounds
        if(Math.abs(thing.xSpeed)>maxXSpeed) {
            thing.xSpeed = maxXSpeed * Math.signum(thing.xSpeed);
        }

        if(Math.abs(thing.ySpeed)>maxYSpeed) {
            thing.ySpeed = maxYSpeed * Math.signum(thing.ySpeed);
        }

        thing.x+=thing.xSpeed*timeDelta;
        thing.y+=thing.ySpeed*timeDelta;

    }

    public void setMaxXSpeed(float max) {
        maxXSpeed = max;
    }

    public void setMaxYSpeed(float max) {
        maxYSpeed = max;
    }
}
