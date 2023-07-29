import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 1. https://leetcode.com/problems/add-binary/

2. https://leetcode.com/problems/shuffle-the-array/

3. https://leetcode.com/problems/kids-with-the-greatest-number-of-candies/

4. https://leetcode.com/problems/sum-of-all-subset-xor-totals/

5. https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/

6. https://leetcode.com/problems/three-consecutive-odds/

7. https://leetcode.com/problems/decode-xored-array/

8. https://leetcode.com/problems/sort-array-by-parity-ii/

9. https://leetcode.com/problems/duplicate-zeros/

10. https://leetcode.com/problems/contains-duplicate-iii/

11. https://leetcode.com/problems/max-consecutive-ones/

12. https://leetcode.com/problems/max-consecutive-ones-iii/

13. https://leetcode.com/problems/online-election/

*/

public class ArrayAssignmentPart4 extends CommonMethods implements Assignments{

	@Override
	public void allQuestion() {
		System.out.println(question1_addBinaryMyApproach("11", "1"));
		System.out.println(question1_addBinaryOptimisedApproach("11", "1"));
		System.out.println(Arrays.toString(question2_shuffleMyApproach(new int[] {2,5,1,3,4,7}, 3)));
		System.out.println(Arrays.toString(question2_shuffleOptimisedApproach(new int[] {1,2,3,4,4,3,2,1}, 4)));
		System.out.println(question3_kidsWithCandies(new int[] {2,3,5,1,3}, 3));
		System.out.println(question4_subsetXORSumMyApproach(new int[] {1,3}));
		System.out.println(question4_subsetXORSumRecursion(new int[] {3,4,5,6,7,8}));
		System.out.println(question5_countNegatives(new int[][] {{4,3,2,-1},{3,2,1,-1},{1,1,-1,-2},{-1,-1,-2,-3}}));
		System.out.println(question6_threeConsecutiveOdds(new int[] {1,2,34,3,4,5,7,23,12}));
		System.out.println(Arrays.toString(question7_decode(new int[] {6,2,7,3}, 4)));
		System.out.println(Arrays.toString(question8_sortArrayByParityII(new int[] {4,2,5,7})));
		System.out.println(Arrays.toString(question9_duplicateZeros(new int[] {1,0,2,3,0,4,5,0})));
		System.out.println(question10_containsNearbyAlmostDuplicateMyApproach(new int[] {1,2,3,1}, 3, 0));
		System.out.println(question10_containsNearbyAlmostDuplicateOptimisedApproach(new int[] {1,2,3,1}, 3, 0));
		System.out.println(question11_findMaxConsecutiveOnes(new int[] {1,1,0,1,1,1}));
		System.out.println(question12_longestOnes(new int[] {1,1,1,0,0,0,1,1,1,1,0}, 2));
		TopVotedCandidate topVotedCandidate = new TopVotedCandidate(new int[] {0, 1, 1, 0, 0, 1, 0}, new int[] {0, 5, 10, 15, 20, 25, 30});
		int[] query = new int[] {3, 12, 25, 15, 24, 8};
		
		for(int i=0; i<query.length; i++) {
			System.out.print(topVotedCandidate.q(query[i]) + ", ");
		}
	}
	
	private String question1_addBinaryMyApproach(String a, String b) {
		printQuestion("\n1. Add Binary MyApproach");
		
        String carry = "0";
        StringBuilder result = new StringBuilder();

        int i = a.length()-1, j = b.length()-1;

        while(i>=0 && j>=0){
            if(a.charAt(i) == '1' && b.charAt(j) == '1'){
                result.append(carry.charAt(0) == '1' ? '1' : '0');
                carry = "1";
            }else if(a.charAt(i) == '1' && b.charAt(j) == '0' || 
                a.charAt(i) == '0' && b.charAt(j) == '1'){
                    result.append(carry.charAt(0) == '1' ? '0' : '1');
            }else {
                if(carry.charAt(0) == '1'){
                    result.append('1');
                    carry = "0";
                }
                else{
                    result.append('0');
                }
            }

            i--; j--;
        }

        while(i>=0){
            if(a.charAt(i) == '1' && carry.charAt(0) == '1'){
                result.append('0');
                carry = "1";
            }else if(
                a.charAt(i) == '1' && carry.charAt(0) == '0' || 
                a.charAt(i) == '0' && carry.charAt(0) == '1' ){
                result.append('1');
                carry="0";
            }else{
                result.append('0');
            }

            i--;
        }

        while(j>=0){
            if(b.charAt(j) == '1' && carry.charAt(0) == '1'){
                result.append('0');
                carry = "1";
            }else if(
                b.charAt(j) == '1' && carry.charAt(0) == '0' || 
                b.charAt(j) == '0' && carry.charAt(0) == '1' ){
                result.append('1');
                carry="0";
            }else{
                result.append('0');
            }

            j--;
        }

        if(carry.charAt(0) == '1'){
            result.append("1");
        }

        return result.reverse().toString();
    }
	
