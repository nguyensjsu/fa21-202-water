package userinterface;

import java.awt.*;

public interface IGameScreenState {

    void setStateGameOverState();

    void setStateGamePlayingState();

    void setStateStartGameState();

    void paintGameOverState(Graphics g);

    void paintGamePlayingState(Graphics g);

    void paintStartGameState(Graphics g);

    void keyGameOverState();

    void keyGamePlayingStateJump();

    void keyGamePlayingStateDown();

    void keyStartGameState();

    void gamePlayingStateUpdate();
}
