package com.jahir.loggin;

import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;

public class Test {

    public static void main(String[] args) {

        String password = "password";

        byte[] token = password.getBytes();

        System.out.println("Token : " + token);

        byte[] tokenEncode = Base64.encodeBase64(token);

        System.out.println("Token Encode : " + tokenEncode);
        System.out.println("Token Encode : " + new String(tokenEncode , StandardCharsets.UTF_8));
        byte[] tokenDecode = Base64.decodeBase64(tokenEncode);

        System.out.println("Token Decode : " + tokenDecode);

        String tokenEnd = new String(tokenDecode , StandardCharsets.UTF_8);

        System.out.println("Token Final : " + tokenEnd);
    }
}
