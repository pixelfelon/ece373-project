package gameui;

import gameobjects.Game;
import gameobjects.GameDifficulty;
import gameobjects.Entity;
import gameobjects.LivingEntity;
import gameobjects.Weapon;
import gameobjects.Phalanx;
import gameobjects.Missile;
import gameobjects.RailGun;

public class MilestoneDriver {

	public static void main(String[] args) throws InterruptedException {
		int inum = Integer.parseInt(args[1]);
		Game game = new Game(inum, GameDifficulty.valueOf(args[0]));
		for(int i = 2; i < args.length; i++) {
			Weapon weapon = null;
			switch (args[i]) {
				case "phalanx"		:
				case "px" 		  	:
				case "machinegun" 	:
				case "mg" 			:	weapon = new Phalanx();
										break;
				case "missile"		:
				case "mis"			:	weapon = new Missile();
										break;
				case "railgun"		:
				case "rg"			:	weapon = new RailGun();
										break;
				default				:	break;
			}
			game.getPlanets().get(i - 2).setWeapon(weapon);
		}
		
		long startTime = System.nanoTime();
		double lastPrintTime = 0;
		System.out.printf("~~~~~~~~ Starting new game... ~~~~~~~~\n");
		while (game.isGameActive()) {
			game.tick();
			double gameTime = (double)(System.nanoTime() - startTime) / 1000000000;
			if (Math.floor(gameTime) > lastPrintTime) {
				System.out.printf("---- Score = %d ---- Sun's Health = %d ----\n", (int)Math.floor(gameTime), game.getSun().getHealth());
				lastPrintTime = gameTime;
			}
			Thread.sleep(33);
		}
		System.out.println("~~~~~~~~");
		System.out.printf("GAME OVER! Score: %d\n", (int)((double)(System.nanoTime() - startTime) / 1000000000));
	}

}
