import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 *
 * BST Class. Creates a Binary search tree, and 
 * implements its methods. 
 *
 * @author Ravish Chawla
 *
 **/

public class BST<T extends Comparable<T>> {
	
	private Node<T> root;
	private int size = 0;





	/**
	 * Adds a data entry to the BST
	 * 
	 * null is positive infinity
	 * 
	 * @param data The data entry to add
	 */
	public void add(T data) {
		

		Node newNode = new Node(data);
		Node here = root;
		if (data == null)
		{
		
			while (here.getRight() != null)
			{
				here = here.getRight();
			}
			
			here.setRight(newNode);
		}
		
		else
		root = add(data, root);
		
		size();
		
		
		
	}
	
	/**
	 * this method is a helper method that 
	 * adds an element to the BST. 
	 * if root is null, it makes it root. 
	 * 
	 * else it traverses down to find the right place. 
	 * @param data the data to add
	 * @param here node required to traverse down the bst. 
	 * @return reference to the new node added. 
	 */
	
	private Node add(T data, Node here)
	{
		
		Node newNode = new Node(data);
		
		if (root == null)
			return (root = new Node(data));
		
		if (here != null && data != null)
		{
			if (here.getData() == null)
				here.right = add(data,here.getRight());
			
			else if (here.getData().compareTo(data) < 0)
				here.right = add(data, here.right);
			
			else if (here.getData().compareTo(data) > 0)
				here.left = add(data, here.left);
			
		}
		

		
		else
		{
			here = new Node(data);
		}
		
		return here;
	}
	
	
	/**
	 * Adds each data entry from the collection to this BST
	 * calls add(..) method on each element. 
	 * @param c the list with elements to be added. 
	 */
	public void addAll(Collection<? extends T> c) {
		
		for(T green: c)
		{
			if (green != null)
				add(green);
			else
			{
				add(null);
			}
		}
			
		
		
	}
	
	/**
	 * Removes a data entry from the BST
	 * 
	 * null is positive infinity
	 * 
	 * @param data The data entry to be removed
	 * @return The removed data entry (null if nothing is removed)
	 */
	public T remove(T data) {
		
		
		
		Node parent = root, child = root;
		//setting up variables for parent and child
		
		if (data == null)
		{
			while (child.getData() != null)
			{
				parent = child;
				child = child.getRight();
			}
		}				//looping through the list for null reference
		else{
						
		for (child = root; child != null && !child.getData().equals(data); )
		{
			parent = child;
			if (child.getData().compareTo(data) > 0)
				child = child.getLeft();
			else if (child.getData().compareTo(data)< 0)
				child = child.getRight();
			
						//finding a refernece to the child, and setting old ref to parent
			
		}
		}
			
		Node originalData = child;
		child = remove(parent,child);	//call remove(..) on child and parent. 
							
		if (originalData != root){
 		if (parent.getLeft() != null)
		{				//do some checks, and if they turn out to be right, than remove the reference to the child. 
			
		if (originalData.getData().equals(parent.getLeft().getData()))

			parent.setLeft(child);
		
		
		else
			parent.setRight(child);
		}
	else
		parent.setRight(child);
		}
		
		size();				//leave while calling size(). 
		
		
		return data;
	}
		
	private Node remove(Node parent, Node child){
		
		Node originalData = child;
		
						//store original data. 
		if (child == null)
			return null;		//null case
			
		if (child.getRight() == null)
			child = child.getLeft();
		else if (child.getLeft() == null)
			child = child.getRight();
		
		
		
		else{
							//find predessor. when you do, you set the data of child to predecessors data. 
			Node preParent = child;
			Node preChild = child.getLeft();
			
			for(; preChild.getRight() != null; preParent = preChild, preChild = preChild.getRight());
			
			child.setData(preChild.getData());
			
			
			if (preParent == child)
				preParent.setLeft(preChild.getLeft());
			else
				preParent.setRight(preChild.getLeft());
			
			
		}
		
							//just leave the chiild as it is and send it back in return. 
		

			
		
		return child;
	
	}
	


	
	
	
	
	/**
	 * Checks if the BST contains a data entry
	 * 
	 * null is positive infinity
	 * 
	 * @param data The data entry to be checked
	 * @return If the data entry is in the BST 
	 */
	public boolean contains(T data) {
	
		//T newData = (T)data;
		
		return (contains(root, data));
		
		
	}
	
	
	private boolean contains(Node blue, T data){
		
		
		if (blue == null)
			return false;
		
		if (blue.getData().equals((T)data))
				return true;
		
		
		else{
			
			if (contains(blue.getLeft(), data))
				return true;
			
			else{
				
				if (contains(blue.getRight(), data))
					return true;
			
			}
		}
		
		return false;
	}
	
	/**
	 * Finds the pre-order traversal of the BST
	 * 
	 * @return A list of the data set in the BST in pre-order
	 */
	public List<T> preOrder() {
	
		
		List<T> things = preOrder(root);
		
		return (things = preOrder(root));
	
		
		
		
	}
	
	private List<T> preOrder(Node dos){
		
		

		List<T> nodes = new ArrayList<T>();
		
		if (dos != null)	
		{
			nodes.add((T)dos.getData());
			nodes.addAll(preOrder(dos.getLeft()));
			nodes.addAll(preOrder(dos.getRight()));
		}
		
		
		
		return nodes;
	}
	
	
	
	
	/**
	 * Finds the in-order traversal of the BST
	 * 
	 * @return A list of the data set in the BST in in-order
	 */
	public List<T> inOrder() {
		
		List<T> things = new ArrayList<T>();
		
		return (things = inOrder(root));
		
	}
	
