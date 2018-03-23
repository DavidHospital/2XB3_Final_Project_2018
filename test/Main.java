import javax.swing.JFrame;


public class Main {
	
	public static final int width = 800;
	public static final int height = 600;
	
	public static void main(String[] args) {
		
		JFrame jf = new JFrame();
		jf.setTitle("Graph test");
		jf.setSize(width, height);
		jf.setVisible(true);
		jf.setResizable(false);
		jf.add(new Game(width, height));
	}
}
