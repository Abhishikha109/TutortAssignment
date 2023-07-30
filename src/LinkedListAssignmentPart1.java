import java.util.HashMap;
import java.util.Map;
/*
1.  https://leetcode.com/problems/remove-nth-node-from-end-of-list/

2. https://leetcode.com/problems/delete-n-nodes-after-m-nodes-of-a-linked-list/

3. https://leetcode.com/problems/remove-zero-sum-consecutive-nodes-from-linked-list/

4. https://leetcode.com/problems/middle-of-the-linked-list/

5. https://leetcode.com/problems/delete-node-in-a-linked-list/

6. https://leetcode.com/problems/remove-linked-list-elements/
 */

 
public class LinkedListAssignmentPart1 extends CommonMethods implements Assignments{

	public class ListNode {
	      int val;
	      ListNode next;
	      ListNode() {}
	      ListNode(int val) { this.val = val; }
	      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	  }
	
	@Override
	public void allQuestion() {

	}
	
    public ListNode removeNthFromEnd_question1(ListNode head, int n) {
        ListNode dummyHead = new ListNode(-1, head); // TO OVERCOME THE PROBLEM FOR DELETING THE HEAD NODE
        ListNode first = dummyHead, second = dummyHead;
        
        while(--n >= 0){
            first = first.next;
        }
        
        while(first.next != null){
            first = first.next;
            second = second.next;
        }
        
        ListNode del = second.next; 
        del = null; 

        second.next = second.next.next;
        return dummyHead.next;
    }
    
    public ListNode removeZeroSumSublists(ListNode head) {
        Map<Integer, ListNode> cumlative = new HashMap<Integer, ListNode>();

        ListNode temp = new ListNode(0, head);
        ListNode dummy = temp;
        int prefixSum = 0;

        while(temp != null){
            prefixSum += temp.val;
            if(cumlative.get(prefixSum) != null){
                ListNode existingNode = cumlative.get(prefixSum).next;
                int p = prefixSum + existingNode.val;

                while(p != prefixSum){
                    cumlative.remove(p);
                    existingNode = existingNode.next;
                    p += existingNode.val;
                }
                cumlative.get(prefixSum).next = temp.next;
            }else {
                cumlative.put(prefixSum, temp);
            }
            temp = temp.next;
        }

        return dummy.next;
    }
    
    public ListNode middleNode_question4(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
    
    public void deleteNode_question5(ListNode node) {
        ListNode temp = node.next;
        node.val = node.next.val;
        node.next = node.next.next;

        temp = null;
    }
    
    public ListNode removeElements_question6(ListNode head, int val) {
        ListNode first = new ListNode(-1, head);
        ListNode dummyNode = first;

        while(dummyNode.next != null){
            if(dummyNode.next.val == val){
                while(dummyNode.next != null && dummyNode.next.val == val){
                    dummyNode.next = dummyNode.next.next;
                }
            }else{
                dummyNode = dummyNode.next;
            }
        }

        return first.next;
    }
}
