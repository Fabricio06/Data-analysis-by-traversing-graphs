import java.time.Duration;// Importacion para tomar las mediciones en milisegundos
import java.time.Instant;// Importacion para tomar los tiempos de inicio y final 
import java.util.ArrayList;// Importacion para crear listas
import java.util.HashMap;// Importacion para para ver el numero de veces que un grafo aparece en un grafo
import java.util.Map;// Importacion para para ver el numero de veces que un grafo aparece en un grafo

/**
 * Clase que se usa para ejecutar los diferentes algoritmos de recubrimiento de grafos
 */
public class VertexCover {
        private long asignaciones = 0; //Para llevar la cuenta de asignacion
        private long comparaciones = 0; //Para llevar la cuenta de comparaciones
	
        private Grafo grafo; //Representa el grafo enviado con sus vertices y aristas
	private int k; //Representa el numero maximo de vertices aceptamos en un recubrimiento
	private int min; // Representa el numero minimo de vertices en un recubrimiento (fuerzaBrutaOptimizado)
	private ArrayList<Vertice> cover;// representa los vértices de un conjunto de cobertura
	ArrayList<ArrayList<Vertice>> posibleCovers = new ArrayList<ArrayList<Vertice>>();// representa los posibles conjuntos de cobertura
	
        
	/**
         * Constructor de la clase
         * @param grafo
         * @param k 
         */
	public VertexCover(Grafo grafo, int k) {
		this.grafo = grafo;//Asigna el grafo creado anteriormente
		this.k = k;// Asigna el limite del numero maximo de vertices por recubrimiento               
        }
	
        /**
         * Permite remover aristas que contienen un vertice en especifico
         * @param vertice
         * @param listaAristas 
         */
	protected void removerAristas(Vertice vertice, ArrayList<Aristas> listaAristas) {
		ArrayList<Aristas> removerArista = new ArrayList<Aristas>();// Crea una nueva instancia para las aristas
		asignaciones+=3;
                for (int i = 0; i < listaAristas.size(); i++) {// Reccorre la listaAristas con el valor que estamos buscando
			if ((listaAristas.get(i)).hasVertex(vertice)){// Revisa si la arista actual tiene el el vertice en especifico
				removerArista.add(listaAristas.get(i));//Agrega la arista a la lista de remover
                                asignaciones++;
                        }
                asignaciones += listaAristas.get(i).getAsignaciones();
                comparaciones += listaAristas.get(i).getComparaciones();
                listaAristas.get(i).resetCounters();
                        
                asignaciones++;
                comparaciones+=2;
                
                }
                comparaciones++;
		for (int i = 0; i < removerArista.size(); i++){// Recorre la lista de arista a eliminar
			listaAristas.remove(removerArista.get(i));// Elimina la arista con el vertice en especifico
                        asignaciones+=2;
                        comparaciones++;
                    }
                comparaciones++;
                }
	
	/**
         * Obtiene el vertice con mayor cantidad de aristas
         * @param vertices
         * @param aristas
         * @return el vertice con mayor cantidad de aristas
         */
	protected Vertice obtenerVerticeMaximo(ArrayList<Vertice> vertices, ArrayList<Aristas> aristas) {
                Map<Vertice, Integer> map = new HashMap<Vertice, Integer>();// Variable encarga de mantener el vertice con su contador de apariciones
		int max = 0;// contador
		Vertice maxVertice = new Vertice();// Variable para guardar el vertice con mayor cantidad de aristas 
		asignaciones+=7; //2 por los parametros, 1 creacion del mapa, 1 por el int, y 3 por la creacion del vértice (asignacion maxVertice y 2 por el constructor)
                for (Vertice vertice : vertices) {// Recorre la lista de vertices restantes en la busqueda
			map.put(vertice,  0);//El vertice estará con un contador inicial de 0
			for (Aristas arista : aristas) {// Recorre la lista de aristas restantes en la busqueda
				if (arista.hasVertex(vertice)) {//Determina si el vertice tiene el eje en especifico
					map.put(vertice,map.get(vertice) + 1);//La arista que contiene ese vertice se aumenta +1
                                        asignaciones++;
                                }
                                comparaciones+=2;
                                asignaciones++;
                                asignaciones += arista.getAsignaciones();
                                comparaciones += arista.getComparaciones();
                                arista.resetCounters();
			}
                        comparaciones++; //Por la falsa del for
			if (map.get(vertice) > max) {// Revisa si el vertice actual le gana al vertice maximo
				max = map.get(vertice);// Se cambia a la nueva cantidad maxima
				maxVertice = vertice;//  Si es asi, se sustituye cual es el vertice maximo
                                asignaciones+=2;
                        }
                        comparaciones+=2;
                        asignaciones+=2;
		}
                comparaciones++; //Por la falsa del for
                asignaciones++; //Por el return
		return maxVertice; //Revuelve el vertice con más aristas asociadas
	}
	
