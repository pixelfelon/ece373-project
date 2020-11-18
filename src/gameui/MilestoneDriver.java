package gameui;

import gameobjects.Game;
import gameobjects.Entity;
import gameobjects.LivingEntity;

public class MilestoneDriver {

	public static void main(String[] args) throws InterruptedException {
		Game game = new Game();
		long startTime = System.nanoTime();
		while (game.isGameActive()) {
			game.tick();
			System.out.printf("\n---- t=%.3f ---- sh=%d ----\n", (double)(System.nanoTime() - startTime) / 1000000000, game.getSun().getHealth());
			for (Entity e : game.getEntities()) {
				System.out.print(e);
				System.out.printf(":\tX=%.3f, Y=%.3f", e.getPosition().getX(), e.getPosition().getY());
				if (LivingEntity.class.isAssignableFrom(e.getClass())) {
					System.out.printf(", Health=%d", ((LivingEntity) e).getHealth());
				}
				System.out.println("");
			}
			Thread.sleep(33);
		}
	}

}
