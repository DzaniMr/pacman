package pacman;

import java.util.Random;

/**
 * Each instance of this class represents a ghost in a Pac-Man maze.
 * 
 * @invar | getSquare() != null
 * @invar | getDirection() != null
 * 
 */
public class Ghost {
	/**
	 * @representationObject
	 */
	private Square square;
	private GhostState ghostState = new RegularGhostState();
	private int initial_delay = 1;
	private final Square finalSquare;
	
	/**
	 * @representationObject
	 */
	private Direction direction;
	
	/**
	 * @basic
	 */
	public Square getSquare() {
		return this.square;
	}
	
	public Square getFinalSquare() {
		return this.finalSquare;
	}
	
	public boolean isVulnerable() {
		if (ghostState instanceof RegularGhostState) {
			return false;
		} else {
			return true;
		}
	}
	
	public void pacManAtePowerPellet() {
		ghostState = new VulnerableGhostState();
		switch (direction) {
		case RIGHT -> direction = direction.LEFT;
		case LEFT -> direction = direction.RIGHT;
		case DOWN -> direction = direction.UP;
		case UP -> direction = direction.DOWN;
		}
		
	}
	
	/**
	 * Returns the direction in which this ghost will preferably move next.
	 * @basic
	 */
	public Direction getDirection() {
		return this.direction;
	}
	
	/**
	 * Initializes this instance so that it can represent a ghost.
	 * @throws NullPointerException if the given Square object is null
	 * 		 | square == null
	 * @throws NullPointerException if the given Direction object is null
	 * 		 | direction == null
	 * @post | getSquare().equals(square)
	 * @post | getDirection().equals(direction)
	 */
	public Ghost(Square square, Direction direction) {
		if (square == null) {
			throw new NullPointerException("`square` is null");
		} else if (direction == null) {
			throw new NullPointerException("`direction` is null");
		}
		
		this.square = square;
		this.direction = direction;
		this.finalSquare = square;
	}
	
	/**
	 * @mutates | this
	 * @pre | square != null
	 * @post | getSquare() == square
	 */
	public void setSquare(Square square) {
		if (square == null)
			throw new IllegalArgumentException("`square` is null");
		
		this.square = square;
	}
		
	/**
	 * @mutates | this
	 * @pre | direction != null
	 * @post | getDirection() == direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
		
	
	private static int MOVE_FORWARD_PREFERENCE = 10;
	
	// No formal document required
	public Direction chooseNextMoveDirection(Random random) {
		int moveForwardPreference = getSquare().canMove(getDirection()) ? MOVE_FORWARD_PREFERENCE : 0;
		Direction[] passableDirections = getSquare().getPassableDirectionsExcept(getDirection().getOpposite());
		if (passableDirections.length == 0)
			return getDirection().getOpposite();
		int result = random.nextInt(moveForwardPreference + passableDirections.length);
		if (result < moveForwardPreference)
			return getDirection();
		return passableDirections[result - moveForwardPreference];
	}
	
	// No formal document required
	public void reallyMove(Random random) {
		setDirection(chooseNextMoveDirection(random));
		setSquare(getSquare().getNeighbor(getDirection()));
	}
	
	public void move(Random random) {
		ghostState = ghostState.move(this, random);
	}
	
	public void hitBy(PacMan pacMan) {
		ghostState = ghostState.hitBy(this, pacMan);
	}
}
