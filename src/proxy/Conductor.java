package proxy;


public class Conductor implements DeYunShe {

	private final int MAX = 3;
	private int audiences=0;
	private DeYunShe dys;
	
	public Conductor(DeYunShe dys) {
		this.dys = dys;
	}
	
	@Override
	public void enter(Audience audience) {
		if (audiences<MAX) {
			dys.enter(audience);
			audiences++;
		}else {
			System.out.println(audience.getName()+" is not allowed to enter!");
		}
	}

}
