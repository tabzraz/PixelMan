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

        //Move player left or right with accel
        if(inputState.left && !inputState.right) {
            player.xAccel = player.playerInAir ? -Player.playerAirHorizAccel : -Player.playerGroundHorizAccel;
        }
        if(inputState.right && !inputState.left) {
            player.xAccel = player.playerInAir ? Player.playerAirHorizAccel : Player.playerGroundHorizAccel;
        }
        //Stop player if not pressing any of the keys
        if(!inputState.right && !inputState.left) {
            player.xAccel = 0;
        }

        //Make player jump if they are on the ground
        if(inputState.up && !player.playerInAir) {
            player.ySpeed = Player.playerJumpSpeed;
            player.playerJumping = true;
        }

        //Indiciate the player has let go of the jump button
        if(player.playerInAir && !inputState.up) {
            player.playerJumping = false;
        }


    }
}
