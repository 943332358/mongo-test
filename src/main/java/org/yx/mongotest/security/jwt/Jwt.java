package org.yx.mongotest.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author yangxin
 */
public class Jwt {
    public static void main(String[] args) throws InterruptedException {
        final String privateKey = "123456789";

        String jwt = Jwts.builder().signWith(SignatureAlgorithm.HS256, privateKey)
                .setExpiration(Date.from(LocalDateTime.now().plusSeconds(3).atZone(ZoneId.systemDefault()).toInstant()))
                .claim("user1", "zs")
                .claim("user2", "ls")
                .claim("user3", "ww")
                .compact();

        System.out.println(jwt);

        Thread.sleep(1000);

        Claims claims = Jwts.parser().setSigningKey("12345678")
                .parseClaimsJws(jwt)
                .getBody();


        System.out.println(claims.get("user1", String.class));
        System.out.println(claims.get("user2", String.class));
        System.out.println(claims.get("user3", String.class));

    }
}
