import java.util.HashSet;
import java.util.Set;

public class Arbol {

    private int[][] matriz;
    private ArbolNodo raiz;

    public Arbol(int raiz, int[][] matriz){
        this.matriz = matriz;
        boolean[] verticeYaProcesado = new boolean[this.matriz.length];
        for (int i = 0; i < this.matriz.length; i++) {
            verticeYaProcesado[i] = false;
        }

        //Genero el nodo raiz y se marca como ya procesado
        this.raiz = new ArbolNodo(raiz);
        verticeYaProcesado[raiz] = true;
        Set<ArbolNodo> nodos = new HashSet<>();
        nodos.add(this.raiz);

        this.procesarArbol(nodos,verticeYaProcesado);
    }

    private void procesarArbol(Set<ArbolNodo> nodos, boolean[] verticeYaProcesado) {
        Set<ArbolNodo> nodos_tmp = new HashSet<>();
        // Recorro todos los nodos hijos para generar el nodo del arbol correspondiente a cada uno
        for (ArbolNodo nodo : nodos){
            for(int i = 0; i < this.matriz.length; i++){
                if (this.matriz[nodo.getValue()][i] != 0 && !verticeYaProcesado[i]){
                    // agrega los nodos al padre y los marca como procesados
                    nodos_tmp.add(nodo.agregarHijo(i));
                    verticeYaProcesado[i] = true;
                }
            }
        }
        // al no crear nodos nuevos se da por terminada la recursividad
        if(!nodos_tmp.isEmpty()){
            this.procesarArbol(nodos_tmp,verticeYaProcesado);
        }
    }

    public void imprimirArbol() {
        this.imprimirArbol(this.raiz);
    }

    private void imprimirArbol(ArbolNodo nodo) {
        if(!nodo.obtenerHijos().isEmpty()) {
            for (ArbolNodo child : nodo.obtenerHijos()){
                this.imprimirArbol(child);
            }
        }
        System.out.println(nodo);
    }
}
