public class Accesorio extends ObjetoPerdido {
    private String tipo;
    private String color;

    public Accesorio(String nombre, String descripcion, String lugarEncontrado, String fechaEncontrado, String tipo, String color) {
        super(nombre, descripcion, lugarEncontrado, fechaEncontrado);
        this.tipo = tipo;
        this.color = color;
    }


    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Tipo: " + tipo);
        System.out.println("Color: " + color);
    }
}