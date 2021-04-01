public class GameBoard{
    
}
/* =====================================
 * This class is for implementing the 
 * algorithm of game; it checks where
 * to randomly spawn numbers,  checks
 * status of game (won or lost), 
 * does manipulation of tiles and checks
 * allowed moves
 * =====================================
 */

package com.javaproject.game;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GameBoard {

	// rows and columns in the board
	public static final int ROWS = 4;
	public static final int COLS = 4;

	// initial number of tiles
	private final int startingTiles = 2;
	
	// declaring 2D array of tiles
	private Tile[][] board;
	
	// variables for game status
	private boolean dead;
	private boolean won;
	
	// background of game board
	private BufferedImage gameBoard;
	
	// game board + the tiles
	private BufferedImage finalBoard;
	
	// coordinates where we want to render
	private int x;
	private int y;
	
	// spacing between tiles
	private static int SPACING = 10;
	
	// board width and height
	public static int BOARD_WIDTH = (COLS+1)*SPACING + COLS*Tile.WIDTH;
 	public static int BOARD_HEIGHT = (ROWS+1)*SPACING + ROWS*Tile.HEIGHT;

 	private boolean hasStarted;
 	
 	
 	// constructor to set coordinates of board and background data
 	public GameBoard(int x, int y)
 	{
 		this.x=x;
 		this.y=y;
 		board = new Tile[ROWS][COLS];
 		gameBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
 		finalBoard = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
 	
 		createBoardImage();
 		start();
 	}
 	
 	// this method is for creating the empty board with grey and black colors
 	// colors will be over written on this board
 	
 	private void createBoardImage()
 	{
 		Graphics2D g = (Graphics2D) gameBoard.getGraphics();
 		g.setColor(Color.darkGray);
 		g.fillRect(0,  0, BOARD_WIDTH, BOARD_HEIGHT);
 		g.setColor(Color.lightGray);
 		
 		for (int row=0; row<ROWS;row++)
 		{
 			for(int col=0; col<COLS;col++)
 			{
 				int x= SPACING + SPACING*col + Tile.WIDTH*col;
 				int y= SPACING + SPACING*row + Tile.HEIGHT*row;
 				g.fillRoundRect(x, y, Tile.WIDTH, Tile.HEIGHT, Tile.ARC_WIDTH, Tile.ARC_HEIGHT);
 			}
 		}
 	}
 	
 	// begins the game with adding 2 tiles at random places
 	private void start()
 	{
 		for(int i=0; i<startingTiles; i++)
 		{
 			spawnRandom();
 		}
 	}
 	
 	// picks 2 spots and checks if 2 or 4 will be spawned at those places
 	private void spawnRandom()
 	{
 		Random random = new Random();
 		boolean notValid = true;
 		
 		// looping till spawn location is valid
 		while(notValid)
 		{
 			int location = random.nextInt(ROWS*COLS);
 			
 			// essentially a double loop
 			int row = location / ROWS;
 			int col = location % COLS;
 			
 			Tile current = board[row][col];
 			
 			// if current tile is empty
 			if(current == null)
 			{
 				//assigns value and draws it on board
 				int value = random.nextInt(10) < 9 ?  2 : 4;
 				Tile tile = new Tile(value, getTileX(col), getTileY(row));
 				board[row][col]= tile;
 				
 				// terminating the loop
 				notValid = false;
 			}
 		}
 	}
 	
 	// X-position of tile
 	public int getTileX(int col)
 	{
 		return SPACING + col*Tile.WIDTH + col*SPACING;
 	}
 	
 	// Y-position of tile
 	public int getTileY(int row)
 	{
 		return SPACING + row*Tile.HEIGHT + row*SPACING;
 	}
 	
 	// render the board and draw tiles
 	public void render(Graphics2D g)
 	{
 		Graphics2D g2d = (Graphics2D) finalBoard.getGraphics();
 		g2d.drawImage(gameBoard, 0, 0, null);
 		
 		for (int row=0;row<ROWS;row++)
 		{
 			for(int col =0;col<COLS;col++)
 			{
 				Tile current = board[row][col];
 				if(current==null) continue;  // ignore if empty tile
 				current.render(g2d);
 			}
 		}
 		
 		// drawing to the screen
 		g.drawImage(finalBoard, x, y, null);
 		g2d.dispose();
 	}
 	
 	public void update()
 	{
 		checkKeys(); // check for keyboard input
 		
 		for(int row =0;row<ROWS; row++)
 		{
 			for(int col=0;col<COLS;col++)
 			{
 				Tile current = board[row][col];
 				if(current==null) continue;
 				current.update();
 				if(current.getValue()==2048) // checking win condition
 				{
 					won=true;
 				}
 			}
 		}
 	}
 	
 	private void checkKeys() // check if key is pressed and move accordingly
 	{
 		if(Keyboard.typed(KeyEvent.VK_LEFT))
 		{
 			if(!hasStarted) hasStarted = true;
 		}
 		
 		if(Keyboard.typed(KeyEvent.VK_RIGHT))
 		{
 			if(!hasStarted) hasStarted = true;
 		}
 		
 		if(Keyboard.typed(KeyEvent.VK_UP))
 		{
 			if(!hasStarted) hasStarted = true;
 		}
 		
 		if(Keyboard.typed(KeyEvent.VK_DOWN))
 		{
 			if(!hasStarted) hasStarted = true;
 		}
 	}

     private void checkKeys(){
        //move tiles left
        moveTiles(Direction.LEFT);
        moveTiles(Direction.RIGHT);
        moveTiles(Direction.UP);
        moveTiles(Direction.DOWN);

    }
    
    private void moveTiles(Direction dir){
        boolean canMove = false; // initially we assume that tiles cannot move
        int horizontalDirection = 0;
        int verticalDirection = 0;
        if(dir == Direction.LEFT){
            horizontalDirection = -1;
            for(int row = 0;row<ROWS;row++){
                for(int col = 0;col<COLS;col++){
                    if(!canMove){ // once canMove is true, we know we can move all the tiles
                        canMove = move(row, col, horizontalDirection, verticalDirection, dir); 
                    }
                    else{
                        move(row, col, horizontalDirection, verticalDirection, dir);
                    }
                }
            }
        }
        else if(dir == Direction.RIGHT){
            horizontalDirection = 1;
            for(int row = 0;row<ROWS;row++){
                for(int col = COLS-1;col>=0;col--){  // this loop will run backwards
                    if(!canMove){ // once canMove is true, we know we can move all the tiles
                        canMove = move(row, col, horizontalDirection, verticalDirection, dir); 
                    }
                    else{
                        move(row, col, horizontalDirection, verticalDirection, dir);
                    }
                }
            }
        }
        else if(dir == Direction.UP){
            verticalDirection = -1;
            for(int row = 0;row<ROWS;row++){
                for(int col = 0;col<COLS;col++){
                    if(!canMove){ // once canMove is true, we know we can move all the tiles
                        canMove = move(row, col, horizontalDirection, verticalDirection, dir); 
                    }
                    else{
                        move(row, col, horizontalDirection, verticalDirection, dir);
                    }
                }
            }
        }
        else if(dir == Direction.DOWN){
            verticalDirection = 1;
            for(int row = ROWS-1;row>=0;row--){
                for(int col = 0;col<COLS;col++){
                    if(!canMove){ // once canMove is true, we know we can move all the tiles
                        canMove = move(row, col, horizontalDirection, verticalDirection, dir); 
                    }
                    else{
                        move(row, col, horizontalDirection, verticalDirection, dir);
                    }
                }
            }
        }
        else{
            System.out.println("Not Valid");
        }

        for(int row = 0; row<ROWS; row++){
            for(int col= 0; col<COLS;col++){
                Tile current = board[row][col]
                if(canMove){
                    spawnRandom();
                }
            }
        }
    }
    private boolean move(int row, int col, int horizontalDirection,int verticalDirection, Direction dir){
        boolean canMove = false;

        Tile current = board[row][col];
        if(current == null)return false;
        boolean move = true;
        int newCol = col;
        int newRow = row;
        while(move){
            newCol += horizontalDirection;
            newRow += verticalDirection;
            if
        }

        return canMove;
    }
}