        /**
         * Permite que se obtengan todas las posibles combinaciones de recubrimiento -Metodo recursivo-
         * @param vertices
         * @param k
         * @param actual
         * @param actualTot
         * @param visit 
         */
	protected void combinaciones(ArrayList<Vertice> vertices, int k, ArrayList<Vertice> actual, int actualTot, boolean[] visit) {
                asignaciones+=5;
		if (actualTot >= k-1) {//Determina si las permutaciones actuales son igual o mas que k                       
			ArrayList<Vertice> temp = new ArrayList<Vertice>();// Crea un temporal para la permutacion actual
                        asignaciones++;
                        for (int i = 0; i < actual.size(); i++){//Recorre las permutaciones actuales
				temp.add(actual.get(i));//Agrega cada vertice en la permutacion actual 
                                asignaciones+=2;
                                comparaciones++;
                        }
                        comparaciones++;
                        posibleCovers.add(temp);// Agrega la permutacion de vertice actual a la lista de posibles recubrimientos
                        asignaciones+=2; //1 por el return;
			return;// Devuelve la iteracion actual de una manera recursiva
		}
                comparaciones++;
		// Recorre los vertices del grafo
		for (int i = 0; i < vertices.size(); i++) {
			if(!visit[i]) {//Revisa si el vertice ya fue visitado
				actual.set(++actualTot, vertices.get(i));// Sino, agrega el vertice en la permutacion actual e incremente el contador de vertices			
                                visit[i] = true;//Coloca el vertice en visitado
				combinaciones(vertices, k, actual, actualTot, visit);// Recursivamente se vuelve a llamar con valores actualizados
				visit[i] = false;// Resetea los vertices y los coloca en no visitados
				actualTot--;// Disminuye el contador de vertices
                                asignaciones+=3;
			}
                    comparaciones+=2;
                    asignaciones++;
		}
                comparaciones++;               
	}
	
