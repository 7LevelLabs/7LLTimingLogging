package ua.ll7.slot7.checks.stringsnotempty.demo;

import ua.ll7.slot7.checks.stringsnotempty.annotation.StringsNotEmpty;

/**
 * @author Alex Velichko
 *         18.03.14 : 20:33
 */
public class DemoBean {

	@StringsNotEmpty
	public void actions(String arg1, int arg2) {
		System.out.println(arg1);
		System.out.println(arg2);
	}
}
