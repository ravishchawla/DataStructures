
public class Linked_List<E extends Comparable<E>> {
	
	private Node<E> head = null;
	private Node<E> tail = null;
	
	public Linked_List() {
		head = new Node<E>(null);   // Phantom at the Head
		tail = new Node<E>(null);  // Phantom at the tail
		head.next = tail;
		tail.prev = head;
	}
	
	public boolean isEmpty(){
		return head.next.next == null;
	}


	public E peak()
		{
			E res = null;
				if (isEmpty()){
					throw new RuntimeException("don't do this");
				}
				else {	

				res = head.next.data;
				}

			return res;
		}



	public E pop(){
		
		E res = null;
		 if (isEmpty()){
			 throw new RuntimeException("dont do this");
		 }
		 else {
			 res= head.next.data;
			 head.next.next.prev = head.next;
			 head.next = head.next.next;
		 }
		 return res;
	}
	

	public void delete (E dat){
		Node<E> here = head;
		while (here.next.next != null //(not off end)
				&& dat.compareTo(here.next.data) != 0) { //&& (dat != data in node)
					here = here.next;
				}

					
						if (here.next.next != null)
						{
							here.next.next.prev = here.next;
							here.next = head.next.next;
						}
					
				
	}



	/**
	 * @param dis - data value
	 * make a new Node
	 * connect that node back to head
	 *                   forward to head.next
	 * connect next node back to newNode
	 * connect prev node (head.next) to newNode                  
	 */
	public void addToHead(E dis) {
		Node<E> newNode = new Node<E>(dis);
		newNode.next = head.next;
		newNode.prev = head;
		newNode.next.prev = newNode;
		head.next = newNode;
	}
	
	/**
	 * @param dis - data value
	 * make a new Node
	 * connect that node back to tail_prev
	 *                   forward to tail
	 * connect tail node prev to newNode
	 * connect tail.prev.next node newNode                  
	 */
	public void addToTail(E dis) {
		Node<E> newNode = new Node<E>(dis);
		newNode.next = tail;
		newNode.prev = tail.prev;
		tail.prev = newNode;
		newNode.prev.next = newNode;
	}
	
	/**
	 * 
	 * @param dat the data for the node
	 *    start at head phantom
	 *    while (not off end) && (dat > data in node)
	 *    		progress down list
	 *    end
	 *    insert new node here
	 */
	
	public void addInOrder(E dat) {
		Node<E> newNode = new Node<E>(dat);
		Node<E> here = head;
		while( here.next.next != null  // (not off end) 
//			&& here.next.data < dat ) { //&& (dat > data in node)
			&& dat.compareTo(here.next.data) > 0) {
			here = here.next;
		}
		// node spliced after here
		newNode.next = here.next;
		newNode.prev = here;
		newNode.next.prev = newNode;
		newNode.prev.next = newNode;
	}
	
	public String toString() {
		String res = "[ ";
		Node<E> here = head.next;
		while (here.next != null) {
			res += here + " ";
			here = here.next;
		}
		return res + "]";
	}

	private class Node<E> {
		public E data = null;
		public Node<E> next = null;
		public Node<E> prev = null;
		

		public Node (E dat) {
			data = dat;
			next = null;
			prev = null;
		}
		
		public String toString() {
			return "" + data;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Linked_List it = new Linked_List();
		
		System.out.println(it);
		System.out.println("Done!");
	}

}