        /**
         * Algoritmo de fuerza brutas para encontrar una manera optima de recubrir un grafo
         * de 'k' o menos vertices
         */
	protected void fuerzaBruta() {
                this.grafo.resetCounters();
		Instant tiempoInicial = Instant.now();//Obtiene la tiempo actual (no cuenta en la contabilizacion de pasos)
		posibleCovers = new ArrayList<ArrayList<Vertice>>();// Crea una lista de posibles recubrimientos
		ArrayList<Vertice> v = new ArrayList<Vertice>();//Crea una nueva lista para usar los metodos de combinaciones
                asignaciones+=2;
		for(int i = 0; i < this.k; i++) {// Recorre k valores
			v.add(new Vertice(i));//Agrega el nuevo vertice el cual mantiene cada valor de las permutacion de los vertices
                    asignaciones+=4; //2 asignaciones por la construcción del nuevo vértice;
                    comparaciones++;
		}
                comparaciones++;
		boolean[] visit = new boolean[grafo.obtenerVertices().size()];//Crea una lista de boolean para usar con el metodo de combinaciones
		asignaciones++;
                for(int i = 0; i < grafo.obtenerVertices().size(); i++) {//Recorre el numero de vertices en el grafo
			visit[i] = false; //Inicializa todos los valores como false
                    asignaciones+=2;
                    comparaciones++;
                }
                comparaciones++; //Por la falsa de for
                
		this.combinaciones(grafo.obtenerVertices(), this.k, v, -1, visit);// llama el metodo de combinaciones para establecer todas las posibles permutaciones en el recubrimiento del grafo
		ArrayList<Aristas> aristas = grafo.obtenerAristas();//Obtiene todas las aristas del grafo
                cover = new ArrayList<Vertice>();//Inicializa una lista para mantener los recubrimientos
		int min = this.k + 1;// Establece un valor inicial minimo de k+1
		int coverIndex = 0;//Para guardar el index del recubrimiento minimo de los vertices en la lista de posibles recubrimientos
		asignaciones+=4;
                for (int i = 0; i < posibleCovers.size(); i++) {// recorre todas las permutaciones posibles de la cubierta del vértice
			for (int j = 0; j < this.k; j++) {// pasa por k valores de la cobertura 
				this.removerAristas(posibleCovers.get(i).get(j), aristas);// elimina todas las aristas asociadas al vértice actual de la cubierta de vértices
				if (aristas.isEmpty()) {// determina si se ha encontrado una cubierta de vértices para el grafo
					if ((j + 1) < min) {// determina si la nueva cobertura de vértices es menor que el mínimo
						min = (j + 1);// si es así, establece el nuevo mínimo al tamaño de la cubierta de vértice
						coverIndex = i;// si es así, establece el valor del índice a la cubierta de vértice actual
                                                asignaciones+=2; 
					}
                                        comparaciones++;
				}
                                comparaciones+=2;
                                asignaciones++;
			}
                        comparaciones++;
			aristas = grafo.obtenerAristas();// restablece la lista de aristas para que contenga todas las aristas del gráfico, lista para la siguiente cobertura potencial de vértices
                        asignaciones+=2;
                        comparaciones++;
                }
                comparaciones++;
		if (min <= k) {//determina si la cubierta de vértices más pequeña encontrada cumple el requisito k
			for (int i = 0; i < min; i++){// recorre el número de vértices de la cubierta de vértices seleccionada
				cover.add(posibleCovers.get(coverIndex).get(i));//establece cada Vértice en la cubierta de Vértices seleccionada
                                asignaciones+=2;
                                comparaciones++;
                        }
                        comparaciones++; //Por la falsa del for
                        
                        
                        System.out.println("------------------------------------------------------------------------");
			System.out.println("----------Algoritmo FUERZA BRUTA---------------------------------------------------");
			System.out.println("Se ha encontrado una cobertura de fuerza bruta que satisface k = " + k);
			System.out.println("Los vértices de la cubierta más pequeña son: " + cover.toString());
                       
                        
                        long sumaAsignaciones = this.asignaciones + grafo.getAsignaciones();
                        long sumaComparaciones = this.comparaciones + grafo.getComparaciones();

                        System.out.println("Se contó una cantidad de: "+sumaAsignaciones+" asignaciones,"+" y: "+sumaComparaciones +" comparaciones");
                        System.out.println(" ");
                
                }               
                else{// si no se ha encontrado ninguna cubierta de vértice que cumpla el requisito k
                        
                        long sumaAsignaciones = this.asignaciones + grafo.getAsignaciones();
                        long sumaComparaciones = this.comparaciones + grafo.getComparaciones();

			System.out.println("No hay cobertura de fuerza bruta que satisfaga k = " + k + " ha sido encontrado.");                        
                        System.out.println("Se contó una cantidad de: "+sumaAsignaciones+" asignaciones,"+" y: "+sumaComparaciones +" comparaciones");
                        System.out.println("Asignaciones obtenidas del algoritmo: "+ this.asignaciones+ ", asignaciones obtenidas del grafico: "+ grafo.getAsignaciones());
                                        
                }
                comparaciones++; //por el if;
                
                
                
                Instant tiempoFinal = Instant.now();
                float sec = Duration.between(tiempoInicial,tiempoFinal).toMillis()/1000.0f;
                System.out.print("Duracion algoritmo fuerza bruta: ");// Muestra la duracion del fuerza bruta
                System.out.format("%.3f",sec);
                System.out.print(" milisegundos");
                System.out.println("\n------------------------------------------------------------------------");
		
                posibleCovers.clear();//Resetea la lista de posibles recubrimientos de vertices               
	}
	
