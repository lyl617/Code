import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException; 
import java.util.Arrays;
import java.util.ArrayList;

public class Palindrome {

	static WordDictionary dictionary = new WordDictionary(); 

	
	// Get all words that can be formed starting at this index
	public static String[] getWords(String text, int index) {
		ArrayList<String> words = new ArrayList<String>();
		for (int i = 0; i <= text.length() - index; i++) {
			String maybeWord = text.substring(index, index + i);
			if (dictionary.isWord(text.substring(index, index + i))) {
				words.add(maybeWord);
			}
		}

		return words.toArray(new String[0]);
	}

    /**
     *
     * @param stack
     * @return reverse String of items in stack
     */
	public static String stackToReverseString(MyStack stack) {
		/* 
		* TODO 3
		*/
		String temp_string = "";
		MyStack temp_stack = new MyStack();
		while (!stack.isEmpty())
        {
            temp_stack.push(stack.pop());
        }
        while (!temp_stack.isEmpty())
        {
            temp_string += temp_stack.peek() + " ";
            stack.push(temp_stack.pop());
        }
		return temp_string;
		/* 
		* TODO 3
		*/
	}

    /**
     *
     * @param text
     * @return reverse String of input text,and without nonalpha
     */
	public static String reverseStringAndRemoveNonAlpha(String text) {
		/* 
		* TODO 4
		*/
        MyStack temp_stack = new MyStack();
        String temp_string = "";
        for (int i = 0; i<text.length(); i++)
        {
            Character temp_char = (Character)text.charAt(i);
            if (Character.isAlphabetic(temp_char))
            {
                temp_stack.push(temp_char);
            }
        }
        while (!temp_stack.isEmpty())
        {
            temp_string += (char)temp_stack.pop();
        }
		return temp_string;
		/* 
		* TODO 4
		*/
	}


    /**
     *
     * @param text
     * @return Returns true if the text is a palindrome, false if not.
     */
	public static boolean isPalindrome(String text) {
		/* 
		* TODO 5: Implement "explorePalindrome"
		*/
        String temp_text = text.toLowerCase();
        MyStack temp_stack = new MyStack();
        MyQueue temp_queue = new MyQueue();
        for (int i = 0; i<temp_text.length(); i++)
        {
            Character temp_char = (Character)temp_text.charAt(i);
            if (Character.isAlphabetic(temp_text.charAt(i)))
            {
                temp_stack.push(temp_char);
                temp_queue.enqueue(temp_char);
            }
        }
        boolean isPalindrome = true;
        while (!temp_stack.isEmpty()&&!temp_queue.isEmpty())
        {
            char temp_stack_char = (char)temp_stack.pop();
            char temp_queue_char = (char)temp_queue.dequeue();
            if(temp_stack_char != temp_queue_char)
            {
                isPalindrome=false;
            }
        }
		return isPalindrome;
		/* 
		* TODO 5: Implement "explorePalindrome"
		*/
	}


    /**
     *
     * @param text
     * lists all possible endings that would make this string a palindrome
     */
	public static void explorePalindrome(String text) {

	/* 
	* TODO 6: Implement "explorePalindrome" & helper function
	*/
	String temp_string = text.toLowerCase();
	String reverse_string = reverseStringAndRemoveNonAlpha(temp_string);
	MyStack decom_stack = new MyStack();
    decomposeText(temp_string,reverse_string,0,decom_stack);

	/* 
	* TODO 6
	*/

	}

    /**
     *
     * @param originalText
     * @param textToDecompose reverse string of originalText
     * @param index find which words could be created from a string at a given index
     * @param decomposition stack to save decomposition
     */
	public static void decomposeText (String originalText, String textToDecompose,  int  index, MyStack decomposition)
    {
        if (index >=textToDecompose.length())
        {
                String temp_result = stackToReverseString(decomposition);
                System.out.println(originalText + ":" + temp_result);
                decomposition.pop();
                return;
        }
        String[] temp_words = getWords(textToDecompose,index);
        if (temp_words.length!=0)
        {
            for (String word : temp_words) {
                decomposition.push(word);
                int temp_index=index+word.length();
                decomposeText(originalText, textToDecompose, temp_index, decomposition);

            }
            if (!decomposition.isEmpty())
            {
                decomposition.pop();
            }
        }else
        {
            if (!decomposition.isEmpty())
            {
                decomposition.pop();
            }else
            {
                System.out.println(originalText + ":" + textToDecompose);
            }
            return;
        }

    }


	// This function looks at the arguments that are passed 
	// and decides whether to test palindromes or expand them
	public static void main(String[] args) throws IOException {

		if (args.length == 0) {
			System.out.println("ERROR: Remember to set the mode with an argument: 'test' or 'expand'");
		} else {
			String mode = args[0];

			// Default palindromes to use if none are provided
			String[] testPalindromes = {"A","ABBA","oh no an oboe","salami?","I'm alas, a salami"};
			//
			if (args.length > 1)
				testPalindromes = Arrays.copyOfRange(args, 1, args.length);

			// Test whether the provided strings are palindromes
			if (mode.equals("test")) {

				for (int i = 0; i < testPalindromes.length; i++) {
					String text = testPalindromes[i];
					boolean result = isPalindrome(text);
					System.out.println("'" + text + "': " + result);
				}

			} else if (mode.equals("expand")) {
				for (int i = 0; i < testPalindromes.length; i++) {

					explorePalindrome(testPalindromes[i]);
				}	
			}
			else {
				System.out.println("unknown mode: " + mode);
			}
		}
	}
}