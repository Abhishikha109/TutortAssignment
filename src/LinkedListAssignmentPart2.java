import java.util.*;
/*
1. https://leetcode.com/problems/reorder-list/

 2. https://leetcode.com/problems/merge-two-sorted-lists/

3. https://leetcode.com/problems/linked-list-cycle/

 4. https://leetcode.com/problems/linked-list-cycle-ii/

5. https://leetcode.com/problems/next-greater-node-in-linked-list/

 6. https://leetcode.com/problems/swap-nodes-in-pairs/

 7. https://leetcode.com/problems/rotate-list/ //////

8. https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/

 9. https://leetcode.com/problems/next-greater-node-in-linked-list/

10. https://leetcode.com/problems/odd-even-linked-list/

11. https://leetcode.com/problems/add-two-polynomials-represented-as-linked-lists/

12. https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/

13. https://leetcode.com/problems/add-two-numbers-ii/

14. https://leetcode.com/problems/reverse-nodes-in-k-group/

 15. https://leetcode.com/problems/copy-list-with-random-pointer/

16. https://leetcode.com/problems/reverse-linked-list/

 17. https://leetcode.com/problems/intersection-of-two-linked-lists/
 */

 
public class LinkedListAssignmentPart2 extends CommonMethods implements Assignments{

	public class ListNode {
	      int val;
	      ListNode next;
	      ListNode() {}
	      ListNode(int val) { this.val = val; }
	      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	  };
	
	class Node {
	    int val;
	    Node next;
	    Node random;

	    public Node(int val) {
	        this.val = val;
	        this.next = null;
	        this.random = null;
	    }
	};
	
	class Polynode{
	    int coeff;
	    int pow;
	    Polynode next;
	    Polynode(int a,int b)
	    {
	        coeff=a;
	        pow=b;
	        next=null;
	    }
	};
	
	@Override
	public void allQuestion() {

	}
	
    public void reorderList_question1(ListNode head) {
        ListNode slow = head, fast = head.next;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode curr = slow.next, prev = null;

        while(curr != null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        slow.next = null;

        while(head != null && prev != null){
            ListNode temp1 = head.next;
            ListNode temp2 = prev.next;

            head.next = prev;
            prev.next = temp1;

            head = temp1;
            prev = temp2;
        }
    }
    
    public ListNode mergeTwoLists_question2(ListNode list1, ListNode list2) {
        
        ListNode dummyHead = new ListNode(0,null);
        ListNode p1 = list1, p2 = list2;
        ListNode current = dummyHead;
        
        while(p1 != null && p2 != null){
            if(p1.val < p2.val){
                current.next = p1;
                p1 = p1.next;
            }
            else{
                current.next = p2;
                p2 = p2.next;
            }
            current = current.next;
        }
        
        if(p2 != null)
            current.next = p2;
        if(p1 != null)
            current.next = p1;
        
        return dummyHead.next;
    }
    
    public boolean hasCycle_question3(ListNode head) {
        ListNode slow = head, fast = head;
        
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            
            if(slow == fast)
                return true;
        }
        return false;
    }
    
