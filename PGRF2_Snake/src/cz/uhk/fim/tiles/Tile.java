package cz.uhk.fim.tiles;



/**
 * 
 * @author Hajek Abstract tile for extending next tile
 *
 */
public abstract class Tile implements InterfaceTile {

	protected int x;
	protected int y;
	public static boolean textures;

	public Tile(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// equals for collision
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj instanceof Tile) {
			Tile t = (Tile) obj;
			if (t.x == x && t.y == y) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Tile() {
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}
}
