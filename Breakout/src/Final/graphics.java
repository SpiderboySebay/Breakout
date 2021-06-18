package Final;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class graphics extends JPanel implements KeyListener, ActionListener, Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int score = 0;
	private Timer timer;
	boolean play = false;
	boolean game_over = false;
	threader thread = new threader();
	Thread t = new Thread(thread);

	// dimensions of frame

	private int height = 640;
	private int width = 800;

	//position of slide

	private int pos_slid_X = 400;
	final private int pos_slid_Y = 590;


	//position of ball

	private int pos_ball_X = getRandomInteger(500,400);
	private int pos_ball_Y = 560;

	//direction of ball

	private int ball_dirX = getRandomInteger(5,1);
	private int ball_dirY = -6;

	//brick dimensions 

	private int brick_width = 25;
	private int brick_height= 15;
	private int bricks [][];
	private int columns = 20;
	private int rows = 8;
	private int nofbricks = columns * rows;

	public static int getRandomInteger(int maximum, int minimum){ 

		return ((int) (Math.random()*(maximum - minimum))) + minimum; 
	}

	enum STATE{
		MENU,
		GAME,
		HELP
	};

	public static STATE state = STATE.MENU;


	public graphics(){

		bricks = new int[columns][rows];
		this.addMouseListener(new MouseInput());
		for (int i =0;i<columns;i++){
			Arrays.fill(bricks[i],1);
		}
		addKeyListener(this);                                               // information that method responsible for
		setFocusable(true);                                                 // Keytyping is in this class
		timer = new Timer(15, this);

		t.start();
		
	}


	public void paint(Graphics g){
		
		if (state == STATE.GAME) {
			thread.setStop(true);
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}

			repaint();

			moveball();                                                         // method responsible for movement of ball

			g.setColor(new Color(200, 102, 99));
			g.fillRect(0,0,width,height);                  // creates game window screen

			drawbricks(g);                                                      // function that draws bricks

			g.setColor(Color.yellow);
			g.fillOval(pos_ball_X,pos_ball_Y,20,20);                    // creates ball

			g.setColor(new Color(93, 57, 200));
			g.fillRect(pos_slid_X,pos_slid_Y,100,10);                    // slider creator
			score(g);                                                          // method that shows score
			lost(game_over,g);                                                 // checks if ball is out of screen

			if (nofbricks ==0) {                                               // if there are no bricks left you won
				win(g);
			}

			Toolkit.getDefaultToolkit().sync();                                // without this method it lags for some reason
			g.dispose();
		}
		else if (state == STATE.MENU) {
			Menu.render(g);
		}
		else if (state == STATE.HELP) {
			Help.render(g);
		}

	}

	class threader implements Runnable{

		private boolean stop = false;
		@Override
		public void run() {

			long lastTime = System.nanoTime();
			double amountOfTicks = 60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;

			while(true) {

				long now = System.nanoTime();
				delta += (now - lastTime)/ns;
				lastTime = now;

				if(delta >= 1) {

					repaint();
					delta--;
				}
			}

		}

		public Boolean getStop() {
			return stop;
		}

		public void setStop(boolean stop) {
			this.stop = stop;
		}

	}



	private void score(Graphics g) {
		/*Updates score on screen*/
		g.setColor(Color.black);
		g.drawString("Score: " + score, width-70,height-50);
	}

	private void win(Graphics g) {
		/*If there are no bricks this method is called and you can start again*/
		timer.stop();
		play = false;
		g.setColor(Color.BLACK);
		g.setFont(new Font(Font.MONOSPACED,Font.BOLD,18));
		g.drawString("You Won", width/2,height/2);
		for (int i =0;i<columns;i++){
			Arrays.fill(bricks[i],1);
		}
		nofbricks = rows * columns;
		pos_ball_X = 415;
		pos_ball_Y = 200;
		ball_dirX = -3;
		ball_dirY = -3;
	}

	private void drawbricks(Graphics g) {
		/*Draws rectangle for each row and column*/
		for (int i =0; i< columns;i++){
			for (int j =0; j< rows;j++){
				if (bricks[i][j]>0) {
					g.setColor(Color.black);
					g.fillRect(100 + i * brick_width + i * 5, 40 + j * brick_height + j * 2, brick_width, brick_height);
					g.setColor(Color.white);
					g.drawRect(100 + i * brick_width + i * 5, 40 + j * brick_height + j * 2, brick_width, brick_height);
				}
			}
		}
	}

	private void lost(boolean game_over, Graphics g) {
		/*If u lost then u lost :)*/
		if (game_over)
		{
			g.setColor(Color.BLACK);
			g.drawString("GAME OVER", width/2,height/2);
			nofbricks = rows * columns;
			score = 0;
			graphics.state = graphics.STATE.MENU;

		}
		else this.game_over = false;
	}

	@Override
	public void keyTyped(KeyEvent keyEvent) {}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		/*If game loads you have to press any button to start*/

		if (!play) timer.start();

		game_over = false;

		/*Here starts code responsible for movement of slider */
		if (keyEvent.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if (pos_slid_X>=this.width-100) {
				pos_slid_X = this.width-100;
			}
			else {
				pos_slid_X += 20;
			}
		}

		if (keyEvent.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if (pos_slid_X < 5) {
				pos_slid_X = 5;
			}
			else {
				pos_slid_X -= 20;
			}
		}

		/*If you press P you can pause the game*/

		if (keyEvent.getKeyCode() == KeyEvent.VK_P){
			if (play) {
				play = false;
				timer.stop();
			}
			else if (!play){
				play=true;
				timer.start();
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent keyEvent) {}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		/*If u press any button game resumes*/
		play = true;
		if (play) {
			repaint();
		}
	}


	private void moveball(){
		/* This method is responsible for changing direction of ball. It checks all possible collisions and respectively 
		 * changes the direction*/

		Rectangle ball_rect = new Rectangle(pos_ball_X + 2,pos_ball_Y + 2,20,20);
		Rectangle slider = new Rectangle(pos_slid_X,pos_slid_Y,100,10);

		if (pos_ball_X <6) ball_dirX = -ball_dirX;


		if (pos_ball_X > width-25) ball_dirX = -ball_dirX;

		if (pos_ball_Y > height-22) {
			play = false;
			game_over = true;
			pos_ball_X = 415;
			pos_ball_Y = 250;
			for (int i =0;i<columns;i++){
				Arrays.fill(bricks[i],1);
			}
			timer.stop();
		}


		if (pos_ball_Y < 6) ball_dirY = -ball_dirY;

		if  (ball_rect.intersects(slider)) ball_dirY = -ball_dirY;


		A:for (int i =0; i< columns;i++){
			for (int j =0; j< rows;j++){
				if (bricks[i][j]>0) {
					int brick_X_pos = 100 + i * brick_width + i * 5;
					int brick_Y_pos = 40 + j * brick_height + j * 2;
					Rectangle brick_rect = new Rectangle(brick_X_pos, brick_Y_pos, brick_width, brick_height);
					if (ball_rect.intersects(brick_rect)) {
						bricks[i][j] = 0;
						if (pos_ball_X+19 <= brick_X_pos || pos_ball_X+1>=brick_X_pos + brick_width ){

							ball_dirX = -ball_dirX;
						}
						else {
							ball_dirY = -ball_dirY;
						}
						score +=1;
						nofbricks--;
						break A;
					}

				}
			}
		}


		this.pos_ball_X += ball_dirX;
		this.pos_ball_Y += ball_dirY;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
