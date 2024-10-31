/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.itheima.validation;

import com.itheima.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
/*
    第一个参数：给哪个注解提供校验规则
    第二个参数：校验数据类型
 */


public class StateValidation implements ConstraintValidator<State,String> {
    //提供校验规则

    /**
     *
     * @param s 将来要校验的数据
     * @param constraintValidatorContext
     * @return false 校验不同通过
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s==null) return  false;
        if(s.equals("已发布")||s.equals("草稿")) return true;
        return false;
    }

}
