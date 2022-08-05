package substrings;


import java.util.HashMap;


public class SubStrings {
    public static String s = "taaareexeraa";
    public static String s1 = "3[a2[w]2[t]]";


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
    */
    public static String decodeString(String s) {
        //3[a2[w]]2[z]
        if (s.matches("^[a-z]*$")) return s;

        if (s.matches("^\\d+\\[.*")) {
            int o = s.indexOf("[");
            int nexto = s.substring(o + 1).indexOf("[");
            int nextc = s.substring(o + 1).indexOf("]");
            int repeat = Integer.parseInt(s.substring(0, o));

            String current = "";
            String next = "";
            String tail = "";
            if (nexto == -1) {
                current = s.substring(o + 1, o + 1 + nextc);
                return current.repeat(repeat);
            }

            if (nexto < nextc) {
                current = s.substring(o + 1, o + nexto);
                next = decodeString(s.substring(o + 1));

                return (current+next).repeat(repeat) + decodeString(s.substring(o + 1 + nextc + 1));
            }

            if (nexto >= nextc) {
                current = s.substring(o + 1, o + nextc + 1);
                next = decodeString(s.substring(o + 1 + nextc));

                if (s.charAt(o + 1 + nextc + 1) == ']') {
                    return current.repeat(repeat);// + decodeString(s.substring(o + 1 + nextc));
                } else {
                    return current.repeat(repeat) + decodeString(s.substring(o + 1 + nextc));
                }
            }
        }

        return decodeString(s.substring(1));
    }




    public static void main(String[] args) {
        String s2 = decodeString(s1);
        System.out.println(s2);
    }
}
