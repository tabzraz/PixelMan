package uk.co.tabish.game.pixelman.platform;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.pixelman.PixelManGame;
import uk.co.tabish.game.thing.AnimationComponent;

public class DeadlyPlatform extends Platform {

    private Texture fire = PixelManGame.manager().get("platforms/FireBack.png", Texture.class);
    private Texture[] topFire = new Texture[5];
    private Texture border = PixelManGame.manager().get("rect.png");

    AnimationComponent lava;

    public DeadlyPlatform(float x, float y, float width, float height) {
        super(x, y, width, height);

        //Load fire
        for(int i=1;i<=5;i++) {
            topFire[i-1] = PixelManGame.manager().get("platforms/FireTop" + i + ".png", Texture.class);
        }

        lava = new AnimationComponent(topFire,6);
    }

    public void draw(SpriteBatch batch) {

        //Clear
        batch.setColor(Color.WHITE);
        //Back
        batch.draw(fire,x,y,bounds().width,bounds().height);

        //Top
        Texture top = lava.getTexture();
        for(int i=-1;i<=bounds().width-top.getWidth()+1;i+=top.getWidth()) {
            batch.draw(top,x+i,y-2);
        }

        //Border
        batch.setColor(Color.BLACK);
        batch.draw(border,x,y+bounds().height-1,bounds().width,1);
        batch.draw(border,x,y+2,1,bounds().height-2);
        batch.draw(border,x+bounds().width-1,y+2,1,bounds().height-2);


    }

    /*
        This platform is only deadly if the player jumps 'into' it(from above).
     */
}
