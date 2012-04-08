package utility;

public class WebHelper {
	
	public String ISBN1310(String ISBN) {
		String CheckDigits = new String("0123456789X0");
	    String s9;
	    int i, n, v;
	    boolean ErrorOccurred;
	    ErrorOccurred = false;
	    s9 = ISBN.substring(3, 12);
	    n = 0;
	    for (i=0; i<9; i++) {
	      if (!ErrorOccurred) {
	        v = Character.getNumericValue(s9.charAt(i));
	        if (v==-1) ErrorOccurred = true;
	        else n = n + (10 - i) * v; 
	      }
	    }
	    if (ErrorOccurred) return "ERROR";
	    else {
	      n = 11 - (n % 11);
	      return s9 + CheckDigits.substring(n, n+1); 
	    }
	  }
	
	  public String getImage(String ISBN)
	  {
		  String isbn10 = ISBN1310(ISBN);
		  return "http://images.amazon.com/images/P/"+isbn10+".jpg";
	  }

}
