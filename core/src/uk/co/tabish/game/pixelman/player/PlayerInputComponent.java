package uk.co.tabish.game.pixelman.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import uk.co.tabish.game.InputState;
import uk.co.tabish.game.pixelman.PixelManGame;

public class PlayerInputComponent {

    private InputState inputState;

    public PlayerInputComponent() {
        inputState = PixelManGame.input();
    }

    public void handleInput(Player player) {

        if(inputState.left && !inputState.right) {
            player.xAccel = -Player.playerHorizAccel;
        }
        if(inputState.right && !inputState.left) {
            player.xAccel = Player.playerHorizAccel;
        }

    }
}
