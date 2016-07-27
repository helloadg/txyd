package com.txyd.database.inter;
/**
 * java类型
 * @author     
 *
 */
public enum JavaType implements IEnum {
	Class(1,"class","类"),
	Annotation(2,"annotation","注解"),
	Field(3,"field","属性"),
	Method(4,"method","方法"),
	Inteface(5,"inteface","接口"),
	Enum(6,"enum","枚举");
	// 成员变量
    private String comments;//枚举值的注释
    private String name;//枚举值的自定义名
    private int index;//枚举值的索引值

    // 构造方法
    private JavaType(int index,String name,String comments) {
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
