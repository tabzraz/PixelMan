package uk.co.tabish.game.pixelman;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import uk.co.tabish.game.Screen;
import uk.co.tabish.game.TwoDimensionalGame;
import uk.co.tabish.game.pixelman.input.InputHandler;

public class PixelManGame extends TwoDimensionalGame {

    /* TODO:
       - Android touch controls
       - Camera scaling
       - Enemy hitting animations
       - Sounds
     */

    //Screen constants
    public static final int LOADING_SCREEN = 3;
    public static final int GAME_SCREEN = 4;
    public static final int TITLE_SCREEN = 5;

    //Screen objects
    Screen loadingScreen;
    Screen gameScreen;
    Screen titleScreen;

    //Input handler
    InputHandler inputHandler;

    @Override
    public void init() {

        //Initialise input handler
        inputHandler = new InputHandler(input(), isAndroid());

        Gdx.input.setInputProcessor(inputHandler);

        //Make screens
        loadingScreen = new LoadingScreen(PixelManGame.manager());
        gameScreen = new GameScreen(inputHandler);
        titleScreen = new TitleScreen();

        //Add screens to map
        this.addScreen(LOADING_SCREEN, loadingScreen);
        this.addScreen(GAME_SCREEN, gameScreen);
        this.addScreen(TITLE_SCREEN, titleScreen);

        //Set loading screen as first screen
        this.initialScreen(LOADING_SCREEN);

        //this.disableFps();
    }

    @Override
    public void processInput() {
        if(isAndroid()) {
            inputHandler.processInput();
        }
    }
}
