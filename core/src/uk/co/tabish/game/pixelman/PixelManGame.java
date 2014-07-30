package uk.co.tabish.game.pixelman;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;
import uk.co.tabish.game.Screen;
import uk.co.tabish.game.TwoDimensionalGame;

import java.awt.*;

public class PixelManGame extends TwoDimensionalGame {

    //Screen constants
    public static final int LOADING_SCREEN = 3;
    public static final int GAME_SCREEN = 4;

    //Screen objects
    Screen loadingScreen;
    Screen gameScreen;

    @Override
    public void init() {
        //Make screens
        loadingScreen = new LoadingScreen(PixelManGame.manager());
        gameScreen = new GameScreen();

        //Add screens to map
        this.addScreen(LOADING_SCREEN, loadingScreen);
        this.addScreen(GAME_SCREEN, gameScreen);

        //Set loading screen as first screen
        this.initialScreen(LOADING_SCREEN);
    }

    @Override
    public void processInput() {

        //Hard coded input for the time being
        input().left = Gdx.input.isKeyPressed(Input.Keys.A);
        input().right = Gdx.input.isKeyPressed(Input.Keys.D);
    }

}
