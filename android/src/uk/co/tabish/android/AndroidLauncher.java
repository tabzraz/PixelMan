package uk.co.tabish.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import uk.co.tabish.game.pixelman.PixelManGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useImmersiveMode=true;
		config.useGLSurfaceView20API18=true;
        PixelManGame game = new PixelManGame();
        game.setAndroidApp();
		initialize(game, config);
	}
}
