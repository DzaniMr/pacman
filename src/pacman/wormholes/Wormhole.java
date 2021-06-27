package pacman.wormholes;


/**
 * @invar getArrivalPortal() != null
 * @invar getDeparturePortal() != null
 * @invar getArrivalPortal().getWormholes().contains(this)
 * @invar getDeparturePortal().getWormHoles().contains(this)
 *
 */
public class Wormhole {
	/**
	 * @invar | true
	 * @invar | arrivalPortal != null && arrivalPortal.wormholes.contains(this)
	 * @invar | departurePortal != null && departurePortal.wormholes.contains(this) 
	 * @peerObject
	 */
	ArrivalPortal arrivalPortal;
	
	/**
	 * @peerObject
	 */
	DeparturePortal departurePortal;
	
	/**
	 * @throws IllegalArgumentException | departurePortal == null
	 * @throws IllegalArgumentException | arrivalPortal == null
	 * @post | departurePortal.equals(getDeparturePortal())
	 * @post | arrivalPortal.equals(getArrivalPortal())
	 * @post | getDeparturePortal().getWormholes().contains(this)
	 * @post | getArrivalPortal().getWormholes().contains(this)
	 */
	public Wormhole(DeparturePortal departurePortal, ArrivalPortal arrivalPortal) {
		if (departurePortal == null)
			throw new IllegalArgumentException("departurePortal is null");
		if (arrivalPortal == null)
			throw new IllegalArgumentException("ArrivalPortal is null");
		
		this.arrivalPortal = arrivalPortal;
		this.departurePortal = departurePortal;
		arrivalPortal.wormholes.add(this);
		departurePortal.wormholes.add(this);
	}

	/**
	 * 
	 * @basic
	 */
	public ArrivalPortal getArrivalPortal() {
		return arrivalPortal;
	}

	/**
	 * @throws IllegalArgumentException | arrivalPortal == null
	 * @mutates_properties | getArrivalPortal(), arrivalPortal.getWormholes()
	 * @post | getArrivalPortal() == arrivalPortal
	 * @post | arrivalPortal == old(getArrivalPortal()) || !old(getArrivalPortal()).getWormholes().contains(this)
	 * @post | getArrivalPortal().getWormholes().contains(this)
	 */
	public void setArrivalPortal(ArrivalPortal arrivalPortal) {
		if (arrivalPortal == null)
			throw new IllegalArgumentException("arrivalPortal is null");
		
		this.arrivalPortal.wormholes.remove(this);
		this.arrivalPortal = arrivalPortal;
		this.arrivalPortal.wormholes.add(this);
		
	}

	/**
	 * 
	 * @basic
	 */
	public DeparturePortal getDeparturePortal() {
		return departurePortal;
	}

	/**
	 * @throws IllegalArgumentException | departurePortal == null
	 * @mutates_properties | getDeparturePortal(), departurePortal.getWormholes()
	 * @post | getDeparturePortal() == departurePortal
	 * @post | departurePortal == old(getDeparturePortal()) || !old(getDeparturePortal()).getWormholes().contains(this)
	 * @post | getDeparturePortal().getWormholes().contains(this)
	 */
	public void setDeparturePortal(DeparturePortal departurePortal) {
		if (departurePortal == null)
			throw new IllegalArgumentException("departurePortal is null");
		
		this.departurePortal.wormholes.remove(this);
		this.departurePortal = departurePortal;
		this.departurePortal.wormholes.add(this);
	}
}
