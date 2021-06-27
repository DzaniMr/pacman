package pacman.wormholes.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;
import pacman.Square;
import pacman.wormholes.*;

class WormholeTest {

	@Test
	void test() {
		boolean[] passable = {false, true, true, true, false, false, true, true, true, 
				false, false, true, true, true, false, false, true, true, true, false};
		
		MazeMap mazeMap = new MazeMap(5, 5, passable);
		Square departureSquareLeft = Square.of(mazeMap, 0, 1);
		Square departureSquareRight = Square.of(mazeMap, 0, 3);
		Square arrivalSquareLeft = Square.of(mazeMap, 3, 1);
		Square arrivalSquareRight = Square.of(mazeMap, 3, 3);
		
		DeparturePortal departurePortalLeft = new DeparturePortal(departureSquareLeft);
		DeparturePortal departurePortalRight = new DeparturePortal(departureSquareRight);
		ArrivalPortal arrivalPortalLeft = new ArrivalPortal(arrivalSquareLeft);
		ArrivalPortal arrivalPortalRight = new ArrivalPortal(arrivalSquareRight);
		
		Wormhole wormholeLeft = new Wormhole(departurePortalLeft, arrivalPortalLeft);
		
		assertEquals(departurePortalLeft, wormholeLeft.getDeparturePortal());
		assertEquals(arrivalPortalLeft, wormholeLeft.getArrivalPortal());
		assertEquals(Set.of(wormholeLeft), arrivalPortalLeft.getWormholes());
		assertEquals(Set.of(wormholeLeft), departurePortalLeft.getWormholes());
		
		wormholeLeft.setArrivalPortal(arrivalPortalRight);
		wormholeLeft.setDeparturePortal(departurePortalRight);
		
		assertTrue(arrivalPortalLeft.getWormholes().isEmpty());
		assertTrue(departurePortalLeft.getWormholes().isEmpty());
		assertEquals(Set.of(wormholeLeft), arrivalPortalRight.getWormholes());
		assertEquals(Set.of(wormholeLeft), departurePortalRight.getWormholes());
		
		Wormhole wormholeRight = new Wormhole(departurePortalRight, arrivalPortalLeft);
		assertEquals(Set.of(wormholeRight), arrivalPortalLeft.getWormholes());
		assertTrue(departurePortalLeft.getWormholes().isEmpty());
		assertEquals(Set.of(wormholeLeft), arrivalPortalRight.getWormholes());
		assertEquals(Set.of(wormholeLeft, wormholeRight), departurePortalRight.getWormholes());
		
		wormholeLeft.setArrivalPortal(arrivalPortalRight);
		wormholeLeft.setDeparturePortal(departurePortalRight);
		
		assertEquals(Set.of(wormholeRight), arrivalPortalLeft.getWormholes());
		assertTrue(departurePortalLeft.getWormholes().isEmpty());
		assertEquals(Set.of(wormholeLeft), arrivalPortalRight.getWormholes());
		assertEquals(Set.of(wormholeLeft, wormholeRight), departurePortalRight.getWormholes());
	}

}
