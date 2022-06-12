package com.jahir.loggin.service;

import com.jahir.loggin.data.UsuarioDAO;
import com.jahir.loggin.domain.Usuario;
import jakarta.inject.Inject;

public class UsuarioServiceImpl implements UsuarioService{
    @Inject
    UsuarioDAO usuarioDAO;
    @Override
    public Usuario getUsuario(Usuario us) {
        return usuarioDAO.getUsuario(us);
    }

    @Override
    public void addUsuario(Usuario us) {
        usuarioDAO.newUser(us);
    }

    @Override
    public void userCheck(Usuario us) {
        usuarioDAO.validateUser(us);
    }
}
