package com.txyd.es;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.junit.After;
import org.junit.Before;
 
/**
 * @author huangfox
 * @date 2014年2月10日 下午3:27:43
 *
 */
public class ESClient {
 
  
    private static final String URL="192.168.133.164";
    private static final int PORT=9200;
    
    private static final String TRANSPORT_URL="127.0.0.1";
    private static final int TRANSPORT_PORT=9300;
    
    private static final String CLUSTER_NAME="txyd-cluster-name";
    private static final String NODE_NAME="txyd-node-name";
    
    
    private static final String INDEX="javaindex";//必须小写
    private static final String INDEX_TYPE="javatype";//必须小写
    private static final String SOME_ITEM_ID="AVKrMwMRUHrUKH-qgfiA";//某一条记录id
    
 
    private Client client;
	/**
	 * 初始化客户端连接
	 */
	@Before
	public void initESClient() {
		// 配置你的es,现在这里只配置了集群的名,默认是elasticsearch,跟服务器的相同
		Settings settings = Settings.settingsBuilder()
				.put("cluster.name",ESClient.CLUSTER_NAME)
				.put("node.name",ESClient.NODE_NAME)
				.build();
		// 这里可以同时连接集群的服务器,可以多个,并且连接服务是可访问的
		try {
//			client = TransportClient.builder().settings(settings).build()
//					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.26.40.74"),9300))
//					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("172.26.40.75"),9300));
			client = TransportClient.builder().settings(settings).build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ESClient.TRANSPORT_URL),ESClient.TRANSPORT_PORT));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("连接成功！");
	}

	@After
	public void closeESClient() {
		client.close();
		System.out.println("连接关闭！");
	}



	/**
	 * 创建索引
	 */
	private void createIndex() {
		List<User> list=new ArrayList<>();
		for(int i=0; i<20; i++){
			User user=new User();
			user.setId((long)(i));
			user.setName("姓名："+i);
//			user.setAge((int)(Math.random()*100));
			user.setAge(i*10+i);
			if(i%2==0){
				user.setDetail("个人描述为偶数:"+i);
			}else {
				user.setDetail("个人描述为奇数:"+i);
			}
			
			list.add(user);
			
		}
		for(User user:list){
			client.prepareIndex(ESClient.INDEX.toLowerCase(), ESClient.INDEX_TYPE.toLowerCase())
				.setSource(getBuilderJson(user))
				.execute().actionGet();
		}
		System.out.println("索引创建成功！");
	}
	private String getBuilderJson(User user){
		String json = "";
		try {
			XContentBuilder contentBuilder = XContentFactory.jsonBuilder().startObject();
			contentBuilder.field("id",user.getId());
			contentBuilder.field("name",user.getName());
			contentBuilder.field("age",user.getAge());
			contentBuilder.field("detail", user.getDetail());
			json = contentBuilder.endObject().string();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	/**
	 * 查询某个索引中的内容
	 */
	public void getIndex() {
		GetResponse res = client.prepareGet()
				.setIndex(ESClient.INDEX.toLowerCase())
				.setType(ESClient.INDEX_TYPE.toLowerCase())
				.setId("AVKrMwLnUHrUKH-qgfh5")
				.execute().actionGet();
		System.out.println(res.getSource());
	}
	/**
	 * 搜索索引
	 */
	public void search(){
		//创建查询索引
		SearchRequestBuilder searchRequestBuilder = client.prepareSearch(ESClient.INDEX.toLowerCase());
		//设置查询索引类型
		searchRequestBuilder.setTypes(ESClient.INDEX_TYPE.toLowerCase());
		//设置查询类型
		//1.SearchType.DFS_QUERY_THEN_FETCH = 精确查询
		// 2.SearchType.SCAN = 扫描查询,无序
		// 3.SearchType.COUNT = 不设置的话,这个为默认值,还有的自己去试试吧
		searchRequestBuilder.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
		//设置查询关键字
		// fieldQuery 这个必须是你的索引字段哦,不然查不到数据,这里我只设置两个字段 id ,title
//		searchRequestBuilder.setQuery(QueryBuilders.termQuery("id", "1454571194"));
//		searchRequestBuilder.setQuery("{\"query\":{\"match\":{\"id\":1}}}");
		searchRequestBuilder.setQuery("{\"query\":{\"match\":{\"id\":1},\"filtered\":{\"filter\":{\"range\":{\"age\":{\"gt\":1980}}}}}} ");
		// 设置查询数据的位置,分页用吧
		searchRequestBuilder.setFrom(0);
		// 设置查询结果集的最大条数
		searchRequestBuilder.setSize(60);
		// 设置是否按查询匹配度排序
		searchRequestBuilder.setExplain(true);
		// 最后就是返回搜索响应信息
		SearchResponse response = searchRequestBuilder.execute().actionGet();
//		System.out.println(response);
		
		//获取搜索文档的结果
		SearchHits searchHits = response.getHits();
		SearchHit[] hits = searchHits.getHits();
		for (SearchHit hit:hits) {
			Map<String, Object> result = hit.getSource();
			System.out.println(result);
		}
		System.out.println("查询索引完毕!");
	}
	
	/**
	 * 获取索引
	 */
	public void get(){
		GetResponse response = client.prepareGet(
					ESClient.INDEX.toLowerCase(), 
					ESClient.INDEX_TYPE.toLowerCase(), 
					ESClient.SOME_ITEM_ID)
				.setOperationThreaded(false)//单线程操作，他默认为true：多线程操作
				.execute().actionGet();
		Set<String> headers = response.getHeaders();
		System.out.println("请求头:"+headers);
		boolean exists = response.isExists();
		System.out.println("索引是否存在:"+exists);
		String sourceString = response.getSourceAsString();
		System.out.println("索引内容:"+sourceString);
		String id = response.getId();
		System.out.println("索引id:"+id);
		boolean sourceEmpty = response.isSourceEmpty();
		System.out.println("索引的内容是否为空:"+sourceEmpty);
	}
	/**
	 * 删除索引
	 */
	public void delete(){
		DeleteResponse response = client.prepareDelete(
				ESClient.INDEX.toLowerCase(), 
				ESClient.INDEX_TYPE.toLowerCase(), 
				ESClient.SOME_ITEM_ID)
				.execute().actionGet();
		boolean isFound = response.isFound();
		System.out.println("返回索引是否存在，存在则删除:"+isFound);//
		
	}
 
	public static void main(String args[]){
		System.out.println("ES集群连接测试");
		ESClient esc = new ESClient();
		esc.initESClient();
		esc.createIndex();
//		esc.getIndex();
		esc.search();
//		esc.get();
//		esc.delete();
		esc.closeESClient();
	}
 
}