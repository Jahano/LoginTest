package com.jahir.loggin.corsfilter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.apache.commons.codec.binary.Base64;
import org.glassfish.jersey.client.filter.CsrfProtectionFilter;


import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.StringTokenizer;

@Provider
public class ContextFilterAuthetification implements ContainerRequestFilter {
    private final static String AUTHORIZATION_HEADER_KEY = "Authorization";
    private final static String AUTHORIZATION_HEADER_PREFIX = "Authorization";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
        if(authHeader != null && authHeader.size() > 0){
            String authToken = authHeader.get(0);
            authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");

            byte[] decodeString = Base64.decodeBase64(authToken.getBytes());

            StringTokenizer tokenizer = new StringTokenizer(decodeString.toString() , ":");
            String username = tokenizer.nextToken();
            String password = tokenizer.nextToken();
        }
    }
}
