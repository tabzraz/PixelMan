package uk.co.tabish.game.pixelman;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.Screen;
import uk.co.tabish.game.pixelman.collision.CollisionHandler;
import uk.co.tabish.game.pixelman.platform.Platform;
import uk.co.tabish.game.pixelman.player.Player;
import uk.co.tabish.game.thing.Thing;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    private Player player;

    //Platforms
    private List<Thing> platforms = new ArrayList<Thing>();

    //Private Camera
    OrthographicCamera camera;

    @Override
    public void init() {

        camera = new OrthographicCamera();
        camera.setToOrtho(true,PixelManGame.width*1.5f, PixelManGame.height*1.5f);

        setupLevel();

    }

    private void setupLevel() {
        player = new Player(300,250);
        Platform ground = new Platform(0,300,1000,30);
        Platform p1 = new Platform(200,200,100,30);
        Platform p2 = new Platform(300,100,100,30);

        platforms.add(ground);
        platforms.add(p1);
        platforms.add(p2);
    }

    @Override
    public void update() {

        //---Move things---

        //Update player
        player.update();

        //Update platforms
        for(Thing thing : platforms) {
            thing.update();
        }

        //Handle collisions
        for(Thing thing : platforms) {
            if(CollisionHandler.isColliding(player,thing)) {
                CollisionHandler.handleCollision(player,thing);
            }
        }

        //Update Camera
        camera.position.set(player.x+player.bounds().width/2f,player.y+player.bounds().height/2f,0f);

        camera.update();

    }

    @Override
    public void draw(SpriteBatch batch) {

        //Use internal camera
        batch.setProjectionMatrix(camera.combined);

        //Draw platforms
        for(Thing thing : platforms) {
            thing.draw(batch);
        }

        //Draw player
        player.draw(batch);
    }

    @Override
    public int changeScreenTo() {
        return -1;
    }

    @Override
    public void dispose() {

    }

}
