package uk.co.tabish.game.pixelman.platform;

public class OneWayPlatform extends Platform {

    public OneWayPlatform(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.solid = false;
    }

    /* The player can jump up through this platform */
}
