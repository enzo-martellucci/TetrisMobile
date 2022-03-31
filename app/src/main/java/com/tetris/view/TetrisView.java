package com.tetris.view;

import static com.tetris.constant.GameColor.BORDER;
import static com.tetris.constant.GameColor.GRID;
import static com.tetris.constant.GameColor.GRID_BG;
import static com.tetris.constant.GameColor.PREVIEW;
import static com.tetris.constant.GameColor.TYPE_COLOR;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;

import com.tetris.ControllerGame;
import com.tetris.model.Tetris;

public class TetrisView extends View
{
	// Attributes
	private ControllerGame ctrl;
	private Tetris         tetris;

	private float gridWidth;
	private float gridHeight;

	private float oX;
	private float oY;

	private float blockSize;


	// Constructor
	public TetrisView(Context context)
	{
		super(context);
	}

	public TetrisView(Context context, AttributeSet attributeSet)
	{
		super(context, attributeSet);
	}

	public void init(ControllerGame ctrl, Tetris tetris)
	{
		this.ctrl   = ctrl;
		this.tetris = tetris;
	}


	// Methods
	public void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);

		// Calculus
		char[][] grid = this.tetris.getGrid();

		float paddingH = (float) (0.05 * this.getHeight());
		float paddingW = (float) (0.05 * this.getWidth());

		this.blockSize = Math.min((this.getWidth() - 2 * paddingW) / grid[0].length, (this.getHeight() - 2 * paddingH) / grid.length);

		this.gridHeight = grid.length * blockSize;
		this.gridWidth  = grid[0].length * blockSize;
		this.oX         = (this.getWidth() - gridWidth) / 2;
		this.oY         = (this.getHeight() - gridHeight) / 2;

		this.drawBackground(canvas);
		this.drawBlock(canvas);
		this.drawPieceAndPreview(canvas);
	}

	private void drawBackground(Canvas canvas)
	{
		char[][] grid       = this.tetris.getGrid();
		float    oXTmp, oYTmp;

		float    lineEnd = this.oX + this.gridWidth - this.blockSize;
		float    colEnd  = this.oY + this.gridHeight - this.blockSize;

		// Draw background
		canvas.drawRect(this.oX, this.oY, this.oX + this.gridWidth, this.oY + this.gridHeight, GRID_BG);

		// Draw grid lines
		oYTmp = this.oY + 4 * this.blockSize;
		for (int l = 4; l < grid.length - 1; l++, oYTmp += this.blockSize)
		     canvas.drawLine(this.oX, oYTmp, lineEnd, oYTmp, GRID);

		// Draw grid cols
		oXTmp = this.oX + 2 * this.blockSize;
		oYTmp = this.oY + 3 * this.blockSize;
		for (int c = 2; c < grid[0].length - 1; c++, oXTmp += this.blockSize)
		     canvas.drawLine(oXTmp, oYTmp, oXTmp, colEnd, GRID);
	}

	private void drawBlock(Canvas canvas)
	{
		char[][] grid = this.tetris.getGrid();
		float oXTmp = this.oX;
		float oYTmp = this.oY;
		Paint paint;

		for (int l = 0; l < grid.length; l++, oXTmp = this.oX, oYTmp += this.blockSize)
			for (int c = 0; c < grid[0].length; c++, oXTmp += this.blockSize)
			{
				paint = TYPE_COLOR.get(grid[l][c]);
				canvas.drawRect(oXTmp, oYTmp, oXTmp + this.blockSize, oYTmp + this.blockSize, paint);
				if (paint != null && paint.getColor() != Color.TRANSPARENT)
					canvas.drawRect(oXTmp, oYTmp, oXTmp + this.blockSize, oYTmp + this.blockSize, BORDER);
			}
	}

	private void drawPieceAndPreview(Canvas canvas)
	{
		// Draw current piece and preview
		boolean[][] structure = this.tetris.getPiece().getStructure();
		Paint       paint     = TYPE_COLOR.get(this.tetris.getPiece().getType());

		int line    = this.tetris.getL();
		int col     = this.tetris.getC();
		int maxLine = this.tetris.getMaxL();

		float oXTmp = this.oX + col * this.blockSize;
		float oYTmp1 = this.oY + line * this.blockSize;
		float oYTmp2 = this.oY + maxLine * this.blockSize;

		for (int l = 0; l < structure.length; l++, oXTmp = this.oX + col * this.blockSize, oYTmp1 += this.blockSize, oYTmp2 += this.blockSize)
			for (int c = 0; c < structure[l].length; c++, oXTmp += blockSize)
				if (structure[l][c])
				{
					canvas.drawRect(oXTmp, oYTmp1, oXTmp + blockSize, oYTmp1 + blockSize, paint);
					canvas.drawRect(oXTmp, oYTmp1, oXTmp + blockSize, oYTmp1 + blockSize, BORDER);
					canvas.drawRect(oXTmp, oYTmp2, oXTmp + blockSize, oYTmp2 + blockSize, PREVIEW);
					canvas.drawRect(oXTmp, oYTmp2, oXTmp + blockSize, oYTmp2 + blockSize, BORDER);
				}
	}
}
