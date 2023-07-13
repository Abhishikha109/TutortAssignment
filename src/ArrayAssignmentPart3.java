import java.util.*;

/*
	https://leetcode.com/problems/find-common-characters/
	https://leetcode.com/problems/third-maximum-number/
	https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
	https://leetcode.com/problems/minimum-moves-to-equal-array-elements/
	https://leetcode.com/problems/assign-cookies/
	https://leetcode.com/problems/degree-of-an-array/
	https://leetcode.com/problems/can-place-flowers/
	https://leetcode.com/problems/plus-one/
	https://leetcode.com/problems/single-number/
	https://leetcode.com/problems/single-number-ii/
	https://leetcode.com/problems/single-number-iii/
	https://leetcode.com/problems/multiply-strings/
	https://leetcode.com/problems/valid-tic-tac-toe-state/
	https://leetcode.com/problems/ones-and-zeroes/
*/

public class ArrayAssignmentPart3 extends CommonMethods implements Assignments{

	@Override
	public void allQuestion() {
		
		System.out.println(question1_commonChars(new String[] {"bella","label","roller"}));
		System.out.println(question2_thirdMax(new int[] {1,2}));
		System.out.println(question3_findDisappearedNumbers(new int[] {4,3,2,7,8,2,3,1}));
		System.out.println(question4_minMoves(new int[] {1,2,3}));
		System.out.println(question5_findContentChildren(new int[] {1,2}, new int[] {1,2,3}));
		System.out.println(question6_findShortestSubArray(new int[] {1,2,2,3,1}));
		System.out.println(question7_canPlaceFlowers(new int[] {1,0,0,0,1}, 1));
		System.out.println(Arrays.toString(question8_plusOne(new int[] {8,9,9})));
		System.out.println(question9_singleNumber(new int[] {4,1,2,1,2}));
		System.out.println(question10_singleNumberII(new int[] {0,1,0,1,0,1,99}));
		System.out.println(Arrays.toString(question11_singleNumberIII(new int[] {1,2,1,3,2,5})));
		System.out.println(question12_multiply("2", "3"));
		System.out.println(question13_validTicTacToe(new String[] {"XOX"," X ","   "}));
		System.out.println(question14_findMaxForm(new String[] {"10","0001","111001","1","0"}, 5, 3));
	}

	private List<String> question1_commonChars(String[] words) {
		printQuestion("\n1. Find Common Characters");
		
        int[][] freq = new int[words.length][26];
        List<String> result = new ArrayList<String>();

        for(int i=0; i<words.length; i++){
            for(char ch : words[i].toCharArray()){
                freq[i][(int)ch-'a']++;
            }
        }

        int minFreq, asciiVal;
        char alphabet;
        boolean flag;

        for(int i=0; i<26; i++){
            int totalCount = freq[0][i];
            if(totalCount == 0)
                continue;
            minFreq = totalCount;
            flag = true;

            for(int j=1; j<words.length; j++){
                if(freq[j][i] != totalCount){
                    minFreq = Math.min(minFreq, freq[j][i]);
                }
                else if(freq[j][i] == 0){
                    flag = false;
                    break;
                }
            }

            if(flag && minFreq > 0){
                while(minFreq > 0){
                    asciiVal = 97+i;
                    alphabet=(char)asciiVal; 
                    result.add(String.valueOf(alphabet));
                    minFreq--;
                }
            }
        }

        return result;
    }
	
