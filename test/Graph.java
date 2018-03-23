package test;

import java.awt.Graphics;


public class Graph {

	public final static double X_RANGE = 60.675;
	public final static double Y_RANGE = 24.238;
	
	public final static double X_START = -125.570;
	public final static double Y_START = 24.624;
	
	Node[][] nodes;
	
	public Graph(int width, int height) {
		nodes = new Node[width][height];
		for (int i = 0; i < nodes.length; i ++) {
			for (int j = 0; j < nodes[0].length; j ++) {
				nodes[i][j] = new Node(i, j);
			}
		}
	}
	
	public void addEvent(DisasterEvent e) {
		Node n1 = getNode(e.getLocation1());
		if (n1 != null) {
			n1.add(e);
			Node n2 = getNode(e.getLocation2());
			if (n2 != null) {
				double dx = e.getLocation2().getLng() - e.getLocation1().getLng();
				double dy = e.getLocation2().getLat() - e.getLocation1().getLat();
				double weight = Math.sqrt(dx * dx + dy * dy);
				n1.addWeight(n2, weight);
			}
		}
	}
	
	private Node getNode(LatLng location) {
		double lat = location.getLat();
		double lng = location.getLng();
		
		int x = (int)((lng - X_START) / X_RANGE * nodes.length);
		int y = (int)((lat - Y_START) / Y_RANGE * nodes[0].length);
		
		if (x >= 0 && x < nodes.length) {
			if (y >= 0 && y < nodes[0].length) {
				return nodes[x][y];
			}
		}
		return null;
	}
	
	public void render(Graphics g) {
		for (Node[] na : nodes) {
			for (Node n : na) {
				n.render(g);
			}
		}
	}
	
}
