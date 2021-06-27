package pacman;

public class PowerPellet extends FoodItem {
	/**
	 * @representationObject
	 */
	private Square square;
	
	/**
	 * basic
	 */
	public Square getSquare() {
		return square;
	}
	
	public int getSize() {
		return 2;
	}
	
	/**
	 * @throws NullPointerException | square == null
	 * @post This dot's square equals the given square.
	 * 		| getSquare().equals(square)
	 */
	public PowerPellet(Square square) {
		super();
		if (square == null) {
			throw new NullPointerException();
		}
		this.square = square;
	}
	
	public void foundPowerPellet(Ghost[] ghosts) {
		for (Ghost ghost : ghosts)
			ghost.pacManAtePowerPellet();
	}
}
