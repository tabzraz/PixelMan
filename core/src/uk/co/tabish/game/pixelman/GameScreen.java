package uk.co.tabish.game.pixelman;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.Screen;
import uk.co.tabish.game.pixelman.collision.CollisionHandler;
import uk.co.tabish.game.pixelman.enemy.Enemy;
import uk.co.tabish.game.pixelman.enemy.GroundEnemy;
import uk.co.tabish.game.pixelman.enemy.Info;
import uk.co.tabish.game.pixelman.level.Level;
import uk.co.tabish.game.pixelman.platform.IcePlatform;
import uk.co.tabish.game.pixelman.platform.OneWayPlatform;
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

    //Enemies
    private List<Enemy> enemies = new ArrayList<Enemy>();

    //Private Camera
    private OrthographicCamera camera;
    private int cameraHeight = 200;
    private int cameraWidth = 200;

    @Override
    public void init() {

        camera = new OrthographicCamera();
        cameraWidth = cameraHeight * PixelManGame.width/PixelManGame.height;
        camera.setToOrtho(true,cameraWidth,cameraHeight);

        setupLevel();

    }

    private void setupLevel() {

        //Reinit objects
        player = new Player(100,250);
        platforms = new ArrayList<Thing>();
        enemies = new ArrayList<Enemy>();

        //TODO: Add level loading here
        Platform ground = new Platform(10,270,480,30);

        platforms.add(ground);

        for(int i=4; i<10;i++) {
            Platform p = new Platform(i*50,200,30,10);
            platforms.add(p);
            Platform p1 = new Platform(400-i*50,120-i*10,30,10);
            //platforms.add(p1);
        }

        IcePlatform i1 = new IcePlatform(200,260,200,10);
        //platforms.add(i1);

        OneWayPlatform o1 = new OneWayPlatform(50,240,100,10);
        //platforms.add(o1);

        GroundEnemy g1 = new GroundEnemy(200,250);
        enemies.add(g1);

        camera.position.set(cameraWidth/2f,level.getHeight()-cameraHeight/2f, 0f);
    }

    private void playerDied() {
        setupLevel();
    }

    @Override
    public void update() {

        //Update player
        player.update();

        //Update platforms
        for(Thing thing : platforms) {
            thing.update();
        }

        //Construct new info object for enemies
        Info info = new Info();
        info.player = player;

        //Update enemies
        for(Enemy enemy : enemies) {
            enemy.receiveInfo(info);
            enemy.update();
        }

        /* TODO: Sort out collisions*/

        //Handle collisions between the player and platforms
        for(Thing thing : platforms) {
            if(CollisionHandler.isColliding(player,thing)) {
                CollisionHandler.handleCollision(player,thing);
            }
        }

        //Handle collisions between the enemies and platforms
        for(Thing enemy : enemies) {
            for(Thing platform : platforms) {
                if(CollisionHandler.isColliding(enemy,platform)) {
                    CollisionHandler.handleCollision(enemy, platform);
                }
            }
        }

        //Handle collisions between the player and enemies
        for(Thing enemy : enemies) {
            if(CollisionHandler.isColliding(player,enemy)) {
                CollisionHandler.handleCollision(player,enemy);
            }
        }

        //Update Camera

        //Horizontal panning
        if(player.x >= cameraWidth/2f - player.bounds().width/2f && player.x <= level.getWidth() - cameraWidth/2f - player.bounds().width/2f) {
            camera.position.set(player.x + player.bounds().width / 2f, camera.position.y, 0f);
        } else if(player.x < cameraWidth/2f - player.bounds().width/2f) {
            camera.position.set(cameraWidth/2f,camera.position.y,0f);
        } else {
            camera.position.set(level.getWidth() - cameraWidth/2f,camera.position.y,0f);
        }

        //Vertical panning
        //Is the camera going to be pushed into an incorrect place, if so then correct it
        if(player.y + cameraHeight/6f>= cameraHeight/2f && player.y+player.bounds().height-cameraHeight/6f <= level.getHeight() - cameraHeight/2f) {
            //Player is within level, and vertical panning might be required
            //Only pan if player is in the top or bottom third of the screen

            if(player.y - (camera.position.y - cameraHeight/2f)<= cameraHeight/3f) {
                //Player in top third
                camera.position.set(camera.position.x, player.y + cameraHeight/6f,0f);
            } else if(camera.position.y+cameraHeight/2f - (player.y+player.bounds().height) <= cameraHeight/3f) {
                //Player in bottom third
                camera.position.set(camera.position.x, player.y+player.bounds().height-cameraHeight/6f,0f);
            }

        } else if(player.y + cameraHeight/6f < cameraHeight/2f) {
            camera.position.set(camera.position.x, cameraHeight/2f,0f);
        } else {
            camera.position.set(camera.position.x, level.getHeight() - cameraHeight/2f,0f);
        }

        camera.update();

        //Check if player died
        if(player.playerDead) {
            playerDied();
        }

    }

    @Override
    public void draw(SpriteBatch batch) {

        //Use internal camera
        batch.setProjectionMatrix(camera.combined);

        //Draw platforms
        for(Thing thing : platforms) {
            thing.draw(batch);
        }

        //Draw enemies
        for(Thing thing : enemies) {
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
