//69. x 的平方根
//实现 int sqrt(int x) 函数。
//
//计算并返回 x 的平方根，其中 x 是非负整数。
//
//由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
//
//示例 1:
//
//输入: 4
//输出: 2
//示例 2:
//
//输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842...,
//     由于返回类型是整数，小数部分将被舍去。
package Week_04;


public class MySqrt {

    //    自己编写，多了一次最后的判断
    public int mySqrt(int x) {
        long left = 0;
        long right = x;
        while (left <= right) {
//            这儿可以使用下面更好，如果是int相加也不会越界
//            left + (right - left)/2
            long sqr = (left + right) / 2;
//            用long存储，防止相乘越界
            if (sqr * sqr == x) {
                return (int) sqr;
            } else if (sqr * sqr < x) {
                left = sqr + 1;
            } else {
                right = sqr - 1;
            }
        }
        return left * left > x ? (int) left - 1 : (int) left;
    }

    //    老师方法
    public int mySqrt2(int x) {
        if (x == 0 || x == 1) return x;
        long left = 1;
        long right = x;
        long mid;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (mid * mid < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int) right;
    }

    public static void main(String[] args) {
        System.out.println(new MySqrt().mySqrt3(120));
        System.out.println(1e-6);
    }

    //   牛顿迭代法
//    牛顿迭代法的公式：一个数x的平方根等于curr = (curr + x/curr)/2 不断迭代到两次的相差小于1e-6即10的-6次方时
//    1e-6这个魔法数可以看做约等于0
    public int mySqrt3(int x) {
        if (x == 0 || x == 1) return x;
        double curr = 1;
        double pre;
        while (true) {
            pre = curr;
            curr = (curr + x / curr) / 2;
            if (Math.abs(curr - pre) < 1e-6) return (int) curr;
        }
    }

    //    牛顿迭代法变形，当从大于跨入小于时，则是求解值
    public int mySqrt4(int x) {
        long curr = x;
        while (curr*curr > x){
            curr = (curr + x/curr)/2;
        }
        return (int)curr;
    }


}
