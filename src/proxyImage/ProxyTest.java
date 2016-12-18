package proxyImage;

public class ProxyTest {
	public static void main(String[] args) {
		Image image1 = new ProxyImage("A");
		Image image2 = new ProxyImage("B");
		
		image1.displayImage();
		image1.displayImage();
		image2.displayImage();
		image2.displayImage();
		
		//这一次不用再次加载
		image1.displayImage();
	}
}
