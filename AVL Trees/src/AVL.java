import java.awt.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

	/**
	 * An AVL Binary Search Tree implementation. 
	 * 
	 * @author Ravish Chawla
	 */

public class AVL<T extends Comparable<T>> {
	
	private Node<T> root;
	private Node<T> head;
	private int size;
	
	
	public static void main(String[] args){
		
		
		AVL<Integer> types = new AVL<Integer>();
		
		types.add(5);
		types.add(7);
		types.add(1);
		types.add(11);
		types.add(17);
		types.add(13);
		types.add(8);
		types.add(2);
		types.add(null);
		types.add(18);
		types.add(3);
		types.add(14);
		
		System.out.println(types.preorder());
		System.out.println("Ta-tada-da!");
		
	}
	
	
	/**
	 * Adds a data entry to the AVL tree
	 * 
	 * @param data The data entry to add
	 */
	

	
	public void add(T data) {
		
		root = add(data,root);
		
		//root = 
//		this.updateHeightAndBF(root);
		size();
		
		
	//	root = rotate(root);
	}
	
	/*
	 * this is a helper method to add, 
	 * it adds the elemnt, and returns a reference
	 * to the new root. it also calls rotate while
	 * recursively coming back. 
	 */
	
	
	private Node add(T data, Node here)
	{
		
	if (here != null){// && data != null){
		//if (here.getData().compareTo(data) > 0)
		
		if (this.comparator(here.getData(), data) > 0)
		{
			here.left = add(data, here.getLeft());
			
			//here = this.updateHeightAndBF(here);
			
			/*if (here.right != null)
				here.right = this.updateHeightAndBF(here.right);
			
			if (here.left != null)
				here.left = this.updateHeightAndBF(here.left);
			*/
			here = this.rotate(here);
			}
		else if (this.comparator(here.getData(), data) < 0)
		{
			here.right = add(data, here.getRight());
		
			//here = this.updateHeightAndBF(here);
			/*if (here.right != null)
				here.right = this.updateHeightAndBF(here.right);
			
			if (here.left != null)
				here.left = this.updateHeightAndBF(here.left);
			*/
			here = rotate(here);
		
		}
		
	}
	else{
		
		here = new Node(data);
		
		//here = 
		here = updateHeightAndBF(here);
		
	}

	return here;
	
	}
	
	
	/**
	 * Adds each data entry from the collection to this AVL tree
	 * 
	 * @param c The collection 
	 */
	public void addAll(Collection<? extends T> c) {
		
		for(T st: c)
			add(st);
		
		
	}
	
	/*
	 * this method is a comparing method, that helps
	 * compare nulls as well as values in the list. 
	 * 
	 */
	
	
	private int comparator(Object o1, Object o2){
		
		if (o1 == null && o2 == null)
		{
			return 0;
		}
		
		
	else if (o1 == null)
		{
			return 1;
			
			
		}
		
		else if (o2 == null)
		{
			return -1;
		}
			
		
		
		else {
			
			T t1 = (T)o1;
			T t2 = (T)o2;
			
			return (t1.compareTo(t2));
			
		}
		
			
			
		
		
	}
	
	
	/**
	 * Removes a data entry from the AVL tree
	 * 
	 * Return null if the value does not exist
	 * 
	 * @param data The data entry to be removed
	 * @return The removed data entry
	 */
	public T remove(T data){
		
		Node parent = root, child = root;
		
		while(child != null && this.comparator(data, child.getData()) != 0){
			
			parent = child;
			
			if (this.comparator(data, child.getData()) <= 0)
				child = child.getLeft();
			else
				child = child.getRight();
			
			
		}
		Node originalData = child;
		child = remove(parent,child);
		
		
		if (this.comparator(originalData.getData(), root.getData()) == 0)
			root = child;
		
		else if (parent.getLeft() != null && this.comparator(parent.getLeft().getData(),originalData.getData()) == 0)
			parent.setLeft(child);
		else
			parent.setRight(child);
	
		
		size();
		return data;
		
	}
	/*
	 * helper method to remove(..), it deals with the 3 cases, 
	 * and returns a reference to the node removed. 
	 */
	
	private Node remove(Node parent,Node child){
		
		Node originalData = child;
		if (child != null)
		{
			
			if (child.right == null)
				child = child.left;
			else if (child.left == null)
				child = child.right;
			
			else{
				
				Node preChild = child.left;
				Node preParent = child;
				
				while (preChild.right != null){
					
					preParent = preChild;
					preChild = preChild.right;
					
					
				}
				
				child.setData(preChild.getData());
				
				 if (this.comparator(preParent.getData(), child.getData()) == 0)
				 {
					 
					 preParent.setLeft(preChild.getLeft());
					 
				 }
				 else
					 preParent.setRight(preChild.getLeft());
				
				
				 
				
			}
			
			
		}
		return child;
		
		
	}
	
	
	/**
	 * Checks if the AVL tree contains a data entry
	 * 
	 * @param data The data entry to be checked
	 * @return If the data entry is in the AVL tree 
	 */
	public boolean contains(T data) {
		
		
		
		return contains(root, data);
	}
	
	/*
	 * helper method to contains(..). 
	 */
	private boolean contains(Node n, T data){
		
		if (n == null)
			return false;

			if (this.comparator(n.getData(), data) > 0)
				return contains(n.left, data);
			else if (this.comparator(n.getData(), data) < 0)
				return contains(n.right, data);
			
			else if (this.comparator(n.getData(), data) == 0)
				return true;
			else
				return false;
			
		
	}
	
	

	
	