	private int question2_thirdMax(int[] nums) {
		printQuestion("\n2. Third Maximum Number");
		
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
	
	private List<Integer> question3_findDisappearedNumbers(int[] nums) {
		
		printQuestion("\n3. Find All Numbers Disappeared in an Array");
		
        List<Integer> result = new ArrayList<Integer>();

        int index;
        for(int i=0; i<nums.length; i++){
            index = (nums[i] > 0) ? nums[i] : -1*nums[i];
            --index;

            if(nums[index] > 0){
                nums[index] *= -1;
            }
        }

        for(int i=0; i<nums.length; i++){
            if(nums[i] > 0){
                result.add(i+1);
            }
        }
        return result;
    }
	
	private int question4_minMoves(int[] nums) {
		printQuestion("\n4. Minimum Moves to Equal Array Elements");
		
	      // int maxElement = nums[0];
        int minElement = nums[0];

        for(int i=0; i<nums.length; i++){
            // maxElement = Math.max(maxElement, nums[i]);
            minElement = Math.min(minElement, nums[i]);
        }

        int count = 0, count1 = 0;
        for(int i=0; i<nums.length; i++){
            count += nums[i] - minElement;
            // count1 += maxElement - nums[i];
        }

        // System.out.println(count1 - count);
        return count;
    }
	
	private int question5_findContentChildren(int[] g, int[] s) {
		printQuestion("\n5. Assign Cookies");
		
	    Arrays.sort(g);
        Arrays.sort(s);

        int gLength = g.length, sLength = s.length, i=0, j=0, count=0;

        while(i < gLength && j < sLength){
            if(g[i] <= s[j]){
                count++;
                i++;
                j++;
            }
            else if(g[i] > s[j]){
                j++;
            }
        }
        return count;
    }
	
	private int question6_findShortestSubArray(int[] nums) {
		printQuestion("\n6. Degree of an Array");
		
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>(), left = new HashMap<Integer, Integer>(), right = new HashMap<Integer, Integer>();
        
        int leastArrayLength = Integer.MAX_VALUE;

        for(int i=0; i<nums.length; i++){
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
            if(!left.containsKey(nums[i])){
                left.put(nums[i], i);
            }
            right.put(nums[i], i);
        }

        int maxFreq = Collections.max(freq.values());

        for(int x : freq.keySet()){
            if(freq.get(x) == maxFreq)
            leastArrayLength = Math.min(leastArrayLength, right.get(x) - left.get(x) + 1);
        }

        return leastArrayLength;
    }
	
	private boolean question7_canPlaceFlowers(int[] flowerbed, int n) {
		printQuestion("\n7. Can Place Flowers");
		
        int flowerbedLength = flowerbed.length;

        for(int i=0; i<flowerbedLength; i++){
                if((flowerbed[i] == 0) &&
                ((i-1 >=0 && i+1<flowerbedLength && flowerbed[i-1] != 1 && flowerbed[i+1] != 1) || 
                (i-1 < 0 && i+1<flowerbedLength && flowerbed[i+1] !=1) || 
                (i+1 >= flowerbedLength && i-1 >= 0 && flowerbed[i-1] != 1) || 
                (flowerbedLength == 1 && flowerbed[0] == 0))){
                    flowerbed[i] = 1;
                    n--;
                }
        }

        return (n <= 0);
    }
	
	private int[] question8_plusOne(int[] digits) {
		printQuestion("\n8. Plus One");
        for(int i=digits.length-1; i>=0; i--){
            if(digits[i] < 9){
                ++digits[i];
                return digits;
            }
            digits[i] = 0;
        }

        digits = new int[digits.length + 1];
        digits[0] = 1;
        
        return digits;
    }
	
	private int question9_singleNumber(int[] nums) {
		printQuestion("\n9. Single Number");
		
        int result = nums[0];

        for(int i=1; i<nums.length; i++){
            result ^= nums[i];
        }

        return result;
	}
	
	private int question10_singleNumberII(int[] nums) {
		printQuestion("\n10. Single Number II");
		
        int result = 0, sum;

        for(int i=0; i<32; i++){
            sum = 0;
            for(int j=0; j<nums.length; j++){
                if(((nums[j] >> i) & 1) == 1){
                    sum++;
                    sum %= 3;
                }
            }

            if(sum != 0){
                result |= sum << i;
            }
        }

        return result;
    }
	
	private int[] question11_singleNumberIII(int[] nums) {
		printQuestion("\n11. Single Number III");
		
        int xor = 0;
        for (int curr: nums) {
            xor ^= curr;    
        }

        int setBit = 0;
        for (int i=0; i<32; ++i) {
            if ((xor & (1<<i)) > 0) {
                setBit = i;
                break;
            }
        }

        int first = 0;
        for (int a: nums) {
            if ((a & (1<<setBit)) >0) {
                first ^= a;
            }
        }
 
        int second = first^xor;

        return new int[] {first, second};
    }
	
	private String question12_multiply(String num1, String num2) {
		printQuestion("\n12. Multiply Strings");
		
        int n = num1.length(), m = num2.length();

        int[] result = new int[n+m]; // the total wont exceed combine length of both the numbers
        int mul, p1, p2, sum;

        for(int i=n-1; i>=0; i--){
            for(int j=m-1; j>=0; j--){
                mul = ((int)num1.charAt(i) - '0') * ((int)num2.charAt(j) - '0');
                p1 = i+j; // this index stores actual number from previous calculation
                p2 = i+j+1; // this index stores carry from previous calculation

                sum = mul + result[p2];
                result[p1] += sum/10; // now here it will store carry
                result[p2] = sum%10; // now here it will store actual number
            }
        }

        StringBuilder answer = new StringBuilder();

        for(int p : result){
            if(!(answer.length() == 0 && p == 0)){
                // eliminating trailing zeroes and consider zeroes which comes in between of the digits
                answer.append(Integer.toString(p));
            }
        }

        return answer.length() == 0 ? "0" : answer.toString();
    }
	
	private boolean question13_validTicTacToe(String[] board){
		printQuestion("\n13. Valid Tic-Tac-Toe State");
        /*
        For winning three situations arises: 
            1) When X wins, then Y must lose : count of X must be greater than count O by 1. 
            2) When Y wins, then X must lose : count of X must be equal to count O 
            3) No one wins: count difference should differ by 1 or it can be zero.
        */

        int countO = 0, countX = 0;
        boolean xWin = false, oWin = false;
        int[] row = new int[3];
        int diag = 0;
        int[] col = new int[3];
        int antiDiag = 0;

        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if(board[i].charAt(j) == 'X'){
                    countX++; row[i]++; col[j]++;
                    if(i == j)
                        diag++;
                    if(i + j == 2)
                        antiDiag++;
                }
                else if(board[i].charAt(j) == 'O'){
                     countO++; row[i]--; col[j]--;
                    if(i == j)
                        diag--;
                    if(i + j == 2)
                        antiDiag--;
                }
            }
        }

