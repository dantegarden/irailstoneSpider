package com.dvt.irailstoneSpider.business.main.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.beust.jcommander.internal.Lists;
import com.dvt.irailstoneSpider.business.main.api.ChaoJiYing;
import com.dvt.irailstoneSpider.business.main.dto.CreateResult;
import com.dvt.irailstoneSpider.business.main.dto.CreateSNParam;
import com.dvt.irailstoneSpider.business.main.dto.Maxsn;
import com.dvt.irailstoneSpider.business.main.dto.Parameter;
import com.dvt.irailstoneSpider.business.main.dto.SerialCode;
import com.dvt.irailstoneSpider.business.main.dto.SerialCodeGroup;
import com.dvt.irailstoneSpider.business.main.dto.SerialCodeQueryResult;
import com.dvt.irailstoneSpider.business.main.dto.SerialGroupQueryResult;
import com.dvt.irailstoneSpider.business.main.dto.SerialNumberResult;
import com.dvt.irailstoneSpider.business.main.dto.YzmResult;
import com.dvt.irailstoneSpider.business.main.service.IrailStoneService;
import com.dvt.irailstoneSpider.business.main.utils.SnHttpUtils;
import com.dvt.irailstoneSpider.commons.utils.CommonHelper;
import com.dvt.irailstoneSpider.commons.utils.HttpUtils;
import com.dvt.irailstoneSpider.commons.utils.JsonUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;

@Service
public class IrailStoneServiceImpl implements IrailStoneService{
	private static final Logger logger = LoggerFactory.getLogger(IrailStoneServiceImpl.class);
	
	private static final String CREATE_CID = "8a8080905d9515f2015d95f5f03a0002";
	private static final String USERNAME = "RSF";
	private static final String PASSWORD = "956107fbdfb82676a60ae444c2dd2475";//SnHttpUtils.EncoderByMd5("20031027");
	
	public static final String username = "dantegarden";
	public static final String password = "10121118Dante";
	public static final String machineCode = "1234";// 数字，随便写
	public static final String codeType = "6004";// 写死6004
	
	public static String SESSION_ID = "";
	public static String LOGIN_IN_USER_ID = "";
	
	@Override
	public SerialNumberResult getSerialNumber(String ewmJson) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SerialNumberResult cancelSerialNumber(String ewmJson)
			throws Exception {
		
		return null;
	}
	
	@Test
	public void test(){
		File verifyCodePic = new File("d:/test/verifycode.png");
		String sessionId = getVerifyCode(verifyCodePic);
		System.out.println(sessionId);
		String yzm_res = ChaoJiYing.PostPic(username, password, machineCode, codeType, "0", "0", "trans-message", verifyCodePic.getAbsolutePath());
		YzmResult yzmres = JsonUtils.jsonToJavaBean(yzm_res,YzmResult.class);
		System.out.println(yzmres.getPic_str());
		String loginUserId = fakeLogin(sessionId, yzmres.getPic_str());
		System.out.println(loginUserId);
		
		SESSION_ID = sessionId;
		LOGIN_IN_USER_ID = loginUserId;
		
		CreateSNParam csn = new CreateSNParam("256477000025", "256477000025 - 动车组闸片", "1");
		List<SerialCode> snList = this.createSN(csn);
		System.out.println(snList.size());
	}
	
