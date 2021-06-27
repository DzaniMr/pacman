package pacman;

/**
 * Each instance of this class represents a dot (a piece of food for Pac-Man) in a Pac-Man maze.
 * 
 * @invar | getSquare() != null
 * @immutable
 */
public class Dot extends FoodItem{
	/**
	 * @representationObject
	 */
	private Square square;
	final private int relative_size = 1;
	/**
	 * basic
	 */
	public Square getSquare() {
		return square;
	}
	
	public int getSize() {
		return 1;
	}
	
	/**
	 * @throws NullPointerException | square == null
	 * @post This dot's square equals the given square.
	 * 		| getSquare().equals(square)
	 */
	public Dot(Square square) {
		super();
		if (square == null)
			throw new IllegalArgumentException("`square` is null");
		this.square = square;
	}
	
	public void foundPowerPellet(Ghost[] ghosts) {
		
	};


}
