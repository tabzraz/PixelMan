package uk.co.tabish.game.pixelman.enemy;

import uk.co.tabish.game.thing.Thing;

/**
 * Created by Tabz on 07/12/2014.
 */
public class GroundEnemy extends Enemy {

    private static final int width = 10;
    private static final int height = 16;

    private static final float walkSpeed = 30f;

    private static final float bufferDist = 20f;

    GroundEnemyCollisionComponent collisionComponent;

    public GroundEnemy(int x, int y) {
        super(x,y,width,height);

        //Set gravity
        this.yAccel = Enemy.gravity;

        //Collision Component
        collisionComponent = new GroundEnemyCollisionComponent();

        this.solid = false;
    }

    public void update() {

        //Ground AI

        if(info().player.x - this.x < -bufferDist) {
            this.xSpeed = -walkSpeed;
        } else if(info().player.x -this.x > bufferDist) {
            this.xSpeed = walkSpeed;
        }

        //---

        super.update();
    }

    public void collided(Thing thing, float xVector, float yVector) {

        collisionComponent.collided(thing,xVector,yVector,this);

    }

}
