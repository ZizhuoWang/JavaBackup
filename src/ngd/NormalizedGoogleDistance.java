package ngd;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.*;
public class NormalizedGoogleDistance {
	
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
		String regex = "百度为您找到相关结果约(.*)个";
		Pattern pattern = Pattern.compile(regex);
		
		//用于存三次搜索各自的索引量
		Double numA,numB,numC;
		numA=1.0;numB=1.0;numC=1.0;
		try {
			//三次搜索，三次连接，三个缓存阅读器
			URL baiduURL1 = new URL("http://www.baidu.com/s?wd="+a);
			URL baiduURL2 = new URL("http://www.baidu.com/s?wd="+b);
			URL baiduURL3 = new URL("http://www.baidu.com/s?wd="+a+b);
			URLConnection connection1 = baiduURL1.openConnection();
			URLConnection connection2 = baiduURL2.openConnection();
			URLConnection connection3 = baiduURL3.openConnection();
			BufferedReader in1 = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
			BufferedReader in2 = new BufferedReader(new InputStreamReader(connection2.getInputStream()));
			BufferedReader in3 = new BufferedReader(new InputStreamReader(connection3.getInputStream()));
			
			//逐行筛选匹配正则表达式，并将结果中的逗号删除，之后转化成数字。
			String html1 = in1.readLine();
			while(html1!=null){
				Matcher matcher = pattern.matcher(html1);
				while(matcher.find()){
					System.out.println(a+": "+matcher.group(1));
					String temp = matcher.group(1);
					numA=Double.parseDouble(temp.replaceAll(",", ""));
				}
				html1 = in1.readLine();
			}
			//逐行筛选匹配正则表达式，并将结果中的逗号删除，之后转化成数字。
			String html2 = in2.readLine();
			while(html2!=null){
				Matcher matcher = pattern.matcher(html2);
				while(matcher.find()){
					System.out.println(b+": "+matcher.group(1));
					String temp = matcher.group(1);
					numB=Double.parseDouble(temp.replaceAll(",", ""));
				}
				html2 = in2.readLine();
			}
			//逐行筛选匹配正则表达式，并将结果中的逗号删除，之后转化成数字。
			String html3 = in3.readLine();
			while(html3!=null){
				Matcher matcher = pattern.matcher(html3);
				while(matcher.find()){
					System.out.println(a+"+"+b+": "+matcher.group(1));
					String temp = matcher.group(1);
					numC=Double.parseDouble(temp.replaceAll(",", ""));
				}
				html3 = in3.readLine();
			}
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
		Double lnSum = Math.log(25270000000.0);//由于百度有索引总量限制，在此使用谷歌搜索总索引量
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