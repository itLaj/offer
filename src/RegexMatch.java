import java.util.ArrayList;

//请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
// 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
public class RegexMatch {
    public static void main(String[] args) {
        System.out.println(match(new char[]{'a','a'}, new char[]{'.','*'}));
        ArrayList<Integer> linkedList = new ArrayList<>();
    }

    public static boolean match(char[] str, char[] pattern) {
        return isMatch(str, pattern, 0, 0);
    }

    private static boolean isMatch(char[] str, char[] pattern, int strIdx, int patIdx) {

        //模式到尾时判断字符串是否到尾;
        if (patIdx == pattern.length) return strIdx == str.length;

        //若当前模式存在下一个字符,看下一个字符是否是 '*'
        if (patIdx < pattern.length - 1 && pattern[patIdx + 1] == '*') {
            //当前字符匹配
            if (strIdx < str.length  && (str[strIdx] == pattern[patIdx] || pattern[patIdx] == '.')) {

                return isMatch(str, pattern, strIdx, patIdx + 2)//跳过模式当前字符及*，表示*前面的字符出现0词
                        || isMatch(str, pattern, strIdx + 1, patIdx); //跳过字符串当前字符，表示*前面字符出现一次
            } else {
                //当前字符不匹配，跳过模式当前字符以及*
                return isMatch(str, pattern, strIdx, patIdx + 2);
            }


        }
        //下一个不是“*”，当前匹配
        if (strIdx < str.length && (str[strIdx] == pattern[patIdx] || pattern[patIdx] == '.'))
            return isMatch(str, pattern, strIdx + 1, patIdx + 1);

        return false;

    }
}
