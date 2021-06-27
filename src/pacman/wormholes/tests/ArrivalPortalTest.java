package pacman.wormholes.tests;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;
import pacman.Square;
import pacman.wormholes.*;

class ArrivalPortalTest {

	@Test
	void test() {
		boolean[] passable = {false, true, true, false, false, true, true, false, false, true, true, false, false, true, true, true};
		MazeMap mazeMap = new MazeMap(4, 4, passable);
		Square square = Square.of(mazeMap, 0, 1);
		ArrivalPortal arrivalPortal = new ArrivalPortal(square);
		assertEquals(square, arrivalPortal.getSquare());
		assertTrue(arrivalPortal.getWormholes().isEmpty());
	}
}

