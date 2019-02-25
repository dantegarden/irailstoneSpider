package com.dvt.irailstoneSpider.commons.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;  
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;  
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;  
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.DeflateDecompressingEntity;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;  
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;  
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class HttpUtils {
	public static final String CHARSET = "UTF-8";
	
	
	public static String doGet(String getUrl, Map<String,Object> formData, Map<String,String> header){
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
                    result = EntityUtils.toString(httpEntity,"UTF-8");
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
	
	/**HTTPCLIENT POST**/
	public static String doPost(String postUrl, Map<String,Object> formData, Map<String,String> header){
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
                    result = EntityUtils.toString(httpEntity, CHARSET);
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
	
	
	public static String escape(String src) {
        int i;
        char j;
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length() * 6);
        for (i = 0; i < src.length(); i++) {
            j = src.charAt(i);
            if (Character.isDigit(j) || Character.isLowerCase(j)
                    || Character.isUpperCase(j))
                tmp.append(j);
            else if (j < 256) {
                tmp.append("%");
                if (j < 16)
                    tmp.append("0");
                tmp.append(Integer.toString(j, 16));
            } else {
                tmp.append("%u");
                tmp.append(Integer.toString(j, 16));
            }
        }
        return tmp.toString();
    }
	
	public static String unescape(String src) {
        StringBuffer tmp = new StringBuffer();
        tmp.ensureCapacity(src.length());
        int lastPos = 0, pos = 0;
        char ch;
        while (lastPos < src.length()) {
            pos = src.indexOf("%", lastPos);
            if (pos == lastPos) {
                if (src.charAt(pos + 1) == 'u') {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 2, pos + 6), 16);
                    tmp.append(ch);
                    lastPos = pos + 6;
                } else {
                    ch = (char) Integer.parseInt(src
                            .substring(pos + 1, pos + 3), 16);
                    tmp.append(ch);
                    lastPos = pos + 3;
                }
            } else {
                if (pos == -1) {
                    tmp.append(src.substring(lastPos));
                    lastPos = src.length();
                } else {
                    tmp.append(src.substring(lastPos, pos));
                    lastPos = pos;
                }
            }
        }
        return tmp.toString();
    }
}
