package com.mc.db;

import java.util.HashSet;
import java.util.Set;

/**
 * 常量类
 */
public class Constants {
	public static final String VERSION = "1.0.0";
	
	/**
	 * service层，返回正确值时的errorCode码值
	 */
	public static final int ERROR_CODE_SUCCESS = 0;
	
	/**
	 * 默认页码起始页
	 */
	public static final int PAGE_NUM = 1;
	/**
	 * 默认每页大小
	 */
	public static final int PAGE_SIZE = 40;
	
	/**
	 * 删除
	 */
	public static final int IS_DELETED_NO = 0;
	public static final int IS_DELETED_YES = 1;
	
	
	/**
	 * redis 缓存时间
	 */
	public static final int REDIS_EXPIRE = 3600;
	
	
	/**
	 * redis key：
	 * 项目所有key开头字符串
	 */
	public static final String REDIS_SYSTEM_KEY = "mallcms_key_";
	
	
	/**
	 * 方案类型：1首页；2二级频道页；3集市；4内容空白页
	 */
	public static final int PLAN_TYPE_HOME = 1;
	public static final int PLAN_TYPE_SECONDCHANNEL = 2;
	public static final int PLAN_TYPE_MARKET = 3;
	public static final int PLAN_TYPE_CONTENT = 4;
	/**
	 * 方案所有的状态值
	 */
	public static final Set<Integer> PLAN_TYPE_SET = new HashSet<Integer>() {{
		add(PLAN_TYPE_HOME);
		add(PLAN_TYPE_SECONDCHANNEL);
		add(PLAN_TYPE_MARKET);
		add(PLAN_TYPE_CONTENT);
	}};
	
	/**
	 * 状态：1编辑中；2待审核；3待发布；4已发布；5已下线；
	 */
	public static final int PLAN_STATUS_EDITING = 1;
	public static final int PLAN_STATUS_PENDING = 2;
	public static final int PLAN_STATUS_TOBEONLINE = 3;
	public static final int PLAN_STATUS_ONLINE = 4;
	public static final int PLAN_STATUS_OFFLINE = 5;
	
	/**
	 * 方案所有的状态值
	 */
	public static final Set<Integer> planStatusSet = new HashSet<Integer>() {{
		add(PLAN_STATUS_EDITING);
		add(PLAN_STATUS_PENDING);
		add(PLAN_STATUS_TOBEONLINE);
		add(PLAN_STATUS_ONLINE);
		add(PLAN_STATUS_OFFLINE);
	}};
	
	/**
	 * 是否是发布后修改状态：0否；1是
	 */
	
	public static final int PLAN_MODIFY_STAUTS_NO = 0;
	public static final int PLAN_MODIFY_STAUTS_YES = 1;
	
	/**
	 * 是否已经进入回收站：0未进入；1已进入；
	 */
	public static final int PLAN_IS_IN_RECYCLE_NO = 0;
	public static final int PLAN_IS_IN_RECYCLE_YES = 1;
	
	/**
	 * 投放范围类型：1全国；2城市；3区域；
	 */
	public static final int PLAN_RANGE_TYPE_NATIONWIDE = 1;
	public static final int PLAN_RANGE_TYPE_CITY = 2;
	public static final int PLAN_RANGE_TYPE_AREA = 3;
	public static Set<Integer> planRangeTypeSet = new HashSet<Integer>() {{
		add(PLAN_RANGE_TYPE_NATIONWIDE);
		add(PLAN_RANGE_TYPE_CITY);
		add(PLAN_RANGE_TYPE_AREA);
	}};
	
	/**
	 * 投放范围全国:id与名称
	 */
	public static final long PLAN_RANGE_TYPE_NATIONWIDE_ID = 0;
	public static final String PLAN_RANGE_TYPE_NATIONWIDE_NAME = "全国";
	
	
	/**
	 * Log类型：1创建、2修改、3提审、4审核通过、5审核打回、6发布、7删除、8移入回收站、9弃审、10复制
	 */
	public static final int PLAN_LOG_TYPE_CREATE = 1;
	public static final int PLAN_LOG_TYPE_MODIFY = 2;
	public static final int PLAN_LOG_TYPE_AUDITING = 3;
	public static final int PLAN_LOG_TYPE_AUDIT_OK = 4;
	public static final int PLAN_LOG_TYPE_AUDIT_FAIL = 5;
	public static final int PLAN_LOG_TYPE_ONLINE = 6;
	public static final int PLAN_LOG_TYPE_DELETE = 7;
	public static final int PLAN_LOG_TYPE_MOVETORECYCLE = 8;
	public static final int PLAN_LOG_TYPE_END_AUDITING = 9;
	public static final int PLAN_LOG_TYPE_COPY_PLAN = 10;
	
