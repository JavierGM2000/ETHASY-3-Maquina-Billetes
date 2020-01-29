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
        Image dimg = img.getScaledInstance(width, height,Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        return imageIcon;
    }

    static public class VentanaPrincipal extends JFrame
    {
        JPanel[] ListaPanel= new JPanel[8];
        VentanaPrincipal()
        {
            setLayout(null);
            this.setBackground(Color.red);
            //this.setBounds(0, 0, 600, 800);
            ListaPanel[0]=new Bienvenido(800,600,this);
            ListaPanel[0].setBounds(0, 0, 800, 600);
            ListaPanel[0].setVisible(true);
            add(ListaPanel[0]);

            ListaPanel[1]=new Login(800,600,this);
            ListaPanel[1].setBounds(0, 0, 800, 600);
            ListaPanel[1].setVisible(false);
            add(ListaPanel[1]);
        }

        void PanelChanger(int desde,int a_cual)
        {
            ListaPanel[desde].setVisible(false);
            ListaPanel[a_cual].setVisible(true);
        }
    }

    static public class Bienvenido extends JPanel implements ActionListener
    {
        ImageIcon fondo;
        JLabel imgfondo;
        JButton Cambiar;
        VentanaPrincipal Padre;

        Bienvenido(int w,int h,VentanaPrincipal Parent)
        {
            Padre=Parent;
            setLayout(null);
            this.setBackground(Color.BLUE);
            this.setPreferredSize(new Dimension(w, h));
            //JButton test= new JButton();
            fondo=ResizeImage("img\\FondoBienvenida.png",w,h);
            imgfondo=new JLabel(fondo);
            imgfondo.setBounds(0, -25, 800, 600);
            add(imgfondo);

            Cambiar = new JButton();
            Cambiar.setBounds(0,0,800,600);
            Cambiar.setVisible(true);
            Cambiar.addActionListener(this);
            Cambiar.setLayout(null);
            Cambiar.setOpaque(false);
            Cambiar.setContentAreaFilled(false);
            add(Cambiar);
        }

        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==Cambiar)
            {
                Padre.PanelChanger(0, 1);
                System.out.println("Salir Bienvenida");
            }
        }

    }

     static public class Login extends JPanel implements ActionListener
     {
        ImageIcon fondo;
        JLabel imgfondo;
        JLabel textDNI,textPSWD;
        JTextField inDNI, inPSWD;
        JButton Login,Registrar,Volver;
        VentanaPrincipal Padre;

        Login(int w,int h,VentanaPrincipal Parent)
        {
            Padre = Parent;
            setLayout(null);
            this.setBackground(Color.WHITE);

            textDNI = new JLabel("DNI:");
            textDNI.setFont(new Font(textDNI.getFont().getName(), Font.PLAIN, 30));
            textDNI.setBounds(260, 120, 100, 50);
            add(textDNI);

            inDNI =  new JTextField();
            inDNI.setBounds(300,170,200,50);
            inDNI.setFont(new Font(inDNI.getFont().getName(), Font.PLAIN, 20));
            add(inDNI);

            textPSWD = new JLabel("Contraseña:");
            textPSWD.setFont(textDNI.getFont());
            textPSWD.setBounds(260, 220, 300, 50);
            add(textPSWD);

            inPSWD =  new JTextField();
            inPSWD.setBounds(300,270,200,50);
            inPSWD.setFont(inDNI.getFont());
            add(inPSWD);

            Login= new JButton("Login");
            Login.setBounds(248, 350, 300, 40);
            Login.setFont(inDNI.getFont());
            add(Login);

            Registrar= new JButton("Registrarse");
            Registrar.setBounds(248, 400, 300, 40);
            Registrar.setFont(inDNI.getFont());
            add(Registrar);

            Volver= new JButton("Volver");
            Volver.setBounds(275, 460, 250, 60);
            Volver.setFont(inDNI.getFont());
            Volver.addActionListener(this);
            add(Volver);

            fondo=ResizeImage("img\\FondoLogin.png",w,h);
            imgfondo=new JLabel(fondo);

            imgfondo.setBounds(0, -25, 800, 600);
            add(imgfondo);

            
        }

        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==Volver)
            {
                Padre.PanelChanger(1, 0);
            }
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
