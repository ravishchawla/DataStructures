
public class Linked_List {
	
	private Node head = null;
	private Node tail = null;
	
	public Linked_List() {
		head = new Node(99999);   // Phantom at the Head
		tail = new Node(-99999);  // Phantom at the tail
		head.next = tail;
		tail.prev = head;
	}
	
	/**
	 * @param dis - data value
	 * make a new Node
	 * connect that node back to head
	 *                   forward to head.next
	 * connect next node back to newNode
	 * connect prev node (head.next) to newNode                  
	 */
	public void addToHead(int dis) {
		Node newNode = new Node(dis);
		newNode.next = head.next;
		newNode.prev = head;
		newNode.next.prev = newNode;
		head.next = newNode;
	}

	/**
	 * @param dis - data value
	 * make a new Node
	 * connect that node back to tail_prev 
	 * 				forward to tail
	 * connect tail node prev to newNode
	 * connect tail.prev.next node newNode
	 **/

	public void addToTail(int dis){
		Node newNode = new Node(dis);
		newNode.next = tail;
		newNode.prev = tail.prev;
		tail.prev = newNode;
		newNode.prev.next = newNode;
	}
	
	/**
	 * @param dat the data for the node
	 * start at the head phantom
	 * 	while (not off end) && ( dat  data in node)
	 * 		progress down list
	 *	end
	 **/

	public void addInOrder(int dat)
	{
		Node newNode = new Node(dat);
		Node here = head;

		while(here.next.next != null && here.next.data < dat)
			here = here.next;


		newNode.next = here.next;
		newNode.prev = here;
		newNode.next.prev = newNode;
		newNode.prev.next = newNode;
	}


	public String toString() {
		String res = "[ ";
		Node here = head.next;
		while (here.next != null) {
			res += here + " ";
			here = here.next;
		}
		return res + "]";
	}

	

	private class Node{
		public int data = -1;
		public Node next = null;
		public Node prev = null;
		
		public Node (int dat) {
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
		it.addToHead(20);
		it.addToHead(10);
		it.addInOrder(30);
		it.addToTail(50);
		System.out.println(it);
		System.out.println("Done!");
	}
}
