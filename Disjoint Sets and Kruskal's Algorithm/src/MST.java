import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.*;
public class MST {


	/**
	 * Run Kruskal's algorithm on the given graph and return the MST, return
	 * null if no MST exists for the graph
	 * 
	 * @param g
	 *            the graph, g will never be null
	 * @return the MST of the graph, null of no valid MST exists
	 */

    public static void main(String[] args)
    {

        String egelist =
                "12 "
                + "0 1 7 "
                + "0 3 5 "
                + "1 3 9 "
                + "1 2 8 "
                + "1 4 7 "
                + "3 4 15 "
                + "3 5 6 "
                + "5 6 11 "
                + "4 6 9 "
                + "2 4 5 "
                + "4 5 8 "
                + "7 7 0";



        Graph giraffe = new Graph(egelist);
        Collection<Edge> list = kruskals(giraffe);

        if(list != null)
        System.out.println(list.toString());
        else
            System.out.println("null kkjlfsad");

    }


    public static Collection<Edge> kruskals(Graph g) {
		// TODO

        Collection<Edge> edges = g.getEdgeList();
        ArrayList<Edge> list = new ArrayList<Edge>();

        for(Edge edge: edges)
        {
            list.add(edge);

        }


        Collections.sort(list);
        System.out.println(list.toString());

        Collection<Edge> MST = new LinkedList<Edge>();

        Set<Vertex> vertices = g.getVertices();
        DisjointSets<Vertex> sets = new DisjointSets<Vertex>(vertices);
        System.out.println(list);
        for(Edge ege: list)
        {

            Vertex one = ege.getU();
            Vertex zero = ege.getV();
            //if(!sets.equals(sets.find(one), sets.find(zero)))
            if(!sets.sameSet(one,zero))
            {
                sets.merge(one, zero);
                MST.add(ege);

            }




        }

        for (Vertex vert: vertices)
               for(Vertex haha: vertices)
                   if(!sets.sameSet(vert, haha))
                       return null;

        return MST;


	}
}
