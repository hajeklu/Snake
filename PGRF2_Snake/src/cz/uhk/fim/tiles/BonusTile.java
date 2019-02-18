package cz.uhk.fim.tiles;

import java.awt.Color;
import java.awt.Graphics;

import cz.uhk.fim.model.ImageMaker;

/**
 * 
 * @author Hajek
 * Tile of bonus
 *
 */
public class BonusTile extends Tile {

	private ImageMaker imageMaker;

	public BonusTile(int x, int y) {
		this.x = x;
		this.y = y;
		imageMaker = ImageMaker.getInstance();
	}

	@Override
	public void draw(Graphics g) {
		// texture or if can not read picture
		if (textures || !imageMaker.isReadyPicture()) {
			g.setColor(new Color(0x0000ff));
			g.fillRect(x, y, InterfaceTile.SIZE, InterfaceTile.SIZE);

		} else {
			g.drawImage(imageMaker.getBonus(), x, y, null);
		}
	}

}
