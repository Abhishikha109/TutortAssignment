import java.util.Arrays;

/*
 
https://leetcode.com/problems/happy-number/

https://leetcode.com/problems/power-of-two/

https://leetcode.com/problems/valid-anagram/

https://leetcode.com/problems/ugly-number/

https://leetcode.com/problems/move-zeroes/

https://leetcode.com/problems/reverse-vowels-of-a-string/

https://leetcode.com/problems/third-maximum-number/

https://leetcode.com/problems/find-the-difference/

https://leetcode.com/problems/add-digits/

https://leetcode.com/problems/sum-of-digits-of-string-after-convert/

*/

public class WarmUpAssignmentPart3 extends CommonMethods implements Assignments{

	@Override
	public void allQuestion() {
		System.out.println(question1_isHappy(2));
		System.out.println(question2_isPowerOfTwo(8));
		System.out.println(question3_isAnagram("anagram", "nagaram"));
		System.out.println(question4_isUgly(6));
		System.out.println(Arrays.toString(question5_moveZeroes(new int[] {0,1,0,3,12})));
		System.out.println(question6_reverseVowels("hello"));
		System.out.println(question7_thirdMax(new int[] {2,2,3,1}));
		System.out.println(question8_findTheDifference("abcd", "abcde"));
		System.out.println(question9_addDigits(38));
		System.out.println(question10_getLucky("iiii", 1));
	}
	
    private int computeSquaredSum(int num){
        int sum = 0;

        while(num > 0){
            sum += (num%10)*(num%10);
            num /= 10;
        }

        return sum;
    }
    
    private boolean question1_isHappy(int num) {
    	printQuestion("\n1. Is Happy");
    	
        int firstPointer = num, secondPointer = num;
        do{
            firstPointer = computeSquaredSum(firstPointer);
            secondPointer = computeSquaredSum(computeSquaredSum(secondPointer));
            if(firstPointer == 1)
                return true;
        }while(firstPointer != secondPointer);

        return false;
    }
    
    private boolean question2_isPowerOfTwo(int n) {
    	printQuestion("\n2. Is power of two");
    	
        if(n<=0)
        return false;

        return (n & (n-1))  == 0 ? true : false;
    }
    
    private boolean question3_isAnagram(String s, String t) {
    	printQuestion("\n3. Is Anagram");
    	
        if(s.length() != t.length())
            return false;
            
        int[] charHash = new int[26];

        for(int i=0; i<s.length(); i++){
            charHash[s.charAt(i) - 97]++;
        }

        for(int i=0; i<t.length(); i++){
            if(charHash[t.charAt(i) - 97] > 0){
                charHash[t.charAt(i) - 97]--;
            }
            else {
                return false;
            }
        }

        return true;
    }
    
    private boolean question4_isUgly(int n) {
    	printQuestion("\n4. Is Ugly");
    	
        if(n <= 0)
            return false;

        while(n%2 == 0){
            n = n/2;
        }

        while(n % 3 == 0){
            n = n/3;
        }
            
        while(n % 5 == 0){
            n = n/5;
        }
            
        return (n == 1) ? true : false;
    }
    
    private int[] question5_moveZeroes(int[] nums) {
    	printQuestion("\n5. Move Zeroes");
    	
        int index = 0;

        for(int i=0; i<nums.length; i++){
            if(nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                index++;
            }
        }
        
        return nums;
    }
    
    private boolean isVowel(char s){
        return (s == 'a' || s == 'e' || s == 'i' || s == 'o' || s == 'u' 
         || s == 'A' || s == 'E' || s == 'I' || s == 'O' || s == 'U');
    }
    
    private String question6_reverseVowels(String s) {
    	printQuestion("\n6. Reverse Vowels");
    	
        int start=0, end=s.length()-1;
        char[] chars = s.toCharArray();

        while(start < end){
            while(start < end && !isVowel(chars[start])){
                start++;
            }

            while(start < end && !isVowel(chars[end])){
                end--;
            }

            if(start<end && end>=0 && start < s.length()){
                char temp = chars[start];
                chars[start] = chars[end];
                chars[end] = temp;
            }
            start++; end--;
        }
        return new String(chars);
    }
    
    private int question7_thirdMax(int[] nums) {
    	printQuestion("\n7. Thrid Max");
    	
        long firstMax = Long.MIN_VALUE;
        long secondMax = Long.MIN_VALUE;
        long thirdMax = Long.MIN_VALUE;

        for(int i=0; i<nums.length; i++){
            if(nums[i] > firstMax){
                thirdMax = secondMax;
                secondMax = firstMax;
                firstMax = nums[i];
            }
            else if(nums[i] > secondMax && nums[i] != firstMax){
                thirdMax = secondMax;
                secondMax = nums[i];
            }
            else if(nums[i] > thirdMax && nums[i] != firstMax && nums[i] != secondMax){
                thirdMax = nums[i];
            }
        }

        return (int)(thirdMax == Long.MIN_VALUE ? firstMax : thirdMax);
    }
    
    public char question8_findTheDifference(String s, String t) {
    	printQuestion("\n8. Find the difference");
    	
        int a = 0;

        for(int i=0; i<s.length(); i++){
            a ^= s.charAt(i);
        }

        for(int i=0; i<t.length(); i++){
            a ^= t.charAt(i);
        }

        return (char)a;
    }
    
    private int question9_addDigits(int num) {
    	printQuestion("\n9. Add digits");
    	
        int sum;
        while(num % 10 != num){
            sum = 0;
            while(num > 0){
                sum += num % 10;
                num /= 10;
            }
            num = sum;
        }

        return num;
        
        /* Other solution */
        //return (num == 0) ? 0 : num % 9 == 0 ? 9 : num % 9;
    }
    
    private int question10_getLucky(String s, int k) {
    	printQuestion("\n10. Get Lucky");
    	
        StringBuilder sb = new StringBuilder();
        int value;
        for(int i=0; i<s.length(); i++){
            value = (int)(s.charAt(i)) - 'a' + 1;
            sb.append(value);
        }

        int sum = 0;

        while(k > 0){
            sum = 0;
            for(int i=0; i<sb.length(); i++){
                sum += (int)sb.charAt(i) - '0';
            }

            sb = new StringBuilder();
            sb.append(sum);
            k--;
        }
        
        return sum;
    }

}
