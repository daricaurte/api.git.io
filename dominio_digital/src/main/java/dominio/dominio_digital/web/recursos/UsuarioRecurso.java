package dominio.dominio_digital.web.recursos;

import dominio.dominio_digital.web.bd.Conexion;
import dominio.dominio_digital.web.modelos.dao.UsuarioDAO;
import dominio.dominio_digital.web.modelos.entidades.Usuario;
import dominio.dominio_digital.web.recursos.filtros.Autorizacion;
import dominio.dominio_digital.web.utilidades.Mensaje;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.ArrayList;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/usuarios")
public class UsuarioRecurso {
    
    Conexion conexion = new Conexion();
    UsuarioDAO usuarioDAO = new UsuarioDAO(conexion);
           
    @POST  
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario login(Usuario usuario){
        Usuario u = this.usuarioDAO.login(usuario);        
                
        if(u.getId()!=0){
            long tiempo = System.currentTimeMillis();
            
            String hash = Jwts.builder()
                              .signWith(SignatureAlgorithm.HS256, Autorizacion.KEY)
                              .setSubject(u.getUsername())
                              .setIssuedAt(new Date(tiempo))
                              .setExpiration(new Date(tiempo + 120000))
                              .claim("username", u.getUsername())
                              .compact();
            u.setPassword("");
            u.setHash(hash);
            
        }
        
        return u;
    }
    
    
}
