package dominio.dominio_digital.web.recursos;

import dominio.dominio_digital.web.bd.Conexion;
import dominio.dominio_digital.web.modelos.dao.EstudianteDAO;
import dominio.dominio_digital.web.modelos.entidades.Estudiante;
import dominio.dominio_digital.web.utilidades.Mensaje;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/estudiantes")
public class EstudianteRecurso {
    
    Conexion conexion = new Conexion();
    EstudianteDAO estudianteDAO = new EstudianteDAO(conexion);
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Estudiante> getEstudiantes(){
        ArrayList<Estudiante> estudiantes = this.estudianteDAO.getEstudiantes();
        
        return estudiantes;
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Estudiante getEstudiante(@PathParam("id") int id){
        Estudiante estudiante = this.estudianteDAO.getEstudiante(id);
        return estudiante;
    }
       
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Estudiante addEstudiante(Estudiante estudiante){
        return this.estudianteDAO.addEstudiante(estudiante);        
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarEstudiante(Estudiante estudiante){
        Mensaje mensaje = new Mensaje();
        boolean resultado = this.estudianteDAO.updateEstudiante(estudiante);
        if(resultado){
            mensaje.setMensaje("SI");
        }
        else{
            mensaje.setMensaje("NO");
        }
        
        return mensaje;
    }
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje deleteEstudiante(@PathParam("id") int id){
        Mensaje mensaje = new Mensaje();
        
        boolean resultado = this.estudianteDAO.deleteEstudiante(id);
        
        if(resultado){
            mensaje.setMensaje("SI");
        }
        else{
            mensaje.setMensaje("NO");
        }
        
        return mensaje;
    
    }
    
}