	private String question1_addBinaryOptimisedApproach(String a, String b) {
		
		printQuestion("\n1. Add Binary Optimised Approach");
		// for adding the decimal number we can use the same approach
		
        int carry = 0;
        StringBuilder result = new StringBuilder();

        int i = a.length()-1, j = b.length()-1;

        while(i>=0 || j>=0){
            int sum = carry;
            if(i >= 0) sum += a.charAt(i) - '0';
            if(j >= 0) sum += b.charAt(j) - '0';

            result.append(sum%2); // decimal number: do number % 10;
            carry = sum/2; // decimal number: do sum / 10;
            i--; j--;
        }

        if(carry != 0){
            result.append(carry);
        }
        return result.reverse().toString();
	}
	
	private int[] question2_shuffleMyApproach(int[] nums, int n) {
		printQuestion("\n2. Shuffle the Array My Approach");
		
        int[] result = new int[2*n];
        int start  = 0;

        for(int i=0; i<n; i++){
            result[i + start] = nums[i];
            result[i + start +1] = nums[i+n];
            start++;
        }

        return result;
    }
	
	private int[] question2_shuffleOptimisedApproach(int[] nums, int n) {
		printQuestion("\n2. Shuffle the Array Optimised");
		
		/* BIT MANIPULATION */
		/* since nums[i] max value is : 10^3, hence in terms of bits it is occupying only 10 bits out of 32 bits, hence we can utilize the other digit bits in the 
		 remaining space. */
		
	
        for(int i=n; i<2*n; i++){
            int firstNum = nums[i] << 10; // shifting 10 bits towards left
            nums[i-n] |= firstNum; // storing both the numbers in nums[i-n]
        }

        int allOnes = (int) Math.pow(2,10)-1;

        for(int i=n-1; i>=0; i--){
            int firstNum = nums[i] >> 10;
            int secondNum = nums[i] & allOnes;

            nums[2*i + 1] = firstNum;
            nums[2*i] = secondNum;
        }

        return nums;
    }
	
	private List<Boolean> question3_kidsWithCandies(int[] candies, int extraCandies) {
		
		printQuestion("\n3. Kids With the Greatest Number of Candies");
		
        int maxCandies = candies[0];

        for(int candy : candies){
            maxCandies = (maxCandies < candy) ? candy : maxCandies;
        }

        List<Boolean> resultantCandies = new ArrayList<Boolean>(candies.length);
        int totalCandies;
        for(int candy : candies)
        {
            totalCandies = candy + extraCandies;
            resultantCandies.add(totalCandies >= maxCandies ? true : false);
        }

        return resultantCandies;
    }
	
	private int question4_subsetXORSumMyApproach(int[] nums) {
		printQuestion("\n4. Sum of All Subset XOR Totals My Approach");
		
        int sum = 0, temp, counter, j;
        int totalSubsets = (int)Math.pow(2, nums.length) - 1;

        for(counter=0; counter<=totalSubsets; counter++){
            temp = 0;
            for(j=0; j<nums.length; j++){
                if((counter & (1 << j)) > 0){
                    temp ^= nums[j];
                }
            }

            sum += temp;
        }

        return sum;
    }
	
    private int helper(int[] nums, int index, int currentXor) {
        if (index == nums.length) return currentXor;
        
        int withElement = helper(nums, index + 1, currentXor ^ nums[index]);
        int withoutElement = helper(nums, index + 1, currentXor);

        return withElement + withoutElement;
    }
    
	private int question4_subsetXORSumRecursion(int[] nums) {
		printQuestion("\n4. Sum of All Subset XOR Totals Optimised Recursion");
		
        return helper(nums, 0, 0);
    }
	
	private int question5_countNegatives(int[][] grid) {
		printQuestion("\n5. Count Negative Numbers in a Sorted Matrix");
		
        int colLength = grid[0].length;
        int currentRowNegativeIndex = colLength - 1;
        int totalNegativeNumber = 0;

        for(int[] row : grid){
            while(currentRowNegativeIndex >=0 && row[currentRowNegativeIndex] < 0){
                currentRowNegativeIndex--;
            }

            totalNegativeNumber += (colLength - (currentRowNegativeIndex + 1));
        }

        return totalNegativeNumber;
    }
	
	private boolean question6_threeConsecutiveOdds(int[] arr) {
		printQuestion("\n6. Three Consecutive Odds");
		
        if(arr.length < 3)
            return false;

        for(int i=0; i<=arr.length - 3; i++){
            if(arr[i] % 2 == 1 && arr[i+1] % 2 == 1 && arr[i+2] % 2 == 1){
                return true;
            }
        }

        return false;
    }
	
	private int[] question7_decode(int[] encoded, int first) {
		printQuestion("\n7. Decode XORed Array");
		
        int[] decodeResult = new int[encoded.length+1];
        decodeResult[0] = first;

        for(int i=1; i<decodeResult.length; i++){
            decodeResult[i] = encoded[i-1] ^ first;
            first = decodeResult[i];
        }

        return decodeResult;
    }
	
