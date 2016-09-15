package com.test.throwstest;

/**
 *  http://blog.csdn.net/naruto_ahu/article/details/8805808
 *  Java中的Infinity和NaN
 *  标签： InfinityNaN
 *  2013-04-15 21:10 10809人阅读 评论(1) 收藏 举报
 *  版权声明：本文为博主原创文章，未经博主允许不得转载。
 *  目录(?)[+]
 *  1、i == i + 1
 *  一个数字永远不会等于它自己加1？Java 强制要求使用IEEE 754 浮点数算术运算[IEEE 754]，它可以让你用一个double 或float来表示无穷大。正如我们在学校里面学到的，无穷大加1还是无穷大。
 *  你可以用任何被计算为无穷大的浮点算术表达式来初始化i，例如：
 *  double i = 1.0 / 0.0;
 *  不过，你最好是能够利用标准类库为你提供的常量：
 *  double i = Double.POSITIVE_INFINITY;
 *  事实上，你不必将i 初始化为无穷大以确保循环永远执行。任何足够大的浮点数都可以实现这一目的，例如：
 *  double i = 1.0e40;
 *  2、i != i
 *  一个数字总是等于它自己? IEEE 754 浮点算术保留了一个特殊的值用来表示一个不是数字的数量[IEEE 754]。这个值就是NaN（“不是一个数字（Not a Number）”的缩写），对于所有没有良好的数字定义的浮点计算，例如0.0/0.0，其值都是它。规范中描述道，NaN 不等于任何浮点数值，包括它自身在内[JLS ]。
 *  你可以用任何计算结果为NaN 的浮点算术表达式来初始化i，例如：
 *  double i = 0.0 / 0.0;
 *  同样，为了表达清晰，你可以使用标准类库提供的常量：
 *  double i = Double.NaN;
 *  NaN 还有其他的惊人之处。任何浮点操作，只要它的一个或多个操作数为NaN，那么其结果为NaN。这条规则是非常合理的，但是它却具有奇怪的结果。例如，下面的程序将打印false：
 *  class Test {
 *  public static void main(String[] args) {
 *  double i = 0.0 / 0.0;
 *  System.out.println(i - i == 0);
 *  }
 *  }
 *  总之，float 和double 类型都有一个特殊的NaN 值，用来表示不是数字的数量。
 *  3、NaN与任何数比较均返回false
 *  if( (0 > c) || (0 == c) || (0 < c)){
 *  System.out.println("NaN compared with 0 is not always false.");
 *  }else{
 *  System.out.println("NaN compared with 0 is always false!");
 *  }
 *  注：
 *  Double.NaN == Double.NaN，结果是false。但是，
 *  Double a = new Double(Double.NaN);
 *  Double b = new Double(Double.NaN);]
 *  a.equals(b);  //true
 *  4、Float.compare()
 *  而当我们使用Float.compare()这个方法来比较两个NaN时，却会得到相等的结果。可以用下面的代码验证：
 *  float nan=Float.NaN;
 *  float anotherNan=Float.NaN;
 *  System.out.println(Float.compare(nan,anotherNan));
 *
 *  compare()方法如果返回0，就说明两个数相等，返回-1，就说明第一个比第二个小，返回1则正好相反。
 *  上面语句的返回结果是0。
 *  一般来说，基本类型的compare()方法与直接使用==的效果“应该”是一样的，但在NaN这个问题上不一致，是利是弊，取决于使用的人作何期望。当程序的语义要求两个NaN不应该被认为相等时（例如用NaN来代表两个无穷大，学过高等数学的朋友们都记得，两个无穷看上去符号是一样，但不应该认为是相等的两样东西），就使用==判断；如果NaN被看得无足轻重（毕竟，我只关心数字，两个不是数字的东西就划归同一类好了嘛）就使用Float.compare()。
 *
 *  另一个在==和compare()方法上表现不一致的浮点数就是正0和负0（当然这也是计算机表示有符号数字的老大难问题），我们（万能的）人类当然知道0.0f和-0.0f应该是相等的数字，但是试试下面的代码：
 *  float negZero=-0.0f;
 *  float zero=0.0f;
 *  System.out.println(zero==negZero);
 *  System.out.println(Float.compare(zero,negZero));
 *
 *  返回的结果是true和-1。看到了么，==认为正0和负0相等，而compare()方法认为正0比负0要大。所以对0的比较来说，==是更好的选择。
 *  更有趣的事：
 *  [java] view plain copy print?
 *  double i = 1.0 / 0;
 *  System.out.println(i);             //Infinity
 *  System.out.println(i + 1);         //Infinity
 *  System.out.println(i == i + 1);    //true
 *
 *  i = 0.0 / 0;
 *  System.out.println(i);             //NaN
 *  System.out.println(i + 1);         //NaN
 *  System.out.println(i == i + 1);    //false
 */
public class ExceptionMethod {
    public static void testMathStatic(Double a,Double b){
        System.out.println(a/b);

    }
    public void testMath(Double a,Double b){
        System.out.println(a/b);

    }

}
