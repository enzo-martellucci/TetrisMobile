package com.tetris.model;

import com.tetris.ControllerGame;

public class Loop implements Runnable
{
	// Attributes
	private ControllerGame ctrl;
	private boolean        running;


	// Constructor
	public Loop(ControllerGame ctrl)
	{
		this.ctrl = ctrl;
	}


	// Methods
	public void run()
	{
		while (this.running)
		{
			this.ctrl.maj();
			try
			{
				Thread.sleep(500);
			}
			catch (Exception e) { e.printStackTrace();}
		}
	}

	public void powerUp()
	{
		this.running = true;
		new Thread(this).start();
	}

	public void powerOff()
	{
		this.running = false;
	}
}
