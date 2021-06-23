package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import entity.Crab;
import entity.Smoke;
import entity.Spawner;

public class Game extends Canvas implements Runnable, MouseListener {

	private static final long serialVersionUID = 1L;
	public static int WIDTH = 480;
	public static int HEIGHT = 480;
	public static Spritesheet spritesheet;
	public static List<Crab> crabs;
	public static List<Smoke> smokes;
	private Spawner spawner;
	public static Rectangle holeMask;
	public static int mx, my;
	public static boolean isPressed = false;
	public static int score = 0;

	public Game() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.addMouseListener(this);
		spritesheet = new Spritesheet("/spritesheet.png");
		crabs = new ArrayList<>();
		smokes = new ArrayList<>();
		spawner = new Spawner();
		holeMask = new Rectangle(WIDTH / 2 - 10, HEIGHT / 2 - 10, 10, 10);
	}

	public void tick() {
		spawner.tick();

		for (int i = 0; i < crabs.size(); i++) {
			crabs.get(i).tick();
		}
		
		for (int i = 0; i < smokes.size(); i++) {
			smokes.get(i).tick();
		}
	}

	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.setColor(new Color(252, 210, 136));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(new Color(217, 161, 100));
		g.fillOval(WIDTH / 2 - 20, HEIGHT / 2 - 20, 40, 40);
		g.setColor(new Color(193, 140, 86));
		g.fillOval(WIDTH / 2 - 10, HEIGHT / 2 - 10, 20, 20);

		for (int i = 0; i < crabs.size(); i++) {
			crabs.get(i).render(g);
		}
		
		for (int i = 0; i < smokes.size(); i++) {
			smokes.get(i).render(g);
		}

		drawScore(g);

		g.dispose();
		bs.show();
	}

	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		frame.setTitle("Game 08");
		frame.add(game);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		new Thread(game).start();
	}

	public void run() {
		double fps = 60.0;
		while (true) {
			tick();
			render();
			try {
				Thread.sleep((int) (1000 / fps));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void drawScore(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("arial", Font.BOLD, 23));
		g.drawString("Score: " + score, 10, 25);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		isPressed = true;
		mx = e.getX();
		my = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
