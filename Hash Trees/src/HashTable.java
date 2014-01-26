import java.util.*;

/*

	Ravish Chawla
	130224.17


 */

public class HashTable<K,V> {

	/**
	 * The maximum load factor for this hashtable
	 */
	
	public static void main(String[] args) throws Exception
	{
		System.out.println("hello");

		HashTable<Integer, Character> hash = new HashTable<Integer, Character>();
        
		
		
		hash.put(70, 'a');
		hash.debug(70);
		hash.put(9, 'b');
		hash.debug(9);
		hash.put(52, 'c');
		hash.debug(52);
		hash.put(56, 'd');
		hash.debug(56);
		hash.put(46, 'e');
		hash.debug(46);
		hash.put(58,'f');
		hash.debug(58);
		hash.remove(52);
		System.out.println("r");
		hash.debug(52);
		hash.put(65, 'f');
		hash.debug(65);
		hash.put(32, 'g');
		hash.debug(32);
		hash.put(25, 'l');
		hash.debug(25);
		hash.put(15, 'h');
		hash.debug(15);
		hash.put(22, 'i');
		hash.debug(22);
		hash.remove(22);
		System.out.println("r");
		hash.debug(22);
		hash.put(4, 'm');
		hash.debug(4);
		hash.put(22, 'n');
		hash.debug(22);
		hash.put(3, 'j');
		hash.debug(3);
		
		System.out.println(hash.toString());
        
        
    }


	private final double MAX_LOAD_FACTOR = .5;

	/**	 * The number of entries in this hashtable
	 */
	private int size;

	/**
	 * The underlying array for this hashtable
	 */
	private Entry<K,V>[] table = new Entry[3];





	/**
	 * Puts the key value pair into the table. If the
	 * key already exists in the table, replace the
	 * old value with the new one and return the old value
	 * 
	 * @param key, never null
	 * @param value, possibly null
	 * @return the replaced value, null if nothing existed previously
	 */
	public V put(K key ,V value) {

               size();
        int code = key.hashCode();
        int posit = code%table.length;
        posit = Math.abs(posit);
        //System.out.println(key + "  key " + code + " posit: " + posit);
        int i = 0;
        V valve = null;

        if (containsKey(key))
        {
         valve =   update(key, value);

        }
         else{
        if (table[posit] == null || table[posit].isAvailable())
        {
            table[posit] = new  Entry<K, V>(key, value);
            table[posit].setAvailable(false);
        }
        else{

        for (i = posit;table[i] != null; i++)
        {
            if(i == (table.length -1))
                i = 0;

            if (table[i] !=null && table[i].isAvailable())
                break;

        }
            table[i] = new Entry<K, V>(key, value);
            table[i].setAvailable(false);
        }
        }
        size();

        float balance = (float)size()/table.length;
        if (balance > MAX_LOAD_FACTOR)
            regrow();


        return valve;
	}
	
	public void debug(Integer what) throws Exception{
		
		System.in.read();
		System.out.println(what + "> " + toString());
		System.in.read();
	}
	
	/**
	 * Removes the entry containing the given key
	 * 
	 * (remember that all objects have a hashCode method)
	 * 
	 * @param key, never null
	 * @return the value of the removed entry
	 */
	public V remove(Object key) {

		int code  = key.hashCode();
        int posit = code%table.length;
        posit = Math.abs(posit);
        size();
        int last = 0;

        if (posit< table.length && table[posit] != null && table[posit].key.equals(key))
        {
            table[posit].setAvailable(true);
            size();
            return table[posit].value;
        }
        else{

        for (int i = posit+1; i != posit; i++)
        {
            if (i == (table.length))
                 i = 0;

            if (table[i]!= null && table[i].key.equals(key))
            {
                table[i].setAvailable(true);
                size();
                return table[i].value;
            }
        }

        }







        return null;
    }
	
