
package com.itheima.anno;

import com.itheima.validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


/*
* @Documented 元注解，抽取到帮助文档
* @Target 元注解 用到什么地方（类 方法 ...）
* @Retention 在那个阶段保留
* @Constraint 哪个类提供校验规则，

 * */
@Documented
@Constraint(validatedBy = {StateValidation.class})//提供校验规则的类
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)

public @interface State {
    //提供校验失败后的提示信息

    String message() default "state参数值只能为已发布或者草稿";
    //指定分组
    Class<?>[] groups() default {};
    //负载 获取State注解的附加信息
    Class<? extends Payload>[] payload() default {};
}
