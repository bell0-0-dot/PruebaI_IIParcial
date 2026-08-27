
package prueb1_iiparcial;
import java.io.File;
import java.util.Scanner;
public class Prueb1_IIParcial {
    
    private static int archivosTxt=0;
    private static int archivosJava=0;
    private static int archivosPDF=0;
    private static int otrosArchivos=0;
    
    private static void contarArchivos(File ruta){
        File []archivos=ruta.listFiles();
        if(ruta==null){
            return;
        }
        for (File archivo: archivos){
            if(archivo.isDirectory()){
                 System.out.println("️ No se pudo leer: " + ruta.getAbsolutePath());
                contarArchivos(archivo);
            }else if(archivo.isFile()){
                clasificar(archivo);
            }
        }
        
    }
    
    private  static void clasificar(File archivo){
        
        String nombre=archivo.getName().toLowerCase();
        if(nombre.endsWith(".txt")){
            archivosTxt++;
        }else if(nombre.endsWith(".java")){
            archivosJava++;
        }else if(nombre.endsWith(".pdf")){
            archivosPDF++;
        }else{
            otrosArchivos++;
        }
        
    }
    
    private static void buscarArchivo(int indice, File[] archivos, String archivoBuscado, boolean[]encontrado){
       
         if (indice >= archivos.length) {
             
            return;
        }
         File archivo=archivos[indice];
         String busquedad=archivoBuscado.toLowerCase();
         if (archivo.isDirectory()) {
            buscarArchivosPorNombre(archivo, archivoBuscado, encontrado);
        } else if (archivo.isFile()) {
   
            String nombreMin = archivo.getName().toLowerCase();
        if (nombreMin.contains(busquedad)) {
            System.out.println(archivo.getAbsolutePath());
            encontrado[0] = true;
        }
    }
         buscarArchivo(indice + 1,archivos, archivoBuscado, encontrado);
        
    }
    private static void buscarArchivosPorNombre(File directorio, String txtBusqueda,boolean[]encontrado){
    
        File[] archivos = directorio.listFiles();
        if (archivos == null) {
            return;
        }
        buscarArchivo(0,archivos, txtBusqueda, encontrado);
    }
    
    private static String obtenerExtension(String nombre){
        
        int posiciones=nombre.lastIndexOf('.');
        if(posiciones==1||posiciones==nombre.length()-1);
        return "";
        
        
    }
    
    public static void main(String[] args) {
       Scanner leer=new Scanner(System.in);
       File rutaRaiz=null;
       String ruta;
       boolean rutaValida = false;
       
       do{
        System.out.println("Ingrese la ruta a analizar: ");
        ruta=leer.nextLine();
        
        if (!rutaRaiz.exists()) {
                System.out.println("Error: la ruta ingresada no existe. Ingrese una existente");
            } else if (!rutaRaiz.isDirectory()) {
                System.out.println("Error: la ruta ingresada corresponde a un archivo, no a un directorio. Intente nuevamente.");
            } else {
                rutaValida = true;
            }
        }while(!rutaValida);
        rutaRaiz=new File(ruta);
        
        
        System.out.println("Ingrese el archivo a buscar: ");
        String TxtBusquedad=leer.nextLine();
        
        contarArchivos(rutaRaiz);
        System.out.println("Analisis d archvos");
        System.out.println("De tipo '.txt' existen: "+archivosTxt+" achivos");
        System.out.println("De tipo '.java' existen: "+archivosJava+" achivos");
        System.out.println("De tipo '.pdf' existen: "+archivosPDF+" achivos");
        System.out.println("De tipo 'otros' existen: "+otrosArchivos+" achivos");
        
        System.out.println("----Archivos eencontrados---");
        boolean encontrado[]={false};
        buscarArchivosPorNombre(rutaRaiz, TxtBusquedad, encontrado);
        
         if (!encontrado[0]) {
            System.out.println("No se encontraron archivos con esa extension");
        }
    }
    
}
