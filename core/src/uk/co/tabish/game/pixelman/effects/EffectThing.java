package uk.co.tabish.game.pixelman.effects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.thing.OnetimeAnimationComponent;

public class EffectThing {

    private float x;
    private float y;
    private OnetimeAnimationComponent self;

    public EffectThing(float x, float y, OnetimeAnimationComponent thing) {
        this.x = x;
        this.y = y;
        self = thing;
    }

    public boolean isFinished() {
        return self.isFinished();
    }

    public void draw(SpriteBatch batch) {
        batch.setColor(Color.WHITE);
        batch.draw(self.getTexture(),x,y);
    }
}
