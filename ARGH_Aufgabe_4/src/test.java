import static org.junit.Assert.*;

import org.junit.Test;

public class test {

	@Test
	public void testy() {
		Utilityklasse<?> uti = new Utilityklasse<Object>();
		Object one = new comparee(6);
		Object two = new comparee(3);//
//		System.out.println(ObjectSizeFetcher.getObjectSize(uti));
		comparee three = (comparee) uti.betterElement(one, two);
		

		
		System.out.println(one.toString());
		System.out.println(two.toString());
		System.out.println(three.value());
		System.out.println(three.toString());
//		fail("Not yet implemented"); // TODO
	}

}
