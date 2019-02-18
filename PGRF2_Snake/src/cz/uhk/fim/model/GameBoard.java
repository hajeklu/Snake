package cz.uhk.fim.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
import cz.uhk.fim.tiles.BonusTile;
import cz.uhk.fim.tiles.MalusTile;
import cz.uhk.fim.tiles.Tile;

/**
 * 
 * @author Hájek Class snake game board
 *
 */
public class GameBoard extends JPanel implements KeyListener {
	/**
	 * for serializable
	 */
	private static final long serialVersionUID = 6272822446839984728L;
	private Snake playerSnake;
	private Timer timer;
	private BonusTile bonus;
	private MalusTile malus;
	private int endOfMalus = 0;
	private boolean blik;
	private boolean clicked;
	private UISnake uiSnake;
	private boolean gameOver = false;
	private ImageMaker imageMaker;
	private int speed = 90;

	public GameBoard() {
		imageMaker = ImageMaker.getInstance();
		addKeyListener(this);
		setFocusable(true);
		playerSnake = new Snake();
		uiSnake = new UISnake();
		bonus = new BonusTile(10, 250);
		malus = new MalusTile(250, 250);
		// timer is moving all scene
		timer = new Timer(speed, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!gameOver) {
					repaint();
					playerSnake.tick();
					uiSnake.setNextDirectionAndTick(playerSnake, bonus);
					// can click
					clicked = true;
				}
			}
		});
		timer.start();
	}

	@Override
	public void paint(Graphics g) {
		// test to collision
		collision();

		if (!gameOver) {
			// test to eating bonus or malus
			eating();

			// Flashing whit eat malus
			if (endOfMalus > 40) {
				if (blik) {
					g.setColor(new Color(0x000000));
				} else {
					g.setColor(new Color(0xffffff));
				}
				blik = !blik;
			}

			// drawing
			g.fillRect(10, 120, 750, 420);

			// drawing head
			Tile head = playerSnake.getHead();
			wallWalk(head);
			head.draw(g);

			// drawing head
			Tile uiHead = uiSnake.getHead();
			wallWalk(uiHead);
			uiHead.draw(g);

			// drawing bonus and malus
			bonus.draw(g);
			malus.draw(g);

			// drawing player snake
			playerSnake.drawBody(g);
			// drawing uisnake
			uiSnake.drawBody(g);

			// return to constant speed
			if (speed < 90) {
				speed++;
				timer.setDelay(speed);
			}
			/**
			 * create border sub metode
			 */
			creatBorder(g);
			/**
			 * malus math
			 */
			endOfMalus--;
			
			/**
			 * draw string
			 */
			g.setColor(new Color(0x4286f4));
			g.drawString("Score snake1:  " + Integer.toString(playerSnake.getLenght()), 658, 132);
			g.setColor(new Color(0xff0000));
			g.drawString("Score snake2:  " + Integer.toString(uiSnake.getLenght()), 658, 145);
			g.setColor(new Color(0xffffff));
			g.drawString("Snake 1.0 - Last edit 27.04.2017", 10, 555);
			g.drawString("© Luboš Hájek - PGRF2", 630, 555);
		} else {
			timer.stop();
			g.setColor(new Color(0xffffff));
			g.drawString("Press SPACE to reset or ESC to exit", 280, 380);
			g.drawImage(imageMaker.getGameover(), 65, 240, null);

		}
	}

	/**
	 * test eat of bonus or malus
	 */
	private void eating() {
		if (isEat(bonus, uiSnake)) {
			uiSnake.eatingBonus();
			generateNewPosicion(bonus);
		}
		if (isEat(malus, uiSnake)) {
			endOfMalus = 63;
			speed = 5;
			timer.setDelay(speed);
			uiSnake.eatingBonus();
			generateNewPosicion(malus);
		}
		if (isEat(bonus, playerSnake)) {
			playerSnake.eatingBonus();
			generateNewPosicion(bonus);
		}
		if (isEat(malus, playerSnake)) {
			endOfMalus = 63;
			speed = 5;
			timer.setDelay(speed);
			playerSnake.eatingBonus();
			generateNewPosicion(malus);
		}
		if (endOfMalus > 0) {
			Tile.textures = true;
		} else {
			Tile.textures = false;
		}
	}

	/**
	 * test collision
	 */
	private void collision() {
		if (playerSnake.isCollisonHimSelf()) {
			gameOver = true;
		}
		if (uiSnake.isCollisonHimSelf()) {
			gameOver = true;
		}
		if (playerSnake.isCollison(uiSnake)) {
			gameOver = true;
		}
		if (uiSnake.isCollison(playerSnake)) {
			gameOver = true;
		}
	}

	private boolean isEat(Tile tile, Snake snake) {
		return tile.getX() == snake.getHead().getX() && tile.getY() == snake.getHead().getY();
	}

	/**
	 * 
	 * @param tile
	 *            generate new posicion bonus or malus x and y must be divisible
	 *            by 10
	 */
	private void generateNewPosicion(Tile tile) {
		Random r = new Random();
		double xtemp = (r.nextInt(740) + 10) / 10;
		double ytemp = (r.nextInt(410) + 120) / 10;
		int x = (int) Math.round(xtemp) * 10;
		int y = (int) Math.round(ytemp) * 10;
		if (tile instanceof BonusTile) {
			bonus = new BonusTile(x, y);
		} else {
			malus = new MalusTile(x, y);
		}

	}

	/**
	 * 
	 * @param g
	 *            metod create border sub metod for better read code
	 */
	public void creatBorder(Graphics g) {
		g.setColor(new Color(0xffffff));
		if(imageMaker.isReadyPicture()){
		g.drawImage(imageMaker.getTilesnake(), 10, 10, null);
		}else{
			g.drawString("Game Snake / non – player character snake", 270, 70);
		}
		g.drawRect(10, 10, 750, 100);
		g.setColor(new Color(0xffffff));
		g.drawRect(10, 120, 750, 420);
	}

	/**
	 * metod to make wall walk... if head is on the end set other side
	 * 
	 * @param head
	 */
	private void wallWalk(Tile head) {
		if (head.getX() < 10) {
			head.setLocation(750, head.getY());
		}
		if (head.getX() > 750) {
			head.setLocation(10, head.getY());
		}
		if (head.getY() < 120) {
			head.setLocation(head.getX(), 530);
		}
		if (head.getY() > 530) {
			head.setLocation(head.getX(), 120);
		}
	}

	/**
	 * reset game new instance snake and start timer
	 */
	public void resetGame() {
		playerSnake = new Snake();
		uiSnake = new UISnake();
		gameOver = false;
		endOfMalus = -1;
		speed = 90;
		timer.setDelay(speed);
		timer.start();
	}

	/**
	 * lisener for control snake can not set direction by 180 degree
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		/**
		 * only is not gameover
		 */
		if (!gameOver) {
			/**
			 * only one press key by one draw
			 */
			if (!clicked) {
				return;
			}
		}
		// can not click in this drawing 
		clicked = false;

		/**
		 * change direction by press key
		 */
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			if (playerSnake.getDirection() != Direction.DOWN)
				playerSnake.setDirection(Direction.UP);
			break;
		case KeyEvent.VK_S:
			if (playerSnake.getDirection() != Direction.UP)
				playerSnake.setDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_D:
			if (playerSnake.getDirection() != Direction.LEFT)
				playerSnake.setDirection(Direction.RIGHT);
			break;
		case KeyEvent.VK_A:
			if (playerSnake.getDirection() != Direction.RIGHT)
				playerSnake.setDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_UP:
			if (playerSnake.getDirection() != Direction.DOWN)
				playerSnake.setDirection(Direction.UP);
			break;
		case KeyEvent.VK_DOWN:
			if (playerSnake.getDirection() != Direction.UP)
				playerSnake.setDirection(Direction.DOWN);
			break;
		case KeyEvent.VK_RIGHT:
			if (playerSnake.getDirection() != Direction.LEFT)
				playerSnake.setDirection(Direction.RIGHT);
			break;
		case KeyEvent.VK_LEFT:
			if (playerSnake.getDirection() != Direction.RIGHT)
				playerSnake.setDirection(Direction.LEFT);
			break;
		case KeyEvent.VK_SPACE:
			if (gameOver) {
				resetGame();
			}
			break;
		case KeyEvent.VK_ESCAPE:
			if (gameOver) {
				System.exit(0);
			}
			break;
		// I leave it here for next testing 
		// case KeyEvent.VK_Q:
		// playerSnake.eatingBonus();
		// break;

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
