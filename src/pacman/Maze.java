package pacman;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import pacman.wormholes.*;

public class Maze {
	
	private Random random;
	private MazeMap map;
	private PacMan pacMan;
	private Ghost[] ghosts;
	private FoodItem[] foodItems;
//	private DeparturePortal[] departurePortals;
//	private ArrivalPortal[] arrivalPortals;
//	private Wormhole[] wormholes = new Wormhole[0];
	private List<Wormhole> wormholes =new ArrayList<Wormhole>();  
	private List<DeparturePortal> departurePortals;
	private List<ArrivalPortal> arrivalPortals;
	
	public MazeMap getMap() { return map; }
	
	public PacMan getPacMan() { return pacMan; }
	
	public Ghost[] getGhosts() { return ghosts.clone(); }
	
	public FoodItem[] getFoodItems() { return foodItems.clone(); }
	
	public Maze(Random random, MazeMap map, PacMan pacMan, Ghost[] ghosts, FoodItem[] foodItems, 
			List<DeparturePortal> departurePortals, List<ArrivalPortal> arrivalPortals) {
		this.random = random;
		this.map = map;
		this.pacMan = pacMan;
		this.ghosts = ghosts.clone();
		this.foodItems = foodItems.clone();
		this.departurePortals = List.copyOf(departurePortals);
		this.arrivalPortals = List.copyOf(arrivalPortals);
	}
	
	public ArrivalPortal[] getArrivalPortals() {
		ArrivalPortal[] arrivalPortalsArray = this.arrivalPortals.toArray(new ArrivalPortal[this.arrivalPortals.size()]);
		return arrivalPortalsArray;
	}
	                         
	public DeparturePortal[] getDeparturePortals() {
		DeparturePortal[] departurePortalsArray = this.departurePortals.toArray(new DeparturePortal[this.departurePortals.size()]);
		return departurePortalsArray;
	}
	public Wormhole[] getWormholes() {
		Wormhole[] wormholesArray = wormholes.toArray(new Wormhole[wormholes.size()]);
		return wormholesArray;
	}
	
	public void addWormhole(Wormhole wormhole) {
		if (!arrivalPortals.contains(wormhole.getArrivalPortal())) {
			throw new IllegalArgumentException("ArrivalPortal of the wormhole is not contained in the array of the arrivalPortals");
		}
		
		if (!departurePortals.contains(wormhole.getDeparturePortal())) {
			throw new IllegalArgumentException("Departure of the wormhole is not contained in the array of the arrivalPortals");
		}
		this.wormholes.add(wormhole);
	}
	
	public boolean isCompleted() {
		return foodItems.length == 0;
	}
	
	private void checkPacManDamage() {
		for (Ghost ghost : ghosts)
			if (ghost.getSquare().equals(pacMan.getSquare()))
				ghost.hitBy(pacMan);
	}
	
	public void moveGhosts() {
		for (Ghost ghost : ghosts)
			ghost.move(random);
		checkPacManDamage();
	}
	
	private void removeFoodItemAtIndex(int index) {
		FoodItem[] newFoodItems = new FoodItem[foodItems.length - 1];
		System.arraycopy(foodItems, 0, newFoodItems, 0, index);
		System.arraycopy(foodItems, index + 1, newFoodItems, index, newFoodItems.length - index);
		foodItems = newFoodItems;
	}
	
	private void removeFoodItemAtSquare(Square square) {
		for (int i = 0; i < foodItems.length; i++) {
			if (foodItems[i].getSquare().equals(square)) {
				foodItems[i].foundPowerPellet(ghosts);
				removeFoodItemAtIndex(i);
				
				return;
			}
		}
	}
	
	public void movePacMan(Direction direction) {		
		Square newSquare = pacMan.getSquare().getNeighbor(direction);
		if (newSquare.isPassable()) {
			for (int i = 0; i < departurePortals.size(); i++) {
				if (newSquare.equals(departurePortals.get(i).getSquare())){
					int length = departurePortals.get(i).getWormholes().size();
					if (length > 0) {
					int randomNumber = random.nextInt(departurePortals.get(i).getWormholes().size());
					int index = 0;
					for (Wormhole wormhole:departurePortals.get(i).getWormholes()) {
						if (randomNumber == index) {
							pacMan.setSquare(wormhole.getDeparturePortal().getSquare());
							checkPacManDamage();
							Square arrivalSquare = wormhole.getArrivalPortal().getSquare();
							pacMan.setSquare(arrivalSquare);
							removeFoodItemAtSquare(newSquare);
							checkPacManDamage();
							return;
						}
						index++;
					}
					}
				}
			}
			pacMan.setSquare(newSquare);
			removeFoodItemAtSquare(newSquare);
			checkPacManDamage();
		}
	}
	
}
