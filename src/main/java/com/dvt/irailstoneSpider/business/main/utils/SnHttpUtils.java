package com.dvt.irailstoneSpider.business.main.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import sun.misc.BASE64Encoder;

import com.dvt.irailstoneSpider.commons.utils.HttpUtils;

public class SnHttpUtils extends HttpUtils {
	/**md5加密算法**/
	public static String EncoderByMd5(String str) {
		String newStr = str;
		try{
			//确定计算方法
	        MessageDigest md5=MessageDigest.getInstance("MD5");
	        BASE64Encoder base64en = new BASE64Encoder();
	        //加密后的字符串
	        newStr=base64en.encode(md5.digest(str.getBytes("utf-8")));
		}catch(Exception e){
			e.printStackTrace();
		}
        return newStr;
    }
	
	/**下载验证码的同时获得sessionId**/
	public static String downloadVerifyCode(String getUrl, Map<String,Object> formData, Map<String,String> header, File pic){
		String result = "";
		HttpGet request = null;
		HttpResponse response = null;
		try{
			// 定义HttpClient    
            HttpClient client = new DefaultHttpClient();
            client.getParams().setParameter("http.protocol.content-charset", "UTF-8");
            //设置参数  
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
            if(formData!=null)
	            for (Iterator iter = formData.keySet().iterator(); iter.hasNext();) {  
	                String name = (String) iter.next();  
	                String value = String.valueOf(formData.get(name));  
	                nvps.add(new BasicNameValuePair(name, value));  
	                //System.out.println(name +"-"+value);  
	            }  
            getUrl += "?" + EntityUtils.toString(new UrlEncodedFormEntity(nvps), CHARSET); 
            
            // 实例化HTTP方法    
            request = new HttpGet();    
            request.setURI(new URI(getUrl));  
            
            for (String hkey : header.keySet()) {
            	request.addHeader(hkey, header.get(hkey));  
			}
            
            response = client.execute(request);
            
            String cookie = "";
            Header[] headerArr = response.getHeaders("Set-Cookie");
            if(headerArr!=null && headerArr.length>0){
            	cookie = headerArr[0].getValue();
            }
            
            HttpEntity httpEntity = response.getEntity();
            int code = response.getStatusLine().getStatusCode();  
            
            if(code == 200){    //请求成功  
            	if (httpEntity != null) {
            		System.out.println(httpEntity.getContentType().getValue());
                    //按指定编码转换结果实体为String类型
            		if(httpEntity.getContentEncoding()!=null){  
                        if("gzip".equalsIgnoreCase(httpEntity.getContentEncoding().getValue())){  
                            httpEntity = new GzipDecompressingEntity(httpEntity);                 
                        } else if("deflate".equalsIgnoreCase(httpEntity.getContentEncoding().getValue())){  
                            httpEntity = new DeflateDecompressingEntity(httpEntity); 
                        }
                    }
            		if(httpEntity.getContentType().getValue().contains("image")){
            			FileOutputStream output = new FileOutputStream(pic);
            			byte[] b = EntityUtils.toByteArray(httpEntity);
            			output.write(b);
            			output.close();
            		}
            		result = cookie;
                    //result = EntityUtils.toString(httpEntity,"UTF-8");
                }
            	EntityUtils.consume(httpEntity);
            }else{
            	request.abort();
                System.out.println("状态码：" + code);  
            }  
            
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return result;
		}
	}
	
	/**模拟登陆的同时获取loginUserId**/
	public static String checkLogin(String postUrl, Map<String,Object> formData, Map<String,String> header){
		String result = "";
		BufferedReader in = null;  
		HttpPost request = null;
		HttpResponse response = null;
		try {    
            // 定义HttpClient    
            HttpClient client = new DefaultHttpClient();    
            // 实例化HTTP方法    
            request = new HttpPost();    
            request.setURI(new URI(postUrl));  
            
            for (String hkey : header.keySet()) {
            	request.addHeader(hkey, header.get(hkey));  
			}
            //设置参数  
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();   
            if(formData!=null)
	            for (Iterator iter = formData.keySet().iterator(); iter.hasNext();) {  
	                String name = (String) iter.next();  
	                String value = String.valueOf(formData.get(name));  
	                nvps.add(new BasicNameValuePair(name, value));  
	                //System.out.println(name +"-"+value);  
	            }  
            request.setEntity(new UrlEncodedFormEntity(nvps,HTTP.UTF_8));  
              
            response = client.execute(request);
            //client.getState().getCookies()
            //CookieStore cookiestore = ((DefaultHttpClient)client).getCookieStore();
            HttpEntity httpEntity = response.getEntity();
            
            String cookie = "";
            Header[] headerArr = response.getHeaders("Set-Cookie");
            if(headerArr!=null && headerArr.length>0){
            	cookie = headerArr[0].getValue();
            }
            
            int code = response.getStatusLine().getStatusCode();  
            if(code == 200){    //请求成功  
            	if (httpEntity != null) {
            		if(httpEntity.getContentEncoding()!=null){  
                        if("gzip".equalsIgnoreCase(httpEntity.getContentEncoding().getValue())){  
                            httpEntity = new GzipDecompressingEntity(httpEntity);                 
                        } else if("deflate".equalsIgnoreCase(httpEntity.getContentEncoding().getValue())){  
                            httpEntity = new DeflateDecompressingEntity(httpEntity);              
                        }
                    }  
                    //按指定编码转换结果实体为String类型
            		//result = EntityUtils.toString(httpEntity, CHARSET);
            		String rcode = EntityUtils.toString(httpEntity, CHARSET);
            		if(rcode.equals("3")){
            			System.out.println("模拟登陆成功！");
            		}else if(rcode.equals("4")){
            			System.out.println("登陆密码错误");
            		}else if(rcode.equals("2")){
            			System.out.println("登录账户被系统锁定");
            		}else if(rcode.equals("-1")){
            			System.out.println("登录账户不存在");
            		}else if(rcode.equals("5")){
            			System.out.println("验证码错误");
            		}else if(rcode.equals("6")){
            			System.out.println("请转至SAAS地址登录");
            		}else{
            			System.out.println("验证码过期，请刷新验证码！");
            		}
            		
            		result = cookie;
                }
            	EntityUtils.consume(httpEntity);
            }else{   //  
            	request.abort();
                System.out.println("状态码：" + code);  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{
        	return result;
        }
	}
}
