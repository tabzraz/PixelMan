package uk.co.tabish.game.pixelman.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.pixelman.platform.Platform;
import uk.co.tabish.game.thing.Thing;

public class Player extends Thing {

    //Player constants
    private static final int playerWidth = 10;
    private static final int playerHeight = 16;

    //Player constants used by components
    public static final float playerMaxXSpeed = 150f;
    public static final float playerMaxYSpeed = 350f;

    public static final float playerGroundHorizAccel = 1500f;
    public static final float playerAirHorizAccel = 350f;

    public static final float playerGroundFriction = 0.2f;
    public static final float playerAirFriction = 0.07f;

    public static final float playerGroundClampSpeed = 20f;
    public static final float playerAirClampSpeed = 0f;

    public static final float playerJumpSpeed = -100f;

    public static final float playerGravity = 200f;
    public static final float playerJumpingGravity = playerGravity * 0.6f;

    public static final float playerWallSlidingFriction = 0.9f;

    //Player variables shared by components
    public boolean playerInAir = false;

    public boolean playerJumping = false;

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
    }

    @Override
    public void collided(Thing thing, float xVector, float yVector) {

        //Move into a collision handling component
        if(thing instanceof Platform) {
            //Vertical collision
            if(Math.abs(yVector)<Math.abs(xVector)) {
                if(yVector < 0f) {
                    //Platform is underneath player
                    playerInAir = false;
                }
                ySpeed=0f;

            }

            //Horizontal collision
            if(Math.abs(xVector)<Math.abs(yVector)) {
                xSpeed=0f;
                if(ySpeed > 0f) {
                    //Player is sliding down a wall
                    ySpeed *= playerWallSlidingFriction;
                }
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(Color.GREEN);
        super.draw(batch);
    }

}
