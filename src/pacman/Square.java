package pacman;

import java.util.ArrayList;
import java.util.Arrays;
import java.lang.Math;

/**
 * Each instance of this class represents a position in a maze, specified by a row index and a column index.
 * The top row and the leftmost column have index 0.
 * @invar The rowIndex is non-negative and smaller than the height of the mazeMap
 * 		| 0 <= getRowIndex()  && getRowIndex() < getMazeMap().getHeight()
 * @invar The columnIndex is non-negative and smaller than the width of the mazeMap
 * 		| 0 <= getColumnIndex()  && getColumnIndex() < getMazeMap().getWidth()
 * @immutable
 */
public class Square {
	private int rowIndex;
	private int columnIndex;

	/**
	 * @representationObject
	 * @invar | mazeMap != null
	 */
	private MazeMap mazeMap;
	
	private Square(MazeMap mazeMap, int rowIndex, int columnIndex) {
		this.mazeMap = mazeMap;
		this.rowIndex = rowIndex;
		this.columnIndex= columnIndex;
	}
	
	/**
	 * @post The result is not {@code null}
	 * 		| result != null
	 */
	public MazeMap getMazeMap() {
		return this.mazeMap;
	}
	
	/**
	 * @basic
	 */
	public int getRowIndex() {
		return this.rowIndex;
	}
	
	/**
	 * @basic
	 */
	public int getColumnIndex() {
		return this.columnIndex;
	}
	
	/**
	 * @basic
	 */
	public boolean isPassable() {
		return mazeMap.isPassable(this.rowIndex, this.columnIndex);
	}
	
	/**
	 * Initializes this instance so that it represents a square with a given rowIndex and columnIndex which coordinates can looked up via the given mazeMap.

	 * @throws NullPointerException if the given mazeMap object is null
	 * 		| mazeMap == null
	 * @throws IndexOutOfBoundsException if the given row index is less than zero or not less than the height of the mazeMap.
	 * 		| rowIndex < 0 || rowIndex > mazeMap.getHeight()
	 * @throws IndexOutOfBoundsException if the given column index is less than zero or not less than the width of the mazeMap.
	 * 		| columnIndex < 0 || columnIndex > mazeMap.getWidth()
	 * 
	 * @post | result.getRowIndex() == rowIndex
	 * @post | result.getColumnIndex() == columnIndex
	 * @post | result.getMazeMap().equals(mazeMap)
	 */
	public static Square of(MazeMap mazeMap, int rowIndex, int columnIndex) {
		if (mazeMap == null) {
			throw new NullPointerException("`mazeMap` is null");
		} else if (rowIndex < 0 || rowIndex >= mazeMap.getHeight()) {
			throw new IndexOutOfBoundsException("the `rowIndex` is smaller than 0 or larger or equal to the height of the `mazeMap`");
		} else if (columnIndex < 0 || columnIndex >= mazeMap.getWidth()) {
			throw new IndexOutOfBoundsException("the `columnIndex` is smaller than 0 or larger or equal to the width of the `mazeMap`");
		}
				
		return new Square(mazeMap, rowIndex, columnIndex);
		
	}
	
	/**
	 * Returns this square's neighbor in the given direction.
	 * If this square has no neigbor in the given direction, return the square that is furthest away in the opposite direction. 
	 */
	// No formal documentation required
	public Square getNeighbor(Direction direction) {
		int rowIndex = this.rowIndex;
		int columnIndex = this.columnIndex;
		switch (direction) {
		case RIGHT -> columnIndex = (columnIndex + 1) % mazeMap.getWidth();
		case LEFT -> columnIndex = Math.floorMod(columnIndex - 1, mazeMap.getWidth());
		case DOWN -> rowIndex = (rowIndex + 1) % mazeMap.getHeight();
		case UP -> rowIndex = Math.floorMod(rowIndex - 1, mazeMap.getHeight());
		}
		return Square.of(mazeMap, rowIndex, columnIndex);
	}

	/**
	 * Returns whether this square's neighbor in the given direction is passable.
	 */
	// No formal documentation required
	public boolean canMove(Direction direction) {
		return getNeighbor(direction).isPassable();
	}

	/**
	 * Returns the directions that are different from the given excluded direction and such that the neighbor in that direction is passable.
	 * The returned array shall have no null elements and shall have no duplicates.
	 */
	// No formal documentation required.
	public Direction[] getPassableDirectionsExcept(Direction excludedDirection) {
		Direction[] result = new Direction[4];
		int nbDirections = 0;
		for (Direction direction : Direction.values())
			if (direction != excludedDirection && canMove(direction))
				result[nbDirections++] = direction;
		return Arrays.copyOf(result, nbDirections);
	}
	
	/**
	 * Returns whether the given square refers to the same {@code MazeMap} object and has the same row and column index as this square.
	 * 
	 * @pre Argument {@code other} is not {@code null}
	 * 		| other != null
	 * @post
	 * 		The result is {@code true} if the row index and column index are the same for both square objects
	 * 		and the mazeMap is the same for both the square objects.
	 * 		| result == (
	 * 		|		this.getRowIndex() == other.getRowIndex() &&
	 * 		|		this.getColumnIndex() == other.getColumnIndex() &&
	 * 		|		this.getMazeMap() == other.getMazeMap()
	 * 		| )
	 * 			
	 */
	public boolean equals(Square other) {
		if (other == null)
			throw new IllegalArgumentException("`other` must not be null");
		
		return mazeMap == other.mazeMap && rowIndex == other.rowIndex && columnIndex == other.columnIndex;
	}
	
}