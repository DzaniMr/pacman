package pacman;

/**
 * Each instance of this class represents the player-controlled Pac-Man character in a Pac-Man maze.
 * 
 * @invar | getSquare() != null
 * @invar | getNbLives() >= 0
 */
public class PacMan {
	/**
	 * @representationObject
	 */
	private Square square;
	private int nbLives;
	
	/**
	 * @basic
	 */
	public Square getSquare() {
		return square;
	}
	
	/**
	 * @basic
	 */
	public int getNbLives() {
		return nbLives;
	}

	/**
	 * Initializes this instance so that it can represent PacMan.
	 * @throws NullPointerException if the given Square object is null
	 * 		| square == null
	 * @post | getNbLives() == nbLives
	 * @post | getSquare().equals(square)
	 */
	public PacMan(int nbLives, Square square) {
		if (square == null) {
			throw new NullPointerException("`square` is null");
		}
		
		this.nbLives = nbLives;
		this.square = square;
	}
	
	/**
	 * 
	 * @mutates | this
	 */
	public void setSquare(Square square) {
		this.square = square;
	}
	
	/**
	 * Decreases this Pac-Man character's number of lives by one.
	 * 
	 * @pre getNbLives() > 0
	 * @post getNbLives() == old(getNbLives) -1 
	 */
	public void die() {
		if (this.nbLives > 0) {
			this.nbLives = this.nbLives -1;
		}
	}
}
