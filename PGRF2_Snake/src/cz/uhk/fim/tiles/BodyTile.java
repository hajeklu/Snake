package cz.uhk.fim.tiles;

import java.awt.Color;
import java.awt.Graphics;

import cz.uhk.fim.model.ImageMaker;
import cz.uhk.fim.model.Snake;
import cz.uhk.fim.model.SnakeTextures;

/**
 * 
 * @author Hájek Class body tile
 *
 */
public class BodyTile extends Tile {

	private ImageMaker imageMaker;
	private Snake snake;

	public BodyTile(int x, int y, Snake snake) {
		this.snake = snake;
		this.x = x;
		this.y = y;
		imageMaker = ImageMaker.getInstance();
	}

	/**
	 * Can draw texture or square
	 */
	@Override
	public void draw(Graphics g) {
		// texture or if can not read picture
		if (textures || !imageMaker.isReadyPicture()) {
			g.setColor(new Color(0x00ff00));
			g.fillRect(x, y, InterfaceTile.SIZE, InterfaceTile.SIZE);
			g.setColor(new Color(0x000000));
			g.drawRect(x, y, InterfaceTile.SIZE, InterfaceTile.SIZE);

		} else {
			// change color of snake
			if (snake.getSnakeTextures() == SnakeTextures.REDCIRCLE) {
				g.drawImage(imageMaker.getRedbody(), x, y, null);
			} else {
				g.drawImage(imageMaker.getBody(), x, y, null);
			}
		}
	}

}
