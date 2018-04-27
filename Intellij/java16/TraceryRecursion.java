/* 
* START: TO DO: Import the packages you need to support your I/O operations.
*/
import java.util.Hashtable;
import java.io.*;
/*
* END: TO DO: Import the packages you need to support your I/O operations.
*/

public class TraceryRecursion {

	/*
	* START: TO DO: outputGrammar(Hashtable<String, Rule> grammar, PrintStream ps)
	* Change the code so everything that is currently output to the console using System.out.println is now output to the PrintStream
	* using the PrinStream.println() method.  
	*/
	public static void outputGrammar(Hashtable<String, Rule[]> grammar, PrintStream ps) {
		ps.println("\nGRAMMAR:");
		for ( String key : grammar.keySet() ) {
			String line = "";
			line += key + ": " + String.format("%1$" + (20 - key.length()) + "s", " ");
			for (Rule rule : grammar.get(key)) {
				line += "\"" + rule + "\"," ;
			}

			ps.println(line);
		}
	}
	/*
	* END: TO DO: changing outputGrammar to use a PrintStream
	*/


	// Given an InputStream, load the grammar at that InputStream
	public static Hashtable<String, Rule[]> loadGrammar(InputStream inStream)throws IOException {

		Hashtable<String, Rule[]> grammar = new Hashtable<String, Rule[]>();

		// TO DO: create a new BufferedReader based on inStream that you'll use to read the stream line by line (using readLine())

		/* 
		* START: TO DO: Make a loop that reads a new line from the BufferedReader line by line and adds it to the grammar
		*/

            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(inStream));
			/* 
			* Put your code that takes each line and adds it to the grammar inside the loop. Below is the code from our solution for doing this,
			* but feel free to substitute this with the code from your own assignment. 
			*/
				String line=null;
				while((line=bufferedReader.readLine())!=null){
					String[] ruleString = line.split(":");
					String[] expansions = ruleString[1].split(",");
					Rule[] rules = new Rule[expansions.length];
					for(int i=0; i < expansions.length; i++) {
						rules[i] = new Rule(expansions[i]);
					}
						grammar.put(ruleString[0], rules);
				}
		/* 
		* END: TO DO: Make a loop that reads a new line from the BufferedReader line by line and processes it.
		*/ 

		return grammar;
	}


	/*
	* START: TO DO: public static InputStream getInputStream(String[] args)
	*/
       public static InputStream getInputStream(String[] args){
       	try{
       	  if(args.length==2){
                File file=new File(args[0]);
       	  	    InputStream inputStream=new FileInputStream(file);
       	  	    return inputStream;
		  }else if(args.length==4){
       	  	  int in_index=0;
       	  	  int file_index=1;
       	  	  if(args[in_index].equals("-in")){
                     file_index=1;
			  }else {
                     file_index=3;
			  }
			  File file=new File(args[file_index]);
       	  	  InputStream inputStream=new FileInputStream(file);
       	  	  return inputStream;
		  }
	   }catch (FileNotFoundException e){
       		System.out.println("Input grammar file does not exist.");
       	}
		   return System.in;
	   }
	/* 
	* END: TO DO: public static InputStream getInputStream(String[] args)
	*/


	/*
	* START: TO DO: public static PrintStream getOutputStream(String[] args)
	*/
	public static PrintStream getOutputStream(String[] args){
		try{
			if(args.length==2){
				//File file=new File(args[0]);
				PrintStream printStream=new PrintStream(args[1]);
				return printStream;
			}else if(args.length==4){
				int out_index=2;
				int file_index=0;
				if(args[out_index].equals("-out")){
					file_index=3;
				}else {
					file_index=1;
				}
				//File file=new File(args[file_index]);
				PrintStream printStream=new PrintStream(args[file_index]);
				return printStream;
			}
		}catch (FileNotFoundException e){
			System.out.println("Output file can not be created.");
		}
		return System.out;
	}

	/* 
	* END: TO DO: public static PrintStream getOutputStream(String[] args)
	*/


	public static void main(String[] args) throws IOException{
		System.out.println("Running TraceryRecursion...");

		String startSymbol = "#origin#"; 

		int count = 10; 
		long seed = 1; 

		/*
		* START: TO DO: call getInputStream(args) and getOutputStream(args) to get the InputStream and PrintStream to use
		*/
         InputStream inputStream=getInputStream(args);
         PrintStream outStream=getOutputStream(args);
		/*
		* END: TO DO: call getInputStream(args) and getOutputStream(args) to get the InputStream and PrintStream to use
		*/

		Rule.setSeed(seed); // Set the seed using a static method defined on Rule

		// To DO: comment this line back in to load the grammar into the Hashtable once you've set the inputStream you're using
		Hashtable<String, Rule[]> grammar = loadGrammar(inputStream);

		// TO DO: comment this line back in to print the loaded grammar. You'll need to set outStream correctly
		outputGrammar(grammar, outStream);

		Rule rule = new Rule(startSymbol); // Create a new Rule object using the startSymbol
		// Expand the start symbol until there are no more symbols to expand. Do this 'count' number of times.
		for (int i = 0; i < count; i++) { 
			// TO DO: Change the line below so it prints to the correct PrintStream instead of always System.out
			// System.out.println(rule.expand(grammar));
			outStream.println(rule.expand(grammar));
		}
	}
}
