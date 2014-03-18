package ua.ll7.slot7.checks.notnull.aspect;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.ll7.slot7.checks.notnull.demo.DemoBean;

/**
 * @author Alex Velichko
 *         18.03.14 : 21:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:notnullAContext.xml")
public class NotNullAspectTest extends Assert {

	@Autowired
	private DemoBean demoBean;

	@Test
	public void testActions1() {
		demoBean.actions("Test", 10);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testActions2() {
		demoBean.actions(null, 10);
	}
}
