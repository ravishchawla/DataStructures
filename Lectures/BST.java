/**
 * A Binary_Tree is either null or a BTNode containing one data item
 * and references to a left and right sub-tree, each of which is
 * a Binary_Tree.
 * 
 *********************************
 *
 * A Binary Search Tree (BST) is a Binary_Tree in which for every
 * BTNode, all the data items in its left sub-tree are less than its data,
 * and all the data items in its right sub-tree are greater than its data.
 *    [unique key required!]
 * 
 */
public class BST {
	Node root = null;
	
	public BST() {
		root = null;
	}
	
	public int count() {
		return count(root);
	}
	

	public String inOrder(){

		String res = "< " ;
		res += inOrder (root);

		return res; + " >";

	}


	public String inOrder(Node here){

		String res = "";
		if (here != null){
			res += inOrder(here.left);
			res += here.data;
			res += here.right;
		}



	public String preOrder(Node here){
		String res = "";
		if (here != null){
			count();	

		}


	//deptth first travelsel:
	//	create a queue, 
	//	add the root at row 0. 
	//	
	//
	//	while (! stop)
	//
	//	deque node;
	//	.
	//	.
	//	.
	//	enque children
	//
	//	}
	//
	//
	

		
	public String bft(){

		
		//creqte a queue;
		//while Q not empty 
		//{
			//deque an element
		//}
		
		return bft(root);
	}


	public String bft(Node here){

		//create a queue;
		
		


	/**
	 * recursively count the tree.
	 * termination: here is null, count = 0
	 * otherwise, add 1 to counts for left and right sub-trees
	 * @param here - the current node
	 * @return - the count of this sub-tree
	 */
	private int count(Node here) {
		int res = 0;
		if(here != null) {
			res = 1 + count(here.left) + count(here.right); 
		}
		return res;
	}
	public int depth() {
		return depth(root);
	}
	
	/**
	 * recursively find depth of the tree.
	 * termination: here is null, count = 0
	 * otherwise, add 1 to max depth of left and right sub-trees
	 * @param here - the current node
	 * @return - the count of this sub-tree
	 */
	private int depth(Node here) {
		int res = 0;
		if(here != null) {
			int l = depth(here.left);
			int r = depth(here.right);
			res = 1 + (l>r ? l : r); 
		}
		return res;
	}
	
	public void insert(int data) {
		root = insert(root, data);
	}
	
	/**
	 * recursively find leaf node to add
	 * termination:  here == null
	 *               return the new node
	 * otherwise, go to left or right recursively
	 *            storing modified left or right subtree  
	 *            return the node given             
	 * @param here - current node in the tree
	 * @param data - value to add
	 * @return
	 */
	private Node insert(Node here, int data) {
		if(here != null) {
			if(data < here.data){
				here.left = insert(here.left, data);
			} else if(data > here.data){
				here.right = insert(here.right, data);				
			}
		} else {
			here = new Node(data);
		}
		return here;
	}

	private class Node{
		int data;
		Node left;
		Node right;
		
		public Node(int data) {
			this.data = data;
			left = null;
			right = null;
		}
		
		public String toString() {
			return "" + data " ";
		}
	}
	
	private class Element {
		public Node node;
		public int level;
		
		public Element(Node n, int lev) {
			node = n;
			level = lev;
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int data[] = {50, 25, 75, 12, 35, 60, 90};
		BST it = new BST();
		for(int i = 0; i < data.length; i++) {
			it.insert(data[i]);
		}
		System.out.println("tree size before eq = " + it.count() 
				+ " depth = " + it.depth());
		it.insert(12);
		System.out.println("          tree size = " + it.count() 
				+ " depth = " + it.depth());
		
		System.out.println("Display: \n" + it.inOrder());

		System.out.println("Done!");


		System.out.println("Breadth first" + it.bft());

	}
	
	private class Queue<E> extends LinkedList<E>{
		
		public void enq(E it){
			
			add(it);
			
		}
		
		public E deq(){
			return remove();
		}
		
		
		
		
	}
	
	
}
