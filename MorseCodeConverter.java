import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * utility class
 * @author Faith Fru Nchang
 */
public class MorseCodeConverter {

	public MorseCodeConverter()
	{
		
	}
	
	/**
	 * returns a string with all the data in the tree in LNR order with an space in between them. Uses the toArrayList method in MorseCodeTree It should return the data in this order:
	 * "h s v i f u e l r a p w j b d x n c k y t z g q m o"
	 * Note the extra space between j and b - that is because there is an empty string that is the root, and in the LNR traversal, the root would come between the right most child of the left tree (j) and the left most child of the right tree (b). This is used for testing purposes to make sure the MorseCodeTree has been built properly
	 * @return the data in the tree in LNR order separated by a space. 
	 */
	public static String printTree()
	{
		// creates a morse code tree
		MorseCodeTree<String> tree = new MorseCodeTree<String>();
		// an ArrayList of all the items in the list in Inorder traversal
		ArrayList<String> data = tree.toArrayList();
		
		String totalData ="";
		if (data.size() ==0)
		{
			return totalData;
		}
		else
		{
			for (String item: data)
			{
				totalData += item + " ";
			}
		
			return totalData.strip();
		}
	}
	
	/**
	 * Converts Morse code into English. Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	 * @param code - the morse code
	 * @return  english translation
	 */
	public static String convertToEnglish(String code)
	{
		MorseCodeTree<String> tree = new MorseCodeTree<String>();
		String message = "";
		
		if (code == null || code.equals(""))
			return message;
		
		// array of the words in the sentence in morse code format
		String[] morseCodeWords = code.strip().split("/");
		// loops through each word in the list of words
		for (String let: morseCodeWords)
		{
			//gets the letter of each word in an array
			String[] codeLetter = let.strip().split(" ");
			for (String codeL: codeLetter)
			{
				// converts in each morse code to its corresponding letter of the alphabet
				String letter = tree.fetch(codeL.strip());	
				message += letter;
		
			}
			message+=" ";
		}
	
		return message.strip();
	}
	
	/**
	 * Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’.
	 * @param codeFile - name of the File that contains Morse Code
	 * @return the English translation of the file
	 * @throws FileNotFoundException
	 */
	public static String convertToEnglish(File codeFile) throws FileNotFoundException
	{
		Scanner sc = new Scanner(codeFile);
		String message = "";
		String code ="";
		while (sc.hasNext())
		{
			code = sc.nextLine();
			message += convertToEnglish(code);
		}
		
		sc.close();
		
		return message;
		
		
	}
}
