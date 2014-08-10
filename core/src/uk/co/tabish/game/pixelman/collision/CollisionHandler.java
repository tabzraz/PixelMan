package uk.co.tabish.game.pixelman.collision;

import uk.co.tabish.game.thing.Thing;

public class CollisionHandler {

    public static boolean isColliding(Thing t1, Thing t2) {
        return t1.bounds().overlaps(t2.bounds());
    }

    public static void handleCollision(Thing moving, Thing stationary) {
        
        //Push the moving thing out by the axis aligned vector of least magnitude

        float xVector = 0f;
        float yVector = 0f;

        //Push left or right
        if(moving.x >= stationary.x - moving.bounds().getWidth()/2f + stationary.bounds().getWidth()/2f) {
            //Push right
            xVector = (stationary.x + stationary.bounds().getWidth()) - moving.x;
        } else {
            //Push left
            xVector = (stationary.x - moving.bounds().getWidth()) - moving.x;
        }

        //Up or down
        if(moving.y >= stationary.y - moving.bounds().getHeight()/2f + stationary.bounds().getHeight()/2f) {
            //Push down
            yVector = (stationary.y + stationary.bounds().getHeight()) - moving.y;
        } else {
            //Push up
            yVector = (stationary.y - moving.bounds().getHeight()) - moving.y;
        }

        // > in order to favour y direction
        if(Math.abs(xVector) < Math.abs(yVector)) {
            moving.x+=xVector;
        } else {
            moving.y+=yVector;
        }

        moving.collided(stationary,xVector,yVector);

    }
}
