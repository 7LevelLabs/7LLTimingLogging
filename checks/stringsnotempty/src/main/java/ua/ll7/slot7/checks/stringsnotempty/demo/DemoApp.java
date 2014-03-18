package ua.ll7.slot7.checks.stringsnotempty.demo;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Alex Velichko
 *         18.03.14 : 20:33
 */
public class DemoApp {
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("stringsnotemptyAContext.xml");

		DemoBean demoBean = (DemoBean) context.getBean("demoBean");

		demoBean.actions("Arg1", 10);

		demoBean.actions(null, 10);

		demoBean.actions("", 10);

		demoBean.actions(" ", 10);
	}
}
