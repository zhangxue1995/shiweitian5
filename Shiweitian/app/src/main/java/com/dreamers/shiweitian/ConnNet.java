package com.dreamers.shiweitian;

import android.widget.Toast;

import org.apache.http.client.methods.HttpPost;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConnNet 
{
	private static final String URLVAR="http://60.205.182.144:8080/shiweitian3/";
	//将路径定义为一个常量，修改的时候也好更改
	//通过url获取网络连接  connection
	public HttpURLConnection getConn(String urlpath)
	{
		String finalurl=URLVAR+urlpath;
		HttpURLConnection connection = null;
		try {
			URL url=new URL(finalurl);
			connection=(HttpURLConnection) url.openConnection();
			connection.setDoInput(true);  //允许输入流
			connection.setDoOutput(true); //允许输出流
			connection.setUseCaches(false);  //不允许使用缓存
			connection.setRequestMethod("POST");  //请求方式
		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return connection;

	}
	public HttpPost gethttPost(String uripath)
	{	
		HttpPost httpPost=new HttpPost(URLVAR+uripath);
	
		System.out.println(URLVAR+uripath);
		return httpPost;
	}

}
