package uk.co.tabish.game.pixelman;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.Screen;
import uk.co.tabish.game.pixelman.platform.Platform;
import uk.co.tabish.game.pixelman.player.Player;
import uk.co.tabish.game.thing.Thing;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {

    private Player player;

    private List<Thing> things = new ArrayList<Thing>();

    @Override
    public void init() {

        setupLevel();

    }

    private void setupLevel() {
        player = new Player(300,250);
        Platform ground = new Platform(0,300,1000,30);

        things.add(player);
        things.add(ground);

    }

    @Override
    public void update() {

        for(Thing thing : things) {
            thing.update();
        }

    }

    @Override
    public void draw(SpriteBatch batch) {
        for(Thing thing : things) {
            thing.draw(batch);
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
