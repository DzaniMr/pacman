package pacman;

public abstract class FoodItem {
	public abstract int getSize();

	public abstract Square getSquare();
	
	public abstract void foundPowerPellet(Ghost[] ghosts);
}
