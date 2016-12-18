package proxyImage;

public class RealImage implements Image {
	private String filename = null;
	//真实图片需要加载
	public RealImage(final String name) {
		this.filename = name;
		loadImageFromDisk();
	}
	//加载方法
	private void loadImageFromDisk(){
		System.out.println("Loading   " + filename);
	}
	@Override
	public void displayImage() {
		System.out.println("Displaying " + filename);
	}
}
