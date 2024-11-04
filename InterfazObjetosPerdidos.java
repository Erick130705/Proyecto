import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazObjetosPerdidos extends JFrame {

    private GestorObjetos gestor;

    public InterfazObjetosPerdidos() {
        gestor = new GestorObjetos();
        
        // Configuración de la ventana
        setTitle("Gestión de Objetos Perdidos");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Colores y fuentes
        Color backgroundColor = new Color(240, 248, 255); // Color de fondo claro
        Color buttonColor = new Color(100, 149, 237);     // Color azul suave para los botones
        Color buttonText = Color.WHITE;
        Font buttonFont = new Font("Arial", Font.BOLD, 14);

        // Panel de botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40)); // Espaciado alrededor
        panel.setBackground(backgroundColor);

        // Creación de botones con estilo
        JButton btnRegistrar = crearBoton("Registrar Usuario", buttonColor, buttonText, buttonFont);
        JButton btnIniciarSesion = crearBoton("Iniciar Sesión", buttonColor, buttonText, buttonFont);
        JButton btnAgregar = crearBoton("Agregar Objeto", buttonColor, buttonText, buttonFont);
        JButton btnEliminar = crearBoton("Eliminar Objeto", buttonColor, buttonText, buttonFont);
        JButton btnBuscar = crearBoton("Buscar Objeto", buttonColor, buttonText, buttonFont);
        JButton btnMostrarTodos = crearBoton("Mostrar Todos", buttonColor, buttonText, buttonFont);

        // Acción de los botones
        btnRegistrar.addActionListener(e -> registrarUsuario());
        btnIniciarSesion.addActionListener(e -> iniciarSesion());
        btnAgregar.addActionListener(e -> agregarObjeto());
        btnEliminar.addActionListener(e -> eliminarObjeto());
        btnBuscar.addActionListener(e -> buscarObjeto());
        btnMostrarTodos.addActionListener(e -> mostrarTodosLosObjetos());

        // Agregar botones al panel
        panel.add(btnRegistrar);
        panel.add(btnIniciarSesion);
        panel.add(btnAgregar);
        panel.add(btnEliminar);
        panel.add(btnBuscar);
        panel.add(btnMostrarTodos);

        // Agregar panel a la ventana
        add(panel, BorderLayout.CENTER);

        // Estilo para la ventana principal
        getContentPane().setBackground(backgroundColor);
        setVisible(true);
    }

    private JButton crearBoton(String texto, Color bgColor, Color fgColor, Font font) {
        JButton button = new JButton(texto);
        button.setFocusPainted(false);
        button.setBackground(bgColor);
        button.setForeground(fgColor);
        button.setFont(font);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.DARK_GRAY),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        return button;
    }

    private void registrarUsuario() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del usuario:", "Registrar Usuario", JOptionPane.PLAIN_MESSAGE);
        String correo = JOptionPane.showInputDialog(this, "Correo del usuario:", "Registrar Usuario", JOptionPane.PLAIN_MESSAGE);
        String contrasena = JOptionPane.showInputDialog(this, "Contraseña del usuario:", "Registrar Usuario", JOptionPane.PLAIN_MESSAGE);
        
        UsuarioBD.registrarUsuario(nombre, correo, contrasena);
        JOptionPane.showMessageDialog(this, "Registro completado.", "Registro", JOptionPane.INFORMATION_MESSAGE);
    }

    private void iniciarSesion() {
        String correo = JOptionPane.showInputDialog(this, "Correo del usuario:", "Iniciar Sesión", JOptionPane.PLAIN_MESSAGE);
        String contrasena = JOptionPane.showInputDialog(this, "Contraseña del usuario:", "Iniciar Sesión", JOptionPane.PLAIN_MESSAGE);

        if (UsuarioBD.verificarUsuario(correo, contrasena)) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.", "Sesión Iniciada", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas. Inténtalo de nuevo.", "Error de Sesión", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarObjeto() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del objeto:", "Agregar Objeto", JOptionPane.PLAIN_MESSAGE);
        String descripcion = JOptionPane.showInputDialog(this, "Descripción:", "Agregar Objeto", JOptionPane.PLAIN_MESSAGE);
        String lugar = JOptionPane.showInputDialog(this, "Lugar encontrado:", "Agregar Objeto", JOptionPane.PLAIN_MESSAGE);
        String fecha = JOptionPane.showInputDialog(this, "Fecha encontrada (dd/mm/yyyy):", "Agregar Objeto", JOptionPane.PLAIN_MESSAGE);
        
        String tipoAccesorio = JOptionPane.showInputDialog(this, "Tipo de accesorio:", "Agregar Objeto", JOptionPane.PLAIN_MESSAGE);
        String color = JOptionPane.showInputDialog(this, "Color:", "Agregar Objeto", JOptionPane.PLAIN_MESSAGE);
        
        gestor.agregarObjeto(new Accesorio(nombre, descripcion, lugar, fecha, tipoAccesorio, color));
        JOptionPane.showMessageDialog(this, "Objeto agregado con éxito.", "Objeto Agregado", JOptionPane.INFORMATION_MESSAGE);
    }

    private void eliminarObjeto() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del objeto a eliminar:", "Eliminar Objeto", JOptionPane.PLAIN_MESSAGE);
        gestor.eliminarObjeto(nombre);
        JOptionPane.showMessageDialog(this, "Si existía, el objeto ha sido eliminado.", "Eliminar Objeto", JOptionPane.INFORMATION_MESSAGE);
    }

    private void buscarObjeto() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del objeto a buscar:", "Buscar Objeto", JOptionPane.PLAIN_MESSAGE);
        ObjetoPerdido objeto = gestor.buscarPorNombre(nombre);
        if (objeto != null) {
            JOptionPane.showMessageDialog(this, objeto.getNombre() + " encontrado: " + objeto.getDescripcion(), "Objeto Encontrado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Objeto no encontrado.", "Buscar Objeto", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrarTodosLosObjetos() {
        StringBuilder info = new StringBuilder();
        for (ObjetoPerdido obj : gestor.obtenerObjetos()) {
            info.append(obj.getNombre()).append("\n");
        }
        if (info.length() == 0) {
            JOptionPane.showMessageDialog(this, "No hay objetos almacenados.", "Objetos Perdidos", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, info.toString(), "Objetos Perdidos", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new InterfazObjetosPerdidos();
    }
}
