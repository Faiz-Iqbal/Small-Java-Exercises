 // exception used for Q5
class CalculateException extends RuntimeException{    
	public CalculateException(String err) {
		super(err);
	}
}
/**
 * 
 * @author mohammedfaiziqbal
 *
 */
//Commit test comment
//....


public class Calculator {
	/*
	 *  Methods for Part 3
	 */

	/**
	 * Method that takes the reversed array, iterates through it to solve the 
	 * polish notation
	 * @param cmds
	 * @return
	 */
	public static int calculate(String [] cmds){
		Stack pstack = new Stack(100);
		int result = 0;
		// reverse the array cmds
		Reverse.reverse(cmds);
		for(int i=0; i<cmds.length; i++) {
			if(isNumber(cmds[i])) {
				pstack.push(convert(cmds[i]));
			}else {
				result = applyOp(pstack.pop().toString(),cmds[i],pstack.pop().toString());
				pstack.push(result);
			}
		}
		return convert(pstack.pop().toString());
	}


	/**
	 * Convert method, that takes a string and converts it to int
	 * using parseint which is called from the integer class
	 *  
	 * @param s
	 * @return
	 * @throws NumberFormatException
	 */
	public static int convert(String s) throws NumberFormatException{
		int conv = Integer.parseInt(s);
		return conv;
	}

	/**
	 * Method tests whether a value is a number or on operator ex. "+"
	 * @param s
	 * @return
	 */
	public static boolean isNumber(String s){
		//Checks if the value is an int or not
		boolean testBool = false;

		try {
			if(Integer.parseInt(s)*0 == 0) {
				testBool = true;
			}
		}catch(NumberFormatException e) {
			testBool = false;
		}

		return testBool;
	}

	/**
	 * Takes the values that are numbers and performs the arithmetic operation 
	 * required.
	 * @param fst
	 * @param op
	 * @param snd
	 * @return
	 */
	// apply the operator after converting the numbers
	public static int applyOp(String fst,String op,String snd){
		int n1 = convert(fst);
		int n2 = convert(snd);

		if(op == "+") {
			return n1+n2;
		}else if(op == "-") {
			return n1-n2;
		}
		return 0;

	}

	// main operation to calculate using Polish notation directly
	public static int calculatePolish(String [] cmds){
		return -1; // dummy value
	}
}
