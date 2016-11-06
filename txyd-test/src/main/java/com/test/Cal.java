package com.test;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/11/3.
 */
public class Cal {
	public static double toCal(int s, double x, double z, double y, double n, int m) {
		BigDecimal s_big = new BigDecimal(s + "");
		BigDecimal x_big = new BigDecimal(x + "");
		BigDecimal z_big = new BigDecimal(z + "");
		BigDecimal y_big = new BigDecimal(y + "");
		BigDecimal n_big = new BigDecimal(n + "");
		BigDecimal m_big = new BigDecimal(m + "");
		{
			BigDecimal r0=s_big;
			BigDecimal r1= new BigDecimal("1").subtract(new BigDecimal(x_big.multiply(y_big).toString())).pow(m_big.multiply(new BigDecimal("1").subtract(n_big)).intValue());
			BigDecimal r2=new BigDecimal("1").add(x_big.multiply(z_big)).pow(m_big.multiply(n_big).intValue());
			BigDecimal result = r0.multiply(r1).multiply(r2);
			return result.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
//
		}
		
//		{
//			double result = s * ((Math.pow((1 - x * y), m * (1 - n)) * Math.pow(1 + x * z, m * n)));
//			return result;
//		}
		
		
	}
	
	public static void main(String[] args) {
		int s = 100;
		double x = 0.2;
		double z = 1;
		double y = 0.5;
		double n = 0.6;
		int m = 100;
		
		System.out.println(new BigDecimal(toCal(s, x, z, y, n, m) + "").setScale(4, BigDecimal.ROUND_HALF_UP).toString());
//		System.out.println(new BigDecimal("2").pow(3).toString());
	
	
	}
}
