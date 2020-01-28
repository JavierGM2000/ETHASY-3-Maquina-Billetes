/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ethasy3maquinabilletes;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.lang.Object;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
/**
 *
 * @author Suplente
 */
public class Main {

    static ImageIcon ResizeImage(String Path, int width, int height)
    {
        BufferedImage img=null;
        try {
            img = ImageIO.read(new File(Path));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image dimg = img.getScaledInstance(height, width,Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        return imageIcon;
    }

    static public class VentanaPrincipal extends JFrame
    {
        JPanel[] ListaPanel= new JPanel[8];
        VentanaPrincipal()
        {
            ListaPanel[0]=new Bienvenido(600,800);
            ListaPanel[0].setVisible(true);
            add(ListaPanel[0]);
        }
    }

    static public class Bienvenido extends JPanel
    {
        ImageIcon fondo;
        JLabel imgfondo;

        Bienvenido(int w,int h)
        {
            GridBagConstraints c = new GridBagConstraints();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            this.setBounds(0, 0, w, h);
            this.setLayout(new GridBagLayout());
            JButton test= new JButton();
            /*fondo=ResizeImage("img\\FondoBienvenida.png",w,h);
            imgfondo=new JLabel(fondo);
            imgfondo.setBounds(0, 0, w, h);*/
            test.setBounds(0,0,100,100);
            add(test,c);
            //add(imgfondo,c);
        }

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
