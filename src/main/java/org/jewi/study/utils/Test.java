package org.jewi.study.utils;

public class Test {

	public static void main(String[] args) {
		String url = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1421217173,958012614&fm=27&gp=0.jpg";
		
		try {
			HttpPostUtil.download(url, "1.jpg", "D://",null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
