
/**
 * This class extends LinkedList, but there's a twist. Read the documentation
 * for each method. Note that the data here is Comparable.
 * 
 * @author Ravish Chawla
 */
public class TwistList<E extends Comparable<E>> extends LinkedList<E> {

	

	
	
	/**
	 * Add a piece of data either at the front of the list if the data
	 * is less than the head. If the data to be added is not less then 
	 * the data at the front of the list then find the first place in the
	 * list where the data is between two other points of data. If this is
	 * never true then place the new piece of data at the end of the list.
	 * 
	 * Last of all call swing with the index at which the new piece of data was added.
	 */
	@Override
	public void add(E e) {
		// TODO Auto-generated method stub
		
		
		Node newNode = new Node(e);
		Node here = head;
		int i = 0;
		if (head.getData() == null)
		{
			head = newNode;
			head.setNext(head);
			
		}
		
		else if (e.compareTo(head.getData()) < 0)
			super.add(e);
		
		else{
			
			
		for ( i = 0;here.getNext() != head; i++, here = here.getNext())
			{
				if (e.compareTo((E)here.getData()) > 0 && e.compareTo((E)here.getNext().getData()) < 0)
					break;

				
			
			}
			
		 newNode.setNext(here.getNext());
		 here.setNext(newNode);
		
		
			
		}
			

	
		
		swing(indexOf(e));
	
		
	}
	
	/**
	 * Reverses the order of the list between the start and stop index inclsively.
	 * 
	 * Assume the indices given are valid and start <= stop
	 * 
	 * @param start The beginning index of the sub section to be reversed
	 * @param stop The end index (inclusive) of the sub section to be reversed
	 */
	public void reverse(int start, int stop) throws IndexOutOfBoundsException{
		

		if (start < 0 || start > size() || stop < 0 || stop > size())
			throw new IndexOutOfBoundsException();
		
		
		System.out.println("size: " + size());
		while (stop > start)
		{
			swap(elementAt(start), elementAt(stop));
			start++;
			stop--;
			
		}
		
		// TODO Auto-generated method stub
	}
	
	private void swap(Node n, Node nn)
	{
		Object dat = n.getData();
		n.setData(nn.getData());
		nn.setData(dat);
	}
	
	/**
	 * This method will take in an index and move everything after 
	 * that index to the front of the list
	 * 
	 * Assume the index given is valid
	 * 
	 * @param index The index at which to cut the list
	 */
	
	
	
	public void flipFlop(int index) throws IndexOutOfBoundsException{
		// TODO Auto-generated method stub

		
	if (index < 0 || index >= size())
		throw new IndexOutOfBoundsException();
		
	System.out.println("size " + size());
//	for (int i = index+1; i <= size(); i++)
	for (int i = size() - 1; i > index; i--)
	{
			
		E data = super.get(size()-1);
		super.remove(size()-1);
		super.add(data);
		
		
		
	}
	

	
	
	}
	
	/**
	 * This method will reverse the order of the first half of the list up to 
	 * the index argument (inclusive), and also reverse the second half of the 
	 * list from index + 1 to the end of the list
	 * 
	 * Assume the index given is valid, however the second half may be empty
	 * 
	 * @param index The index to swing around
	 */
	public void swing(int index) throws IndexOutOfBoundsException{
		
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException();
		
		reverse(0, index);
		
		reverse(index+1, size()-1);
		
		// TODO Auto-generated method stub
	}
}
