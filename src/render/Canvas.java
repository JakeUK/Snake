package render;

import java.awt.*;

import javax.swing.JPanel;

public class Canvas extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	//Double buffering
    private Image dbImage;
    private Graphics dbg;
    private int GWIDTH, GHEIGHT;
    
    private Drawer brush;
    
    Color bg;
	
	protected Canvas(Dimension dim) {
		
		bg = Color.darkGray;
		GWIDTH = dim.width;
		GHEIGHT = dim.height;
        
        setPreferredSize(dim);
        setLayout(new GridBagLayout());
        setFocusable(true);
        requestFocus();
	}
    
    protected void render(){
        
        if(dbImage == null){
            dbImage = createImage(GWIDTH, GHEIGHT);
            if(dbImage == null){
                System.err.println("dbImage is still null");
                return;
            }else{
                dbg = dbImage.getGraphics();
            }
            
        }
        dbg.setColor(bg);
        dbg.fillRect(0, 0, getWidth(), getHeight());
        //Draw Game elements
        brush.draw(dbg);
    }
    
    protected void paintScreen(){
        
        Graphics g;
        try{
            g = this.getGraphics();
            if(dbImage != null && g != null){
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync();
            g.dispose();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    protected void addBrush(Drawer b) {
    	brush = b;
    }
    
    /**
     * Set the background color of the canvas
     */
    protected void setBackgroundColor(Color c) {
    	bg = c;
    }
	
}
