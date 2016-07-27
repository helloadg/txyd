package com.txyd.database.inter;
/**
 * java符号类型
 * @author     
 *
 */
public enum CharType implements IEnum {
	tab(1,"\t","类"),
	newLine(2,"\n","注解");
	// 成员变量
    private String comments;//枚举值的注释
    private String name;//枚举值的自定义名
    private int index;//枚举值的索引值

    // 构造方法
    private CharType(int index,String name,String comments) {
        this.name=name;
        this.comments=comments;
        this.index = index;
    }

    /**
     * 
     * @Description 获得枚举的名称
     * @author     
     * @return
     */
    @Override
    public String toString() {
    	return super.toString();
    }
    /**
     * 
     * @Description 获得枚举的自定义名
     * @author     
     * @return
     */
    @Override
    public String getName() {
        return this.name;
    }
    
    /**
     * 
     * @Description 获得枚举的注释
     * @author     
     * @return
     */
    @Override
    public String getComments() {
        return this.comments;
    }
    /**
     * 
     * @Description 获得枚举的索引
     * @author     
     * @return
     */
    @Override
    public int getIndex() {
        return this.index;
    }
    /**
     * 
     * @Description 获得枚举值的全路径，即："类名.值名"
     * @author     
     * @return
     */
    @Override
    public String getPath()
    {
    	return this.getClass().getSimpleName()+"."+this.toString();
    }
}
