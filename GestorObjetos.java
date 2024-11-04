import java.util.ArrayList;

public class GestorObjetos {
    private ArrayList<ObjetoPerdido> almacen; 

    public GestorObjetos() {
        this.almacen = new ArrayList<>();
    }

    public void agregarObjeto(ObjetoPerdido objeto) {
        almacen.add(objeto);
        System.out.println("Objeto agregado: " + objeto.getNombre());
    }

    public void eliminarObjeto(String nombre) {
        ObjetoPerdido obj = buscarPorNombre(nombre);
        if (obj != null) {
            almacen.remove(obj);
            System.out.println("Objeto eliminado: " + nombre);
        } else {
            System.out.println("Objeto no encontrado: " + nombre);
        }
    }

    public ObjetoPerdido buscarPorNombre(String nombre) {
        for (ObjetoPerdido obj : almacen) {
            if (obj.getNombre().equalsIgnoreCase(nombre)) {
                return obj;
            }
        }
        return null;  
    }

    public void mostrarTodosLosObjetos() {
        if (almacen.isEmpty()) {
            System.out.println("No hay objetos almacenados.");
        } else {
            for (ObjetoPerdido obj : almacen) {
                obj.mostrarInfo();
                System.out.println();
            }
        }
    }

    public ArrayList<ObjetoPerdido> obtenerObjetos() {
        return almacen; 
    }
}
