package userinterface;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GamePlayingState implements IGameState {

    IGameScreenState state;

    public GamePlayingState(IGameScreenState s) {
        this.state = s;
    }

    @Override
    public void nextState() {
        state.setStateGameOverState();
    }

    @Override
    public void paint(Graphics g) {
        state.paintGamePlayingState(g);
    }

    @Override
    public void keyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            state.keyGamePlayingStateJump();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            state.keyGamePlayingStateDown();
        }
    }

    @Override
    public void keyRelease() {
        state.keyGamePlayingStateDown();
    }

    @Override
    public void gameUpdate() {
        state.gamePlayingStateUpdate();
    }
}
