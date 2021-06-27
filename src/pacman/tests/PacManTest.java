package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pacman.Dot;
import pacman.MazeMap;
import pacman.PacMan;
import pacman.Square;

class PacManTest {

	@Test
	void test() {
		boolean[] passable = {true, true, true, true, false, false, false, false, true, true, true, true, false, false, false, false};
		MazeMap mazeMap = new MazeMap(4, 4, passable);
		Square square = Square.of(mazeMap, 2, 2);
		
		PacMan pacMan = new PacMan(3, square);
		
		assertEquals(square, pacMan.getSquare());
		assertEquals(3, pacMan.getNbLives());
		
		pacMan.die();
		assertEquals(2, pacMan.getNbLives());
		
		Square squareSecond = Square.of(mazeMap, 1, 3);
		
		pacMan.setSquare(squareSecond);
		
		assertNotEquals(square, pacMan.getSquare());
		assertEquals(squareSecond, pacMan.getSquare());
	}
}


