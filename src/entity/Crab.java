package entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.Game;

public class Crab {

	public double x, y, dx, dy;
	public double speed = 4;

	public static BufferedImage[] crabSprites;
	private int curIndex = 0, maxIndex = 3, curAnimationFrames = 0, maxAnimationFrames = 15;

	public Crab(int x, int y) {
		this.x = x;
		this.y = y;
		double radius = Math.atan2((Game.HEIGHT / 2 - 20) - y, (Game.WIDTH / 2 - 20) - x);
		this.dx = Math.cos(radius);
		this.dy = Math.sin(radius);
		crabSprites = new BufferedImage[3];

		for (int i = 0; i < crabSprites.length; i++) {
			crabSprites[i] = Game.spritesheet.getSprite(i * 16, 0);
		}
	}

	public void tick() {
		x += (dx * speed);
		y += (dy * speed);

		if (new Rectangle((int) x, (int) y, 40, 40).intersects(Game.holeMask)) {
			Game.crabs.remove(this);
			return;
		}

		animation();

	}

	public void render(Graphics g) {
		g.drawImage(crabSprites[curIndex], (int) x, (int) y, 40, 40, null);
	}

	private void animation() {
		curAnimationFrames++;

		if (curAnimationFrames >= maxAnimationFrames) {
			curAnimationFrames = 0;
			curIndex++;

			if (curIndex >= maxIndex) {
				curIndex = 0;
			}
		}
	}

}
