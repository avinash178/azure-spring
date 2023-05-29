package com.eom.security;

import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtUtils {
 public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60;
	 
	 
	 private String SECRET_KEY="secret";
	 
	 
	 public String getUsernameFromToken(String token) {
	        return getClaimFromToken(token, Claims::getSubject);
	    }

	    public Date getExpirationDateFromToken(String token) {
	        return getClaimFromToken(token, Claims::getExpiration);
	    }

	    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = getAllClaimsFromToken(token);
	        return claimsResolver.apply(claims);
	    }

	    private Claims getAllClaimsFromToken(String token) {
	        return Jwts.parser()
	                .setSigningKey(SECRET_KEY)
	                .parseClaimsJws(token)
	                .getBody();
	    }

	    private Boolean isTokenExpired(String token) {
	        final Date expiration = getExpirationDateFromToken(token);
	        return expiration.before(new Date());
	    }

	    public String generateToken(UserDetails user) {
	    	//Map<String , Object> claims=new HashMap<>();
	        return doGenerateToken(user);
	    }

	    private String doGenerateToken(UserDetails user) {

	        Claims claims = Jwts.claims().setSubject(user.getUsername());
	        claims.put("scopes", java.util.Arrays.asList(new SimpleGrantedAuthority(user.getAuthorities().toString())));

	        return Jwts.builder()
	                .setClaims(claims)
	                .setIssuer("http://devglan.com")
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000))
	                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
	                .compact();
	    }

	    public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = getUsernameFromToken(token);
	        return (
	              username.equals(userDetails.getUsername())
	                    && !isTokenExpired(token));
	    }

}
