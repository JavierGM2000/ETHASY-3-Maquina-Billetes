/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ethasy3maquinabilletes;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.Object;
import javax.swing.*;
import java.awt.event.*;
/**
 *
 * @author Suplente
 */
public class Main {

    static public class VentanaPrincipal extends JFrame
    {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*MEGATEST*/
        VentanaPrincipal Princip= new VentanaPrincipal();
        Princip.setBounds(0,0,800,600);
        Princip.setVisible(true);
        Princip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Princip.setResizable(false);
    }

}