	/**
	 * 是否有上边距：1有；0无
	 */
	public static final int IS_MARGIN_TOP_YES = 1;
	public static final int IS_MARGIN_TOP_NO = 0;
	public static final int IS_MARGIN_TOP_DEFAULT = IS_MARGIN_TOP_NO;//默认没有上边距
	/**
	 * 是否有上边距的所有值
	 */
	public static final Set<Integer> IS_MARGIN_TOP_SET = new HashSet<Integer>() {{
		add(IS_MARGIN_TOP_YES);
		add(IS_MARGIN_TOP_NO);
	}};
	
	/**
	 * 排列方向：1左；2中；3右
	 */
	public static final int IS_DIRECTION_LEFT = 1;
	public static final int IS_DIRECTION_CENTER = 2;
	public static final int IS_DIRECTION_RIGHT = 3;
	public static final int IS_DIRECTION_DEFAULT = IS_DIRECTION_LEFT;//默认为左
	
	/**
	 * 排列方向值的集合：1左；2中；3右
	 */
	public static final Set<Integer> isDirectionSet = new HashSet<Integer>() {{
		add(IS_DIRECTION_LEFT);
		add(IS_DIRECTION_CENTER);
		add(IS_DIRECTION_RIGHT);
	}};
	
	/**
	 * 方案下控件关联ids字符串分隔符
	 */
	public static final String PLAN_LAYOUT_WIDGET_ID_SEPARATOR = "_";
	
	/**
	 * 默认版本号
	 */
	public static final int PLAN_LAYOUT_DEFAULT_VERSION = 0;//默认值为0，首次添加控件后，方案的布局版本+1，所以每一个方案的布局版本开始值为1
	public static final int PLAN_CONTENT_DEFAULT_VERSION = 0;//默认值为0，首次添加控件后，方案的内容版本+1，所以每一个方案的内容版本开始值为1
	public static final int PLAN_WIDGET_DEFAULT_VERSION = 1;//控件的版本号默认开始值，此版本号会随着系统控件的版本增加而增加
	
	/**
	 * 是否投放ios、微信、android
	 */
	public static final int PLAN_IS_IOS_YES = 1;
	public static final int PLAN_IS_WEIXIN_YES = 1;
	public static final int PLAN_IS_ANDROID_YES = 1;
	
	public static final int PLAN_IS_IOS_NO = 0;
	public static final int PLAN_IS_WEIXIN_NO = 0;
	public static final int PLAN_IS_ANDROID_NO = 0;
	
	public static final int PLAN_IS_IOS_DEFAULT = PLAN_IS_IOS_YES;
	public static final int PLAN_IS_WEIXIN_DEFAULT = PLAN_IS_WEIXIN_YES;
	public static final int PLAN_IS_ANDROID_DEFAULT = PLAN_IS_ANDROID_YES;
	
	public static final Set<Integer> planIsIosSet = new HashSet<Integer>() {{
		add(PLAN_IS_IOS_NO);
		add(PLAN_IS_IOS_YES);
	}};
	
	public static final Set<Integer> planIsWeiXinSet = new HashSet<Integer>() {{
		add(PLAN_IS_WEIXIN_NO);
		add(PLAN_IS_WEIXIN_YES);
	}};
	
	public static final Set<Integer> planIsAndroidSet = new HashSet<Integer>() {{
		add(PLAN_IS_ANDROID_NO);
		add(PLAN_IS_ANDROID_YES);
	}};
	
	/**
	 * 控件内容变更类型：1修改、2增加、3删除
	 */
	public static final int PLAN_WIDGET_MONITOR_ADD = 1;
	public static final int PLAN_WIDGET_MONITOR_MODIFY = 2;
	public static final int PLAN_WIDGET_MONITOR_DELETE = 3;
	
	/**
	 * 控件下板块数量
	 */
	public static final int PLAN_WIDGET_PLATE_NUM_INFINITE = -1;
	public static final int PLAN_WIDGET_PLATE_NUM_ZERO = 0;
	
