

import java.io.File;
import java.io.FileWriter;
import java.util.*;


/*
    @author Ravish Chawla


 */
public class SplayTrees<T extends Comparable<? super T>> {

	private Node<T> root;

	private int size;

    private Node setToSplayLaterOn;
    public static  void main(String[] args) throws Exception{



	    
	SplayTrees<Integer> d7000 = new SplayTrees<Integer>();
	
	List<Integer> list = Arrays.asList(70,9,52,56,46,58,65,32,15,22,3);

	
	for (Integer t: list){
		
		System.in.read();
		d7000.add(t);
		System.out.println(t + "> " + d7000.preorder(d7000.root));
		System.in.read();
		
	}

	Collections.shuffle(list);
	for (Integer t: list)
	{
		System.in.read();
		d7000.remove(t);
		System.out.println(t + "> " + d7000.preorder(d7000.root));
		System.in.read();
		
	}
	
	
    		
            System.out.println("done");


    }


    /*

        preorder method for debugging.

     */
    private String preorder(Node here){

        String list = "";
        if (here!=null){
        list = list + " " + here;
        list = list + " " + preorder(here.left);
        list = list + " " + preorder(here.right);

        }
        return list + "";



    }


	/**
	 * adds elements to list and splays them.
	 * 
	 * @param data
	 */
	public void add(T data) {  

        root = add(root,data,null);
        root = brutforce(root,null);

        splay(setToSplayLaterOn);
        root.parent = null;

        size();


		// TODO Auto-generated method stub
	}

    /*
        helper method to add(..). returns reference to new updated node.

     */
    public Node<T> add(Node here, T data, Node parent){

        Node newNode = new Node((data));


        if (here != null){

            here.parent = parent;
        if (here.compareTo(newNode) > 0)
        {
            here.left = add(here.left, data,here);


        }


        else if (here.compareTo(newNode) < 0)
        {
            here.right = add(here.right, data,here);
        }
        }

        else
        {

            here = new Node((data));
            here.parent = parent;
            setToSplayLaterOn = here;

        }


        return here;

    }


	/**

        removes an element from the splay tree.
        only if its in list. than splays its parent.
	 * 
	 * @param
	 * @return
	 */


