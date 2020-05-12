//367. 有效的完全平方数
//给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
//
//说明：不要使用任何内置的库函数，如  sqrt。
//
//示例 1：
//
//输入：16
//输出：True
//示例 2：
//
//输入：14
//输出：False
package Week_04;


public class IsPerfectSquare {

    public boolean isPerfectSquare(int num) {
        long left = 0;
        long right = num;
        long mid;
        while ((left <= right)) {
            mid = left + (right - left) / 2;
            long multiplyMid = mid * mid;
            if (multiplyMid == num) {
                return true;
            } else if (multiplyMid < num){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return false;
    }

}
