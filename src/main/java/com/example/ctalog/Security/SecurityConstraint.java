package com.example.ctalog.Security;

public class SecurityConstraint {

    public static final String HEADER_STRING="Authorization";
    public static final String TOKEN_PREFIX="";
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 300000; // 5 min

}
