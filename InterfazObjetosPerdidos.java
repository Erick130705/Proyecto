import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InterfazObjetosPerdidos extends JFrame {

    private GestorObjetos gestor;

    public InterfazObjetosPerdidos() {
        gestor = new GestorObjetos();

        setTitle("Gestión de Objetos Perdidos");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(230, 240, 255));

        JPanel panelBotones = new JPanel(new GridLayout(6, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelBotones.setBackground(new Color(255, 255, 255));

        JButton btnRegistrar = crearBoton("Registrar Usuario");
        JButton btnIniciarSesion = crearBoton("Iniciar Sesión");
        JButton btnAgregar = crearBoton("Agregar Objeto");
        JButton btnEliminar = crearBoton("Eliminar Objeto");
        JButton btnBuscar = crearBoton("Buscar Objeto");
        JButton btnMostrarTodos = crearBoton("Mostrar Todos");

        btnRegistrar.addActionListener(e -> mostrarFormularioRegistro());
        btnIniciarSesion.addActionListener(e -> iniciarSesion());
        btnAgregar.addActionListener(e -> mostrarFormularioAgregarObjeto());
        btnEliminar.addActionListener(e -> eliminarObjeto());
        btnBuscar.addActionListener(e -> buscarObjeto());
        btnMostrarTodos.addActionListener(e -> mostrarObjetosPorCategoria());

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnIniciarSesion);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnMostrarTodos);

        mainPanel.add(panelBotones, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton crearBoton(String texto) {
        JButton button = new JButton(texto);
        button.setFocusPainted(false);
        button.setBackground(new Color(100, 149, 237));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 130, 180)),
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        ));
        return button;
    }

    private void mostrarFormularioRegistro() {
        JFrame frame = new JFrame("Registro de Usuario");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(new Color(245, 245, 245));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField(20);

        JLabel lblCorreo = new JLabel("Correo:");
        JTextField txtCorreo = new JTextField(20);

        JLabel lblContrasena = new JLabel("Contraseña:");
        JPasswordField txtContrasena = new JPasswordField(20);

        JLabel lblConfirmarContrasena = new JLabel("Confirmar Contraseña:");
        JPasswordField txtConfirmarContrasena = new JPasswordField(20);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBackground(new Color(34, 139, 34));
        btnRegistrar.setForeground(Color.WHITE);

        btnRegistrar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String correo = txtCorreo.getText();
            String contrasena = new String(txtContrasena.getPassword());
            String confirmarContrasena = new String(txtConfirmarContrasena.getPassword());

            if (!contrasena.equals(confirmarContrasena)) {
                JOptionPane.showMessageDialog(frame, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                UsuarioBD.registrarUsuario(nombre, correo, contrasena);
                JOptionPane.showMessageDialog(frame, "Registro completado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            }
        });

        formPanel.add(lblNombre);
        formPanel.add(txtNombre);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(lblCorreo);
        formPanel.add(txtCorreo);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(lblContrasena);
        formPanel.add(txtContrasena);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(lblConfirmarContrasena);
        formPanel.add(txtConfirmarContrasena);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(btnRegistrar);

        JScrollPane scrollPane = new JScrollPane(formPanel);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    private void mostrarFormularioAgregarObjeto() {
        JFrame frame = new JFrame("Agregar Objeto");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(this);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        formPanel.setBackground(new Color(245, 245, 245));

        JLabel lblNombre = new JLabel("Nombre del Objeto:");
        JTextField txtNombre = new JTextField(20);

        JLabel lblDescripcion = new JLabel("Descripción:");
        JTextField txtDescripcion = new JTextField(20);

        JLabel lblLugar = new JLabel("Lugar Encontrado:");
        JTextField txtLugar = new JTextField(20);

        JLabel lblFecha = new JLabel("Fecha Encontrado (dd/mm/yyyy):");
        JTextField txtFecha = new JTextField(20);

        JLabel lblTipoAccesorio = new JLabel("Tipo de Accesorio:");
        JTextField txtTipoAccesorio = new JTextField(20);

        JLabel lblColor = new JLabel("Color:");
        JTextField txtColor = new JTextField(20);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(new Color(34, 139, 34));
        btnAgregar.setForeground(Color.WHITE);

        btnAgregar.addActionListener(e -> {
            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();
            String lugar = txtLugar.getText();
            String fecha = txtFecha.getText();
            String tipoAccesorio = txtTipoAccesorio.getText();
            String color = txtColor.getText();

            gestor.agregarObjeto(new Accesorio(nombre, descripcion, lugar, fecha, tipoAccesorio, color));
            JOptionPane.showMessageDialog(frame, "Objeto agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
        });

        formPanel.add(lblNombre);
        formPanel.add(txtNombre);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(lblDescripcion);
        formPanel.add(txtDescripcion);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(lblLugar);
        formPanel.add(txtLugar);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(lblFecha);
        formPanel.add(txtFecha);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(lblTipoAccesorio);
        formPanel.add(txtTipoAccesorio);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(lblColor);
        formPanel.add(txtColor);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(btnAgregar);

        JScrollPane scrollPane = new JScrollPane(formPanel);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    private void mostrarObjetosPorCategoria() {
        JFrame frame = new JFrame("Objetos por Categoría");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(this);

        JPanel panelObjetos = new JPanel();
        panelObjetos.setLayout(new BoxLayout(panelObjetos, BoxLayout.Y_AXIS));
        panelObjetos.setBackground(new Color(245, 245, 245));

        // Map para organizar los objetos por categoría
        Map<String, StringBuilder> categorias = new HashMap<>();

        // Agrupación de objetos por categoría
        for (ObjetoPerdido obj : gestor.obtenerObjetos()) {
            categorias.computeIfAbsent(obj.getCategoria(), k -> new StringBuilder())
                      .append("- ").append(obj.getNombre()).append("\n");
        }

        // Agregar cada categoría y sus objetos a la interfaz
        for (Map.Entry<String, StringBuilder> entry : categorias.entrySet()) {
            JLabel lblCategoria = new JLabel(entry.getKey() + ":");
            lblCategoria.setFont(new Font("Arial", Font.BOLD, 14));
            lblCategoria.setForeground(new Color(100, 149, 237));

            JTextArea txtObjetos = new JTextArea(entry.getValue().toString());
            txtObjetos.setEditable(false);
            txtObjetos.setBackground(new Color(230, 240, 255));
            txtObjetos.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            panelObjetos.add(lblCategoria);
            panelObjetos.add(new JScrollPane(txtObjetos));
            panelObjetos.add(Box.createVerticalStrut(10));
        }

        JScrollPane scrollPane = new JScrollPane(panelObjetos);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    private void iniciarSesion() {
        String correo = JOptionPane.showInputDialog("Correo del usuario:");
        String contrasena = JOptionPane.showInputDialog("Contraseña del usuario:");

        if (UsuarioBD.verificarUsuario(correo, contrasena)) {
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas. Inténtalo de nuevo.");
        }
    }

    private void eliminarObjeto() {
        String nombre = JOptionPane.showInputDialog("Nombre del objeto a eliminar:");
        gestor.eliminarObjeto(nombre);
        JOptionPane.showMessageDialog(this, "Si existía, el objeto ha sido eliminado.");
    }

    private void buscarObjeto() {
        String nombre = JOptionPane.showInputDialog("Nombre del objeto a buscar:");
        ObjetoPerdido objeto = gestor.buscarPorNombre(nombre);
        if (objeto != null) {
            JOptionPane.showMessageDialog(this, objeto.getNombre() + " encontrado: " + objeto.getDescripcion());
        } else {
            JOptionPane.showMessageDialog(this, "Objeto no encontrado.");
        }
    }

    public static void main(String[] args) {
        new InterfazObjetosPerdidos();
    }
}

