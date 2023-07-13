import java.util.Arrays;

public class WarmUpAssignmentPart1 extends CommonMethods implements Assignments{
	
	public void allQuestion() {
		
		System.out.println(question1(new int[] {1,2,3,4}));
		System.out.println(question2(new int[] {10, 20, 30, 40, 50}, 2));
		System.out.println(question3(new int[] {1, 2, 4, 5, 8, 1}, 9));
		question4(new int[] {1,2,3,4,5});
		question5(new int[] {15, 2, 45, 12, 7});
		System.out.println(question6(new int[] {1, 2, 3, 2, 1}));
		System.out.println(question7(new int[] {7, 8, 3, 4, 2, 9, 5}));
		question8(new int[] {7, -2, 3, 4, 9, -1});
		System.out.println(question9(5));
		System.out.println(question10(853));
		System.out.println(bonusQuestion(new int[] {1, 5, 3, 2}));
	}
	
	private int question1(int[] arr1){
		printQuestion("\n1. Print the sum of all of the integers.");
		
		int sum = 0;
		for(int i=0; i<arr1.length; i++) {
			sum += arr1[i];
		}
		
		return sum ;
	}

	private int question2(int[] arr2, int searchIndex) {
		printQuestion("\n2. Print the element present at index key in the array.");
		
		return arr2[searchIndex];
	}
	
	private int question3(int[] arr3, int x) {
		printQuestion("\n3. Sorted array. Find number of elements which are less than or equal to given element X.");
		
		int count = 0;
		for(int i=0; i<arr3.length; i++) {
			if(arr3[i]<= x)
				count++;
		}
		
		return count;
	}
	
	private void question4(int[] arr4) {
		printQuestion("\n4. Print elements of A in alternate order (starting from index 0).");
		
		for(int i=0; i<arr4.length; i=i+2) {
			System.out.print(arr4[i] + ", ");
		}
		System.out.println();
	}
	
	private void question5(int[] arr5) {
		printQuestion("\n5. Find the elements whose value is equal to that of its index value ( Consider 1-based indexing ).");
		
		for(int i=0; i<arr5.length; i++) {
			if(i+1 == arr5[i]) {
				System.out.println(arr5[i] + " exists here\n");
			}
		}
	}
	
	private String question6(int[] arr6) {
		printQuestion("\n"
				+ "6. An array is said to be perfect if it's reverse array matches the original array.");
		
		int i=0, j=arr6.length-1;
		boolean flag = true;
		
		while(i<j) {
			if(arr6[i] == arr6[j]) {
				i++; j--;
			}
			else {
				flag = false;
				break;
			}
		}
		
		return flag ? "PERFECT\n" : "NOT PERFECT\n";
	}
	
	private int question7(int[] arr7) {
		printQuestion("\n7. Given an array of length N, at each step it is reduced by 1 element. In the first step the maximum element would be removed, while in the second step minimum element of the remaining array would be removed, in the third step again the maximum and so on. Continue this till the array contains only 1 element. And find the final element remaining in the array.");
		
		
		Arrays.sort(arr7);
		return arr7[arr7.length/2];
	}
	
	private void question8(int[] arr8) {
		
		printQuestion("\n8. Given an array of N distinct elements, the task is to find all elements in array except two greatest elements in sorted order.");
		
		for(int k=0; k<arr8.length-2; k++) {
			System.out.print(arr8[k] + ", ");
		}
		System.out.println();
	}
	
	private int question9(int n) {
		printQuestion("\n9. Write a program to find the sum of the given series 1+2+3+ . . . . . .(N terms)");

		int ans = (n%2 == 0) ? (n/2)*(n) : ((n+1) / 2) * n;
		return ans;
	}
	
	private String question10(int N) {
		printQuestion("\n10. "
				+ "Fascinating Number: When a number(should contain 3 digits or more) is multiplied by 2 and 3 ,and when both these products are concatenated with the original number, then it results in all digits from 1 to 9 present exactly once.");

		int multiBy2 = N*2;
		int multiBy3 = N*3;
		StringBuilder concatedString = new StringBuilder(Integer.toString(N)).append(Integer.toString(multiBy2)).append(Integer.toString(multiBy3));
		String result = concatedString.toString();
		
		int[] hash = new int[10];
		boolean flag1 = true;
		
		if(concatedString.length() < 9 || concatedString.length() > 9) {
			flag1 = false;
		}
		else {
			for(int k=0; k<result.length(); k++) {
				int num = result.charAt(k) - '0';
				hash[num]++;
			}
			
			for(int k=1; k<=9; k++) {
				if(hash[k]==0 || hash[k] > 1) {
					flag1 = false;
					break;
				}
			}
		}
		
		return (flag1 ? "Fascinating\n": "Not Fascinating\n");
	}
	
	private int bonusQuestion(int[] arr11) {
		printQuestion("\nBonus Question\n"
				+ "An array is balanced if the sum of the left half of the array elements is equal to the sum of right half.");
		
		int arrLength = arr11.length;
		int sumLeft = 0, sumRight = 0;
		
		for(int k=0; k<arrLength/2; k++) {
			sumLeft += arr11[k];
			sumRight += arr11[arrLength - k -1];
		}
		
		return sumLeft > sumRight ? sumLeft - sumRight : sumRight - sumLeft;
	}
	
}
