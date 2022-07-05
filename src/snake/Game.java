package snake;

public class Game{
	
	Grid grid;
	
	private boolean finished = false;
	
	//Snake related variables
	private int headX, headY; //Initial position of the snake
	private int nextX, nextY;
	private int score;
	private Direction lastMove;
	private Direction nextMove;
	private int GRID_X, GRID_Y;
	
	
	//**           SETTINGS          **//
	public static final int STARTING_LENGTH = 4;                             //Initial snake length
	public static final Direction STARTING_MOVE = Direction.RIGHT;           //Starting direction of the snake
	public static final int STARTING_X = 6;                                  //Initial x of head of snake
	public static final int STARTING_Y = 6;								  //Initial y of head of snake
	
	
	
	public Game(int gridx, int gridy){
		
		this.GRID_X = gridx;
		this.GRID_Y = gridy;
		initGame();
		
	}
	
	public void initGame() {
		//Initialise variables
		finished = false;
		headX = STARTING_X;
		headY = STARTING_Y;
		nextX = headX;
		nextY = headY;
		score = STARTING_LENGTH;
		nextMove = STARTING_MOVE;
		
		grid = new Grid(GRID_X, GRID_Y);
		grid.spawnSnake(headX, headY, score);
		
		grid.spawnApple();
	}
	
	public void tick() {
		
		//Should the game go through an update cycle
		//if(currentTime >= startTime + (tickCount * tickTime)) {
			
		if(!finished) {
			int nextPos = getNextPos();
			if(nextPos < 0){
				finished = true;
				return;
			}
			
			grid.gridTick(nextPos > 0);
			score += nextPos;
			moveSnake();
			
			if(nextPos > 0) {
				grid.despawnApple(nextX, nextY);
				grid.spawnApple();
			}
			
		}
		
	}
	
	/**
	 * Returns the value of the next position of the snake
	 * Returns -1 if the snake exits the grid
	 */
	private int getNextPos() {
		
		nextX += nextMove.getX();
		nextY += nextMove.getY();
		
		if(nextX < 0 || nextX == GRID_X ||
		   nextY < 0 || nextY == GRID_Y) { //Snake hits edge of play area
			return -1; //End the game
		}else {
			return grid.getCoordinate(nextX, nextY);
		}
		
	}
	
	/**
	 * Moves the snake head
	 */
	private void moveSnake() {
		grid.setMatrix(nextX, nextY, 0 - score);
		lastMove = nextMove;
		headX = nextX;
		headY = nextY;
	}

	
	/**
	 * Returns the grid matrix
	 */
	public int[][] getMatrix() {
		return grid.getMatrix();
	}
	
	/**
	 * Returns the score
	 * @return
	 */
	public int getScore() {
		return score;
	}
	
	public boolean isFinished() {
		return finished;
	}
	
	
	/**
	 * Move a given direction after checking it would not make the snake move in on itself
	 */
	public void move(Direction dir) {
		if(lastMove.isOpposite(dir)) {
			return;
		}else {
			nextMove = dir;
		}
	}
	
}
