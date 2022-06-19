package com.jahir.loggin.data;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConexion {
    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("postgres://jxeguigxoekpur:552f20e8db4a601644c8335596a9fd79762d5d4688b2e6cb3c4bc8d1050a43fa@ec2-52-44-13-158.compute-1.amazonaws.com:5432/dsnm5hmgn0muq"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }

}