	private int[] question8_sortArrayByParityII(int[] nums) {
		printQuestion("\n8. Sort Array By Parity II");

        int even = 0, odd = 1, n = nums.length;

        while(even < n && odd < n){
            while(even < n && nums[even]%2 == 0){
                even += 2;
            }

            while(odd < n && nums[odd]%2 == 1){
                odd += 2;
            }

            if(even < n && odd < n){
                int temp = nums[even];
                nums[even] = nums[odd];
                nums[odd] = temp;
            }
        }

        return nums;
    }
	
	private int[] question9_duplicateZeros(int[] arr) {
		printQuestion("\n9. Duplicate Zeros");
		
        int zeroes = 0, n = arr.length;

        for(int i=0; i<n; i++){
            if(arr[i] == 0) zeroes++;
        }

        int i = n-1, j=n-1+zeroes;

        while(i < j){
            if(arr[i] != 0){
                if(j < n){
                    arr[j] = arr[i];
                }
            }else
            {
                if(j < n){
                    arr[j] = 0;
                }
                j--;
                if(j>=0 && j < n){
                    arr[j] = 0;
                }
            }
            i--;
            j--;
        }
        
        return arr;
	}
	
	private boolean question10_containsNearbyAlmostDuplicateMyApproach(int[] nums, int indexDiff, int valueDiff) {
		printQuestion("\n10. Contains Duplicate III");

        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j<nums.length; j++){
                if( Math.abs(i-j) <= indexDiff){
                    if(Math.abs(nums[j] - nums[i]) <= valueDiff){
                        return true;
                    }
                }else{
                    break;
                }
            }
        }

        return false;
    }
	
	
	private boolean question10_containsNearbyAlmostDuplicateOptimisedApproach(int[] nums, int indexDiff, int valueDiff) {
		printQuestion("\n10. Contains Duplicate III");
        HashMap<Long, Long> map = new HashMap<Long, Long>();

        for(int i=0; i<nums.length; i++){
            long remapped = (long)nums[i] - Integer.MIN_VALUE;
            long bucket = remapped / ((long)valueDiff + 1);

            if(map.containsKey(bucket) || 
               (map.containsKey(bucket - 1) && remapped - map.get(bucket-1) <= valueDiff) ||
               (map.containsKey(bucket + 1) && map.get(bucket+1) - remapped <= valueDiff))
            return true;
            
            if(map.size() >= indexDiff) {
                long lastValue = (long)nums[i - indexDiff] - Integer.MIN_VALUE;
                long lastBucket = lastValue / ((long)valueDiff + 1);

                map.remove(lastBucket);
            }

            map.put(bucket, remapped);
        }

        return false;
    }
	
	private int question11_findMaxConsecutiveOnes(int[] nums) {
		printQuestion("\n11. Max Consecutive Ones");
	
        int maxCount = 0;
        int count = 0;

        for(int i=0; i<nums.length; i++){
            if(nums[i] == 1){
               count++;
            }
            else {
                maxCount = Math.max(count, maxCount);
                count=0;
            }
        }

        maxCount = Math.max(count, maxCount);
        return maxCount;
    }
	
	private int question12_longestOnes(int[] nums, int K) {
		printQuestion("\n12. Max Consecutive Ones III");
		
//        int i = 0, j;
//        for (j = 0; j < nums.length; ++j) {
//            if (nums[j] == 0) K--;
//            if (K < 0 && nums[i++] == 0) K++;
//        }
//        return j - i;
		
        int zeros = 0, left = 0, maxLength = Integer.MIN_VALUE;

        for(int right=0; right < nums.length; right++){
            if(nums[right] == 0){
                zeros++;
            }
            if(zeros > K){
                while(zeros > K){
                    if(nums[left] == 0){
                        zeros--;
                    }
                    left++;
                }
            }

            maxLength = Math.max(maxLength, right-left+1);
        }

        return maxLength;
    }
	
	class TopVotedCandidate {
	    HashMap<Integer, Integer> count = new HashMap<Integer, Integer>();
	    HashMap<Integer, Integer> leadingCandidate = new HashMap<Integer, Integer>();
	    int[] time;

	    public TopVotedCandidate(int[] persons, int[] times) {
	    	
	    	printQuestion("\n13. Online Election\n");
	    	
	        time = times;
	        int lead = -1;
	        
	        for(int i=0; i<persons.length; i++){
	            count.put(persons[i], count.getOrDefault(persons[i], 0) + 1);

	            if(i == 0 || count.get(persons[i]) >= count.get(lead)){
	                lead = persons[i];
	            }
	            leadingCandidate.put(times[i], lead);
	        }
	    }
	    
	    public int q(int t) {
	        int i = Arrays.binarySearch(time, t);
	        // System.out.println(i<0 ? ("i: " + i + ", -i-2: " + (-i-2)) : "");
	        return (i<0) ? leadingCandidate.get(time[-i-2]) : leadingCandidate.get(time[i]);
	    }
	}

}
