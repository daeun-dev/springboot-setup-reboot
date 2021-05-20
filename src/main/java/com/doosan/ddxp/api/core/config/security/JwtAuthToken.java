package com.doosan.ddxp.api.core.config.security;



import java.security.Key;
import java.util.Date;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class JwtAuthToken implements AuthToken<Claims> {
	Logger logger = LoggerFactory.getLogger(JwtAuthToken.class);

    @Getter
    private final String token;
    private final Key key;

    //decode용
    JwtAuthToken(String token, Key key) {
        this.token = token;
       //this.key = secretKey;
        this.key = key;
    }

    JwtAuthToken(Key key) {
    	logger.info("key :"+ key);
    	 this.key = key;
    	 logger.info("key :"+ this.key);
         this.token = createJwtAuthToken().get();
         
    }

    JwtAuthToken(String subject, String id, Date expiredDate, Key key) {
        this.key = key;
        this.token = createJwtAuthToken(subject, id, expiredDate).get();
    }

    private Optional<String> createJwtAuthToken() {
		
        var token = Jwts.builder()
                .signWith(key)
                .compact();

        return Optional.ofNullable(token);
    }

    private Optional<String> createJwtAuthToken(String subject, String id, Date expiredDate) {
		
        var token = Jwts.builder()
        		.setIssuer("doosan.ddxp")								//발급자
    			.setIssuedAt(new Date(System.currentTimeMillis()))		//발급일시
    			.setExpiration(expiredDate)								//토큰 만료일시				
    			.setSubject(subject)									//토큰제목
                .claim("data", id) 										//계정정보					
                .setExpiration(expiredDate)
                .signWith(key)
                .compact();

        return Optional.ofNullable(token);
    }
    
    @Override
    public boolean validate() {
        return getData() != null;
    }

//    @Override
//    public Claims getData() {
//
//        try {
////        	return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
//        } catch (SecurityException e) {
//            log.info("Invalid JWT signature.");
////            throw new CustomJwtRuntimeException();
//        } catch (MalformedJwtException e) {
//            log.info("Invalid JWT token.");
////            throw new CustomJwtRuntimeException();
//        } catch (ExpiredJwtException e) {
//            log.info("Expired JWT token.");
////            throw new CustomJwtRuntimeException();
//        } catch (UnsupportedJwtException e) {
//            log.info("Unsupported JWT token.");
////            throw new CustomJwtRuntimeException();
//        } catch (IllegalArgumentException e) {
//            log.info("JWT token compact of handler are invalid.");
////            throw new CustomJwtRuntimeException();
//        }
//		return null;
//    }



@Override
public Claims getData() {
	// TODO Auto-generated method stub
	return null;
}
    
}