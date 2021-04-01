/* ===========================================
 * Intermediate class that keeps track
 * of all pressing events and checks the press
 * and release status of keys
 * ===========================================
 */


// calling package and importing library
package com.javaproject.game;

import java.awt.event.KeyEvent;

public class Keyboard {
	
	// boolean arrays for keys on keyboard
	public static boolean[] pressed = new boolean[256];
	public static boolean[] prev = new boolean[256];

	private Keyboard() { }
	
	public static void update() // transferring key data from current array (pressed) to 'prev' array
	{
		for(int i=0;i<4;i++)
		{
			if(i==0) prev[KeyEvent.VK_LEFT] = pressed[KeyEvent.VK_LEFT];
			if(i==1) prev[KeyEvent.VK_RIGHT] = pressed[KeyEvent.VK_RIGHT];
			if(i==2) prev[KeyEvent.VK_UP] = pressed[KeyEvent.VK_UP];
			if(i==3) prev[KeyEvent.VK_DOWN] = pressed[KeyEvent.VK_DOWN];

		}
	}
	
	public static void keyPressed(KeyEvent e) // returns true status when key is pressed, game board gets updated
	{
		pressed[e.getKeyCode()] = true;
	}
	
	public static void keyReleased(KeyEvent e) // returns true status when key is released, game board gets updated
	{
		pressed[e.getKeyCode()] = false;
	}
	
	public static boolean typed(int keyEvent) // returns status when key is kept pressed
	{
		return !pressed[keyEvent] && prev[keyEvent];
	}
}
