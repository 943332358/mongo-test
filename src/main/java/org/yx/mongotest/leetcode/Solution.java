package org.yx.mongotest.leetcode;

import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution {


    public static void main(String[] args) {

        System.out.println(oneEditAway("pales", "pal"));
    }


    /**
     * <pre>
     *    字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
     *
     *  
     *
     * 示例 1:
     *
     * 输入:
     * first = "pale"
     * second = "ple"
     * 输出: True
     *  
     *
     * 示例 2:
     *
     * 输入:
     * first = "pales"
     * second = "pal"
     * 输出: False
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/one-away-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * </pre>
     */
    public static boolean oneEditAway(String first, String second) {
        int len1 = first.length();
        int len2 = second.length();
        if (Math.abs(len1 - len2) > 1) {
            return false;
        }
        if (len2 > len1) {
            return oneEditAway(second, first);
        }

        char[] f = first.toCharArray();
        char[] s = second.toCharArray();

        int j = 0;
        for (int i = 0; i < s.length; i++) {
            if (f[i] != s[i]) {
                j++;
            }

        }


        return j <= 1;

    }

    /**
     * <pre>
     *     给定一个字符串，编写一个函数判定其是否为某个回文串的排列之一。
     *
     * 回文串是指正反两个方向都一样的单词或短语。排列是指字母的重新排列。
     *
     * 回文串不一定是字典当中的单词。
     *
     *
     * 示例1：
     *
     * 输入："tactcoa"
     * 输出：true（排列有"tacocat"、"atcocta"，等等）
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindrome-permutation-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * </pre>
     */
    public static boolean canPermutePalindrome(String s) {
        Set<Character> chars = Sets.newHashSet();

        for (char c : s.toCharArray()) {
            if (!chars.add(c)) {
                chars.remove(c);
            }

        }
        return chars.size() <= 1;
    }

    /**
     * <pre>
     *     URL化。编写一种方法，将字符串中的空格全部替换为%20。假定该字符串尾部有足够的空间存放新增字符，并且知道字符串的“真实”长度。（注：用Java实现的话，请使用字符数组实现，以便直接在数组上操作。）
     *
     * 示例 1：
     *
     * 输入："Mr John Smith    ", 13
     * 输出："Mr%20John%20Smith"
     * 示例 2：
     *
     * 输入："               ", 5
     * 输出："%20%20%20%20%20"
     *
     * 提示：
     *
     * 字符串长度在 [0, 500000] 范围内。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/string-to-url-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * </pre>
     */
    public static String replaceSpaces(String s, int length) {
        return Arrays.stream(s.substring(0, length).split("")).map(m -> " ".equals(m) ? "%20" : m)
                .collect(Collectors.joining());

    }

    /**
     * <pre>
     *     给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
     *
     * 示例 1：
     *
     * 输入: s1 = "abc", s2 = "bca"
     * 输出: true
     * 示例 2：
     *
     * 输入: s1 = "abc", s2 = "bad"
     * 输出: false
     * 说明：
     *
     * 0 <= len(s1) <= 100
     * 0 <= len(s2) <= 100
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/check-permutation-lcci
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * </pre>
     */
    public static boolean checkPermutation(String s1, String s2) {
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        return new String(chars1).equals(new String(chars2));
    }

    /**
     * 判断是否存在重复
     */
    public static boolean isUnique(String astr) {
        String[] strings = astr.split("");
        for (String s : strings) {
            int a = 0;
            for (String string : strings) {
                if (Objects.equals(s, string)) {
                    a++;
                }
                if (a > 1) {
                    return false;
                }
            }

        }
        return true;
    }
}
