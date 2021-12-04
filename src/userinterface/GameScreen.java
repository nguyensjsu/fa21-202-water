package userinterface;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import objects.ClassicObjectFactory;
import objects.IObject;
import objects.IObjectFactory;
import util.Resource;
import util.Settings;

public class GameScreen extends JPanel implements Runnable, KeyListener, IGameScreenState {

	private final CharacterSelectionState characterSelectionState;
	private StartGameState startGameState;
	private GamePlayingState gamePlayingState;
	private GameOverState gameOverState;
	private IGameState state;
	private Settings settings;

	// private final IObjectFactoryFactory factoryFactory;
	private IObjectFactory objectFactory;

	private boolean isKeyPressed;
	private int fps = 40;

	private BufferedImage replayButtonImage;
	private BufferedImage gameOverButtonImage;

	private final int CHARACTER_BRUCE_LEE = 1;
	private final int CHARACTER_DINOSAUR = 2;
	private final int CHARACTER_MARIO = 3;
	private final int CHARACTER_RANDOM = 4;

	public GameScreen() {
		characterSelectionState = new CharacterSelectionState(this);
		startGameState = new StartGameState(this);
		gameOverState = new GameOverState(this);
		gamePlayingState = new GamePlayingState(this);

		// this.setStateStartGameState();
		this.setStateCharacterSelectionState();

		// factoryFactory = new RandomFactoryFactory();
		objectFactory = new ClassicObjectFactory();

		// objectFactory.getCharacterDecorator().setSpeedX(4);

		replayButtonImage = Resource.getResourceImage("data/replay_button.png");
		gameOverButtonImage = Resource.getResourceImage("data/gameover_text.png");
		settings = Settings.getInstance();
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
			objectFactory.getCharacterDecorator().resetBonus();
			objectFactory.getCharacterDecorator().resetScore();
			state.nextState();
			objectFactory.getCharacterDecorator().dead(true);
		} else if (objectFactory.getPowerUpManager().isCollision()) {
			objectFactory.getCharacterDecorator().playBonusSound();
			objectFactory.getCharacterDecorator().IncreaseBonus();
			// state.nextState();
			// objectFactory.getCharacterDecorator().dead(true);
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
		g.drawString("Score " + objectFactory.getCharacterDecorator().score, 500, 20);
		g.drawImage(gameOverButtonImage, 200, 30, null);
		g.drawImage(replayButtonImage, 283, 50, null);
	}

	public void paintGamePlayingState(Graphics g) {
		objectFactory.getObjects().forEach((object) -> object.draw(g));
		g.setColor(Color.BLACK);
		// g.drawString("HI " + objectFactory.getCharacterDecorator().score, 500, 20);
		g.drawString("Score " + objectFactory.getCharacterDecorator().score, 500, 20);
		g.drawString("Bonus " + objectFactory.getCharacterDecorator().bonus, 500, 35);
	}

	public void paintStartGameState(Graphics g) {
		objectFactory.getCharacterDecorator().playThemeSound();
		objectFactory.getCharacterDecorator().draw(g);
	}

	public void paintCharacterSelectionState(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString("Select character ", 220, 35);
		g.drawString("Press 1 for  BruceLee ", 220, 55);
		g.drawString("Press 2 for Dinosaur ", 220, 75);
		g.drawString("Press 3 for Mario ", 220, 95);
		g.drawString("Press 4 for Random Characters ", 220, 115);

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
		// int counter2 = 0;

		while (true) {
			gameUpdate();
			repaint();
			if (counter % 20 == 0) {
				msPerFrame = increaseFPS();
				counter = 0;
			}
			// if (counter2 % 200 == 0) {
			// objectFactory = settings.getNewFactory();
			// counter2 = 0;
			// }
			counter++;
			// counter2++;
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
		setStateCharacterSelectionState();
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

	public void keyCharacterSelectionState(int character) {
		if (character == CHARACTER_BRUCE_LEE) {
			settings.setCharacter(Settings.Character.CHARACTER_BRUCE_LEE);
		} else if (character == CHARACTER_DINOSAUR) {
			settings.setCharacter(Settings.Character.CHARACTER_DINOSAUR);
		} else if (character == CHARACTER_MARIO) {
			settings.setCharacter(Settings.Character.CHARACTER_MARIO);
		} else {
			settings.setCharacter(Settings.Character.CHARACTER_RANDOM);
		}
		if (character == CHARACTER_RANDOM) {
			objectFactory = settings.getNewFactory();
		} else {
			objectFactory = new ClassicObjectFactory();
		}
		objectFactory.getCharacterDecorator().setSpeedX(4);
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

	public void setStateCharacterSelectionState() {
		state = characterSelectionState;
	}

}
