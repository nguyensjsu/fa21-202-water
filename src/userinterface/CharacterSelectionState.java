package userinterface;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CharacterSelectionState implements IGameState {

    IGameScreenState state;

    public CharacterSelectionState(IGameScreenState s) {
        this.state = s;
    }

    @Override
    public void nextState() {
        state.setStateStartGameState();
    }

    @Override
    public void paint(Graphics g) {
        state.paintCharacterSelectionState(g);
    }

    @Override
    public void keyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_1) {
            state.keyCharacterSelectionState(1);
        }else if (e.getKeyCode() == KeyEvent.VK_2) {
            state.keyCharacterSelectionState(2);
        }else if (e.getKeyCode() == KeyEvent.VK_3) {
            state.keyCharacterSelectionState(3);
        }
    }

    @Override
    public void keyRelease() {

    }

    @Override
    public void gameUpdate() {

    }
}
