package com.jahir.loggin.api;


import com.jahir.loggin.domain.Usuario;
import com.jahir.loggin.service.UsuarioService;

import com.jahir.loggin.util.Tokenizer;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.HashMap;

@Path("/log")
public class LogginAPI {

    @Inject
    UsuarioService usuarioService;

    public LogginAPI() {
    }


    @GET
    @Path("/ping")
    @Produces(MediaType.APPLICATION_JSON)
    public Response pingtest(){
        return Response.ok().entity("Investigando como hacer ping").build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response userLogin(Usuario us , @Context HttpServletRequest rq) throws Exception {
        Usuario usuario = usuarioService.getUsuario(us);

        if (usuario != null){
            if(usuario.getPassword().equals(us.getPassword())){
                    return Response.ok().entity(Tokenizer.generateToken(us.getUsername() , us.getPassword())).build();
            }else{
                return Response.status(Response.Status.FORBIDDEN).entity("La contraseña no coincide").build();
            }
        }else{
            return Response.status(Response.Status.FORBIDDEN).entity("Usuario no encontrado").build();
        }
//        //TRy of login
//        System.out.println("Intento de logueo desde endpoint /login");
//        HttpSession session = rq.getSession(); ;
//        //Search for the user with the username provided
//        Usuario user = usuarioService.getUsuario(us);
//        //Verify that user != to null
//
//        if(user != null){
//            //verify that password be the same that provided by the client
//            if(user.getPassword().equals(us.getPassword())){
//                //Set new Attribute in the session with the user info
//                session.setAttribute("username" , us.getUsername());
//                session.setAttribute("login" , true);
//                System.out.println("Session has started with success");
//                String data[] = {"true" , "User has started session with success" };
//                return Response.ok().entity(data).build();
//            }
//            else{
//                String data[] = {"false" , "Wrong password, please verify and try again" };
//                session.setAttribute("login" , false);
//                return Response.ok().entity( data).build();
//            }
//        }else{
//            String data[] = {"false" , "User : "+ us.getUsername()+ " name not found" };
//            session.setAttribute("login" , false);
//            return Response.ok().entity(data).build();
//        }
    }

    @GET
    @Path("/checkSession")
    @Produces(MediaType.APPLICATION_JSON)
    public Response userLoginCheck(@Context HttpServletRequest rq){
        //Call the current session information
        HttpSession session = rq.getSession(false);
        HashMap data = new HashMap();
        //Verify if the session is new and return not session available
        if( session == null){
            data.put("login" , "false");
        }
        else{ //If session != null  & attribute 'username' exist, then call 'username' attribute from the session
            data.put("login" , "true");
            data.put("username" , session.getAttribute("username"));
        }
        return Response.ok().entity(data).build();
    }
    //This method don't work from web client app, pending for test
    //Maybe another problems with CORS configuration, working on solve
    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(Usuario us)
    {
        String msg;
        System.out.println(" \nUsername : " + us.getUsername() + " \nPassword: " + us.getPassword() + " \nEmail : " +  us.getEmail());
        try {
            usuarioService.addUsuario(us);
            msg = "User has been register, please check you email to confirm the account";
            return Response.ok().entity(msg).build();
        }catch (Exception ex){
            ex.printStackTrace(System.out);
            msg="An error has occurred, pleas try again latter ";
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(msg).build();
        }

    }

}
