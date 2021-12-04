package userinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import objects.*;
import objects.EnemiesManager;
import objects.CharacterDecorator;
import util.Resource;

public class GameScreen extends JPanel implements Runnable, KeyListener, IGameScreenState {

	private final CharacterSelectionState characterSelectionState;
	private StartGameState startGameState;
	private GamePlayingState gamePlayingState;
	private GameOverState gameOverState;
	private IGameState state;

	//private final IObjectFactoryFactory factoryFactory;
	private IObjectFactory objectFactory;

	private boolean isKeyPressed;
	private int fps = 40;

	private BufferedImage replayButtonImage;
	private BufferedImage gameOverButtonImage;

	private final int CHARACTER_BRUCE_LEE = 1;
	private final int CHARACTER_DINOSAUR = 2;
	private final int CHARACTER_MARIO = 3;

	public GameScreen() {
		characterSelectionState = new CharacterSelectionState(this);
		startGameState = new StartGameState(this);
		gameOverState = new GameOverState(this);
		gamePlayingState = new GamePlayingState(this);
		//this.setStateStartGameState();
		this.setStateCharacterSelectionState();

		//factoryFactory = new RandomFactoryFactory();
		objectFactory = new ClassicObjectFactory();

		//objectFactory.getCharacterDecorator().setSpeedX(4);

		replayButtonImage = Resource.getResouceImage("data/replay_button.png");
		gameOverButtonImage = Resource.getResouceImage("data/gameover_text.png");
	}

	public void startGame() {
		Thread thread = new Thread(this);
		thread.start();
	}

	public void gameUpdate() {
		state.gameUpdate();
	}

	public void gamePlayingStateUpdate() {
		objectFactory.getObjects().forEach(IObject::update);
		if (objectFactory.getEnemyManager().isCollision()) {
			objectFactory.getCharacterDecorator().playDeadSound();
			objectFactory.getCharacterDecorator().resetHealth();
			objectFactory.getCharacterDecorator().resetScore();
			state.nextState();
			objectFactory.getCharacterDecorator().dead(true);
		} else if (objectFactory.getPowerUpManager().isCollision()) {
			objectFactory.getCharacterDecorator().playDeadSound();
			objectFactory.getCharacterDecorator().IncreaseHealth();
//			state.nextState();
//			objectFactory.getCharacterDecorator().dead(true);
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.decode("#f7f7f7"));
		g.fillRect(0, 0, getWidth(), getHeight());
		state.paint(g);
	}

	public void paintGameOverState(Graphics g) {
		fps = 40;
		objectFactory.getObjects().forEach((object) -> object.draw(g));
		g.setColor(Color.BLACK);
		g.drawString("HI " + objectFactory.getCharacterDecorator().score, 500, 20);
		g.drawImage(gameOverButtonImage, 200, 30, null);
		g.drawImage(replayButtonImage, 283, 50, null);
	}

	public void paintGamePlayingState(Graphics g) {
		objectFactory.getObjects().forEach((object) -> object.draw(g));
		g.setColor(Color.BLACK);
		//g.drawString("HI " + objectFactory.getCharacterDecorator().score, 500, 20);
		g.drawString("HI " + objectFactory.getCharacterDecorator().score, 500, 20);
		g.drawString("Health " + objectFactory.getCharacterDecorator().health, 500, 35);
	}

	public void paintStartGameState(Graphics g) {
		//objectFactory.getCharacterDecorator().playThemeSound();
		objectFactory.getCharacterDecorator().draw(g);
	}

	public void paintCharacterSelectionState(Graphics g){
		g.setColor(Color.BLACK);
		g.drawString("Select character ", 250, 35);
		g.drawString("1. BruceLee ", 250, 55);
		g.drawString("2. Dinosaur ", 250, 75);
		g.drawString("3. Mario ", 250, 95);

	}

	public long increaseFPS() {
		this.fps += 1;
		return 1000 * 1000000 / fps;
	}

	@Override
	public void run() {

		int fps = 100;
		long msPerFrame = 1000 * 1000000 / fps;
		long lastTime = 0;
		long elapsed;

		int msSleep;
		int nanoSleep;

		int counter = 0;

		while (true) {
			gameUpdate();
			repaint();
			if (counter % 20 == 0) {
				msPerFrame = increaseFPS();
				counter = 0;
			}
			counter++;
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
		objectFactory.getCharacterDecorator().jump();
	}

	public void keyGamePlayingStateDown() {
		objectFactory.getCharacterDecorator().down(true);
	}

	public void keyStartGameState() {
		state.nextState();
	}

	public void keyCharacterSelectionState(int character){
		if (character == CHARACTER_BRUCE_LEE) {
			objectFactory.initialise(CHARACTER_BRUCE_LEE);
			objectFactory.getCharacterDecorator().setSpeedX(4);
			state.nextState();
		} else if (character == CHARACTER_DINOSAUR) {
			objectFactory.initialise(CHARACTER_DINOSAUR);
			objectFactory.getCharacterDecorator().setSpeedX(4);
			state.nextState();
		}else if(character == CHARACTER_MARIO){
			objectFactory.initialise(CHARACTER_MARIO);
			objectFactory.getCharacterDecorator().setSpeedX(4);
			state.nextState();
		}

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
		objectFactory.getEnemyManager().reset();
		objectFactory.getCharacterDecorator().dead(false);
		objectFactory.getCharacterDecorator().reset();
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

	public void setStateCharacterSelectionState(){
		state = characterSelectionState;
	}

}
