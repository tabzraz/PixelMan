package uk.co.tabish.game.pixelman.enemy;

import java.util.List;

/**
 * Created by Tabz on 07/12/2014.
 */
public class Cannon extends GroundEnemy {

    private static final int width = 10;
    private static final int height = 10;

    private static final float walkSpeed = 20f;
    private static final int activeDist = 400;
    private static final int bufferDist = 10;

    //Projectile variables
    private List<Enemy> projectiles;

    private int counter = 0;

    private int firingCount = 100;

    private float projectileSpeed = 100;
    //---

    public Cannon(int x, int y, List<Enemy> projectiles) {
        super(x,y,width,height,activeDist,walkSpeed,bufferDist);
        this.projectiles = projectiles;
    }

    public void update() {
        super.update();

        if(this.active()) {
            counter++;
            if(counter>firingCount) {
                counter=0;
                EnemyProjectile p = new EnemyProjectile((int)(this.x+width/2f-EnemyProjectile.width/2f),(int)(this.y+height/2f-EnemyProjectile.height/2f));
                if(info().player.x - this.x >= 0) {
                    p.xSpeed = projectileSpeed;
                } else {
                    p.xSpeed = -projectileSpeed;
                }
                projectiles.add(p);
            }
        }
    }
}
