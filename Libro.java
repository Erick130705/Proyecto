public class Libro extends ObjetoPerdido {
    private String autor;
    private String editorial;

    public Libro(String nombre, String descripcion, String lugarEncontrado, String fechaEncontrado, String autor, String editorial) {
        super(nombre, descripcion, lugarEncontrado, fechaEncontrado);
        this.autor = autor;
        this.editorial = editorial;
    }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getEditorial() { return editorial; }
    public void setEditorial(String editorial) { this.editorial = editorial; }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Autor: " + autor);
        System.out.println("Editorial: " + editorial);
    }
}
