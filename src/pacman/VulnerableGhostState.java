package pacman;

import java.util.Random;

public class VulnerableGhostState extends GhostState{
	private int initialDelay = 1;
	private int movements = 0;
	private int count = 1;
	public GhostState move(Ghost ghost, Random random) {
		if (count != 12) {
		count++;
		if (this.initialDelay == 1 && this.movements < 6) {
			this.initialDelay = 0;
			return this;
		} else {
			this.initialDelay = 1;
			this.movements = this.movements + 1;
			ghost.reallyMove(random);
			return this;
		}} else {
			ghost.reallyMove(random);
			return new RegularGhostState();
		}
	}
	
	public GhostState hitBy(Ghost ghost, PacMan pacMan) {
		ghost.setSquare(ghost.getFinalSquare());
		
		return new RegularGhostState();
	}
	
	
}
