package ua.ll7.slot7.logging.demo;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Alex Velichko
 *         15.03.14 : 19:30
 */
public class DemoApp {
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("loggingAContext.xml");

		DemoBean demoBean = (DemoBean) context.getBean("testBean");
		demoBean.actions();

	}
}
