import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import substrings.SubStrings;

public class SubStringsTest {


    @Test
    public void decodeStringTest_One() {
        String str = SubStrings.decodeString("3[a]2[bc]");
        Assertions.assertEquals("aaabcbc", str);
    }

    @Test

    public void decodeStringTest_Two() {
        String str = SubStrings.decodeString("3[a2[c]]");
        Assertions.assertEquals("accaccacc", str);
    }

    @Test
    public void decodeStringTest_Three() {
        String str = SubStrings.decodeString("2[abc]3[cd]ef");
        Assertions.assertEquals("abcabccdcdcdef", str);
    }

    @Test
    public void decodeStringTest_Four() {
        String str = SubStrings.decodeString("abc3[cd]ef");
        Assertions.assertEquals("abccdcdcdef", str);
    }

    @Test
    public void decodeStringTest_Five() {
        String str = SubStrings.decodeString("3[2[cd]2[ef]]");
        Assertions.assertEquals("cdcdefefcdcdefefcdcdefef", str);
    }

    @Test
    public void decodeStringTest_Six() {
        String str = SubStrings.decodeString("3[2[cd]]");
        Assertions.assertEquals("cdcdcdcdcdcd", str);
    }

    @Test
    public void decodeStringTest_Seven() {
        String str = SubStrings.decodeString("aaa3[2[cd]]");
        Assertions.assertEquals("aaacdcdcdcdcdcd", str);
    }

    @Test
    public void decodeStringTest_Eight() {
        String str = SubStrings.decodeString("aaa3[2[cd]]2[f]eu");
        Assertions.assertEquals("aaacdcdcdcdcdcdffeu", str);
    }

    @Test
    public void decodeStringTest_Nine() {
        String str = SubStrings.decodeString("aaa");
        Assertions.assertEquals("aaa", str);
    }

    @Test
    public void decodeStringTest_Ten() {
        String str = SubStrings.decodeString("10[ab]");
        Assertions.assertEquals("abababababababababab", str);
    }


}
