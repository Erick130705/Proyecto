import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioBD {

    public static void registrarUsuario(String nombre, String correo, String contrasena) {
        String sql = "INSERT INTO usuarios (nombre, correo, contraseña) VALUES (?, ?, ?)";
        try (Connection conn = new ConexionBD().conectar(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setString(2, correo);
            pstmt.setString(3, contrasena);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean verificarUsuario(String correo, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE correo = ? AND contraseña = ?";
        try (Connection conn = new ConexionBD().conectar(); 
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, correo);
            pstmt.setString(2, contrasena);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); 
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
