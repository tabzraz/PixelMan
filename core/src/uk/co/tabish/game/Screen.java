package uk.co.tabish.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Screen {

    //Initialise the screen
    public void init();

    //Main update loop
    public void update();

    //Draw screen
    public void draw(SpriteBatch batch);

    //Transition to another screen, -1 indicates no transition
    public int changeScreenTo();

    //Dispose of any assets unique to the screen
    public void dispose();

}
