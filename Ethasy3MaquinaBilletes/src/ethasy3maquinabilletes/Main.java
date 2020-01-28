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
 public class Main extends JFrame implements ActionListener{

    static public class VentanaPrincipal extends JFrame
    {

       private JButton consultar,comprar;
       
       public Main(){

           consultar=new JButton();
           consultar.setBounds(30,50,170,170);
           add(consultar);
           consultar.addActionListener(this);

           comprar=new JButton();
           comprar.setBounds(200,50,170,170);
           add(comprar);
           comprar.addActionListener(this);
         

        }

   

   
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
}
