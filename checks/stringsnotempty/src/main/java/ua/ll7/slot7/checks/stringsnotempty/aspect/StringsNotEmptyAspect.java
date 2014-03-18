package ua.ll7.slot7.checks.stringsnotempty.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Intercept method marked with {@link ua.ll7.slot7.checks.stringsnotempty.annotation.StringsNotEmpty}
 *
 * @author Alex Velichko
 *         18.03.14 : 20:39
 * @see ua.ll7.slot7.checks.stringsnotempty.annotation.StringsNotEmpty
 */
@Component
@Aspect
public class StringsNotEmptyAspect {
	@Pointcut("execution(@ua.ll7.slot7.checks.stringsnotempty.annotation.StringsNotEmpty * *(..))")
	private void methodPointcut() {
	}

	@Before("methodPointcut()")
	public void methodExecution(JoinPoint joinPoint) throws Throwable {

		//check all args
		Object[] arguments = joinPoint.getArgs();
		for (Object argument : arguments) {
			if (argument instanceof String) {
				if ((StringUtils.isBlank((String) argument))) {
					throw new IllegalArgumentException("String arguments must be not null or empty");
				}
			}
		}
	}
}
