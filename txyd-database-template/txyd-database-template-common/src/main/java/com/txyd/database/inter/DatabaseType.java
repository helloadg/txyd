package com.txyd.database.inter;

public enum DatabaseType  implements IEnum{
	all(1,"all","所有的数据库的类型"),
	oracle(2,"oracle","oracle数据库"),
	mysql(3,"mysql","mysql数据库"),
	ms_sql_server(4,"ms_sql_server","微软sql Server数据库"),
	access(5,"access","微软access数据库"),
	db2(6,"access","db2数据库，美国IBM公司开发的一套关系型数据库管理系统"),
	informix(7,"informix","informix数据库，美国IBM公司开发的一套关系型数据库管理系统");
	// 成员变量
    private String comments;
    private String name;
    private int index;

    // 构造方法
    private DatabaseType(int index,String name,String comments) {
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
    public static void main(String[] args) {

    	System.out.println(DatabaseType.mysql.toString());
        DatabaseType.all.getPath();
//    	IEnum iEnum=DatabaseType.all;    	
//        System.out.println(iEnum.getComments());
//        System.out.println(Enum.valueOf(DatabaseType.class,iEnum.getName()));
//        //System.out.println(Enum.valueOf(DatabaseType.class,iEnum.getComments()));//出错
    }
}
