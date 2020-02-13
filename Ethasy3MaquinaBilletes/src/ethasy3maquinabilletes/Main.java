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
import java.sql.*;
import java.util.Arrays;
import java.util.Calendar;
/**
 *
 * @author Suplente
 */
public class Main {

    static char LetrasDNI[]=new char[]{'t','r','w','a','g','m','y','f','p','d',
                                'x','b','n','j','z','s','q','v','h',
                                'l','c','k','e'};
    static int DiasMes[] = new int[]{31,28,31,30,31,30,31,30,31,30,31};

    public static ImageIcon ResizeImage(String Path, int width, int height)
    {
        BufferedImage img=null;
        try {
            img = ImageIO.read(new File(Path));
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image dimg = img.getScaledInstance(width, height,Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        return imageIcon;
    }

    static public class VentanaPrincipal extends JFrame
    {
        String url="jdbc:mysql://localhost:3306/ethasy3";
        Connection mycon;
        GeneralPanel[] ListaPanel= new GeneralPanel[8];
        VentanaPrincipal() throws SQLException, ClassNotFoundException
        {
            mycon = DriverManager.getConnection(url, "root", "");
            
            setLayout(null);
            this.setBackground(Color.red);
            //this.setBounds(0, 0, 600, 800);
            ListaPanel[0]=new BienvenidaVentana(800,600,this);
            ListaPanel[0].setBounds(0, 0, 800, 600);
            ListaPanel[0].setVisible(true);
            add(ListaPanel[0]);

            ListaPanel[1]=new Login(800,600,this);
            ListaPanel[1].setBounds(0, 0, 800, 600);
            ListaPanel[1].setVisible(false);
            add(ListaPanel[1]);
            
            ListaPanel[2]=new Registrar(800,600,this);
            ListaPanel[2].setBounds(0, 0, 800, 600);
            ListaPanel[2].setVisible(false);
            add(ListaPanel[2]);
            
                    
            ListaPanel[5]=new EscogerTicket(800,600,this);
            ListaPanel[5].setBounds(0, 0, 800, 600);
            ListaPanel[5].setVisible(true);
            add(ListaPanel[5]);

            ListaPanel[3]=new SeleccionarOperacion(800,600,this);
            ListaPanel[3].setBounds(0, 0, 800, 600);
            ListaPanel[3].setVisible(false);
            add(ListaPanel[3]);

            ListaPanel[4]=new EscogerTicket(800,600,this);
            ListaPanel[4].setBounds(0, 0, 800, 600);
            ListaPanel[4].setVisible(false);
            add(ListaPanel[4]);

            
        }

        void PanelChanger(int desde,int a_cual)
        {
            ListaPanel[desde].setVisible(false);
            ListaPanel[a_cual].ClearText();
            ListaPanel[a_cual].setVisible(true);
        }
    }
     static public class GeneralPanel extends JPanel implements ActionListener
     {

         void ClearText()
         {
         }

        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

     }
    
     

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // TODO code application logic here
        /*MEGATEST*/
        VentanaPrincipal Princip= new VentanaPrincipal();
        Princip.setBounds(0,0,800,600);
        Princip.setVisible(true);
        Princip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Princip.setResizable(false);
    }
}
