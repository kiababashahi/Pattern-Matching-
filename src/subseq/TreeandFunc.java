package subseq;

import java.util.ArrayList;

public class TreeandFunc {
	String sentence;
	ArrayList<String> pattern;
	SuffixTree s;
	Node root;
	public TreeandFunc(String sentence,ArrayList<String> pattern,Node root) {
		this.sentence=sentence;
		this.root=root;
		this.pattern=pattern;
	}
	public void creatTree() {
		s = new SuffixTree(root,sentence);
		for(int i=0;i<sentence.length();i++) {
			s.Build_Tree(root,i, sentence.length()-1);
		}
	}
	public void suffix_searchs() {
		ArrayList<Integer> values=new ArrayList<Integer>();
		for(int i=0;i<pattern.size();i++) {
			values.add(s.search(root, pattern.get(i))-pattern.get(i).length());
		}
		
		ArrayList<Integer> finalvalues=new ArrayList<Integer>();
		for(int i=0;i<pattern.size();i++) {
			if(values.get(i)<0) {
				finalvalues.add(-1);
			}
			else
				finalvalues.add(values.get(i));
		}
		Write_To_file wr = new Write_To_file("SuffixTreeOutput", finalvalues);
		wr.start();
	}
	public void simpleSearch() {
		ArrayList<Integer> locations = new ArrayList<Integer>();
		simple_Matching sm = new simple_Matching();
		for (int i = 0; i < pattern.size(); i++) {
			locations.add(sm.Simple_matching(sentence, pattern.get(i)));
		}
		Write_To_file wr1 = new Write_To_file("SimpleOutput", locations);
		wr1.start();
	}
}
