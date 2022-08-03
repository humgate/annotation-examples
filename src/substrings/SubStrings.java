package substrings;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public class SubStrings {
    public static String s = "taaareexeraa";

    /**
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
                .forEach(e -> b.append(String.valueOf(e.getKey()).repeat(Math.max(0, e.getValue()))));

        return b.toString();
    }

    public static void main(String[] args) {
        System.out.println(frequencySort(s));
    }
}
