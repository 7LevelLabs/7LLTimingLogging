package ua.ll7.slot7.logging.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Alex Velichko
 *         15.03.14 : 17:50
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogThisMethod {
	boolean logException() default false;

	boolean logArguments() default false;

	boolean logResponse() default false;
}
