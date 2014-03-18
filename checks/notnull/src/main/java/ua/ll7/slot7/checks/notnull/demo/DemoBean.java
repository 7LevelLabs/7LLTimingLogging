package ua.ll7.slot7.checks.notnull.demo;

import ua.ll7.slot7.checks.notnull.annotation.NotNull;

/**
 * @author Alex Velichko
 *         18.03.14 : 16:20
 */
public class DemoBean {

	@NotNull
	public void actions(String arg1, int arg2) {
		System.out.println(arg1);
		System.out.println(arg2);
	}

}
