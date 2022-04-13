package br.com.pedro.indicados.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.pedro.indicados.models.User;
import br.com.pedro.indicados.repository.UserRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private UserRepository userRepository;
	
	public AutenticacaoViaTokenFilter(TokenService tokenService, UserRepository userRepository) {		
		this.tokenService = tokenService;
		this.userRepository = userRepository; 
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token = recuperarToken(request);	
		Boolean valido = tokenService.isTokenValido(token);	
		
		if (valido) {
			autenticarCliente(token);
		}
		
		filterChain.doFilter(request, response);		
	}

	private void autenticarCliente(String token) {	
		Long id = tokenService.getIdUsuario(token);
		User usuario = userRepository.findById(id).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities()); 
		SecurityContextHolder.getContext().setAuthentication(authentication);		
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if ((token == null) || (token.isEmpty()) || (!token.startsWith("Bearer"))) {
			return null;
		}
		
		return token.substring(7, token.length());
	}

}
