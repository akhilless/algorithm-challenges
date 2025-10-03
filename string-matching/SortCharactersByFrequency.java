// https://leetcode.com/problems/sort-characters-by-frequency/
class SortCharactersByFrequency {
    public String frequencySort(String s) {
        int[] small = new int[26];
        int[] capital = new int[26];
        int[] digits = new int[10];

        for (char c : s.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                capital[c - 'A']++;
            } else if (c >= 'a' && c <= 'z') {
                small[c - 'a']++;
            } else {
                digits[c - '0']++;
            }
        }

        PriorityQueue<Character> sortedChars = new PriorityQueue<>(Comparator.<Character>comparingInt(c -> compareLetters(c, capital, small, digits)).reversed());
        for (int i = 0; i < small.length; i++) {
            if (small[i] != 0) {
                sortedChars.add((char)('a' + i));
            }
            if (capital[i] != 0) {
                sortedChars.add((char)('A' + i));
            }
            if (i < 10 && digits[i] != 0) {
                sortedChars.add((char)('0' + i));
            }            
        }
        
        char c = '\0';
        StringBuilder sb = new StringBuilder();
        int count = -1;

        while (sortedChars.peek() != null) {
            c = sortedChars.poll();
            if (c >= 'A' && c <= 'Z') {
                count = capital[c - 'A'];
            } else if (c >= 'a' && c <= 'z') {
                count = small[c - 'a'];
            } else {
                count = digits[c - '0'];
            }
            for (int i = 0; i < count; i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static int compareLetters(char c, int[] capital, int[] small, int[] digits) {
        if (c >= 'A' && c <= 'Z') {
            return capital[c - 'A'];
        } else if (c >= 'a' && c <= 'z') {
            return small[c - 'a'];
        } else {
            return digits[c - '0'];
        }
    }
}
