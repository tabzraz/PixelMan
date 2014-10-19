package uk.co.tabish.game.pixelman.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.pixelman.platform.DeadlyPlatform;
import uk.co.tabish.game.pixelman.platform.IcePlatform;
import uk.co.tabish.game.pixelman.platform.MovingPlatform;
import uk.co.tabish.game.pixelman.platform.Platform;
import uk.co.tabish.game.thing.Thing;

public class Player extends Thing {

    //Player constants
    private static final int playerWidth = 10;
    private static final int playerHeight = 16;

    //Player constants used by components
    public static final float playerMaxXSpeed = 125f;
    public static final float playerMaxYSpeed = 200f;

    public static final float playerGroundHorizAccelNormal = 1500f;
    public static final float playerAirHorizAccel = 100f;

    public static final float playerGroundFriction = 0.2f;
    public static final float playerAirFriction = 0.0f;

    public static final float playerGroundClampSpeed = 20f;
    public static final float playerAirClampSpeed = 0f;

    public static final float playerJumpSpeed = -100f;

    public static final float playerGravity = 280f;
    public static final float playerJumpingGravity = playerGravity * 0.6f;

    public static final float playerWallSlidingFriction = 0.9f;

    public static final float playerWallBounce = 10f;

    public static final float playerDeathDistance = 4f;

    //Player variables shared by components
    public boolean playerInAir = false;

    public boolean playerJumping = false;

    public boolean canDoubleJump = true;

    public boolean playerDead = false;

    public float playerGroundHorizAccel = playerGroundHorizAccelNormal;

    //Components
    private PlayerPhysicsComponent physicsComponent;
    private PlayerInputComponent inputComponent;

    public Player(float x, float y) {
        super(x, y, playerWidth, playerHeight);

        //Initialise all components
        physicsComponent = new PlayerPhysicsComponent();

        inputComponent = new PlayerInputComponent();
    }

    @Override
    public void update() {
        inputComponent.handleInput(this);

        physicsComponent.moveThing(this);

        //Move into collision component
        playerInAir = true;

        System.out.println(xSpeed);
    }

    @Override
    public void collided(Thing thing, float xVector, float yVector) {

        //Todo: Move into a collision handling component
        if(thing instanceof Platform) {
            //Vertical collision
            if(Math.abs(yVector)<Math.abs(xVector)) {
                if(yVector < 0f) {
                    //Platform is underneath player
                    playerInAir = false;
                    if(thing.ySpeed<=0f) {
                        ySpeed=Math.min(ySpeed,thing.ySpeed);
                    } else {
                        ySpeed=thing.ySpeed;
                    }
                    x+=thing.xSpeed*PlayerPhysicsComponent.timeDelta;

                    //Check for deadly platform
                    if(thing instanceof DeadlyPlatform) {
                        playerDied();
                    }

                    //Check for ice platform
                    if(thing instanceof IcePlatform) {
                        physicsComponent.setXFriction(IcePlatform.groundFriction);
                        playerGroundHorizAccel = IcePlatform.horizAccel;
                        physicsComponent.setXClampSpeed(IcePlatform.clampSpeed);
                    }

                } else {
                    //Platform above player, 'bounce' player of
                    ySpeed = thing.ySpeed+playerWallBounce;
                }

            }

            //Horizontal collision
            if(Math.abs(xVector)<Math.abs(yVector)) {
                if(xVector*xSpeed<0f) {
                    //Player is moving into the platform
                    /*
                        This is to stop the scenario when the player is moving horizontally across a platform,
                        and is then pushed outwards horiz in that direction on the last 'step' and being stopped.
                     */
                    xSpeed=0f;
                }

                if(ySpeed > 0f) {
                    //Player is sliding down a wall

                    if(!(thing instanceof IcePlatform)) {
                        ySpeed *= playerWallSlidingFriction;
                    }

                }
            }

            //Death check
            /*
                If the player got squished between platforms and was moved a "long way" they are now dead
            */
            if(Math.min(Math.abs(xVector),Math.abs(yVector))>playerDeathDistance) {
                System.out.println("Player Died: "+xVector+ " ,"+yVector);
                playerDied();
            }
        }
    }

    private void playerDied() {
        playerDead = true;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(Color.GREEN);
        super.draw(batch);
    }

}
