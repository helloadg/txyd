package txyd.util;



//import java.math.BigInteger;
//import java.util.regex.Pattern;

//import org.apache.commons.lang.StringUtils;


/**
 * ClassName:ParamsUtils <br/>
 * Date:     2015年7月1日 下午8:08:29 <br/>
 * @author   兴权
 * @version  v1.0 
 * @since    JDK 1.7
 * @see 	 
 */
public class ParamsUtils {

//	/**
//	 * 把Object类型转为Integer. <br/>
//	 * 要转换的参数不允许为空 <br/>
//	 * @param Object <br/>
//	 * @return Integer  <br/>
//	 * 如果参数为空、空字符串、非数字类型都抛出 InvalidParamException 异常
//	 */
//	public static Integer objectToIntegerNotNull(String paramName ,Object obj){
//		if(obj==null){
//			throw new InvalidParamException("Parameter ["+paramName+"] Not [null]");
//		}
//		String objStr = objectToStringTrim(obj);
//		if("".equals(objStr)){
//			throw new InvalidParamException("Parameter ["+paramName+"] Not empty");
//		}
//		if(!isPositiveInteger(objStr)){
//			throw new InvalidParamException("Parameter ["+paramName+"]  Must be an Integer ");
//		}
//		return Integer.valueOf(objStr);
//	}
//	
//	public static Long objectToLongNotNull(String paramName ,Object obj){
//		Long param=null;
//		if(obj==null){
//			throw new InvalidParamException("Parameter ["+paramName+"] Not [null]");
//		}
//		String objStr = objectToStringTrim(obj);
//		if("".equals(objStr)){
//			throw new InvalidParamException("Parameter ["+paramName+"] Not [\"\"]");
//		}
//		if(!isPositiveInteger(objStr)){
//			throw new InvalidParamException("Parameter ["+paramName+"]  Must be an Integer ");
//		}
//		try{
//			param = Long.valueOf(objStr);
//		}catch(NumberFormatException e)
//		{
//			throw new InvalidParamException("Parameter ["+paramName+"] is out of range of long ");
//		}
//		return param;
//	}
//	/**
//	 * 把Object类型转为String 并去空格). <br/>
//	 * 要转换的参数不允许为空 <br/>
//	 * @param Object <br/>
//	 * @param String <br/>
//	 * 如果参数为空或者空字符串则抛出 InvalidParamException 异常
//	 */
//	public static String objectToStringNotNull(String paramName ,Object obj){
//		 if(obj==null){
//			 throw new InvalidParamException("Parameter ["+paramName+"] Not [NULL]");
//		 }
//		 String param = objectToStringTrim(obj);
//		 if(StringUtils.isBlank(param)){
//			 throw new InvalidParamException("Parameter ["+paramName+"] 不能为空");
//		 }
//		return param;
//	}
//	
//	/**
//	 * isPositiveInteger:(验证是否是正整数). <br/>
//	 * @return boolean
//	 */
//	public static boolean isPositiveInteger(String str){  
//		if(StringUtils.isBlank(str)){
//			return false;
//		}
//	    Pattern pattern = Pattern.compile("^-?[0-9]\\d*$");  
//	    return pattern.matcher(str).matches();     
//	}  
//	/**
//	 * 验证是否是数字
//	 * @return boolean
//	 */
//	public static boolean isNumeric(String str) {
//		if(StringUtils.isBlank(str)){
//			return false;
//		}
//		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
//	}
//	/**
//	 * objectToStringTrim:(Object转String 并去空格). <br/>
//	 * @return String
//	 */
//	public static String objectToStringTrim(Object obj){
//		String param = null;
//		if(obj!=null){
//			param = String.valueOf(obj);
////			param = param.replaceAll("　","").replaceAll(" ", "");
//			param = param.trim();
//		}
//		return param;
//	}
//	
//	/**
//	 * 
//	 * objectToString:(Object转字符串). <br/>
//	 */
//	public static String objectToString(Object obj){
//		String param = null;
//		if(obj!=null){
//			param = String.valueOf(obj);
//		}
//		return param;
//	}
//	
//	/**
//	 * objectToInteger:((Object转Integer ). <br/>
//	 * @return Integer
//	 */
//	public static Integer objectToInteger(String paramName ,Object obj){
//		Integer param = null;
//		if(obj!=null){
//			String objStr = objectToStringTrim(obj);
//			if("".equals(objStr)){
//				return null;
//			}
//			if(!isPositiveInteger(objStr)){
//				throw new InvalidParamException("Parameter ["+paramName+"] Must be an Integer ");
//			}
//			try
//			{
//				param = Integer.valueOf(objStr);
//			}catch(NumberFormatException e)
//			{
//				throw new InvalidParamException("Parameter ["+paramName+"] is out of range of int ");
//			}
//				
//		}
//		return param;
//	}
//	public static Long objectToLong(String paramName ,Object obj){
//		Long param = null;
//		if(obj!=null){
//			String objStr = objectToStringTrim(obj);
//			if("".equals(objStr)){
//				return null;
//			}
//			if(!isPositiveInteger(objStr)){
//				throw new InvalidParamException("Parameter ["+paramName+"] Must be an Integer ");
//			}
//			try
//			{
//				param = Long.valueOf(objStr);
//			}catch(NumberFormatException e)
//			{
//				throw new InvalidParamException("Parameter ["+paramName+"] is out of range of long ");
//			}
//		}
//		return param;
//	}
//	
//	/**
//	 * (把Object类型的list 转 成List<Integer>). <br/>
//	 * 如果传入的参数不是整型参数将其过滤掉
//	 */
//	@SuppressWarnings({"unchecked" })
//	public static List<Integer> objectListToIntegerList(Object obj){
//		List<Object> objList = (List<Object>) obj;
//		List<Integer> intList = new ArrayList<Integer>();
//		if(objList!=null && !objList.isEmpty()){
//			for(Object it : objList){
//				String val = objectToStringTrim(it);
//				if(isPositiveInteger(val)){
//					try
//					{
//						Integer param = null;
//						param = Integer.valueOf(val);
//						intList.add(param);
//					}catch(NumberFormatException e)
//					{
//						throw new InvalidParamException("Parameter  is out of range of int ");
//					}
//				}
//			}
//		}
//		
//		return intList.isEmpty()?null:intList;
//	}
//	
//	/**
//	 * (把Object类型的list 转 成List<Long>). <br/>
//	 * 如果传入的参数不是整型参数将其过滤掉
//	 */
//	@SuppressWarnings({"unchecked" })
//	public static List<Long> objectToLongList(Object obj){
//		List<Object> objList = (List<Object>) obj;
//		List<Long> intList = new ArrayList<Long>();
//		if(objList!=null && !objList.isEmpty()){
//			for(Object it : objList){
//				String val = objectToStringTrim(it);
//				if(isPositiveInteger(val)){
//					try
//					{
//						Long param = null;
//						param = Long.valueOf(val);
//						intList.add(param);
//					}catch(NumberFormatException e)
//					{
//						throw new InvalidParamException("Parameter  is out of range of long ");
//					}
////					intList.add(Long.valueOf(val));
//				}
//			}
//		}
//		return intList.isEmpty()?null:intList;
//	}
//	
//	/**
//	 * (把Object类型的list 转 成Long[]. <br/>
//	 * 如果传入的参数不是整型参数将其过滤掉
//	 */
//	@SuppressWarnings({"unchecked" })
//	public static Long[] objectListToLongList(Object obj){
//		List<Object> objList = (List<Object>) obj;
//		List<Integer> itList = objectListToIntegerList(objList);
//		if(itList == null || itList.isEmpty()){
//			return new Long[]{};
//		}
//		Long[] array = new Long[itList.size()];
//		for(int i=0;i<itList.size();i++){
//			array[i] = Long.valueOf(itList.get(i));
//		}
//		return array;
//	}
//	
//	
//	/**
//	 * 将HashMap转成Json串
//	 * 输入参数不能为空
//	 */
//	@SuppressWarnings("rawtypes")
//	public static String mapToJson(Map<String,Object> param) {
//        JSONObject json = new JSONObject();
//        if(param==null||param.isEmpty()){
//        	throw new InvalidParamException("参数为空，转Json时异常");
//        }
//        Iterator i = param.entrySet().iterator();
// 		while (i.hasNext()) {
// 			Map.Entry e = (Map.Entry) i.next();
// 			 json.put((String) e.getKey(), e.getValue());
// 		}
//		return json.toString();
//	}
//	
//	public static BasicParam paseBasicParam(HashMap params){
//		BasicParam param = new BasicParam();
//		Number cityIdNum = (Number)params.get("city_id"); 
//		int cityId = cityIdNum == null ? -1 : cityIdNum.intValue();
//		param.setCityId(cityId);
//		
//		Boolean isNeedSi = (Boolean)params.get("need_si");
//		boolean needSi = isNeedSi == null ? true : isNeedSi;
//		param.setNeedSi(needSi);
//		
//		Boolean isNeedCp = (Boolean)params.get("need_cp");
//		boolean needCp = isNeedCp == null ? true : isNeedCp;
//		param.setNeesCp(needCp);
//		
//		Boolean isNeedCiInfo = (Boolean)params.get("need_ci_info");
//		boolean needCiInfo = isNeedCiInfo == null ? true : isNeedCiInfo;
//		param.setNeedCiInfo(needCiInfo);
//		
//		Boolean isNeedSellLimit = (Boolean)params.get("need_sell_limit");
//		boolean needSellLimit = isNeedSellLimit == null ? true : isNeedSellLimit;
//		param.setNeedSellLimit(needSellLimit);
//		
//		Boolean isNeedStorage = (Boolean)params.get("need_storage");
//		boolean needStorage = isNeedStorage == null ? true : isNeedStorage;
//		param.setNeedStorage(needStorage);
//		
//		Boolean isNeedPurchase = (Boolean)params.get("need_purchase");
//		boolean needPurchase = isNeedPurchase == null ? true : isNeedPurchase;
//		param.setNeedPurchase(needPurchase);
//		
//		
//		Boolean isNeedFilterCp = (Boolean)params.get("need_filter_cp");
//		boolean needFilterCp = isNeedFilterCp == null ? false : isNeedFilterCp;
//		param.setNeedFilterCp(needFilterCp);
//		
//		Boolean isNeedPackage = (Boolean)params.get("need_package");
//		boolean needPackage = isNeedPackage == null ? false : isNeedPackage;
//		param.setNeedPackage(needPackage);
//		return param;
//	}
//
//	@SuppressWarnings("unchecked")
//	public static List<String> objectListToStringList(Object object) {
//		List<Object> objList = (List<Object>) object;
//		List<String> strList = new ArrayList<String>();
//		if(objList!=null&&!objList.isEmpty()){
//			for(Object o : objList){
//				String val = objectToStringTrim(o);
//				strList.add(val);
//			}
//		}
//		return strList.isEmpty()?null:strList;
//	}
//
//	public static Integer doublePriceToIntegerNotNull(String string, Object obj) {
//		if(obj==null){
//			throw new InvalidParamException("参数"+string+"不允许为空");
//		}
//		String value = String.valueOf(obj);
//		if(!isNumeric(value)){
//			throw new InvalidParamException("参数"+string+"必须是数字类型");
//		}
//		double d = Double.valueOf(value);
//		BigDecimal b = new BigDecimal(d);
//		double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//		int priceInput = (int) (f1*1000);
//		return priceInput;
//	}
//	
//	/**
//	 * List<Long> 转成List<Integer>
//	 * @param inList
//	 * @return
//	 */
//	 public static List<Integer> collLongToIntegerLst(List<Long> inList){
//	        List<Integer> iList =new ArrayList<Integer>(inList.size());
//	        CollectionUtils.collect(inList, 
//	                  new Transformer(){
//	                    public java.lang.Object transform(Object input){
//	                      return new Integer(String.valueOf(input));
//	                    }
//	                  } ,iList );
//	        return iList;
//	    }
//	 
//	 /**
//	  * List<Long> 转成List<Integer>
//	  * @param inList
//	  * @return
//	  */
//	  public static List<Long> collIntegerListToLongLst(List<Integer> inList){
//	         List<Long> iList =new ArrayList<Long>(inList.size());
//	         CollectionUtils.collect(inList, 
//	                   new Transformer(){
//	                     public java.lang.Object transform(Object input){
//	                       return new Long(String.valueOf(input));
//	                     }
//	                   } ,iList );
//	         return iList;
//	     }
//
//	/**
//	 * isInteger:(验证是否是Integer). <br/>
//	 * 
//	 * @return boolean
//	 */
//	public static boolean isInteger(String str) {
//		if (StringUtils.isBlank(str)) {
//			return false;
//		}
//		Pattern pattern = Pattern.compile("^-?[0-9]\\d*$");
//		boolean isNumber=pattern.matcher(str).matches();
//		if(!isNumber){
//			return false;			
//		}
//		BigInteger bigInteger=new BigInteger(str);
//		BigInteger max=new BigInteger(String.valueOf(Integer.MAX_VALUE));
//		BigInteger min=new BigInteger(String.valueOf(Integer.MIN_VALUE));
//		if(bigInteger.compareTo(max)==1||bigInteger.compareTo(min)==-1){
//			return false;			
//		}		
//		return true;
//	}
//	/**
//	 * isInteger:(验证是否是Integer). <br/>
//	 * 
//	 * @return boolean
//	 */
//	public static boolean isLong(String str) {
//		if (StringUtils.isBlank(str)) {
//			return false;
//		}
//		Pattern pattern = Pattern.compile("^-?[0-9]\\d*$");
//		boolean isNumber=pattern.matcher(str).matches();
//		if(!isNumber){
//			return false;			
//		}
//		BigInteger bigInteger=new BigInteger(str);
//		BigInteger max=new BigInteger(String.valueOf(Long.MAX_VALUE));
//		BigInteger min=new BigInteger(String.valueOf(Long.MIN_VALUE));
//		if(bigInteger.compareTo(max)==1||bigInteger.compareTo(min)==-1){
//			return false;			
//		}		
//		return true;
//	}
}




