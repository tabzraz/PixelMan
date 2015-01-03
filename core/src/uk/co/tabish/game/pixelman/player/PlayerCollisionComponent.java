package uk.co.tabish.game.pixelman.player;

import uk.co.tabish.game.pixelman.enemy.Enemy;
import uk.co.tabish.game.pixelman.enemy.GroundEnemy;
import uk.co.tabish.game.pixelman.platform.*;
import uk.co.tabish.game.thing.Thing;

public class PlayerCollisionComponent {

    PlayerPhysicsComponent physicsComponent;

    public PlayerCollisionComponent(PlayerPhysicsComponent physics) {
        physicsComponent = physics;
    }

    /*
        Everything the player collides with is sent here.
        Things that require action on collision have their own methods.
        TODO: Move collision code into other objects, so that enemies, etc can decide how the player collides.
     */
    public void collided(Thing thing, float xVector, float yVector, Player player) {

        if(thing instanceof DeadlyPlatform) {
            this.collidedWithDeadlyPlatform((DeadlyPlatform) thing, xVector, yVector, player);
        } else if(thing instanceof IcePlatform) {
            this.collidedWithIcePlatform((IcePlatform) thing, xVector, yVector, player);
        } else if(thing instanceof OneWayPlatform) {
            this.collidedWithOneWayPlatform((OneWayPlatform)thing,xVector,yVector,player);
        } else if(thing instanceof Platform) {
            this.collidedWithPlatform((Platform) thing, xVector, yVector, player);
        } else if(thing instanceof GroundEnemy) {
            this.collidedWithGroundEnemy((GroundEnemy) thing, xVector, yVector, player);
        } else if(thing instanceof Enemy) {
            this.collidedWithEnemy((Enemy) thing, xVector,yVector,player);
        }

    }

    public void collidedWithGroundEnemy(GroundEnemy enemy, float xVector, float yVector, Player player) {
        if(Math.abs(yVector)<Math.abs(xVector) && yVector < 0f) {
            //Vertical collision and player was above enemy when collision occured
            enemy.dead = true;

        } else {
            this.collidedWithEnemy(enemy,xVector,yVector,player);
        }
    }


    public void collidedWithEnemy(Enemy enemy, float xVector, float yVector, Player player) {

        player.playerHit();

        enemy.collided(player, -xVector, -yVector);

    }

    public void collidedWithOneWayPlatform(OneWayPlatform thing, float xVector, float yVector, Player player) {

        //Can only collide with this platform when above it
        if(Math.abs(yVector)<Math.abs(xVector)) {
            //Vertical
            if(yVector < 0f) {

                if(player.y+Player.playerHeight -player.ySpeed*PlayerPhysicsComponent.timeDelta<= thing.y) {
                    //Player must have been above the platform before falling onto it

                    //Move the player up
                    player.y += yVector;

                    player.playerInAir = false;

                    player.ySpeed=0f;
                }

            }
        }
    }

    public void collidedWithIcePlatform(IcePlatform thing, float xVector, float yVector, Player player) {

        if(Math.abs(yVector)<Math.abs(xVector)) {
            //Vertical collision

            if(yVector<0f) {
                //Player is on top of the platform
                physicsComponent.setXFriction(IcePlatform.groundFriction);
                player.playerGroundHorizAccel = IcePlatform.horizAccel;
                physicsComponent.setXClampSpeed(IcePlatform.clampSpeed);
            }
        } else {
            //Horizontal collsion
            if(player.ySpeed>0f) {
                //Player sliding down ice platform
                //To counteract the friction applied when colliding with a platform
                player.ySpeed /= Player.playerWallSlidingFriction;

            }

        }

        this.collidedWithPlatform(thing, xVector, yVector, player);

    }

    public void collidedWithDeadlyPlatform(DeadlyPlatform thing, float xVector, float yVector, Player player) {
        if(Math.abs(yVector)<Math.abs(xVector)) {
            if (yVector < 0f) {
                player.playerDied();
            }
        }

        this.collidedWithPlatform(thing, xVector, yVector, player);
    }

    //Helper methods
    private void collidedWithPlatform(Platform thing, float xVector, float yVector, Player player) {

        //Vertical collision
        if(Math.abs(yVector)<Math.abs(xVector)) {

            if(yVector < 0f) {

                //Platform is underneath player
                player.playerInAir = false;

                //Extra logic here is for moving platforms, can't really split them up
                if(thing.ySpeed<=0f) {
                    player.ySpeed=Math.min(player.ySpeed,thing.ySpeed);
                } else {
                    player.ySpeed=thing.ySpeed;
                }
                player.x+=thing.xSpeed*PlayerPhysicsComponent.timeDelta;


            } else {
                //Platform above player, 'bounce' player of
                player.ySpeed = thing.ySpeed+Player.playerWallBounce;
            }

        }

        //Horizontal collision
        if(Math.abs(xVector)<Math.abs(yVector)) {

            if(xVector*player.xSpeed<0f) {
                //Player is moving into the platform
                /*
                    The check (xVector*xSpeed<0f) is to stop the scenario when the player is moving horizontally across a platform,
                    and is then pushed outwards horiz in that direction on the last 'step' and stopped.
                 */
                player.xSpeed=0f;
            }

            if(player.ySpeed > 0f) {
                //Player is sliding down a wall
                player.ySpeed *= Player.playerWallSlidingFriction;
            }

        }

        //Death check
        /*
            If the player got squished between platforms and was moved a "long way" they are now dead
        */
        if(Math.min(Math.abs(xVector),Math.abs(yVector))>Player.playerDeathDistance) {
            System.out.println("Player Died: "+xVector+ " ,"+yVector);
            player.playerDied();
        }

    }

}
