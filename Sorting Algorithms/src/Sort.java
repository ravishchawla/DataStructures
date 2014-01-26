//import com.sun.deploy.util.ArrayUtil;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.*;

/*
 Ravish Chawla
 */

public class Sort {


	public static void main(String[] stuff){
		
		
		List<Integer> arr = Arrays.asList(23,13,10,27,11,18,26,15,8,12,16,20);
		//List<Integer> arr = Arrays.asList(1,2,3,4,5,6,7,8);
		Integer[] result = new Integer[8];
		result = arr.toArray(result);
		
		Sort sort = new Sort();
		sort.bubblesort(result);
		System.out.println(sort.toString(result));
	}
	
	
	static int count = 0;
	

	
	/**
	 * Implement bubble sort.
	 * 
	 * It should be:
	 *  inplace
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n)
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<T>> void bubblesort(T[] arr) {
		// TODO Auto-generated method stub
		
		

		
		int n = arr.length;
		int k = 0;
		while (n!= 0)
		{
			int newn = 0;	
			for (int i = 1; i < n; ++i)
			{	
				System.out.println("dfs " + ++k);
				if (arr[i-1].compareTo(arr[i]) > 0)
				{
					T temp = arr[i-1];
					arr[i-1] = arr[i];
					arr[i] = temp;
					
					newn = i;
				}
				
				
			
			}
			
			n = newn;
			
		}
		
	}
	
	/**
	 * Implement insertion sort.
	 * 
	 * It should be:
	 *  inplace
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n)
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<T>> void insertionsort(T[] arr) {
		// TODO Auto-generated method stub
		

		
		
		for (int i = 1; i < arr.length; ++i)
		{
			int j  = i;
			while (j > 0 && arr[j].compareTo(arr[j-1]) < 0){
				{
					
					
					T temp = arr[j];
					arr[j] = arr[j-1];
					arr[j-1] = temp;
					
					--j;
					
					
				}
					
			}
				
			
			
			
		}
		
		
		
		
	}
	
	/**
	 * Implement quick sort. 
	 * 
	 * Use the provided random object to select your pivots.
	 * For example if you need a pivot between a (inclusive)
	 * and b (exclusive) where b > a, use the following code:
	 * 
	 * int pivotIndex = r.nextInt(b - a) + a;
	 * 
	 * It should be:
	 *  inplace
	 *  
	 * Have a worst case running time of:
	 *  O(n^2)
	 *  
	 * And a best case running time of:
	 *  O(n log n)
	 * 
	 * @param arr
	 */
	public static <T extends Comparable<T>> void quicksort(T[] arr, Random r) {

		quicksort(arr, 0, arr.length-1, r);
		
		
		
		// TODO Auto-generated method stub
	}
	
	public static <T extends Comparable<T>> void quicksort(T[] arr, int left, int right, Random r) {
		
		if (left < right){
            
			int newPivot = sortIt(arr, left, right, r.nextInt(right - left) + left);
			quicksort(arr, left, newPivot -1, r);
			quicksort(arr, newPivot + 1, right, r);
			
		}
		
		
	}
	
	/*
	 this method traverses the array based on the pivot and 
	 exchanges element based on it. it's a helper method
	 to quicksort(int[] arr)
	 */
		private static <T extends Comparable<T>> int sortIt(T[] arr, int left, int right, int pivotIndex) {
		

		
		T pivot = arr[pivotIndex];
		T temp = arr[pivotIndex];
		arr[pivotIndex] = arr[right];
		arr[right] = temp;
		
		int bump = left;
		
		for (int i = left; i < right; ++i)
		{
			
			if (arr[i].compareTo(pivot) < 0)
			{
				
				temp = arr[i];
				arr[i] = arr[bump];
				arr[bump] = temp;
				++bump;
				
			}
			
		}
		
		
		temp = arr[bump];
		arr[bump] = arr[right];
		arr[right] = temp;
		
		
		return bump;
		
		
	}
	
	
	/**
	 * Implement merge sort.
	 * 
	 * It should be:
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(n log n)
	 *  
	 * And a best case running time of:
	 *  O(n log n)
	 *  
	 * @param arr
	 * @return
	 */
	public static <T extends Comparable<T>> T[] mergesort(T[] arr) {
		// TODO Auto-generated method stub
		System.out.println("dfs " + ++count);
		
		if (arr.length <= 1)
			return arr;
		
		int middle = arr.length/2;
		T[] left = (T[])(new Comparable[middle]);
		T[] right = (T[])(new Comparable[arr.length - middle]);
        
		for(int i = 0; i < middle; ++i)
		{
			left[i] = arr[i];

        }
		
		for (int i = middle; i < arr.length; i++)
		{
			right[i-middle] = arr[i];

		}
		
		left = mergesort(left);
		right = mergesort(right);
		T[] bump  = merge(left, right);
		
		for (int i = 0; i < bump.length; i++){
			
			arr[i] = bump[i];
		}
		
		
		return arr;
		

	}
	
