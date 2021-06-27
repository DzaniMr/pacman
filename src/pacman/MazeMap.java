package pacman;

import java.util.Arrays;
//* @invar | Arrays.stream(passable).allMatch(cell -> cell != null && cell.length == width *  height)
/**
 * Each instance of this class represents a maze layout, specifying the width and height of the maze
 * and, for each position in the maze, whether it is passable or not.
 * 
 * @immutable
 * @invar | 1 <= getHeight()
 */
public class MazeMap {
	/**
	 * @invar | passable != null
	 * @invar | 1 <= passable.length
	 * @invar | 1 <= width
	 * @invar | passable.length % width == 0

	 */
	private final int width;
	private final int height;
	/**
	 * @representationObject
	 */
	private final boolean[] passable;
	
	/**
	 * Returns the width (i.e. the number of columns) of this maze map.
	 * @basic
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * Returns the height (i.e. the number of rows) of this maze map.
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Returns whether the square in this maze at row index {@code row} and column index {@code column} is passable.
	 * The square in the top-left corner of the maze has row index 0 and column index 0.
	 * 
	 * @basic
	 * 
	 * @throws IndexOutOfBoundsException if the given height is less than zero.
	 * 		| rowIndex < 0 || rowIndex > this.getHeight()
	 * @throws IndexOutOfBoundsException if the given width is less than zero.
	 * 		| columnIndex < 0 || columnIndex < this.getWidth()
	 */
	public boolean isPassable(int rowIndex, int columnIndex) {
		if (rowIndex < 0 || rowIndex > this.getHeight()) {
			throw new IndexOutOfBoundsException();
		} else if (columnIndex < 0 || columnIndex > this.getWidth()) {
			throw new IndexOutOfBoundsException();
		}
		
		return passable[rowIndex * width + columnIndex];
	}
	
	/**
	 * Initializes this object so that it represents a maze layout with the given width, height, and
	 * passable positions. The passable positions are given in row-major order (i.e. the first {@code width} elements
	 * of {@code passable} specify the passability of the maze positions in the first row of the maze).
	 * 
	 * @throws NullPointerException | passable == null
	 * @throws IndexOutOfBoundsException if the given height is less than zero.
	 * 		| height < 1
	 * @throws IndexOutOfBoundsException if the given width is less than zero.
	 * 		| width < 1
	 * 
	 * @post | getHeight() == height
	 * @post | getWidth() == width
	 *  
	 */
	public MazeMap(int width, int height, boolean[] passable) {
		if (passable == null) {
			throw new NullPointerException();
		} else if (height < 0) {
			throw new IndexOutOfBoundsException();
		} else if (width < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		this.width = width;
		this.height = height;
		this.passable= passable.clone();
		}
}