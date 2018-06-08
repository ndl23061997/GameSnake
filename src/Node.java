
public class Node {
	int x, y, size;
	
	public Node(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}
	public Node(int size) {
		this.size = size;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
