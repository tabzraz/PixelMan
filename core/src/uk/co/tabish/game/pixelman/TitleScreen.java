package uk.co.tabish.game.pixelman;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.Screen;

public class TitleScreen implements Screen {

    private OrthographicCamera camera;
    private BitmapFont font;

    private boolean clicked = false;

    private Texture title;
    private Texture click;

    private int flashCount = 0;

    @Override
    public void init() {
        camera = new OrthographicCamera();
        camera.setToOrtho(true, 120,65);
        //TODO: adjust camera measurements slightlty to make it fit best on the screen

        font = new BitmapFont();

        title = PixelManGame.manager().get("title/title.png");
        click = PixelManGame.manager().get("title/start.png");

    }

    @Override
    public void update() {
        if(PixelManGame.input().up) {
            clicked = true;
        }
        flashCount++;
        if(flashCount>60){
            flashCount=0;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {

        batch.setProjectionMatrix(camera.combined);

        batch.setColor(99f/255f,155f/255f,255f/255f,1f);
        batch.draw(PixelManGame.manager().get("rect.png", Texture.class), camera.position.x-camera.viewportWidth/2f,camera.position.y-camera.viewportHeight/2f,camera.viewportWidth,camera.viewportHeight);

        batch.setColor(Color.WHITE);
        batch.draw(title, 10,10);
        if(flashCount<=30) {
            batch.draw(click,22.5f,40);
        }


    }

    @Override
    public int changeScreenTo() {
        return clicked ? PixelManGame.GAME_SCREEN : -1;
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
