package uk.co.tabish.game.thing;

import com.badlogic.gdx.graphics.Texture;

public class OnetimeAnimationComponent extends AnimationComponent {

    private int limit = 0;
    private int limitCount = -1;
    private boolean finished = false;

    public OnetimeAnimationComponent(Texture[] images, int framesPerImage) {
        super(images, framesPerImage);
        limit = images.length*framesPerImage;
    }

    public Texture getTexture() {
        limitCount++;
        if(limitCount>=limit-1) {
            //Going to draw the final frame now, so it has finished the cycle
            finished = true;
        }
        return super.getTexture();
    }

    public boolean isFinished() { return finished; }
}
