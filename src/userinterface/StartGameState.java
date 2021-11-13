package userinterface;

import java.awt.*;
import java.awt.event.KeyEvent;

public class StartGameState implements IGameState {

    IGameScreenState state;

    public StartGameState(IGameScreenState s) {
        this.state = s;
    }

    @Override
    public void nextState() {
        state.setStateGamePlayingState();
    }

    @Override
    public void paint(Graphics g) {
        state.paintStartGameState(g);
    }

    @Override
    public void keyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            state.keyStartGameState();
        }
    }

    @Override
    public void keyRelease() {

    }

    @Override
    public void gameUpdate() {

    }
}
