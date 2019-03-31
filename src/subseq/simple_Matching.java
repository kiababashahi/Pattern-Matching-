package subseq;

import java.util.ArrayList;

public class simple_Matching {
	int match=0;
	public int Simple_matching(String text,String pattern) {
		int first_index=0;
		int n=text.length();
		int m=pattern.length();
		boolean flag=true;
		for(int i=0;i<n;i++) {
		    for(int j=0;j<m && i+j<n;j++) { 
		      if(text.charAt(i+j) != pattern.charAt(j)) {
		    	  flag=false;
		    	  break;
		    	  }
		      // mismatch found, break the inner loop
		      if(j==m-1) {
		    	  first_index=i;
		    	 // System.out.println(first_index);
		    	  flag=true;
		    	  break;
		      }// match found
		  }
		    if(flag==true) {
		    	//System.out.println("pizza");
		    	break;
		    }
		}
		if(flag==true)
			match=first_index;
		else 
			match=-1;
		return match;
	}
}
