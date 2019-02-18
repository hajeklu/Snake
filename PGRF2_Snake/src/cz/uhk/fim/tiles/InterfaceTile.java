package cz.uhk.fim.tiles;



import java.awt.Graphics;

/**
 * 
 * @author Hajek 
 * Interface of all tiles
 *
 */
public interface InterfaceTile {

	// size of all tiles
	public static int SIZE = 10;

	public void draw(Graphics g);

	public int getX();

	public void setX(int x);

	public int getY();

	public void setY(int y);

	public void setLocation(int x, int y);
}
