package snake;
import java.awt.event.KeyEvent;

public enum KeyAction {
	
	UP,
	DOWN,
	LEFT,
	RIGHT,
	PRIDE,
	RESTART,
	EMPTY;
	
	
	/*
	 * Returns the KeyAction associated to the key pressed
	 */
	public static KeyAction getAction(KeyEvent e) {
		
		int code = e.getKeyCode();
		KeyAction action = EMPTY;
		
		if(code == KeyEvent.VK_UP
		 ||code == KeyEvent.VK_W) {
			action = UP;
		}
		
		if(code == KeyEvent.VK_DOWN
		 ||code == KeyEvent.VK_S) {
			action = DOWN;
		}
		
		if(code == KeyEvent.VK_LEFT
		 ||code == KeyEvent.VK_A) {
			action = LEFT;	
		}
		
		if(code == KeyEvent.VK_RIGHT
		 ||code == KeyEvent.VK_D) {
			action = RIGHT;	
		}
		
		if(code == KeyEvent.VK_P) {
			action = PRIDE;	
		}
		
		if(code == KeyEvent.VK_SPACE) {
			action = RESTART;
		}
		
		return action;
		
	}
	
	
	/**
	 * Returns direction enum of action taken
	 * Will return null if the action is not a direction
	 * @return
	 */
	public Direction getDirection() {
		switch(this) {
			case UP:
				return Direction.UP;
			case DOWN:
				return Direction.DOWN;
			case LEFT:
				return Direction.LEFT;
			case RIGHT:
				return Direction.RIGHT;
			default:
				return null;
		}
	}
	
}
