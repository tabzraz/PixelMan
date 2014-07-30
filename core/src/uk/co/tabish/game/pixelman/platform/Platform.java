package uk.co.tabish.game.pixelman.platform;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.thing.Thing;

public class Platform extends Thing {

    public Platform(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(Color.RED);
        super.draw(batch);
    }
}
