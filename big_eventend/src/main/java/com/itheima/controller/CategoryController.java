
package com.itheima.controller;

import com.itheima.pojo.Category;
import com.itheima.pojo.Result;
import com.itheima.service.CategoryService;
import com.itheima.util.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    //注入Service层接口
    // CategoryService接口
    @Autowired
    private CategoryService categoryService;


    //新增分类
    /*@RequestBody json转换成实体类对象
     * 参数校验不仅要在实体类属性上进行注解校验，
     * 还要在接口方法上添加@Validated注解校验
     * */

    @PostMapping("/add")
    public Result add(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.add(category);
        return Result.success();
    }



    /*列表查询
    * 查询返回有多个Category对象，
    * 用list来封装数组对象
    *
    * */
    @GetMapping("/list")
    public Result<List<Category>> list() {
        List<Category> cs = categoryService.list();
        return Result.success(cs);
    }

    //根据id（索引）查询文章详细信息
    /*
    *指定泛型，响应前端浏览器主体数据是对象Category
    * 把它转换成json字符串
    * */
    @GetMapping("/detail")
    public Result<Category> detail(Integer id) {
        Category category = categoryService.findById(id);
        return Result.success(category);
    }

    //更新文章分类
    @PutMapping("/update")
    public Result update(@RequestBody @Validated(Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }

    //根据id删除信息
    @DeleteMapping("/deleteById")
    public Result deleteById(Integer id) {
        //从拦截器的THreadLocal获取登陆账号信息，从而解析Id
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer categoryId = (Integer) map.get("id");

        //校验传来的Id是否存在
        if (!categoryId.equals(id)) {
            return Result.error("id不存在");
        }
        categoryService.deleteById(id);
        return Result.success();
    }
}
