/*Esta clase representa un grafo dirigido de NumVertices, la
 *implementación fue realizada mediante matrices de adyacencia*/


public class Grafo {
    private final int NUM_VERTICES;
    private int[][] grafo;

    //Crea un grafo de numVertices
    //La matriz de adyacencia es inicializada con ceros.
    public Grafo(int numVertices){
        this.NUM_VERTICES = numVertices;
        grafo = new int[NUM_VERTICES][NUM_VERTICES];

        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[i].length; j++) {
                grafo[i][j] = 0;
            }
        }
    }

    public void insertarArista(int v1, int v2, int peso)
            throws ArrayIndexOutOfBoundsException{
        this.grafo[v1][v2] = peso;
        this.grafo[v2][v1] = peso;
    }

    public void imprimirGrafo(){
        String ANSI_RED = "\u001B[31m";
        String ANSI_RESET = "\u001B[0m";
        System.out.printf(ANSI_RED + "\td%d" , 1);
        for (int i = 1; i < grafo.length; i++) {
            System.out.printf("\td%d" , i+1);
        }
        System.out.println(ANSI_RESET);

        for(int i = 0; i < grafo.length; i++){
            System.out.printf(ANSI_RED + "d%d\t" + ANSI_RESET,i+1);
            for(int j = 0; j < grafo[i].length; j++){
                System.out.printf("%d\t" , grafo[i][j]);
            }
            System.out.println();
        }
    }

    public int[][] retornarMatrizAdjacencia(){
        return this.grafo;
    }

    public void caminoMinimoSinPeso(int[][] grafo, int src) {
        int[] dist = new int[this.NUM_VERTICES];
        // dist[i] guarda la distancia mas corta desde src hasta el vertice i

        boolean[] verticeYaProcesado = new boolean[this.NUM_VERTICES];
        //Este arreglo tiene true si el vertice i ya fue procesado

        // Initialize all distances as INFINITE and stpSet[] as false
        for (int i = 0; i < this.NUM_VERTICES; i++) {
            dist[i] = Integer.MAX_VALUE;
            verticeYaProcesado[i] = false;
        }
        // La distancia del vertice origen hacia el mismo es siempre 0
        dist[src] = 0;

        //Encuentra el camino mas corto para todos los vertices
        for (int count = 0; count < this.NUM_VERTICES-1; count++)
        {

            //Toma el vertice con la distancia minima del cojunto de vertices aun no procesados
            int u = distanciaMinima(dist, verticeYaProcesado);

            // Se marca como ya procesado
            verticeYaProcesado[u] = true;

            // Actualiza el valor de dist de los vértices adyacentes del vértice seleccionado.
            for (int v = 0; v < this.NUM_VERTICES; v++)
                if (!verticeYaProcesado[v] && grafo[u][v] > 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u]+1 < dist[v])
                    dist[v] = dist[u] + 1;
        }

        // se imprime el arreglo con las distancias
        printSolution(dist, src);
    }

    public void dijkstra(int inicio, int fin) {
        int[] dist = new int[this.NUM_VERTICES];
        // dist[i] guarda la distancia mas corta desde inicio hasta todos los diferentes vertices

        boolean[] verticeYaProcesado = new boolean[this.NUM_VERTICES];
        //Este arreglo tiene true si el vertice i ya fue procesado

        // Inicializa todas las distancias como INFINITE y stpSet [] como falso
        for (int i = 0; i < this.NUM_VERTICES; i++) {
            dist[i] = Integer.MAX_VALUE;
            verticeYaProcesado[i] = false;
        }
        // La distancia del vertice origen hacia el mismo es siempre 0
        dist[inicio] = 0;

        //Encuentra el camino mas corto para todos los vertices
        for (int count = 0; count < this.NUM_VERTICES-1; count++) {

            //Toma el vertice con la distancia minima del cojunto de vertices aun no procesados
            //En la primera iteracion siempre se devuelve src
            int u = distanciaMinima(dist, verticeYaProcesado);

            // Se marca como ya procesado
            verticeYaProcesado[u] = true;

            // Actualiza el valor dist de los vértices adyacentes del vértice seleccionado.
            for (int v = 0; v < this.NUM_VERTICES; v++)

                //Se actualiza la dist[v] solo si no esta en verticeYaProcesado, hay un
                //arco desde u a v y el peso total del camino desde src hasta v a traves de u es
                // mas pequeno que el valor actual de dist[v]
                if (!verticeYaProcesado[v] && this.grafo[u][v] > 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u]+this.grafo[u][v] < dist[v])
                    dist[v] = dist[u] + this.grafo[u][v];
        }

        // se imprime el arreglo con las distancias
        System.out.printf("Distancia desde d%d hasta d%d -> %d\n", inicio+1, fin+1, dist[fin]);
    }

    private void printSolution(int[] dist, int n) {
        for (int i = 0; i < this.NUM_VERTICES; i++)
            System.out.printf("Distancia entre d%d y d%d -> %d\n",n+1, i+1,dist[i]);
    }

    private int distanciaMinima(int[] dist, boolean[] verticeYaProcesado) {
        // Initialize min value
        int min = Integer.MAX_VALUE; int min_index=0;

        // acumula todas las dictancias de los vertices que no fueran procesados anteriormente
        for (int v = 0; v < this.NUM_VERTICES; v++)
            if (verticeYaProcesado[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }

}