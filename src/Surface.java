import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
	int gameType, gameLevel;
	Bait bait;
	/*
	 * gameStatus
	 * 	0: Tro choi chua bat dau , chon muc choi va kieu tro choi
	 * 	1: Game bat dau va ran chua di chuyen
	 * 	2: Tro choi bat dau
	 */
	int gameStatus; 
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
			if(gameStatus == 1) gameStatus = 2;
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
	private void initTimer(int dl) {
		timer = new Timer(dl,this);
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
		initMenu();
		gameStatus = 0;
		rd.setSeed(System.currentTimeMillis()); // Cai Seed cho ham random
		this.setSize(x,x);
		this.width = x;
		this.size = x/20;
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
	JLabel lb1 = new JLabel("Loại trò chơi :");
	JLabel lb2 = new JLabel("Độ khó :");
	String country[] = {"Loại 1", "Loại 2"};
    JComboBox cb = new JComboBox(country);
    String country2[] = {"Dễ", "Trung bình","Khó"};
    JComboBox cb2 = new JComboBox(country2);
    
    JButton btnStart = new JButton("Play!");
    private void initMenu() {
        add(lb1);
        add(lb2);
        add(cb);
        add(cb2);
        add(btnStart);
        btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				gameStatus = 2;
				gameType = cb.getSelectedIndex();
				switch(cb2.getSelectedIndex()) {
				case 0:
					gameLevel = 300;
					break;
				case 1:
					gameLevel = 200;
					break;
				case 2:
					gameLevel = 100;
					break;
				}
				initTimer(gameLevel);
				initSnake();
				initBait();
				addKeyListener(kl);
				setFocusable(true);
			}
		});
        hideMenu();
        
    }
    
    private void hideMenu() {
    	this.lb1.setVisible(false);
        this.lb2.setVisible(false);
        this.cb.setVisible(false);
        this.cb2.setVisible(false);
        this.btnStart.setVisible(false);
    }
    private void showMenu() {
    	this.lb1.setVisible(true);
        this.lb2.setVisible(true);
        this.cb.setVisible(true);
        this.cb2.setVisible(true);
        this.btnStart.setVisible(true);
    }
	private void drawMainMenu() {
		
		this.lb1.setLocation(30,405);
		this.cb.setLocation(lb1.getWidth() + 35,405);
		lb2.setLocation(30, 405 + cb.getHeight() + 5);
		cb2.setLocation(35 + lb2.getWidth(), 405 + cb.getHeight() + 5);
		btnStart.setLocation(lb1.getWidth() + 35 + cb.getWidth() + 10,405);
		showMenu();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		g.drawRect(0, 0, width, width);
		if (gameStatus != 0) {
			g.setFont(new Font("Cosolas", Font.BOLD, 20));
			g.setColor(Color.red);
			g.drawString("Length : " + Integer.toString(snake.length), 30, 430);
			drawSnake(g);
			drawBait(g);
		} else {
			g.setFont(new Font("Cosolas", Font.BOLD, 40));
			g.setColor(Color.red);
			g.drawString("Snake", 150, 200);
			drawMainMenu();
		}
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
		switch(gameType) {
		// Loai 1 ran dam vao tuong ket thuc tro choi
		case 0:
			if(snake.node[0].x < 0 || snake.node[0].x > size*19) return 3;
			if(snake.node[0].y < 0 || snake.node[0].y > size*19) return 3;
			break;
		// Loai 2 ran dam vao tuong khong chet
		case 1:
			if(snake.node[0].x < 0) snake.node[0].x = size* 19;
			if(snake.node[0].x > size*19) snake.node[0].x = 0;
			if(snake.node[0].y < 0) snake.node[0].y = size* 19;
			if(snake.node[0].y > size*19) snake.node[0].y = 0;
			break;
		}
		return 0;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(gameStatus != 0) {
			action = 0;
			hideMenu();
			if(gameStatus == 2){
				snake.run();
			}
			switch (isImpact()) {
			case 1:
				JOptionPane.showMessageDialog(null, "Game Over!\nSoccer: " + snake.length);
				gameStatus = 0;
				timer.stop();
				break;
			case 2: 
				snake.Eat(bait);
				createBait();
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "Game Over!\nSoccer: " + (snake.length - 5));
				gameStatus = 0;
				timer.stop();
				break;
			}
			repaint();
		}
		
	}
}
