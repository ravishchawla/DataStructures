import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This is a circular, singly linked list.
 
 *
 *@author Ravish Chawla
 */
public class LinkedList<E> implements List<E> {

	protected Node<E> head;
	
	protected int size;
	
	
/**
 * this constructor initializes 
 * the head node as a null value, 
 * and sets the initial size
 */
	public LinkedList()
	{
		head = new Node(null);
		head.setNext(head);
		size = 0;
	}
	
	public static void main(String[] stuff){
		
		LinkedList<Integer> things = new LinkedList<Integer>();
		things.add(0);
		System.out.println(things.size);
		
		
	}

	@Override
	
	/* create a new node of type e
	 * 
	 * set it's next to head's next
	 * set head to e
	 * 
	 */
	public void add(E e) {
		// TODO Auto-generated method stub

		
		if (isEmpty())
		{
			System.out.println("it's empty");
			head = new Node(e);
			head.setNext(head);
			
		}
		
		
		
		
		else
		{
			
			Node here = head;
			
			while (here.getNext() != head)
			{
				here = here.getNext();
			}
			
			Node newNode = new Node(e);
			newNode.setNext(head);
			head = newNode;
			here.setNext(newNode);
			
		}
		
		size();
		
		
	}

	/*
	 * You will want to look at Iterator, Iterable, and 
	 * how to use a for-each loop for this method.
	 * 
	 * call add(..) on each of the elements. 
	 * 
	 */
	@Override
	
	
	
