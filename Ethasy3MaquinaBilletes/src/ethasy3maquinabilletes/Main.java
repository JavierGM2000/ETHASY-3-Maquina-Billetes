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
        JPanel[] ListaPanel= new JPanel[8];
        VentanaPrincipal() throws SQLException, ClassNotFoundException
        {
            mycon = DriverManager.getConnection(url, "root", "");
            
            setLayout(null);
            this.setBackground(Color.red);
            //this.setBounds(0, 0, 600, 800);
            ListaPanel[0]=new BienvenidaVentana(800,600,this);
            ListaPanel[0].setBounds(0, 0, 800, 600);
            ListaPanel[0].setVisible(false);
            add(ListaPanel[0]);

            ListaPanel[1]=new Login(800,600,this);
            ListaPanel[1].setBounds(0, 0, 800, 600);
            ListaPanel[1].setVisible(false);
            add(ListaPanel[1]);
            
            ListaPanel[3]=new Registrar(800,600,this);
            ListaPanel[3].setBounds(0, 0, 800, 600);
            ListaPanel[3].setVisible(true);
            add(ListaPanel[3]);

            
        }

        void PanelChanger(int desde,int a_cual)
        {
            ListaPanel[desde].setVisible(false);
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
     static public class Registrar extends GeneralPanel
     {
         VentanaPrincipal Padre;

        JLabel DNI, Pass, ConfirmPass, Nombre, Apellido1, Apellido2,Genero, FechaNacimiento,Error;
        JTextField inDNI,inNombre,inApellido1, inApellido2,inDia,inmes,inAno;
        JPasswordField inPass1, inPass2;
        JRadioButton radio1,radio2,radio3;
        ButtonGroup bg;
        JButton Registro, volver;
        Registrar(int w,int h,VentanaPrincipal Parent)
        {
            Padre = Parent;
            setLayout(null);

            DNI = new JLabel("DNI:");
            DNI.setBounds(50, 50, 200, 15);
            add(DNI);

            inDNI = new JTextField();
            inDNI.setBounds(50, 70, 200, 20);
            add(inDNI);

            Pass = new JLabel("Contraseña:");
            Pass.setBounds(50, 100, 200, 15);
            add(Pass);

            inPass1 = new JPasswordField();
            inPass1.setBounds(50, 120, 200, 20);
            add(inPass1);

            ConfirmPass = new JLabel("Comfirme contraseña:");
            ConfirmPass.setBounds(50, 150, 200, 15);
            add(ConfirmPass);

            inPass2 = new JPasswordField();
            inPass2.setBounds(50, 170, 200, 20);
            add(inPass2);

            Nombre = new JLabel("Nombre:");
            Nombre.setBounds(50, 200, 200, 20);
            add(Nombre);

            inNombre = new JTextField();
            inNombre.setBounds(50, 220, 200, 20);
            add(inNombre);

            Apellido1 = new JLabel("Primer apellido:");
            Apellido1.setBounds(50, 250, 200, 20);
            add(Apellido1);

            inApellido1 = new JTextField();
            inApellido1.setBounds(50, 270, 200, 20);
            add(inApellido1);

            Apellido1 = new JLabel("Segundo apellido: (Opcional)");
            Apellido1.setBounds(50, 300, 200, 20);
            add(Apellido1);

            inApellido2 = new JTextField();
            inApellido2.setBounds(50, 320, 200, 20);
            add(inApellido2);

            Genero = new JLabel("Genero:");
            Genero.setBounds(50, 350, 200, 20);
            add(Genero);

            bg=new ButtonGroup();
            radio1 = new JRadioButton("Hombre");
            radio1.setBounds(50, 370, 200, 20);
            add(radio1);
            bg.add(radio1);

            radio2 = new JRadioButton("Mujer");
            radio2.setBounds(50, 390, 200, 20);
            add(radio2);
            bg.add(radio2);

            radio3 = new JRadioButton("Otro");
            radio3.setBounds(50, 410, 200, 20);
            add(radio3);
            bg.add(radio3);

            FechaNacimiento = new JLabel("Fecha nacimiento: (Dia/Mes/Año)");
            FechaNacimiento.setBounds(50, 430, 200, 20);
            add(FechaNacimiento);

            inDia = new JTextField();
            inDia.setBounds(50, 450, 30, 20);
            add(inDia);

            inmes = new JTextField();
            inmes.setBounds(90, 450, 30, 20);
            add(inmes);

            inAno = new JTextField();
            inAno.setBounds(130, 450, 60, 20);
            add(inAno);

            Registro = new JButton("Registrar");
            Registro.setBounds(50, 480, 200, 30);
            Registro.addActionListener(this);
            add(Registro);

            Error=new JLabel("Error");
            Error.setForeground(Color.RED);
            Error.setBounds(228, 520, 300, 20);
            add(Error);

         }

        void SetError(int ErrorCod)
        {
            switch(ErrorCod)
            {
                case (1):
                    Error.setText("El DNI tiene que tener 9 caracteres");
                    Error.setBounds(260, 70, 200, 20);
                    break;
                case (2):
                    Error.setText("El DNI no es correcto");
                    Error.setBounds(260, 70, 200, 20);
                    break;
                case (3):
                    Error.setText("Ese DNI ya esta en uso");
                    Error.setBounds(260, 70, 200, 20);
                    break;
                case (4):
                    Error.setText("La contraseña es demasiado larga");
                    Error.setBounds(260, 120, 200, 20);
                    break;
                case (5):
                    Error.setText("Por favor inserte contraseña");
                    Error.setBounds(260, 120, 200, 20);
                    break;
                case (6):
                    Error.setText("La contraseña es demasiado larga");
                    Error.setBounds(260, 170, 200, 20);
                    break;
                case (7):
                    Error.setText("Por favor inserte contraseña");
                    Error.setBounds(260, 170, 200, 20);
                    break;
                case (8):
                    Error.setText("Las contraseñas no son iguales");
                    Error.setBounds(260, 170, 200, 20);
                    break;
                case (9):
                    Error.setText("Por favor inserte un nombre");
                    Error.setBounds(260, 220, 200, 20);
                    break;
                case (10):
                    Error.setText("Nombre demasiado largo");
                    Error.setBounds(260, 220, 200, 20);
                    break;
                case (11):
                    Error.setText("Por favor inserte un apellido");
                    Error.setBounds(260, 270, 200, 20);
                    break;
                case (12):
                    Error.setText("Apellido demasiado largo");
                    Error.setBounds(260, 270, 200, 20);
                    break;
                case (13):
                    Error.setText("Apellido demasiado largo");
                    Error.setBounds(260, 270, 200, 20);
                    break;
                case (14):
                    Error.setText("Seleccione una opcion");
                    Error.setBounds(260, 390, 200, 20);
                    break;
                case (15):
                    Error.setText("No deje casillas vacias");
                    Error.setBounds(200, 450, 200, 20);
                    break;
                case (16):
                    Error.setText("Solo inserte numeros");
                    Error.setBounds(200, 450, 200, 20);
                    break;
                case (17):
                    Error.setText("Para registrarse hace falta tener 10 años");
                    Error.setBounds(200, 450, 200, 20);
                    break;
                case (18):
                    Error.setText("Los mayores de 100 viajan gratis");
                    Error.setBounds(200, 450, 200, 20);
                    break;
                case (19):
                    Error.setText("El mes no es valido  ");
                    Error.setBounds(200, 450, 200, 20);
                    break;
                case (20):
                    Error.setText("El día no es valido  ");
                    Error.setBounds(200, 450, 200, 20);
                    break;

            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==Registro)
            {
                try {
                    String SDNI = inDNI.getText();
                    if (SDNI.length() != 9) {
                        SetError(1);
                        return;
                    }
                    for (int i = 0; i < 8; i++) {
                        if (SDNI.toLowerCase().charAt(i) < '0' || SDNI.toLowerCase().charAt(i) > '9') {
                            SetError(2);
                        }
                    }
                    int NumDNI = Integer.parseInt(SDNI.substring(0, 8));
                    if (LetrasDNI[NumDNI % 23] != SDNI.toLowerCase().charAt(8)) {
                        SetError(2);
                        return;
                    }
                    Statement mysts = Padre.mycon.createStatement();
                    String sql = "select count(*),DNI,Contra from CLIENTE WHERE DNI=\'" + inDNI.getText() + "\'";
                    ResultSet rs = mysts.executeQuery(sql);
                    rs.first();
                    if (rs.getInt(1) > 0) {
                        SetError(3);
                        return;
                    }
                    if (inPass1.getPassword().length > 15) {
                        SetError(4);
                        return;
                    }
                    if (inPass1.getPassword().length == 0) {
                        SetError(5);
                        return;
                    }
                    if (inPass2.getPassword().length > 15) {
                        SetError(6);
                        return;
                    }
                    if (inPass2.getPassword().length == 0) {
                        SetError(7);
                        return;
                    }
                    if (inPass2.getPassword().length != inPass1.getPassword().length) {
                        SetError(8);
                        return;
                    }
                    if(!Arrays.equals(inPass1.getPassword(), inPass2.getPassword()))
                    {
                        SetError(8);
                        return;
                    }
                    if (inNombre.getText().length() == 0) {
                        SetError(9);
                        return;
                    }
                    if (inNombre.getText().length() > 15) {
                        SetError(10);
                        return;
                    }
                    if (inApellido1.getText().length() == 0) {
                        SetError(11);
                        return;
                    }
                    if (inApellido1.getText().length() > 15) {
                        SetError(12);
                        return;
                    }
                    if (inApellido2.getText().length() > 15) {
                        SetError(13);
                        return;
                    }
                    if(!radio1.isSelected()&&!radio2.isSelected()&&!radio3.isSelected())
                    {
                        SetError(14);
                        return;
                    }
                    if(inDia.getText().length() == 0 || inmes.getText().length()==0 || inAno.getText().length()==0)
                    {
                        SetError(15);
                        return;
                    }
                    if(!CheckText(inDia.getText()) || !CheckText(inmes.getText()) || !CheckText(inAno.getText()))
                    {
                        SetError(16);
                        return;
                    }
                    if(Integer.parseInt(inAno.getText())> Calendar.getInstance().get(Calendar.YEAR)-10)
                    {
                        SetError(17);
                        return;
                    }
                    if(Integer.parseInt(inAno.getText())< Calendar.getInstance().get(Calendar.YEAR)-100)
                    {
                        SetError(18);
                        return;
                    }
                    if(Integer.parseInt(inmes.getText())> 12 || Integer.parseInt(inmes.getText())== 0)
                    {
                        SetError(19);
                        return;
                    }
                    if(Integer.parseInt(inDia.getText())> DiasMes[Integer.parseInt(inmes.getText())-1] || Integer.parseInt(inmes.getText())== 0)
                    {
                        SetError(20);
                        return;
                    }
                    char Sexo='H';
                    if(radio1.isSelected())
                        Sexo = 'H';
                    if(radio2.isSelected())
                        Sexo = 'M';
                    if(radio3.isSelected())
                        Sexo = 'O';

                    sql = "INSERT INTO CLIENTE(DNI,Nombre,Apellido,Apellido2,Sexo,FechaNac,Contra)"
                            + "VALUES (\'"+inDNI.getText()+"\',\'"+inNombre.getText()+"\',\'"+inApellido1.getText()
                            + "\',\'"+inApellido2.getText()+"\',\'"+Sexo+"\',\'"+
                            inAno.getText()+'-'+inmes.getText()+'-'+inDia.getText()+"\',\'"+String.valueOf(inPass1.getPassword())+"\');";
                    mysts.execute(sql);

                    

                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        boolean CheckText(String Acomprobar)
        {
            for(int i=0; i<Acomprobar.length(); i++)
            {
                if(Acomprobar.charAt(i)<'0'||Acomprobar.charAt(i)>'9')
                {
                    return false;
                }
            }

            return true;
        }

     }

     static public class Login extends GeneralPanel
     {
        ImageIcon fondo;
        JLabel imgfondo;
        JLabel textDNI,textPSWD;
        JTextField inDNI;
        JPasswordField inPSWD;
        JButton Login,Registrar,Volver;
        VentanaPrincipal Padre;
        JLabel Error;

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

            inPSWD =  new JPasswordField();
            inPSWD.setBounds(300,270,200,50);
            inPSWD.setFont(inDNI.getFont());
            add(inPSWD);

            Login= new JButton("Login");
            Login.setBounds(248, 350, 300, 40);
            Login.setFont(inDNI.getFont());
            Login.addActionListener(this);
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

            Error=new JLabel("Error");
            Error.setForeground(Color.RED);
            Error.setBounds(228, 520, 300, 20);
            add(Error);

            fondo=ResizeImage("img\\FondoLogin.png",w,h);
            imgfondo=new JLabel(fondo);
            imgfondo.setBounds(0, -25, 800, 600);
            add(imgfondo);

            
        }

        void SetError(int ErrorCod)
        {
            switch(ErrorCod)
            {
                case (1):
                    Error.setText("El DNI tiene que tener 9 caracteres");
                    break;
                case (2):
                    Error.setText("El DNI no es correcto");
                    break;
                case (3):
                    Error.setText("La contraseña es demasiado larga");
                    break;
                case (4):
                    Error.setText("Por favor inserte algo en el campo contraseña");
                    break;
                case (5):
                    Error.setText("Este usuario no existe");
                    break;
                case (6):
                    Error.setText("La constraseña no es correcta");
                    break;
                case (7):
                    Error.setText("login bien");
                    break;
            }
        }


        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==Volver)
            {
                Padre.PanelChanger(1, 0);
                return;
            }
            if(e.getSource()==Login)
            {
                try {
                    String DNI = inDNI.getText();
                    if (DNI.length() != 9) {
                        SetError(1);
                        return;
                    }
                    for (int i = 0; i < 8; i++) {
                        if (DNI.toLowerCase().charAt(i) < '0' || DNI.toLowerCase().charAt(i) > '9') {
                            SetError(2);
                        }
                    }
                    int NumDNI = Integer.parseInt(DNI.substring(0, 8));
                    if (LetrasDNI[NumDNI % 23] != DNI.toLowerCase().charAt(8)) {
                        SetError(2);
                        return;
                    }
                    if (inPSWD.getPassword().length > 15) {
                        SetError(3);
                        return;
                    }
                    if (inPSWD.getPassword().length == 0) {
                        SetError(4);
                        return;
                    }
                    Statement mysts = Padre.mycon.createStatement();
                    String sql = "select count(*),DNI,Contra from CLIENTE WHERE DNI=\'" + inDNI.getText() + "\'";
                    ResultSet rs = mysts.executeQuery(sql);
                    rs.first();
                    if(rs.getInt(1)==0)
                    {
                        SetError(5);
                        return;
                    }
                    if(!Arrays.equals(rs.getString(3).toCharArray(), inPSWD.getPassword()))
                    {
                        SetError(6);
                        return;
                    }
                    SetError(7);

                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
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
