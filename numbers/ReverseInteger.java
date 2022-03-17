/**
* Given a signed 32-bit integer x, return x with its digits reversed.
* If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
* https://leetcode.com/problems/reverse-integer/submissions/
**/

class ReverseInteger {
    public int reverse(int x) {
        int res = 0;
        int rem = 0;
        int power = -1;
        IntList digits = new IntList();
        boolean isNegative = false;
        
        if (x < 0) {
            isNegative = true;
            x *= -1; 
        }
        
        while (x > 0) {
            digits.push(x % 10);
            x /= 10; 
            power++;
        }        
        
        while (power >= 0) {
            if (res > Integer.MAX_VALUE / 10 || res < Integer.MIN_VALUE/10) {
                return 0;
            }
            res = res*10 + digits.pop();
            power--;
        }        
        
        return isNegative ? -1 * res : res;
    }
    
    class Node {
        Node next;
        int val;       
    }
    
    class IntList {
        Node head;
        Node tail;
        
        void push(int val) {
            if (head == null) {
                head = new Node();
                head.val = val;
                tail = head;
            } else {
                Node newTail = new Node();
                newTail.val = val;
                tail.next = newTail;
                tail = newTail;
            }
        }
        
        int pop() {
            if (head != null) {
                int res = head.val;
                head = head.next;
                return res;
            }
            
            throw new RuntimeException();
        }
    }
}
