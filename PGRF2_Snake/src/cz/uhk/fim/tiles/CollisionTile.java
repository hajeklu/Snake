package cz.uhk.fim.tiles;

import java.awt.Graphics;

/**
 * Class onlo for collision
 */
public class CollisionTile extends Tile {

	public CollisionTile(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(Graphics g) {
		// tile whitout draw... only abstract on Collision
	}

}
