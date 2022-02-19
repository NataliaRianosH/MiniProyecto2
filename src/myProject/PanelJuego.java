package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
/**
 * This class is used for realizar los eventos del juego despues de haber ingresado un usuario
 * @autor Natalia Riaños Horta (2042568) rianos.natalia@correounivalle.edu.co
 * Miguel Ángel Ospina Hernández (2040634) miguel.ospina.hernandez@correounivalle.edu.co
 *
 * @version v.1.0.0 date: 08/02/2022
 */

public class PanelJuego extends JPanel {
    public static final int WIDTH = 500;
    public static final int HEIGH = 200;
    Escucha escucha;
    private ControlGame controlJuego;
    private Timer unSegundo, sieteSegundos;
    private PanelNivel panelNivel;
    private JPanel contenedor; //contiene los botones
    private JButton botonEmpezar, estaba, noEstaba;
    private JLabel labelTiempo; //no lo usé

    private int nivel, indice=0,respuesta, caso=0, tiempo=7,
            seleccion=-1, //-1 no seleccionó nada, 0 noEstaba, 1 síEstaba
            aciertos;
    private boolean mostrarPalabras=true,
            esperandoRespuesta,
            tiempoTermino=false;

/**
 * constructor, da valores iniciales
 */
    public PanelJuego(ControlGame controlDeJuego){
        controlJuego=controlDeJuego;
        controlJuego.inicializarVariables();
        escucha=new Escucha();
        nivel=controlDeJuego.getNivel();
        //Set up JFrame Container's Layout
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(WIDTH, HEIGH));
        this.setBackground(new Color(190,226,248));
        //timer
        unSegundo = new Timer(1000,escucha);
        sieteSegundos =new Timer(7000,escucha);//son 8

        //botones
        botonEmpezar=new JButton("Empezar");
        botonEmpezar.addActionListener(escucha);

        estaba=new JButton("Estaba");
        estaba.setBackground(Color.WHITE);
        estaba.addActionListener(escucha);

        noEstaba=new JButton("no Estaba");
        noEstaba.setBackground(Color.WHITE);
        noEstaba.addActionListener(escucha);

