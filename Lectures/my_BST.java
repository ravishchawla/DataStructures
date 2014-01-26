//Binary Search Tree
//
//A Binary search tree is a Binary Tree in which for every BTNode, all the daa items in its left sub-tree are less than its data and all the data items in its right sub tree are greater than its data. 
//all the elements must have a unique key (no elements can be same)
//
//
//


public class BST{

	BTNode root = null;
	
	public BST(){
		root = null;
	}

	public void insert(int data)
	{
	
		root = insert(root, data);


	}
	

	
	public int count()
	{
		return count(root);

	}


	public int count(BTNode here)
	{	
		int res = 0;
	
		if (here == null)
			return res;
		else{

		//	res = 1+ count(here.left) + count(here.right);
		//
			res = 1 + (count(here.left) > count(here.right) ? count(here.left): count(here.right));
		}

		return res;

	}

	private BTNode insert (BTNode here, int data){

		if (here != null)
		{
			if (data < here.data)
			{
				here.left = insert (here.left, data);
			}

			else if (data > here.data)
			{
				here.right = insert(here.right, data);
			}

			else {
				here = new BTNode (data);
			}

		}

	
		return here;
	}

	private class BTNode
	{
		int data;
		BTNode left, right;
	

		public BTNode(int data)
		{
			this.data = data;
			left = right = null;
		}



		public String toString(){
			return  "" + data;
	}

	}

		public static void main(String[] args){
		int data[] = {50, 25, 75, 12, 35, 60, 90};
		BST it = new BST();

		for (int i = 0; i < data.length; i++)
		{
			it.insert(data[i]);

		}

		System.out.println("tree size = " + it.count());
		it.insert(12);
		System.out.println("done");


		


	}

}
	

