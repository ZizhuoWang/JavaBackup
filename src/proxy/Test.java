package proxy;

public class Test {

	public static void main(String[] args) {
		Conductor proxy = new Conductor(new NanJingDeYunShe());
		for(int i=1;i<=5;i++){
			proxy.enter(new Audience("Audience"+i));
		}
	}
}
