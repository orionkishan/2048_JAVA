/* ============================================
 * Utility class for determining the dimensions
 * of the text that goes in the tile. This will 
 * be useful is setting the text in the center 
 * of the tile.
 * ============================================
 */

package com.javaproject.game;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

public class DrawUtils {

	private DrawUtils() { }
	
	// method bounds the text with rectangle and calculates the width
	public static int getMessageWidth(String message, Font font, Graphics2D g)
	{
		g.setFont(font);
		Rectangle2D bounds = g.getFontMetrics().getStringBounds(message, g);
		return(int)bounds.getWidth();
	}
	
	// method to return the height of text. Rectangle bounding
	// may give wrong output as padding is also included
	public static int getMessageHeight(String message, Font font, Graphics2D g)
	{
		g.setFont(font);
		if(message.length()==0) 
			return 0;
		TextLayout tl = new TextLayout(message, font, g.getFontRenderContext());
		return(int)tl.getBounds().getHeight();
	}
}
