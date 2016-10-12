public class Stack 
{
	public int[] push(int[] arr, int i)
	{
		arr = grow(arr);
		arr[0]=i;
		return arr;
	}
	public int[] grow(int[] arr)
	{
		int[] newArr = new int[arr.length+1];
		for(int i=0;i<arr.length;i++)
		{
			newArr[i+1] = arr[i];
		}
		return newArr;
	}
	public String[] stringPush(String[] arr, String s)
	{
		if(arr.length==0)
			arr=new String[1];
		stringGrow(arr);
		arr[0]=s;
		return arr;
	}
	public String[] stringGrow(String[] arr)
	{
		String[] newArr = new String[arr.length+1];
		for(int i=0;i<arr.length;i++)		
			newArr[i+1] = arr[i];		
		return newArr;
	}
	public int[] pop(int[] arr)
	{
		int[] res=new int[arr.length-1];
		for(int i : res)		
			res[i]=arr[i+1];		
		return res;
	}
}