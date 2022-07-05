package snake;

import java.util.ArrayList;
import java.util.Random;

public class Grid {
	
	private int width, height;
	private int[][] matrix;
	private ArrayList<Apple> apples; //Not used
	private Random rand;
	private ArrayList<String> openSpaces;
	
	/**
	 * Create an empty grid with dimensions w x h
	 */
	public Grid(int w, int h) {
		
		this.width = w;
		this.height = h;
		
		matrix = new int[w][h];
		fillMatrix(0);
		gridTick(false); //Used to initiate the grid
		
		apples = new ArrayList<>();
		
		rand = new Random();
	}
	
	/**
	 * Spawn snake in position with certain length
	 */
	public void spawnSnake(int x, int y, int length) {
		//First check length fits in play area
		if(x <= 0 || x >= width - 1 ||
		   y <= 0 || y >= height - 1 ||
		   x < length) {
			throw new IllegalArgumentException("Snake spawn location invalid");
		}
		
		for(int i = length; i > 0; i--) {
			setMatrix(x, y, 0 - i);
			x--;
		}
		
	}
	
	
	/**
	 * Spawn a new apple in a random available space
	 */
	public void spawnApple() {
		
		//Points scored per apple
		int score = 1;
		
		int spaces = openSpaces.size();
		
		if(spaces != 0) {
			
			String spawnString = openSpaces.get(rand.nextInt(spaces));
			String[] spawnSplit = spawnString.split(";");
			
			int spawnX = Integer.parseInt(spawnSplit[0]);
			int spawnY = Integer.parseInt(spawnSplit[1]);
			
			apples.add(new Apple(spawnX, spawnY));
			setMatrix(spawnX, spawnY, score);
			
		}
		else {
			//Game Completed
		}
		
	}
	
	/**
	 * Despawn an apple at given coordinates
	 */
	public void despawnApple(int x, int y) {
		if(apples.size() == 0) {
			return;
		}
		
		for(int i = 0; i < apples.size(); i++) {
			if(x == apples.get(i).getX() && y == apples.get(i).getY()) {
				apples.remove(i);
				return;
			}
		}
	}
	
	
	/**
	 * Progress the board by one turn
	 * If an apple is eaten that tick then tail will not lose length
	 */
	public void gridTick(boolean eaten) {
		openSpaces = new ArrayList<>();
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				int mVal = matrix[x][y];
				
				if(mVal < 0 && !eaten) {
					setMatrix(x, y, mVal + 1); //Remove parts of tail
				}
				else if(mVal == 0) {
					openSpaces.add(x + ";" + y); //Updates the empty spaces arrayList
				}
			}
		}
		
	}
	
	/**
	 * Return the int matrix
	 */
	public int[][] getMatrix(){
		return matrix;
	}
	
	/**
	 * Return state of given coordinates
	 */
	public int getCoordinate(int x, int y) {
		return matrix[x][y];
	}
	
	/**
	 * Fills the matrix with a specific number
	 */
	private void fillMatrix(int filler) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				setMatrix(i, j, filler);
			}
		}
	}
	
	/**
	 * Set value of certain point in matrix
	 */
	public void setMatrix(int x, int y, int value) {
		matrix[x][y] = value;
	}
	
}