        xWin = diag == 3 || antiDiag == 3;
        oWin = diag == -3 || antiDiag == -3;

        for(int i=0; i<3; i++){
            xWin = xWin || row[i] == 3 || col[i] == 3;
            oWin = oWin || row[i] == -3 || col[i] == -3;
        }

        int diff = countX-countO;

        if(xWin){
            return !oWin && diff == 1; // 1)
        }

        if(oWin){
            return !xWin && diff == 0; // 2)
        }

        return countX >= countO && diff <= 1; // 3)
    }
	
	private int question14_findMaxForm(String[] strs,int passedZero, int passedOne){
		printQuestion("\n14. Ones and Zeroes");
		
//		int[][] dp = new int[passedOne+1][passedZero+1];
//
//        ArrayList<Pair<Integer, Integer>> list = new ArrayList<Pair<Integer, Integer>>();
//        int zero, one;
//
//        for(String s : strs){
//            zero = 0; one = 0;
//
//            for(char c : s.toCharArray()){
//                if(c == '0') 
//                    zero++;
//                else one++;
//            }
//            list.add(new Pair<>(one, zero));
//        } 
//
//        for(Pair<Integer, Integer> s: list){
//            one = s.getKey() > 0 ? s.getKey() : 0;
//            zero = s.getValue() > 0 ? s.getValue() : 0;
//
//            for(int i=passedOne; i >= one; i--){
//                for(int j=passedZero; j >= zero; j--){
//                    dp[i][j] = Math.max(1 + dp[i-one][j-zero], dp[i][j]);
//                }
//            }
//        }
//
//        return dp[passedOne][passedZero];
        
        return 0;
    }
	
    private void reverseArray(int[] arr){
        int leftIndex = 0, rightIndex = arr.length-1;
        int temp;

        while(leftIndex <= rightIndex){
            temp = arr[leftIndex];
            arr[leftIndex] = arr[rightIndex];
            arr[rightIndex] = temp;
            leftIndex++;
            rightIndex--;
        }
    }

    
    private StringBuilder addDigits(String num1, String num2){
        String smallNumber;
        String largeNumber; 
        int lengthDiff;
        if(num1.length() > num2.length()){
            largeNumber = num1;
            smallNumber = num2;
            lengthDiff = num1.length() - num2.length();
        }
        else {
            largeNumber = num2;
            smallNumber = num1;
            lengthDiff = num2.length() - num1.length();
        }

        StringBuilder result = new StringBuilder("");
        int carry = 0, i = largeNumber.length()-1, j = i - lengthDiff;

        while(i>=0 && j>=0){
            int sum = (int)largeNumber.charAt(i) - '0' + (int)smallNumber.charAt(j) - '0' + carry;
            carry = sum/10;
            result.append(Integer.toString(sum%10));
            i--;
            j = i-lengthDiff;
        }

        while(i>=0){
            int sum = (int)largeNumber.charAt(i) - '0' + carry;
            carry = sum/10;
            result.append(Integer.toString(sum%10));
            i--;
        }

         if(carry > 0){
            result.append(Integer.toString(carry));
        }

        return result.reverse();
    }

    private StringBuilder multiplyDigits(String num1, char num2){
        StringBuilder result = new StringBuilder("");
        int secondNumber = (int)num2 - '0';
        int firstNumber;
        int carry = 0;
        int mul;

        for(int i=num1.length()-1; i>=0; i--){
           firstNumber = (int)num1.charAt(i) - '0';
           mul = (firstNumber * secondNumber) + carry;
           carry = mul / 10;
           result.append(Integer.toString(mul%10));
        }

        if(carry > 0){
            result.append(Integer.toString(carry));
        }

        return result.reverse();
    }

    private String multiplyMyApproach(String num1, String num2) {
        if(num1.length() == 1 && num1.charAt(0) == '0'){
            return "0";
        }

        if(num2.length() == 1 && num2.charAt(0) == '0'){
            return "0";
        }

        String smallNumber;
        String largeNumber; 

        if(num1.length() > num2.length()){
            largeNumber = num1;
            smallNumber = num2;
        }
        else {
            largeNumber = num2;
            smallNumber = num1;
        }
        
        StringBuilder multiplyResult;
        StringBuilder prevResult = new StringBuilder("0");
        StringBuilder tempZero = new StringBuilder();

        for(int i=smallNumber.length()-1; i>=0; i--){
            multiplyResult = multiplyDigits(largeNumber, smallNumber.charAt(i));
            prevResult = 
            addDigits(multiplyResult.append(tempZero.toString()).toString(), prevResult.toString());
            tempZero.append("0");
        }

        return prevResult.toString();
    }
    
    private int[] plusOneMyApproach(int[] digits) {
        List<Integer> result = new ArrayList<Integer>();
        int k=0;
        int digLength = digits.length;

        int carry = 1;
        int temp;
        for(int i=digLength-1; i>=0; i--){
            temp = digits[i] + carry;
            result.add(temp%10);
            carry = temp/10;
        }

        if(carry == 1)
        result.add(1);

        int[] finalResult = new int[result.size()];

        for(Integer a : result){
            finalResult[k++] = a;
        }

        reverseArray(finalResult);

        return finalResult;
    }
    
    
