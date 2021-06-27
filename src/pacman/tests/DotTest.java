package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.MazeMap;
import pacman.Square;
import pacman.Dot;

class DotTest {

	@Test
	void test() {
		boolean[] passable = {true, true, true, true, false, false, false, false, true, true, true, true, false, false, false, false};
		MazeMap mazeMap = new MazeMap(4, 4, passable);
		Square square = Square.of(mazeMap, 1, 1);
		
//		Dot dot = new Dot(square);
//		
//		assertEquals(square, dot.getSquare());
	}

}

