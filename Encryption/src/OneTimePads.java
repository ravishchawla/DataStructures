import java.util.Random;


public class OneTimePads {

	public static String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ., '\"-_()@1234567890\\";
	public static void main(String args[])
	{
		String message = "Lorem ipsum dolor sit amet, te cum tollit persequeris, quo minim explicari cu. In iracundia concludaturque sea, ad eum soluta rationibus. Cu vivendo sententiae quo, cu audiam scribentur his. No sea voluptua apeirian necessitatibus, ut nibh tractatos vel, ea iudico molestie probatus cum. Ad audire scaevola qualisque ius, has et fugit vivendum moderatius."

+ "Luptatum sadipscing vel id, tota melius ut mei. Ex discere efficiantur est, vis debet maluisset ex. No eum legere eleifend electram. Te atqui decore labitur vix, ad sea quod disputationi. Saepe phaedrum et qui, mel ad facete gubergren."

+ "Sea aliquando consequuntur id. Ut eos fierent gubergren, sea melius minimum perfecto ea, vel id nobis partem laoreet. Eam unum impedit veritus ea, meis detracto qui et. Nec ne paulo principes, admodum adolescens eloquentiam no mei."

+ "Vel percipit iracundia no, mei ad tantas facete fierent. Eam et movet cetero, vide velit nam te, ad quo albucius atomorum. Ipsum consequat eum te, at labores corpora eos, nonumy impedit ne qui. Vidisse legendos sea ei, quot etiam in qui, cu vix prompta appareat."

+ "Mel ne amet solum consequuntur, ex duis nonumy intellegebat eam, ex usu sint quidam noster. Sonet consulatu sadipscing sit et, hinc ponderum dissentiet mei ad. Posse recteque sit ut. Eam ex volutpat hendrerit, an qui quod purto doctus, ut duo dolores oportere efficiendi. Ut pri tibique referrentur theophrastus, ex laudem viderer usu. Suas iriure mediocrem qui ei, id vel scripta appetere.";
		String key = generateKey(message.length());
		String code;
		System.out.println("Code: \n\t" + (code = encode(message,key)));
		
		String decodedMessage;
		System.out.println("\n\ndeCode: \n\t" + (decodedMessage = decode(code,key)));
		
		
		System.out.println("\n " + message.equals(decodedMessage));
		
	}
	
	public static String encode(String message)
	{
		String key = generateKey(message.length());
		
		return(encode(message,key));
		
	}
	
	public static String encode(String message, String key)
	{
		
		
		int messageArray[] = numerize(message);
		int keyArray[] = numerize(key);
		
		int code[] = new int[message.length()];
		
		for(int i = 0; i < message.length(); ++i)
		{
			code[i] = (messageArray[i] + keyArray[i])%alphabet.length();
		}
		
		return(alphabetize(code));
		
	}
	
	public static String decode(String code, String key)
	{
		
		int codeArray[] = numerize(code);
		int keyArray[] = numerize(key);
		int message[] = new int[code.length()];
		
		for(int i = 0; i < code.length(); ++i)
		{
			message[i] = (codeArray[i] - keyArray[i])%alphabet.length();
			if(message[i] < 0)
				message[i] += alphabet.length();
		}
		
		return(alphabetize(message));
		
	}
	
	public static String generateKey(int length)
	{
		Random gen = new Random();
		String key = "";
		
		
		for(int i = 0; i < length; ++i)
		{
			key += alphabet.charAt(gen.nextInt(alphabet.length()));
			
		}
		
		return key;
		
	}
	
	private static int[] numerize(String string)
	{
		int numeric[] = new int[string.length()];
		
		for(int i = 0; i < string.length(); ++i)
		{
			numeric[i] = alphabet.indexOf(string.charAt(i));
		}
		
		return numeric;
	}
	
	private static String alphabetize(int numericArray[])
	{
		String string = new String();
		
		int i = 0;

		for(i = 0; i < numericArray.length; ++i)
		{
			string += alphabet.charAt(numericArray[i]);
		}

		

		return string;
	}
	
	
}
