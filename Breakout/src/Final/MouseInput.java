package Final;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx = e.getX();
		int my = e.getY();
		
		// public static Rectangle playButton = new Rectangle(325, 150, 150, 75);
		// public static Rectangle helpButton = new Rectangle(325, 300, 150, 75);
		// public static Rectangle quitButton = new Rectangle(325, 450, 150, 75);
		
		if (mx >= 325 && mx <= 475) {
			if (my >= 150 && my <= 225) {
				graphics.state = graphics.STATE.GAME;
				System.out.println("HELLO");
			}
		}
		
		if (mx >= 325 && mx <= 475) {
			if (my >=300 && my <= 375) {
				graphics.state = graphics.STATE.HELP;
				
			}
		}
		if (mx >= 325 && mx <= 475) {
			if (my >= 450 && my <= 525) {
				System.exit(1);
			}
		}
		
		if (mx >= 20 && mx <=170) {
			if (my >= 500 && my <= 575) {
				graphics.state = graphics.STATE.MENU;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}

