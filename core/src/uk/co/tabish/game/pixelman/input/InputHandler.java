package uk.co.tabish.game.pixelman.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import uk.co.tabish.game.pixelman.PixelManGame;

import java.util.HashMap;
import java.util.Map;

public class InputHandler implements InputProcessor {

    //TODO: Clean this up

    class TouchInfo {
        public boolean isDown = false;
        public Rectangle bounds = new Rectangle(0,0,0,0);
    }

    public static float fingerRadius = 0.1f;

    private InputState state;

    private Map<Integer, TouchInfo> fingers = new HashMap<Integer, TouchInfo>();

    //Buttons
    private Rectangle left = new Rectangle(0.05f,0.75f,0.25f,0.15f);
    private Rectangle right = new Rectangle(0.35f,0.75f,0.25f,0.15f);
    private Rectangle up = new Rectangle(0.7f,0.75f,0.2f,0.2f);

    private OrthographicCamera camera;
    float width = 200f;
    float height = 100f;

    private Texture upButton;
    private Texture leftButton;
    private Texture rightButton;

    private boolean isAndroid = false;

    public InputHandler(InputState state, boolean isAndroid) {
        this.state = state;
        for(int i=0;i<10;i++) {
            fingers.put(i,new TouchInfo());
        }
        camera = new OrthographicCamera();
        height = width*PixelManGame.height/(PixelManGame.width*1f);
        camera.setToOrtho(true, width,height);
        //System.out.println(width+","+height);

        left = new Rectangle(0.05f,0.7f,0.2f,0.2f*0.6f*width/height);
        right = new Rectangle(0.3f,0.7f,0.2f,0.2f*0.6f*width/height);
        up = new Rectangle(0.75f,0.675f,0.15f,0.15f*width/height);

        this.isAndroid = isAndroid;
    }

    public void getAssets() {
        upButton = PixelManGame.manager().get("controls/up.png");
        leftButton = PixelManGame.manager().get("controls/left.png");
        rightButton = PixelManGame.manager().get("controls/right.png");
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.D) {
            state.right = true;
        }
        if(keycode == Input.Keys.A) {
            state.left = true;
        }
        if(keycode == Input.Keys.W) {
            state.up = true;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if(keycode == Input.Keys.D) {
            state.right = false;
        }
        if(keycode == Input.Keys.A) {
            state.left = false;
        }
        if(keycode == Input.Keys.W) {
            state.up = false;
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        TouchInfo info = fingers.get(pointer);
        info.isDown = true;
        info.bounds.set(screenX/(PixelManGame.width * 1f), screenY/(PixelManGame.height * 1f), InputHandler.fingerRadius, InputHandler.fingerRadius);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {

        TouchInfo info = fingers.get(pointer);
        info.isDown = false;

        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    //Process all the touch events to update the input state;
    public void processInput() {

        boolean l = false;
        boolean r = false;
        boolean u = false;
        boolean isTouched = false;

        for(TouchInfo info : fingers.values()) {
            if(info.isDown) {
                isTouched = true;
                if(info.bounds.overlaps(left)) {
                    l = true;
                }
                if(info.bounds.overlaps(right)) {
                    r = true;
                }
                if(info.bounds.overlaps(up)) {
                    u = true;
                }
            }
        }

        state.left = l;
        state.right = r;
        state.up = u;
        state.a = isTouched;

    }
    
    public void draw(SpriteBatch batch) {
        if (isAndroid) {
            batch.setProjectionMatrix(camera.combined);
            batch.setColor(1f, 1f, 1f, 0.5f);
            batch.draw(leftButton, left.x * width, left.y * height, left.width * width, left.height * height);
            batch.draw(rightButton, right.x * width, right.y * height, right.width * width, right.height * height);
            batch.draw(upButton, up.x * width, up.y * height, up.width * width, up.height * height);
        }
    }

}
