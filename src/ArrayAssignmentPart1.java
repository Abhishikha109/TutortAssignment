import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/*
 
1. https://leetcode.com/problems/two-sum/ 

2. https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

3. https://leetcode.com/problems/merge-sorted-array/ 

4. https://leetcode.com/problems/pascals-triangle/ 

5. https://leetcode.com/problems/pascals-triangle-ii/ 

6. https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

7. https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

8. https://leetcode.com/problems/majority-element/

9. https://leetcode.com/problems/majority-element-ii/

10.https://leetcode.com/problems/missing-ranges/ 

11.https://leetcode.com/problems/3sum/ 

12.https://leetcode.com/problems/3sum-smaller/ 

13.https://leetcode.com/problems/3sum-closest/ 

14.https://leetcode.com/problems/4sum/

15.https://leetcode.com/problems/rotate-image/

*/

public class ArrayAssignmentPart1 implements Assignments{

	@Override
	public void allQuestion() {
		System.out.println(Arrays.toString(question1_twoSum(new int[] {2,7,11,15}, 9)));
		System.out.println(Arrays.toString(question2_twoSum(new int[] {-1,0}, -1)));
		System.out.println(Arrays.toString(question3_merge(new int[] {1,2,3,0,0,0}, 3, new int[] {2,5,6}, 3)));
		question4_pascalTriangle(5);
		System.out.println(question5_getRow(3));
		System.out.println(question6_maxProfit(new int[] {7,1,5,3,6,4}));
		System.out.println(question7_maxProfit2(new int[] {7,1,3,6,3,8}));
		System.out.println(question8_majorityElement(new int[] {3,1,3,6,3,3}));
		System.out.println(question9_majorityElement(new int[] {3,2,3}));
		question15_rotate(new int[][] {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}});
	}
	
   private int[] question1_twoSum(int[] nums, int target) 
    {
        HashMap<Integer, Integer> indexedDict = new HashMap<Integer, Integer>();
        int diff, index;
        
        for(int i=0; i<nums.length; i++)
        {
           diff = target - nums[i];

           if(indexedDict.containsKey(diff)){
               index = indexedDict.get(diff);
                return new int[]{index, i};
           } 

           indexedDict.put(nums[i], i);

        }
        return new int[]{};
    }
   
   private int[] question2_twoSum(int[] numbers, int target) {
       int leftIndex=0, rightIndex=numbers.length - 1;

       while(leftIndex < rightIndex){
           if(numbers[leftIndex] + numbers[rightIndex] == target){
               return new int[]{leftIndex+1,rightIndex+1};
           }
           if(numbers[leftIndex] + numbers[rightIndex] > target){
               rightIndex--;
           }
           else {
               leftIndex++;
           }
       }

       return new int[]{};
   }
   
   private int[] question3_merge(int[] nums1, int m, int[] nums2, int n) {
       int i=m-1, j=n-1, k=m+n-1;

       while(i>=0 && j>=0){
           while(i>=0 && j>=0 && nums1[i] >= nums2[j]){
               nums1[k--] = nums1[i--];
           }

           while(i>=0 && j>=0 && nums2[j] > nums1[i]){
               nums1[k--] = nums2[j--];
           }
       }

       while(j>=0){
           nums1[k--] = nums2[j--];
       }
       
       return nums1;
   }
   
   private void question4_pascalTriangle(int numRows) {
       List<List<Integer>> triangle = new ArrayList<List<Integer>>();
       List<Integer> row;

       for(int i=0; i<numRows; i++){
           row = new ArrayList<Integer>();

           for(int j=0; j<=i; j++){
               row.add(j==0 || i==j ? 1 : triangle.get(i-1).get(j-1) + triangle.get(i-1).get(j));
           }
           triangle.add(row);
       } 

       for(int i=0; i<numRows; i++) {
    	   for(int j=0; j<=i; j++) {
    		   System.out.print(triangle.get(i).get(j) + ", ");
    	   }
    	   System.out.println();
       }
       
   }
   
   private List<Integer> question5_getRow(int rowIndex) {
       Integer[] pascalRow = new Integer[rowIndex+1];
       Arrays.fill(pascalRow, 0);
       pascalRow[0] = 1;

       for(int i=1; i<=rowIndex; i++){
           for(int j=i; j>0; j--){
               pascalRow[j] = pascalRow[j] + pascalRow[j-1];
           }
       }

       return Arrays.asList(pascalRow);
   }
   
   private int question6_maxProfit(int[] prices) {
       int minPrice = Integer.MAX_VALUE;
       int maxProfitTotal = Integer.MIN_VALUE;

       for(int i=0; i<prices.length; i++){
           minPrice = minPrice > prices[i] ? prices[i] : minPrice;
           maxProfitTotal = (maxProfitTotal < prices[i] - minPrice) ? prices[i] - minPrice : maxProfitTotal;
       }

       return maxProfitTotal;
   }
   
   private int question7_maxProfit2(int[] prices) {
       int minPrice = prices[0];
       int maxProfitTotal = 0;
       int prevValue = prices[0];

       for(int i=1; i<prices.length; i++){
           if(prices[i] > prevValue){
               prevValue = prices[i];
           }
           else {
               maxProfitTotal += prevValue - minPrice;
               prevValue = prices[i];
               minPrice = prices[i];
           }
       }

       if(prevValue > minPrice)
           maxProfitTotal += prevValue - minPrice;
       return maxProfitTotal;
   }
   
   private int question8_majorityElement(int[] nums) {

       /* O(nlogn) */

       // Arrays.sort(nums);
       // return nums[nums.length/2];

       /* Moore's voting algorithm  O(n)*/
       
       int count = 1;
       int majorityElement = nums[0];

       for(int i=1; i<nums.length; i++){
           if(majorityElement == nums[i]){
               count++;
           }
           else {
               count--;
           }

           if(count == 0){
               majorityElement = nums[i];
               count = 1;
           }
       }

       return majorityElement;
   }

   private List<Integer> question9_majorityElement(int[] nums) {
			List<Integer> result = new ArrayList<Integer>();

			if (nums.length == 0)
					return result;

			int firstMajor = 0, firstSum = 0, secondMajor = 0, secondSum = 0;
			
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == firstMajor)
					firstSum++;
				else if (nums[i] == secondMajor)
					secondSum++;
				else if (firstSum == 0) {
					firstMajor = nums[i];
					firstSum = 1;
				}
				else if (secondSum == 0) {
					secondMajor = nums[i];
					secondSum = 1;
				}
				else {
					firstSum--;
					secondSum--;
				}
			}

			firstSum = 0;
			secondSum = 0;
			for (int i = 0; i < nums.length; i++) {
				if (nums[i] == firstMajor)
					firstSum++;    
				else if (nums[i] == secondMajor)
					secondSum++;
			}
			if (firstSum > nums.length / 3)
				result.add(firstMajor);
			if (secondSum > nums.length / 3)
				result.add(secondMajor);
			return result;
   }
   
   /** 10. https://leetcode.com/problems/missing-ranges/  ***/
   
   /** 12. https://leetcode.com/problems/3sum-smaller/  ***/
   private List<List<Integer>> question11_threeSum(int[] nums) {
       Arrays.sort(nums);
	return null;
   }
   
   private void question15_rotate(int[][] matrix) {
       int n = matrix.length;

       for(int i=0; i<(n+1)/2; i++){
           for(int j=0; j<n/2; j++){
               int temp = matrix[n-j-1][i];
               matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
               matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
               matrix[j][n-i-1] = matrix[i][j];
               matrix[i][j] = temp;
           }
       }
       
       for(int i=0; i<n; i++){
           for(int j=0; j<n; j++){
               System.out.print(matrix[i][j] + ", ");
           }
           System.out.println();
       }
   }
}
