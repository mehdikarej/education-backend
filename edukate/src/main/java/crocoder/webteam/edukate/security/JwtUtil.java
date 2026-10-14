package crocoder.webteam.edukate.security;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import crocoder.webteam.edukate.models.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
@Component
public class JwtUtil {
	private final String secret_key ="mysecretkeyisnotwhatyouthinkaboutbrodontworryaboutithahahahahahahahahahahahahaha";
	private final JwtParser jwtParser;
	private final String TOKEN_HEADER ="Authorization";
	private final String TOKEN_PREFIX ="Bearer ";

	@SuppressWarnings("deprecation")
	public JwtUtil() {
		this.jwtParser = Jwts.parser().setSigningKey(secret_key);
	}

	public String createToken(UserDetails userDetails, crocoder.webteam.edukate.models.User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId",user.getId());
		claims.put("username", userDetails.getUsername());
		claims.put("firstName", user.getFirstName());
		claims.put("lastName", user.getLastName());
		claims.put("email", user.getEmail());
        claims.put("phoneNumber",user.getPhoneNumber());
        claims.put("password",user.getPassword());
        claims.put("address",user.getAddress());
        claims.put("specialty",user.getSpecialty());
        claims.put("experience",user.getExperience());
        claims.put("cv",user.getCv());
        claims.put("photo",user.getPhoto());
        
		List<String> roleNames = user.getRoles().stream().map(Role::getName) 
				.collect(Collectors.toList());

		claims.put("roles", roleNames); 

		return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, secret_key).compact();
	}

	public String createPasswordToken(UserDetails user) {
		Claims claims = Jwts.claims().setSubject(user.getUsername());
		claims.setAudience("password");
		Date tokenCreateTime = new Date();
		Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MILLISECONDS.toMillis(1000 * 60 * 30));
		return Jwts.builder().setClaims(claims).setExpiration(tokenValidity)
				.signWith(SignatureAlgorithm.HS256, secret_key).compact();
	}

	public boolean isPasswordToken(String token) {
		return Objects.equals(parseJwtClaims(token).getAudience(), "password");
	}

	public String getEmailFromToken(String token) throws ExpiredJwtException, UnsupportedJwtException,
			MalformedJwtException, SignatureException, IllegalArgumentException {
		Claims c = parseJwtClaims(token);
		return c.getSubject();
	}

	private Claims parseJwtClaims(String token) throws ExpiredJwtException, UnsupportedJwtException,
			MalformedJwtException, SignatureException, IllegalArgumentException {
		return jwtParser.parseClaimsJws(token).getBody();
	}

	public Claims resolveClaims(HttpServletRequest req) {
		try {
			String token = resolveToken(req);
			if (token != null) {
				return parseJwtClaims(token);
			}
			return null;
		} catch (ExpiredJwtException ex) {
			req.setAttribute("expired", ex.getMessage());
			throw ex;
		} catch (Exception ex) {
			req.setAttribute("invalid", ex.getMessage());
			throw ex;
		}
	}

	public String resolveToken(HttpServletRequest request) {

		String bearerToken = request.getHeader(TOKEN_HEADER);
		if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
			return bearerToken.substring(TOKEN_PREFIX.length());
		}
		return null;
	}

	public boolean validateClaims(Claims claims) throws AuthenticationException {
		try {
			return claims.getExpiration().after(new Date());
		} catch (Exception e) {
			throw e;
		}
	}
}
