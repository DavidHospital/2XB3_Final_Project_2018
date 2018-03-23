import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class Node {

	List<DisasterEvent> events;
	HashMap<Node, Double> weights;
	
	int x, y;
	
	public Node(int x, int y) {
		events = new ArrayList<DisasterEvent>();
		this.x = x;
		this.y = y;
	}
	
	
	public void add(DisasterEvent e) {
		events.add(e);
	}
	
	public void addWeight(Node other, double weight) {
		if (!weights.containsKey(other)) {
			weights.put(other, weight);
		} else {
			weights.put(other, weights.get(other) + weight);
		}
	}
	
	public List<DisasterEvent> get() {
		return events;
	}
	
	public double getWeight(Node other) {
		if (weights.containsKey(other)) {
			return weights.get(other);
		} else {
			return -1;
		}
	}
	
	public void render(Graphics g) {
		for (Node n : weights.keySet()) {
			g.setColor(Color.getHSBColor(1.0f, 1.0f, 1.0f));
			g.drawLine(this.x, this.y, n.x, n.y);
		}
	}
}
