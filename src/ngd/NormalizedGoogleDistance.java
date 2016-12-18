package ngd;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.*;
public class NormalizedGoogleDistance {
	static String regex = "百度为您找到相关结果约(.*)个";
	static Pattern pattern = Pattern.compile(regex);
	static String url = "http://www.baidu.com/s?wd=";
	
	/**
	 * @param name
	 * @return 搜索到的网页数量
	 */
	private static Double searchResult(String name){
		try {
			URL baiduURL = new URL(url+name);
			URLConnection connection = baiduURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			//逐行筛选匹配正则表达式，并将结果中的逗号删除，之后转化成数字。
			String html = in.readLine();
			while(html!=null){
				Matcher matcher = pattern.matcher(html);
				while(matcher.find()){
					System.out.println(name.replaceAll("%20", " ")+": "+matcher.group(1));//格式优化
					String temp = matcher.group(1);
					return Double.parseDouble(temp.replaceAll(",", ""));
				}
				html = in.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0;//有可能没有返回值，所以默认写一个0.0
	}
	
	/**
	 * @param a
	 * @param b
	 * @return The result of NGD
	 */
	public static Double NGD(String a,String b){
		//删去空格，以防搜索出错
		a=a.replaceAll(" ", "");
		b=b.replaceAll(" ", "");
		//正则表达式匹配个数
		
		//用于存三次搜索各自的索引量
		Double numA,numB,numC;
		numA=1.0;numB=1.0;numC=1.0;
		try {
			numA = searchResult(a);
			numB = searchResult(b);
			numC = searchResult(a+"%20"+b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回Calculate函数的计算结果
		return Calculate(numA, numB, numC);
	}
	
	/**
	 * @param numA
	 * @param numB
	 * @param numC
	 * @return NGD公式计算结果
	 */
	public static Double Calculate(Double numA,Double numB,Double numC){
		Double lnx = Math.log(numA);
		Double lny = Math.log(numB);
		Double lnSum = Math.log(25270000000.0);//由于不知具体数值，这里取谷歌搜素最大索引限制
		Double lnxy = Math.log(numC);
		//NGD公式
		if (lnx>lny) {
			return (lnx-lnxy)/(lnSum-lny);
		}else {
			return (lny-lnxy)/(lnSum-lnx);
		}
	}
	
	/**
	 * @param args
	 * 从外部传进两个参数作为关键字，并将结果输出
	 */
	public static void main(String[] args) {
		Double result = NGD(args[0], args[1]);
		System.out.println("NGD: "+result);
	}
}