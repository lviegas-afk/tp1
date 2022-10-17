import java.util.ArrayList;
import java.util.List;

public class ArbolNodo {
    private int value;
    private List<ArbolNodo> hijos;

    public ArbolNodo(int valor) {
        this.value = valor;
        this.hijos = new ArrayList<>();
    }

    public ArbolNodo agregarHijo(int valor) {
        ArbolNodo nuevoNodo = new ArbolNodo(valor);
        this.hijos.add(nuevoNodo);
        return nuevoNodo;
    }

    public List<ArbolNodo> obtenerHijos(){
        return this.hijos;
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public String toString() {
        String hijo_string = "";
        for (ArbolNodo child : this.hijos){
            hijo_string = hijo_string + Integer.toString(child.getValue() + 1) + " - ";
        }
        if(hijo_string.length() > 3){
            hijo_string = hijo_string.substring(0,hijo_string.length() - 3);
        }
        String resultado =
                "d" + Integer.toString(this.value + 1)
                        + " -> Los hijos son [" + hijo_string + "]";

        return resultado;
    }
}