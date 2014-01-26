import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created with IntelliJ IDEA.
 * User: Ravish
 * Date: 2/26/13
 * Time: 10:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class hELLO {


    public static void main(String[] args) throws IOException
    {


        
        Comparator hetfield = new yoo();
        BinaryHeap<Integer> lars = new BinaryHeap<Integer>(hetfield);
        lars = new BinaryHeap<Integer>();
        System.out.println("hemmet");
        
        java.util.List<Integer> stuff = Arrays.asList(15, 51, 93, 27, 68, 72, 70, 1, 86, 43, 99, null, 19, 96, 52);//, 73, 4, 44, 65, 64, 14, 66, 25, 92, 90, 71, 69, 77, 67, 45);
        //null = 102400


        for (Integer in: stuff){
        //
        //
      //  	System.in.read();
        	System.out.print(in + ": "); lars.add(in);
        //	System.in.read();
        	
        }
        
        while (!lars.isEmpty()){
        	//System.in.read();
            System.out.println(lars.size()-1 + ": " + lars.remove());
            System.out.println(lars);
        	//System.in.read();
            stuff = stuff;
        }
        
        lars.remove();
        lars.remove();
        lars.remove();
        System.out.println(lars.remove());
        
        System.out.println("trujilo");
    }
    
    
    




	
	
}
