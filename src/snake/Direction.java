package snake;

public enum Direction {
	UP(0, -1),
	DOWN(0, 1),
	LEFT(-1, 0),
	RIGHT(1, 0);
	
	private final int x;
	private final int y;
	
	Direction(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Return x
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Return y
	 * @return
	 */
	public int getY() {
		return y;
	}
	
	private Direction getOpposite(Direction compare) {
		switch(compare) {
			case UP:
				return DOWN;
			case DOWN:
				return UP;
			case LEFT:
				return RIGHT;
			case RIGHT:
				return LEFT;
			default:
				return null;
		}
	}
	
	/**
	 * Returns boolean on whether the direction is opposite to the one inputted
	 */
	public boolean isOpposite(Direction compare) {
		if(getOpposite(this) == compare) {
			return true;
		}else {
			return false;
		}
	}
}