        labelTiempo=new JLabel("HOLA");
        // this.add(labelTiempo,BorderLayout.BEFORE_FIRST_LINE);
        //paneles
        panelNivel=new PanelNivel(controlDeJuego);
        this.add(panelNivel,BorderLayout.NORTH);
        contenedor=new JPanel();
        contenedor.setLayout(new GridBagLayout());
        contenedor.setOpaque(false);
        contenedor.setPreferredSize(new Dimension(WIDTH,50));
        contenedor.add(botonEmpezar);
        this.add(contenedor,BorderLayout.SOUTH);
        contenedor.repaint();
        repaint();

    }
    public void fase1(){
        mostrarPalabras=true;
        indice=0;
        caso=1;
        aciertos=0;
        seleccion=-1;
        panelNivel.aumentarAciertos(aciertos);
        tiempo=5;
        contenedor.removeAll();
        contenedor.add(botonEmpezar);
        panelNivel.reset();
        repaint();
    }
    public void fase2(){
        indice=0;
        caso=2;
        seleccion=-1;
        tiempo=7;
        tiempoTermino=false;
        aciertos=0;
        contenedor.removeAll();
        contenedor.add(estaba, new GridBagConstraints());
        contenedor.add(noEstaba,new GridBagConstraints());
        panelNivel.reset2();
       // noEstaba.setVisible(true);
        contenedor.setOpaque(false);
        contenedor.repaint();
    }

    private void acierto(){
        if(seleccion==-1){
            System.out.println("no seleccionó nada");
        }else if(seleccion==respuesta){
            System.out.println("Respuesta correcta");
            aciertos++;
            panelNivel.aumentarAciertos(aciertos);
            System.out.println("ACIERTORS"+aciertos);
        }else{
            System.out.println("Respuesta Incorrecta");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        switch (caso){
            case 0:
                g.setColor(Color.BLACK);
                g.setFont(new Font("Comic Sans MS",10,20));
                g.drawString("Memoriza las palabras",(WIDTH/4)+3, HEIGH/2);
                break;
            case 1:
                g.setColor(Color.BLACK);
                g.setFont(new Font("Comic Sans MS",10,40));

                g.drawString(controlJuego.getPalabrasCorrectas().get(indice),(WIDTH/4)+3, HEIGH/2);
                System.out.println(controlJuego.getPalabrasCorrectas().get(indice)+indice);
                break;
            case 2:
                g.setFont(new Font("Comic Sans MS",10,40));
                g.drawString(controlJuego.getPalabrasDelNivel().get(indice),(WIDTH/4)+3, HEIGH/2);

                break;
            case 3:
                g.setFont(new Font("Comic Sans MS",10,20));
                g.drawString("ahora debes decir si la palabra estaba o no",10, HEIGH/2);
                break;
        }

    }
    private void actualizarRespuesta(){
       /**    System.out.println("la palabra a buscar es "+ controlJuego.getPalabrasDelNivel().get(indice));**/
       //   System.out.println("indice"+controlJuego.getPalabrasCorrectas().indexOf(controlJuego.getPalabrasDelNivel().get(indice)));
        if (controlJuego.getPalabrasCorrectas().indexOf(controlJuego.getPalabrasDelNivel().get(indice))==-1){ //if mal planteado
            respuesta = 0;//no taba
           System.out.println("la respuesta es NO porque " + controlJuego.getPalabrasDelNivel().get(indice)+
                    "No está en el panel palabras del juego y el indice fue:"+
                    controlJuego.getPalabrasCorrectas().indexOf(controlJuego.getPalabrasDelNivel().get(indice)));
            System.out.println("la respuesta es no");
        } else {
            respuesta = 1;//sí está
            System.out.println("la respuesta es Sí poque " + controlJuego.getPalabrasDelNivel().get(indice)+
                    "Sí está en el panel palabras del juego y el indice fue:"+
                    controlJuego.getPalabrasCorrectas().indexOf(controlJuego.getPalabrasDelNivel().get(indice)));
        }
        System.out.println(indice);

    }

    private void controlTiempo(){
        if(tiempo==0){
           System.out.println("el tiempo TERMINÓ es cero");
           esperandoRespuesta=false;
            tiempo=7;
        }else{
            //si el tiempo no es cero: esperamos una respuesta, el tiempo va disminuyendo
            System.out.println("Esperando respuesta= "+ tiempo);
            esperandoRespuesta=true;
            tiempo=tiempo-1;
        }
        labelTiempo.setText(Integer.toString(tiempo));
        panelNivel.reset2();
    }
    private void siguienteNivel(){
        System.out.println("acietos necesarios " +controlJuego.getAciertosParaGanar()+
        "tus aciertos"+aciertos);
        if(aciertos>=controlJuego.getAciertosParaGanar()){
            controlJuego.subirNivel();
            nivel++;
            unSegundo.stop();
            panelNivel.reset();
            int a= JOptionPane.showConfirmDialog(null, "LOGRASTE SUBIR DE NIVEL \n ¿Quieres seguir Jugando? nivel"+controlJuego.getNivel(),null,JOptionPane.YES_NO_OPTION);
            if(a==JOptionPane.YES_OPTION){
                controlJuego.inicializarVariables();
            }else{
                System.exit(0);
            }

        }else{
            unSegundo.stop();
            int a= JOptionPane.showConfirmDialog(null, "NO LOGRASTE SUBIR DE NIVEL \n ¿Quieres seguir Jugando? nivel"+controlJuego.getNivel(),null,JOptionPane.YES_NO_OPTION);
            if(a==JOptionPane.YES_OPTION){
                controlJuego.inicializarVariables();
            }else{
                System.exit(0);
            }
            controlJuego.inicializarVariables();
             }

      //  panelNivel.reset2();
        fase1();

    }

    private class Escucha implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(mostrarPalabras) {
                if (e.getSource() == botonEmpezar) {
                    controlJuego.getNivel();
                    caso = 1;
                    tiempo=5;
                    unSegundo.start();
                    sieteSegundos.start();
                    System.out.println("los timer empieza a correr"); //va mostrar el caso 1+
                    repaint();
                    botonEmpezar.removeActionListener(escucha);
                }

                if (e.getSource() == unSegundo) {
                    labelTiempo.setText(Integer.toString(tiempo));
                    panelNivel.reset();
                }

                if (e.getSource() == sieteSegundos) {
                    indice++;
                        if (indice == controlJuego.getPalabrasCorrectas().size()) {
                            //terminaron de mostrarse
                            unSegundo.stop();
                            caso = 3;
                            // contadorDeClick=1;
                            sieteSegundos.stop();
                            //   repaint();
                            indice = 0;
                            botonEmpezar.setText("continuar");
                            botonEmpezar.addActionListener(escucha);
                            mostrarPalabras=false;
                        }
                   repaint();

                }
            }else { //+++++++++++++++++ TURNO DEL USUARIO
                if (e.getSource() == botonEmpezar) {
                    tiempo=7;
                    System.out.println("PALABRAS CORRECTAS");
                    controlJuego.getPalabrasCorrectas().forEach(System.out::println);

                    System.out.println("PALABRAS NIVEL");
                    controlJuego.getPalabrasDelNivel().forEach(System.out::println);
                    indice = 0;
                    caso = 2;
                    actualizarRespuesta();
                    JOptionPane.showMessageDialog(null, "empecemos");
                    unSegundo.start();
                    fase2();
                    repaint();
                }
                controlTiempo();
                if (e.getSource() == unSegundo) {
                    // controlTiempo();
                    panelNivel.reset2();
                    if (tiempo == 0) {
                        System.out.println("EL TIEMPO PARA SELECCIONAR TERMINÓ");
                        unSegundo.stop();
                        tiempoTermino = true;
                        System.out.println(seleccion);
                    }
                }

                if (tiempoTermino){ //ya terminó el tiempo para elegir
                    if (indice < controlJuego.getPalabrasDelNivel().size()-1) {
                        //hay otra palabra pero primero hay que ver si acertó la anterior
                        acierto(); //respuestacorrecta
                        //activar el action listener
                        noEstaba.addActionListener(escucha);
                        noEstaba.setBackground(Color.WHITE);
                        estaba.addActionListener(escucha);
                        estaba.setBackground(Color.WHITE);
                        unSegundo.start();
                        seleccion = -1; //como va aelegir entonces se pone -1
                        tiempoTermino = false; //el tiempo no ha terminado
                        unSegundo.stop();
                        panelNivel.reset2();
                        JOptionPane.showMessageDialog(null, "siguiente palabra");
                        unSegundo.restart();
                        indice++;
                        actualizarRespuesta();
                        repaint();
                    } else { //si el indice no es menor, el indice es mayor o igual

                            System.out.println("NO HAY MÁS PALABRAS");
                            acierto();
                            //se debe hacer lo mismo pero sin aumentar el indice
                            caso=3;
                           // fase1();
                            siguienteNivel();
                            panelNivel.reset2();
                            unSegundo.stop();
                            tiempoTermino = true;
                            noEstaba.removeActionListener(escucha);
                            estaba.removeActionListener(escucha);
                            repaint();
                            //DEBE PARAR LA EJECUCION


                    }
                } else { // aun puede elegir

                        if (seleccion != -1) { //ya eligió algo
                            noEstaba.removeActionListener(escucha);
                            estaba.removeActionListener(escucha);
                        } else { //mirar si elige
                            if (e.getSource() == noEstaba) {
                                noEstaba.setBackground(Color.gray);
                                seleccion = 0;
                                System.out.println("No estaba ");
                                noEstaba.removeActionListener(escucha);
                                estaba.removeActionListener(escucha);
                            } else if (e.getSource() == estaba) {
                                estaba.setBackground(Color.gray);
                                seleccion = 1;
                                System.out.println("estaba");
                                noEstaba.removeActionListener(escucha);
                                estaba.removeActionListener(escucha);
                            } else {
                                seleccion = -1;
                            }
                        }
                }
            }
        }
    }
}
