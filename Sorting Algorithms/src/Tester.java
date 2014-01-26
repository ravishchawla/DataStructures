import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Ravish
 * Date: 3/9/13
 * Time: 11:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tester {

    public static void main(String[] args){


        //Integer[] arr = {4,6,9,11,3,0,94,32,18,97,39,5,1,8,34};
        //Integer[] arr = {8,4,3,2, 1, 0};
        //	try{
        //	FileWriter stream = new FileWriter("out.txt");
        //BufferedWriter out = new BufferedWriter(stream);
    	Random gen = new Random();

        int tada = 2;
        while (--tada > 0)
        {
            int limit = 10;

          //  int[] arr /*= {324, 126,-9, 481, 53, -345, 111, 964, 267, -867};//*/ = new int[limit];
       /*     Integer[] arr = new Integer[limit];
            Random gen = new Random();
            Set visit = new HashSet();
            for (int i = 0; i < arr.length; ++i)
            {
                int j = gen.nextInt(100000)-50000;

                while (visit.contains(j))
                {

                    j = gen.nextInt(100000) - 50000;


                }
                visit.add(j);
                arr[i] = j;

            }
        */

        Ravish[] arr = new Ravish[11];

         int choi = 0;
            switch (choi){

                case(0):

             arr[0] = new Ravish(324, true);
             arr[1] = new Ravish(126, false);
             arr[2] = new Ravish(481, true);
             arr[3] = new Ravish(53, true);
             arr[4] = new Ravish(324, false);
             arr[5] = new Ravish(111, true);
             arr[6] = new Ravish(-845, true);
             arr[7] = new Ravish(964, true);
             arr[8] = new Ravish(287, true);
             arr[9] = new Ravish(126, true);
             arr[10] = new Ravish(-324, true);

                    break;

                case(1):
            arr[0] = new Ravish(-845, true);
            arr[1] = new Ravish(-324, true);
            arr[2] = new Ravish(53, true);
            arr[3] = new Ravish(111, true);
            arr[4] = new Ravish(126, true);
            arr[5] = new Ravish(126, false);
            arr[6] = new Ravish(287, true);
            arr[7] = new Ravish(324, false);
            arr[8] = new Ravish(324, true);
            arr[9] = new Ravish(481, true);
            arr[10] = new Ravish(964, true);
            break;

                case(2):
            arr[0] = new Ravish(964, true);
            arr[1] = new Ravish(481, true);
            arr[2] = new Ravish(324, false);
            arr[3] = new Ravish(324, true);
            arr[4] = new Ravish(287, true);
            arr[5] = new Ravish(126, true);
            arr[6] = new Ravish(126, false);
            arr[7] = new Ravish(111, true);
            arr[8] = new Ravish(53, true);
            arr[9] = new Ravish(-324, true);
            arr[10] = new Ravish(-845, true);
                 break;
            }
            //   arr[685] = -9583;
            Sort tar = new Sort();
            //System.out.println(tar.toString(arr));
            System.out.println(tar.toString(arr));

            tar.quicksort(arr, gen);
            //	Arrays.sort(arr);

            System.out.println(tar.toString(arr));
            Boolean sorted = true;
            for (Integer i = 1; i < arr.length; ++i)
            {
                		if (arr[i-1].compareTo(arr[i]) > 0)
               // if (arr[i-1] > arr[i])
                {
                    sorted = false;
                    System.out.println(i);
                    break;
                }


            }

            //out.write(sorted.toString()+"\n");

            if (sorted.equals(false))
            {
                System.out.println("false found!!!");
            }
        }

        //	out.close();


        //}
	/*	catch(Exception ieo)
		{
			System.out.println("thrown something" + ieo.getMessage());
		}
		*/

    }



}
