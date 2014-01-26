import java.util.HashMap;
import java.util.Set;

public class DisjointSets<T> {

    HashMap<Integer, Node> hashMap = new HashMap<Integer, Node>();
    int size = 0;

    public DisjointSets()
    {
        size = 0;
    }

	/**
	 * @param setItems
	 *            the initial items (sameSet and merge will never be called with
	 *            items not in this set, this set will never contain null
	 *            elements)
	 */
	public DisjointSets(Set<T> setItems) {
		// TODO

            for (T telt: setItems)
            {
                Node newNode = new Node(telt);
                hashMap.put(telt.hashCode(), newNode);
                ++size;

            }




	}

	/**
	 * @param u
	 * @param v
	 * @return true if the items given are in the same set, false otherwise
	 */
	public boolean sameSet(T u, T v) {
		// TODO

        Node zero = hashMap.get(u.hashCode());
        Node one = hashMap.get(v.hashCode());

        if(find(zero).equals(find(one)))
            return true;




        return false;
	}

	/**
	 * merges the sets u and v are in, do nothing if they are in the same set
	 * 
	 * @param u
	 * @param v
	 */
	public void merge(T u, T v) {
		// TODO

        Node one = hashMap.get(u.hashCode());
        Node zero = hashMap.get(v.hashCode());

        Node oneRoot = find(one);
        Node zeroRoot = find(zero);

        if (oneRoot.rank < zeroRoot.rank)
            oneRoot.parent = zeroRoot;

        else if (oneRoot.rank > zeroRoot.rank)
            zeroRoot.parent = oneRoot;

        else
        {
            zeroRoot.parent = oneRoot;
            ++oneRoot.rank;
        }



	}


    public Node find (Node here)
    {


        if (!here.parent.equals(here))
        {
            here.parent = find(here.parent);

        }

        return here.parent;

                /*
        if x.parent != x
         x.parent := Find(x.parent)
      return x.parent

         */



    }


    public boolean equals(Node one, Node zero)
    {
        return one.equals(zero);
    }


private class Node<T>{

    Node parent;
    T t;
    int rank;

    public Node(T t)
    {

        this.t = t;
        parent = this;
        rank = 0;

    }

    public boolean equals(Node<T> hundred)
    {

        return hundred.t.equals(this.t)        ;
    }

    public String toString()
    {
        return "" + t.toString();
    }


}


}


