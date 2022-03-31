package com.tetris.model;

import com.tetris.constant.Direction;
import com.tetris.constant.Type;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Piece
{
	// Attributes
	private boolean[][] structure;
	private char        type;
	private char        direction;


	// Constructor
	private Piece(boolean[][] structure, char type)
	{
		this.structure = structure;
		this.type      = type;
		this.direction = Direction.UP;
	}


	// Getters
	public boolean[][] getStructure()
	{
		return structure;
	}
	public char getType()
	{
		return type;
	}
	public char getDirection()
	{
		return direction;
	}


	// Methods
	public void turnR()
	{
		boolean[][] newStructure = new boolean[this.structure[0].length][this.structure.length];

		for (int l = 0; l < this.structure.length; l++)
			for (int c = 0; c < this.structure[0].length; c++)
			     newStructure[c][this.structure.length - l - 1] = this.structure[l][c];

		this.structure = newStructure;
	}

	public void turnL()
	{
		boolean[][] newStructure = new boolean[this.structure[0].length][this.structure.length];

		for (int l = 0; l < this.structure.length; l++)
			for (int c = 0; c < this.structure[0].length; c++)
			     newStructure[this.structure[0].length - c - 1][l] = this.structure[l][c];

		this.structure = newStructure;
	}

	public String toString()
	{
		StringBuilder display = new StringBuilder();

		for (int l = 0; l < this.structure.length; l++, display.append('\n'))
			for (int c = 0; c < this.structure[l].length; c++)
			     display.append(this.structure[l][c] ? this.type : Type.VOID);

		return display.toString();
	}


	// Factory
	public static List<Piece> createBag()
	{
		List<Piece> bag = new ArrayList<>();
		Piece       piece;

		boolean T = true, F = false;

		bag.add(new Piece(new boolean[][]{ { T, T }, { T, T } }, Type.O));
		bag.add(new Piece(new boolean[][]{ { F, F, F, F }, { T, T, T, T }, { F, F, F, F }, { F, F, F, F } }, Type.I));
		bag.add(new Piece(new boolean[][]{ { F, T, F }, { T, T, T }, { F, F, F } }, Type.T));
		bag.add(new Piece(new boolean[][]{ { F, F, T }, { T, T, T }, { F, F, F } }, Type.L));
		bag.add(new Piece(new boolean[][]{ { T, F, F }, { T, T, T }, { F, F, F } }, Type.J));
		bag.add(new Piece(new boolean[][]{ { F, T, T }, { T, T, F }, { F, F, F } }, Type.S));
		bag.add(new Piece(new boolean[][]{ { T, T, F }, { F, T, T }, { F, F, F } }, Type.Z));

		Collections.shuffle(bag);
		return bag;
	}
}
