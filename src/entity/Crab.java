package entity;

import java.awt.Color;
import java.awt.Graphics;

public class Crab {

	public double x, y, dx, dy;
	public double speed = 4;

	public Crab(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void tick() {
		x++;
		y++;
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int) x, (int) y, 40, 40);
	}

}