	// Optimized brute force approach to finding a Vertex Cover of k or less Vertices
        /**
         * Algoritmo de fuerza bruta optimizado para encontrar una manera optima de recubrir un grafo
         * de 'k' o menos vertices
         */
	protected void fuerzaBrutaOptimizado() { //Muchas instrucciones duplicadas de fuerza bruta, entonces se omiten algunos comentarios
                this.grafo.resetCounters();
		Instant tiempoInicial = Instant.now();//No cuenta en la contabilizacion de pasos
		ArrayList<Vertice> v = new ArrayList<Vertice>();
		asignaciones++;
                for(int i = 0; i < this.k; i++) {
			v.add(new Vertice(i));
                        asignaciones+=4; //2 asignaciones en la creación del vértice
                        comparaciones++;
                }
                comparaciones++;
		boolean[] visit = new boolean[grafo.obtenerVertices().size()];
		asignaciones++; 
                for(int i = 0; i < grafo.obtenerVertices().size(); i++) {
			visit[i] = false;
                        asignaciones+=2;
                        comparaciones++;
		}
                comparaciones++;
		this.min = k + 1;
		cover = new ArrayList<Vertice>();                
		asignaciones+=2;
                this.fuerzaBrutaOptimizado(grafo.obtenerVertices(), this.k, v, -1, visit);// Llama a un metodo para determinar el recubrimiento minimo de los vertices
                if (!this.cover.isEmpty()) {// Verifica que si se haya encontrado un metodo que satisface las condiciones
			
                    
                        System.out.println("----------Algoritmo FUERZA BRUTA OPTIMIZADO------------------------------------------");
			System.out.println("Se ha encontrado una cobertura de fuerza bruta optimizada que satisface k = " + k);
			System.out.println("Los vertices de recubrimiento minimo son: " + this.cover.toString());		
                        
                        long sumaAsignaciones = this.asignaciones + grafo.getAsignaciones();
                        long sumaComparaciones = this.comparaciones + grafo.getComparaciones();
                        System.out.println("Se contó una cantidad de: "+sumaAsignaciones+" asignaciones,"+" y: "+sumaComparaciones +" comparaciones");
                
                        System.out.println(" ");
                }
                
                else{
                        comparaciones++; //la falsa el if

                        long sumaAsignaciones = this.asignaciones + grafo.getAsignaciones();
                        long sumaComparaciones = this.comparaciones + grafo.getComparaciones();

			System.out.println("No se encontro una cobertura de fuerza bruta optimizada que satisface k = " + k + " ha sido encontrado");
                        System.out.println("Se contó una cantidad de: "+sumaAsignaciones+" asignaciones,"+" y: "+sumaComparaciones +" comparaciones");
                        System.out.println("Asignaciones obtenidas del algoritmo: "+ this.asignaciones+ ", asignaciones obtenidas del grafico: "+ grafo.getAsignaciones());
                }
                comparaciones++; //por el if
                
                Instant tiempoFinal = Instant.now();
                float sec = Duration.between(tiempoInicial,tiempoFinal).toMillis()/1000.0f;
                System.out.print("Duracion algoritmo fuerza bruta optimizado: ");// Muestra la duracion del fuerza bruta optimizado
                System.out.format("%.3f",sec);
                System.out.print(" milisegundos");
                System.out.println("\n------------------------------------------------------------------------");
	}
	
