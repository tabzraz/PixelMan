package uk.co.tabish.game.thing;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import uk.co.tabish.game.pixelman.PixelManGame;

public class Thing {

    public float x;
    public float y;
    public float xSpeed;
    public float ySpeed;
    public float xAccel;
    public float yAccel;

    public boolean solid = true;

    private float width;
    private float height;

    private Rectangle bounds;

    private Texture self;
    private boolean debugTexture = true;

    public Thing(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bounds = new Rectangle(x,y,width,height);
    }

    public void update() {

    }

    public Rectangle bounds() {
        bounds.setPosition(x,y);
        return bounds;
    }

    public void collided(Thing thing, float xVector, float yVector) {

    }

    public void draw(SpriteBatch batch) {
        //Temporary until all sprites are actually made
        if(debugTexture) {
            self = PixelManGame.manager().get("rect.png");
        }

        batch.setColor(Color.WHITE);
        batch.draw(self,x,y,width,height);

    }

    public void setTexture(Texture newSelf) {
        debugTexture = false;
        self = newSelf;
    }

}
