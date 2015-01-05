package uk.co.tabish.game.pixelman;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.Screen;

public class LoadingScreen implements Screen {

    private AssetManager manager;
    private boolean finished = false;
    private float progress = 0f;
    private Texture rectangle;

    public LoadingScreen(AssetManager manager) {
        this.manager = manager;
    }

    @Override
    public void init() {
        //Assets used for drawing the loading screen
        manager.load("rect.png", Texture.class);

        //Block until those assets are loaded
        manager.finishLoading();

        //Initialise loading screen assets
        rectangle = manager.get("rect.png", Texture.class);


        //Load all assets here

        //---Textures---

        //Platforms
        //Plain
        manager.load("platforms/PlainBack.png", Texture.class);
        manager.load("platforms/PlainTop.png", Texture.class);
        //Ice
        manager.load("platforms/IceBack.png", Texture.class);
        manager.load("platforms/IceTop.png", Texture.class);
        //Moving
        manager.load("platforms/MovingBack.png", Texture.class);
        manager.load("platforms/MovingTop.png", Texture.class);
        //Oneway
        manager.load("platforms/OnewayBack.png", Texture.class);
        manager.load("platforms/OnewayTop.png", Texture.class);
        //Fire
        manager.load("platforms/FireBack.png", Texture.class);
        for(int i=1;i<=5;i++) {
            manager.load("platforms/FireTop"+i+".png", Texture.class);
        }

        //---

        //--Sound--

        //--Level--

    }

    @Override
    public void update() {
        if(manager.update()) {
            finished = true;
        }
        progress = manager.getProgress();
    }

    @Override
    public void draw(SpriteBatch batch) {

        //Draw loading bar
        batch.setColor(Color.WHITE);
        batch.draw(rectangle, PixelManGame.width / 5f, PixelManGame.height / 3f, PixelManGame.width * 3f / 5f, PixelManGame.height / 3f);
        batch.setColor(Color.RED);
        batch.draw(rectangle,PixelManGame.width/5f+10f,PixelManGame.height/3f+10f,(PixelManGame.width*3f/5f-10f)*progress-10f,PixelManGame.height/3f-20f);
    }

    @Override
    public int changeScreenTo() {
        if(finished) {
            //Temp, go to game screen
            return PixelManGame.GAME_SCREEN;
        } else {
            return -1;
        }
    }

    @Override
    public void dispose() {

    }
}
