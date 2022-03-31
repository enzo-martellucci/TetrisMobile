package com.tetris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ControllerMenu extends AppCompatActivity
{
	// Attributes


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}


	// Methods
	public void play(View v)
	{
		Intent intent = new Intent(this, ControllerGame.class);
		this.startActivity(intent);
	}

	public void score(View v)
	{
		Intent intent = new Intent(this, ControllerScore.class);
		this.startActivity(intent);
	}

	public void parameter(View v)
	{
		Intent intent = new Intent(this, ControllerParameter.class);
		this.startActivity(intent);
	}

	public void exit(View v)
	{
		this.finish();
	}
}