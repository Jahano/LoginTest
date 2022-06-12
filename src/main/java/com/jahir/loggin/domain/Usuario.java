package com.jahir.loggin.domain;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.Instant;

@Table(name = "tb_usuario")
@Entity
public class Usuario {

    public Usuario() {

    }

    public Usuario(String username) {
        this.username = username;
    }

    public Usuario(String username , String password , String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false, length = 40)
    private String username;

    @Column(name = "password", nullable = false, length = 40)
    private String password;

    @Column(name = "email", nullable = false, length = 40)
    private String email;

    @Column(name = "validated")
    private Boolean validated;

    @Column(name = "register_date", nullable = false)
    private Date registerDate;

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
