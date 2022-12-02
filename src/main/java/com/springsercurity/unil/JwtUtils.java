package com.springsercurity.unil;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.val;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtUtils {

    // jti：jwt的唯一身份标识
    public static final String JWT_ID = UUID.randomUUID().toString();
    // 加密密文，私钥
    public static final String JWT_SECRET = "chechong";
    /**
     * 过期时间，单位毫秒
     */
    public static final int EXPIRE_TIME =  60*60 * 1000;



    // 由字符串生成加密key
    public static SecretKey generalKey() {
        // 本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(JWT_SECRET);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }


    public static String getToken(Map<String,String> map){
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);        //过期时间
        JWTCreator.Builder builder = JWT.create();
        //payload
        map.forEach((k,v)->{
            builder.withClaim(k, String.valueOf(v));    //遍历存储payload信息
        });

        String token = null;  //设置令牌时间和签名加密
        try {
            token = builder.withExpiresAt(date)
                    .sign(Algorithm.HMAC256(JWT_SECRET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }

    public static String getToken(String value){
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);        //过期时间
        JWTCreator.Builder builder = JWT.create();
        //payload
        builder.withClaim("id",value);
        String token = null;  //设置令牌时间和签名加密
        try {
            token = builder.withExpiresAt(date)
                    .sign(Algorithm.HMAC256(JWT_SECRET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }


    /**
     * auth0.jwt验证token的有效性
     * */
    public static boolean verify(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(JWT_SECRET)).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("TOKEN失效");
        }
    }



    public static Claim getClaim(String token, String name) {
        Claim claim = null;
        Map<String, Claim> claims = JWT.decode(token).getClaims();
        claim = claims.get(name);
        return claim;
    }

    public static DecodedJWT getTokenInfo(String token){
        DecodedJWT decode = JWT.decode(token);
//        decode.getClaims().get("openId");
        JWT.decode(token).getPayload();
        return decode;
    }

}
