/**
 * Clase encargada de unir los vertices para crear las aristas
 */
public class Aristas {
	private Vertice vertice1; // Representa el primer vertice 
	private Vertice vertice2; // Representa el segundo vertice
	public long asignaciones = 0; //Contador de asignaciones
        public long comparaciones = 0; //Contador de comparaciones
        
	/**
         * Constructor para dos vertices
         * @param vertice1
         * @param vertice2 
         */
	public Aristas(Vertice vertice1, Vertice vertice2) {
		this.vertice1 = vertice1;// Asigna el valor del primer vertice, al inicio de la arista
		this.vertice2 = vertice2;// Asigna el valor del segundo vertice, al final de la arista
	}
		
	/**
         * Constructor para dos valores enteros
         * @param vertice1
         * @param vertice2 
         */
	public Aristas(int vertice1, int vertice2) {
		this.vertice1 = new Vertice(vertice1); // Asigna el valor del primer vertice al primer valor de la arista
		this.vertice2 = new Vertice(vertice2); // Asigna el valor del segundo vertice al segundo valor de la arista
	}
	
	/**
         * @return el primer valor(vertice) de la arista
         */
	public Vertice getVertex1() {
		return this.vertice1; //el primer valor(vertice) de la arista
	}
	
	/**
         * @return el segundo valor(vertice) de la arista
         */
	public Vertice getVertex2() {
		return this.vertice2; //el segundo valor(vertice) de la arista
	}
	

        /**
         * Metodo que permite determinar si una arista tiene un vertice en especifico
         * @param vertex
         * @return 
         */
	public boolean hasVertex(Vertice vertex) {
		if (this.vertice1.getVertice() == vertex.getVertice() || this.vertice2.getVertice() == vertex.getVertice()){
                        this.comparaciones +=2;
                        this.asignaciones +=4;
			return true;// Devuelve verdad si la arista si tiene ese vertice
                }
                else{
                        this.asignaciones++;
			return false; //Devuelve falso si no tiene ese vertice en la arista
                }
        }
	
	/**
         * Metodo que se encarga de imprimir la aritas completa
         * @return una cadena con el par ordenado de la arista con sus vertices
         */
	public String toString() {
		return "(" + vertice1 + ", " + vertice2 + ")";
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
