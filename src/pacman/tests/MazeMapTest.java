package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.Ghost;
import pacman.MazeMap;
import pacman.Square;

class MazeMapTest {

	@Test
	void test() {
		boolean[] passable = {true, true, true, true, false, false, false, false, true, true, true, true, false, false, false, false};
		MazeMap mazeMap = new MazeMap(4, 4, passable);
		
		assertEquals(4, mazeMap.getWidth());
		assertEquals(4, mazeMap.getHeight());
		assertEquals(true, mazeMap.isPassable(0, 0));
	}

}


