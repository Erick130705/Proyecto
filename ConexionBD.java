import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexionBD {
    private String url;

    public ConexionBD() {
        this.url = "jdbc:sqlite:C:/Users/usr/Documents/Proyecto POO/Proyecto/usuarios.db";
        crearTabla();
    }

    public Connection conectar() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Conexión a la base de datos establecida.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios ("
                   + " id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + " nombre TEXT NOT NULL,"
                   + " correo TEXT NOT NULL,"  
                   + " contraseña TEXT NOT NULL);";
        try (Connection conn = this.conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabla usuarios creada o actualizada."); 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}

