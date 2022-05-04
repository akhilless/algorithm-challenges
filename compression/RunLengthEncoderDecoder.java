/**
 * Run-length encoding is a fast and simple method of encoding strings. The basic idea is to represent repeated successive characters as a single count and character.
 * For example, the string "AAAABBBCCDAA" would be encoded as "4A3B2C1D2A".
 * Implement run-length encoding and decoding. You can assume the string to be encoded have no digits and consists solely of alphabetic characters.
 * You can assume the string to be decoded is valid.
 *
 * Time complexity of both operations: O(n) where n is the length of the input string for encoding and length of the resulting string for decoding.
 * Space complexity of both operations: O(n) where n is the length of the resulting string 
 *
 */

public class RunLengthEncoderDecoder {     
     private static String encode(String text) {
         if (text == null || text.isEmpty()) {
            return "";    
         }
         
         int count = 1;
         char last = text.charAt(0);
         StringBuilder sb = new StringBuilder();
         char cur = last;
         
         for (int i = 1; i < text.length(); i++) {
             cur = text.charAt(i);
             
             if (cur == last) {
                 count++;
             } else {
                 sb.append(count).append(last);
                 count = 1;
                 last = cur;
             }
         }
         
         sb.append(count).append(last);
         return sb.toString();
     }
     
     //according to the requirements, we assume the encoded string is valid, thus we do not do any validations.
     private static String decode(String text) {
         int count = 0;
         char c = '\0';
         StringBuilder sb = new StringBuilder();
         
         for (int i = 0; i < text.length(); i += 2) {
             count = Character.getNumericValue(text.charAt(i));
             c = text.charAt(i+1);
             
             for (int j = 0; j < count; j++) {
                sb.append(c);
             }
         }
         
         return sb.toString();
     }
}