	public void addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		
		for (E ee: c)
		{
			
			add(ee);
		}
		
		
		size();
	}

	
	/**
	 * this method clears all the elemnts
	 * in the list, by setting head to  null
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
	
		
		head.setNext(null);
		head = new Node(null);
	
	
		
		size();
	}

	
	/**
	 * this method checks if an object
	 * is in the list. it returns true
	 * for a null input only if 
	 * the only element in the list is also
	 * null, otherwise, it returns true if
	 * the element is in list. 
	 * 
	 * @return boolean whether a value is in list
	 * @param o object to search for. 
	 */
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		
		Node here = head;
		
		if (here.getData() == null)
			return (here.getData() == o);
		
		
		while (here.getNext() != head)
		{
			
			
			if (here.getData().equals(o))
					return true;
			
			here = here.getNext();
		}
		
		return(here.getData().equals(o));
			
		
	}

	/**
	 * 
	 * this method returns the data
	 * at a particular index. 
	 * 
	 * @param index what to return
	 * @return here.getData() the data to be returned
	 * @throws IndexOutOfBoundsException if index is out of bounds. 
	 * 
	 */
	@Override
	public E get(int index) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		
		Node here = head;
		
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		
		for (int i = 0; i < index && here.getNext() != head; i++, here = here.getNext())
		{
			
		}
		
		return (E) here.getData();
	}
	
	/**
	 * This method returns a reference
	 * to the node at a particular index. 
	 * it is similar to the the get(..) method, 
	 * 
	 * it is utilized in the TwistList class. 
	 * 
	 * @param index the index to where to find the element
	 * @return reference to the node at that point. 
	 * 
	 * @throws IndexOutOfBoundsException if no element is found. 
	 */
	
	public Node elementAt(int index) throws IndexOutOfBoundsException
	{
		Node here = head;
		
		if (index < 0 || index >= size())
			{
			
			throw new IndexOutOfBoundsException();
			}
		
		for (int i = 0; i< index && here.getNext() != head; i++, here = here.getNext())
		{
			
			
		}
		
		return here;
	}

	/**
	 * this method returns the index where an object is. 
	 * 
	 * @param o the object of which index is to be found
	 * @return the indx where it is
	 * @throws NullPointerException if the object is not in the list. 
	 */
	
	@Override
	public int indexOf(Object o) throws NullPointerException{
		// TODO Auto-generated method stub
		Node here = head;
		int i = 0;
		
		for (i = 0; here.getNext() != head && !here.getData().equals(o); i++, here = here.getNext())
		{
			
		}
		
		if (here.getData().equals(o))
			return i;
		else
			throw new NullPointerException();
		
	}

	/**
	 * this method returns true if the list
	 * is empty, or false if not. 
	 * 
	 * @return boolean value corresponding to emptiness. 
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (head.getData() == null);
	}

	
	/**
	 * this method removes an element at a particular
	 * index. it returns the element it has just removed. 
	 * 
	 * @param index index where the elemnt is being looked for
	 * @return stuff the data that has just been removed. 
	 * @throws IndexOutOfBoundsException if index is out of bounds. 
	 */
	@Override
	public E remove(int index) throws IndexOutOfBoundsException{
		// TODO Auto-generated method stub

	Node here;	
	E stuff;
		if (index < 0 || index >= size())
			throw new IndexOutOfBoundsException();
		
	
		
		if (index == 0)
		{
			
			here = head;
			
			while (here.getNext() != head)
			{
				
				here = here.getNext();
				
			}
			
			stuff = head.getData();
			head = head.getNext();
			here.setNext(head);
			
			
			
		}
		

		else{
		here = head;
		for (int i = 1; i < index && here.getNext() != head; i++, here = here.getNext())
			{
			
			}
		
			stuff = (E)here.getNext().getData();
			here.setNext(here.getNext().getNext());
		}
		
		size();
		
		return stuff;
	}
	
	/**
	 * this method does the same thing as the above, 
	 * but it takes in a object to compare to, not an idex. 
	 * 
	 * @param o the object of which index is being searched for
	 * @return data the data that has just been removed. 
	 * @throws NullPointerException if the object is not in the list. 
	 */

	@Override
	public E remove(Object o) throws NullPointerException{
		// TODO Auto-generated method stub
		
		
		int index = indexOf(o);
		
		
		
		E data = get(index);
		
		
		
		remove(index);
		
		
		
		
		return data;
	}

	
	/**
	 * this method sets the data of a node to a particular value. 
	 * 
	 * @param index node where element has to be changed. 
	 * @param e the new value to set to. 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. 
	 */
	@Override
	public E set(int index, E e) throws IndexOutOfBoundsException{
		// TODO Auto-generated method stub
	
		if (index < 0 || index > size())
			throw new IndexOutOfBoundsException();
		
		
		Node here = head;
		for (int i = 0; i < index && here.getNext() != head; i++, here = here.getNext())
		{
			
		}
		
		here.setData(e);
		

		
		
		return e;
	}

	@Override
	
	/**
	 * 
	 * this method returns the size of the linked list. 
	 * it returns the total number of elements, not the 
	 * index of the last node (so the count starts at 1, not 0)
	 * 
	 * @return the size of the array. 
	 * 
	 */
	public int size() {
		// TODO Auto-generated method stub
		Node here = head;
		
		if (isEmpty())
			return 0;
		
		int i = 0;
		for ( i = 1; here.getNext() != head; i++, here = here.getNext())
		{
			
		}
		
		size = i;
		
		return i;
	}

	
	/**
	 * this method returns a string representation of the linked
	 * list. 
	 * 
	 * @return res the string containing list of the elements. 
	 */
	public String toString()
	{
		String res = "<";
		Node here = head;
		
		if (here.getData() == null)
			res += " >";
		
		else{
		
		while (here.getNext() != head)
		{
			res += " " + here;
			here = here.getNext();
			
			
		}
			
		
		res += " " + here + " >";
		}
		
		return res;
		
	}
	
	/*
	 * The following methods are for grading. Do not modify them, and you do not
	 * need to use them.
	 */

	public void setSize(int size) {
		this.size = size;
	}

	public Node<E> getHead() {
		return head;
	}

	public void setHead(Node<E> head) {
		this.head = head;
	}
	
	
	
}
