package com.itheima.service;

import com.itheima.pojo.Category;

import java.util.List;

public interface CategoryService {
    //新增分类
    void add(Category category);
    //列表查询
    List<Category> list();
    //根据id（索引）查询文章详细信息
    Category findById(Integer id);
    //更新文章分类
    void update(Category category);

    //根据id删除信息

    void deleteById(Integer id);
}
