import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/*
 
1. Reverse the Number like 153 => 351 . Numbers can be negative

2. https://practice.geeksforgeeks.org/problems/pascal-triangle0652/1

3. https://leetcode.com/problems/richest-customer-wealth

4. https://leetcode.com/problems/running-sum-of-1d-array/

5. https://leetcode.com/problems/jewels-and-stones

6. https://leetcode.com/problems/minimum-absolute-difference

7. https://leetcode.com/problems/three-consecutive-odds

8. https://leetcode.com/problems/move-zeroes

*/

public class WarmUpAssignmentPart2 extends CommonMethods implements Assignments{
	
	@Override
	public void allQuestion() {
		System.out.println(question1(-234));
		System.out.println(question2_nthRowOfPascalTriangle(3));
		System.out.println(question3(new int[][] {{1,2,3},{3,2,1}}));
		System.out.println(Arrays.toString(question4(new int[] {3,1,2,10,1})));
		System.out.println(question5("aA", "aAAbbbb"));
		System.out.println(question6(new int[] {3,8,-10,23,19,-4,-14,27}));
		System.out.println(question7(new int[] {1,2,34,3,4,5,7,23,12}));
		System.out.println(Arrays.toString(question8(new int[] {0,1,0,3,12})));
	}
	
	private int question1(int num) {
		printQuestion("\n1. Reverse the Number like 153 => 351 . Numbers can be negative");
		
		boolean isNegative = false;
		
		if(num < 0) {
			isNegative = true;
			num *= -1;
		}
		
		int reverseNum = 0;
		int prod = 10;
		while(num > 0) {
			reverseNum = (reverseNum * prod) + (num % 10 );
			num /= 10;
		}
		
		return (isNegative ? -reverseNum : reverseNum);
	}

	private List<Integer> question2_nthRowOfPascalTriangle(int rowIndex) {
		printQuestion("\n2. Pascal Triangle");
		
        Integer[] pascalRow = new Integer[rowIndex + 1];
        Arrays.fill(pascalRow, 0);

        pascalRow[0] = 1;

        for(int i=1; i<rowIndex + 1; i++){
            for(int j=i; j>=1; j--){
                pascalRow[j] += pascalRow[j-1];
            }
        }

        return Arrays.asList(pascalRow);
	}

	private int question3(int[][] accounts) {
		printQuestion("\n3. Richest Customer Wealth");
		
        int maxWealth = 0;
        int tempSum; 
        for(int i=0; i<accounts.length; i++){
            tempSum = 0;
            for(int j=0; j<accounts[i].length; j++){
                tempSum += accounts[i][j];
            }

            maxWealth = tempSum > maxWealth ? tempSum : maxWealth;
        }

        return maxWealth;
	}
	
	private int[] question4(int[] nums) {
		printQuestion("\n4. Running Sum of 1d Array");
		
        for(int i=1; i<nums.length; i++){
            nums[i] += nums[i-1]; 
        }
        
		return nums;
	}

	private int question5(String jewels, String stones) {
		printQuestion("\n5. Jewels and Stones");
		
		Set<Character> set = new HashSet<Character>();

        for(int i=0; i<jewels.length(); i++){
            set.add(jewels.charAt(i));
        }

        int countJewels = 0;

        for(int i=0; i<stones.length(); i++){
            if(set.contains(stones.charAt(i))){
            	countJewels++;
            }
        }

       return countJewels;
	}
	
	private List<List<Integer>> question6(int[] arr) {
		printQuestion("\n6. Minimum Absolute Difference");
		
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for(int i=0; i<=arr.length-2; i++){
            if(arr[i+1] - arr[i] == minDiff){
                result.add(Arrays.asList(arr[i], arr[i+1]));
            }
            else if(arr[i+1] - arr[i] < minDiff){
                    result.clear();
                    minDiff = arr[i+1] - arr[i];
                    result.add(Arrays.asList(arr[i], arr[i+1]));
                }
        }

        return result;
	}
	
	private boolean question7(int[] arr) {
		printQuestion("\n7. Three Consecutive Odds");
		
		 if(arr.length < 3)
	            return false;
	
	        for(int i=0; i<=arr.length - 3; i++){
	            if(arr[i] % 2 == 1 && arr[i+1] % 2 == 1 && arr[i+2] % 2 == 1){
	                return true;
	            }
	        }
	
	    return false;
	}
	
	private int[] question8(int[] nums) {
		printQuestion("\n8. Move Zeroes");
		
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
}
