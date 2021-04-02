/* ==================================
 * This class contains the main()
 * method, creates the game window
 * and starts the game
 * ==================================
 */

/* calling the package and importing
 * necessary libraries
 */

package com.javaproject.game;
import Game.*;
import javax.swing.*;

public class Start {

	public static void main(String args[])
	{           
		
		// creates an object for game
		Game game=new Game();
		
		// initializing the window
		// and its properties
		
		JFrame window = new JFrame("2048");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.add(game);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		//starts the game
		game.start();
	}
}