    public ListNode detectCycle_question4(ListNode head) {
        ListNode slow = head, fast = head;

        // 2 * slowPointer = fastPointer
        // 2 * (l1 + l2) = l1 + l2 + n*(number of cycles in loop)
        // l1 = n * number of cycles in loop - l2

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast){
                slow = head;

                while(slow != fast){
                    if(slow.next == fast.next){
                        System.out.println(fast.val);
                    }
                    slow = slow.next;
                    fast = fast.next;
                }

                return slow;
            }
        }
        return null;
    }
    
    public int[] nextLargerNodes_question5(ListNode head) {
    	
    	/* Not yet completed*/
        ListNode newNode = head;
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();
        List<Integer> result = new ArrayList<Integer>();

        while(newNode != null){
            while(!stack.isEmpty() && stack.peek() < newNode.val){
                stack.pop();
            }
            stack.push(newNode.val);
            newNode = newNode.next;
        }

        while(!stack.isEmpty()){
            stack2.push(stack.peek());
            stack.pop();
        }

        while(head != null){
            if(head.val < stack2.peek()){
                result.add(stack2.peek());
            }else if(head.val == stack2.peek() || head.val > stack2.peek()){
                result.add(0);
                stack2.pop();
            }
            head = head.next;
        }
        int[] finalResult = new int[result.size()];
        int i=0;
        for(Integer a : result){
            finalResult[i++] = a;
        }
        return finalResult;
    }
    
    public ListNode swapPairs_question6(ListNode head) {
        while(head == null || head.next == null){
            return head;
        }

        ListNode newNode = swapPairs_question6(head.next.next);
        ListNode newHead = head.next;
        newHead.next = head;
        head.next = newNode;

        return newHead;
    }
    
    public ListNode rotateRightquestion7(ListNode head, int k) {
        int totalNodes = 1;
        ListNode tail = head;
        
        while(tail != null && tail.next != null){
            tail = tail.next;
            ++totalNodes;
        }
         k = k%totalNodes;
        
        if(k==0)
            return head;
        
        tail.next = head;
        
        int stepsToNewHead = totalNodes-k;
        ListNode newTail = tail;
        
        while(stepsToNewHead-- > 0){
            newTail = newTail.next;
        }
        
       ListNode newHead = newTail.next;
        newTail.next = null;
        
        return newHead;
    }
    
    public ListNode deleteDuplicates_question8(ListNode head) {
        ListNode newNode = head;
        ListNode dummy = new ListNode(0, null);
        ListNode curr = dummy;

        while(newNode != null){
            int val = newNode.val;
            boolean flag = false;

            while(newNode.next != null && newNode.next.val == val){
                newNode = newNode.next;
                flag = true;
            }

            if(!flag){
                dummy.next = new ListNode(newNode.val, null);
                dummy = dummy.next;
            }

            if(newNode != null) 
                newNode = newNode.next;
        }

        return curr.next;
    }
    
    public int[] nextLargerNodes_question9(ListNode head) {
    	
    	/* Not yet completed*/
        ListNode newNode = head;
        Stack<Integer> stack = new Stack<Integer>();
        Stack<Integer> stack2 = new Stack<Integer>();
        List<Integer> result = new ArrayList<Integer>();

        while(newNode != null){
            while(!stack.isEmpty() && stack.peek() < newNode.val){
                stack.pop();
            }
            stack.push(newNode.val);
            newNode = newNode.next;
        }

        while(!stack.isEmpty()){
            stack2.push(stack.peek());
            stack.pop();
        }

        while(head != null){
            if(head.val < stack2.peek()){
                result.add(stack2.peek());
            }else if(head.val == stack2.peek() || head.val > stack2.peek()){
                result.add(0);
                stack2.pop();
            }
            head = head.next;
        }
        int[] finalResult = new int[result.size()];
        int i=0;
        for(Integer a : result){
            finalResult[i++] = a;
        }
        return finalResult;
    }
    
    public ListNode oddEvenList_question10(ListNode head) {
        if(head == null){
            return head;
        }
        
        ListNode evenHead = head.next;
        ListNode odd = head;
        ListNode even = evenHead;

        while(even != null && even.next != null){
            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }

        odd.next = evenHead;

        return head;
    }
    
    public Polynode addPolynomial(Polynode p1,Polynode p2)
    {
    	Polynode dummy = new Polynode(0, 0);
    	Polynode result = dummy;
        
        while(p1 != null && p2 != null){
            if(p1.pow == p2.pow){
                    dummy.next = new Polynode(p1.coeff + p2.coeff, p1.pow);
                    p1 = p1.next;
                    p2 = p2.next;
                }
            else if(p1.pow < p2.pow){
                    dummy.next = new Polynode(p2.coeff, p2.pow);
                    p2 = p2.next;
                }
            else {
                    dummy.next = new Polynode(p1.coeff, p1.pow);
                    p1 = p1.next;
                }
            dummy = dummy.next;
        }
        
        while(p1 != null){
            dummy.next = new Polynode(p1.coeff, p1.pow);
            p1 = p1.next;
        }
        
        while(p2 != null){
            dummy.next = new Polynode(p2.coeff, p2.pow);
            p2 = p2.next;
        }
        
        return result.next;
    }
    
    public ListNode deleteDuplicates_question12(ListNode head) {
        ListNode newNode = head;
        ListNode dummy = new ListNode(0, null);
        ListNode curr = dummy;

        while(newNode != null){
            int val = newNode.val;
            boolean flag = false;

            while(newNode.next != null && newNode.next.val == val){
                newNode = newNode.next;
                flag = true;
            }

            if(!flag){
                dummy.next = new ListNode(newNode.val, null);
                dummy = dummy.next;
            }

            if(newNode != null) 
                newNode = newNode.next;
        }

        return curr.next;
    }
    
    public ListNode reverse(ListNode head){
        if(head == null || head.next == null){
            return head;
        }

        ListNode curr = head, prev = null;

        while(curr != null){
            ListNode node = curr.next;
            curr.next = prev;
            prev = curr;
            curr = node;
        }

        return prev;
    }

    public ListNode addTwoNumbers_question13(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        ListNode dummy = new ListNode(0, null);
        ListNode result = dummy;

        int carry = 0, sum;

        while(l1 != null || l2 != null){
            sum  = carry;
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }

            dummy.next = new ListNode(sum%10, null);
            dummy = dummy.next;
            carry = sum/10;
        }

        return reverse(result.next);
    }
    
    public ListNode reverseKGroup_question14(ListNode head, int k) {
        int count = 1;
        ListNode temp = head;

        if(temp == null || temp.next == null){
            return temp;
        }

        while(temp != null && count <= k){
            temp = temp.next;
            count++;
        }

        ListNode remainingList;
        if(count > k){
            remainingList = reverseKGroup_question14(temp, k);
        }else{
            return head; // TO HANDLE THE SITUATION WHEN COUNT < K, HENCE WE DON'T NEED TO REVERSE HERE.
        }
        
        ListNode dummy = head;
        ListNode curr = head, prev = null;

        while(curr != temp){
            ListNode tp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tp;
        }

        dummy.next = remainingList;
        
        return prev; 
    }
    
    public Node copyRandomList_question15(Node head) {
        if(head == null){
            return null;
        }

        /*
        1. create a clone list
        2. store original list (key) and clone list(value) in map
        3. again iterate original list and assign clone list random pointer via map
            O(n) & O(1)
         */

        // Node temp = new Node(head.val);
        // Node clone = temp;
        // Node originalNode = head.next;

        // while(originalNode != null){
        //     temp.next = new Node(originalNode.val);
        //     temp = temp.next;
        //     originalNode = originalNode.next;
        // }

        // originalNode = head;
        // temp = clone;
        // Map<Node, Node> map = new HashMap<Node, Node>();

        // while(originalNode != null){
        //     map.put(originalNode, temp);
        //     originalNode = originalNode.next;
        //     temp = temp.next;
        // }

        // temp = clone;
        // originalNode = head;

        // while(temp != null){
        //     temp.random = map.get(originalNode.random);
        //     temp = temp.next;
        //     originalNode = originalNode.next;
        // }

        // return clone;

        
        // 1. create a clone list

        Node temp = new Node(head.val);
        Node clone = temp;
        Node originalNode = head.next;

        while(originalNode != null){
            temp.next = new Node(originalNode.val);
            temp = temp.next;
            originalNode = originalNode.next;
        }

        // 2. clone nodes adds in between original linked list

        originalNode = head;
        temp = clone;

        while(originalNode != null){
            Node next = originalNode.next;
            originalNode.next = temp;
            originalNode = next;

            next = temp.next;
            temp.next = originalNode;
            temp = next;
        }

        // // 3. random pointer copy

        originalNode = head;

        while(originalNode != null){
            if(originalNode.next != null){
                originalNode.next.random = 
                originalNode.random != null ? originalNode.random.next : originalNode.random;
            }

            originalNode = originalNode.next.next;
        }
        // // 4. revert changes done in step 2

        originalNode = head;
        temp = clone;

        while(originalNode != null){
            originalNode.next = temp.next;
            originalNode = originalNode.next;

            if(originalNode != null)
            temp.next = originalNode.next;
            temp = temp.next;
        }

        return clone;
    }
    
    private ListNode reversal(ListNode node){
        
        if(node == null || node.next == null){
            return node;
        }

        /*      RECURSIVE APPROACH      */
        // ListNode rev = reversal(node.next);
        // ListNode dummy = rev;
        // while(dummy.next != null){
        //     dummy = dummy.next;
        // }

        // dummy.next = node;
        // dummy.next.next = null;
        // return rev;

        ListNode newNode = reversal(node.next);
        ListNode headNext = node.next;
        headNext.next = node;
        node.next = null;

        return newNode;
    }
    public ListNode reverseList_question16(ListNode node) {

        return reversal(node);
        //             curr  temp
        //              |    |
        // prev = null  1 -> 2 -> 3 -> 4
        //              |
        //              head

        
        // if(node == null || node.next == null){
        //     return node;
        // }

        // ListNode dummy = new ListNode(-1, node);
        // ListNode abc = reversal(dummy.next);

        // while(node != null){
        //     System.out.println(node.val);
        //     node = node.next;
        // }
        // return abc;
        /*      RECURSIVE APPROACH      */
        // ListNode rev = reverseList(node.next);
        // ListNode dummy = rev;
        // while(dummy.next != null){
        //     dummy = dummy.next;
        // }

        // dummy.next = node;
        // dummy.next.next = null;
        // return rev;

        /*      ITERATIVE APPROACH      */
        // ListNode curr = node, prev = null;

        // while(curr != null){
        //     ListNode temp = curr.next;
        //     curr.next = prev;
        //     prev = curr;
        //     curr = temp;
        // }

        // while(node != null){
        //     System.out.println(node.val);
        //     node = node.next;
        // }
        // return prev;
    }
    
    public ListNode getIntersectionNode_question17(ListNode headA, ListNode headB) {
        ListNode temp1 = headA; 
        ListNode temp2 = headB;
        int countA = 0, countB = 0;

        while(temp1 != null){
            temp1 = temp1.next;
            countA++;
        }

        while(temp2 != null){
            temp2 = temp2.next;
            countB++;
        }

        int diff;
        temp1 = headA;             
        temp2 = headB;

        if(countA > countB){
            diff = countA - countB;
            while(temp1 != null && diff > 0){
                temp1 = temp1.next;
                diff--;
            }
        }
        else {
            diff = countB - countA;
            while(temp2 != null && diff > 0){
                temp2 = temp2.next;
                diff--;
            }
        }

        if(temp1 == temp2){
            return temp1;
        }
        while(temp1 != null && temp2!= null){
            if(temp1.next == temp2.next){
                return temp1.next;
            }

            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        return null;
    }
}
