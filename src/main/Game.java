package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import entity.Crab;
import entity.Spawner;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static int WIDTH = 480;
	public static int HEIGHT = 480;

	public static List<Crab> crabs;
	private Spawner spawner;

	public Game() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		crabs = new ArrayList<>();

		crabs.add(new Crab(16, 16));

		spawner = new Spawner();
	}

	public void tick() {
		spawner.tick();

		for (int i = 0; i < crabs.size(); i++) {
			crabs.get(i).tick();
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
		g.fillOval(WIDTH / 2 - 35, HEIGHT / 2 - 35, 70, 70);
		g.setColor(new Color(193, 140, 86));
		g.fillOval(WIDTH / 2 - 25, HEIGHT / 2 - 25, 50, 50);

		for (int i = 0; i < crabs.size(); i++) {
			crabs.get(i).render(g);
		}

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
}
