package ethasy3maquinabilletes;


import ethasy3maquinabilletes.Main.VentanaPrincipal;
import ethasy3maquinabilletes.Main.GeneralPanel;
import ethasy3maquinabilletes.Main.VentanaPrincipal;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Suplente
 */
public class Registrar extends GeneralPanel
     {
    static char LetrasDNI[]=new char[]{'t','r','w','a','g','m','y','f','p','d',
                                'x','b','n','j','z','s','q','v','h',
                                'l','c','k','e'};
    static int DiasMes[] = new int[]{31,28,31,30,31,30,31,30,31,30,31};

         VentanaPrincipal Padre;

        JLabel imgfondo;
        JLabel DNI, Pass, ConfirmPass, Nombre, Apellido1, Apellido2,Genero, FechaNacimiento,Error;
        JTextField inDNI,inNombre,inApellido1, inApellido2,inDia,inmes,inAno;
        JPasswordField inPass1, inPass2;
        JRadioButton radio1,radio2,radio3;
        ButtonGroup bg;
        JButton Registro, Volver;
        ArrayList<String> LogOut;
        Registrar(int w,int h,VentanaPrincipal Parent)
        {
            LogOut = new ArrayList();

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
            radio1.setOpaque(false);
            add(radio1);
            bg.add(radio1);

            radio2 = new JRadioButton("Mujer");
            radio2.setBounds(50, 390, 200, 20);
            radio2.setOpaque(false);
            add(radio2);
            bg.add(radio2);

            radio3 = new JRadioButton("Otro");
            radio3.setBounds(50, 410, 200, 20);
            radio3.setOpaque(false);
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

            Volver = new JButton("Volver");
            Volver.setBounds(450, 500, 150, 50);
            Volver.addActionListener(this);
            add(Volver);

            ImageIcon fondo=ethasy3maquinabilletes.Main.ResizeImage("img\\FondoRegistro.png",w,h);
            imgfondo=new JLabel(fondo);
            imgfondo.setBounds(0, 0, 800, 600);
            add(imgfondo);

         }

        @Override
        void ClearText()
        {
          inDNI.setText("");
          inNombre.setText("");
          inApellido1.setText("");
          inApellido2.setText("");
          inDia.setText("");
          inmes.setText("");
          inAno.setText("");
          inPass1.setText("");
          inPass2.setText("");
          bg.clearSelection();
          Error.setText("");
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
            if(e.getSource()==Volver)
            {
                Padre.PanelChanger(2, 1);
            }

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

                    LogOut.add("REGISTRO USUARIO");
                    LogOut.add("DNI:"+inDNI.getText());
                    LogOut.add("Nombre:"+inNombre.getText());
                    LogOut.add("Apellido1:"+inApellido1.getText());
                    LogOut.add("Apellido2:"+inApellido2.getText());
                    LogOut.add("Genero:"+Sexo);
                    LogOut.add("FechaNac:"+ inAno.getText()+'-'+inmes.getText()+'-'+inDia.getText());
                    Padre.LogThing(LogOut);
                    LogOut.clear();
                    JOptionPane.showMessageDialog(null, "Usuario registrado correctamente");
                    Padre.PanelChanger(2, 1);

                } catch (SQLException ex) {
                    
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

