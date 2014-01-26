import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Random;

/**
 * A Binary_Tree is either null or a BTNode containing one data item and
 * references to a left and right sub-tree, each of which is a Binary_Tree.
 * 
 ********************************* 
 * 
 * A Binary Search Tree (BST) is a Binary_Tree in which for every BTNode, all
 * the data items in its left sub-tree are less than its data, and all the data
 * items in its right sub-tree are greater than its data. [unique key required!]
 * 
 */
public class BST {
	private Node root = null;
	private Node checkFrom = null;
	public static PrintStream debug = null;
	public static enum Choice {BASIC, RANDOM, AVL};

	public BST() {
		root = null;
	}

	/**
	 * Delete a value 1. find it on the tree count its children case 0: just
	 * remove it (return null) case 1: return the non-null child case 2: go
	 * left, the right until victim has a right null replace original data with
	 * victim's data delete the victim
	 */

	public boolean isEmpty() {
		return root == null;
	}

	public Node getRoot() {
		return root;
	}

	public void delete(int val) {
		checkFrom = null;
		debug.println("delete( " + val + " )");
		root = delete(root, val);
		checkTree(checkFrom, true);
	}

	private Node delete(Node here, int val) {
		if (here != null) {
			if (val < here.data) {
				here.left = delete(here.left, val);
			} else if (val > here.data) {
				here.right = delete(here.right, val);
			} else {
				here = deleteNode(here);
			}
		}
		return here;
	}

	private Node deleteNode(Node here) {
		// cases 0 and 1
		if (here.left == null) {
			if (here.right != null) {
				here.right.parent = here.parent;
				checkFrom = here.right;
			} else {
				checkFrom = here.parent;
			}
			here = here.right;
		} else if (here.right == null) {
			here.left.parent = here.parent;
			here = here.left;
			checkFrom = here;
			// case 2
		} else {
			int dr = height(here.right);
			int dl = height(here.left);
			boolean moved = false;
			if (dr >= dl) {
				// go right
				// destroy the smallest node on the right
				// find the victim
				Node small = here.right;
				while (small.left != null) {
					small = small.left;
					moved = true;
				}
				here.data = small.data;
				if (small.right != null) {
					small.right.parent = small.parent;
				}
				checkFrom = small.parent;
				if (moved) {
					small.parent.left = small.right;
				} else {
					small.parent.right = small.right;
				}
			} else {
				// go left
				// destroy the largest node on the left
				// find the victim
				Node big = here.left;
				while (big.right != null) {
					big = big.right;
					moved = true;
				}
				here.data = big.data;
				checkFrom = big.parent;
				if (big.left != null)
					big.left.parent = big.parent;
				if (moved) {
					big.parent.right = big.left;
				} else {
					big.parent.left = big.left;
				}
			}
		}
		return here;
	}

	/**
	 * Breadth-first Traversal General Algorithm: Create a Collection, C insert
	 * the starting node while C not empty { remove a node do what you came for
	 * insert the children } Specifically, Collection is a Queue Starting Node
	 * is the root of the tree Operations are enq and deq
	 * 
	 * Amendments: 1. don't stop putting nulls into the Q 2. deq a null, print
	 * it as '**' 3. keep enq'ing its children as long as there are real data in
	 * the Q 4. count number of real vals in the Q
	 * 
	 * @return
	 */
	public String bft() {
		String res = "";
		String spaces = "                                                                      ";
		int w = 240; // width of the "screen"
		int row = 0;
		int reals = 1;
		// create the collection
		Queue<Element> Q = new Queue<Element>();
		if (root != null) {
			// enq the root
			Q.enq(new Element(root, 1));
			while (!Q.isEmpty()) {
				// deq a node
				Element e = Q.deq();
				// Do what you came for
				if (e.level > row) {
					row++;
					w /= 2;
					if (w <= 6)
						w = 6;
					res += "\n" + spaces.substring(0, w / 2 - 2);
				}
				Node node = e.node;
				if (node == null) {
					if (reals > 0) {
						res += "-- ";
						if (row > 1)
							res += spaces.substring(0, w - 2);
						Q.enq(new Element(null, row + 1));
						Q.enq(new Element(null, row + 1));
					}
				} else {
					reals--;
					res += node;
					if (row > 1)
						res += spaces.substring(0, w - 2);
					// enq the children
					if (node.left != null) {
						Q.enq(new Element(node.left, row + 1));
						reals++;
					} else {
						Q.enq(new Element(null, row + 1));
					}
					if (node.right != null) {
						Q.enq(new Element(node.right, row + 1));
						reals++;
					} else {
						Q.enq(new Element(null, row + 1));
					}
				}
			}
		}
		return res;
	}