	/**
	 * Gets the value of the entry given a specific key
	 * 
	 * (remember that all objects have a hashCode method)
	 * 
	 * @param key, never null
	 * @return
	 */
	public V get(Object key) {

        int code  = key.hashCode();
        int posit = code%table.length;
        posit = Math.abs(posit);
        int last = 0;
        size();

        if (posit< table.length && table[posit] != null && table[posit].key.equals(key))
        {
            return table[posit].value;
        }
        else{

            for (int i = posit+1; i != posit; i++)
            {
                if (i == table.length)
                    i = 0;

                if (table[i]!= null && table[i].key.equals(key))
                {
         //           table[i].setAvailable(true);
                    return table[i].value;
                }
            }

        }

        return null;
    }


	
	/**
	 * @param key, never null
	 * @return true if this table contains the given key, false otherwise
	 */
	public boolean containsKey(Object key) {
//
  //      boolean ya = false;
        for (Entry ent: table)
        {
            if (ent!= null && !ent.isAvailable() && ent.key.equals(key))
                           return true;
        }

        return false;



	}

    private V update(K key, V value)
    {
        int code = key.hashCode();
        int posit = code%table.length;

        V valve = null;
        int i = posit;

        for (Entry ent: table)
        {
            if (ent!= null && ent.key.equals(key))
            {
                valve = (V)ent.value;
                   ent.value = value;
            }
        }


        return valve;


    }

    public void regrow()
    {
        
        //Entry<K, V>[] crash = new Entry[table.length*2 + 1];
        Entry<K, V>[] crash = table;
        table = new Entry[crash.length*2 + 1];
        for (int i = 0; i < crash.length; ++i)
        {
            if (crash[i] != null && !crash[i].isAvailable())
                put(crash[i].key, crash[i].value);
        }



    }

	/**
	 * Clears this hashTable
	 */
	public void clear() {


        for (int i = 0; i < table.length; i++)
        {
            table[i] = null;
        }


	}
	
	/**
	 * @return true if this hashtable is empty, false otherwise
	 */
	public boolean isEmpty() {

           boolean one = true;

        for (Entry ent: table)
        {
            if (ent != null && !ent.isAvailable())
                one = false;

        }

        return one;


	}
	
	/**
	 * @return the value from this hashtable
	 */
	public Collection<V> values() {


        Collection<V> yarray = new LinkedList<V>();

        for (Entry ent: table)
        {
            if (ent!= null && !ent.isAvailable())
                yarray.add((V)ent.value);
        }



        return yarray;

	}
	
	/**
	 * @return the unique keys from this hashtable
	 */
	public Set<K> keySet() {

        Set<K> yset = new HashSet<K>();

        for (Entry ent: table)
        {
            if (ent != null && !ent.isAvailable())
                    yset.add((K)ent.key);

        }



             return yset;

	}
	
	/**
	 * @return the unique entries from this hashtable
	 */
	public Set<Entry<K, V>> entrySet() {
        Set<Entry<K,V>> yset = new HashSet<Entry<K,V>>();

        for (Entry ent: table)
        {
            if (ent != null && !ent.isAvailable())
                yset.add((Entry)ent);

        }



        return yset;
	}
	
	/**
	 * @return the size of this hashtable
	 */
	public int size() {
		short numbers = 0;

        for (int i = 0; i < table.length; ++i)
        {
            if (table[i] != null && !table[i].isAvailable())
                numbers++;


        }
            return (size = numbers);

	}

	/*
	 * Don't modify any code below this point
	 */
	
	public void setSize(int size) {
		this.size = size;
	}

	public Entry<K,V>[] getTable() {
		return table;
	}

	public void setTable(Entry<K,V>[] table) {
		this.table = table;
	}

	public double getMaxLoadFactor() {
		return MAX_LOAD_FACTOR;
	}



	public String toString(){
		
		String yov = "";
		
		for(int i = 0; i < table.length; i++){
			if(table[i] != null)
			yov += "<" + i +"," + table[i].key + ">\t";
			
		}
		
		return yov;
		
	}



	public static class Entry<K,V> {
		private K key;
		private V value;
		private boolean available;
		
		public Entry(K key, V value) {
			this.setKey(key);
			this.setValue(value);
			this.setAvailable(true);
		}
		
		public void setKey(K key) {
			this.key = key;
		}
		
		public K getKey() {
			return this.key;
		}
		
		public void setValue(V value) {
			this.value = value;
		}
		
		public V getValue() {
			return this.value;
		}

		public boolean isAvailable() {
			return available;
		}

		public void setAvailable(boolean available) {
			this.available = available;
		}

        public String toString()
        {
            return "<" + key + ", " + value + ">";
        }

	}
}
