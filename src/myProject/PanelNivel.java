package myProject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is used for know at what level you are
 * @autor Natalia Riaños Horta (2042568) rianos.natalia@correounivalle.edu.co
 * Miguel Ángel Ospina Hernández (2040634) miguel.ospina.hernandez@correounivalle.edu.co
 *
 * @version v.1.0.0 date: 11/02/2022
 */

public class PanelNivel extends JPanel{
    private JLabel labelNivel, labelAciertos, labeltiempo;
    private Timer timer;
    private int nivel, acierto, tiempo=5,tiempo2=7;
    public static final int WIDTH = 600;
    public static final int HEIGH = 30;
    private ControlGame controlDeJuego;

    public PanelNivel(ControlGame controlDelJuego){
    controlDeJuego=controlDelJuego;
        this.setPreferredSize(new Dimension(WIDTH, HEIGH));
        this.setLayout(new BorderLayout());

        this.setBackground((Color.WHITE));
        nivel=controlDeJuego.getNivel();

        labelNivel=new JLabel("    Nivel: "+nivel);
        labelAciertos = new JLabel("                 " +
                "                  " +
                "                       " +
                "           Aciertos: " + acierto);
        labeltiempo = new JLabel("Tiempo: " + tiempo + "    ");
        this.add(labelNivel,BorderLayout.WEST);
        this.add(labeltiempo,BorderLayout.EAST);
        this.add(labelAciertos,BorderLayout.CENTER);
    }

    public void reset(){
        if(tiempo>0){
            labeltiempo.setText("Tiempo: " + tiempo + "    ");
            labelAciertos.setText("                 " +
                    "                  " +
                    "                       " +
                    "           Aciertos: " + acierto);
            tiempo=tiempo-1;
        }else {
            tiempo=5;
            labeltiempo.setText("Tiempo: " + tiempo + "    ");
        }
        controlDeJuego.actualizarNivel();
        nivel=controlDeJuego.getNivel();
        labelNivel.setText("    Nivel: "+nivel);
    }

    public void reset2(){
        if(tiempo2>0){
            labeltiempo.setText("Tiempo: " + tiempo2 + "    ");
            labelAciertos.setText("                 " +
                    "                  " +
                    "                       " +
                    "           Aciertos: " + acierto);
            tiempo2=tiempo2-1;
        }else {
            tiempo2=7;
            labeltiempo.setText("Tiempo: " + tiempo2 + "    ");
        }
      //  controlDeJuego.actualizarNivel();
        nivel=controlDeJuego.getNivel();
        labelNivel.setText("    Nivel: "+nivel);
    }

    public int getTiempo() {
        return tiempo;
    }
    public void aumentarAciertos(int aciertos){
        acierto=aciertos;
    }
   // public void aumentarTiempo(int tiempoNuevo){ tiempo=tiempoNuevo;}
}
