package uk.co.tabish.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import uk.co.tabish.game.pixelman.input.InputState;

import java.util.HashMap;
import java.util.Map;

public class TwoDimensionalGame extends ApplicationAdapter {


    /* TODO:
        - AssetManager to reload textures, etc when pausing and resuming
     */

    //Drawing
    private OrthographicCamera camera;
    private SpriteBatch batch;

    //Screen info
    public static int width = 0;
    public static int height = 0;

    //Fps
    private long time = 0;
    private int fps = 0;
    private int showFps = 60;
    private boolean fpsVisible = true;

    //Screens
    private Map<Integer, Screen> screens;
    private Screen currentScreen;

    //Font
    private BitmapFont font;

    //AssetManager
    private static AssetManager assetManager;

    //Input state
    private static InputState inputState;

    //Is an Android app
    private boolean isAndroid = false;

    @Override
    public void create () {

        //Set screen info
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        Gdx.graphics.setVSync(true);

        //Create orthographic camera
        camera = new OrthographicCamera(width,height);
        //Set y axis pointing down
        camera.setToOrtho(true);

        //Create spritebatch for drawing to
        batch = new SpriteBatch();
        //Temporary font for drawing fps with
        font = new BitmapFont();

        //Camera and spritebatch settings
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        //Init time
        time = TimeUtils.nanoTime();

        //Map of screens
        screens = new HashMap<Integer, Screen>();

        //AssetManager
        assetManager = new AssetManager();

        //InputState
        inputState = new InputState();

        //Initialise
        init();
    }

    //Initialise the game here in children
    public void init() {

    }

    //Dont show fps
    public void disableFps() {
        fpsVisible = false;
    }

    //Access the assetmanager
    public static AssetManager manager() {
        return assetManager;
    }

    //Access input state
    public static InputState input() {
        return inputState;
    }

    //Handle input
    public void processInput() {

    }

    //Set as Android App
    public void setAndroidApp() {
        isAndroid = true;
    }

    public boolean isAndroid() {
        return isAndroid;
    }

    //Add new screen
    public void addScreen(int key,Screen value) {
        screens.put(key,value);
    }

    //Set initial screen
    public void initialScreen(int newScreen) {
        this.changeScreen(newScreen);
    }

    //Change the screen
    public void changeScreen(int newScreen) {
        //Whenever a screen is changed the old screen is disposed and the new one initialised
        if(currentScreen != null) {
            currentScreen.dispose();
        }
        currentScreen = screens.get(newScreen);
        currentScreen.init();
    }

    @Override
    public void render () {
        //Change the current screen if required
        if(currentScreen.changeScreenTo() != -1) {
            this.changeScreen(currentScreen.changeScreenTo());
        }

        //Process input and update the inputState
        processInput();

        //Update the current screen
        currentScreen.update();

        //Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        //Draw the current screen
        currentScreen.draw(batch);

        //Reset camera
        batch.setProjectionMatrix(camera.combined);

        if(fpsVisible) {
            //Draw fps in top left corner
            font.setColor(Color.WHITE);
            font.setScale(1, -1);
            font.draw(batch, "" + showFps, 0, 0);
        }

        batch.end();

        //Calculate fps
        fps++;
        if(TimeUtils.timeSinceNanos(time)>(1000000000-1)) {
            showFps = fps;
            if(showFps <= 55) {
                System.out.println("Fps drop");
            }
            fps=0;
            time = TimeUtils.nanoTime();
        }
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        camera = new OrthographicCamera(width, height);
        camera.setToOrtho(true);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        currentScreen.dispose();
        assetManager.dispose();
    }

}
