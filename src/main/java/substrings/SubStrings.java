package substrings;


import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SubStrings {
    static Pattern p = Pattern.compile("\\d+\\[+?[a-z]+\\]");


    /**
     * Description from leetcode:
     * Given a string s, sort it in decreasing order based on the frequency of the characters.
     * The frequency of a character is the number of times it appears in the string. Return the sorted string.
     * If there are multiple answers, return any of them.
     *
     * @param s - given string
     * @return - results string
     */
    public static String frequencySort(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder b = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.putIfAbsent(c, 0);
            map.merge(c, map.get(c), (integer, integer2) -> integer + 1);
        }

        map
                .entrySet()
                .stream()
                .sorted((e1, e2) -> e1.getValue() > e2.getValue() ? -1 : 1)
                .forEach(e -> b.append(String.valueOf(e.getKey()).repeat(e.getValue())));

        return b.toString();
    }

    /**
    * Description from leetcode:
    * Given an encoded string, return its decoded string.
    * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is
    * being repeated exactly k times. Note that k is guaranteed to be a positive integer.
    * You may assume that the input string is always valid; there are no extra white spaces, square brackets are
    * well-formed, etc. Furthermore, you may assume that the original data does not contain any digits and
    * that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].
    * @param s - given string
    * @return - result string
    */
    public static String decodeString(String s) {
        Matcher m = p.matcher(s);

        String res = m.replaceAll(mr -> {
            int o = s.indexOf('[', mr.start());
            int r = Integer.parseInt(s.substring(mr.start(), o));
            String temp = s.substring(o + 1, mr.end() - 1);
            return temp.repeat(r);
        });

        if (s.equals(res)) return s;
        return decodeString(res);
    }


    /**
     * Description from leetcode:
     * Given an array of characters chars, compress it using the following algorithm:
     * Input: chars = ["a","a","b","b","c","c","c"]
     * Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
     * Compressed string s should be stored in the input character array chars.
     * Note that group lengths that are 10 or longer will be split into multiple characters in chars.
     *
     * @param chars - input char sequence
     * @return length of compressed sequence
     */
    public static int compress(char[] chars) {
        List<Character> list = new LinkedList<>();
        int i = 0;
        int count = 1;

        while (i < chars.length) {
            int j = 1;
            while ((i + j) < chars.length) {
                if (chars[i] == chars[i + j]) {
                    count++;
                    j++;
                } else {
                    break;
                }
            }

            if (count < 10) {
                list.add(chars[i]);
                if (count !=1) list.add(Character.forDigit(count % 10, 10));
            }

            if (count >= 10) {
                list.add(chars[i]);
                char[] temp = String.valueOf(count).toCharArray();
                for (char c: temp) {
                    list.add(c);
                }
            }
            i += count;
            count = 1;
        }

        i = 0;
        for (Character c : list) {
            chars[i] = c;
            i++;
        }
        return list.size();
    }


    public static void main(String[] args) {
        System.out.println(decodeString("2[2[ab]2[q]x]"));
    }
}
