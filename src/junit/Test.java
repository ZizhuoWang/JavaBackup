package junit;

import static org.junit.Assert.*;

public class Test {

//	@org.junit.Test
//	public void test() {
//		fail("Not yet implemented");
//	}
	@org.junit.Test
	public void testAdd(){
		int i=0;
		do {
			assertEquals(5, new Calculate().add(2, 3));
			i++;
		} while (i<10000);
	}

}
