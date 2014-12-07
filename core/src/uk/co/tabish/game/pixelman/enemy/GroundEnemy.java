package uk.co.tabish.game.pixelman.enemy;

import uk.co.tabish.game.thing.Thing;

/**
 * Created by Tabz on 07/12/2014.
 */
public class GroundEnemy extends Enemy {

    //TODO: Make this a template for ground based enemies
    private static final float walkSpeed = 30f;

    private static final float bufferDist = 20f;

    public static final int actingDistance = 200;

    GroundEnemyCollisionComponent collisionComponent;

    public GroundEnemy(int x, int y, int width, int height) {
        super(x,y,width,height);

        //Set gravity
        this.yAccel = Enemy.gravity;

        //Collision Component
        collisionComponent = new GroundEnemyCollisionComponent();

        this.solid = false;
    }

    public void update() {

        //Ground AI

        if(Math.abs(info().player.x - this.x) < actingDistance) {
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

}
