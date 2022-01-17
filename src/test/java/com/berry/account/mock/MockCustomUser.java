package com.berry.account.mock;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.security.test.context.support.WithSecurityContext;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = MockCustomUserSecurityContextFactory.class)
public @interface MockCustomUser {

    String tel() default "01012345678";

    String email() default "user@email.com";

    String role() default "";

    String[] authorities() default {};
}
