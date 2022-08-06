package substrings;


import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SubStrings {
    public static String s = "taaareexeraa";
    public static String s1 = "10[leetcode]";
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
    public static void main(String[] args) {
        System.out.println(decodeString(s1));
    }
}
