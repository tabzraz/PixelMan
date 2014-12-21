package uk.co.tabish.game.pixelman.enemy;

/**
 * Created by Tabz on 07/12/2014.
 */
public class Skeleton extends GroundEnemy{

    private static final int width = 10;
    private static final int height = 16;

    private static final float walkSpeed = 60f;

    private static final int bufferDist = 10;

    private static final int activationDistance = 100;

    public Skeleton(int x, int y) {
        super(x, y, width, height, activationDistance, walkSpeed, bufferDist);
    }
}
