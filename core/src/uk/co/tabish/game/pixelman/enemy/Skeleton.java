package uk.co.tabish.game.pixelman.enemy;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.pixelman.PixelManGame;
import uk.co.tabish.game.thing.AnimationComponent;
import uk.co.tabish.game.thing.OnetimeAnimationComponent;

/**
 * Created by Tabz on 07/12/2014.
 */
public class Skeleton extends GroundEnemy{

    private static final int width = 8;
    private static final int height = 12;

    private static final float walkSpeed = 15f;

    private static final int bufferDist = 10;

    private static final int activationDistance = 100;

    private EnemyAnimationComponent skeleton = new EnemyAnimationComponent();

    public Skeleton(int x, int y) {
        super(x, y, width, height, activationDistance, walkSpeed, bufferDist);

        //Walking animation
        Texture[] skeletonWalk = new Texture[7];

        for(int i=1;i<=4;i++) {
            skeletonWalk[i-1] = PixelManGame.manager().get("enemies/skeleton" + i + ".png", Texture.class);
            if(i!=1) {
                skeletonWalk[7-(i-1)] = PixelManGame.manager().get("enemies/skeleton" + i + ".png", Texture.class);
            }
        }

        skeleton.setWalking(skeletonWalk,6);

        //Still animation
        Texture[] skeletonStill = new Texture[2];

        for(int i=1;i<=2;i++) {
            skeletonStill[i-1] = PixelManGame.manager().get("enemies/skeletonstill"+i+".png", Texture.class);
        }

        skeleton.setStill(skeletonStill,25);

        //Death animation
        Texture[] death = new Texture[11];
        for(int i=1;i<=11;i++) {
            death[i-1] = PixelManGame.manager().get("enemies/skeletondeath"+i+".png", Texture.class);
        }
        deathAnim = new OnetimeAnimationComponent(death,8);

    }

    @Override
    public void draw(SpriteBatch batch) {
        skeleton.drawTexture(batch,this,-1,-4);
    }
}
