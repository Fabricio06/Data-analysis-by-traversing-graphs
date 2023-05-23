/**
 * Representa los vertices en el grafo con los numeros enteros en él
 */
public class Vertice {
	private int vertice; //El numero del vertice

        /**
         * Constructor del vertice con el valor numerico establecido
         * @param vertex numero entero enviado
         */
	public Vertice(int vertex) {
		this.vertice = vertex;//Le da el valor del int al vertice
	}
	
	/**
         * Constructor para muchos vertices
         * @param vertices 
         */
	public Vertice(int... vertices) {
		for (int vertice : vertices) {//Permite que cada entero enviado se conviertan en vertices de una vez
			new Vertice(vertice); // Crea un nuevo vertice para cada entero 
		}
	}
	
	/**
         * @return valor int del vertice
         */
	protected int getVertice() {
		return vertice;
	}
	
	/**
         * @return el valor impreso de un vertice 
         */
	public String toString() {
		return Integer.toString(vertice);
	}
}