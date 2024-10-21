import java.util.ArrayList;

class Proceso {
    private String nombre;
    private int prioridad;

    public Proceso(String nombre, int prioridad) {
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return nombre + " (Prioridad: " + prioridad + ")";
    }
}

class MonticuloMinimo {
    private ArrayList<Proceso> monticulo;

    public MonticuloMinimo() {
        monticulo = new ArrayList<>();
    }

    public void insertar(Proceso proceso) {
        monticulo.add(proceso);
        int i = monticulo.size() - 1;
        while (i > 0) {
            int padre = (i - 1) / 2;
            if (monticulo.get(i).getPrioridad() >= monticulo.get(padre).getPrioridad()) {
                break;
            }
            intercambiar(i, padre);
            i = padre;
        }
    }

    public Proceso eliminarMin() {
        if (monticulo.size() == 0) {
            return null;
        }

        Proceso min = monticulo.get(0);
        monticulo.set(0, monticulo.get(monticulo.size() - 1));
        monticulo.remove(monticulo.size() - 1);
        heapify(0);
        return min;
    }

    private void heapify(int i) {
        int menor = i;
        int izquierda = 2 * i + 1;
        int derecha = 2 * i + 2;

        if (izquierda < monticulo.size() && monticulo.get(izquierda).getPrioridad() < monticulo.get(menor).getPrioridad()) {
            menor = izquierda;
        }
        if (derecha < monticulo.size() && monticulo.get(derecha).getPrioridad() < monticulo.get(menor).getPrioridad()) {
            menor = derecha;
        }

        if (menor != i) {
            intercambiar(i, menor);
            heapify(menor);
        }
    }

    private void intercambiar(int i, int j) {
        Proceso temp = monticulo.get(i);
        monticulo.set(i, monticulo.get(j));
        monticulo.set(j, temp);
    }

    public boolean estaVacio() {
        return monticulo.isEmpty();
    }
}

public class SistemaDePrioridades {
    public static void main(String[] args) {
        MonticuloMinimo colaDePrioridad = new MonticuloMinimo();

        // Inserción de procesos con diferentes prioridades
        colaDePrioridad.insertar(new Proceso("Proceso1", 3));
        colaDePrioridad.insertar(new Proceso("Proceso2", 1));
        colaDePrioridad.insertar(new Proceso("Proceso3", 2));
        colaDePrioridad.insertar(new Proceso("Proceso4", 5));
        colaDePrioridad.insertar(new Proceso("Proceso5", 4));

        // Eliminación de procesos según su prioridad (el de menor prioridad primero)
        while (!colaDePrioridad.estaVacio()) {
            Proceso procesoAtendido = colaDePrioridad.eliminarMin();
            System.out.println("Atendiendo: " + procesoAtendido);
        }
    }
}
