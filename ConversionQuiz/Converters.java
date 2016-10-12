public class Converters extends Stack
{

	public long toBinary(int num)
	{		
		int[] resArr={};
		String resArrString="";
		long res=0;
		if(num!=0)
		{
			while(num>0)
			{			
				resArr = push(resArr,num%2);
				num=num/2;				
			}			
			for(int i=0;i<resArr.length;i++)
			{
				resArrString=resArrString+resArr[i];
			}			
			res=Long.parseLong(resArrString);				
		}
		return res;
	}

	public String hexToBinary(String num)
	{
		String[] splitted = num.split("");
		String res = "";
		
		for(String s : splitted)
		{
			res+=intCheck(s);
		}
		
		return res;
	}

	public int decimal(int num, String type)
	{
		int res=0;
		int[] resArr={};
		String resArrString="";

		if(type.equals("octal")&&num!=0)
		{
			while(num>0)
			{			
				resArr = push(resArr,num%8);
				num=num/8;				
			}			
			for(int i=0;i<resArr.length;i++)			
				resArrString=resArrString+resArr[i];
			
			res=Integer.parseInt(resArrString);
		}
		return res;
	}
	
	public int binary(long num, String type)
	{
		int res=0;
		int column=1;
		int chunks =0;
		int chunkKey=0;
		int[]numArr=makeArr(num);
		String resString = "";
		
		if(type.equals("decimal"))
		{
			for(int i=numArr.length-1;i>=0;i--)
			{
				res+=(numArr[i]*column);
				column=column*2;
			}
		}		
		//to Octal
		if(type.equals("octal"))
		{
			numArr =  addLeadz(numArr,3);
			chunkKey=0;
			String octString = "";
			//Sets the array up into three digit chunks with which to do the conversion process.
			chunks=numArr.length/3;
			int[] chunksArr=new int[chunks];
			int[] decArr=new int[chunks];
			for(int i=0;i<chunks;i++)
			{					
				String[] temp = {Integer.toString(numArr[chunkKey]), Integer.toString(numArr[chunkKey+1]), Integer.toString(numArr[chunkKey+2])};   
				String temp2 = temp[0]+temp[1]+temp[2];
				chunksArr[i]= Integer.parseInt(temp2);
				chunkKey+=3;
			}
			
			for(int i=0;i<chunksArr.length;i++)
				decArr[i] = binary(chunksArr[i], "decimal");
			for(int i=0;i<decArr.length;i++)
				octString+=decArr[i];
			
			res = Integer.parseInt(octString);
		}
		if(type.equals("bcd"))
		{
			resString = toBcd(binary(num, "decimal"));
			res = Integer.parseInt(resString);
		}
		if(type.equals("hex"))
			resString=toHex(num);
		return res;
	}
	
	public int octal(int num)
	{
		int[] numArr=makeArr(num);
		
		int res=0;
 
			for(int i=0;i<numArr.length;i++)
			{
				res+=numArr[i];
				if(i<numArr.length-1)
					res = res*8;
			}
		  
		return res;
	}
	//must go in as a decimal first
	public String toBcd(int num)
	{
		int[] numArr=makeArr(num);
		String resString = "";
		int[] values={8,4,2,1};
		
		for(int i=0;i<numArr.length;i++)
		{
			int digit = numArr[i];
			for(int j=0;j<4;j++)
			{
				if(digit-values[j]>=0)
				{
					resString+="1";
					digit-=values[j];
				}
				else if(digit-values[j]<0)
					resString+="0";	
			}
		}
		return resString;
	}
	
	//returns decimal
	public int fromBcd(String num)
	{				
		String resString = "";
		int res=0;
		
		String[] chunks = new String[num.length()/4]; 
		int beginIndex=0;
		int endIndex=4;
		for(int i=0;i<chunks.length;i++)
		{
			String chunk="";
			if(endIndex+4>num.length())
				chunk=num.substring(beginIndex);
			else
				chunk =num.substring(beginIndex	,endIndex);
			chunks[i]=chunk;
			beginIndex+=4;
			endIndex+=4;
		}
		for(int i=0;i<chunks.length;i++)
		{
			int tempInt = Integer.parseInt(chunks[i]);
			resString+=binary(tempInt,"decimal");
		}
        res = Integer.parseInt(resString);
        
		return res;
	}
	
	public String toHex(long num) //must go in as binary, split into chunks of 4
	{
		String res="";		
		int chunkKey=0;
		int[] numArr= makeArr(num);
		if(numArr.length%4!=0)
			numArr = addLeadz(numArr, 4);

		int chunks = numArr.length/4;
		
		String[] chunksArr=new String[chunks];
		for(int i=0;i<chunks;i++)
		{					
			String temp= Integer.toString(numArr[chunkKey]) + Integer.toString(numArr[chunkKey+1])
				+ Integer.toString(numArr[chunkKey+2]) + Integer.toString(numArr[chunkKey+3]);
			chunksArr[i]=temp;
			chunkKey+=4;
		}
		for(int i=0;i<chunksArr.length;i++)
		{
			int parsed = Integer.parseInt(chunksArr[i]);
			int temp = binary(parsed, "decimal");
			res+=charCheck(temp);
		}
		
		return res;		
	}
	
	public int fromHex(String num)
	{
		int res = 0;
		int dec=0;
		int exponent=num.length()-1;
		int[] intArr = new int[num.length()];
		String[] splitted = num.split("");
		for(int i=0;i<splitted.length;i++)
		{
			try{
			if(Integer.parseInt(splitted[i])<10)
			{
				int extraTemp = Integer.parseInt(splitted[i]);
				intArr[i]=extraTemp;
			}}
			catch(Exception e){
			if(splitted[i].equalsIgnoreCase("a"))			
				intArr[i] = 10;			
			if(splitted[i].equalsIgnoreCase("b"))			
				intArr[i] = 11;
			if(splitted[i].equalsIgnoreCase("c"))			
				intArr[i] = 12;
			if(splitted[i].equalsIgnoreCase("d"))			
				intArr[i] = 13;
			if(splitted[i].equalsIgnoreCase("e"))			
				intArr[i] = 14;
			if(splitted[i].equalsIgnoreCase("f"))			
				intArr[i] = 15;
			}
		}
		for(int i=0;i<intArr.length;i++)
		{
			dec +=intArr[i] *Math.pow(16, exponent);
			exponent--;
		}
		res=dec;
		return res;
	}

	//turn a number into an array
	private int[] makeArr(int num)
	{
		String numString = Integer.toString(num);		
		String[] splitted = numString.split("");
		int[] numArr=new int[splitted.length];
		for(int i=0;i<splitted.length;i++)
			numArr[i]=Integer.parseInt(splitted[i]);		
		return numArr;
	}
	private String charCheck(int i)
	{
		String res="";
		if(i<10)
			res=Integer.toString(i);
		else if(i==10)
			res = "A";
		else if(i==11)
			res= "B";
		else if(i==12)
			res="C";
		else if(i==13)
			res = "D";
		else if(i==14)
			res = "E";
		else if(i==15)
			res = "F";
		return res;
	}
	private String intCheck(String s)
	{
		String res="";
		s=s.toLowerCase();
		if(s.equals("0"))
			res="0000";
		else if(s.equals("1"))
			res="0001";
		else if(s.equals("2"))
			res="0010";
		else if(s.equals("3"))
			res="0011";
		else if(s.equals("4"))
			res="0100";
		else if(s.equals("5"))
			res="0101";
		else if(s.equals("6"))
			res="0110";
		else if(s.equals("7"))
			res="0111";
		else if(s.equals("8"))
			res="1000";
		else if(s.equals("9"))
			res="1001";
		else if(s.equals("a"))
			res="1010";
		else if(s.equals("b"))
			res="1011";
		else if(s.equals("c"))
			res="1100";
		else if(s.equals("d"))
			res="1101";
		else if(s.equals("e"))
			res="1110";
		else if(s.equals("f"))
			res="1111";
		
		return res;
	}
	//turn a long into an array
	private int[] makeArr(long num)
	{
		String numString = Long.toString(num);		
		String[] splitted = numString.split("");
		int[] numArr=new int[splitted.length];
		for(int i=0;i<splitted.length;i++)		
			numArr[i]=Integer.parseInt(splitted[i]);		
		return numArr;
	}
	//add leading zeroes
	private int[] addLeadz(int[] numArr, int chunk)
	{
		int needed;
		int wrong = numArr.length%chunk;
		if(wrong==chunk)
			needed = chunk - numArr.length;
		else
			needed = chunk-wrong;
		for(int i=0;i<needed;i++)		
			numArr=push(numArr, 0);		
		return numArr;
	}
	
} 