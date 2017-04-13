package com.txyd.mybatis.entity;
import java.io.Serializable;
//import com.fasterxml.jackson.annotation.JsonProperty;
/**		
 * 数据库类型：mysql
 * 表所属schema：popbase
 * 表所属用户：root
 * 表名称：t_settlement_bill_period
 * 表注释：系统自动账单期表
 * 类型：table
 * @author：
 */
public class BillPeriodEntity  implements Serializable  {
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 是否可以为NULL：否
	 * 列类型：bigint(20) unsigned
	 * 默认值：null
	 * 列的数据类型的长度：20.0
	 * 列注释：主键
	 * 列的扩展：auto_increment
	 * 列名：id
	 * 列的数据类型：bigint
	 * 是否是主键：是
	 */
	private Long id;	
	
	/**
	 * 是否可以为NULL：否
	 * 列类型：int(10)
	 * 默认值：0
	 * 列的数据类型的长度：10.0
	 * 列注释：pop商家id
	 * 列的扩展：
	 * 列名：pop_id
	 * 列的数据类型：int
	 * 是否是主键：否
	 */
	private Integer popId;	
	
	/**
	 * 是否可以为NULL：否
	 * 列类型：int(11)
	 * 默认值：0
	 * 列的数据类型的长度：10.0
	 * 列注释：账单开始时间：格式20170801
	 * 列的扩展：
	 * 列名：bill_start_time
	 * 列的数据类型：int
	 * 是否是主键：否
	 */
	private Integer billStartTime;	
	
	/**
	 * 是否可以为NULL：否
	 * 列类型：int(11)
	 * 默认值：0
	 * 列的数据类型的长度：10.0
	 * 列注释：账单结束时间：格式20170801
	 * 列的扩展：
	 * 列名：bill_end_time
	 * 列的数据类型：int
	 * 是否是主键：否
	 */
	private Integer billEndTime;	
	
	/**
	 * 是否可以为NULL：否
	 * 列类型：int(11)
	 * 默认值：0
	 * 列的数据类型的长度：10.0
	 * 列注释：预执行时间
	 * 列的扩展：
	 * 列名：pre_exe_time
	 * 列的数据类型：int
	 * 是否是主键：否
	 */
	private Integer preExeTime;	
	
	/**
	 * 是否可以为NULL：否
	 * 列类型：int(11)
	 * 默认值：0
	 * 列的数据类型的长度：10.0
	 * 列注释：执行时间
	 * 列的扩展：
	 * 列名：exe_time
	 * 列的数据类型：int
	 * 是否是主键：否
	 */
	private Integer exeTime;	
	
	/**
	 * 是否可以为NULL：否
	 * 列类型：tinyint(4)
	 * 默认值：0
	 * 列的数据类型的长度：3.0
	 * 列注释：执行状态:0未执行，1已执行，2执行失败
	 * 列的扩展：
	 * 列名：status
	 * 列的数据类型：tinyint
	 * 是否是主键：否
	 */
	private Integer status;	
	
	/**
	 * 是否可以为NULL：否
	 * 列类型：varchar(1024)
	 * 默认值：
	 * 列的数据类型的长度：1024
	 * 列注释：执行情况描述
	 * 列的扩展：
	 * 列名：reason
	 * 列的数据类型：varchar
	 * 是否是主键：否
	 */
	private String reason;	
	
	/**
	 * 是否可以为NULL：否
	 * 列类型：int(11)
	 * 默认值：0
	 * 列的数据类型的长度：10.0
	 * 列注释：创建时间
	 * 列的扩展：
	 * 列名：c_t
	 * 列的数据类型：int
	 * 是否是主键：否
	 */
	private Integer ct;	
	
	/**
	 * 是否可以为NULL：否
	 * 列类型：int(11)
	 * 默认值：0
	 * 列的数据类型的长度：10.0
	 * 列注释：修改时间
	 * 列的扩展：
	 * 列名：u_t
	 * 列的数据类型：int
	 * 是否是主键：否
	 */
	private Integer ut;	
	
	/**
	 * 是否可以为NULL：否
	 * 列类型：tinyint(4)
	 * 默认值：0
	 * 列的数据类型的长度：3.0
	 * 列注释：是否被删除：0正常；1被删除
	 * 列的扩展：
	 * 列名：is_deleted
	 * 列的数据类型：tinyint
	 * 是否是主键：否
	 */
	private Integer isDeleted;	

	
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}

	
	public Integer getPopId(){
		return this.popId;
	}
	
	public void setPopId(Integer popId){
		this.popId = popId;
	}

	
	public Integer getBillStartTime(){
		return this.billStartTime;
	}
	
	public void setBillStartTime(Integer billStartTime){
		this.billStartTime = billStartTime;
	}

	
	public Integer getBillEndTime(){
		return this.billEndTime;
	}
	
	public void setBillEndTime(Integer billEndTime){
		this.billEndTime = billEndTime;
	}

	
	public Integer getPreExeTime(){
		return this.preExeTime;
	}
	
	public void setPreExeTime(Integer preExeTime){
		this.preExeTime = preExeTime;
	}

	
	public Integer getExeTime(){
		return this.exeTime;
	}
	
	public void setExeTime(Integer exeTime){
		this.exeTime = exeTime;
	}

	
	public Integer getStatus(){
		return this.status;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}

	
	public String getReason(){
		return this.reason;
	}
	
	public void setReason(String reason){
		this.reason = reason;
	}

	
	public Integer getCt(){
		return this.ct;
	}
	
	public void setCt(Integer ct){
		this.ct = ct;
	}

	
	public Integer getUt(){
		return this.ut;
	}
	
	public void setUt(Integer ut){
		this.ut = ut;
	}

	
	public Integer getIsDeleted(){
		return this.isDeleted;
	}
	
	public void setIsDeleted(Integer isDeleted){
		this.isDeleted = isDeleted;
	}


}