package entity;

import java.util.Random;

import main.Game;

public class Spawner {

	private Random random;
	private int curTime = 0, spawnTime = 60;

	public Spawner() {
		random = new Random();
	}

	public void tick() {
		curTime++;

		if (curTime == spawnTime) {
			curTime = 0;
			Game.crabs.add(new Crab(random.nextInt(Game.WIDTH - 40), random.nextInt(Game.HEIGHT - 40)));
		}
	}

}
