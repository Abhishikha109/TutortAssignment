import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;
/*
 
1. https://leetcode.com/problems/move-zeroes/

2. https://leetcode.com/problems/contains-duplicate/

3. https://leetcode.com/problems/contains-duplicate-ii/

4. https://leetcode.com/problems/summary-ranges/

5. https://leetcode.com/problems/range-sum-query-immutable/

6. https://leetcode.com/problems/range-sum-query-2d-immutable/

7. https://leetcode.com/problems/remove-element/

8. https://leetcode.com/problems/intersection-of-two-arrays/

9. https://leetcode.com/problems/intersection-of-two-arrays-ii/

10. https://leetcode.com/problems/next-greater-element-i/

11. https://leetcode.com/problems/next-greater-element-ii/

12. https://leetcode.com/problems/next-greater-element-iii/

13. https://leetcode.com/problems/rank-teams-by-votes/

14. https://leetcode.com/problems/reduce-array-size-to-the-half/

 */

public class ArrayAssignmentPart2 extends CommonMethods implements Assignments{

	@Override
	public void allQuestion() {
		question1_moveZeroes(new int[] {0,1,0,3,12});
		String abc = "dab";
		System.out.println(question2_containsDuplicate(new int[] {1,1,1,3,3,4,3,2,4,2}));
		System.out.println(question3_containsNearbyDuplicate(new int[] {1,0,1,1}, 1));
		System.out.println(question4_summaryRanges(new int[] {0,2,3,4,6,8,9}));
		NumArray numArray = new NumArray(new int[] {-2, 0, 3, -5, 2, -1});
		System.out.println(numArray.question5_sumRange(2, 5));
		NumMatrix numMatrix = new NumMatrix(new int[][] {{3, 0, 1, 4, 2}, {5, 6, 3, 2, 1}, {1, 2, 0, 1, 5}, {4, 1, 0, 1, 7}, {1, 0, 3, 0, 5}});
		System.out.println(numMatrix.question6_sumRegion(1, 1, 2, 2));
		System.out.println(question7_removeElement(new int[] {3,2,2,3}, 3));
		System.out.println(Arrays.toString(question8_intersection(new int[] {4,9,5}, new int[] {9,4,9,8,4})));
		System.out.println(Arrays.toString(question9_intersectII(new int[] {1,2,2,1}, new int[] {2,2})));
		System.out.println(Arrays.toString(question10_nextGreaterElementI(new int[] {4,1,2}, new int[] {1,3,4,2})));
		System.out.println(Arrays.toString(question11_nextGreaterElementII(new int[] {1,2,3,4,3})));
		System.out.println(question12_nextGreaterElementIII(12222333));
		System.out.println(question13_rankTeams(new String[] {"ABC","ACB","ABC","ACB","ACB"}));
		System.out.println(question14_minSetSize(new int[] {3,3,3,3,5,5,5,2,2,7}));
	}
	
    private void question1_moveZeroes(int[] nums) {
    	
    	printQuestion("\n1. move zeroes at the end");
    	
        int index = 0;

        for(int i=0; i<nums.length; i++){
            if(nums[i] != 0){
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                index++;
            }
        }
        
        for(int i=0; i<nums.length; i++) {
        	System.out.print(i != nums.length-1 ? nums[i] + ", " : nums[i] + "\n");
        }
    }
    
    private boolean question2_containsDuplicate(int[] nums) {
    	
    	printQuestion("\n2. Contains Duplicate");
    	
        Arrays.sort(nums);

        for(int i=0; i<= nums.length-2; i++){
            if((nums[i]^nums[i+1]) == 0)
                return true;
        }

        return false;
    }
    
    private boolean question3_containsNearbyDuplicate(int[] nums, int k) {
    	
    	printQuestion("\n3. Contains Duplicate II");
    	
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<nums.length; i++){
            if(map.containsKey(nums[i])){
                if(i - map.get(nums[i]) <= k)
                    return true;
            }
            map.put(nums[i], i);
        }

