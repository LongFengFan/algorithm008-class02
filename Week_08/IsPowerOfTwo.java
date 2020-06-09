//给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
//
//示例 1:
//
//输入: 1
//输出: true
//解释: 20 = 1
//示例 2:
//
//输入: 16
//输出: true
//解释: 24 = 16
//示例 3:
//
//输入: 218
//输出: false
//
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/power-of-two
//著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
package Week_08;


public class IsPowerOfTwo {
    //    循环遍历 时间复杂度log N
    public boolean isPowerOfTwo(int n) {
        int count = 0;
        while (n != 0) {
            if (n % 2 == 1) {
                count++;
                if (count > 1) return false;
            }
            n /= 2;
        }
        return count == 1;
    }

    //    如何获取二进制中最右边的 1：x & (-x)。
//如何将二进制中最右边的 1 设置为 0：x & (x - 1)。
    public boolean isPowerOfTwo2(int n) {
//        int防止越界,转为long类型
        if(n == 0) return false;
        long x = n;
        return (x & (-x)) == x;
    }

    public boolean isPowerOfTwo3(int n) {
        if(n == 0) return false;
        long x = n;
        return (x & (x - 1)) == 0;
    }



}
