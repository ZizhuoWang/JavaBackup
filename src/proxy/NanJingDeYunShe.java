package proxy;

public class NanJingDeYunShe implements DeYunShe {

	@Override
	public void enter(Audience audience) {
		System.out.println(audience.getName()+" entered!");
	}

}
