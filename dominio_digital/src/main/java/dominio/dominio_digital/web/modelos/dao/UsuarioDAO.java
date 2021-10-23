package dominio.dominio_digital.web.modelos.dao;

import dominio.dominio_digital.web.bd.Conexion;
import dominio.dominio_digital.web.modelos.entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UsuarioDAO {
    
    private Conexion conexion;

    public UsuarioDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    public Usuario login(Usuario usuario){
        
        
        try{
            String sql = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
            PreparedStatement pst;
            pst = this.conexion.getConexion().prepareStatement(sql);
            pst.setString(1, usuario.getUsername());
            pst.setString(2, usuario.getPassword());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){                
                usuario.setId(rs.getInt("id"));
            }
            
            rs.close();
            pst.close();
        }
        catch(SQLException e){
            System.out.println("Error: "+e.getMessage());
        }
        
        return usuario;
    }
    
    
}
