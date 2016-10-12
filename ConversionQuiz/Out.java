import java.io.*;

public class Out 
{
	private Converters c = new Converters();
	private String quizText = "";
	private String solText="";
	private RandomGen rg=new RandomGen();
	private PrintWriter quiz;
	private PrintWriter solutions;
	private String type1="";
	private String type2="";
	public void make(String t1, String t2,int questions, String name) throws FileNotFoundException
	{
		String fName=name;
		if(!name.contains(".txt"))
			fName+=".txt";
		quiz = new PrintWriter(fName);
		String solName = name.substring(0, fName.length()-4) + "Solutions.txt";
		solutions = new PrintWriter(solName);

		type1=t1.toLowerCase();
		type2=t2.toLowerCase();

		switch(type1)
		{
			case "binary": for(int i=0;i<questions;i++)
							{
								String temp = rg.getBinary();
								quizText+=temp+System.lineSeparator();
								solText+=convert(temp)+System.lineSeparator();
							}
							break;
			case "bcd": for(int i=0;i<questions;i++)
						{
							String temp = rg.randomBcd();
							quizText+=temp+System.lineSeparator();
							solText+=convert(temp)+System.lineSeparator();
						}
						break;
			case "hexadecimal": for(int i=0;i<questions;i++)
								{
									String temp = rg.randomHex();
									quizText+=temp+System.lineSeparator();
									solText+=convert(temp)+System.lineSeparator();
								}
								break;
			case "decimal": for(int i=0;i<questions;i++)
							{
								String temp = rg.randomDecimal();
								quizText+=temp+System.lineSeparator();
								solText+=convert(temp)+System.lineSeparator();
							}
							break;
			case "octal": for(int i=0;i<questions;i++)
						  {
							  String temp = rg.randomOctal();
							  quizText+=temp+System.lineSeparator();
							  solText+=convert(temp)+System.lineSeparator();
						  }
						  break;
		}
		quiz.print(quizText);
		quiz.close();
		solutions.print(solText);
		solutions.close();
		quizText="";
		solText="";
	}
	
	private String convert(String in)
	{
		String out = "awefg";
		
		if(type1.equals(type2))
		{
			out=in;
		}
		if(in.equals("0")&&!type2.equals("bcd"))
			out="0";
		else if(type1.equals("binary"))
		{
			 if(type2.equals("decimal"))
				out = Integer.toString(c.binary(Integer.parseInt(in), type2));
			 else if(type2.equals("octal"))
				 out=Integer.toString(c.binary(Integer.parseInt(in), "octal"));
			else if(type2.equals("hexadecimal"))
				out = c.toHex(Long.parseLong(in));
			else if(type2.equals("bcd"))
				out = c.toBcd(c.binary(Integer.parseInt(in), "decimal"));
		}
		else if(type1.equals("octal"))
		{
			if(type2.equals("decimal"))
				out = Integer.toString(c.octal(Integer.parseInt(in)));
			else if(type2.equals("binary"))
			{
				int tempInt = c.octal(Integer.parseInt(in));
				long temp = c.toBinary(tempInt);
				out = Long.toString(temp);
			}
			else if(type2.equals("hexadecimal"))
			{
				int t =c.octal(Integer.parseInt(in)); 
				long temp = c.toBinary(t);
				out= c.toHex(temp);
			}
			else if(type2.equals("bcd"))
				out = c.toBcd(c.octal(Integer.parseInt(in)));
		}
		else if(type1.equals("bcd"))
		{
			if(type2.equals("decimal"))
				out = Integer.toString(c.fromBcd(in));
			else if(type2.equals("octal"))
				out = Integer.toString(c.decimal(c.fromBcd(in),"octal"));
			else if(type2.equals("binary"))
				out = Long.toString(c.toBinary(c.fromBcd(in)));
			else if(type2.equals("hexadecimal"))
				out =c.toHex(c.toBinary(c.fromBcd(in)));
		}
		else if(type1.equals("hexadecimal"))
		{
			if(type2.equals("decimal"))
				out = Integer.toString(c.fromHex(in));
			else if(type2.equals("octal"))
				out = Integer.toString(c.octal(c.fromHex(in)));
			else if(type2.equals("bcd"))
			{
				out = c.toBcd(c.fromHex(in));
			}
			else if(type2.equals("binary"))
			{
				out=c.hexToBinary(in);
			}
		}
		else if(type1.equals("decimal"))
		{
			if(type2.equals("octal"))
				out=Integer.toString(c.decimal(Integer.parseInt(in), "octal"));
			else if(type2.equals("decimal"))
				out=Long.toString(c.toBinary(Integer.parseInt(in)));
			else if(type2.equals("bcd"))
				out = c.toBcd(Integer.parseInt(in));
			else if(type2.equals("hexadecimal"))
				out=c.toHex(c.toBinary(Integer.parseInt(in)));
			else if(type2.equals("binary"))
				out=Long.toString(c.toBinary(Integer.parseInt(in)));
		}
		return out;
	}
}