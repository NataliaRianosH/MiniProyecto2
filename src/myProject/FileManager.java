package myProject;

import java.io.*;
import java.util.ArrayList;
/**
 * This class is used for...
 * @autor Natalia Riaños Horta (2042568) rianos.natalia@correounivalle.edu.co
 * Miguel Ángel Ospina Hernández (2040634) miguel.ospina.hernandez@correounivalle.edu.co
 *
 * @version v.1.0.0 date: 08/02/2022
 */
public class FileManager {
    public static final String PATH = "src/myProject/files/diccionario.txt";
    private FileReader fileReader;
    private BufferedReader input;
    private FileWriter fileWriter,fileWriter2;
    private BufferedWriter output,output2;
    private ArrayList<String > usuarios,niveles;

    public FileManager(){
        usuarios=new ArrayList<String>();
        niveles=new ArrayList<String>();
        usuarios=lecturaFile("src/myProject/files/nombres.txt");
        niveles=lecturaFile("src/myProject/files/niveles.txt");
    }

    public ArrayList<String> getUsuarios() {
        return usuarios;
    }

    public ArrayList<String> getNiveles() {
        return niveles;
    }

    public ArrayList<String> lecturaFile(String direccion) { //Manejo de flujo de datos. lectura y escritura
        //adecuar la salida
        ArrayList <String> frases = new ArrayList<String>();
        //gestiona mejor la memoria que un arreglo estatico
        try {
            fileReader = new FileReader(direccion);
            input = new BufferedReader(fileReader);
            String line = input.readLine();

            while(line!=null){ //adiciona los elementos a la lista
                frases.add(line);
                line=input.readLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return frases;
    }
    public void insertarUsuario (String usuario){
        usuarios.add(usuario);
        niveles.add("1");
        try {
            fileWriter = new FileWriter("src/myProject/files/nombres.txt",true);
            output= new BufferedWriter(fileWriter);
            output.write(usuario);
                output.newLine();
            fileWriter2 = new FileWriter("src/myProject/files/niveles.txt",true);
            output2= new BufferedWriter(fileWriter2);
            output2.write("1");
            output2.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                output.close();
                output2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void borrarUsuario(int number){

         usuarios.remove(number); //lista
         niveles.remove(number);//lista usuarios

        try {
            fileWriter2 = new FileWriter("src/myProject/files/nombres.txt",false);
            output2= new BufferedWriter(fileWriter2);
            output2.write("");
            output2.close();

            for(int i=0;i<usuarios.size();i++){
                fileWriter = new FileWriter("src/myProject/files/nombres.txt",true);
                output= new BufferedWriter(fileWriter);
                output.write(usuarios.get(i));
                output.newLine();
                output.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileWriter2 = new FileWriter("src/myProject/files/niveles.txt",false);
            output2= new BufferedWriter(fileWriter2);
            output2.write("");
            output2.close();

            for(int i=0;i<niveles.size();i++){
                fileWriter = new FileWriter("src/myProject/files/niveles.txt",true);
                output= new BufferedWriter(fileWriter);
                output.write(String.valueOf(niveles.get(i)));
                output.newLine();
                output.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void subirNivel(int cualUsuario) {
        int nivelActual= Integer.parseInt(niveles.get(cualUsuario));
        if(nivelActual==10){

        }else{

            niveles.remove(cualUsuario);//lista usuarios
            nivelActual++;
            niveles.add(cualUsuario,Integer.toString(nivelActual));
            try {
                fileWriter2 = new FileWriter("src/myProject/files/niveles.txt",false);
                output2= new BufferedWriter(fileWriter2);
                output2.write("");
                output2.close();

                for(int i=0;i<niveles.size();i++){
                    fileWriter = new FileWriter("src/myProject/files/niveles.txt",true);
                    output= new BufferedWriter(fileWriter);
                    output.write(niveles.get(i));
                    output.newLine();
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

    }




    //Descubrir que hace eso
    /*
    public void escribirTexto(String linea) {
        try {
            fileWriter = new FileWriter(PATH, true);
            output = new BufferedWriter(fileWriter);
            output.write(linea);
            output.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }*/
}