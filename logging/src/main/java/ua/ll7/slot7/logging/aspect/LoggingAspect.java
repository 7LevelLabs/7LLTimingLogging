package ua.ll7.slot7.logging.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Intercept method marked with {@link ua.ll7.slot7.logging.annotation.LogThisMethod} annotation. Log execution time.
 *
 * @author Alex Velichko
 *         15.03.14 : 17:58
 * @see ua.ll7.slot7.logging.annotation.LogThisMethod
 */
@Component
@Aspect
public class LoggingAspect {

	@Pointcut("execution(@ua.ll7.slot7.logging.annotation.LogThisMethod * *(..))")
	private void logMethodPointcut() {

	}

	@Around("logMethodPointcut()")
	public void methodExecution(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		String name = proceedingJoinPoint.getThis().getClass().getSimpleName() + "." + proceedingJoinPoint.getSignature().getName();

		Logger localLogger = Logger.getLogger(proceedingJoinPoint.getThis().getClass().getSimpleName());
		localLogger.info("Execution of the " + name + " method");
		long ping = System.currentTimeMillis();

		proceedingJoinPoint.proceed(); //intercepted method

		long pong = System.currentTimeMillis();
		long time = pong - ping;
		localLogger.info("Execution time is " + time + " ms");
	}
}
