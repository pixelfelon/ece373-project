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
		int j = 0;
		for(int i = 2; i < args.length; i++) {
			Phalanx phalanx = new Phalanx();
			Missile missile = new Missile();
			RailGun railgun = new RailGun();
			switch (args[i]) {
				case "phalanx"		: 	game.getPlanets().get(j).setWeapon(phalanx); break;
				case "px" 		  	:	game.getPlanets().get(j).setWeapon(phalanx); break;
				case "machinegun" 	:	game.getPlanets().get(j).setWeapon(phalanx); break;
				case "mg" 			:	game.getPlanets().get(j).setWeapon(phalanx); break;
				case "missile"		:	game.getPlanets().get(j).setWeapon(missile); break;
				case "mis"			:	game.getPlanets().get(j).setWeapon(missile); break;
				case "railgun"		:	game.getPlanets().get(j).setWeapon(railgun); break;
				case "rg"			:	game.getPlanets().get(j).setWeapon(railgun); break;
				default				:	break;
			}
			j++;
		}
		
		long startTime = System.nanoTime();
		while (game.isGameActive()) {
			game.tick();
			System.out.printf("\n---- score=%.0f ---- sh=%d ----\n", (double)(System.nanoTime() - startTime) / 1000000000, game.getSun().getHealth());
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
