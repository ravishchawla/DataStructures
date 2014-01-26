

import java.io.BufferedWriter;
import java.io.CharConversionException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;

import javax.sound.sampled.AudioFormat.Encoding;

public class Huffman {

    public static void main(String args[]) throws IOException
    {
    	

		System.out.println("Hello yo");

    	
    }

    private static void print(Collection mapp)
    {
    	
        for(Object o: mapp)
            System.out.print(o.toString() + "\t");

        System.out.println();
    }

    private static void print(Map mapp)
    {
        System.out.println(mapp.toString());

    }

    private static void print(Queue mapp)
    {
    	Iterator rater = mapp.iterator();
    	
    	while(rater.hasNext())
    		System.out.print(rater.next().toString() + "\t");
    	
    	
    }
    	
	/**
	 * Builds a frequency map of characters for the given string.
	 * 
	 * This should just be the count of each character.
	 * 
	 * @param s
	 * @return
	 */
	public static Map<Character, Integer> buildFrequencyMap(String s) {

        Map<Character, Integer> mapp = new HashMap<Character, Integer>();
        char charArray[]  = s.toCharArray();

        for(int i = 0 ; i < charArray.length; i++)
        {
            if(mapp.containsKey((Character)charArray[i]))
                mapp.put((Character)charArray[i], mapp.get((Character)charArray[i]).intValue()+1);
            else
                mapp.put((Character)charArray[i], 1);
        }



        return mapp;
	}
	
	/**
	 * Build the Huffman tree using the frequencies given.
	 * 
	 * The frequency map will not necessarily come from the buildFrequencyMap() method.
	 * 
	 * @param freq
	 * @return
	 */
	public static Node buildHuffmanTree(Map<Character, Integer> freq) {

		Comparator<Node> comparator = new nodeComparator();
        PriorityQueue<Node> nQueue = new PriorityQueue<Node>();//freq.size(), comparator);

        Set values = freq.entrySet();
        Iterator setIterator = values.iterator();

        while(setIterator.hasNext())
        {
            Map.Entry entry = (Map.Entry)setIterator.next();
            Node newNode = new Node((Character)entry.getKey(), (Integer)entry.getValue())          ;
            nQueue.add(newNode);
            
        }

        
        System.out.println();
        System.out.println("Queue: ");
        print(nQueue);
        
        
        while(nQueue.size() > 1)
        {

                Node one = nQueue.remove();
                Node two = nQueue.remove();
                Node three = null;

		if(one.compareTo(two) > 0)
		{
			three = new Node(one, two);
		}
		
		else
		{
			three = new Node(two, one);
		}


                nQueue.add(three);

        }  


        System.out.println("Size: " + nQueue.size());
        return nQueue.remove();
       // return nQueue;
	}

	

 	/**
 	 * Traverse the tree and extract the encoding for each character in the tree
 	 * 
 	 * The tree provided will be a valid huffman tree but may not come from the buildHuffmanTree() method.
 	 * 
 	 * @param tree
 	 * @return
 	 */
 	public static Map<Character, EncodedString> buildEncodingMap(Node tree) {
 		
 		EncodedString string = new EncodedString();
 		Map<Character, EncodedString>	mapp = new HashMap<Character, EncodedString>();
 		
 		List<Node> theNodes = preOrder(tree); 
 		
 		
 		for(Node n: theNodes)
 		{
 			EncodedString result = encodeMap(n.character, tree, new EncodedString());
 			if(result.isEmpty())
 				result.zero();
 			mapp.put(n.character, result);
 		}
 		return mapp;
 	}
 	
 	public static EncodedString encodeMap(Character c, Node tree, EncodedString st)
 	{

 		
 		if(tree.left == null && tree.right == null)
 		{
 			if(tree.character == c)
 		 			return st;
 			else
				return null;
			
 		}
 		else{
 			
 			st.zero();
 			if(encodeMap(c, tree.left, st) == null)
 				st.remove();
 			else
 				return st;
 			
 			
 			st.one();
 			if(encodeMap(c, tree.right, st) == null)
 				st.remove();
 			else
 				return st;
 			
 			
 			
 			return null;
 			
 		}
 		
 		
 		
 		
 	}
 
 		
 		
 
 		
 	
 	public static List<Node> preOrder(Node dos)
 	{
		List<Node> nodes = new ArrayList<Node>();
		
		if (dos != null)	
		{			
			if(dos.left == null && dos.right == null)
				nodes.add(dos);
			nodes.addAll(preOrder(dos.left));
			nodes.addAll(preOrder(dos.right));
		}
		
		
		
		
		return nodes;
 		
 		
 		
 	}

	
	/**
	 * Encode each character in the string using the map provided.
	 * 
	 * If a character in the string doesn't exist in the map ignore it.
	 * 
	 * The encoding map may not necessarily come from the buildEncodingMap() method, but will be correct
	 * for the tree given to decode() when decoding this method's output.
	 * 
	 * @param encodingMap
	 * @param s
	 * @return
	 */
	public static EncodedString encode(Map<Character, EncodedString> encodingMap, String s) {
		
		char charArray[] = s.toCharArray();
		EncodedString answer = new EncodedString();
		
		for(int i = 0; i < charArray.length; ++i)
		{
			answer.concat(encodingMap.get(charArray[i]));
			
		}
		
		
		
		
		return answer;
	}
	
	/**
	 * Decode the encoded string using the tree provided.
	 * 
	 * The encoded string may not necessarily come from encode, but will be a valid string for the given tree.
	 * 
	 * (tip: use StringBuilder to make this method faster -- concatenating strings is SLOW)
	 * 
	 * @param tree
	 * @param es
	 * @return
	 */
	public static String decode(Node tree, EncodedString es) {
		
		Iterator<Byte> iterator = es.iterator();
		Node tempNode = tree;
		String string = "";

		int i = 0;
		
			Byte next = null;
			
			for(; iterator.hasNext();  i++)
			{
			if(tempNode.left == null && tempNode.right == null)
			
			{
				string+= tempNode.character;
				tempNode = tree;
			
			}
			next = (Byte)iterator.next();
			if(next == 0)
			{
				if(tempNode.left != null)
				tempNode = tempNode.left;
			}
			
			else if (next == 1)
			{ 
				if(tempNode.right != null)
				tempNode = tempNode.right;
			}
			
			}
			
			if(tree.left == null && tree.right == null)
				return string;
			else
				return string + tempNode.character;
	}
	
    static class nodeComparator implements  Comparator<Node>
    {
        public int compare(Node one, Node two)
        {
            if(one.frequency > two.frequency)
            	return 1;
            else if (two.frequency > one.frequency)
            	return -1;
            else
            	return 0;
        	
        	
        }

    }


}
