package com.tetris.constant;

import static com.tetris.constant.Type.*;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GameColor
{
	public static final Paint                 GRID_BG    = new Paint();
	public static final Paint                 PREVIEW    = new Paint();
	public static final Paint                 BORDER     = new Paint();
	public static final Paint                 GRID       = new Paint();
	public static final Map<Character, Paint> TYPE_COLOR = new HashMap<>();

	static
	{
		GRID_BG.setColor(Color.BLACK);
		GRID_BG.setStyle(Paint.Style.FILL);
		PREVIEW.setColor(Color.argb(100, 190, 190, 190));
		PREVIEW.setStyle(Paint.Style.FILL);

		BORDER.setColor(Color.DKGRAY);
		BORDER.setStrokeWidth(2.5F);
		BORDER.setStyle(Paint.Style.STROKE);
		GRID.setColor(Color.rgb(211, 211, 211));
		GRID.setStrokeWidth(2.5F);
		GRID.setStyle(Paint.Style.STROKE);

		for (Character t : Arrays.asList(VOID, WALL, WALL_INV, O, I, T, L, J, S, Z))
		{
			TYPE_COLOR.put(t, new Paint());
			TYPE_COLOR.get(t).setStyle(Paint.Style.FILL);
		}

		TYPE_COLOR.get(VOID).setColor(Color.TRANSPARENT);
		TYPE_COLOR.get(WALL).setColor(Color.BLACK);
		TYPE_COLOR.get(WALL_INV).setColor(Color.TRANSPARENT);
		TYPE_COLOR.get(O).setColor(Color.rgb(173, 153, 50));
		TYPE_COLOR.get(I).setColor(Color.rgb(49, 178, 131));
		TYPE_COLOR.get(T).setColor(Color.rgb(165, 63, 156));
		TYPE_COLOR.get(L).setColor(Color.rgb(179, 99, 49));
		TYPE_COLOR.get(J).setColor(Color.rgb(81, 63, 166));
		TYPE_COLOR.get(S).setColor(Color.rgb(132, 180, 51));
		TYPE_COLOR.get(Z).setColor(Color.rgb(179, 52, 59));
	}
}