        return false;
    }
    
    private List<String> question4_summaryRanges(int[] nums) {
    	
    	printQuestion("\n4. Summary Ranges");
    	
        List <String> output = new ArrayList<String>();

        if(nums.length == 0) return output;

        int rangeStartIndex = 0, rangeEndIndex = 0;

        for(int i=1; i<nums.length; i++){
            if(nums[i]-1 == nums[i-1]){
                rangeEndIndex = i;
            }else {
                if(rangeStartIndex == rangeEndIndex)
                 output.add(nums[rangeStartIndex] + "");
                 else {
                     output.add(nums[rangeStartIndex] + "->" + nums[rangeEndIndex]);
                 }
                 rangeStartIndex = i;
                 rangeEndIndex = i;
            }
        }

        if(rangeStartIndex == rangeEndIndex)
                output.add(nums[rangeStartIndex] + "");
            else 
                output.add( nums[rangeStartIndex] + "->" + nums[rangeEndIndex]);

        return output;
    }
    
    class NumArray {

        int[] nums;
        int[] leftSum;

        public NumArray(int[] nums) {
            this.nums = nums;

            leftSum = new int[nums.length];

            leftSum[0] = nums[0];

            for(int i=1; i<nums.length; i++){
                leftSum[i] = nums[i] + leftSum[i-1];
            }
        }
        
        public int question5_sumRange(int left, int right) {
        	
        	printQuestion("\n5. Sum Ranges");
        	
            int result = leftSum[right];

            if(left == right){
                if(left > 0){
                    result -= leftSum[left-1];
                }
            }
            else if(--left >= 0){
                result -= leftSum[left];
            }
            return result;
        }
    }
    
    class NumMatrix {
        int[][] matrix;
        int[][] sumMatrix;

        public NumMatrix(int[][] matrix) {
            this.matrix = matrix;
            sumMatrix = new int[matrix.length][matrix[0].length];

            sumMatrix[0][0] = matrix[0][0];

           for(int i=1; i<matrix[0].length; i++){
        	   sumMatrix[0][i] = sumMatrix[0][i-1] + matrix[0][i];
           }

           for(int i=1; i<matrix.length; i++){
        	   sumMatrix[i][0] = sumMatrix[i-1][0] + matrix[i][0];
           }

        
            for(int i=1; i<matrix.length; i++){
                for(int j=1; j<matrix[0].length; j++){
                	sumMatrix[i][j] = sumMatrix[i-1][j] + sumMatrix[i][j-1] - sumMatrix[i-1][j-1] + matrix[i][j];
                }
            }
        }
        
        public int question6_sumRegion(int row1, int col1, int row2, int col2) {
        	
        	printQuestion("\n6. Sum Region");
        	
            int sum = sumMatrix[row2][col2];
            

            if(row1 > 0){
                sum -= sumMatrix[row1 - 1][col2];
            }

             if(col1 > 0){
                sum -= sumMatrix[row2][col1-1];
            }

            if(row1 > 0 && col1 > 0){
                sum += sumMatrix[row1 - 1][col1-1];
            }

            return sum;
        }
    }
    
    private int question7_removeElement(int[] nums, int val) {
    	
    	printQuestion("\n7. Remove Element");
    	
        int currentIndex = 0;

        for(int i=0; i<nums.length; i++){
            if(nums[i] != val){
                nums[currentIndex++] = nums[i];
            }
        }
        return currentIndex;
    }
    
    private Set<Integer> setElements(int[] nums){
        Set<Integer> set = new HashSet<Integer>();

        for(int i=0; i<nums.length; i++){
            set.add(nums[i]);
        }

        return set;
    }

    private int[] intersectElements(int[] nums, Set<Integer> set){
        int count = 0;
        int[] result = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            if(set.contains(nums[i])){
                result[count++] = nums[i];
                set.remove(nums[i]);
            }
        }

        int[] finalResult = new int[count];

        for(int i=0; i<count; i++){
            finalResult[i] = result[i];
        }

        return finalResult;
    }

    public int[] question8_intersection(int[] nums1, int[] nums2) {
    	
    	printQuestion("\n8. intersection of two arrays");
    	
        Set<Integer> set = 
        (nums1.length >= nums2.length) ? setElements(nums2) : setElements(nums1);

        return 
        (nums1.length >= nums2.length) ?
         intersectElements(nums1, set) : 
         intersectElements(nums2, set);
    }
    
    private int[] intersectUtil(int[] nums1, int[] nums2){
        int nums1length = nums1.length;
        int nums2length = nums2.length;
        int i = 0, j = 0, k = 0;

        while(i < nums1length && j < nums2length){
            if(nums1[i] == nums2[j]){
                nums1[k++] = nums1[i];
                i++; j++;
            }
            else if(nums1[i] < nums2[j]){
                i++;
            }
            else {
                j++;
            }
        }

        return Arrays.copyOfRange(nums1, 0, k);
    }

    private int[] question9_intersectII(int[] nums1, int[] nums2) {
    	
    	printQuestion("\n9. intersection II of two arrays");
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        return nums1.length >= nums2.length ? intersectUtil(nums2, nums1) : intersectUtil(nums1, nums2);
    }
    
    private int[] question10_nextGreaterElementI(int[] nums1, int[] nums2) {
    	
    	printQuestion("\n10. Next Greater Element I");
    	
        HashMap<Integer, Integer> map = new HashMap<>();
        Stack<Integer> st = new Stack<>();

        for(int i=nums2.length-1; i>=0; i--){
            while(st.isEmpty() != true && st.peek() < nums2[i]){
                st.pop();
            }

            if(st.isEmpty() == true) 
                map.put(nums2[i], -1) ;
            else 
                map.put(nums2[i], st.peek());
            st.push(nums2[i]);
        }

        for(int i=0; i<nums1.length; i++){
            nums1[i] = map.get(nums1[i]);
        }

        return nums1; 
    }
    
    private int[] question11_nextGreaterElementII(int[] nums) {
    	
    	printQuestion("\n11. Next Greater Element II");
    	
    	/* since the question is asking to search in a circular array, I found that if we can add the array to the existing array 
    	 like nums[1,2] then newArray would be [1,2, 1,2].
    	 Hence doing this we can easily find the next greater element by using the above approach.
    	 
    	 But then I found that I do not need to create a new array which contains the twice element, instead we can do modulo of nums length, and
    	 reach the required element directly.
    	 
    	 1st approach
    	 
    	int numsLength = nums.length;
        int newNumsLength = 2*numsLength;
        int[] newNums = new int[newNumsLength];

        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<newNumsLength; i++){
            newNums[i] = nums[i%numsLength];
        }

        for(int i=newNumsLength-1; i>=0; i--){
            while(stack.isEmpty() != true && stack.peek() <= newNums[i]){
                stack.pop();
            }

            if(stack.isEmpty() == true){
                newNums[i] = -1;
            }
            else {
                newNums[i] = stack.peek();
            }
            stack.push(nums[i%numsLength]);
        }
        
        FINAL APPRAOCH
    	 */
    	
        int numsLength = nums.length;
        int[] newNums = new int[numsLength];

        Stack<Integer> stack = new Stack<>();

        for(int i=2*numsLength-1; i>=0; i--){
            while(stack.isEmpty() != true && stack.peek() <= nums[i%numsLength]){
                stack.pop();
            }

            newNums[i%numsLength] = (stack.isEmpty() == true) ? -1 : stack.peek();
            stack.push(nums[i%numsLength]);
        }

        return newNums;
    }
    
    private void swap(int[] nums, int firstIndex, int secondIndex){
        int temp = nums[firstIndex];
        nums[firstIndex] = nums[secondIndex];
        nums[secondIndex] = temp;
    }
	
    private void reverseArray(int[] nums, int start, int end){
        while(start < end){
            swap(nums, start++, end--);
        }
    }

    private void greaterElement(int[] nums) {
        int length = nums.length;
        int k, l=0;

        for(k=length-2; k>=0; k--){
            if(nums[k] < nums[k+1]){
                break;
            }
        }  

        if(k < 0){
            reverseArray(nums, 0, length-1);
            return;
        }
        else {
            for(l=length-1; l>k; l--){
                if(nums[k] < nums[l]){
                    break;
                }
            }
        }

        swap(nums, k, l);
        reverseArray(nums, k+1, length-1);
	}
    
    private int question12_nextGreaterElementIII(int n) {
    	
    	printQuestion("\n12. Next Greater Element III");
    	
        String temp = Integer.toString(n);
        int[] array = new int[temp.length()];

        for(int i=0; i<temp.length(); i++){
            array[i] = temp.charAt(i) - '0';
        }

        greaterElement(array);

        StringBuilder stringResult = new StringBuilder("");

        for(int i=0; i<array.length; i++){
            stringResult.append(Integer.toString(array[i]));
        }

        long integerResult = Long.parseLong(stringResult.toString());

        if(integerResult <= Integer.MAX_VALUE){
            if((int) integerResult > n){
                return (int) integerResult;
            }
        }

        return -1;
    }
    
    private String listToString(List<Character> candidates) {
        StringBuilder sb = new StringBuilder();
        for (Character ch : candidates) {
            sb.append(ch);
        }
        return sb.toString();
    }

    private List<Character> strToList(String str) {
        List<Character> list = new ArrayList<>();

        for(int i=0; i<str.length(); i++){
            list.add(str.charAt(i));
        }

        return list;
    }
    
    private String question13_rankTeams(String[] votes) {
    	
    	printQuestion("\n13. Rank teams by Votes");
    	
    	 int[][] frequency = new int[26][26];
        List<Character> allCandidates = strToList(votes[0]);

        for(String vote : votes){
            for(int i=0; i<vote.length(); i++){
                frequency[vote.charAt(i) - 'A'][i]++;
            }
        }

        allCandidates.sort((Comparator<? super Character>) new Comparator<Character>() {
        @Override
        public int compare(Character c1, Character c2) {
            System.out.println("c1: " + c1 + " c2: " + c2);
            for (int i = 0; i < 26; i++) {
                if (frequency[c1 - 'A'][i] != frequency[c2 - 'A'][i]) {
                    return frequency[c2 - 'A'][i] - frequency[c1 - 'A'][i];
                }
            }
            return c1 - c2;
        }});

        return listToString(allCandidates);
    }
    
    private int question14_minSetSize(int[] arr) {
        
    	printQuestion("\n14. Reduce Array Size to The Half");
    	
        int maxNumber = arr[0];
        int arrLengthHalf = arr.length/2;

        for(int i=0; i<arr.length; i++){
            maxNumber = Math.max(arr[i], maxNumber);
        }

        int[] frequency = new int[maxNumber+1];

        for(int i=0; i<arr.length; i++){
            frequency[arr[i]]++;
        }

        Arrays.sort(frequency);
        int count = 0;
        for(int i=frequency.length-1; i>=0; i--){
            if(arrLengthHalf <= 0){
                break;
            }
            arrLengthHalf -= frequency[i];
            count++;
        }

        return count;
    }

}
