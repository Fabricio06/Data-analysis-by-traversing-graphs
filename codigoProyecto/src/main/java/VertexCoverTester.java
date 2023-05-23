/**
 * :____________________________________________:
 * :                                            : 
 * : Fecha de inicio: 04/09/2022-05:00 Pm       :
 * :                                            :
 * : Última modificación 28/09/2022-09:50 Pm    :
 * :____________________________________________:
 * 
 * @authors Fabricio Porras Morera y Carlos Solis Mora
 * Referencias de lo utilizado, en el documento escrito
 * 
 */
public class VertexCoverTester {

    /**
     * Metodo principal donde se declaran las funciones que se van a utilizar y las llamadas a las diferentes clases y metodos
     * @param args 
     */
    public static void main(String[] args) {
                byte totalVertices = 4; //Definir los vertices totales
                short totalAristas = 5; //Definir las aristas totales
                
                if(totalAristas>(totalVertices*(totalVertices-1)/2)){ //Compara que no se excedan las aristas para un numero x de vertices
                    return;
                }

                Aleatorio aleatorio = new Aleatorio(totalVertices,totalAristas); //Se crea el objeto de la clase Aleatorio con los vertices totales y aristas               
                Vertice[] vertices = aleatorio.verticesAleatorios(totalVertices); //Devuelve la lista con los numeros desde 1 hasta totalVertices
                Aristas [] aristas = aleatorio.aristasAleatorios(totalAristas, vertices); //Devuelve la lista con las relaciones de los vertices
                Grafo grafo = new Grafo(vertices, aristas);//Crea el grafo con los vertices y aristas correspondientes
                
                // Muestra la información del grafo
		System.out.println("# de vertices: " + grafo.numVertices());
		System.out.println(grafo);
                
                
		VertexCover vertexCover = new VertexCover(grafo, totalVertices);// crea el grafico y el limite número máximo de vértices aceptados en una cubierta
                vertexCover.heuristicaVoraz();// Corre la covertura del grafo con el algoritmo de heuristica voraz
                vertexCover.fuerzaBrutaOptimizado();// Corre la covertura del grafo con el algoritmo de fuerza bruta
                vertexCover.fuerzaBruta();// Corre la covertura del grafo con el algoritmo de fuerza bruta
			
	}
}
