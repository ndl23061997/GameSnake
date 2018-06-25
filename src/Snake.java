
public class Snake {
	Node node[];
	int length; // Chieu dai ran
	int size;
	int face; // 1 : tren , 2 : phai , 3 : duoi, 4 : trai
	public Snake( int x, int y,int size, int length){
		init();
		this.length = length;
		this.size = size;
		
		node[0].x = x;
		node[0].y = y;
		for(int i = 1; i < length; i++) {
			node[i].x = x - i*size;
			node[i].y = y;
		}
	}
	void init() {
		node = new Node[100];
		for(int i = 0; i < 100; i++) {
			node[i] = new Node(size);
		}
	}
	void Eat(Bait b) {
		node[length] = new Node(size);
		for(int i = length; i > 0; i--) {
			node[i].setLocation(node[i-1].x, node[i-1].y);
		}
		node[0].setLocation(b.x, b.y);
		length++;
	}
	
	public void run() {
		for(int i = length-1; i > 0; i--) {
			node[i].x = node[i-1].x;
			node[i].y = node[i-1].y;
		}
		switch (face) {
			case 1:
				node[0].y -= size;
				break;
			case 2:
				node[0].x += size;
				break;
			case 3:
				node[0].y += size;
				break;
			case 4:
				node[0].x -= size;
				break;
		}
	}
	
	
}
