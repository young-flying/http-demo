package org.jewi.study.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class QQTest {
	static String url = "";
	static String sessionId = "";
	static String savePath = "";
	public static void main(String[] args) {
		analysis_1();
		//codeGenerator(1);
	}
	
	public static void analysis_1() {
		try {
			Map<String, String> parameters = new HashMap<>();
			String res = HttpPostUtil.sendGet(url, parameters,sessionId);
			res = res.substring(16, res.length() -2);
			JSONObject json = JSONObject.parseObject(res);
			JSONObject dataJson = json.getJSONObject("data");
			if(null != dataJson) {
				JSONArray photoList = dataJson.getJSONArray("photoList");
				analysisPhotoList(photoList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void analysis_2() {
		try {
			Map<String, String> parameters = new HashMap<>();
			String res = HttpPostUtil.sendGet(url, parameters,sessionId);
			res = res.substring(16, res.length() -2);
			JSONObject json = JSONObject.parseObject(res);
			JSONObject dataJson = json.getJSONObject("data");
			
			if(null != dataJson) {
				JSONObject tempObj = null;
				JSONArray rangeList = dataJson.getJSONArray("rangeList");
				if(null != rangeList && !rangeList.isEmpty()) {
					for(Object obj : rangeList) {
						tempObj = (JSONObject)obj;
						if(null == tempObj) continue;
						JSONArray photoList = tempObj.getJSONArray("photoList");
						analysisPhotoList(photoList);
					}
				}
				
				
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void analysisPhotoList(JSONArray photoList) throws Exception {
		
		
		JSONObject tempObj = null;
		String imgUrl = "";
		int index = 1;
		String prefix = codeGenerator(3)+"_";
		if(null != photoList && !photoList.isEmpty()) {
			for(Object obj : photoList) {
				tempObj = (JSONObject)obj;
				imgUrl =  tempObj.getString("url");
				System.out.println(imgUrl);
				HttpPostUtil.download(imgUrl, prefix+index+".jpg",
						savePath,sessionId);
				index++;
			}
		}
	}
	
	
	public static String codeGenerator(int num ) {
		StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {// 你想生成几个字符的，就把4改成几，如果改成1,那就生成一个随机字母．
        	sb.append((char) (Math.random() * 26 + 'A'));
        }
        return sb.toString();
	}
}
