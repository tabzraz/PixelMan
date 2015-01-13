package uk.co.tabish.game.pixelman.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.thing.Thing;

public class Player extends Thing {

    //Player constants
    public static final int playerWidth = 10;
    public static final int playerHeight = 16;

    //Player constants used by components
    public static final float playerMaxXSpeed = 125f;
    public static final float playerMaxYSpeed = 200f;

    public static final float playerGroundHorizAccelNormal = 500f;
    public static final float playerAirHorizAccel = 100f;

    public static final float playerGroundFriction = 0.05f;
    public static final float playerAirFriction = 0.0f;

    public static final float playerGroundClampSpeed = 5f;
    public static final float playerAirClampSpeed = 0f;

    public static final float playerJumpSpeed = -80f;

    public static final float playerGravity = 280f;
    public static final float playerJumpingGravity = playerGravity * 0.6f;

    public static final float playerWallSlidingFriction = 0.9f;

    public static final float playerWallBounce = 10f;

    public static final float playerDeathDistance = 4f;

    public static final int playerInvincibleCount = 40;

    //Player variables shared by components
    public boolean playerInAir = false;

    public boolean playerJumping = false;

    public boolean canDoubleJump = true;

    public boolean playerDead = false;

    public float playerGroundHorizAccel = playerGroundHorizAccelNormal;

    public boolean playerWallHanging = false;

    //Private player values
    private int playerLife = 3;

    private int invincibleCounter = 0;

    private boolean invincible = false;

    //Components
    private PlayerPhysicsComponent physicsComponent;
    private PlayerInputComponent inputComponent;
    private PlayerCollisionComponent collisionComponent;
    private PlayerAnimationComponent animationComponent;

    public Player(float x, float y) {
        super(x, y, playerWidth, playerHeight);

        //Initialise all components
        physicsComponent = new PlayerPhysicsComponent();

        inputComponent = new PlayerInputComponent();

        collisionComponent = new PlayerCollisionComponent(physicsComponent);

        animationComponent = new PlayerAnimationComponent();
    }

    @Override
    public void update() {
        inputComponent.handleInput(this);

        physicsComponent.moveThing(this);

        if(this.invincible) {
            this.invincibleCounter++;
            if(this.invincibleCounter > Player.playerInvincibleCount) {
                invincible = false;
                invincibleCounter=0;
            }
        }
    }

    @Override
    public void collided(Thing thing, float xVector, float yVector) {

        collisionComponent.collided(thing,xVector,yVector,this);

    }

    public void playerDied() {
        playerDead = true;
    }

    public void playerHit() {
        if (!invincible) {
            playerLife -= 1;
            if (playerLife < 1) {
                playerDied();
            }
            this.invincible = true;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        animationComponent.drawPlayer(batch,this);
    }

}
