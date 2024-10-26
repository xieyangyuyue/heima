package com.itheima.pojo;

import com.itheima.anno.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Data
public class Article {
    private Integer id;
    @NotNull
    @Pattern(regexp = "^\\S{1,10}$")
    private String title;//文章标题
    @NotEmpty
    private String content;//内容
    @NotEmpty
    @URL
    private String coverImg;//封面图像
    @State
    private String state;//发布状态 （已发布，草稿）
    @NotNull
    private Integer categoryId;//文章分类Id
    private Integer createUser;//创建人Id
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;
}