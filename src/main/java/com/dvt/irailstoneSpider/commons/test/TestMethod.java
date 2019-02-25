package com.dvt.irailstoneSpider.commons.test;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.junit.Test;

import com.dvt.irailstoneSpider.business.main.dto.CreateResult;
import com.dvt.irailstoneSpider.business.main.dto.Parameter;
import com.dvt.irailstoneSpider.business.main.dto.SerialCodeQueryResult;
import com.dvt.irailstoneSpider.business.main.dto.SerialGroupQueryResult;
import com.dvt.irailstoneSpider.commons.utils.HttpUtils;
import com.dvt.irailstoneSpider.commons.utils.JsonUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public class TestMethod {
	//@Test
	public void test(){
		Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:test.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	}
	
	//@Test
	public void test2() throws Exception{
		final String URL = "http://localhost:8069"; 
	    //final String DB = "hospital-mh";  
	    //final int USERID = 1;  
	    //final String PASS = "123456";  
	    List<String> emptyList = Lists.newArrayList();
		
	    XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();  
		XmlRpcClient client = new XmlRpcClient();
		
	    config.setServerURL(new URL(String.format("%s/xmlrpc/2/common", URL)));  
	    client.setConfig(config);
		Object obj = client.execute(config, "authenticate", Arrays.asList(
        "hospital-mh", "admin", "123456", Maps.newHashMap()));
		 
        
        if(obj != null){
        	System.out.println(obj.toString());
        }
	}
	
//	@Test
//	public void testmethod() throws IOException{
//		Map<String,String> params = Maps.newHashMap();
//		params.put("result", "{xxxx:'12312312'}");
//		String reply = HttpHelper.startPost("http://localhost:8069/messageTest", params);
//		System.out.println(reply);
//	}
	private final static ExecutorService pool;
	private final static String WAIT_TIME = "60";
	static{
		pool = Executors.newFixedThreadPool(Integer.valueOf("2"));
	}
	
	@Test
	public void test1() throws Exception{
//		String PRODUCT_CODE = "256477000025";
//		String CREATE_CID = "8a8080905d9515f2015d95f5f03a0002";
//		List<Parameter> parameters = ImmutableList.of(
//				new Parameter("CREATE_CID", CREATE_CID),
//				new Parameter("PRODUCT_CODE", "256477000025")
//				);
		List<Parameter> parameters = ImmutableList.of(
				new Parameter("ORDER_ID", "933e19b1-ca09-4cbf-9148-cb3ec84cfbc9", null)
				);
		String parameterJson = JsonUtils.JavaBeanToJson(parameters);		
		System.out.println(parameterJson);
		
		
		String url = "http://www.irailstone.com/RnumberModule/RNUMBER_PRODUCT_SN_ORDER/SubmitFormPro?KeyValue=";
		Map<String,String> header = ImmutableMap.<String, String>builder()
				.put("Content-Type","application/x-www-form-urlencoded; charset=UTF-8")
				.put("Accept","application/json, text/javascript, */*; q=0.01")
				.put("Accept-Encoding", "gzip, deflate")
				.put("Accept-Language", "zh-CN,zh;q=0.9")
				.put("Cache-Control", "no-cache")
				.put("Connection", "keep-alive")
				.put("Host", "www.irailstone.com")
				.put("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
				.build();
		Map<String,Object> formData = ImmutableMap.<String, Object>builder()
				.put("PRODUCT_CODE", "256477000025")
				.put("PRODUCT_NAME", "256477000025 - 动车组闸片") //表示是否是搜索请求
				//.put("nd", "1527040388821") //表示已经发送请求的次数
				.put("ID", "&nbsp;") // 表示请求行数
				.put("CID", "8a8080905d9515f2015d95f5f03a0002")  // 表示请求页码
				.put("IS_AFTER_SALE", "0")
				.put("SERIAL_TYPE", "0")
				.put("MAKE_NUM", "1")
				.put("ORDER_TIME", "2018-05")
				.put("START_NUM", "256477000025180500025")
				.put("END_NUM", "256477000025180500025")
				.put("SUPPLEMENT_INFO", "ceshi2")
				.put("REMARK", "remark2")
				.build();
		String result = HttpUtils.doPost(url, formData, header);
		System.out.println(result);
		CreateResult cr = JsonUtils.jsonToJavaBean(result, CreateResult.class);
		System.out.println(cr.getMessage());
		//return snResult;
	}
	
	
//	public void testmethod(int i) throws Exception{
//		
//		Thread mainThread = Thread.currentThread();//主线程
//		
//		System.out.println("我是消费线程"+i);
//		NewSaxServiceImpl saxService = new NewSaxServiceImpl();
//		
//		String ewmJson = "{\"kjje\": \"332952\", \"fphm\": \"10014863\", \"kprq\": \"20170816\", \"fpdm\": \"6100171320\"}";
//		saxService.setEwmJson(ewmJson);
//		Future<String> future = pool.submit(saxService);
//		try {  
//			String backJson = future.get(Integer.valueOf(WAIT_TIME), TimeUnit.SECONDS);  
//			//invokeOdooInPost(backJson, index, message);
//        } catch (InterruptedException e) {
//        	System.out.println("InterruptedException 超时10秒");
//        	future.cancel(true);
//        	throw new TimeoutException("获取超时"+WAIT_TIME+"秒");
//        } catch (ExecutionException e) {
//        	System.out.println("ExecutionException 超时10秒");
//        	future.cancel(true);
//        	throw new TimeoutException("获取超时"+WAIT_TIME+"秒");
//        } catch (TimeoutException e) {  
//        	System.out.println("TimeoutException 超时10秒");
//        	future.cancel(true); 
//        	throw new TimeoutException("获取超时"+WAIT_TIME+"秒");
//        }
//		while(!future.isDone()&&!future.isCancelled()){
//			mainThread.sleep(1000);
//		}
//		System.out.println("1111");   
//	}
}
