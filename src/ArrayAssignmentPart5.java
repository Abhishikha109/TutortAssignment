import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Arrays;
import java.util.HashMap;

/*
 1. https://leetcode.com/problems/distribute-candies/

2. https://leetcode.com/problems/unique-email-addresses/

3. https://leetcode.com/problems/rank-transform-of-an-array/

4. https://leetcode.com/problems/check-if-it-is-a-straight-line/

5. https://leetcode.com/problems/matrix-diagonal-sum/

6. https://leetcode.com/problems/height-checker/

7. https://leetcode.com/problems/baseball-game/

8. https://leetcode.com/problems/crawler-log-folder/
 */

public class ArrayAssignmentPart5 extends CommonMethods implements Assignments{

	@Override
	public void allQuestion() {
		System.out.println(question1_distributeCandies(new int[] {1,1,2,2,3,3}));
		System.out.println(question2_numUniqueEmails(new String[] {"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"}));
		System.out.println(Arrays.toString(question3_arrayRankTransform(new int[] {37,12,28,9,100,56,80,5,12})));
		System.out.println(question4_checkStraightLine(new int[][] {{1,2},{2,3},{3,4},{4,5},{5,6},{6,7}}));
		System.out.println(question5_diagonalSum(new int[][] {{1,2,3}, {4,5,6},{7,8,9}}));
		System.out.println(question6_heightChecker(new int[] {5,1,2,3,4}));
		System.out.println(question7_calPoints(new  String[] {"5","2","C","D","+"}));
		System.out.println(question8_minOperations(new String[] {"d1/","d2/","../","d21/","./"}));
		
	}

	private int question1_distributeCandies(int[] candyType) 
    {
	   printQuestion("\n1. question1_distributeCandies.");
	   
       int length = candyType.length / 2;

       Set<Integer> kinds = new HashSet<>();
       for (int candy : candyType) kinds.add(candy);
       return kinds.size() >= length ? length : kinds.size();
    }
   
   private int question2_numUniqueEmails(String[] emails) {
	   
	   printQuestion("\n2. question2_numUniqueEmails.");
	   
       Set<String> set = new HashSet<String>();

       for(String email : emails){
           int i;
           StringBuilder actualEmail = new StringBuilder();

           for(i=0; i<email.length(); i++){
               if(email.charAt(i) == '@' || email.charAt(i) == '+'){
                   while(email.charAt(i) != '@'){
                       i++;
                   }
                   break;
               }else if(email.charAt(i) == '.'){
                   continue;
               }else {
                   actualEmail.append(email.charAt(i));
               }
           }

           actualEmail.append(email.substring(i,email.length()));
           set.add(actualEmail.toString());
       }

       return set.size();
   }
   
   private int[] question3_arrayRankTransform(int[] arr) {
	   
	   printQuestion("\n3. question3_arrayRankTransform.");
	   
       int[] newArr = new int[arr.length];

       for(int i=0; i<arr.length; i++){
           newArr[i] = arr[i];
       }

       Arrays.sort(newArr);

       int rank = 1;
       HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
       
       for(int i=0; i<arr.length; i++){
           if(map.get(newArr[i]) == null){
                map.put(newArr[i], rank++);
           }
       }

       for(int i=0; i<arr.length; i++){
           arr[i] = map.get(arr[i]);
       }

       return arr;
   }
   
   private int getYDiff(int[] a, int[] b){
       return a[1] - b[1];
	}
	
	private int getXDiff(int[] a, int[] b){
	       return a[0] - b[0];
	}
   
   private boolean question4_checkStraightLine(int[][] coordinates) {
	   
	   printQuestion("\n4. question4_checkStraightLine.");
	   
       int deltaX = getXDiff(coordinates[1], coordinates[0]);
       int deltaY = getYDiff(coordinates[1], coordinates[0]);
       
       for(int i=2; i<coordinates.length; i++){
           if(
               deltaY * getXDiff(coordinates[i], coordinates[0]) != 
               deltaX * getYDiff(coordinates[i], coordinates[0])
           ){
               return false;
           }
       }

       return true;
   }
   
   private int question5_diagonalSum(int[][] mat) {
	   
	   printQuestion("\n5. question5_diagonalSum.");
	   
       int sum = 0, matLength = mat.length;

       for(int i=0; i<matLength; i++){
          sum += mat[i][i] + mat[matLength-i-1][i];
       }

       return (matLength%2 == 1)? sum - mat[matLength/2][matLength/2] : sum;
   }
   
   private int question6_heightChecker(int[] heights){
	   
	   printQuestion("\n6. question6_heightChecker.");

       int[] ele = new int[101];

       for(int i=0; i<heights.length; i++){
           ele[heights[i]]++;
       }

       int index = 0;
       int count = 0;

       for(int i=0; i<101; i++){
           if(ele[i] != 0){
               while(ele[i] > 0){
                   if(heights[index] != i){
                       count++;
                   }
                   ele[i]--;
                   index++;
               }
           }
       }

       return count;
   }
   
   private int question7_calPoints(String[] operations) {
	   
	   printQuestion("\n7. question7_calPoints.");
	   
       Stack<Integer> stack = new Stack<Integer>();

       for(int i=0; i<operations.length; i++){
            if(operations[i].charAt(0) == 'C'){
                if(!stack.isEmpty()){
                    stack.pop();
                }
            }else if(operations[i].charAt(0) == 'D'){
                if(!stack.isEmpty()){
                    stack.push(stack.peek() * 2);
                }
            }else if(operations[i].charAt(0) == '+'){
                int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;

                if(!stack.isEmpty()){
                    first = stack.peek();
                    stack.pop();
                }

                if(!stack.isEmpty()){
                    second = stack.peek();
                }

                if(first != Integer.MIN_VALUE){
                    stack.push(first);
                }

               int sum = 0;
                if(first != Integer.MIN_VALUE){
                    sum += first;
                }
                if(second != Integer.MIN_VALUE){
                    sum += second;
                }

                stack.push(sum);
            }
            else {
                stack.push(Integer.parseInt(operations[i]));
            }
       }

       int totalSum = 0;

       while(!stack.isEmpty()){
           totalSum += stack.peek();
           stack.pop();
       }

       return totalSum;
   }
   
   private int question8_minOperations(String[] logs) {

	   printQuestion("\n8. question8_minOperations.");
       int operations = 0;

       for(int i=0; i<logs.length; i++){
           if(logs[i].charAt(1) == '.'){
               if(operations != 0){
                   operations--;
               }
           }else if(logs[i].charAt(0) != '.'){
               operations++;
           }
       }

       return operations;
   }


}
