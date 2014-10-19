package uk.co.tabish.game.pixelman.platform;

public class DeadlyPlatform extends Platform {

    public DeadlyPlatform(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    /*
        This platform is only deadly if the player jumps 'into' it(from above).
     */
}
