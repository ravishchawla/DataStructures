import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;





public class Test {


	public static void main(String[] args){
		
		Test test = new Test();
		
		LinkedList<Thing> l = test.ll(5);
		System.out.println(l.size());
		l.clear();
		System.out.println(l.size());
		System.out.println(l.getHead());
		
		//System.out.println(test.t(0) + " , " + test.a(r, 0));
		//System.out.println(test.t(0) + " , " + test.a(r, 1));
		//System.out.println(ll.isEmpty());
		
		
		
		
		
	}
	
	
	public LinkedList<Thing> ll(int n) {
		LinkedList<Thing> ll = new LinkedList<>();
		ll.setHead(l(n));
		ll.setSize(n);
		return ll;
	}
	
	/**
	 * constructs a twist list containing 0 to n-1 (inclusive)
	 */
	public TwistList<Thing> tl(int n) {
		TwistList<Thing> tl = new TwistList<>();
		tl.setHead(l(n));
		tl.setSize(n);
		return tl;
	}
	
	/**
	 * creates a circular list from 0 to n - 1 (inclusive)
	 * and returns the head node 
	 */
	public Node<Thing> l(int n) {
		if (n <= 0) return null;
		Node<Thing> h = n(0);
		Node<Thing> t = h;
		for (int i = 1; i < n; i++) {
			t.setNext(n(i));
			t = t.getNext();
		}
		t.setNext(h);
		return h;
	}
	
	/**
	 * Creates a node containing a thing
	 * representing the given int
	 */
	public Node<Thing> n(int i) {
		return new Node<>(t(i));
	}
	
	/**
	 * Manually advances the node n, a nodes forward,
	 * then returns the data
	 */
	public Thing a(Node<Thing> n, int a) {
		for (int i = 0; i < a; i++) {
			n = n.getNext();
		}
		return n.getData();
	}
	
	
	/**
	 * Prints out your Linked List
	 */
	public void print(LinkedList<?> l) {
		if (l == null) return;
		Node<?> n = l.getHead(), p = n;
		System.out.print("[");
		do {
			if (p == null) continue;
			System.out.print(p.getData() + ", ");
			p = p.getNext();
		} while(p != n && p != null);
		System.out.println("]");
	}
	
	/**
	 * Returns a thing representing the given integer
	 */
	public Thing t(int i) {
		return new Thing(i);
	}
	
	/**
	 * A comparable class used for testing, gives each object
	 * created a unique identifier, that is not factored into
	 * equals or compareTo. Useful for testing purposes.
	 */
	public static class Thing implements Comparable<Thing> {
		private static int counter = 0;
		
		final int id = counter++;
		int i;
		
		public Thing(int i) {
			this.i = i;
		}
		
		@Override
		public int compareTo(Thing tht) {
			return Integer.compare(i, tht.i);
		}
		
		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Thing) {
				return ((Thing) obj).i == i;
			}
			return false;
		}
		
		@Override
		public int hashCode() {
			return i;
		}
		
		@Override
		public String toString() {
			return i + " (" + id + ")";
		}
	}
	
	
}
