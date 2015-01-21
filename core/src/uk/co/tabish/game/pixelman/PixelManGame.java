package uk.co.tabish.game.pixelman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import uk.co.tabish.game.Screen;
import uk.co.tabish.game.TwoDimensionalGame;

public class PixelManGame extends TwoDimensionalGame {

    /* TODO:
       - TitleScreen
       - Android touch controls
       - Camera scaling
       - Enemy hitting animations
     */

    //Screen constants
    public static final int LOADING_SCREEN = 3;
    public static final int GAME_SCREEN = 4;
    public static final int TITLE_SCREEN = 5;

    //Screen objects
    Screen loadingScreen;
    Screen gameScreen;
    Screen titleScreen;

    @Override
    public void init() {
        //Make screens
        loadingScreen = new LoadingScreen(PixelManGame.manager());
        gameScreen = new GameScreen();
        titleScreen = new TitleScreen();

        //Add screens to map
        this.addScreen(LOADING_SCREEN, loadingScreen);
        this.addScreen(GAME_SCREEN, gameScreen);
        this.addScreen(TITLE_SCREEN, titleScreen);

        //Set loading screen as first screen
        this.initialScreen(LOADING_SCREEN);
    }

    @Override
    public void processInput() {

        //Hard coded input for the time being
        //Move this into an input handling
        input().left = Gdx.input.isKeyPressed(Input.Keys.A);
        input().right = Gdx.input.isKeyPressed(Input.Keys.D);
        input().up = Gdx.input.isKeyPressed(Input.Keys.W);
    }

}
