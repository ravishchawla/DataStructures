import java.util.Comparator;

public class yoo implements  Comparator<Integer>{

    public int compare(Integer one, Integer two){

        if (one == null)
        {
            if (two == null)
                return 0;
            else
                return -1024;
        }

        else if (two == null)
            return 1024;


        Comparable<Integer> three = (Comparable<Integer>)one;
        Comparable<Integer> four = (Comparable<Integer>)two;

        return (-1*three.compareTo((Integer)four));

    }

	
}