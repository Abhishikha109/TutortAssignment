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

10. https://practice.geeksforgeeks.org/problems/missing-ranges-of-numbers1019/1

11.https://leetcode.com/problems/3sum/ 

12.https://leetcode.com/problems/3sum-smaller/ 

13.https://leetcode.com/problems/3sum-closest/ 

14.https://leetcode.com/problems/4sum/

15.https://leetcode.com/problems/rotate-image/

*/

public class ArrayAssignmentPart1 extends CommonMethods implements Assignments{

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
		System.out.println(question10_findMissing(new int[] {0}, 1));
		System.out.println(question11_threeSum(new int[] {-1,0,1,2,-1,-4}));
		System.out.println(question13_threeSumClosest(new int[] {-1,2,1,-4}, 1));
		System.out.println(question14_fourSum(new int[] {2,2,2,2,2}, 8));
		question15_rotate(new int[][] {{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}});
	}
	
   private int[] question1_twoSum(int[] nums, int target) 
    {
	   printQuestion("\n1. question1_twoSum.");
	   
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
	   
	   printQuestion("\n2. question2_twoSum.");
	   
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
	   
	   printQuestion("\n3. question3_merge.");
	   
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
	   
	   printQuestion("\n4. question4_pascalTriangle.");
	   
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
	   
	   printQuestion("\n5. question5_getRow.");
	   
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
	   
	   printQuestion("\n6. question6_maxProfit.");
	   
       int minPrice = Integer.MAX_VALUE;
       int maxProfitTotal = Integer.MIN_VALUE;

       for(int i=0; i<prices.length; i++){
           minPrice = minPrice > prices[i] ? prices[i] : minPrice;
           maxProfitTotal = (maxProfitTotal < prices[i] - minPrice) ? prices[i] - minPrice : maxProfitTotal;
       }

       return maxProfitTotal;
   }
   
   private int question7_maxProfit2(int[] prices) {
	   
	   printQuestion("\n7. question7_maxProfit2.");
	   
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
       
	   printQuestion("\n8. question8_majorityElement.");
	   
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
	   
	   printQuestion("\n9. question9_majorityElement.");
	   
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
   
   private String question10_findMissing(int[] arr, int n) {
	   
	   printQuestion("\n10. question10_findMissing.");
	   
	   Arrays.sort(arr);
       
       int left = 0;
       int right;
       
       StringBuilder stringBuilder = new StringBuilder();
       
       for(int i=0; i<n; i++){
           if(left == arr[i]){
               left++;
           }
           else {
               right = arr[i]-1;
               
               stringBuilder.append( (left == right) ? left + " " : left + "-"+ right + " ");
               
               left = arr[i]+1 ;
           }
       }
       
       String ans = stringBuilder.toString() == "" ? "-1" :  stringBuilder.toString();
       return ans;
   }
   
   private List<List<Integer>> question11_threeSum(int[] nums) {
	   
	   printQuestion("\n11. question11_threeSum.");
	   
       Arrays.sort(nums);
       int leftIndex, rightIndex, val;

       List<List<Integer>> allrows = new ArrayList<List<Integer>>();
       ArrayList<Integer> row;

       for(int i=0; i<=nums.length-3; i++){

           if(i!=0 && nums[i] == nums[i-1])
               continue;

           leftIndex = i+1; rightIndex = nums.length - 1;
           val = nums[i] * -1;

           while(leftIndex < rightIndex){
               if(nums[leftIndex] + nums[rightIndex] == val){
                   row = new ArrayList<Integer>();
                   row.add(nums[i]);
                   row.add(nums[leftIndex]);
                   row.add(nums[rightIndex]);
                   allrows.add(row);
                   
                   while(++leftIndex < rightIndex && nums[leftIndex] == nums[leftIndex-1]);
                   while(--rightIndex > leftIndex && nums[rightIndex] == nums[rightIndex+1]);
               }
               else if(nums[leftIndex] + nums[rightIndex] < val)
            	   leftIndex++;
               else
            	   rightIndex--;
           }
       }

       return allrows;
   }
   
   /** 12. https://leetcode.com/problems/3sum-smaller/  ***/
   
   private int question13_threeSumClosest(int[] nums, int target) {
	   
	   printQuestion("\n13. question13_threeSumClosest.");
	   
       int length = nums.length;
       Arrays.sort(nums);
       int diff = Integer.MAX_VALUE;
       int result = 0;
       int threeSum;

       for(int i=0; i<=length-3; i++){
           if(i!=0 && nums[i] == nums[i-1]){
               continue;
           }
           int j=i+1, k=length-1;

           while(j<k){
               threeSum = nums[i] + nums[j] + nums[k];
               if(threeSum == target){
                   if(diff > target - threeSum){
                       diff = target - threeSum;
                       result = threeSum;
                   }
                   j++; k--;
               }
               else if(threeSum < target){
                   if(diff > target - threeSum){
                       diff = target - threeSum;
                       result = threeSum;
                   }
                   j++;
               }
               else {
                    if(diff > threeSum - target){
                       diff = threeSum - target;
                       result = threeSum;
                   }
                   k--;
               }
           }
       }

       return result;
   }
   
   private List<List<Integer>> question14_fourSum(int[] nums, int target) {
	   
	   printQuestion("\n14. question14_fourSum.");
	   
       int length = nums.length;
       Arrays.sort(nums);

       List<List<Integer>> allrows = new ArrayList<List<Integer>>();
       ArrayList<Integer> row;

       for(int i=0; i<=length-4; i++){

           // eliminate the duplicates
           if(i!= 0 && nums[i] == nums[i-1]){
               continue;
           }
               
           for(int j=i+1; j<=length-3;j++){

               // eliminate the duplicates
               if(j!= i+1 && nums[j] == nums[j-1]){
                   continue;
               }

               int sum = (nums[i] + nums[j]); 

               // since nums[i] can be 10^9 hence addition of such two digits will leads to overflow
               long val = (long)target - (long)sum;

               int m = j+1, n = length-1;

               while(m<n){ // m<=n is wrong, because it will render duplicate result
                   if(nums[m] + nums[n] == val){
                       row = new ArrayList<Integer>();
                       row.add(nums[i]);
                       row.add(nums[j]);
                       row.add(nums[m]);
                       row.add(nums[n]);
                       allrows.add(row);
                       
                       // eliminate the duplicates
                       while(++m < n && nums[m] == nums[m-1]);
                       while(--n > m && nums[n] == nums[n+1]);
                   }
                   else if(nums[m] + nums[n] < val)
                       m++;
                   else
                       n--;
               }
           }
       }

       return allrows;
   }
   
   private void question15_rotate(int[][] matrix) {
	   
	   printQuestion("\n15. question15_rotate.");
	   
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
