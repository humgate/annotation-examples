package typeannotation;

import java.lang.annotation.*;

/**
 * Example of type annotation
 *
 */
@Documented
@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTypeAnnotation {
    int studentAge() default 18;
    String studentName();
    String stuAddress();
    String stuStream() default "CSE";
}


