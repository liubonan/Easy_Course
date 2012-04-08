package utility;

public class IdHelper {
	
	public String nextId(String current)
	{
		int i=0;
		
		for(;i<current.length();i++)
		{
			if(current.charAt(i) == '0')
				break;
		}
		
		String prefix = current.substring(0, i);
		int num = Integer.valueOf(current.substring(i));
		
		num++;
		
		String result = Integer.toString(num);
		
		for(;result.length() <(current.length() - i);)
			result = "0" + result;		
		
		return prefix+result;
		
	}

}
