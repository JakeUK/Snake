package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import render.Window;
import render.Drawer;

/**
 * Starts game then creates Window to put it on
 */
public class Main implements Runnable, KeyListener, Drawer{
	
	private volatile boolean running = false;
	private long startTime;
	private int tick;
	private Thread t1;
	
	private boolean isPride = false;
	private Color[] pride = new Color[] {Color.RED,
										 Color.ORANGE,
										 Color.YELLOW,
										 Color.GREEN,
										 Color.BLUE,
										 new Color(128, 0, 128)};
	
	private final int GRIDX = 30;
	private final int GRIDY = 30;
	private final int GAMEX = 600;
	private final int GAMEY = 600;
	private final int WINX = 600;
	private final int WINY = 680;
	private final int TICKRATE = 10;
	//private final int FRAMERATE = 60; //Not implemented
	
	Game game;
	Window window;
	
	public Main() {
		
		game = new Game(GRIDX, GRIDY);
		window = new Window(WINX, WINY);
		window.setTitle("Snake REVISED");
		window.setResizable(true);
		window.setBackgroundColor(Color.DARK_GRAY);
		window.createCanvas();
		
		window.addListener(this);
		window.addBrush(this);
		
		beginGame();
	}
	
	/**
	 * Handles keyPresses
	 */
	public void keyPressed(KeyEvent e) {
		
		KeyAction action = KeyAction.getAction(e);
		Direction dir = action.getDirection();
		
		if(dir != null) {
			game.move(dir);
		}
		else {
			switch(action) {
				case RESTART:
					tick = 0;
					startTime = System.currentTimeMillis();
					game.initGame();
					break;
				case PRIDE:
					isPride = !isPride;
					break;
				default:
					break;
			}
		}
		
	}
	
	/**
	 * Running loop
	 */
	public void run() {
		
		while(running) {
			long currentTime = System.currentTimeMillis();
			
			if(currentTime >= startTime + (tick * 1000/TICKRATE)) {
				game.tick();
				tick++;
			}
			
			long time = System.currentTimeMillis();
			window.render();
		}
		
	}
	
	public void draw(Graphics g) {
		
		int diffX = GAMEX/GRIDX;
		int diffY = GAMEY/GRIDY;
		int pixX = 0;
		int pixY = 0;
		int[][] matrix = game.getMatrix();
		
		//Draw the blocks
		for(int y = 0; y < GRIDY; y++) {
			for(int x = 0; x < GRIDX; x++) {
				Color c;
				int val = matrix[x][y];
				
				if(val < 0) {
					
					if(!game.isFinished()) {
						
						if(!isPride) c=Color.green;
						else {
							int rot = 0 - val;
							c = pride[5 - (rot % 6)];
						}
					}
					else c=Color.red; //Color of death
					
				}else if(val > 0) {
					c = Color.yellow;
				}else {
					c = Color.black;
				}
				
				g.setColor(c);
				g.fillRect(pixX + 1, pixY + 1, diffX - 2, diffY - 2);
				
				
				pixX += diffX;
			}
			pixX = 0;
			pixY += diffY;
		}
		
		//Scoring
		g.setFont(new Font("Consolas", Font.BOLD, 38));
		g.setColor(Color.WHITE);
		g.drawString("Score : " + (game.getScore() - Game.STARTING_LENGTH), GAMEX/2 - 100, GAMEY+50);
		
		//Finished indicator
		g.setFont(new Font("Consolas", Font.BOLD, 20));
		g.setColor(Color.RED);
		if(game.isFinished()) {
			g.drawString("Press SPACE to restart", GAMEX/2 - 130, GAMEY+70);
		}
		
		//In-game indicators
		if(isPride) {
			g.setColor(pride[(int)tick % 6]);
			g.drawRect(0, 0, GAMEX - 1, GAMEY);
		}
		
	}
	
	/**
	 * Starts game/render threads
	 */
	public void beginGame() {
		if(t1 == null || !running) {
			t1 = new Thread(this);
			startTime = System.currentTimeMillis();
			t1.start();
			running = true;
			log("Thread started");
		}
	}
	
	/**
	 * Stops game/rendering
	 */
	@SuppressWarnings("unused")
	private void stopGame() {
		if(running) {
			running = false;
		}
	}
	
	public static void main(String[] args) {
		@SuppressWarnings("unused")
		char c = 51;
		System.out.println(c);
		Main m = new Main();
	}
	
	private void log(String s) {
		System.out.println(s);
	}
	
	//Unused
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
}
