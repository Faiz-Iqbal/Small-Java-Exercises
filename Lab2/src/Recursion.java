/**
 * 
 * @author mohammedfaiziqbal
 *
 */
public class Recursion {

	// Part 1: complete
	/**
	 * Method Sum is a recursive method thattakes a int value n, 
	 * checks whether its 0 in the base case, if not then it adds 
	 * up all the numbers between n and 0.
	 * @param n
	 * @return
	 */
	public static int sum(int n){
		if (n==0) {return 0;}
		else {
			return n + sum(n-1);
		}
	}

	// Part 1 complete
	/**
	 * Method multiply is a recursive method takes 2 parameters m and n. In
	 * order to multiply these without using the * operator we will have to
	 * add m together n times. eg. 2*3 > 2+2+2. For this we will first check
	 * if any of the value m or n are 0 since that'll return 0 then use the
	 * second if else function to check whether we are multiplying only
	 * positives, positive and negative or only negatives and use an appropriate
	 * recursive function to avoid having an infinte loop when negative numbers are involved
	 * @param m
	 * @param n
	 * @return
	 */
	public static int multiply(int m, int n){
		if(m==0 || n==0) return 0;
		else {
			if(n<0) {return -m + multiply(m,n+1);}
			else {return m + multiply(m,n-1);}
		}
	}

	// Part 1: complete
	/**
	 * Fibonacci method also uses recursion to create a fibonacci sequence by
	 * taking the sum of the previous two numbers in the sequence. This sequence
	 * requires 2 base cases 0 and 1 as starting points to create the sequence
	 * @param n
	 * @return
	 */
	public static int Fibonacci(int n){ 
		if (n==0) return 0;
		else if(n==1)return 1;
		else return Fibonacci(n-2)+Fibonacci(n-1);
	}


}