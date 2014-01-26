
import java.util.ArrayList;
import java.util.Arrays;

public class StringSearches {


    public static void main(String[] args)
    {

        System.out.println("rnnning");



        String needle = "a";

       String hay = "adf;ljafdsfjlk;azxcadjfqewpocxm.as;kladsjlkadsasdkdaslk;jdfasjkladsfjklasdljkadsjkldsafjkl;adskljadslkcxvnm,sancljkadsnlasdljkafquwehjflaxcm,zxnlkasdjalml;ja;ljfal;dfja;lfjas;la;ljadsfklaj;lajal;kjdsalfjal;dajl;ajaljal;djaflajdfl;kasdjfasl;djasl;kfjasdlkaksjd;flajl;dasjda;lsfjasldfjaljdasl;fjadl;ajaljaljal;jasl;ajl;asdjfl;asdjfasl;kdjasl;jal;akslfajl;adsj;lfjas;dlfjkasdl;fjasd;lfjasdl;fjasl;fjasl;dfjsl;kdafsj;dlksjf;ldskafjal;dfkjl;afjdsa";
        System.out.println(hay.length());
        int[] answer = boyerMoore(needle, hay);

        for(int i = 0; i < answer.length; i++)
            System.out.print(answer[i] + "\t");

        answer = rabinKarp(needle, hay);

        System.out.println();
        for(int i = 0; i < answer.length; i++)
            System.out.print(answer[i] + "\t");



        answer = kmp(needle, hay);

        System.out.println();
        for(int i = 0; i < answer.length; i++)
            System.out.print(answer[i] + "\t");




        //System.out.println(answer);


       // int[] array = rabinKarp("h", "hahy");

         //   for(int i = 0; i < array.length; i++)
           //     System.out.print(array[i] + "\t");



    }



	/**
	 * Return a table for use with Boyer-Moore.
	 * 
	 * map[c] = the length - 1 - last index of c in the needle
	 * map[c] = the length if c doesn't appear in the needle
	 * 
	 * the map should have an entry for every character, 0 to Character.MAX_VALUE
	 */
	public static int[] buildCharTable(String needle) {


        int[] map = new int[Character.MAX_VALUE + 1];

        ArrayList<Character> list = new ArrayList<Character>();
        for(char c: needle.toCharArray())
            list.add(c);




        int count = 0;

        for(int i = 0; i < map.length; ++i)
        {
            char ar = (char)i;
            if(list.contains((Character)(char)i))
                map[i] = Math.max(needle.length() - list.lastIndexOf((Character)(char)i) -1,1);
              //  map[i] = needle.length() - list.indexOf((Character)(char)i) -1;
            else
                map[i] = needle.length();

        }


		return map;
	}

	/**
	 * Run Boyer-Moore on the given strings, looking for needle in haystack.
	 * Return an array of the indices of the occurrence of the needle in the
	 * haystack. 
	 * 
	 * If there are matches that start at index 4, 7, and 9 in the
	 * haystack, return an array containing only 4, 7, and 9. If there are no
	 * matches return an empty array, new int[0]
	 * 
	 * Running time matters, you will not get full credit if it is not
	 * implemented correctly
	 * 
	 * 
	 */
	public static int[] boyerMoore(String needle, String haystack) {

	if(needle == null || needle.equals("") || haystack == null || haystack.equals(""))
		return (new int[haystack.length()]);

        int[] map = buildCharTable(needle);

        char[] searchable = haystack.toCharArray();
        char[] searchee = needle.toCharArray();

        int[] answer = new int[searchable.length];

        int pos = needle.length() -1; /*position in needle*/
        int key = 0; /*position in haystack*/
        int rval = 0;

        for (; (key + pos) < searchable.length; )
        {

            for( pos = needle.length() -1; pos >= 0 ; pos--)
            {



                if(searchable[key +  pos] == searchee[pos])
                {

                    continue;

                }
                else
                    break;
            }

            if(pos < 0)
            {
                answer[rval++] = key;

                key += needle.length();
                pos = needle.length()-1;
            }

            else
            key += map[searchable[key + pos]];

        }

        return answer;
	}

