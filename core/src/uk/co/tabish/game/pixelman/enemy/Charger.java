package uk.co.tabish.game.pixelman.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.pixelman.PixelManGame;
import uk.co.tabish.game.thing.OnetimeAnimationComponent;

/**
 * Created by Tabz on 07/12/2014.
 */
public class Charger  extends GroundEnemy {

    private static final int width = 16;
    private static final int height = 10;

    private static final float walkSpeed = 80f;

    private static final int bufferDist = 80;

    private static final int activationDistance = 150;
    
    private EnemyAnimationComponent bull = new EnemyAnimationComponent();

    public Charger(int x, int y) {
        super(x, y, width, height, activationDistance, walkSpeed, bufferDist);
        
        //Walking animation
        Texture[] bullWalk = new Texture[6];

        for(int i=1;i<=6;i++) {
            bullWalk[i-1] = PixelManGame.manager().get("enemies/bull" + i + ".png", Texture.class);
        }

        bull.setWalking(bullWalk,3);

        //Still animation
        Texture[] bullStill = new Texture[5];

        for(int i=1;i<=5;i++) {
            bullStill[i-1] = PixelManGame.manager().get("enemies/bullstill"+i+".png", Texture.class);
        }

        bull.setStill(bullStill,15);

        //Death animation
        Texture[] death = new Texture[8];
        for(int i=1;i<=8;i++) {
            death[i-1] = PixelManGame.manager().get("enemies/bulldie"+i+".png", Texture.class);
        }
        deathAnim = new OnetimeAnimationComponent(death,8);
    }

    public void draw(SpriteBatch batch) {
        bull.drawTexture(batch,this,-2,-4);
    }
}
