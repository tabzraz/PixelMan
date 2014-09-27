package uk.co.tabish.game.pixelman;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.Screen;
import uk.co.tabish.game.pixelman.collision.CollisionHandler;
import uk.co.tabish.game.pixelman.level.Level;
import uk.co.tabish.game.pixelman.platform.Platform;
import uk.co.tabish.game.pixelman.player.Player;
import uk.co.tabish.game.thing.Thing;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    private Level level = new Level();

    private Player player;

    //Platforms
    private List<Thing> platforms = new ArrayList<Thing>();

    //Private Camera
    OrthographicCamera camera;

    @Override
    public void init() {

        camera = new OrthographicCamera();
        //TODO: Sort scaling out
        camera.setToOrtho(true,PixelManGame.width, PixelManGame.height);

        setupLevel();

    }

    private void setupLevel() {
        player = new Player(300,250);
        Platform ground = new Platform(0,300,1000,30);
        Platform p1 = new Platform(200,270,100,10);
        Platform p2 = new Platform(300,240,100,10);

        platforms.add(ground);
        platforms.add(p1);
        platforms.add(p2);
    }

    @Override
    public void update() {

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
        //TODO: Update this

        //Dont pan camera when player is at the edge of the level
        if(player.x < -1000)
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
