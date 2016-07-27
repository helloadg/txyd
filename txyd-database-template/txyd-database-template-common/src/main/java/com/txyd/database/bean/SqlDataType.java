package com.txyd.database.bean;

import java.lang.reflect.Field;

public class SqlDataType {
	public static String TINYBLOB;
	public static String MEDIUMBLOB;
	public static String BLOB;
	public static String LONGBLOB;
	public static String BIT;
	public static String TINYINT;
	public static String SMALLINT;
	public static String MEDIUMINT;
	public static String INT;
	public static String INTEGER;
	public static String BIGINT;
	public static String FLOAT;
	public static String DOUBLE;
	public static String DECIMAL;
	public static String CHAR;
	public static String VARCHAR;
	public static String TINYTEXT;
	public static String MEDIUMTEXT;
	public static String TEXT;
	public static String LONGTEXT;
	public static String DATE;
	public static String TIME;
	public static String DATETIME;
	public static String TIMESTAMP;
	public static String YEAR;
	public static String SET;
	public static String ENUM;
	
	/**
	 * 根据数据库的类型获得java所对应的类型
	 * 如果不存在默认为"java.lang.String"类型
	 * @param sqlType
	 * @return
	 */
	public static String getJavaType(String sqlType){
		String result=String.class.getName();//"java.lang.String"
		try {
			if(sqlType!=null&&!sqlType.trim().isEmpty()){
				SqlDataType sqlDataType=SqlDataType.class.newInstance();
				Field field=sqlDataType.getClass().getDeclaredField(sqlType.trim().toUpperCase());
				if(field!=null){
					result=field.get(sqlDataType).toString().trim();
				}
			}
			
		} catch (Exception e) {
		}
		return result;
		
	}
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		SqlDataType sqlDataType=SqlDataType.class.newInstance();
		SqlDataType.ENUM="enum";
		System.out.println(getJavaType("enum1"));
		System.out.println(String.class.getName());
		String.class.getName();
		
		
	}
	
	

	/****************************************************************/
	/*********************使用静态方法，方便spring注入****************************/
	/****************************************************************/
	
	public static String getTINYBLOB() {
		return TINYBLOB;
	}
	public static void setTINYBLOB(String tINYBLOB) {
		TINYBLOB = tINYBLOB;
	}
	public static String getMEDIUMBLOB() {
		return MEDIUMBLOB;
	}
	public static void setMEDIUMBLOB(String mEDIUMBLOB) {
		MEDIUMBLOB = mEDIUMBLOB;
	}
	public static String getBLOB() {
		return BLOB;
	}
	public static void setBLOB(String bLOB) {
		BLOB = bLOB;
	}
	public static String getLONGBLOB() {
		return LONGBLOB;
	}
	public static void setLONGBLOB(String lONGBLOB) {
		LONGBLOB = lONGBLOB;
	}
	public static String getBIT() {
		return BIT;
	}
	public static void setBIT(String bIT) {
		BIT = bIT;
	}
	public static String getTINYINT() {
		return TINYINT;
	}
	public static void setTINYINT(String tINYINT) {
		TINYINT = tINYINT;
	}
	public static String getSMALLINT() {
		return SMALLINT;
	}
	public static void setSMALLINT(String sMALLINT) {
		SMALLINT = sMALLINT;
	}
	public static String getMEDIUMINT() {
		return MEDIUMINT;
	}
	public static void setMEDIUMINT(String mEDIUMINT) {
		MEDIUMINT = mEDIUMINT;
	}
	public static String getINT() {
		return INT;
	}
	public static void setINT(String iNT) {
		INT = iNT;
	}
	public static String getINTEGER() {
		return INTEGER;
	}
	public static void setINTEGER(String iNTEGER) {
		INTEGER = iNTEGER;
	}
	public static String getBIGINT() {
		return BIGINT;
	}
	public static void setBIGINT(String bIGINT) {
		BIGINT = bIGINT;
	}
	public static String getFLOAT() {
		return FLOAT;
	}
	public static void setFLOAT(String fLOAT) {
		FLOAT = fLOAT;
	}
	public static String getDOUBLE() {
		return DOUBLE;
	}
	public static void setDOUBLE(String dOUBLE) {
		DOUBLE = dOUBLE;
	}
	public static String getDECIMAL() {
		return DECIMAL;
	}
	public static void setDECIMAL(String dECIMAL) {
		DECIMAL = dECIMAL;
	}
	public static String getCHAR() {
		return CHAR;
	}
	public static void setCHAR(String cHAR) {
		CHAR = cHAR;
	}
	public static String getVARCHAR() {
		return VARCHAR;
	}
	public static void setVARCHAR(String vARCHAR) {
		VARCHAR = vARCHAR;
	}
	public static String getTINYTEXT() {
		return TINYTEXT;
	}
	public static void setTINYTEXT(String tINYTEXT) {
		TINYTEXT = tINYTEXT;
	}
	public static String getMEDIUMTEXT() {
		return MEDIUMTEXT;
	}
	public static void setMEDIUMTEXT(String mEDIUMTEXT) {
		MEDIUMTEXT = mEDIUMTEXT;
	}
	public static String getTEXT() {
		return TEXT;
	}
	public static void setTEXT(String tEXT) {
		TEXT = tEXT;
	}
	public static String getLONGTEXT() {
		return LONGTEXT;
	}
	public static void setLONGTEXT(String lONGTEXT) {
		LONGTEXT = lONGTEXT;
	}
	public static String getDATE() {
		return DATE;
	}
	public static void setDATE(String dATE) {
		DATE = dATE;
	}
	public static String getTIME() {
		return TIME;
	}
	public static void setTIME(String tIME) {
		TIME = tIME;
	}
	public static String getDATETIME() {
		return DATETIME;
	}
	public static void setDATETIME(String dATETIME) {
		DATETIME = dATETIME;
	}
	public static String getTIMESTAMP() {
		return TIMESTAMP;
	}
	public static void setTIMESTAMP(String tIMESTAMP) {
		TIMESTAMP = tIMESTAMP;
	}
	public static String getYEAR() {
		return YEAR;
	}
	public static void setYEAR(String yEAR) {
		YEAR = yEAR;
	}
	public static String getSET() {
		return SET;
	}
	public static void setSET(String sET) {
		SET = sET;
	}
	public static String getENUM() {
		return ENUM;
	}
	public static void setENUM(String eNUM) {
		ENUM = eNUM;
	}
	
	
	
}
