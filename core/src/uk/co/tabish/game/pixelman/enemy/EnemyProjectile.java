package uk.co.tabish.game.pixelman.enemy;

import com.badlogic.gdx.graphics.Texture;
import uk.co.tabish.game.pixelman.PixelManGame;
import uk.co.tabish.game.thing.Thing;

/**
 * Created by Tabz on 07/12/2014.
 */
public class EnemyProjectile extends Enemy {

    public static final int width = 4;
    public static final int height = 4;

    public EnemyProjectile(int x, int y) {
        super(x, y, width, height);
        this.setTexture(PixelManGame.manager().get("enemies/bullet.png", Texture.class));
    }

    public void collided(Thing thing, float xVector, float yVector) {
        this.dead = true;
    }

}
