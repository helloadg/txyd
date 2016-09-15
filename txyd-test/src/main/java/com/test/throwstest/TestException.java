package com.test.throwstest;

/**
 * Created by Administrator on 2016/9/15.
 */
public class TestException {
    public void testExceptionStatic(Double a,Double b){
        ExceptionMethod.testMathStatic(a,b);
    }

    public void testException(Double a,Double b){
        new ExceptionMethod().testMathStatic(a,b);
    }

    public static void main(String[] args) {
        try{
            new TestException().testExceptionStatic(1.0,null);
        }catch(Throwable throwable){
            System.out.println(throwable.getMessage());
        }
        try{
            new TestException().testException(1.0,null);
        }catch(Throwable throwable){
            System.out.println(throwable.getMessage());
        }
        System.out.println(Double.NaN==Float.NaN);
        System.out.println(Double.NaN==Double.NaN);
        System.out.println(Float.NaN==Float.NaN);
        System.out.println(Float.compare(Float.NaN,Float.NaN));
        System.out.println(Double.compare(Float.NaN,Float.NaN));
        System.out.println(Double.compare(Double.NaN,Float.NaN));
        System.out.println(Double.compare(Double.NaN,Double.NaN));
    }



}
