package myProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is the main program to run
 * @autor Natalia Riaños Horta (2042568) rianos.natalia@correounivalle.edu.co
 * Miguel Ángel Ospina Hernández (2040634) miguel.ospina.hernandez@correounivalle.edu.co
 *
 * @version v.1.0.0 date: 05/02/2022
 */
public class GUIinicio extends JFrame {

    private Header headerProject;
    private Timer timer;
    private int contador, c=0;
    private ControlGame controlJuego;
    // Variables declaration - do not modify
    private JButton jugar,nuevoJugador,eliminarJugador;
    private JLabel titulo;
    private Escucha escucha;
    private JPanel panel;
    private boolean empezarJuego;
    private  PanelJuego panelJuego;

    /**
     * Constructor of GUI class
     */
    public GUIinicio(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("I know that world");
        // this.setSize(200,100);
        this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {

        //Set up JFrame Container's Layout
        panel=new JPanel();
        controlJuego=new ControlGame();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        panel.setBackground(new Color(190,226,248));


        jugar = new JButton();


        escucha=new Escucha();
        nuevoJugador = new JButton();
        titulo = new JLabel();
        eliminarJugador = new JButton();
        empezarJuego=false;
        panel.setLayout(new GridBagLayout());

        jugar.setText("Jugar");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.anchor = constraints.NORTHWEST;
        constraints.insets = new Insets(50, 40, 67, 0);
        jugar.addActionListener(escucha);
        panel.add(jugar, constraints);

        nuevoJugador.setText("Nuevo Jugador");
        nuevoJugador.addActionListener(escucha);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.insets = new Insets(50, 51, 67, 0);
        panel.add(nuevoJugador,constraints);

        titulo.setFont(new Font("Comic Sans MS", 3, 24)); // NOI18N
        titulo.setText("I KNOW THAT WORLD");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.ipadx = 9;
        constraints.ipady = 6;
        constraints.anchor = constraints.NORTHWEST;
        constraints.insets = new Insets(70, 70, 0,  0);
        panel.add(titulo,constraints);

        eliminarJugador.setText("Eliminar Jugador");
        eliminarJugador.addActionListener(escucha);
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        constraints.anchor = constraints.NORTHWEST;
        constraints.insets = new Insets(50, 35, 67, 19);
        panel.add(eliminarJugador,constraints);
        this.add(panel);
    }
    public void reset(){
        this.remove(panel);
        panelJuego = new PanelJuego(controlJuego);
        this.add(panelJuego);

        pack();
      //  this.setResizable(false);
        repaint();
    }


    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line hen
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            GUIinicio miProjectGUI = new GUIinicio();
        });
    }


    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getSource()==jugar){
                controlJuego.seleccionarUsuario();
                if(controlJuego.getnomreUsuario()==null){
                }else{
                    System.out.println("El nivel es: " + controlJuego.getNivel());
                    reset();
                }
            }
            if(e.getSource()==nuevoJugador){
                controlJuego.crearUsuario();
            }
            if(e.getSource()==eliminarJugador){
                controlJuego.eliminarUsuario();

            }

        }
    }

}
