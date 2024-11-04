public class ObjetoPerdido {
    protected String nombre;
    protected String descripcion;
    protected String lugarEncontrado;
    protected String fechaEncontrado;


    public ObjetoPerdido(String nombre, String descripcion, String lugarEncontrado, String fechaEncontrado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.lugarEncontrado = lugarEncontrado;
        this.fechaEncontrado = fechaEncontrado;
    }


    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getLugarEncontrado() { return lugarEncontrado; }
    public void setLugarEncontrado(String lugarEncontrado) { this.lugarEncontrado = lugarEncontrado; }

    public String getFechaEncontrado() { return fechaEncontrado; }
    public void setFechaEncontrado(String fechaEncontrado) { this.fechaEncontrado = fechaEncontrado; }

    
    public void mostrarInfo() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Descripción: " + descripcion);
        System.out.println("Lugar Encontrado: " + lugarEncontrado);
        System.out.println("Fecha Encontrado: " + fechaEncontrado);
    }
}