	/**
	 * 方案下投放范围是否有效
	 */
	public static final int PLAN_RANGE_IS_ABLE_YES = 1;
	public static final int PLAN_RANGE_IS_ABLE_NO = 0;
	
	
	/**
	 * 板块内容是否固定
	 */
	public static final int PLAN_PLATE_CONTENT_IS_FIXED_NO = 0;
	public static final int PLAN_PLATE_CONTENT_IS_FIXED_YES = 1;
	
	/**
	 * 控件的英文名称
	 */
	public static final String PLAN_WIDGET_EN_NAME_LINK1 = "link1";//广告位控件
	public static final String PLAN_WIDGET_EN_NAME_LINK2 = "link2";//图片控件
	public static final String PLAN_WIDGET_EN_NAME_LINK3 = "link3";//广告位控件2
	public static final String PLAN_WIDGET_EN_NAME_LINK4 = "link4";//栏目控件
	public static final String PLAN_WIDGET_EN_NAME_LINK5 = "link5";//商品位控件
	public static final String PLAN_WIDGET_EN_NAME_SLIDER1 = "slider1";//轮播图控件
	public static final String PLAN_WIDGET_EN_NAME_GOOD1 = "good1";//商品位控件
	
	/**
	 * 控件名称长度限制
	 */
	public static final int PLAN_WIDGET_EN_NAME_LINK4_NAME_LENGTH = 5;//栏目控件
	
	/**
	 * 商品跳转
	 */
	public static final String APP_LINK_FOR_GOODS_DETAIL = "jump2Page(3,JSON.stringify({'cid':{id}}))";
	public static final String WX_LINK_FOR_GOODS_DETAIL = "{weiXinUrl}/goods/detail?id={id}";
	
	/**
	 * 审核状态
	 * 审核是否通过：0否；1是；
	 */
	public static final int PLAN_REVIEW_IS_AGREED_YES = 1;
	public static final int PLAN_REVIEW_IS_AGREED_NO = 0;
	
	/**
	 * 本地状态下（即测试状态）
	 * 默认用户的用户名、id、城市id
	 */
	public static final int DEFAULT_USER_ID = 0;
	public static final String DEFAULT_USER_NAME = "native";
	public static final int DEFAULT_USER_CURRENT_CITY_ID = 1;
	
	/**
	 * 方案名称长度以及方案中页面标题长度
	 */
	public static final int PLAN_NAME_LENGTH = 14;
	public static final int PLAN_PAGE_TITLE_LENGTH = 8;
	
	/**
	 * 方案控件的默认高度
	 * -1：表示该方案控件没有设置高度
	 */
	public static final int PLAN_WIDGET_HEIGHT_DEFAULT = -1;
	
	/**
	 * 是否分享：0不分享；1分享
	 */
	public static final int PLAN_IS_SHARE_YES = 1;
	public static final int PLAN_IS_SHARE_NO = 0;
	public static final int PLAN_IS_SHARE_DEFAULT = PLAN_IS_SHARE_NO;
	public static final Set<Integer> planIsShareSet = new HashSet<Integer>() {{
		add(PLAN_IS_SHARE_YES);
		add(PLAN_IS_SHARE_NO);
	}};
	public static final int PLAN_SHARE_TITLE_MAX_NUM = 16;
	public static final int PLAN_SHARE_LINK_MAX_NUM = 100;
	public static final int PLAN_SHARE_DESC_MAX_NUM = 40;
	public static final int PLAN_SHARE_PIC_MAX_NUM = 200;
	
	/**
	 * 方案控件排序起始值;
	 */
	public static final int PLAN_WIDGET_SORT_START_NUM = 1;
	
	/**
	 * 校验类型 1 提审 2 审核
	 */
	public static final int OPERATOR_TYPE_TI_SHEN = 1;
	public static final int OPERATOR_TYPE_SHEN_HE = 2;
	
	/**
	 * 1微信、2android、3ios
	 */
	public static final int PLATFORM_WEIXIN = 1;
	public static final int PLATFORM_ANDROID = 2;
	public static final int PLATFORM_IOS = 3;
	public static final Set<Integer> plateformSet = new HashSet<Integer>() {{
		add(PLATFORM_WEIXIN);
		add(PLATFORM_ANDROID);
		add(PLATFORM_IOS);
	}};
	
}
