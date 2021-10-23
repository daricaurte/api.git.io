package dominio.dominio_digital.web.recursos.filtros;

import dominio.dominio_digital.web.utilidades.Mensaje;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.io.IOException;
import java.security.Key;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class Autorizacion implements ContainerRequestFilter{

    public static final Key KEY = MacProvider.generateKey();
    
    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        String url = request.getUriInfo().getAbsolutePath().toString();
        
        if(url.contains("/api/usuarios/login")){
            System.out.println("entrando al login");
            return;
        }
        else{
            String hash = request.getHeaderString("Authorization");
            Mensaje mensaje = new Mensaje("NO_AUTORIZADO");
            
            if(hash==null || hash.trim().equals("")){
                
                request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                          .entity(mensaje)
                                          .type(MediaType.APPLICATION_JSON)
                                          .build());
                                                         
                return;        
            }
            
            try{
                Jws<Claims> claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(hash);
            }
            catch(MalformedJwtException e){
                request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                          .entity(mensaje)
                                          .type(MediaType.APPLICATION_JSON)
                                          .build());
                                                         
                return;
            }
            catch(SignatureException e){
                request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                          .entity(mensaje)
                                          .type(MediaType.APPLICATION_JSON)
                                          .build());
                                                         
                return;
            }
            catch(ExpiredJwtException e){
                request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                                          .entity(mensaje)
                                          .type(MediaType.APPLICATION_JSON)
                                          .build());
                                                         
                return;
            }
        }
        
    }
    
}
