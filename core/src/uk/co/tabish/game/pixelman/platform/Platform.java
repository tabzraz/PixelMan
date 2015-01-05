package uk.co.tabish.game.pixelman.platform;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.pixelman.PixelManGame;
import uk.co.tabish.game.thing.Thing;

public class Platform extends Thing {

    protected Texture back= PixelManGame.manager().get("platforms/PlainBack.png", Texture.class);
    protected Texture top = PixelManGame.manager().get("platforms/PlainTop.png", Texture.class);
    private Texture border = PixelManGame.manager().get("rect.png");

    public Platform(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(SpriteBatch batch) {

        //TODO: Move this into a component

        //Clear
        batch.setColor(Color.WHITE);
        //Back
        batch.draw(back,x,y,bounds().width,bounds().height);

        //Top
        for(int i=0;i<=bounds().width-top.getWidth();i+=top.getWidth()) {
            batch.draw(top,x+i,y);
        }

        //Border
        batch.setColor(Color.BLACK);
        batch.draw(border,x,y,bounds().width,1);
        batch.draw(border,x,y+bounds().height-1,bounds().width,1);
        batch.draw(border,x,y,1,bounds().height);
        batch.draw(border,x+bounds().width-1,y,1,bounds().height);


    }
}
