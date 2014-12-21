package uk.co.tabish.game.pixelman.enemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.thing.PhysicsComponent;
import uk.co.tabish.game.thing.Thing;

/**
 * Created by Tabz on 07/12/2014.
 */
public class Enemy extends Thing {

    private PhysicsComponent moveComponent = new PhysicsComponent();

    public static final float gravity = 250f;

    private Info info;
    
    public boolean dead = false;

    public Enemy(int x, int y, int width, int height) {
        super(x,y,width,height);
    }

    @Override
    public void update() {
        moveComponent.moveThing(this);
    }

    /* Get info about the environment and the player */
    public void receiveInfo(Info info) {
        this.info = info;
    }

    protected Info info() {
        return info;
    }

    protected boolean active() {
        return true;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(Color.CYAN);
        super.draw(batch);
    }
}