	/**模拟登陆
	 * @param sessionId 从获取验证码一步拿到的会话id
	 * @param verifyCode 超级鹰识别的验证码
	 * */
	private String fakeLogin(String sessionId, String verifyCode){
		String cookie = sessionId;
		
		String url = "http://www.irailstone.com/Login/CheckLogin";
		Map<String,String> header = ImmutableMap.<String, String>builder()
				.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
				.put("Accept","text/plain, */*; q=0.01")
				.put("Accept-Encoding", "gzip, deflate")
				.put("Accept-Language", "zh-CN,zh;q=0.9")
				.put("Cache-Control", "no-cache")
				.put("Connection", "keep-alive")
				.put("Host", "www.irailstone.com")
				.put("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
				.put("Cookie", cookie)
				.build();
		Map<String,Object> formData = ImmutableMap.<String, Object>builder()
				.put("Account", USERNAME)
				.put("Password", PASSWORD) //表示是否是搜索请求
				//.put("nd", "1527040388821") //表示已经发送请求的次数
				.put("Verifycode", verifyCode) // 表示请求行数
				.build();
		String result = SnHttpUtils.checkLogin(url, formData, header);
		if(StringUtils.isNotBlank(result)){
			return result.split(";")[0];
		}else{
			return "";
		}
	}
	
	/**获得验证码
	 * @param verifyCodePic 下载验证码到文件
	 * **/
	private String getVerifyCode(File verifyCodePic){
		String url = "http://www.irailstone.com/Login/VerifyCode";
		Map<String,String> header = ImmutableMap.<String, String>builder()
				.put("Accept","image/webp,image/apng,image/*,*/*;q=0.8")
				.put("Accept-Encoding", "gzip, deflate")
				.put("Accept-Language", "zh-CN,zh;q=0.9")
				.put("Cache-Control", "no-cache")
				.put("Connection", "keep-alive")
				.put("Host", "www.irailstone.com")
				.put("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
				.build();
		verifyCodePic.deleteOnExit();
		String result = SnHttpUtils.downloadVerifyCode(url, null, header, verifyCodePic);
		if(StringUtils.isNotBlank(result)){
			return result.split(";")[0];
		}else{
			return "";
		}
	}
	
	
	/**
	 * 通过productCode,productNum,markNum创建序列号组
	 * 自动识别起始号码、终止号码
	 * **/
	private List<SerialCode> createSN(CreateSNParam csn){
		//必须有productCode,productNum, markNum
		String createDate = CommonHelper.date2Str(new Date(), "yyyy-MM");
		String startNum = this.getNewSNStartNum(csn.getProductCode(), createDate);
		csn.setCreateTime(createDate);
		csn.setStartNum(startNum);
		csn.setRemark("remark");
		csn.setSupplementInfo("supplenment info");
		CreateResult cr = this.createSNGroup(csn);
		if(cr.getSuccess()){
			System.out.println(cr.getMessage());
			SerialGroupQueryResult sgqr = this.querySerialGroupByPage(csn.getProductCode(), 100, 1);
			
			SerialCodeGroup group = sgqr.getRows().get(0);
			List<SerialCode> snList = this.querySerialCodeByGroup(group.getID());
			return snList;
		}else{
			System.out.println(cr.getMessage());
			return Lists.newArrayList();
		}
	}
	
	/**新建序列号组
	 * @param csn
	 * **/
	private CreateResult createSNGroup(CreateSNParam csn){
		/**从cookie中获取LoginUserKey*/
		String cookie = SESSION_ID + "; " + LOGIN_IN_USER_ID +"; "; 
		
		String url = "http://www.irailstone.com/RnumberModule/RNUMBER_PRODUCT_SN_ORDER/SubmitFormPro?KeyValue=";
		Map<String,String> header = ImmutableMap.<String, String>builder()
				.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
				.put("Accept","application/json, text/javascript, */*; q=0.01")
				.put("Accept-Encoding", "gzip, deflate")
				.put("Accept-Language", "zh-CN,zh;q=0.9")
				.put("Cache-Control", "no-cache")
				.put("Connection", "keep-alive")
				.put("Host", "www.irailstone.com")
				.put("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
				.put("Cookie", cookie)
				.build();
		Map<String,Object> formData = ImmutableMap.<String, Object>builder()
				.put("PRODUCT_CODE", csn.getProductCode())
				.put("PRODUCT_NAME", csn.getProductName()) //表示是否是搜索请求
				//.put("nd", "1527040388821") //表示已经发送请求的次数
				.put("ID", "&nbsp;") // 表示请求行数
				.put("CID", "8a8080905d9515f2015d95f5f03a0002")  // 表示请求页码
				.put("IS_AFTER_SALE", "0")
				.put("SERIAL_TYPE", "0")
				.put("MAKE_NUM", csn.getMakeNum())
				.put("ORDER_TIME", csn.getCreateTime())
				.put("START_NUM", csn.getStartNum())
				.put("END_NUM", csn.getEndNum())
				.put("SUPPLEMENT_INFO", csn.getSupplementInfo())
				.put("REMARK", csn.getRemark())
				.build();
		String result = HttpUtils.doPost(url, formData, header);
		System.out.println(result);
		CreateResult cr = JsonUtils.jsonToJavaBean(result, CreateResult.class);
		return cr;
	}
	
	/**
	 * 获得新建序列号组的起始号码
	 * @param productCode
	 * **/
	private String getNewSNStartNum(String productCode, String createDate){
		
		String url = "http://www.irailstone.com/RnumberModule/RNUMBER_PRODUCT_SN/GetMaxSN";
		Map<String,String> header = ImmutableMap.<String, String>builder()
				.put("Accept","application/json, text/javascript, */*; q=0.01")
				.put("Accept-Encoding", "gzip, deflate")
				.put("Accept-Language", "zh-CN,zh;q=0.9")
				.put("Cache-Control", "no-cache")
				.put("Connection", "keep-alive")
				.put("Host", "www.irailstone.com")
				.put("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
				.build();
		Map<String,Object> formData = ImmutableMap.<String, Object>builder()
				.put("product_code", productCode)
				.put("createdate", createDate) 
				.build();
		String result = HttpUtils.doGet(url, formData, header);
		System.out.println(result);
		Maxsn maxsn = JsonUtils.jsonToJavaBean(result, Maxsn.class);
		return maxsn.getMaxsn();
	}
	
	/**
	 * 按照序列号组查询序列号
	 * @param groupId 
	 * **/
	private List<SerialCode> querySerialCodeByGroup(String groupId){
		List<Parameter> parameters = ImmutableList.of(
				new Parameter("ORDER_ID", groupId, null)
				);
		String parameterJson = JsonUtils.JavaBeanToJson(parameters);		
		
		String url = "http://www.irailstone.com/RnumberModule/RNUMBER_PRODUCT_SN/GridJson";
		Map<String,String> header = ImmutableMap.<String, String>builder()
				.put("Accept","application/json, text/javascript, */*; q=0.01")
				.put("Accept-Encoding", "gzip, deflate")
				.put("Accept-Language", "zh-CN,zh;q=0.9")
				.put("Cache-Control", "no-cache")
				.put("Connection", "keep-alive")
				.put("Host", "www.irailstone.com")
				.put("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
				.build();
		Map<String,Object> formData = ImmutableMap.<String, Object>builder()
				.put("ParameterJson", parameterJson)
				.put("_search", false) //表示是否是搜索请求
				.put("rows", "all") // 表示请求行数
				.put("page", 1)  // 表示请求页码
				.put("sidx", "")
				.put("sord", "asc")
				.build();
		String result = HttpUtils.doGet(url, formData, header);
		System.out.println(result);
		List<SerialCode> list = JsonUtils.jsonToList(result, SerialCode.class);
		return list;
	}
	
	/**
	 * 按照产品代码查询所有序列号组
	 * 默认按照CREATEDATE降序排列
	 * **/
	private List<SerialCodeGroup> queryAllSerialGroup(String productCode){
		SerialGroupQueryResult sgqr = this.querySerialGroupByPage(productCode, 100, 1);
		List<SerialCodeGroup> sgrList = Lists.newArrayList();
		if(sgqr.getPage() == sgqr.getTotal() && CollectionUtils.isNotEmpty(sgqr.getRows())){
			sgrList.addAll(sgqr.getRows());
		}else{
			sgrList.addAll(sgqr.getRows());
			for (int i = 2; i <= sgqr.getTotal(); i++) {
				SerialGroupQueryResult _sgqr = this.querySerialGroupByPage(productCode, 100, i);
				sgrList.addAll(_sgqr.getRows());
			}
		}
		return sgrList;
	}
	
	/**
	 * 按产品代码分页查询序列号组
	 * 默认按照CREATEDATE降序排列
	 * **/
	private SerialGroupQueryResult querySerialGroupByPage(String productCode, int pageSize, int page){
		String PRODUCT_CODE = productCode;
		List<Parameter> parameters = ImmutableList.of(
				new Parameter("CREATE_CID", CREATE_CID),
				new Parameter("PRODUCT_CODE", productCode)
				);
		String parameterJson = JsonUtils.JavaBeanToJson(parameters);		
		
		String url = "http://www.irailstone.com/RnumberModule/RNUMBER_PRODUCT_SN_ORDER/GridPageJson";
		Map<String,String> header = ImmutableMap.<String, String>builder()
				.put("Accept","application/json, text/javascript, */*; q=0.01")
				.put("Accept-Encoding", "gzip, deflate")
				.put("Accept-Language", "zh-CN,zh;q=0.9")
				.put("Cache-Control", "no-cache")
				.put("Connection", "keep-alive")
				.put("Host", "irailstone.com")
				.put("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
				.build();
		Map<String,Object> formData = ImmutableMap.<String, Object>builder()
				.put("ParameterJson", parameterJson)
				.put("_search", false) //表示是否是搜索请求
				//.put("nd", "1527040388821") //表示已经发送请求的次数
				.put("rows", pageSize) // 表示请求行数
				.put("page", page)  // 表示请求页码
				.put("sidx", "CREATEDATE")
				.put("sord", "desc")
				.build();
		String result = HttpUtils.doGet(url, formData, header);
		SerialGroupQueryResult snResult = JsonUtils.jsonToJavaBean(result, SerialGroupQueryResult.class);
		return snResult;
	}
	
	/**
	 * 按照产品代码查询所有序列号
	 * 默认按照CREATEDATE降序排列
	 * **/
	private List<SerialCode> queryAllSerialCode(String productCode){
		SerialCodeQueryResult scqr = this.querySerialCodeByPage(productCode, 100, 1);
		List<SerialCode> scrList = Lists.newArrayList();
		if(scqr.getPage() == scqr.getTotal() && CollectionUtils.isNotEmpty(scqr.getRows())){
			scrList.addAll(scqr.getRows());
		}else{
			scrList.addAll(scqr.getRows());
			for (int i = 2; i <= scqr.getTotal(); i++) {
				SerialCodeQueryResult _scqr = this.querySerialCodeByPage(productCode, 100, i);
				scrList.addAll(_scqr.getRows());
			}
		}
		return scrList;
	}
	
	/**
	 * 按照产品代码分页查询
	 * 默认按照CREATEDATE降序排列
	 * @param pageSize 一页多少个
	 * @param page 查第几页
	 * **/
	private SerialCodeQueryResult querySerialCodeByPage(String productCode,int pageSize, int page){
		String PRODUCT_CODE = productCode;
		List<Parameter> parameters = ImmutableList.of(
				new Parameter("CREATE_CID", CREATE_CID),
				new Parameter("PRODUCT_CODE", PRODUCT_CODE)
				);
		String parameterJson = JsonUtils.JavaBeanToJson(parameters);		
		
		String url = "http://www.irailstone.com/RnumberModule/RNUMBER_PRODUCT_SN/GridPageJson";
		Map<String,String> header = ImmutableMap.<String, String>builder()
				.put("Accept","application/json, text/javascript, */*; q=0.01")
				.put("Accept-Encoding", "gzip, deflate")
				.put("Accept-Language", "zh-CN,zh;q=0.9")
				.put("Cache-Control", "no-cache")
				.put("Connection", "keep-alive")
				.put("Host", "irailstone.com")
				.put("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
				.build();
		Map<String,Object> formData = ImmutableMap.<String, Object>builder()
				.put("ParameterJson", parameterJson)
				.put("_search", false) //表示是否是搜索请求
				//.put("nd", "1527040388821") //表示已经发送请求的次数
				.put("rows", pageSize) // 表示请求行数
				.put("page", page)  // 表示请求页码
				.put("sidx", "CREATEDATE")
				.put("sord", "desc")
				.build();
		String result = HttpUtils.doGet(url, formData, header);
		SerialCodeQueryResult snResult = JsonUtils.jsonToJavaBean(result, SerialCodeQueryResult.class);
		return snResult;
	}
	
	/**按照uuid删除记录
	 * @param uuid
	 * */
	private boolean cancelByUUID(String uuid){
		String url = "http://www.irailstone.com/RnumberModule/RNUMBER_PRODUCT_SN/updatestate";
		Map<String,String> header = ImmutableMap.<String, String>builder()
				.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
				.put("Accept","application/json, text/javascript, */*; q=0.01")
				.put("Accept-Encoding", "gzip, deflate")
				.put("Accept-Language", "zh-CN,zh;q=0.9")
				.put("Cache-Control", "no-cache")
				.put("Connection", "keep-alive")
				.put("Host", "www.irailstone.com")
				.put("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36")
				.build();
		Map<String,Object> formData = ImmutableMap.of(
				"id", uuid,
				"state", 7);
		String result = HttpUtils.doPost(url, formData, header);
		if(StringUtils.equals(result, "1")){
			return Boolean.TRUE;
		}else if(StringUtils.equals(result, "0")){
			return Boolean.FALSE;
		}else{
			System.out.println(result);
			return Boolean.FALSE;
		}
	}
}
