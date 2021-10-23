package dominio.dominio_digital.web.modelos.entidades;

public class Usuario {

    private int id;
    private String username;
    private String password;
    private String hash;

    public Usuario() {
    }

    public Usuario(int id, String username, String password, String hash) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.hash = hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    
    
}
