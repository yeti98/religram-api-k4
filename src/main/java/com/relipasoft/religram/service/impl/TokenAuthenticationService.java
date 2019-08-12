package com.relipasoft.religram.service.impl;

import java.util.Date;

import com.relipasoft.religram.entity.UserDB;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenAuthenticationService {

	static final long EXPIRATIONTIME = 864_000_000; // 10 days
    
    static final String SECRET = "ThisIsASecret";
     
    static final String TOKEN_PREFIX = "Bearer";
     
    static final String HEADER_STRING = "Authorization";
  
    public static String getToken(UserDB user) {
        String JWT = Jwts.builder().setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
        return JWT;
    }
    
}
