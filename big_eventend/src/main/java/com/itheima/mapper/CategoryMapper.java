package com.itheima.mapper;

import com.itheima.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    //新增
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time) " +
            "values (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);



    //根据ID查询所有
    @Select(" select * from category where create_user=#{createUserId}")
    List<Category> list(Integer createUserId);

    //根据id（索引）查询文章详细信息
    @Select("select * from category where id=#{id}")
    Category findById(Integer id);
    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias}," +
            "update_time=#{updateTime} where id=#{id}" )
    void update(Category category);

    @Delete(" delete from category where id=#{id}")
    void deleteById(Integer id);
}
