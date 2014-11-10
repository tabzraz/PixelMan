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
    public static final int playerWidth = 10;
    public static final int playerHeight = 16;

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
    private PlayerCollisionComponent collisionComponent;

    public Player(float x, float y) {
        super(x, y, playerWidth, playerHeight);

        //Initialise all components
        physicsComponent = new PlayerPhysicsComponent();

        inputComponent = new PlayerInputComponent();

        collisionComponent = new PlayerCollisionComponent(physicsComponent);
    }

    @Override
    public void update() {
        inputComponent.handleInput(this);

        physicsComponent.moveThing(this);
    }

    @Override
    public void collided(Thing thing, float xVector, float yVector) {

        collisionComponent.collided(thing,xVector,yVector,this);

    }

    public void playerDied() {
        playerDead = true;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(Color.GREEN);
        super.draw(batch);
    }

}