	public String inOrder() {
		String res = "[ ";
		res += inOrder(root);
		return res + " ]";
	}

	private String inOrder(Node here) {
		String res = "";
		if (here != null) {
			res += inOrder(here.left);
			res += here.toString();
			res += inOrder(here.right);
		}
		return res;
	}

	public String preOrder() {
		String res = "[ ";
		res += preOrder(root);
		return res + " ]";
	}

	private String preOrder(Node here) {
		String res = "";
		if (here != null) {
			res += here.toString();
			res += preOrder(here.left);
			res += preOrder(here.right);
		}
		return res;
	}

	public int count() {
		return count(root);
	}

	/**
	 * recursively count the tree. termination: here is null, count = 0
	 * otherwise, add 1 to counts for left and right sub-trees
	 * 
	 * @param here
	 *            - the current node
	 * @return - the count of this sub-tree
	 */
	private int count(Node here) {
		int res = 0;
		if (here != null) {
			res = 1 + count(here.left) + count(here.right);
		}
		return res;
	}

	public int height() {
		return height(root);
	}

	/**
	 * recursively find depth of the tree. termination: here is null, count = 0
	 * otherwise, add 1 to max depth of left and right sub-trees
	 * 
	 * @param here
	 *            - the current node
	 * @return - the count of this sub-tree
	 */
	private int height(Node here) {
		int res = 0;
		if (here != null) {
			int l = height(here.left);
			int r = height(here.right);
			res = 1 + (l > r ? l : r);
		}
		return res;
	}

	public void insert(int data) {
		checkFrom = null;
		root = insert(root, data, null);
		checkTree(checkFrom, false);
	}

	/**
	 * recursively find leaf node to add termination: here == null return the
	 * new node otherwise, go to left or right recursively storing modified left
	 * or right subtree return the node given
	 * 
	 * @param here
	 *            - current node in the tree
	 * @param data
	 *            - value to add
	 * @param par
	 *            - parent node of here
	 * @return
	 */
	private Node insert(Node here, int data, Node par) {
		if (here != null) {
			here.parent = par;
			// debug.println("Parent of " + here + " is " + par);
			if (data < here.data) {
				here.left = insert(here.left, data, here);
			} else if (data > here.data) {
				here.right = insert(here.right, data, here);
			}
		} else {
			here = new Node(data, par);
			checkFrom = here;
		}
		return here;
	}

	public Node findLeaf() {
		return findLeaf(root);
	}

	public Node findLeaf(Node here) {
		Node res = null;
		if (here != null) {
			// is this what I want?
			if (here.left == null && here.right == null) {
				res = here;
			} else {
				res = findLeaf(here.left);
				if (res == null) {
					res = findLeaf(here.right);
				}
			}
		}
		return res;
	}

	public Node findOne() {
		return findOne(root);
	}

	public Node findOne(Node here) {
		Node res = null;
		if (here != null) {
			// is this what I want?
			if (here.left == null ^ here.right == null) {
				res = here;
			} else {
				res = findOne(here.left);
				if (res == null) {
					res = findOne(here.right);
				}
			}
		}
		return res;
	}

	public Node findTwo() {
		return findTwo(root);
	}

