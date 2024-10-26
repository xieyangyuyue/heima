package com.itheima;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {
    @Test
    /**
     *创建了一个 Map 对象来存储JWT的载荷（payload）数据。在这个例子中，载荷包含了用户的ID和用户名。
     * 使用 JWT.create() 方法开始创建一个新的JWT。
     * withClaim("user", claims) 方法将载荷数据添加到JWT中。这里的 “user” 是一个自定义的键，claims 是包含用户信息的 Map。
     * withExpiresAt() 方法设置了JWT的有效期。在这个例子中，JWT将在12小时后过期。
     * sign() 方法用于签名JWT，它需要指定一个算法和一个密钥。在这个例子中，使用了HMAC SHA-256算法，密钥是字符串 “itheima”。
     * 最后，sign() 方法返回了生成的JWT字符串，然后这个字符串被打印到控制台。
     */
    public void testGen() {
        //创建了一个 Map 对象来存储JWT的载荷（payload）数据。在这个例子中，载荷包含了用户的ID和用户名。
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "张三");
        //生成JWT的代码
        String token = JWT.create()
                .withClaim("user", claims)//添加载荷
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))//有效时间12小时
                .sign(Algorithm.HMAC256("itheima"));//配置算法，密钥
        System.out.println(token);
    }

    @Test
    public void testParse() {
        //定义字符串，模拟用户传递token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9" +
                ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MTgxNDU2MzJ9" +
                ".Ce_MJS302GOOhEwvM-2dJ8-K5uugbbn95srLe3LX2uw";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("itheima")).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);//验证token，生成一个解析后的JWT对象
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));
        //如果篡改头部和载荷部分的数据据，那么验证失败
        //如果以密钥改了，验证失败
        //token过期

    }
}
