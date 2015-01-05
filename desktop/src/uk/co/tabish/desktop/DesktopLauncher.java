package uk.co.tabish.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import uk.co.tabish.game.pixelman.PixelManGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 450;
        config.resizable = false;
        config.vSyncEnabled = true;
		new LwjglApplication(new PixelManGame(), config);
	}
}
