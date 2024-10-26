package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import com.itheima.util.JwtUtil;
import com.itheima.util.Md5Util;
import com.itheima.util.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 1.@RestController是@ResponseBody和@Controller的组合注解。
 * 注解用于注入controller层 Bean对象
 * 2.@RequestMapping 配置url映射
 */
@RestController
@RequestMapping("/user")
@Validated
@CrossOrigin
class UserController {
    @Autowired
    private UserService userService;

    //注册

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password) {
        //查询用户
        User u = userService.findByUserName(username);
        if (u == null) {
            //没用占用
            //注册
            userService.register(username, password);
            return Result.success();
        } else {
            //占用
            return Result.error("用户名已被注册");
        }
    }

    //登录
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,
                                @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据用户名查询用户
        User loginUser = userService.findByUserName(username);
        //判断用户是否存在
        if (loginUser == null) return Result.error("用户名错误");
        //判断密码是否正确，loginUser的password是密文
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            //登陆成功
            //对登陆数据添加到claims，生成jwt令牌,
            //将登陆的id，username封装添加到载荷，生成 token
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        //密码错误
        return Result.error("密码错误");
    }

    //查询用户所有信息
    /*
     * @RequestHeader(name="Authorization")根据头部信息获取token令牌,
     *
     * */
    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name="Authorization")String token*/) {
       /* //根据用户名查询用户信息
        Map<String, Object> map = JwtUtil.parseToken(token);
       String username = (String) map.get("username");*/
        // ThreadLocalUtil.get()获取拦截器存储的数据（Map类型）
        //获取map的键为username的登录用户的姓名
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User byUserName = userService.findByUserName(username);
        return Result.success(byUserName);

    }

    /*更新用户信息
     *数据以请求体json格式携带过来
     * @RequestBody 将将请求体json格式转化成实体类对象
     *  @Validated 规范nickname，id，Email等字段所需注解
     *  @PutMapping 更新
     * */
    /*
     * @PatchMapping vs. @PutMapping：@PatchMapping 用于部分更新资源，
     * 而 @PutMapping 用于替换整个资源。@PatchMapping 请求仅包含要更新的部分内容，
     * 而 @PutMapping 请求包含完整的资源表述。
     * */
    /*
     * 参数校验不仅要在实体类属性上进行注解校验，
     * 还要在接口方法上添加@Validated注解校验
     * */
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }

    /*@RequestParam 数据从avatarUrl拿取
    @URL 校验是否为URL格式
    @PatchMapping 更新局部
    * */
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    /*更新密码
     * @RequestBody 将json转化成map集合对象
     * json键名中没有对应的实体类对象，采用map集合来接受参数
     * @PatchMapping 更新局部
     * */
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
        //1、校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要参数");
        }
        //原密码正确
        //调用userService根据用户名拿到原密码，和old_pwd比对
        /*username从ThreadLocal(拦截器，获取数据)获得
         * */
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        //获取登陆账号信息
        User loginUser = userService.findByUserName(username);
        if (!Md5Util.getMD5String(oldPwd).equals(loginUser.getPassword())) {
            return Result.error("原密码填写错误");
        }

        //校验newPwd，rePwd
        if (!newPwd.equals(rePwd)) {
            return Result.error("两次填写密码不一致");
        }

        //2、调用service完成密码更新
        userService.updatePwd(newPwd);
        return Result.success();
    }
}
