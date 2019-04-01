# Objective and Program description
 Write and test two pattern matching algorithms. This kind of task is very important in many text processing applications, including in bioinformatics. You are given as input two files. The first file is called string.txt, and is a string on the alphabet {A, B, ... ,Z} . This string is called the Text. The text, could for example, be a genome. The length of the string is called m and is at most 400,000. 

The second file is called patterns.txt. The first line of this file contains an integer called k. The next k lines, each contain a string of length at most n where 1<=n<= 200. Each of the k strings is called a Pattern. We also know that k<=10,000. 

Your job is to check, for each pattern, if it is a sub-string of the text, and if so, at what position? A pattern is a sub-string of the text at position i if Pattern(j) = Text(i + j) for 0<=j<=length(Pattern)-1. 
Write your output into a file called Output.txt. For each pattern in the patterns.txt file, write the position where it is a sub-string of the text on a separate line. If the pattern
is not a sub-string of the text, the corresponding entry should be -1. If there are multiple positions where the pattern occurs, you should output the first one.
To perform the pattern matching, you should implement two algorithms:
1. A straightforward algorithm that checks for each position i with 0<=i<=m-n if the
pattern occurs at position i.
2. An algorithm using suffix trees.
# Code and classes Description
I read the input using the ReadInput class and store each of the Patterns as an element of the array list called "pattern" also I will store the text file in the string called text.
I will first describe the simple matching algorithm found in class simple_matching.java which is just an exhaustive search technique.

The following function takes as input the text file and a pattern(which is a string  from the pattern array list) and outputs the position of occurrence of the pattern in the text. 
```ruby
public int Simple_matching(String text,String pattern) {
	int first_index=0;
	int n=text.length(); // the text length 
	int m=pattern.length(); // the pattern length
	boolean flag=true; //  // true if so far all of the characters matched false otherwise
	for(int i=0;i<n;i++) { //start from the beginning of the text file 
	    for(int j=0;j<m && i+j<n;j++) {  //for each character in the pattern 
	      if(text.charAt(i+j) != pattern.charAt(j)) { // if you see a mismatch 
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

		if(flag==true)
			match=first_index;
		else 
			match=-1;
		return match;
	}
}
```
As you can see the above is an exhaustive search algorithm. If the character at the ith position caused a mismatched, maybe we could take a more efficient approach rather than moving the pointer in the text file forward for only one character. 
### The suffix tree is : 
A suffix tree is a compressed trie containing all the suffixes of the given text as their keys and positions in the text as their values. Suffix trees allow particularly fast implementations of many important string operations.



We first need to Create the suffix tree which can be done using: 
```ruby
	public void creatTree() {
		s = new SuffixTree(root,sentence);
		for(int i=0;i<sentence.length();i++) {
			s.Build_Tree(root,i, sentence.length()-1);
		}
	}
``` 
which will call: 
```ruby 
	public void Build_Tree(Node n, int a, int b) {
		int i = a;
		boolean flag=false;		
		int match_index = -1;
		for (int j = 0; j < n.Edges().size(); j++) {// the first character does match or not
			if (sentence.charAt(a) == sentence.charAt(n.Edges().get(j).get_Elable()[0])) {
				flag = true;				
				match_index = j;
				break;
			}
		}
		if (flag == false) {
		
			Edge e = new Edge();
			Node w = new Node();
			w.set_label(a);
			e.creat_edge(n, w, a, b);
			n.add_edge(e);
			flag = true;
		} 
		
		else {
			// System.out.println(i);
			int edge_val = n.Edges().get(match_index).get_Elable()[0];// this is
			// the place where the strings match it ends in the if below
			// when we see the first mismatch
			//int count = 1;
			while (i<b) {

				if (edge_val > n.Edges().get(match_index).get_Elable()[1]) {
					// when the string has a bigger length than the edge value
					// treat the
					// System.out.println("here");
					Node w = n.Edges().get(match_index).get_destination();
					Build_Tree(w, i, b);
					break;
				}
				if ((sentence.charAt(i) == sentence.charAt(edge_val))) {
					i++;
					edge_val++;
					
				} else {
					// break the edge insert a node and copy the previous
					Edge e = new Edge();
					Node w = new Node();
					e.creat_edge(w, n.Edges().get(match_index).get_destination(), edge_val,
							n.Edges().get(match_index).get_Elable()[1]);
					w.add_edge(e);

					n.Edges().get(match_index).set_finish(edge_val - 1);// the common part the
					 // start stays the same but the ending is modified to the last
					// common
					// new edge is created from w to the old destination of n
					n.Edges().get(match_index).set_dest(w);
					// w.set_label();

					Edge e1 = new Edge();
					Node w1 = new Node();
					w1.set_label(a);
					e1.creat_edge(w, w1, i, b);
					w.add_edge(e1);
					// System.out.println("hello");
					break;

				}
			}
		}
	
	}
```
After we need to feed each pattern to the generated tree to search if it is included in the text or not which is being done by: 

```ruby 
public void suffix_searchs() {
		ArrayList<Integer> values=new ArrayList<Integer>();
		for(int i=0;i<pattern.size();i++) {
			values.add(s.search(root, pattern.get(i))-pattern.get(i).length());
		}

```
the out put of the suffix_search function would be a negative integer if the pattern does not exist in the text otherwise it will be the index of where the pattern can be found in the text file. 
```ruby
	public int search(Node node, String pattern) {
		boolean flag = false;		
		int match_index = -1;
                //the following is a recursive procedure and below you can find the stopping criteria
		for (int i = 0; i < node.Edges().size(); i++) {
			if (sentence.charAt(node.Edges().get(i).get_Elable()[0]) == pattern.charAt(0)) {
				flag = true;
				match_index = i;
				break;
			}
		}
		if (flag == false) {
			
			return -1;
		}

		else {
			int counter = 0;
			int edge_index = node.Edges().get(match_index).get_Elable()[0];
			while (counter < pattern.length()) {
				if (edge_index > node.Edges().get(match_index).get_Elable()[1]) {
				//recursively call the search function from the remaining 
					return search(node.Edges().get(match_index).get_destination(),
							pattern.substring(counter, pattern.length()));
				}
				if (pattern.charAt(counter) == sentence.charAt(edge_index)) { 
					counter++;
					edge_index++;
				} else {
					return -1;
				}
			}
		
			return edge_index;
		}

	}
```


The output of the search function will be added to the values arraylist and eventually used to write the information to the file.
	
