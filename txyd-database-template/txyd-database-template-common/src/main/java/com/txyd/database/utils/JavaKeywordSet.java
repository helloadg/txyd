package com.txyd.database.utils;

import java.util.HashSet;
import java.util.Set;


public final class JavaKeywordSet extends HashSet<String> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Set<String> javaKeywordSet;
	static{
		javaKeywordSet=new HashSet<String>();
		javaKeywordSet.add("abstract");
		javaKeywordSet.add("assert");
		javaKeywordSet.add("boolean");
		javaKeywordSet.add("break");
		javaKeywordSet.add("byte");
		javaKeywordSet.add("case");
		javaKeywordSet.add("catch");
		javaKeywordSet.add("char");
		javaKeywordSet.add("class");
		javaKeywordSet.add("const");
		javaKeywordSet.add("continue");
		javaKeywordSet.add("default");
		javaKeywordSet.add("do");
		javaKeywordSet.add("double");
		javaKeywordSet.add("else");
		javaKeywordSet.add("enum");
		javaKeywordSet.add("extends");
		javaKeywordSet.add("final");
		javaKeywordSet.add("finally");
		javaKeywordSet.add("float");
		javaKeywordSet.add("for");
		javaKeywordSet.add("if");
		javaKeywordSet.add("implements");
		javaKeywordSet.add("import");
		javaKeywordSet.add("instanceof");
		javaKeywordSet.add("int");
		javaKeywordSet.add("interface");
		javaKeywordSet.add("long");
		javaKeywordSet.add("native");
		javaKeywordSet.add("new");
		javaKeywordSet.add("package");
		javaKeywordSet.add("private");
		javaKeywordSet.add("protected");
		javaKeywordSet.add("public");
		javaKeywordSet.add("return");
		javaKeywordSet.add("short");
		javaKeywordSet.add("static");
		javaKeywordSet.add("strictfp");
		javaKeywordSet.add("super");
		javaKeywordSet.add("switch");
		javaKeywordSet.add("synchronized");
		javaKeywordSet.add("this");
		javaKeywordSet.add("throw");
		javaKeywordSet.add("throws");
		javaKeywordSet.add("transient");
		javaKeywordSet.add("try");
		javaKeywordSet.add("void");
		javaKeywordSet.add("volatile");
		javaKeywordSet.add("while");
		javaKeywordSet.add("cast");
		javaKeywordSet.add("false");
		javaKeywordSet.add("future");
		javaKeywordSet.add("generic");
		javaKeywordSet.add("inner");
		javaKeywordSet.add("operator");
		javaKeywordSet.add("outer");
		javaKeywordSet.add("rest");
		javaKeywordSet.add("true");
		javaKeywordSet.add("var");
		javaKeywordSet.add("goto");
		javaKeywordSet.add("const");
		javaKeywordSet.add("null");
	}
	private JavaKeywordSet()
	{
	}
	public static  Set<String> getInstance()
	{
		return  javaKeywordSet;
	}

}
