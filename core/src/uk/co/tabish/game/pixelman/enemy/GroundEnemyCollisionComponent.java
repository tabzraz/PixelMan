package uk.co.tabish.game.pixelman.enemy;

import uk.co.tabish.game.pixelman.platform.OneWayPlatform;
import uk.co.tabish.game.thing.PhysicsComponent;
import uk.co.tabish.game.thing.Thing;

/**
 * Created by Tabz on 07/12/2014.
 */

public class GroundEnemyCollisionComponent {

    public void collided(Thing thing, float xVector, float yVector, Enemy enemy) {

        //Vertical collision
        if (Math.abs(yVector) < Math.abs(xVector)) {

            if (yVector < 0f) {

                //Since oneway platforms aren't solid
                if(thing instanceof OneWayPlatform) {
                    enemy.y+=yVector;
                }

                //Extra logic here is for moving platforms, can't really split them up
                if (thing.ySpeed <= 0f) {
                    enemy.ySpeed = Math.min(enemy.ySpeed, thing.ySpeed);
                } else {
                    enemy.ySpeed = thing.ySpeed;
                }
                enemy.x += thing.xSpeed * PhysicsComponent.timeDelta;


            }

        }

        //Horizontal collision
        if (Math.abs(xVector) < Math.abs(yVector)) {

            if (xVector * enemy.xSpeed < 0f) {
                //enemy is moving into the platform
            /*
                The check (xVector*xSpeed<0f) is to stop the scenario when the enemy is moving horizontally across a platform,
                and is then pushed outwards horiz in that direction on the last 'step' and stopped.
             */
                enemy.xSpeed = 0f;
            }

        }

    }

}


