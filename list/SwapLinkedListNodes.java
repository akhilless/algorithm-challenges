/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 * 
 * We traverse the list keeping links to 2 nodes to be swapped (variables first and sec), last processed node so far variable called (tail) and next node after the
 * two to be swapped (variable temp). After swapping nodes we point tail to the temp node and update the tail to the last  processed node so far. Then we move
 * first and sec pointers forward. So, essentially we are using sliding window technique. If the length of the linked list is an odd number, then when
 * stop processing the list further after processing the two nodes before the last node.
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
class SwapLinkedListNodes {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode first = head;
        ListNode sec = head.next;
        ListNode tmp = head;
        ListNode tail = sec;
        head = sec;
        
        do {
            tmp = sec.next;
            sec.next = first;
            first.next = tmp;
            
            if(tail != sec) {
                tail.next = sec;
            }
            
            tail = first;
            
            first = tmp;            
            sec = tmp != null ? tmp.next : null;

        } while (first != null && sec != null);     
        
        return head;
        
    }
  
  static class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
