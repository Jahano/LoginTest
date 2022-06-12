package com.jahir.loggin.data;


import com.jahir.loggin.domain.Usuario;

public interface UsuarioDAO {

    public Usuario getUsuario(Usuario us);
    public void newUser(Usuario us);
    public void validateUser(Usuario us);

}
