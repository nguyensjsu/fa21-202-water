package userinterface;

import java.awt.*;
import java.awt.event.KeyEvent;

public interface IGameState {

    void nextState();

    void paint(Graphics g);

    void keyPress(KeyEvent e);

    void keyRelease();

    void gameUpdate();
}
