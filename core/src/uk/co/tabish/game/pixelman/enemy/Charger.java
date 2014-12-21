package uk.co.tabish.game.pixelman.enemy;

/**
 * Created by Tabz on 07/12/2014.
 */
public class Charger  extends GroundEnemy {

    private static final int width = 16;
    private static final int height = 20;

    private static final float walkSpeed = 150f;

    private static final int bufferDist = 200;

    private static final int activationDistance = 250;

    public Charger(int x, int y) {
        super(x, y, width, height, activationDistance, walkSpeed, bufferDist);
    }
}
