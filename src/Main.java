import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static int ingresarValor(String msg){
        int resultado = 0;
        boolean correcto = false;
        Scanner in = new Scanner(System.in);
        try {
            while (!correcto) {
                System.out.println(msg);
                resultado = in.nextInt();
                if (resultado < 1 || resultado > 13) {
                    System.out.println("Valor incorrecta, numeros aceptados 1-2-3-4-5-6-7-8-9-10-11-12-13 ");
                } else {
                    correcto = true;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Los valores ingresados deben ser numericos entre 1 y 13");
            ingresarValor(msg);
        }
        return resultado;
    }

    public static void main(String[] args) {
        Grafo g = new Grafo(13);

        // Agrego todos los vertices al grafo
        g.insertarArista(0,1,200);
        g.insertarArista(0,12, 250);
        g.insertarArista(0,8, 290);
        g.insertarArista(1,5, 360);
        g.insertarArista(1,2, 190);
        g.insertarArista(2,5, 250);
        g.insertarArista(2,4,190);
        g.insertarArista(2,0,300);
        g.insertarArista(3,2, 180);
        g.insertarArista(4,5,300);
        g.insertarArista(4,9, 400);
        g.insertarArista(5,10, 350);
        g.insertarArista(5,11, 300);
        g.insertarArista(6,3, 300);
        g.insertarArista(6,2, 250);
        g.insertarArista(6,0, 150);
        g.insertarArista(7,6, 200);
        g.insertarArista(7,0,220);
        g.insertarArista(8,7, 180);
        g.insertarArista(8,12, 180);
        g.insertarArista(9,3, 200);
        g.insertarArista(10,9, 700);
        g.insertarArista(10,4, 200);
        g.insertarArista(11,1, 150);
        g.insertarArista(12,11, 100);
        g.insertarArista(12,1, 200);

        // imprime matriz de adyacencia
        System.out.println("Se genera el grafo con la siguiente matriz de adyacencia: ");
        g.imprimirGrafo();
        int origen = ingresarValor("Ingrese el nodo origen para el calculo de los caminos minimos (1-2-3-4-5-6-7-8-9-10-11-12-13)");
        System.out.println("Caminos sin pesos a todos los nodos");
        g.caminoMinimoSinPeso(g.retornarMatrizAdjacencia(), origen - 1);
        System.out.println("Calculo de camino minimo usando el algoritmo de dijkstra");
        int inicio = ingresarValor("Ingrese nodo origen (1-2-3-4-5-6-7-8-9-10-11-12-13): ");
        int fin = ingresarValor("Ingrese nodo destino (1-2-3-4-5-6-7-8-9-10-11-12-13):");
        g.dijkstra(inicio-1, fin-1);

        int raiz = ingresarValor("Arbol: Ingrese nodo raiz (1-2-3-4-5-6-7-8-9-10-11-12-13):");
        Arbol arbol = new Arbol(raiz-1, g.retornarMatrizAdjacencia());
        arbol.imprimirArbol();
    }
}