package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.MazeMap;
import pacman.PacMan;
import pacman.Square;

class SquareTest {

	@Test
	void test() {
		boolean[] passable = {true, true, true, true, false, false, false, false, true, true, true, true, false, false, false, false};
		MazeMap mazeMap = new MazeMap(4, 4, passable);
		Square square = Square.of(mazeMap, 0, 1);
		
		assertEquals(0, square.getRowIndex());
		assertEquals(1, square.getColumnIndex());
		assertEquals(mazeMap, square.getMazeMap());
		assertEquals(true, square.isPassable());
		
		Square neighbor = square.getNeighbor(Direction.LEFT);
		assertEquals(0, neighbor.getRowIndex());
		assertEquals(0, neighbor.getColumnIndex());
		assertEquals(true, neighbor.isPassable());
		
		assertFalse(square.equals(neighbor));
		assertTrue(square.equals(square));
				
		Direction[] directionArray = square.getPassableDirectionsExcept(Direction.DOWN);
		assertArrayEquals(directionArray, new Direction[]{Direction.RIGHT, Direction.LEFT, Direction.UP});

		
	
	}
}

