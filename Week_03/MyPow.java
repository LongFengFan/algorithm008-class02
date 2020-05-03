//50. Pow(x, n)
//实现 pow(x, n) ，即计算 x 的 n 次幂函数。
//
//示例 1:
//
//输入: 2.00000, 10
//输出: 1024.00000
//示例 2:
//
//输入: 2.10000, 3
//输出: 9.26100
//示例 3:
//
//输入: 2.00000, -2
//输出: 0.25000
//解释: 2-2 = 1/22 = 1/4 = 0.25
//说明:
//
//-100.0 < x < 100.0
//n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
package Week_03;


public class MyPow {
    //    暴力递归，太长容易发生溢出
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n == -1) return 1 / x;
        if (n < 0) {
            return (1 / x) * myPow(x, n + 1);
        }
        return x * myPow(x, n - 1);
    }

    //    模拟相乘,超时
    public double myPow2(double x, int n) {
//        假如n 小于 0，看成1/x -n
        if (n == 0) return 1;
        if (n < 0) {
            double sum = 1 / x;
            for (int i = 0; i < -n; i++) {
                sum *= sum;
            }
            return sum;
        }
        double sum = x;
        for (int i = 0; i < n; i++) {
            sum *= x;
        }
        return sum;
    }

    public double myPow10(double x, int n) {
        return Math.pow(x, n);
    }

    //  分治递归
    public double myPow6(double x, int n) {
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        return fastPow(x, n);

    }

    private double fastPow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (n % 2 == 0) {
            return fastPow(x, n / 2) * fastPow(x, n / 2);
        } else {
            return fastPow(x, n / 2) * fastPow(x, n / 2) * x;
        }
    }

    public static void main(String[] args) {
//        System.out.println(new MyPow().myPow6(0.00001, 2147483647));
        System.out.println(Math.pow(2,31) - 1);
    }

}
