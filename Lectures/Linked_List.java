
public class Linked_List<E extends Comparable<E>> {
	
	private Node<E> head = null;
	private Node<E> tail = null;
	
	public Linked_List() {
		head = new Node<E>(null);   // Phantom at the Head
		tail = new Node<E>(null);  // Phantom at the tail
		head.next = tail;
		tail.prev = head;
	}
	
	public boolean isEmpty() {
		return head.next.next == null;
	}
	
	/**
	 * 
	 * @param dat the data for the node
	 *    start at head phantom
	 *    while (not off end) && (dat != data in node)
	 *    		progress down list
	 *    end
	 *    delete node here (if it was found)
	 */
	public void delete(E dat) {
		Node<E> here = head;
		while( here.next.next != null  // (not off end) 
				&& dat.compareTo(here.next.data) != 0 ) { //&& (dat != data in node)
				here = here.next;
		}
		if(here.next.next != null) {
			here.next.next.prev = here;
			here.next = here.next.next;			
		}
	}
	
	public E peek() {
		E res = null;
		if(isEmpty()) {
			throw new RuntimeException("don't do that again!");
		} else {
			res = head.next.data;
		}
		return res;
	}
	
	public E pop() {
		E res = null;
		if(isEmpty()) {
			throw new RuntimeException("don't do that again!");
		} else {
			res = head.next.data;
			head.next.next.prev = head;
			head.next = head.next.next;
		}
		return res;
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
//			&& dat > here.next.data ) { //&& (dat > data in node)
			&& dat.compareTo(here.next.data) > 0 ) { //&& (dat > data in node)
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
		
		public Node(E dat) {
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
		Linked_List it = new Linked_List();
		it.addToHead(20);
		it.addToHead(10);
		it.addToTail(50);
		System.out.println("after adding to head and tail - " + it);
		it.addInOrder(30);
		it.addInOrder(5);
		it.addInOrder(60);
		System.out.println("after adding in order - " + it);
		System.out.println("peek returned " + it.peek());
		System.out.println("after peek - " + it);
		System.out.println("pop returned " + it.pop());
		System.out.println("after pop - " + it);
		it.delete(50);
		System.out.println("after deleting 50 - " + it);
		while(!it.isEmpty()) {
			System.out.println("Popped " + it.pop());
		}
		System.out.println("Silly Pop " + it.pop());
		System.out.println("Done!");
	}

}
