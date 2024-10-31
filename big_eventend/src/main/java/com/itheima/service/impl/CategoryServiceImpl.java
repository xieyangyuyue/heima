package com.itheima.service.impl;

import com.itheima.mapper.CategoryMapper;
import com.itheima.pojo.Category;
import com.itheima.service.CategoryService;
import com.itheima.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    /*
     * 注入Mapper层接口
     * */
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        //补充属性值
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        //获取登录者的ID，作为createUser的值
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer createUserId = (Integer) map.get("id");
        category.setCreateUser(createUserId);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        /*需要按照id来查询
        * 获取登录者的ID，作为createUser的值
        * */
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer createUserId = (Integer) map.get("id");

        return categoryMapper.list(createUserId);

    }

    @Override
    public Category findById(Integer id) {
        Category category = categoryMapper.findById(id);
        return category;
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    //根据id删除信息
    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }
}
