package uk.co.tabish.game.pixelman.background;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.pixelman.PixelManGame;

import java.util.ArrayList;
import java.util.Random;

public class Background {

    private ArrayList<Integer> yPositions = new ArrayList<Integer>();
    private int xOffset = 0;
    private int xIncreaser = 0;
    Texture[] clouds = new Texture[3];

    public Background() {
        Random rnd = new Random();
        for(int i=0;i<10;i++) {
            yPositions.add(rnd.nextInt(130));
        }

        for(int i=1;i<=3;i++) {
            clouds[i-1] = PixelManGame.manager().get("background/cloud" + i + ".png", Texture.class);
        }
    }

    public void draw(SpriteBatch batch, Camera camera) {

        batch.setColor(Color.WHITE);
        for(int i=0;i<yPositions.size();i++) {
            batch.draw(clouds[i%clouds.length],  camera.position.x-camera.viewportWidth/2f+(((xOffset + 50*i) % 500)-50),camera.position.y-camera.viewportHeight/2f+yPositions.get(i));
        }

        xIncreaser++;
        xIncreaser%=30;
        if(xIncreaser==0) {
            xOffset++;
        }
        if(xOffset>500) {
            xOffset-=500;
        }

    }
}
