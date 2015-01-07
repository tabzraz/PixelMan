package uk.co.tabish.game.pixelman.enemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import uk.co.tabish.game.thing.AnimationComponent;

public class EnemyAnimationComponent {

    //Enemies have 2 states: walking or still
    //They can also face left or right

    private AnimationComponent walking;
    private AnimationComponent still;

    boolean facingLeft = true;

    public void setWalking(Texture[] texture, int framesPerImage) {
        walking = new AnimationComponent(texture, framesPerImage);
    }

    public void setStill(Texture[] texture, int framesPerImage) {
        still = new AnimationComponent(texture, framesPerImage);
    }

    public void drawTexture(SpriteBatch batch, Enemy enemy, int xOffset, int yOffset) {

        if(enemy.xSpeed > 0) {
            facingLeft = false;
        } else if(enemy.xSpeed < 0) {
            facingLeft = true;
        }

        Texture toDraw;

        if(enemy.xSpeed != 0) {
            toDraw = walking.getTexture();
        } else {
            toDraw = still.getTexture();
        }

        batch.setColor(Color.WHITE);
        batch.draw(toDraw,enemy.x+xOffset,enemy.y+yOffset,toDraw.getWidth(),toDraw.getHeight(),0,0,toDraw.getWidth(),toDraw.getHeight(),!facingLeft,false);
    }
}
