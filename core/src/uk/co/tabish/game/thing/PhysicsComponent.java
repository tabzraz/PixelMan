package uk.co.tabish.game.thing;

import com.badlogic.gdx.math.MathUtils;

public class PhysicsComponent {

    public static final float timeDelta = 1/60f;

    public void moveThing(Thing thing) {

        //Basic Euler integration assuming a time delta of 1/60s
        //Speeds are in pixels/second

        thing.xSpeed+=thing.xAccel*timeDelta;
        thing.ySpeed+=thing.yAccel*timeDelta;

        thing.x+=thing.xSpeed*timeDelta;
        thing.y+=thing.ySpeed*timeDelta;

    }

}
