/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ethasy3maquinabilletes;


import ethasy3maquinabilletes.Main.GeneralPanel;
import ethasy3maquinabilletes.Main.VentanaPrincipal;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;

/**
 *
 * @author Suplente
 */


public class Login extends GeneralPanel
     {
    static char LetrasDNI[]=new char[]{'t','r','w','a','g','m','y','f','p','d',
                                'x','b','n','j','z','s','q','v','h',
                                'l','c','k','e'};

        ImageIcon fondo;
        JLabel imgfondo;
        JLabel textDNI,textPSWD;
        JTextField inDNI;
        JPasswordField inPSWD;
        JButton Login,Registrar,Volver;
        VentanaPrincipal Padre;
        JLabel Error;
        ArrayList<String> LogOut;

        Login(int w,int h,VentanaPrincipal Parent)
        {
            LogOut = new ArrayList();
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
            Registrar.addActionListener(this);
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

            fondo=ethasy3maquinabilletes.Main.ResizeImage("img\\FondoLogin.png",w,h);
            imgfondo=new JLabel(fondo);
            imgfondo.setBounds(0, -25, 800, 600);
            add(imgfondo);


        }

        @Override
        void ClearText()
        {
            Error.setText("");
            inDNI.setText("");
            inPSWD.setText("");
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


        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==Volver)
            {
                Padre.PanelChanger(1, 0);
                return;
            }
            if(e.getSource()==Registrar)
            {
                Padre.PanelChanger(1, 2);
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
                    Statement mysts = (Statement) Padre.mycon.createStatement();
                    String sql = "select count(*),DNI,Contra from CLIENTE WHERE DNI=\'" + inDNI.getText() + "\'";
                    ResultSet rs = mysts.executeQuery(sql);
                    rs.first();
                    if(rs.getInt(1)==0)
                    {
                        SetError(5);
                        return;
                    }
                    if(!Arrays.equals(rs.getString(3).toCharArray(),Main.CodificarPassword(String.valueOf(inPSWD.getPassword())).toCharArray()))
                    {
                        SetError(6);
                        return;
                    }
                    SetError(7);
                    LogOut.add("LOGIN DE USUARIO");
                    LogOut.add(inDNI.getText());
                    Padre.LogThing(LogOut);
                    LogOut.clear();
                    Padre.setCliente(new Cliente(Padre,inDNI.getText()));
                    Padre.PanelChanger(1, 3);

                } catch (SQLException ex) {
                    
                }

            }
        }

     }

