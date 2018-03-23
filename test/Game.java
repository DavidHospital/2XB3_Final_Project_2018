import java.awt.Graphics;

import javax.swing.JPanel;

public class Game extends JPanel{
	
	
	private int width, height;			
	
	private Graph graph;
	
	
	Game (int width, int height) {
		this.setWidth(width);
		this.setHeight(height);		
		
		graph = new Graph(800, 600);
		
		Parser.parseCSV("test.csv", graph);
		
		repaint ();														
	}
	
	@Override
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		
		graph.render(g);
		//recall this method
		repaint ();
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	private static final long serialVersionUID = 1L;
	
}