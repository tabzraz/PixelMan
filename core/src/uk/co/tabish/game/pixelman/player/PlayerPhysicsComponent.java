package uk.co.tabish.game.pixelman.player;

import uk.co.tabish.game.thing.Thing;

public class PlayerPhysicsComponent {

    public static final float timeDelta = 1/60f;

    private float maxXSpeed = Float.MAX_VALUE;
    private float maxYSpeed = Float.MAX_VALUE;

    private float xFriction = 0f;
    private float yFriction = 0f;

    private float xClampSpeed = 0f;
    private float yClampSpeed = 0f;

    public PlayerPhysicsComponent() {
        this.setMaxYSpeed(Player.playerMaxXSpeed);
        this.setMaxYSpeed(Player.playerMaxYSpeed);
    }

    public void moveThing(Player player) {

        //Change constants if in the air
        if(player.playerInAir) {
            this.setXFriction(Player.playerAirFriction);
            this.setXClampSpeed(Player.playerAirClampSpeed);
        } else {
            this.setXFriction(Player.playerGroundFriction);
            this.setXClampSpeed(Player.playerGroundClampSpeed);
            if(!player.playerJumping) {
                //Reset the ability to double jump unless you have just started jumping
                /*
                    The check is needed because on the first frame of the jump, at this point the playerInAir variable
                    has not been set to true yet, since collisions are handled after updates.
                    Without this reset the player can have double jumping allowed when they walk of a platform by
                    only jumping once before walking of the platform.
                */
                player.canDoubleJump=false;
            }
        }

        //---Player jumping---

        //Less gravity when jumping up
        if(player.playerJumping) {
            player.yAccel = Player.playerJumpingGravity;
        } else {
            player.yAccel = Player.playerGravity;
        }

        //Return gravity to normal at the top of the jump
        if(player.playerJumping && player.ySpeed>=0) {
            player.playerJumping = false;
        }

        //---

        //---'Integrate'---

        //Friction
        player.xSpeed *= (1f-xFriction);
        player.ySpeed *= (1f-yFriction);

        player.xSpeed+=player.xAccel*timeDelta;
        player.ySpeed+=player.yAccel*timeDelta;

        //Check speeds are within bounds
        if(Math.abs(player.xSpeed)>maxXSpeed) {
            player.xSpeed = maxXSpeed * Math.signum(player.xSpeed);
        }

        if(Math.abs(player.ySpeed)>maxYSpeed) {
            player.ySpeed = maxYSpeed * Math.signum(player.ySpeed);
        }

        //If the speed is less than the clamp speed, set it to 0
        if(Math.abs(player.xSpeed)<=xClampSpeed) {
            player.xSpeed=0f;
        }
        if(Math.abs(player.ySpeed)<=yClampSpeed) {
            player.ySpeed=0f;
        }

        player.x+=player.xSpeed*timeDelta;
        player.y+=player.ySpeed*timeDelta;

        //---

    }

    public void setMaxXSpeed(float max) {
        maxXSpeed = max;
    }

    public void setMaxYSpeed(float max) {
        maxYSpeed = max;
    }

    public void setXFriction(float fric) {
        xFriction = fric;
    }

    public void setYFriction(float fric) {
        yFriction = fric;
    }

    public void setXClampSpeed(float clamp) {
        xClampSpeed = clamp;
    }

    public void setYClampSpeed(float clamp) {
        yClampSpeed = clamp;
    }
}
