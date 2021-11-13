package userinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import gameobject.*;
import util.Resource;

public class GameScreen extends JPanel implements Runnable, KeyListener, IGameScreenState {

	private StartGameState startGameState;
	private GamePlayingState gamePlayingState;
	private GameOverState gameOverState;
	private IGameState state;
	
	private Land land;
	private MainCharacter mainCharacter;
	private EnemiesManager enemiesManager;
	private Clouds clouds;
	private Thread thread;

	private boolean isKeyPressed;

	private BufferedImage replayButtonImage;
	private BufferedImage gameOverButtonImage;

	public GameScreen() {
		startGameState = new StartGameState(this);
		gameOverState = new GameOverState(this);
		gamePlayingState = new GamePlayingState(this);
		this.setStateStartGameState();

		mainCharacter = new MainCharacter();
		land = new Land(GameWindow.SCREEN_WIDTH, mainCharacter);
		mainCharacter.setSpeedX(4);
		replayButtonImage = Resource.getResouceImage("data/replay_button.png");
		gameOverButtonImage = Resource.getResouceImage("data/gameover_text.png");
		enemiesManager = new EnemiesManager(mainCharacter);
		clouds = new Clouds(GameWindow.SCREEN_WIDTH, mainCharacter);
	}

	public void startGame() {
		thread = new Thread(this);
		thread.start();
	}

	public void gameUpdate() {
		state.gameUpdate();
	}

	public void gamePlayingStateUpdate() {
		clouds.update();
		land.update();
		mainCharacter.update();
		enemiesManager.update();
		if (enemiesManager.isCollision()) {
			mainCharacter.playDeadSound();
			state.nextState();
			mainCharacter.dead(true);
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.decode("#f7f7f7"));
		g.fillRect(0, 0, getWidth(), getHeight());
		state.paint(g);
	}

	public void paintGameOverState(Graphics g) {
		clouds.draw(g);
		land.draw(g);
		enemiesManager.draw(g);
		mainCharacter.draw(g);
		g.setColor(Color.BLACK);
		g.drawString("HI " + mainCharacter.score, 500, 20);
		g.drawImage(gameOverButtonImage, 200, 30, null);
		g.drawImage(replayButtonImage, 283, 50, null);
	}

	public void paintGamePlayingState(Graphics g) {
		clouds.draw(g);
		land.draw(g);
		enemiesManager.draw(g);
		mainCharacter.draw(g);
		g.setColor(Color.BLACK);
		g.drawString("HI " + mainCharacter.score, 500, 20);
	}

	public void paintStartGameState(Graphics g) {
		mainCharacter.draw(g);
	}

	@Override
	public void run() {

		int fps = 100;
		long msPerFrame = 1000 * 1000000 / fps;
		long lastTime = 0;
		long elapsed;
		
		int msSleep;
		int nanoSleep;

		long endProcessGame;
		long lag = 0;

		while (true) {
			gameUpdate();
			repaint();
			endProcessGame = System.nanoTime();
			elapsed = (lastTime + msPerFrame - System.nanoTime());
			msSleep = (int) (elapsed / 1000000);
			nanoSleep = (int) (elapsed % 1000000);
			if (msSleep <= 0) {
				lastTime = System.nanoTime();
				continue;
			}
			try {
				Thread.sleep(msSleep, nanoSleep);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			lastTime = System.nanoTime();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!isKeyPressed) {
			isKeyPressed = true;
			state.keyPress(e);
		}
	}

	public void keyGameOverState() {
		setStateGamePlayingState();
		resetGame();
	}

	public void keyGamePlayingStateJump() {
		mainCharacter.jump();
	}

	public void keyGamePlayingStateDown() {
		mainCharacter.down(true);
	}

	public void keyStartGameState() {
		state.nextState();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		isKeyPressed = false;
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			state.keyRelease();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	private void resetGame() {
		enemiesManager.reset();
		mainCharacter.dead(false);
		mainCharacter.reset();
	}

	public void setStateGameOverState() {
		state = gameOverState;
	}

	public void setStateGamePlayingState() {
		state = gamePlayingState;
	}

	public void setStateStartGameState() {
		state = startGameState;
	}

}
