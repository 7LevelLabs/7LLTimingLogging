package ua.ll7.slot7.timing.apps;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Alex Velichko
 *         20.12.13 : 19:11
 */
public class TimingTmpApp {
	public static void main(String[] args) throws InterruptedException {
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("writerAContext.xml");
		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");

		//timing

		//data

		TimingTestBean bean1 = (TimingTestBean) context.getBean("timingTestBean1");
		System.out.println("Starting " + bean1.getId());
		taskExecutor.execute(bean1);

		TimingTestBean bean2 = (TimingTestBean) context.getBean("timingTestBean2");
		System.out.println("Starting " + bean2.getId());
		taskExecutor.execute(bean2);

		//yes, quick and dirty
		for (; ; ) {
			int count = taskExecutor.getActiveCount();
			System.out.println("Active Threads : " + count);
			System.out.println("getActiveCount : " + taskExecutor.getActiveCount());

			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (count == 0) {
				taskExecutor.shutdown();
				break;
			}
		}

		//done
		System.out.println("Done.");
		context.close();


	}
}
