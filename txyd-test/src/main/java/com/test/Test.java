package com.test;


import net.sf.jsqlparser.statement.select.OrderByElement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.WithItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Test {
	
	public static List<OrderByElement> extraOrderBy(SelectBody selectBody) {
		if ((selectBody instanceof PlainSelect)) {
			List<OrderByElement> orderByElements = ((PlainSelect) selectBody).getOrderByElements();
			((PlainSelect) selectBody).setOrderByElements(null);
			return orderByElements;
		}
		if ((selectBody instanceof WithItem)) {
			WithItem withItem = (WithItem) selectBody;
			if (withItem.getSelectBody() != null) {
				return extraOrderBy(withItem.getSelectBody());
			}
		} else {
			SetOperationList operationList = (SetOperationList) selectBody;
			if ((operationList.getSelects() != null) && (operationList.getSelects().size() > 0)) {
				List<SelectBody> plainSelects = operationList.getSelects();
				List<OrderByElement> orderByElements = ((PlainSelect) plainSelects.get(plainSelects.size() - 1)).getOrderByElements();
				((PlainSelect) plainSelects.get(plainSelects.size() - 1)).setOrderByElements(null);
				return orderByElements;
			}
		}
		return null;
	}
	
	public static void checkSql() {
		String sqlOld = "";
		//sql+="SELECT schema_name,comments from (select * from tableName t where t.id=? and t.name=? order by t.id) r where r.id=? and r.name=? order by r.id   ";
		sqlOld += "SELECT\t\n";
		sqlOld += "		\n ";
		sqlOld += "	     \n";
		sqlOld += "		schema_name,comments\n";
		sqlOld += "	     \n";
		sqlOld += "	 \n";
		sqlOld += "		 from\n";
		sqlOld += "	     \n";
		sqlOld += "	    (\n";
		sqlOld += "	     SELECT\n";
		sqlOld += "				r.schema_name,\n";
		sqlOld += "				concat( \n";
		sqlOld += "					'DEFAULT_CHARACTER_SET_NAME:',	\n";
		sqlOld += "					ifnull(r.DEFAULT_CHARACTER_SET_NAME,'') ,\n";
		sqlOld += "					';DEFAULT_COLLATION_NAME',\n";
		sqlOld += "					ifnull(r.DEFAULT_COLLATION_NAME,'')\n";
		sqlOld += "				) as comments\n";
		sqlOld += "				\n";
		sqlOld += "			FROM\n";
		sqlOld += "				information_schema.SCHEMATA r\n";
		sqlOld += "			WHERE\n";
		sqlOld += "				r.SCHEMA_NAME = ?\n";
		sqlOld += "\n";
		sqlOld += "	    )t\n";
		sqlOld = sqlOld.trim();
		
		String[] sqlOldArray = sqlOld.split("'");
		
		
		String sqlNew = sqlOld.replace("\t", " ").replace("\n", " ").replace("\r", " ").replace("*", "* ").replace(")", ") ");
		while (sqlNew.contains("  ")) {
			sqlNew = sqlNew.replace("  ", " ");
		}
		System.out.println(sqlNew);

//		String sqlWithOutOrderBy=sqlOld;
//		int lastIndexOfOrderBy=sqlOld.toLowerCase().lastIndexOf("order by");
//		if(lastIndexOfOrderBy>-1){
//
//		}
//		String sqlNewLower=sqlNew.toLowerCase();
//
//		String sqlCount="";
//
//		if ((sqlNewLower.startsWith("select ")||sqlNewLower.startsWith("select*")||sqlNewLower.startsWith("select("))
//				&&!sqlNewLower.contains("union all")){
//
//		}else{
//			sqlCount="select count(1) from ("+sqlOld+")";
//		}
		
		
	}
	
	
	public static void checkPattern() {
		Pattern SELECT_PATTERN = Pattern.compile("^select\\s+(\\b[\\s\\S]+?)\\s*\\bfrom\\b([\\S\\s]+?)(\\border\\s+by[\\s\\S]+)$",
				Pattern.CASE_INSENSITIVE);
		String distinct = "DISTINCT";
		String str = "";
		str += "SELECT schema_name,comments from (select * from tableName t where t.id=? and t.name=? order by t.id) r where r.id=? and r.name=? order by r.id   ";
//		str+="SELECT";
//		str+="		 ";
//		str+="	     ";
//		str+="		schema_name,comments";
//		str+="	     ";
//		str+="	 ";
//		str+="		 from";
//		str+="	     ";
//		str+="	    (";
//		str+="	     SELECT";
//		str+="				r.schema_name,";
//		str+="				concat( ";
//		str+="					'DEFAULT_CHARACTER_SET_NAME:',	";
//		str+="					ifnull(r.DEFAULT_CHARACTER_SET_NAME,'') ,";
//		str+="					';DEFAULT_COLLATION_NAME',";
//		str+="					ifnull(r.DEFAULT_COLLATION_NAME,'')";
//		str+="				) as comments";
//		str+="				";
//		str+="			FROM";
//		str+="				information_schema.SCHEMATA r";
//		str+="			WHERE";
//		str+="				r.SCHEMA_NAME = ?";
//		str+="";
//		str+="	    )t";
		Matcher matcher = SELECT_PATTERN.matcher(str);
		if (matcher.find()) {
			String fields = matcher.group(1);
			String fromExpression = matcher.group(2);
			
			StringBuilder sbSql = new StringBuilder(str.length() + 20);
			sbSql.append("select ");
			fields = fields.trim();
			if (fields.length() > distinct.length()
					&& distinct.equalsIgnoreCase(fields.substring(0, distinct.length()))) {
				sbSql.append("COUNT ({columns} as cn ) ".replace("{columns}", fields));
			} else {
				sbSql.append("COUNT(1) as cn");
			}
			sbSql.append(" from ");
			sbSql.append(fromExpression);
			System.out.println(sbSql);
		} else {
			System.out.println("匹配不上");
		}
		
	}
	
	public static void checkPlanTimes(List<Integer> planTimes) {
		for (int i = 0; i < planTimes.size(); i += 2) {
			if (i + 2 < planTimes.size()) {
				int startTime = planTimes.get(i);
				int endTime = planTimes.get(i + 1);
				for (int j = i + 2; j < planTimes.size(); j += 2) {
					if (j + 2 < planTimes.size()) {
						if (startTime >= planTimes.get(j) && startTime <= planTimes.get(j + 1)) {
							System.out.println("计划时间不能交叉! ");
						}
						if (endTime >= planTimes.get(j) && endTime <= planTimes.get(j + 1)) {
							System.out.println("计划时间不能交叉! ");
						}
					}
				}
			}
		}
	}
	
	static final int hash(Object key) {
		int h;
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//		return key.hashCode();
	}
	
	public static <T> List<List<T>> splite(List<T> all, int stepSize) {
		List<List<T>> list = new ArrayList<>();
		if (all == null || all.size() == 0 || stepSize < 1) {
			return list;
		}
		int size = all.size();
		int step = size / stepSize + 1;
		for (int i = 0; i < step; i++) {
			int start = i * stepSize;
			int end = start + stepSize;
			if (start < size) {
				if (end > size) {
					end = size;
				}
				List<T> item = all.subList(start, end);
				if (item != null && item.size() > 0) {
					list.add(item);
				}
			}
		}
		return list;
	}
	
	public static void main(String[] args) throws Exception {
		{
			ArrayList<Integer> list = new ArrayList<>();
			list.addAll(new HashMap<String,Integer>().values());
			System.out.println(list);
		}
		{
//			Map<String,String>  map1=new LinkedHashMap<String,String>(){{
//				for(int i=0;i<10;i++){
//					put("key_"+(i+1),"value_"+(i+1));
//				}
//			}};
//			Map<String,String>  map2=new LinkedHashMap<String,String>(){{
//				for(int i=5;i<15;i++){
//					put("key_"+(i+1),"value_"+(i+1));
//				}
//			}};
//			Set<String> set1=new HashSet<>(map1.keySet());
//			set1.retainAll(map2.keySet());
//			System.out.println(set1);
//			System.out.println(map1);
//			System.out.println(map2);
		}
		{
//			BigDecimal b1=new BigDecimal("100.0");
//			BigDecimal b2=new BigDecimal("100.00");
//			System.out.println(b1.equals(b2));
//			System.out.println(b1.compareTo(b2));
		}
		
		{
//			System.out.println(0.1*3);
//			System.out.println(0.1*3.0);
//			System.out.println(new BigDecimal("0.1").multiply(new BigDecimal("3")).toString());
			{
				{
//					List<BigDecimal> list = new ArrayList<BigDecimal>(){{
//						add(new BigDecimal("2"));
//						add(new BigDecimal("5"));
//						add(new BigDecimal("1"));
//						add(new BigDecimal("7"));
//						add(new BigDecimal("9"));
//						add(new BigDecimal("8"));
//					}};
//					System.out.println(list);
//					Collections.sort(list, new Comparator<BigDecimal>() {
//						@Override
//						public int compare(BigDecimal o1, BigDecimal o2) {
//							return o1.compareTo(o2);
//						}
//					});
//					System.out.println(list);
					
				}
				{
//					Set<String> set = new HashSet<>();
//					set.add(new String("dd"));
//					System.out.println(set.contains(new String("dd")));
				}
				{
//					double g= 2.4;
//					System.out.println(g* 1);
//					System.out.println(g* 1.0);
//					System.out.println(g* 0.1);
				}
//				double g= 12.35;
//				{
//					System.out.println("################################");
//					System.out.println(Double.toHexString(g));
//					System.out.println(Double.toHexString(0.1));
//					System.out.println(Double.toHexString(15));
//					System.out.println(Integer.toHexString(15));
//					System.out.println("################################");
//					BigDecimal bigG=new BigDecimal(g).setScale(1, BigDecimal.ROUND_HALF_UP); //期望得到12.4
//					System.out.println("test G:"+bigG.doubleValue());//test G:12.3
//				}
//				{
//					BigDecimal bigG=new BigDecimal(g+"").setScale(1, BigDecimal.ROUND_HALF_UP); //期望得到12.4
//					System.out.println("test G:"+bigG.doubleValue());//test G:12.4
//				}
			}
		}
		{
//			Set<Long> set = new HashSet<>();
//			set = Stream.<Long>iterate(0L,e->++e).limit(2000).collect(Collectors.<Long>toSet());
//			System.out.println(JsonUtil.toJson(set));
//			System.out.println(set.contains(1000L));
		}
		{
//			int length =5;
//			List<User> userList = new ArrayList<>();
//			Set<User> setList = new HashSet<>();
//			for (int i = 0; i < length; i++) {
//				int index = i;
//				User user = new User();
//				user.setId(index);
//				user.setUserName("un" + index);
//				user.setPassword("password" + index);
//				userList.add(user);
//				setList.add(user);
//			}
//			System.out.println(JsonUtil.toJson(userList));
//			System.out.println(JsonUtil.toJson(setList));
		}
		{
//			System.out.println(Application.GWS.ordinal());
//			System.out.println(Application.GWS1.name());
			
		}
		{
//			Scanner scanner=new Scanner(System.in);
//			String in = scanner.nextLine();
//			while (!in.equals("end")) {
//				if(StringUtils.isInteger(in.trim())){
//					int length = StringUtils.getInteger(in.trim());
//					if(length>0){
//						List<User> userList = new ArrayList<>();
//						{
//							long statTime = TimeUtil.getMillis();
//							for (int i = 0; i < length; i++) {
//								User user = new User();
//								user.setId(i);
//								user.setUserName("un" + i);
//								user.setPassword("password" + i);
//								userList.add(user);
//							}
//							long endTime = TimeUtil.getMillis();
//							System.out.println("赋值耗时{time}ms".replace("{time}",(endTime-statTime)+""));
//
//						}
//						// 通过Lambda取出User里面id的值
//						{
//							long statTime = TimeUtil.getMillis();
//							List<Integer> idList = userList.stream().map(User::getId).collect(Collectors.toList());
//							long endTime = TimeUtil.getMillis();
//							System.out.println("lambda耗时{time}ms".replace("{time}",(endTime-statTime)+""));
//							if(idList.size()<10){
//								System.out.println(idList);
//							}else{
//								System.out.println(idList.size());
//							}
//						}
//						// 通过Lambda foreach取出User里面id的值
//						{
//							List<Integer> idList = new ArrayList();
//							long statTime = TimeUtil.getMillis();
//							userList.stream().forEach(
//									e-> idList.add(e.getId())
//							);
//							long endTime = TimeUtil.getMillis();
//							System.out.println("lambda foreach耗时{time}ms".replace("{time}",(endTime-statTime)+""));
//							if(idList.size()<10){
//								System.out.println(idList);
//							}else{
//								System.out.println(idList.size());
//							}
//						}
//						// 普通方法
//						{
//							List idList = new ArrayList<>();
//							long statTime = TimeUtil.getMillis();
//							for (User user : userList) {
//								idList.add(user.getId());
//							}
//							long endTime = TimeUtil.getMillis();
//							System.out.println("for耗时{time}ms".replace("{time}",(endTime-statTime)+""));
//							if(idList.size()<10){
//								System.out.println(idList);
//							}else{
//								System.out.println(idList.size());
//							}
//						}
//						// 普通方法
//						{
//							List idList = new ArrayList<>();
//							Iterator<User> iterator = userList.iterator();
//							long statTime = TimeUtil.getMillis();
//							while(iterator.hasNext()){
//								idList.add(iterator.next().getId());
//							}
//							long endTime = TimeUtil.getMillis();
//							System.out.println("iterator耗时{time}ms".replace("{time}",(endTime-statTime)+""));
//							if(idList.size()<10){
//								System.out.println(idList);
//							}else{
//								System.out.println(idList.size());
//							}
//						}
//
//
//					}
//
//				}
//				in = scanner.nextLine();
//
//			}
		}
		{
//			List<Integer> all = new ArrayList<>();
//			for (int i = 0; i < 21; i++) {
//				all.add(i + 1);
//			}
//			{
//				List<List<Integer>> list = splite(all, 9);
//				System.out.println(list);
//			}
		}
		{
//            HashSet<Long> set = new HashSet<>();
//            for(int i=0;i<100;i++){
//                set.add(i+10000L);
//            }
//            System.out.println(set.contains(10000+12));
//            System.out.println(set.contains(10000+12L));
//            System.out.println(set.contains(10000L+12L));
		}
		{
//            Set<Object> setTemp= new HashSet<Object>(){{add("sss");add("sss2");}};
//            Set<Number> setTemp2= new HashSet<Number>(){{add(1);add(2);}};
//            Set<Number> set = Collections.checkedSet(new HashSet<>(), Number.class);
//            set = setTemp2;
			
		}
		{
//            Comparator<Son> comparator=(e1,e2)->e1.getId()-e2.getId();
//            Map<Son,String> map=new TreeMap<>(comparator);
//            Set<Son> set = new TreeSet<>(comparator);
		}
		{
//			InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream("");
//			Properties properties= new Properties();
//			properties.load(in);
//			System.out.println(properties.getProperty("key"));
		}
		{
//			Deque<Integer> deque=new ArrayDeque<>();
//			for(int i=0;i<10;i++){
//				deque.push(i);
//			}
//			System.out.println(deque);
//			System.out.println(deque.peek());
		}
		{
//			Stack<Integer> stack=new Stack<>();
//			for(int  i=0; i<10;i++){
//				stack.push(i);
//			}
//			System.out.println(stack);
//			System.out.println(stack.search(0));
//			System.out.println(stack.get(0));
//			System.out.println(stack.peek());
		}
		{
//			Vector<String> vector=new Vector<>();
//			vector.add("a");
//			vector.add("b");
//			vector.add("c");
//			for(String e:vector){
//				System.out.println(e);
//			}
//			for(Enumeration<String> e =vector.elements();e.hasMoreElements();){
//				System.out.println(e.nextElement());
//			}
			
		}
		{
//			LinkedList<String> lkList=new LinkedList<>();
//			for (int  i=0;  i<26;i++) {
//				lkList.add(new Character((char) (i+'a')).toString());
//				lkList.add(new Character((char) (i+'A')).toString());
//			}
//			lkList.add("C");
//			System.out.println(lkList);
//			lkList.remove("C");
//			System.out.println(lkList);
//			lkList.remove("C");
//			System.out.println(lkList);
//			System.out.println(lkList.get(0));
		}
		{
//			Set<Integer> set=new HashSet<>();
//			set.add(1011);
//			set.add(1109);
//			set.add(1089);
//			set.add(1025);
//			set.add(1010);
//			set.add(1014);
//			set.add(1026);
//			set.add(1055);
//			set.add(1096);
//			set.add(1080);
//
//			for(Integer item :set){
//				System.out.print(item+"\t");
//
//			}
//
//			System.out.println("\n*********************************************");
		}
		{
//			Random random=new Random();
//			int length=10;
//			int start=1000;
//			Set<Integer> setRandom=new HashSet<>();
//			for(int i=start;i<start+length;i++){
//				setRandom.add(start+random.nextInt(length+100));
//			}
//			for(Integer item:setRandom){
//						System.out.print(item+("/{hashcode}/{hash}\t"
//								.replace("{hashcode}",item.hashCode()+"")
//								.replace("{hash}", hash(item)+"")));
//			}
//			System.out.println();
			
		}
		{
//			int length=30;
//			Set<Integer> set2=new HashSet<>();
//			set2.add(2);
//			set2.add(1);
//			set2.add(4);
//			set2.add(3);
//
//			Set<Integer> set3=new HashSet<>();
//			Set<Integer> set=new HashSet<>();
//			Set<Integer> setRandom=new HashSet<>();
//			{
//
//				for(int i=0;i<length;i++){
//					Integer temp=(int)(Math.random()*length);
//					setRandom.add(temp);
//					set.add(length-1-i);
//					set3.add(i);
//
//					System.out.print(temp+("/{hashcode}\t"
//							.replace("{hashcode}",temp.hashCode()+"")));
//				}
//				System.out.println();
//				Scanner scanner=new Scanner(System.in);
//				String in=scanner.nextLine();
//				while (!in.equals("end")){
//					for(Integer item:set){
//						System.out.print(item+("/{hashcode}\t"
//								.replace("{hashcode}",item.hashCode()+"")));
//					}
//					System.out.println();
//					for(Integer item:set2){
//						System.out.print(item+("/{hashcode}\t"
//								.replace("{hashcode}",item.hashCode()+"")));
//					}
//					System.out.println();
//					for(Integer item:set3){
//						System.out.print(item+("/{hashcode}\t"
//								.replace("{hashcode}",item.hashCode()+"")));
//					}
//					System.out.println();
//					for(Integer item:setRandom){
//						System.out.print(item+("/{hashcode}\t"
//								.replace("{hashcode}",item.hashCode()+"")));
//					}
//					in=scanner.nextLine();
//
//				}
//			}
			{

//				for(int i=0;i<length;i++){
//					Integer temp=(int)(Math.random()*length);
//					setRandom.add(temp);
//					set.add(length-1-i);
//					set3.add(i);
//
//					System.out.print(temp+("/{hashcode}/{hash}\t"
//							.replace("{hashcode}",temp.hashCode()+"")
//							.replace("{hash}", hash(temp)+"")));
//				}
//				System.out.println();
//				Scanner scanner=new Scanner(System.in);
//				String in=scanner.nextLine();
//				while (!in.equals("end")){
//					for(Integer item:set){
//						System.out.print(item+("/{hashcode}/{hash}\t"
//								.replace("{hashcode}",item.hashCode()+"")
//								.replace("{hash}", hash(item)+"")));
//					}
//					System.out.println();
//					for(Integer item:set2){
//						System.out.print(item+("/{hashcode}/{hash}\t"
//								.replace("{hashcode}",item.hashCode()+"")
//								.replace("{hash}", hash(item)+"")));
//					}
//					System.out.println();
//					for(Integer item:set3){
//						System.out.print(item+("/{hashcode}/{hash}\t"
//								.replace("{hashcode}",item.hashCode()+"")
//								.replace("{hash}", hash(item)+"")));
//					}
//					System.out.println();
//					for(Integer item:setRandom){
//						System.out.print(item+("/{hashcode}/{hash}\t"
//								.replace("{hashcode}",item.hashCode()+"")
//								.replace("{hash}", hash(item)+"")));
//					}
//					in=scanner.nextLine();
//
//				}
			}
			
		}
		{
//			double dd=3.0*0.1;
//			double tt=0.3;
//			System.out.println(3*0.1==0.3);
//			System.out.println(3.0*0.1==0.3);
//			System.out.println(3.0*0.2==0.6);
//			System.out.println(dd==tt);
		}
		{
//			System.out.println(Class.forName("java.lang.Integer"));
		}
		{
//			int [] arr=new int[]{8,2,1,0,3};
//			int[] index=new int[]{2,0,3,2,4,0,1,3,2,3,3};
//			String tel="";
//			for (int i:index) {
//				tel+=arr[i];
//			}
//			System.out.println(tel);
			
		}
		{
//			String sql="SELECT * FROM `t_lottery_activity` r where  r.`name` like '%活动%' order by (id+100*random()) asc";
//			sql="SELECT schema_name,comments   from\\n (select * from tableName t where t.id=? and t.name like '%活\\'动%' order by t.name desc) r where r.id=? and r.name=? order by r.id asc  ";
//			{
//				String[] sqlLine=sql.split("\n");
//
//				boolean isOrderByExists=false;
//				int orderbyLineNum=0;
//				for(int i=0;i<sqlLine.length;i++){
//					String line=sqlLine[i];
//					if(line.toLowerCase().contains("order")){
//
//					}
//
//				}
//			}
//
//			String[] sqlArr=sql.split("\\\\'");
//			for(String string:sqlArr){
//				System.out.println(string);
//			}
		}
		{
//			String sql="SELECT * FROM `t_lottery_activity` r where  r.`name` like '%活动%' order by (id+100*random()) asc";
//			sql="SELECT schema_name,comments from (select * from tableName t where t.id=? and t.name like '%活\'动%' order by t.id) r where r.id=? and r.name=? order by r.id   ";
//			Statement stmt = CCJSqlParserUtil.parse(sql);
//		    Select select = (Select) stmt;
//		    SelectBody selectBody = select.getSelectBody();
//		    List<OrderByElement> list=Test.extraOrderBy(selectBody);
////		    list.stream().toArray(String[]::new);
//		    list.forEach(e->{System.out.println(e.toString());});
//		    System.out.println(selectBody.toString());
		}
		{
/*			System.out.println("\n\t".length());
            String[] strings = "select * from tt r where r.name like  'xxxx\''".split("'");
			int i=0;
			System.out.println(strings.length+":"+Math.random());
			System.out.println("++++++++++++++++");
			for(String str:strings){
				System.out.println(i+++":"+str);
			}
			System.out.println("++++++++++++++++");*/
		}
		{
//			Test.checkSql();
			
		}
		{
//			List<Integer> times=new ArrayList<>();
//			long startTime=System.currentTimeMillis();
//			for(int i=0;i<396;i++){
//				times.add(396*i);
//				times.add(396*(i+1)-1);
//			}
//			Test.checkPlanTimes(times);
//			long endTime=System.currentTimeMillis();
//			System.out.println(endTime-startTime);
//			System.out.println(times.toString());
			
		}
		{
//			String url="121212ddd/ddddsdfsfd/tt?id=1";
//			int index=url.indexOf("?");
//			if(index>0){
//				url=url.substring(0, index);
//			}
//			int lineIndex=url.lastIndexOf("/");
//			if(lineIndex>0){
//				url=url.substring(lineIndex+1, url.length());
//			}

//			System.out.println(url);
//			String str="1122\n333\n444\n";
//			for(String s:str.split("\n")){
//				System.out.println(s);
//
//			}
//			Set<Integer> levels=new HashSet<>();
//			levels.add(Integer.valueOf(1));
//			levels.add(Integer.valueOf(2));
//			levels.add(Integer.valueOf(3));
//			levels.add(Integer.valueOf(4));
//			System.out.println(levels.toString());
			
			
		}
		{
//			Set<Integer> set =new HashSet<>();
//			for(int i=0;i<100;i++){
//	    		int tmp = (int)(1+Math.random()*(3-1+1));
//	    		set.add(tmp);
//	    		System.out.println(tmp);
//			}
//    		System.out.println(set);
		}
		{
//			String str="1122";
//			Integer i=Integer.valueOf(str);
//			System.out.println();
		}
		{
//			String[] arrayLocal=new String[]{
//					"id",
//					"city_id"
//			};
//			String[] arrayRemote=new String[]{
//					"sku_desc"
//
//
//			};
//			Set<String> setLocal=new HashSet<>();
//			setLocal.addAll(Arrays.asList(arrayLocal));
//
//
//			Set<String> setRemote=new HashSet<>();
//			setRemote.addAll(Arrays.asList(arrayRemote));
//
////			System.out.println(setLocal.removeAll(setRemote));
////			System.out.println(setLocal);
//
//			System.out.println(setRemote.removeAll(setLocal));
//			System.out.println(setRemote);
//
////			System.out.println(setLocal.containsAll(setRemote));
////			System.out.println(setRemote.containsAll(setLocal));
			
		}
		{
//			System.out.println(Integer.MAX_VALUE);
//			System.out.println(Long.MAX_VALUE);
//			System.out.println(System.getProperty("java.io.tmpdir"));
//			for(int i=0;i<10;i++){
//				System.out.println(new Random().nextInt(10000));
//
//			}
//
		}
		{
//			File dir=new File("G:/image1/image1");
//
//			for(File file:dir.listFiles()){
//				for(File fileTemp:file.listFiles()){
//					String fileName=fileTemp.getName();
////					System.out.println(fileTemp.getCanonicalPath()+":"+fileName);
//					System.out.println(fileTemp.getParentFile().getAbsolutePath().replaceAll("\\\\", "/")+"/"+fileName);
//					fileTemp.renameTo(new File(fileTemp.getParentFile().getAbsolutePath().replaceAll("\\\\", "/")+"/"+"mall-lottery-choujiang22-"+fileName));
//					
//				}
////				String fileName=file.getName();
////				System.out.println("<script src=\""+fileName+"\"/></script> ");
////				
////				file.renameTo(new File("D:/image/mall-lottery-choujiang-"+fileName));
////				
////				System.out.println("<img width='100px' height='50px' src='http://img.xxxxxxxxx.com/mall-lottery-choujiang-"+file.getName()+"' />");
//				
//			}
		}
		{
//			try{
//				double e=1/0;
//				
//			}catch(Exception e){
//				System.out.println(e.getMessage());
//				System.out.println("#####################");
//				e.printStackTrace(System.out);
//				System.out.println("#####################");
//				System.out.println(e.getStackTrace().toString());
//				System.out.println("#####################");
//				System.out.println(e.getLocalizedMessage());
//				System.out.println("#####################");
//				
//			}
			
		}
		{
//			System.out.println(Integer.MAX_VALUE);
		}
		
		{
//			String[] strArray=new String[1];
//			List<String> strList=new ArrayList<>();
//			strList.add("1");
//			strList.add("2");
//			strList.add("3");
//			for(String str:strList.toArray(strArray)){
//
//				System.out.println(str);
//			}
		}
		{
//			String comments="";
//
//			comments+="/**\n";
//			comments+=" *\n";			
//			comments+=" *数据库类型：{databaseType}\n";
//			comments+=" *表所属schema：{tableSchema}\n";
//			comments+=" *表所属用户：{tableOwner}\n";
//			comments+=" *表名称：{tableName}\n";
//			comments+=" *表注释：{comments}\n";
//			comments+=" *类型：{tableType}\n";
//			comments+=" *@author：{author}\n";
//			comments+=" */";
//			for(String str :comments.split("\n")){
//				System.out.println(str);
//				
//			}
		}
		{
//			String str="12.3";
//			System.out.println(str.substring(str.lastIndexOf(".")));
		}
		
		
	}
	
}
