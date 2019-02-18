package cz.uhk.fim.tiles;

import java.awt.Color;
import java.awt.Graphics;

import cz.uhk.fim.model.ImageMaker;
/**¨
 * 
 * @author Hajek
 * Tile of Malus
 *
 */
public class MalusTile extends Tile {

	private ImageMaker imageMaker;

	public MalusTile(int x, int y) {
		this.x = x;
		this.y = y;
		imageMaker = ImageMaker.getInstance();
	}

	@Override
	public void draw(Graphics g) {
		// draw texture or square
		if (textures || !imageMaker.isReadyPicture()) {
			g.setColor(new Color(0x00ff27));
			g.fillRect(x, y, InterfaceTile.SIZE, InterfaceTile.SIZE);
		} else {
			g.drawImage(imageMaker.getMalus(), x, y, null);
		}
	}

}
