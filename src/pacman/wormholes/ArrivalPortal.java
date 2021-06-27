package pacman.wormholes;
import java.util.HashSet;
import java.util.Set;

import pacman.Square;

/**
 * @invar | getSquare() != null
 * @invar | getWormholes() != null
 * @invar | getWormholes().stream().allMatch(w -> w != null && w.getArrivalPortal() == this)
 *
 */
public class ArrivalPortal {
	/**
	 *@invar | wormholes.stream().allMatch(w -> w != null && w.arrivalPortal == this)
	 *@representationObject
	 */
	private Square square;
	/**
	 * @representationObject
	 * @peerObjects
	 */
	Set<Wormhole> wormholes = new HashSet<>();
	
	/**
	 * 
	 * @throws IllegalArgumentException | square == null
	 * @post | square == getSquare()
	 */
	public ArrivalPortal(Square square) {
		if (square == null)
			throw new IllegalArgumentException("Square is null");
		this.square = square;
	}

	/**
	 * 
	 * @basic
	 */
	public Square getSquare() {
		return square;
	}

	/**
	 * @basic
	 * @creates | result
	 */
	public Set<Wormhole> getWormholes() {
		return Set.copyOf(wormholes);
	}
}
