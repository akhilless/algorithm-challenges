/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * https://leetcode.com/problems/add-two-numbers/
 */
class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        
        if (l2 == null) {
            return l1;
        }
        
        int carry = 0;
        int sum = 0;        
        ListNode head = null;
        ListNode currentNode = null;
        ListNode sumNode = null;        
        
        while (l1 != null && l2 != null) {
            sum = l1.val + l2.val + carry;
            carry = 0;
            
            if (sum >= 10) {
                sum -= 10;
                carry = 1;                
            }            
            
            sumNode = new ListNode(sum);
            
            if (head == null) {
                head = sumNode;
                currentNode = head;
            } else {
                currentNode.next = sumNode;
                currentNode = currentNode.next;
            }
            
            l1 = l1.next;
            l2 = l2.next;
        }
        
        if (l1 == null && l2 == null) {
            if (carry == 1) {
                currentNode.next = new ListNode(carry);
            }
        } else if (l1 != null) {
            while (l1 != null) {
                if (carry == 1) {
                    sum = l1.val+carry;
                    carry=0;
                } else {
                    sum = l1.val;
                }
                
                if (sum >= 10) {
                    sum -= 10;
                    carry = 1;
                }
                
                sumNode = new ListNode(sum);
                currentNode.next = sumNode;
                currentNode = currentNode.next;
                l1 = l1.next;
            }
            
            if (carry == 1) {
                currentNode.next = new  ListNode(carry);
            }
        } else {
            while (l2 != null) {
                if (carry == 1) {
                    sum = l2.val+carry;
                    carry=0;
                } else {
                    sum = l2.val;
                }
                
                if (sum >= 10) {
                    sum -= 10;
                    carry = 1;
                }
                
                sumNode = new ListNode(sum);
                currentNode.next = sumNode;
                currentNode = currentNode.next;
                l2 = l2.next;
            }
            
            if (carry == 1) {
                currentNode.next = new  ListNode(carry);
            }
        }
        
        return head;
    }
}
