package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
// MyExucludeComponent가 붙으면, 애노테이션에서 제외할 것
public @interface MyExcludeComponent {

}
