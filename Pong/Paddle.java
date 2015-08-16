import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;

public class Paddle implements KeyListener
{
	private int paddleX = 170; //X axis position of paddle, starts at 170
	private int paddleY = 370; //Y axis position of paddle, starts at 370
	private int xVel  = 0;
	private int padXEnd = 0;


	public int getX()
	{
		return paddleX;
	}

	public void paintPaddle(Graphics g)
	{
		updatePosition();
		g.setColor(Color.red);
		g.fillRect(paddleX, paddleY, 60, 10);
	}
	private void updatePosition()
	{
		paddleX = paddleX + xVel;


		if((paddleX + 60)>390)
		{
			paddleX = 330;
		}

		if(paddleX < 10)
		{
			paddleX = 10;
		}
	}
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			xVel = 10;
		}

		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			xVel = - 10;
		}

	}
	public void keyReleased(KeyEvent e)
	{
		xVel = 0;
	}

	public void keyTyped(KeyEvent e)
	{}



}