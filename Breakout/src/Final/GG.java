package Final;

import javax.swing.*;


public class GG extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
        JFrame frame = new JFrame();
        graphics g = new graphics();
        frame.setResizable(false);
        frame.setBounds(0,0,800,640);
        frame.setTitle("Brick Breaker");
        frame.add(g);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
    }
}