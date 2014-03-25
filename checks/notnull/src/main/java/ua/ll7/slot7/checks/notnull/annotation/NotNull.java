package ua.ll7.slot7.checks.notnull.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * On the marked method all parameters must be not null
 *
 * @author Alex Velichko
 *         18.03.14 : 16:00
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {

}
