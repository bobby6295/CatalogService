package com.example.ctalog.Security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.ctalog.Exception.HeaderNullPointerException;
import com.example.ctalog.Security.SecurityConstraint;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {


    private  AuthenticationManager authenticationManager;
    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
       super(authenticationManager);

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException,HeaderNullPointerException {


        String header = request.getHeader(SecurityConstraint.HEADER_STRING);


        if (header==null||!header.startsWith(SecurityConstraint.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        try {


            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = Jwts.builder().
                    setSubject(authentication.getName())
                    .setExpiration(new Date(System.currentTimeMillis() + SecurityConstraint.EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SecurityConstraint.SECRET.getBytes())
                    .compact();
            response.addHeader(SecurityConstraint.HEADER_STRING, SecurityConstraint.TOKEN_PREFIX + token);

            System.err.println("response catalog"+token);
            chain.doFilter(request, response);
        }
        catch (NullPointerException e)
        {
            logger.info(e.getMessage());
        }
        catch (Exception e)
        {
            logger.info(e.getMessage());

        }





    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(SecurityConstraint.HEADER_STRING);

        if (token != null) {
            // parse the token.
            String user = Jwts.parser()
                    .setSigningKey(SecurityConstraint.SECRET.getBytes())
                    .parseClaimsJws(token.replace(SecurityConstraint.TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject()
                    ;
            Long expirationTime = Jwts.parser()
                    .setSigningKey(SecurityConstraint.SECRET.getBytes())
                    .parseClaimsJws(token.replace(SecurityConstraint.TOKEN_PREFIX, ""))
                    .getBody()
                    .getExpiration().getTime();

            Long currenttime = System.currentTimeMillis();

            System.err.println("Current TimeMiless " + currenttime);
            if (expirationTime-currenttime >= 0) {

                System.err.println(expirationTime-currenttime >= 0);
                if (user != null) {

                    return new UsernamePasswordAuthenticationToken(user, 0, new ArrayList<>());

                }
                return null;

            } else {

                System.err.println("Session Expire");
                return null;

            }


        }
        return null;
    }


}
