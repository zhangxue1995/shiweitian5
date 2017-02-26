package com.dreamers.shiweitian;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.dreamers.shiweitian.News_page.News;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
public class JsonUtil<T> {
//	public List<T> StringFromJson (String jsondata){
//		Type listType = new TypeToken<List<T>>(){}.getType();
//		Gson gson=new Gson();
//		ArrayList<T> list=gson.fromJson(jsondata, listType);
//		return list;
//	}
	public static List<News> StringFromJson (String jsondata){
		Type listType = new TypeToken<List<News>>(){}.getType();
		Gson gson=new Gson();
		ArrayList<News> list=gson.fromJson(jsondata, listType);
		return list;
	}
	/*
	 * 将List数据转换为json字符串
	 */
	public  String getJsonData(List<?> list){
		Gson gson=new Gson();
		String jsonstring=gson.toJson(list);
		return jsonstring;
	}
	/*
	 * 将json字符串转化为List数组
	 */
	public static <T> List<T> fromJsonArray(String json, Class<T> clazz) throws Exception {   
		List<T> lst =  new ArrayList<T>();
		JsonArray array = new JsonParser().parse(json).getAsJsonArray();
		for(final JsonElement elem : array){
			lst.add(new Gson().fromJson(elem, clazz));        
		}        
			return lst;
		}

}
