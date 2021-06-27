package pacman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import pacman.wormholes.ArrivalPortal;
import pacman.wormholes.DeparturePortal;

public class MazeDescriptions {
	
	private MazeDescriptions() { throw new AssertionError("This class is not intended to be instantiated"); }
	
	public static Maze createMazeFromDescription(Random random, String description) {
		String[] lines = description.trim().split("\n");
		
		int height = lines.length;
		int width = lines[0].length();
		
		boolean[] passable = new boolean[height * width];
		for (int i = 0; i < passable.length; i++)
			passable[i] = true;
		
		int nbFoodItems = 0;
		FoodItem[] foodItems = new FoodItem[width * height];
		
		int nbGhosts = 0;
		Ghost[] ghosts = new Ghost[width * height];
		
		int nbArrivalPortals = 0;
		List<ArrivalPortal> arrivalPortals = new ArrayList<ArrivalPortal>();
				
		int nbDeparturePortals = 0;
		List<DeparturePortal> departurePortals = new ArrayList<DeparturePortal>();
		
		for (int row = 0; row < lines.length; row++) {
			String line = lines[row];
			for (int column = 0; column < line.length(); column++) {
				char c = line.charAt(column);
				if (c == '#')
					passable[row * width + column] = false;
			}
		}
		MazeMap map = new MazeMap(width, height, passable);
		
		PacMan pacMan = null;
		for (int row = 0; row < lines.length; row++) {
			String line = lines[row];
			for (int column = 0; column < line.length(); column++) {
				char c = line.charAt(column);
				switch (c) {
				case ' ' -> {}
				case 'A' -> arrivalPortals.add(new ArrivalPortal(Square.of(map, row, column)));
				case 'D' -> departurePortals.add(new DeparturePortal(Square.of(map, row, column)));
				case '#' -> {}
				case '.' -> foodItems[nbFoodItems++] = new Dot(Square.of(map, row, column));
				case 'G' -> ghosts[nbGhosts++] = new Ghost(Square.of(map, row, column), Direction.values()[random.nextInt(Direction.values().length)]);
				case 'P' -> {
					if (pacMan != null)
						throw new IllegalArgumentException("Maze description contains multiple P characters");
					pacMan = new PacMan(3, Square.of(map, row, column));
				}
				case 'p' -> foodItems[nbFoodItems++] = new PowerPellet(Square.of(map, row, column));
				default -> throw new IllegalArgumentException("Invalid character in maze description: " + c);
				}
			}
		}
		
		if (pacMan == null)
			throw new IllegalArgumentException("Maze description does not contain a P character");
		
		return new Maze(random, map, pacMan, Arrays.copyOf(ghosts, nbGhosts), Arrays.copyOf(foodItems, nbFoodItems), 
				List.copyOf(departurePortals), List.copyOf(arrivalPortals));
	}

}