	public Node findTwo(Node here) {
		Node res = null;
		if (here != null) {
			// is this what I want?
			if (here.left != null && here.right != null) {
				res = here;
			} else {
				res = findTwo(here.left);
				if (res == null) {
					res = findTwo(here.right);
				}
			}
		}
		return res;
	}

	public void checkParents() {
		checkParents(root);
	}

	public void checkParents(Node here) {
		if (here != null) {
			if (here.left != null) {
				if (here.left.parent != here) {
					debug.println("parent of " + here.left + " is not " + here);
					throw new RuntimeException("sorry, charlie!");
				}
				checkParents(here.left);
			}
			if (here.right != null) {
				if (here.right.parent != here) {
					debug.println("parent of " + here.right + " is not " + here);
					throw new RuntimeException("sorry, charlie!");
				}
				checkParents(here.right);
			}
		}
	}

	private void checkTree(Node here, boolean keepGoing) {
		boolean more = true;
		debug.println("checkTree(" + here + ", " + keepGoing + ")");
		while (more && here != null) {
			// search parents for |bf| > 1
			int bf = height(here.right) - height(here.left);
			if (bf > 1) {
				// do the rotation(s)
				rotate_left(here);
				// if keepGoing, continue to the root
				more = keepGoing;
			}
			if (bf < -1) {
				// do the rotation(s)
				rotate_right(here);
				// if keepGoing, continue to the root
				more = keepGoing;
			}
			here = here.parent;
		}
	}
 
	/*
	 * rotating   B                     C
	 *        A       C            B         D
	 *             o     D       A   o          x
	 *                     x        
	 *             
	 */
	private void rotate_left(Node B) {
		int oops = height(B.right.right) - height(B.right.left);
		if(oops < 0) {
			rotate_right(B.right);
		}
		// save the three links
		Node C = B.right;
		Node orphan = C.left;
		Node B_parent = B.parent;
		// make B the left child of C
		C.left = B;
		B.parent = C;
		// put the orphan to the right of B
		B.right = orphan;
		// Connect C to B's parent
		change_child(B_parent, B, C);
		C.parent = B_parent;
	}

	/*
	 * rotating   B                     C
	 *        C       A            D         B
	 *     D    o                x        o     A
	 *  x                           
	 *             
	 */

	private void rotate_right(Node B) {
		int oops = height(B.left.right) - height(B.left.left);
		if(oops > 0) {
			rotate_left(B.left);
		}
		// save the three links
		Node C = B.left;
		Node orphan = C.right;
		Node B_parent = B.parent;
		// make B the left child of C
		C.right = B;
		B.parent = C;
		// put the orphan to the right of B
		B.left = orphan;
		// Connect C to B's parent
		change_child(B_parent, B, C);
		C.parent = B_parent;
	}

	/**
	 * 
	 * @param par - the parent
	 * @param was - what one of its children was
	 * @param is  - what that child should be
	 */
	private void change_child(Node par, Node was, Node is) {
		if (par == null) {
			root = is;
		} else {
			if (par.right == was) {
				par.right = is;
			} else {
				par.left = is;
			}
		}
	}

	private class Node {
		public Node parent;
		public int data;
		public Node left;
		public Node right;

		public Node(int data, Node par) {
			this.data = data;
			left = null;
			right = null;
			parent = par;
			debug.println("Parent of " + this + " is " + par);
		}

		public String toString() {
			return "" + data + " ";
		}
	}

	private class Element {
		public Node node;
		public int level;

		public Element(Node n, int lev) {
			node = n;
			level = lev;
		}

		public String toString() {
			return node.toString();
		}
	}

	private class Queue<E> extends LinkedList<E> {
		public void enq(E it) {
			add(it);
		}

		public E deq() {
			return remove();
		}
	}

