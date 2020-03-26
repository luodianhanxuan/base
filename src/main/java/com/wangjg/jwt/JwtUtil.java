package com.wangjg.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.binary.Base64;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjg
 * 2020/3/16
 */
public class JwtUtil {

    private static final String EXP = "exp";

    private static final String PAYLOAD = "payload";

    /**
     * 加密生成token
     *
     * @param object 载体信息
     * @param maxAge 有效时长
     * @param secret 服务器私钥
     * @param <T>
     * @return
     */
    public static <T> String createToken(T object, long maxAge, String secret) {
        try {
            final Algorithm signer = Algorithm.HMAC256(secret);//生成签名
            String token = JWT.create()
                    .withIssuer("iquantex")
                    .withSubject("user")//主题，科目
                    .withClaim("userInfo", object.toString())
                    .withClaim("userInfo2", object.toString())
                    .withExpiresAt(new Date(System.currentTimeMillis() + maxAge))
                    .sign(signer);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解析验证token
     *
     * @param token  加密后的token字符串
     * @param secret 服务器私钥
     * @return
     */
    public static Boolean verifyToken(String token, String secret) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            System.out.println("校验失败");
        }
        return false;
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "小明");
        map.put("ege", 19);
        String jwt = createToken(map, 1000000000, "jwt.iquantex.secret");
        System.out.println(jwt);

        Boolean aBoolean = verifyToken(jwt, "jwt.iquantex.secret");

        System.out.println(aBoolean);

        String s1 = jwt.split("\\.")[1];

        String s = new String(Base64.decodeBase64(jwt.getBytes()));

        System.out.println(s);

        System.out.println(1 << 2);

    }
}
