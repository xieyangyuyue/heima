/*
 * Copyright (c) 2024. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ArticleMapper;
import com.itheima.pojo.Article;
import com.itheima.pojo.PageBean;
import com.itheima.service.ArticleService;
import com.itheima.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        //补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        //获取登录者的ID，作为createUser的值
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer createUserId = (Integer) map.get("id");
        article.setCreateUser(createUserId);

        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, Integer state) {
        //1、创建PageBean对象，封装查询后的数据
        PageBean<Article> pb = new PageBean<>();
        //2、开启分页查询PageHelper
        PageHelper.startPage(pageNum, pageSize);

        //3、调用Mapper
        //获取登录者的ID，作为createUser的值
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer createUser = (Integer) map.get("id");

         // PageHelper完成查询后返回一个List实现类对象
        List<Article> as = articleMapper.list(createUser, categoryId, state);

        //强转成PageHelper返回的对像
        //page中提供方法，可以获取PageHelper分页查询，查到总记录条数和当前页数据
        Page<Article> p = (Page<Article>) as;

        //把数据填充到PageBean对象中
        pb.setTotal(p.getTotal());
        pb.setItems(p.getResult());
        return pb;
    }
}
