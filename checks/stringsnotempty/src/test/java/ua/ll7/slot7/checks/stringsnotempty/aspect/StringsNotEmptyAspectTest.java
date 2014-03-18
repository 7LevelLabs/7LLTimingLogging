package ua.ll7.slot7.checks.stringsnotempty.aspect;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.ll7.slot7.checks.stringsnotempty.demo.DemoBean;

/**
 * @author Alex Velichko
 *         18.03.14 : 21:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:stringsnotemptyAContext.xml")
public class StringsNotEmptyAspectTest extends Assert {

	@Autowired
	private DemoBean demoBean;

	@Test
	public void testActions1() {
		demoBean.actions("Test", 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testActions2() {
		demoBean.actions("", 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testActions3() {
		demoBean.actions(" ", 10);
	}

}
