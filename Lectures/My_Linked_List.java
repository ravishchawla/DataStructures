public class My_Linked_List<E extends Comparable<E>>{
	
	private Node<E> head = null;
	private Node<E> tail = null;


	public My_Linked_List(){

	head = new Node(null);
	tail = new Node(null);
	
	head.next = tail;
	tail.prev = head;


	}


	public boolean isEmpty()
	{
		if (head.next.next == null)
			return true;
		return false;
	}



	/**
	 * @param dat the data for the node
	 * 	start at head phanton
	 * 	while (not off end) && (dat != data in node)
	 * 		progress down list
	 * 	end
	 * 	delete node here (if it was found)
	 **/

	public void delete (E dat)
	{
		
		Node<E> bob;
		for (bob = head; bob.next.next != null && dat.compareTo(bob.next.data) != 0; bob = bob.next){}
		
		if (bob.next.next != null)
		{
			
			bob.next.next.prev = bob;
			bob.next = bob.next.next;
		}
	}
			
			


	/**
	*@param dis - data value
	*make a new Node
	*connect that node back to head, forward to head.next
	*connect next node back to newNode
	*connec ptrev node (head.next) to newNode
	**/


	

	public void addToHead(E dis)
	{
		
		Node<E> newNode = new Node<E>(dis);

		newNode.next = head.next;
		newNode.prev = head;

		newNode.next.prev = newNode;
		head.next = newNode;

	}

	/**
	*@param dis - data value;
	*make a new Node
	*connect that node back to tail_prev, forward to tail
	*connect tail node prev to newNode
	*connect tail.prev.next node newNode
	**/
	public void addToTail(E dis)
	{
		Node<E> newNode = new Node<E>(dis);
		newNode.next = tail;
		newNode.prev = tail.prev;
		newNode.prev.next = newNode;
		tail.prev = newNode;

	

	}
	

	
	/**
	 * @param dat the data for the node
	 * start at head phantom
	 * while (not off end) && (dat > data in node)
	 * 	progress down list
	 * end
	 * insert new node here
	 **/

	public void addInOrder(E dat)
	{
	Node<E> tmp, newNode = new Node<E>(dat);
	
	for (tmp =head; tmp.next.next != null && dat.compareTo(tmp.next.data) > 0; tmp = tmp.next);
	
	

	newNode.next = tmp.next;
	newNode.prev = tmp;
	newNode.next.prev = newNode;
	tmp.next = newNode;

	}
	

	public String toString()
	{
		String res = "[";
		Node<E> here = head.next;
		while (here.next != null){
			res += here + "\t";
			here = here.next;
		}

		return res + "]";
	}



private class Node<E>{
	public E data = null;
	public Node<E> next = null;
	public Node<E> prev = null;

	public Node (E dat)
	{
		data = dat;
		next = null;
		prev = null;
	}

	public String toString()
	{
		return "" + data;
	}

}
public static void main(String[] args){
	

	Linked_List it = new Linked_List();
	it.addToHead(20);
	it.addToHead(10);
	it.addToTail(50);
	System.out.println("after adding to head and tail - " + it);
	it.addInOrder(30);
	it.addInOrder(5);
	it.addInOrder(60);
	System.out.println("after adding in order - =" + it);
//	System.out.println("peek returned " + it.peek());
//	System.out.println("after peek - " + it);
//	System.out.println("pop returned " + it.pop());
//	System.out.println("after pop -" + it);
	it.delete(50);
	System.out.println("after deleting 50 - " + it);
//	while(!isEmpty())
//	{
//		System.out.println("Popped" + it.pop());
//		}
//
//		System.out.println("Silly pop" + it.pop());
//		System.out.println("Done!");

}
}


