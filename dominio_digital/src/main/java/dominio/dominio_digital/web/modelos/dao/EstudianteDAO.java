package dominio.dominio_digital.web.modelos.dao;

import dominio.dominio_digital.web.bd.Conexion;
import dominio.dominio_digital.web.modelos.entidades.Estudiante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class EstudianteDAO {
    
    private Conexion conexion;

    public EstudianteDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    public ArrayList<Estudiante> getEstudiantes(){
        ArrayList<Estudiante> estudiantes = new ArrayList<>();
        
        try{
            String sql = "SELECT * FROM estudiantes";
            PreparedStatement pst;
            pst = this.conexion.getConexion().prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                Estudiante e = new Estudiante();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setNota1(rs.getFloat("nota1"));
                e.setNota2(rs.getFloat("nota2"));
                e.setNota3(rs.getFloat("nota3"));
                estudiantes.add(e);
            }
            
            rs.close();
            pst.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return estudiantes;
    }
    
    public Estudiante getEstudiante(int id){
        Estudiante estudiante = new Estudiante();
        
        try{
            String sql = "SELECT * FROM estudiantes WHERE id = ?";
            PreparedStatement pst;
            pst = this.conexion.getConexion().prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                
                estudiante.setId(rs.getInt("id"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setNota1(rs.getFloat("nota1"));
                estudiante.setNota2(rs.getFloat("nota2"));
                estudiante.setNota3(rs.getFloat("nota3"));
                
            }
            
            rs.close();
            pst.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return estudiante;
    }
    
    
    public Estudiante addEstudiante(Estudiante estudiante){
        
        try{
            String sql = "INSERT INTO estudiantes VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst;
            pst = this.conexion.getConexion().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1, 0);
            pst.setString(2, estudiante.getNombre());
            pst.setFloat(3, estudiante.getNota1());
            pst.setFloat(4, estudiante.getNota2());
            pst.setFloat(5, estudiante.getNota3());
            
            int filas = pst.executeUpdate();
            
            if(filas>0){
                ResultSet rs = pst.getGeneratedKeys();
                while(rs.next()){
                    int id = rs.getInt(1);
                    estudiante.setId(id);
                }
                rs.close();
            }
            
            pst.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return estudiante;
    }
    
    public boolean updateEstudiante(Estudiante estudiante){
        boolean resultado = false;
        try{
            String sql = "UPDATE estudiantes SET nombre = ?, nota1 = ?, nota2 = ?, nota3 = ? WHERE id = ?";
            PreparedStatement pst;
            pst = this.conexion.getConexion().prepareStatement(sql);
            pst.setString(1, estudiante.getNombre());
            pst.setFloat(2, estudiante.getNota1());
            pst.setFloat(3, estudiante.getNota2());
            pst.setFloat(4, estudiante.getNota3());
            pst.setInt(5, estudiante.getId());
            
            int filas = pst.executeUpdate();
            
            if(filas>0){
                resultado = true;
            }
            
            pst.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return resultado;
    }
    
    public boolean deleteEstudiante(int id){
        boolean resultado = false;
        try{
            String sql = "DELETE FROM estudiantes WHERE id = ?";
            PreparedStatement pst;
            pst = this.conexion.getConexion().prepareStatement(sql);
            pst.setInt(1, id);
            
            int filas = pst.executeUpdate();
            
            if(filas>0){
                resultado = true;
            }
            
            pst.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return resultado;
    }
    
    
    
}
