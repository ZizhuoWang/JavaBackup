package proxyImage;

public class ProxyImage implements Image {
	private RealImage image = null;
    private String filename = null;
    
    public ProxyImage(final String filename) { 
        this.filename = filename; 
    }
    @Override
    public void displayImage() {
    	//懒汉式单例:只有在第一次请求实例的时候创建
        if (image == null) {
           image = new RealImage(filename);
        }
        image.displayImage();
    }
}
