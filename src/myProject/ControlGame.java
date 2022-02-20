package myProject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class is is where you will find the parts of the game and their functions
 * @autor Natalia Riaños Horta (2042568) rianos.natalia@correounivalle.edu.co
 * Miguel Ángel Ospina Hernández (2040634) miguel.ospina.hernandez@correounivalle.edu.co
 *
 * @version v.1.0.0 date: 05/02/2022
 */

public class ControlGame {
    private FileManager fileManager;
    private ArrayList<String> diccionario,
            palabrasTotales,
            palabrasDelNivel,
            palabrasIncorrectas,
            palabrasCorrectas,
            nombresUsuarios,
            nivelesUsuarios,
    sinnada;

    private int nivel=1, palabrasAmemorizar,cantPalabrasDelNivel,aciertosParaGanar,aciertos=0;
    private String nombreUsuario,usuarioaAeliminar;
/**
 * constructor, da los valores iniciales al jugego
 * */
    public ControlGame(){
        fileManager =new FileManager();
        palabrasTotales=new ArrayList<>();
        diccionario=new ArrayList<>();
        palabrasIncorrectas=new ArrayList<String>();
        palabrasCorrectas=new ArrayList<>();
        palabrasDelNivel=new ArrayList<>();
        sinnada=new ArrayList<>();
        diccionario=fileManager.lecturaFile("src/myProject/files/diccionario.txt");
        palabrasTotales= (ArrayList<String>) diccionario.clone(); //crea una copia de las palabras totales
        nombresUsuarios=fileManager.getUsuarios();
        nivelesUsuarios=fileManager.getNiveles();
     //   inicializarVariables();
      //   InitVariables();
    }
    /**
     * inicializa las variables segun el nivel en el que se encuentra
     * */
    public void inicializarVariables(){
        actualizarNivel();
        InitVariables();
        generarPalabrasDelNivel();
        generarPalabrasCorrectas();
    }
    /**
     * genera las palabras que se usarán en el nivel
     * */
    public void generarPalabrasDelNivel(){
        palabrasDelNivel.clear();
       // System.out.println("nivel en generar palabras del nivel"+nivel);
        Random random=new Random();
        int indexAleatorio; //index de la palabra que vamos a meter en el arry de palabras correctas

        for(int i=0; i<cantPalabrasDelNivel;i++){ //se va a repetir la cantidad de palabaras que hay en el nivel (correctas e incorrectas)
            indexAleatorio=random.nextInt(palabrasTotales.size()+0); //genera un aleatorio entre 0 y el tamaño del array de todas las palabras
            palabrasDelNivel.add(palabrasTotales.get(indexAleatorio)); //podemos añadir el elemento aleatorio sin problema
            palabrasTotales.remove(indexAleatorio);//eliminamos el elemento aleatorio de las palabras incorrectas
        }

    }
    /**
     * a partir de las palabras del nivel, selecciona unas que serán las correctas
     * */
    public void generarPalabrasCorrectas(){
        palabrasCorrectas.clear();
       // System.out.println("nivel en fenerar palabras correctas"+nivel);
        Random random=new Random();
        int indexAleatorio; //index de la palabra que vamos a meter en el arry de palabras correctas
        ArrayList<String> copiaPalabrasNivel= new ArrayList<>();
        copiaPalabrasNivel= (ArrayList<String>) palabrasDelNivel.clone();
       // System.out.println("PALABRAS A MEMORIAR genCorrectas"+palabrasAmemorizar);
        for(int i=0; i<palabrasAmemorizar;i++){ //se va a repetir la cantidad de palabaras que hay que memorizar en el nivel
            //es decir la cantidad que debe tener palabras correctas
            indexAleatorio=random.nextInt(copiaPalabrasNivel.size()+0); //genera un aleatorio entre 0 y el tamaño del array donde van a quedar las palabras incorrectas
            palabrasCorrectas.add(copiaPalabrasNivel.get(indexAleatorio)); //podemos añadir el elemento aleatorio sin problema
            copiaPalabrasNivel.remove(indexAleatorio);//eliminamos el elemento aleatorio de las palabras incorrectas
        }
     //   System.out.println("palabras correctas : " + palabrasCorrectas.size());
     //   palabrasCorrectas.forEach(System.out::println);

    }
    /**
     * actualiza el nuevo nivel
     * */
    public void subirNivel(){
      //  System.out.println("SE SUbio el nivel en el control");
        fileManager.subirNivel(nombresUsuarios.indexOf(nombreUsuario));
        actualizarNivel();
    }

