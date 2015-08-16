import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.applet.AudioClip;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

public class Pong extends JPanel implements ActionListener
{

	private Paddle paddle = new Paddle();
	private Ball ball = new Ball();
	private int balls = ball.getBalls();
	private ImageIcon bgIcon = new ImageIcon("pongbg.gif");
	private Image bg;
	private AudioClip bgm = Applet.newAudioClip(getClass().getResource("debussy_Arabesque_1.mid"));

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("It's Pong!");
		frame.setSize(416, 439);
		frame.add(new Pong());
		frame.setVisible(true);
	}

	public Pong()
	{
		bgm.play();
		bg = bgIcon.getImage();
		setFocusable(true);
		addKeyListener(paddle);
		ball.addPaddle(paddle);
		Timer t = new Timer(50, this);
		t.start();
	}
	public void paintComponent (Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0, 0, 400, 400);
		g.drawImage(bg, 10, 10, this);
		g.setColor(Color.white);
		ball.paintBall(g);
		paddle.paintPaddle(g);
	}
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}


}