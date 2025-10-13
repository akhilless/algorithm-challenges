// https://leetcode.com/problems/rotate-string/description/
class RotateString {
    public boolean rotateString(String s, String goal) { 
        if (s.length() != goal.length()) {
            return false;
        }       
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == goal.charAt(0) &&
                goal.equals(s.substring(i, s.length()) + s.substring(0, i))) {
                return true;
            }
        }
        return false;        
    }
}
