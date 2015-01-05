package uk.co.tabish.game.pixelman.platform;

import com.badlogic.gdx.graphics.Texture;
import uk.co.tabish.game.pixelman.PixelManGame;

public class IcePlatform  extends Platform {

    public static final float groundFriction = 0f;
    public static final float horizAccel = 100f;
    public static final float clampSpeed = 0f;

    public IcePlatform(float x, float y, float width, float height) {
        super(x, y, width, height);
        back= PixelManGame.manager().get("platforms/IceBack.png", Texture.class);
        top = PixelManGame.manager().get("platforms/IceTop.png", Texture.class);
    }



    /*
        Slippery platform
     */
}