        /**
         * determinar el recubrimiento minimo de los vertices
         * @param vertices
         * @param k
         * @param actual
         * @param actualTot
         * @param visit 
         */
	protected void fuerzaBrutaOptimizado(ArrayList<Vertice> vertices, int k, ArrayList<Vertice> actual,int actualTot , boolean[] visit) {
		ArrayList<Aristas> aristas = grafo.obtenerAristas(); //Crea una lista de las aristas
		asignaciones+=6; 
		if (actualTot < k-1) {//actualTot = -1 y se recorre hasta k-1
			if (actualTot < min) { //Mientras el actualTot sea menor que el minimo
				for (int j = 0; j < actual.size(); j++) {//Recorre el tamano de los vertices
					this.removerAristas(actual.get(j), aristas); //Elimina las aristas con el vertice mandado por el indice
					if (aristas.isEmpty()) { //Verifica que aristas no esté vacío
						if ((j + 1) < this.min) { 
							this.min = (j + 1); //Establece un nuevo valor para el minimo
                                                        asignaciones++;
							cover.clear(); //Limpia las posibles coberturas
							for (int i = 0; i <= j; i++) { 
								cover.add(actual.get(i)); //Agrega a la lista de recubrimientos un vertice
                                                                comparaciones++;
                                                                asignaciones+=2;
							}
                                                        comparaciones++; //La falsa del for
						}
                                                comparaciones++; //Por el if
					}
                                 comparaciones+=2;
				asignaciones++;
                                }
                         comparaciones++; //comparacion falsa del for   
			}
                        
                aristas = grafo.obtenerAristas(); //Obtiene todas las aristas del grafo
		asignaciones++;
                comparaciones++;	
		}
                else if (actualTot == k-1 && actualTot < min) {
                        for (int j = 0; j < actual.size(); j++) { //Para recorrer todo el tamano de la cantidad de vertices
				this.removerAristas(actual.get(j), aristas); //Elimina la arista que tiene un vertice en especifico
				if (aristas.isEmpty()) { //Verifica que las aristas no estén vacías
					if ((j + 1) < this.min) {
						this.min = (j + 1); //Se actualiza el minimo
						cover.clear(); //Se limpiar los recubrimientos
                                                asignaciones++;
						for (int i = 0; i <= j; i++) {
							cover.add(actual.get(i)); //Se agrega a la lista de recubrimientos, el vertice en el indice i
                                                        asignaciones+=2;
                                                        comparaciones++;
						}
                                                comparaciones++; //Por la falsa del for
					}
                                        comparaciones++;
				}
                                comparaciones+=2;
                                asignaciones++;
			}
                        comparaciones++; //Por la falsa del for 
			aristas = grafo.obtenerAristas();
                        asignaciones+=2; //1 por el return y otra por el aristas de arrriba;
			return;
		}
                else {
                        comparaciones++; // Por la falsa del primer if
                        comparaciones+=2; //Por las dos falsas del else if
			return;
		}
		comparaciones++; // Por la falsa del primer if
                comparaciones+=2; //Por las dos falsas del else if
		for (int i = 0; i < vertices.size(); i++) { //Recorre todos los vertices
			if(!visit[i]) {
				actual.set(++actualTot, vertices.get(i));
				visit[i] = true; //Se coloca el vertice en visitado
				fuerzaBrutaOptimizado(vertices, k, actual, actualTot, visit); //Se vuelve a enviar de forma recursiva con los datos actualizados
				visit[i] = false; //Se actualizan todos los vertices como false
				actualTot--; //Se le resta al contador
                                asignaciones+=3; //Sin contar actual.set pues no forma parte de la programación propia del proyecto
			}
                        comparaciones+=2;
                        asignaciones++;
		}
                comparaciones++; //Por el falso del for
	}
		
	/**
         * Recubrimiento del grafo utilizando la heuristicaVoraz
         */
	protected void heuristicaVoraz() {
                this.grafo.resetCounters();
		Instant tiempoInicial = Instant.now(); // Obtiene el tiempo actual de inicio
		
                
                ArrayList<Aristas> aristas = grafo.obtenerAristas(); // Obtiene todas las aristas del grafo
		ArrayList<Vertice> vertices = grafo.obtenerVertices(); // Obtiene todos los vertices del grafo
		cover = new ArrayList<Vertice>(); //Crea una lista de recubrimientos
                asignaciones+=3;
		while (!aristas.isEmpty()) {//Recorre mientras las aristas no estén vacías
			Vertice vertex = obtenerVerticeMaximo(vertices, aristas);//Obtiene el vertice con mayor cantidad de aristas
			cover.add(vertex);//Se agrega el vertice encontrado a la lista
			vertices.remove(vertex);// Elimina el vertice de la lista 
			this.removerAristas(vertex, aristas);//Elimina todas las aristas que contienen dicho vertice
                        asignaciones+=3;
                        comparaciones++;
                }
                comparaciones++;
		
                
                Instant tiempoFinal = Instant.now();
		System.out.println("----------Algoritmo HEURISTICA VORAZ--------------------------------------------------");
		System.out.println("Una heuristica voraz para el grafico ha sido encontrada:");
		System.out.println("Los vertices de la heuristica voraz son: " + cover.toString());
                long sumaAsignaciones = this.asignaciones + grafo.getAsignaciones();
                long sumaComparaciones = this.comparaciones + grafo.getComparaciones();
                System.out.println("Se contó una cantidad de: "+sumaAsignaciones+" asignaciones,"+" y: "+sumaComparaciones +" comparaciones");
                System.out.println(" ");
                float sec = Duration.between(tiempoInicial,tiempoFinal).toMillis()/1000.0f;
                System.out.print("Duracion algoritmo voraz: ");// Muestra la duracion del algoritmo voraz
                System.out.format("%.3f",sec);
                System.out.print(" milisegundos");
                System.out.println("\n------------------------------------------------------------------------");
        }
}
