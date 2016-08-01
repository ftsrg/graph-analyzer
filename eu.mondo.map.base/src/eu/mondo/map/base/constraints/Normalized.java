package eu.mondo.map.base.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Positive
@Range(lowerBound = 0.0, upperBound = 1.0)
public @interface Normalized {

}
