package ua.ll7.slot7.checks.notnull.demo;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Alex Velichko
 *         18.03.14 : 16:20
 */
public class DemoApp {
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("notnullAContext.xml");

		DemoBean demoBean = (DemoBean) context.getBean("demoBean");

		demoBean.actions("Arg1", 10);

		demoBean.actions(null, 10);

	}
}