    public T remove(T data){

        Node<T> newNode = new Node<T>(data);
        Node parent = root, child = root;




        if (containsWithoughtSplayMethod(data)){
            while (child != null && newNode.compareTo(child) != 0)
            {parent = child;

                //if (this.comparator(data, child.getData()) <= 0)
                if (newNode.compareTo(child) <= 0)
                    child = child.getLeft();
                else
                    child = child.getRight();


            }
            Node originalData = child;
            boolean ro = originalData.compareTo(root) == 0;
            child = remove(parent,child);


            //if (this.comparator(originalData.getData(), root.getData()) == 0)
            if(ro)
                root = child;

                //else if (parent.getLeft() != null && this.comparator(parent.getLeft().getData(),originalData.getData()) == 0)
            else if (parent.getLeft() != null && parent.getLeft().compareTo(originalData) == 0)
                parent.setLeft(child);
            else
                parent.setRight(child);


            root = brutforce(root,null);

            if (root != null && !root.equals(child))
                splay(parent);

            size();

           // if (size <= 1)
             //   root = null;

            size();
        }
        else
            return null;




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

                //        if (this.comparator(preParent.getData(), child.getData()) == 0)
                if (preParent.compareTo(child) == 0)
                {

                    preParent.setLeft(preChild.getLeft());

                }
                else
                    preParent.setRight(preChild.getLeft());




            }


        }
        root = brutforce(root,null);
        return child;


    }


    /**

        this method updates all the parent
     references in the list, after a splay

	 */

      private Node brutforce(Node toot, Node parent){

          if (toot != null){
            toot.parent = parent;
            toot.left = brutforce(toot.left, toot);
            toot.right = brutforce(toot.right,toot);



          }
          return toot;

      }
                                          /*
          method splays a node.
                                          it checks for a parent and grandparent and
                                          depending on its position, calls the right zig,zigzig,or zigzag
                                            it's in a recursive call, that stops when
                                            the node becomes the root.
                                           */

    public Node splay(Node here)
    {
          Node parent = here.parent;
          Node grandparent = null;
          if (parent != null)
              grandparent = parent.parent;

          Node R = null;
          if (grandparent!= null && !grandparent.equals(root))
        	  R = grandparent.parent;
          
          //System.out.println("grandparent is " + grandparent);
          
        if (parent == null)
        {
            //System.out.println("parent is null!!!!!");
            root = here;
        }


          if (here.compareTo(root) == 0)
          {
              //root = brutforce(root,null);
              return root;
          }



        else if (root.getLeft() != null && root.getLeft().compareTo(here) == 0)

          {
              //root = brutforce(root,null);
              root = zig(root.getLeft());
              return root;
          }


        else if (root.getRight() != null && root.getRight().compareTo(here) == 0)
          {
             // root = brutforce(root,null);
              root = zig(root.getRight());
              return root;
          }

        else
          {
        		
        	Node c = here;
              if (grandparent != null){

                  if (grandparent.left != null && grandparent.left.compareTo(parent) == 0)
                  {
                      if (parent.left!=null&& parent.left.compareTo(here) == 0)
                      {
                    	  
                    	  c = zigzig(c);
                    	  
                      }

                      else if (parent.right!=null&& parent.right.compareTo(here) == 0)
                                                     here = zigzag(here);

                  }

                  else if (grandparent.right!=null && grandparent.right.compareTo(parent) == 0)
                  {

                      if (parent.left!=null&& parent.left.compareTo(here) == 0)
                                        here = zigzag(here);

                      else if (parent.right!=null && parent.right.compareTo(here) == 0)
                                        c = zigzig(c);

                  }

                  if (!grandparent.equals(root) && R!=null)
                  {
                	  
                	  //System.out.println("now grandparent is " + grandparent);
                	  //System.out.println("and R is " + R);
                	  if (R.left != null && R.left.equals(grandparent))
                	  {
                		  R.left = c;
                	  }
                	  
                	  else if (R.right != null && R.right.equals(grandparent))
                		  R.right = c;
                	  
                	  c.parent = R;
                  }
                  
                  else
                  {
                      root = c;
                      root.parent = null;
                  }
                  
                  
              }



          }

                   root = brutforce(root,null);
                   return splay(here);

       //   return null;
    }

    /*
        zig rotation for a node.
        only works if node is child of root.

     */

    public Node zig(Node here)
    {

        Node A = here.parent;
        Node B = here;
        Node D;

        if (A.getLeft() != null && A.getLeft().compareTo(B) == 0)
        {

            D = B.getRight();


            root = B;
            B.setRight(A);

            A.setLeft(D);
         //   A.getParent().setRight(A);

        }

        else if (A.getRight() != null && A.getRight().compareTo(B) == 0)
        {
            D = B.getLeft();

            root = B;
            B.setLeft(A);
            A.setRight(D);
         //   A.getParent().setLeft(B);


        B.setParent(null);
        A.setParent(B);
        if (D != null)
            D.setParent(A);



        }


         return B;
    }

    /*

        zigzig rotation for a node. calls
        double-right or double-left rotation
        on a node.
     */


    public Node zigzig(Node c)
    {
    	Node mid = c.parent;
    	Node parent = mid.parent;
    	
    	if (parent.left != null && parent.left.equals(mid))
    	{
    		mid = right(mid.parent);
    		mid = right(mid);
    	}
    	
    	else if (parent.right != null && parent.right.equals(mid))
    	{
    		mid = left(mid.parent);
    		mid = left(mid);
    	}
    	
    	
    	return mid;
    }
  
    
             /*
             zigzag rotation for a node.
             does a left-right or right-left rotation

              */
    
    public Node zigzag(Node c){
    	
    	Node mid = c.parent;
    	Node parent = mid.parent;
    	
    	if (parent.left != null && parent.left.equals(mid))
    	{
    		mid = left(mid);
    		parent.left = mid;
    		mid.parent = parent;
    		mid = right(mid.parent);
    	}
    	
    	else if (parent.right != null && parent.right.equals(mid))
    	{
    		mid = right(mid);
    		parent.right = mid;
    		mid.parent = parent;
    		mid = left(mid.parent);
    	}
    	
    	return mid;
    }
    	

    /*
        a right rotation method,
        like an AVL rotation.

     */
    public Node right (Node A)
    {

    	Node left = A.left;
    	Node orphan = left.right;
    	
    	left.right = A;
    	A.left = orphan;
    	A.parent = left;
    	if (orphan!=null)
    		orphan.parent = A;
    	
    	
    	return left;
    	



    }

    /*
        a left rotation method,
        similar to AVL rotation.

     */

    public Node left (Node A){


    	Node right = A.right;
    	Node orphan = right.left;
    	
    	right.left = A;
    	A.right = orphan;
    	A.parent = right;
    	if (orphan!=null)
    		orphan.parent = A;
		

		
		return right;

    }

    /*

        gets the data, if in tree,
        splays it. else ignores it.
     */


	public T get(T data) {
		// TODO Auto-generated method stub

        contains(data);


        return data;
	}

    /*
        a contains method that does not call
        splay. debugging method.

     */

    private boolean  containsWithoughtSplayMethod(T data){

        return(contains(root,data));

    }

	/**
	 * Splay the object found matching the data, do nothing if
	 * o is not in the tree
	 * 
	 * @param
	 * @return
	 */

    /*
        a contains method, returns boole
        value. if true, splays it, else ignores it.

     */
    public boolean contains(T data) {


        boolean yo = contains(root,data);
        if(yo){
        root = brutforce(root,null);
        splay(setToSplayLaterOn);
        root=brutforce(root,null);
        }
            return yo;
    }

    /*
     * helper method to contains(..).
     */
    private boolean contains(Node n, T data){

        Node newNode = new Node(data);
        if (n == null)
            return false;

       // if (this.comparator(n.getData(), data) > 0)
          if (n.compareTo(newNode) > 0)
            return contains(n.left, data);
        else if (n.compareTo(newNode) < 0)
            return contains(n.right, data);

        else if (n.compareTo(newNode) == 0)
          {
              setToSplayLaterOn = n;
              return true;

          }
        else
            return false;


    }

    /*
        returns the size of the tree.

     */

    public int size() {
        int i = 0;

        return (size = size(root));

    }

    /*

        helper method for size
     */

    private int size(Node n)
    {
        int nn = 0;

        if (n != null)
            nn =  (1 + size(n.getLeft()) + size(n.getRight()));

        return nn;

    }

    /*

    returns true if size is 0, else not.
     */



    public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size() == 0);
	}

	/*
	 * These methods are for grading, don't modify them
	 */



	public void setSize(int size) {
		this.size = size;
	}

	public Node<T> getRoot() { 
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}


	public static class Node<E extends Comparable<? super E>> implements Comparable<Node<E>>{

		private Node<E> parent, left, right;
		private E data;

		public Node(E data) {
			this.data = data;
		}

		public Node<E> getParent() {
			return parent;
		}

		public void setParent(Node<E> parent) {
			this.parent = parent;
		}

		public Node<E> getLeft() {
			return left;
		}

		public void setLeft(Node<E> left) {
			this.left = left;
		}

		public Node<E> getRight() {
			return right;
		}

		public void setRight(Node<E> right) {
			this.right = right;
		}

		public E getData() {
			return data;
		}
		
		public void setData(E data) {
			this.data = data;
		}

        public String toString()
        {
            return "`" + data + "`";
        }


		@Override
		public int compareTo(Node<E> tht) {
			if (data == null && tht.data == null) return 0;
			if (tht.data == null) return -1332;
			if (data == null) return 1332;
			return data.compareTo(tht.data);
		}
	}

}