//  int[][][] dp;
//    
//    private int ans(ArrayList<Pair<Integer, Integer>> list, int index, int nOnes, int nZeros){
//        // for(Pair<Integer, Integer> s : list){
//        //     System.out.println(s.getKey() + ", " + s.getValue());
//        // }
//
//        if(list.size() == index || nOnes + nZeros == 0)
//            return 0;
//        
//        if(dp[nOnes][nZeros][index] > 0) { // MEMOIZATION
//        	return dp[nOnes][nZeros][index];
//        }
//        
//        int consider = 0;
//        if(list.get(index).getKey() <= nOnes &&  list.get(index).getValue() <= nZeros){
//            consider = 1 + ans(list, index+1, nOnes-list.get(index).getKey(), nZeros - list.get(index).getValue());
//        }
//
//        int skip = ans(list, index+1, nOnes, nZeros);
//        
//        dp[nOnes][nZeros][index] = Math.max(skip, consider);
//
//        return dp[nOnes][nZeros][index];
//    }
//    
//    private int findMaxForm(String[] strs, int passedZero, int passedOne) {
//    	dp = new int[passedOne+1][passedZero+1][strs.length]; // MEMOIZATION
//    	
//        ArrayList<Pair<Integer, Integer>> map = new ArrayList<Pair<Integer, Integer>>();
//        int zero, one;
//
//        for(String s : strs){
//            zero = 0; one = 0;
//
//            for(char c : s.toCharArray()){
//                if(c == '0') 
//                    zero++;
//                else one++;
//            }
//            map.add(new Pair<>(one, zero));
//        } 
//
//        return ans(map, 0, passedOne, passedZero);
//    }
}
