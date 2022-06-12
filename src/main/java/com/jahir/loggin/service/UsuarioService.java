package com.jahir.loggin.service;

import com.jahir.loggin.domain.Usuario;
import javax.ejb.Local;



@Local
public interface UsuarioService {
     Usuario getUsuario(Usuario us);
     void addUsuario(Usuario us);
     void userCheck(Usuario us);
}
