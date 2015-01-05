package uk.co.tabish.game.pixelman.platform;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import uk.co.tabish.game.pixelman.PixelManGame;
import uk.co.tabish.game.thing.PhysicsComponent;
import uk.co.tabish.game.thing.Thing;

public class MovingPlatform extends Platform {

    private int horizDistance = 0;
    private int vertDistance = 0;

    private float horizStart=0f;
    private float vertStart=0f;

    private PhysicsComponent moveComponent = new PhysicsComponent();

    public MovingPlatform(float x, float y, float width, float height, float xSpeed, float ySpeed, int hDist, int vDist) {
        super(x, y, width, height);
        this.xSpeed=xSpeed;
        this.ySpeed=ySpeed;
        this.horizDistance=hDist;
        this.vertDistance=vDist;
        this.horizStart=x;
        this.vertStart=y;
        back= PixelManGame.manager().get("platforms/MovingBack.png", Texture.class);
        top = PixelManGame.manager().get("platforms/MovingTop.png", Texture.class);
    }

    @Override
    public void update() {

        //Ordering is important for moving platforms...

        if(Math.abs(x-horizStart)>=horizDistance) {
            horizStart+=Math.signum(xSpeed)*horizDistance;
            xSpeed*=-1f;
        }

        moveComponent.moveThing(this);

        if(Math.abs(y-vertStart)>=vertDistance) {
            vertStart+=Math.signum(ySpeed)*vertDistance;
            ySpeed*=-1f;
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.setColor(Color.MAGENTA);
        super.draw(batch);
    }


}
