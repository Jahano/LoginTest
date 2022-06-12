package com.jahir.loggin.data;

import com.jahir.loggin.domain.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;



@Stateless
public class UsuarioDAOImpl implements UsuarioDAO{
    @PersistenceContext(unitName = "LoginPU")
    EntityManager em;
    final private String  GETUSERQUERY = "SELECT us FROM Usuario us WHERE us.username = :username";
    @Override
    public Usuario getUsuario(Usuario us) {

        return (Usuario) em.createQuery(GETUSERQUERY).setParameter("username" , us.getUsername()).getSingleResult();
    }

    @Override
    public void newUser(Usuario us) {
        us.setRegisterDate(new Date(System.currentTimeMillis()));
        em.persist(us);
    }

    @Override
    public void validateUser(Usuario us) {
        em.merge(us);
    }
}
