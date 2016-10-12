import java.util.Random;

public class RandomGen 
{
	private Random rand = new Random();
	public String randomBinary()
	{
		String res = "0";
		String str = "";
		int length = rand.nextInt(10)+1;	
		for(int i=0;i<length;i++)
		{
			int binRand = rand.nextInt(2);
			str+=(Integer.toString(binRand));
		}
		if(str.substring(0, 1).equals("0"))
			str=randomBinary();
		if(str.length()<2)
			str=randomBinary();
		res=str;
		return res;
	}
	public String getBinary()
	{
		String res="";
		res=randomBinary();
		while(res.equals("0"))
			res=randomBinary();
		return res;
	}
	public String randomBcd()
	{
		String ret="";
		 String str="";
		 String[] possible = {"0001","0010","0011","0100","0101","0110","0111","1000","1001","0000"};
		 String[] arr = new String[rand.nextInt(5)+1];
		 for(int i=0;i<arr.length;i++)
		 {
			 //by making sure the first one cannot be 0000, we eliminate the possibility of leading zeroes
			 if(i==0)
				 arr[i]=possible[rand.nextInt(8)];
			 else
				 arr[i]=possible[rand.nextInt(9)];
		 }
	
		 for(String s : arr)
			 str+=s;

		ret=str;
		 return ret;		
	}
	public String randomOctal()
	{
		String res="";
		String str="";
		int length = rand.nextInt(4)+1;
		String[] possible={"1","2","3","4","5","6","7","0"};
		for(int i=0;i<length;i++)
		{
			if(i==0)
				str+=(possible[rand.nextInt(7)]);
			else
				str+=(possible[rand.nextInt(8)]);
		}
		res=str;
		return res;
	}
	public String randomDecimal()
	{
		String res="";
		res=Integer.toString(rand.nextInt(99)+1);
		return res;
	}
	public String randomHex()
	{
		String[] values = {"1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","0"};
		String str="";
		String res="0";
		int length = rand.nextInt(7)+1;
		for(int i=0;i<length;i++)
		{
			if(i==0)
				str+=values[rand.nextInt(15)];
			else
				str+=values[rand.nextInt(16)];
		}
		res=str;
		return res;
	}
}