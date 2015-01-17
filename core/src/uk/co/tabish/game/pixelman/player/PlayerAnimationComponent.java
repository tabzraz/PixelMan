package uk.co.tabish.game.pixelman.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.pixelman.PixelManGame;
import uk.co.tabish.game.thing.AnimationComponent;

public class PlayerAnimationComponent {

    private boolean facingLeft = false;

    private AnimationComponent still;
    private AnimationComponent walk;
    private Texture slide;
    private Texture jumpStart;
    private Texture jump;
    private Texture fall;
    private Texture wallHang;

    private boolean wasOnGround = true;
    private boolean couldDoubleJump = true;
    private boolean jumpStarting = false;
    private int jumpStartCounter = 0;

    private boolean invincible = false;
    private int flashInvincible = 0;

    public PlayerAnimationComponent() {

        //Still
        Texture[] stillT = new Texture[2];
        for(int i=1;i<=2;i++) {
            stillT[i-1] = PixelManGame.manager().get("player/playerStill" + i + ".png", Texture.class);
        }
        still = new AnimationComponent(stillT,20);

        //Walking
        Texture[] walkT = new Texture[6];
        for(int i=1;i<=6;i++) {
            walkT[i-1] = PixelManGame.manager().get("player/playerWalk" + i + ".png", Texture.class);
        }
        walk = new AnimationComponent(walkT,7);

        //Sliding
        slide = PixelManGame.manager().get("player/playerSlide.png", Texture.class);

        //Jumping
        jumpStart = PixelManGame.manager().get("player/playerJumpStart.png", Texture.class);
        jump =PixelManGame.manager().get("player/playerJump.png", Texture.class);

        //Falling
        fall = PixelManGame.manager().get("player/playerFall.png", Texture.class);

        //Wallhang
        wallHang = PixelManGame.manager().get("player/playerWallhang.png", Texture.class);

    }

    public void setInvincibleStatus(boolean invin) {
        invincible = invin;
        if(invincible) {
            flashInvincible++;
            if (flashInvincible > 20) {
                flashInvincible = 0;
            }
        }
    }

    public void drawPlayer(SpriteBatch batch, Player player) {

        if(player.xSpeed > 0) {
            facingLeft = false;
        } else if(player.xSpeed < 0) {
            facingLeft = true;
        }

        Texture toDraw;

        //Pick texture
        if(player.playerWallHanging) {
            toDraw = wallHang;
        } else {
            if(player.playerInAir && player.ySpeed>0f) {
                //Falling
                toDraw = fall;
            } else if(player.playerInAir && player.ySpeed<0f) {
                //Jumping
                if(jumpStarting || wasOnGround || (couldDoubleJump && !player.canDoubleJump)) {
                    //Has just started a jump or double jump
                    toDraw = jumpStart;
                    if(!jumpStarting) {
                        jumpStartCounter = 0;
                    }
                    jumpStarting = true;
                    jumpStartCounter++;
                    if(jumpStartCounter > 5) {
                        jumpStarting = false;
                    }
                } else {
                    toDraw = jump;
                }
            } else {
                if(Math.abs(player.xSpeed) > 0f) {
                    if(player.xSpeed * player.xAccel < 0f) {
                        //Player accelerating in opposite direction to movement => sliding
                        toDraw = slide;
                    } else {
                        toDraw = walk.getTexture();
                    }
                } else {
                    toDraw = still.getTexture();
                }
            }
        }

        //---

        wasOnGround = !player.playerInAir;
        //System.out.println(wasOnGround);
        couldDoubleJump = player.canDoubleJump;

        if(invincible && flashInvincible<11) {
            batch.setColor(1f,0f,0f,0.5f);
        } else {
            batch.setColor(Color.WHITE);
        }

        batch.draw(toDraw,player.x-2,player.y-4,toDraw.getWidth(),toDraw.getHeight(),0,0,toDraw.getWidth(),toDraw.getHeight(),!facingLeft,false);
    }
}
