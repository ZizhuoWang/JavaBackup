package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.*;

//百度为您找到相关结果约100,000,000个
public class Easy {

	public static void main(String[] args) {
		try {
			String regex = "百度为您找到相关结果约(.*)个";
			Pattern pattern = Pattern.compile(regex);
			String word = "余泽晨";
			URL baiduURL = new URL("http://www.baidu.com/s?wd="+word);
			URLConnection connection = baiduURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String html = in.readLine();
			while(html!=null){
				Matcher matcher = pattern.matcher(html);
				while(matcher.find()){
					System.out.println(matcher.group(1));
				}
				html = in.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
