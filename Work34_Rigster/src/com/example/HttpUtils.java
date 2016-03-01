package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.cookie.Cookie;

import android.util.Log;
/**
 * 发送请求  
 * @author Barry
 *
 */
public class HttpUtils {
	public static final String path = "http://192.168.1.103:8080/Http1";
	//android 不像浏览器     每次访问服务器的时候都都要 发送cookie 来得到自己的那个session的数据   
	private static String cookie;
	/**
	 * 发送注册和登陆的信息
	 * @param url
	 * @param body
	 * @return
	 * @throws IOException
	 */
	public static String post(String url, String body) throws IOException {
		URL ur = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
		conn.setRequestMethod("POST");

		
		conn.setDoOutput(true);

		conn.getOutputStream().write(body.getBytes());

		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			cookie = conn.getHeaderField("Set-Cookie");
			// 返回的内容
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			br.close();
			 
			return sb.toString();
		}

		return "发送失败";
	}
	/**
	 * 登陆成功后得到  登陆的用户的信息
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static String get(String path) throws IOException {
		URL ur = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) ur.openConnection();
		conn.setRequestProperty("Cookie", cookie);

		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			
			// 返回的内容
			InputStream is = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			is.close();
			br.close();

			return sb.toString();
		}

		return "发送失败";
	}
}