	/**
	 * Return a table for use with KMP. In this table, table[i] is the length of
	 * the longest possible prefix that matches a proper suffix in the string
	 * needle.substring(0, i)
	 */
	public static int[] buildTable(String needle) {


        char word[] = needle.toCharArray();
        int table[] = new int[word.length];

        int pos = 2;
        int key = 0;

        table[0] = -1;
        if(needle.length() > 1)
            table[1] = 0;

        while(pos < word.length)
        {
            if(word[pos-1] == word[key])
               table[pos++] = ++key;

            else if (key > 0)
                key = table[key];

            else
                table[pos++] = 0;


        }




        return table;
	}

	/**
	 * Run Knuth-Morris-Pratt on the given strings, looking for needle in
	 * haystack. Return an array of the indices of the occurrence of the needle
	 * in the haystack.
	 *
	 * If there are matches that start at index 4, 7, and 9 in the
	 * haystack, return an array containing only 4, 7, and 9. If there are no
	 * matches return an empty array, new int[0]
	 */
	public static int[] kmp(String needle, String haystack) {

        if(needle == null || needle.equals("") || haystack == null || haystack.equals(""))
            return (new int[haystack.length()]);


        char[] searchable = haystack.toCharArray();           /*S*/
        char[] searchee = needle.toCharArray();               /*W*/

        int key = 0;
        int pos = 0;

        int[] table = buildTable(needle);

        int[] ansewr = new int[searchable.length];
        int i = 0;


        while(key+pos < searchable.length)
        {
            if(searchee[pos] == searchable[key+pos])
            {
                if(pos == searchee.length -1)
                {
                    ansewr[i++] = key;
                    pos = 0;
                    key = (key+pos - table[pos]);
                }

                if((pos + 1) < searchee.length)
                    pos++;
            }

        else
            {


                key = (key+pos - table[pos]);


            if(table[pos] > -1)
                 pos = table[pos];
            else
                 pos = 0;





            }



        }




        return ansewr;
	}

	// This is the base you should use, don't change it
	public static final int BASE = 1332;

	/**
	 * Given the hash for a string, return the hash for that string removing
	 * oldChar from the front and adding newChar to the end.
	 * 
	 * Power is BASE raised to the power of the length of the needle
	 */
	public static int updateHash(int oldHash, int power, char newChar, char oldChar) {


      //  System.out.println("(" + oldHash + " - (int)(" + (int)oldChar + ")*(" + power + "/" + BASE + "))*" + BASE + "(int)(" + (int)newChar + ")");
       // double answer = (oldHash - ((int)(oldChar)*(power/BASE)))*BASE + (int)(newChar);
        double answer = oldHash * BASE - oldChar * power + newChar;

        return (int)answer;
	}

    private static int power(int base, int power)
    {

        int answer = 1;
        for(int i = 0; i < power; i++)
        {
            int quote = answer*base;
            answer = quote;
        }

    return answer;
    }



	/**
	 * Hash the given string, using the formula given in the homework
	 */
	public static int hash(String s) {

        int answer = 0;
        char[] charArray = s.toCharArray()       ;

        for(int i = 0; i < charArray.length; i++)
        {

            int w = power(BASE, s.length() -1 -i);
            int ca = (int)charArray[i];

            answer += (w*ca);
        }

        return answer;
	}

	/**
	 * Run Rabin-Karp on the given strings, looking for needle in haystack.
	 * Return an array of the indices of the occurrence of the needle in the
	 * haystack.
	 * 
	 * If there are matches that start at index 4, 7, and 9 in the
	 * haystack, return an array containing only 4, 7, and 9. If there are no
	 * matches return an empty array, new int[0]
	 */
	public static int[] rabinKarp(String needle, String haystack) {

        if(needle == null || needle.equals("") || haystack == null || haystack.equals(""))
            return (new int[haystack.length()]);

        char[] charArray = haystack.toCharArray();
        char[] needleArray = needle.toCharArray();
        int check = hash(needle);

                     int count = 0;
        int[] answer = new int[haystack.length()];
        int factor = power(BASE,needle.length());
        int oldHash = hash(haystack.substring(0,needle.length()));
        for(int i = 0; i < charArray.length - needle.length() + 1; i++)
        {


            if(oldHash == check)
            {
                for(int j = 0; j < needleArray.length;j++)
                        if(needleArray[j] != charArray[i+j] )
                                  break;
                answer[count++] = i;

            }


            if( i < charArray.length - needle.length())
             oldHash = (updateHash(oldHash, factor, charArray[i + needle.length()], charArray[i]));


        }






        return answer;
	}
}
