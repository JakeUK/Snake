package render;

import java.awt.*;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Window extends JFrame{
	
	
	static final long serialVersionUID = 1L;
	
	private Canvas canvas;
	private int width, height;
	private Dimension dim;
	private Color bg;
	
	private int bNorth, bSouth, bWest, bEast;
	
	public Window(int width, int height) {
		this.width = width;
		this.height = height;
		
		//Defaults
		bNorth = bSouth = bWest = bEast = 10;
		bg = Color.WHITE;
		
		dim = new Dimension(width + 2*(bWest + bEast), height + 2*(bNorth + bSouth) + 30);
		setBackground(bg);
		setLayout(new GridLayout(1, 1, 0, 0));
	}
	
	public void createCanvas() {
		
		//Set up frame
		setSize(dim);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		setMinimumSize(dim);
		
		JPanel content = new JPanel(new GridBagLayout());
		content.setBackground(bg);
        content.setBorder(new EmptyBorder(bNorth, bWest, bSouth, bEast));
		setContentPane(content);
		
		//Set up canvas
		canvas = new Canvas(new Dimension(width, height));
		canvas.setBackgroundColor(bg);
		add(canvas);
		pack();
		
		setVisible(true);
	}
	
	/**
	 * Set border thickness
	 */
	public void setBorder(int b) {
		bNorth = bSouth = bEast = bWest = b;
	}
	
	/**
	 * Set border thickness
	 * @param top
	 * @param left
	 * @param bottom
	 * @param right
	 */
	public void setBorder(int top, int left, int bottom, int right) {
		bNorth = top;
		bWest = left;
		bSouth = bottom;
		bEast = right;
	}
	
	/**
     * Set the background color of the canvas
     */
	public void setBackgroundColor(Color c) {
		bg = c;
	}
	
	/**
	 * Add a brush (draw function) to the canvas
	 * @param b
	 */
	public void addBrush(Drawer b) {
		canvas.addBrush(b);
	}
	
	/**
	 * Adds listener to the canvas
	 * @param l
	 */
	public void addListener(KeyListener l) {
		canvas.addKeyListener(l);
	}
	
	/**
	 * Go through render cycle
	 */
	public void render() {
		canvas.render();
		canvas.paintScreen();
	}
	
	
}
