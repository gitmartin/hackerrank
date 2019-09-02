// Reverse Shuffle Merge problem from hackerrank.
// Inspired by denis631's solution.

import java.util.*;

public class Reverse_Shuffle_Merge {

    public static void main(String[] args) {
        String s = "abab";
      //  s = "aeiouuoiea";
     //   s = "eggegg";
        s = "abcdefgabcdefg";
        s = "djjcddjggbiigjhfghehhbgdigjicafgjcehhfgifadihiajgciagicdahcbajjbhifjiaajigdgdfhdiijjgaiejgegbbiigida";

        String out = reverseShuffleMerge(s);
        System.out.println("-------\nANSWER: " + out);
    }

    static String reverseShuffleMerge(String s) {
        String reverse_s = "";
        for (int i = 0; i < s.length(); i++) {
            reverse_s = s.charAt(i) + reverse_s;
        }
        s = reverse_s;

        LinkedList<Character> res = new LinkedList<>();

        HashMap<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) +1);
        }
        System.out.println(count);
        HashMap<Character, Integer> word_hm = new HashMap<>();
        for (char c : count.keySet()) word_hm.put(c, count.get(c)/2);
        System.out.println(word_hm);

        HashMap<Character, Integer> used_chars = new HashMap<>();

        for (char c : s.toCharArray()) {
            int needed = word_hm.get(c) - used_chars.getOrDefault(c,0);
            if (needed > 0) {
                while (res.size() > 0 && res.getLast() > c &&
                        canpop(res, count, word_hm, used_chars)) {
                    char temp = res.removeLast();
                    used_chars.put(temp, used_chars.get(temp) -1);
                }

                used_chars.put(c, used_chars.getOrDefault(c, 0) + 1);
                res.add(c);
            }
            count.put(c, count.get(c) - 1);
        }
        String out = "";
        for (char c : res) out += c;
        return out;
    }

    private static boolean canpop(LinkedList<Character> res, HashMap<Character, Integer> count, HashMap<Character, Integer> word_hm, HashMap<Character, Integer> used_chars) {
        char prev = res.getLast();
        if (count.get(prev) >  word_hm.get(prev) - used_chars.get(prev))
            return true;
        return false;
    }

}