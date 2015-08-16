import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.applet.AudioClip;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;

public class Ball extends JPanel
{
	private int ballX = 200; //X axis position of the ball, starts at 200
	private int ballY = 40; //Y axis position of the ball, starts at 40
	private int yVel = 10; //the velocity the ball travels along the y axis
	private int xVel = 10; //the velocity the ball travels along the x axis
	private int padX = 0; //paddle's location
	private int balls = 3; //how many balls the player has left, starts at 3


	private ImageIcon ballIcon = new ImageIcon("ball.png");
	private Image ballSprite;

	private AudioClip blip = Applet.newAudioClip(getClass().getResource("blip.wav"));

	private Font ballsFont = new Font("monospaced", Font.BOLD, 10);
	private Font loser = new Font("monospaced", Font.BOLD, 40);

	private Paddle paddle;

	public void addPaddle(Paddle p)
	{
		paddle = p;
	}

	public int getBalls()
	{
		return balls;
	}

	public void paintBall (Graphics g)
	{
		g.setColor(Color.white);
		g.setFont(ballsFont);
		g.drawString("Balls left: " + balls, 200, 10);
		updatePosition();
		ballIcon.paintIcon(this, g, ballX, ballY);


		if(balls<1)
		{
			g.setFont(loser);
			g.setColor(Color.white);
			g.drawString("Game Over", 100, 100);
		}
	}

	private void updatePosition()
	{
		ballX = ballX + xVel;
		ballY = ballY + yVel;

		if(ballX <10)
		{
			ballX = 10;
			xVel = Math.abs(xVel);
		}

		if(ballX > 370)
		{
			ballX = 370;
			xVel = -Math.abs(xVel);
		}

		if(ballY < 10)
		{
			ballY = 10;
			yVel = Math.abs(yVel);
		}
		padX = paddle.getX();

		if(ballY > 349)
		{
			if(ballX >= padX && ballX <= padX + 60 && ballY < 369)
			{
				yVel = -Math.abs(yVel);
				blip.play();
			}
			else
			{
				if(ballY > 370)
				{
					balls--;

					if(balls > 0)
					{
						ballX = 200;
						ballY = 40;
						xVel = 10;
						yVel = 10;
					}
					if(balls <1)
					{
						xVel = 0;
						yVel = 0;
						balls = 0;
					}
				}
			}
		}

	}

}