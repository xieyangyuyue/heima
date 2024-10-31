package com.itheima.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;

/*
 *lombok 在编译阶段，为实体类自动生成 setter getter toString
 * pom文件引入依赖 在实体类上添加注解
* @Data
* 自动生成getter setter等方法
*
* @JsonIgnore
  让springmvc把当前对象转成json字符串的时候，忽略password
  *
* @NotNull
* 值不为null
*
* @NotEmpty
* 值不为null并内容不为空
*
*  @Email
* 满足邮箱格式
* */
@Data
public class User {
    @NotNull
    private Integer id;//主键ID
    private String username;//用户名

    @JsonIgnore
    //让springmvc把当前对象转成json字符串的时候，忽略password
    private String password;//密码
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname;//昵称
    @NotEmpty
    @Email
    private String email;//邮箱
    private String userPic;//头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}


