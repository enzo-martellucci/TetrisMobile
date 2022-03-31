package com.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.tetris.model.Loop;
import com.tetris.model.Tetris;
import com.tetris.view.TetrisView;

public class ControllerGame extends AppCompatActivity
{
	// Attributes
	private Loop   loop;
	private Tetris tetris;
	private TetrisView view;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		this.tetris = new Tetris();
		setContentView(R.layout.activity_game);
		this.view = this.findViewById(R.id.tetrisView);

		this.view.init(this, this.tetris);

		this.findViewById(R.id.btnRight).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				ControllerGame.this.moveR();
			}
		});

		this.findViewById(R.id.btnLeft).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				ControllerGame.this.moveL();
			}
		});

		this.findViewById(R.id.btnDown).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				ControllerGame.this.moveMaxD();
			}
		});

		this.findViewById(R.id.btnTurn).setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view)
			{
				ControllerGame.this.turn();
			}
		});
	}


	// Looping methods
	@Override
	protected void onResume()
	{
		super.onResume();
		this.loop = new Loop(this);
		this.loop.powerUp();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		this.loop.powerOff();
	}

	public void maj()
	{
		this.runOnUiThread(new Runnable()
		{
			@Override
			public void run()
			{
				if (ControllerGame.this.tetris.moveD())
					ControllerGame.this.view.invalidate();
			}
		});
	}

	// Methods
	private void moveR()
	{
		if (this.tetris.moveR())
			this.view.invalidate();
	}

	private void moveL()
	{
		if (this.tetris.moveL())
			this.view.invalidate();
	}

	private void moveMaxD()
	{
		boolean loose = !this.tetris.moveMaxD();
		this.view.invalidate();

		if (loose)
			this.finish();
	}

	private void turn()
	{
		if (this.tetris.turn())
			this.view.invalidate();
	}
}