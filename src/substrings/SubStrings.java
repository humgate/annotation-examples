package substrings;


import java.util.HashMap;


public class SubStrings {
    public static String s = "taaareexeraa";
    public static String s1 = "3[a2[x]2[b]]";


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
        //3[a2[x]2[s]]
        if (s.isEmpty()) return "";

//        if (s.matches("^\\d+\\[[a-z]+\\]{2,}.*")) {
//            int i1 = s.indexOf("[");
//            int i2 = s.indexOf("]");
//            int repeat = Integer.parseInt(s.substring(0, i1));
//            return s.substring(i1 + 1, i2).repeat(repeat);
//        }


        if (s.matches("^\\d+\\[[a-z]+\\].*")) {
            int i1 = s.indexOf("[");
            int i2 = s.indexOf("]");
            int repeat = Integer.parseInt(s.substring(0, i1));
            return s.substring(i1 + 1, i2).repeat(repeat) + decodeString(s.substring(1));
        }

        if (s.matches("^\\d+\\[[a-z]+.+")) {
            String stemp = decodeString(s.substring(1));
            int i1 = s.indexOf("[");
            int i2 = s.substring(i1 + 1).indexOf("[");
            int i3 = s.indexOf("]");
            int repeat = Integer.parseInt(s.substring(0, i1));
            String stemp2 = s.replace(s.substring(i1 + 2, i3 + 1), stemp);
            i3 = stemp2.indexOf("]");
            String stemp3 = stemp2.substring(i1 + 1, i3);

            return stemp3.repeat(repeat);
        }

           return decodeString(s.substring(1));
    }




    public static void main(String[] args) {
        String s2 = decodeString(s1);
        System.out.println(s2);
    }
}
