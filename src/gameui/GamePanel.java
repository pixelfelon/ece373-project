package gameui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JPanel;
import javax.swing.Timer;

import gameobjects.Entity;
import gameobjects.Game;

public class GamePanel extends JPanel
{

	private HashSet<UIEntity> entities;
	private Game game;
	private boolean running;
	private Timer simTimer;
	private Image background;

	private static final int tickMs = 16;

	public
	GamePanel (Game game, Image background)
	{
		this.background = background;
		this.entities = new HashSet<UIEntity>();
		this.game = game;
		this.running = false;
		this.simTimer = new Timer(
			tickMs,
			new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					GamePanel.this.tickSimulation();
				}
			}
		);
		this.setOpaque(false);
	}

	private void
	addEntity (Entity entity)
	{
		this.entities.add(UIFactory.makeUIEntity(this, entity));
	}

	@Override
	public void
	paintComponent (Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(this.background, 0, 0, null);
		Graphics2D g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		for (UIEntity e: this.entities)
		{
			e.update(g2);
		}
	}

	public void
	startSimulation ()
	{
		double vh = this.getHeight();
		double vw = this.getWidth();
		System.out.printf("Screen h/w %f %f\n", vh, vw);
		this.running = true;
		this.game.reset();
		this.entities.clear();
		for (Entity gameEntity: this.game.getEntities())
		{
			this.addEntity(gameEntity);
		}
		this.game.clearNewEntities();
		this.simTimer.start();
	}

	public void
	stopSimulation ()
	{
		this.running = false;
		this.simTimer.stop();
		this.pruneEntities();
		this.repaint();
	}

	public void
	togglePauseSimulation ()
	{
		if (this.running)
		{
			if (this.simTimer.isRunning())
			{
				this.simTimer.stop();
			}
			else
			{
				this.game.resetDT();
				this.simTimer.start();
			}
		}
	}

	public boolean
	isRunning ()
	{
		return this.running;
	}

	private void
	pruneEntities ()
	{
		HashSet<UIEntity> toDelete = new HashSet<UIEntity>();
		for (UIEntity e : this.entities) {
			if (e.isReadyToDelete()) {
				toDelete.add(e);
			}
		}
		for (UIEntity entity : toDelete) {
			this.entities.remove(entity);
		}
	}

	private void
	tickSimulation ()
	{
		this.pruneEntities();

		// Add new entities
		for (Entity gameEntity: this.game.getNewEntities())
		{
			this.addEntity(gameEntity);
		}
		this.game.clearNewEntities();

		// Tick game and repaint
		this.game.tick();
		this.repaint();
		if (!this.game.isGameActive())
		{
			//this.stopSimulation();
		}
	}
	public Game getGame() {
		return game;
	}
}
