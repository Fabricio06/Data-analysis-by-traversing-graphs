import java.util.Random;

/**
 *
 * @author Fabricio Porras y Carlos Solis
 * Esta clase  tiene metodos los cuales realizan los vertices aleatorios
 * al igual que las aristas 
 */
public class Aleatorio {
    private byte vertices; //Cantidad de vertices
    private short aristas; //Cantidad de aristas 
    
    /**
     * 
     * @param vertices
     * @param aristas 
     * Constructor de la clase
     */
    public Aleatorio(byte vertices, short aristas){
        this.vertices = vertices;
        this.aristas = aristas;
    }
    
    /**
     * 
     * @return el numero de vertices del objeto
     */
    public byte getVertices() {
        return this.vertices;
    }
    
    /**
     * 
     * @return el numero de aristas del objeto
     */
    public short getAristas() {
        return this.aristas;
    }
   
    /**
     * Metodo que se encarga de generar los vertices 
     * @param vertices el total de vertices 
     * @return La lista con todos los vertices creados y sus respectivos numeros
     */
    public Vertice[] verticesAleatorios(byte vertices){
        byte c = 0; //Incrementador de 1 hasta n vertices
        Vertice[] listaVerticesAleatorios = new Vertice[vertices]; //Se crea la lista de tamaño 'vertices' donde se van a agregar todos los vertices
        
        while(c<vertices){ //Ciclo para agregar desde 1 hasta n vertices
            listaVerticesAleatorios[c] = new Vertice(c+1); //Se crea y se agrega el vertice a la lista
            c++; //Se incrementa el indice de los enteros del vertice y del ciclo
        }
        return listaVerticesAleatorios; //Se devuelve la lista con los vertices ya formados
    }
    
    /**
     * Metodo encargado de enlazar vertices de manera aleatorios para crear las aristas
     * @param aristas
     * @param listaVertices
     * @return la lista con las aristas creadas 
     */
    public Aristas[] aristasAleatorios(short aristas,Vertice[] listaVertices){
        Random random = new Random();
        Aristas[] listaAleatoriaAristas = new Aristas[aristas]; //Se crea la lista donde van las aristas   
        
        for(int i=0; i<aristas; i++){ //Ciclo hastas las aristas totales que establecimos
            boolean flag = true; //Bandera que se utilizará para ver si se repiten aristas
            
            int v = (random.nextInt(listaVertices.length))+1; //Se obtiene un numero aleatorio dependiendo de los vertices
            int w = (random.nextInt(listaVertices.length))+1; //Se obtiene un numero aleatorio dependiendo de los vertices
                        
            for(int j=0; j < listaAleatoriaAristas.length; j++){               
                Aristas prove = listaAleatoriaAristas[j];
                
                if(prove==null){
                    break;
                }
                else{
                    int value1 = prove.getVertex1().getVertice(); //Arista(8,9)-> 8 
                    int value2 = prove.getVertex2().getVertice(); //Arista(8,9)-> 9 
                
                    if(((value1==v||value1==w)&&(value2==v||value2==w))){
                        flag = false;
                        break;
                    }
                    else if(listaAleatoriaAristas[j]==null){
                        break;
                    }             
                }

            }
            
            if(flag==false||v==w){ 
                i=i-1;
            }
            else{
                listaAleatoriaAristas[i] = new Aristas(v, w);
                listaAleatoriaAristas[i] = new Aristas(w, v);
            }
        }

        return listaAleatoriaAristas; //Devuelve la lista ya con las aristas enlazadas con sus respectivos vertices
    }
    


}    