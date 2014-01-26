import java.awt.List;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * This is an implementation of a heap that is backed by an array.
 * 
 * This implementation will accept a comparator object that can be used to
 * define an ordering of the items contained in this heap, other than the
 * objects' default compareTo method (if they are comparable). This is useful if
 * you wanted to sort strings by their length rather than their lexicographic
 * ordering. That's just one example.
 * 
 * Null should be treated as positive infinity if no comparator is provided. If
 * a comparator is provided, you should let it handle nulls, which means it
 * could possibly throw a NullPointerException, which in this case would be
 * fine.
 * 
 * If a comparator is provided that should always be what you use to compare
 * objects. If no comparator is provided you may assume the objects are
 * Comparable and cast them to type Comparable<T> for comparisons. If they
 * happen to not be Comparable you do not need to handle anything, and you can
 * just let your cast throw a ClassCastException.
 * 
 * This is a minimum heap, so the smallest item should always be at the root.
 * 
 * @param <T>
 *            The type of objects in this heap
 */
public class BinaryHeap<T> implements Heap<T> {

	/**
	 * The comparator that should be used to order the elements in this heap
	 */

    public static void main(String[] args) throws IOException{


        BinaryHeap<Integer> heeep = new BinaryHeap<Integer>();
        System.out.println(heeep.toString());

        java.util.List<Integer> stuff = Arrays.asList(15, 51, 93, 27, 68, 72, 70, 1, 86, 43, 99, null, 19, 96, 52, 73, 4, 44, 65, 64, 14, 66, 25, 92, 90, 71, 69, 77, 67, 45);
        //null = 102400


        for (Integer in: stuff){
        //
        //
       //  System.in.read();
        	System.out.print(in + ": "); heeep.add(in);
        //	System.in.read();
        	
        }
        
        while (!heeep.isEmpty()){
        	//System.in.read();
            System.out.print(heeep.size-1 + ": ");	heeep.remove();
        	//System.in.read();
            stuff = stuff;
        }

        
    }


	private Comparator<T> comp;

	/**
	 * The backing array of this heap
	 */
	private T[] data;

	/**
	 * The number of elements that have been added to this heap, this is NOT the
	 * same as data.length
	 */
	private int size = 0;
	public float balance_factor = 0.64f;

	/**
	 * Default constructor, this should initialize data to a default size (11 is
	 * normally a good choice)
	 * 
	 * This assumes that the generic objects are Comparable, you will need to
	 * cast them when comparing since there are no bounds on the generic
	 * parameter
	 */
	public BinaryHeap() {
		// TODO Implement this.
		
	
	data = (T[])(new Object[11]);
        this.comp = new Compare();

	}

	/**
	 * Constructor that accepts a comparator to use with this heap. Also
	 * initializes data to a default size.
	 * 
	 * When a comparator is provided it should be preferred over the objects'
	 * compareTo method
	 * 
	 * If the comparator given is null you should attempt to cast the objects to
	 * Comparable as if a comparator were not given
	 * 
	 * @param comp
	 */
	public BinaryHeap(Comparator<T> comp) {
		// TODO Implement this.

		this.comp = comp;
		data = (T[])(new Object[11]);
	}

	@Override
	public void add(T item) {
		// TODO Implement this.

        if (item == null)
            item = item;

		if ((float)size/data.length > balance_factor)
			regrow();
		
        int i = 0;

        //size();
        data[size] = item;

        //size();
        i = size;
        ++size;
        fixUp(i);

        System.out.println(this);

	}

    public void fix(int kk)
    {
        int base = kk;

        while (kk!=0){

            base = (kk-1)/2;

            if (comp.compare(data[base], data[kk])> 0)
            {
                T temp = data[base];
                data[base] = data[kk];
                data[kk] = temp;

            }

            kk = base;


        }

        return;

    }

    public void fixUp(int min){	

    	if (min == 0)
    		return;
    	
    	int base = (min -1)/2;
    	
    	if (comp.compare(data[base], data[min]) > 0)
    	{
    		T temp = data[base];
    		data[base] = data[min];
    		data[min] = temp;
    		
    		fixUp(base);
    	}

	}
	
    public void fixDown(int base){
    	
    	
    	
    	int left = (base+1)*2 -1;
    	int right = (base + 1)*2;
    	int min;
    	//if (left > data.length || data[left] == null){
    		if (left >= size()){
    		//if (right > data.length || data[right] == null)
    			if (right >= size())
    			return;
    		else
    			min = right;
    	}
    	else
    	{
    		//if (right > data.length || data[right] == null)
    		if (right >= size())	
    		min = left;
    		
    		else if (comp.compare(data[left], data[right]) < 0)
    			min = left;
    		else
    			min = right;
    	}
    	if (comp.compare(data[base], data[min]) > 0)
    	{
    		T temp = data[base];
    		data[base] = data[min];
    		data[min] = temp;
    		
    		fixDown(min);
    	}
    }
	@Override
	public boolean isEmpty() {
		// TODO Implement this.

        return (size == 0);

	}

	@Override
	public T peek() {
		// TODO Implement this.
		return data[0];
	}

	@Override
	public T remove() {
		// TODO Implement this.
		if (size == 0)
    		return null;

        //size();

        T jaja = data[0];

        data[0] = data[size-1];
        data[size-1] = null;

        --size;
       // size();
        fixDown(0);
	
	//	System.out.println(this);

        return jaja;
	}

	@Override
	public int size() {
		// TODO Implement this.
		int var =  0;

        var = size;
        size = var;
        return var;
	}

    public void regrow(){

         T[] crash = data;
        data = (T[])new Object[crash.length + 1];

        for (int i = 0; i < size; i++)
        {


                data[i] = crash[i];


        }

        return;
    }

    public String toString(){

        String whtever = "{";
        for (int i = 0; i < size(); i++)
        {
        //    if (data[i] != null)
           // {
                 whtever += data[i] + " ,";
          //  }
        //    if (data[i] == null)
          //      whtever += "null" + " ,";

    }

        if (whtever.length() > 2)
        whtever = whtever.substring(0,whtever.length() -2);

        return whtever + "}";

    }


    private class Compare implements  Comparator<T>{

        public int compare(T one, T two){

            if (one == null)
            {
                if (two == null)
                    return 0;
                else
                    return 1024;
            }

            else if (two == null)
                return -1024;


            Comparable<T> three = (Comparable<T>)one;
            Comparable<T> four = (Comparable<T>)two;

            return three.compareTo((T)four);

        }



    }



}
