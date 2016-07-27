package com.txyd.mybatis.entity;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 数据库类型：mysql
 * 表所属schema：test
 * 表所属用户：root
 * 表名称：t_lottery_activity
 * 表注释：
 * 类型：table
 * @author：
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActivityEntity extends BaseEntity  implements Serializable  {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 是否可以为NULL：no
	 * 列类型：bigint(20)
	 * 默认值：
	 * 列的数据类型的长度：19.0
	 * 列注释：主键
	 * 列的扩展：auto_increment
	 * 列名：id
	 * 列的数据类型：bigint
	 * 是否是主键：yes
	 */
	@JsonProperty("id")
	private Long id;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：tinyint(4)
	 * 默认值：
	 * 列的数据类型的长度：3.0
	 * 列注释：模板
	 * 列的扩展：
	 * 列名：template
	 * 列的数据类型：tinyint
	 * 是否是主键：no
	 */
	@JsonProperty("template")
	private Integer template;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：varchar(200)
	 * 默认值：
	 * 列的数据类型的长度：200
	 * 列注释：活动名称
	 * 列的扩展：
	 * 列名：name
	 * 列的数据类型：varchar
	 * 是否是主键：no
	 */
	@JsonProperty("name")
	private String name;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：varchar(500)
	 * 默认值：
	 * 列的数据类型的长度：500
	 * 列注释：url
	 * 列的扩展：
	 * 列名：url
	 * 列的数据类型：varchar
	 * 是否是主键：no
	 */
	@JsonProperty("url")
	private String url;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：int(11)
	 * 默认值：
	 * 列的数据类型的长度：10.0
	 * 列注释：活动开始时间
	 * 列的扩展：
	 * 列名：activity_start_time
	 * 列的数据类型：int
	 * 是否是主键：no
	 */
	@JsonProperty("activity_start_time")
	private Integer activityStartTime;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：int(11)
	 * 默认值：
	 * 列的数据类型的长度：10.0
	 * 列注释：活动结束时间
	 * 列的扩展：
	 * 列名：activity_end_time
	 * 列的数据类型：int
	 * 是否是主键：no
	 */
	@JsonProperty("activity_end_time")
	private Integer activityEndTime;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：int(11)
	 * 默认值：
	 * 列的数据类型的长度：10.0
	 * 列注释：抽奖开始时间
	 * 列的扩展：
	 * 列名：lottery_start_time
	 * 列的数据类型：int
	 * 是否是主键：no
	 */
	@JsonProperty("lottery_start_time")
	private Integer lotteryStartTime;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：int(11)
	 * 默认值：
	 * 列的数据类型的长度：10.0
	 * 列注释：抽奖结束
	 * 列的扩展：
	 * 列名：lottery_end_time
	 * 列的数据类型：int
	 * 是否是主键：no
	 */
	@JsonProperty("lottery_end_time")
	private Integer lotteryEndTime;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：int(11)
	 * 默认值：
	 * 列的数据类型的长度：10.0
	 * 列注释：活动区域
	 * 列的扩展：
	 * 列名：activity_area
	 * 列的数据类型：int
	 * 是否是主键：no
	 */
	@JsonProperty("activity_area")
	private Integer activityArea;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：int(11)
	 * 默认值：
	 * 列的数据类型的长度：10.0
	 * 列注释：
	 * 列的扩展：
	 * 列名：everyday_start_time
	 * 列的数据类型：int
	 * 是否是主键：no
	 */
	@JsonProperty("everyday_start_time")
	private Integer everydayStartTime;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：int(11)
	 * 默认值：
	 * 列的数据类型的长度：10.0
	 * 列注释：
	 * 列的扩展：
	 * 列名：everyday_end_time
	 * 列的数据类型：int
	 * 是否是主键：no
	 */
	@JsonProperty("everyday_end_time")
	private Integer everydayEndTime;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：varchar(100)
	 * 默认值：
	 * 列的数据类型的长度：100
	 * 列注释：分享标题
	 * 列的扩展：
	 * 列名：share_titile
	 * 列的数据类型：varchar
	 * 是否是主键：no
	 */
	@JsonProperty("share_titile")
	private String shareTitile;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：varchar(500)
	 * 默认值：
	 * 列的数据类型的长度：500
	 * 列注释：
	 * 列的扩展：
	 * 列名：share_desc
	 * 列的数据类型：varchar
	 * 是否是主键：no
	 */
	@JsonProperty("share_desc")
	private String shareDesc;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：varchar(500)
	 * 默认值：
	 * 列的数据类型的长度：500
	 * 列注释：分享链接
	 * 列的扩展：
	 * 列名：share_url
	 * 列的数据类型：varchar
	 * 是否是主键：no
	 */
	@JsonProperty("share_url")
	private String shareUrl;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：varchar(200)
	 * 默认值：
	 * 列的数据类型的长度：200
	 * 列注释：分享图片路径
	 * 列的扩展：
	 * 列名：share_img
	 * 列的数据类型：varchar
	 * 是否是主键：no
	 */
	@JsonProperty("share_img")
	private String shareImg;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：tinyint(4)
	 * 默认值：
	 * 列的数据类型的长度：3.0
	 * 列注释：是否需要登录才能抽奖
	 * 列的扩展：
	 * 列名：qf_is_login
	 * 列的数据类型：tinyint
	 * 是否是主键：no
	 */
	@JsonProperty("qf_is_login")
	private Integer qfIsLogin;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：tinyint(4)
	 * 默认值：
	 * 列的数据类型的长度：3.0
	 * 列注释：是否前一天下单才能抽奖,0否1是
	 * 列的扩展：
	 * 列名：qf_is_lastday_order
	 * 列的数据类型：tinyint
	 * 是否是主键：no
	 */
	@JsonProperty("qf_is_lastday_order")
	private Integer qfIsLastdayOrder;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：double
	 * 默认值：
	 * 列的数据类型的长度：
	 * 列注释：订单金额限制
	 * 列的扩展：
	 * 列名：order_money_limit
	 * 列的数据类型：double
	 * 是否是主键：no
	 */
	@JsonProperty("order_money_limit")
	private Double orderMoneyLimit;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：tinyint(4)
	 * 默认值：
	 * 列的数据类型的长度：3.0
	 * 列注释：抽奖次数类型0固定，1根据活动下单次数
	 * 列的扩展：
	 * 列名：lottery_times_type
	 * 列的数据类型：tinyint
	 * 是否是主键：no
	 */
	@JsonProperty("lottery_times_type")
	private Integer lotteryTimesType;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：int(11)
	 * 默认值：
	 * 列的数据类型的长度：10.0
	 * 列注释：抽奖次数
	 * 列的扩展：
	 * 列名：lottery_times
	 * 列的数据类型：int
	 * 是否是主键：no
	 */
	@JsonProperty("lottery_times")
	private Integer lotteryTimes;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：int(11)
	 * 默认值：
	 * 列的数据类型的长度：10.0
	 * 列注释：创建人id
	 * 列的扩展：
	 * 列名：create_user_id
	 * 列的数据类型：int
	 * 是否是主键：no
	 */
	@JsonProperty("create_user_id")
	private Integer createUserId;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：varchar(50)
	 * 默认值：
	 * 列的数据类型的长度：50
	 * 列注释：创建人名称
	 * 列的扩展：
	 * 列名：create_user_name
	 * 列的数据类型：varchar
	 * 是否是主键：no
	 */
	@JsonProperty("create_user_name")
	private String createUserName;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：int(11)
	 * 默认值：
	 * 列的数据类型的长度：10.0
	 * 列注释：修改人id
	 * 列的扩展：
	 * 列名：update_user_id
	 * 列的数据类型：int
	 * 是否是主键：no
	 */
	@JsonProperty("update_user_id")
	private Integer updateUserId;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：tinyint(4)
	 * 默认值：
	 * 列的数据类型的长度：3.0
	 * 列注释：是否必须中奖，0否1是
	 * 列的扩展：
	 * 列名：is_must_win
	 * 列的数据类型：tinyint
	 * 是否是主键：no
	 */
	@JsonProperty("is_must_win")
	private Integer isMustWin;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：varchar(50)
	 * 默认值：
	 * 列的数据类型的长度：50
	 * 列注释：修改人名称
	 * 列的扩展：
	 * 列名：update_user_name
	 * 列的数据类型：varchar
	 * 是否是主键：no
	 */
	@JsonProperty("update_user_name")
	private String updateUserName;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：tinyint(4)
	 * 默认值：
	 * 列的数据类型的长度：3.0
	 * 列注释：0无效1有效2下线
	 * 列的扩展：
	 * 列名：status
	 * 列的数据类型：tinyint
	 * 是否是主键：no
	 */
	@JsonProperty("status")
	private Integer status;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：text
	 * 默认值：
	 * 列的数据类型的长度：65535
	 * 列注释：活动规则
	 * 列的扩展：
	 * 列名：rule_desc
	 * 列的数据类型：text
	 * 是否是主键：no
	 */
	@JsonProperty("rule_desc")
	private String ruleDesc;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：int(11)
	 * 默认值：
	 * 列的数据类型的长度：10.0
	 * 列注释：创建时间
	 * 列的扩展：
	 * 列名：c_t
	 * 列的数据类型：int
	 * 是否是主键：no
	 */
	@JsonProperty("c_t")
	private Integer ct;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：int(11)
	 * 默认值：
	 * 列的数据类型的长度：10.0
	 * 列注释：修改时间
	 * 列的扩展：
	 * 列名：u_t
	 * 列的数据类型：int
	 * 是否是主键：no
	 */
	@JsonProperty("u_t")
	private Integer ut;	
	
	/**
	 * 是否可以为NULL：no
	 * 列类型：tinyint(4)
	 * 默认值：
	 * 列的数据类型的长度：3.0
	 * 列注释：删除标识，0正常，1删除
	 * 列的扩展：
	 * 列名：is_deleted
	 * 列的数据类型：tinyint
	 * 是否是主键：no
	 */
	@JsonProperty("is_deleted")
	private Integer isDeleted;	

	
	public Long getId(){
		return this.id;
	}
	
	public void setId(Long id){
		this.id = id;
	}
	
	public Integer getTemplate(){
		return this.template;
	}
	
	public void setTemplate(Integer template){
		this.template = template;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getUrl(){
		return this.url;
	}
	
	public void setUrl(String url){
		this.url = url;
	}
	
	public Integer getActivityStartTime(){
		return this.activityStartTime;
	}
	
	public void setActivityStartTime(Integer activityStartTime){
		this.activityStartTime = activityStartTime;
	}
	
	public Integer getActivityEndTime(){
		return this.activityEndTime;
	}
	
	public void setActivityEndTime(Integer activityEndTime){
		this.activityEndTime = activityEndTime;
	}
	
	public Integer getLotteryStartTime(){
		return this.lotteryStartTime;
	}
	
	public void setLotteryStartTime(Integer lotteryStartTime){
		this.lotteryStartTime = lotteryStartTime;
	}
	
	public Integer getLotteryEndTime(){
		return this.lotteryEndTime;
	}
	
	public void setLotteryEndTime(Integer lotteryEndTime){
		this.lotteryEndTime = lotteryEndTime;
	}
	
	public Integer getActivityArea(){
		return this.activityArea;
	}
	
	public void setActivityArea(Integer activityArea){
		this.activityArea = activityArea;
	}
	
	public Integer getEverydayStartTime(){
		return this.everydayStartTime;
	}
	
	public void setEverydayStartTime(Integer everydayStartTime){
		this.everydayStartTime = everydayStartTime;
	}
	
	public Integer getEverydayEndTime(){
		return this.everydayEndTime;
	}
	
	public void setEverydayEndTime(Integer everydayEndTime){
		this.everydayEndTime = everydayEndTime;
	}
	
	public String getShareTitile(){
		return this.shareTitile;
	}
	
	public void setShareTitile(String shareTitile){
		this.shareTitile = shareTitile;
	}
	
	public String getShareDesc(){
		return this.shareDesc;
	}
	
	public void setShareDesc(String shareDesc){
		this.shareDesc = shareDesc;
	}
	
	public String getShareUrl(){
		return this.shareUrl;
	}
	
	public void setShareUrl(String shareUrl){
		this.shareUrl = shareUrl;
	}
	
	public String getShareImg(){
		return this.shareImg;
	}
	
	public void setShareImg(String shareImg){
		this.shareImg = shareImg;
	}
	
	public Integer getQfIsLogin(){
		return this.qfIsLogin;
	}
	
	public void setQfIsLogin(Integer qfIsLogin){
		this.qfIsLogin = qfIsLogin;
	}
	
	public Integer getQfIsLastdayOrder(){
		return this.qfIsLastdayOrder;
	}
	
	public void setQfIsLastdayOrder(Integer qfIsLastdayOrder){
		this.qfIsLastdayOrder = qfIsLastdayOrder;
	}
	
	public Double getOrderMoneyLimit(){
		return this.orderMoneyLimit;
	}
	
	public void setOrderMoneyLimit(Double orderMoneyLimit){
		this.orderMoneyLimit = orderMoneyLimit;
	}
	
	public Integer getLotteryTimesType(){
		return this.lotteryTimesType;
	}
	
	public void setLotteryTimesType(Integer lotteryTimesType){
		this.lotteryTimesType = lotteryTimesType;
	}
	
	public Integer getLotteryTimes(){
		return this.lotteryTimes;
	}
	
	public void setLotteryTimes(Integer lotteryTimes){
		this.lotteryTimes = lotteryTimes;
	}
	
	public Integer getCreateUserId(){
		return this.createUserId;
	}
	
	public void setCreateUserId(Integer createUserId){
		this.createUserId = createUserId;
	}
	
	public String getCreateUserName(){
		return this.createUserName;
	}
	
	public void setCreateUserName(String createUserName){
		this.createUserName = createUserName;
	}
	
	public Integer getUpdateUserId(){
		return this.updateUserId;
	}
	
	public void setUpdateUserId(Integer updateUserId){
		this.updateUserId = updateUserId;
	}
	
	public Integer getIsMustWin(){
		return this.isMustWin;
	}
	
	public void setIsMustWin(Integer isMustWin){
		this.isMustWin = isMustWin;
	}
	
	public String getUpdateUserName(){
		return this.updateUserName;
	}
	
	public void setUpdateUserName(String updateUserName){
		this.updateUserName = updateUserName;
	}
	
	public Integer getStatus(){
		return this.status;
	}
	
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public String getRuleDesc(){
		return this.ruleDesc;
	}
	
	public void setRuleDesc(String ruleDesc){
		this.ruleDesc = ruleDesc;
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