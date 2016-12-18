package proxy;


public class Conductor implements DeYunShe {

	private int max=0;
	private int audiences=0;
	private DeYunShe dys;
	
	public Conductor(DeYunShe dys) {
		this.dys = dys;
		getSeats();
	}
	@Override
	public int getSeats() {
		max = dys.getSeats();
		return 0;
	}
	
	@Override
	public void enter(Audience audience) {
		if (audiences<max) {
			dys.enter(audience);
			audiences++;
		}else {
			System.out.println(audience.getName()+" is not allowed to enter!");
		}
	}


}
