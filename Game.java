/* =============================================
 * This class initializes the instance of game
 * and maintains track of FPS(frames per second)
 * to ensure the FPS stays above 60. 
 * =============================================
 */


package com.javaproject.game;

import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.*;

public class Game extends JPanel implements KeyListener,Runnable{

	private static final long serialVersionUID = 1L;
	
	// window dimensions
	public static final int WIDTH = 400;
	public static final int HEIGHT = 630;
	
	// font for game 
	public static final Font main = new Font("Bebas Neue Regular",Font.PLAIN,28);
	private Thread game; 			// thread for game
	private boolean running;		// to check if game thread is running
	
	
	// all the drawing will be done on this image, which will be
	// then drawn to JPanel's graphics (double buffering to prevent flickering)
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private GameBoard board;
	
	private long startTime;
	private long elapsed;
	private boolean set;
	
	// constructor
	public Game()
	{
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		addKeyListener(this);
		
		board = new GameBoard(WIDTH/2 - GameBoard.BOARD_WIDTH/2, HEIGHT - GameBoard.BOARD_HEIGHT - 10);
	}
	
	private void update()  // update status of game
	{
		board.update();
		Keyboard.update(); // status of keys is changed
	}
	
	private void render()  // renders the image of game as it is being played
	{
		
		// first, drawing is done on variable 'image'
		Graphics2D g= (Graphics2D) image.getGraphics();
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		board.render(g);
		g.dispose();
		
		// drawing is then done on JPanel's graphics variable
		Graphics2D g2d=(Graphics2D) getGraphics();
		g2d.drawImage(image, 0, 0, null);
		g2d.dispose();
	}
	
	@Override
	public void run() // maintaining the 60 FPS mark
	{
		int fps = 0, updates = 0;
		long fpsTimer = System.currentTimeMillis();
		
		// nanoseconds per update
		double nsPerUpdate = 1000000000.0 / 60;
		
		double then = System.nanoTime(); // system time in nanoseconds
		double unprocessed = 0;   // number of updates required to be done
		
		while(running)
		{
			boolean shouldRender = false;
			double now = System.nanoTime();
			unprocessed += (now-then) /nsPerUpdate;
			then=now;
		
			while(unprocessed >= 1)  // doing the required updates and modifying variables accordingly
			{
				updates++;
				update();
				unprocessed--;
				shouldRender = true;
			}
			
			if(shouldRender)  // increase the fps if needed
			{
				fps++;
				render();
				shouldRender = false;
			}
			
			else // if not rendering, then sleep the Thread
			{
				try
				{
					Thread.sleep(1);
				}catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
		// debugging method to check FPS and number of updates per second
		if(System.currentTimeMillis()-fpsTimer > 1000)
		{
			System.out.printf("%d fps %d updates", fps, updates);
			System.out.println();
			fps = 0;
			updates = 0;
			fpsTimer+=100;
		}
	}

	// start the Thread by setting 'running' to true
	// synchronized to ensure the entire method gets called 
	// and Thread manager won't switch Threads in the middle
	public synchronized void start() 
	{
		if(running) return;
		running = true;
		game = new Thread(this, "game");
		game.start();
	}
	
	public synchronized void stop()  // stop the Thread by setting 'running' to false
	{
		if(!running) return;
		running = false;
		System.exit(0);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Keyboard.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Keyboard.keyReleased(e);
	}

}
