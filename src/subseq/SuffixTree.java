package subseq;

import java.math.MathContext;
import java.util.ArrayList;

public class SuffixTree {
	private Node root;
	String sentence;
static int index=0;
	public SuffixTree(Node root, String sentence) {
		this.root = root;
		this.sentence = sentence;
	}

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
	
	
	
	
	
	public int search(Node node, String pattern) {
		boolean flag = false;
		
		int match_index = -1;
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

	
	
	/*public int LeafValue(Edge e) {
		if (!IsLeaf(e.get_destination())) {
			Edge e1 = Find_Min(e.get_destination());
			return LeafValue(e1);
		} else
			return e.get_destination().get_label();
	}

	public Edge Find_Min(Node n) {
		//System.out.println("kia");
		int min = Integer.MAX_VALUE;
		Edge e = new Edge();
		for (int i = 0; i < n.Edges().size(); i++) {
			if (n.Edges().get(i).get_Elable()[0] < min) {
				min = n.Edges().get(i).get_Elable()[0];
				e = n.Edges().get(i);
			}
		}
		//System.out.println("hello" + e.get_destination().get_label());
		return e;
	}

	public boolean IsLeaf(Node n) {
		if (n.Edges().size() == 0)
			return true;
		else
			return false;
	}*/
}