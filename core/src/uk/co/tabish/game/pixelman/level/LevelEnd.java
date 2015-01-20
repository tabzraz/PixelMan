package uk.co.tabish.game.pixelman.level;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.pixelman.PixelManGame;
import uk.co.tabish.game.thing.AnimationComponent;
import uk.co.tabish.game.thing.Thing;

public class LevelEnd extends Thing {

    AnimationComponent crystal;

    public LevelEnd(int x, int y) {
        super(x,y,10,20);

        Texture[] crystalT = new Texture[6];

        for(int i=1;i<=6;i++) {
            crystalT[i-1] = PixelManGame.manager().get("level/Crystal" + i + ".png", Texture.class);
        }

        crystal = new AnimationComponent(crystalT, 10);

    }

    public void draw(SpriteBatch batch) {
        //Clear
        batch.setColor(Color.WHITE);
        //Back
        batch.draw(crystal.getTexture(),x,y,bounds().width,bounds().height);
    }
}
