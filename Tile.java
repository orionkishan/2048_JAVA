/* ===============================
 * This class creates individual
 * tiles that will be on the board
 * ===============================
 */

// calling package and importing necessary libraries

package com.javaproject.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Tile {

	// width and height of each tile
	public static final int WIDTH= 80;
	public static final int HEIGHT= 80;
	
	// sliding speed when tile is moved
	public static final int SLIDE_SPEED= 20;
	
	// edge will be rounded rectangle so arc properties must be mentioned
	public static final int ARC_WIDTH= 15;
	public static final int ARC_HEIGHT= 15;
	
	private int value;    // value on tile
	private BufferedImage tileImage; // background with text on it
	private Color background;        // background of tile
	private Color text; 			 // text on tile with number on it
	private Font font;				 // font of text
	private int x;
	private int y;

	private Point slideTo; // row and col where the tile needs to go to
    private boolean canCombine; // if the thing is already combine or not
	
	// constructor to create tile
	public Tile(int value, int x, int y)
	{
		this.value= value;
		this.x=x;
		this.y=y;
		tileImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		drawImage();
	}

	// drawing the background depending on the value it holds
	private void drawImage()
	{
		Graphics2D g =(Graphics2D) tileImage.getGraphics();
		switch(value)
		{
		case 2:{
			background = new Color(0xe9e9e9);
			text = new Color(0x000000);
		}
		break;
		
		case 4:{
			background = new Color(0xe6daab);
			text = new Color(0x000000);
		}
		break;

		
		case 8:{
			background = new Color(0xf79d3d);
			text = new Color(0xffffff);
		}
		break;

		
		case 16:{
			background = new Color(0xf28007);
			text = new Color(0xffffff);
		}
		break;

		
		case 32:{
			background = new Color(0xf55e3b);
			text = new Color(0xffffff);
		}
		break;

		
		case 64:{
			background = new Color(0xff0000);
			text = new Color(0xffffff);
		}
		break;

		case 128:{
			background = new Color(0xe9de84);
			text = new Color(0xffffff);
		}
		break;

		case 256:{
			background = new Color(0xf6e873);
			text = new Color(0xffffff);
		}
		break;

		case 512:{
			background = new Color(0xf5e455);
			text = new Color(0xffffff);
		}
		break;

		
		case 1024:{
			background = new Color(0xf7e12c);
			text = new Color(0xffffff);
		}
		break;

		case 2048:{
			background = new Color(0xffe400);
			text = new Color(0xffffff);
		}
		break;

		default:
		{
			background = Color.black;
			text = Color.white;
		}
		break;

		}
		
		// filling rectangle and rounded rectangle
		g.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0 , WIDTH, HEIGHT);
		
		g.setColor(background);
		g.fillRoundRect(0, 0, WIDTH, HEIGHT, ARC_WIDTH, ARC_HEIGHT);
		
		g.setColor(text);
		
		
		// change font if number has more than 2 digits
		if(value <=64)
		{
			font = Game.main.deriveFont(36f);
		}
		
		else
		{
			font = Game.main;
		}
		
		g.setFont(font);
		
		// finding the location where text goes for center alignment
		int drawX = WIDTH/2 - DrawUtils.getMessageWidth("" + value, font, g)/2;
		int drawY = HEIGHT/2 + DrawUtils.getMessageHeight("" + value, font, g)/2;
		
		// add string to tile
		g.drawString(""+value, drawX, drawY);
		g.dispose();

	}
	
	public void update()
	{
		
	}
	
	public void render(Graphics2D g) // draw tile to screen
	{
		g.drawImage(tileImage, x, y, null);
	}

	public int getValue()
	{
		return value;
	}

	// Getter Setter Methods of CanCombine
    public boolean canCombine(){
        return canCombine;
    }
    public void setcanCombine(boolean canCombine){
        this.canCombine = canCombine;
    }

    // Getter Setter Methods of getSlideTo
    public Point getSlideTo(){
        return this.slideTo
    }
    public setSlideTo(){
        this.slideTo = slideTo
    }
	
}
