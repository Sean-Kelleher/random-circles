/* This program animates circles in different colors and sizes.
 * The color, locations, amount and size of each circle is determined randomly, as is the background color.
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;

public class RandomCircles extends JPanel implements ActionListener
{
    public Random randInt = new Random();
    public static JFrame frame = new JFrame("just feel it");
    public int width = 556;
    public int height = 459;    
    private int shape;
    private int XPos = 0;
    private int YPos = 0;
    private int[][] positions = new int[200][2];
    private int colorAmt = 0; //amount of colors in the "colors" array, also the amount of shapes.
    private Color[] colors = new Color[100];
    
    public static void main(String[] args)
    {
	frame.setSize(556, 459);
	frame.setResizable(false);
	frame.add(new SummerProgram());
	frame.setVisible(true);       
    } 
    public RandomCircles()
    {        
        Timer metronome = new Timer(500, this);
        metronome.start();        
        
    }    
    public void paintComponent(Graphics g)
    {
        
        g.setColor(new Color(randInt.nextInt(255), randInt.nextInt(255), randInt.nextInt(255)));
        g.fillRect(0,0,width, height);
        colorAmt = randInt.nextInt(15)+5;
        
        for(int i = 0; i<colorAmt; i++)
        {
            colors[i] = new Color(randInt.nextInt(255), randInt.nextInt(255), randInt.nextInt(255));
        }

        for(int i=0; i<colorAmt; i++)
        {
            XPos = randInt.nextInt(550)-25;
            YPos = randInt.nextInt(550)-25;
            shape = randInt.nextInt(123) + 25;
            g.setColor(colors[i]);
            g.fillOval (XPos, YPos, shape, shape);
        }
        
    }
    public void actionPerformed(ActionEvent e)
    {
	repaint();
    }
}