	private static <T extends Comparable<T>> T[] merge(T[] left, T[] right){
		
		T[] result = (T[])(new Comparable[left.length + right.length]);
		
	
		
		
		int i ,j, k;
		
		for ( i = k = j = 0; i <= left.length && j < right.length; ++k){
           
			if (i < left.length && left[i].compareTo(right[j])<= 0){
				
					result[k] = left[i];
					++i;
					
				}
				
				
			else {
				
				result[k] = right[j];
				++j;
				
				
			}
			
				
				
			}
		
		
		for (; i < left.length; ++i, ++k){
			result[k] = left[i];
		}
		
		
		
		
		return result;	
		
	}
	

	

	
	
	/**
	 * Implement radix sort
	 * 
	 * Hint: You can use Integer.toString to get a string
	 * of the digits. Don't forget to account for negative
	 * integers, they will have a '-' at the front of the
	 * string.
	 * 
	 * It should be:
	 *  stable
	 *  
	 * Have a worst case running time of:
	 *  O(kn)
	 *  
	 * And a best case running time of:
	 *  O(kn)
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] radixsort(int[] arr) {

		// TODO Auto-generated method stub


        int[] negatives = new int[arr.length];
        int[] positives = new int[arr.length];

        int i = 0,j = 0;
        for (int telt: arr)
        {
            if (telt < 0)
               negatives[i++] = -telt;

            else
                positives[j++] = telt;
        }

        negatives = Arrays.copyOf(negatives, i);
        positives = Arrays.copyOf(positives, j);

        int k = 1;
        int count = findLargest(arr);
        do{
            negatives = bucketsort(negatives, k);
            positives = bucketsort(positives, k);
        }
        while (k++ < count);


        negatives = fixNegatives(negatives);
        System.arraycopy(negatives, 0, arr, 0, negatives.length);
        System.arraycopy(positives, 0, arr, negatives.length, positives.length);


      

   return arr;

	}

	/*
	 * this method sorts elements into buckets
	 	based on the significance of the digit
	 	passed in as the parameter. it then concatinates
	 	all the buckets into a single array and returns it. 
	 */
	
    public static int[] bucketsort(int[] arr, int by){

        int power = (int)Math.pow(10,by);
        int udiv = power/10;
        int[] result = new int[arr.length];
        Queue<Integer>[] target = new LinkedList[10];
        for (int i = 0; i < 10; ++i)
            target[i] = new LinkedList<>();

        for (Integer telt: arr){

            int why = telt/udiv;
            int what = why%10;
            target[what].add(telt);

            
        }

       //int[] result = new int[arr.length];
       int j = 0;


        for (Queue queue: target){

            while (!queue.isEmpty())
//                list.add((Integer)queue.remove());
            {    result[j] = (Integer)queue.remove();




                ++j;
            }
        }

     
        return  result;



    }

    /*
     this method takes in an array, and 
     reverses the sign of each element in it, and reverses
     the order of the array. 
     its a helper method to radix sort, which only works with positive
      numbers, so the absolute value of the integers is used to 
      sort using radix sort, and this method to fix the nagative signs. 
     */
    
    public  static int[] fixNegatives(int[] arr){

        int[] result = new int[arr.length];

        for(int i = 0 ; i < arr.length; i++)
        {
            result[arr.length - i - 1] = -arr[i];
        }

        return result;
    }
    /*
     * this method finds the largest number
     of significant digits in the array.
     it also returns that number.  
     */

    public static int findLargest(int[] arr)
    {

        int largestSoFar = 0;


        for (int telt: arr){

            int count = 0;
            while (telt!= 0)
            {
                telt /=10;
                ++count;

            }

            if (count > largestSoFar)
                largestSoFar = count;



        }


        return largestSoFar;
    }
    /*
     returns a string representation of the generic array
     */

	public static String toString(Object[] arr) {
		String yov = "{";

		for (int i = 0; i < arr.length; i++)
			{
				yov += arr[i].toString() + " ,";

			}

		if (yov.length() > 2)
			yov = yov.substring(0,yov.length()-2);

		return yov + "}";

	}
	
	/*
	 returns a string representation of the int array. 
	 */
    public static String toString(int[] arr) {
        String yov = "{";

        for (int i = 0; i < arr.length; i++)
        {
            yov += arr[i] + " ,";

        }

        if (yov.length() > 2)
            yov = yov.substring(0,yov.length()-2);

        return yov + "}";

    }

}
