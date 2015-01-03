package uk.co.tabish.game.pixelman.level;

import uk.co.tabish.game.pixelman.enemy.Enemy;
import uk.co.tabish.game.pixelman.player.Player;
import uk.co.tabish.game.thing.Thing;

import java.util.ArrayList;
import java.util.List;

public class Level {

    private int width = 500;
    private int height = 300;

    //Player
    public Player player;

    //Platforms
    public List<Thing> platforms = new ArrayList<Thing>();

    //Enemies
    public List<Enemy> enemies = new ArrayList<Enemy>();

    //Enemies to add
    public List<Enemy> enemiesToAdd = new ArrayList<Enemy>();

    public Level(int w, int h, List<Thing> plats, List<Enemy> enems, List<Enemy> enemsToAdd, Player play) {
        width = w;
        height = h;
        platforms = plats;
        enemies = enems;
        player = play;
        enemiesToAdd = enemsToAdd;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
