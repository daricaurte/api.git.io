package dominio.dominio_digital.web.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private Connection conexion;
    
    public Conexion(){
        this.conexion = null;
    }
    
    public Connection getConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/api", "root", "");
            
            System.out.println("Conexion exitosa");
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        
        return this.conexion;
    }
    
}
