package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @param <T>泛型 由于返回不同的数据
 * @NoArgsConstructor 无参构造
 * @AllArgsConstructor 有参构造
 * private T data;响应数据  泛型 由于返回不同的数据
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;//业务状态吗，0成功 1 失败
    private String message;//提示信息
    private T data;//响应数据

    //快速返回操作成功响应结果（带响应数据）
    /*
        public static：这是一个公共的静态方法，意味着它可以通过类名直接调用，不需要创建类的实例。
        <E>：这是泛型参数，表明这个方法可以接受任何类型的参数，并在返回的Result对象中保存这个类型的对象。
        Result<E> success(E data)：方法的签名表明它返回一个Result类型的对象，这个对象的泛型参数与方法的泛型参数一致。
        方法接受一个参数data，这个参数的类型就是泛型参数E。
     */
    public static <E> Result<E> success(E data) {
        return new Result<>(0, "操作成功", data);
    }

    //快速返回操作成功响应结果
    public static Result success() {
        return new Result(0, "操作成功", null);
    }

    public static Result error(String message) {
        return new Result(1, message, null);
    }
}
