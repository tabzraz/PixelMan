package uk.co.tabish.game.pixelman.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import uk.co.tabish.game.pixelman.enemy.Cannon;
import uk.co.tabish.game.pixelman.enemy.Charger;
import uk.co.tabish.game.pixelman.enemy.Enemy;
import uk.co.tabish.game.pixelman.enemy.Skeleton;
import uk.co.tabish.game.pixelman.platform.*;
import uk.co.tabish.game.pixelman.player.Player;
import uk.co.tabish.game.thing.Thing;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Tabz on 07/12/2014.
 */
public class LevelReader {

    public static Level getLevel(String levelName) {
        //Load the file
        FileHandle levelhandle = Gdx.files.internal("levels/" + levelName + ".lvl");
        Scanner lines = new Scanner(levelhandle.read());

        //Indicating the level information has been reached in the file
        boolean starting = false;

        //Variables to construct a level
        int width = 100;
        int height = 100;

        int playerX = 10;
        int playerY = 10;

        List<Thing> platforms = new ArrayList<Thing>();

        List<Enemy> enemies = new ArrayList<Enemy>();

        List<Enemy> enemiesToAdd = new ArrayList<Enemy>();
        //---

        while(lines.hasNext()) {

            String line = lines.nextLine();
            String[] words = line.split("\\s");

            //The level information starts after LEVEL START
            if(line.equals("LEVEL START")) {
                starting = true;
                continue;
            }
            //Level info ends at LEVEL END
            if(line.equals("LEVEL END")) {
                break;
            }

            //Read in the lines and create the respective objects
            if(starting) {

                if(words[0].equals("Level")) {

                    //Level width height
                    width = Integer.valueOf(words[1]);
                    height = Integer.valueOf(words[2]);

                } else if(words[0].equals("Player")) {

                    //Player x y
                    playerX = Integer.valueOf(words[1]);
                    playerY = Integer.valueOf(words[2]);

                } else if(words[0].equals("Platform")) {

                    platforms.add(createPlatform(words));

                } else if(words[0].equals("Enemy")) {

                    enemies.add(createEnemy(words,enemiesToAdd));

                } else if(words[0].equals("Crystal")) {

                    platforms.add(new LevelEnd(Integer.valueOf(words[1]), Integer.valueOf(words[2])));
                }
            }

        }

        Level level = new Level(width,height,platforms,enemies,enemiesToAdd,new Player(playerX,playerY));

        return level;
    }

    private static Platform createPlatform(String[] info) {
        //Platform {Platform type} x y width height
        //Platform Moving x y width height xSpeed ySpeed hDist vDist

        int x = Integer.valueOf(info[2]);
        int y = Integer.valueOf(info[3]);
        int width = Integer.valueOf(info[4]);
        int height = Integer.valueOf(info[5]);

        if(info[1].equals("Platform")) {

            return new Platform(x,y,width,height);

        } else if(info[1].equals("Deadly")) {

            return new DeadlyPlatform(x,y,width,height);

        } else if(info[1].equals("Ice")) {

            return new IcePlatform(x,y,width,height);

        } else if(info[1].equals("Oneway")) {

            return new OneWayPlatform(x,y,width,height);

        } else if(info[1].equals("Moving")) {

            float xSpeed = Float.valueOf(info[6]);
            float ySpeed = Float.valueOf(info[7]);
            int hDist = Integer.valueOf(info[8]);
            int vDist = Integer.valueOf(info[9]);

            return new MovingPlatform(x,y,width,height,xSpeed,ySpeed,hDist,vDist);
        }

        return null;
    }

    private static Enemy createEnemy(String[] info, List<Enemy> enemsToAdd) {
        //Enemy {Enemy type} x y

        int x = Integer.valueOf(info[2]);
        int y = Integer.valueOf(info[3]);

        if(info[1].equals("Cannon")) {
            return new Cannon(x,y,enemsToAdd);
        } else if(info[1].equals("Charger")) {
            return new Charger(x,y);
        } else if(info[1].equals("Skeleton")) {
            return new Skeleton(x,y);
        }

        return null;
    }
}
