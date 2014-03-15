package ua.ll7.slot7.logging.demo;

import ua.ll7.slot7.logging.annotation.LogThisMethod;

/**
 * @author Alex Velichko
 *         15.03.14 : 19:26
 */
public class DemoBean {

	@LogThisMethod
	public void actions() throws InterruptedException {
		System.out.println("DemoBean actions begin");
		Thread.sleep(1000);
		System.out.println("DemoBean actions end");
	}

}
