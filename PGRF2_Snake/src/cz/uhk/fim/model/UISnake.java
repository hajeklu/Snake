package cz.uhk.fim.model;

import java.util.ArrayList;
import java.util.List;

import cz.uhk.fim.tiles.BodyTile;
import cz.uhk.fim.tiles.BonusTile;
import cz.uhk.fim.tiles.CollisionTile;
import cz.uhk.fim.tiles.HeadTile;
import cz.uhk.fim.tiles.InterfaceTile;
import cz.uhk.fim.tiles.Tile;

/**
 * 
 * Class npc snake and his direction
 */
public class UISnake extends Snake {
	private boolean collision = false;

	/**
	 * Set start direction and pozicion
	 */
	public UISnake() {
		snakeTextures = SnakeTextures.REDCIRCLE;
		direction = Direction.UP;
		head = new HeadTile(10, 530, direction, this);
		lastTile = new BodyTile(800, 800, this);
	}

	/**
	 * He set on the basis of position, himself, the opponent and the bonus
	 * determine the next direction so that there is no collision
	 */
	public void setNextDirectionAndTick(Snake enemy, BonusTile bonusTile) {
		collision = false;

		List<Tile> nextTilesStraight = new ArrayList<>();
		List<Tile> nextTilesLeft = new ArrayList<>();
		List<Tile> nextTilesRight = new ArrayList<>();
		/**
		 * every ciclus for one side... better that one ciclus for all sides..
		 */
		switch (direction) {
		case DOWN:
			// generate 3 straight and 2 on sides
			for (int i = 1; i < 3; i++) {
				nextTilesStraight
						.add(new CollisionTile(Math.round(head.getX()), (head.getY()) + InterfaceTile.SIZE * i));
			}
			for (int i = 1; i < 2; i++) {
				nextTilesLeft.add(new CollisionTile(head.getX() + InterfaceTile.SIZE * i, head.getY()));
			}
			for (int i = 1; i < 2; i++) {
				nextTilesRight.add(new CollisionTile(head.getX() - InterfaceTile.SIZE * i, head.getY()));
			}
			break;
		case UP:
			// generate 3 straight and 2 on sides
			for (int i = 1; i < 3; i++) {
				nextTilesStraight.add(new CollisionTile(head.getX(), head.getY() - InterfaceTile.SIZE * i));
			}
			for (int i = 1; i < 2; i++) {
				nextTilesLeft.add(new CollisionTile(head.getX() - InterfaceTile.SIZE * i, head.getY()));
			}
			for (int i = 1; i < 2; i++) {
				nextTilesRight.add(new CollisionTile(head.getX() + InterfaceTile.SIZE * i, head.getY()));
			}
			break;
		case LEFT:
			// generate 3 straight and 2 on sides
			for (int i = 1; i < 3; i++) {
				nextTilesStraight.add(new CollisionTile(head.getX() - InterfaceTile.SIZE * i, head.getY()));
			}
			for (int i = 1; i < 2; i++) {
				nextTilesRight.add(new CollisionTile(Math.round(head.getX()), (head.getY()) - InterfaceTile.SIZE * i));
			}
			for (int i = 1; i < 2; i++) {
				nextTilesLeft.add(new CollisionTile(Math.round(head.getX()), (head.getY()) + InterfaceTile.SIZE * i));
			}
			break;
		case RIGHT:
			// generate 3 straight and 2 on sides
			for (int i = 1; i < 3; i++) {
				nextTilesStraight.add(new CollisionTile(head.getX() + InterfaceTile.SIZE * i, head.getY()));
			}
			for (int i = 1; i < 2; i++) {
				nextTilesRight.add(new CollisionTile(Math.round(head.getX()), (head.getY()) + InterfaceTile.SIZE * i));
			}
			for (int i = 1; i < 2; i++) {
				nextTilesLeft.add(new CollisionTile(Math.round(head.getX()), (head.getY()) - InterfaceTile.SIZE * i));
			}
			break;
		}
		// next step colison
		collision = collisonCheck(nextTilesStraight, enemy);
		// right tiles colision
		boolean colisonRight = collisonCheck(nextTilesRight, enemy);
		// left ties colision
		boolean colisonLeft = collisonCheck(nextTilesLeft, enemy);

		// if not colision go for the bonus
		if (!collision) {
			int x = head.getX() - bonusTile.getX();
			int y = head.getY() - bonusTile.getY();
			// can change direction if not 180 degree
			if (x > 0 && direction != Direction.RIGHT) {
				// can change direction if not colision
				if (direction == Direction.UP && !colisonLeft || direction == Direction.DOWN && !colisonRight) {
					direction = Direction.LEFT;
				}
				// can change direction if not 180 degree
			} else if (x < 0 && direction != Direction.LEFT) {
				// can change direction if not colision
				if (direction == Direction.UP && !colisonRight || direction == Direction.DOWN && !colisonLeft) {
					direction = Direction.RIGHT;
				}
				// can change direction if not 180 degree
			} else if (y < 0 && direction != Direction.UP) {
				// can change direction if not colision
				if (direction == Direction.RIGHT && !colisonRight || direction == Direction.LEFT && !colisonLeft) {
					direction = Direction.DOWN;
				}
				// can change direction if not 180 degree
			} else if (y > 0 && direction != Direction.DOWN) {
				// can change direction if not colision
				if (direction == Direction.RIGHT && !colisonLeft || direction == Direction.LEFT && !colisonRight) {
					direction = Direction.UP;
				}
			}
		}

		// if colision try fix it
		if (collision) {
			// by direction can try fix collision
			switch (direction) {
			case DOWN:
				if (!collisonCheck(nextTilesLeft, enemy)) {
					direction = Direction.RIGHT;
				} else if (!collisonCheck(nextTilesRight, enemy)) {
					System.out.println(2);
					direction = Direction.LEFT;
				}
				break;
			case UP:
				if (!collisonCheck(nextTilesRight, enemy)) {
					direction = Direction.RIGHT;
				} else if (!collisonCheck(nextTilesLeft, enemy)) {
					System.out.println(4);
					direction = Direction.LEFT;
				}
				break;
			case LEFT:
				if (!collisonCheck(nextTilesRight, enemy)) {
					direction = Direction.UP;
				} else if (!collisonCheck(nextTilesLeft, enemy)) {
					System.out.println(6);
					direction = Direction.DOWN;
				}
				break;
			case RIGHT:
				if (!collisonCheck(nextTilesRight, enemy)) {
					direction = Direction.DOWN;
				} else if (!collisonCheck(nextTilesLeft, enemy)) {
					direction = Direction.UP;
				}
				break;
			}
		}

		/**
		 * and finally tick
		 */
		tick();
	}

	/**
	 * check for collision whit himself and enemy
	 */
	private boolean collisonCheck(List<Tile> nextTiles, Snake enemy) {
		// check himself
		for (Tile tile : snakeTiles) {
			for (Tile t : nextTiles) {
				if (tile.equals(t)) {
					return true;
				}
			}
		}
		// check enemy
		for (Tile tile : enemy.getSnakeTiles()) {
			for (Tile t : nextTiles) {
				if (tile.equals(t)) {
					return true;
				}
			}
		}
		return false;
	}

}
