/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ethasy3maquinabilletes;


import ethasy3maquinabilletes.Main.GeneralPanel;
import ethasy3maquinabilletes.Main.VentanaPrincipal;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author Tarde
 */
public class ResumenTicket extends GeneralPanel{


    private JButton  comprar, volver;
    private JLabel rest, precio;
    private JTextField P;
    private JTextArea info;
    private VentanaPrincipal Padre;
    

    public ResumenTicket(int w,int h,VentanaPrincipal Parent){
        setLayout(null);
        Padre=Parent;
        rest=new JLabel("Resumen Ticket");
        rest.setBounds(40,-10,300,100);
        rest.setFont(rest.getFont().deriveFont(30f));
        add(rest);

        precio=new JLabel("Precio");
        precio.setBounds(300,400,100,100);
        precio.setFont(precio.getFont().deriveFont(15f));
        add(precio);

        info=new JTextArea("ABCD");
        info.setBounds(150,80,500,340);
        info.setFont(info.getFont().deriveFont(18f));
        info.setBorder(new LineBorder(Color.BLACK));
        info.setLineWrap(true);
        info.setEditable(false);
        add(info);

        P=new JTextField("€");
        P.setBounds(280,470,200,50);
        P.setFont(P.getFont().deriveFont(18f));
        P.setHorizontalAlignment(SwingConstants.RIGHT);
        add(P);

        comprar=new JButton("Comprar");
        comprar.setBounds(620,495,100,50);
        add(comprar);
        comprar.addActionListener(this);

        volver=new JButton("Volver");
        volver.setBounds(60,495,100,50);
        add(volver);
        volver.addActionListener(this);


    }

    @Override
    void ClearText() {
        P.setText(String.valueOf(Padre.currentBil.toDouble())+'€');
        info.setText(Padre.currentBil.toString());
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==comprar)
        {
            Padre.PanelChanger(5, 6);
        }
        if(e.getSource()==volver)
        {
            Padre.PanelChanger(5, 4);
        }
    }


}


