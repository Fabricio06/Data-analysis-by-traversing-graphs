import java.util.ArrayList;// imports library used to create lists of Vertices and Edges 

/**
 * Clase con la funcionalidad de simular el grafo con sus vertices y aristas
 */
public class Grafo {
	private ArrayList<Vertice> vertices; // Representa los vertices del grafo
	private ArrayList<Aristas> aristas;// Representa las aristas del grafo
	public long asignaciones = 0; //Contador de asignaciones
        public long comparaciones = 0; //Contador de comparaciones
        

        /**
         * Constructor para los vertices y aristas en forma de arrays
         * @param nuevosVertices
         * @param nuevasAristas 
         */
	public Grafo(Vertice[] nuevosVertices, Aristas[] nuevasAristas) { //No se contabilizan las asignaciones y comparaciones pues forma parte de la contrucción.
		
                aristas = new ArrayList<Aristas>(); // crea una nueva instancia para las aristas
		for (Aristas listaAristas : nuevasAristas) // recorre las aristas brindadas anteriormente
			this.aristas.add(listaAristas);// agrega cada objeto de la lista al grafo
		vertices = new ArrayList<Vertice>();// crea una nueva instancia para los vertices
		for (Vertice vertexList : nuevosVertices) //recorre los vertices brindados anteriormente
			this.vertices.add(vertexList);// Agrega cada objeto de los vertices al grafo
	}
        
	/**
         * Metodo que permite usuario obtener un vertice en especifico del grafo 
         * @param i
         * @return un vertice en especifico
         */
	protected Vertice getVertex(int i) {
                this.asignaciones++; //Por el return
		return vertices.get(i);
	}
	
	/**
         * Metodo que permite usuario obtener las aristas del grafo 
         * @param i
         * @return la lista de las aristas
         */
	protected ArrayList<Aristas> obtenerAristas() {
		ArrayList<Aristas> aristas = new ArrayList<Aristas>();// Crea la nueva instancia de aristas
		this.asignaciones++;
                for (Aristas arista : this.aristas){ //Recorre las aristas del grafo
			aristas.add(arista);//Copia las aristas y las agrega en una lista nueva de aristas
                        this.asignaciones+=2; //Se suma dos a las asignaciones
                        this.comparaciones++; //Se suma uno por la comparacion
                }
                this.comparaciones++; //Por la falsa
                this.asignaciones++; //Por el reutrn
                return aristas;//Devuelve la copia de las aristas
	}
	
	/**
         * Obtiene todos los vertices del grafo
         * @return la lista con los vertices
         */
	protected ArrayList<Vertice> obtenerVertices() {
		ArrayList<Vertice> vertices = new ArrayList<Vertice>();// Crea una lista nueva donde se van a agregar los vertices
		this.asignaciones++; //Se suma uno a las asignaciones
                for (Vertice vertex : this.vertices){//Recorre los vertices del grafo
			vertices.add(vertex);// Copia cada elemento de la lista de los grafos en una nueva lista
                        this.asignaciones+=2; //Suma dos a las asignaciones
                        this.comparaciones++; //Suma 1 a las comparaciones
                }
                this.comparaciones++; //Por la falsa del for
                this.asignaciones++; //por el return
                return vertices;// Devuelve la lista con los vertices
	}

	/**
         * Metodo que devuelve el numero total de vertices en una grafico
         * @return el numero total de vertices
         */
        protected int numVertices() {
                this.asignaciones++; //Por el return
		return vertices.size();//devuelve el numero total de vertices en el grafico
	}
	
        /**
         * Metodo que devuelve un texto con los vertices y aristas del grafico
         * @return un string 
         */
	public String toString() {
                this.asignaciones++; //Por el return
		return "Vertices: " + vertices.toString() + "\nAristas: " + aristas.toString(); //muestra la informacion de los vertices y aristas
	}
      
        /**
         * Obtiene el total de asignacioens que se hicieron durante el proceso de el vertexCover
         * @return long
         */
        public long getAsignaciones(){
            this.asignaciones++;//Por el return
            return this.asignaciones;
        }
        
         /**
         * Obtiene el total de comparaciones que se hicieron durante el proceso de el vertexCover
         * @return long
         */
        public long getComparaciones(){
            this.comparaciones++; //Por el return
            return this.comparaciones;
        }
        
        /**
         * Metodo que resetea los contadores de asignaciones y comparacioens
         */
        public void resetCounters(){
            this.asignaciones=0;
            this.comparaciones=0;
        }       
}
