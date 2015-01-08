package uk.co.tabish.game.pixelman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.Screen;
import uk.co.tabish.game.pixelman.collision.CollisionHandler;
import uk.co.tabish.game.pixelman.effects.EffectThing;
import uk.co.tabish.game.pixelman.enemy.Cannon;
import uk.co.tabish.game.pixelman.enemy.Enemy;
import uk.co.tabish.game.pixelman.enemy.Info;
import uk.co.tabish.game.pixelman.level.Level;
import uk.co.tabish.game.pixelman.level.LevelReader;
import uk.co.tabish.game.pixelman.platform.IcePlatform;
import uk.co.tabish.game.pixelman.platform.OneWayPlatform;
import uk.co.tabish.game.pixelman.platform.Platform;
import uk.co.tabish.game.pixelman.player.Player;
import uk.co.tabish.game.thing.OnetimeAnimationComponent;
import uk.co.tabish.game.thing.Thing;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    private Level level;

    private Player player;

    //Platforms
    private List<Thing> platforms = new ArrayList<Thing>();

    //Enemies
    private List<Enemy> enemies = new ArrayList<Enemy>();
    private List<Enemy> enemiesToAdd = new ArrayList<Enemy>();

    //Effects/Death animations
    private List<EffectThing> effects = new ArrayList<EffectThing>();

    //Private Camera
    private OrthographicCamera camera;
    private float cameraHeight = 150;
    private float cameraWidth = 200;

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
        enemiesToAdd = new ArrayList<Enemy>();

        //Get level
        level = LevelReader.getLevel("level1");

        //Setup level
        player = level.player;
        platforms = level.platforms;
        enemies = level.enemies;
        enemiesToAdd = level.enemiesToAdd;

        camera.position.set(cameraWidth/2f,level.getHeight()-cameraHeight/2f, 0f);
    }

    private void playerDied() {
        //TODO: Player death animation
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

        //Add any enemies that were spawned
        enemies.addAll(enemiesToAdd);
        enemiesToAdd.clear();

        //Construct new info object for enemies
        Info info = new Info();
        info.player = player;

        //Update enemies
        for(int i=0; i<enemies.size();i++) {
            Enemy enemy = enemies.get(i);
            enemy.receiveInfo(info);
            enemy.update();
            if(enemy.dead) {
                //Remove enemy from the list
                enemies.remove(i);
                i--;
                //Add its death animation to the list to be shown
                if(enemy.deathAnim != null) {
                    effects.add(new EffectThing(enemy.x, enemy.y, enemy.deathAnim));
                }
            }
        }

        //Check if any effects need to be removed from the list
        for(int i=0;i<effects.size();i++) {
            if(effects.get(i).isFinished()) {
                effects.remove(i);
                i--;
            }
        }

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

        //Background
        batch.setColor(99f/255f,155f/255f,255f/255f,1f);
        batch.draw(PixelManGame.manager().get("rect.png", Texture.class), camera.position.x-cameraWidth/2f,camera.position.y-cameraHeight/2f,cameraWidth,cameraHeight);

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


        //Draw effects
        for(EffectThing effectThing : effects) {
            effectThing.draw(batch);
        }
    }

    @Override
    public int changeScreenTo() {
        return -1;
    }

    @Override
    public void dispose() {

    }

}
