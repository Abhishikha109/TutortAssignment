import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 1. https://leetcode.com/problems/powx-n/

2. https://leetcode.com/problems/power-of-two/

3. https://leetcode.com/problems/count-good-numbers/

4. https://leetcode.com/problems/minimum-non-zero-product-of-the-array-elements/

 */
public class RecursionAssignment extends CommonMethods implements Assignments{

	@Override
	public void allQuestion() {
		System.out.println(question1_myPow(2.00000, 10));
		System.out.println(question2_isPowerOfTwo(3));
		System.out.println(question3_countGoodNumbers(50));
		System.out.println(question4_minNonZeroProduct(60));
	}
	
    private double expo(double x, long n){
        if(n==0){
            return 1;
        }
        if(n < 0){
            return 1.0/expo(x, -1*n);
        }

        if(n%2 == 0){
           return expo(x*x, n/2);
        }else{
           return x*expo(x*x, (n-1)/2);
        }
    }
    
	private double question1_myPow(double x, long n) {
		
		printQuestion("\n1.  Pow(x, n)");
		
		 return expo(x, (long)n);
	}
	
	private boolean question2_isPowerOfTwo(int n) {
		printQuestion("\n2. Power of Two");
		
		if(n<=0)
	        return false;

	    return (n & (n-1))  == 0 ? true : false;
    }
	
    long MODULO = 1000000007;

    private long pow(long base, long exp){
        if(exp == 0) return 1;
        if(exp == 1) return base % MODULO;

        long temp = pow(base, exp/2);
        long answer = (temp*temp) % MODULO;
        
        return (exp % 2 == 0) ? answer : (answer*(base % MODULO)) % MODULO;
    }

    
	private int question3_countGoodNumbers(long n) {
		
		printQuestion("\n3. Count Good Numbers");
		
        long even = n - (n/2);
        long odd = n/2;

        long evenResult = pow(5, even) % MODULO;
        long oddResult = pow(4, odd) % MODULO;

        return (int)( (evenResult * oddResult) % MODULO);
    }
	
	private int question4_minNonZeroProduct(int p) {
		printQuestion("\n4. Minimum Non-Zero Product of the Array Elements");
		
        long val = (long)Math.pow(2,p); // expn(2, p) is not working
        val--;
        long K = val/2;            
        long N = val-1;            
        long ans = pow(N,K);      
        return (int) (ans * ((val) % MODULO) % MODULO); 
    }

}
