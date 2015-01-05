package uk.co.tabish.game.pixelman.platform;

import com.badlogic.gdx.graphics.Texture;
import uk.co.tabish.game.pixelman.PixelManGame;

public class OneWayPlatform extends Platform {

    public OneWayPlatform(float x, float y, float width, float height) {
        super(x, y, width, height);
        this.solid = false;
        back= PixelManGame.manager().get("platforms/OnewayBack.png", Texture.class);
        top = PixelManGame.manager().get("platforms/OnewayTop.png", Texture.class);
    }

    /* The player can jump up through this platform */
}
