import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

@SuppressWarnings("serial")
public class GUI extends JPanel implements ActionListener
{
  
    
    private Out o = new Out();
    private SpinnerModel model = new SpinnerNumberModel(5,5, 100, 1);
	private JFrame jf = new JFrame("Conversion");
	private JPanel jp = new JPanel();
	private JPanel saving = new JPanel();
	private JPanel choices=new JPanel();
	private JPanel inPanel = new JPanel();
	private JPanel outPanel = new JPanel();
 	private JLabel inForm = new JLabel("Input:");
 	private JLabel outForm=new JLabel("Output:");
 	private JLabel number = new JLabel("Number of questions:");
 	private JButton saveButton = new JButton("Save as...");
	private FlowLayout mainLayout = new FlowLayout(FlowLayout.CENTER, 10,20);
	private GridLayout choiceLayout=new GridLayout(1,2);
	private FlowLayout ioLay= new FlowLayout(FlowLayout.TRAILING,10,10);
	private JSpinner spinner = new JSpinner(model);
	private FileDialog fd = new FileDialog(jf,"Save it",FileDialog.SAVE);
	private JComboBox<String> combo1 = new JComboBox<String>();
	private JComboBox<String> combo2 = new JComboBox<String>();
	private String type2="";
	private String type1="";
	
	public static void main(String args[])
	{
		GUI g = new GUI();
		g.window();
	}
	private void window()
	{
		
		jf.setSize(400,200);
		jf.setResizable(false);
		choices.setLayout(choiceLayout);
		jp.setLayout(mainLayout);
		combo1.addItem("Binary");
		combo1.addItem("Decimal");
		combo1.addItem("Hexadecimal");
		combo1.addItem("Octal");
		combo1.addItem("BCD");
	
		combo2.addItem("Binary");
		combo2.addItem("Decimal");
		combo2.addItem("Hexadecimal");
		combo2.addItem("Octal");
		combo2.addItem("BCD");
		combo2.addActionListener(this);
		
		saveButton.addActionListener(this);
		inPanel.setLayout(ioLay);
		outPanel.setLayout(ioLay);
		
		inPanel.add(inForm);
		
		inPanel.add(combo1);
		outPanel.add(outForm);
		
		outPanel.add(combo2);
	
		choices.add(inPanel);
		choices.add(outPanel);
		saving.add(number);
		saving.add(spinner);
		saving.add(saveButton);
		
		jp.add(choices);
		jp.add(saving);

		jf.add(jp);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setLocationRelativeTo(null);
		jf.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==saveButton)
		{		
			type1 = (String) combo1.getSelectedItem();			
			type2 = (String) combo2.getSelectedItem();
			int questions = (int) spinner.getValue();
			fd.setDirectory("C:\\");
			fd.setFile(".txt");
			fd.setVisible(true);

			String path = fd.getDirectory() + fd.getFile();
		       try {
				o.make(type1,type2,questions,path);
			} catch (FileNotFoundException e1) {e1.printStackTrace();}		
		}
	}
}