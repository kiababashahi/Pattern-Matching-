package subseq;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		String text_path = "C:\\Users\\.....txt";
		String PatternPath = "C:\\Users\\...patterns.txt";
		String sentence;
		ArrayList<String> pattern;
		ReadInput Reader = new ReadInput(text_path, PatternPath);
		sentence = Reader.Mytext();
		pattern = Reader.My_Pattern();
		// System.out.println(locations.get(0)+ "the normal search: " +
		// sentence.charAt(locations.get(0)));
		// wr.start();
		Node root = new Node();
		long LINEARstartTime = System.currentTimeMillis();
		TreeandFunc t = new TreeandFunc(sentence, pattern, root);
		t.creatTree();
		/*long LINEARendTime = System.currentTimeMillis();
		long LINEARduration = (LINEARendTime - LINEARstartTime);
		System.out.println("the the time to build the tree is :" + LINEARduration);
		long LINEARstartTime1 = System.currentTimeMillis();*/
		// for(int i=0;i<1000;i++) {
		t.suffix_searchs();
		 //}
		/*long LINEARendTime1 = System.currentTimeMillis();
		long LINEARduration1 = (LINEARendTime1 - LINEARstartTime1);
		System.out.println("the suffix tree algorithm runs in :" + LINEARduration1);
		long LINEARstartTime2 = System.currentTimeMillis();*/
		// for(int i=0;i<1000;i++) {
		t.simpleSearch();
		 //}
		/*long LINEARendTime2 = System.currentTimeMillis();
		long LINEARduration2 = (LINEARendTime2 - LINEARstartTime2);
		System.out.println("the simple search algorithm runs in  :" + LINEARduration2);
*/
	}

}
