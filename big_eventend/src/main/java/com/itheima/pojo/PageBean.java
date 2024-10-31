package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//封装分页查询的对像
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageBean<T> {
    public Long total;//总条数
    public List<T> items;//当前页数据集合
}
