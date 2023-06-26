package com.hmy.staybooking.filter;

import com.hmy.staybooking.model.Authority;
import com.hmy.staybooking.repository.AuthorityRepository;
import com.hmy.staybooking.util.JwtUtil;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

// By using the Spring Security framework, we enabled the Spring Security filters for
// user authentication and authorization. User credentials(username and password) are
// provided in the login request body and sent to the Spring Security filters.

// For this project, since the client uses the JWT token as the credential, we need to
// create a filter before the Spring Security filters so that we can decrypt the JWT token,
// make sure the token is valid, get the user information from the token and feed it to
// the Spring Security filters only for user authorization.

// Make JwtFilter extends the OncePerRequestFilter interface and implement doFilterInternal method.

@Component
public class JwtFilter extends OncePerRequestFilter {
    private static final String HEADER = "Authorization";
    private static final String PREFIX = "Bearer ";

    private final AuthorityRepository authorityRepository;
    private final JwtUtil jwtUtil;

    public JwtFilter(AuthorityRepository authorityRepository, JwtUtil jwtUtil) {
        this.authorityRepository = authorityRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = httpServletRequest.getHeader(HEADER);

        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith(PREFIX)) {
            jwt = authorizationHeader.substring(PREFIX.length());
        }

        if (jwt != null && jwtUtil.validateToken(jwt) && SecurityContextHolder.getContext().getAuthentication() == null) {
            String username = jwtUtil.extractUsername(jwt);
            Authority authority = authorityRepository.findById(username).orElse(null);
            if (authority != null) {
                List<GrantedAuthority> grantedAuthorities = Arrays.asList(new GrantedAuthority[]{new SimpleGrantedAuthority(authority.getAuthority())});
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        username, null, grantedAuthorities);
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}