	/**
	 * Comprehensive Test Suite
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			debug = new PrintStream(new FileOutputStream("debug.log"));
		} catch (IOException e) {
			System.out.print("Error " + e + " opening debug.log");
		}
		debug.println("debug log opened");
		BST it = new BST();
		String title = "Tree";
		Choice choice = Choice.AVL;
		switch (choice) {
		case AVL:
			int data[] = { 25, 50 };
			for (int i = 0; i < data.length; i++) {
				it.insert(data[i]);
			}
			debug.println("          tree size = " + it.count() + " depth = "
					+ it.height());
			debug.println("In Order:" + it.inOrder());
			debug.println("Pre-Order: " + it.preOrder());
			debug.println(title + ":" + it.bft());
			it.checkParents();
			it.insert(75);
			title += " with " + 75;
			debug.println(title + ":" + it.bft());
			it.checkParents();
			it.insert(90);
			title += " && " + 90;
			debug.println(title + ":" + it.bft());
			it.checkParents();
			it.insert(110);
			title += " && " + 110;
			debug.println(title + ":" + it.bft());
			it.checkParents();
			it.insert(12);
			title += " && " + 12;
			debug.println(title + ":" + it.bft());
			it.checkParents();
			it.insert(6);
			title += " && " + 6;
			debug.println(title + ":" + it.bft());
			it.checkParents();
			it.insert(130);
			title += " && " + 130;
			debug.println(title + ":" + it.bft());
			it.checkParents();
			it.insert(120);
			title += " && " + 120;
			debug.println(title + ":" + it.bft());
			it.checkParents();
			it.insert(3);
			title += " && " + 3;
			debug.println(title + ":" + it.bft());
			it.checkParents();
			it.insert(4);
			title += " && " + 4;
			debug.println(title + ":" + it.bft());
			it.checkParents();
			it.delete(110);
			title += " without " + 110;
			debug.println(title + ":" + it.bft());
			it.checkParents();
			it.delete(75);
			title += " without " + 75;
			debug.println(title + ":" + it.bft());
			it.checkParents();
			break;
		case RANDOM:
			Random rand = new Random();
			for (int i = 0; i < 10; i++) {
				it.insert(rand.nextInt(100));
			}
			debug.println("          tree size = " + it.count() + " depth = "
					+ it.height());
			debug.println("In Order:" + it.inOrder());
			debug.println("Pre-Order: " + it.preOrder());
			debug.println(title + ":" + it.bft());
			it.checkParents();
			Node getIt = it.findLeaf();
			it.delete(getIt.data);
			title += " without " + getIt.data;
			debug.println(title + ":" + it.bft());
			it.checkParents();
			getIt = it.findOne();
			it.delete(getIt.data);
			title += " && " + getIt.data;
			debug.println(title + ":" + it.bft());
			it.checkParents();
			getIt = it.findTwo();
			title += " && " + getIt.data;
			it.delete(getIt.data);
			debug.println(title + ":" + it.bft());
			it.checkParents();
			while (!it.isEmpty()) {
				getIt = it.getRoot();
				title += " && " + getIt.data;
				it.delete(getIt.data);
				debug.println(title + ":" + it.bft());
				it.checkParents();
			}
			break;
		case BASIC:		
			int bdata[] = { 50, 25, 75, 12, 35, 60, 90, 
					5, 17, 30, 40, 55, 99, 2, 45, 52, 3 }; 
			for (int i = 0; i < bdata.length; i++) { 
				it.insert(bdata[i]); }
			
			debug.println("tree size = " + it.count() + " height = " + it.height()); 
			debug.println("In Order:" + it.inOrder());
			debug.println("Pre-Order: " + it.preOrder()); 
			debug.println(title + ":" +	it.bft()); 
			it.checkParents(); 
			it.delete(45); 
			title += " without " + 45;
			debug.println(title + ":" + it.bft()); 
			it.checkParents(); 
			it.delete(90);
			title += " && " + 90; 
			debug.println(title + ":" + it.bft());
			it.checkParents(); 
			it.delete(25); 
			title += " && " + 25; 
			debug.println(title + ":" + it.bft()); it.checkParents(); 
			it.delete(50); 
			title += " && " + 50;
			debug.println(title + ":" + it.bft());
			break;
		}
		System.out.println("Done!");
	}
}
