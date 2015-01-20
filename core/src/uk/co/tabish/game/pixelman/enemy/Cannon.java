package uk.co.tabish.game.pixelman.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.pixelman.PixelManGame;
import uk.co.tabish.game.thing.OnetimeAnimationComponent;

import java.util.List;

/**
 * Created by Tabz on 07/12/2014.
 */
public class Cannon extends GroundEnemy {

    private static final int width = 10;
    private static final int height = 10;

    private static final float walkSpeed = 30f;
    private static final int activeDist = 150;
    private static final int bufferDist = 10;

    //Projectile variables
    private List<Enemy> projectiles;

    private int counter = 0;

    private int firingCount = 100;

    private float projectileSpeed = 100;
    //---

    EnemyAnimationComponent cannon = new EnemyAnimationComponent();

    public Cannon(int x, int y, List<Enemy> projectiles) {
        super(x,y,width,height,activeDist,walkSpeed,bufferDist);
        this.projectiles = projectiles;

        //Animations
        //Still
        Texture[] cannonStill = new Texture[3];

        for(int i=1;i<=3;i++) {
            cannonStill[i-1] = PixelManGame.manager().get("enemies/cannonstill"+i+".png", Texture.class);
        }

        cannon.setStill(cannonStill,10);

        //Walking
        Texture[] cannonWalk = new Texture[3];

        for(int i=1;i<=3;i++) {
            cannonWalk[i-1] = PixelManGame.manager().get("enemies/cannon"+i+".png", Texture.class);
        }

        cannon.setWalking(cannonWalk, 9);

        //Death
        Texture[] cannonDeath = new Texture[6];

        for(int i=1;i<=6;i++) {
            cannonDeath[i-1] = PixelManGame.manager().get("enemies/cannondeath"+i+".png", Texture.class);
        }

        deathAnim = new OnetimeAnimationComponent(cannonDeath,10);


    }

    public void update() {
        super.update();

        //TODO: Move this into an ai component
        if(this.active()) {
            counter++;
            if(counter>firingCount) {
                counter=0;
                EnemyProjectile p = new EnemyProjectile((int)(this.x+width/2f-EnemyProjectile.width/2f+Math.signum(xSpeed)*3),(int)(this.y+height/2f-EnemyProjectile.height/2f-4));
                if(info().player.x - this.x >= 0) {
                    p.xSpeed = projectileSpeed;
                } else {
                    p.xSpeed = -projectileSpeed;
                }
                projectiles.add(p);
            }
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        cannon.drawTexture(batch,this,-2,-4);
    }
}
