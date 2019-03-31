package subseq;

import java.util.ArrayList;

public class Node {
	private int label;
	private ArrayList<Edge> Edge_list;
	public Node() {
		Edge_list=new ArrayList<Edge>();
	}
	public int get_label() {
		return label;
	}
	public void set_label(int m) {
		label=m;
	}
	public void add_edge(Edge e) {
		Edge_list.add(e);
	}
	public ArrayList<Edge> Edges(){
		return Edge_list;
	}
}
