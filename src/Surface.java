import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

public class Surface extends JPanel implements ActionListener{
	Random rd = new Random();
	Timer timer;
	Snake snake;
	int width;
	int size; // 
	int action;
	Bait bait;
	KeyListener kl = new KeyListener() {
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			if(action == 1) return;
			switch(e.getKeyCode()) {
			case KeyEvent.VK_LEFT:
				if(snake.face != 2 && snake.face != 4) {
					snake.face = 4;
				}
				break;
			case KeyEvent.VK_RIGHT:
				if(snake.face != 2 && snake.face != 4) {
					snake.face = 2;
				}
				break;
			case KeyEvent.VK_UP:
				if(snake.face != 1 && snake.face != 3) {
					snake.face = 1;
				}
				break;
			case KeyEvent.VK_DOWN:
				if(snake.face != 1 && snake.face != 3) {
					snake.face = 3;
				}
				break;
			}
			action = 1;
		}
	};
	private void initTimer() {
		timer = new Timer(200,this);
		timer.start();
	}
	private void initSnake() {
		snake = new Snake(10*size, 10*size, size, 5);
		snake.face = 2;
	}
	private void initBait() {
		bait = new Bait(size);
		createBait();
	}
	public Surface(int x) {
		rd.setSeed(System.currentTimeMillis());
		this.setSize(x,x);
		this.width = x;
		this.size = x/20;
		initTimer();
		initSnake();
		initBait();
		this.addKeyListener(kl);
		this.setFocusable(true);
	}
	// Ham ve ran tren man hinh
	private void drawSnake(Graphics g) {
		g.setColor(Color.BLACK);
		for(int i = 0; i < snake.length ; i++) {
			g.fillRect(snake.node[i].x, snake.node[i].y, snake.size, snake.size);
		}
	}
	// Ve moi tren man hinh
	private void drawBait(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillOval(bait.x, bait.y, bait.r, bait.r);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
//		g.setColor(Color.black);
//		g.fillRect(0, 0, this.getWidth(), this.getHeight());
//		g.setColor(Color.WHITE);
//		g.fillRect(0,0,width,width);
		g.setColor(Color.black);
		g.drawRect(0, 0, width, width);
		g.setFont(new Font("Cosolas", Font.BOLD, 20));
		g.setColor(Color.red);
		g.drawString("Length : " + Integer.toString(snake.length), 30, 430);
		drawSnake(g);
		drawBait(g);
	}
	void createBait() {
		while (checkBaitInSnake(bait)) {
			bait.x = (rd.nextInt()%20)*size;
			bait.y = (rd.nextInt()%20)*size;
		}
	}
	boolean checkBaitInSnake(Bait b) {
		for(int i = 0; i < snake.length; i++) {
			if(b.x == snake.node[i].x && b.y == snake.node[i].y) {
				return true;
			}
		}
		if(b.x < 0 || b.x >= 19*size || b.y < 0 || b.y > size*19) return true;
		return false;
	}
	
	int isImpact() {
		// Dau Ran va cham voi than ran
		for(int i = 1; i < snake.length; i++) {
			if(snake.node[0].x == snake.node[i].x && snake.node[0].y == snake.node[i].y) return 1;
		}
		// Ran va cham voi moi
		if(snake.node[0].x == bait.x && snake.node[0].y == bait.y) {
			return 2;
		}
		// Ran dam vao tuong
		if(snake.node[0].x < 0 || snake.node[0].x > size*19) return 3;
		if(snake.node[0].y < 0 || snake.node[0].y > size*19) return 3;
		return 0;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		snake.run();
		
		try {
			switch(isImpact()) {
			case 1:
				JOptionPane.showMessageDialog(null, "Tro choi ket thuc");
				System.exit(0);
				break;
			case 2:
				snake.Eat(bait);
				createBait();
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "Tro choi ket thuc");
				System.exit(0);
				break;
			}
		} catch (Exception e) {
			
		}
		repaint();
		action = 0;
	}
}
