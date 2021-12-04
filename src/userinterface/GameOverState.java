package userinterface;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverState implements IGameState {

    IGameScreenState state;

    public GameOverState(IGameScreenState s) {
        this.state = s;
    }

    @Override
    public void nextState() {
        state.setStateCharacterSelectionState();
    }

    @Override
    public void paint(Graphics g) {
        state.paintGameOverState(g);
    }

    @Override
    public void keyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            state.keyGameOverState();
        }
    }

    @Override
    public void keyRelease() {

    }

    @Override
    public void gameUpdate() {

    }
}
