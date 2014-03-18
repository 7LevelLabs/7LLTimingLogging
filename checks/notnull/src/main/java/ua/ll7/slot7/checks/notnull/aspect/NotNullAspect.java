package ua.ll7.slot7.checks.notnull.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Intercept method marked with {@link ua.ll7.slot7.checks.notnull.annotation.NotNull}
 *
 * @author Alex Velichko
 *         18.03.14 : 16:03
 * @see ua.ll7.slot7.checks.notnull.annotation.NotNull
 */
@Component
@Aspect
public class NotNullAspect {

	@Pointcut("execution(@ua.ll7.slot7.checks.notnull.annotation.NotNull * *(..))")
	private void methodPointcut() {

	}

	@Before("methodPointcut()")
	public void methodExecution(JoinPoint joinPoint) throws Throwable {

		//check all args
		Object[] arguments = joinPoint.getArgs();
		for (Object argument : arguments) {
			if (argument == null) {
				throw new IllegalArgumentException("Arguments must be not null");
			}
		}
	}
}
