// https://leetcode.com/problems/string-to-integer-atoi/
class StringToInteger {
    public int myAtoi(String s) {
        char c;
        int end = s.length()-1;
        int start = 0;
        boolean isLeading = true;
        for (int i = 0; i < s.length(); i++) {
            c = s.charAt(i);
            if (c == ' ' && isLeading) {
                start++;
            } else if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '.' || (i > start && c == '-') || (i > start && c == '+') || (c == ' ' && !isLeading)) {
                end = i-1;
                isLeading = false;
                break;
            } else { // digits or +/- in the beginning
                isLeading = false;
            }

        }
        
        long num = 0;
        if (start == s.length()) {
            return (int)num;
        }
        for (int i = end; i >= start; i--) {
            c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                num += (c - '0') * Math.pow(10, end - i);
            }
        }
        if (s.charAt(start) == '-') {
            num *= -1;
        }
        if (num > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (num < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) num;        
    }
}