	private List<T> inOrder(Node dos){
		
		

		List<T> nodes = new ArrayList<T>();
		
		if (dos != null)	
		{
			
			nodes.addAll(inOrder(dos.getLeft()));
			nodes.add((T)dos.getData());
			nodes.addAll(inOrder(dos.getRight()));
		}
		
		
		
		return nodes;
	}
	
	
	/**
	 * Finds the post-order traversal of the BST
	 * 
	 * @return A list of the data set in the BST in post-order
	 */
	public List<T> postOrder() {
		
		List<T> nodes = new ArrayList<T>();
		
		return (nodes = postOrder(root));
		
		
		
		
	}
	
	
	private List<T> postOrder(Node dos){
		
		

		List<T> nodes = new ArrayList<T>();
		
		if (dos != null)	
		{
			
			nodes.addAll(postOrder(dos.getLeft()));
			nodes.addAll(postOrder(dos.getRight()));
			nodes.add((T)dos.getData());
		}
		
		
		
		return nodes;
	}
	
	/**
	 * Checks to see if the BST is empty
	 * 
	 * @return If the BST is empty or not
	 */
	public boolean isEmpty() {
		return (root == null);
	}
	
	/**
	 * Clears this BST
	 */
	public void clear() {
		
		
		root = null;
		size();
		
	}
	
	/**
	 * @return the size of this BST
	 */
	public int size() {
		int i = 0;
		
		return (size = size(root));
		
	}
	
	private int size(Node n)
	{
		int nn = 0;
		
		if (n != null)
			nn =  (1 + size(n.getLeft()) + size(n.getRight()));
		
		return nn;
		
	}
	
	

	
	
	/**
	 * First clears this BST, then reconstructs the BST that is
	 * uniquely defined by the given preorder and inorder traversals
	 * 
	 * (When you finish, this BST should produce the same preorder and
	 * inorder traversals as those given)
	 * 
	 * @param preorder a preorder traversal of the BST to reconstruct
	 * @param inorder an inorder traversal of the BST to reconstruct
	 */
	public void reconstruct(List<? extends T> preorder, List<? extends T> inorder) {
	
		
			if (preorder.size() != 0){
		
			
			T main = null;
			
			int end = 0;
			
			for (int j = 0; j < inorder.size(); j++)
			{
				
				if (inorder.get(j).equals(preorder.get(0)))
					{
					
					main = inorder.get(j);
					end = j;
					break;
					}
			}
			
			if (main != null)
				add(main);
			
			
			List<? extends T> left = new ArrayList<T>(inorder.subList(0, end));
			List<? extends T> right = new ArrayList<T>(inorder.subList(end, inorder.size()));
			
			
			reconstruct(preorder.subList(1, preorder.size()), left);
			reconstruct(preorder.subList(1, preorder.size()), right);
				
			}
		
	}
	
	
		
	
	/*
	 * The following methods are for grading, do not modify them
	 */
	
	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int height(Node ref){
	
	if (ref == null)
	{
	return 0;
	}

	int left = height(ref.left);
	int right = height(ref.right);

	if (left > right)
		return left+1;
	else
		return right + 1;


		}


	
	
	public static void main(String[] args){
		
		BST<Integer> types = new BST<Integer>();
		types.add(50);
		types.add(9);
		types.add(13);
		types.add(25);
		types.add(85);
		types.add(79);
		types.add(58);
		types.add(48);
		types.add(1);
		types.add(6);
		types.add(83);
		types.add(34);
		types.add(88);
		types.add(51);
		types.add(21);
		types.add(7);

		System.out.println("height : " + types.height(types.root));
		System.out.println("preorder: " + types.preOrder());
			

		/*

			50
			9, 85
			1, 13, 79, 88
			0, 6, 0, 25, 58, 83, 0, 0
			0, 0, 0, 7, 0, 0, 21, 48, 51, 0, 0, 0, 0, 0, 0, 0, 
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 34, 0, 0, 0, 0, 0 , 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 

.fill 0
.fill 50
.fill 9
.fill 85
.fill 1
.fill 13
.fill 79
.fill 88
.fill 0
.fill 6
.fill 0
.fill 25
.fill 58
.fill 83
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0
.fill 7
.fill 0
.fill 0
.fill 21
.fill 48
.fill 51
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0	;7
.fill 0
.fill 0
.fill 0
.fill 0	;1
.fill 0
.fill 0
.fill 0
.fill 0	;2
.fill 0
.fill 0
.fill 0
.fill 0	;3
.fill 0
.fill 0
.fill 34
.fill 0	;4
.fill 0
.fill 0
.fill 0
.fill 0 ;5
.fill 0
.fill 0
.fill 0
.fill 0	;6
.fill 0
.fill 0
.fill 0
.fill 0 ;7
.fill 0
.fill 0
.fill 0
.fill 0 ;8



		*/
	}
	
	public static class Node<K extends Comparable<K>> {
		
		private K data;
		private Node<K> left, right;
		
		public Node(K data) {
			setData(data);
		}

		public K getData() {
			return data;
		}

		public void setData(K data) {
			this.data = data;
		}
		
		public Node<K> getLeft() {
			return left;
		}
		
		public void setLeft(Node<K> left) {
			this.left = left;
		}
		
		public Node<K> getRight() {
			return right;
		}
		
		public void setRight(Node<K> right) {
			this.right = right;
		}
	}

}
