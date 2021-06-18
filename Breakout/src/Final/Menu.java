package Final;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu {

	public static Rectangle playButton = new Rectangle(325, 150, 150, 75);
	public static Rectangle helpButton = new Rectangle(325, 300, 150, 75);
	public static Rectangle quitButton = new Rectangle(325, 450, 150, 75);

	public static void render (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		Font fnt0 = new Font ("arial", Font.BOLD, 50);
		g.setFont(fnt0);

		g.setColor(new Color(200, 102, 99));
		g.fillRect(0,0,840,800);

		g.setColor(Color.black);
		g.drawString("BRICK BREAKER", 200, 100);

		Font fnt1 = new Font ("arial", Font.BOLD, 30);

		g.setFont(fnt1);

		g.drawString("Play",  playButton.x + 42, playButton.y + 47);
		g2d.draw(playButton);
		g.drawString("Help",  playButton.x + 42, playButton.y + 197);
		g2d.draw(helpButton);
		g.drawString("Quit",  playButton.x + 42, playButton.y + 347);
		g2d.draw(quitButton);
	}
}
