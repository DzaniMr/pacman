package pacman.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

import pacman.Direction;
import pacman.Ghost;
import pacman.MazeMap;
import pacman.PacMan;
import pacman.Square;

class GhostTest {

	@Test
	void test() {
		boolean[] passable = {true, true, true, true, false, false, false, false, true, true, true, true, false, false, false, false};
		MazeMap mazeMap = new MazeMap(4, 4, passable);
//		System.out.println(mazeMap.getHeight());
//		System.out.println(mazeMap.getWidth());
		Square square = Square.of(mazeMap, 0, 0);
		Direction direction = Direction.RIGHT;
		
		Ghost ghost = new Ghost(square, direction);
		
		assertEquals(square, ghost.getSquare());
		assertEquals(direction, ghost.getDirection());
		
		Square squareSecond = Square.of(mazeMap, 3, 2);
		Direction directionSecond = Direction.LEFT;
		
		ghost.setSquare(squareSecond);
		ghost.setDirection(directionSecond);
		
		assertNotEquals(square, ghost.getSquare());
		assertNotEquals(direction, ghost.getDirection());
		assertEquals(squareSecond, ghost.getSquare());
		assertEquals(directionSecond, ghost.getDirection());
		Random random = new Random();
		
		int oldColumnIndex = ghost.getSquare().getColumnIndex();
		int oldRowIndex = ghost.getSquare().getRowIndex();
		ghost.move(random);
		int newColumnIndex = ghost.getSquare().getColumnIndex();
		int newRowIndex = ghost.getSquare().getRowIndex();
		assertTrue(newColumnIndex != oldColumnIndex || newRowIndex != oldRowIndex);
		
		Square squarePacman = Square.of(mazeMap, 2, 2);
		PacMan pacMan = new PacMan(3, squarePacman);
		
		ghost.hitBy(pacMan);
		int nbLives = pacMan.getNbLives();
		assertEquals(nbLives, 2);
		
		

		
	}

}


