package uk.co.tabish.game.pixelman.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.thing.MovingComponent;
import uk.co.tabish.game.thing.Thing;

public class Player extends Thing {

    //Player constants
    private static final int playerWidth = 30;
    private static final int playerHeight = 50;

    private static final float playerMaxXSpeed = 400f;
    private static final float playerMaxYSpeed = 400f;

    public static final float playerHorizAccel = 800f;


    //Components
    private MovingComponent movingComponent;
    private PlayerInputComponent inputComponent;

    public Player(float x, float y) {
        super(x, y, playerWidth, playerHeight);

        //Initialise all components
        movingComponent = new MovingComponent();
        movingComponent.setMaxXSpeed(playerMaxXSpeed);
        movingComponent.setMaxYSpeed(playerMaxYSpeed);

        inputComponent = new PlayerInputComponent();
    }

    @Override
    public void update() {
        inputComponent.handleInput(this);

        movingComponent.moveThing(this);
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(Color.GREEN);
        super.draw(batch);
    }

}