	/**
	 * Calculates the current height and balance factor for a node and updates the values
	 * 
	 * THIS DOES NOT RECURSIVELY UPDATE N AND ALL OF N'S CHILDREN, ONLY UPDATE N
	 * (caps because it's important! Don't kill the running time of everything!)
	 * 
	 * @param n The node whose values are to be calculated and updated
	 * @return The node passed in with updated values
	 */
	private Node<T> updateHeightAndBF(Node<T> here) {
		
					//if (here != null){	
				here.setHeight(height(here));
				int bf = height(here.getLeft()) - height(here.getRight());
				here.setBf(bf);
					//}
			
		
		return here;
	}
	
	/*
	 * this method returns the height of the list from a particular node.  
	 * it's a helper method to updateHeightAndBf()
	 */

	private int height(Node here){
	
		
		int count = 0;
		if (here != null)
		{
			int left = height(here.left);
			int right = height(here.right);
			count = (left > right? left: right)+1;
			
		}
		
		return count;
			
	}
		
	
	
	/**
	 * Determines what rotation, if any, needs to be performed on a node and does the appropriate rotation
	 * 
	 * @param n The node to potentially be rotated
	 * @return The new root of the subtree that is now balanced due to the rotation 
	 * 			(possibly the same node that was passed in) 
	 */

	
	
	
	private Node<T> rotate(Node<T> here) {
		
			here = this.updateHeightAndBF(here);
			
			if (here.right != null)
				here.right = this.updateHeightAndBF(here.right);
			
			if (here.left != null)
				here.left = this.updateHeightAndBF(here.left);
			

		if (here.getBf() > 1){
			if (here.getLeft() != null && here.getLeft().getBf() < 0)
			{
				
			
				here = this.leftRight(here);
				
			
			}
			else{
				
				here = this.right(here);
			}
			
			
	
		}
		
		
		else if (here.getBf() < -1)
		{
			if (here.getRight() != null && here.getRight().getBf() > 0)
			{
				here = this.rightLeft(here);
				
			}
			
			else{
				here = this.left(here);
			}
		}
			
		
		
		return here; 
	}
	
	/**
	 * Performs a left rotation on a node
	 * 
	 * @param n The node to have the left rotation performed on
	 * @return The new root of the subtree that is now balanced due to the rotation
	 */
	private Node<T> left(Node<T> n) {
		
		Node a = n;
		Node b = a.getRight();
		
		
		Node d = b.getLeft();
		
		
		a.setRight(b.getLeft());
		b.setLeft(a);
		
		
		

		
		return b;
	}
	
	/**
	 * Performs a right rotation on a node
	 * 
	 * @param n The node to have the right rotation performed on
	 * @return The new root of the subtree that is now balanced due to the rotation
	 */
	private Node<T> right(Node<T> n) {
		
		
		Node c = n;
		Node b = c.getLeft();
		
		Node d = b.getRight();
		
		
		c.setLeft(b.getRight());
		b.setRight(c);
	
		
		
		
		
		return b;
	}
	
	
	
	/**
	 * Performs a left right rotation on a node
	 * 
	 * @param n The node to have the left right rotation performed on
	 * @return The new root of the subtree that is now balanced due to the rotation
	 */
	private Node<T> leftRight(Node<T> here) {
		//right on left subtree
		//left itself
		
		here.left = this.left(here.left);
		here = this.right(here);
		
		return here;
		
	}
	
	/**
	 * Performs a right left rotation on a node
	 * 
	 * @param n The node to have the right left rotation performed on
	 * @return The new root of the subtree that is now balanced due to the rotation
	 */
	private Node<T> rightLeft(Node<T> here) {
		here.right = this.right(here.right);
		here = this.left(here);
		
		return here;
	}
	
	/**
	 * Checks to see if the AVL tree is empty
	 * 
	 * @return If the AVL tree is empty or not
	 */
	public boolean isEmpty() {
		return (head == null);
	}
	
	/**
	 * Clears this AVL tree
	 */
	public void clear() {
		head = null;
		return;
	}
	
	/*
	 * this method gives the preorder of the list, 
	 * useful for debugging. 
	 */
	
	private String preorder(){
		
		String res = "< ";
		res += preorder(root);
		return res + " >";
		
		
	}
	
	/*
	 * helper method to preorder, that returns a string of ints. 
	 */
	
	public String preorder(Node here){
		
		String res = "";
		if (here != null)
		{
			res += "\t" + here.getData();
			res += preorder(here.getLeft());
			res += preorder(here.getRight());
			
		}
		
		return res;
		
	}
	
	
	/*
	 * Getters and Setters: Do not modify anything below this point
	 */
	
	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	public int size() {
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	

	
	
	public static class Node<K extends Comparable<K>>{

		private K data;
		private Node<K> left, right;
		private int height;
		private int bf;
		
		public Node(K data) {
			setData(data);
			height = 0;
			bf = -1;
			
			
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

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getBf() {
			return bf;
		}

		public void setBf(int bf) {
			this.bf = bf;
		}
		
		public String toString()
		{
			if (data != null)
				return data.toString();
			return null;
		}
	}
}
