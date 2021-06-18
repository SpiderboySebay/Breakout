package Final;

import java.awt.*;

public class Help {
	
	public static Rectangle backButton = new Rectangle(20, 500, 150, 75);
	
	public static void render (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font ("arial", Font.BOLD, 18);
		Font fnt1 = new Font ("arial", Font.BOLD, 30);
		
		
		g.setColor(new Color(200, 102, 99));
		g.fillRect(0,0,840,800);
		g.setColor(Color.black);
		
		g.setFont(fnt0);
		g.drawString("Back", backButton.x + 53, backButton.y + 44);
		g2d.draw(backButton);
		
		g.setFont(fnt1);
		g.drawString("HOW TO PLAY", 100, 60);
		
		
		g.setFont(fnt0);
		g.drawString("The objective of the game is to break all the bricks by ", 100, 120);
		g.drawString("bouncing the ball off the paddle and the walls.", 100, 150 );
		g.drawString("Use the left and right arrow keys to move the paddle around.", 100, 200);
	}

}
