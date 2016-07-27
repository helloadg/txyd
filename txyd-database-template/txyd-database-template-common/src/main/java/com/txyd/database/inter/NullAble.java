package com.txyd.database.inter;
/**
 * 是否可以为null
 */
public enum NullAble implements IEnum {
	yes(1,"yes","可以为NULL"),no(2,"yes","不可以为NULL");
	// 成员变量
    private String comments;
    private String name;
    private int index;

    // 构造方法
    private NullAble(int index,String name,String comments) {
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
