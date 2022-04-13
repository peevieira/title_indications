package br.com.pedro.indicados.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.pedro.indicados.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {	
	
	@Value("${indicados.jwt.expiration}")
	private String expiration;	
	
	@Value("${indicados.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authentication) {
		
		User logado = (User) authentication.getPrincipal();
		Date agora = new Date();
		Date expiracao = new Date(agora.getTime() + Long.parseLong(expiration));
		
		return Jwts
				.builder()
					.setIssuer("Forum da Alura")
					.setSubject(logado.getId().toString())
					.setIssuedAt(agora)
					.setExpiration(expiracao)
					.signWith(SignatureAlgorithm.HS256, secret)
					.compact();
	}
	
	public Boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
		} catch(Exception e) {
			return false;
		}		
		
		return true;		
	}
	
	public Long getIdUsuario(String token) {
		Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		
		return Long.parseLong(body.getSubject());
	}

}
