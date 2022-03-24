/*
 * Given the head of a linked list, return the list after sorting it in ascending order.
 * https://leetcode.com/problems/sort-list/
 *
 * We add elements of the given linked list into a binary heap, then pop minimum elements from the heap and add it to the result linked list till binary heap is empty.
 * Traversal of a linked list has complexity O(n) and adding or popping an element to and from the binary heap has complexity O(log n), so the solution has time
 * complexity of O(n log n). Space complexity of the solutio is O(n) since we need an array of size n+1 to represent a binary heap.
 */
class SortLinkedList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) { //list contains 0 or 1 element
            return head;
        }
        
        BinaryHeap heap = new BinaryHeap(5*10000);
        
        while (head != null) {
            heap.add(head.val);
            head = head.next;
        }
        
        ListNode resultHead = new ListNode(heap.min());
        ListNode resultTail = resultHead;
        
        while (!heap.isEmpty()) {
            resultTail.next = new ListNode(heap.min());
            resultTail = resultTail.next;
        }
        
        return resultHead;
    }
    
    /*
     * Bimary Min-Heap which stores elements in non-decreasing order (any parent is less than or equals to each of its children and any given child is
     * greater than or equals to its parent) and quarantees O(log n) complexity for adding an element and popping the minimum element.
     * It uses an 1-indexed array to represent the binary heap. Its 1-indexed to make calculations of positions of the parent or children of a given element
     * easier. Parent for a given heap[i] element is at heap[i/2]. Children of a given heap[i] element is at heap[2*i] and heap[2*i+1].
     */
    static class BinaryHeap {
        private int[] heap;
        //tail of the heap
        private int lastIndex = 0;
        
        public BinaryHeap(int length) {
            heap = new int[length+1];
        }
        
        public void add(int num) {
            if (lastIndex >= heap.length-1) {
                return;
            }
            
            lastIndex++;
            heap[lastIndex] = num;
            swim();
        }
        
        public int min() {
            if (isEmpty()) {
                return Integer.MAX_VALUE;
            }            
            
            swap(lastIndex, 1);
            int min = heap[lastIndex];
            lastIndex--;
            sink(1);
            return min;
        }
        
        public boolean isEmpty() {
            return lastIndex < 1;
        }
        
        private void swim() {
            int i = lastIndex;
            while (i > 1 && heap[i] < heap[i/2]) {
                swap(i/2, i);
                i /= 2;
            }
        }
        
        private void sink(int itemIndex) {
            if (isEmpty()) {
                return;
            }
            
            int i = itemIndex;
            int minChildIndex = 0;
            while ((2*i <= lastIndex && heap[2*i] < heap[i]) || (2*i+1 <= lastIndex && heap[2*i+1] < heap[i])) {
                minChildIndex = (2*i+1 <= lastIndex && heap[2*i+1]<heap[2*i]) ? 2*i+1 : 2*i;
                swap(minChildIndex, i);
                i = minChildIndex;
            }
        }
        
        private void swap(int itemIndex, int subIndex) {
            if (itemIndex == subIndex) {
                return;
            }
            
            int tmp = heap[itemIndex];
            heap[itemIndex] = heap[subIndex];
            heap[subIndex] = tmp;
        }
    }
    
    //Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
