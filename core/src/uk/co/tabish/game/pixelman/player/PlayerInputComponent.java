package uk.co.tabish.game.pixelman.player;

import uk.co.tabish.game.pixelman.input.InputState;
import uk.co.tabish.game.pixelman.PixelManGame;

public class PlayerInputComponent {

    private InputState inputState;

    //To prevent bunny hopping
    private boolean letGoOfUp = true;

    public PlayerInputComponent() {
        inputState = PixelManGame.input();
    }

    public void handleInput(Player player) {

        //Move player left or right with accel
        if(inputState.left && !inputState.right) {
            player.xAccel = player.playerInAir ? -Player.playerAirHorizAccel : -player.playerGroundHorizAccel;
        }
        if(inputState.right && !inputState.left) {
            player.xAccel = player.playerInAir ? Player.playerAirHorizAccel : player.playerGroundHorizAccel;
        }
        //Stop player if not pressing any of the keys
        if(!inputState.right && !inputState.left) {
            player.xAccel = 0;
        }

        //Make player jump if they are on the ground or can double jump
        if(inputState.up && (!player.playerInAir || player.canDoubleJump) && letGoOfUp) {
            player.ySpeed = Player.playerJumpSpeed;
            player.playerJumping = true;
            letGoOfUp = false;
            if(!player.playerInAir) {
                //Starting a jump from the ground
                player.canDoubleJump=true;
            } else {
                //In the air
                player.canDoubleJump=false;
            }
        }

        //Indiciate the player has stopped jumping
        if(player.playerInAir && !inputState.up) {
            player.playerJumping = false;
        }

        //Player has let go of the jump button
        if(!inputState.up) {
            letGoOfUp = true;
        }



    }
}
