package uk.co.tabish.game.pixelman.background;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.pixelman.PixelManGame;
import uk.co.tabish.game.pixelman.level.Level;

import java.util.ArrayList;
import java.util.Random;

public class Background {

    private ArrayList<Integer> yPositions = new ArrayList<Integer>();
    private int xOffset = 0;
    private int xIncreaser = 0;
    Texture[] clouds = new Texture[3];

    Texture castle;
    Texture hill;
    Texture smallHill;

    public Background() {
        //Random rnd = new Random();
        for(int i=0;i<10;i++) {
            yPositions.add((i*23)%57);
        }

        for(int i=1;i<=3;i++) {
            clouds[i-1] = PixelManGame.manager().get("background/cloud" + i + ".png", Texture.class);
        }

        castle = PixelManGame.manager().get("background/castle.png", Texture.class);

        hill = PixelManGame.manager().get("background/hill.png", Texture.class);

        smallHill = PixelManGame.manager().get("background/hillsmall.png", Texture.class);
    }

    public void draw(SpriteBatch batch, Camera camera, Level level) {

        batch.setColor(Color.WHITE);

        //Hills
        for(float i= camera.position.x- camera.viewportWidth/2f;i<camera.position.x+camera.viewportWidth/2f;i+=smallHill.getWidth()+10) {
            batch.draw(smallHill, i, level.getHeight()-10-smallHill.getHeight());
        }

        for(float i= camera.position.x- camera.viewportWidth/2f+15;i<camera.position.x+camera.viewportWidth/2f;i+=smallHill.getWidth()+10) {
            batch.draw(smallHill, i, level.getHeight()-10-smallHill.getHeight());
        }

        //Big Hill
        batch.draw(hill, camera.position.x+camera.viewportWidth-(camera.viewportWidth*2f)*(camera.position.x/level.getWidth()), level.getHeight()+8-hill.getHeight());

        //Castle
        batch.draw(castle, camera.position.x+camera.viewportWidth-(camera.viewportWidth*2f)*(camera.position.x/level.getWidth())+12.5f, level.getHeight()+8-15-castle.getHeight());

        //Clouds
        for(int i=0;i<yPositions.size();i++) {
            batch.draw(clouds[i%clouds.length],  camera.position.x-camera.viewportWidth/2f+(((xOffset + 50*i) % 500)-50),camera.position.y-camera.viewportHeight/2f+yPositions.get(i)+40-(70f*camera.position.y/level.getHeight()));
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
