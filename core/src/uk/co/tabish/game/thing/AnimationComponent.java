package uk.co.tabish.game.thing;

import com.badlogic.gdx.graphics.Texture;

public class AnimationComponent {

    private int framesPerImage = 1;
    private Texture[] images;
    private int count;
    private int index;

    public AnimationComponent(Texture[] images, int framesPerImage) {
        this.images = images;
        this.framesPerImage = framesPerImage;
    }

    public Texture getTexture() {
        count++;
        if(count>=framesPerImage) {
            count = 0;
            index++;
            index%= images.length;
        }
        return images[index];
    }
}