    public void seleccionarUsuario(){
      //  inicializarVariables();
        if(nombresUsuarios.size()==0){
            crearUsuario();
        }else{
            String nombres[] =new String[nombresUsuarios.size()];
            for (int i=0;i<nombresUsuarios.size();i++){
                nombres[i]= String.valueOf(nombresUsuarios.get(i));
            }
            Object seleccion = JOptionPane.showInputDialog(
                    null,
                    "Seleccione su usuario",
                    "Jugadores",
                    JOptionPane.QUESTION_MESSAGE,
                    null, nombres,
                    "seleccion");

           // System.out.println("El usuario ha elegido "+seleccion);

            if(seleccion==null){
             //   System.out.println("canceló");
                nombreUsuario=null;
            }else {
                nombreUsuario=String.valueOf(seleccion);
                actualizarNivel();
                //nivel=Integer.parseInt(nivelesUsuarios.get(nombresUsuarios.indexOf(nombreUsuario)));
//                inicializarVariables();

            }
        }
      //  inicializarVariables();
    }
    public void actualizarNivel(){
    //    System.out.println("SE ACTUALIZÓ EL NIVEL EN CONTROL GAME");
        nivel=Integer.parseInt(nivelesUsuarios.get(nombresUsuarios.indexOf(nombreUsuario)));
    }

    public String getnomreUsuario(){
        return nombreUsuario;
    }
    public void crearUsuario(){
        //ializarVariables();
        String nuevo = JOptionPane.showInputDialog(null,"ingresa tu nombre","registro");
       // System.out.println(nuevo);
        if(nuevo==null){

        }else{
            // nombresUsuarios.add(nuevo);
            nombresUsuarios.forEach(System.out::println);
            fileManager.insertarUsuario(nuevo);
            nombresUsuarios=fileManager.lecturaFile("src/myProject/files/nombres.txt");
            nombresUsuarios.forEach(System.out::println);
        }

    //    inicializarVariables();
    }
    public void eliminarUsuario(){
     //   inicializarVariables();
        String nombres[] =new String[nombresUsuarios.size()];
        for (int i=0;i<nombresUsuarios.size();i++){
            nombres[i]= String.valueOf(nombresUsuarios.get(i));
        }

        Object seleccion = JOptionPane.showInputDialog(
                null,
                "Seleccione su usuario",
                "Jugadores",
                JOptionPane.QUESTION_MESSAGE,
                null, nombres,
                "seleccion");

     //   System.out.println("El usuario ha elegido "+seleccion);
        if(seleccion==null){
            usuarioaAeliminar=null;
        }else{
            usuarioaAeliminar=String.valueOf(seleccion);
            fileManager.borrarUsuario(nombresUsuarios.indexOf(seleccion));
            nombresUsuarios.remove(seleccion);
        }
       // inicializarVariables();
    }
    public String getUsuarioAeliminar(){
        return usuarioaAeliminar;
    }


    public void InitVariables(){
     //   System.out.println("nivel en init variables "+nivel);
        switch (nivel){
            case 1:
                palabrasAmemorizar=10;
                cantPalabrasDelNivel=20;
                aciertosParaGanar=14;
                break;
            case 2:
                palabrasAmemorizar=20;
                cantPalabrasDelNivel=40;
                aciertosParaGanar=28;
                break;
            case 3:
                palabrasAmemorizar=25;
                cantPalabrasDelNivel=50;
                aciertosParaGanar=37;
                break;
            case 4:
                palabrasAmemorizar=30;
                cantPalabrasDelNivel=60;
                aciertosParaGanar=48;
                break;
            case 5:
                palabrasAmemorizar=35;
                cantPalabrasDelNivel=70;
                aciertosParaGanar=56;
                break;
            case 6:
                palabrasAmemorizar=40;
                cantPalabrasDelNivel=80;
                aciertosParaGanar=68;
                break;
            case 7:
                palabrasAmemorizar=50;
                cantPalabrasDelNivel=100;
                aciertosParaGanar=90;
                break;
            case 8:
                palabrasAmemorizar=60;
                cantPalabrasDelNivel=120;
                aciertosParaGanar=108;
                break;
            case 9:
                palabrasAmemorizar=70;
                cantPalabrasDelNivel=140;
                aciertosParaGanar=133;
                break;
            case 10:
                palabrasAmemorizar=100;
                cantPalabrasDelNivel=100;
                aciertosParaGanar=200;
                break;
        }

    }

    public ArrayList<String> getPalabrasTotales() {
        return palabrasTotales;
    }

    public ArrayList<String> getPalabrasIncorrectas() {
        return palabrasIncorrectas;
    }

    public ArrayList<String> getPalabrasCorrectas() {
        return palabrasCorrectas;
    }

    public int getAciertosParaGanar() {
        return aciertosParaGanar;
    }

    public ArrayList<String> getPalabrasDelNivel() {
        return palabrasDelNivel;
    }

    public int getNivel() {
        actualizarNivel();
        return nivel;
    }
}
