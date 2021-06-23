package entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;

public class Smoke {

	public int x, y;
	public static BufferedImage[] smokeSprites;
	private int curIndex = 0, maxIndex = 3, curAnimationFrames = 0, maxAnimationFrames = 15;

	public Smoke(int x, int y) {
		this.x = x;
		this.y = y;
		smokeSprites = new BufferedImage[3];

		for (int i = 0; i < smokeSprites.length; i++) {
			smokeSprites[i] = Game.spritesheet.getSprite(i * 16, 16);
		}
	}

	public void tick() {
		animation();
	}

	public void render(Graphics g) {
		g.drawImage(smokeSprites[curIndex], (int) x, (int) y, 40, 40, null);
	}

	private void animation() {
		curAnimationFrames++;

		if (curAnimationFrames >= maxAnimationFrames) {
			curAnimationFrames = 0;
			curIndex++;

			if (curIndex >= maxIndex) {
				curIndex = 0;
				Game.smokes.remove(this);
			}
		}
	}
}
