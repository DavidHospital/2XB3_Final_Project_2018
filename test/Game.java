package test;

import java.awt.Color;
import java.awt.Graphics;

public class Game {
	
	
	private int width, height;			
	
	private Graph graph;
	
	
	Game (int width, int height) {
		this.setWidth(width);
		this.setHeight(height);		
		
		graph = new Graph(800, 600);
		
		Parser.parseCSV("test.csv", graph);						
	}
	
	public void render(Graphics g) { 
		graph.render(g);
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