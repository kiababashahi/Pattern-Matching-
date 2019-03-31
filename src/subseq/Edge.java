package subseq;

public class Edge {
	private int[] edge_lable=new int[2];
	private Node start;
	private Node destination;
	public Node get_start() {
		return start;
	}
	public Node get_destination() {
		return destination;
	}
	public void set_Start(Node w) {
		start=w;
	}
	public void set_dest(Node w) {
		destination=w;
	}
	public int[] get_Elable() {
		return edge_lable;
	}
	public void set_Elabel(int a, int b) {
		edge_lable[0]=a;
		edge_lable[1]=b;
	}
	public void set_finish(int c) {
		edge_lable[1]=c;
	}
	public void set_start(int c) {
		edge_lable[0]=c;
	}
	public void modify_start_label(int a) {
		edge_lable[0]=a;
	}
	public void modify_finish(int a) {
		edge_lable[1]=a;
	}
	public void creat_edge(Node s,Node d, int a , int b) {
		set_Start(s);
		set_dest(d);
		set_Elabel(a, b);
	}
}
