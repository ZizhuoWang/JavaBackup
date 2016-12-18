package proxy;

public class NanJingDeYunShe implements DeYunShe {
	
	public static final int seats=3;
	@Override
	public void enter(Audience audience) {
		System.out.println(audience.getName()+" entered!");
	}
	@Override
	public int getSeats() {
		return seats;
	}

}
