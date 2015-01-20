package uk.co.tabish.game.pixelman.enemy;

import uk.co.tabish.game.thing.Thing;

/**
 * Created by Tabz on 07/12/2014.
 */
public class GroundEnemy extends Enemy {

    private float walkSpeed = 30f;

    private int bufferDist = 20;

    private int actingDistance = 200;

    private int sightHeight = 40;

    GroundEnemyCollisionComponent collisionComponent;

    public GroundEnemy(int x, int y, int width, int height, int activeDist, float walk, int buffer) {
        super(x,y,width,height);

        this.actingDistance = activeDist;
        this.bufferDist = buffer;
        this.walkSpeed = walk;

        //Set gravity
        this.yAccel = Enemy.gravity;

        //Collision Component
        collisionComponent = new GroundEnemyCollisionComponent();

        this.solid = true;
    }

    public void update() {

        //Ground AI

        if(this.active()) {
            if (info().player.x - this.x < -bufferDist) {
                this.xSpeed = -walkSpeed;
            } else if (info().player.x - this.x > bufferDist) {
                this.xSpeed = walkSpeed;
            }
        } else {
            //Idle
            xSpeed=0f;
        }

        //---

        super.update();
    }

    public void collided(Thing thing, float xVector, float yVector) {

        collisionComponent.collided(thing,xVector,yVector,this);

    }

    @Override
    protected boolean active() {
        return (Math.abs(info().player.x - this.x) < actingDistance) && (Math.abs(info().player.y+info().player.bounds().height/2f - this.y) < sightHeight);
    }

}
