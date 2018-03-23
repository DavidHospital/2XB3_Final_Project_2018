package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Main extends JFrame  {
	
	private boolean isRunning = true;
	private int fps = 1000;
	private int windowWidth = 800;
	private int windowHeight = 600;
	
	private BufferedImage backBuffer;
	private Insets insets;
	
	private Game game;
	
	private Main() {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
	}
	
	private void run() {
		initialize();
		
		while(isRunning) 
        { 
            long time = System.currentTimeMillis(); 

            render(); 

            time = (1000 / fps) - (System.currentTimeMillis() - time); 

            if (time > 0) 
            { 
                try 
                { 
                	Thread.sleep(time); 
                } 
                catch(Exception e){} 
            } 
        } 

        setVisible(false); 
	}
	
	private void initialize() {
		
		setTitle("Parser Test");
		setSize(windowWidth, windowHeight);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		game = new Game(windowWidth, windowHeight);
		
		insets = getInsets();
		setSize(insets.left + windowWidth + insets.right,
				insets.top + windowHeight + insets.bottom);
		
		backBuffer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_RGB);
	}
	
	private void render() {		
		Graphics g = getGraphics();
		Graphics bgg = backBuffer.getGraphics();
		
		bgg.setColor(Color.white);
		bgg.fillRect(0, 0, windowWidth, windowHeight);
		
		game.render(bgg);
		
		g.drawImage(backBuffer, insets.left, insets.top, this);
	}
	
	public static void main (String[] args) {
		// Initialize Window
		
		Main game = new Main();
		game.run();
		System.exit(0);
	}
}
