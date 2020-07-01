//20. 有效的括号
//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
//
//有效字符串需满足：
//
//左括号必须用相同类型的右括号闭合。
//左括号必须以正确的顺序闭合。
//注意空字符串可被认为是有效字符串。
//
//示例 1:
//
//输入: "()"
//输出: true
//示例 2:
//
//输入: "()[]{}"
//输出: true
//示例 3:
//
//输入: "(]"
//输出: false
//示例 4:
//
//输入: "([)]"
//输出: false
//示例 5:
//
//输入: "{[]}"
//输出: true
package Week_09;


import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class IsValid {
    public boolean isValid(String s) {
        HashSet<Character> set = new HashSet<>();
        set.add('(');
        set.add('{');
        set.add('[');
        LinkedList<Character> list = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(set.contains(c)){
                list.addFirst(c);
            }else {
                if(list.isEmpty()) return false;
                Character poll = list.poll();
                if(c == ']' && poll != '['){
                    return false;
                }
                if(c == '}' && poll != '{'){
                    return false;
                }
                if(c == ')' && poll != '('){
                    return false;
                }
            }
        }
        return list.isEmpty();
    }

    public static void main(String[] args) {
        new IsValid().isValid("([)]");
    }
}
